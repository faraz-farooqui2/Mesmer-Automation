package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import org.openqa.selenium.JavascriptExecutor;

public class ManageTestController {

	private static ManageTestController mInstance= null

	private ManageTestController(){
	}

	public static ManageTestController getInstance(){
		if(mInstance == null){
			mInstance = new ManageTestController()
		}

		return mInstance
	}
	/**
	 * This method will be used to verify the elements of manage test screen statuses
	 * @return
	 */
	public boolean verifyManageTestStatuses(){
		boolean result = false
		TestObject countTotalTestCases = findTestObject('Object Repository/OR_ManageTestCases/count_totalTestCases')
		TestObject textTotalTC = findTestObject('Object Repository/OR_ManageTestCases/text_testCases')
		if(WebUI.waitForElementPresent(countTotalTestCases,30) && WebUI.waitForElementPresent(textTotalTC,30)){
			TestObject countPassedTestCases = findTestObject('Object Repository/OR_ManageTestCases/count_passedTestCases')
			TestObject textPassed = findTestObject('Object Repository/OR_ManageTestCases/text_passed')
			if(WebUI.waitForElementPresent(countPassedTestCases,30) && WebUI.waitForElementPresent(textPassed,30)){
				TestObject countFailedTestCases = findTestObject('Object Repository/OR_ManageTestCases/count_failedTestCases')
				TestObject textFailed = findTestObject('Object Repository/OR_ManageTestCases/text_failed')
				if(WebUI.waitForElementPresent(countFailedTestCases,30) && WebUI.waitForElementPresent(textFailed,30)){
					TestObject countBrokenTestCases = findTestObject('Object Repository/OR_ManageTestCases/count_brokenTestCases')
					TestObject textBroken = findTestObject('Object Repository/OR_ManageTestCases/text_broken')
					if(WebUI.waitForElementPresent(countBrokenTestCases,30) && WebUI.waitForElementPresent(textBroken,30)){
						result = true
					}
					else{
						MesmerLogUtils.markFailed("Either broken tests count or label not found")
					}
				}
				else{
					MesmerLogUtils.markFailed("Either failed tests count or label not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("Either passed tests count or label not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Either test cases count or label not found")
		}


		return result
	}
	/**
	 * Get the count of all test cases
	 * @return
	 */
	public int getAllTestCasesStatusCount(){
		TestObject countTotalTestCases = findTestObject('Object Repository/OR_ManageTestCases/count_totalTestCases')
		String count = WebUI.getText(countTotalTestCases)
		return Integer.parseInt(count)
	}
	/**
	 * Click the test cases label
	 * @return
	 */
	public boolean clickAllTestCasesLabel(){
		boolean result = false
		TestObject textTotalTC = findTestObject('Object Repository/OR_ManageTestCases/text_testCases')
		if(WebUI.waitForElementPresent(textTotalTC,30)){
			WebUI.click(textTotalTC)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test cases label not found")
		}

		return result
	}

	/**
	 * Get the count of passed test cases
	 * @return
	 */
	public int getPassedTestCasesStatusCount(){
		TestObject countPassedTestCases = findTestObject('Object Repository/OR_ManageTestCases/count_passedTestCases')
		String count = WebUI.getText(countPassedTestCases)
		return Integer.parseInt(count)
	}
	/**
	 * Click the passed test cases label
	 * @return
	 */
	public boolean clickPassedTestCasesLabel(){
		boolean result = false
		TestObject textPassed = findTestObject('Object Repository/OR_ManageTestCases/text_passed')
		if(WebUI.waitForElementPresent(textPassed,30)){
			WebUI.click(textPassed)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Passed test cases label not found")
		}

		return result
	}

	/**
	 * Get the count of failed test cases
	 * @return
	 */
	public int getFailedTestCasesStatusCount(){
		TestObject countFailedTestCases = findTestObject('Object Repository/OR_ManageTestCases/count_failedTestCases')
		String count = WebUI.getText(countFailedTestCases)
		return Integer.parseInt(count)
	}
	/**
	 * Click the failed test cases label
	 * @return
	 */
	public boolean clickFailedTestCasesLabel(){
		boolean result = false
		TestObject textFailed = findTestObject('Object Repository/OR_ManageTestCases/text_failed')
		if(WebUI.waitForElementPresent(textFailed,30)){
			WebUI.click(textFailed)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Failed test cases label not found")
		}

		return result
	}

	/**
	 * Get the count of broken test cases
	 * @return
	 */
	public int getBrokenTestCasesStatusCount(){
		TestObject countBrokenTestCases = findTestObject('Object Repository/OR_ManageTestCases/count_brokenTestCases')
		String count = WebUI.getText(countBrokenTestCases)
		return Integer.parseInt(count)
	}
	/**
	 * Click the broken test cases label
	 * @return
	 */
	public boolean clickBrokenTestCasesLabel(){
		boolean result = false
		TestObject textBroken = findTestObject('Object Repository/OR_ManageTestCases/text_broken')
		if(WebUI.waitForElementPresent(textBroken,30)){
			WebUI.click(textBroken)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Broken test cases label not found")
		}

		return result
	}

	/**
	 * Get the count of needs review test cases
	 * @return
	 */
	public int getNeedsReviewTestCasesStatusCount(){
		TestObject countNeedsReviewTestCases = findTestObject('Object Repository/OR_ManageTestCases/count_needsReviewTestCases')
		String count = WebUI.getText(countNeedsReviewTestCases)
		return Integer.parseInt(count)
	}
	/**
	 * Click the needs review test cases label
	 * @return
	 */
	public boolean clickNeedsReviewTestCasesLabel(){
		boolean result = false
		TestObject textNeedsReview = findTestObject('Object Repository/OR_ManageTestCases/text_needsReview')
		if(WebUI.waitForElementPresent(textNeedsReview,30)){
			WebUI.click(textNeedsReview)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Needs review test cases label not found")
		}

		return result
	}

	/**
	 * Click reRun icon
	 * @return
	 */
	public boolean clickReRunButton(){
		boolean result = false
		TestObject btnReRun = findTestObject('Object Repository/OR_ManageTestCases/icon_reRun')
		if(WebUI.waitForElementPresent(btnReRun,30)){
			WebUI.click(btnReRun)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("reRun button not found")
		}

		return result
	}

	/**
	 * Click new test icon
	 * @return
	 */
	public boolean clickCreateNewTestButton(){
		boolean result = false
		TestObject btnNewTest = findTestObject('Object Repository/OR_CreateNewTestCase/a_newTest_OnManageTestScreen')
		if(WebUI.waitForElementPresent(btnNewTest,30)){
			WebUI.click(btnNewTest)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("New test button not found")
		}

		return result
	}

	/**
	 * Check the select device header
	 * @return
	 */
	public boolean checkIfSelectDeviceHeaderVisible(){
		boolean result = false
		TestObject headerSelectDevice = findTestObject('Object Repository/OR_ManageTestCases/popup_SelectDevices')
		if(WebUI.waitForElementPresent(headerSelectDevice,30)){
			result = true
			WebUI.delay(1)
		}
		else{
			MesmerLogUtils.markFailed("Select device header not found")
		}

		return result
	}

	/**
	 * Check if no device text appears
	 * @return
	 */
	public boolean checkIfNoDeviceTextVisible(){
		boolean result = false
		TestObject textNoDevice = findTestObject('Object Repository/OR_ManageTestCases/text_NoDeviceAvailable')
		if(WebUI.waitForElementPresent(textNoDevice,30)){
			result = true
			WebUI.delay(1)
		}
		else{
			MesmerLogUtils.logInfo("No device text not found")
		}

		return result
	}

	/**
	 * Click the run button on select device pop up
	 * @return
	 */
	public boolean clickButtonRun(){
		boolean result = false
		TestObject btnRun = findTestObject('Object Repository/OR_ManageTestCases/btn_Run')
		if(WebUI.waitForElementPresent(btnRun,30)){
			WebUI.click(btnRun)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Run button on select device popup not found")
		}

		return result
	}

	/**
	 * Check the run confirmation dialog
	 * @return
	 */
	public boolean checkRunConfirmationDialog(){
		boolean result = false
		TestObject runConfirmationDialog = findTestObject('Object Repository/OR_ManageTestCases/dialog_runConfirmation')
		if(WebUI.waitForElementPresent(runConfirmationDialog,30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Run confirmation dialog not appeared")
		}

		return result
	}

	/**
	 * Click yes button on run confirmation dialog
	 * @return
	 */
	public boolean clickButtonYes(){
		boolean result = false
		TestObject btnYes = findTestObject('Object Repository/OR_ManageTestCases/btn_Yes')
		if(WebUI.waitForElementPresent(btnYes,30)){
			WebUI.click(btnYes)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Yes button not found")
		}

		return result
	}

	/**
	 * Click copy button to copy the selected test case
	 * @return
	 */
	public boolean clickButtonCopy(){
		boolean result = false
		TestObject btnCopy = findTestObject('Object Repository/OR_ManageTestCases/button_CopyTestCaseToOtherProject')
		if(WebUI.waitForElementPresent(btnCopy,30)){
			WebUI.click(btnCopy)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Copy button not found")
		}

		return result
	}

	/**
	 * Check the copy to other projects header
	 * @return
	 */
	public boolean checkIfCopyToOtherProjectPresent(){
		boolean result = false
		TestObject copyToOtherProjectPop = findTestObject('Object Repository/OR_ManageTestCases/verify_CopyToProjectPopOver')
		if(WebUI.waitForElementPresent(copyToOtherProjectPop,30)){
			result = true
			WebUI.delay(1)
		}
		else{
			MesmerLogUtils.markFailed("Copy to other projects option not found")
		}

		return result
	}

	/**
	 * Click select project tick icon
	 * @return
	 */
	public boolean clickSelectProjectTick(){
		boolean result = false
		TestObject projectTick = findTestObject('Object Repository/OR_ManageTestCases/click_SelectProjectTick')
		if(WebUI.waitForElementPresent(projectTick,30)){
			WebUI.click(projectTick)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Select project tick icon not found")
		}

		return result
	}

	/**
	 * Click copy all test cases button
	 * @return
	 */
	public boolean clickCopyAllTestCases(){
		boolean result = false
		TestObject btnCopyAllTestCases = findTestObject('Object Repository/OR_ManageTestCases/button_CopyAllTestCases')
		if(WebUI.waitForElementPresent(btnCopyAllTestCases,30)){
			WebUI.click(btnCopyAllTestCases)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Copy all test cases button not found")
		}

		return result
	}

	/**
	 * Click copy all test cases yes button
	 * @return
	 */
	public boolean clickCopyAllTestCasesConfirmationYes(){
		boolean result = false
		TestObject btnCopyAllTestCasesYes = findTestObject('Object Repository/OR_ManageTestCases/button_ConfirmationDialogueYes')
		if(WebUI.waitForElementVisible(btnCopyAllTestCasesYes,30)){
			WebUI.delay(2)
			WebUI.click(btnCopyAllTestCasesYes)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Copy all test cases yes button not found")
		}

		return result
	}

	/**
	 * Click delete test cases yes button
	 * @return
	 */
	public boolean clickDeleteTestCaseYes(){
		boolean result = false
		TestObject btnDeleteTestCasesYes = findTestObject('Object Repository/OR_ManageTestCases/button_deleteTCConfirmationYes')
		if(WebUI.waitForElementPresent(btnDeleteTestCasesYes,30)){
			WebUI.click(btnDeleteTestCasesYes)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Delete test case confirmation dialog yes button not found")
		}

		return result
	}

	/**
	 * Click delete test cases no button
	 * @return
	 */
	public boolean clickDeleteTestCaseNo(){
		boolean result = false
		TestObject btnDeleteTestCasesNo = findTestObject('Object Repository/OR_ManageTestCases/button_deleteTCConfirmationNo')
		if(WebUI.waitForElementPresent(btnDeleteTestCasesNo,30)){
			WebUI.click(btnDeleteTestCasesNo)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Delete test case confirmation dialog no button not found")
		}

		return result
	}

	/**
	 * Checks if duplicate test case alert appears, then click the yes button
	 * @return
	 */
	public boolean verifyDuplicateTestCaseAlert(){
		boolean result = false
		// Wait and check if confirmation dialog appears
		TestObject dialogDuplicateTestCase = findTestObject('Object Repository/OR_ManageTestCases/dialog_DuplicateTestCase')
		if(WebUI.waitForElementVisible(dialogDuplicateTestCase, 30)){
			if(clickButtonYes()){
				result = true
			}
			else{
				MesmerLogUtils.logInfo('Button yes not clicked')
			}
		}
		else{
			MesmerLogUtils.markFailed('Duplicate test case confirmation dialog not appeared')
		}

		return result
	}

	/**
	 * Select the random test cases starting from the first
	 * @return
	 */
	public boolean selectRandomTestCases(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		try{
			String testCasesListXpath = findTestObject('Object Repository/OR_ManageTestCases/xpath_testCasesList').findPropertyValue('xpath').toString()
			List<WebElement> testCasesList = driver.findElements(By.xpath(testCasesListXpath))
			if(testCasesList != null && testCasesList.size() > 0){
				String numberOFcheckboxesXpath = findTestObject('Object Repository/OR_ManageTestCases/xpath_numberOfCheckBoxes').findPropertyValue('xpath').toString()
				List<WebElement> numberOFcheckboxes = driver.findElements(By.xpath(numberOFcheckboxesXpath))
				int checkBoxesSize = numberOFcheckboxes.size();
				if(numberOFcheckboxes.size() > 3){
					checkBoxesSize = 3;
				}
				for(int i=0; i<=checkBoxesSize; i++){
					numberOFcheckboxes.get(i).click()
					WebUI.delay(1)
					if(i == checkBoxesSize){
						result = true
					}
				}

			}
			else{
				MesmerLogUtils.markWarning("There is no test case in the list")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}
	public List<WebElement> getTestCasesList(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(1)
		String testCasesListXpath = findTestObject('Object Repository/OR_ManageTestCases/xpath_testCasesList').findPropertyValue('xpath').toString()
		List<WebElement> testCasesList = driver.findElements(By.xpath(testCasesListXpath))

		return testCasesList
	}
	/**
	 * This method will be used to find any specific test case and do the provided action.
	 * Actions can be clone, download, delete, comments and assignUser
	 * @param testCaseName
	 * @param actionName
	 */
	public boolean findTestCaseAndPerformAction(String testCaseName, String actionName){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(1)
		String testCasesListXpath = findTestObject('Object Repository/OR_ManageTestCases/xpath_testCasesList').findPropertyValue('xpath').toString()
		List<WebElement> testCasesList = driver.findElements(By.xpath(testCasesListXpath))
		if(testCasesList != null && testCasesList.size() > 0){
			for(int i= 0; i < testCasesList.size(); i++){
				String titleXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneLeft"]/descendant::span[starts-with(@class,"titleLimit")]'
				WebElement testCaseTitle = driver.findElement(By.xpath(titleXPath))
				if(testCaseTitle != null && testCaseTitle.getText().equals(testCaseName)){
					WebUI.delay(2)
					TestObject to = new TestObject("objectName")
					String spanXpathText = findTestObject('Object Repository/OR_ManageTestCases/xpath_span').findPropertyValue('xpath').toString()
					to.addProperty("xpath", ConditionType.EQUALS, spanXpathText +testCaseName+'"]')
					WebUI.scrollToElement(to, 10)
					WebUI.mouseOver(to)
					WebUI.delay(2)
					if(actionName.equalsIgnoreCase("select")){
						WebUI.click(to)
						result = true
					}
					else if(actionName.equalsIgnoreCase("clone")){
						String cloneXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon clone"]'
						WebElement iconClone = driver.findElement(By.xpath(cloneXPath))
						if(iconClone != null){
							iconClone.click()
							result = true
							WebUI.delay(2)
						}
						else{
							WebElement iconDots = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
							if(iconDots != null){
								iconDots.click()
								WebUI.delay(2)
								cloneXPath = findTestObject('Object Repository/OR_ManageTestCases/xpath_Clone').findPropertyValue('xpath').toString()
								//cloneXPath = '//popover-container/div[@class="popover-content popover-body"]/div/a/span[@class="icon clone"]'
							}
							List<WebElement> popOverCloneIcon = driver.findElements(By.xpath(cloneXPath))
							if(popOverCloneIcon != null && popOverCloneIcon.size() > 0){
								popOverCloneIcon.get(0).click()
								result = true
							}
						}
					}
					else if(actionName.equalsIgnoreCase("testData")){
						// Add Test data
						String testDataXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon testData"]'
						WebElement iconTestData = driver.findElement(By.xpath(testDataXPath))
						if(iconTestData != null){
							iconTestData.click()
							result = true
							WebUI.delay(2)
						}
						else{
							WebElement iconDotsTestData = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
							if(iconDotsTestData != null){
								iconDotsTestData.click()
								WebUI.delay(2)
								testDataXPath = findTestObject('Object Repository/OR_ManageTestCases/xpath_addTestData').findPropertyValue('xpath').toString()
							}
							List<WebElement> popOverTestDataIcon = driver.findElements(By.xpath(testDataXPath))
							if(popOverTestDataIcon != null && popOverTestDataIcon.size() > 0){
								popOverTestDataIcon.get(0).click()
								result = true
							}
						}
					}
					else if(actionName.equalsIgnoreCase("download")){
						// Download assests
						String downloadXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon download"]'
						WebElement iconDownload = driver.findElement(By.xpath(downloadXPath))
						if(iconDownload != null){
							iconDownload.click()
							result = true
							WebUI.delay(2)
						}
						else{
							WebElement iconDots1 = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
							if(iconDots1 != null){
								iconDots1.click()
								WebUI.delay(2)
								downloadXPath = findTestObject('Object Repository/OR_ManageTestCases/xpath_download').findPropertyValue('xpath').toString()
							}
							List<WebElement> popOverDownloadIcon = driver.findElements(By.xpath(downloadXPath))
							if(popOverDownloadIcon != null && popOverDownloadIcon.size() > 0){
								popOverDownloadIcon.get(0).click()
								result = true
							}
						}
					}
					else if(actionName.equalsIgnoreCase("delete")){
						String deleteXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon delete"]'
						WebElement iconDelete = driver.findElement(By.xpath(deleteXPath))
						if(iconDelete != null){
							iconDelete.click()
							if(clickDeleteTestCaseYes()){
								result = true
								WebUI.delay(2)
							}
						}
						else{
							WebElement iconDots = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
							if(iconDots != null){
								iconDots.click()
								WebUI.delay(2)
								deleteXPath = findTestObject('Object Repository/OR_ManageTestCases/xpath_delete').findPropertyValue('xpath').toString()
							}
							List<WebElement> popOverDeleteIcon = driver.findElements(By.xpath(deleteXPath))
							if(popOverDeleteIcon != null && popOverDeleteIcon.size() > 0){
								popOverDeleteIcon.get(0).click()
								if(clickDeleteTestCaseYes()){
									result = true
									WebUI.delay(2)
								}
							}
						}
					}
					else if(actionName.equalsIgnoreCase("delete-no")){
						String deleteXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon delete"]'
						WebElement iconDelete = driver.findElement(By.xpath(deleteXPath))
						if(iconDelete != null){
							iconDelete.click()
							if(clickDeleteTestCaseNo()){
								result = true
								WebUI.delay(2)
							}
						}
						else{
							WebElement iconDots = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
							if(iconDots != null){
								iconDots.click()
								WebUI.delay(2)
								deleteXPath = findTestObject('Object Repository/OR_ManageTestCases/xpath_delete').findPropertyValue('xpath').toString()
							}
							List<WebElement> popOverDeleteIcon = driver.findElements(By.xpath(deleteXPath))
							if(popOverDeleteIcon != null && popOverDeleteIcon.size() > 0){
								popOverDeleteIcon.get(0).click()
								if(clickDeleteTestCaseNo()){
									result = true
									WebUI.delay(2)
								}
							}
						}
					}
					else if(actionName.equalsIgnoreCase("edit")){
						// Edit icon XPath
						WebElement iconDots = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon editTestCase"]'))
						if(iconDots != null){
							iconDots.click()
							result = true
							WebUI.delay(2)
						}
						else{
							String editXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon editTestCase"]'
							WebElement iconEdit = driver.findElement(By.xpath(editXPath))
							if(iconEdit != null){
								WebUI.delay(2)
								iconEdit.click()
								result = true
								WebUI.delay(2)
							}
						}
					}
					else if(actionName.equalsIgnoreCase("comments")){
						String commentXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/a[@class="comments CP"]'
						WebElement iconComment = driver.findElement(By.xpath(commentXPath))
						if(iconComment != null){
							iconComment.click()
							WebUI.delay(2)
							if(checkCommentsScreenAndAddComment()){
								result = true
							}
						}
						else{
							MesmerLogUtils.markFailed("Comment icon not found")
						}
					}
					else if(actionName.equalsIgnoreCase("assignUser")){
						String avatarXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/a/span[@class="userAvatar"]'
						WebElement iconAvatar = driver.findElement(By.xpath(avatarXPath))
						if(iconAvatar != null){
							iconAvatar.click()
							WebUI.delay(2)
							if(checkForUsersListAndAssign()){
								result = true
							}
							else{
								result = true
								MesmerLogUtils.markWarning("There is no user in the list")
							}
						}
						else{
							MesmerLogUtils.markFailed("avatar icon not found")
						}
					}
					WebUI.delay(2)
					break;
				}
				if(i == (testCasesList.size()-1)){
					MesmerLogUtils.markWarning("Test case in manage tests list not found")
				}
			}
		}
		else{
			MesmerLogUtils.markWarning("There is no test case in the manage tests list")
		}

		return result
	}

	public boolean editTestCaseTitle(String title){
		boolean result = false
		try{
			TestObject inputField = findTestObject('Object Repository/OR_ManageTestCases/input_EditTestCaseTitle')
			if(Utility.isMac()){
				WebUI.sendKeys(inputField, Keys.chord(Keys.COMMAND, 'a'))
			}
			else{
				WebUI.sendKeys(inputField, Keys.chord(Keys.CONTROL, "a"))
			}
			WebUI.sendKeys(inputField, Keys.chord(Keys.chord(Keys.BACK_SPACE)))
			WebUI.clearText(inputField)
			WebUI.delay(2)
			WebUI.setText(inputField,title)
			WebUI.delay(2)
			//			sendEnterKey()
			clickTickIconInEditTest()
			WebUI.delay(2)
			result = true
			WebUI.delay(2)
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean clickTickIconInEditTest(){
		boolean result = false
		try{
			TestObject obj = findTestObject('Object Repository/OR_ManageTestCases/icon_Edit_Tick')
			if(WebUI.waitForElementVisible(obj, 30)){
				WebUI.click(obj)
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Tick icon during title editing not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean clickEditTestIcon(){
		boolean result = false
		try{
			TestObject obj = findTestObject('Object Repository/OR_ManageTestCases/icon_Edit_Tick')
			if(WebUI.waitForElementVisible(obj, 30)){
				WebUI.click(obj)
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Pencil icon during title editing not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	/**
	 * Checks the users list and select one of them
	 * @return
	 */
	public boolean checkForUsersListAndAssign(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String usersListXpath = findTestObject('Object Repository/OR_ManageTestCases/xpath_userList').findPropertyValue('xpath').toString()
		List<WebElement> usersList = driver.findElements(By.xpath(usersListXpath))
		if(usersList != null && usersList.size() > 0){
			usersList.get(0).click()
			result = true
		}

		return result
	}
	/**
	 * This method checks the comments screen and add the comment to input area
	 * @return
	 */
	public boolean checkCommentsScreenAndAddComment(){
		boolean result = false
		TestObject btnAddComment = findTestObject('Object Repository/OR_ManageTestCases/button_addComment')
		if(WebUI.waitForElementVisible(btnAddComment, 30)){
			TestObject textAreaAddComment = findTestObject('Object Repository/OR_ManageTestCases/textArea_AddComment')
			if(WebUI.waitForElementVisible(textAreaAddComment, 30)){
				WebUI.click(textAreaAddComment)
				WebUI.setText(textAreaAddComment, "Test comment")
				WebUI.click(btnAddComment)
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Add comment text area not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Add comment icon not found")
		}

		return result
	}

	public boolean checkAndClickTheScreenShot(String action){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		//		String testScreensListXpath = findTestObject('Object Repository/OR_ManageTestCases/xpath_testScreensList').findPropertyValue('xpath').toString()
		//		List<WebElement> testScreensList = driver.findElements(By.xpath(testScreensListXpath))
		//
		//		//		TestObject btnDots = findTestObject('Object Repository/OR_ManageTestCases/btn_TestCaseScreenDots')
		//		String btnDotsXpath = findTestObject('Object Repository/OR_ManageTestCases/btn_TestCaseScreenDots').findPropertyValue('xpath').toString()
		//		List<WebElement> btnDots = driver.findElements(By.xpath(btnDotsXpath))

		//		String redoFromHereXpath = findTestObject('Object Repository/OR_ManageTestCases/btn_recordFromHere').findPropertyValue('xpath').toString()
		//		WebElement redoFromHereHover = driver.findElement(By.xpath(redoFromHereXpath))

		//		if(testScreensList != null && testScreensList.size() > 1){
		//			//			Actions builder = new Actions(driver);
		//			//			builder.moveToElement(testScreensList.get(1)).click(testScreensList.get(1)).build().perform();
		//			//			WebUI.delay(2)
		//			btnDots.get(1).click()
		//			//			testScreensList.get(1).click()
		//			WebUI.delay(2)


		//			if(WebUI.waitForElementVisible(btnDots, 60)){
		//				WebUI.click(btnDots)
		//			}
		TestObject openTestCaseScreen = findTestObject('Object Repository/OR_UDA/click_ToOpenTestCaseScreen')
		if(WebUI.waitForElementVisible(openTestCaseScreen, 30) ==true){
			WebUI.delay(5)
			WebUI.click(openTestCaseScreen)
			MesmerLogUtils.markPassed("Testcase screen open successfully ")
			WebUI.delay(2)

			if(action == "recordFromHere"){

				TestObject reDoFromHere = findTestObject('Object Repository/OR_ManageTestCases/btn_recordFromHere')

				if(WebUI.waitForElementPresent(reDoFromHere, 60)){
					//WebUI.click(reDoFromHere)

					JavascriptExecutor js = (JavascriptExecutor) driver;
					String redoFromHereXpath = findTestObject('Object Repository/OR_ManageTestCases/btn_recordFromHere').findPropertyValue('xpath').toString()
					WebElement redoFromHereHover = driver.findElement(By.xpath(redoFromHereXpath))


					((JavascriptExecutor) driver).executeScript("arguments[0].click();", redoFromHereHover);
					WebUI.delay(2)

					//					if(redoFromHereHover != null ){
					//						builder.moveToElement(redoFromHereHover).click(redoFromHereHover).build().perform()
					//					}else{
					//						MesmerLogUtils.markFailed('Record from here is null')
					//					}


					if(selectDeviceAndPerformOperation("recordFromHere")){
						result = true
					}
				}
				else{
					MesmerLogUtils.markFailed('Record from here option not available')
				}
			}
			else if(action == "addAssertions"){
				WebUI.delay(2)
				TestObject optionAddAssertion = findTestObject('Object Repository/OR_ManageTestCases/a_Add Assertions')
				if(WebUI.waitForElementVisible(optionAddAssertion, 30)){
					WebUI.click(optionAddAssertion);
					WebUI.delay(5)
					TestObject lblDialogAssertions = findTestObject('Object Repository/OR_ManageTestCases/title_dialog_Assertions')
					if(WebUI.waitForElementVisible(lblDialogAssertions, 30)){
						String screenElementsXPath = findTestObject('Object Repository/OR_ManageTestCases/xpath_screenElement').findPropertyValue('xpath').toString()
						WebElement assertionTypeElementsList = driver.findElement(By.xpath(screenElementsXPath))
						if(assertionTypeElementsList != null){
							assertionTypeElementsList.click()
							WebUI.delay(2)
							String waitXPath = findTestObject('Object Repository/OR_ManageTestCases/xpath_wait').findPropertyValue('xpath').toString()
							WebElement waitElement = driver.findElement(By.xpath(waitXPath))
							if(waitElement != null){
								waitElement.click()
								WebUI.delay(2)
								String assertionTypeXPath = findTestObject('Object Repository/OR_ManageTestCases/xpath_assertionType').findPropertyValue('xpath').toString()
								List<WebElement> assertionTypesList = driver.findElements(By.xpath(assertionTypeXPath))
								if(assertionTypesList != null && assertionTypesList.size() > 1){
									assertionTypesList.get(1).click()
									//div[starts-with(@class,"conditionBlock m-t-0")][2]/div/div[@class="inputAssertion disabledSelect"]/select
									//										WebElement assertionTypeElement = driver.findElement(By.xpath('//div[starts-with(@class,"conditionBlock m-t-0")][2]/div/div[@class="inputAssertion disabledSelect"]/select'))
									//										if(assertionTypeElement != null){
									//											WebUI.delay(2)
									//										}
								}
								TestObject btnCross = findTestObject('Object Repository/OR_ManageTestCases/btn_cross_assertions')
								if(WebUI.waitForElementVisible(btnCross, 30)){
									WebUI.click(btnCross)
									result = true
								}
								else{
									MesmerLogUtils.markFailed('Cross button not visible')
								}
							}
						}
						else{
							MesmerLogUtils.logInfo("Screen assertion elements not found")
						}
					}
					else{
						MesmerLogUtils.markFailed('Assertions dialog not appeared')
					}
				}
				else{
					MesmerLogUtils.markFailed('Add assertion option not available')
				}
			}
			else if(action == "data"){
				WebUI.delay(2)
				TestObject optionData = findTestObject('Object Repository/OR_ManageTestCases/a_Data')
				if(WebUI.waitForElementVisible(optionData, 30)){
					WebUI.click(optionData);
					WebUI.delay(2)
					TestObject lblPreCondition = findTestObject('Object Repository/OR_ManageTestCases/label_UploadPreConditionScript')
					TestObject lblPostCondition = findTestObject('Object Repository/OR_ManageTestCases/label_UploadPostConditionScript')
					TestObject btnCrossStepData = findTestObject('Object Repository/OR_ManageTestCases/btnCross_StepData')
					if(WebUI.waitForElementVisible(lblPreCondition, 30) && WebUI.waitForElementVisible(lblPostCondition, 30)){
						WebUI.click(btnCrossStepData)
						result = true
						WebUI.delay(2)
					}
					else{
						MesmerLogUtils.markFailed('One of the upload conditon script option is not visible')
					}
				}
				else{
					MesmerLogUtils.markFailed('Data option not available')
				}
			}
			else if(action == "clearStep"){
				WebUI.delay(2)
				TestObject optionDelete = findTestObject('Object Repository/OR_ManageTestCases/a_Delete')
				if(WebUI.waitForElementVisible(optionDelete, 30)){
					JavascriptExecutor js = (JavascriptExecutor) driver;
					String optionDeleteXpath = findTestObject('Object Repository/OR_ManageTestCases/a_Delete').findPropertyValue('xpath').toString()
					WebElement optionDeleteHover = driver.findElement(By.xpath(optionDeleteXpath))


					((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionDeleteHover);

					//					WebUI.click(optionDelete);
					//					WebUI.delay(2)
					if(clickButtonYes()){
						result = true
					}
				}
				else{
					MesmerLogUtils.markFailed('Delete option not available')
				}
			}

			//			}
			//			else{
			//				MesmerLogUtils.markFailed('Dots icon not visible')
			//			}
		}
		else{
			MesmerLogUtils.markFailed('There is no test case screen available')
		}

		return result
	}

	private boolean selectDeviceAndPerformOperation(String action){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		if(action == "recordFromHere"){
			if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){
				String devicesListXPath = findTestObject('Object Repository/OR_CreateNewTestCase/deviceList').findPropertyValue('xpath').toString()
				List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
				if(devicesList != null && devicesList.size() > 0){
					devicesList.get(0).click()
					if(CreateTestController.getInstance().clickNextBtn()){
						if(CreateTestController.getInstance().deviceChecks()){
							TestObject navigatingToScreen = findTestObject('Object Repository/OR_CreateNewTestCase/verify_navigatingToSelectedScreen')
							if(WebUI.waitForElementPresent(navigatingToScreen, 120)){
								if(WebUI.waitForElementNotPresent(navigatingToScreen, 240)){
									if(CreateTestController.getInstance().clickDoneGreenButton()){
										if(CreateTestController.getInstance().saveNewTestCase()){
											if(CreateTestController.getInstance().viewTestCase()){
												if(CreateTestController.getInstance().viewAndEditTestCase()){
													result = true
													WebUI.delay(4)
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return result
	}

	private boolean discardTest(){
		boolean result = false
		if(CreateTestController.getInstance().clickDiscardButton()){
			if(CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()){
				MesmerLogUtils.logInfo("Test case discarded successfully")
				result = true
			}
			else{
				MesmerLogUtils.logInfo("Discard alert not appeared")
			}
		}
		else{
			MesmerLogUtils.logInfo("Discard button not clicked")
		}

		return result
	}
}
