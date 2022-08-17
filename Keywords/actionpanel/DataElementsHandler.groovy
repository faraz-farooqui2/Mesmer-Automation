package actionpanel

public class DataElementsHandler {
	private static DataElementsHandler mInstance = null

	private DataElementsHandler(){
	}
	public static DataElementsHandler getInstance(){
		if(mInstance == null){
			mInstance = new DataElementsHandler()
		}

		return mInstance
	}
}
