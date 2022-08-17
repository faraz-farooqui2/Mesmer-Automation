import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

//MS-LogOut-01 | Verify that user can logout successfully from mesmer console.


if(Utility.isSanityProfile() || Utility.isPocProfile() || Utility.isRegressionProfile() || Utility.isProductionProfile()){
	Utility.setPlatformName("Generic")
}
else{
	Utility.setPlatformName("Logout")
}
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

if(CustomKeywords.'com.mesmer.Utility.goToTestResults'()){
	
	//1. User clicks on Avatar icon/User Account option on the top right corner of mesmer console.
	if(WebUI.waitForElementVisible(userLogoImage, 3)==true){
		WebUI.click(userLogoImage)

		//Verification of dropdown - A drop down will appear with options like Your Profile,Invite Users, Log Out
		if(WebUI.waitForElementVisible(listUserProfile, 5)==true){
			//Verification of Logout option in Dropdown

			if(WebUI.waitForElementVisible(logOutOption, 3)==true){
				//2. User clicks on Log out options
				WebUI.click(logOutOption)

				//Verification - User is logged out and redirected on login page.
				if(WebUI.waitForElementVisible(loginButton, 30)==true){
					MesmerLogUtils.markPassed("User logged out successfully")
				}
				else{
					MesmerLogUtils.markFailed("User is unable to logout")
				}
			}
			else{
				MesmerLogUtils.markFailed("Logout option is not shown")
			}
		}
		else{
			MesmerLogUtils.markFailed("Profile List is not displayed")
		}

	}
	else{
		MesmerLogUtils.markFailed("User logo image is not displayed")
	}
}else{
	MesmerLogUtils.markFailed("Unable to navigate to test result page ")
}
