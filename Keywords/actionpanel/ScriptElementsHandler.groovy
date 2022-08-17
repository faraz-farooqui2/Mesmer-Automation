package actionpanel

public class ScriptElementsHandler {
	private static ScriptElementsHandler mInstance = null

	private ScriptElementsHandler(){
	}
	public static ScriptElementsHandler getInstance(){
		if(mInstance == null){
			mInstance = new ScriptElementsHandler()
		}

		return mInstance
	}
}
