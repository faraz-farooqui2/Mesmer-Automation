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
//					WebUI.click(btnBack)
					WebUI.delay(3)
					KeywordUtil.markPassed("PASSED: MR-HelpChat-05 Successful")
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
		if(WebUI.waitForElementPresent(btnGifPicker, 30)){
			WebUI.click(btnGifPicker)
			sendGif();
		}
		else{
			KeywordUtil.markFailed("FAILED: Gif picker button not found")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: New conversation button not found")
	}
}
def sendGif(){
	if(WebUI.waitForElementPresent(inputSearchGifs, 30)){
		WebUI.click(inputSearchGifs)
		WebUI.delay(2)
		WebUI.setText(inputSearchGifs, "hello")
		WebUI.switchToFrame(helpScreenIFrame, 1)
		WebUI.delay(10)
		WebDriver driver = DriverFactory.getWebDriver()
		String gifsXPath = '//div[@class="intercom-c3jq20 e1ui6fit3"]/div'
		List<WebElement> gifsList = driver.findElements(By.xpath(gifsXPath))
		if(gifsList != null && gifsList.size() > 0){
			gifsList.get(0).click()
			WebUI.delay(10)
			String sentGifXPath = '//div[@class="intercom-comment-single intercom-umqthi e28em7w1"]'
			List<WebElement> sentGifsList = driver.findElements(By.xpath(sentGifXPath))
			if(sentGifsList != null && sentGifsList.size() > 0){
				WebUI.delay(2)
				WebUI.switchToDefaultContent()
				//				sentGifsList.get(0).click()
//				WebUI.delay(4)
//				if(WebUI.waitForElementVisible(btnEmojiClose, 30)){
//					WebUI.click(btnEmojiClose)
//					WebUI.delay(2)
//				}
			}
			else{
				KeywordUtil.markWarning("WARNING: Sent gif not found")
			}
		}
		else{
			KeywordUtil.markFailed("FAILED: No gif found in the search list")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: Gif picker button not found")
	}
}