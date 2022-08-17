import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil
import org.apache.poi.ss.usermodel.Row
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.Utility
import com.mesmer.WriteExcelSheet
import com.mesmer.MesmerLogUtils

/*
 * MS-Manage Tests-04 | Verify that user should be able to Copy/Move test cases to another project of same platform from 'Manage Test' page.
 */
// Go to managetests page
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebUI.delay(2)
try{
	// Check test cases count and label
	if(WebUI.waitForElementPresent(counterTotalTC,10) && WebUI.waitForElementPresent(textTotalTC,10)){
		String manageTestsTotalCount = WebUI.getText(counterTotalTC);
		WebUI.delay(2)
		// Check the test cases and select few of them
		selectTestCases();
		// Find and click copy button
		if(WebUI.waitForElementPresent(btnCopy, 10)){
			WebUI.click(btnCopy)
			// Wait for select project popover to appear
			if(WebUI.waitForElementPresent(copyToProjectPopOver, 10)){
				if(WebUI.waitForElementPresent(selectProjectTick, 10)== true){
					WebUI.click(selectProjectTick)
					if(WebUI.waitForElementPresent(btnCopyAllTestCases, 10)){
						WebUI.click(btnCopyAllTestCases)
						if(WebUI.waitForElementPresent(btnConfirmationDialogueYes, 30)){
							WebUI.click(btnConfirmationDialogueYes)
							KeywordUtil.markPassed('PASSED: MS-ManageTest-04 Successful')
						}
						else{
							KeywordUtil.markWarning('WARNING: Confirmation dialog not appeared')
						}
					}
					else{
						KeywordUtil.markWarning('WARNING: Copy all test cases not found')
					}
				}
				else{
					KeywordUtil.markPassed('PASSED: There is no project in the list')
				}
			}
			else{
				KeywordUtil.markFailed('FAILED: Select project popover not found')
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Copy test case button not found')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Test Cases count or title not found')
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.delay(2)
	WebUI.refresh()
}

def void selectTestCases(){
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(1)
	try{
		// Find test cases list
		List<WebElement> testCasesList = driver.findElements(By.xpath('//app-manage-test-case-list/div'))
		if(testCasesList != null && testCasesList.size() > 0){
			List<WebElement> numberOFcheckboxes = driver.findElements(By.xpath('//app-manage-test-case-list/div/div[@class="round"]'))
			int checkBoxesSize = numberOFcheckboxes.size();
			if(numberOFcheckboxes.size() > 3){
				checkBoxesSize = 3;
			}
			// Select the checkbox's
			for(int i=0; i<=checkBoxesSize; i++){
				numberOFcheckboxes.get(i).click()
				WebUI.delay(1)
			}
			
		}
		else{
			KeywordUtil.markWarning("WARNING: There is no test case in the list")
		}
	}
	catch(Exception e){
		e.printStackTrace()
	}
}
def void findAndSelectDevice(){
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(1)
	try{
		// Find the list of devices
		List<WebElement> devicesList = driver.findElements(By.xpath('//div[@class="deviceList ng-star-inserted"]/div'))
		if(devicesList != null && devicesList.size() > 0){
			devicesList.get(0).click()
			WebUI.delay(1)
		}
		else{
			KeywordUtil.markWarning("WARNING: There is no device in the list")
		}
	}
	catch(Exception e){
		e.printStackTrace()
	}
}
