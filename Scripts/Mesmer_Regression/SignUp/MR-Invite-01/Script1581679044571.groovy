import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

//MR-Invite-01 | Valid email in invite users section
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
			WebUI.sendKeys(InviteEmailField, emailAddress)

			WebUI.delay(1)
			WebUI.click(AddInviteIcon)

			if(WebUI.verifyElementPresent(Invite, 2)==true){
				//4. User clicks on 'Invite' button
				WebUI.click(Invite)
				
				//Verification:: Sending Invite' alert message will appear in blue from the top right corner of mesmer console.
				if(WebUI.waitForElementVisible(notificationSendingInvite, 10)==true && WebUI.waitForElementVisible(notificationNew,2)==true)
				{
					KeywordUtil.markPassed("PASSED: Invite successfully sent")
					WebUI.delay(1)
				}
				else{
					KeywordUtil.markFailed("FAILED: Invite is already sent to the user")
				}

			}
			else{
				KeywordUtil.markFailed("FAILED: Invite not sent")
			}
		}
	}
}
catch(Exception e){
	println(e.printStackTrace())
}

finally{


}


