import java.util.ArrayList;

public class SeatSelect {

    public static Pair[] SeatMain(RunningInfo ri) {
        System.out.println("===== 영화예매 ======\r\n"
                + "----------- 선택한 영화 정보 -----------");
        System.out.println(Print.makeDateFormet(ri.getDate())+" | "+Print.makeTimeFormet(ri.getTime())+" | "+ ri.getTheater()+" | "+ri.getMovieName());
        int ppl = 0;
        while(true){
            System.out.print("예매 인원 >>>");
            ppl = InputRule.rsrvPplInput();
            if(ppl == 0) System.out.println("올바르지 않은 입력입니다.");
            else break;
        }


        ArrayList<ReserveInfo> rsiArr = ri.getReserve();
        String theater = ri.getTheater();
        TheaterInfo ti = TheaterDataManage.findTheater(theater);
        int theaterX = ti.getRow();
        int theaterY = ti.getCol();

        int[][] seatArr = seatPrint(ri);
        String seatInput = null;
        Pair[] rsSeat = new Pair[ppl];

        while (true){
            for(int i = 0; i < ppl; i++) {
                System.out.print("좌석을 선택해 주세요["+(i+1)+"/"+ppl+"]>>>");
                seatInput = InputRule.SeatRule();
                if(seatInput == null) {
                    System.out.println("해당 좌석이 존재하지 않습니다.");
                    i--;
                }
                else {
                    int x = seatInput.charAt(0) - 'a';
                    int y = 0;
                    int result = 0;
                    if(seatInput.length() == 2) y = (seatInput.charAt(1) - '0') - 1;
                    else y = (seatInput.charAt(1)-'0') * 10 + (seatInput.charAt(2) - '0') - 1;

            if(x <= theaterX && y <= theaterY){
                if(seatArr[x][y]==1) result = -1;
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
        System.out.print("선택하신 좌석 ");
        for(Pair rsP : rsSeat){
            char tmpx = (char) (rsP.getRow() + 'A');
            System.out.print(tmpx+""+ (rsP.getCol()+1)+" ");
        }
        System.out.print("이 맞습니까?(y/n) >>>");
        int yorn = InputRule.YesOrNo();
        if(yorn == 1) {
            // 예약 성공
            return rsSeat;
        }
        else {
            // 예약 실패
            return null;
        }
    }

    public static int[][] seatPrint(RunningInfo ri) {
        String theater = ri.getTheater();
        TheaterInfo ti = TheaterDataManage.findTheater(theater);
        int row = ti.getRow();
        int col = ti.getCol();
        ArrayList<ReserveInfo> rsiArr = ri.getReserve();
        int count = 0;
        Pair[] pair = null;
        if(rsiArr != null){
            for(ReserveInfo rsi : rsiArr){
                ArrayList<String> seatArr = rsi.getSeat();
                for(String seat : seatArr)
                    count++;
            }
            pair = new Pair[count];
            int s = 0;
            for(ReserveInfo rsi : rsiArr){
                ArrayList<String> seatArr = rsi.getSeat();
                for(String seat : seatArr){
                    int tmpx = seat.charAt(0) - 'A';
                    int tmpy = 0;
                    if(seat.length() == 2) tmpy = seat.charAt(1) - '0';
                    else tmpy = ((seat.charAt(1)- '0') * 10 ) + (seat.charAt(2) - '0');
                    pair[s++] = new Pair(tmpx, tmpy);
                }
            }
        }

        int[][] seat = new int[row][col]; // 좌석 배열 초기화

        for(int i = 0; i < count; i++) { // 예약된 좌석 >> 1
            int _row = pair[i].getRow();
            int _col = pair[i].getCol() - 1;
            System.out.println(_row +" "+_col);
            seat[_row][_col] = 1;
        }

        System.out.println("□: 선택 가능 ▩: 예매 완료");
        System.out.print("  ");

        for(int i = 1; i <= col; i++) {
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
        return seat;
    }

    public static void seatReservedPrint(RunningInfo ri, Pair[] rsPair) {
        String theater = ri.getTheater();
        TheaterInfo ti = TheaterDataManage.findTheater(theater);
        int row = ti.getRow();
        int col = ti.getCol();
        ArrayList<ReserveInfo> rsiArr = ri.getReserve();
        int count = 0;
        Pair[] pair = null;
        if(rsiArr != null){
            for(ReserveInfo rsi : rsiArr){
                ArrayList<String> seatArr = rsi.getSeat();
                for(String seat : seatArr)
                    count++;
            }

            pair = new Pair[count];
            int s = 0;
            for(ReserveInfo rsi : rsiArr){
                ArrayList<String> seatArr = rsi.getSeat();
                for(String seat : seatArr){
                    int tmpx = seat.charAt(0) - 'A';
                    int tmpy = 0;
                    if(seat.length() == 2) tmpy = seat.charAt(1) - '0';
                    else tmpy = ((seat.charAt(1)- '0') * 10 ) + (seat.charAt(2) - '0');
                    pair[s++] = new Pair(tmpx, tmpy);
                }
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
            int rscol = rs.getCol();
            seat[rsrow][rscol] = 2;
        }

        System.out.println("□: 선택 가능 ▩: 예매 완료 ■: 선택한 좌석");
        System.out.print("  ");

        for(int i = 0; i < col; i++) {
            System.out.print(i+" ");
        }
        System.out.println();

        for(int i = 0; i < row; i++) {
            char alpRow = (char)(i + 65);
            System.out.print(alpRow + " ");
            for(int j = 0; j < col; j++) {
                if(seat[i][j] == 0) {
                    System.out.print("□ ");
                }
                else if(seat[i][j] == 1) {
                    System.out.print("▩ ");
                }
                else {
                    System.out.print("■ ");
                }
            }
            System.out.println();
        }
    }
}
