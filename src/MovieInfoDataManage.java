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
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	static MovieInfo movieinfo;
	
	public static void getJson() { // json 파일 get
		
	}
	public static void setJson() { //json 파일 set //String new_title,String new_runtime
		JsonObject jsonobject = new JsonObject();
		jsonobject.addProperty("DP","90분");
		String json = gson.toJson(jsonobject);
		System.out.println(json);
		FileWriter writer;
		try {
			writer = new FileWriter(".\\resource\\movie.json");
			System.out.println(json);
			writer.write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		setJson();
	}
}
