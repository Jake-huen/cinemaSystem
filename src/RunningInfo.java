import java.util.ArrayList;

public class RunningInfo {//상영정보
    private String code;
    private String movie;
    private String date; //날짜, 시각, 상영관, 예약 좌석
    private String time;
    private String theater;
    private ArrayList<ReserveInfo> reserve;

    RunningInfo(String date, String time, String movie, String theater, ArrayList<ReserveInfo> reserve){
        this.code = date + time + theater;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.theater = theater;
        this.reserve = reserve;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMovieName() {
        return movie;
    }

    public void setMovieName(String movie) {
        this.movie = movie;
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
        this.setCode(date+time+theater);
    }

    public ArrayList<ReserveInfo> getReserve() {
        return reserve;
    }

    public void setReserve(ArrayList<ReserveInfo> reserve) {
        this.reserve = reserve;
    }
    

	@Override
	public String toString() {
		String runInfoStr = Print.makeDateFormet(date) +" | ";
		runInfoStr += Print.makeTimeFormet(time) +" | ";
		runInfoStr += theater+" | ";
		runInfoStr += movie +"\n";
		return runInfoStr;
	}
	public boolean check(int row, int col) {
		if(reserve==null) {
			return false;
		}else {
			for(int i =0; i<reserve.size(); i++) {
				if(reserve.get(i).row_check(row)||reserve.get(i).col_check(col))
					return true;//수정불가
			}
		}
		return false;
	}

}
