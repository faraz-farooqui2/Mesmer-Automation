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
		startSearch();
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
	WebUI.switchToDefaultContent()
	WebUI.delay(2)
	if(WebUI.waitForElementPresent(inputSearchArticle, 30)){
		WebUI.scrollToElement(inputSearchArticle, 10)
		WebUI.click(inputSearchArticle)
		String searchTerm = "app map"
		WebUI.setText(inputSearchArticle, searchTerm)
		WebUI.delay(2)
		WebUI.switchToFrame(helpScreenIFrame, 10)
		if(WebUI.waitForElementPresent(btnSearchArticle, 30)){
			WebUI.click(btnSearchArticle)
			WebUI.delay(10)
			WebDriver driver = DriverFactory.getWebDriver()
			String searchResultsXPath = '//div[@class="intercom-18c1xc5 e6km4ip0"]/div'
			List<WebElement> searchResutsList = driver.findElements(By.xpath(searchResultsXPath))
			if(searchResutsList != null && searchResutsList.size() == 3){
				if(WebUI.waitForElementPresent(btnSeeResults, 30)){
					WebUI.scrollToElement(btnSeeResults, 10)
					WebUI.click(btnSeeResults)
					WebUI.delay(4)
					WebUI.closeWindowIndex(1)
					WebUI.delay(2)
					WebUI.switchToWindowIndex(0)
					WebUI.delay(2)
					KeywordUtil.markPassed("PASSED: MR-HelpChat-15 Successful")
				}
				else{
					KeywordUtil.markFailed("FAILED: See more results button not found")
				}
			}
			else{
				KeywordUtil.markFailed("FAILED: Search results count is not 3")
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