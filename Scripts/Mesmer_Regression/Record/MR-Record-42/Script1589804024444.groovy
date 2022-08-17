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
			if(WebUI.waitForElementClickable(btnShowSteps,20)){
				WebUI.click(btnShowSteps)

				String stepsXpath = findTestObject('Object Repository/OR_CreateNewTestCase/xpath_stepsList').findPropertyValue('xpath').toString()
				List<WebElement> stepsList = driver.findElements(By.xpath(stepsXpath))

				if(stepsList.size() > 0 ){
					MesmerLogUtils.markPassed("Recording steps are showing in show steps")
					if(WebUI.waitForElementClickable(btnHideSteps, 20)){
						WebUI.click(btnHideSteps)
					}else{
						MesmerLogUtils.markFailed("Unable to click on hide steps")
					}
				}else{
					MesmerLogUtils.markFailed("No recording steps found")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to click on show steps")
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
