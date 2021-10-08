import java.util.Scanner;

public class InitialPage {
   static Scanner scan = new Scanner(System.in);
   public static void initialPage()
   {
	     String[] menuname = {"","회원가입", "로그인"};
         while(true)
         {
         System.out.println("===============");
         System.out.println("1. 회원가입");
         System.out.println("2. 로그인");
         System.out.print(">>> ");
         
         String menu = InputRule.MenuRule(menuname);
         if(menu==null)
         {
        	 System.out.println("올바르지 않은 입력입니다."); 
        	 continue;
         }
         switch(menu) {	        	
            case "1":
               if(SignupPage.signupPage())
               {
                  LoginPage.loginPage();
               }
               continue;
               
            case "2":
               LoginPage.loginPage();
               
            default:
            	System.out.println("올바르지 않은 입력입니다."); 
         }
      }
   }
}