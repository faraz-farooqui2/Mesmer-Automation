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


//MR-Replay-08 | Verify the device in Provisioned state should be accessed within a few minutes
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
				String provDeviceList = findTestObject('Object Repository/OR_Replay/list_provDeviceList').findPropertyValue('xpath').toString()
				List<WebElement> provdevice = driver.findElements(By.xpath(provDeviceList))
				int provListCount = provdevice.size()
				println(provListCount)
				if(provdevice != null && provdevice.size() > 0 ){
					provdevice.get(0).click()
					KeywordUtil.markPassed("PASSED: Clicked on Provisioned Device in the list")

					if(WebUI.waitForElementPresent(btnRun, 10)){
						WebUI.click(btnRun)

						if(WebUI.waitForElementPresent(btnYes, 10)){
							WebUI.click(btnYes)
							if(WebUI.waitForElementPresent(msgQueue, 10)){
								WebUI.delay(2)
								WebUI.refresh()
								if(WebUI.waitForElementNotPresent(inQueueStatus, 180)){

								}else{
									KeywordUtil.markFailed("FAILED: Test case failed to replay in 3 mins")
								}
							}else if (WebUI.waitForElementPresent(inqueueProgress, 180)){
								KeywordUtil.markFailed("FAILED: Test case still in queue for more than 3 mins")
							}
						}else{
							KeywordUtil.markFailed("FAILED: Unable to click on Yes button")
						}
					}else{
						KeywordUtil.markFailed("FAILED: Unable to Click on Run Button")
					}
				}else{
					KeywordUtil.markWarning("WARNING: Unable to click on Provisioned Device")
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