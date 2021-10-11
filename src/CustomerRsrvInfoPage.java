
public class CustomerRsrvInfoPage {
	private String id;
	private String date;
	private String time;
	private int rsrvNum = 0; // 예약 영화 개수 
	
	public CustomerRsrvInfoPage(String id, String date, String time) {
		this.id = id;
		this.date = date;
		this.time = time;
	}
	
	public void menu() {
		while(true) {
			System.out.println("===== 예매 정보 =====");
	
			// 메뉴 출력 
			Print.menu(makeMenuNameforPrint(), true);
			System.out.println("\n수정 및 삭제할 예매 내역을 선택하세요 >>> ");
			
			// 메뉴 입력 
			int menuNum=InputRule.MenuRule(makeMenuNameforInput());
			
			// 메뉴 입력에 따른 행동  
			if(menuNum == 0)
				return;
			
			else if(menuNum == -1) {
				System.out.println("올바르지 않은 입력입니다.");
				continue;
			}
			else {
				// 예매 수정 및 취소 페이지 
				RsrvModCancelPage modCancelPg = new RsrvModCancelPage(id);
				modCancelPg.menu();
			} 
		}
	}
	
	// 메뉴 이름 '출력'하기 위한 메뉴이름 배열 만들기 - 미구현 & 미완성 
	private String[] makeMenuNameforPrint() {
		
		// 데이터에서 예매 정보 개수 가져오기 - 미구현 (현재 날짜랑 시간을 인자로 받아야함)
		rsrvNum = 3; // 임시 
		String[] menuName = new String[rsrvNum];
		
		// 메뉴 이름 배열 만들기 - 미구현 
		
		/* <구현방식>
		 * 1. 우선 예매된 상영정보에 대한 내용을 객체로 받아오기 
		 * 1.1 id로 login.json의 code 가져오기 
		 * 1.2 code로 info.json의 상영정보 찾기 
		 * 1.3 필요한 데이터 모두 모아서 객체로 받아오기
		 * 2. 상영정보 객체 String으로 반환하는 함수 만들기 (어디서 만들지 결정 필요) 
		 * 3. 반환된 String 값 -> menuName에 넣기 
		 */ 
		
		for(int i=0;i<rsrvNum;i++) { // 임시 
			if(i==0)
				menuName[0]="돌아가기";
			else 
				menuName[i]="영화"+i;
		}
		
		return menuName;
	}
	
	
	// 메뉴'입력'하기 위한 메뉴이름 배열 만들기 
	private String[] makeMenuNameforInput() {
		String[] menuName = new String[rsrvNum];
		
		for(int i=0;i<rsrvNum;i++) { // 임시 
			if(i==0)
				menuName[0]="돌아가기";
			else 
				menuName[i]=""; // 메뉴 번호만 올바른 입력으로 받으므로 
		}
		return menuName;
	}
	
	
// --------------------------- 중첩 class -----------------------------------------------
	// 영화 수정 취소 페이지 
	class RsrvModCancelPage{
		
		private String id;
		/*private 상영정보 담은 객체 - 미구현*/
		private String[] menuName= {"돌아가기","예매 인원 수정","예매 좌석 수정","예매 취소"};
		
		public RsrvModCancelPage(String id) {
			this.id = id;
			// 상영정보 담은 객체 초기화 구문 <- 인자에도 추가하기 : 미구현 
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
			// 예매 정보 출력하는 함수 넣기 - 미구현 
			System.out.println("몇월 몇일 땡땡 영화");
			System.out.println("-------------------------- ");
		}
	}
}
