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
				verifyElements()
				WebUI.delay(3)
				if(WebUI.waitForElementPresent(inputSearchArticle, 30)){
					WebUI.delay(2)
					if(WebUI.waitForElementPresent(spanRunOnInterCom, 30)){
						WebUI.delay(2)
						KeywordUtil.markPassed("PASSED: MR-HelpChat-03 Successful")
					}
					else{
						KeywordUtil.markFailed("FAILED: Text run on intercom not found")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: Search input field not found")
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

def verifyElements(){
	WebUI.delay(3)
	String convListXPath = '//div[@class="intercom-icbmz8 e1yjqpel0"]/div[2]/div'
	WebDriver driver = DriverFactory.getWebDriver()
	List<WebElement> convList = driver.findElements(By.xpath(convListXPath))
	if(convList != null && convList.size() > 1){
		WebUI.delay(3)
	}
	else{
		KeywordUtil.markFailed("FAILED: No conversation happened yet")
	}
}