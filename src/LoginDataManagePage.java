import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LoginDataManagePage {
	static String id;
	static String pw;
	static List<String> code;
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
	
	public static void set_Admin(String ins_id, String ins_pw)
	{
		getJson();
		id = ins_id;
		pw = ins_pw;
		
		Map<String, String> admin = new LinkedHashMap<>();
		admin.put("id", id);
		admin.put("pw", pw);
		
		List<Map<String, String>> new_admin = new ArrayList<>();
		for(int i=0; i<adminData.size(); i++)
		{
			if(adminData.get(i).get("id").equals(id))			//아이디가 동일할 경우 재등록(수정할때 사용)
				continue;
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
	
	public static void set_User(String ins_id, String ins_pw, List<String> ins_code)
	{
		getJson();
		id = ins_id;
		pw = ins_pw;
		code = ins_code;
		
		Map<String, Object> user = new LinkedHashMap<>();
		user.put("id", id);
		user.put("pw", pw);
		user.put("code", code);

		List<Map<String, Object>> new_user = new ArrayList<>();
		for(int i=0; i<userData.size(); i++)
		{
			if(userData.get(i).get("id").equals(id))		//아이디가 동일할 경우 재등록(수정할때 사용)
				continue;
			new_user.add(userData.get(i));
		}
		new_user.add(user);
		logininfo.setUser(new_user);
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
	public static List<String> getCode(String id)
	{// 만약 알맞은 id없으면 빈배열 반환, 알맞은 id있어도 code가 없을시 빈배열 반환
		getJson();
		List<String> code_check= new ArrayList<String>();
		for(int i=0; i<userData.size(); i++)
		{
			if(((String)userData.get(i).get("id")).equals(id))
			{
				code_check = (List<String>)userData.get(i).get("code");
			}
		}
		return code_check;
		
	}
	
	
	public static void addCode(String ins_id, String ins_pw, String add_code)
	{//아이디, 비밀번호를 이용해서 입력한 add_code값을 login.json에 추가
	//아이디, 비밀번호가 틀렸을때 add_code안해주는거 아직 안함.
		getJson();
		if (is_User(ins_id, ins_pw))
			{
				code = getCode(ins_id);
				code.add(add_code);
				set_User(ins_id, ins_pw, code);	
				return;
			}
		else
			System.out.println("해당하는 id가 없거나, 비밀번호가 올바르지 않습니다.");
		return;
	}
}
