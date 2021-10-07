import java.util.Scanner;

public class ReservationInfoPage {//8.2.4 예매정보확인페이지
	
	static Scanner scan = new Scanner(System.in);
	
	public static void reservationInfoPage() {
		String tmp="";
		System.out.println("확인할 예매 정보를 검색해주세요(날짜␣영화제목␣상영관)");
		System.out.print(">>>");
		tmp=scan.nextLine();
		reservationInfoDetailPage();
	}
	public static void reservationInfoDetailPage() {//8.2.4.1 예매현황확인
		String tmp="";
		System.out.println("======예매정보======");
		System.out.println("(뒤로가기는 0을 입력해주세요.)");
		//시간 영화이름 좌석현황 출력
		System.out.println("09:50 오징어게임 45/90");
		System.out.println("15:30 문어게임 12/90");
		tmp=scan.next();
	}
}
