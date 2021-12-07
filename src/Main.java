import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class Main {
	static UserInfo userinfo;
	public static void main(String args[]) {
		System.setProperty("file.encoding", "UTF-8");
		try {
			Field charset = Charset.class.getDeclaredField("defaultCharset");
			charset.setAccessible(true);
			charset.set(null, null);
		} catch (Exception e) {
		}
    
		while(true)
		{
			userinfo = InitialPage.initialPage();
			if(userinfo.getIsAdmin())
			{
				ManagerMainPage.managerMainPage(userinfo);
			}
			else
			{
				CustomerMainPage customerMainPage = new CustomerMainPage(userinfo);
				customerMainPage.menu();
			}
		}
	}
}
