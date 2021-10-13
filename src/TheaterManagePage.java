import java.util.Scanner;

public class TheaterManagePage {//8.2.2상영관관리페이지

	static Scanner scan = new Scanner(System.in);

	public static void theaterManagePage() {
		while(true) {
			String[] theaters=TheaterDataManage.getTheater();
			System.out.println("======상영관 목록======");
			//등록된 상영관 읽어오기 (DataManage클래스 내 함수 호출)
			for(int i=0;i<theaters.length;i++) {
				System.out.println(theaters[i]);
			}
			System.out.println("===================");
			String[] tmp={"메인페이지로가기","상영관등록","상영관수정및삭제"};
			Print.menu(tmp,true);
			System.out.print(">>>");
			int menuNum=InputRule.MenuRule(tmp);
			switch(menuNum) {
			case 0:
				return;
			case 1:
				theaterRegisterPage();
				break;
			case 2:
				theaterCheckPage();
				break;
			}
		}
	}
	public static void theaterRegisterPage() {//8.2.2.1 상영관정보등록
		String theater="",rc="";
		System.out.print("상영관이름>>");
		theater=InputRule.ScreenRule();
		if(theater==null) {
			System.out.println("올바르지 않은 입력입니다.");
			return;
		}
		
		System.out.print("좌석의 행과 열 수>>");//입력규칙정의필요
		rc=scan.nextLine();
		String[] tmp=rc.split(" ");
		int row=Integer.parseInt(tmp[0]);
		int col=Integer.parseInt(tmp[1]);
		TheaterDataManage.setJsonTheater(theater,row,col);
		
		System.out.println("=====등록완료=====");
	}
	public static void theaterCheckPage() {//8.2.2.2 상영관정보확인
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
		System.out.print("수정 및 삭제할 상영관을 선택하세요>>>");
		int menuNum=InputRule.MenuRule(theatermenu);

		theaterFixPage(menuNum-1);//정상입력시
	}
	public static void theaterFixPage(int index) {//8.2.2.2.1 상영관정보수정및삭제
		String theater="",rc="";
		System.out.println("======영화 수정 및 삭제======");
		System.out.println(TheaterDataManage.readIndexTheater(index));
		System.out.println("0. 뒤로가기");
		System.out.println("1. 수정");
		System.out.println("2. 삭제");
		System.out.print(">>>");

		String[] tmp2={"뒤로가기","수정","삭제"};
		int menuNum=InputRule.MenuRule(tmp2);
		if(menuNum==0) return;
		else if(menuNum==1) {
			System.out.println("======상영관 수정======"); 
			System.out.print("상영관 이름>>>");
			theater=InputRule.ScreenRule();
			while(theater==null) {
				System.out.println("올바르지 않은 입력입니다.");
				System.out.print("상영관 이름>>>");
				theater=InputRule.ScreenRule();
			}
			System.out.print("좌석의 행과 열 수>>");//입력규칙정의필요
			rc=scan.nextLine();
			int row=Integer.parseInt(rc.split("/")[0]);
			int col=Integer.parseInt(rc.split("/")[1]);
			
			TheaterDataManage.fixTheater(index, theater, row, col);
		}else if(menuNum==2) {
			TheaterDataManage.deleteTheater(index);
		}
	}
}
