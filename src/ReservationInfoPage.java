import java.util.Scanner;

public class ReservationInfoPage {//8.2.4 예매정보확인페이지

	static Scanner scan = new Scanner(System.in);

	public static void reservationInfoPage() {
		while(true) {
			String tmp="";
			System.out.println("=========================");
			System.out.println("확인할 예매 정보를 검색해주세요(날짜␣영화제목␣상영관)");
			System.out.print(">>>");
			tmp=scan.nextLine();
			String[] array = tmp.split(" ");
			int num = array.length;
			if(num>=3) {
				String temp = "";
				for(int i=1; i<num-1; i++)
					temp +=array[i]+" ";
				temp = temp.trim();
				array[1] = temp;
				array[2] = array[num-1];
				if(InputRule.DateRule2(array[0])!=null) {
					array[0] = InputRule.DateRule2(array[0]);
				}
				if(InputRule.DateRule2(array[0])==null) {
					System.out.println("올바르지 않은 입력입니다.");
				}else if(InputRule.ScreenRule2(array[2])==null) {
					System.out.println("올바르지 않은 입력입니다.");
				}else if(InputRule.MTRule(array[1])==null) {
					System.out.println("올바르지 않은 입력입니다.");
				}else {
					reservationInfoDetailPage(array[0], array[1], array[2]);
					return;
				}
			}else {
				System.out.println("올바르지 않은 입력입니다.");
			}
		}
		//array[0] = 날짜, array[1] = 영화제목,array[2] = 상영관
		//array[0] = 날짜, array[1] = 영화제목,array[2] = 상영관
		//입력규칙 확인
	}
	public static void reservationInfoDetailPage(String date, String movieName, String theaterName) {//8.2.4.1 예매현황확인
		while(true) {
			String tmp="";
			System.out.println("======예매정보======");
			System.out.println("(뒤로가기는 0을 입력해주세요.)");
			//시간 영화이름 좌석현황 출력할 String
			String result = "";

			int count = RunningInfoManage.count(date, movieName, theaterName);
			if(count==0) {
				System.out.println("해당하는 영화없음");
				while(true) {
					tmp=scan.nextLine();
					if(tmp.equals("0"))
						break;
				}
				return;
			}
			else {
				for(int i = 0; i<count; i++) {
					//date, movieName, theaterName이 일치하는 info.json을 찾아서 time출력하기
					String startTime = RunningInfoManage.checkDateMovieTheater(date, movieName, theaterName, i);
					//date, movieName, theaterName이 일치하는 RunningInfo을 찾아서 예약된 자리가 몇자리인지 출력하기
					int reservedSeatsNum = RunningInfoManage.checkReservedSeatsNum(date, movieName, theaterName, i);
					//theaterName으로 좌석수가져오기
					int totalseats = TheaterDataManage.readTheaterNameReturnSeat(theaterName, i);
					result+=startTime.substring(0,2)+":"+startTime.substring(2,4)+" "+movieName+" "+reservedSeatsNum+"/"+totalseats+"\n";
				}
				System.out.println(result);
				while(true) {
					tmp=scan.nextLine();
					if(tmp.equals("0"))
						break;
				}
				return;
			}
		}//while end
	}
}

