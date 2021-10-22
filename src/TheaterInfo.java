
public class TheaterInfo {//상영관정보
	private String name;
	private int row;
	private int col;
	
	public TheaterInfo (String name, int row, int col) {
		this.name= name;
		this.row =row;
		this.col=col;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
}
