import java.util.ArrayList;
import java.util.Scanner;

public class ModifyRsrvSeatPage{
	
	public Scanner scan;

	private int[][]  theaterMap;
	private ArrayList<Pair> selectedSeats;
	private RunningInfo runInfo;
	private ReserveInfo rsrvInfo;
	private int row;
	private int col; 
	private int userSeatNum;
	private int curSelectednum=1;
	
	private UserInfo user;
	
	// 생성자 - 예매 인원 수 변경하고 좌석 변경하는 경우 
	public ModifyRsrvSeatPage(UserInfo user, UserRsrvInfo userRsrvInfo,int userSeatNum) {
		this(user,userRsrvInfo);
		this.userSeatNum = userSeatNum; // 새로 입력한 좌석 개수 사용 
	}
	
	// 생성자 - 좌석 변경만 하는 경우 
	public ModifyRsrvSeatPage(UserInfo user,UserRsrvInfo userRsrvInfo) {
		scan= new Scanner(System.in);
		// 상영정보 예매정보 초기화 
		runInfo = userRsrvInfo.getRunInfo();
		rsrvInfo = userRsrvInfo.getRsrvInfo();
		
		// 상영관 정보 받아오기  
		TheaterInfo theater = TheaterDataManage.findTheater(runInfo.getTheater());
		
		// 상영관 행, 열 받아오기
		row = theater.getRow();
		col = theater.getCol();
		
		// 상영관 배열 및 좌석배열 초기화 
		theaterMap = new int[row][col]; // 0 : 빈 좌석, 1: 예매된 좌석 , 2: 기존 사용자 예매 좌석 3: 현재 사용자 예매 좌석  
		initTheaterMap(theater);
		
		selectedSeats = new ArrayList<Pair>();
		
		// user 정보 초기화 
		this.user = user;
		userSeatNum=userRsrvInfo.getRsrvInfo().getSeat().length; // 사용자의 원래 예매 좌석 개수 그대로 활용 
		
	}

	// 예매 좌석 변경 페이지 
	public void showPage() {
		// 좌석표 출력
		printSeat(false);
		
		System.out.println();
		
		while(curSelectednum <= userSeatNum ) {
			String curSeatInfo = "["+ curSelectednum +"/"+userSeatNum+"]";
			System.out.print("좌석을 선택해 주세요."+curSeatInfo+" (뒤로가기:0) >>>");
			
			// 좌석 입력받기 
			String seat =scan.nextLine();
			seat = seat.trim();
			// 0 입력한 경우 
			if(seat.equals("0")) {
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
			if(seat == null) {
				System.out.println("해당 좌석이 존재하지 않습니다.");
				continue;
			}
			
			// string -> Pair 객체로 변환 
			Pair seatPair = Print.seatStrToPair(seat);
			int curRow= seatPair.getRow();
			int curCol= seatPair.getCol();
			System.out.println("cur Row : "+curRow +"cur Col : "+curCol);
			
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
		
		// json 에서 데이터 수정
		rsrvInfo.setSeat(modifyRsrvInfo());
		RunningInfoManage.modifyReserve(runInfo, rsrvInfo, user.getId());
		
	}
	
	// 변경된 좌석 배열 반환 - 파일에 반영 안됌  
	private String[] modifyRsrvInfo() {
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
		
		// 기존 예매된 좌석 받아오기
		for(String curSeatStr:rsrvInfo.getSeat())
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
				System.out.print("  ");
			else 
				System.out.print( c +" ");
				
			if(c == col)
				System.out.println();
		}
		
		// 좌석 출력 
		for(int r=0;r<row;r++) {
			for(int c=0;c<col;c++) {
				if(c==0)
					System.out.print((char)('A'+ r)+" ");
				
				String symbol= "";
				switch(theaterMap[r][c]) {
				case 0: case 2:
					symbol = "□";
					break;
				case 1:
					symbol = "▩";
					break;
				case 3:
					symbol = "■";
					break;
				}
				
				System.out.print(symbol +" ");
			}
			System.out.println();
		}
		
	}
	
}
