import java.util.Comparator;

public class LogData implements Comparable<LogData>{

	private String date;
	private String time;
	private int row;
	private int col;	
	
	LogData(String date, String time, int row, int col){
		this.date = date;
		this.time = time;
		this.row =row;
		this.col = col;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		String str = date+" "+time +" "+row+" "+col;
		return str;
	}

	@Override
	public int compareTo(LogData o) {
		if(Print.isAfterDate(this.date, this.time, o.date, o.time))
			return 1;
		else
			return -1;
	}

	
	
}
