
public class MovieInformation { //영화 정보 json으로 받아오기
	// 영화제목, 영화 상영시간(runtime)
	// MovieTitle ,Runtime
	private String title;
	private int runtime;
	
	public MovieInformation(String title,int runtime) {
		this.title = title;
		this.runtime = runtime;
	}
	
	public String toString() {
		return "Movietitle="+title+"\tRuntime="+runtime;
	}
}
