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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.interactions.Action
import org.openqa.selenium.interactions.Actions
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility


//MS-Test Result-03 | Verify that all filters and tags are working fine on test result page.



WebDriver driver = DriverFactory.getWebDriver()
//2. User clicks on all filters under Test Result heading
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		WebUI.waitForPageLoad(5)

		testCasesText = WebUI.getText(totalTestCases)

		totalNumberOfTCs = CustomKeywords.'com.mesmer.Utility.extractNumericData'(testCasesText)

		System.out.println("Total no. of test cases " + totalNumberOfTCs)

		List<WebElement> testCaseOptionsList = driver.findElements(By.xpath('//div[@class="textLinks"]/a'))
		if(testCaseOptionsList != null && testCaseOptionsList.size() == 8){
			String lblAll = WebUI.getText(allTestCaseLabel)
			String lblNew = WebUI.getText(newTestCaseLabel)
			String lblPassed = WebUI.getText(passedTestCaseLabel)
			String lblFailed = WebUI.getText(failedTestCaseLabel)
			String lblShowMine = WebUI.getText(showMineTestCaseLabel)
			String lblBroken = WebUI.getText(brokenTestCaseLabel)
			String lblInProgress = WebUI.getText(inProgressTestCaseLabel)
			String lblQueued = WebUI.getText(queuedTestCaseLabel)

			int allTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblAll)
			int newTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblNew)
			int passedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblPassed)
			int failedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblFailed)
			int showMineTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblShowMine)
			int brokenTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblBroken)
			int inProgressTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblInProgress)
			int queuedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblQueued)

			if(totalNumberOfTCs == allTestCaseCount){
				WebUI.delay(1)
				if(newTestCaseCount >= 0){
					WebUI.click(newTestCaseLabel)
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markPassed("New test cases count is 0")
				}
				if(passedTestCaseCount > 0){
					WebUI.click(passedTestCaseLabel)
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markPassed("Passed test cases count is 0")
				}
				if(failedTestCaseCount > 0){
					WebUI.click(failedTestCaseLabel)
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markPassed("Failed test cases count is 0")
				}

				if(brokenTestCaseCount > 0){
					WebUI.click(brokenTestCaseLabel)
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markPassed("Broken test cases count is 0")
				}

				if(queuedTestCaseCount > 0){
					WebUI.click(queuedTestCaseLabel)
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markPassed("Queued test cases count is 0")
				}
				if(inProgressTestCaseCount > 0){
					WebUI.click(inProgressTestCaseLabel)
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markPassed("In-progress test cases count is 0")
				}

				if(showMineTestCaseCount > 0){
					WebUI.click(showMineTestCaseLabel)
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markPassed("Show mine test cases count is 0")
				}
			}
			else{
				MesmerLogUtils.markFailed('Test cases count does not match')
			}
		}
		else{
			MesmerLogUtils.markFailed('Options count does not match')
		}
		//3. User clicks 'Tag' option on Test result page.
		//4. User clicks on any tag from the list

		if(WebUI.waitForElementPresent(Tag, 10) ==true){
			WebUI.click(Tag)
		}else{
			MesmerLogUtils.markFailed('Could not click on Tag')
		}

		String tag = findTestObject('Object Repository/OR_TestResult/list_Tags').findPropertyValue('xpath').toString()
		List<WebElement> tagList  = driver.findElements(By.xpath(tag))

		if(WebUI.waitForElementPresent(noTagsAvailable, 5)){
			MesmerLogUtils.markPassed("No tag is available")
		}else {
			if(tagList != null && tagList.size() > 0){
				tagList.get(0).click()
				MesmerLogUtils.markPassed('Clicked on a Tag')
			}else{
				MesmerLogUtils.markFailed('Could not click on a Tag')
			}
		}

		//5. User clicks on 'Sort by' option
		if(WebUI.waitForElementPresent(optionSortBy, 10) ==true){
			WebUI.click(optionSortBy)
			WebUI.delay(2)
			if(WebUI.waitForElementPresent(optionStatus, 10) ==true){
				WebUI.click(optionStatus)
				MesmerLogUtils.markPassed('Clicked on Sort and Select a sort by Option')
			}else{
				MesmerLogUtils.markFailed('Sort by status not found')
			}
		}else{
			MesmerLogUtils.markFailed('Sort by option not found')
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
	//	WebUI.refresh()
}