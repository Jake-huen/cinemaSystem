import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TodayMovie {
    private static ArrayList<RunningInfo> riArr;
    private static ArrayList<RunningInfo> riList;
    static Scanner scan = new Scanner(System.in);

    public static void ImportToday(UserInfo user) {
        riList = new ArrayList<>();
        riArr = RunningInfoManage.getRiArr();
        for(int i = 0; i < riArr.size(); i++) {
            if(riArr.get(i).getDate().equals(user.getDate()) && Print.isAfterDate(riArr.get(i).getDate(),riArr.get(i).getTime(),user.getDate(),user.getTime()))  riList.add(riArr.get(i));
        }
    }
    public static void PrintToday(UserInfo user, String date) { //초기 화면
        ImportToday(user);
        System.out.println("===== 오늘 영화 예매하기 " +Print.makeDateFormet(date)+" ======");
        for(RunningInfo ri : riList){
            System.out.println(Print.makeTimeFormet(ri.getTime())+" | "+ri.getTheater()+" | "+ri.getMovieName());
        }
//        System.out.println("08:30 | 차리서관 | 오징어 게임");
//        System.out.println("14:25 | 별관 | 문어 게임");
//        System.out.println("19:30 | 3관 | 오징어 게임\n");
        InputToday(user);
    }

    public static void InputToday(UserInfo user) {
        while(true) {
        	String tr = null; String sr = null; String mr = null;
            while (true){
                System.out.println("예매할 영화를 입력해주세요. (시간␣상영관␣오징어게임)");
                System.out.print(">>>");
                String input = scan.nextLine();
                String[] inputArr = input.split(" ");
                if(inputArr.length < 3){
                    System.out.println("올바르지 않은 입력입니다.");
                }
                else {
                    tr = InputRule.TimeRule(inputArr[0]);
                    sr = InputRule.ScreenRule(inputArr[1]);
                    mr = "";
                    for(int i = 2; i < inputArr.length; i++){
                        mr += inputArr[i] + " ";
                    }
                    mr = mr.trim();
                    if(tr == null || sr == null || mr == null){
                        System.out.println("올바르지 않은 입력입니다.");
                    }
                    else break;
                }
            }
            tr = tr.replace(":","");

            if(Objects.nonNull(tr) && Objects.nonNull(sr) && Objects.nonNull(mr)) {
                int index;
                for(index=0; index < riList.size(); index++){
                    RunningInfo ritmp = riList.get(index);
                    String movieTheater = ritmp.getTheater();
                    if(ritmp.getTime().equals(tr)&&movieTheater.equals(sr)&&ritmp.getMovieName().equals(mr)){
                        break;
                    }
                }

                if(index < riList.size()) {
                    Pair[] pairs = SeatSelect.SeatMain(riList.get(index));
                    if(pairs == null) break; //좌석 선택 실패

                    ArrayList<String> seat = new ArrayList<String>();
                    for(Pair pair : pairs){
                        char chx = (char)(pair.getRow() + 'A');
                        String tmpx = ""+chx;
                        String tmpy = Integer.toString(pair.getCol()+1);
                        String tmpSeat = tmpx + tmpy;
                        seat.add(tmpSeat);
                    }

                    ArrayList<ReserveInfo> rsiArr = riList.get(index).getReserve();
                    ReserveInfo rsitmp = null;
                    for(ReserveInfo rsi : rsiArr){
                        if(rsi.getUserId().equals(user.getId()))
                            rsitmp = rsi;
                    }

                    if(rsitmp == null)
                        rsitmp = new ReserveInfo(user.getId(), seat);
                    else{
                        for(String s : seat){
                            rsitmp.getSeat().add(s);
                        }
                    }
                    RunningInfoManage.updateReserve(riList.get(index), rsitmp);
                    String code = riList.get(index).getCode();
                    LoginDataManage.addCode(user.getId(),user.getPw(),code);
                    break;
                }
                else {
                    System.out.println("올바르지 않은 입력입니다.");
                }
            }
            else {
                System.out.println("올바르지 않은 입력입니다.");
            }
        }

    }
}
