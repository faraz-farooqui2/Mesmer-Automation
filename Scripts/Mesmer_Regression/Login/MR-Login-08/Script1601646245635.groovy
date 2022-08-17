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


	if(WebUI.waitForElementVisible(mesmerBrandLogo, 20)==true){
		MesmerLogUtils.markPassed('Mesmer Brand Logo found')

		if(WebUI.waitForElementVisible(loginWithEmail, 10)==true){
			MesmerLogUtils.markPassed('Log in with email found')

			if(WebUI.waitForElementVisible(userName, 10)==true){
				MesmerLogUtils.markPassed('User name field found')

				if(WebUI.waitForElementVisible(password, 10)==true){
					MesmerLogUtils.markPassed('Password field found')

					if(WebUI.waitForElementVisible(logInButton, 10)==true){
						MesmerLogUtils.markPassed('Login Button found')

						if(WebUI.waitForElementPresent(copyRightsFooterText, 10)){
							String copyRightText = WebUI.getText(copyRightsFooterText)
							MesmerLogUtils.markPassed('Copy Rights' + " : " + copyRightText )
						
						}else{
						MesmerLogUtils.markFailed('Copy Rights footer not found')
					}
					}else{
						MesmerLogUtils.markFailed('LogIn Button Not Found')
					}

				}else{
					MesmerLogUtils.markFailed('Password field not found')
				}
			}else{
				MesmerLogUtils.markFailed('User name field not found')
			}
		}else{
			MesmerLogUtils.markFailed('Log in with email not found')
		}
	}else{
		MesmerLogUtils.markFailed('Mesmer Brand Logo not found')
	}

}catch(Exception e){
	e.printStackTrace()
} finally{
}



