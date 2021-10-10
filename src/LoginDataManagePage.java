import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginDataManagePage {
	static String id;
	static String pw;
	static String[] code;
	static Map<String, Object> user;
	static List<Map<String, String>> adminData;
	static List<Map<String, Object>> userData;
	static LoginInfo logininfo;
	static Gson gson = new Gson();
	
	public static void getJson()
	{
		JsonParser parser = new JsonParser();
		try {
			Reader reader = new FileReader(".\\resource\\login.json");
			
			logininfo = gson.fromJson(reader, LoginInfo.class);
			adminData = logininfo.getAdmin();
			userData = logininfo.getUser();
			System.out.println(logininfo);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean is_Admin(String id, String pw)
	{
		getJson();
		for(int i=0; i<adminData.size(); i++)
		{
			if(((String)adminData.get(i).get("id")).equals(id) &&((String)adminData.get(i).get("pw")).equals(pw))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean is_User(String id, String pw)
	{
		getJson();
		for(int i=0; i<userData.size(); i++)
		{
			if(((String)userData.get(i).get("id")).equals(id) &&((String)userData.get(i).get("pw")).equals(pw))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void setAdmin(String ins_id, String ins_pw)
	{
		getJson();
		id = ins_id;
		pw = ins_pw;
		
		Map<String, String> admin = new HashMap<>();
		admin.put("id", id);
		admin.put("pw", pw);
		
		List<Map<String, String>> new_admin = new ArrayList<>();
		for(int i=0; i<adminData.size(); i++)
		{
			new_admin.add(adminData.get(i));
		}
		new_admin.add(admin);
		logininfo.setAdmin(new_admin);
		try {
			FileWriter fw = new FileWriter(".\\resource\\login.json");
			gson.toJson(logininfo, fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public void setUser()
	{
		
	}
	
	public void setUserCode(String id, String code)
	{
		
	}
}
