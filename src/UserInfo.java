//현재 로그인 되어있는 사용자의 id,pw,date,time정보
public class UserInfo {
	
	private String id;
	private String pw;
	private String date;
	private String time;
	private boolean is_Admin;		//true면 admin, false면 user
	
	public UserInfo(String id, String pw, String date, String time)
	{
		this.id = id;
		this.pw = pw;
		this.date = date;
		this.time = time;
	}
	
	public UserInfo(String id, String pw, String date, String time, boolean is_admin)
	{
		this.id = id;
		this.pw = pw;
		this.date = date;
		this.time = time;
		this.is_Admin = is_admin; 
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
	
	public void setIsAdmin(boolean is_Admin)
	{
		this.is_Admin = is_Admin;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	public String getId() {
		return id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public boolean getIsAdmin() {
		return is_Admin;
	}
	
}
