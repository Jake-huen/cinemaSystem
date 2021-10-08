import java.util.Scanner;

public class LoginPage {
   static Scanner scan = new Scanner(System.in);
   static String[][] idpw = {{"ims1", "check123123"}};                //임시저장용
   
   
   public static void loginPage()
   {
      while(true) {
         System.out.println("======로그인======");
         System.out.println("0. 뒤로가기");
         System.out.println("1. 관리자용 로그인");
         System.out.println("2. 고객용 로그인");
         System.out.print(">>>");
         
         String menu = scan.next();
         
         switch(menu) {
            case "0":
               return;
            case "1":
               adminloginPage();
               continue;
            case "2":
               clientloginPage();
               continue;
            }   
      }
      
      
   }
   public static void adminloginPage()
   {
      while(true)
      {
         System.out.println("=====관리자용=====");
         System.out.print("아이디 >>");
         String id = InputRule.IDRule();
         if(id== null)            //입력규칙과 맞지않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         
         System.out.print("비밀번호 >>");
         String pw = InputRule.PWRule();
         if(pw== null)            //입력규칙과 맞지 않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         
         for(int i=0; i<idpw.length; i++)
         {
            if(idpw[0][0].equals(id))
            {
               if(idpw[0][1].equals(pw))
               {
                  System.out.println("====로그인 완료====");
                  getDate();
                  System.exit(0);
               }
            }
         }
         System.out.println("아이디나 비밀번호를 다시 확인해주세요");
         continue;
      }
   }
   
   public static void clientloginPage()
   {
      while(true)
      {
         System.out.println("=====고객용=====");
         System.out.print("아이디 >>");
         String id = InputRule.IDRule();
         if(id== null)            //입력규칙과 맞지않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         
         System.out.print("비밀번호 >>");
         String pw = InputRule.PWRule();
         if(pw== null)            //입력규칙과 맞지 않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         
         for(int i=0; i<idpw.length; i++)
         {
            if(idpw[0][0].equals(id))
            {
               if(idpw[0][1].equals(pw))
               {
                  System.out.println("====로그인 완료====");
                  getDate();
                  System.exit(0);
               }
            }
         }
         System.out.println("아이디나 비밀번호를 다시 확인해주세요");
         continue;
      }
   }
   
   public static String[] getDate()
   {
      while(true)
      {
      System.out.println("날짜를 입력해 주세요");
      System.out.print(">>");
      String date = scan.next();         //날짜 입력규칙에 의해 처리
      if(date.equals("이상하다면"))
      {
         System.out.println("올바르지 않은 입력입니다.");
         continue;
      }
      System.out.println("시각을 입력해 주세요");
      System.out.print(">>");
      String time = scan.next();         //시각 입력규칙에 의해 처리
       
      if(time.equals("이상하다면"))
      {
         System.out.println("올바르지 않은 입력입니다.");
         continue;
      }
      
      String[] datetime = {date, time};
      System.out.println("====환영합니다====");
      return datetime;
      }
   }
}