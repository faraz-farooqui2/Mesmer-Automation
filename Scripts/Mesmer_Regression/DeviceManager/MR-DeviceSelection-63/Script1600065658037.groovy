import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController

/*
 * MR-Device Selection-63 | Record | Verify list of devices should appear when user redirect to record test case page
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()

try{
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){

		List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())

		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			Utility.selectDeviceAndSetParams("" , "" , Device.toString(), "" , "", "", "")
			if(CreateTestController.getInstance().deviceChecks()){
				if(CreateTestController.getInstance().waitForRecordingStarts()){

				}else{
					MesmerLogUtils.markFailed("Recording not started yet")
				}
			}else{
				MesmerLogUtils.markFailed("Device checks failed")
			}

		}else{
			MesmerLogUtils.markFailed("Unable to click on ready device")
		}
	}
	else{
		MesmerLogUtils.markFailed("CreateNewTest screen did not open within 1 minute")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}
