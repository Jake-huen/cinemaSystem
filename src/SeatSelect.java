
public class SeatSelect {

	public static void SeatMain() {
		System.out.println("===== 영화예매 ======\r\n"
				+ "----------- 선택한 영화 정보 -----------");
		System.out.println("2021년 9월 24일 | 19:30 | 3관 | 오징어 게임");
		System.out.print("예매 인원 >>>");
		int ppl = InputRule.rsrvPplInput();
		seatPrint();
		String seatInput = null;
		Pair[] rsSeat = new Pair[ppl];
		for(int i = 0; i < ppl; i++) {
			System.out.print("좌석을 선택해 주세요["+i+"/"+ppl+"]>>>");
			seatInput = InputRule.SeatRule();
			int x = seatInput.charAt(0) - 'a';
			int y = 0;
			if(seatInput.length() == 2) y = seatInput.charAt(1);
			else y = seatInput.charAt(1) * 10 + seatInput.charAt(2);
			rsSeat[i] = new Pair(x, y);
		}
		seatReservedPrint(rsSeat);
		System.out.print("선택하신 좌석 C6 C7이 맞습니까?(y/n) >>>");
		int yorn = InputRule.YesOrNo();
		if(yorn == 1) {
			// 예약 성공
		}
		else {
			// 예약 실패
		}
	}
	
	public static void seatPrint() {
		int row = 10;
		int col = 10;
		int tmp = 1; // 예약된 고객 수 임시
		Pair[] pair = new Pair[tmp];
		pair[0] = new Pair(0,3);
		
		int[][] seat = new int[row][col]; // 좌석 배열 초기화
		
		for(int i = 0; i < tmp; i++) { // 예약된 좌석 >> 1
			int _row = pair[i].getRow();
			int _col = pair[i].getCol() - 1;
			seat[_row][_col] = 1;
		}
		
		System.out.println("□: 선택 가능 ▩: 예매 완료");
		System.out.println("  ");
		
		for(int i = 0; i < col; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
		
		for(int i = 0; i < row; i++) {
			char alpRow = (char)(i + 65);
			System.out.print(alpRow + " ");
			for(int j = 0; j < col; j++) {
				System.out.print(seat[i][j] == 1 ? "▩ " : "□ ");
			}
			System.out.println();
		}
	}
	
	public static void seatReservedPrint(Pair[] rsPair) {
		int row = 10;
		int col = 10;
		int tmp = 1; // 예약된 고객 수 임시
		Pair[] pair = new Pair[tmp];
		pair[0] = new Pair(0,3);
		
		int[][] seat = new int[row][col]; // 좌석 배열 초기화
		
		for(int i = 0; i < tmp; i++) { // 예약된 좌석 >> 1
			int _row = pair[i].getRow();
			int _col = pair[i].getCol() - 1;
			seat[_row][_col] = 1;
		}
		
		for(Pair rs:rsPair) { // 예약 진행 중 좌석 >> 2
			int rsrow = rs.getRow();
			int rscol = rs.getCol() - 1;
			seat[rsrow][rscol] = 2;
		}
		
		System.out.println("□: 선택 가능 ▩: 예매 완료 ■: 선택한 좌석");
		System.out.println("  ");
		
		for(int i = 0; i < col; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
		
		for(int i = 0; i < row; i++) {
			char alpRow = (char)(i + 65);
			System.out.print(alpRow + " ");
			for(int j = 0; j < col; j++) {
				if(seat[i][j] == 0) {
					System.out.print("□");
				}
				else if(seat[i][j] == 1) {
					System.out.print("▩");
				}
				else {
					System.out.print("■");
				}
			}
			System.out.println();
		}
	}
}
