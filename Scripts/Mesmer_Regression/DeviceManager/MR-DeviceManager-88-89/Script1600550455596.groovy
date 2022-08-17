import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.DeviceManagerController

// Asuming tests are already in queue
// MR-DeviceManager-02 | Verify Queue column should show the actual queue count on a device with an eye icon having details of current task
// MR-DeviceManager-03 | Verify clicking View Live Task link should direct user to the in-progress task
//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebUI.delay(10)
WebDriver driver = DriverFactory.getWebDriver()
try{

	if(testCase88()){
		MesmerLogUtils.markPassed("Test case 88 passed")
		if(testCase89()){
			MesmerLogUtils.markPassed("Test case 89 passed")
		}else{
			MesmerLogUtils.markFailed("Test case 89 failed")
		}
	}else{
		MesmerLogUtils.markFailed("Test case 88 failed")
	}
}
catch(Exception e){
	e.printStackTrace()
}

private boolean testCase88(){
	boolean result = false
	if(DeviceManagerController.getInstance().mouseHoverOnQueueCount()){
		if(DeviceManagerController.getInstance().verifyLiveViewTaskPopOver()){
			WebUI.delay(5)
			result = true
		}else{
			MesmerLogUtils.markFailed("Verify Live View Task Pop Over method failed")
		}
	}else{
		MesmerLogUtils.markFailed("Mouse Hover On Queue method failed")
	}
	return result
}

private boolean testCase89(){
	boolean result = false
	if(DeviceManagerController.getInstance().clickOnLiveViewTaskBtn()){
		WebUI.delay(10)
		if(DeviceManagerController.getInstance().verifyRedirectionLiveViewTask()){
			result = true
		}else{
			MesmerLogUtils.markFailed("Verify Redirection Live View Task method failed")
		}
	}else{
		MesmerLogUtils.markFailed("Click On Live View Task Btn method failed")
	}
	return result
}