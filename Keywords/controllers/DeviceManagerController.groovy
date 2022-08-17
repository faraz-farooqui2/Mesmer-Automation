package controllers

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import org.openqa.selenium.interactions.Actions
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.mesmer.MesmerLogUtils

import internal.GlobalVariable

public class DeviceManagerController {

	private static DeviceManagerController mInstance = null

	private DeviceManagerController(){
	}

	public static DeviceManagerController getInstance(){
		if(mInstance == null){
			mInstance = new DeviceManagerController()
		}

		return mInstance
	}


	public void selectDeviceStatus(TestObject deviceStatus, String statusOption){
		String xpStatusName

		xpStatusName = deviceStatus.findPropertyValue('xpath')
		MesmerLogUtils.logInfo("" + xpStatusName)
		xpStatusName = xpStatusName.toString().replace('<pTC>', statusOption)
		MesmerLogUtils.logInfo("" + xpStatusName)

		deviceStatus.findProperty('xpath').setValue(xpStatusName)
		MesmerLogUtils.logInfo("" + deviceStatus)

		WebUI.click(deviceStatus)
		WebUI.delay(1)
	}

	public void clickOnAllFilter(){
		boolean result = false
		TestObject btnAll = findTestObject('Object Repository/OR_ManageTestCases/count_totalTestCases')
	}

	public boolean clickOnEyeIcon(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String eyeIconXpath = findTestObject('OR_DeviceManager/deviceManager_eyeIcon').findPropertyValue('xpath').toString()
		List<WebElement> eyeIcon = driver.findElements(By.xpath(eyeIconXpath))
		if(eyeIcon.size() > 0){
			eyeIcon.get(0).click()

			if(verifyDeviceQueueDialogue()){
				result= true
			}else{
				MesmerLogUtils.logInfo("Device Queue dialogue not opened")
			}
		}else{
			MesmerLogUtils.logInfo("No test case in queue")
		}
		return result
	}

	public boolean verifyDeviceQueueDialogue(){
		boolean result = false
		TestObject queueDialogue = findTestObject('Object Repository/OR_DeviceManager/deviceManager_deviceQueueDialogue')
		if(WebUI.waitForElementPresent(queueDialogue,10)){
			MesmerLogUtils.logInfo("Device Queue dialogue opened")
			result = true
		}else{
			MesmerLogUtils.logInfo("Device Queue dialogue not opened")
		}
		return result
	}
	public boolean clickOnCloseBtn(){
		boolean result = false
		TestObject closebtn = findTestObject('Object Repository/OR_DeviceManager/btn_close')
		if(WebUI.waitForElementPresent(closebtn,10)){
			WebUI.click(closebtn)
			MesmerLogUtils.logInfo("Clicked on Close button")
			result = true
		}else{
			MesmerLogUtils.logInfo("Unable to click on Close button")
		}
		return result
	}
	public boolean clickOnTasksAwaitingDeviceBtn(){
		boolean result = false
		TestObject taskAwaitingDeviceBtn = findTestObject('Object Repository/OR_DeviceManager/deviceManager_tasksAwaitingDeviceBtn')
		if(WebUI.waitForElementPresent(taskAwaitingDeviceBtn,10)){
			WebUI.click(taskAwaitingDeviceBtn)
			MesmerLogUtils.logInfo("Clicked on task awaiting device button")
			result = true
		}else{
			MesmerLogUtils.logInfo("Unable to click on task awaiting device button")
		}
		return result
	}

	public boolean verifyTasksAwaitingADeviceDialogue(){
		boolean result = false
		TestObject tasksAwaitingADeviceDialogue = findTestObject('Object Repository/OR_DeviceManager/deviceManager_tasksAwaitingADeviceDialogue')
		if(WebUI.waitForElementPresent(tasksAwaitingADeviceDialogue,10)){
			MesmerLogUtils.logInfo("Tasks awaiting a device dialogue opened")
			result = true
		}else{
			MesmerLogUtils.logInfo("Tasks awaiting a device dialogue not opened")
		}
		return result
	}

	public boolean clickOnTasksAwaitingDeviceReplayFilter(){
		boolean result = false
		TestObject replayTasksAwaitingFilter = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingADevice_replayFilter')
		if(WebUI.waitForElementPresent(replayTasksAwaitingFilter,10)){
			WebUI.click(replayTasksAwaitingFilter)
			MesmerLogUtils.logInfo("Clicked on task awaiting device replay filter")
			result = true
		}else{
			MesmerLogUtils.logInfo("Unable to click on task awaiting device replay filter")
		}
		return result
	}

	public boolean clickOnTasksAwaitingDeviceCrawlFilter(){
		boolean result = false

		TestObject crawlTasksAwaitingFilter = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingADevice_crawlFilter')
		if(WebUI.waitForElementPresent(crawlTasksAwaitingFilter,10)){
			WebUI.click(crawlTasksAwaitingFilter)
			MesmerLogUtils.logInfo("Clicked on task awaiting device crawl filter")
			result = true
		}else{
			MesmerLogUtils.logInfo("Unable to click on task awaiting device crawl filter")
		}
		return result
	}

	public boolean verifyQueueInTaskAwaitingADevice(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject noRecordAvailable = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingDevice_noRecordAvailable')
		TestObject queueDeviceAwaitingList = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingDevice_Queue')
		if(WebUI.waitForElementPresent(queueDeviceAwaitingList, 10)){
			String queueDeviceAwaitingXpath = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingDevice_Queue').findPropertyValue('xpath').toString()
			List<WebElement> queueDeviceAwaiting = driver.findElements(By.xpath(queueDeviceAwaitingXpath))
			if(queueDeviceAwaiting != null && queueDeviceAwaiting.size() > 0){
				MesmerLogUtils.logInfo("Tests awaiting for a device")
				result = true
			}else{
				MesmerLogUtils.logInfo("Queue list is null or its size is zero")
			}
		}else if (WebUI.waitForElementPresent(noRecordAvailable, 10)) {
			MesmerLogUtils.logInfo("No tests awaiting for a device")
			result = true
		}
		return result
	}

	public boolean clickOnClearQueueBtn(){
		boolean result = false
		TestObject clearQueue = findTestObject('Object Repository/OR_DeviceManager/deviceManager_clearQueueButton')
		if(WebUI.waitForElementPresent(clearQueue,10)){
			WebUI.click(clearQueue)
			MesmerLogUtils.logInfo("Clicked on clear queue button")
			result = true
		}else{
			MesmerLogUtils.logInfo("Unable to click on clear queue button")
		}
		return result
	}

	public boolean selectTestCaseFromNew(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject btnNew = findTestObject('Object Repository/OR_Replay/link_new')

		if(WebUI.waitForElementPresent(btnNew, 20)== true){
			WebUI.click(btnNew)
			WebUI.delay(2)
			MesmerLogUtils.logInfo("Clicked on New filter")

			String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
			List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))
			if(titleStreamList != null && titleStreamList.size() > 0 ){

				String checkBoxTestCaseXpath = findTestObject('Object Repository/OR_DeviceManager/testResult_selectTestCaseCheckBox').findPropertyValue('xpath').toString()
				List<WebElement> checkBoxTestCase = driver.findElements(By.xpath(checkBoxTestCaseXpath))

				if (checkBoxTestCase != null && checkBoxTestCase.size() > 5){
					Actions builder = new Actions(driver);
					builder.moveToElement(checkBoxTestCase.get(0)).click(checkBoxTestCase.get(0)).build().perform();
					builder.moveToElement(checkBoxTestCase.get(1)).click(checkBoxTestCase.get(1)).build().perform();
					builder.moveToElement(checkBoxTestCase.get(2)).click(checkBoxTestCase.get(2)).build().perform();
					builder.moveToElement(checkBoxTestCase.get(3)).click(checkBoxTestCase.get(3)).build().perform();
					builder.moveToElement(checkBoxTestCase.get(4)).click(checkBoxTestCase.get(4)).build().perform();
					MesmerLogUtils.logInfo("Test case selected")
					result = true
				}else{
					MesmerLogUtils.logInfo("Test case count is not greater than 5")
				}
			}else{
				MesmerLogUtils.logInfo("There is no test case in new filter")
			}
		}else{
			MesmerLogUtils.logInfo("Unable to click on new filter")
		}
		return result
	}

	public boolean runTestCase(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject btnReplay = findTestObject('Object Repository/OR_Replay/btn_Replay')
		TestObject btnRun = findTestObject('Object Repository/OR_Replay/btn_Run')
		TestObject btnYes = findTestObject('Object Repository/OR_Replay/btn_Yes')
		TestObject queueMsg = findTestObject('Object Repository/OR_Replay/msg_queue')
		TestObject deviceList = findTestObject('Object Repository/OR_Replay/list_DeviceList')
		if(WebUI.waitForElementPresent(btnReplay , 120)==true){
			WebUI.click(btnReplay)
			MesmerLogUtils.markPassed("Click On Re-run")

			if(WebUI.waitForElementPresent(deviceList , 30)==true){
				String deviceListXpath = findTestObject('Object Repository/OR_Replay/list_DeviceList').findPropertyValue('xpath').toString()
				List<WebElement> device = driver.findElements(By.xpath(deviceListXpath))
				if(device != null && device.size() > 0){
					device.get(0).click()
					MesmerLogUtils.markPassed("Device Selected")

					//4. Click on Run button
					if(WebUI.waitForElementClickable(btnRun , 20) == true){

						WebUI.click(btnRun)
						MesmerLogUtils.markPassed("Click on Run Button")

						if(WebUI.waitForElementPresent(btnYes , 20) == true){
							WebUI.click(btnYes)
							MesmerLogUtils.markPassed("Click on Yes Button")
							if(WebUI.waitForElementPresent(queueMsg , 30) == true){
								MesmerLogUtils.markPassed("Test cases lined up in queue")
								if(WebUI.waitForElementNotPresent(queueMsg , 30) == true){
									MesmerLogUtils.markPassed("Queue msg pop up disappeared")
									result = true
								}else{
									MesmerLogUtils.logInfo("Queue msg pop up not disappeared")
								}
							}else{
								MesmerLogUtils.logInfo("Test cases not lined up in a queue")
							}
						}else{
							MesmerLogUtils.logInfo("Unable to click on Yes button")
						}
					}else{
						MesmerLogUtils.logInfo("Unable to click on Run button")
					}
				}else{
					MesmerLogUtils.logInfo("No device available")
				}
			}else{
				MesmerLogUtils.logInfo("Devices not fetched in 30 sec wait")
			}
		}else{
			MesmerLogUtils.logInfo("Unable to click on replay button")
		}
		return result
	}
	public boolean verifyTestsQueueCount(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String testsQueueCountXpath = findTestObject('Object Repository/OR_DeviceManager/deviceManager_testQueueCount').findPropertyValue('xpath').toString()
		List<WebElement> testsQueueCount = driver.findElements(By.xpath(testsQueueCountXpath))
		if(testsQueueCount.size() > 0){
			for (WebElement webElement : testsQueueCount) {
				String testsQueueCountListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue on device " + " : " + "  "  +  testsQueueCountListText)
				result = true
			}

		}else{
			MesmerLogUtils.logInfo("No tests queue on device")
		}
		return result
	}

	public boolean mouseHoverOnQueueCount(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String queueCountXpath = findTestObject('Object Repository/OR_DeviceManager/deviceManager_testQueueCount').findPropertyValue('xpath').toString()
		List<WebElement> queueCount = driver.findElements(By.xpath(queueCountXpath))
		if(queueCount.size() > 0){
			Actions builder = new Actions(driver);
			builder.moveToElement(queueCount.get(0)).perform()
			MesmerLogUtils.logInfo("Mouse hover to queue count")
			result = true
		}else{
			MesmerLogUtils.logInfo("There is no active task on device")
		}
		return result
	}

	public boolean clickOnLiveViewTaskBtn(){
		boolean	result = false
		TestObject liveViewTaskBtn = findTestObject('Object Repository/OR_DeviceManager/btn_viewLiveTask')
		if(WebUI.waitForElementPresent(liveViewTaskBtn, 10)){
			WebUI.click(liveViewTaskBtn)
			result = true
		}else{
			MesmerLogUtils.logInfo("Could not click on Live View Task")
		}
		return result
	}

	public boolean verifyRedirectionLiveViewTask(){
		boolean result = false
		TestObject appMapLiveView = findTestObject('Object Repository/OR_DeviceManager/nav_appMapLiveViewTask')
		TestObject recordLiveView = findTestObject('Object Repository/OR_DeviceManager/nav_recordLiveViewTask')
		TestObject replayLiveView = findTestObject('Object Repository/OR_DeviceManager/nav_replayLiveViewTask')

		if(WebUI.waitForElementPresent(appMapLiveView, 10)){
			MesmerLogUtils.logInfo("Redirected to App Map page")
			result = true
		}else if (WebUI.waitForElementPresent(recordLiveView, 5)){
			MesmerLogUtils.logInfo("Redirected to Record page")
			result = true
		}else if (WebUI.waitForElementPresent(replayLiveView, 5)){
			MesmerLogUtils.logInfo("Redirected to Replay page")
			result = true
		}else{
			MesmerLogUtils.logInfo("Page redirection failed")
		}
		return result
	}

	public boolean verifyLiveViewTaskPopOver(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject lblcurrentTask = findTestObject('Object Repository/OR_DeviceManager/menuWrap_CurrentTask')
		TestObject lblBuild = findTestObject('Object Repository/OR_DeviceManager/menuWrap_miniLabelBuild')
		TestObject lblTask = findTestObject('Object Repository/OR_DeviceManager/menuWrap_Task')

		TestObject runningTask = findTestObject('Object Repository/OR_DeviceManager/menuWrap_RunningTask')
		TestObject runningTaskBuildNumber = findTestObject('Object Repository/OR_DeviceManager/menuWarp_buildNumber')

		String runningTaskBuildNumberXpath = findTestObject('Object Repository/OR_DeviceManager/menuWarp_buildNumber').findPropertyValue('xpath').toString()
		WebElement buildNumberText = driver.findElement(By.xpath(runningTaskBuildNumberXpath))
		String buildNumber = buildNumberText.getText()

		String runningTaskXpath = findTestObject('Object Repository/OR_DeviceManager/menuWrap_RunningTask').findPropertyValue('xpath').toString()
		WebElement runningTaskText = driver.findElement(By.xpath(runningTaskXpath))
		String runningInProgressTask = runningTaskText.getText()

		if(WebUI.verifyElementPresent(lblcurrentTask, 10)){
			MesmerLogUtils.logInfo("Label Current Task found")

			if (WebUI.verifyElementPresent(lblBuild, 5)){
				MesmerLogUtils.logInfo("Label Build found")

				if (WebUI.waitForElementPresent(runningTaskBuildNumber, 5)){
					MesmerLogUtils.logInfo("Build Number" + " : " + buildNumber)

					if (WebUI.verifyElementPresent(lblTask, 5)){
						MesmerLogUtils.logInfo("Label Task found")
						if (WebUI.waitForElementPresent(runningTask, 5)){
							MesmerLogUtils.logInfo("Running Task" + " : " + runningInProgressTask)
							result = true
						}else{
							MesmerLogUtils.logInfo("No running in-progress task found")
						}
					}else{
						MesmerLogUtils.logInfo("Label Task not found")
					}
				}else{
					MesmerLogUtils.logInfo("No build number found")
				}
			}else{
				MesmerLogUtils.logInfo("Label Build not found")
			}
		}else{
			MesmerLogUtils.logInfo("Label Current Task not found")
		}
		return result
	}

	public boolean contentOfQueueTable(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String queueTaskXpath = findTestObject('Object Repository/OR_DeviceManager/tasksAwaitingADevice_Task').findPropertyValue('xpath').toString()
		List<WebElement> queueTask = driver.findElements(By.xpath(queueTaskXpath))

		String queueBuildXpath = findTestObject('Object Repository/OR_DeviceManager/tasksAwaitingADevice_Build').findPropertyValue('xpath').toString()
		List<WebElement> queueBuild = driver.findElements(By.xpath(queueBuildXpath))

		String queueTestCaseXpath = findTestObject('Object Repository/OR_DeviceManager/tasksAwaitingADevice_TestCase').findPropertyValue('xpath').toString()
		List<WebElement> queueTestCase = driver.findElements(By.xpath(queueTestCaseXpath))

		String queueInitiatedByXpath = findTestObject('Object Repository/OR_DeviceManager/tasksAwaitingADevice_InitiatedBy').findPropertyValue('xpath').toString()
		List<WebElement> queueInitiated = driver.findElements(By.xpath(queueInitiatedByXpath))

		String queueAddedDateXpath = findTestObject('Object Repository/OR_DeviceManager/tasksAwaitingADevice_AddedDate').findPropertyValue('xpath').toString()
		List<WebElement> queueAddedDate = driver.findElements(By.xpath(queueAddedDateXpath))

		String queueAddedTimeXpath = findTestObject('Object Repository/OR_DeviceManager/tasksAwaitingADevice_AddedTime').findPropertyValue('xpath').toString()
		List<WebElement> queueAddedTime = driver.findElements(By.xpath(queueAddedTimeXpath))


		String queueDeviceAwaitingXpath = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingDevice_Queue').findPropertyValue('xpath').toString()
		List<WebElement> queueDeviceAwaiting = driver.findElements(By.xpath(queueDeviceAwaitingXpath))


		if(queueDeviceAwaiting.size() > 0){

			for (WebElement webElement : queueTask) {
				String queueTaskListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue Task " + " : " + "  "  +  queueTaskListText)
			}


			for (WebElement webElement : queueBuild) {
				String queueBuildListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue Build " + " : " + "  "  +  queueBuildListText)
			}

			for (WebElement webElement : queueTestCase) {
				String queueTestCaseListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue Test Case " + " : " + "  "  +  queueTestCaseListText)
			}

			for (WebElement webElement : queueInitiated) {
				String queueInitiatedListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue Initiated By " + " : " + "  "  +  queueInitiatedListText)
			}

			for (WebElement webElement : queueAddedDate) {
				String queueAddedDateListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue Added Date " + " : " + "  "  +  queueAddedDateListText)
			}

			for (WebElement webElement : queueAddedTime) {
				String queueAddedTimeListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue Added Time " + " : " + "  "  +  queueAddedTimeListText)
			}
			result = true
		}else{
			MesmerLogUtils.logInfo("No tests queue on device")
		}
		return result
	}

	public boolean removeSingleTestFromQueue(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String queueDeviceAwaitingXpath = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingDevice_Queue').findPropertyValue('xpath').toString()
		List<WebElement> queueDeviceAwaiting = driver.findElements(By.xpath(queueDeviceAwaitingXpath))
		if(queueDeviceAwaiting != null && queueDeviceAwaiting.size() > 0){

			String removeSingleTestXpath = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingADevice_removeSingleTest').findPropertyValue('xpath').toString()
			List<WebElement> removeSingleTest = driver.findElements(By.xpath(removeSingleTestXpath))
			if(removeSingleTest != null && removeSingleTest.size() > 0){
				Actions builder = new Actions(driver);
				builder.moveToElement(removeSingleTest.get(0)).click(removeSingleTest.get(0)).build().perform();
				MesmerLogUtils.logInfo("Clicked on cross icon to remove a queue")
				result = true
			}else{
				MesmerLogUtils.logInfo("Could not click on cross button")
			}

		}else{
			MesmerLogUtils.logInfo("There is no test case in queue for awaiting a device")
		}
		return result
	}

	public boolean confirmationToRemoveSingleTest(){
		boolean result = false
		TestObject confirmationDialogueToRemoveSingleTest = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingADevice_queueDeleteConfirmationText')

		if(WebUI.waitForElementPresent(confirmationDialogueToRemoveSingleTest, 10)){
			MesmerLogUtils.logInfo("Confirmation pop up appears for deleting a test")
			result = true

		}else{
			MesmerLogUtils.logInfo("No confirmation pop up appears for deleting a test")
		}
		return result
	}

	public boolean confirmationToRemoveSingleTestYes(){
		boolean result = false
		TestObject confirmationDialogueToRemoveSingleTestYes = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingADevice_btnYes')

		if(WebUI.waitForElementPresent(confirmationDialogueToRemoveSingleTestYes, 10)){
			WebUI.click(confirmationDialogueToRemoveSingleTestYes)
			MesmerLogUtils.logInfo("Clicked on Yes button")
			result = true

		}else{
			MesmerLogUtils.logInfo("Could not click on Yes button")
		}
		return result
	}

	public boolean confirmationToRemoveSingleTestCancel(){
		boolean result = false
		TestObject confirmationDialogueToRemoveSingleTestCancel = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingADevice_btnYes')

		if(WebUI.waitForElementPresent(confirmationDialogueToRemoveSingleTestCancel, 10)){
			WebUI.click(confirmationDialogueToRemoveSingleTestCancel)
			MesmerLogUtils.logInfo("Clicked on Cancel button")
			result = true

		}else{
			MesmerLogUtils.logInfo("Could not click on Cancel button")
		}
		return result
	}

	public boolean confirmationToRemoveClearQueue(){
		boolean result = false
		TestObject confirmationDialogueToRemoveClearQueue = findTestObject('Object Repository/OR_DeviceManager/tasksAwaitingADevice_clearQueueConfirmation')

		if(WebUI.waitForElementPresent(confirmationDialogueToRemoveClearQueue, 10)){
			MesmerLogUtils.logInfo("Confirmation pop up appears for deleting queue")
			result = true

		}else{
			MesmerLogUtils.logInfo("No confirmation pop up appears for deleting queue")
		}
		return result
	}

	public boolean ClickOnSearch(String serach_value){
		boolean result = false
		TestObject inputSearch = findTestObject('Object Repository/OR_DeviceManager/tasksAwaitingADevice_search')
		TestObject clickSearchIcon = findTestObject('Object Repository/OR_DeviceManager/tasksAwaitingADevice_serachIcon')
		if(WebUI.waitForElementPresent(inputSearch, 10)){
			WebUI.setText(inputSearch,serach_value)
			WebUI.sendKeys(null, Keys.chord(Keys.ENTER))
			if(WebUI.waitForElementPresent(clickSearchIcon, 10)){
				WebUI.click(clickSearchIcon)
				result = true
			}else{
				MesmerLogUtils.logInfo("Unable to click on search icon")
			}
		}else{
			MesmerLogUtils.logInfo("Unable to find search field")
		}
		return result
	}

	public boolean deviceQueueCurrentTask(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		TestObject currentTaskObj = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_noTaskRunningCurrentTask')
		TestObject inCaseCrawl = findTestObject('Object Repository/OR_DeviceManager/deviceQueueCurrentTask_testCaseCrawl')
		String crawlTestCaseName = WebUI.getText(inCaseCrawl)

		String currentTaskXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueueCurrentTask_task').findPropertyValue('xpath').toString()
		List<WebElement> currentTask = driver.findElements(By.xpath(currentTaskXpath))

		String currentBuildXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueueCurrentTask_build').findPropertyValue('xpath').toString()
		List<WebElement> currentBuild = driver.findElements(By.xpath(currentBuildXpath))

		String currentTestCaseXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueueCurrentTask_testCase').findPropertyValue('xpath').toString()
		List<WebElement> currentTestCase = driver.findElements(By.xpath(currentTestCaseXpath))

		String currentInitiatedByXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueueCurrentTask_initiatedBy').findPropertyValue('xpath').toString()
		List<WebElement> currentInitiated = driver.findElements(By.xpath(currentInitiatedByXpath))

		//		String currentAddedDateXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueueCurrentTask_StartedDate').findPropertyValue('xpath').toString()
		//		List<WebElement> currentAddedDate = driver.findElements(By.xpath(currentAddedDateXpath))
		//
		//		String currentAddedTimeXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueueCurrentTask_StartedTime').findPropertyValue('xpath').toString()
		//		List<WebElement> currentAddedTime = driver.findElements(By.xpath(currentAddedTimeXpath))


		String queueDeviceAwaitingXpath = findTestObject('Object Repository/OR_DeviceManager/taskAwaitingDevice_Queue').findPropertyValue('xpath').toString()
		List<WebElement> queueDeviceAwaiting = driver.findElements(By.xpath(queueDeviceAwaitingXpath))


		if(WebUI.waitForElementNotPresent(currentTaskObj, 10)){

			for (WebElement webElement : currentTask) {
				String currentTaskListText = webElement.getText();
				MesmerLogUtils.logInfo("Current Task " + " : " + "  "  +  currentTaskListText)
			}


			for (WebElement webElement : currentBuild) {
				String currentBuildListText = webElement.getText();
				MesmerLogUtils.logInfo("Current Build " + " : " + "  "  +  currentBuildListText)
			}


			for (WebElement webElement : currentTestCase) {
				String currentTestCaseListText = webElement.getText();

				if(currentTestCaseListText.contains("---")){
					MesmerLogUtils.logInfo("No Name for Crawl")
				} else {
					MesmerLogUtils.logInfo("Current Test Case " + " : " + "  "  +  currentTestCaseListText)
				}

			}

			for (WebElement webElement : currentInitiated) {
				String currentInitiatedListText = webElement.getText();
				MesmerLogUtils.logInfo("Current Initiated By " + " : " + "  "  +  currentInitiatedListText)
			}

			//			for (WebElement webElement : currentAddedDate) {
			//				String currentAddedDateListText = webElement.getText();
			//				MesmerLogUtils.logInfo("Current Added Date " + " : " + "  "  +  currentAddedDate)
			//			}
			//
			//			for (WebElement webElement : currentAddedTime) {
			//				String currentAddedTimeListText = webElement.getText();
			//				MesmerLogUtils.logInfo("Current Added Time " + " : " + "  "  +  currentAddedTimeListText)
			//			}
			result = true
		}else{
			MesmerLogUtils.logInfo("No current task")
		}
		return result
	}

	public boolean deviceQueueTask(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		TestObject queueTaskObj = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_noTaskRunningQueueTask')

		//		String crawlQueueNoNameXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_testCaseCrawlName').findPropertyValue('xpath').toString()
		//		List<WebElement> crawlQueueNoName = driver.findElements(By.xpath(crawlQueueNoNameXpath))


		String queueTaskXpath = findTestObject('OR_DeviceManager/deviceQueue_task').findPropertyValue('xpath').toString()
		List<WebElement> queueTask = driver.findElements(By.xpath(queueTaskXpath))

		String queueBuildXpath = findTestObject('OR_DeviceManager/deviceQueue_build').findPropertyValue('xpath').toString()
		List<WebElement> queueBuild = driver.findElements(By.xpath(queueBuildXpath))

		String queueTestCaseXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_testCase').findPropertyValue('xpath').toString()
		List<WebElement> queueTestCase = driver.findElements(By.xpath(queueTestCaseXpath))

		String queueInitiatedByXpath = findTestObject('OR_DeviceManager/deviceQueue_initiatedBy').findPropertyValue('xpath').toString()
		List<WebElement> queueInitiated = driver.findElements(By.xpath(queueInitiatedByXpath))

		String queueAddedDateXpath = findTestObject('OR_DeviceManager/deviceQueue_AddedDate').findPropertyValue('xpath').toString()
		List<WebElement> queueAddedDate = driver.findElements(By.xpath(queueAddedDateXpath))

		String queueAddedTimeXpath = findTestObject('OR_DeviceManager/deviceQueue_AddedTime').findPropertyValue('xpath').toString()
		List<WebElement> queueAddedTime = driver.findElements(By.xpath(queueAddedTimeXpath))




		if(WebUI.waitForElementNotPresent(queueTaskObj, 10)){

			for (WebElement webElement : queueTask) {
				String queueTaskListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue Task " + " : " + "  "  +  queueTaskListText)
			}


			for (WebElement webElement :queueBuild) {
				String queueBuildListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue Build " + " : " + "  "  +  queueBuildListText)
			}


			for (WebElement webElement : queueTestCase) {
				String queueTestCaseListText = webElement.getText();
				if(queueTestCaseListText.contains("---")){
					MesmerLogUtils.logInfo("No Name for Crawl")
				} else {
					MesmerLogUtils.logInfo("Queue Test Case " + " : " + "  "  +  queueTestCaseListText)
				}

			}


			for (WebElement webElement : queueInitiated) {
				String queueInitiatedListText = webElement.getText();
				MesmerLogUtils.logInfo("Queue Initiated By " + " : " + "  "  +  queueInitiatedListText)
			}

			for (WebElement webElement : queueAddedDate) {
				String queueAddedDateListText = webElement.getText();
				MesmerLogUtils.logInfo("Added Date " + " : " + "  "  +  queueAddedDateListText)
			}

			for (WebElement webElement : queueAddedTime) {
				String queueAddedTimeListText = webElement.getText();
				MesmerLogUtils.logInfo("Added Time " + " : " + "  "  +  queueAddedTimeListText)
			}
			result = true
		}else{
			MesmerLogUtils.logInfo("No Queue task")
		}
		return result
	}

	public boolean deviceQueueLastTask(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		TestObject lastTaskObj = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_noTaskRunningLastTask')

		String lastTaskXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_lastTask_task').findPropertyValue('xpath').toString()
		List<WebElement> lastTask = driver.findElements(By.xpath(lastTaskXpath))

		String lastBuildXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_lastTask_Build').findPropertyValue('xpath').toString()
		List<WebElement> lastBuild = driver.findElements(By.xpath(lastBuildXpath))

		String lastTestCaseXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_lastTask_testcase').findPropertyValue('xpath').toString()
		List<WebElement> lastTestCase = driver.findElements(By.xpath(lastTestCaseXpath))

		String lastInitiatedByXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_lastTask_initiatedBy').findPropertyValue('xpath').toString()
		List<WebElement> lastInitiated = driver.findElements(By.xpath(lastInitiatedByXpath))

		String lastCompletedDateXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_lastTask_completedDate').findPropertyValue('xpath').toString()
		List<WebElement> lastCompletedDate = driver.findElements(By.xpath(lastCompletedDateXpath))

		String lastCompletedTimeXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_lastTask_completedTime').findPropertyValue('xpath').toString()
		List<WebElement> lastCompletedTime = driver.findElements(By.xpath(lastCompletedTimeXpath))



		if(WebUI.waitForElementPresent(lastTaskObj, 10)){

			for (WebElement webElement : lastTask) {
				String lastTaskListText = webElement.getText();
				MesmerLogUtils.logInfo("Last Task " + " : " + "  "  +  lastTaskListText)
			}


			for (WebElement webElement :lastBuild) {
				String lastBuildListText = webElement.getText();
				MesmerLogUtils.logInfo("Last Build " + " : " + "  "  +  lastBuildListText)
			}

			for (WebElement webElement : lastTestCase) {
				String lastTestCaseListText = webElement.getText();
				if(lastTestCaseListText.contains("---")){
					MesmerLogUtils.logInfo("No Name for Crawl")
				} else {
					MesmerLogUtils.logInfo("Last Test Case " + " : " + "  "  +  lastTestCaseListText)
				}
			}

			for (WebElement webElement : lastInitiated) {
				String lastInitiatedListText = webElement.getText();
				MesmerLogUtils.logInfo("Last Initiated By " + " : " + "  "  +  lastInitiatedListText)
			}

			for (WebElement webElement : lastCompletedDate) {
				String lastCompletedDateListText = webElement.getText();
				MesmerLogUtils.logInfo("Completed Date " + " : " + "  "  +  lastCompletedDateListText)
			}

			for (WebElement webElement : lastCompletedTime) {
				String lastCompletedTimeListText = webElement.getText();
				MesmerLogUtils.logInfo("Completed Time " + " : " + "  "  +  lastCompletedTimeListText)

			}
			result = true
		}
		else{
			MesmerLogUtils.logInfo("No Last task")
		}
		return result
	}

	public boolean deviceQueueDeviceOS(){
		boolean result = false
		TestObject deviceOS = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_deviceOS')

		String deviceOSText = WebUI.getText(deviceOS)

		if(WebUI.waitForElementPresent(deviceOS, 10)){
			MesmerLogUtils.logInfo("Device OS " + " : " +  deviceOSText)
			result = true

		}else{
			MesmerLogUtils.logInfo("Device OS not found")
		}
		return result
	}

	public boolean deviceQueueDeviceName(){
		boolean result = false
		TestObject deviceName = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_deviceOS')

		String deviceNameText = WebUI.getText(deviceName)

		if(WebUI.waitForElementPresent(deviceName, 10)){
			MesmerLogUtils.logInfo("Device Name " + " : " +  deviceNameText)
			result = true

		}else{
			MesmerLogUtils.logInfo("Device Name not foud")
		}
		return result
	}

	public boolean deviceQueueDeviceType(){
		boolean result = false
		TestObject deviceType = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_deviceType')

		String deviceTypeText = WebUI.getText(deviceType)

		if(WebUI.waitForElementPresent(deviceType, 10)){
			MesmerLogUtils.logInfo("Device Type " + " : " +  deviceTypeText)
			result = true

		}else{
			MesmerLogUtils.logInfo("Device Type not foud")
		}
		return result
	}

	public boolean deviceQueueStopAndClearTasks(){
		boolean result = false
		TestObject btnStopAndClearTask = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_stopAndClearBtn')

		if(WebUI.waitForElementPresent(btnStopAndClearTask, 10)){

			result = true

		}else{
			MesmerLogUtils.logInfo("Stop and clear tasks button not found")
		}
		return result
	}

	public boolean deviceQueueRebootBtn(){
		boolean result = false
		TestObject btnReboot = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_rebootBtn')

		if(WebUI.waitForElementPresent(btnReboot, 10)){

			result = true

		}else{
			MesmerLogUtils.logInfo("Reboot button not found")
		}
		return result
	}

	public boolean deviceQueueDeviceBar(){
		boolean result = false
		TestObject deviceBar = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_deviceBar')

		if(WebUI.waitForElementPresent(deviceBar, 10)){

			result = true

		}else{
			MesmerLogUtils.logInfo("Device bar not found")
		}
		return result
	}

	public boolean clickOnStopAndClearTasks(){
		boolean result = false
		TestObject btnStopAndClearTask = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_stopAndClearBtn')

		if(WebUI.waitForElementPresent(btnStopAndClearTask, 10)){
			WebUI.click(btnStopAndClearTask)
			result = true

		}else{
			MesmerLogUtils.logInfo("Stop and clear tasks button not found")
		}
		return result
	}

	public boolean confirmationDialogueClearTasks(){
		boolean result = false
		TestObject confirmationDialogue = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_clearTasksConfirmationDlg')

		if(WebUI.waitForElementPresent(confirmationDialogue, 10)){

			result = true

		}else{
			MesmerLogUtils.logInfo("Confirmation dialogue not found")
		}
		return result
	}

	public boolean verifyNothingInQueue(){
		boolean result = false
		TestObject nothingInQueue = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_noTaskRunningQueueTask')

		if(WebUI.waitForElementPresent(nothingInQueue, 10)){

			result = true

		}else{
			MesmerLogUtils.logInfo("Queue not cleared")
		}
		return result
	}

	public boolean verifyNoTaskRunning(){
		boolean result = false
		TestObject noTaskRunning = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_noTaskRunningCurrentTask')

		if(WebUI.waitForElementPresent(noTaskRunning, 60)){

			result = true

		}else{
			MesmerLogUtils.logInfo("Running task not cleared")
		}
		return result
	}

	public boolean removeRunningTask(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String removeRunningTaskXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_removeRunningTask').findPropertyValue('xpath').toString()
		WebElement removeRunningTask = driver.findElement(By.xpath(removeRunningTaskXpath))
		if(removeRunningTask != null && removeRunningTask.size() > 0){
			Actions builder = new Actions(driver);
			builder.moveToElement(removeRunningTask).click(removeRunningTask).build().perform();
			MesmerLogUtils.logInfo("Clicked on cross icon to remove a running task")
			result = true
		}else{
			MesmerLogUtils.logInfo("Could not click on cross button")
		}
		return result
	}

	public boolean removeSingleTestFromDeviceQueue(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()


		String removeSingleTestXpath = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_removeSingleFromQueue').findPropertyValue('xpath').toString()
		List<WebElement> removeSingleTest = driver.findElements(By.xpath(removeSingleTestXpath))
		if(removeSingleTest != null && removeSingleTest.size() > 0){
			Actions builder = new Actions(driver);
			builder.moveToElement(removeSingleTest.get(0)).click(removeSingleTest.get(0)).build().perform();
			MesmerLogUtils.logInfo("Clicked on cross icon to remove a queue")
			result = true
		}else{
			MesmerLogUtils.logInfo("Could not click on cross button")
		}
		return result
	}

	public boolean clickOnDoneBtn(){
		boolean result = false
		TestObject btnDone = findTestObject('Object Repository/OR_DeviceManager/deviceQueue_DoneBtn')

		if(WebUI.waitForElementPresent(btnDone, 10)){
			WebUI.click(btnDone)
			result = true

		}else{
			MesmerLogUtils.logInfo("Could not click on done button")
		}
		return result
	}
}