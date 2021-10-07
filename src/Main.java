
public class Main {
	public static void main(String args[]) {
		//main
		//초기화면(로그인페이지) 호출
		System.out.println("영화를 입력해주세요");
		String movie = InputRule.MTRule();
		System.out.println(movie);
	}
}
