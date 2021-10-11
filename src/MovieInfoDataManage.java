import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
public class MovieInfoDataManage {
	static String title;
	static String runtime;
	static List<Map<String,String>> movieData;
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	static MovieInfo movieinfo;
	public static void getJson() { // json 파일 get
		try {
			//json 파일 읽어서, movieinfo 형태로 변환
			Reader reader = new FileReader(".\\resource\\movie.json");
			movieinfo = gson.fromJson(reader,MovieInfo.class);
			movieData = movieinfo.getMovie();
			System.out.println(movieinfo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setJson(String title,String runtime) { //json 파일 set
		getJson();
		MovieInfoDataManage.title=title;
		MovieInfoDataManage.runtime = runtime;
		Map<String,String> movie = new LinkedHashMap<>();
		movie.put("title",title);
		movie.put("runtime",runtime);
		List<Map<String,String>> new_movie = new ArrayList<>();
		for(int i=0;i<movieData.size();i++) {
			new_movie.add(movieData.get(i));
		}
		new_movie.add(movie);
		movieinfo.setMovie(new_movie);
		// getJson();
		try {
			FileWriter fw = new FileWriter(".\\resource\\movie.json");
			gson.toJson(movieinfo,fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String args[]) {
		setJson("오징어 게임","90분");
		getJson();
	}
}
