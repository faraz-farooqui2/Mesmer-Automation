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
		if(WebUI.waitForElementVisible(logoMesmer, 30)){
			if(WebUI.waitForElementVisible(spanStumpedInfo, 30)){
				checkConversationsList()
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
def checkConversationsList(){
	if(WebUI.waitForElementPresent(spanSeeAll, 30)){
		WebUI.click(spanSeeAll)
		WebUI.delay(4)
		if(WebUI.waitForElementPresent(headerTitle, 30)){
			WebDriver driver = DriverFactory.getWebDriver()
			String conversationsXPath = '//div[@class="intercom-1mhn2ve e3ty8w57"]/div'
			List<WebElement> conversationsList = driver.findElements(By.xpath(conversationsXPath))
			if(conversationsList != null && conversationsList.size() > 0){
				WebUI.switchToDefaultContent()
				if(WebUI.waitForElementPresent(btnBack, 30)){
					WebUI.click(btnBack)
					WebUI.delay(3)
					KeywordUtil.markPassed("PASSED: MR-HelpChat-07 Successful")
				}
				else{
					KeywordUtil.markWarning("WARNING: Back button not found")
				}
			}
		}
		else{
			KeywordUtil.markFailed("FAILED: Gif picker button not found")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: New conversation button not found")
	}
}