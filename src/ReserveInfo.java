
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
	
	
	
}
