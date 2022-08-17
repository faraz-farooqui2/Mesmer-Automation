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
 * MS-Create a new Test Case-01 | Verify that user should be able to record an iOS test case (Physical/Simulator)
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()

try{
	WebElement searchedReadyDevice = Utility.selectDevice(Device.toString())
	if(searchedReadyDevice != null){
		searchedReadyDevice.click()
		if(CreateTestController.getInstance().deviceChecks()){
			WebUI.delay(15)
			closeApplicationButton()

		}else{
			MesmerLogUtils.markFailed("Device checks failed")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on ready device")
	}

}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

def closeApplicationButton(){

	if(WebUI.waitForElementPresent(closeApplication, 30)){
		WebUI.click(closeApplication)
		WebUI.delay(10)

		if(WebUI.waitForElementPresent(confirmationCloseDialogue, 10)){
			WebUI.delay(2)
			if(WebUI.waitForElementPresent(btnYesCloseIt, 10)){
				
				if(WebUI.waitForElementPresent(btnCancelCloseApplication, 10)){

				}else{
					MesmerLogUtils.markFailed("Cancel button not appears")
				}
			}else{
				MesmerLogUtils.markFailed("Yes Close It button not appears")
			}
		}else{
			MesmerLogUtils.markFailed("Confirmation dialogue close application not appears")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on close application button")
	}
}
