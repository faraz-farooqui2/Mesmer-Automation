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
				checkAndPerformAction()
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
def checkAndPerformAction(){
	if(WebUI.waitForElementPresent(spanSeeAll, 30)){
		WebUI.click(spanSeeAll)
		WebUI.delay(4)
		if(WebUI.waitForElementPresent(headerTitle, 30)){
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> resultList = null;
			List<WebElement> conversationsList = getConvList();
			if(conversationsList != null && conversationsList.size() > 0){
				JavascriptExecutor js = (JavascriptExecutor) driver;
				resultList = conversationsList
				boolean isListNull = false;
				while(!isListNull){
					js.executeScript("arguments[0].scrollIntoView();", resultList.get(resultList.size()-1));
					WebUI.delay(5)
					conversationsList = getConvList();
					if(conversationsList.size() == resultList.size()){
						isListNull = true
						break
					}
					resultList = conversationsList
				}
				String ahmedConversationXPath = '//div[@class="intercom-1mhn2ve e3ty8w57"]/div['+(resultList.size()-1)+']/descendant::span[contains(text(),"Ahmed")]'
				WebElement ahmedConTitle = driver.findElement(By.xpath(ahmedConversationXPath))
				if(ahmedConTitle.getText().equalsIgnoreCase("Ahmed")){
					resultList.get(resultList.size()-1).click()
					WebUI.delay(4)
					if(WebUI.waitForElementPresent(titleAhmedConv, 10)){
						WebUI.click(titleAhmedConv)
						WebUI.delay(3)
						if(WebUI.waitForElementPresent(btnDownload, 10)){
							WebUI.delay(2)
							if(WebUI.waitForElementPresent(iconTwitter, 10)){
									if(WebUI.waitForElementPresent(divProfileStatus, 10)){
										if(WebUI.waitForElementPresent(divTimeAndLocation, 10)){
											if(WebUI.waitForElementPresent(iconLinkedIn, 10)){
												WebUI.click(iconLinkedIn)
												WebUI.delay(2)
												WebUI.closeWindowIndex(1)
												WebUI.delay(2)
												WebUI.switchToWindowIndex(0)
												WebUI.delay(4)
												KeywordUtil.markPassed("PASSED: MR-HelpChat-10 Successful")
											}
											else{
												KeywordUtil.markFailed("FAILED: LinkedIn icon not found")
											}
										}
										else{
											KeywordUtil.markFailed("FAILED: Time and location not found")
										}
									}
									else{
										KeywordUtil.markFailed("FAILED: Profile status not found")
									}
							}
							else{
								KeywordUtil.markFailed("FAILED: Twitter icon not found")
							}
						}
						else{
							KeywordUtil.markFailed("FAILED: Download button not found")
						}
					}
					else{
						KeywordUtil.markFailed("FAILED:There is no title named ahmed found")
					}
				}
				else{
					WebUI.comment("There is no conversation with ahmed found")
				}
				WebUI.delay(3)
			}
		}
		else{
			KeywordUtil.markFailed("FAILED: Header title not found")
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: See all option not found")
	}
}

def List<WebElement> getConvList(){
	WebDriver driver = DriverFactory.getWebDriver()
	String conversationsXPath = '//div[@class="intercom-1mhn2ve e3ty8w57"]/div'
	List<WebElement> conversationsList = driver.findElements(By.xpath(conversationsXPath))
	List<WebElement> result = null;
	if(conversationsList != null && conversationsList.size() > 0){
		result = conversationsList;
	}
	return result;
}