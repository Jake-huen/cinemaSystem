import java.util.Scanner;

public class ManagerMainPage {

	static Scanner scan = new Scanner(System.in);

	public static void managerMainPage(UserInfo userinfo) {
		while(true) {
			System.out.println("1. 영화관리");
			System.out.println("2. 상영관관리");
			System.out.println("3. 상영정보등록");
			System.out.println("4. 예매정보확인");
			System.out.println("5. 종료");
			System.out.print(">>>");
			
			int menuNum=-1;
			String[] tmp={"영화관리","상영관관리","상영정보등록","예매정보확인","종료"};
			String menuStr=scan.nextLine();
			menuStr=menuStr.replaceAll(" ", "");

			for(int i=0;i<5;i++) {
				if((tmp[i].equals(menuStr))||(menuStr.equals((i+1)+""))) {
					menuNum=i+1;
				}
			}
			if(menuNum==-1) {
				System.out.println("올바르지 않은 입력입니다.");
				continue;
			}
			
			
			switch(menuNum) {
			case 1:
				MovieManagePage.movieManagePage();
				break;
			case 2:
				TheaterManagePage.theaterManagePage();
				break;
			case 3:
				RunningInfoRegisterPage.runningInfoRegisterPage(userinfo);
				break;
			case 4:
				ReservationInfoPage.reservationInfoPage();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
				break;
			default:
            	System.out.println("올바르지 않은 입력입니다.");
			}
		}
	}
}
