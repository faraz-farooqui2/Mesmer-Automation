import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-29 | Behaviour when user selects more than one devices for replay
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{
	//	//Additional Steps to perform Step No.2
	//	if(WebUI.waitForElementPresent(Tag, 10) ==true){
	//		WebUI.click(Tag)
	//		if(WebUI.waitForElementPresent(optionTag, 10) == true){
	//			WebUI.click(optionTag)
	//			WebUI.click(Tag)
	//			KeywordUtil.markPassed("PASSED: Clicked on Tags and Selected a Tag")
	//		}
	//	}
	//	else{
	//		KeywordUtil.markFailed("FAILED: Tag option not found")
	//	}

	if (WebUI.waitForElementPresent(testLink , 20)){
		WebUI.click(testLink)
		WebUI.delay(2)

		//1. User clicks on replay icon
		if(WebUI.waitForElementPresent(btnRerun, 10)==true){
			WebUI.click(btnRerun)
			KeywordUtil.markPassed("PASSED: Clicked on Replay Button")
			WebUI.delay(2)
			if(WebUI.waitForElementPresent(deviceList, 10)==true){
				String deviceList = findTestObject('Object Repository/OR_Replay/list_DeviceList').findPropertyValue('xpath').toString()

				List<WebElement> device = driver.findElements(By.xpath(deviceList))
				int deviceListCount = device.size()
				
				if(device.size() > 2 ){
					device.get(0).click()
					WebUI.delay(2)
					device.get(1).click()
					WebUI.delay(2)
					KeywordUtil.markPassed("PASSED: Number of Devices in a list " + deviceListCount)
					if(WebUI.waitForElementPresent(btnRun, 10)==true){
						WebUI.click(btnRun)
						KeywordUtil.markPassed("PASSED: Clicked on Run Button")
						if(WebUI.waitForElementPresent(btnYes, 10)==true){
							WebUI.click(btnYes)
							WebUI.delay(2)
							KeywordUtil.markPassed("PASSED: Clicked on Yes Button")
							if(WebUI.waitForElementPresent(msgqueue, 10)==true){
								if(WebUI.waitForElementNotPresent(msgqueue, 10)==true){
								
							}else{
							KeywordUtil.markFailed("FAILED: Queue msg not disappear")
							}
							}else{
							KeywordUtil.markFailed("FAILED: No queue msg appears")
							}
						}else{
							KeywordUtil.markFailed("FAILED: Unable to click on Yes button")
						}
					}else{
						KeywordUtil.markFailed("FAILED: Unable to click on Run button")
					}
				}else{
					KeywordUtil.markFailed("WARNING: No Device Found")
				}
			}
		}else{
			KeywordUtil.markFailed("FAILED: Unable to Click on Replay Button")
		}
	}else{
		KeywordUtil.markFailed("Could not click on test case")
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
	if(CustomKeywords.'com.mesmer.Utility.stopExecution'()){
		}else{
		KeywordUtil.markFailed("Could not stop test case replay")
	}
}