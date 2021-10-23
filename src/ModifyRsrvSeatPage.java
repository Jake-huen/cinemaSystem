import java.util.ArrayList;

public class ModifyRsrvSeatPage{
	
	private int[][]  theaterMap;
	private int row;
	private int col; 
	private int userSeatNum;
	private int curSelectednum=0;
	
	public ModifyRsrvSeatPage(TheaterInfo theater, RunningInfo runInfo, ReserveInfo userRsrvInfo) {
		
		// 상영관 행, 열 받아오기
		int row = theater.getRow();
		int col = theater.getCol();
		
		theaterMap = new int[row][col]; // 0 : 빈 좌석, 1: 예매된 좌석 , 2: 현재 사용자 예매 좌석 
		initTheaterMap(theater, runInfo, userRsrvInfo);
		
		userSeatNum=userRsrvInfo.getSeat().length;
		
	}

	public void showPage() {
		// 좌석표 출력
		System.out.println("===== 예매 좌석 수정 =====");
		printSeat(false);
		System.out.println();
		
		while(true) {
			String curSeatInfo = "["+ curSelectednum +"/"+userSeatNum+"]";
			System.out.print("좌석을 선택해 주세요."+curSeatInfo+" (뒤로가기:0) >>>");
			
			
			
			
			
		}
		
		
		
		
	}
	
	// theaterMap 초기화 함수 
	private void initTheaterMap(TheaterInfo theater, RunningInfo runInfo, ReserveInfo userRsrvInfo) {
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
