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

import controllers.ReplayController

import org.openqa.selenium.JavascriptExecutor


WebDriver driver = DriverFactory.getWebDriver()
try{

	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)

	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(CustomKeywords.'com.mesmer.Utility.goToTestResults'()){

			//	//1. Click on a Tile/Test case to open.
			if(openTestCase()){
				MesmerLogUtils.markPassed("Test case opened")
				//2. Click on 'Rerun' button appearing in the top right corner
				if(WebUI.waitForElementPresent(btnRerun , 120)==true){
					WebUI.click(btnRerun)
					MesmerLogUtils.markPassed("Click On Re-run")

					//3. Click/select a device that is in Provisioned/ Ready state
					if(ReplayController.getInstance().selectDevice()){
						MesmerLogUtils.markPassed("Clicked on a device")

						//4. Click on Run button
						if(WebUI.waitForElementClickable(btnRun , 20) == true){
							WebUI.click(btnRun)
							MesmerLogUtils.markPassed("Click on Run Button")

							if(WebUI.waitForElementPresent(btnYes , 20) == true){
								WebUI.click(btnYes)
								MesmerLogUtils.markPassed("Click on Yes Button")

								if(verifyReplay()){
									MesmerLogUtils.markPassed("Replay verification is successful")

								}
								else{
									MesmerLogUtils.markFailed("Issue in verifying replay")
								}

							}else{
								MesmerLogUtils.markFailed("Unable to Click on Yes Button")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to Click on Run Button")
						}
					}
					else{
						MesmerLogUtils.markFailed("Could not select a device")
					}
				}
				else{
					MesmerLogUtils.markFailed("Unable to Click on Re-run")
				}
			}else{
				MesmerLogUtils.markFailed("Issue in opening a test case")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to navigate to test result page")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.delay(5)
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

private boolean openTestCase(){
	boolean result = false
	TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
	TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)
	if(testCaseTitle != null){
		WebUI.click(testCaseTitle)
		WebUI.delay(2)
		MesmerLogUtils.logInfo("Navigated to test detail page")
		result = true
	}else{
		MesmerLogUtils.markFailed("[DATA ISSUE] No test case found")
	}
	return result
}
private boolean verifyReplay(){
	boolean result = false

	if (WebUI.waitForElementPresent(statusInprogress, 180)){
//		WebUI.takeScreenshot("TestCaseInProgress")
		MesmerLogUtils.logInfo("Test Case replay started")
		Utility.logCurrentUTCTime("Time when replay started")
		WebUI.delay(1)

		if (WebUI.waitForElementNotPresent(statusInprogress, 900)){
			MesmerLogUtils.logInfo("Test Case Replay completed")
			Utility.logCurrentUTCTime("Time when replay completed")

			WebUI.delay(2)

			if(WebUI.verifyElementPresent(getTCStatus, 10)){
				String testCaseStatus1 = WebUI.getAttribute(getTCStatus, 'class')

				if(testCaseStatus1 != null && !testCaseStatus1.isEmpty()){
					if(testCaseStatus1.contains("passed")){
						MesmerLogUtils.logInfo("Test case is in Passed status")
						if(ReplayController.getInstance().verifyReplayWatchVideo())
						{
							MesmerLogUtils.markPassed("Video played successfully")
							result = true

						}
						else{
							MesmerLogUtils.markFailed("Video not played")
						}

					}
					else if(testCaseStatus1.contains("broken")){
						MesmerLogUtils.logInfo("Test Case is in Broken Status")
						if(ReplayController.getInstance().verifyReplayWatchVideo())
						{
							MesmerLogUtils.markPassed("Video played successfully")
							//getBrokenTCErrors()
							result = true
						}
						else{
							MesmerLogUtils.markFailed("Video not played")
						}

					}
					else if(testCaseStatus1.contains("failed")){
						MesmerLogUtils.logInfo("Test case is in Failed status")
						if(ReplayController.getInstance().verifyReplayWatchVideo())
						{
							MesmerLogUtils.markPassed("Video played successfully")
							//getFailedTCErrors()
							result = true
						}
						else{
							MesmerLogUtils.markFailed("Video not played")
						}

					}
					else if(testCaseStatus1.contains("needs review")){
						MesmerLogUtils.logInfo("Test case is in Needs Review status")
						if(ReplayController.getInstance().verifyReplayWatchVideo())
						{
							MesmerLogUtils.markPassed("Video played successfully")
							result = true
						}
						else{
							MesmerLogUtils.markFailed("Video not played")
						}
					}
				}else{
					MesmerLogUtils.markFailed("Test case status is empty")
				}
			}
			else{
				MesmerLogUtils.markFailed("Test case status label not found")
			}
		} else {
			MesmerLogUtils.markFailed("Replay still not completed in 15 mins")
		}
	}else {
		MesmerLogUtils.markFailed("Replay not started in 3 mins")
	}
	return result
}

def getBrokenTCErrors() {
	//	WebDriver driver = DriverFactory.getWebDriver()
	//	String listScreens = findTestObject('Object Repository/OR_Replay/list_testCaseScreens').findPropertyValue('xpath').toString()
	//	List<WebElement> testCaseScreens = driver.findElements(By.xpath(listScreens))
//	WebUI.takeScreenshot("GetBrokenTCErrors")
	if(WebUI.verifyElementPresent(brokenStep, 10)){
		MesmerLogUtils.logInfo("Broken test step found")
		WebUI.click(brokenStep)
		MesmerLogUtils.logInfo("Broken test step clicked")
		WebUI.delay(2)
//		WebUI.takeScreenshot("BrokenTest")

		getErrorsFromTestStepDialog()
		if (WebUI.verifyElementPresent(btnBackArrow, 10)){
			WebUI.click(btnBackArrow)
			MesmerLogUtils.logInfo("Clicked on the back arrow button on test step detail dialogue")
		}else{
			MesmerLogUtils.logInfo("Could not click on the back arrow button on test step detail dialogue")
		}
		WebUI.delay(2)
	}else {
		MesmerLogUtils.logInfo("No broken step found in the test steps")
	}

	if (WebUI.waitForElementPresent(mesmerEncounteredErrorStep, 10)){
		MesmerLogUtils.logInfo("Broken Div Found in Test Step")
		String brokenText = WebUI.getText(mesmerEncounteredErrorStep)
		MesmerLogUtils.markWarning("Test Case broken due to [" + brokenText + "]")
	}else{
		MesmerLogUtils.logInfo("No Broken Div found in the test step")
	}
}

def getErrorsFromTestStepDialog(){
	WebDriver driver = DriverFactory.getWebDriver()

	//	String listErrorOnScreen = findTestObject('Object Repository/OR_Replay/list_errorOnScreen').findPropertyValue('xpath').toString()
//	WebUI.takeScreenshot("getErrorsFromTestStepDialog")
	String listPointErrors = WebUI.getText(listPointErrors)
	String errorStr = "Device Errors ::"
	String otherFailureStr = "Other Errors ::"

	if (WebUI.verifyElementPresent(listPointErrors, 10)){

		List<WebElement> error = driver.findElements(By.xpath(listPointErrors))

		for (WebElement webElement : error) {
			String errorText = webElement.getText();
			if(errorText.contains("E002")){
				errorStr = errorStr + " -- [" + errorText + "]"
			} else {
				otherFailureStr = otherFailureStr + " -- [" + errorText + "]"
			}
		}

		if (errorStr.contains("E002")){
			WebUI.takeScreenshot("E002Error")
			MesmerLogUtils.markFailed(errorStr);
		}else if (otherFailureStr.contains("--")) {
			MesmerLogUtils.markWarning(otherFailureStr)
		} else {
			MesmerLogUtils.logInfo("No error found")
		}
	}else {
		MesmerLogUtils.logInfo("Could not find list of errors in Test Step Details")
	}
}

def getFailedTCErrors() {
//	WebUI.takeScreenshot("getFailedTCErrors")
	if(WebUI.waitForElementPresent(brokenStep, 20)){
		MesmerLogUtils.logInfo("Failed test step found")
		WebUI.click(brokenStep)
		MesmerLogUtils.logInfo("Failed test step clicked")

		WebUI.delay(2)
//		WebUI.takeScreenshot("FailedTest")
		getFailuresFromTestStepDialog()
		if (WebUI.verifyElementPresent(btnBackArrow, 10)){
			WebUI.click(btnBackArrow)
			MesmerLogUtils.logInfo("Clicked on the back arrow button on test step detail dialogue")
		}else{
			MesmerLogUtils.logInfo("Could not click on the cross button on test step detail dialogue")
		}
	}else {
		MesmerLogUtils.logInfo("No failed step found in the test steps")
	}
}

def getFailuresFromTestStepDialog(){
	WebDriver driver = DriverFactory.getWebDriver()

	String listFailedOnScreen = findTestObject('Object Repository/OR_Replay/list_failedOnScreen').findPropertyValue('xpath').toString()
	String failureStr = "Device Failures ::"
	String otherFailureStr = "Other Failures ::"

	if (WebUI.verifyElementPresent(findTestObject('Object Repository/OR_Replay/list_failedOnScreen'), 10)){
		List<WebElement> failed = driver.findElements(By.xpath(listFailedOnScreen))

		for (WebElement webElement : failed) {
			String failedText = webElement.getText()
			if(failedText.contains("E002")){
				failureStr = failureStr + " -- [" + failedText + "]"
			} else {
				otherFailureStr = otherFailureStr + " -- [" + failedText + "]"
			}
		}

		if (failureStr.contains("E002")){
			MesmerLogUtils.markFailed(failureStr);
		}else if (otherFailureStr.contains("--")) {
			MesmerLogUtils.markWarning(otherFailureStr)
		} else {
			MesmerLogUtils.logInfo("No failures found")
		}
	} else {
		MesmerLogUtils.logInfo("Could not find list of failures in Test Step Details")
	}
}