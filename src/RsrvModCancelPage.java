import java.util.ArrayList;
import java.util.List;

public class RsrvModCancelPage {
	private UserInfo user;
	private UserRsrvInfo userRsrvInfo;
	private ModifyRsrvSeatPage modifyRservSeatPage;
	
	private String[] menuName= {"돌아가기","예매 인원 수정","예매 좌석 수정","예매 취소"};
	
	// 생성자 
	public RsrvModCancelPage(UserInfo user, UserRsrvInfo userRsrvInfo) {
		this.user = user;
		this.userRsrvInfo = userRsrvInfo;
	}
	
	
	public void menu() {
		while(true) {
			System.out.println("\n\n===== 예매 수정 및 취소 =====");
			
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
				modifyRservSeatPage = new ModifyRsrvSeatPage(user,userRsrvInfo);
				modifyRservSeatPage.showPage();
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
		int leftSeats = getLeftSeats();
		
		while(true) {
			System.out.println("\n\n===== 예매 인원 수정 =====");
			printRsrvInfo(leftSeats); // 예매 정보 출력 
			
			System.out.print("예매 인원(뒤로가기 : 0) >>> ");
			modPplNum = InputRule.rsrvPplInput(); // 수정 인원 입력받기 
			
			if(modPplNum == 0)
				return; // 돌아가기 
			else if(modPplNum == -1) {
				System.out.println("올바르지 않은 입력입니다.\n");
				continue;
			}else if(modPplNum > leftSeats) {
				System.out.println("남은 좌석이 입력한 인원보다 적습니다.\n");
				continue;
			}else {
				// 예매 좌석 수정 함수 실행
				modifyRservSeatPage = new ModifyRsrvSeatPage(user,userRsrvInfo,modPplNum);
				int back = modifyRservSeatPage.showPage();
				if(back ==-1) // -1 이면 좌석 수정 함수에서 뒤로가기 누른 것 
					continue;
				break;
			}
		}
		
	}
	
	// 해당 상영 영화의 남은 좌석수 반환 
	private int getLeftSeats() {
		RunningInfo runInfo = userRsrvInfo.getRunInfo();
		TheaterInfo theater = TheaterDataManage.findTheater(runInfo.getTheater());
		
		int totalSeat = theater.getRow() * theater.getCol();
		int curRsrvedSeat=0;
		for(ReserveInfo rsrvInfo : runInfo.getReserve()) {
			curRsrvedSeat += rsrvInfo.getSeat().size();
		}
		
		
		// 현재 사용자가 예매한 좌석 수는 제외 
		curRsrvedSeat-= userRsrvInfo.getRsrvInfo().getSeat().size();
		return totalSeat - curRsrvedSeat;
	}
	
	// 예매 취소 함수 
	private void cancelRsrv() {
		while(true) {
			System.out.println("\n\n===== 예매 취소 =====");
			printRsrvInfo(); // 예매 정보 출력 
			System.out.print("예매를 취소하시겠습니까? (Y/N) >>> ");
			
			// 취소 여부 yes or no
			int answer = InputRule.YesOrNo();
			
			if(answer == 1) {
				// 예매 정보 삭제하는 함수 실행
				String movieCode = userRsrvInfo.getRunInfo().getCode();
				
				// userInfo에서 해당 code 삭제 (json 수정)
				LoginDataManage.removeCode(user.getId(), user.getPw(), movieCode);
				
				// RunningInfo에서 해당 ReserveInfo 삭제 (json 수정)
				RunningInfoManage.removeReserve(userRsrvInfo.getRunInfo(), userRsrvInfo.getRsrvInfo(), user.getId());
				
				System.out.println("예매가 취소되었습니다.");
				return;
			}
			else if(answer == 0) {
				return;
			}else 
				System.out.println("올바르지 않은 입력입니다.\n");
		}
		
	}

	
	// 예매 정보 출력 함수 
	private void printRsrvInfo() {
		System.out.println("-------- 예매 정보 --------- ");
		
		// 예매 정보 출력
		System.out.print(userRsrvInfo);
		
		
		System.out.println("-------------------------- ");
	}
	
	// 예매 정보 출력 함수  - 남은 좌석수 출력 
		private void printRsrvInfo(int leftSeats) {
			System.out.println("-------- 예매 정보 --------- ");
			
			// 예매 정보 출력
			System.out.print(userRsrvInfo);
			
			System.out.println("남은 좌석 수 : "+ leftSeats +"석");
			
			System.out.println("-------------------------- ");
		}
	
}
