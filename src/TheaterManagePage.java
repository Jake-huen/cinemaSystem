import java.util.Scanner;

public class TheaterManagePage {//8.2.2상영관관리페이지

	static Scanner scan = new Scanner(System.in);

	public static void theaterManagePage() {
		while(true) {
			System.out.println("======상영관 목록======");
			//등록된 상영관 읽어오기 (DataManage클래스 내 함수 호출)
			System.out.println("1관 /120석");
			System.out.println("2관 /80석");
			System.out.println("별관 /160석");
			System.out.println("차리서관 /40석");
			System.out.println("===================");
			System.out.println("0. 메인 페이지로 가기");
			System.out.println("1. 상영관 등록");
			System.out.println("2. 상영관 수정 및 삭제");
			System.out.print(">>>");

			int menuNum=scan.nextInt();//scanner.nextInt() 대신 메뉴입력규칙 함수호출
			switch(menuNum) {
			case 0:
				return;
			case 1:
				theaterRegisterPage();
				break;
			case 2:
				theaterCheckPage();
				break;
			}
		}
	}
	public static void theaterRegisterPage() {//8.2.2.1 상영관정보등록
		String tmp="";
		System.out.print("상영관이름>>");
		tmp=scan.next();
		System.out.print("좌석의 행과 열 수>>");
		tmp=scan.next();
		System.out.println("=====등록완료=====");
	}
	public static void theaterCheckPage() {//8.2.2.2 상영관정보확인
		System.out.println("======상영관 목록======");
		//등록된 상영관 읽어오기 (DataManage클래스 내 함수 호출)
		System.out.println("0. 돌아가기");
		System.out.println("1. 1관 /120석");
		System.out.println("2. 2관 /80석");
		System.out.println("3. 별관 /160석");
		System.out.println("4. 차리서관 /40석");
		System.out.println("===================");
		System.out.print("수정 및 삭제할 상영관을 선택하세요>>>");
		String tmp="";
		tmp=scan.next();
		
		theaterFixPage();//정상입력시
	}
	public static void theaterFixPage() {//8.2.2.2.1 상영관정보수정및삭제
		String tmp="";
		System.out.println("======영화 수정 및 삭제======");
		System.out.println("1관 /120석");
		System.out.println("0. 뒤로가기");
		System.out.println("1. 수정");
		System.out.println("2. 삭제");
		System.out.print(">>>");
		tmp=scan.next();
		System.out.println("======상영관 수정======"); //or 영화삭제
		System.out.print("상영관 이름>>>");
		tmp=scan.next();
		System.out.print("좌석의 행과 열 수>>>");
		tmp=scan.next();
	}
}
