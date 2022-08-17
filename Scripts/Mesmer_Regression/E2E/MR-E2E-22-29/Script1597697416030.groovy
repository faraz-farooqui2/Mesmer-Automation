import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.sun.media.ui.CacheControlComponent.CancelButton

import controllers.AppMapController
import controllers.CreateTestController

import com.kms.katalon.core.testobject.TestObject
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils;



//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()

try{

	WebUI.delay(2)

	if(AppMapController.getInstance().clickRunCrawl(timeInHrs, timeInMinutes)){
		if(AppMapController.getInstance().clickDeviceLogButton()){
			WebUI.delay(5)
			List<WebElement> deviceLogsList = AppMapController.getInstance().getDeviceLogsList()
			if(deviceLogsList != null && deviceLogsList.size() > 0){
				MesmerLogUtils.logInfo("E2E-27 Successfull")
			}
		}
	}

	if(checkCrawlStopped()){

		KeywordUtil.logInfo("[MESMER]: Crawl is completed successfully")
		//LastCrawlID
		String LastCrawlID1 = WebUI.executeJavaScript("return localStorage.getItem('LastCrawlID')", null)
		MesmerLogUtils.logInfo("Last Crawl ID of iteration " + SrNumber + " ---- > " + LastCrawlID1)

		if(LastCrawlID1 != null && !LastCrawlID1.isEmpty()){
			KTRequestHandler.getCrawlLogsErrors(LastCrawlID1)
		}
		else{
			MesmerLogUtils.logInfo("Last crawl id not found")
		}
		if(AppMapController.getInstance().clickDeviceLogButton()){
			WebUI.delay(5)
			if(CreateTestController.getInstance().clickSettingsIcon()){
				if(CreateTestController.getInstance().checkIfAllSettingsOptionsAvailable()){
					if(CreateTestController.getInstance().clickSettingsOption("Download Log")){
						MesmerLogUtils.logInfo("E2E-29 Successfull")
					}
				}
			}
		}
	}
	else{

		KeywordUtil.markFailed("[MESMER]: Crawl is not completed successfully")
		String LastCrawlID1 = WebUI.executeJavaScript("return localStorage.getItem('LastCrawlID')", null)

		if(LastCrawlID1 != null && !LastCrawlID1.isEmpty()){
			KTRequestHandler.getCrawlLogsErrors(LastCrawlID1)
		}
		else{
			MesmerLogUtils.logInfo("Last crawl id not found")
		}
	}

}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}

private boolean checkCrawlStopped(){
	boolean stopCrawlResult = false

	int dataTimeHrs = Integer.parseInt(timeInHrs)
	int dataTimemins = Integer.parseInt(timeInMinutes)
	int totalDataTime = dataTimeHrs*60+dataTimemins
	int totalTimeInSeconds = (totalDataTime*60)+600
	if(WebUI.waitForElementVisible(textCrawlCompleted, totalTimeInSeconds)==true){
		KeywordUtil.logInfo("[MESMER]: Crawl completed successfully")
		WebUI.delay(5)
		WebUI.refresh()
		WebUI.delay(10)
		stopCrawlResult = true
		getListofElementsinCrawlHistory()

	}
	else{

		KeywordUtil.markFailed("[MESMER]: Crawl is not completed after " + timeInHrs + " Hrs")
	}
	return stopCrawlResult
}

def getListofElementsinCrawlHistory() {
	WebDriver driver = DriverFactory.getWebDriver()
	if (WebUI.waitForElementVisible(historyIcon, 5) == true) {
		KeywordUtil.logInfo('[MESMER]: History Icon is displayed correctly')

		WebUI.click(historyIcon)
		WebUI.delay(2)

		//		VERIFICATION
		//"A modal will open with all the crawls history ran on the build with info like
		//		Initiated, Device, Crawl duration, Screens Crawled, New/Changed screens, Recommended tests created,
		//		App crashes, Max. heap use with Delete Crawl button in the botton of each crawl history.
		//Details like App crashes, Recommended tests created for the crawls should match with their respective recommended test pages.
		if (WebUI.waitForElementVisible(textCrawlHistory, 2) == true) {
			WebUI.delay(2)
			List<WebElement> initiated_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'Initiated')]/following-sibling::div[1]"))
			List<WebElement> device_CrawlHistory = driver.findElements(By.xpath("//div[@class='label' and contains(text(),'Device')]/following-sibling::div[1]"))
			List<WebElement> crawlDuration_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'Crawl Duration')]/following-sibling::div[1]"))
			List<WebElement> screensCrawled_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'Screens Crawled')]/following-sibling::div[1]"))
			List<WebElement> newChangedScreens_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'New/Changed Screens')]/following-sibling::div[1]"))
			List<WebElement> recommendedTestCreated_CrawlHistory = driver.findElements(By.xpath("//span[contains(text(),'Recommended Tests Created')]/parent::div/following-sibling::div[1]"))
			List<WebElement> appCrashes_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'App Crashes')]/following-sibling::div[1]"))
			List<WebElement> maxHeapUse_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'Max Heap Use')]/following-sibling::div[1]"))


			if(initiated_CrawlHistory.get(0).displayed == true){
				KeywordUtil.logInfo("[MESMER]: Initiated label for Crawl History is displayed")
			}
			else{
				KeywordUtil.markWarning("[MESMER]: Initiated label for Crawl History is not displayed")
			}

			if (device_CrawlHistory.get(1).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Device label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Device label for Crawl History is not displayed')
			}

			if (crawlDuration_CrawlHistory.get(2).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Crawl Duration label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Crawl Duration label for Crawl History is not displayed')
			}

			if (screensCrawled_CrawlHistory.get(3).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Screens Crawled label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Screens Crawled label for Crawl History is not displayed')
			}

			if (newChangedScreens_CrawlHistory.get(4).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: New/Changed Screens label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: New/Changed Screens label for Crawl History is not displayed')
			}

			if (recommendedTestCreated_CrawlHistory.get(5).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Recommended Tests Created label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Recommended Tests Created label for Crawl History is not displayed')
			}

			if (appCrashes_CrawlHistory.get(6).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: App Crashes label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: App Crashes label for Crawl History is not displayed')
			}

			if(maxHeapUse_CrawlHistory.get(7).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Max Heap Use label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Max Heap Use label for Crawl History is not displayed')
			}

			KeywordUtil.logInfo("[MESMER]: Crawl Initiated time - " + initiated_CrawlHistory.get(0).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Device used for Crawl is - " + device_CrawlHistory.get(1).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Duration of Crawl - " + crawlDuration_CrawlHistory.get(2).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Screen crawled - " + screensCrawled_CrawlHistory.get(3).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Number of New/Changed screens - " + newChangedScreens_CrawlHistory.get(4).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Number of Recommended TCs in Crawl - " + recommendedTestCreated_CrawlHistory.get(5).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Number of App Crashes in Crawl - " + appCrashes_CrawlHistory.get(6).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Number of Max Heap Used in Crawl - " + maxHeapUse_CrawlHistory.get(7).getText() + "\n")

			String crawlTime = crawlDuration_CrawlHistory.get(0).getText()

			int totalTime = getTimeFromHistory(crawlTime)
			KeywordUtil.logInfo("[MESMER]: Total Crawl time - " + totalTime)
			int dataTimeHrs = Integer.parseInt(timeInHrs)
			int dataTimemins = Integer.parseInt(timeInMinutes)
			int totalDataTime = dataTimeHrs*60+dataTimemins
			KeywordUtil.logInfo("[MESMER]: Total time from Data file - " + totalDataTime)
			if((totalTime+5) < totalDataTime){
				KeywordUtil.markFailed("[MESMER]: Crawl terminated without completing configured time. Time was set to "
						+ totalDataTime + " mins. However it completed " + totalTime + " mins" )
			}
			else{
				KeywordUtil.markPassed('[MESMER]: Crawl successfully ran for the configured time')
			}
		}
		else{
			KeywordUtil.logInfo("[MESMER]: Crawl History is not displayed")
		}


	}
	else{
		KeywordUtil.logInfo("[MESMER]: History icon is not displayed")
	}

}

private int getTimeFromHistory(String crawlTime){
	int hrsCount = 0;
	int minsCount = 0;
	int totalTime = 0;
	if(crawlTime.contains("h")){
		String[] timeList = crawlTime.split(" ")
		if(timeList != null && timeList.size() == 2){
			String hrsStr = timeList[0].replace("h", "");
			hrsCount = Integer.parseInt(hrsStr)
			String minsStr = timeList[1].replace("m", "");
			minsCount = Integer.parseInt(minsStr)
			totalTime = hrsCount*60+minsCount

		}
	}
	else{
		String minsStr = crawlTime.replace("m", "");
		minsCount = Integer.parseInt(minsStr)
		totalTime = minsCount

	}

	return totalTime
}

def cancelCrawlinQueue(){
	if(WebUI.waitForElementVisible(cancelCrawl, 5)==true){
		KeywordUtil.logInfo("[MESMER]: Click Cancel Crawl")
		WebUI.click(cancelCrawl)
		KeywordUtil.logInfo("[MESMER]: Cancel Crawl button is clicked")
		if(WebUI.waitForElementVisible(cancelCrawlEnqueWindow, 5)==true){
			KeywordUtil.logInfo("[MESMER]: Cancel Crawl confirmation window is displayed")

			if(WebUI.waitForElementVisible(YesBtnOnCancelCrawl, 5)==true){
				KeywordUtil.logInfo("[MESMER]: Yes button is displayed on Cancel Crawl")
				WebUI.click(YesBtnOnCancelCrawl)
				KeywordUtil.logInfo("[MESMER]: Yes button is clicked on Cancel Crawl")
				WebUI.delay(2)
				WebUI.refresh()

			}
			else{
				KeywordUtil.logInfo("[MESMER]: Yes button is not displayed")
			}
		}
		else{
			KeywordUtil.logInfo("[MESMER]: Cancel Crawl window is not displayed")
		}
	}
	else{
		KeywordUtil.logInfo("[MESMER]: Cancel Crawl button is not displayed")
	}
}


