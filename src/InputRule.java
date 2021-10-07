import java.util.Scanner;
//
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
	public static int TimeRule(){ //7.4시간입력규칙
		char tmp;
		int r_answer;
		while(true) {
			Boolean a_check=true;
			String answer="";
			String time=sc.nextLine();
			int q_count=0;
			r_answer=0;
			String check_time = time.replaceAll(" ","");
			//#있는지 체크 필요
			if(time.contains("#")) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else {
				if(!time.equals(check_time)) { //공백이 있는 경우
					System.out.println("올바르지 않은 입력입니다.");
					a_check=false;
				}
				else {
					Boolean check=true;
					for(int i=0;i<time.length();i++) {
						tmp=time.charAt(i);
						if('0'<=tmp&&tmp<='9') {
							if(check)
								answer+=tmp;
							else {
								answer+='#';
								q_count+=1;
								answer+=tmp;
								check=true;
							}
						}
						else {
							if(check) {
								answer+='#';
								q_count+=1;
								answer+=tmp;
								check=false;
							}
							else {
								answer+=tmp;
							}
						}
					}
					if(q_count==0) { //숫자만 있는 경우
						r_answer=Integer.parseInt(answer);
					}
					else if(q_count==1) { //숫자+문자
						String[] splited=answer.split("#");
						if(splited[1].equals("시간")||splited[1].equals("h")) {
							r_answer=Integer.parseInt(splited[0])*60;
						}
						else if(splited[1].equals("분")||splited[1].equals("m")){
							r_answer = Integer.parseInt(splited[0]);
						}
						else {
							System.out.println("올바르지 않은 입력입니다.");
							a_check=false;
						}
					}
					else if(q_count==3) { //숫자+문자+숫자+문자
						String[] splited=answer.split("#");
						if(splited[1].equals("h") || splited[1].equals(":") || splited[1].equals("-") || splited[1].equals("/")) {
							if(splited[3].equals("분") || splited[3].equals("m")) {
								r_answer=Integer.parseInt(splited[0])*60+Integer.parseInt(splited[2]);
							}
							else {
								System.out.println("올바르지 않은 입력입니다.");
								a_check=false;
							}
						}
						else {
							System.out.println("올바르지 않은 입력입니다.");
							a_check=false;
						}
					}
					else {
						System.out.println("올바르지 않은 입력입니다.");
						a_check=false;
					}
				}
				if(r_answer>=10&&r_answer<=720&&a_check) {
					break;
				}
				else if(a_check) {
					System.out.println("올바르지 않은 입력입니다.");
				}
			}
		}
		return r_answer; 
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
			// 입력으로 '관' 만 입력하는 경우 --->(기획서 반영?)
			else {
				break;
			}
		}
		return screen;
	}
	
	public static String SeatRule() { //7.9 예매 좌석 입력 규칙
        String seat = sc.nextLine();
        seat = seat.replace(" ", "");
        seat = seat.trim();
        seat = seat.toLowerCase();
        int ascii = seat.charAt(0);
        if(ascii >= 97 && ascii <= 122) {
            if(seat.length() > 3 || seat.length() == 1) {
                return null;
            }
            else if(seat.length() == 3) {
                int a = seat.charAt(1) - '0';
                int b = seat.charAt(2) - '0';
                int num = a * 10 + b;
                if(num >= 1 && num <= 50) return seat;
                else return null;
            } else {
                int num = seat.charAt(1) - '0';
                if(num >= 1 && num <= 9) return seat;
                else return null;
            }
        } else {
            return null;
        }
    }
	public static int YesOrNo() { // 7.10 yes / no 입력규칙
        String yon = sc.next();
        yon = yon.toLowerCase();
        if(yon.equals("yes") || yon.equals("y")) return 1; //yes or y --> 1
        else if(yon.equals("no") || yon.equals("n")) return 0; //no or n --> 0
        else return -1; //others --> -1
    }

}
