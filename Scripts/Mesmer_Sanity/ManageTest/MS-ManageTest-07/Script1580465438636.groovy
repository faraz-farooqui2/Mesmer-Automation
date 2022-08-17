import org.openqa.selenium.By as By
import org.openqa.selenium.Keys
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
 * MS-Manage Tests-07 | Verify that user should be able to use TestData
 */


try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToManageTests'()
		WebUI.delay(2)

		// Check test cases count and label
		if(WebUI.waitForElementPresent(counterTotalTC,10) && WebUI.waitForElementPresent(textTotalTC,10)){
			String manageTestsTotalCount = WebUI.getText(counterTotalTC);
			WebUI.delay(2)
			// Check the test cases and select few of them
			findTestCaseAndPerformAction("ManageTest05","testData");
			// Check replicate test popup
			WebUI.delay(2)
			uploadScript()
			WebUI.delay(2)
			downloadSampleScript()
			WebUI.delay(2)
			checkReplicateTestPopUpFieldsAndPerformAction()

		}
		else{
			KeywordUtil.markFailed('FAILED: Test Cases count or title not found')
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
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
				else if(actionName == "testData"){
					String testDataXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon testData"]'
					WebElement iconTestData = driver.findElement(By.xpath(testDataXPath))
					if(iconTestData != null){
						iconTestData.click()
						WebUI.delay(2)
					}
					else{
						WebElement iconDots = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
						if(iconDots != null){
							iconDots.click()
							WebUI.delay(4)
							testDataXPath = '//popover-container/div[@class="popover-content popover-body"]/div/a/span[@class="icon testData"]'
							List<WebElement> popOverTestDataIcon = driver.findElements(By.xpath(testDataXPath))
							if(popOverTestDataIcon != null && popOverTestDataIcon.size() > 0){
								popOverTestDataIcon.get(0).click()
								WebUI.delay(2)
							}
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
				else if(actionName == "edit"){
					WebElement iconDots = driver.findElement(By.xpath('//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
					if(iconDots != null){
						iconDots.click()
						WebUI.delay(2)
						String editXPath = '//popover-container/div[@class="popover-content popover-body"]/div/a/span[@class="icon editTestCase"]'
						//						editXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon editTestCase"]'
						List<WebElement> popOverEditIcon = driver.findElements(By.xpath(editXPath))
						if(popOverEditIcon != null && popOverEditIcon.size() > 0){
							popOverEditIcon.get(0).click()
						}
					}
					else{
						String editXPath = '//app-manage-test-case-list/div['+(i+1)+']/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div[@class="actionButtons"]/div[@class="hoverItems"]/a/span[@class="icon editTestCase"]'
						WebElement iconEdit = driver.findElement(By.xpath(editXPath))
						if(iconEdit != null){
							WebUI.delay(2)
							iconEdit.click()
							WebUI.delay(2)
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
					}
					else{
						KeywordUtil.markFailed("FAILED: Comment icon not found")
					}
				}
				WebUI.delay(2)
				break;
			}
			if(i == (testCasesList.size()-1)){
				KeywordUtil.markFailed("[DATA ISSUE] Test case not found")
			}
		}
	}
	else{
		KeywordUtil.markFailed("[DATA ISSUE] There is no test case in the list")
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

def checkAndClickTheScreenShot(String action){
	WebDriver driver = DriverFactory.getWebDriver()
	List<WebElement> testScreensList = driver.findElements(By.xpath('//div[@class="dataPanel"]/div'))
	if(testScreensList != null && testScreensList.size() > 1){
		testScreensList.get(1).click()
		WebUI.delay(2)
		if(WebUI.waitForElementVisible(btnDots, 60)){
			WebUI.delay(2)
			if(action == "reDoFromHere"){
				if(WebUI.waitForElementVisible(reDoFromHere, 60)){
					WebUI.click(reDoFromHere)
					if(WebUI.waitForElementVisible(selectDeviceOption, 60)){
						WebUI.click(selectDeviceOption)
						WebUI.delay(2)
						List<WebElement> devicesList = driver.findElements(By.xpath('//div[@class="deviceList ng-star-inserted"]/div'))
						if(devicesList != null && devicesList.size() > 0){
							WebUI.delay(2)
							List<WebElement> provisionedDevicesList = driver.findElements(By.xpath('//div[@class="device provisioned ng-star-inserted"]'))
							if(provisionedDevicesList != null && provisionedDevicesList.size() > 0){
								provisionedDevicesList.get(0).click()
								KeywordUtil.markPassed('PASSED: Clicked On The Provisioned Device')
							}
							else{
								KeywordUtil.markFailed('WARNING: There is no provisioned device available')
							}
						}
						else{
							KeywordUtil.markFailed('WARNING: There is no device available')
						}
					}
					else{
						KeywordUtil.markFailed('WARNING: Select device option not available')
					}
				}
				else{
					KeywordUtil.markFailed('FAILED: ReDo from here option not available')
				}
			}
			else if(action == "assertions"){
				WebUI.delay(2)
				if(WebUI.waitForElementVisible(optionAddAssertion, 30)){
					WebUI.click(optionAddAssertion);
					WebUI.delay(2)
					if(WebUI.waitForElementVisible(lblDialogAssertions, 30)){
						String screenElementsXPath = '//div[@class="screen"]/div[@class="elementSizeHighlighter ng-star-inserted"]'
						List<WebElement> screenAssertionElementsList = driver.findElements(By.xpath(screenElementsXPath))
						if(screenAssertionElementsList != null && screenAssertionElementsList.size() > 0){
							if(screenAssertionElementsList.size() > 2){
								screenAssertionElementsList.get(1).click()
							}
							else{
								screenAssertionElementsList.get(0).click()
							}
							WebUI.delay(4)
							String assertionTypeXPath = 'div[starts-with(@class,"conditionBlock m-t-0")]'
							List<WebElement> assertionTypesList = driver.findElements(By.xpath(assertionTypeXPath))
							if(assertionTypesList != null && assertionTypesList.size() > 1){
								//div[starts-with(@class,"conditionBlock m-t-0")][2]/div/div[@class="inputAssertion disabledSelect"]/select
								WebElement assertionTypeElement = driver.findElement(By.xpath('//div[starts-with(@class,"conditionBlock m-t-0")][2]/div/div[@class="inputAssertion disabledSelect"]/select'))
								if(assertionTypeElement != null){
									WebUI.delay(2)
								}
							}
							if(WebUI.waitForElementVisible(btnCross, 30)){
								WebUI.click(btnCross)
							}
							else{
								KeywordUtil.markFailed('FAILED: Cross button not visible')
							}
						}
						else{
							KeywordUtil.markFailed('FAILED: Assertions dialog not appeared')
						}
					}
					else{
						KeywordUtil.markFailed('FAILED: Assertions dialog not appeared')
					}
				}
				else{
					KeywordUtil.markFailed('FAILED: Add assertion option not available')
				}
			}
			else if(action == "data"){
				WebUI.delay(2)
				if(WebUI.waitForElementVisible(optionData, 30)){
					WebUI.click(optionData);
					WebUI.delay(2)
					if(WebUI.waitForElementVisible(lblPreCondition, 30) && WebUI.waitForElementVisible(lblPostCondition, 30)){
						WebUI.click(btnCrossStepData)
						WebUI.delay(2)
					}
					else{
						KeywordUtil.markFailed('FAILED: One of the upload conditon script option is not visible')
					}
				}
				else{
					KeywordUtil.markFailed('FAILED: Data option not available')
				}
			}
			else if(action == "delete"){
				WebUI.delay(2)
				if(WebUI.waitForElementVisible(optionDelete, 30)){
					WebUI.click(optionDelete);
					WebUI.delay(2)
					if(WebUI.waitForElementVisible(btnSaveActive, 30)){
						WebUI.delay(4)
					}
					else{
						KeywordUtil.markFailed('FAILED: Save button not in active condition')
					}
				}
				else{
					KeywordUtil.markFailed('FAILED: Delete option not available')
				}
			}

		}
		else{
			KeywordUtil.markFailed('FAILED: Dots icon not visible')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: There is no test case screen available')
	}
}

def deleteTestCase(){
	// Delete test case
	WebUI.delay(1)
	WebUI.click(buttonDeleteTCConfirmationYes)
	WebUI.delay(2)
	KeywordUtil.markPassed("Test Case deleted successfully")
}
def downloadSampleScript(){
	if(WebUI.waitForElementVisible(downloadSampleScript, 30)){
		WebUI.click(downloadSampleScript)
		WebUI.delay(2)
	}
	else{
		KeywordUtil.markFailed('FAILED: Download sample script button not found')
	}
}

def uploadScript(){
	if(WebUI.waitForElementPresent(inputUploadPreConditionScript, 30)){
		WebUI.uploadFile(inputUploadPreConditionScript, "/Users/mesmerhq/WorkSpace/QAAutomation/Resources/script.sh")
		WebUI.delay(5)
	}
	else{
		KeywordUtil.markFailed('FAILED: Upload pre condition script button not found')
	}
}

def checkReplicateTestPopUpFieldsAndPerformAction(){
	String fieldsXPath = '//app-test-case-data/div'
	WebDriver driver = DriverFactory.getWebDriver()
	List<WebElement> testDataFieldsList = driver.findElements(By.xpath(fieldsXPath))
	if(testDataFieldsList != null && testDataFieldsList.size() > 1){
		String userLoginAddXPath = '//app-test-case-data/div[1]/descendant::span[@class="addTag ng-star-inserted"]'
		WebElement userLoginAddBtn = driver.findElement(By.xpath(userLoginAddXPath))
		if(userLoginAddBtn != null){
			userLoginAddBtn.click()
			WebUI.delay(2)
			addUserLogins();
			userLoginAddBtn.click()
			WebUI.delay(2)
			deleteUserLogins()
			userLoginAddBtn.click()
			addUserLogins();
			userLoginAddBtn.click()
			if(WebUI.waitForElementPresent(btnYesTestData, 30)){
				WebUI.click(btnYesTestData)
				WebUI.delay(2)
			}
			else{
				KeywordUtil.markFailed('FAILED: Yes button not found')
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: User logins add button not found')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: No field found in test data popup')
	}
}

def addUserLogins(){
	if(WebUI.waitForElementVisible(inputUserName, 10)){
		if(WebUI.waitForElementVisible(inputPassword, 10)){
			WebUI.setText(inputUserName, "testuser")
			WebUI.delay(2)
			WebUI.setText(inputPassword, "testpassword")
			WebUI.delay(2)
		}
		else{
			KeywordUtil.markFailed('FAILED: Input password not found')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Input username not found')
	}
}
def deleteUserLogins(){
	if(WebUI.waitForElementVisible(btnCrossUserLogins, 10)){
		WebUI.click(btnCrossUserLogins)
		WebUI.delay(2)
	}
	else{
		KeywordUtil.markFailed('FAILED: Button cross user logins not found')
	}
}
