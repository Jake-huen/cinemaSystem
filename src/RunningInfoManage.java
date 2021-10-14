import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

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
}
