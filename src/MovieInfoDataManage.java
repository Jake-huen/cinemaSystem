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
public class MovieInfoDataManage {
	static String title;
	static String runtime;
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static JsonObject getJson(){ // json 파일 get
		try {
			Reader reader = new FileReader(".\\resource\\movie.json");
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonObject jsonobject = element.getAsJsonObject();
			// JsonArray movieInfos = (JsonArray)jsonobject.get("movies");//영화 전체 가져오기
			return jsonobject;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String[] getTitle() { //영화 제목들만 받아오기 -->for문으로 영화제목판별
		JsonObject jsonobject = getJson();
		JsonArray movieInfos = (JsonArray)jsonobject.get("movies");
		if(movieInfos.size()==0) return null;
		String[] rt=new String[movieInfos.size()];
		for(int i=0;i<movieInfos.size();i++) { //영화전체 크기만큼 가져오기
			rt[i]=((JsonObject) movieInfos.get(i)).get("title").toString();
			//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
			//System.out.println(movieinfo.get("title"));
		}
		return rt;
	}
	public static String[] getRuntime() { //영화 runtime들만 받아오기
		JsonObject jsonobject = getJson();
		JsonArray movieInfos = (JsonArray)jsonobject.get("movies");
		if(movieInfos.size()==0) return null;
		String[] rt=new String[movieInfos.size()];
		for(int i=0;i<movieInfos.size();i++) { //영화전체 크기만큼 가져오기
			rt[i]=((JsonObject) movieInfos.get(i)).get("runtime").toString();
			//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
			//System.out.println(movieinfo.get("runtime"));
		}
		return rt;
	}
	
	public static void setJsonMovie(String new_title,String new_runtime) { //json 파일 set
		JsonObject jsonobject= getJson(); //Json파일 전체 받아옴
		JsonArray movieInfos = (JsonArray)jsonobject.get("movies");
		JsonObject temp=new JsonObject();
		temp.addProperty("title",new_title);
		temp.addProperty("runtime",new_runtime);
		movieInfos.add(temp);
		jsonobject.add("movies",movieInfos);
		System.out.println(jsonobject);
		String json = gson.toJson(jsonobject);
		FileWriter writer = null;
		System.out.println(json);
		try {
			writer = new FileWriter(".\\resource\\movie.json");
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void fixMovie(int index) {//index받아와서 해당 영화 수정
		JsonObject jsonobject = getJson();
		JsonArray movieInfos = (JsonArray)jsonobject.get("movies");
		JsonObject movieinfo =(JsonObject)movieInfos.get(index);
		movieinfo.addProperty("title", runtime);
		
	}
}