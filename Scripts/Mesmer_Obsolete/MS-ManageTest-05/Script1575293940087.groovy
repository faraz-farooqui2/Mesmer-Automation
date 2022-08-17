import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

/*
 * MS-Manage Tests-05 | Verify that user should be able to use all the available options for a test result like Comments, Assignee,Duplicate test, Download Assets,Delete.
 */
// Go to managetests page
//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.delay(2)
try{
	// Check test cases count and label
	if(WebUI.waitForElementPresent(counterTotalTC,10) && WebUI.waitForElementPresent(textTotalTC,10)){
		String manageTestsTotalCount = WebUI.getText(counterTotalTC);
		WebUI.delay(2)
		// Check the test cases and select few of them
		findTestCaseAndPerformAction("ManageTest05","clone");
		// Verify if the confirmation dialog appears
		verifyDuplicateTestCaseAlert();
		WebUI.delay(4)
		// Find the test case and download assests
		findTestCaseAndPerformAction("ManageTest05","download");
		// Find the cloned test case and try delete
		findTestCaseAndPerformAction("ManageTest05-clone-1","delete");
		// Verify if the delete confirmation dialog appears
		deleteTestCase();
		// Find and click comment icon
		findTestCaseAndPerformAction("ManageTest05","comments");
		WebUI.delay(2)
		// Move to Managetests again
		CustomKeywords.'com.mesmer.Utility.goToManageTests'()
		// Check the assignedUser and change it
		findTestCaseAndPerformAction("ManageTest05","assignUser");
				
	}
	else{
		KeywordUtil.markFailed('FAILED: Test Cases count or title not found')
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}

def verifyDuplicateTestCaseAlert(){
	// Wait and check if confirmation dialog appears
	if(WebUI.waitForElementVisible(dialogDuplicateTestCase, 30)){
		if(WebUI.waitForElementVisible(btnYes, 20)){
			WebUI.click(btnYes)
			WebUI.delay(2)
		}
		else{
			KeywordUtil.markFailed('FAILED: Yes button not visible')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Confirmation dialog not appeared')
	}
}

def void findTestCaseAndPerformAction(String testCaseName, String actionName){
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(1)
	List<WebElement> testCasesList = driver.findElements(By.xpath('//app-manage-test-case-list/div'))
	if(testCasesList != null && testCasesList.size() > 0){
		for(int i= 0; i < testCasesList.size(); i++){
			String titleXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneLeft"]/descendant::span[starts-with(@class,"titleLimit")]'
			WebElement testCaseTitle = driver.findElement(By.xpath(titleXPath))
			if(testCaseTitle != null && testCaseTitle.getText().equals(testCaseName)){
				WebUI.delay(2)
				TestObject to = new TestObject("objectName")
				to.addProperty("xpath", ConditionType.EQUALS,'//app-manage-test-case-list/div/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneLeft"]/descendant::span[starts-with(@class,"titleLimit") and text()="'+testCaseName+'"]')
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				//This will scroll the page till the element is found
//				js.executeScript("arguments[0].scrollIntoView();", testCaseTitle);
				WebUI.scrollToElement(to, 10)
				WebUI.mouseOver(to)
//				WebUI.click(to)
				WebUI.delay(2)
				if(actionName == "clone"){
					String cloneXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon clone"]'
					WebElement iconClone = driver.findElement(By.xpath(cloneXPath))
					if(iconClone != null){
						iconClone.click()
						WebUI.delay(2)
					}
					else{
						WebElement iconDots = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
						if(iconDots != null){
							iconDots.click()
							WebUI.delay(2)
							cloneXPath = '//popover-container/div[@class="popover-content popover-body"]/div/a/span[@class="icon clone"]'
						}
						List<WebElement> popOverCloneIcon = driver.findElements(By.xpath(cloneXPath))
						if(popOverCloneIcon != null && popOverCloneIcon.size() > 0){
							popOverCloneIcon.get(0).click()
						}
					}
				}
				else if(actionName == "download"){
					// Download assests
					String downloadXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon download"]'
					WebElement iconDownload = driver.findElement(By.xpath(downloadXPath))
					if(iconDownload != null){
						iconDownload.click()
						WebUI.delay(2)
					}
					else{
						WebElement iconDots1 = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
						if(iconDots1 != null){
							iconDots1.click()
							WebUI.delay(2)
							downloadXPath = '//popover-container/div[@class="popover-content popover-body"]/div/a/span[@class="icon download"]'
						}
						List<WebElement> popOverDownloadIcon = driver.findElements(By.xpath(downloadXPath))
						if(popOverDownloadIcon != null && popOverDownloadIcon.size() > 0){
							popOverDownloadIcon.get(0).click()
						}
					}
				}
				else if(actionName == "delete"){
					String deleteXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon delete"]'
					WebElement iconDelete = driver.findElement(By.xpath(deleteXPath))
					if(iconDelete != null){
						iconDelete.click()
						WebUI.delay(2)
					}
					else{
						WebElement iconDots = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
						if(iconDots != null){
							iconDots.click()
							WebUI.delay(2)
							deleteXPath = '//popover-container/div[@class="popover-content popover-body"]/div/a/span[@class="icon delete"]'
						}
						List<WebElement> popOverDeleteIcon = driver.findElements(By.xpath(deleteXPath))
						if(popOverDeleteIcon != null && popOverDeleteIcon.size() > 0){
							popOverDeleteIcon.get(0).click()
						}
					}
				}
				else if(actionName == "comments"){
					String commentXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/a[@class="comments CP"]'
					WebElement iconComment = driver.findElement(By.xpath(commentXPath))
					if(iconComment != null){
						iconComment.click()
						WebUI.delay(2)
						checkForCommentsScreen();
					}
					else{
						KeywordUtil.markFailed("FAILED: Comment icon not found")
					}
				}
				else if(actionName == "assignUser"){
					String avatarXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/a/span[@class="userAvatar"]'
					WebElement iconAvatar = driver.findElement(By.xpath(avatarXPath))
					if(iconAvatar != null){
						iconAvatar.click()
						WebUI.delay(2)
						checkForUsersListAndAssign();
					}
					else{
						KeywordUtil.markFailed("FAILED: Comment icon not found")
					}
				}
				WebUI.delay(2)
				break;
			}
			if(i == (testCasesList.size()-1)){
				KeywordUtil.markWarning("WARNING: Test case not found")
			}
		}
	}
	else{
		KeywordUtil.markWarning("WARNING: There is no test case in the list")
	}
}

def checkForCommentsScreen(){
	if(WebUI.waitForElementVisible(btnAddComment, 30)){
		if(WebUI.waitForElementVisible(textAreaAddComment, 30)){
			WebUI.click(textAreaAddComment)
			WebUI.setText(textAreaAddComment, "Test comment")
			WebUI.click(btnAddComment)
			WebUI.delay(2)
		}
		else{
			KeywordUtil.markFailed("FAILED: Add comment text area not found")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: Add comment icon not found")
	}
}

def checkForUsersListAndAssign(){
	WebDriver driver = DriverFactory.getWebDriver()
	List<WebElement> usersList = driver.findElements(By.xpath('//div[@class="usersList ng-star-inserted"]/a'))
	if(usersList != null && usersList.size() > 0){
		usersList.get(0).click()
	}
}

def deleteTestCase(){
	// Delete test case
	WebUI.delay(1)
	WebUI.click(buttonDeleteTCConfirmationYes)
	WebUI.delay(2)
	KeywordUtil.markPassed("Test Case deleted successfully")
}
