import java.util.ArrayList;

public class TheaterInfo {//상영관정보
	private String name;
	private int row;
	private int col;
	
	private ArrayList<LogData> log;
	
	public TheaterInfo (String name,ArrayList<LogData> log) {
		this.name = name;
		this.log = log;
	}
	
	public TheaterInfo () {
	}
	
	public ArrayList<LogData> getLog(){
		return this.log;
	}	
	
	
	public void setLog(ArrayList<LogData> log) {
		this.log = log;	
	}
	
	// old one -------
	
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
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
}
