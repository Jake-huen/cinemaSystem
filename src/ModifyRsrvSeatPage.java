import java.util.ArrayList;
import java.util.Scanner;

public class ModifyRsrvSeatPage{
	
	public Scanner scan;

	private int[][]  theaterMap;
	private ArrayList<Pair> selectedSeats;
	private RunningInfo runInfo;
	private ReserveInfo userRsrvInfo;
	private int row;
	private int col; 
	private int userSeatNum;
	private int curSelectednum=1;
	
	private UserInfo user;
	
	public ModifyRsrvSeatPage(UserInfo user,int userSeatNum,TheaterInfo theater, RunningInfo runInfo, ReserveInfo userRsrvInfo) {
		scan= new Scanner(System.in);
		
		// 상영관 행, 열 받아오기
		int row = theater.getRow();
		int col = theater.getCol();
		
		// 배열 및 좌석 개수 초기화 
		theaterMap = new int[row][col]; // 0 : 빈 좌석, 1: 예매된 좌석 , 2: 기존 사용자 예매 좌석 3: 현재 사용자 예매 좌석  
		initTheaterMap(theater);
		
		selectedSeats = new ArrayList<Pair>();
		
		userSeatNum=userRsrvInfo.getSeat().length; /// 주의 : 인원 바뀐 경우 반영되어야 함!!!!! 
		
		// user 초기화 
		this.user = user;
		this.userSeatNum = userSeatNum;
		
	}

	public void showPage() {
		// 좌석표 출력
		printSeat(false);
		
		System.out.println();
		
		while(curSelectednum > userSeatNum) {
			String curSeatInfo = "["+ curSelectednum +"/"+userSeatNum+"]";
			System.out.print("좌석을 선택해 주세요."+curSeatInfo+" (뒤로가기:0) >>>");
			
			// 좌석 입력받기 
			String seat =scan.nextLine();
			
			// 0 입력한 경우 
			if(seat.trim().equals("0")) {
				if(curSelectednum == 1)
					return;
				else {
					curSelectednum--;
					continue;
				}
			}
			
			// 좌석 입력규칙 검사 
			seat = InputRule.SeatRule(seat, row, col);
			
			// 좌석 양식과 다른 입력 or 범위 벗어난 입력인 경우 
			if(seat.isEmpty()) {
				System.out.println("해당 좌석이 존재하지 않습니다.");
				continue;
			}
			
			// string -> Pair 객체로 변환 
			Pair seatPair = Print.seatStrToPair(seat);
			int curRow= seatPair.getRow();
			int curCol= seatPair.getCol();
			
			// 이미 선택된 좌석인 경우 ( 다른 사람이 선택한 좌석 or 본인이 현재 선택한 좌석 ) 
			if(theaterMap[curRow][curCol] == 1 ||theaterMap[curRow][curCol] == 3) {
				System.out.println("이미 선택된 좌석입니다.");
				continue;
			}
			
			// 정상 좌석인 경우 
			curSelectednum++;
			theaterMap[curRow][curCol] = 3;
			selectedSeats.add(seatPair);
			
		}
		
		// 수정된 좌석표 출력 
		printSeat(true);
		System.out.println();
		System.out.println("좌석 수정이 완료되었습니다.");
		
		// json 에서 데이터 수정 - 미구현 
		/* 
		 * <수정해야할 데이터>
		 * - runningInfo 
		 * 
		 * */
		userRsrvInfo.setSeat(ModifyRsrvInfo());
		
	}
	
	private String[] ModifyRsrvInfo() {
		String[] seatStrs= new String[userSeatNum];
		for(int i = 0; i<userSeatNum ;i++) {
			seatStrs[i] = Print.PairToSeatStr(selectedSeats.get(i));
		}
		return seatStrs;
			
	}
	
	// theaterMap 초기화 함수 
	private void initTheaterMap(TheaterInfo theater) {
		ArrayList<Pair> totalRsrvSeats = new ArrayList<Pair>(); // 전체 예매된 좌석 담는 배열
		ArrayList<Pair> userRsrvSeats = new ArrayList<Pair>(); // 사용자가 현재 예매한 좌석 담는 배열 
		
		// 전체 예매된 좌석 받아오기  
		ArrayList<ReserveInfo> rsrvInfos  = runInfo.getReserve();
		for(ReserveInfo rsrvInfo : rsrvInfos) {
			for(String seatStr : rsrvInfo.getSeat()) 
				totalRsrvSeats.add(Print.seatStrToPair(seatStr));
		}
		
		// 현재 예매된 좌석 받아오기
		for(String curSeatStr:userRsrvInfo.getSeat())
			userRsrvSeats.add(Print.seatStrToPair(curSeatStr));
		
		// 좌석 종류에 따라 theaterMap에 숫자 넣기 (초기화)  
		for(Pair trs : totalRsrvSeats) {
			theaterMap[trs.getRow()][trs.getCol()] = 1;
		}
		
		for(Pair urs : userRsrvSeats) {
			theaterMap[urs.getRow()][urs.getCol()] = 2;
		}
	}

	
	// 현재 예매 좌석 제외하고 출력하기 - 마지막 인자 : 현재 예매 좌석 출력할건지 말건지 결정 
	public void printSeat(boolean includeCurSeat) {		
		System.out.println("===== 예매 좌석 수정 =====");
		
		// 출력 
		System.out.println("□: 선택 가능 ▩: 예매 완료 ■: 현재 예매좌석");
		// 첫번째 행
		for(int c=0;c<=col;c++) {
			if(c==0)
				System.out.print("\t");
			else 
				System.out.print( c +" ");
				
			if(c ==col-1)
				System.out.println();
		}
		
		// 좌석 출력 
		for(int r=0;r<row;r++) {
			for(int c=0;c<col;c++) {
				if(c==0)
					System.out.print((char)('A'+ r)+" ");
				
				String symbol= "";
				switch(theaterMap[r][c]) {
				case 0: 
					symbol = "□";
					break;
				case 1:
					symbol = "▩";
					break;
				case 2:
					if(includeCurSeat)
						symbol = "■";
					else
						symbol = "□";
					break;
				}
				
				System.out.print(symbol +" ");
			}
			System.out.println();
		}
		
	}
	
}
