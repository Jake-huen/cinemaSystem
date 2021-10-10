import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MovieInformation {
	Gson gson = new Gson();
	Gson gson2 = new GsonBuilder().create();
	Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
}
