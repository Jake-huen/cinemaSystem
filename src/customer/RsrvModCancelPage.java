package src.customer;

public class RsrvModCancelPage {
	private UserInfo user;
	private UserRsrvInfo userRsrvInfo;
	
	
	private String[] menuName= {"돌아가기","예매 인원 수정","예매 좌석 수정","예매 취소"};
	
	// 생성자 
	public RsrvModCancelPage(UserInfo user, UserRsrvInfo userRsrvInfo) {
		this.user = user;
		this.userRsrvInfo = userRsrvInfo;
	}
	
	
	public void menu() {
		while(true) {
			System.out.println("===== 예매 수정 및 취소 =====");
			
			// 예매 정보 출력
			printRsrvInfo(); 
			
			Print.menu(menuName, true);
			System.out.print(">>> ");
			
			// 메뉴 입력
			int menuNum=InputRule.MenuRule(menuName);
			
			switch(menuNum) {
			case 0: // 돌아가기
				return;
			case 1:// 예매 인원 수정
				modRsrvPplNum();
				break;
			case 2:// 예매 좌석 수정
				// 예매 좌석 수정 객체 - 미구현 
				break;
			case 3: // 예매 취소 
				cancelRsrv();
				return; // 8.3.4로 돌아가기 
				
			default:
				System.out.println("올바르지 않은 입력입니다.\n");
			}
		}
	}
	
	// 예매 인원 수정 함수 
	private void modRsrvPplNum() {
		int modPplNum;
		
		while(true) {
			System.out.println("===== 예매 인원 수정 =====");
			printRsrvInfo(); // 예매 정보 출력 
			System.out.print("예매 인원(뒤로가기 : 0) >>> ");
			modPplNum = InputRule.rsrvPplInput(); // 수정 인원 입력받기 
			
			if(modPplNum == 0)
				return; // 돌아가기 
			else if(modPplNum == -1) {
				System.out.println("올바르지 않은 입력입니다.\n");
				continue;
			}else 
				break;
		}
		
		// 예매 좌석 객체에 인원수 전달 && 예매 좌석 선택 함수 실행 - 미구현 
	}
	
	// 좌석 관련 구현 방식 : 좌석관련 클래스 따로 구현하기 
	/* 1. 상영관 정보 받아오기
	 * 1.1 상영관 이름은 userRsrvInfo 에서 가져올 수 있음.
	 * 1.2 파일에 있는 상영관 정보 읽어오기. -> 상영관 관련 json class에서 추가해야할듯.
	 * 1.2.1 파일 읽어오다가 input한 string이랑 같은 theater 이름이면 theater 객체 반환하도록 
	 * 
	 * 2. 상영관 출력하기 
	 * - 상영관 행,열(TheaterInfo객체), 현재 예매 현황(RunnningInfo 객체), 현재 고객이 예매한 좌석(ReserveInfo) 필요 
	 * 2.1 TheaterInfo, userRsrvInfo 를 인자로 받으면 좌석 출력해주는 함수 구현
	 * -> 좌석 예매 page 클래스 안에 구현하기 
	 * 
	 * 3. 예매 좌석 수정하는 함수 구현하기
	 * 3.1 2에서 구현한 함수 이용해서 좌석 출력 
	 * 3.2 이미 선택된 좌석은 선택 불가하도록 
	 * 3.3 
	 * 
	 * 
	 * */
	
	// 예매 취소 함수 
	private void cancelRsrv() {
		while(true) {
			System.out.println("===== 예매 취소 =====");
			printRsrvInfo(); // 예매 정보 출력 
			System.out.print("예매를 취소하시겠습니까? (Y/N) >>> ");
			
			// 취소 여부 yes or no
			int answer = InputRule.YesOrNo();
			
			if(answer == 1) {
				// 예매 정보 삭제하는 함수 실행 - 미구현(파일접근 필요) 
				return;
			}
			else if(answer == -1) {
				return;
			}else 
				System.out.println("올바르지 않은 입력입니다.\n");
		}
		
	}
	
	// 예매 정보 출력 함수 
	private void printRsrvInfo() {
		System.out.println("-------- 예매 정보 --------- ");
		
		// 예매 정보 출력
		System.out.println(userRsrvInfo);
		
		System.out.println("-------------------------- ");
	}
	
}
