import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class TheaterDataArray {
	static String theaterName;
	static int row;
	static int col;
	static List<Map<String, Object>> theaters;
	static TheaterInfo theaterinfo;
	static Gson gson = new Gson();
	
	public static void readData() {
		JsonParser parser = new JsonParser();
		try {
			Reader reader = new FileReader("filepath.json");
			theaterinfo = gson.fromJson(reader, TheaterInfo.class);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
}
