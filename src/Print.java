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

}
