import java.io.File;
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
	static String pathTheater = "."+File.separator+"resource"+File.separator+"theater.json";
	public static JsonObject getJson(){ // json 파일 get
		try {
			Reader reader = new FileReader(pathTheater);
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
	public static String[] getTheater() { //영화 제목들만 받아오기 -->for문으로 영화제목판별
		JsonObject jsonobject = getJson();
		JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
		if(theaterInfos.size()==0) return null;
		String[] rt=new String[theaterInfos.size()];
		for(int i=0;i<theaterInfos.size();i++) { //영화전체 크기만큼 가져오기
			rt[i]=((JsonObject) theaterInfos.get(i)).get("theater").toString();
			rt[i]=rt[i].substring(1,rt[i].length()-1);
			int row=Integer.parseInt(((JsonObject) theaterInfos.get(i)).get("row").toString());
			int col=Integer.parseInt(((JsonObject) theaterInfos.get(i)).get("col").toString());
			rt[i]+=" / "+row*col+"석";
			//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
			//System.out.println(movieinfo.get("title"));
		}
		return rt;
	}
	public static String[] getTheaterName() {
		JsonObject jsonobject = getJson();
		JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
		if(theaterInfos.size()==0) return null;
		String[] rt=new String[theaterInfos.size()];
		for(int i=0;i<theaterInfos.size();i++) { //영화전체 크기만큼 가져오기
			rt[i]=((JsonObject) theaterInfos.get(i)).get("theater").toString();
			//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
			//System.out.println(movieinfo.get("title"));
		}
		return rt;
	}
	public static void setJsonTheater(String theater,int row,int col) { //json 파일 set
		JsonObject jsonobject= getJson(); //Json파일 전체 받아옴
		JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
		JsonObject temp=new JsonObject();
		temp.addProperty("theater",theater);
		temp.addProperty("row",row);
		temp.addProperty("col",col);
		theaterInfos.add(temp);
		jsonobject.add("theaters",theaterInfos);
		String json = gson.toJson(jsonobject);
		FileWriter writer = null;
		try {
			writer = new FileWriter(pathTheater);
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String readIndexTheater(int index) {//index해당하는 영화관 출력
		JsonObject jsonobject = getJson();
		JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
		String rt=((JsonObject) theaterInfos.get(index)).get("theater").toString();
		int row=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("row").toString());
		int col=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("col").toString());
		rt+="/"+row*col+"석";
			//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
			//System.out.println(movieinfo.get("runtime"));
		return rt;
	}
	public static String readIndexTheaterName(int index) {//index해당하는 영화관 출력
		JsonObject jsonobject = getJson();
		JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
		String rt=((JsonObject) theaterInfos.get(index)).get("theater").toString();
			//JsonObject movieinfo =(JsonObject)movieInfos.get(i);
			//System.out.println(movieinfo.get("runtime"));
		return rt;
	}
	public static void fixTheater(int index,String newT,int row,int col) {//index받아와서 해당 영화관 수정
		try {
			Reader reader = new FileReader(pathTheater);
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonObject jsonobject = element.getAsJsonObject();

			JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
			JsonObject theaterinfo =(JsonObject)theaterInfos.get(index);
			
			String theaterName = ((JsonObject) theaterInfos.get(index)).get("theater").toString();
			int _row=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("row").toString());
			int _col=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("col").toString());
			theaterName =  Print.removeQuotes(theaterName);
			//기존의 theaterinfo의 행과 열이 입력값보다 크면 info.json에서 확인 필요
			if(_row>row || _col>col) {
				//info.json에서 확인
				if(RunningInfoManage.check_reserveInfo_for_fix(theaterName, row, col)) {
					System.out.println("상영등록정보 중 소실되는 예매좌석이 생기므로 좌석을 수정할 수 없습니다.");
					return;
				}
			}
			theaterinfo.addProperty("theater", newT);
			theaterinfo.addProperty("row", row);
			theaterinfo.addProperty("col", col);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(element);
			FileWriter writer=null;
			try {
				writer = new FileWriter(pathTheater);
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
	public static void deleteTheater(int index) {//해당 index의 영화관 삭제
		try {
			Reader reader = new FileReader(pathTheater);
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(reader);
			JsonObject jsonobject = element.getAsJsonObject();
			JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
			theaterInfos.remove(index);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(element);
			FileWriter writer=null;
			try {
				writer = new FileWriter(pathTheater);
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
	
	
	//상영관 명 입력하면 TheaterInfo객체 반환하는 함수 
	public static TheaterInfo findTheater(String theaterName) {
		JsonObject jsonobject = getJson();
		JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters");
		
		for( int i = 0;i<theaterInfos.size();i++) {
			String theater=((JsonObject) theaterInfos.get(i)).get("theater").toString();
			// 따옴표 제거 
			theater =  Print.removeQuotes(theater);
			
			if(theater.equals(theaterName)) {
				int row=Integer.parseInt(((JsonObject) theaterInfos.get(i)).get("row").toString());
				int col=Integer.parseInt(((JsonObject) theaterInfos.get(i)).get("col").toString());
				
				return new TheaterInfo(theater,row,col);
			}
			
		}
		return null;
	}
	public static int readTheaterNameReturnSeat(String theaterName, int count) {
		int index = 0;
		int flag = 0;
		String[] theaterNames =  getTheaterName();
		for(int i =0; i<theaterNames.length; i++) {
			if(theaterNames[i].equals(theaterName)) {
				if(count==flag) {
					index=i;
					break;
				}
				else {
					flag++;
				}
			}
		}
		JsonObject jsonobject = getJson();
		JsonArray theaterInfos = (JsonArray)jsonobject.get("theaters"); 
		int row=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("row").toString());
		int col=Integer.parseInt(((JsonObject) theaterInfos.get(index)).get("col").toString());
		return row*col;
	}
}
	
