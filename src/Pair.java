
public class Pair { 
	/* CWT
	 * 영화 좌표 표현을 위한 Pair 객체입니다.
	 * int 형식의 (행, 열)순서쌍 표현이므로 A = 0, B = 1 등으로 사용합니다.
	 * ex) B행 3열 = (1,3)
	 * */
	    private int x;
	    private int y;

	    Pair(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }

	    public int getRow(){
	        return x;
	    }

	    public int getCol(){
	        return y;
	    }
}
