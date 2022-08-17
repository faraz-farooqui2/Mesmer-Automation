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

//String NewReplayPath = ""
int SN = Integer.parseInt(SrNumber)

if (SN <= 100){
	NewReplayPath = ReplayPath + "user01" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewReplayPath = " + NewReplayPath)
} else if (SN > 100 && SN <= 200){
	NewReplayPath = ReplayPath + "user02" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewReplayPath = " + NewReplayPath)
} else if (SN > 200 && SN <= 300){
	NewReplayPath = ReplayPath + "user03" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewReplayPath = " + NewReplayPath)
} else if (SN > 300 && SN <= 400){
	NewReplayPath = ReplayPath + "user04" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewReplayPath = " + NewReplayPath)
}
else if (SN > 400 && SN <= 500){
	NewReplayPath = ReplayPath + "user05" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewReplayPath = " + NewReplayPath)
}
else if (SN > 500 && SN <= 600){
	NewReplayPath = ReplayPath + "user06" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewReplayPath = " + NewReplayPath)
}
else if (SN > 600 && SN <= 700){
	NewReplayPath = ReplayPath + "user07" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewReplayPath = " + NewReplayPath)
}


Utility.logCurrentUTCTime("Time before selecting project")
MesmerLogUtils.logInfo("Replay URL before selecting project -- " + WebUI.getUrl())
Utility.takeScreenshot(NewReplayPath, SrNumber +"-BeforeSelectingProject")
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.takeScreenshot(NewReplayPath, SrNumber +"-AfterSelectingProject")
MesmerLogUtils.logInfo("Replay URL after selecting project -- " + WebUI.getUrl())
Utility.logCurrentUTCTime("Time after selecting project and before navigating to Test Results")

Utility.setPlatformName(platformName)

Utility.takeScreenshot(NewReplayPath, SrNumber +"-BeforeGoingToTestResults")
CustomKeywords.'com.mesmer.Utility.goToTestResults'()
Utility.takeScreenshot(NewReplayPath, SrNumber +"-AfterGoingToTestResults")

Utility.logCurrentUTCTime("Time after navigating to Test Results and before opening the test case")

WebDriver driver = DriverFactory.getWebDriver()

try{

	Boolean tcVerified = false
	Boolean isReplayStarted = false

	if (openTestCase()){
		MesmerLogUtils.logInfo("Replay URL before replaying a testcase -- " + WebUI.getUrl())
		Utility.takeScreenshot(NewReplayPath, SrNumber +"-AfterOpeningTestCase")
		Utility.logCurrentUTCTime("Time after opening the test case")

		ReplayController.getInstance().deleteAllReplayExecution(NewReplayPath, SrNumber)
		ReplayController.getInstance().stopATestCase()

		if (!performReplay(true)){
			MesmerLogUtils.logInfo("Replay failed in 1st attempt. Retrying...")
			WebUI.refresh()
			WebUI.delay(5)
			ReplayController.getInstance().deleteAllReplayExecution(NewReplayPath, SrNumber)
			WebUI.delay(5)

			if (!performReplay(false)){
				MesmerLogUtils.logInfo("Replay failed in 2nd attempt as well. Exiting...")
			} else {
				MesmerLogUtils.logInfo("Replay passed in 2nd attempt")
				tcVerified = true
			}
		} else {
			MesmerLogUtils.logInfo("Replay passed in 1st attempt")
			tcVerified = true
		}

	} else {
		MesmerLogUtils.markWarning("Could not open the test case")
	}

	Utility.takeScreenshot(NewReplayPath, SrNumber +"-TestEnded")
	Utility.logCurrentUTCTime("Test End Time")

}catch(Exception e){
	e.printStackTrace()
	Utility.takeScreenshot(NewReplayPath, SrNumber +"-InCatchBlock")
	Utility.logCurrentUTCTime("Catch Block Time" + SrNumber + " ")
	WebUI.refresh()
	WebUI.waitForPageLoad(5)

}finally{

	Utility.takeScreenshot(NewReplayPath, SrNumber +"-FinalBlock1")
	Utility.logCurrentUTCTime("Final Block Time" + SrNumber + " ")
	MesmerLogUtils.logInfo("URL in Final Block -- " + WebUI.getUrl())
	WebUI.refresh()
	if(checkInQueueAndClickCancel())
	{
		MesmerLogUtils.logInfo("Test Case is in queue in Finally block. Cancelling from Queue...")
	}else{
		MesmerLogUtils.logInfo("Test Case is not in queue in Finally block")
	}

	MesmerLogUtils.logInfo("Wait (5 seconds) for device recovery")
	Utility.takeScreenshot(NewReplayPath, SrNumber +"-FinalBlock2")

	WebUI.delay(5)

	ReplayController.getInstance().stopATestCase()
	WebUI.delay(2)
	ReplayController.getInstance().deleteAllReplayExecution(NewReplayPath, SrNumber)

	KTRequestHandler.getXHRCalls()
	KTRequestHandler.getBrowserConsoleLogs()
	KTRequestHandler.clearBrowserConsoleLogs()

}

private boolean performReplay(boolean attempt){
	boolean tcVerified = false
	String attmpt = "2nd"

	if (attempt){
		attmpt = "1st"
	}

	if(WebUI.waitForElementPresent(btnRerun, 10) == true) {
		Utility.logCurrentUTCTime("Time before clicking Replay button (" + attmpt + " Attempt)")
		WebUI.click(btnRerun)
		MesmerLogUtils.logInfo("Replay button clicked successfully (" + attmpt + " Attempt)")
		Utility.logCurrentUTCTime("Time when Replay button is clicked (" + attmpt + " Attempt)")

		WebUI.delay(2)

		Utility.takeScreenshot(NewReplayPath, SrNumber +"-AfterClickingReplayBtn" + attmpt + "")

		if(WebUI.waitForElementPresent(deviceList, 10) || WebUI.waitForElementVisible(deviceListProv, 5)){
			MesmerLogUtils.logInfo("List of Devices is displayed (" + attmpt + " Attempt)")
			Utility.logCurrentUTCTime("Time when list of device is displayed (" + attmpt + " Attempt)")

			boolean isDeviceSelected = false
			Utility.logCurrentUTCTime("Time before device selection (" + attmpt + " Attempt)")
			if(Device.toString().isEmpty()){
				isDeviceSelected = selectDevice();
			}
			else{
				isDeviceSelected = selectDevice(Device.toString());
			}
			Utility.logCurrentUTCTime("Time after device selection (" + attmpt + " Attempt)")

			if(isDeviceSelected){
				MesmerLogUtils.logInfo("Device selected successfully (" + attmpt + " Attempt)")
				Utility.takeScreenshot(NewReplayPath, SrNumber +"-AfterSelectingDevice" + attmpt + "")

				getSelectedDeviceInfo(attempt)
				if(startReplay()){
					tcVerified = verifyReplay(attempt)
				}
			} 
			else{
				Utility.takeScreenshot(NewReplayPath, SrNumber +"-NoDeviceSelection" + attmpt + "")
				MesmerLogUtils.markWarning("Could not select required Device (" + attmpt + " Attempt)")
				Utility.logCurrentUTCTime("Replay " + attmpt + " attempt end time")
			}
		} else {
			MesmerLogUtils.markFailed("No Devices found in the List (" + attmpt + " Attempt)")
			Utility.logCurrentUTCTime("Replay " + attmpt + " attempt end time")
		}
	} else {
		MesmerLogUtils.markWarning("Could not click Replay button (" + attmpt + " Attempt)")
		Utility.logCurrentUTCTime("Replay " + attmpt + " attempt end time")
	}

	return tcVerified
}

private boolean selectDevice(){
	boolean result = false
	List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList("Virtual")
	if(virtualDevicesList != null && virtualDevicesList.size() >=Integer.parseInt(minFreeDevices)){
		MesmerLogUtils.logInfo("Virtual Devices Count: "+virtualDevicesList.size())
		WebElement searchedVirtualDevice = Utility.selectDevice("Virtual")
		if(searchedVirtualDevice != null){
			MesmerLogUtils.logInfo("Virtual Device UDID: "+searchedVirtualDevice.getText())
			searchedVirtualDevice.click()
			MesmerLogUtils.logInfo("Virtual device selected")
			result = true
		}

	}
	else{
		List<WebElement> physicalDevicesList = Utility.getAvailableDevicesList("Physical")
		if(physicalDevicesList != null && physicalDevicesList.size() >=Integer.parseInt(minFreeDevices)){
			MesmerLogUtils.logInfo("Physical Devices Count: "+physicalDevicesList.size())
			WebElement searchedPhysicalDevice = Utility.selectDevice("Physical")
			if(searchedPhysicalDevice != null){
				MesmerLogUtils.logInfo("Physical Device UDID: "+searchedPhysicalDevice.getText())
				searchedPhysicalDevice.click()
				MesmerLogUtils.logInfo("Physical device selected")
				result = true
			}

		}
		else{
			MesmerLogUtils.markFailed("Device not available")
		}
	}
	return result
}

private boolean selectDevice(String deviceType){
	boolean result = false
	List<WebElement> devicesList = Utility.getAvailableDevicesList(deviceType)
	if(devicesList != null && devicesList.size() >=Integer.parseInt(minFreeDevices)){
		MesmerLogUtils.logInfo(deviceType + " Devices Count: "+devicesList.size())
		WebElement searchedDevice = Utility.selectDevice(deviceType)
		if(searchedDevice != null){
			searchedDevice.click()
			MesmerLogUtils.logInfo(deviceType + " Device selected")
			Utility.logCurrentUTCTime("Device selection time")
			result = true
		}else{
		MesmerLogUtils.markFailed("Serached device is null")
		}

	}
	else{
		MesmerLogUtils.markFailed("Device not available")
	}

	return result
}

def getSelectedDeviceInfo(Boolean firstAttempt) {
	WebDriver gsdDriver = DriverFactory.getWebDriver()

	String selectedDeviceOSVersion = WebUI.getText(findTestObject('OR_Replay/list_SelectedDeviceOSVersion'))
	String selectedDeviceInfo = WebUI.getText(findTestObject('OR_Replay/list_SelectedDeviceInfo'))
	String selectedDeviceStatus = WebUI.getText(findTestObject('OR_Replay/list_SelectedDeviceStatus'))
	String selectedDeviceUDID = WebUI.getText(findTestObject('Object Repository/OR_Replay/list_SelectedDeviceUDID'))

	if (firstAttempt){
		MesmerLogUtils.logInfo("Selected Device Information (1st Attempt) ::")
	} else {
		MesmerLogUtils.logInfo("Selected Device Information (2nd Attempt) ::")
	}

	MesmerLogUtils.logInfo("OS :: " + selectedDeviceOSVersion)
	MesmerLogUtils.logInfo("INFO :: " + selectedDeviceInfo)
	MesmerLogUtils.logInfo("STATUS :: " + selectedDeviceStatus)
	MesmerLogUtils.logInfo("UDID :: " + selectedDeviceUDID)

	return selectedDeviceUDID
}

private boolean startReplay(){
	boolean result = false
	Utility.logCurrentUTCTime("Time before starting the replay")
	if(WebUI.waitForElementVisible(btnRun, 10)){
		Utility.takeScreenshot(NewReplayPath, SrNumber +"-BeforeClickingRunButton")
		WebUI.click(btnRun)
		WebUI.delay(1)
		MesmerLogUtils.logInfo("Run button is clicked successfully")
		Utility.takeScreenshot(NewReplayPath, SrNumber +"-RunBtnClicked")
		if(WebUI.waitForElementPresent(btnYes, 10)){
			WebUI.click(btnYes)
			MesmerLogUtils.logInfo("Confirmation Yes button is clicked")
			Utility.logCurrentUTCTime("Time when replay confirmed")
			Utility.takeScreenshot(NewReplayPath, SrNumber +"-ConfirmationReplayYesBtn")
			result = true
			if(WebUI.waitForElementPresent(msgQueue, 10)){
				Utility.logCurrentUTCTime("Time when Test Case in Queue notification displayed")
				Utility.takeScreenshot(NewReplayPath, SrNumber +"-TestCaseinQueue")
				WebUI.delay(3)
				MesmerLogUtils.logInfo("Testcase in queue notification displayed")
			}else{
				Utility.takeScreenshot(NewReplayPath, SrNumber +"-NoInQueueMsg")
				MesmerLogUtils.markWarning("No message appeared for testcase in queue")
			}
		} else{
			Utility.takeScreenshot(NewReplayPath, SrNumber +"-NoConfirmationYesButton")
			MesmerLogUtils.markWarning("Could not click Confirmation Yes button")
		}
	}else{
		Utility.takeScreenshot(NewReplayPath, SrNumber +"-NoRunButton")
		MesmerLogUtils.markWarning("Could not click Run button")
	}
	return result
}

private boolean openTestCase(){
	boolean result = false
	TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
	TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)
	if(testCaseTitle != null){
		WebUI.click(testCaseTitle)
		result = true
		WebUI.delay(2)
		//		getUrlParams()
		MesmerLogUtils.logInfo("Navigated to test detail page")
	}
	return result
}

def verifyReplay(Boolean isFirstAttempt){
	Boolean replayVerified = true
	Utility.takeScreenshot(NewReplayPath, SrNumber +"-BeforeTestCaseGoesInProgress")
	if (WebUI.waitForElementPresent(statusInprogress, 180)){
		Utility.takeScreenshot(NewReplayPath, SrNumber +"TestCaseInProgress")
		MesmerLogUtils.logInfo("Test Case replay started")
		Utility.logCurrentUTCTime("Time when replay started")
		WebUI.delay(1)

		if (WebUI.waitForElementNotPresent(statusInprogress, 900)){
			MesmerLogUtils.logInfo("Test Case Replay completed")
			Utility.logCurrentUTCTime("Time when replay completed")

			WebUI.delay(2)
			String ResultID1 = WebUI.executeJavaScript("return localStorage.getItem('ResultID')", null)
			MesmerLogUtils.logInfo("Result ID of iteration " + SrNumber + " ---- > " + ResultID1)


			Utility.takeScreenshot(NewReplayPath, SrNumber +"-TCCompleted")
			// Call api method for test case result id
			//			getTestCaseResultID()

			if(WebUI.verifyElementPresent(getTCStatus, 10)){
				//				String statusStr = WebUI.getText(testCaseStatus)

				String testCaseStatus1 = WebUI.getAttribute(getTCStatus, 'class')
				if(testCaseStatus1 != null && !testCaseStatus1.isEmpty()){
					if(testCaseStatus1.contains("passed")){
						MesmerLogUtils.logInfo("Test case is in Passed status")
						if(ReplayController.getInstance().checkReplayWatchVideo(NewReplayPath, SrNumber))
						{
							MesmerLogUtils.markPassed("Video is replayed successfully")
						}
						else{
							MesmerLogUtils.markWarning("Video is not replayed")
						}

					}
					else if(testCaseStatus1.contains("broken")){
						MesmerLogUtils.logInfo("Test Case is in Broken Status")
						if(ReplayController.getInstance().checkReplayWatchVideo(NewReplayPath, SrNumber))
						{
							MesmerLogUtils.markPassed("Video is replayed successfully")
						}
						else{
							MesmerLogUtils.markWarning("Video is not replayed")
						}
						getBrokenTCErrors()
					}
					else if(testCaseStatus1.contains("failed")){
						MesmerLogUtils.logInfo("Test case is in Failed status")
						if(ReplayController.getInstance().checkReplayWatchVideo(NewReplayPath, SrNumber))
						{
							MesmerLogUtils.markPassed("Video is replayed successfully")
						}
						else{
							MesmerLogUtils.markWarning("Video is not replayed")
						}
						getFailedTCErrors()
					}
					else if(testCaseStatus1.contains("needs review")){
						MesmerLogUtils.logInfo("Test case is in Needs Review status")
						if(ReplayController.getInstance().checkReplayWatchVideo(NewReplayPath, SrNumber))
						{
							MesmerLogUtils.markPassed("Video is replayed successfully")
						}
						else{
							MesmerLogUtils.markWarning("Video is not replayed")
						}
					}
				}
			}
			else{
				MesmerLogUtils.markWarning("Test case status label not found")
			}
		} else {
			MesmerLogUtils.markWarning("Replay still not completed in 15 mins")
		}
	} else {


		if(isFirstAttempt){
			MesmerLogUtils.markWarning("Replay still not started in 3 mins. Cancelling the queue")
		}
		else{
			MesmerLogUtils.markFailed("Replay still not started in 3 mins. Cancelling and exiting")
		}

		if (WebUI.waitForElementVisible(btnCancelReplay, 5)==true && WebUI.waitForElementVisible(textTCinQueue, 5)==true){
			//Stop test case and Re-call Replay.
			MesmerLogUtils.logInfo("Cancel Replay link is displayed")
			WebUI.click(btnCancelReplay)
			MesmerLogUtils.logInfo("Cancel Replay link is clicked")

			if(WebUI.waitForElementVisible(stopTCConfirmationWindow, 5)==true){
				MesmerLogUtils.logInfo("Cancel Replay Confirmation dialog appeared")
				if(WebUI.waitForElementVisible(btnYes, 5)==true){
					WebUI.click(btnYes)
					MesmerLogUtils.logInfo("Cancel Replay Confirmation Yes button is clicked")
				}else{
					MesmerLogUtils.markWarning("Could not click Cancel Replay Confirmation Yes button")
				}
			}else{
				MesmerLogUtils.markWarning("Cancel Replay Confirmation dialog did not appear")
			}
		}
		replayVerified = false
	}

	return replayVerified
}


def getBrokenTCErrors() {
	//	WebDriver driver = DriverFactory.getWebDriver()
	//	String listScreens = findTestObject('Object Repository/OR_Replay/list_testCaseScreens').findPropertyValue('xpath').toString()
	//	List<WebElement> testCaseScreens = driver.findElements(By.xpath(listScreens))
	Utility.takeScreenshot(NewReplayPath, SrNumber +"-GetBrokenTCErrors")
	if(WebUI.verifyElementPresent(brokenStep, 10)){
		MesmerLogUtils.logInfo("Broken test step found")
		WebUI.click(brokenStep)
		MesmerLogUtils.logInfo("Broken test step clicked")
		WebUI.delay(2)
		Utility.takeScreenshot(NewReplayPath, SrNumber +"BrokenTest")

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
	Utility.takeScreenshot(NewReplayPath, SrNumber +"-getErrorsFromTestStepDialog")
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
			Utility.takeScreenshot(NewReplayPath, SrNumber +"-E002Error")
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
	Utility.takeScreenshot(NewReplayPath, SrNumber +"-getFailedTCErrors")
	if(WebUI.waitForElementPresent(brokenStep, 20)){
		MesmerLogUtils.logInfo("Failed test step found")
		WebUI.click(brokenStep)
		MesmerLogUtils.logInfo("Failed test step clicked")

		WebUI.delay(2)
		Utility.takeScreenshot(NewReplayPath, SrNumber +"FailedTest")
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

boolean checkInQueueAndClickCancel(){
	boolean result = false

	if (WebUI.waitForElementVisible(textTCinQueue, 2)==true){
		//Stop test case and Re-call Replay.
		MesmerLogUtils.logInfo("Cancel Queue option is displayed")
		WebUI.click(textTCinQueue)
		MesmerLogUtils.logInfo("Cancel Queue option is clicked")

		if(WebUI.waitForElementVisible(removeBtn, 5)==true){
			MesmerLogUtils.logInfo("Remove Replay button is displayed")

			WebUI.click(removeBtn)
			MesmerLogUtils.logInfo("Remove Replay button is clicked")
			WebUI.delay(2)
			result =true

		}else{
			MesmerLogUtils.markWarning("Remove Replay button did not appear")
		}
	}
	else{
		MesmerLogUtils.logInfo("Cancel Queue option is not displayed")
	}
	return result
}
