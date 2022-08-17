package actionpanel

public class AssertionElementsHandler {
	private static AssertionElementsHandler mInstance = null

	private AssertionElementsHandler(){
	}
	public static AssertionElementsHandler getInstance(){
		if(mInstance == null){
			mInstance = new AssertionElementsHandler()
		}

		return mInstance
	}
}
