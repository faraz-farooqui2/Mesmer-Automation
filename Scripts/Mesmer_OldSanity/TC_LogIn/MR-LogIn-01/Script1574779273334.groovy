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


if(WebUI.waitForElementVisible(mesmerHeaderLogo, 30)==true)
{
	KeywordUtil.markPassed('PASSED: Mesmer Brand Logo Found')
}else{
	KeywordUtil.markFailed('FAILED: Mesmer Brand Logo Not Found')
}

if(WebUI.waitForElementVisible(userName, 10)==true)
{
	KeywordUtil.markPassed('PASSED: User Name field Found')

}else{
	KeywordUtil.markFailed('FAILED: User Name field Not Found')
}

if(WebUI.waitForElementVisible(password, 10)==true)
{
	KeywordUtil.markPassed('PASSED: Password field Found')

}else{
	KeywordUtil.markFailed('FAILED: Password field Not Found')
}

if(WebUI.waitForElementVisible(forgotPassword, 10)==true)
{
	KeywordUtil.markPassed('PASSED: Forgot Password field Found')

}else{
	KeywordUtil.markFailed('FAILED: Forgot Password field Not Found')
}

if(WebUI.waitForElementVisible(logInButton, 10)==true)
{
	KeywordUtil.markPassed('PASSED: LogIn Button Found')

}else{
	KeywordUtil.markFailed('FAILED: LogIn Button Not Found')
}

if(WebUI.waitForElementVisible(mesmerFooterLogo, 10)==true)
{
	KeywordUtil.markPassed('PASSED: Mesmer Footer Logo Found')
}
else{
	KeywordUtil.markFailed('FAILED: Mesmer Footer Logo Not Found')
}

if (WebUI.verifyElementText(copyRightsFooterText, copyRightsText)== true){
	KeywordUtil.markPassed('PASSED: Copy Rights Text Matched')
}else{
	KeywordUtil.markFailed('FAILED: Copy Rights Text Not Matched')
}


