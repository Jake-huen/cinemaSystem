import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TheaterDataManage {
	static String theater;
	static int row;
	static int col;
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static JsonObject getJson(){ // json 파일 get
		try {
			Reader reader = new FileReader(".\\resource\\theater.json");
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonObject jsonobject = element.getAsJsonObject();
			return jsonobject;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setJsonTheater(String theater,int row,int col) { //json 파일 set
		JsonObject jsonobject= getJson(); //Json파일 전체 받아옴
		JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
		System.out.println(theaterInfos);
		JsonObject temp=new JsonObject();
		temp.addProperty("theater","차리서관");
		temp.addProperty("row",row);
		temp.addProperty("col",col);
		theaterInfos.add(temp);
		jsonobject.add("theaters",theaterInfos);
		String json = gson.toJson(jsonobject);
		FileWriter writer = null;
		try {
			writer = new FileWriter(".\\resource\\theater.json");
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		setJsonTheater("차리서관",10,30);
	}
}
	
