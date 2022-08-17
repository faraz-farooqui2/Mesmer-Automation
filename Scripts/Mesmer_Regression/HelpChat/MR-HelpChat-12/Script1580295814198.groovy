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
		WebUI.switchToFrame(helpScreenIFrame, 10)
		WebUI.comment("Switched the frame")
		if(WebUI.waitForElementPresent(spanRunOnInterCom, 30)){
			WebUI.click(spanRunOnInterCom)
			WebUI.delay(2)
			WebUI.closeWindowIndex(1)
			WebUI.delay(2)
			WebUI.switchToWindowIndex(0)
			WebUI.delay(4)
			KeywordUtil.markPassed("PASSED: MR-HelpChat-12 Successful")
		}
		else{
			KeywordUtil.markFailed("FAILED: Text run on intercom not found")
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