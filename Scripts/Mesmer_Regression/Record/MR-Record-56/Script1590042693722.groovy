import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
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
WebDriver driver = DriverFactory.getWebDriver()
try{

	WebElement searchedReadyDevice = Utility.selectDevice(Device.toString())
	if(searchedReadyDevice != null){
		searchedReadyDevice.click()
		if(CreateTestController.getInstance().deviceChecks()){
			WebUI.delay(10)
			if(CreateTestController.getInstance().clickDoneGreenButton()){
				if(WebUI.waitForElementPresent(btnDiscard, 20)){
					WebUI.click(btnDiscard)
					if(WebUI.verifyElementPresent(alertDiscardRecordingTestCase, 20)){
						if(WebUI.verifyElementPresent(btnYesDiscardIt, 20)){

							if(WebUI.verifyElementPresent(btnNeverMind, 20)){
								if(WebUI.verifyElementPresent(btnCross, 20)){
									WebUI.click(btnCross)
								}else{
									MesmerLogUtils.markFailed("Cross button is not available")
								}

							}else{
								MesmerLogUtils.markFailed("Nevermind button is not available")
							}
						}else{
							MesmerLogUtils.markFailed("Yes, Discard It button is not available")
						}
					}else{
						MesmerLogUtils.markFailed("No confirmation dialogue appears on discard recording")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to click on discard button")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to click on done button")
			}
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

