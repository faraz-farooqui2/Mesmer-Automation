import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.main.CustomKeywordDelegatingMetaClass
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

//MS-Recommended-05 -- Accept Test

try{
	WebDriver driver = DriverFactory.getWebDriver()
	//Calling Select Project Method
	CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
	 ///////////////////////////////
	
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.delay(3)
	
	//Total Test Case on Test Results page
	String totalNumberOfTestResultsTCs = WebUI.getText(totalNumberOFTestCasesOnTestResults)
	println("String of total ---> " + totalNumberOfTestResultsTCs)
	
	//Total Number Of Test Cases on Test Results page
	int noOfTCsOnTestResultsPage = CustomKeywords.'com.mesmer.Utility.extractNumericData'(totalNumberOfTestResultsTCs)
	
	println("Number of Test Cases on Test Results page ---> " + noOfTCsOnTestResultsPage)
	
	CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()
	
	WebUI.waitForPageLoad(10)
	WebUI.delay(2)
	if(WebUI.waitForElementVisible(noTestCaseAvailableinRecommended, 5)==false){
		
		String text_TotalTCsRec = WebUI.getText(totalNumberOfRecommendedTests)
		int numberOfRecommendedTestCases = CustomKeywords.'com.mesmer.Utility.extractNumericData'(text_TotalTCsRec)
		
		//All Checkboxes in Recommended Test Cases
		List<WebElement> numberOFcheckboxes = driver.findElements(By.xpath("//div[@class='round']/label"))
		List<WebElement> numberOfAcceptButtons = driver.findElements(By.xpath("//a[@title='Accept Test']"))
		
		//Number of checkboxes
		int length = numberOFcheckboxes.size()
		println("Number of checkboxes on Recommended Test Cases page = " + length)
		
		
		if(length>0){
			
			numberOFcheckboxes.get(1).click()
			numberOfAcceptButtons.get(1).click()
			
			if(WebUI.waitForElementVisible(confirmationWindow, 5)==true){
				if(WebUI.waitForElementVisible(yesButtonOnConfirmationWindow, 5)==true){
					WebUI.click(yesButtonOnConfirmationWindow)
					
					WebUI.verifyElementNotVisible(confirmationWindow)
					
					String text_TC2 = WebUI.getText(totalNumberOfRecommendedTests)
					int noOfRTCs = CustomKeywords.'com.mesmer.Utility.extractNumericData'(text_TC2)
					
					if(numberOfRecommendedTestCases-1 == noOfRTCs){
						KeywordUtil.markPassed("SUCCESS: Test case is accepted from Recommended Test Cases")
					}
					else{
						KeywordUtil.markFailed("FAILED: Test Case is not accepted from Recommended Test Case")
					}
					
					CustomKeywords.'com.mesmer.Utility.goToTestResults'()
					String text_TestResult = WebUI.getText(totalNumberOFTestCasesOnTestResults)
					int totalNoOfTConTestResultsPageAfterRecommendedTestCases = CustomKeywords.'com.mesmer.Utility.extractNumericData'(text_TestResult)
					
					if(noOfTCsOnTestResultsPage+1 == totalNoOfTConTestResultsPageAfterRecommendedTestCases){
						KeywordUtil.markPassed("SUCCESS: Accepted TC is displayed on Test Results page")
					}
					else{
						KeywordUtil.markFailed("FAILED:  Accepted TC is not displayed on Test Results page")
					}
					
				}
				else{
					KeywordUtil.markFailed("FAILED: Yes button is not displayed on Confirmation Window")
				}
				
			}
			else{
				KeywordUtil.markFailed("FAILED: Confirmation Windows is not displayed on accepting Single Test Case")
			}
		}
		else{
			
			KeywordUtil.markFailed("FAILED: Checkboxes and Accept button is not available")
		}
		
	}
	else{
		KeywordUtil.markWarning("WARNING: No Test Cases available in Recommended Test Cases page")
	}
	
}catch(Exception e){
	println(e.stackTrace)
}

