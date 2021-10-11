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
	public String toString() {
		return "MovieTitle:"+getTitle()+"상영시간 : "+getRuntime(); 
	}

	public List<Map<String, String>> getMovie() {
		return null;
	}

	public void setMovie(List<Map<String, String>> new_movie) {
		// TODO Auto-generated method stub
		
	}
}
