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
		String searchTerm = "werwerwer"
		WebUI.setText(inputSearchArticle, searchTerm)
		WebUI.delay(2)
		WebUI.switchToFrame(helpScreenIFrame, 10)
		if(WebUI.waitForElementPresent(btnSearchArticle, 30)){
			WebUI.click(btnSearchArticle)
			WebUI.delay(2)
			if(WebUI.waitForElementPresent(errorSearchTerm, 30)){
				KeywordUtil.markPassed("PASSED: MR-ChatHelp-14 successful")
				WebUI.delay(2)
//				String searchErrorString = WebUI.getText(errorSearchTerm);
//				if(searchErrorString.equalsIgnoreCase("No results for "+searchTerm)){
//					KeywordUtil.markPassed("PASSED: MR-ChatHelp-14 successful")
//					WebUI.delay(2)
//				}
//				else{
//					KeywordUtil.markFailed("FAILED: Error search term not found")
//				}
			}
			else{
				KeywordUtil.markFailed("FAILED: Error search div not found")
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