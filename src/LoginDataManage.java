import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.File;
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

public class LoginDataManage {
	static String id;
	static String pw;
	static List<String> code;
	static Map<String, Object> user;
	static List<Map<String, String>> adminData;		//관리자 정보는 id와 pw란 key값만 가지고 있으므로 이에 대한 대응값은 모두 String
													//따라서 모든 adminData를 배열안에 map형식으로 저장. 이때 Map의 key와 value의 형식을 <>안에 적어줌.
	static List<Map<String, Object>> userData;		//사용자 정보는 code값을 추가로 가지고 있고 이는 String배열이므로, value의 값이 동일하지 않음. 따라서 Object로 선언
	static LoginInfo logininfo;
	static Gson gson = new Gson();
	static String pathLogin = "."+File.separator+"resource"+File.separator+"login.json";
	
	public static void getJson()
	{																	//Json파일 받아오는 함수
		JsonParser parser = new JsonParser();
		try {
			Reader reader = new FileReader(pathLogin);	//resource에 login.json파일을 받아옴.
			logininfo = gson.fromJson(reader, LoginInfo.class);			//인자 "LoginInfo.class"  --> 읽어온 json파일을 LoginInfo라는 클래스형식으로 표현하겠다.
			adminData = logininfo.getAdmin();							// logininfo의 함수들을 통해 객체를 구별. 
			userData = logininfo.getUser();								
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static boolean is_Admin(String id)
	{																	//회원가입시 중복된 id존재여부 확인할때(관리자)
		getJson();
		for(int i=0; i<adminData.size(); i++)
		{
			if(((String)adminData.get(i).get("id")).equals(id))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean is_Admin(String id, String pw)
	{																	//로그인시 올바른 id와 비밀번호 존재하는지 확인(관리자)
		getJson();
		for(int i=0; i<adminData.size(); i++)
		{
			if(((String)adminData.get(i).get("id")).equals(id)&&((String)adminData.get(i).get("pw")).equals(pw))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean is_User(String id)
	{																	//회원가입시 중복된 id존재여부 확인할때(고객)
		getJson();
		for(int i=0; i<userData.size(); i++)
		{
			if(((String)userData.get(i).get("id")).equals(id))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean is_User(String id,String pw)
	{																	//로그인시 올바른 id와 비밀번호 존재하는지 확인(고객)
		getJson();
		for(int i=0; i<userData.size(); i++)
		{
			if(((String)userData.get(i).get("id")).equals(id)&&((String)userData.get(i).get("pw")).equals(pw))
			{
				return true;
			}
		}
		return false;
	}
	
	
	public static void set_Admin(String ins_id, String ins_pw)			//관리자 등록
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
			if(adminData.get(i).get("id").equals(id))				//아이디가 동일할 경우 재등록(수정할때 사용)
				continue;
			new_admin.add(adminData.get(i));
		}
		new_admin.add(admin);
		logininfo.setAdmin(new_admin);
		try {
			FileWriter fw = new FileWriter(pathLogin);
			gson.toJson(logininfo, fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public static void set_User(String ins_id, String ins_pw, List<String> ins_code)	//사용자 등록
	{
		getJson();
		id = ins_id;
		pw = ins_pw;
		
		if(ins_code==null)
			code = new ArrayList<String>();
		else
			code = ins_code;
		
		Map<String, Object> user = new LinkedHashMap<>();
		user.put("id", id);
		user.put("pw", pw);
		user.put("code", code);

		List<Map<String, Object>> new_user = new ArrayList<>();
		for(int i=0; i<userData.size(); i++)
		{
			if(userData.get(i).get("id").equals(id))						//아이디가 동일할 경우 재등록(수정할때 사용)
				continue;
			new_user.add(userData.get(i));
		}
		new_user.add(user);
		logininfo.setUser(new_user);
		try {
			FileWriter fw = new FileWriter(pathLogin);
			gson.toJson(logininfo, fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	public static List<String> getCode(String id)					// 해당하는 id에 대한 code값 가져오는 함수
	{																// 만약 알맞은 id없으면 null 반환, 알맞은 id 있어도 code가 없을시 빈배열 반환
		getJson();													
		List<String> code_check= new ArrayList<String>();
		for(int i=0; i<userData.size(); i++)
		{
			if(((String)userData.get(i).get("id")).equals(id))
			{
				code_check = (List<String>)userData.get(i).get("code");
				return code_check;
			}
		}
		return null;
		
	}
	
	
	public static void addCode(String ins_id, String ins_pw, String add_code)	//만약 id나 비밀번호가 틀려서 addCode가 정상실행하지 않는다면 오류 문구만 출력. 
	{																			//새로운 영화를 예매했을때, id와 pw와 예매한 영화의 코드를 같이 넣어주세요.
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
	
	public static void removeCode(String ins_id, String ins_pw, String rm_code)	// 예매 취소한 영화의 code 삭제  
	{																			
		getJson();
		if (is_User(ins_id, ins_pw))
			{
				code = getCode(ins_id);
				code.remove(code.indexOf(rm_code));
				set_User(ins_id, ins_pw, code);	
				return;
			}
		else
			System.out.println("해당하는 id가 없거나, 비밀번호가 올바르지 않습니다.");
		return;
	}
}
