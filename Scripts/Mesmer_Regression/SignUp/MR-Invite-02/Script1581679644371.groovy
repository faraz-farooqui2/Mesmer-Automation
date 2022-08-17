import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

//MR-Invite-02 | Invalid email in invite users section
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
			WebUI.sendKeys(InviteEmailField, invalidEmailAddress)

			WebUI.delay(1)
			WebUI.click(AddInviteIcon)

			if(WebUI.verifyElementPresent(Invite, 2)==true){
				//4. User clicks on 'Invite' button
				WebUI.click(Invite)

				if(WebUI.waitForElementVisible(txtInvalidEmailErrorMsg, 10)==true){
					KeywordUtil.markPassed("PASSED: Email address is invalid")
					WebUI.delay(1)
					
					if(WebUI.waitForElementVisible(btnClose, 10)==true){
						WebUI.click(btnClose)
					}else{
						KeywordUtil.markFailed("FAILED: Unable to click on Close button")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: No error for invalid email address")
				}

			}
			else{
				KeywordUtil.markFailed("FAILED: Not click on Invite button")
			}
		}
	}
}
catch(Exception e){
	println(e.printStackTrace())
}

finally{

}


