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
//import internal.GlobalVariable as GlobalVariable
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

// MS-Your Profile-02 | Verify that user can successfully change the password from Your Profile page.
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//1. Click on User Account/Avatar icon on top right corner of the mesmer console
//2. Click on Your profile option

//CustomKeywords.'com.mesmer.Utility.goToUserProfile'()


try{
	WebUI.delay(2)
	//3. Click on 'Change Password' option in password field
	if(WebUI.waitForElementPresent(buttonChangePassword , 10) == true){
		WebUI.click(buttonChangePassword)

		//4. Click on Current password field and enter the existing password
		if(WebUI.waitForElementPresent(textChangePasswordPage, 10)){
			WebUI.click(inputCurrentPassword)
			WebUI.setText(inputCurrentPassword, currentPassword)
			MesmerLogUtils.markPassed("Current Password Set")

			//5. Click on New password field and enter the new password
			if(WebUI.waitForElementPresent(inputNewPasswordField,10) == true){
				WebUI.click(inputNewPasswordField)
				WebUI.setText(inputNewPasswordField,newPassword)
				MesmerLogUtils.markPassed("New Password Set")

				//6. Click on 'Change Password' button
				if(WebUI.waitForElementPresent(buttonDialogueChangePassword,10) == true){
					WebUI.click(buttonDialogueChangePassword)
					if(WebUI.waitForElementPresent(passwordValidatorSuccess, 10) ==true){
						MesmerLogUtils.markPassed("Password changed Successfully")
					}else{

						MesmerLogUtils.markFailed('Password not changed Successfully')
					}


				}else{
					MesmerLogUtils.markFailed('Not Clicked On Change Password Button')

				}
			}
			else{
				MesmerLogUtils.markFailed('New Password Not Set')
			}
		}
		else{
			MesmerLogUtils.markFailed('Current Password Is Set')
		}
	}else{
		MesmerLogUtils.markFailed('Change Password Button Not Found')
	}
}catch(Exception e){
	e.printStackTrace()
}
finally{
	// Performing Additional Steps to Revert Password To Its Original Password

	if(WebUI.waitForElementPresent(buttonChangePassword , 10) == true){

		WebUI.click(buttonChangePassword)

		if(WebUI.waitForElementPresent(textChangePasswordPage, 10) ==true){
			if(WebUI.waitForElementPresent(inputCurrentPassword, 10) ==true){
				WebUI.click(inputCurrentPassword)
				MesmerLogUtils.markPassed("Clicked on input current password filed")
				WebUI.setText(inputCurrentPassword, newPassword)
				MesmerLogUtils.markPassed("Current Password Set")


				if(WebUI.waitForElementPresent(inputNewPasswordField,10) == true){
					WebUI.click(inputNewPasswordField)
					WebUI.setText(inputNewPasswordField,currentPassword)

					MesmerLogUtils.markPassed("New Password Set")

					if(WebUI.waitForElementPresent(buttonDialogueChangePassword,10) == true){
						WebUI.click(buttonDialogueChangePassword)
						if(WebUI.waitForElementPresent(passwordValidatorSuccess, 10) ==true){
							MesmerLogUtils.markPassed("Password changed Successfully")
						}else{

							MesmerLogUtils.markFailed('Password not changed Successfully')
						}


					}else{
						MesmerLogUtils.markFailed('Not Clicked On Change Password Button')

					}
				}
				else{
					MesmerLogUtils.markFailed('New Password Not Set')
				}
			}
			else{
				MesmerLogUtils.markFailed('User Not on Change Your Password Page')
			}
		}
		else{
			MesmerLogUtils.markFailed('User Not on Change Your Password Page')
		}
	}else{
		MesmerLogUtils.markFailed('Change Password Button Not Found')
	}
	WebUI.refresh()
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}