import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;

public class RunningInfoManage {
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static String path = ".\\resource\\info.json";
	private static ArrayList<RunningInfo> riArr;

    public static ArrayList<RunningInfo> getRiArr() {
        getJson();
        return riArr;
    }
//    public static void setRiArr(ArrayList<RunningInfo> riArr) {
//        test.riArr = riArr;
//    }

    public static void getJson() {
        riArr = new ArrayList<RunningInfo>();
        RunningInfo[] riArrTmp;

        try  {
        	Reader reader = new FileReader(path);
            riArrTmp = gson.fromJson(reader, RunningInfo[].class);
            for(RunningInfo ri : riArrTmp){
                    riArr.add(ri);
                    // System.out.println(ri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setJson(String date, String time, String movie, String theater, ArrayList<ReserveInfo> rsiArr) {
        getJson();
        try{
        	FileWriter fw = new FileWriter(".\\resource\\info.json");
            RunningInfo ri = new RunningInfo(date,time,movie,theater,rsiArr);
            riArr.add(ri);

            gson.toJson(riArr, fw);
            fw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setJson2(String date, String time, String movie, String theater, ArrayList<ReserveInfo> rsiArr) {
        getJson();
        try{
        	FileWriter fw = new FileWriter(".\\resource\\info.json");
            RunningInfo ri = new RunningInfo(date,time,movie,theater,rsiArr);
            riArr.add(ri);

            gson.toJson(riArr, fw);
            fw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateReserve(RunningInfo ri, ReserveInfo rsi){
        getJson();
        try{
        	FileWriter fw = new FileWriter(".\\resource\\info.json");
            for(RunningInfo jsonRI : riArr){
                if(ri.getMovieName().equals(jsonRI.getMovieName())
                && ri.getTheater().equals(jsonRI.getTheater())
                && ri.getTime().equals(jsonRI.getTime())
                && ri.getDate().equals(jsonRI.getDate())){
                    System.out.println("true");
                    ArrayList<ReserveInfo> jsonReserve = jsonRI.getReserve();
                    jsonReserve.add(rsi);
                }
            }
            gson.toJson(riArr, fw);
            fw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String[]> readDateRi(String date,int theaterindex) {//특정날짜에 상영하는 상영정보 뽑기
        getJson();
        String theaterName=(TheaterDataManage.readIndexTheaterName(theaterindex)).replaceAll("\"","");
        ArrayList<String[]> rt=new ArrayList<String[]>(0);
        for(int i=0; i<riArr.size();i++) {
        	String[] tmp=new String[2];//tmp[0]은 시간정보, [1]은 영화이름
        	String getTheater=riArr.get(i).getTheater().replace("\"", "");
            if(riArr.get(i).getDate().equals(date)&&getTheater.equals(theaterName)) {
            	tmp[0]=riArr.get(i).getTime();
                tmp[1]=riArr.get(i).getMovieName();
                rt.add(tmp);
            }
        }
        return rt;
    }
    public static int count(String date, String movieName, String theaterName) {
		int count = 0;
		getJson();
		for(int i =0; i<riArr.size(); i++) {
			if(riArr.get(i).getDate().equals(date)&&riArr.get(i).getTheater().equals(theaterName)&&riArr.get(i).getMovieName().equals(movieName)) {
				count++;
			}
		}
		return count;
	}
  //아래의 int flag 변수는 info.json에서 date, movieName, theaterName이 일치하는 RunningInfo가 여러개 들어가 있다면 그 모든 객체를 찾기 위한 변수
  	public static String checkDateMovieTheater(String date, String movieName, String theaterName, int count) {
  		//info.json에서 date, movieName, theaterName이 일치하는 RunningInfo을 찾아서 time출력하기
  		getJson();
  		int flag = 0;
  		for(int i =0; i<riArr.size(); i++) {
  			if(riArr.get(i).getDate().equals(date)&&riArr.get(i).getTheater().equals(theaterName)&&riArr.get(i).getMovieName().equals(movieName)) {
  				if(flag==count)
  					return riArr.get(i).getTime();
  				else
  					flag++;
  			}
  		}
  		return null;
  	}
  	public static int checkReservedSeatsNum(String date, String movieName, String theaterName, int count) {
		//info.json에서 date, movieName, theaterName이 일치하는 RunningInfo을 찾아서 예약된 자리가 몇자리인지 출력하기
		getJson();
		int sumReservedSeats=0;
		int flag = 0;
		for(int i =0; i<riArr.size(); i++) {
			if(riArr.get(i).getDate().equals(date)&&riArr.get(i).getTheater().equals(theaterName)&&riArr.get(i).getMovieName().equals(movieName)) {
				if(count==flag) {
					ArrayList<ReserveInfo> reserveInfo = riArr.get(i).getReserve();
					for(int j = 0; j<reserveInfo.size(); j++) {
						sumReservedSeats+=reserveInfo.get(j).getSeat().length;
					}
					return sumReservedSeats;
				}else {
					flag++;
				}
			}
		}return -1;//일치하는 부분 없음
		
	}
//    public static void main(String[] args) {
//    	getJson();
//    }
}
