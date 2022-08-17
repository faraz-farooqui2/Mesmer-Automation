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

			crossRebootDevice()

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

def crossRebootDevice(){
	if(WebUI.waitForElementPresent(deviceReboot, 20)){
		WebUI.click(deviceReboot)
		WebUI.delay(5)
		if(WebUI.waitForElementPresent(confirmationRebootDialogue, 20)){
			MesmerLogUtils.markPassed("Reboot device confirmation dialogue appears")

			if(WebUI.waitForElementPresent(btnCrossReboot, 20)){
				WebUI.click(btnCrossReboot)
			}else{
				MesmerLogUtils.markFailed("Unable to click on cross reboot button")
			}
		}else{
			MesmerLogUtils.markFailed("Reboot device confirmation dialogue not appears")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on device reboot button")
	}

}