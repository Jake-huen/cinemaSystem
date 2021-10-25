
import java.util.ArrayList;
import java.util.List;

public class CustomerRsrvInfoPage {
	private UserInfo user;
	private String date;
	private String time;
	private int rsrvNum = 0; // 예약 영화 개수
	// 상영정보 + 예매정보 저장 배열 
	private ArrayList<UserRsrvInfo> userRsrvInfos; 
	
	public CustomerRsrvInfoPage(UserInfo user, String date, String time) {
		this.user = user;
		this.date = date;
		this.time = time;
	}
	
	public void menu() {
		
		while(true) {
			// 예매 정보 (상영정보 + 예매좌석) 가져오기
			getCurRunRsrvInfo();
			
			System.out.println("\n\n===== 예매 정보 =====");
	
			// 메뉴 출력 
			Print.menu(makeMenuNameforPrint(), true);
			System.out.print("수정 및 삭제할 예매 내역을 선택하세요 >>> ");
			
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
				RsrvModCancelPage modCancelPg = new RsrvModCancelPage(user, userRsrvInfos.get(menuNum-1));
				modCancelPg.menu();
			} 
		}
	}
	
	// 메뉴 이름 '출력'하기 위한 메뉴이름 배열 만들기
	private String[] makeMenuNameforPrint() {
		
		// 데이터에서 예매 정보 개수 가져오기
		rsrvNum = userRsrvInfos.size() + 1; // 상영정보 개수 + 돌아가기 메뉴 
		String[] menuName = new String[rsrvNum];
		
		// 메뉴 이름 배열 만들기
		for(int i=0;i<rsrvNum;i++) { // 임시 
			if(i==0)
				menuName[0]="돌아가기";
			else 
				menuName[i]=userRsrvInfos.get(i-1).toString();
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
	
	private ReserveInfo getRsrvInfo(RunningInfo runInfo) {
		// 상영정보에서 해당 user의 예매 정보만 가져오기 
		ArrayList<ReserveInfo> totalRsrvInfos = runInfo.getReserve();
		
		for(ReserveInfo rsrvInfo : totalRsrvInfos) {
			if(rsrvInfo.getUserId().equals(user.getId()))
				return rsrvInfo;
		}
		
		return null;
	}
	
	// 상영 예매 정보 새로 가져오는 함수 
	private void getCurRunRsrvInfo() {
		
		/* 예매 영화 상영정보 가져오기*/
		userRsrvInfos = new ArrayList<UserRsrvInfo>(); 
		// user 정보 중 code 가져오기 
		List<String> movCodes =new ArrayList<String>();
		movCodes = LoginDataManage.getCode(user.getId());
		
		// 현재 등록된 전체 상영정보 가져오기
		ArrayList<RunningInfo> riArr = RunningInfoManage.getRiArr();

		// 사용자가 예매한 상영정보 + 예매 정보 만 저장하기
		for(String movieCode : movCodes) {
			for(RunningInfo runInfo : riArr) {
				if(runInfo.getCode().equals(movieCode)&& Print.isAfterDate(runInfo.getDate(),runInfo.getTime(),date,time)) 
					userRsrvInfos.add(new UserRsrvInfo(runInfo , getRsrvInfo(runInfo)));
			}
		}
	}

}

