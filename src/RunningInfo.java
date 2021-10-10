

public class RunningInfo {//상영정보
	private String date; //날짜, 시각, 상영관, 예약 좌석
	private String time;
	private String theater;
	private ReserveInfo[] reserve;
	
	RunningInfo(String date, String time, String theater, ReserveInfo[] reserve){
		this.date = date;
		this.time = time;
		this.theater = theater;
		this.reserve = reserve;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public ReserveInfo[] getReserve() {
		return reserve;
	}

	public void setReserve(ReserveInfo[] reserve) {
		this.reserve = reserve;
	}
	
	

}
