package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import java.time.Instant


public class ReplayController {

	private static ReplayController mInstance = null

	private ReplayController(){
	}

	public static ReplayController getInstance(){
		if(mInstance == null){
			mInstance = new ReplayController()
		}
		return mInstance
	}


	public boolean stopATestCase(){
		boolean result = false
		TestObject stopReplayIcon = findTestObject('Object Repository/OR_Replay/icon_stopReplay')

		if(WebUI.waitForElementVisible(stopReplayIcon, 60)){
			MesmerLogUtils.logInfo("Stop Replay Icon is displayed. Now Clicking...")
			WebUI.click(stopReplayIcon)
			WebUI.delay(1)
			TestObject stopReplayBtn = findTestObject('Object Repository/OR_Replay/btn_stopReplay')
			if(WebUI.waitForElementVisible(stopReplayBtn, 30)){
				MesmerLogUtils.logInfo("Stop Replay button is displayed. Now clicking...")
				WebUI.click(stopReplayBtn)
				MesmerLogUtils.logInfo("Stop button is clicked")
				TestObject stopProgress = findTestObject('Object Repository/OR_Replay/inprogress_status')
				if(WebUI.waitForElementNotPresent(stopProgress, 60)){
					MesmerLogUtils.logInfo("Test case is stopped successfully")
					WebUI.delay(5)
					result = true
				}
				else{
					MesmerLogUtils.logInfo("Test case is not stopped successfully")
				}
			}
			else{
				MesmerLogUtils.logInfo("Stop Replay Button is not dispalyed")
			}
		}
		else{
			MesmerLogUtils.logInfo("Stop replay icon is not displayed")
		}
		return result
	}


	public boolean clickOnStopTestCaseIcon(){
		boolean result = false
		TestObject stopReplayIcon = findTestObject('Object Repository/OR_Replay/icon_stopReplay')

		if(WebUI.waitForElementVisible(stopReplayIcon, 60)){
			MesmerLogUtils.logInfo("Stop Replay Icon is displayed. Now Clicking...")
			WebUI.click(stopReplayIcon)
			WebUI.delay(1)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Stop replay icon is not displayed")
		}
		return result
	}

	public boolean confirmationDialogueStopTestCase(){
		boolean result = false
		TestObject verifyConfirmationDialogue = findTestObject('Object Repository/OR_TestDetails/confirmation_stopTestCase')

		if(WebUI.waitForElementVisible(verifyConfirmationDialogue, 60)){
			MesmerLogUtils.logInfo("Confirmation msg appears on clicking stop replay icon")
			WebUI.delay(1)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("No confirmation msg appears on clicking stop replay icon")
		}
		return result
	}

	public boolean clickOnCancelStopTestCase(){
		boolean result = false
		TestObject cancelBtn = findTestObject('Object Repository/OR_TestDetails/btn_cancelStop')

		if(WebUI.waitForElementVisible(cancelBtn, 60)){
			WebUI.click(cancelBtn)
			WebUI.delay(1)
			MesmerLogUtils.logInfo("Clicked on cancel button")
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Could not click on cancel button")
		}
		return result
	}

	public boolean clickOnYesStopTestCase(){
		boolean result = false
		TestObject yesBtn = findTestObject('Object Repository/OR_Replay/btn_stopReplay')

		if(WebUI.waitForElementVisible(yesBtn, 60)){
			WebUI.click(yesBtn)
			WebUI.delay(1)
			MesmerLogUtils.logInfo("Clicked on stop button")
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Could not click on stop button")
		}
		return result
	}

	public boolean stopTestCaseFromTestResult(){
		boolean result = false

		TestObject stopReplayBtn = findTestObject('Object Repository/OR_Replay/button_stopReplayTestResult')
		if(WebUI.waitForElementPresent(stopReplayBtn, 20)){
			MesmerLogUtils.logInfo("Stop Replay button is displayed. Now clicking...")
			WebUI.click(stopReplayBtn)
			MesmerLogUtils.logInfo("Stop button is clicked")
			TestObject btnYes = findTestObject('Object Repository/OR_Replay/btn_Yes')
			if(WebUI.waitForElementPresent(btnYes, 20)){
				WebUI.click(btnYes)
				MesmerLogUtils.logInfo("Yes button is clicked")
				result = true
			}
			else{
				MesmerLogUtils.logInfo("Could not click on Yes button")
			}
		}
		else{
			MesmerLogUtils.markFailed("Stop Replay Button is not dispalyed")
		}

		return result
	}

	public boolean verifyExecutionStopped(){
		boolean result = false
		TestObject stopProgress = findTestObject('Object Repository/OR_Replay/executionProgress')
		if(WebUI.waitForElementNotPresent(stopProgress, 60)){
			MesmerLogUtils.logInfo("Test case is stopped successfully")
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Test case is not stopped successfully")
		}
		return result
	}

	public boolean checkReplayWatchVideo(String imagePath, String serialNo){
		boolean result = false

		WebUI.delay(20)
		Instant instant = Instant.now();
		TestObject watchVideoOption = findTestObject('Object Repository/OR_Replay/NewUIXpaths/button_watchVideoNew')
		if(WebUI.waitForElementVisible(watchVideoOption, 60)) {
			MesmerLogUtils.markPassed("Watch Video button is displayed")
			Utility.takeScreenshot(imagePath, "WatchVideoOption"+serialNo)
			MesmerLogUtils.logInfo("Clicking Watch Video button")
			WebUI.click(watchVideoOption)

			MesmerLogUtils.logInfo("Watch Video button is clicked")

			TestObject errorVideoNotFound = findTestObject('Object Repository/OR_Replay/NewUIXpaths/notification_noVideoFound')

			if(WebUI.waitForElementVisible(errorVideoNotFound, 5)){
				Utility.takeScreenshot(imagePath, "NoVideoFoundError"+serialNo)
				MesmerLogUtils.markFailed("Video is not found after clicking PLAY button on completing a test case")
				WebUI.delay(8)
			}
			else{

				TestObject titleVideo = findTestObject('Object Repository/OR_Replay/title_Video')
				if(WebUI.waitForElementVisible(titleVideo, 5)){
					MesmerLogUtils.logInfo("Title Video dialog is displayed")
					WebUI.delay(5)
					TestObject playBtn = findTestObject('Object Repository/OR_Replay/VideoVerification/btn_playVideo')

					//findTestObject('Object Repository/OR_Replay/btn_VideoPlay')
					if(WebUI.waitForElementVisible(playBtn, 5)){
						MesmerLogUtils.logInfo("Play button is displayed. Now clicking play button...")
						WebUI.click(playBtn)
						MesmerLogUtils.logInfo("Play button is clicked")
						Utility.takeScreenshot(imagePath, "PlayBtnClicked"+serialNo)
						TestObject videoCompleted = findTestObject('Object Repository/OR_Replay/VideoVerification/video_100PercentCompleted')
						TestObject closeBtn = findTestObject('Object Repository/OR_Replay/button_Close')
						WebUI.delay(2)
						TestObject video0Percent = findTestObject('Object Repository/OR_Replay/VideoVerification/video_0percentCompleted')
						if(WebUI.verifyElementNotPresent(video0Percent, 5)){
							MesmerLogUtils.markPassed("Video is started successfully")
							if(!imagePath.isEmpty()){
								Utility.takeScreenshot(imagePath, "VideoCompletedSuccessfully"+serialNo)
							}
							MesmerLogUtils.logInfo("Clicking Close button")
							WebUI.click(closeBtn)
							MesmerLogUtils.logInfo("Close button is clicked")

							result = true
						}
						else{
							MesmerLogUtils.markFailed("Video is not started")
							MesmerLogUtils.logInfo("Clicking Close button")
							WebUI.click(closeBtn)
							MesmerLogUtils.logInfo("Close button is clicked")
						}

					}
					else{
						MesmerLogUtils.logInfo("Play button is not displayed")
					}


				}else{
					MesmerLogUtils.logInfo("Title Video dialog is not displayed")
				}

			}

		}else{
			MesmerLogUtils.markFailed("Watch Video button is not displayed")
		}


		return result
	}

	public boolean verifyReplayWatchVideo(){
		boolean result = false

		WebUI.delay(20)
		Instant instant = Instant.now();
		TestObject watchVideoOption = findTestObject('Object Repository/OR_Replay/NewUIXpaths/button_watchVideoNew')
		if(WebUI.waitForElementVisible(watchVideoOption, 60)) {
			MesmerLogUtils.markPassed("Watch Video button is displayed")
			WebUI.takeScreenshot("WatchVideoOption")
			MesmerLogUtils.logInfo("Clicking Watch Video button")
			WebUI.click(watchVideoOption)

			MesmerLogUtils.logInfo("Watch Video button is clicked")

			TestObject errorVideoNotFound = findTestObject('Object Repository/OR_Replay/NewUIXpaths/notification_noVideoFound')

			if(WebUI.waitForElementVisible(errorVideoNotFound, 5)){
				WebUI.takeScreenshot("NoVideoFoundError")
				MesmerLogUtils.markFailed("Video is not found after clicking PLAY button on completing a test case")
				WebUI.delay(8)
			}
			else{

				TestObject titleVideo = findTestObject('Object Repository/OR_Replay/title_Video')
				if(WebUI.waitForElementVisible(titleVideo, 5)){
					MesmerLogUtils.logInfo("Title Video dialog is displayed")
					WebUI.delay(5)
					TestObject playBtn = findTestObject('Object Repository/OR_Replay/VideoVerification/btn_playVideo')

					//findTestObject('Object Repository/OR_Replay/btn_VideoPlay')
					if(WebUI.waitForElementVisible(playBtn, 5)){
						MesmerLogUtils.logInfo("Play button is displayed. Now clicking play button...")
						WebUI.click(playBtn)
						MesmerLogUtils.logInfo("Play button is clicked")
						WebUI.takeScreenshot("PlayBtnClicked")
						TestObject videoCompleted = findTestObject('Object Repository/OR_Replay/VideoVerification/video_100PercentCompleted')
						TestObject closeBtn = findTestObject('Object Repository/OR_Replay/button_Close')
						WebUI.delay(2)
						TestObject video0Percent = findTestObject('Object Repository/OR_Replay/VideoVerification/video_0percentCompleted')
						if(WebUI.verifyElementNotPresent(video0Percent, 5)){
							MesmerLogUtils.markPassed("Video is started successfully")
							MesmerLogUtils.logInfo("Clicking Close button")
							WebUI.click(closeBtn)
							MesmerLogUtils.logInfo("Close button is clicked")

							result = true
						}
						else{
							MesmerLogUtils.markFailed("Video is not started")
							MesmerLogUtils.logInfo("Clicking Close button")
							WebUI.click(closeBtn)
							MesmerLogUtils.logInfo("Close button is clicked")
						}

					}
					else{
						MesmerLogUtils.logInfo("Play button is not displayed")
					}


				}else{
					MesmerLogUtils.logInfo("Title Video dialog is not displayed")
				}

			}

		}else{
			MesmerLogUtils.logInfo("Watch Video button is not displayed")
		}


		return result
	}

	//delete all Replay executions by selecting all test cases and click Delete option
	public boolean deleteAllReplayExecution(String imagePath, String serNo){
		boolean result = false

		TestObject noTestRuns = findTestObject('Object Repository/OR_Replay/NewUIXpaths/info_noTestRuns')


		if(WebUI.waitForElementVisible(noTestRuns, 2)){
			MesmerLogUtils.logInfo("No Test Run entry...")
		}
		else{
			TestObject selectAllReplayOption = findTestObject('Object Repository/OR_Replay/NewUIXpaths/btn_selectAllReplayTCs')
			if(WebUI.waitForElementVisible(selectAllReplayOption, 2)){
				MesmerLogUtils.logInfo("Select ALL Replay Iterations option is displayed. Now Clicking...")
				WebUI.click(selectAllReplayOption)
				MesmerLogUtils.logInfo("Select All Replay Iterations option is clicked")
				WebUI.delay(1)


				TestObject deleteAllTCs = findTestObject('Object Repository/OR_Replay/NewUIXpaths/btn_deleteReplayTCs')
				if(WebUI.waitForElementVisible(deleteAllTCs, 2)){
					MesmerLogUtils.logInfo("Delete All Replay Iterations option is displayed. Now clicking...")
					if(!imagePath.isEmpty()){

						Utility.takeScreenshot(imagePath, "BeforeClickingDeleteButton"+serNo)
					}
					WebUI.click(deleteAllTCs)
					MesmerLogUtils.logInfo("Delete All option is clicked")
					WebUI.delay(2)

				}
				else{
					MesmerLogUtils.logInfo("Delete all Replay Iterations option is not displayed")

				}

			}
			else{
				MesmerLogUtils.logInfo("Select All Replay Iterations option is not displayed")
			}

		}

	}

	//delete a single iteration
	public boolean deleteReplayExecution(String imagePath){
		boolean result = false

		TestObject testRunControlPanel = findTestObject('Object Repository/OR_Replay/NewUIXpaths/panel_testRunControlPanel')
		if(WebUI.waitForElementVisible(testRunControlPanel, 5)){
			MesmerLogUtils.logInfo("Test Run Control Panel is displayed")
			WebUI.mouseOver(testRunControlPanel)
			WebUI.delay(1)
		}

		TestObject optionMenu = findTestObject('Object Repository/OR_Replay/NewUIXpaths/option_ResultOptionMenu')
		if(WebUI.waitForElementVisible(optionMenu, 10)){
			MesmerLogUtils.logInfo("Option menu button is displayed. Now clicking")
			WebUI.click(optionMenu)
			MesmerLogUtils.logInfo("Option menu button is clicked")
			WebUI.delay(1)
			Utility.takeScreenshot(imagePath, "OptionMenuClicked")

			TestObject removeTestRunBtn = findTestObject('Object Repository/OR_Replay/NewUIXpaths/button_RemoveTestRun')
			if(WebUI.waitForElementVisible(removeTestRunBtn, 10)){
				MesmerLogUtils.logInfo("Remove button is displayed")
				WebUI.click(removeTestRunBtn)
				MesmerLogUtils.logInfo("Remove Test Run button is clicked")
				WebUI.delay(1)

				TestObject yesButton = findTestObject('Object Repository/OR_Replay/NewUIXpaths/button_YesConfirmation')
				if(WebUI.waitForElementVisible(yesButton, 10)){
					MesmerLogUtils.logInfo("Yes button is displayed. Now Clicking...")
					WebUI.click(yesButton)
					WebUI.delay(1)
					MesmerLogUtils.logInfo("Yes button is clicked")
					result = true

				}
				else{
					MesmerLogUtils.logInfo("Yes button is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Remove Test Run button is not displayed")
			}

		}
		else{
			MesmerLogUtils.logInfo("Option menu button is not displayed")
		}

		return result

	}


	public boolean deleteReplayTC(){

		boolean result = false
		TestObject threeDotBtn = findTestObject('Object Repository/OR_Replay/btn_Options')

		TestObject deleteBtn = findTestObject('Object Repository/OR_Replay/btn_Delete')

		TestObject yesBtn = findTestObject('Object Repository/OR_Replay/btn_Yes')

		TestObject titleTestResult = findTestObject('Object Repository/OR_Obsolete/OR_Comments/testResultTextTiltle')

		if(WebUI.waitForElementVisible(threeDotBtn, 5)){
			MesmerLogUtils.logInfo("Three dot button is displayed. Now clicking...")
			WebUI.click(threeDotBtn)
			MesmerLogUtils.logInfo("Three dot button is clicked")
			if(WebUI.waitForElementVisible(deleteBtn, 5)){
				MesmerLogUtils.logInfo("Delete button is displayed. Now clicking...")
				WebUI.click(deleteBtn)
				MesmerLogUtils.logInfo("Delete button is clicked")

				if(WebUI.waitForElementVisible(yesBtn, 0)){
					MesmerLogUtils.logInfo("Yes button is displayed. Now clicking...")
					WebUI.click(yesBtn)
					MesmerLogUtils.logInfo("Yes button is clicked")

					if(WebUI.waitForElementVisible(titleTestResult, 5)){
						MesmerLogUtils.logInfo("Test case is deleted...")
						result = true
					}
					else{
						MesmerLogUtils.logInfo("Test case is not deleted")
					}
				}
				else{
					MesmerLogUtils.logInfo("Yes button is not displayed.")
				}
			}
			else{
				MesmerLogUtils.logInfo("Delete button is not displayed")
			}
		}
		else{
			MesmerLogUtils.logInfo("Three dot button is not displayed on Replay page")
		}
		return result
	}

	public void getBrokenTCErrors() {
		//	WebDriver driver = DriverFactory.getWebDriver()
		//	String listScreens = findTestObject('Object Repository/OR_Replay/list_testCaseScreens').findPropertyValue('xpath').toString()
		//	List<WebElement> testCaseScreens = driver.findElements(By.xpath(listScreens))

		if(WebUI.verifyElementPresent(brokenStep, 10)){
			MesmerLogUtils.logInfo("Broken test step found")
			WebUI.click(brokenStep)
			MesmerLogUtils.logInfo("Broken test step clicked")
			WebUI.delay(2)

			getErrorsFromTestStepDialog()
			if (WebUI.verifyElementPresent(btnClose, 10)){
				WebUI.click(btnClose)
				MesmerLogUtils.logInfo("Clicked on the cross button on test step detail dialogue")
			}else{
				MesmerLogUtils.logInfo("Could not click on the cross button on test step detail dialogue")
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

	public void getErrorsFromTestStepDialog(){
		WebDriver driver = DriverFactory.getWebDriver()

		//	String listErrorOnScreen = findTestObject('Object Repository/OR_Replay/list_errorOnScreen').findPropertyValue('xpath').toString()

		String listPointErrors = findTestObject('Object Repository/OR_Replay/list_errorPoint').findPropertyValue('xpath').toString()
		String errorStr = "Device Errors ::"
		String otherFailureStr = "Other Errors ::"

		if (WebUI.verifyElementPresent(findTestObject('Object Repository/OR_Replay/list_errorPoint'), 10)){

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

	public void getFailedTCErrors() {
		if(WebUI.waitForElementPresent(txtFailed, 20)){
			MesmerLogUtils.logInfo("Failed test step found")
			WebUI.click(txtFailed)
			MesmerLogUtils.logInfo("Failed test step clicked")

			WebUI.delay(2)
			getFailuresFromTestStepDialog()
			if (WebUI.verifyElementPresent(btnClose, 10)){
				WebUI.click(btnClose)
				MesmerLogUtils.logInfo("Clicked on the cross button on test step detail dialogue")
			}else{
				MesmerLogUtils.logInfo("Could not click on the cross button on test step detail dialogue")
			}
		}else {
			MesmerLogUtils.logInfo("No failed step found in the test steps")
		}
	}

	public void getFailuresFromTestStepDialog(){
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


	public boolean performOperations(String action){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject btnOption = findTestObject('Object Repository/OR_Replay/btn_Options')

		if(WebUI.waitForElementVisible(btnOption, 60)){
			WebUI.click(btnOption)


			if(action == "Duplicate"){
				TestObject btnDuplicate = findTestObject('Object Repository/OR_Replay/btn_Duplicate')
				if(WebUI.waitForElementVisible(btnDuplicate, 20)){
					WebUI.click(btnDuplicate)

				}else{
					MesmerLogUtils.markFailed("Could not click on Duplicate button")
				}

			}else if (action == "Download Results"){

				TestObject clearLogs = findTestObject('Object Repository/OR_AppMap/option_ClearLog')
				if(WebUI.waitForElementVisible(clearLogs, 20)){
					WebUI.click(clearLogs)

				}
			}
			else if (action == "Download Log"){
				TestObject downloadLogs = findTestObject('Object Repository/OR_AppMap/option_downloadLogs')
				if(WebUI.waitForElementVisible(downloadLogs, 20)){
					WebUI.click(downloadLogs)
					result = true
					MesmerLogUtils.markPassed("Logs are downloaded successfully")
				}
				else{
					MesmerLogUtils.markFailed("Download Logs option is not displayed")
				}
			}

		}else{
			MesmerLogUtils.logInfo("Could not click on three dot button")
		}


		return result
	}

	public boolean selectDevice(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String deviceList = findTestObject('Object Repository/OR_Replay/deviceList').findPropertyValue('xpath').toString()
		List<WebElement> device = driver.findElements(By.xpath(deviceList))

		TestObject deviceObj = findTestObject('Object Repository/OR_Replay/deviceList')

		WebUI.delay(10)
		if(WebUI.waitForElementPresent(deviceObj, 10)){
			if(device != null && device.size() > 0){

				String readyDeviceListXpath = findTestObject('Object Repository/OR_Replay/list_readyDeviceList').findPropertyValue('xpath').toString()
				List<WebElement> readyDeviceList = driver.findElements(By.xpath(readyDeviceListXpath))

				String provisionedDeviceListXpath = findTestObject('Object Repository/OR_Replay/list_DeviceListProv').findPropertyValue('xpath').toString()
				List<WebElement> provisionedDeviceList = driver.findElements(By.xpath(provisionedDeviceListXpath))

				String inUseDeviceListXpath = findTestObject('Object Repository/OR_Replay/list_DeviceInUse').findPropertyValue('xpath').toString()
				List<WebElement> inUseDeviceList = driver.findElements(By.xpath(inUseDeviceListXpath))

				if(readyDeviceList != null && readyDeviceList.size() > 0){
					readyDeviceList.get(0).click()
					MesmerLogUtils.logInfo("Clicked on Ready device")
					result = true

				}else if (provisionedDeviceList != null && provisionedDeviceList.size() > 0){
					provisionedDeviceList.get(0).click()
					MesmerLogUtils.logInfo("Clicked on Provisioned device")
					result = true


				}else if (inUseDeviceList != null && inUseDeviceList.size() > 0){
					inUseDeviceList.get(0).click()
					MesmerLogUtils.logInfo("Clicked on In-Use device")
					result = true
				}else{
					MesmerLogUtils.logInfo("Could not clicked on any device")
				}
			}else{
				MesmerLogUtils.markFailed("No devices available")
			}
		}else{
			MesmerLogUtils.markFailed("Device list not fetched")
		}
		return result
	}

	public boolean selectInUseDevice(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		TestObject inUseDeviceObj = findTestObject('Object Repository/OR_Replay/list_DeviceInUse')

		WebUI.delay(10)
		if(WebUI.waitForElementPresent(inUseDeviceObj, 10)){

			String inUseDeviceListXpath = findTestObject('Object Repository/OR_Replay/list_DeviceInUse').findPropertyValue('xpath').toString()
			List<WebElement> inUseDeviceList = driver.findElements(By.xpath(inUseDeviceListXpath))

			if (inUseDeviceList != null && inUseDeviceList.size() > 0){
				inUseDeviceList.get(0).click()
				MesmerLogUtils.logInfo("Clicked on In-Use device")
				result = true
			}else{
				MesmerLogUtils.markFailed("Could not click on In-Use device")
			}
		}else{
			MesmerLogUtils.markFailed("No in-use device available")
		}
		return result
	}

	public boolean availableDeviceListTestDetails(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject deviceObj = findTestObject('Object Repository/OR_TestDetails/list_deviceList')

		if(WebUI.waitForElementPresent(deviceObj, 10)){
			WebUI.delay(5)

			String deviceList = findTestObject('Object Repository/OR_TestDetails/list_deviceList').findPropertyValue('xpath').toString()
			List<WebElement> device = driver.findElements(By.xpath(deviceList))

			if(device != null && device.size() > 0){

				WebUI.delay(5)
				String readyDeviceListXpath = findTestObject('Object Repository/OR_TestDetails/list_readyDevices').findPropertyValue('xpath').toString()
				List<WebElement> readyDeviceList = driver.findElements(By.xpath(readyDeviceListXpath))

				if( readyDeviceList != null && readyDeviceList.size() > 0 ){

					MesmerLogUtils.logInfo("Available Ready devices  " + " : " + "  "  +  readyDeviceList.size())

				}else{
					MesmerLogUtils.logInfo("No ready devices available")
				}

				String provisionedDeviceListXpath = findTestObject('Object Repository/OR_TestDetails/list_provisionedDevices').findPropertyValue('xpath').toString()
				List<WebElement> provisionedDeviceList = driver.findElements(By.xpath(provisionedDeviceListXpath))

				if(provisionedDeviceList != null && provisionedDeviceList.size() > 0 ){

					MesmerLogUtils.logInfo("Available Provisioned devices  " + " : " + "  "  +  provisionedDeviceList.size())

				}else{
					MesmerLogUtils.logInfo("No provisioned devices available")
				}

				String inUseDeviceListXpath = findTestObject('Object Repository/OR_TestDetails/list_inUseDevice').findPropertyValue('xpath').toString()
				List<WebElement> inUseDeviceList = driver.findElements(By.xpath(inUseDeviceListXpath))

				if(inUseDeviceList != null && inUseDeviceList.size() > 0 ){

					MesmerLogUtils.logInfo("In-Use devices Count  " + " : " + "  "  +  inUseDeviceList.size())

				}else{
					MesmerLogUtils.logInfo("No in-use devices available")
				}

				String unavailableDeviceListXpath = findTestObject('Object Repository/OR_TestDetails/list_unavailableDevice').findPropertyValue('xpath').toString()
				List<WebElement> unavailableDeviceList = driver.findElements(By.xpath(unavailableDeviceListXpath))

				if(unavailableDeviceList != null && unavailableDeviceList.size() > 0 ){

					MesmerLogUtils.logInfo("Unavailable devices Count  " + " : " + "  "  +  unavailableDeviceList.size())

				}else{
					MesmerLogUtils.logInfo("No unavailable devices available")
				}
				result = true
			}else{
				MesmerLogUtils.markFailed("No devices available")
			}
		}else{
			MesmerLogUtils.markFailed("Device list not fetched")
		}
		return result
	}

	public boolean clickOnReplayButtonTestDetail(){
		boolean result = false
		TestObject btnRerun = findTestObject('Object Repository/OR_TestDetails/btn_reRun')

		if(WebUI.waitForElementPresent(btnRerun, 20)){
			WebUI.click(btnRerun)
			MesmerLogUtils.logInfo("Cliked on Re-run button")
			result = true
		}else{
			MesmerLogUtils.markFailed("Could not click on Re-run button")
		}
		return result
	}

	public boolean clickOnRunButton(){
		boolean result = false
		TestObject btnRun = findTestObject('Object Repository/OR_TestDetails/btn_Run')

		if(WebUI.waitForElementPresent(btnRun, 20)){
			WebUI.click(btnRun)
			MesmerLogUtils.logInfo("Clicked on run button")
			result = true
		}else{
			MesmerLogUtils.markFailed("Could not click on run button")
		}
		return result
	}

	public boolean confirmationRunningTestCase(){
		boolean result = false
		TestObject btnYes = findTestObject('Object Repository/OR_TestDetails/btn_Yes')

		if(WebUI.waitForElementPresent(btnYes, 20)){
			WebUI.click(btnYes)
			MesmerLogUtils.logInfo("Clicked on Yes button")
			result = true
		}else{
			MesmerLogUtils.markFailed("Could not click on yes button")
		}
		return result
	}

	public boolean clickOnReplayTestResults(){
		boolean result = false
		TestObject btnReplay = findTestObject('Object Repository/OR_TestDetails/btn_Replay')

		if(WebUI.waitForElementPresent(btnReplay, 20)){
			WebUI.click(btnReplay)
			MesmerLogUtils.markPassed("Clicked on Replay button")
			result = true
		}else{
			MesmerLogUtils.markFailed("Could not click on replay button")
		}
		return result
	}

	public boolean selectAllDevices(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()


		TestObject deviceObj = findTestObject('Object Repository/OR_TestDetails/list_deviceList')

		WebUI.delay(5)
		if(WebUI.waitForElementPresent(deviceObj, 10)){
			WebUI.delay(5)
			String readyDeviceListXpath = findTestObject('Object Repository/OR_TestDetails/list_readyDevices').findPropertyValue('xpath').toString()
			List<WebElement> readyDeviceList = driver.findElements(By.xpath(readyDeviceListXpath))

			if(readyDeviceList != null && readyDeviceList.size() > 0){
				for (WebElement deviceElement : readyDeviceList) {
					deviceElement.click();
					WebUI.delay(5)
					result = true
				}

			}else{
				MesmerLogUtils.markFailed("No devices available")
			}
		}else{
			MesmerLogUtils.markFailed("Device list not fetched")
		}
		return result
	}

	public boolean testResultInProgress(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		//		String tileInProgressXpath = findTestObject('Object Repository/OR_Replay/tile_inProgressTile').findPropertyValue('xpath').toString()
		//		List<WebElement> tileInProgress = driver.findElements(By.xpath(tileInProgressXpath))

		TestObject tileInProgressObj = findTestObject('Object Repository/OR_Replay/tile_inProgressTile')

		if(WebUI.waitForElementPresent(tileInProgressObj, 120)){
			MesmerLogUtils.logInfo("Test case in-progress")
			result = true
		}else{
			MesmerLogUtils.markFailed("No test case in-progress")
		}
		return result
	}

	public boolean testResultInQueue(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		//		String tileInQueueXpath = findTestObject('Object Repository/OR_Replay/tile_Inqueue').findPropertyValue('xpath').toString()
		//		List<WebElement> tileInQueue = driver.findElements(By.xpath(tileInQueueXpath))

		TestObject tileInQueueObj = findTestObject('Object Repository/OR_Replay/tile_Inqueue')

		if(WebUI.waitForElementPresent(tileInQueueObj, 120)){
			MesmerLogUtils.logInfo("Test case in-queue")
			result = true
		}else{
			MesmerLogUtils.markFailed("No test case in-queue")
		}
		return result
	}

	public boolean openPassedTestCase(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String passedTileXpath = findTestObject('Object Repository/OR_Replay/tile_Passed').findPropertyValue('xpath').toString()
		List<WebElement> passedTile = driver.findElements(By.xpath(passedTileXpath))

		if(passedTile.size() > 0 ){
			MesmerLogUtils.logInfo("Number of Passed test cases in a project " + "  :  " + passedTile.size())

			passedTile.get(0).click()
			MesmerLogUtils.logInfo("Clicked on Passed test case")
			result = true
		}else{
			MesmerLogUtils.markFailed("Could not click on Passed test case")
		}
		return result
	}

	public boolean openFailedTestCase(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String failedTileXpath = findTestObject('Object Repository/OR_Replay/tile_failed').findPropertyValue('xpath').toString()
		List<WebElement> failedTile = driver.findElements(By.xpath(failedTileXpath))

		if(failedTile.size() > 0 ){
			MesmerLogUtils.logInfo("Number of Failed test cases in a project " + "  :  " + failedTile.size())

			failedTile.get(0).click()
			MesmerLogUtils.logInfo("Clicked on Failed test case")
			result = true
		}else{
			MesmerLogUtils.markFailed("Could not click on Failed test case")
		}
		return result
	}

	public boolean openBrokenTestCase(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String brokenTileXpath = findTestObject('Object Repository/OR_Replay/tile_broken').findPropertyValue('xpath').toString()
		List<WebElement> brokenTile = driver.findElements(By.xpath(brokenTileXpath))

		if(brokenTile.size() > 0 ){
			MesmerLogUtils.logInfo("Number of Broken test cases in a project " + "  :  " + brokenTile.size())

			brokenTile.get(0).click()
			MesmerLogUtils.logInfo("Clicked on Broken test case")
			result = true
		}else{
			MesmerLogUtils.markFailed("Could not click on Broken test case")
		}
		return result
	}

	public boolean openInProgressTestCase(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String inProgressTileXpath = findTestObject('Object Repository/OR_Replay/tile_inProgressTile').findPropertyValue('xpath').toString()
		List<WebElement> inProgressTile = driver.findElements(By.xpath(inProgressTileXpath))

		if(inProgressTile.size() > 0 ){
			MesmerLogUtils.logInfo("Number of In-Progress test cases in a project " + "  :  " + inProgressTile.size())

			inProgressTile.get(0).click()
			MesmerLogUtils.logInfo("Clicked on In-Progress test case")
			result = true
		}else{
			MesmerLogUtils.markFailed("Could not click on In-Progress test case")
		}
		return result
	}

	public boolean checkNumberOfTestCasesInTestResult(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String testCaseTileXpath = findTestObject('Object Repository/OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
		List<WebElement> testCaseTile = driver.findElements(By.xpath(testCaseTileXpath))

		if(testCaseTile.size() > 0 ){
			MesmerLogUtils.logInfo("Number of test cases in a project " + "  :  " + testCaseTile.size())
			result = true
		}else{
			MesmerLogUtils.markFailed("No test case found in a Project")
		}
		return result
	}

	public boolean msgQueue(){
		boolean result = false
		TestObject queueMsg = findTestObject('Object Repository/OR_Replay/msg_queue')

		if(WebUI.waitForElementVisible(queueMsg, 60)){
			MesmerLogUtils.logInfo("Queue msg appears")
			result = true
		}
		else{
			MesmerLogUtils.markFailed("No queue msg appears")
		}
		return result
	}

	public boolean deviceBusyMsg(){
		boolean result = false
		TestObject devicBusyObj = findTestObject('Object Repository/OR_TestDetails/device_busyMsg')

		if(WebUI.waitForElementVisible(devicBusyObj, 60)){
			MesmerLogUtils.logInfo("Device is busy msg appeared")
			result = true
		}
		else{
			MesmerLogUtils.markFailed("No device is busy")
		}
		return result
	}
	/**
	 * Status could be Passed, inQueue and In Progress etc...
	 */
	public boolean selectATestCaseWithStatus(String status){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String statusXPath = '//div[@class="mat-card-content"]/span[starts-with(@class,"status '+status+'")]'
		List<WebElement> testCasesList = driver.findElements(By.xpath(statusXPath))
		if(testCasesList != null && testCasesList.size() > 0){
			testCasesList.get(0).click()
			result = true
			WebUI.delay(2)
		}
		return result
	}
}