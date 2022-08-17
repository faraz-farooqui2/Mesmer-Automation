package actionpanel

public class DataController {
	private static DataController mInstance = null

	private DataController(){
	}
	public static DataController getInstance(){
		if(mInstance == null){
			mInstance = new DataController()
		}

		return mInstance
	}
}
