import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileWriter;
public class MovieInformationDataManage {
	public static void main(String[] args) {
		MovieInformation mi = new MovieInformation("오징어 게임",90);
		Gson gson = new Gson();
		String miJson = gson.toJson(mi);
		System.out.println(miJson);
	}
}
