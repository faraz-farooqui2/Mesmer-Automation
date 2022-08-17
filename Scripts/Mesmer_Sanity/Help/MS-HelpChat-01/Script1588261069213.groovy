import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import com.mesmer.KTRequestHandler
import controllers.HelpChatController

//CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
//WebUI.delay(2)
//CustomKeywords.'com.mesmer.Utility.goToTestResults'()
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

WebUI.delay(5)
try{
	
	if(HelpChatController.getInstance().startConversation()){
		if(WebUI.waitForElementPresent(textAreaSendMessage, 30)){
			WebUI.click(textAreaSendMessage)
			WebUI.setText(textAreaSendMessage, "Hello, This is a test message")
			WebUI.delay(2)
			if(WebUI.waitForElementPresent(btnSendMessage, 30)){
				WebUI.click(btnSendMessage)
				WebUI.delay(4)

//				if(WebUI.waitForElementPresent(btnBack, 30)){
//					WebUI.click(btnBack)
//					WebUI.delay(5)
				MesmerLogUtils.markPassed("MS-HelpChat-01 successful")

//				}
//				else{
//					MesmerLogUtils.markWarning("Back button not found")
//				}
			}
			else{
				MesmerLogUtils.markFailed("Send message button not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Text area for sending the message not found")
		}

	}else{
		MesmerLogUtils.markFailed("Unable to start conversation")
	}

}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
//	WebUI.delay(10)
}



