
public class Main {
	static UserInfo userinfo;
	public static void main(String args[]) {
		userinfo = InitialPage.initialPage();
		if(LoginDataManage.is_Admin(userinfo.getId()))
		{
			ManagerMainPage.managerMainPage();
		}
		else if(LoginDataManage.is_User(userinfo.getId()))
		{
			CustomerMainPage customerMainPage = new CustomerMainPage(userinfo);
			customerMainPage.menu();
		}
		
	}
}
