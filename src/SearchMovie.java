import java.util.Objects;

public class SearchMovie {
	public static void SearchMain() {
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
					SearchByDate();
					break;
				case 2:
					//영화 제목 검색 모드
					SearchByTitle();
					break;
				}
			}
			
		}
		
	}
	
	public static void SearchByDate() {
		System.out.println("===== 영화검색_날짜 검색 모드 ======");
		System.out.print("검색할 날짜를 입력해주세요(뒤로가기: 0)>>> ");
		String date = InputRule.DateRule();
		if(date.equals("0")) return;
		System.out.println("===== 오늘 영화 예매하기 : 2021년 9월 24일 ======");
		System.out.println("08:30 | 차리서관 | 오징어 게임");
		System.out.println("14:25 | 별관 | 문어 게임");
		System.out.println("19:30 | 3관 | 오징어 게임\n");
		System.out.print("예매할 영화를 선택해주세요(뒤로가기 : 0) >>>");
		String[] str = {""};
		int input = InputRule.MenuRule(str); //*****
		if(input == -1) return;
	}
	
	public static void SearchByTitle() {
		System.out.println("===== 영화검색_영화 제목 검색 모드 ======");
		System.out.print("검색할 영화제목을 입력해주세요(뒤로가기: 0) >>> ");
		String title=""; //영화제목입력받기
		InputRule.MTRule(title);
		if(Objects.isNull(title)) return;
		System.out.println("08:30 | 차리서관 | 오징어 게임");
		System.out.println("14:25 | 별관 | 문어 게임");
		System.out.println("19:30 | 3관 | 오징어 게임\n");
		System.out.print("예매할 영화를 선택해주세요(뒤로가기 : 0) >>>");
		String[] str = {""};
		int input = InputRule.MenuRule(str); //*****
		if(input == -1) return;
	}
}
