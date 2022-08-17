package datamodels

public class EmailModel {
	private int passedTestCount;
	private int failedTestCount;

	public EmailModel(){
	}

	public int getPassedTestCount() {
		return passedTestCount;
	}

	public void setPassedTestCount(int passedTestCount) {
		this.passedTestCount = passedTestCount;
	}

	public int getFailedTestCount() {
		return failedTestCount;
	}

	public void setFailedTestCount(int failedTestCount) {
		this.failedTestCount = failedTestCount;
	}
}
