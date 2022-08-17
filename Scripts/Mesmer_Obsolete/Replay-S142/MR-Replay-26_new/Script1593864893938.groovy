import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-24 | Behavior when replay test case is failed due to "Element recorded not found"

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
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

		if(WebUI.waitForElementPresent(btnReplay, 10) == true) {

			WebUI.click(btnReplay)
			KeywordUtil.logInfo("Replay button clicked successfully")

			if(WebUI.waitForElementPresent(deviceList, 10) == true) {
				if(WebUI.waitForElementPresent(btnRun, 10) == true) {
					WebUI.click(btnRun)
					WebUI.click(btnYes)
					
					KeywordUtil.markPassed("PASSED: Clicked on Run Button")
					
					if(WebUI.waitForElementPresent(msgqueue, 10) == true) {
					
						openTestCase()

					if (WebUI.waitForElementPresent(statusInprogress, 180)){
						KeywordUtil.logInfo("Test Case replay started")
						WebUI.delay(1)

						if (WebUI.waitForElementNotPresent(statusInprogress, 1200)){
							KeywordUtil.logInfo("Test Case Replay completed")

							if(WebUI.verifyElementPresent(testCaseStatus, 10)){
								String statusStr = WebUI.getText(testCaseStatus)
								if(statusStr != null && !statusStr.isEmpty()){
									if(statusStr.contains("Passed")){
										KeywordUtil.logInfo("Test case is in Passed status")
									}
									else if(statusStr.contains("Broken")){
										KeywordUtil.logInfo("Test Case is in Broken Status")
										getBrokenTCErrors()
									}
									else if(statusStr.contains("Failed")){
										KeywordUtil.logInfo("Test case is in Failed status")
										getFailedTCErrors()
									}
									else if(statusStr.contains("Needs Review")){
										KeywordUtil.logInfo("Test case is in Needs Review status")
									}
								}
							}
							else{
								KeywordUtil.markFailed("Test case status label not found")
							}
						} else {
							KeywordUtil.markFailed("Replay still not completed in 20 mins")
						}
					} else{
						KeywordUtil.markFailed("Replay not started in 3 mins")
					}
					
					} else {
						KeywordUtil.markWarning("No alert appear for queue msg on Test Result Page")
					}

				} else {
					KeywordUtil.markFailed("Could not click on Run button")
				}
			} else {
				KeywordUtil.markFailed("No Devices found in the List (Replay on Test Result)")
			}

		}
		else{
			KeywordUtil.markFailed("Replay did not start)")
		}
	}else{
		KeywordUtil.markWarning("No test case found with the name '" + tcName + "'")
	}
}catch(Exception e){
	e.printStackTrace()
}

private boolean openTestCase(){
boolean result = false
	TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
	TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)
	if(testCaseTitle != null){
		WebUI.click(testCaseTitle)
		WebUI.delay(2)
		KeywordUtil.logInfo("Navigated to test detail page")
		result = true
	}else{
		KeywordUtil.markFailed("Unable to open test case")
	}
	return result
}


def getBrokenTCErrors() {
	//	WebDriver driver = DriverFactory.getWebDriver()
	//	String listScreens = findTestObject('Object Repository/OR_Replay/list_testCaseScreens').findPropertyValue('xpath').toString()
	//	List<WebElement> testCaseScreens = driver.findElements(By.xpath(listScreens))
	
	if(WebUI.verifyElementPresent(brokenStep, 10)){
		KeywordUtil.logInfo("Broken test step found")
		WebUI.click(brokenStep)
		KeywordUtil.logInfo("Broken test step clicked")
		WebUI.delay(2)

		getErrorsFromTestStepDialog()
		if (WebUI.verifyElementPresent(btnClose, 10)){
			WebUI.click(btnClose)
			KeywordUtil.logInfo("Clicked on the cross button on test step detail dialogue")
			
		}else{
			KeywordUtil.logInfo("Could not click on the cross button on test step detail dialogue")
		}
	}else {
		KeywordUtil.logInfo("No broken step found in the test steps")
	}

	if (WebUI.waitForElementPresent(mesmerEncounteredErrorStep, 10)){
		KeywordUtil.logInfo("Broken Div Found in Test Step")
		String brokenText = WebUI.getText(mesmerEncounteredErrorStep)
		KeywordUtil.markWarning("Test Case broken due to [" + brokenText + "]")
	}else{
		KeywordUtil.logInfo("No Broken Div found in the test step")
	}
}

private boolean getErrorsFromTestStepDialog(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()

	//	String listErrorOnScreen = findTestObject('Object Repository/OR_Replay/list_errorOnScreen').findPropertyValue('xpath').toString()

	String listPointErrors = findTestObject('Object Repository/OR_Replay/list_errorPoint').findPropertyValue('xpath').toString()
	String errorStr = "Device Errors ::"
	String otherFailureStr = "Other Errors ::"

	if (WebUI.verifyElementPresent(findTestObject('Object Repository/OR_Replay/list_errorPoint'), 10)){

		List<WebElement> error = driver.findElements(By.xpath(listPointErrors))

		for (WebElement webElement : error) {
			String errorText = webElement.getText();
			if(errorText.contains("Element recorded not found")){
				errorStr = errorStr + " -- [" + errorText + "]"
			} else {
				otherFailureStr = otherFailureStr + " -- [" + errorText + "]"
			}
		}

		if (errorStr.contains("Element recorded not found")){
			KeywordUtil.markFailed(errorStr);
			result = true
		}else if (otherFailureStr.contains("--")) {
			KeywordUtil.markWarning(otherFailureStr)
			result = true
		} else {
			KeywordUtil.logInfo("No error found")
		}
	}else {
		KeywordUtil.logInfo("Could not find list of errors in Test Step Details")
	}
	return result 
}

def getFailedTCErrors() {
	if(WebUI.waitForElementPresent(failedStep, 20)){
		KeywordUtil.logInfo("Failed test step found")
		WebUI.click(failedStep)
		KeywordUtil.logInfo("Failed test step clicked")

		WebUI.delay(2)
		getFailuresFromTestStepDialog()
		if (WebUI.verifyElementPresent(btnClose, 10)){
			WebUI.click(btnClose)
			KeywordUtil.logInfo("Clicked on the cross button on test step detail dialogue")
		}else{
			KeywordUtil.logInfo("Could not click on the cross button on test step detail dialogue")
		}
	}else {
		KeywordUtil.logInfo("No failed step found in the test steps")
	}
	
	if (WebUI.waitForElementPresent(mesmerEncounteredErrorStep, 10)){
		KeywordUtil.logInfo("Broken Div Found in Test Step")
		String brokenText = WebUI.getText(mesmerEncounteredErrorStep)
		KeywordUtil.markWarning("Test Case broken due to [" + brokenText + "]")
	}else{
		KeywordUtil.logInfo("No Broken Div found in the test step")
	}
}

private boolean getFailuresFromTestStepDialog(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()

	String listFailedOnScreen = findTestObject('Object Repository/OR_Replay/list_failedOnScreen').findPropertyValue('xpath').toString()
	String failureStr = "Device Failures ::"
	String otherFailureStr = "Other Failures ::"

	if (WebUI.verifyElementPresent(findTestObject('Object Repository/OR_Replay/list_failedOnScreen'), 10)){
		List<WebElement> failed = driver.findElements(By.xpath(listFailedOnScreen))

		for (WebElement webElement : failed) {
			String failedText = webElement.getText()
			if(failedText.contains("Element recorded not found")){
				failureStr = failureStr + " -- [" + failedText + "]"
			} else {
				otherFailureStr = otherFailureStr + " -- [" + failedText + "]"
			}
		}

		if (failureStr.contains("Element recorded not found")){
			KeywordUtil.markFailed(failureStr);
			result = true 
		}else if (otherFailureStr.contains("--")) {
			KeywordUtil.markWarning(otherFailureStr)
			result = true
		} else {
			KeywordUtil.logInfo("No failures found")
		}
	} else {
		KeywordUtil.logInfo("Could not find list of failures in Test Step Details")
	}
	return result
}