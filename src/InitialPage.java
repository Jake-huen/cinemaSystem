import java.util.Scanner;

public class InitialPage {
   static Scanner scan = new Scanner(System.in);
   static UserInfo info;
   public static UserInfo initialPage()
   {
	     String[] menuname = {"","회원가입", "로그인"};
         while(true)
         {
         System.out.println("===============");
         System.out.println("1. 회원가입");
         System.out.println("2. 로그인");
         System.out.print(">>> ");
         
         int menu = InputRule.MenuRule(menuname);
         switch(menu) {
            case 1:
               if(SignupPage.signupPage())
               {
                  info = LoginPage.loginPage();
                  if(info!=null)
                  {
                	  return info;
                  }
               }
               else
            	   continue;
            case 2:
               info = LoginPage.loginPage();
               if(info!=null)
               {
            	   return info;
               }
            default:
            	System.out.println("올바르지 않은 입력입니다."); 
         	}
         }
   }
}