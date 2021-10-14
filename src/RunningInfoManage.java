import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;

public class RunningInfoManage {
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static String path = ".\\resource\\info.json";
	private static RunningInfo[] riArr;
	private static int count = 0;

	public static RunningInfo[] getRiArr() {
		return riArr;
	}
	public static void setRiArr(RunningInfo[] _riArr) {
		riArr = _riArr;
	}

	public static void getJson() {
		riArr = new RunningInfo[500];
		RunningInfo[] riArrTmp;

		try (Reader reader = new FileReader(path)) {
			riArrTmp = gson.fromJson(reader, RunningInfo[].class);
			for(RunningInfo ri : riArrTmp){
				riArr[count++] = ri;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setJson(String date, String time, String movie, String theater, ReserveInfo[] rsiArr) {
		getJson();
		try(FileWriter fw = new FileWriter(path)){
			RunningInfo ri = new RunningInfo(date,time,movie,theater,rsiArr);
			riArr[count++] = ri;
			RunningInfo[] riArrTmp = new RunningInfo[count];

			for(int k = 0; k < count; k++){
				riArrTmp[k] = riArr[k];
			}
			gson.toJson(riArrTmp, fw);
			fw.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<String[]> readDateRi(String date,int theaterindex) {//특정날짜에 상영하는 상영정보 뽑기
		getJson();
		String theaterName=(TheaterDataManage.readIndexTheaterName(theaterindex)).replaceAll("\"","");
		ArrayList<String[]> rt=new ArrayList<String[]>();
		String[] tmp=new String[2];//tmp[0]은 시간정보, [1]은 영화이름
		for(int i=0; i<count;i++) {
			if(riArr[i].getDate().equals(date)&&riArr[i].getTheater().equals(theaterName)) {
				tmp[0]=riArr[i].getTime();
				tmp[1]=riArr[i].getMovieName();
				rt.add(tmp);
			}
		}
		return rt;
	}
}
