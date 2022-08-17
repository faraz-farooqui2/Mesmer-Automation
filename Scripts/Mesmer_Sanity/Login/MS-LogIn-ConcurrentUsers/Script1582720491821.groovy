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
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

//import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//MS-LogIn-01 | Verify that user can login to mesmer console successfully with valid credentials.

//1. User clicks on Email field.
//2. User enters valid email address in the field.
//String myUserName = "muhammad.zaman@mesmerhq.com"
//String myPassword = "mesmer123"
Utility.setPlatformName("Login")
if(WebUI.waitForElementVisible(userName, 10)==true)
{
	MesmerLogUtils.markPassed('User Name field Found')

	WebUI.setText(userName, user)

}else{
	MesmerLogUtils.markFailed('User Name field Not Found')
}

//3. User clicks of Password field.
//4. User enters valid password in the field
if(WebUI.waitForElementVisible(password, 10)==true)
{
	MesmerLogUtils.markPassed("Password field Found")

	WebUI.setText(password, pass)

}else{
	MesmerLogUtils.markFailed('Password field Not Found')
}

MesmerLogUtils.logInfo("URL When User " + user + " is on Login screen - " + WebUI.getUrl())

//5. User clicks on login button.
if(WebUI.waitForElementVisible(logInButton, 10)==true)
{
	MesmerLogUtils.markPassed('LogIn Button Found')

	WebUI.click(logInButton)


}else{
	MesmerLogUtils.markFailed('LogIn Button Not Found')
}

//Verification of User logged in Successfully. Checking UserLogo or TestResults page
if(WebUI.waitForElementVisible(testResults, 90)==true)
{
	MesmerLogUtils.markPassed('User logged in successfully')
}
else{
	MesmerLogUtils.markFailed("User is unable to login")
}
