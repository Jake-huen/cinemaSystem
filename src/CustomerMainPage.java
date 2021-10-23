import java.util.ArrayList;

public class CustomerMainPage {
	private UserInfo user;
	private String date;
	private String time;
	private String [] menuName = {"", "오늘 영화 예매하기","영화 검색","예매정보 확인 및 수정 삭제","종료하기"};
	private TodayMovie todayMovie;

	
	private CustomerRsrvInfoPage cusRsrvPage;
	
	public CustomerMainPage(UserInfo user, String date, String time) {
		this.user = user;
		this.date = date;
		this.time = time;
		cusRsrvPage = new CustomerRsrvInfoPage(this.user, this.date, this.time);
		todayMovie = new TodayMovie();
	}
	
	public void menu() {
		
		while(true) {
			// 오늘 날짜 출력
			System.out.println("====== "+ Print.makeDateFormet(date) +" 상영 영화 ======");
			
			// 오늘 상영하는 영화 출력 
			printTodayMovie();
			
			System.out.println("====================================");
			
			// 메뉴 출력
			Print.menu(menuName, false);
			System.out.print("메뉴를 선택해주세요>>>");	
			
			// 메뉴 입력
			int menuNum=InputRule.MenuRule(menuName);
			
			switch(menuNum) {
				case 1: // 오늘 영화 예매하기
					TodayMovie.PrintToday(user.getId(), date);// 오늘 영화 예매 객체 추가
					break;
				case 2:// 영화 검색 
					SearchMovie.SearchMain(user.getId());// 영화 검색 객체 or 함수 추가 
					break;
				case 3:// 예매 정보 확인 및 수정 삭제
					cusRsrvPage.menu();
					break;
				case 4: // 종료 
					System.exit(0);
					break;
					
				default:
					System.out.println("올바르지 않은 입력입니다.\n");
			}
		}
		
	}
	
	// 오늘 상영하는 영화 출력 
	private void printTodayMovie() {
		ArrayList<RunningInfo> todayRunning = RunningInfoManage.readDateRi(date);
		
		for(RunningInfo runInfo : todayRunning)
			System.out.println(runInfo);
	}
	
	
	

}
