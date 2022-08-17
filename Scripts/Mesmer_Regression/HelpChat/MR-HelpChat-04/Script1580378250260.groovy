import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import controllers.HelpChatController

WebUI.delay(4)
try{
	if(HelpChatController.getInstance().clickHelpChatIcon()){
		WebUI.switchToFrame(helpScreenIFrame, 1)
		WebUI.comment("Switched the frame")
		if(WebUI.waitForElementVisible(logoMesmer, 30)){
			if(WebUI.waitForElementVisible(spanStumpedInfo, 30)){
				WebUI.switchToDefaultContent()
				startConversation()
				if(WebUI.waitForElementPresent(btnBack, 30)){
					WebUI.click(btnBack)
					WebUI.delay(3)
					KeywordUtil.markPassed("PASSED: MR-HelpChat-04 Successful")
				}
				else{
					KeywordUtil.markFailed("FAILED: Back button not found")
				}
			}
			else{
				KeywordUtil.markFailed("FAILED: Stumped info not found")
			}
		}
		else{
			KeywordUtil.markFailed("FAILED: Mesmer logo not found")
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
	if(WebUI.waitForElementPresent(btnNewConversation, 30)){
		WebUI.click(btnNewConversation)
		WebUI.delay(4)
		if(WebUI.waitForElementPresent(btnAttachFile, 30)){
//			WebUI.click(btnAttachFile)
			uploadFile();
		}
		else{
			KeywordUtil.markFailed("FAILED: Button upload attachment not found")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: New conversation button not found")
	}
}
def uploadFile(){
	CustomKeywords.'com.mesmer.Utility.uploadFile'(btnAttachFile,"/Users/mesmerhq/WorkSpace/QAAutomation/Resources/Data Files/SampleData.xlsx")
//	WebUI.uploadFile(btnAttachFile, "/Users/mesmerhq/WorkSpace/QAAutomation/Resources/Data Files/SampleData.xlsx")
	WebUI.delay(10)
}