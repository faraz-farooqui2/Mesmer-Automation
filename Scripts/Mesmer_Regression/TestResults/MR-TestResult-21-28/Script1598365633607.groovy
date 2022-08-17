import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

import controllers.ManageTestController
import controllers.TestDetailsController
import controllers.TestResultController


//MR-TestResults-21 | Report generation from 'Generate execution report document' button
//MR-TestResults-22 | CSV report generation from 'Generate Execution Report CSV '
//MR-TestResults-23 | Replay selected test cases
//MR-TestResults-24 | Verify List of Devices status for Replay
//MR-TestResults-26 | Verify that user can stop replay of selected TCs
//MR-TestResults-27 | Verify that Load More button is working fine

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
//1. Observe test result page
CustomKeywords.'com.mesmer.Utility.goToTestResults'()

WebUI.waitForPageLoad(5)
try{
	//MR-TestResult-21
	checkTestCase21Steps();
	//MR-TestResult-22
	checkTestCase22Steps();
	//MR-TestResult-23
	checkTestCase23Steps();
	//MR-TestResult-24
	checkTestCase24Steps();
	//MR-TestResult-26
	checkTestCase26Steps();
	//MR-TestResult-27
	checkTestCase27Steps();
	//MR-TestResult-28
	checkTestCase28Steps();
}
catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.refresh()
	WebUI.delay(4)
}

def checkTestCase21Steps(){
	if(WebUI.waitForElementPresent(btnDocReport, 10)){
		WebUI.click(btnDocReport)
		WebUI.delay(5)
		KeywordUtil.markPassed('PASSED: MR-TestResult-21 Successful')
	}
	else{
		KeywordUtil.markFailed('FAILED: Button generate execution report not found')
	}
}

def checkTestCase22Steps(){
	if(WebUI.waitForElementPresent(btnCsvReport, 10)){
		WebUI.click(btnCsvReport)
		WebUI.delay(5)
		KeywordUtil.markPassed('PASSED: MR-TestResult-22 Successful')
	}
	else{
		KeywordUtil.markFailed('FAILED: Button generate csv report not found')
	}
}

def checkTestCase23Steps(){
	TestResultController.getInstance().findTestCaseAndPerformAction("ManageTest-06","selectTestCase", "execute")
}
def checkTestCase24Steps(){
	checkDevicesStatus()
}
def checkTestCase26Steps(){
	TestResultController.getInstance().findTestCaseAndPerformAction("ManageTest-06","selectTestCase", "stop")
}
def checkTestCase27Steps(){
	checkLoadMoreButtonFunctionality()
}

def checkDevicesStatus(){
	if(WebUI.waitForElementPresent(btnReplay, 10)){
		WebUI.click(btnReplay)
		WebUI.delay(4)
		if(devicesListDiv != null && devicesListDiv.findPropertyValue("xpath") != null){
			String devicesXPath = devicesListDiv.findPropertyValue("xpath")
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> devicesList = driver.findElements(By.xpath(devicesXPath))
			if(devicesList != null && devicesList.size() > 0){
				String deviceStatus = "Provisioned"
				String searchDeviceXPath = devicesXPath+'/descendant::div[@class="label MWP100" and contains(text(),"'+deviceStatus+'")]'
				List<WebElement> provisionedDevicesList = driver.findElements(By.xpath(searchDeviceXPath))
				if(provisionedDevicesList != null && provisionedDevicesList.size() > 0){
					KeywordUtil.markPassed('PASSED: Provisioned device found in the list')
				}
				else{
					KeywordUtil.markWarning('WARNING: There is no provisioned device in the list')
				}
				deviceStatus = "In Use"
				searchDeviceXPath = devicesXPath+'/descendant::div[@class="label MWP100" and contains(text(),"'+deviceStatus+'")]'
				List<WebElement> inUseDevicesList = driver.findElements(By.xpath(searchDeviceXPath))
				if(inUseDevicesList != null && inUseDevicesList.size() > 0){
					KeywordUtil.markPassed('PASSED: inUse device found in the list')
				}
				else{
					KeywordUtil.markWarning('WARNING: There is no inUse device in the list')
				}
				deviceStatus = "Broken"
				searchDeviceXPath = devicesXPath+'/descendant::div[@class="label MWP100" and contains(text(),"'+deviceStatus+'")]'
				List<WebElement> brokenDevicesList = driver.findElements(By.xpath(searchDeviceXPath))
				if(brokenDevicesList != null && brokenDevicesList.size() > 0){
					KeywordUtil.markPassed('PASSED: Broken device found in the list')
				}
				else{
					KeywordUtil.markWarning('WARNING: There is no broken device in the list')
				}
				deviceStatus = "Ready"
				searchDeviceXPath = devicesXPath+'/descendant::div[@class="label MWP100" and contains(text(),"'+deviceStatus+'")]'
				List<WebElement> readyDevicesList = driver.findElements(By.xpath(searchDeviceXPath))
				if(readyDevicesList != null && readyDevicesList.size() > 0){
					KeywordUtil.markPassed('PASSED: Ready device found in the list')
				}
				else{
					KeywordUtil.markWarning('WARNING: There is no ready device in the list')
				}
				WebUI.click(btnReplay)
			}
			else{
				KeywordUtil.markFailed('FAILED: There is no device in the list')
			}
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Button replay not found')
	}
}

def checkLoadMoreButtonFunctionality(){
	if(testCasesDivXPath != null && testCasesDivXPath.findPropertyValue("xpath") != null){
		String testResultTestCasesDivXPath = testCasesDivXPath.findPropertyValue("xpath")
		WebDriver driver = DriverFactory.getWebDriver()
		if(testCasesListXPath != null && testCasesListXPath.findPropertyValue("xpath") != null){
			String testResultTestCasesXPath = testCasesListXPath.findPropertyValue("xpath")
			List<WebElement> testCasesList = driver.findElements(By.xpath(testResultTestCasesXPath))
			if(testCasesList != null && testCasesList.size() > 0){
				if(WebUI.waitForElementPresent(btnLoadMore, 30)){
					WebUI.click(btnLoadMore)
					KeywordUtil.markPassed('PASSED: MR-TestResult-27 Successful')
					WebUI.delay(5)
				}
				else{
					KeywordUtil.markFailed('FAILED: Load More button not found')
				}
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				js.executeScript("arguments[0].scrollIntoView();", testCasesList.get(testCasesList.size()-1));
//				WebUI.delay(5)
			}
			else{
				KeywordUtil.markWarning('WARNING: There is no test case in the list')
			}
		}
		else{
			KeywordUtil.markWarning('WARNING: There is no test case in the list')
		}
	}
	else{
		KeywordUtil.markWarning('WARNING: Test cases container not found')
	}
}

private boolean checkTestCase28Steps(){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction("ManageTest-06", "selectTestCase", "")){
		if(TestDetailsController.getInstance().clickBaselineEditTestIcon()){
			MesmerLogUtils.logInfo("Edit icon found and clicked")
			WebUI.delay(2)
			TestResultController.getInstance().editTestCaseName("","clickTitle")
		}
	}
	return result
}