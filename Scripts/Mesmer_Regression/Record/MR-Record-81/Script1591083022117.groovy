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
			if(CreateTestController.getInstance().waitForRecordingStarts()){

				String deviceLogsIconXpath = findTestObject('Object Repository/OR_CreateNewTestCase/icon_logs').findPropertyValue('xpath').toString()
				WebElement deviceLogsIcon = driver.findElement(By.xpath(deviceLogsIconXpath))
				deviceLogsIcon.click()

				if(WebUI.waitForElementPresent(btnSetting, 20)){
					WebUI.click(btnSetting)

					if(CreateTestController.getInstance().verifyOptionsAvailableOnSetting()){
						
					}else{
					MesmerLogUtils.markFailed("Unable to verify available option for settings button")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to click on Settings button")
				}
			}else{
				MesmerLogUtils.markFailed("Recording not started yet")
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
