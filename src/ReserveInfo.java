
public class ReserveInfo {
	private String userId;
	private String[] seat;
	
	ReserveInfo(String _userId, String[] _seat){
		this.userId = _userId;
		this.seat = _seat;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getSeat() {
		return seat;
	}

	public void setSeat(String[] seat) {
		this.seat = seat;
	}
	

	@Override
	public String toString() {
		int seatLen = seat.length;
		String rsvInfoStr = "예약인원 : " + seatLen +"명\n";
		rsvInfoStr+="예매 좌석 : ";
		for(int i = 0;i<seat.length;i++) {
			rsvInfoStr+=seat[i];
			if(i!=seatLen-1)
				rsvInfoStr+=", ";
		}
		rsvInfoStr+="\n";
		
		return rsvInfoStr;
	}
	
	
}
