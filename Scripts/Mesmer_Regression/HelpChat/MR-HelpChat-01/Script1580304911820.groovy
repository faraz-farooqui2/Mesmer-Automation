import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import controllers.HelpChatController

WebUI.delay(4)
try{
	if(HelpChatController.getInstance().startConversation()){
		startConversation();
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
	WebUI.delay(2)
}
def startConversation(){
	if(WebUI.waitForElementVisible(textAreaSendMessage, 30)){
		WebUI.click(textAreaSendMessage)
		WebUI.setText(textAreaSendMessage, "Hello, This is a test message")
		WebUI.delay(2)
		if(WebUI.waitForElementVisible(btnSendMessage, 30)){
			WebUI.click(btnSendMessage)
			WebUI.delay(6)
			KeywordUtil.markPassed("PASSED: MS-HelpChat-01 successful")
			if(WebUI.waitForElementPresent(btnBack, 30)){
				WebUI.click(btnBack)
				WebUI.delay(3)
			}
			else{
				KeywordUtil.markWarning("WARNING: Back button not found")
			}
		}
		else{
			KeywordUtil.markFailed("FAILED: Send message button not found")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: Text area for sending the message not found")
	}
}