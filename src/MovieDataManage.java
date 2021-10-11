import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MovieDataManage {
	static String title;
	static String runtime;
	static List<String> code;
	static Map<String,Object> movie;
	// static List<Map<String,String>> movieInfo;
	static MovieInfo movieInfo;
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static void getMovie() {
		
	}
	public static void setMovie(String title,String runtime) {
		
	}
	public static void main(String args[]) {
        
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("title", "오징어게임");
        jsonobject.addProperty("runtime", "90");        
       
		 String json = gson.toJson(jsonobject);
		 System.out.println(jsonobject);
		 try {
			FileWriter writer = new FileWriter(".\\resource\\movie.json");
			writer.write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
