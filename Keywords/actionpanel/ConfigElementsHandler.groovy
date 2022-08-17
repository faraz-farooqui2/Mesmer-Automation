package actionpanel

public class ConfigElementsHandler {
	private static ConfigElementsHandler mInstance = null

	private ConfigElementsHandler(){
	}
	public static ConfigElementsHandler getInstance(){
		if(mInstance == null){
			mInstance = new AssertionElementsHandler()
		}

		return mInstance
	}
}
