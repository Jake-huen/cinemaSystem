import java.util.Scanner;

public class MovieManagePage {//8.2.1영화관리페이지

	static Scanner scan = new Scanner(System.in);

	public static void movieManagePage() {
		while(true) {
			String[] title=MovieInfoDataManage.getTitle(); //영화제목 받아오기
			String[] runtime=MovieInfoDataManage.getRuntime(); //영화시간 받아오기
			System.out.println("======영화목록======");
			//등록된 영화 읽어오기 (DataManage클래스 내 함수 호출)
			for(int i=0;i<title.length;i++) {
				title[i]=title[i].substring(1,title[i].length()-1);
				runtime[i]=runtime[i].substring(1,runtime[i].length()-1);
				System.out.println(title[i]+" / "+runtime[i]);
			}
			System.out.println("=================");
			System.out.println("0. 뒤로가기");
			System.out.println("1. 영화 정보 등록");
			System.out.println("2. 영화 정보 수정 및 삭제");
			System.out.print(">>>");

			String[] tmp={"뒤로가기","영화정보등록","영화정보수정및삭제"};
			int menuNum=InputRule.MenuRule(tmp);
			switch(menuNum) {
			case 0:
				return;
			case 1:
				movieRegisterPage();
				break;
			case 2:
				movieCheckPage();
				break;
			default:
				System.out.println("올바르지 않은 입력입니다.");
			}
		}
	}
	public static void movieRegisterPage() {//8.2.1.1 영화정보등록
		String title="",runtime="";

		System.out.print("영화제목>>");
		title=scan.nextLine();
		String check_title = InputRule.MTRule(title);
		if(check_title==null) { //중복 입력인 경우 오류처리 필요
			System.out.println("올바르지 않은 입력입니다.");
			return;
		}
		String[] current_titles = MovieInfoDataManage.getTitle();
		for(int i=0;i<current_titles.length;i++) {
			current_titles[i]=current_titles[i].substring(1,current_titles[i].length()-1);
			// System.out.println(current_titles[i]);
			// System.out.println(check_title);
			if(current_titles[i].equals(check_title)) {
				System.out.println("동일한 영화가 존재합니다.");
				return;
			}
		}

		System.out.print("상영시간>>");
		if((runtime=InputRule.RunTimeRule())==null) {
			System.out.println("올바르지 않은 입력입니다.");
			return;
		}
		runtime = runtime+"분";
		MovieInfoDataManage.setJsonMovie(title,runtime); //데이터베이스에 등록

		System.out.println("=====등록완료=====");
	}
	public static void movieCheckPage() {//8.2.1.2 영화정보확인
		String[] title=MovieInfoDataManage.getTitle(); //영화제목 받아오기
		String[] runtime=MovieInfoDataManage.getRuntime(); //영화시간 받아오기
		String[] movieInfo =new String[title.length+1],moviemenu=new String[title.length+1];
		movieInfo[0]="뒤로가기"; moviemenu[0]="뒤로가기";
		for(int i=1;i<title.length+1;i++) {
			title[i-1]=title[i-1].substring(1,title[i-1].length()-1);
			runtime[i-1]=runtime[i-1].substring(1,runtime[i-1].length()-1);
			movieInfo[i]=title[i-1]+" / "+runtime[i-1];
			moviemenu[i]=title[i-1];
		}
		System.out.println("======영화목록======");
		Print.menu(movieInfo, true);
		System.out.println("=================");
		System.out.print("수정 및 삭제할 영화를 선택하세요>>>");
		int menuNum=InputRule.MenuRule(moviemenu);
		if(menuNum==0) return;//8.2.1로

		//정상입력시
		movieFixPage(menuNum-1);
	}
	public static void movieFixPage(int num) {//8.2.1.2.1 영화정보수정및삭제
		System.out.println("======영화 수정 및 삭제======");
		System.out.println(MovieInfoDataManage.readIndexMovie(num));
		System.out.println("0. 뒤로가기");
		System.out.println("1. 수정");
		System.out.println("2. 삭제");
		System.out.print(">>>");
		String[] tmp2={"뒤로가기","수정","삭제"};
		int menuNum=InputRule.MenuRule(tmp2);
		if(menuNum==0) return;
		else if(menuNum==1) {//영화 수정
			System.out.println("======영화수정======"); //or 영화삭제

			String title="",runtime="";
			System.out.print("영화제목>>");
			title=scan.nextLine();
			if(InputRule.MTRule(title)==null) {
				System.out.println("올바르지 않은 입력입니다.");
				return;
			}

			System.out.print("상영시간>>");
			if((runtime=InputRule.RunTimeRule())==null) {
				return;
			}
			runtime = runtime +"분";
			MovieInfoDataManage.fixMovie(num, title, runtime);
		}else if(menuNum==2) {//영화 삭제
			MovieInfoDataManage.deleteMovie(num);
		}


	}
}
