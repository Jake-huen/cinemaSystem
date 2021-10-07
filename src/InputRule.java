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
	public static void ScreenRule(String screen) {//7.5 상영관입력규칙
		
	}
}
