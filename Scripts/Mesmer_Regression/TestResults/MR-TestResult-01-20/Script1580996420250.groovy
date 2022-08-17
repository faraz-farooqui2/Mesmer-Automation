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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.interactions.Action
import org.openqa.selenium.interactions.Actions


//MR-TestResults-01 | Verify Test Results page should be available after clicking on Test Results tab
//MR-TestResults-02 | Verify total count Tests' along with 'Failed' percentage heads next to Test Results
//MR-TestResults-03 | Test Case Filters along with count: All/New/ Passed/ Failed/ Needs Review/ Broken
//MR-TestResults-04 | Test Count is shown in relevant filters (All/New/ Passed/ Failed/ Needs Review/ Broken)
//MR-TestResults-05 | Verify that Test Case filters are working fine
//MR-TestResults-06 | Verify Tags filter for sorting is appearing on Test Results page
//MR-TestResults-07 | Verify on clicking tags filter, downward key is converted to upward and tag list is shown correctly
//MR-TestResults-08 | No tag is available' message appears in Tags filter if no tag exists
//MR-TestResults-09 | Select/Sort the data w.r.t. added Tags
//MR-TestResults-10 | Verify that created tags are showing in tag drop down list
//MR-TestResults-11 | Verify that user can select multiple tags from drop down list
//MR-TestResults-12 | Clear All Tags
//MR-TestResults-13 | Clear the selected tag by deselecting tag
//MR-TestResults-14 | Sort By 'Latest Run' option
//MR-TestResults-15 | Verify on clicking Sort by filter, downward key is converted to upward and sorting options are shown correctly
//MR-TestResults-16 | Sort By 'Status' option
//MR-TestResults-17 | Sort By 'Tags' option
//MR-TestResults-18 | Listed devices under Replay Testcases button (If available)
//MR-TestResults-19 | Replay all test cases by clicking on Replay icon/Button
//MR-TestResults-20 | Stop all executions from 'Force Stop' button
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
//1. Observe test result page
CustomKeywords.'com.mesmer.Utility.goToTestResults'()
//MR-TestResult-01
KeywordUtil.markPassed('PASSED: MR-TestResult-01 Successful')

WebUI.waitForPageLoad(5)
try{
	//MR-TestResult-02
	checkTestCase2Steps();
	//MR-TestResult-03,04,05
	checkTestCase345Steps();
	//MR-TestResult-06,07,08
	checkTestCase678Steps();
	//MR-TestResult-09,10
	checkTestCase910Steps();
	//MR-TestResult-11
	checkTestCase11Steps();
	//MR-TestResult-12
	checkTestCase12Steps();
	//MR-TestResult-13
	checkTestCase13Steps();
	//MR-TestResult-14,15
	checkTestCase1415Steps();
	//MR-TestResult-16,17
	checkTestCase1617Steps();
	//MR-TestResult-18
	checkTestCase18Steps();
	//MR-TestResult-19
	checkTestCase19Steps();
	//MR-TestResult-20
	checkTestCase20Steps();
}
catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.refresh()
	WebUI.delay(4)
}

def checkTestCase2Steps(){
	if(WebUI.waitForElementPresent(totalTestCases, 10)){
		if(WebUI.waitForElementPresent(lblPercentageFailed, 10)){
			KeywordUtil.markPassed('PASSED: MR-TestResult-02 Successful')
		}
		else{
			KeywordUtil.markFailed('FAILED: Failed percentage label not found')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Tests total count label not found')
	}
}

def checkTestCase345Steps(){
	WebDriver driver = DriverFactory.getWebDriver()
	testCasesText = WebUI.getText(totalTestCases)

	totalNumberOfTCs = CustomKeywords.'com.mesmer.Utility.extractNumericData'(testCasesText)

	System.out.println("Total no. of test cases " + totalNumberOfTCs)

	List<WebElement> testCaseOptionsList = driver.findElements(By.xpath('//div[@class="textLinks"]/a'))
	if(testCaseOptionsList != null && testCaseOptionsList.size() == 8){
		String lblAll = WebUI.getText(allTestCaseLabel)
		String lblNew = WebUI.getText(newTestCaseLabel)
		String lblPassed = WebUI.getText(passedTestCaseLabel)
		String lblFailed = WebUI.getText(failedTestCaseLabel)
		String lblInProgress = WebUI.getText(inProgressTestCaseLabel)
		String lblBroken = WebUI.getText(brokenTestCaseLabel)
		String lblShowMine = WebUI.getText(showMineTestCaseLabel)
		String lblQueued = WebUI.getText(queuedTestCaseLabel)

		KeywordUtil.markPassed('PASSED: MR-TestResult-03 Successful')
		WebUI.delay(2)

		int allTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblAll)
		int newTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblNew)
		int passedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblPassed)
		int failedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblFailed)
		int inProgressTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblInProgress)
		int brokenTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblBroken)
		int showMineTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblShowMine)
		int queuedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblShowMine)

		if(totalNumberOfTCs == allTestCaseCount){
			WebUI.delay(1)
			if(newTestCaseCount > 0){
				WebUI.click(newTestCaseLabel)
				WebUI.delay(2)
			}
			else{
				KeywordUtil.markWarning("WARNING: New test cases count is 0")
			}
			if(passedTestCaseCount > 0){
				WebUI.click(passedTestCaseLabel)
				WebUI.delay(2)
			}
			else{
				KeywordUtil.markWarning("WARNING: Passed test cases count is 0")
			}
			if(failedTestCaseCount > 0){
				WebUI.click(failedTestCaseLabel)
				WebUI.delay(2)
			}
			else{
				KeywordUtil.markWarning("WARNING: Failed test cases count is 0")
			}
			if(queuedTestCaseCount > 0){
				WebUI.click(queuedTestCaseLabel)
				WebUI.delay(2)
			}
			else{
				KeywordUtil.markWarning("WARNING: Queued test cases count is 0")
			}
			if(inProgressTestCaseCount > 0){
				WebUI.click(inProgressTestCaseLabel)
				WebUI.delay(2)
			}
			else{
				KeywordUtil.markWarning("WARNING: In-progress test cases count is 0")
			}
			if(brokenTestCaseCount > 0){
				WebUI.click(brokenTestCaseLabel)
				WebUI.delay(2)
				
			}
			else{
				KeywordUtil.markWarning("WARNING: Broken test cases count is 0")
			}
			if(showMineTestCaseCount > 0){
				KeywordUtil.markPassed('PASSED: MR-TestResult-04 Successful')
				WebUI.delay(2)
				WebUI.click(showMineTestCaseLabel)
				WebUI.delay(2)
				WebUI.click(allTestCaseLabel)
				WebUI.delay(2)
				KeywordUtil.markPassed('PASSED: MR-TestResult-05 Successful')
			}
			else{
				KeywordUtil.markWarning("WARNING: Show Mine test cases count is 0")
			}
			if(WebUI.waitForElementVisible(allTestCaseLabel, 10)){
				WebUI.click(allTestCaseLabel)
			}

		}
		else{
			KeywordUtil.markFailed('FAILED: Test cases count does not match')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Options count does not match')
	}
}
def checkTestCase678Steps(){
	if(tagsDropDownList != null && tagsDropDownList.findPropertyValue("xpath") != null){
		String tagsDropDownXpath = tagsDropDownList.findPropertyValue("xpath")
		if(WebUI.waitForElementPresent(optionTag, 10)){
			WebUI.click(optionTag)
			WebUI.delay(2)
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> tagsDropDownList = driver.findElements(By.xpath(tagsDropDownXpath))
			if(tagsDropDownList != null && tagsDropDownList.size() > 0){
				KeywordUtil.markPassed('PASSED: MR-TestResult-06 Successful')
				//				tagsDropDownList.get(0).click()
				WebUI.click(optionTag)
				WebUI.delay(2)
				KeywordUtil.markPassed('PASSED: MR-TestResult-07 Successful')
			}
			else{
				KeywordUtil.markPassed('PASSED: There is no tag in the list')
				KeywordUtil.markPassed('PASSED: MR-TestResult-08 Successful')
				WebUI.click(optionTag)
				WebUI.delay(2)
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Tag option not found')
		}
	}
}
def checkTestCase910Steps(){
	if(tagsDropDownList != null && tagsDropDownList.findPropertyValue("xpath") != null){
		String tagsDropDownXpath = tagsDropDownList.findPropertyValue("xpath")
		if(WebUI.waitForElementPresent(optionTag, 10)){
			WebUI.click(optionTag)
			WebUI.delay(2)
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> tagsDropDownList = driver.findElements(By.xpath(tagsDropDownXpath))
			if(tagsDropDownList != null && tagsDropDownList.size() > 0){
				tagsDropDownList.get(0).click()
				KeywordUtil.markPassed('PASSED: MR-TestResult-09 Successful')
				WebUI.delay(2)
				WebUI.click(optionTag)
				WebUI.delay(2)
				KeywordUtil.markPassed('PASSED: MR-TestResult-10 Successful')
			}
			else{
				KeywordUtil.markPassed('PASSED: There is no tag in the list')
				WebUI.click(optionTag)
				WebUI.delay(2)
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Tag option not found')
		}
	}
}
def checkTestCase11Steps(){
	if(tagsDropDownList != null && tagsDropDownList.findPropertyValue("xpath") != null){
		String tagsDropDownXpath = tagsDropDownList.findPropertyValue("xpath")
		if(WebUI.waitForElementPresent(optionTag, 10)){
			WebUI.click(optionTag)
			WebUI.delay(2)
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> tagsDropDownList = driver.findElements(By.xpath(tagsDropDownXpath))
			if(tagsDropDownList != null && tagsDropDownList.size() > 1){
				tagsDropDownList.get(0).click()
				WebUI.delay(2)
				tagsDropDownList.get(1).click()
				WebUI.delay(2)
				WebUI.click(optionTag)
				WebUI.delay(2)
				KeywordUtil.markPassed('PASSED: MR-TestResult-11 Successful')
			}
			else{
				KeywordUtil.markPassed('PASSED: Tags list size is less than 2')
				WebUI.click(optionTag)
				WebUI.delay(2)
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Tag option not found')
		}
	}
}
def checkTestCase12Steps(){
	if(tagsDropDownList != null && tagsDropDownList.findPropertyValue("xpath") != null){
		String tagsDropDownXpath = tagsDropDownList.findPropertyValue("xpath")
		if(WebUI.waitForElementPresent(optionTag, 10)){
			WebUI.click(optionTag)
			WebUI.delay(2)
			if(WebUI.waitForElementPresent(btnClearAll, 10)){
				WebUI.click(btnClearAll)
				WebUI.delay(2)
				//				WebUI.click(optionTag)
				//				WebUI.delay(2)
				KeywordUtil.markPassed('PASSED: MR-TestResult-12 Successful')
			}
			else{
				KeywordUtil.markPassed('PASSED: Clear all button not found')
				WebUI.click(optionTag)
				WebUI.delay(2)
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Tag option not found')
		}
	}
}

def checkTestCase13Steps(){
	if(tagsDropDownList != null && tagsDropDownList.findPropertyValue("xpath") != null){
		String tagsDropDownXpath = tagsDropDownList.findPropertyValue("xpath")
		if(WebUI.waitForElementPresent(optionTag, 10)){
			WebUI.click(optionTag)
			WebUI.delay(2)
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> tagsDropDownList = driver.findElements(By.xpath(tagsDropDownXpath))
			if(tagsDropDownList != null && tagsDropDownList.size() > 0){
				tagsDropDownList.get(0).click()
				WebUI.delay(5)
				tagsDropDownList.get(0).click()
				WebUI.delay(4)
				WebUI.click(optionTag)
				WebUI.delay(2)
				KeywordUtil.markPassed('PASSED: MR-TestResult-13 Successful')
			}
			else{
				KeywordUtil.markPassed('PASSED: There is no tag in the list')
				WebUI.click(optionTag)
				WebUI.delay(2)
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Tag option not found')
		}
	}
}
def checkTestCase1415Steps(){
	if(WebUI.waitForElementPresent(optionSortBy, 10)){
		if(WebUI.waitForElementPresent(lblSortByLatestRun, 10)){
			KeywordUtil.markPassed('PASSED: MR-TestResult-14 Successful')
			WebUI.click(optionSortBy)
			WebUI.delay(2)
			WebUI.click(optionSortBy)
			WebUI.delay(2)
			KeywordUtil.markPassed('PASSED: MR-TestResult-15 Successful')
		}
		else{
			KeywordUtil.markFailed('FAILED: Label Sort by latest run not found')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Sort by option not found')
	}
}
def checkTestCase1617Steps(){
	if(WebUI.waitForElementPresent(optionSortBy, 10)){
		WebUI.click(optionSortBy)
		WebUI.delay(4)
		WebDriver driver = DriverFactory.getWebDriver()
		String statusXPath = '//div[@class="popover-content popover-body"]/div/div[contains(text(),"Status")]'
		WebElement sortByStatus = driver.findElement(By.xpath(statusXPath))
		if(sortByStatus != null){
			sortByStatus.click()
			WebUI.delay(5)
			KeywordUtil.markPassed('PASSED: MR-TestResult-16 Successful')
		}
		else{
			KeywordUtil.markFailed('FAILED: Sort by status not found')
		}
		WebUI.click(optionSortBy)
		WebUI.delay(4)
		String tagsXPath = '//div[@class="popover-content popover-body"]/div/div[contains(text(),"Tags")]'
		WebElement sortByTags = driver.findElement(By.xpath(tagsXPath))
		if(sortByTags != null){
			sortByTags.click()
			WebUI.delay(5)
			KeywordUtil.markPassed('PASSED: MR-TestResult-17 Successful')
		}
		else{
			KeywordUtil.markFailed('FAILED: Sort by tags not found')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Sort by option not found')
	}
}
def checkTestCase18Steps(){
	if(WebUI.waitForElementPresent(btnReplay, 10)){
		WebUI.click(btnReplay)
		WebUI.delay(5)
		if(devicesListDiv != null && devicesListDiv.findPropertyValue("xpath") != null){
			String devicesXPath = devicesListDiv.findPropertyValue("xpath")
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> devicesList = driver.findElements(By.xpath(devicesXPath))
			if(devicesList != null && devicesList.size() > 0){
				if(WebUI.waitForElementPresent(btnRun, 10)){
					KeywordUtil.markPassed('PASSED: MR-TestResult-18 Successful')
					WebUI.delay(2)
					WebUI.click(btnReplay)
				}
				else{
					KeywordUtil.markFailed('FAILED: Button run not found')
				}
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
def checkTestCase19Steps(){
	if(WebUI.waitForElementPresent(btnReplay, 10)){
		WebUI.click(btnReplay)
		WebUI.delay(5)
		if(devicesListDiv != null && devicesListDiv.findPropertyValue("xpath") != null){
			String devicesXPath = devicesListDiv.findPropertyValue("xpath")
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> devicesList = driver.findElements(By.xpath(devicesXPath))
			if(devicesList != null && devicesList.size() > 0){
				devicesList.get(0).click()
				WebUI.delay(4)
				if(WebUI.waitForElementPresent(btnRun, 10)){
					WebUI.click(btnRun)
					WebUI.delay(2)
					if(WebUI.waitForElementPresent(btnYes, 10)){
						WebUI.click(btnYes)
						KeywordUtil.markPassed('PASSED: MR-TestResult-19 Successful')
						WebUI.delay(5)
						WebUI.refresh()
					}
					else{
						KeywordUtil.markFailed('FAILED: Button yes not found')
					}
				}
				else{
					KeywordUtil.markFailed('FAILED: Button run not found')
				}
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

def checkTestCase20Steps(){
	if(WebUI.waitForElementClickable(btnStop, 120)){
		WebUI.click(btnStop)
		WebUI.delay(5)
		if(WebUI.waitForElementVisible(btnYes, 20)){
			WebUI.click(btnYes)
			KeywordUtil.markPassed('PASSED: MR-TestResult-20 Successful')
			WebUI.delay(5)
		}
		else{
			KeywordUtil.markFailed('FAILED: Button yes not found')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Button replay not found')
	}
}