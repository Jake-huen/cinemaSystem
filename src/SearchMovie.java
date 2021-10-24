import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SearchMovie {
    static Scanner sc = new Scanner(System.in);

    public static void SearchMain(UserInfo user) {
        System.out.println("===== 상영영화 검색 ======");
        System.out.println("0. 돌아가기");
        System.out.println("1. 날짜 검색 모드");
        System.out.println("2. 영화 제목 검색 모드");
        while(true) {
            System.out.print("메뉴를 선택해주세요 >>>");
            String[] menu = {"돌아가기", "날짜 검색 모드","영화 제목 검색 모드"};
            int inputMenu = InputRule.MenuRule(menu);
            if(inputMenu == -1) {
                System.out.println("올바르지 않은 입력입니다.");
            }else {
                switch(inputMenu) {
                    case 0: //돌아가기
                        return;
                    case 1:
                        //날짜 검색 모드
                        SearchByDate(user);
                        break;
                    case 2:
                        //영화 제목 검색 모드
                        SearchByTitle(user);
                        break;
                }
            }

        }

    }

    public static void SearchByDate(UserInfo user) {
        System.out.println("===== 영화검색_날짜 검색 모드 ======");
        String date = null;
        while (true){
            System.out.print("검색할 날짜를 입력해주세요(뒤로가기: 0)>>> ");
            date = sc.nextLine();
            if(date.equals("0")){
                return;
            }
            else{
                date = InputRule.DateRule2(date);
                if(date == null){
                    System.out.println("올바르지 않은 입력입니다.");
                }else {
                    ArrayList<RunningInfo> riList = new ArrayList<>();
                    ArrayList<RunningInfo> riArr = RunningInfoManage.getRiArr();
                    int count = 0;
                    for(int i = 0; i < riArr.size(); i++) {
                        if(riArr.get(i).getDate().equals(date))  {
                            count++;
                            riList.add(riArr.get(i));
                        }
                    }
                    if(count == 0){
                        System.out.println("해당 날짜에 상영되는 영화가 없습니다.");
                        return;
                    }

                    int p = 1;
                    for(RunningInfo ri : riList){
                        System.out.println(p+". "+Print.makeDateFormet(ri.getDate())+" | "+Print.makeTimeFormet(ri.getTime())+" | "+ri.getTheater()+" | "+ri.getMovieName());
                        p++;
                    }
                    System.out.print("예매할 영화를 선택해주세요(뒤로가기 : 0) >>>");
                    int input = sc.nextInt();
                    if(input == 0) {
                        //날짜 입력 다시받기
                        sc.nextLine();
                        continue;
                    }
                    else {
                        success(user, riList, input-1);
                        return;
                    }
                }
            }
        }
    }

    public static void SearchByTitle(UserInfo user) {
        System.out.println("===== 영화검색_영화 제목 검색 모드 ======");
        String title = null;
        String movieTitle = null;
        while (true){
            System.out.print("검색할 영화제목을 입력해주세요(뒤로가기: 0) >>> ");
            title=sc.nextLine(); //영화제목입력받기
            if(Objects.isNull(title)) {
                System.out.println("올바르지 않은 입력입니다.");
            }
            else if(title.equals("0")) return;
            else {
                movieTitle = InputRule.MTRule(title);
                ArrayList<RunningInfo> riList = new ArrayList<>();
                ArrayList<RunningInfo> riArr = RunningInfoManage.getRiArr();
                int count = 0;
                for(int i = 0; i < riArr.size(); i++) {
                    if(riArr.get(i).getMovieName().contains(movieTitle))  {
                        count++;
                        riList.add(riArr.get(i));
                    }
                }

                if(riList.size() == 0){
                    System.out.println("검색하신 제목을 가진 영화가 없습니다.");
                    return;
                }
                int p = 1;
                for(RunningInfo ri : riList){
                    System.out.println(p+". "+Print.makeDateFormet(ri.getDate())+" | "+Print.makeTimeFormet(ri.getTime())+" | "+ri.getTheater()+" | "+ri.getMovieName());
                    p++;
                }
                System.out.print("예매할 영화를 선택해주세요(뒤로가기 : 0) >>>");
                int input = sc.nextInt();
                if(input == 0) {
                    sc.nextLine();
                    continue;
                }
                else success(user, riList, input-1);
                return;
            }
        }
    }
    
    public static void success(UserInfo user, ArrayList<RunningInfo> riList, int index) {
        Pair[] pairs = SeatSelect.SeatMain(riList.get(index));
        if(pairs == null) return;//좌석 선택 실패

        ArrayList<String> seat = new ArrayList<String>();
        int i = 0;
        for(Pair pair : pairs){
            char chx = (char)(pair.getRow() + 'A');
            String tmpx = ""+chx;
            String tmpy = Integer.toString(pair.getCol()+1);
            String tmpSeat = tmpx + tmpy;
            seat.add(tmpSeat);
        }
        ArrayList<ReserveInfo> rsiArr = riList.get(index).getReserve();
        ReserveInfo rsitmp = null;
        if(rsiArr != null){
            for(ReserveInfo rsi : rsiArr){
                if(rsi.getUserId().equals(user.getId()))
                    rsitmp = rsi;
            }
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
    }
}