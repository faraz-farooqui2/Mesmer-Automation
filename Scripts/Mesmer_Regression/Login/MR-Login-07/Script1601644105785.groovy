import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import internal.GlobalVariable

//MR-Login-6 | Leaving password field empty for login credentials

//Utility.setPlatformName("Generic")
//MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//1. User clicks on Email field.
//2. User enters valid email address in the field.
//String myUserName = GlobalVariable.username
//String myPassword = GlobalVariable.password
try{
	if(WebUI.waitForElementVisible(userName, 10)==true){
		WebUI.click(userName)

		// Set username from Config file
		//	WebUI.setText(userName, myUserName)

		// Set username from Data file
		WebUI.setText(userName, user)
		MesmerLogUtils.markPassed("Set Username" + " : " + user)

		//3. User clicks of Password field.
		//4. User enters valid password in the field
		if(WebUI.waitForElementVisible(password, 10)==true){
			WebUI.click(password)

			//Set password from Config file
			// WebUI.setText(password, myPassword)

			//Set password from Data file
			WebUI.setText(password, pass)
			MesmerLogUtils.markPassed("Set Password" + " : " + pass)


			//5. User clicks on login button.
			if(WebUI.waitForElementVisible(logInButton, 10)==true){
				WebUI.click(logInButton)
				MesmerLogUtils.markPassed('Clicked on Login Button')

				if(WebUI.waitForElementPresent(msg_emailRequired, 10)){
					MesmerLogUtils.markPassed('Email is required field found')
				}else{
					MesmerLogUtils.markFailed('Email is required field is not found ')
				}
				
				if(WebUI.waitForElementPresent(msg_emailRequired, 10)){
					MesmerLogUtils.markPassed('Password is required field found')
				}else{
					MesmerLogUtils.markFailed('Password is required field is not found ')
				}
			}else{
				MesmerLogUtils.markFailed('LogIn Button Not Found')
			}

		}else{
			MesmerLogUtils.markFailed('Password field Not Found')
		}

	}else{
		MesmerLogUtils.markFailed('User Name field Not Found')
	}


}catch(Exception e){
	e.printStackTrace()
} finally{
}



