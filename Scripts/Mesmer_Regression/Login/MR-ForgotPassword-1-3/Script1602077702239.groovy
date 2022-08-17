import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import internal.GlobalVariable

//MR-Login-8 | Verify version on Login page

//Utility.setPlatformName("Generic")
//MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//1. User clicks on Email field.
//2. User enters valid email address in the field.
//String myUserName = GlobalVariable.username
//String myPassword = GlobalVariable.password
try{


	if(WebUI.waitForElementVisible(btnForgotPassword, 20)==true){
		WebUI.click(btnForgotPassword)
		MesmerLogUtils.markPassed("Clicked on Forgot Password")

		if(WebUI.waitForElementVisible(userName, 10)==true){
			WebUI.click(userName)

			WebUI.setText(userName, user)
			MesmerLogUtils.markPassed("Set Username is " + " : " + user )




			if(WebUI.waitForElementVisible(recoverEmailSent, 10)==true){
				String emailSent = WebUI.getText(recoverEmailSent)
				MesmerLogUtils.markPassed("Recovery Email Msg" + " : " + emailSent )

				if(WebUI.waitForElementVisible(btnReturnToLogin, 10)==true){
					MesmerLogUtils.markPassed("Return To Login found")

					if(WebUI.waitForElementVisible(btnRecoverAccount, 10)==true){
						WebUI.click(btnRecoverAccount)
						MesmerLogUtils.markPassed("Clicked on Recover Account")
						if(forgotPassword02()){
							WebUI.delay(10)
							if(forgotPassword03()){
							}else{
								MesmerLogUtils.markFailed("Forgot Password 3 Test Case Failed")
							}
						}else{
							MesmerLogUtils.markFailed("Forgot Password 2 Test Case Failed")
						}
					}else{
						MesmerLogUtils.markFailed("Could not click on Recover Account button")
					}
				}else{
					MesmerLogUtils.markFailed("Return To Login not found")
				}
			}else{
				MesmerLogUtils.markFailed("Recovery email was sent msg not appears")
			}
		}else{
			MesmerLogUtils.markFailed("User Name field Not Found")
		}

	}else{
		MesmerLogUtils.markFailed("Could not click on Forgot Password")
	}

}catch(Exception e){
	e.printStackTrace()
} finally{
}


private boolean forgotPassword02(){

	boolean result = false

	WebUI.navigateToUrl('https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false&flowName=GlifWebSignIn&flowEntry=ServiceLogin')
	WebUI.delay(5)
	if(WebUI.waitForElementPresent(inputGmailField, 30)){
		WebUI.sendKeys(inputGmailField, gmailID)
		WebUI.click(gmailNext)
		WebUI.delay(5)

		if(WebUI.waitForElementPresent(inputGmailPassword, 30)){

			WebUI.sendKeys(inputGmailPassword, gmailPassword)
			WebUI.click(btnPasswordNext)
			WebUI.delay(10)

			if(WebUI.waitForElementPresent(openEmail, 20)){
				WebUI.click(openEmail)
				WebUI.delay(5)

				if(WebUI.waitForElementPresent(resetNow, 20)){
					WebUI.click(resetNow)

					WebUI.switchToWindowIndex(1)

					if(WebUI.waitForElementPresent(newPassword, 20)){
						WebUI.click(newPassword)
						WebUI.sendKeys(newPassword, newPasswordKey)

						if(WebUI.waitForElementPresent(retypeNewPassword, 20)){
							WebUI.click(retypeNewPassword)
							WebUI.sendKeys(retypeNewPassword, mismathPasswordKey)

							if(WebUI.waitForElementPresent(changePasswordDisabled, 20)){
								result = true
							}else{
								MesmerLogUtils.logInfo("Change Password button is enabled on mismatch password")
							}
						}else{
							MesmerLogUtils.logInfo("Could not enter retype new Password" + "   " + mismathPasswordKey)
						}
					}else{
						MesmerLogUtils.logInfo("Could not enter new Password" + "   " + newPasswordKey)
					}
				}else{
					MesmerLogUtils.logInfo("Could not click on reset password")
				}
			}else{
				MesmerLogUtils.logInfo("Could not open email")
			}
		}else{
			MesmerLogUtils.logInfo("Could not enter gmail Password" + "  " + gmailPassword +  "   or   " + "  Could not click on Next button ")
		}
	}else{
		MesmerLogUtils.logInfo("Could not enter gmail ID" + "  " + gmailID +  "   or   " + "  Could not click on Next button ")
	}
	return result
}

private boolean forgotPassword03(){
	boolean result = false
	if(WebUI.clearText(retypeNewPassword)){
		WebUI.waitForElementPresent(retypeNewPassword, 20)
		WebUI.click(retypeNewPassword)
		WebUI.sendKeys(retypeNewPassword, retypePasswordKey)

		if(WebUI.waitForElementPresent(changePassword, 20)){
			WebUI.click(changePassword)
			result = true
		}else{
			MesmerLogUtils.logInfo("Could not click on change password ")
		}
	}else{
		MesmerLogUtils.logInfo("Could not enter retype new Password" + "    " + retypePasswordKey)
	}
	return result
}
