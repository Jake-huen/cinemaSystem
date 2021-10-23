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
			// rt[i]=rt[i].substring(1,rt[i].length()-1);
			//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
			//System.out.println(movieinfos.get("title"));
			// System.out.println(rt[i]);
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
			System.out.println(rt[i]);
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
		String json = gson.toJson(jsonobject);
		FileWriter writer = null;
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
	public static String readIndexMovie(int index) {//index해당하는 영화 출력
		JsonObject jsonobject = getJson();
		JsonArray movieInfos = (JsonArray)jsonobject.get("movies");
		String rt=((JsonObject) movieInfos.get(index)).get("title").toString();
		rt= rt.substring(1,rt.length()-1);
		String runtime_temp = ((JsonObject) movieInfos.get(index)).get("runtime").toString();
		runtime_temp= runtime_temp.substring(1,runtime_temp.length()-1);
		rt+=" / "+runtime_temp;
		// System.out.println(rt);
			//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
			//System.out.println(movieinfo.get("runtime"));
		return rt;
	}
	public static String getmovieRuntime(int index) { //특정 영화 runtime만 받아오기
		JsonObject jsonobject = getJson();
		JsonArray movieInfos = (JsonArray)jsonobject.get("movies");
		String rt=((JsonObject) movieInfos.get(index)).get("title").toString();
		String runtime_temp = ((JsonObject) movieInfos.get(index)).get("runtime").toString();
		runtime_temp= runtime_temp.substring(1,runtime_temp.length()-2);
		// System.out.println(rt);
			//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
			//System.out.println(movieinfo.get("runtime"));
		return runtime_temp;
	}
	public static void fixMovie(int index,String newT,String newR) {//index받아와서 해당 영화 수정
		try {
			Reader reader = new FileReader(".\\resource\\movie.json");
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonObject jsonobject = element.getAsJsonObject();

			JsonArray movieInfos = (JsonArray)jsonobject.get("movies");
			JsonObject movieinfo =(JsonObject)movieInfos.get(index);
			movieinfo.addProperty("title", newT);
			movieinfo.addProperty("runtime", newR);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(element);
			FileWriter writer=null;
			try {
				writer = new FileWriter(".\\resource\\movie.json");
				writer.write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("수정완료");
	}
	public static void deleteMovie(int index) {//해당 index의 영화 삭제
		try {
			Reader reader = new FileReader(".\\resource\\movie.json");
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonObject jsonobject = element.getAsJsonObject();
			JsonArray movieInfos = (JsonArray)jsonobject.get("movies");
			movieInfos.remove(index);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(element);
			FileWriter writer=null;
			try {
				writer = new FileWriter(".\\resource\\movie.json");
				writer.write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("삭제완료");
	}
//	public static void main(String args[]) {
//		System.out.println(getmovieRuntime(1));
//	}
}
