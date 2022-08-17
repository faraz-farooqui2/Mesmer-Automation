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

//CustomKeywords.'com.mesmer.Utility.generateAccessToken'("/public/rest/api/1.0/executions?issueId=34672&projectId=10400")

if(WebUI.waitForElementVisible(mesmerHeaderLogo, 60)==true)
{
	KeywordUtil.markPassed("PASSED: Mesmer Logo Found")
}else{
	KeywordUtil.markFailed('FAILED: Mesmer Logo Not Found')
}


if (WebUI.verifyElementText(copyRightsFooterText, copyRightsText)== true){
	KeywordUtil.markPassed('PASSED: Copy Rights Text Matched')
}else{
	KeywordUtil.markFailed('FAILED: Copy Rights Text Not Matched')
}

if(WebUI.waitForElementVisible(userName, 10)==true)
{
	KeywordUtil.markPassed('PASSED: User Name field Found')

	WebUI.setText(userName, myUserName)

}else{
	KeywordUtil.markFailed('FAILED: User Name field Not Found')
}

if(WebUI.waitForElementVisible(password, 10)==true)
{
	KeywordUtil.markPassed("PASSED: Password field Found")

	WebUI.setText(password, myPassword)

}else{
	KeywordUtil.markFailed('FAILED: Password field Not Found')
}
if(WebUI.waitForElementVisible(logInButton, 10)==true)
{
	KeywordUtil.markPassed('PASSED: LogIn Button Found')

	WebUI.click(logInButton)


}else{
	KeywordUtil.markFailed('FAILED: LogIn Button Not Found')
}
if(WebUI.waitForElementVisible(userLogo, 10)==true || WebUI.waitForElementVisible(testResults, 10)==true)
{
	KeywordUtil.markPassed('User logged in successfully')
}
else{
	KeywordUtil.markFailed("User is unable to login")
}
