import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import internal.GlobalVariable

//MS-LogIn-01 | Verify that user can login to mesmer console successfully with valid credentials.

Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//1. User clicks on Email field.
//2. User enters valid email address in the field.
//String myUserName = GlobalVariable.username
//String myPassword = GlobalVariable.password
try{
	if(WebUI.waitForElementPresent(userName, 10)==true){
		WebUI.click(userName)

		// Set username from Config file
		//	WebUI.setText(userName, myUserName)

		// Set username from Data file
		WebUI.setText(userName, user)
		MesmerLogUtils.markPassed("Set Username")

		//3. User clicks of Password field.
		//4. User enters valid password in the field
		if(WebUI.waitForElementPresent(password, 10)==true){
			WebUI.click(password)

			//Set password from Config file
			// WebUI.setText(password, myPassword)

			//Set password from Data file
			WebUI.setText(password, pass)
			MesmerLogUtils.markPassed("Set Password")


			//5. User clicks on login button.
			if(WebUI.waitForElementPresent(logInButton, 10)==true){
				WebUI.click(logInButton)
				MesmerLogUtils.markPassed('Clicked on Login Button')

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
	WebUI.delay(3)
	//Verification of User logged in Successfully. Checking UserLogo or TestResults page
	if(WebUI.waitForElementPresent(userLogo, 60)==true || WebUI.waitForElementPresent(testResults, 60)==true)
	{
		MesmerLogUtils.markPassed('User logged in successfully')
	}
}