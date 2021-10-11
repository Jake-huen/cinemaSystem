//현재 로그인 되어있는 사용자의 id,pw,date,time정보
public class UserInfo {
	
	private String id;
	private String pw;
	private String date;
	private String time;
	
	public UserInfo(String id, String pw, String date, String time)
	{
		this.id = id;
		this.pw = pw;
		this.date = date;
		this.time = time;
		
	}
	public String getId() {
		return id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
}
