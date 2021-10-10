import java.util.Objects;
import java.util.Scanner;

public class TodayMovie {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void PrintToday() {
		System.out.println("===== 오늘 영화 예매하기 : 2021년 9월 24일 ======");
		System.out.println("08:30 | 차리서관 | 오징어 게임");
		System.out.println("14:25 | 별관 | 문어 게임");
		System.out.println("19:30 | 3관 | 오징어 게임\n");
		InputToday();
	}
	
	public static void InputToday() {
		while(true) {
			System.out.println("예매할 영화를 입력해주세요. (시간␣상영관␣오징어게임)");
			System.out.print(">>>");
			String input = scan.nextLine();
			String[] inputArr = input.split(" ");
			String tr = InputRule.TimeRule(inputArr[0]);
			String sr = InputRule.ScreenRule(inputArr[1]);
			String mr = InputRule.MTRule(inputArr[2]);
			if(Objects.nonNull(tr) && Objects.nonNull(sr) && Objects.nonNull(mr)) {
				//좌석 선택으로 이동
				break;
			}
			else {
				System.out.println("올바르지 않은 입력입니다.");
			}
		}
		
	}
}
