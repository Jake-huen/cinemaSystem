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
	
	
	public static void getMovie() {
		
	}
	public static void setMovie(String title,String runtime) {
		
	}
	public static void main(String args[]) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        MovieInfo movie = new MovieInfo();
        
	}
}
