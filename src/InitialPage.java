import java.util.Scanner;

public class InitialPage {
   static Scanner scan = new Scanner(System.in);
   public static void initialPage()
   {
         while(true)
         {
         System.out.println("===============");
         System.out.println("1. 회원가입");
         System.out.println("2. 로그인");
         System.out.print(">>> ");
         
         String menu = scan.next();
         switch(menu) {
            case "1":
               if(SignupPage.signupPage())
               {
                  LoginPage.loginPage();
               }
               continue;
            case "2":
               LoginPage.loginPage();
            }
               
         }
   }
}