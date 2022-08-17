import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

//MR-Signup-01 | Valid invite link
Utility.setPlatformName("")
WebUI.delay(1)
//1. User clicks on Avatar icon/User Account option on the top right corner of mesmer console.
//2. User clicks on Invite Users options
CustomKeywords.'com.mesmer.Utility.goToInviteUser'()
WebUI.delay(1)
try{
	//3. User add valid email address in input field that is not register earlier on this server.
	if(WebUI.verifyElementVisible(AddInviteIcon)==true){

		if(WebUI.verifyElementVisible(InviteEmailField)==true){
			WebUI.sendKeys(InviteEmailField, emailAddress)

			WebUI.delay(1)
			WebUI.click(AddInviteIcon)

			if(WebUI.verifyElementPresent(Invite, 2)==true){
				//4. User clicks on 'Invite' button
				WebUI.click(Invite)

				//Verification:: Sending Invite' alert message will appear in blue from the top right corner of mesmer console.
				if(WebUI.waitForElementVisible(notificationSendingInvite, 10)==true && WebUI.waitForElementVisible(notificationNew,2)==true)
				{
					KeywordUtil.markPassed("PASSED: Invite successfully sent")
					WebUI.delay(1)
				}
				else{
					KeywordUtil.markFailed("FAILED: Invite is already sent to the user")
				}

			}
			else{
				KeywordUtil.markFailed("FAILED: Invite not sent")
			}
		}
	}
}
catch(Exception e){
	println(e.printStackTrace())
}

finally{



}


def signup01(){
	WebUI.callTestCase(findTestCase("Test Cases/Mesmer_Sanity/Logout/MS-Logout-01"), [:], FailureHandling.STOP_ON_FAILURE)
	WebUI.delay(5)
	WebUI.navigateToUrl('https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false&flowName=GlifWebSignIn&flowEntry=ServiceLogin')
	WebUI.waitForElementPresent(inputGmailField, 10)
	WebUI.sendKeys(inputGmailField, gmailID)
	WebUI.click(gmailNext)
	WebUI.waitForElementPresent(inputGmailPassword, 10)
	WebUI.click(inputGmailPassword)
	WebUI.sendKeys(inputGmailPassword, gmailPassword)
	WebUI.click(btnPasswordNext)
	WebUI.waitForElementPresent(openEmail, 20)
	WebUI.click(openEmail)
	WebUI.waitForElementPresent(btnJoinNow, 20)
	WebUI.click(btnJoinNow)

}

def signup02(){
	String userName = "QA Test"
	WebUI.waitForElementPresent(txtNameSignup, 20)
	WebUI.click(txtNameSignup)
	WebUI.sendKeys(txtNameSignup, userName)
}

def signup03(){
	String password = "mesmer123"
	WebUI.waitForElementPresent(txtPasswordSignup, 20)
	WebUI.click(txtPasswordSignup)
	WebUI.sendKeys(txtPasswordSignup, password)
}

def signup04(){
	String shortPassword = "mesmer"
	WebUI.waitForElementPresent(txtPasswordSignup, 20)
	WebUI.click(txtPasswordSignup)
	WebUI.sendKeys(txtPasswordSignup, shortPassword)
	WebUI.waitForElementPresent(minimumPasswordSignupValidation, 10)
}

def signup05(){
	
	WebUI.waitForElementPresent(captch, 20)
	WebUI.click(captch)
	
}