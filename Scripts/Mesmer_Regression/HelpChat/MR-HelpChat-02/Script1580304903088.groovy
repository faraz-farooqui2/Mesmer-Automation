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
		startSearch()
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
def startSearch(){
	WebUI.switchToFrame(helpScreenIFrame, 1)
	if(WebUI.waitForElementPresent(inputSearchArticle, 30)){
		WebUI.scrollToElement(inputSearchArticle, 10)
		WebUI.click(inputSearchArticle)
		WebUI.setText(inputSearchArticle, "build upload")
		//					WebUI.sendKeys(null,Keys.chord(Keys.ENTER))
		WebUI.delay(2)
		if(WebUI.waitForElementVisible(btnSearchArticle, 30)){
			WebUI.click(btnSearchArticle)
			WebUI.delay(2)
			if(WebUI.waitForElementVisible(textUploadNewBuilds, 30)){
				WebUI.scrollToElement(textUploadNewBuilds, 10)
				WebUI.click(textUploadNewBuilds)
				WebUI.delay(2)
				if(WebUI.waitForElementVisible(labelHelpCenter, 30)){
					WebUI.delay(6)
//					WebUI.scrollToPosition(9999999, 9999999)
//					WebUI.delay(6)
//					if(WebUI.waitForElementPresent(emojiNeutral, 30)){
//						WebUI.scrollToElement(emojiNeutral,30)
//						WebUI.delay(2)
//						WebUI.click(emojiNeutral)
//						WebUI.delay(2)
						if(WebUI.waitForElementVisible(btnCrossHelpCenter, 30)){
							WebUI.click(btnCrossHelpCenter)
							WebUI.delay(2)
							KeywordUtil.markPassed("PASSED: MS-HelpChat-02 successful")
						}
						else{
							KeywordUtil.markFailed("FAILED: Button cross help center not found")
						}
//					}
//					else{
//						KeywordUtil.markFailed("FAILED: Emoji neutral not found")
//					}
				}
				else{
					KeywordUtil.markFailed("FAILED: Help center label not found")
				}
			}
			else{
				KeywordUtil.markFailed("FAILED: Upload new build text not found")
			}
		}
		else{
			KeywordUtil.markFailed("FAILED: Button search article not found")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: Input Search article not found")
	}
}