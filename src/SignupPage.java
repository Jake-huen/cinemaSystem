import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupPage {
   static Scanner scan = new Scanner(System.in);
   static String[] idpw = new String[1];                //임시저장용
   public static boolean signupPage()
   {
	  String[] menuname = {"뒤로가기", "관리자용회원가입", "고객용회원가입"};
      while(true) {
         System.out.println("======회원가입======");
         System.out.println("0. 뒤로가기");
         System.out.println("1. 관리자용 회원가입");
         System.out.println("2. 고객용 회원가입");
         System.out.print(">>>");
         
         String menu = InputRule.MenuRule(menuname);
         
         if(menu==null)
         {
        	 System.out.println("올바르지 않은 입력입니다."); 
        	 continue;
         }
         switch(menu) {	        	
            case "0":
               return false;
            case "1":
               adminsignupPage();
               return true;
            case "2":
               clientsignupPage();
               return true;
            default:
            	System.out.println("올바르지 않은 입력입니다.");
            	continue;
         }   
      }
      
      
   }
   public static void adminsignupPage()
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
         
         if(id.equals("겹침"))            //중복된 아이디 존재
         {
            System.out.println("중복된 아이디가 존재합니다.");
            continue;
         }
         
         
         System.out.print("비밀번호 >>");
         String pw = InputRule.PWRule();
         if(pw == null)            //입력규칙과 맞지 않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         System.out.print("비밀번호 확인 >>");
         if(pw.equals(scan.next()))
         {
            System.out.println("====회원등록 완료====");
            idpw[0] = id+pw;
         }
         else {
            System.out.println("비밀번호가 일치하지 않습니다.");
            continue;
         }
         return;
      }
   }
   
   public static void clientsignupPage()
   {
      while(true)
      {
         System.out.println("=====고객용=====");
         System.out.print("아이디 >>");
         String id = InputRule.IDRule();
         if(id == null)            //입력규칙과 맞지않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         
         if(id.equals("겹침"))            //중복된 아이디 존재
         {
            System.out.println("중복된 아이디가 존재합니다.");
            continue;
         }
         
         
         System.out.print("비밀번호 >>");
         String pw = InputRule.PWRule();
         if(pw== null)            //입력규칙과 맞지 않을때
         {
            System.out.println("올바르지 않은 입력입니다.");
            continue;
         }
         System.out.print("비밀번호 확인 >>");
         if(pw.equals(scan.next()))
         {
            System.out.println("====회원등록 완료====");
            idpw[0] = id+pw;
         }
         else {
            System.out.println("비밀번호가 일치하지 않습니다.");
            continue;
         }
         return;
      }
   }
}