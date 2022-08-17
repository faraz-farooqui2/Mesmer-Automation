package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility


public class TestResultController {

	private static TestResultController mInstance = null

	private TestResultController(){
	}

	public static TestResultController getInstance(){
		if(mInstance == null){
			mInstance = new TestResultController()
		}
		return mInstance
	}

	public int getTestResultTestCasesCount(){
		int result = 0;
		TestObject countObj = findTestObject('Object Repository/OR_ManageTestCases/text_TotalNumberOfTestCases')
		if(WebUI.waitForElementPresent(countObj, 60)){
			String count = WebUI.getText(countObj)
			if(count.equalsIgnoreCase("")){
				count = "0"
			}
			result = Integer.parseInt(count)
		}
		else{
			MesmerLogUtils.markFailed("Test result tests count not found")
		}

		return result
	}

	public boolean findTestCaseAndPerformAction(String testCaseName, String actionName, String subActionName){
		boolean result = false
		TestObject testCasesDivXPath = findTestObject('Object Repository/OR_TestResult/div_testCasesList')
		if(testCasesDivXPath != null && testCasesDivXPath.findPropertyValue("xpath") != null){
			String testResultTestCasesDivXPath = testCasesDivXPath.findPropertyValue("xpath")
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject testCasesListXPath = findTestObject('Object Repository/OR_TestResult/list_TestCasesXPath')
			if(testCasesListXPath != null && testCasesListXPath.findPropertyValue("xpath") != null){
				String testResultTestCasesXPath = testCasesListXPath.findPropertyValue("xpath")
				List<WebElement> testCasesList = driver.findElements(By.xpath(testResultTestCasesXPath))
				if(testCasesList != null && testCasesList.size() > 0){
					for(int i= 0; i < testCasesList.size(); i++){
						String titleXPath = '//app-test-results-tiles[@class="vScrollCards"]/div/a['+(i+1)+']/descendant::div[@class="mat-card-title"]/span[contains(@class,"ng-star-inserted")]'
						WebElement testCaseTitle = driver.findElement(By.xpath(titleXPath))
						if(testCaseTitle != null && testCaseTitle.getText().equals(testCaseName)){
							WebUI.delay(2)
							TestObject to = new TestObject("objectName")
							to.addProperty("xpath", ConditionType.EQUALS,'//app-test-results-tiles[@class="vScrollCards"]/div/a['+(i+1)+']/descendant::div[@class="mat-card-title"]/span[contains(@class,"ng-star-inserted") and text()="'+testCaseName+'"]')
							WebUI.scrollToElement(to, 10)
							WebUI.delay(2)
							WebUI.mouseOver(to)
							WebUI.delay(2)
							if(actionName == "selectTestCase"){
								String checkBoxXPath = '//app-test-results-tiles[@class="vScrollCards"]/div/a['+(i+1)+']/descendant::div[@class="round"]'
								WebElement checkBox = driver.findElement(By.xpath(checkBoxXPath))
								if(checkBox != null){
									checkBox.click()
									WebUI.delay(2)
									WebUI.click(to)
									WebUI.delay(5)
									if(subActionName == "delete"){
										if(deleteTest()){
											result = true
										}
									}
									else if(subActionName == "execute"){
										if(replayTestCases("Virtual")){
											result = true
										}
									}
									else if(subActionName == "stop"){
										if(stopTestCasesExecution()){
											result = true
										}
									}
									if(subActionName.equalsIgnoreCase("")){
										result = true
									}
									break
								}
								else{
									MesmerLogUtils.markFailed('Checkbox not found')
								}
							}
						}
					}
				}
				else{
					MesmerLogUtils.markWarning('There is no test case in the list')
				}
			}
			else{
				MesmerLogUtils.markWarning('There is no test case in the list')
			}
		}
		else{
			MesmerLogUtils.markWarning('Test cases container not found')
		}

		return result
	}

	public boolean replayTestCases(String deviceType){
		boolean result = false
		if(clickReplayButton()){
			WebUI.delay(5)
			List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(deviceType)
			if(virtualDevicesList != null && virtualDevicesList.size() >=1){
				Utility.selectDeviceAndSetParams("", "", deviceType, "", "", "", "")
				//				if(searchedReadyDevice != null){
				//					searchedReadyDevice.click()
				WebUI.delay(4)
				if(clickRunButton()){
					WebUI.delay(2)
					if(clickYesButton()){
						result = true
						WebUI.delay(2)
					}
					else{
						MesmerLogUtils.logInfo("Yes button not found or not clicked")
					}
				}
				else{
					MesmerLogUtils.logInfo("Run button not found or not clicked")
				}
				//				}
			}
			else{
				MesmerLogUtils.markFailed("No virtual device available in the list")
			}
		}
		else{
			MesmerLogUtils.logInfo("Replay button not clicked or not found")
		}
		return result
	}

	public boolean stopTestCasesExecution(){
		boolean result = false
		if(clickStopButton()){
			WebUI.delay(5)
			if(clickYesButton()){
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.logInfo("Yes button not found or not clicked")
			}
		}
		else{
			MesmerLogUtils.logInfo("Stop button not found or not clicked")
		}
		return result
	}

	private boolean deleteTest(){
		boolean result = false
		//7. Click on Delete option from the drop down list of 3 dots menu
		TestObject option3DotButton = findTestObject('Object Repository/OR_TestDetails/option_3DotButton')
		if(WebUI.waitForElementPresent(option3DotButton, 60)){
			WebUI.click(option3DotButton)
			WebUI.delay(5)
			TestObject btnDelete = findTestObject('Object Repository/OR_TestDetails/btn_Delete')
			if(WebUI.waitForElementPresent(btnDelete, 30)){
				WebUI.click(btnDelete)
				WebUI.delay(5)
				MesmerLogUtils.markPassed("Clicked on Delete Button ")
				TestObject deleteConfirmation = findTestObject('Object Repository/OR_TestDetails/window_ConfirmationOnDeletingTest')
				if(WebUI.waitForElementPresent(deleteConfirmation, 30)){
					MesmerLogUtils.markPassed("Test Case Delete Confirmation Dialogue")
					//8. Click on Yes option
					TestObject deleteYes = findTestObject('Object Repository/OR_TestDetails/button_YesOnDeletingTest-ConfirmationWindow')
					if(WebUI.waitForElementPresent(deleteYes, 30)){
						WebUI.click(deleteYes)
						result = true
						WebUI.delay(5)
						MesmerLogUtils.markPassed("Clicked on Yes to Delete Test Case")
					}else{
						MesmerLogUtils.markFailed("Unable to Click on Yes to Delete Test Case ")
					}
				}else{
					MesmerLogUtils.markFailed("No Test Case Delete Confirmation Dialogue ")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to Click on Delete Button")
			}
		}
		else{
			MesmerLogUtils.markFailed("Button 3 dots not found")
		}

		return result
	}
	/**
	 * Click the 3 dots button
	 * @return
	 */
	public boolean clickDotsOptionButton(){
		boolean result = false
		TestObject option3DotButton = findTestObject('Object Repository/OR_TestDetails/option_3DotButton')
		if(WebUI.waitForElementPresent(option3DotButton, 60)){
			WebUI.click(option3DotButton)
			WebUI.delay(5)
		}
		else{
			MesmerLogUtils.markFailed("Button 3 dots not found")
		}
		return result
	}

	public void checkIfDeleteInQueueDialogAppears(){
		TestObject dialogDelete = findTestObject("Object Repository/OR_CreateNewTestCase/dialog_DeleteInQueueTestCase")
		if(WebUI.waitForElementVisible(dialogDelete, 5)){
			TestObject deleteYes = findTestObject('Object Repository/OR_TestDetails/button_YesOnDeletingTest-ConfirmationWindow')
			if(WebUI.waitForElementPresent(deleteYes, 5)){
				WebUI.click(deleteYes)
				WebUI.delay(5)
			}else{
				MesmerLogUtils.markFailed("Unable to Click on Yes to Delete Test Case ")
			}
		}
	}


	public void checkIfAssertionDialogAppears(){
		boolean result = false
		TestObject dialogAssertion = findTestObject("Object Repository/OR_UDA/dialogue_Assertion")
		if(WebUI.waitForElementVisible(dialogAssertion, 20)){
			TestObject btnClose = findTestObject('Object Repository/OR_UDA/button_Close')
			if(WebUI.waitForElementPresent(btnClose, 20)){
				WebUI.click(btnClose)
				if(WebUI.waitForElementNotPresent(dialogAssertion, 120)){
					result = true
					MesmerLogUtils.logInfo("Assertion Dialogue close successfully")
				}
			}
		}

		result = false
	}
	/**
	 * Check the total test cases label
	 * @return
	 */
	public boolean checkTotalTestCasesLabel(){
		boolean result = false
		TestObject totalTestCases = findTestObject("Object Repository/OR_TestResult/text_TotalNumberOfTestCases")
		if(WebUI.waitForElementPresent(totalTestCases, 30)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Label total test cases not found")
		}

		return result
	}

	/**
	 * Check the percentage failed label
	 * @return
	 */
	public boolean checkPercentageFailedLabel(){
		boolean result = false
		TestObject lblPercentageFailed = findTestObject("Object Repository/OR_TestResult/text_PercentageFailed")
		if(WebUI.waitForElementPresent(lblPercentageFailed, 30)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Label percentage failed not found")
		}

		return result
	}

	/**
	 * Check the all test cases label
	 * @return
	 */
	public boolean checkAllTestCasesLabel(){
		boolean result = false
		TestObject allTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionAll")
		if(WebUI.waitForElementPresent(allTestCaseLabel, 30)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("All test cases label not found")
		}

		return result
	}
	/**
	 * Get All test cases count
	 * @return
	 */
	public int getAllTestCasesCount(){
		int result = 0
		if(checkAllTestCasesLabel()){
			TestObject allTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionAll")
			String lblAll = WebUI.getText(allTestCaseLabel)
			result = getNumberOutOfString(lblAll)
		}
		else{
			MesmerLogUtils.logInfo("All test cases label not found")
		}

		return result
	}

	/**
	 * Check the new test cases label
	 * @return
	 */
	public boolean checkNewTestCasesLabel(){
		boolean result = false
		TestObject newTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionNew")
		if(WebUI.waitForElementPresent(newTestCaseLabel, 30)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("New test cases label not found")
		}

		return result
	}
	/**
	 * Get new test cases count
	 * @return
	 */
	public int getNewTestCasesCount(){
		int result = 0
		if(checkNewTestCasesLabel()){
			TestObject newTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionNew")
			String lblNew = WebUI.getText(newTestCaseLabel)
			result = getNumberOutOfString(lblNew)
		}
		else{
			MesmerLogUtils.logInfo("New test cases label not found")
		}

		return result
	}

	/**
	 * Check the passed test cases label
	 * @return
	 */
	public boolean checkPassedTestCasesLabel(){
		boolean result = false
		TestObject passedTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionPassed")
		if(WebUI.waitForElementPresent(passedTestCaseLabel, 30)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Passed test cases label not found")
		}

		return result
	}
	/**
	 * Get passed test cases count
	 * @return
	 */
	public int getPassedTestCasesCount(){
		int result = 0
		if(checkPassedTestCasesLabel()){
			TestObject passedTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionPassed")
			String lblPassed = WebUI.getText(passedTestCaseLabel)
			result = getNumberOutOfString(lblPassed)
		}
		else{
			MesmerLogUtils.logInfo("Passed test cases label not found")
		}

		return result
	}

	/**
	 * Check the failed test cases label
	 * @return
	 */
	public boolean checkFailedTestCasesLabel(){
		boolean result = false
		TestObject failedTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionFailed")
		if(WebUI.waitForElementPresent(failedTestCaseLabel, 30)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Failed test cases label not found")
		}

		return result
	}
	/**
	 * Get failed test cases count
	 * @return
	 */
	public int getFailedTestCasesCount(){
		int result = 0
		if(checkFailedTestCasesLabel()){
			TestObject failedTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionFailed")
			String lblFailed = WebUI.getText(failedTestCaseLabel)
			result = getNumberOutOfString(lblFailed)
		}
		else{
			MesmerLogUtils.logInfo("Failed test cases label not found")
		}

		return result
	}

	/**
	 * Check the needs review test cases label
	 * @return
	 */
	public boolean checkNeedsReviewTestCasesLabel(){
		boolean result = false
		TestObject needsReviewTestCaseLabel = findTestObject("OR_TestResult/testCase_OptionShowMine")
		if(WebUI.waitForElementPresent(needsReviewTestCaseLabel, 30)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Needs review test cases label not found")
		}

		return result
	}
	/**
	 * Get needs review test cases count
	 * @return
	 */
	public int getNeedsReviewTestCasesCount(){
		int result = 0
		if(checkNeedsReviewTestCasesLabel()){
			TestObject needsReviewTestCaseLabel = findTestObject("OR_TestResult/testCase_OptionShowMine")
			String lblNeedsReview = WebUI.getText(needsReviewTestCaseLabel)
			result = getNumberOutOfString(lblNeedsReview)
		}
		else{
			MesmerLogUtils.logInfo("Needs review test cases label not found")
		}

		return result
	}

	/**
	 * Check the broken test cases label
	 * @return
	 */
	public boolean checkBrokenTestCasesLabel(){
		boolean result = false
		TestObject brokenTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionBroken")
		if(WebUI.waitForElementPresent(brokenTestCaseLabel, 30)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Broken test cases label not found")
		}

		return result
	}
	/**
	 * Get broken test cases count
	 * @return
	 */
	public int getBrokenTestCasesCount(){
		int result = 0
		if(checkBrokenTestCasesLabel()){
			TestObject brokenTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionBroken")
			String lblBroken = WebUI.getText(brokenTestCaseLabel)
			result = getNumberOutOfString(lblBroken)
		}
		else{
			MesmerLogUtils.logInfo("Broken test cases label not found")
		}

		return result
	}

	/**
	 * Check test cases options list and size of the list
	 * @return
	 */
	public boolean checkTestCasesOptionsList(){
		boolean result = false
		TestObject optionsList = findTestObject("Object Repository/OR_TestResult/div_TestCaseOptionsList")
		WebDriver driver = DriverFactory.getWebDriver()
		String optionsListXpath = optionsList.findPropertyValue("xpath").toString()
		List<WebElement> testCaseOptionsList = driver.findElements(By.xpath(optionsListXpath))
		if(testCaseOptionsList != null && testCaseOptionsList.size() > 0){
			MesmerLogUtils.logInfo("Test cases options list found and the size is "+testCaseOptionsList.size())
			result = true
		}

		return result
	}

	/**
	 * Check and click the tags label
	 * @return
	 */
	public boolean clickTagsOption(){
		boolean result = false
		TestObject optionTag = findTestObject("Object Repository/OR_TestResult/label_Tags")
		if(WebUI.waitForElementPresent(optionTag, 30)){
			WebUI.click(optionTag)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find tags label")
		}
		return result
	}

	/**
	 * Check and get the tags list
	 * @return
	 */
	public List<WebElement> getTagsDropDownList(){
		List<WebElement> result = null
		TestObject tagsDropDownList = findTestObject("Object Repository/OR_TestResult/tags_DropDownList")
		if(tagsDropDownList != null && tagsDropDownList.findPropertyValue("xpath") != null){
			String tagsDropDownXpath = tagsDropDownList.findPropertyValue("xpath")
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> tagsList = driver.findElements(By.xpath(tagsDropDownXpath))
			if(tagsList != null && tagsList.size() > 0){
				result = tagsList
			}
			else{
				MesmerLogUtils.logInfo("There is no tag in the dropdown list")
			}

		}
		return result
	}

	/**
	 * Check and click the clear all button
	 * @return
	 */
	public boolean clickClearAllButton(){
		boolean result = false
		TestObject btnClearAll = findTestObject("Object Repository/OR_TestResult/btn_clearAll")
		if(WebUI.waitForElementPresent(btnClearAll, 30)){
			WebUI.click(btnClearAll)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the clear all button")
		}
		return result
	}

	/**
	 * Check and click the sort by option
	 * @return
	 */
	public boolean checkAndClickSortByOption(){
		boolean result = false
		TestObject optionSortBy = findTestObject("Object Repository/OR_TestResult/label_SortBy")
		if(WebUI.waitForElementPresent(optionSortBy, 30)){
			WebUI.click(optionSortBy)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the sort by label")
		}
		return result
	}

	/**
	 * Check and click the sort by latest run option
	 * @return
	 */
	public boolean checkAndClickSortByLatestRunOption(){
		boolean result = false
		TestObject optionSortByLatestRun = findTestObject("Object Repository/OR_TestResult/lbl_SortByLatestRun")
		if(WebUI.waitForElementPresent(optionSortByLatestRun, 30)){
			WebUI.click(optionSortByLatestRun)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the sort by latest run label")
		}
		return result
	}
	/**
	 * Check and click the tags drop down option item
	 * @param itemName
	 * @return
	 */
	public boolean clickTagsDropDownItem(String itemName){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String itemXPath = '//div[@class="popover-content popover-body"]/div/div[contains(text(),"'+itemName+'")]'
		WebElement itemElement = driver.findElement(By.xpath(itemXPath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the option "+itemName)
		}
		return result
	}

	/**
	 * Check and click the replay button
	 * @return
	 */
	public boolean clickReplayButton(){
		boolean result = false
		TestObject btnReplay = findTestObject("Object Repository/OR_TestResult/btn_replay")
		if(WebUI.waitForElementPresent(btnReplay, 30)){
			WebUI.click(btnReplay)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the replay button")
		}
		return result
	}

	/**
	 * Check and click the run button
	 * @return
	 */
	public boolean clickRunButton(){
		boolean result = false
		TestObject btnRun = findTestObject("Object Repository/OR_TestResult/btn_run")
		if(WebUI.waitForElementPresent(btnRun, 30)){
			WebUI.click(btnRun)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the run button")
		}
		return result
	}

	/**
	 * Check and click the yes button
	 * @return
	 */
	public boolean clickYesButton(){
		boolean result = false
		TestObject btnYes = findTestObject("Object Repository/OR_TestResult/btn_Yes")
		if(WebUI.waitForElementPresent(btnYes, 30)){
			WebUI.click(btnYes)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the yes button")
		}
		return result
	}

	/**
	 * Check and click the stop button
	 * @return
	 */
	public boolean clickStopButton(){
		boolean result = false
		TestObject btnStop = findTestObject("Object Repository/OR_TestResult/btn_stop")
		if(WebUI.waitForElementPresent(btnStop, 30)){
			WebUI.click(btnStop)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the stop button")
		}
		return result
	}

	/**
	 * Check and click the doc report button
	 * @return
	 */
	public boolean clickDocReportButton(){
		boolean result = false
		TestObject btnDocReport = findTestObject("Object Repository/OR_TestResult/btn_DocReport")
		if(WebUI.waitForElementPresent(btnDocReport, 30)){
			WebUI.click(btnDocReport)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the doc report button")
		}
		return result
	}

	/**
	 * Check and click the csv report button
	 * @return
	 */
	public boolean clickCsvReportButton(){
		boolean result = false
		TestObject btnCsvReport = findTestObject("Object Repository/OR_TestResult/btn_CsvReport")
		if(WebUI.waitForElementPresent(btnCsvReport, 30)){
			WebUI.click(btnCsvReport)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the csv report button")
		}
		return result
	}
	/**
	 * Click test case title name
	 * @return
	 */
	public boolean clickTestCaseTitleName(){
		boolean result = false
		TestObject testCaseTitle = findTestObject("Object Repository/OR_TestResult/span_TestCaseTitleOnDetailPage")
		if(WebUI.waitForElementVisible(testCaseTitle,30)){
			WebUI.click(testCaseTitle)
			result = true
			WebUI.delay(2)
			MesmerLogUtils.logInfo("Test case name clicked")
		}
		else{
			MesmerLogUtils.markFailed("Test case title field not found")
		}
		return result
	}
	/**
	 * Get the title input field
	 * @return
	 */
	public boolean checkIfTitleInputFieldAvailable(){
		boolean result = false
		TestObject inputField = findTestObject('Object Repository/OR_TestDetails/input_App Map_ng-untouched ng-valid ng-dirty')
		if(WebUI.waitForElementVisible(inputField, 30)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Test case title input field not visible")
		}
		return result
	}
	/**
	 * Set title name in input field without enter
	 * @param name
	 * @return
	 */
	public boolean setTitleNameWithoutEnter(String name){
		boolean result = false
		if(checkIfTitleInputFieldAvailable()){
			TestObject inputField = findTestObject('Object Repository/OR_TestResult/input_AddTestCaseTitle')
			WebUI.setText(inputField,name)
			result = true
		}
		else
		{
			MesmerLogUtils.logInfo("Input field for title not available")
		}
		return result
	}

	/**
	 * Set the name in title input field
	 * @param name
	 * @return
	 */
	public boolean setTitleInInputField(String testName){
		boolean result = false
		TestObject inputField = findTestObject('Object Repository/OR_TestResult/input_AddTestCaseTitle')
		if(Utility.isMac()){
			WebUI.sendKeys(inputField, Keys.chord(Keys.COMMAND, 'a'))
		}
		else{
			WebUI.sendKeys(inputField, Keys.chord(Keys.CONTROL, "a"))
		}
		WebUI.sendKeys(inputField, Keys.chord(Keys.chord(Keys.BACK_SPACE)))
		WebUI.delay(2)
		WebUI.setText(inputField,testName)
		WebUI.delay(2)
		sendEnterKey()
		result = true
		WebUI.delay(2)
		return result
	}

	/**
	 * Set the name in title input field
	 * @param name
	 * @return
	 */
	public boolean editTitleInInputField(String testName){
		boolean result = false
		try{
			TestObject inputField = findTestObject('Object Repository/OR_TestResult/input_EditTitle')
			if(Utility.isMac()){
				WebUI.sendKeys(inputField, Keys.chord(Keys.COMMAND, 'a'))
			}
			else{
				WebUI.sendKeys(inputField, Keys.chord(Keys.CONTROL, "a"))
			}
			WebUI.sendKeys(inputField, Keys.chord(Keys.chord(Keys.BACK_SPACE)))
			//		WebUI.clearText(inputField)
			WebUI.delay(2)
			WebUI.setText(inputField,testName)
			WebUI.delay(2)
			sendEnterKey()
			WebUI.delay(2)
			result = true
			WebUI.delay(2)
		}
		catch(Exception e){
			e.printStackTrace()
			clickEditTestCaseIcon()
		}

		return result
	}

	public void sendEnterKey(){
		WebUI.sendKeys(null, Keys.chord(Keys.ENTER))
		WebUI.delay(2)
	}
	/**
	 * Get test case title text
	 * @return
	 */
	public String getTestCaseTitleName(){
		String result = ""
		TestObject testCaseTitle = findTestObject("Object Repository/OR_TestResult/span_TestCaseTitleOnDetailPage")
		if(WebUI.waitForElementVisible(testCaseTitle,60)){
			result = WebUI.getText(testCaseTitle)
		}
		else{
			MesmerLogUtils.markFailed("Test case title field not found")
		}
		return result
	}
	/**
	 * Clear the input title field
	 * @return
	 */
	public boolean clearTitleInputField(){
		boolean result = false
		if(checkIfTitleInputFieldAvailable()){
			TestObject inputField = findTestObject('Object Repository/OR_TestDetails/input_App Map_ng-untouched ng-valid ng-dirty')
			WebUI.clearText(inputField)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Test case title input field not available")
		}
		return result
	}

	/**
	 * Set test case name
	 * @param name
	 * @return
	 */
	public boolean setTestCaseName(String name){
		boolean result = false
		if(clickTestCaseTitleName()){
			if(setTitleInInputField(name)){
				result = true
			}
		}
		else{
			MesmerLogUtils.logInfo("Test case title field not clicked")
		}
		return result
	}
	/**
	 * Edit the test case title based on the action
	 * @param name
	 * @param actionName
	 * @return
	 */
	public boolean editTestCaseName(String name, String actionName){
		boolean result = false
		if(actionName.equalsIgnoreCase("clickTitle")){
			if(clickTestCaseTitleName()){
				if(editTitleInInputField(name)){
					String editedName = getTestCaseTitleName()
					while(!editedName.contains(name)){
						editTitleInInputField(name)
					}
					result = true
				}
				else{
					MesmerLogUtils.logInfo("Title did not set properly")
				}
			}
		}
		else if(actionName.equalsIgnoreCase("clickEditIcon")){
			if(clickEditTestCaseIcon()){
				if(editTitleInInputField(name)){
					String editedName = getTestCaseTitleName()
					while(!editedName.contains(name)){
						editTitleInInputField(name)
					}
					result = true
				}

			}
			else{
				MesmerLogUtils.logInfo("Edit title icon not clicked")
			}
		}
		return result
	}

	/**
	 * Click edit icon
	 * @return
	 */
	public boolean clickEditTestCaseIcon(){
		boolean result = false
		TestObject editIcon = findTestObject("Object Repository/OR_TestResult/icon_EditTitle")
		if(WebUI.waitForElementVisible(editIcon,30)){
			WebUI.click(editIcon)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test case edit icon not found")
		}
		return result
	}

	/**
	 * Click edit close icon
	 * @return
	 */
	public boolean clickEditCloseIcon(){
		boolean result = false
		TestObject closeIcon = findTestObject("Object Repository/OR_TestDetails/icon_CloseEdit")
		if(WebUI.waitForElementVisible(closeIcon,30)){
			WebUI.click(closeIcon)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test case edit close icon not found")
		}
		return result
	}
	/**
	 * Check and click the base line text appearing in first row
	 * @return
	 */
	public boolean clickBaseLineText(){
		boolean result = false
		TestObject baseLine = findTestObject("Object Repository/OR_TestResult/title_baseLine")
		if(WebUI.waitForElementVisible(baseLine,30)){
			WebUI.click(baseLine)
			result = true
			MesmerLogUtils.logInfo("Baseline text clicked")
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Base line title not found")
		}
		return result
	}
	/**
	 * Returns the size of replayed tests list
	 * @return
	 */
	public int getReplayedTestsListSize(){
		int size = 0
		TestObject listReplayedTestsObject = findTestObject("Object Repository/OR_TestResult/list_ReplayedTests")
		WebDriver driver = DriverFactory.getWebDriver()
		String itemXPath = listReplayedTestsObject.findPropertyValue("xpath")
		List<WebElement> itemElement = driver.findElements(By.xpath(itemXPath))
		if(itemElement != null){
			size = itemElement.size()
		}
		return size
	}
	/**
	 * Click the replayed test header bar
	 * @return
	 */
	public boolean clickReplayedTestTitleBar(){
		boolean result = false
		if(getReplayedTestsListSize() > 1){
			String replayedTestHeaderBarXPath = '//div[starts-with(@class,"deviceStream")]/div[2]/div[@class="titleSection"]'
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement itemElement = driver.findElement(By.xpath(replayedTestHeaderBarXPath))
			if(itemElement != null){
				itemElement.click()
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Replayed test header bar not clicked")
			}
		}
		else{
			MesmerLogUtils.markWarning("List size is less than 2")
		}
		return result
	}
	/**
	 * Click the baseline or replay tab based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public boolean clickBaselineReplayTab(int type){
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream")]/div['+type+']/div[contains(@class,"expandDevice")]'
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement itemElement = driver.findElement(By.xpath(xpath))
			if(itemElement != null){
				itemElement.click()
				result = true
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}
	/**
	 * Get the baseline or replay tab text based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public String getBaselineReplayTabDeviceName(int type){
		String result = ""
		String xpath = '//div[starts-with(@class,"deviceStream")]/descendant::div[@class="deviceNameExecutionDate"]['+type+']/div[starts-with(@class,"deviceName")]'
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement itemElement = driver.findElement(By.xpath(xpath))
			if(itemElement != null){
				result = itemElement.getText()
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}
	/**
	 * Get the baseline or replay tab device info elements based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public List<WebElement> getBaselineReplayTabDeviceInfoElemnents(int type){
		List<WebElement> result = null
		String xpath = '//div[starts-with(@class,"deviceStream")]/descendant::div[@class="deviceInfo"]['+type+']/span'
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
			if(itemElements != null && itemElements.size() > 0){
				result = itemElements
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}
	/**
	 * Click the dots icon on Baseline or Replayed test based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public boolean clickBaselineReplayedTestDotsIcon(int type){
		//div[@class="popover-content popover-body"]/div/a/span[text()="Download Results"]
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream")]/descendant::div[@class="statusSection"]['+type+']/div/a[@class="iconBtn ellipsis CP"]'
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement itemElement = driver.findElement(By.xpath(xpath))
			if(itemElement != null){
				itemElement.click()
				result = true
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}
	/**
	 * Click the baseline or replayed tests popover options based on the provided action name
	 * @param actionName
	 * @return
	 */
	public boolean clickThePopOverActions(String actionName){
		boolean result = false
		String xpath = '//div[@class="popover-content popover-body"]/div/a/span[text()="'+actionName+'"]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null && itemElement.isDisplayed()){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("The element for the provided action in popover not found")
		}
		return result
	}

	/**
	 * Check and click the cross button on play video dialog
	 * @return
	 */
	public boolean clickVideoDialogCloseButton(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/btn_CloseWatchVideo")
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Close button on watch video dialog not found")
		}
		return result
	}
	/**
	 * Check and click the play video button on play video dialog
	 * @return
	 */
	public boolean clickVideoPlayButton(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/btn_PlayVideo")
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Play button on watch video dialog not found")
		}
		return result
	}

	/**
	 * Check and click the pause video button on play video dialog
	 * @return
	 */
	public boolean clickVideoPauseButton(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/btn_PauseVideo")
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Pause button on watch video dialog not found")
		}
		return result
	}
	/**
	 * Check if the play video view is loaded
	 * @return
	 */
	public boolean checkIfVideoViewLoaded(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/view_VideoLoaded")
		if(WebUI.waitForElementVisible(obj,120)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Video view not loaded within 2 minutes")
		}
		return result
	}

	/**
	 * Check and click the Re run button
	 * @return
	 */
	public boolean clickRerunButton(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestResult/btn_Rerun")
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Re run button not found")
		}
		return result
	}
	/**
	 * Check if the re-run in queue dialog appears
	 * @return
	 */
	public boolean checkIfReRunInInQueueDialogAppears(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/msg_TestCaseAlreadyInProgress")
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Re run in pregress dialog not found")
		}
		return result
	}

	/**
	 * Check and click the Got It button
	 * @return
	 */
	public boolean clickGotItButton(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/btn_GotIt")
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Got it button not found")
		}
		return result
	}

	/**
	 * Check and click the Cancel Run button
	 * @return
	 */
	public boolean clickCancelRunButton(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/btn_CancelRun")
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Cancel run button not found")
		}
		return result
	}

	/**
	 * Check if the re-run in-progress alert appears
	 * @return
	 */
	public boolean checkIfReRunInProgressAlertAppears(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/alert_TestCaseAlreadyInProgress")
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Re run in pregress alert not found")
		}
		return result
	}

	/**
	 * Click the right arrow on the baseline or replay tab elements based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public boolean clickBaselineReplayedTestRightArrow(int type){
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream")]/div['+type+']/div[contains(@class,"expandDevice")]/descendant::a[@class="rightArrow ng-star-inserted"]'
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement itemElement = driver.findElement(By.xpath(xpath))
			if(itemElement != null){
				itemElement.click()
				result = true
				WebUI.delay(2)
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}
	/**
	 * Returns the list of screens of a replayed test based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public List<WebElement> getReplayedTestScreensList(int type){
		List<WebElement> result = null
		String xpath = '//div[@class="testCasesScroll"]['+type+']/mat-card'
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
			if(itemElements != null && itemElements.size() > 0){
				result = itemElements
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}
	/**
	 * Check if the baseline or replay tabs are expanded based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public boolean checkIfBaselineORReplayTabExpaned(int type){
		boolean result = false
		String xpath = '//div[contains(@class,"expandDevice")]['+type+']'
		try{
			TestObject obj = new TestObject("objectName")
			obj.addProperty("xpath", ConditionType.EQUALS,xpath)
			if(WebUI.waitForElementVisible(obj,15)){
				result = true
				if(type == 1){
					MesmerLogUtils.logInfo("Baseline tab expanded")
				}
				else{
					MesmerLogUtils.logInfo("Replay tab expanded")
				}
				WebUI.delay(2)
			}
			else{

			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	/**
	 * Click the baseline or replayed test header bar based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public boolean clickBaselineReplayedTestTitleBar(int type){
		boolean result = false
		String replayedTestHeaderBarXPath = '//div[starts-with(@class,"deviceStream")]/div['+type+']/div[@class="titleSection"]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(replayedTestHeaderBarXPath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Replayed test header bar not clicked")
		}
		return result
	}
	/**
	 * Expand the baseline or replayed test header bar based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public boolean expandBaselineORReplayTab(int type){
		boolean result = false
		try{
			if(checkIfBaselineORReplayTabExpaned(type)){
				result = true
				WebUI.delay(2)
			}
			else{
				if(clickBaselineReplayedTestTitleBar(type)){
					result = true
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.logInfo("Tab title not clicked")
				}
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}
	/**
	 * Check the 3 options in dropdown list
	 * @return
	 */
	public boolean checkDotsDropDownOptions(){
		boolean result = false
		if(clickDotsOptionButton()){
			//a[@class="dropdown-popover-item CP"]/span[contains(text(),"Download Results")]
			String xpath = '//a[@class="dropdown-popover-item CP"]/span'
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
			if(itemElements != null && itemElements.size() == 3){
				int count = 0
				for(int i = 0; i < itemElements.size(); i++){
					WebElement item = itemElements.get(i)
					if(item != null && item.getText().equalsIgnoreCase("Download Results")
					|| item.getText().equalsIgnoreCase("Show Comments")
					|| item.getText().equalsIgnoreCase("Delete")){
						count++
					}
					if(count == 3){
						count = 0
						result = true
						break
					}
				}
			}
		}
		return result
	}
	/**
	 * Click the drop down option based on the provided action
	 * @param action
	 * @return
	 */
	public boolean clickDropDownOption(String action){
		boolean result = false
		String xpath = '//a[@class="dropdown-popover-item CP"]/span[contains(text(),"'+action+'")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null && itemElement.isDisplayed()){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("The element for the provided action not found")
		}

		return result
	}
	/**
	 * Find the comments text area and add a new comment
	 * @param message
	 * @return
	 */
	public boolean addCommentToTextArea(String message){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/textarea_comments")
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			WebUI.delay(2)
			WebUI.sendKeys(obj, message)
			WebUI.delay(2)
			TestObject btnAddComments = findTestObject("Object Repository/OR_TestDetails/btn_AddComment")
			if(WebUI.waitForElementVisible(btnAddComments,30)){
				WebUI.click(btnAddComments)
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Button add comments not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Text area to add comments not found")
		}
		return result
	}
	/**
	 * Hide comments area
	 * @return
	 */
	public boolean hideComments(){
		boolean result = false
		TestObject obj = findTestObject("Object Repository/OR_TestDetails/btn_HideComments")
		if(WebUI.waitForElementVisible(obj, 10)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			if(clickDotsOptionButton()){
				if(WebUI.waitForElementVisible(obj, 10)){
					WebUI.click(obj)
					result = true
					WebUI.delay(2)
				}
			}
		}
		return result
	}

	private int getNumberOutOfString(String response){
		if(response){
			def numberList = response.findAll( /\d+/ )*.toInteger()
			if(numberList.size() == 1)
			{
				return numberList.get(0) as Integer

			}
			else
			{
				return -1
			}
		}
	}

	public boolean checkDevicesElements(){
		boolean result = false
		List<WebElement> searchedDevicesList = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebUI.delay(4)
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/OR_CreateNewTestCase/div_DevicesWindow"), 60)){
			if(Utility.checkIfNoDeviceAvailable()){
				MesmerLogUtils.markFailed("No device available in the list")
				return searchedDevicesList
			}

			// Get devices list
			String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
			List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
			if(devicesList != null && devicesList.size() > 0){
				searchedDevicesList = new ArrayList<WebElement>()
				for(int i=0; i < devicesList.size(); i++){
					String deviceNameXP = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="infoText ng-star-inserted"]/span[1]'
					WebElement deviceNameElement = driver.findElement(By.xpath(deviceNameXP))
					String osVersionXP = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="infoText"]'
					WebElement osVersionElement = driver.findElement(By.xpath(osVersionXP))
					String deviceTypeXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="mesmerSimpleTooltip ng-star-inserted"]/span[@class="deviceUDID singleEllipses"]'
					WebElement deviceTypeElement = driver.findElement(By.xpath(deviceTypeXPath))
					String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div'
					WebElement deviceStatusElement = driver.findElement(By.xpath(devicesStatusXPath))
					String copyUDIDXP = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="mesmerSimpleTooltip ng-star-inserted"]/span[@class="copyUDID"]'
					WebElement copyUDIDElement = driver.findElement(By.xpath(copyUDIDXP))
					if(deviceNameElement != null && deviceNameElement.isDisplayed()){
						if(osVersionElement != null && osVersionElement.isDisplayed()){
							if(deviceTypeElement != null && deviceTypeElement.isDisplayed() &&
							(deviceTypeElement.getText().contains("Virtual") || deviceTypeElement.getText().contains("Physical"))){
								if(deviceStatusElement != null && (deviceStatusElement.getText().contains("Ready") ||
								deviceStatusElement.getText().contains("Provisioned"))){
									if(copyUDIDElement != null && copyUDIDElement.isDisplayed()){
										copyUDIDElement.click()
										WebUI.delay(2)
										result = true
										break
									}
								}
							}
						}
					}
				}
			}
			else{
				MesmerLogUtils.markWarning("There is no device in the list")
			}
		}
		else{
			MesmerLogUtils.markWarning("Devices list window not appeared")
		}
		return result
	}
}
