import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import org.openqa.selenium.interactions.Actions

//MR-Replay-21 | Verify behavior when 'Replay' is in progress
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
				String deviceList = findTestObject('Object Repository/OR_Replay/list_DeviceList').findPropertyValue('xpath').toString()

				List<WebElement> device = driver.findElements(By.xpath(deviceList))
				
				if(device.size() > 0 ){
					device.get(0).click()
					KeywordUtil.markPassed("PASSED: Number of Devices " + device.size() + " in the list")
					if(WebUI.waitForElementPresent(btnRun, 20)){
						WebUI.click(btnRun)
						if(WebUI.waitForElementPresent(btnYes, 20)){
							WebUI.click(btnYes)
							if(WebUI.waitForElementPresent(executionProgressBar, 240)){
								WebUI.delay(2)
							}else{
								KeywordUtil.markFailed("FAILED: No execution progress bar appears in 4 mins")
							}
						}else{
							KeywordUtil.markFailed("FAILED: Unable to click on Yes button")
						}
					}else{
						KeywordUtil.markFailed("FAILED: Unable to click on Run button")
					}
				}else{
					KeywordUtil.markFailed("FAILED: No Device Found")
				}
			}
		}else{
			KeywordUtil.markFailed("FAILED: Unable to Click on Replay Button")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to open test case")
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
	CustomKeywords.'com.mesmer.Utility.stopExecution'()
	WebUI.delay(10)
}