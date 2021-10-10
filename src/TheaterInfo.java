
public class TheaterInfo {//상영관정보
	String theaterName;
	int row;
	int col;
	
	public TheaterInfo(String theaterName, int row, int col) {
		this.theaterName = theaterName;
		this.row = row;
		this.col = col;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	

}
