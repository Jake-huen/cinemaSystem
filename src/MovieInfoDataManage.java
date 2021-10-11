import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
public class MovieInfoDataManage {
	static String title;
	static String runtime;
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static void getJson() { // json 파일 get
		try {
			//json 파일 읽어서, movieinfo 형태로 변환
			Reader reader = new FileReader(".\\resource\\movie.json");
			MovieInfo movieinfo = gson.fromJson(reader,MovieInfo.class);
			
			System.out.println(movieinfo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setJson(String title,String runtime) { //json 파일 set
		MovieInfo movieinfo = new MovieInfo();
		movieinfo.setTitle(title);
		movieinfo.setRuntime(runtime);
		
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
//	public static void main(String args[]) {
//		setJson("오징어 게임","90분");
//		getJson();
//	}
}
