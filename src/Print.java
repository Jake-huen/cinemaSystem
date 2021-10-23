import java.util.ArrayList;

// 자주 사용되는 출력 관련 메소드 
public class Print {
	
	// 메뉴 출력 함수 (printZero: 0번 메뉴 출력여부) 
	public static void menu(String [] menuName, boolean printZero) {
		 
		int menuNum = menuName.length;
		for(int i = 0; i < menuNum; i++) {
			if(!printZero && i==0)
				continue;
			System.out.println(i+". "+menuName[i]);
		}
	}
	
	// 8자리 날짜 -> 년 월 일 로 변환하는 함수 
	public static String makeDateFormet(String date) {
		final int YEAR_IDX = 4;
		final int MON_IDX = 6;
		
		String year = date.substring(0,YEAR_IDX);
		String month = date.substring(YEAR_IDX,MON_IDX);
		String day = date.substring(MON_IDX);
		
		return year+"년 "+month +"월 "+day+"일";
	}
	
	// 시간 0000 을 00:00 형태로 변환하는 함수 
	public static String makeTimeFormet(String time) {
		final int HOUR_IDX = 2;
		
		String hour = time.substring(0,HOUR_IDX);
		String min = time.substring(HOUR_IDX);
		
		return hour+":"+min;
	}
	
	// 현재 예매 좌석 제외하고 출력하기 
	public static void printSeatExclCurRsv(TheaterInfo theater, RunningInfo runInfo, ReserveInfo rsrvInfo) {		
		ArrayList<Pair> totalRsrvSeats = new ArrayList<Pair>();
		ArrayList<Pair> userRsrvSeats = new ArrayList<Pair>();
		ArrayList<ReserveInfo> rsrvInfos  = runInfo.getReserve();
		//for(ReserveInfo rInfo : rsrvInfos)
	}
	
	private static Pair seatStrToPair(String seat) {
		int row = seat.indexOf(0) -'A';
		int col = Integer.parseInt(seat.substring(1));
		return new Pair(row,col);
	}

}
