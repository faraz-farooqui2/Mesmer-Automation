import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController

/*
 * MS-Create a new Test Case-03 | Verify that user should be able to select a compatible device to record a test case.
 */


try{

	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()

		List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())
		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			Utility.selectDeviceAndSetParams("" , "" ,Device.toString(), "" , "", "", "")
			if(CreateTestController.getInstance().deviceChecks()){
				if(CreateTestController.getInstance().waitForRecordingStarts()){
					if(CreateTestController.getInstance().clickDoneGreenButton()){
						if(CreateTestController.getInstance().clickDiscardButton()){
							if(CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()){
							}else{
								MesmerLogUtils.markFailed("Unable to click on discard alert")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to click on discard button")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to click on done button")
					}
				}else{
					MesmerLogUtils.markFailed("Recording not started yet")
				}
			}else{
				MesmerLogUtils.markFailed("Device checks failed")
			}
		}
		else{
			MesmerLogUtils.markFailed("No "+ Device.toString() + " device available in the list")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}

}catch(Exception e){
	e.printStackTrace()
}finally{

}

