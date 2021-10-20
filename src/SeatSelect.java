import java.util.ArrayList;

public class SeatSelect {

    public static void SeatMain(RunningInfo ri) {
        System.out.println("===== 영화예매 ======\r\n"
                + "----------- 선택한 영화 정보 -----------");
        System.out.println(ri.getDate()+" | "+ri.getTime()+" | "+ ri.getTheater()+"관 | "+ri.getMovieName());
        System.out.print("예매 인원 >>>");
        int ppl = InputRule.rsrvPplInput();

        ReserveInfo[] rsiArr = ri.getReserve();
        String theater = ri.getTheater();
        //영화관 정보를 받아와야 합니다.*****
        int theaterX = 10;
        int theaterY = 10;

        seatPrint(ri);
        String seatInput = null;
        Pair[] rsSeat = new Pair[ppl];
        for(int i = 0; i < ppl; i++) {
            System.out.print("좌석을 선택해 주세요["+i+"/"+ppl+"]>>>");
            seatInput = InputRule.SeatRule();
            int x = seatInput.charAt(0) - 'a';
            int y = 0;
            int result = 0;
            if(seatInput.length() == 2) y = seatInput.charAt(1);
            else y = seatInput.charAt(1) * 10 + seatInput.charAt(2);

            if(theaterX <= x && theaterY <= y){
                for(ReserveInfo rsi : rsiArr){
                    String[] seats = rsi.getSeat();
                    for(String seat:seats){
                        if(seatInput.equals(seat)){
                            result = -1; // 이미 선택된 자리
                        }
                    }
                }
            }else{
                result = -2;
            }

            if(result == 0){
                rsSeat[i] = new Pair(x, y);
            }else if(result == -1){
                System.out.println("이미 선택된 좌석입니다.");
                i--;
            }
            else{
                System.out.println("해당 좌석이 존재하지 않습니다.");
                i--;
            }
        }
        seatReservedPrint(ri,rsSeat);
        System.out.print("선택하신 좌석 C6 C7이 맞습니까?(y/n) >>>");
        int yorn = InputRule.YesOrNo();
        if(yorn == 1) {
            // 예약 성공
        }
        else {
            // 예약 실패
        }
    }

    public static void seatPrint(RunningInfo ri) {
        int row = 50;
        int col = 50;
        //Theater의 정보 필요*****
        ReserveInfo[] rsiArr = ri.getReserve();
        int count = 0;
        for(ReserveInfo rsi : rsiArr){
            String[] seatArr = rsi.getSeat();
            for(String seat : seatArr)
                count++;
        }
        Pair[] pair = new Pair[count];
        int s = 0;
        for(ReserveInfo rsi : rsiArr){
            String[] seatArr = rsi.getSeat();
            for(String seat : seatArr){
                int tmpx = seat.charAt(0) - 'A';
                int tmpy = 0;
                if(seat.length() == 2) tmpy = seat.charAt(1) - '0';
                else tmpy = seat.charAt(1) * 10 + seat.charAt(2);
                pair[s++] = new Pair(tmpx, tmpy);
            }
        }

        int[][] seat = new int[row][col]; // 좌석 배열 초기화

        for(int i = 0; i < count; i++) { // 예약된 좌석 >> 1
            int _row = pair[i].getRow();
            int _col = pair[i].getCol() - 1;
            System.out.println(_row+" "+_col);
            seat[_row][_col] = 1;
        }

        System.out.println("□: 선택 가능 ▩: 예매 완료");
        System.out.println("  ");

        for(int i = 0; i < col; i++) {
            System.out.print(i+" ");
        }
        System.out.println();

        for(int i = 0; i < row; i++) {
            char alpRow = (char)(i + 65);
            System.out.print(alpRow + " ");
            for(int j = 0; j < col; j++) {
                System.out.print(seat[i][j] == 1 ? "▩ " : "□ ");
            }
            System.out.println();
        }
    }

    public static void seatReservedPrint(RunningInfo ri, Pair[] rsPair) {
        int row = 10;
        int col = 10;
        //Theater의 정보 필요*****
        ReserveInfo[] rsiArr = ri.getReserve();
        int count = 0;
        for(ReserveInfo rsi : rsiArr){
            String[] seatArr = rsi.getSeat();
            for(String seat : seatArr)
                count++;
        }
        Pair[] pair = new Pair[count];
        int s = 0;
        for(ReserveInfo rsi : rsiArr){
            String[] seatArr = rsi.getSeat();
            for(String seat : seatArr){
                int tmpx = seat.charAt(0) - 'a';
                int tmpy = 0;
                if(seat.length() == 2) tmpy = seat.charAt(1);
                else tmpy = seat.charAt(1) * 10 + seat.charAt(2);
                pair[s++] = new Pair(tmpx, tmpy);
            }
        }

        int[][] seat = new int[row][col]; // 좌석 배열 초기화

        for(int i = 0; i < count; i++) { // 예약된 좌석 >> 1
            int _row = pair[i].getRow();
            int _col = pair[i].getCol() - 1;
            seat[_row][_col] = 1;
        }

        for(Pair rs:rsPair) { // 예약 진행 중 좌석 >> 2
            int rsrow = rs.getRow();
            int rscol = rs.getCol() - 1;
            seat[rsrow][rscol] = 2;
        }

        System.out.println("□: 선택 가능 ▩: 예매 완료 ■: 선택한 좌석");
        System.out.println("  ");

        for(int i = 0; i < col; i++) {
            System.out.print(i+" ");
        }
        System.out.println();

        for(int i = 0; i < row; i++) {
            char alpRow = (char)(i + 65);
            System.out.print(alpRow + " ");
            for(int j = 0; j < col; j++) {
                if(seat[i][j] == 0) {
                    System.out.print("□");
                }
                else if(seat[i][j] == 1) {
                    System.out.print("▩");
                }
                else {
                    System.out.print("■");
                }
            }
            System.out.println();
        }
    }
}
