import java.util.ArrayList;

public class ReserveInfo {
	private String userId;
	private ArrayList<String> seat;
	
	ReserveInfo(String _userId, ArrayList<String> _seat){
		this.userId = _userId;
		this.seat = _seat;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<String> getSeat() {
		return seat;
	}

	public void setSeat(ArrayList<String> seat) {
		this.seat = seat;
	}
	

	@Override
	public String toString() {
		int seatLen = seat.size();
		String rsvInfoStr = "예약인원 : " + seatLen +"명\n";
		rsvInfoStr+="예매 좌석 : ";
		for(int i = 0;i<seat.size();i++) {
			rsvInfoStr+=seat.get(i);
			if(i!=seatLen-1)
				rsvInfoStr+=", ";
		}
		rsvInfoStr+="\n";
		
		return rsvInfoStr;
	}
	public boolean row_check(int num) {
		for(int i =0; i<seat.size(); i++) {
			char s = seat.get(i).charAt(0);
			int asciiNum = s-64;
			if(asciiNum>num) return true; //상영관 크기를 줄였을때 손실된 좌석이 생김 수정불가
		}
		return false; //상영관 크기를 줄였을때 손실된 좌석 없음 수정가능
	}
	//예약된 자석중에 num보다 큰 열이 있으면 false 없으면 true
	public boolean col_check(int num) {
		for(int i =0; i<seat.size(); i++) {
			char s = seat.get(i).charAt(1);
			int asciiNum = s-48;
			if(asciiNum>num) return true; //상영관 크기를 줄였을때 손실된 좌석이 생김 수정불가
		}
		return false; //상영관 크기를 줄였을때 손실된 좌석 없음 수정가능
	}
	
}
