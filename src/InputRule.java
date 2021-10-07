import java.util.Scanner;

public class InputRule {//입력규칙 정의 (static으로)
	static Scanner sc = new Scanner(System.in);
	public static String MTRule() { //7.3영화제목입력규칙
		String movie;
		while(true) {
			movie = sc.nextLine();
			movie=movie.trim();
			if(movie.equals("0")) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else if(movie.length()<1 || movie.length()>30) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else {
				break;
			}
		}
		return movie;
	}
	public static void TimeRule(String time){ //7.4시간입력규칙
		
	}
	public static String ScreenRule() {//7.5 상영관입력규칙
		String screen;
		while(true) {
			screen=sc.nextLine();
			String check_screen = screen.trim();
			if(!screen.equals(check_screen)) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else if(screen.length()<1 || screen.length()>100) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else if(screen.charAt(screen.length()-1)!='관'){
				System.out.println("올바르지 않은 입력입니다.");
			}
			else {
				break;
			}
		}
		return screen;
	}
}
