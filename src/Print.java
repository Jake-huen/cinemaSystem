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

}
