import java.util.Scanner;

public class ManagerMainPage {

	static Scanner scan = new Scanner(System.in);

	public static void managerMainPage() {
		while(true) {
			System.out.println("1. 영화관리");
			System.out.println("2. 상영관 관리");
			System.out.println("3. 상영 정보 등록");
			System.out.println("4. 예매 정보 확인");
			System.out.println("5. 종료");
			System.out.print(">>>");

			int menuNum=scan.nextInt(); //scanner.nextInt() 대신 메뉴입력규칙 함수호출
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
			}
		}
	}
}
