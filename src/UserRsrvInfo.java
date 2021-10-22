

public class UserRsrvInfo {
	private RunningInfo runInfo;
	private ReserveInfo rsrvInfo;
	
	public UserRsrvInfo(RunningInfo runInfo, ReserveInfo rsrvInfo) {
		this.runInfo = runInfo;
		this.rsrvInfo = rsrvInfo;
	}

	@Override
	public String toString() {
		String infoStr = runInfo.toString();
		infoStr += rsrvInfo.toString();
		return infoStr;
	}
	
}
