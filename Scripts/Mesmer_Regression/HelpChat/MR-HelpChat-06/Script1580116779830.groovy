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
					KeywordUtil.markPassed("PASSED: MR-HelpChat-06 Successful")
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
		if(WebUI.waitForElementPresent(btnEmojiPicker, 30)){
			WebUI.click(btnEmojiPicker)
			sendEmoji();
		}
		else{
			KeywordUtil.markFailed("FAILED: Gif picker button not found")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: New conversation button not found")
	}
}
def sendEmoji(){
	if(WebUI.waitForElementPresent(inputSearchEmoji, 30)){
		WebUI.click(inputSearchEmoji)
		WebUI.delay(2)
		WebUI.setText(inputSearchEmoji, "smile")
		WebUI.switchToFrame(helpScreenIFrame, 1)
		WebUI.delay(4)
		WebDriver driver = DriverFactory.getWebDriver()
		String emojiXPath = '//div[@class="intercom-1mt7bgj e1w5qe5f5" and @aria-live="polite"]/div/span'
		List<WebElement> emojisList = driver.findElements(By.xpath(emojiXPath))
		if(emojisList != null && emojisList.size() > 0){
			emojisList.get(0).click()
			WebUI.delay(4)
			WebUI.switchToDefaultContent()
		}
		else{
			KeywordUtil.markFailed("FAILED: No gif found in the search list")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: Gif picker button not found")
	}
}