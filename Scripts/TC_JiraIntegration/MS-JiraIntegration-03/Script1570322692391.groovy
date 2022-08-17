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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


///Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)


if(WebUI.waitForElementPresent(testCaseOpen, 10) == true){

	WebUI.click(testCaseOpen)
	KeywordUtil.markPassed('PASSED: Test Case Opens Successfully')
}else{
	KeywordUtil.markFailed('FAILED: Test Case Not Open')
}

if(WebUI.waitForElementPresent(clickOnJiraIssues, 10) == true){

	WebUI.click(clickOnJiraIssues)
	KeywordUtil.markPassed('PASSED: Clicked on Jira Issues Successfully')
}else{
	KeywordUtil.markFailed('FAILED: Unable to Click on Jira Issues')
}


if(WebUI.waitForElementPresent(createNewIssue, 10) == true){

	WebUI.click(createNewIssue)
	KeywordUtil.markPassed('PASSED: Clicked on Create New Issue Successfully')
}else{
	KeywordUtil.markFailed('FAILED: Unable to Click on Create New Issue')
	
}
String mesmerURL = WebUI.getUrl()
String mesmerTilte = WebUI.getWindowTitle()
WebUI.delay(5)

WebUI.switchToWindowTitle('Log in to continue - Log in with Atlassian account')

if(WebUI.waitForElementPresent(jiraUserField, 10) == true){

	WebUI.setText(jiraUserField, JiraUsername)
	WebUI.delay(1)

	KeywordUtil.markPassed('PASSED: User Name Set Successfully')
}else{
	KeywordUtil.markFailed('FAILED: Unable To Set User Name')
}

if(WebUI.waitForElementPresent(jiraContinueButton, 10) == true){

	WebUI.click(jiraContinueButton)
	WebUI.delay(1)

	KeywordUtil.markPassed('PASSED: Click On The Continue Button')
}else{
	KeywordUtil.markFailed('FAILED: Unable To Click On The Continue Button')
}

if(WebUI.waitForElementPresent(jiraPasswordField, 10) == true){

	WebUI.setEncryptedText(jiraPasswordField, jiraPassword)
	WebUI.delay(1)

	KeywordUtil.markPassed('PASSED: Jira Password Set')
}else{
	KeywordUtil.markFailed('FAILED: Unable To Set Jira Password')
}


if(WebUI.waitForElementPresent(jiraLogin, 10) == true){

	WebUI.click(jiraLogin)

	WebUI.delay(1)

	KeywordUtil.markPassed('PASSED: Click On The Jira Login Button')
}else{
	KeywordUtil.markFailed('FAILED: Unable To Click On The Jira Login Button')
}
	WebUI.delay(25)
	
	String jiraTitle = WebUI.getWindowTitle()
	
	if(WebUI.verifyNotEqual(mesmerTilte, jiraTitle) == true){
	KeywordUtil.markPassed('PASSED: Jira Issue Created')
		
	}else{

	KeywordUtil.markFailed('FAILED: Jira Issue Not Created')
	}

WebUI.navigateToUrl(mesmerURL)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()