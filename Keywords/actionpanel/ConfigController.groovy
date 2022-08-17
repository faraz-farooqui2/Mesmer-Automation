package actionpanel

public class ConfigController {
	private static ConfigController mInstance = null

	private ConfigController(){
	}
	public static ConfigController getInstance(){
		if(mInstance == null){
			mInstance = new ConfigController()
		}

		return mInstance
	}
}
