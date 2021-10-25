import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputRule {//입력규칙 정의 (static으로)
	static Scanner sc = new Scanner(System.in);

	public static int MenuRule(String[] menu_name)		//7.1 메뉴입력규칙
	{									
		String menu = sc.nextLine();
		if(menu.equals(" "))
		{
			return -1;
		}
		menu = menu.trim();
		for(int i=0; i<menu_name.length; i++)
		{									
			if(menu_name[i] == "" && String.valueOf(i).equals(menu))		//메뉴이름을 입력받으면 안될때, 메뉴이름을 String배열에 ""로 저장
			{
				return i;
			}

			if(menu_name[i].equals(menu) || String.valueOf(i).equals(menu))
			{
				return i;
			}
		}											//메뉴이름을 String 배열에 index에 맞춰 넣어주세요
		return -1;
	}

	public static String IDRule()		//7.2로그인 입력규칙 - ID
	{
		Pattern pattern = Pattern.compile("^[A-Za-z[0-9]]{2,10}$");
		String id;
		id = sc.nextLine();
		Matcher matcher = pattern.matcher(id);
		if(!matcher.find())
		{
			return null;
		}
		return id;
	}

	public static String PWRule()		//7.2로그인 입력규칙 - PW
	{   
		Pattern pattern1 = Pattern.compile("^[A-Za-z[0-9]]{8,}$");
		Pattern pattern2 = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9]).{8,}$");
		String pw;
		pw = sc.nextLine();
		Matcher matcher1 = pattern1.matcher(pw);
 		Matcher matcher2 = pattern2.matcher(pw);
		if(matcher1.find() && matcher2.find())
		{
			return pw;
		}
		else 
			return null;
	}		

	public static String MTRule(String movie) { //7.3영화제목입력규칙 parameter
		movie=movie.trim();
		if(movie.equals("0")) {
			return null;
		}
		else if(movie.length()<1 || movie.length()>30) {
			return null;
		}
		return movie;
	}

	public static String RunTimeRule(){ //7.4시간입력규칙
		char tmp;
		int r_answer;
		Boolean a_check=true;
		String answer="";
		String time=sc.nextLine();
		int q_count=0;
		r_answer=0;
		String check_time = time.replaceAll(" ","");
		//#있는지 체크 필요
		if(time.contains("#")) {
			return null;
		}
		else {
			if(!time.equals(check_time)) { //공백이 있는 경우
				return null;
			}
			else {
				Boolean check=true;
				for(int i=0;i<time.length();i++) {
					tmp=time.charAt(i);
					if('0'<=tmp&&tmp<='9') {
						if(check)
							answer+=tmp;
						else {
							answer+='#';
							q_count+=1;
							answer+=tmp;
							check=true;
						}
					}
					else {
						if(check) {
							answer+='#';
							q_count+=1;
							answer+=tmp;
							check=false;
						}
						else {
							answer+=tmp;
						}
					}
				}
				// System.out.println(answer);
				if(q_count==0) { //숫자만 있는 경우
					r_answer=Integer.parseInt(answer);
				}
				else if(q_count==1) { //숫자+문자
					String[] splited=answer.split("#");
					if(splited[1].equals("시간")||splited[1].equals("h")) {
						r_answer=Integer.parseInt(splited[0])*60;
					}
					else if(splited[1].equals("분")||splited[1].equals("m")){
						r_answer = Integer.parseInt(splited[0]);
					}
					else {
						return null;
					}
				}
				else if(q_count==3) { //숫자+문자+숫자+문자
					String[] splited=answer.split("#");
					if(splited[1].equals("시간")||splited[1].equals("h") || splited[1].equals(":") || splited[1].equals("-") || splited[1].equals("/")) {
						if(splited[3].equals("분") || splited[3].equals("m")) {
							r_answer=Integer.parseInt(splited[0])*60+Integer.parseInt(splited[2]);
						}
						else {
							return null;
						}
					}
					else {
						return null;
					}
				}
				else {
					return null;
				}
			}
			if(r_answer>=10&&r_answer<=720&&a_check) {
				return Integer.toString(r_answer);
			}
			else if(a_check) {
				return null;
			}
		}
		return Integer.toString(r_answer);
	}
	public static String ScreenRule() {//7.5 상영관입력규칙
		String screen;
		screen=sc.nextLine();
		String check_screen = screen.replaceAll(" ","");
		if(!screen.equals(check_screen)) {
			return null;
		}
		else if(screen.length()<1 || screen.length()>100) {
			return null;
		}
		else if(screen.charAt(screen.length()-1)!='관'){
			return null;
		}
		else if(screen.equals('관')) {
			return null;
		}
		// 입력으로 '관' 만 입력하는 경우 --->(기획서 반영?)
		else {
			return screen;
		}
	}


	public static String ScreenRule(String screen) {//7.5 상영관입력규칙
		String check_screen = screen.replaceAll(" ","");
		if(!screen.equals(check_screen)) {
			return null;
		}
		else if(screen.length()<1 || screen.length()>100) {
			return null;
		}
		else if(screen.charAt(screen.length()-1)!='관'){
			return null;
		}
		else if(screen.equals('관')) {
			return null;
		}
		// 입력으로 '관' 만 입력하는 경우 --->(기획서 반영?)
		else {
			return screen;
		}
	}


	public static String ScreenRule2(String screen) {//7.5 상영관입력규칙
		String check_screen = screen.replaceAll(" ","");
		if(!screen.equals(check_screen)) {
			return null;
		}
		else if(screen.length()<1 || screen.length()>100) {
			return null;
		}
		else if(screen.charAt(screen.length()-1)!='관'){
			return null;
		}
		else if(screen.equals('관')) {
			return null;
		}
		// 입력으로 '관' 만 입력하는 경우 —>(기획서 반영?)
		else {
			return screen;
		}
	}

	public static String DateRule() {// 7.6 날짜입력규칙 _ return 값은 YYYYMMDD형식
		String checkdate = sc.nextLine();
		String blankcheck = checkdate.replaceAll(" ","");
		if(!checkdate.equals(blankcheck)) {
			return null;
		}
		boolean isNumeric = true;
		for(int i =0; i<checkdate.length(); i++) {
			if(checkdate.charAt(i)>='0'&&checkdate.charAt(i)<='9') {}
			else {
				isNumeric = false;
			}
		}
		if (isNumeric) {// 문법규칙(1)의 경우
			if (checkdate.length() == 8) {
				//매우 올바른형식임_아래 의미규칙만 확인
			} else if (checkdate.length() == 6) {
				String s_year = checkdate.substring(0, 2);
				if(s_year.startsWith("0"))
					s_year =  s_year.substring(1,2);
				int year = Integer.parseInt(s_year);
				if (year >= 50 && year <= 99) {
					checkdate = "19" + checkdate;
				}
				else {
					checkdate = "20" + checkdate;
				}
			} else {
				return null; // 문법형식에 맞지 않음
			}
		} // 문법규칙(2)의 경우
		else if (checkdate.contains("-") || checkdate.contains("/")) {
			checkdate = checkdate.replace("/", "-"); // 구분기호 "-로 통일
			int count = 0;
			for(int i =0; i<checkdate.length(); i++)
				if(checkdate.charAt(i)=='-')
					count++;
			if(count!=2)	return null;
			String date[] = checkdate.split("-");
			if (date[0].length() == 2) {
				if(date[0].startsWith("0"))
					date[0] =  date[0].substring(1,2);
				int year = Integer.parseInt(date[0]);
				if (year >= 50 && year <= 99)
					year = 1900 + year;
				else
					year = 2000 + year;
				date[0] = Integer.toString(year);
			}
			if (date[1].length() == 1)
				date[1] = "0" + date[1];
			if (date[2].length() == 1)
				date[2] = "0" + date[2];
			checkdate = "";
			for (int i = 0; i < date.length; i++)
				checkdate += date[i];
		} else {
			return null; // 위의 조건에 만족하지 않는 경우 없음
		}
		//return checkdate; // YYYYMMDD형식

		//의미규칙 확인 유효한 날짜인지
		String s_year = checkdate.substring(0, 4);
		String s_month = checkdate.substring(4,6);
		if(s_month.startsWith("0"))
			s_month = checkdate.substring(5,6);
		String s_day = checkdate.substring(6, 8);
		if(s_day.startsWith("0"))
			s_day = checkdate.substring(7,8);
		int year = Integer.parseInt(s_year);
		int month = Integer.parseInt(s_month);
		int day = Integer.parseInt(s_day);
		//		System.out.println(s_year);
		//		System.out.println(s_month);
		//		System.out.println(s_day);
		boolean isLeapYear = false;
		if((year / 4 == 0 && year / 100 != 0) || year / 400 == 0)
			isLeapYear = true;
		if(month>=1 && month <= 12) {
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if(day >= 1 && day <= 31)
					return checkdate;
				else
					return null;
			}else if(month == 4 || month == 6 || month == 9 || month == 11) {
				if(day >= 1 && day <= 30)
					return checkdate;
				else
					return null;
			}else if(month==2) {
				if(isLeapYear) {
					if(day >= 1 && day <= 29)
						return checkdate;
					else
						return null;
				}else {
					if(day >= 1 && day <= 28)
						return checkdate;
					else
						return null;
				}
			}else
				return null;
		}else
			return null;
	}
	public static String DateRule2(String checkdate) {// 7.6 날짜입력규칙 _ return 값은 YYYYMMDD형식
		boolean isNumeric = true;
		for(int i =0; i<checkdate.length(); i++) {
			if(checkdate.charAt(i)>='0'&&checkdate.charAt(i)<='9') {}
			else {
				isNumeric = false;
			}
		}
		if (isNumeric) {// 문법규칙(1)의 경우
			if (checkdate.length() == 8) {
				//매우 올바른형식임_아래 의미규칙만 확인
			} else if (checkdate.length() == 6) {
				String s_year = checkdate.substring(0, 2);
				if(s_year.startsWith("0"))
					s_year =  s_year.substring(1,2);
				int year = Integer.parseInt(s_year);
				if (year >= 50 && year <= 99) {
					checkdate = "19" + checkdate;
				}
				else {
					checkdate = "20" + checkdate;
				}
			} else {
				return null; // 문법형식에 맞지 않음
			}
		} // 문법규칙(2)의 경우
		else if (checkdate.contains("-") || checkdate.contains("/")) {
			checkdate = checkdate.replace("/", "-"); // 구분기호 "-로 통일
			int count = 0;
			for(int i =0; i<checkdate.length(); i++)
				if(checkdate.charAt(i)=='-')
					count++;
			if(count!=2)	return null;
			String date[] = checkdate.split("-");
			if (date[0].length() == 2) {
				if(date[0].startsWith("0"))
					date[0] =  date[0].substring(1,2);
				int year = Integer.parseInt(date[0]);
				if (year >= 50 && year <= 99)
					year = 1900 + year;
				else
					year = 2000 + year;
				date[0] = Integer.toString(year);
			}
			if (date[1].length() == 1)
				date[1] = "0" + date[1];
			if (date[2].length() == 1)
				date[2] = "0" + date[2];
			checkdate = "";
			for (int i = 0; i < date.length; i++)
				checkdate += date[i];
		} else {
			return null; // 위의 조건에 만족하지 않는 경우 없음
		}
		//return checkdate; // YYYYMMDD형식

		//의미규칙 확인 유효한 날짜인지
		String s_year = checkdate.substring(0, 4);
		String s_month = checkdate.substring(4,6);
		if(s_month.startsWith("0"))
			s_month = checkdate.substring(5,6);
		String s_day = checkdate.substring(6, 8);
		if(s_day.startsWith("0"))
			s_day = checkdate.substring(7,8);
		int year = Integer.parseInt(s_year);
		int month = Integer.parseInt(s_month);
		int day = Integer.parseInt(s_day);
		//		System.out.println(s_year);
		//		System.out.println(s_month);
		//		System.out.println(s_day);
		boolean isLeapYear = false;
		if((year / 4 == 0 && year / 100 != 0) || year / 400 == 0)
			isLeapYear = true;
		if(month>=1 && month <= 12) {
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if(day >= 1 && day <= 31)
					return checkdate;
				else
					return null;
			}else if(month == 4 || month == 6 || month == 9 || month == 11) {
				if(day >= 1 && day <= 30)
					return checkdate;
				else
					return null;
			}else if(month==2) {
				if(isLeapYear) {
					if(day >= 1 && day <= 29)
						return checkdate;
					else
						return null;
				}else {
					if(day >= 1 && day <= 28)
						return checkdate;
					else
						return null;
				}
			}else
				return null;
		}else
			return null;
	}
	public static String TimeRule() {// 7.7시각입력규칙 _ return 값은 HH:MM형식
		String checktime = sc.nextLine();
		return TimeRule(checktime);
	}

	public static String TimeRule(String checktime) {// 7.7시각입력규칙 parameter
		if(checktime.contains(" "))
			return null;
		int flag = 0;//숫자 : - 이세개만 허용
		char[] checktimeChar = checktime.toCharArray();
		for(int i = 0; i<checktimeChar.length; i++) {
			if(checktimeChar[i]>=48 && checktimeChar[i]<=58 || checktimeChar[i]==45) {}
			else
				flag = 1;
		}
		if(flag==1) {
			return null;
		}
		boolean isNumeric = true;
		for(int i =0; i<checktime.length(); i++) {
			if(checktime.charAt(i)>='0'&&checktime.charAt(i)<='9') {}
			else {
				isNumeric = false;
			}
		}
		if(isNumeric && checktime.length()==4) {
			//매우 올바른형식임_아래 의미규칙만 확인
		}else if(checktime.contains("-") || checktime.contains(":")) {
			checktime = checktime.replace("-", ":"); // 구분기호 ":"로 통일
			int count = 0;
			for(int i =0; i<checktime.length(); i++)
				if(checktime.charAt(i)==':')
					count++;
			if(count!=1)	return null;
			String time[] = checktime.split(":");
			if (time[0].length() == 1) {
				time[0] = "0"+time[0];
			}
			if (time[1].length() == 1) {
				time[1] = "0"+time[1];
			}
			checktime = "";
			for (int i = 0; i < time.length; i++)
				checktime += time[i];
		} else {
			return null;
		}
		checktime = checktime.substring(0, 2)+":"+checktime.substring(2, 4);
		//return null;
		//의미규칙확인
		String s_hour = checktime.substring(0, 2);
		String s_min = checktime.substring(3,5);
		if(s_hour.startsWith("0"))
			s_hour =  s_hour.substring(1,2);
		int hour = Integer.parseInt(s_hour);
		if(s_min.startsWith("0"))
			s_min =  s_min.substring(1,2);
		int min = Integer.parseInt(s_min);
		if(hour>=0 && hour<24 && min>=0 && min < 60) {
			return checktime;
		}else
			return null;
	}

	/* 정윤 - 인원수 정상 입력 : 인원수 반환 / 0 입력 : 0반환 / 잘못된 입력 -1 반환 */ 
	public static int rsrvPplInput() { // 7.8 예매인원 입력 규칙 
		String pplStr;
		int pplNum;

		pplStr = sc.nextLine();
		pplStr=pplStr.trim(); // 앞뒤 공백제거 

		// 공백 입력한 경우 
		if(pplStr.isEmpty())
			return -1;
		// 0 입력한 경우 
		if(pplStr.equals("0"))
			return 0;  

		// 0을 제외한 입력 
		int lastIdx = pplStr.length()-1;
		if(pplStr.charAt(lastIdx)=='명' || pplStr.charAt(lastIdx)=='인') {
			pplStr= pplStr.substring(0, lastIdx); // 명 or 인을 제외한 문자열로 재할당 
		}

		pplStr=pplStr.trim(); // 앞뒤 공백제거 

		try {
			pplNum = Integer.parseInt(pplStr);
		} catch (NumberFormatException e) {
			return -1;
		}
		if(pplNum <= 0)
			return -1;

		return pplNum;
	}


	public static String SeatRule() { //7.9 예매 좌석 입력 규칙
		String seat = sc.nextLine();
		if(seat.contains(" ")){
			String[] seatArr = seat.split(" ");
			if(seatArr[0].length() >= 2 || seatArr.length >= 3) return null;
		}
		seat = seat.replace(" ", "");
		seat = seat.trim();
		seat = seat.toLowerCase();
		int ascii = seat.charAt(0);
		if(ascii >= 97 && ascii <= 122) {
			if(seat.length() > 3 || seat.length() == 1) {
				return null;
			}
			else if(seat.length() == 3) {
				int a = seat.charAt(1) - '0';
				int b = seat.charAt(2) - '0';
				int num = a * 10 + b;
				if(num >= 1 && num <= 50) return seat;
				else return null;
			} else {
				int num = seat.charAt(1) - '0';
				if(num >= 1 && num <= 9) return seat;
				else return null;
			}
		} else {
			return null;
		}
	}
	public static int YesOrNo() { // 7.10 yes / no 입력규칙
		String yon = sc.nextLine();
		yon = yon.toLowerCase();
		yon = yon.trim();
		if(yon.equals("yes") || yon.equals("y")) return 1; //yes or y --> 1
		else if(yon.equals("no") || yon.equals("n")) return 0; //no or n --> 0
		else return -1; //others --> -1
	}
	//7.9 예매 좌석 입력 규칙 - 오버로딩 (상영관 행 열 반영)
	public static String SeatRule(String seat,int row, int col) { 
		seat = seat.replace(" ", "");
		seat = seat.trim();
		seat = seat.toUpperCase();
		
		if(seat == null || seat.length()<=1)
			return null;
		
		// 좌석의 행 
		int seatRow = seat.charAt(0);
		
		// 상영관 행 범위 벗어나는 경우 
		if(seatRow <'A' || seatRow>= row + 'A')
			return null;
		
		// 좌석의 열부분 
		int seatCol;
		String seatColStr = seat.substring(1);
		try {
			seatCol = Integer.parseInt(seatColStr);
		}catch(NumberFormatException e) {
			// 정수로 변환 안되면 null 반환 
			return null;
		}
		
		// 상영관 행 범위 벗어나는 경우 
		if(seatCol <1 ||seatCol>col)
			return null;
		
		return seat;
	}


}
