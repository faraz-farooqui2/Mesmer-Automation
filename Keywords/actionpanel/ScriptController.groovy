package actionpanel

public class ScriptController {
	private static ScriptController mInstance = null

	private ScriptController(){
	}
	public static ScriptController getInstance(){
		if(mInstance == null){
			mInstance = new ScriptController()
		}

		return mInstance
	}
}
