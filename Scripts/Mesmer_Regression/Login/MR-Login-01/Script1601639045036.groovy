import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import internal.GlobalVariable

//MR-Login-1 | Verify that user can successfully login after updating server build

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
		MesmerLogUtils.markPassed("Set Username")

		//3. User clicks of Password field.
		//4. User enters valid password in the field
		if(WebUI.waitForElementVisible(password, 10)==true){
			WebUI.click(password)

			//Set password from Config file
			// WebUI.setText(password, myPassword)

			//Set password from Data file
			WebUI.setText(password, pass)
			MesmerLogUtils.markPassed("Set Password")


			//5. User clicks on login button.
			if(WebUI.waitForElementVisible(logInButton, 10)==true){
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
	if(WebUI.waitForElementVisible(userLogo, 60)==true || WebUI.waitForElementVisible(testResults, 60)==true)
	{
		MesmerLogUtils.markPassed('User logged in successfully')
	}
}



