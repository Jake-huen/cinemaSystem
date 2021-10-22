import java.util.Scanner;

public class ManagerMainPage {

	static Scanner scan = new Scanner(System.in);

	public static void managerMainPage() {
		while(true) {
			System.out.println("1. 영화관리");
			System.out.println("2. 상영관관리");
			System.out.println("3. 상영정보등록");
			System.out.println("4. 예매정보확인");
			System.out.println("5. 종료");
			System.out.print(">>>");
			
			String[] tmp={"영화관리","상영관 관리","상영정보등록","예매정보확인","종료",""};
			
			int menuNum=InputRule.MenuRule(tmp);
			System.out.println(menuNum);
			switch(menuNum) {
			case 1:
				MovieManagePage.movieManagePage();
				break;
			case 2:
				TheaterManagePage.theaterManagePage();
				break;
			case 3:
				RunningInfoRegisterPage.runningInfoRegisterPage();
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
