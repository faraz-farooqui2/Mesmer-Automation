import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

//MR-Replay-10 | Verify the device with the status "In Use" should be accessible to another user
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()


try{
	WebDriver driver = DriverFactory.getWebDriver()
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
	TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, testCaseName)

	TestObject chkTestCase = findTestObject('Object Repository/OR_Replay/chkbox_replayProv')
	TestObject chktestCaseTitle = CustomKeywords.'com.mesmer.Utility.clickCheckbox'(chkTestCase, checkBoxName)

	String test = testCaseTitle.findPropertyValue('xpath').toString()
	WebElement testCaseTitle2 = driver.findElement(By.xpath(test))
	if(testCaseTitle2 != null){
		Actions builder = new Actions(driver);
		builder.moveToElement(testCaseTitle2).perform();


		WebUI.click(chktestCaseTitle)
		WebUI.delay(5)
		//1. User clicks on replay icon
		if(WebUI.waitForElementPresent(btnReplay, 10)==true){
			WebUI.click(btnReplay)
			KeywordUtil.markPassed("PASSED: Clicked on Replay Button")
			if(WebUI.waitForElementPresent(deviceList, 10)==true){
				String inUseDeviceList = findTestObject('Object Repository/OR_Replay/list_DeviceInUse').findPropertyValue('xpath').toString()

				List<WebElement> inUsedevice = driver.findElements(By.xpath(inUseDeviceList))
				int inUseListCount = inUsedevice.size()
				println(inUseListCount)
				if((inUsedevice != null) && (inUsedevice.size() > 0 )){
					inUsedevice.get(0).click()
					KeywordUtil.markPassed("PASSED: Number of InUse Devices " + (inUseListCount) +" in the list")
					if(WebUI.waitForElementPresent(btnRun, 10)){
						WebUI.click(btnRun)
						if(WebUI.waitForElementPresent(btnYes, 10)){
							WebUI.click(btnYes)
							
							TestObject xpathInprogressReplay = findTestObject('Object Repository/OR_Replay/status_InprogressReplay')
							TestObject inProgressReplay = CustomKeywords.'com.mesmer.Utility.selectTestCase'(xpathInprogressReplay, testCaseName)
							
							TestObject xpathInqueueReplay = findTestObject('Object Repository/OR_Replay/status_InqueueReplay')
							TestObject inqueueReplay = CustomKeywords.'com.mesmer.Utility.selectTestCase'(xpathInqueueReplay, testCaseName)
							
							if(WebUI.waitForElementPresent(msgQueue, 5)== true || WebUI.waitForElementPresent(inqueueProgress, 5)== true || WebUI.waitForElementPresent(inProgressReplay, 5)== true || WebUI.waitForElementPresent(inqueueReplay, 5)== true ){

							}else {
								KeywordUtil.markFailed("FAILED: No pop up msg appear")
							}
						}else{
							KeywordUtil.markFailed("FAILED: Unable to click on Yes button")
						}
					}else{
						KeywordUtil.markFailed("FAILED: Unable to Click on Run Button")
					}

				}else{
					KeywordUtil.markWarning("WARNING: No InUse Devices Found")
				}
			}
		}else{
			KeywordUtil.markFailed("FAILED: Unable to Click on Replay Button")
		}
	}else{
		KeywordUtil.markFailed("WARNING: No test case found with the name"  +testCaseName )
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
	if(CustomKeywords.'com.mesmer.Utility.stopExecution'()){
	}else{
		KeywordUtil.markFailed("Could not stop test case replay")
	}
}

