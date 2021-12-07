import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo("ccc111","cccc1111","20211015","0824");
//        CustomerMainPage cm = new CustomerMainPage(userInfo);
//        cm.menu();
        ManagerMainPage.managerMainPage(userInfo);
//        ArrayList<RunningInfo> riArr = RunningInfoManage.getRiArr();
//        RunningInfo runningInfo = null;
//        for(RunningInfo ri : riArr){
//            if(ri.getDate().equals("20211015"))
//                runningInfo = ri;
//        }
//        while (true){
//            SeatSelect.SeatMain(runningInfo);
//        }
//        while (true){
//            System.out.print("선택하신 좌석 A1 이 맞습니까?(y/n) >>>");
//            int k = InputRule.YesOrNo();
//            if(k == 1) {
//                System.out.println("선택하신 좌석의 예매가 완료되었습니다. 감사합니다.");
//                System.out.println("true");
//                System.out.print("메뉴를 선택해주세요>>>");
//                System.out.println();
//            }
//            else if(k==0){
//                System.out.println("좌석을 선택해 주세요[0/1]>>>");
//                System.out.println();
//            }
//            else {
//                System.out.println("올바르지 않은 입력입니다.\n");
//            }
//        }
    }
}
