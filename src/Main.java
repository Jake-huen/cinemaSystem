
public class Main {
	static UserInfo userinfo;
	public static void main(String args[]) {
		userinfo = InitialPage.initialPage();
		if(userinfo.getIsAdmin())
		{
			ManagerMainPage.managerMainPage();
		}
		else
		{
			CustomerMainPage customerMainPage = new CustomerMainPage(userinfo);
			customerMainPage.menu();
		}
		
	}
}
