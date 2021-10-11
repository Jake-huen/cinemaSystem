import java.util.List;
import java.util.Map;

public class MovieInfo {//영화정보
	String title;
	String runtime;
	List<Map<String,String>> movies;
	MovieInfo(){
		title="";
		runtime="";
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
}
