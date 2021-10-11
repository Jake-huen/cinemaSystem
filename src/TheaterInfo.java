import java.util.*;

public class TheaterInfo {//상영관정보
	String Name;
	int row;
	int col;
	List<Map<String, Object>> theaters;
	
	public List<Map<String, Object>> getTheaters() {
		return theaters;
	}
	public void setTheaters(List<Map<String, Object>> theaters) {
		this.theaters = theaters;
	}
	
}
