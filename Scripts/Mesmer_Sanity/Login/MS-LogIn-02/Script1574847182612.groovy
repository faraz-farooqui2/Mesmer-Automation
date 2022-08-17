import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import internal.GlobalVariable


//MS-LogIn-02 | Verify that user can't login to mesmer console with invalid credentials.
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

//1. User clicks on Email field.
//2. User enters invalid email address in the field.
try{
	if(WebUI.waitForElementVisible(userName, 10)==true)
	{
		WebUI.setText(userName, user)
		MesmerLogUtils.markPassed('User Name field Found')

		//3. User clicks of Password field.
		//4. User enters valid password in the field
		if(WebUI.waitForElementVisible(password, 10)==true)
		{
			WebUI.setText(password, pass)
			MesmerLogUtils.markPassed("Password field Found")

			//5. User clicks on login button.
			if(WebUI.waitForElementVisible(logInButton, 10)==true)
			{
				MesmerLogUtils.markPassed('LogIn Button Found')

				WebUI.click(logInButton)


				//Verification --- User should be shown error message "Invalid Credentials"
				if(WebUI.waitForElementVisible(errorInvalidCredentials, 10)==true)
				{
					MesmerLogUtils.markPassed('Invalid Crednetials')
				}
				else{
					MesmerLogUtils.markFailed('User logins Successfully')

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

}
finally{
	
	WebUI.delay(5)
	WebUI.refresh()
}