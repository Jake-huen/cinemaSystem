import java.util.Scanner;

public class RunningInfoRegisterPage {//8.2.3 상영정보등록페이지

	static Scanner scan = new Scanner(System.in);

	public static void runningInfoRegisterPage() {
		while(true) {
			String tmp="";
			System.out.println("======상영관 목록======");
			//등록된 상영관 읽어오기 (DataManage클래스 내 함수 호출)
			System.out.println("0. 뒤로가기");
			System.out.println("1. 1관");
			System.out.println("2. 2관");
			System.out.println("3. 별관");
			System.out.println("4. 차리서관");
			System.out.println("===================");
			System.out.print("영화를 등록할 상영관을 선택해주세요>>>");
			tmp=scan.next();
			if(tmp.equals("0")) return;
			System.out.println();

			System.out.print("등록할 날짜를 입력해주세요>>>");
			tmp=scan.next();
			System.out.println();

			runningInfoDetailPage();
		}
	}
	public static void runningInfoDetailPage() { //8.2.3.1 상영관
		String tmp="";
		System.out.println("==2021년9월23일, 2관 상영정보==");
		//특정상영관 특정 날짜에 등록된 영화들 읽어오기 (DataManage클래스 내 함수 호출)
		System.out.println("07:00~08:30 오징어게임");
		System.out.println("09:50~11:30 오징어게임");
		System.out.println("15:00~16:30 오징어게임");
		System.out.println("==========================");

		System.out.println("==========영화목록==========");
		//등록된 영화 읽어오기 (DataManage클래스 내 함수 호출)
		System.out.println("0. 뒤로가기");
		System.out.println("1. 오징어게임 /90분");
		System.out.println("2. DP /100분");
		System.out.println("=========================");

		System.out.print("영화를 선택해주세요>>>");
		tmp=scan.next();
		if(tmp.equals("0")) return;
		System.out.println();

		System.out.print("상영시작시간을 설정하세요>>>");
		tmp=scan.next();
		System.out.println();

		System.out.println("정상추가 되었습니다");
	}
}
