public class MovieInfo {//영화정보
	String title;
	String runtime;
	MovieInfo(){
		title="";
		runtime="";
	}
	public String getTitle() {
		return title;
	}
	
	public String getRuntime() {
		return runtime;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String toString() {
		return "영화제목 : "+this.title+"\t상영시간 : "+this.runtime;
	}

}