import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-09 | Verify the device in Ready state should be immediately accessed
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()

try{

	TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
	TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)

	TestObject chkTestCase = findTestObject('Object Repository/OR_Replay/chkbox_replayProv')
	TestObject chktestCaseTitle = CustomKeywords.'com.mesmer.Utility.clickCheckbox'(chkTestCase, cName)

	String test = testCaseTitle.findPropertyValue('xpath').toString()
	WebElement testCaseTitle2 = driver.findElement(By.xpath(test))

	if(testCaseTitle2 != null){
		Actions builder = new Actions(driver);

		builder.moveToElement(testCaseTitle2).perform();
		KeywordUtil.logInfo("[MESMER]: Test case focused successfully by mouse hovering")
		WebUI.delay(5)
		WebUI.click(chktestCaseTitle)
		KeywordUtil.logInfo("[MESMER]: Test case selected successfully")

		//1. User clicks on replay icon
		if(WebUI.waitForElementPresent(btnReplay, 10)==true){
			WebUI.click(btnReplay)
			KeywordUtil.markPassed("PASSED: Clicked on Replay Button")
			if(WebUI.waitForElementPresent(deviceList, 10)==true){
				String readyDeviceList = findTestObject('Object Repository/OR_Replay/list_readyDeviceList').findPropertyValue('xpath').toString()

				List<WebElement> readydevice = driver.findElements(By.xpath(readyDeviceList))
				int readyListCount = readydevice.size()
				println(readyDeviceList)
				if((readydevice != null) && (readydevice.size() > 0 )){
					readydevice.get(0).click()
					KeywordUtil.markPassed("PASSED: Number of Ready Devices " + (readyDeviceList) +" in the list")
					if(WebUI.waitForElementPresent(btnRun, 10)){
						WebUI.click(btnRun)
						if(WebUI.waitForElementPresent(btnYes, 10)){
							WebUI.click(btnYes)
							
							TestObject xpathInprogressReplay = findTestObject('Object Repository/OR_Replay/status_InprogressReplay')
							TestObject inProgressReplay = CustomKeywords.'com.mesmer.Utility.selectTestCase'(xpathInprogressReplay, tcName)
							
							TestObject xpathInqueueReplay = findTestObject('Object Repository/OR_Replay/status_InqueueReplay')
							TestObject inqueueReplay = CustomKeywords.'com.mesmer.Utility.selectTestCase'(xpathInqueueReplay, tcName)
							
							if(WebUI.waitForElementPresent(msgQueue, 5)== true || WebUI.waitForElementPresent(inqueueProgress, 5)== true || WebUI.waitForElementPresent(inProgressReplay, 5)== true || WebUI.waitForElementPresent(inqueueReplay, 5)== true ){

							}else {
								KeywordUtil.markFailed("FAILED: No pop up msg appear")
							}
							
//							if(WebUI.waitForElementPresent(msgQueue, 10)){
//							
//								if(WebUI.waitForElementNotPresent(inQueueStatus, 180)){
//
//								}else{
//									KeywordUtil.markFailed("FAILED: Test case failed to replay in 3 mins")
//								}
//							}else if (WebUI.waitForElementPresent(inqueueProgress, 180)){
//								KeywordUtil.markFailed("FAILED: Test case still in queue for more than 3 mins")
//							}
						}else{
							KeywordUtil.markFailed("FAILED: Unable to click on Yes button")
						}
					}else{
						KeywordUtil.markFailed("FAILED: Unable to Click on Run Button")
					}
				}else{
					KeywordUtil.markWarning("WARNING: No Ready Devices Found")
				}
			}
		}else{
			KeywordUtil.markFailed("FAILED: Unable to Click on Replay Button")
		}
	}else{
		KeywordUtil.markWarning("WARNING: No test case found")
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
if(CustomKeywords.'com.mesmer.Utility.stopExecution'()){
	
		}else{
		KeywordUtil.markFailed("Could not stop test case replay")
	}
}