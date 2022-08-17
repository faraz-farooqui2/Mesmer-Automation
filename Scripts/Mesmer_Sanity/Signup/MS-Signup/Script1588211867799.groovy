import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility


//MR-Signup-01 | Valid invite link
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

//1. User clicks on Avatar icon/User Account option on the top right corner of mesmer console.
//2. User clicks on Invite Users options


try{

	if(SignUp01()){
		KTRequestHandler.updateTaskOnZephyr("MS-SignUp-01",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-SignUp-01",Utility.getPlatformName(),2)
	}

	if(SignUp02()){
		KTRequestHandler.updateTaskOnZephyr("MS-SignUp-02",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-SignUp-02",Utility.getPlatformName(),-1)
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{

}

def SendInvite(){

	TestObject login_LoginBtn = findTestObject('Object Repository/OR_LogIn/button_Login')

	if(WebUI.waitForElementPresent(login_LoginBtn, 1)==true){
		KeywordUtil.logInfo("User is going to login again...")
		WebUI.callTestCase(findTestCase("Test Cases/Mesmer_Sanity/Login/MS-LogIn-01"), [:], FailureHandling.STOP_ON_FAILURE)
	}
	else{
		println("Already logged In")
	}


	WebUI.delay(5)
	CustomKeywords.'com.mesmer.Utility.goToInviteUser'()
	WebUI.delay(2)

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
				if(WebUI.waitForElementVisible(notificationSendingInvite, 10)==true && WebUI.waitForElementVisible(notificationNew, 5)==false)
				{
					WebUI.delay(6)
					MesmerLogUtils.markPassed("Invitation sent successfully to" + "::" + emailAddress )
				}
				else {
					MesmerLogUtils.markFailed("Invitation failed")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to click on invite button")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to add email address in invite field ")
		}
	}else{
		MesmerLogUtils.markFailed("Add invite icon is visible")
	}

}


private boolean SignUp01(){

	boolean result = false

	//	SendInvite()
	//	WebUI.callTestCase(findTestCase("Test Cases/Mesmer_Sanity/Logout/MS-Logout-01"), [:], FailureHandling.STOP_ON_FAILURE)
	//	WebUI.delay(5)
	WebUI.navigateToUrl('https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false&flowName=GlifWebSignIn&flowEntry=ServiceLogin')
	WebUI.delay(5)

	if(WebUI.waitForElementPresent(inputGmailField, 5)){
		WebUI.sendKeys(inputGmailField, gmailID)
		result = true
		MesmerLogUtils.markPassed("Entered Email Address")
	}else if (WebUI.waitForElementPresent(inputGmailField2, 5)){
		WebUI.sendKeys(inputGmailField2, gmailID)
		result = true
		MesmerLogUtils.markPassed("Entered Email Address")
	}
	if(WebUI.waitForElementPresent(gmailNext, 5)){
		WebUI.click(gmailNext)
		result = true
		MesmerLogUtils.markPassed("Clicked on next button")
	}else if (WebUI.waitForElementPresent(gmailNext2, 5)){
		WebUI.click(gmailNext2)
		result = true
		MesmerLogUtils.markPassed("Clicked on next button")
	}
	WebUI.delay(5)

	if(WebUI.waitForElementPresent(inputGmailPassword, 5)){
		WebUI.click(inputGmailPassword)
		WebUI.sendKeys(inputGmailPassword, gmailPassword)
		result = true
		MesmerLogUtils.markPassed("Entered Password")
	}
	//		else if (WebUI.waitForElementPresent(inputGmailPassword, 5)){
	//		WebUI.click(inputGmailPassword)
	//		WebUI.sendKeys(inputGmailPassword, gmailPassword)
	//		result = true
	//		MesmerLogUtils.markPassed("Entered Password")
	//	}

	if(WebUI.waitForElementPresent(btnPasswordNext, 5)){
		WebUI.click(btnPasswordNext)
		result = true
		MesmerLogUtils.markPassed("Clicked on Password Next")
	}else if (WebUI.waitForElementPresent(btnPasswordNext2, 5)){
		WebUI.click(btnPasswordNext2)
		result = true
		MesmerLogUtils.markPassed("Clicked on Password Next")
	}

	WebUI.delay(5)

	if(WebUI.waitForElementPresent(openEmail, 20)){

		WebUI.click(openEmail)

		WebUI.waitForElementPresent(btnJoinNow, 20)

		WebUI.click(btnJoinNow)
		result= true
		//				}else{
		//					MesmerLogUtils.markFailed("Unable to click on Join Now Button")
		//				}
		//			}else{
		//				MesmerLogUtils.markFailed("Unable to find Join Now Button")
		//			}
		//		}else{
		//			MesmerLogUtils.markFailed("Unable to open  support email")
		//		}
	}else{
		MesmerLogUtils.markFailed("Email has not yet arrived")
	}
	return result
}


private boolean SignUp02(){
	boolean result = false
	String userName = "QA"
	String password = "mesmer123"

	WebUI.delay(5)

	WebUI.switchToWindowIndex(1)

	WebUI.waitForElementPresent(txtNameSignup, 20)
	WebUI.click(txtNameSignup)
	WebUI.sendKeys(txtNameSignup, userName)

	WebUI.waitForElementClickable(txtPasswordSignup, 20)
	WebUI.click(txtPasswordSignup)
	WebUI.sendKeys(txtPasswordSignup, password)

	WebUI.delay(2)
	
	//WebUI.switchToFrame(FrameCaptcha, 10)
	
	WebUI.waitForElementPresent(captch, 20)

	WebUI.click(captch)
	
	WebUI.waitForElementPresent(payLoadCaptcha, 5)
	WebUI.takeScreenshot()

	WebUI.click(btnSignup)
	//				result= true


	//		}else{
	//			MesmerLogUtils.markFailed("Unable to click on CAPTCHA checkbox")
	//		}
	//	}else{
	//		MesmerLogUtils.markFailed("Unable to find CAPTCHA checkbox")
	//	}
	//						}else{
	//							MesmerLogUtils.markFailed("Unable to set password")
	//						}
	//					}else{
	//						MesmerLogUtils.markFailed("Unable to click on Password field")
	//					}
	//				}else{
	//					MesmerLogUtils.markFailed("Password field not found")
	//				}
	//			}else{
	//				MesmerLogUtils.markFailed("Unable to set user name")
	//			}
	//		}else{
	//			MesmerLogUtils.markFailed("Unable to click on Name field")
	//		}
	//	}else{
	//		MesmerLogUtils.markFailed("Name field not found")
	//	}

	//	return result
}
