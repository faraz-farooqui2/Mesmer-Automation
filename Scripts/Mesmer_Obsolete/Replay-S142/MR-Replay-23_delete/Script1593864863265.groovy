import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.json.JSONObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility


//MR-Replay-23 | Verify behavior when 'Replay' is done
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	selectTestCase()
	WebUI.delay(5)
	runTestCaseOnRandomDevice()
	WebUI.delay(10)
	openTestCase()
	WebUI.delay(5)
	verifyReplay()


	if(WebUI.verifyElementPresent(replayDeviceName, 10)){
		String deviceName = WebUI.getText(findTestObject('Object Repository/OR_Replay/txt_ReplayDeviceName'))
		KeywordUtil.markPassed("Device Name ::" + deviceName)

		if(WebUI.verifyElementPresent(replayDeviceOS, 10)){
			String deviceOS = WebUI.getText(findTestObject('Object Repository/OR_Replay/txt_ReplayDeviceOS'))
			KeywordUtil.markPassed("Device Name ::" + deviceOS)

			if(WebUI.verifyElementPresent(replayDeviceResolution, 10)){
				String deviceResolution = WebUI.getText(findTestObject('Object Repository/OR_Replay/txt_ReplayDeviceResolution'))
				KeywordUtil.markPassed("Device Name ::" + deviceResolution)

				String screenNumbers = findTestObject('Object Repository/OR_Replay/screenNumbers').findPropertyValue('xpath').toString()
				List<WebElement> screenCount = driver.findElements(By.xpath(screenNumbers))
				KeywordUtil.markPassed("Screen Numbers ::" + screenCount.size())

				if((WebUI.verifyElementPresent(statsCountReview, 10)) || (WebUI.verifyElementPresent(statsCountDefect, 10)) || (WebUI.verifyElementPresent(statsCountBroken, 10))){
					String reviewCount = WebUI.getText(findTestObject('Object Repository/OR_Replay/stats_CountReview'))
					KeywordUtil.markPassed("Test case is in Needs Review status::" + reviewCount)

					String defectCount = WebUI.getText(findTestObject('Object Repository/OR_Replay/stats_CountFailed'))
					KeywordUtil.markPassed("Test case is in Needs Review status::" + defectCount)

					String brokenCount = WebUI.getText(findTestObject('Object Repository/OR_Replay/stats_CountBroken'))
					KeywordUtil.markPassed("Test case is in Needs Review status::" + brokenCount)

					String testCasesScroll = findTestObject('Object Repository/OR_Replay/screenNumbers').findPropertyValue('xpath').toString()
					List<WebElement> matCards = driver.findElements(By.xpath(testCasesScroll))

					if (WebUI.verifyElementPresent(matCards, 20)){

						if(matCards != null && !matCards.isEmpty()){

							if((WebUI.verifyElementPresent(needReviewStep, 10)) || (WebUI.verifyElementPresent(failedStep, 10)) || (WebUI.verifyElementPresent(brokenStep, 10))){

								String stepReviewList = findTestObject('Object Repository/OR_Replay/txt_Review').findPropertyValue('xpath').toString()

								List<WebElement> stepReview = driver.findElements(By.xpath(stepReviewList))

								for (WebElement webElement : stepReview) {
									KeywordUtil.logInfo("Needs Review Count ::" + stepReview.size())
								}

								String stepDefectList = findTestObject('Object Repository/OR_Replay/txt_Failed').findPropertyValue('xpath').toString()

								List<WebElement> stepDefect = driver.findElements(By.xpath(stepDefectList))

								for (WebElement webElement : stepDefect) {
									KeywordUtil.logInfo("Defect Count ::" + stepDefect.size())
								}

								String stepBrokenList = findTestObject('Object Repository/OR_Replay/txt_error').findPropertyValue('xpath').toString()

								List<WebElement> stepBroken = driver.findElements(By.xpath(stepBrokenList))

								for (WebElement webElement : stepBroken) {
									KeywordUtil.logInfo("Defect Count ::" + stepBroken.size())
								}


							}else{

							}
						}else{

						}


					}else{
						KeywordUtil.markFailed("No Mat Cards Shown")
					}

				}else{
					KeywordUtil.markFailed("No Stats Count Shown")
				}


			}else{
				KeywordUtil.markFailed("No replay device Resolution")
			}
		}else{
			KeywordUtil.markFailed("No replay device OS")
		}
	}else{
		KeywordUtil.markFailed("No replay device name")
	}

}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.stopExecution'()
}


private boolean selectTestCase(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
	TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)

	TestObject chkTestCase = findTestObject('Object Repository/OR_Replay/chkbox_replayProv')
	TestObject chktestCaseTitle = CustomKeywords.'com.mesmer.Utility.clickCheckbox'(chkTestCase, cName)

	String test = testCaseTitle.findPropertyValue('xpath').toString()
	WebElement testCaseTitle2 = driver.findElement(By.xpath(test))

	if(testCaseTitle2 != null){
		Actions builder = new Actions(driver);

		builder.moveToElement(testCaseTitle2).perform();
		KeywordUtil.markPassed("Test case focused successfully by mouse hovering")

		WebUI.click(chktestCaseTitle)
		KeywordUtil.markPassed("Test case selected successfully")
		WebUI.delay(5)
		result = true 
	}else{
		KeywordUtil.markFailed("No test case found with the name '" + tcName + "'")
	}
	return result
}

private boolean runTestCaseOnRandomDevice(){
	boolean result = false
	if(WebUI.waitForElementPresent(btnReplay, 20)==true){
		WebUI.click(btnReplay)
		KeywordUtil.markPassed("Clicked on Replay Test Cases")
		WebUI.delay(2)
		if(WebUI.waitForElementPresent(btnRun, 20)==true){
			WebUI.click(btnRun)
			KeywordUtil.markPassed("Clicked on Run Test Cases")
		}else{
			KeywordUtil.markFailed("Button Run Test Not Clicked")
		}
		if(WebUI.waitForElementPresent(btnYes, 20)==true){
			WebUI.click(btnYes)
			KeywordUtil.markPassed("Clicked on Yes button")
			result = true
		}
	}else{
		KeywordUtil.markFailed("Button Replay Not Clicked")
	}
	return result
}


private boolean openTestCase(){
boolean result = false
	TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
	TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)
	if(testCaseTitle != null){
		WebUI.click(testCaseTitle)
		WebUI.delay(2)
		KeywordUtil.markPassed("Navigated to test detail page")
		result = true
	}else{
		KeywordUtil.markFailed("Not navigated to test detail page")
	}
return result
}


def verifyReplay(){

	if (WebUI.waitForElementPresent(statusInprogress, 180)){
		KeywordUtil.markPassed("Test Case replay started")
		WebUI.delay(1)

		if (WebUI.waitForElementNotPresent(statusInprogress, 1200)){
			KeywordUtil.markPassed("Test Case Replay completed")

			if(WebUI.verifyElementPresent(testCaseStatus, 10)){
				String statusStr = WebUI.getText(testCaseStatus)
				if(statusStr != null && !statusStr.isEmpty()){
					if(statusStr.contains("Passed")){
						KeywordUtil.markPassed("Test case is in Passed status")
					}
					else if(statusStr.contains("Broken")){
						KeywordUtil.markPassed("Test Case is in Broken Status")

					}
					else if(statusStr.contains("Failed")){
						KeywordUtil.markPassed("Test case is in Failed status")

					}
					else if(statusStr.contains("Needs Review")){
						KeywordUtil.markPassed("Test case is in Needs Review status")

					}
				}
			}
			else{
				KeywordUtil.markFailed("Test case status label not found")
			}
		} else {
			KeywordUtil.markFailed("Replay still not completed in 20 mins")
		}
	} else {
		KeywordUtil.markFailed("Replay not started in 3 mins")
	}
}
