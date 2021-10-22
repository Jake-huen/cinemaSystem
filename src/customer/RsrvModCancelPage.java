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
