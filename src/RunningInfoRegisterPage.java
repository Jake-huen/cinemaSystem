import java.util.ArrayList;
import java.util.Scanner;

public class RunningInfoRegisterPage {//8.2.3 상영정보등록페이지

	static Scanner scan = new Scanner(System.in);

	public static void runningInfoRegisterPage() {
		while(true) {
			String[] theaters=TheaterDataManage.getTheater();
			String[] theatersName=TheaterDataManage.getTheaterName();
			String[] theatermenu=new String[theatersName.length+1];
			theatermenu[0]="뒤로가기";
			for(int i=1;i<theaters.length+1;i++) {
				theatermenu[i]=theatersName[i-1];
			}
			System.out.println("======상영관 목록======");
			System.out.println("0. 뒤로가기");
			for(int i=0;i<theaters.length;i++)
				System.out.println((i+1)+". "+theaters[i]);
			while(true) {
				System.out.println("===================");
				System.out.print("영화를 등록할 상영관을 선택해주세요>>>");
				int menuNum=InputRule.MenuRule(theatermenu);
				if(menuNum==0) {
					return;
				}
				else if(menuNum==-1){
					System.out.println("올바르지 않은 입력입니다.");
				}
				else {
					while(true) {
						System.out.print("등록할 날짜를 입력해주세요>>>");
						String date=InputRule.DateRule();
						if(date==null) { 
							System.out.println("올바르지 않은 입력입니다.");
						}
						else {
							runningInfoDetailPage(menuNum-1,date);
							return;
						}
					}
				}
			}
		}
	}
	public static void runningInfoDetailPage(int theaterIndex,String inputdate) { //8.2.3.1 상영관
		ArrayList<String[]> ri=RunningInfoManage.readDateRi(inputdate,theaterIndex);
		// String[] temp2 = null;
		String tt = TheaterDataManage.readIndexTheaterName(theaterIndex);
		tt = tt.substring(1,tt.length()-1);
		System.out.println(inputdate.substring(0, 4)+"년"+inputdate.substring(4, 6)+"월"
				+inputdate.substring(6, 8)+"일,"+
				tt+" 상영정보");
		// 상영정보 출력
		
		System.out.println("==========================");
		String[] seTime=new String[ri.size()*2];
		for(int i=0;i<ri.size();i++) {
			String startTime="",endTime="";
			startTime=ri.get(i)[0].substring(0,2)+":"+ri.get(i)[0].substring(2,4);
			int h=Integer.parseInt(ri.get(i)[0].substring(0,2));
			int m=Integer.parseInt(ri.get(i)[0].substring(2,4));
			int runtime=Integer.parseInt(MovieInfoDataManage.getmovieRuntime(ri.get(i)[1]));
			h+=runtime/60;
			m+=runtime%60;
			if(m>=60) {
				h++;
				m%=60;
			}
			if(h>=24) {
				h%=24;
			}
			endTime=h+":"+m;
			if(h<10) {
				endTime="0"+h+":"+m;
			}
			else if(m<10) {
				endTime=h+":"+"0"+m;
			}
			else if(h<10&&m<10)
				endTime="0"+h+":"+"0"+m;
			
			System.out.println(startTime+"~"+endTime+" "+ri.get(i)[1]);
			
			seTime[i*2]=startTime;
			seTime[i*2+1]=endTime;
		}
		//등록된 영화 출력
		String[] title=MovieInfoDataManage.getTitle(); //영화제목 받아오기
		String[] runtime=MovieInfoDataManage.getRuntime(); //영화시간 받아오기
		String[] movieInfo =new String[title.length+1];
		String[] moviemenu=new String[title.length+1];
		movieInfo[0]="뒤로가기"; moviemenu[0]="뒤로가기";
		for(int i=1;i<title.length+1;i++) {
			// movieInfo[i]=movieInfo[i].substring(1,movieInfo[i].length()-1);
			movieInfo[i]=title[i-1].replaceAll("\"", "")+" / "+runtime[i-1].replaceAll("\"", "");
			moviemenu[i]=title[i-1];
		}
		System.out.println("======영화목록======");
		Print.menu(movieInfo, true);
		System.out.println("=================");
		while(true) {
			System.out.print("영화를 선택해주세요>>>");
			int menuNum=InputRule.MenuRule(moviemenu);
			if(menuNum==0) return;
			else if(menuNum==-1) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else {
				while(true) {
					System.out.print("상영시작시간을 설정하세요>>>");//상영시간 중복시 오류처리 해야됨
					String startTime=InputRule.TimeRule();
					String rt = MovieInfoDataManage.getmovieRuntime(menuNum-1);
					// System.out.println(rt);
					// String[] runtime2=MovieInfoDataManage.getRuntime();
					if(startTime==null) {
						System.out.println("올바르지 않은 입력입니다.");
					}
					else if(!Check(seTime,startTime,rt)) {
						System.out.println("상영시간이 중복됩니다.");	
					}
					else {
						// startTime
						ArrayList<ReserveInfo> reserve = new ArrayList<ReserveInfo>();
						RunningInfoManage.setJson(inputdate, startTime.replaceAll(":", ""),title[menuNum-1].replaceAll("\"", "") , 
								TheaterDataManage.readIndexTheaterName(theaterIndex), reserve);
						System.out.println("정상추가 되었습니다");
						return;
					}
				}
			}
		}
	}
	public static boolean Check(String[] temp, String userStartTime, String runtime) {
		if(temp.length==0) {
			return true;
		}
		int runtimeInt = Integer.parseInt(runtime);
		double runtimeDouble = runtimeInt/60.0;//120->2.0
		double[] checktime = new double[temp.length];
		for(int i = 0; i<checktime.length; i++) {
			checktime[i] = translate(temp[i]);
		}
		double userStartTimeDouble = translate(userStartTime);
		double endTime = userStartTimeDouble + runtimeDouble;
		
		//checktime과 userStartTimeDouble~endTime중복비교
		for(int i =0; i<temp.length-1; i+=2) {//i는 0,2,4,6,...
			if(checktime[i]<userStartTimeDouble && checktime[i+1]>userStartTimeDouble) {
				return false; //이경우는 상영시작시간 자체가 다른영화상영중 시간으로 입력됨
			}
		}
		for(int j = 1; j<temp.length-2;j+=2) {
			if(checktime[j]<endTime && checktime[j+1]>endTime) {
				return true;
			}
		}
		if(checktime[0]>userStartTimeDouble&&checktime[0]>endTime)
			return true;
		if(checktime[temp.length-1]<userStartTimeDouble&&checktime[temp.length-1]<endTime)
			return true;
		
		return false;
	}
	public static double translate(String string) { // 시간 -> 소수형태로
		//07:40 -> 7.666
		string = InputRule.TimeRule(string);
		String time[]  = null;
		time= string.split(":");
		if(time.length!=2) {
			System.out.println("올바른입력이 아닙니다.");
			return 0.0;
		}
				
		double hour = 0.0;
		double resultTime, min = 0.0;
		hour = Double.parseDouble(time[0]);
		min = Double.parseDouble(time[1]);
		if(time[1].equals("00")) {}
			//아무것도 안해도 됨
		else
			min = min/60.0;
		resultTime = hour+min;
		return resultTime;
	}
//	public static void main(String args[]) {
//		System.out.println(translate("0940"));
//	}
}
