import java.util.Scanner;

public class TheaterManagePage {//8.2.2상영관관리페이지

	static Scanner scan = new Scanner(System.in);

	public static void theaterManagePage() {
		while(true) {
			String[] theaters=TheaterDataManage.getTheater();
			System.out.println("======상영관 목록======");
			for(int i=0;i<theaters.length;i++) {
				System.out.println(theaters[i]);
			}
			System.out.println("===================");
			String[] tmp={"메인 페이지로가기","상영관 등록","상영관 수정 및 삭제"};
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
			default:
				System.out.println("올바르지 않은 입력입니다.");
			}
		}
	}
	public static void theaterRegisterPage() {//8.2.2.1 상영관정보등록'
		while(true) {
			String theater="",rc="";
			int check=0;
			System.out.print("상영관 이름>>");
			theater=InputRule.ScreenRule();
			String theaters[] = TheaterDataManage.getTheaterName();
			for(int i=0;i<theaters.length;i++) {
				if(theaters[i].substring(1,theaters[i].length()-1).equals(theater)) {
					check=1;
				}
			}
			if(theater==null) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else if(check==1){
				System.out.println("이미 존재하는 이름입니다.");
			}
			else {
				while(true) {
					System.out.print("좌석의 행과 열 수>>");
					rc=scan.nextLine();
					rc=rc.replace(" ","");
					int row=0,col=0;
					try {
						String[] tmp=rc.split("-|/");
						row=Integer.parseInt(tmp[0]);
						col=Integer.parseInt(tmp[1]);
					}catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("올바르지 않은 입력입니다.");
						continue;
					}
					if(row<1||row>26||col<1||col>50) {
						System.out.println("올바르지 않은 입력입니다.");
					}
					else {
						TheaterDataManage.setJsonTheater(theater,row,col);
						System.out.println("=====등록완료=====");
						return;
					}
				}	
			}
		}
	}
	public static void theaterCheckPage() {//8.2.2.2 상영관정보확인
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
			System.out.print("수정 및 삭제할 상영관을 선택하세요>>>");
			int menuNum=InputRule.MenuRule(theatermenu);
			if(menuNum==-1) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			else if(menuNum==0) break;
			else {
				theaterFixPage(menuNum-1);//정상입력시
			}
		}
		
	}
	public static void theaterFixPage(int index) {//8.2.2.2.1 상영관정보수정및삭제
		while(true) {
			String theater="",rc="";
			System.out.println("======영화 수정 및 삭제======");
			System.out.println(TheaterDataManage.readIndexTheater(index));
			System.out.println("0. 뒤로가기");
			System.out.println("1. 수정");
			System.out.println("2. 삭제");
			System.out.print(">>>");
			String[] tmp2={"뒤로가기","수정","삭제"};
			int menuNum=InputRule.MenuRule(tmp2);
			if(menuNum==-1) {
				System.out.println("올바르지 않은 입력입니다.");
			}
			if(menuNum==0) {// 여기 오류나는데 잘 모르겠음 왜인지..
				break;
			}
			else if(menuNum==1){
				while(true) {
					System.out.println("======상영관 수정======"); 
					System.out.print("상영관 이름>>>");
					theater=InputRule.ScreenRule();
					int check=0;
					String theaters[] = TheaterDataManage.getTheaterName();
					for(int i=0;i<theaters.length;i++) {
						if(theaters[i].substring(1,theaters[i].length()-1).equals(theater)) {
							check=1;
							break;
						}
					}
					if(theater==null) {
						System.out.println("올바르지 않은 입력입니다.");
					}
					else if(check==1) {
						System.out.println("이미 존재하는 이름입니다.");
					}
					else {
						System.out.print("좌석의 행과 열 수>>");
						rc=scan.nextLine();
						rc=rc.replace(" ","");
						int row=0,col=0;
						try {
							String[] tmp=rc.split("-|/");
							row=Integer.parseInt(tmp[0]);
							col=Integer.parseInt(tmp[1]);
						}catch(ArrayIndexOutOfBoundsException e) {
							System.out.println("올바르지 않은 입력입니다.");
							continue;
						}
						
						if(row<1||row>26||col<1||col>50) {
							System.out.println("올바르지 않은 입력입니다.");
						}
						else {
							TheaterDataManage.fixTheater(index, theater, row, col);
							return;
						}
					}
					return;
				}
			}
			else if(menuNum==2) {
				TheaterDataManage.deleteTheater(index);
				return;
			}
		}
	}
}
