import java.util.List;
import java.util.Map;

public class LoginInfo {//전체사용자정보
	List<String> code;
	List<Map<String, String>> admin;
	List<Map<String, Object>> user;
	
	public List<Map<String, String>> getAdmin()
	{
		return admin;
	}
	
	public List<Map<String, Object>> getUser()
	{
		return user;
	}
	
	public void setAdmin(List<Map<String, String>> sadmin) {
		admin = sadmin;
		return;
	}
	
	public void setUser(List<Map<String, Object>> suser) {
		user = suser;
		return;
	}
	
	public String toString() {
		return admin + "\n유저" + user;
	}
}
