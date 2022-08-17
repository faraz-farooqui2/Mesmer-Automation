import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

//CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
//WebUI.delay(2)
//CustomKeywords.'com.mesmer.Utility.goToTestResults'()
Utility.setPlatformName("")
WebUI.delay(4)
try{
	if(WebUI.waitForElementPresent(helpIcon, 30)){
		WebUI.click(helpIcon)
		WebUI.delay(2)
		if(WebUI.waitForElementVisible(btnNewConversation, 30)){
			WebUI.click(btnNewConversation)
			WebUI.delay(2)
			startConversation();
		}
		else{
			startConversation();
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: Help/Chat icon not found")
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
	if(WebUI.waitForElementPresent(textAreaSendMessage, 30)){
		WebUI.click(textAreaSendMessage)
		WebUI.setText(textAreaSendMessage, "Hello, This is a test message")
		WebUI.delay(2)
		if(WebUI.waitForElementPresent(btnSendMessage, 30)){
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