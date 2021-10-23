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
    public static void setRiArr(ArrayList<RunningInfo> riArr) {
        test.riArr = riArr;
    }

    public static void getJson() {
        riArr = new ArrayList<>();
        RunningInfo[] riArrTmp;

        try (Reader reader = new FileReader(path)) {
            riArrTmp = gson.fromJson(reader, RunningInfo[].class);
            for(RunningInfo ri : riArrTmp){
                    riArr.add(ri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setJson(String date, String time, String movie, String theater, ArrayList<ReserveInfo> rsiArr) {
        getJson();
        try(FileWriter fw = new FileWriter(path)){
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
        try(FileWriter fw = new FileWriter(path)){
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
        ArrayList<String[]> rt=new ArrayList<String[]>();
        String[] tmp=new String[2];//tmp[0]은 시간정보, [1]은 영화이름
        for(int i=0; i<riArr.size();i++) {
            if(riArr.get(i).getDate().equals(date)&&riArr.get(i).getTheater().equals(theaterName)) {
                tmp[0]=riArr.get(i).getTime();
                tmp[1]=riArr.get(i).getMovieName();
                rt.add(tmp);
            }
        }
        return rt;
    }
    
    // 해당 날짜에 상영하는 상영정보 반환 (날짜만 고려) 
    public static ArrayList<RunningInfo> readDateRi(String date) {//특정날짜에 상영하는 상영정보 뽑기
        getJson();
        ArrayList<RunningInfo> rt=new ArrayList<RunningInfo>();
        for(int i=0; i<riArr.size();i++) {
            if(riArr.get(i).getDate().equals(date)) {
                rt.add(riArr.get(i));
            }
        }
        return rt;
    }
    
    // 예매 내역 수정하는 함수 
    public static void modifyReserve(RunningInfo ri, ReserveInfo rsi, String id){
        getJson();
        try(FileWriter fw = new FileWriter(path)){
            for(RunningInfo jsonRI : riArr){
                if(ri.getMovieName().equals(jsonRI.getMovieName())
                && ri.getTheater().equals(jsonRI.getTheater())
                && ri.getTime().equals(jsonRI.getTime())
                && ri.getDate().equals(jsonRI.getDate())){

                	ArrayList<ReserveInfo> jsonReserve = jsonRI.getReserve();
                    for(ReserveInfo rsrvInfo : jsonReserve) {
                    	// 해당 id 갖는 ReserveInfo 찾아서 내용변경 
                    	if(rsrvInfo.getUserId().equals(id)) 
                    		rsrvInfo = rsi;
                    }
                }
            }
            gson.toJson(riArr, fw);
            fw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 예매 내역 삭제하는 함수 
    public static void removeReserve(RunningInfo ri, ReserveInfo rsi, String id){
        getJson();
        try(FileWriter fw = new FileWriter(path)){
            for(RunningInfo jsonRI : riArr){
                if(ri.getMovieName().equals(jsonRI.getMovieName())
                && ri.getTheater().equals(jsonRI.getTheater())
                && ri.getTime().equals(jsonRI.getTime())
                && ri.getDate().equals(jsonRI.getDate())){

                	ArrayList<ReserveInfo> jsonReserve = jsonRI.getReserve();
                    for(ReserveInfo rsrvInfo : jsonReserve) {
                    	// 해당 id 갖는 ReserveInfo 찾아서 삭제 
                    	if(rsrvInfo.getUserId().equals(id)) {
                    		int rmIndex = jsonReserve.indexOf(rsrvInfo);
                    		jsonReserve.remove(rmIndex);
                    		break;
                    	}
                    		
                    }
                }
            }
            gson.toJson(riArr, fw);
            fw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
