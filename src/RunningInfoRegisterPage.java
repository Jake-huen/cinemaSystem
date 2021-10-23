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
			System.out.println("===================");
			System.out.print("영화를 등록할 상영관을 선택해주세요>>>");
			while(true) {
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
							// System.out.println(menuNum);
							// System.out.println(date);
							runningInfoDetailPage(menuNum-1,date);
							break;
						}
					}
					break;
				}
			}
		}
	}
	public static void runningInfoDetailPage(int theaterIndex,String inputdate) { //8.2.3.1 상영관
		ArrayList<String[]> ri=RunningInfoManage.readDateRi(inputdate,theaterIndex);
		System.out.println(inputdate.substring(0, 4)+"년"+inputdate.substring(4, 6)+"월"
				+inputdate.substring(6, 8)+"일,"+
				TheaterDataManage.readIndexTheaterName(theaterIndex)+" 상영정보");
		System.out.println("==========================");
		System.out.println("여기까지1");
		for(int i=0;i<ri.size();i++) {
			System.out.println(ri.get(i)[0].substring(0,2)+":"+
					ri.get(i)[0].substring(2,4)+"~"+"끝시간"+" "+
					ri.get(i)[1]);
		}
		System.out.println("여기까지2");
		//등록된 영화 출력
		String[] title=MovieInfoDataManage.getTitle(); //영화제목 받아오기
		String[] runtime=MovieInfoDataManage.getRuntime(); //영화시간 받아오기
		String[] movieInfo =new String[title.length+1],moviemenu=new String[title.length+1];
		movieInfo[0]="뒤로가기"; moviemenu[0]="뒤로가기";
		for(int i=1;i<title.length+1;i++) {
			movieInfo[i]=movieInfo[i].substring(1,movieInfo[i].length()-1);
			movieInfo[i]=title[i-1]+" / "+runtime[i-1];
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
					// MovieInfoDataManage.getmovieRuntime(menuNum-1);
					if(startTime==null) {
						System.out.println("올바르지 않은 입력입니다.");
					}
					else if(Check(runtime,startTime,"11")) {
						System.out.println("상영시간이 중복됩니다.");	
					}
					else {
						// startTime
						RunningInfoManage.setJson2(inputdate, startTime,title[menuNum-1] , 
								TheaterDataManage.readIndexTheaterName(theaterIndex), null);
						System.out.println("정상추가 되었습니다");
						return;
					}
				}
			}
		}
	}
	public static boolean Check(String[] temp, String userStartTime, String runtime) {
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
		String time[] = string.split(":");
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
//	public static void main(String[] args) {
//		int theaterIndex=1;
//		String inputdate=""
//		ArrayList<String[]> ri=RunningInfoManage.readDateRi(inputdate,theaterIndex);
//		System.out.println(inputdate.substring(0, 4)+"년"+inputdate.substring(4, 6)+"월"
//				+inputdate.substring(6, 8)+"일,"+
//				TheaterDataManage.readIndexTheaterName(theaterIndex)+" 상영정보");
//		System.out.println("==========================");
//		for(int i=0;i<ri.size();i++) {
//			System.out.println(ri.get(i)[0].substring(0,2)+":"+
//					ri.get(i)[0].substring(2,4)+"~"+"끝시간"+" "+
//					ri.get(i)[1]);
//		}
//	}
}
