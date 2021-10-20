import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TodayMovie {
	private static ArrayList<RunningInfo> riArr;
    private static ArrayList<RunningInfo> riList;

    static Scanner scan = new Scanner(System.in);

    public static void ImportToday(String date) {
        riList = new ArrayList<>();
        riArr = test.getRiArr();
        for(int i = 0; i < riArr.size(); i++) {
            if(riArr.get(i).getDate().equals(date))  riList.add(riArr.get(i));
        }
    }
    public static void PrintToday(String date) { //초기 화면
        ImportToday(date);
        System.out.println("===== 오늘 영화 예매하기 ======");
        for(RunningInfo ri : riList){
            System.out.println(ri.getTime()+" | "+ri.getTheater()+"관 | "+ri.getMovieName());
        }
//        System.out.println("08:30 | 차리서관 | 오징어 게임");
//        System.out.println("14:25 | 별관 | 문어 게임");
//        System.out.println("19:30 | 3관 | 오징어 게임\n");
        InputToday();
    }

    public static void InputToday() {
        while(true) {
            System.out.println("예매할 영화를 입력해주세요. (시간␣상영관␣오징어게임)");
            System.out.print(">>>");
            String input = scan.nextLine();
            String[] inputArr = input.split(" ");
            String tr = InputRule.TimeRule(inputArr[0]);
            String sr = InputRule.ScreenRule(inputArr[1]);
            String mr = "";
            for(int i = 2; i < inputArr.length; i++){
                mr += inputArr[i] + " ";
            }
            mr = mr.trim();

            System.out.println(mr);

            if(Objects.nonNull(tr) && Objects.nonNull(sr) && Objects.nonNull(mr)) {
                int index;
                for(index=0; index < riList.size(); index++){
                    RunningInfo ritmp = riList.get(index);
                    String movieTheater = ritmp.getTheater()+"관";
                    if(ritmp.getTime().equals(tr)&&movieTheater.equals(sr)&&ritmp.getMovieName().equals(mr)){
                        break;
                    }
                }
                if(index < riList.size()) {
                    SeatSelect.SeatMain(riList.get(index));
                    break;
                }
            }
            else {
                System.out.println("올바르지 않은 입력입니다.");
            }
        }

    }
}
