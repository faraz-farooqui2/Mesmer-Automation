package actionpanel

public class AssertionController {
	private static AssertionController mInstance = null

	private AssertionController(){
	}
	public static AssertionController getInstance(){
		if(mInstance == null){
			mInstance = new AssertionController()
		}

		return mInstance
	}
}
