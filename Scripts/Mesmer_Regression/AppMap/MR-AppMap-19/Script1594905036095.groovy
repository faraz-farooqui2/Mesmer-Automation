import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.sun.media.ui.CacheControlComponent.CancelButton
import com.kms.katalon.core.testobject.TestObject
import java.time.Duration;

import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils;
import controllers.AppMapController

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName) 
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{

	if(WebUI.waitForElementVisible(stopCrawlButton, 3)==true){
		WebUI.click(stopCrawlButton)

		if(WebUI.waitForElementVisible(confirmationWindow_StopCrawl, 3)==true){
			if(WebUI.waitForElementVisible(yesButton_StopCrawl, 3)==true){
				WebUI.click(yesButton_StopCrawl)
				WebUI.delay(10)

				if(WebUI.waitForElementVisible(textCrawlCompleted, 10)==true){
					MesmerLogUtils.markPassed("Crawl stopped successfully")
					
				}
				else{
					MesmerLogUtils.markFailed("Crawl is not stopped successfully")
				}

			}
			else{
				KeywordUtil.markFailed("FAILED: Unable to click on Yes button on Stop Crawl window")
			}

		}
		else{
			KeywordUtil.markFailed("FAILED: Confirmation window of Stop Crawl is not displayed")
		}
	}
	else{
		KeywordUtil.markWarning("Warning: Stop Crawl button is not displayed")
	}

}
catch(Exception e){
	e.printStackTrace()
}
finally{

	WebUI.refresh()
}
