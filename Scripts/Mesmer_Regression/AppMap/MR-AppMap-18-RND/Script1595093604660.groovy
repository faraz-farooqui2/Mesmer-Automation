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
CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()

try{

	WebUI.delay(1)
	//Set crawl time and select device

	//Start Crawl
//	if(startCrawl()){
//		KeywordUtil.logInfo("[MESMER]: Crawl started successfully")
//
//		WebUI.delay(180)
//
//		if(checkCrawlStop()){
//			MesmerLogUtils.markPassed("Crawl is stopped successfully")
			if(recommendedTestFromCrawl()){
				MesmerLogUtils.markPassed("Test Case 17 is passed")
			}
			else{
				MesmerLogUtils.markPassed("Test Case 17 is failed")
			}
			WebUI.delay(2)
//		}else{
//			MesmerLogUtils.markFailed("Crawl is unable to stop")
//		}
//
//	}
//	else{
//		KeywordUtil.markFailed("[MESMER]: Crawl is not started")
//	}

	WebUI.delay(2)



}
catch(Exception e){
	e.printStackTrace()
}
finally{

	WebUI.refresh()
}



//private boolean startCrawl() {
//	boolean crawlResult = false
//
//	if(WebUI.waitForElementVisible(ThreeDotButton,5)==true){
//		KeywordUtil.logInfo("[MESMER]: Three dot button is displayed")
//		WebUI.click(ThreeDotButton)
//		KeywordUtil.logInfo("[MESMER]: Three dot button is clicked")
//		if(WebUI.waitForElementVisible(RunCrawlOption,5)==true){
//			KeywordUtil.logInfo("[MESMER]: Run Crawl option is displayed")
//			WebUI.click(RunCrawlOption)
//			KeywordUtil.logInfo("[MESMER]: Run Crawl option is clicked")
//			WebUI.delay(1)
//
//			if(WebUI.waitForElementVisible(NoReCrawlBtn, 5)==true){
//				MesmerLogUtils.logInfo("No button is displayed on Re Crawl popup")
//				WebUI.click(NoReCrawlBtn)
//				MesmerLogUtils.logInfo("No button is clicked on Re Crawl popup")
//				WebUI.delay(2)
//
//				if(WebUI.waitForElementNotPresent(NoReCrawlBtn, 2)==true){
//					MesmerLogUtils.markPassed("No button is clicked and Re-crawl popup is disappeared successfully")
//				}
//				else{
//					MesmerLogUtils.markFailed("Re-crawl popup is not disappeared")
//				}
//
//
//			}
//
//			if(WebUI.waitForElementVisible(ThreeDotButton,5)==true){
//				KeywordUtil.logInfo("[MESMER]: Three dot button is displayed")
//				WebUI.click(ThreeDotButton)
//				KeywordUtil.logInfo("[MESMER]: Three dot button is clicked")
//				if(WebUI.waitForElementVisible(RunCrawlOption,5)==true){
//					KeywordUtil.logInfo("[MESMER]: Run Crawl option is displayed")
//					WebUI.click(RunCrawlOption)
//					KeywordUtil.logInfo("[MESMER]: Run Crawl option is clicked")
//					WebUI.delay(1)
//				}
//				else{
//
//					KeywordUtil.markWarning("[MESMER]: Run Crawl option is not displayed in list")
//				}
//
//			}
//			else{
//				KeywordUtil.markWarning("[MESMER]: Three dot button is not displayed")
//			}
//
//
//			if(WebUI.waitForElementVisible(YesReCrawlButton,5)==true){
//				KeywordUtil.logInfo("[MESMER]: Yes button is displayed on Run Crawl window")
//				WebUI.click(YesReCrawlButton)
//				KeywordUtil.logInfo("[MESMER]: Yes button is clicked on Run Crawl window")
//				WebUI.delay(4)
//				//WebUI.refresh()
//				KeywordUtil.logInfo("[MESMER]: Calling Check Crawl Started method")
//				if(checkCrawlStarted()){
//
//					WebUI.delay(2)
//					WebUI.refresh()
//					WebUI.delay(5)
//					//checking Crawl Image
//					if(WebUI.waitForElementPresent(stopCrawlButton, 5)==true || WebUI.waitForElementVisible(WebUI.waitForElementVisible(stillCrawlingText, 5)==true)){
//						KeywordUtil.logInfo("[MESMER]:Crawl is in progress")
//						crawlResult = true
//
//					}
//					else{
//						KeywordUtil.markWarning("[MESMER]: Canvas image is not displayed and Crawl is not started")
//					}
//				}
//				else{
//					KeywordUtil.markWarning("[MESMER]: Crawl is not started")
//				}
//			}
//			else{
//				KeywordUtil.markWarning("[MESMER]: Yes button is not displayed on Run Crawl window")
//			}
//		}else{
//			KeywordUtil.markWarning("[MESMER]: Run Crawl option is not displayed in list")
//		}
//	}
//	else{
//		KeywordUtil.markWarning("[MESMER]: Three dot button is not displayed")
//	}
//	return crawlResult;
//}


//private boolean checkCrawlStarted(){
//	boolean checkCrawlStartedResult = false
//	Utility.logCurrentUTCTime("Starting Crawl time")
//	if(WebUI.waitForElementVisible(text_preparingDevice, 180)==true){
//		KeywordUtil.logInfo("[MESMER]: Preparing device text is completed")
//
//		if(WebUI.waitForElementVisible(text_deviceRetrieved, 180)==true){
//			KeywordUtil.logInfo("[MESMER]: Device is Retrieved")
//
//			if(WebUI.waitForElementVisible(text_deviceConnected, 180)==true){
//				KeywordUtil.logInfo("[MESMER]: Device is Connected successfully")
//
//				if(WebUI.waitForElementVisible(text_deviceConfigured, 180)==true){
//					KeywordUtil.logInfo("[MESMER]: Device is Configured successfully")
//
//					if(WebUI.waitForElementVisible(text_buildDownloaded, 180)==true){
//						KeywordUtil.logInfo("[MESMER]: Build is downloaded on device")
//
//						if(WebUI.waitForElementVisible(text_buildIntsalled, 180)==true){
//							KeywordUtil.logInfo("[MESMER]: Build is installed on device")
//
//							if(WebUI.waitForElementVisible(stillCrawlingText, 60)==true){
//								KeywordUtil.logInfo("[MESMER]: Crawl started successfully")
//								checkCrawlStartedResult = true
//							}
//							else if(WebUI.waitForElementVisible(unableToStartCrawl, 5)==true){
//								KeywordUtil.markFailed("[MESMER]: Unable to start crawl")
//
//								//Cancelling crawl in queue
//								cancelCrawlinQueue()
//
//							}
//							else{
//								KeywordUtil.markWarning("[MESMER]: Crawl is not started...")
//							}
//						}else{
//							KeywordUtil.markFailed("[MESMER]: Build installed is not completed")
//						}
//
//					}
//					else{
//						KeywordUtil.markFailed("[MESMER]: Build downloaded is not completed")
//					}
//
//				}
//				else{
//					KeywordUtil.markFailed("[MESMER]: Device Configured is not completed")
//				}
//			}
//			else{
//				KeywordUtil.markFailed("[MESMER]: Device Connected is not completed")
//			}
//
//		}else{
//			KeywordUtil.markFailed("[MESMER]: Device Retrieved is not Completed")
//		}
//
//	}else{
//		KeywordUtil.markFailed("[MESMER]: Device preparation is not Completed")
//	}
//	return checkCrawlStartedResult;
//}


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

			if (device_CrawlHistory.get(0).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Device label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Device label for Crawl History is not displayed')
			}

			if (crawlDuration_CrawlHistory.get(0).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Crawl Duration label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Crawl Duration label for Crawl History is not displayed')
			}

			if (screensCrawled_CrawlHistory.get(0).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Screens Crawled label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Screens Crawled label for Crawl History is not displayed')
			}

			if (newChangedScreens_CrawlHistory.get(0).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: New/Changed Screens label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: New/Changed Screens label for Crawl History is not displayed')
			}

			if (recommendedTestCreated_CrawlHistory.get(0).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Recommended Tests Created label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Recommended Tests Created label for Crawl History is not displayed')
			}

			if (appCrashes_CrawlHistory.get(0).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: App Crashes label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: App Crashes label for Crawl History is not displayed')
			}

			if(maxHeapUse_CrawlHistory.get(0).displayed == true) {

				KeywordUtil.logInfo('[MESMER]: Max Heap Use label for Crawl History is displayed')
			} else {
				KeywordUtil.markWarning('[MESMER]: Max Heap Use label for Crawl History is not displayed')
			}

			KeywordUtil.logInfo("[MESMER]: Crawl Initiated time - " + initiated_CrawlHistory.get(0).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Device used for Crawl is - " + device_CrawlHistory.get(0).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Duration of Crawl - " + crawlDuration_CrawlHistory.get(0).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Screen crawled - " + screensCrawled_CrawlHistory.get(0).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Number of New/Changed screens - " + newChangedScreens_CrawlHistory.get(0).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Number of Recommended TCs in Crawl - " + recommendedTestCreated_CrawlHistory.get(0).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Number of App Crashes in Crawl - " + appCrashes_CrawlHistory.get(0).getText() + "\n")
			KeywordUtil.logInfo("[MESMER]: Number of Max Heap Used in Crawl - " + maxHeapUse_CrawlHistory.get(0).getText() + "\n")

			String crawlTime = crawlDuration_CrawlHistory.get(0).getText()

			int totalTime = getTimeFromHistory(crawlTime)
			KeywordUtil.logInfo("[MESMER]: Total Crawl time - " + totalTime)
			int dataTimeHrs = Integer.parseInt(timeInHrs)
			int dataTimemins = Integer.parseInt(timeInMinutes)
			int totalDataTime = dataTimeHrs*60+dataTimemins
			KeywordUtil.logInfo("[MESMER]: Total time from Data file - " + totalDataTime)

			if((totalTime+5) < totalDataTime){
				KeywordUtil.markFailed('[MESMER]: Crawl terminated without completing configured time')
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

private boolean checkCrawlStop(){
	boolean crawlStopResult = false

	if(WebUI.waitForElementVisible(stopCrawlButton, 3)==true){
		WebUI.click(stopCrawlButton)

		if(WebUI.waitForElementVisible(confirmationWindow_StopCrawl, 3)==true){
			if(WebUI.waitForElementVisible(yesButton_StopCrawl, 3)==true){
				WebUI.click(yesButton_StopCrawl)
				WebUI.delay(10)

				if(WebUI.waitForElementVisible(textCrawlCompleted, 10)==true){
					MesmerLogUtils.markPassed("Crawl stopped successfully")
					crawlStopResult = true
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
	return crawlStopResult;
}

private boolean recommendedTestFromCrawl(){

	boolean result = false
	//Verify Recommended Tests text
	if(WebUI.verifyElementVisible(numberOfTCsAfterCrawl)==true){

		String recommendedText = WebUI.getText(numberOfTCsAfterCrawl)
		MesmerLogUtils.logInfo("Text is --- " + recommendedText)
		KeywordUtil.markPassed("PASSED: Recommended Tests text is displayed")
		int noOfTestCases = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(recommendedText)
		int num2 = CustomKeywords.'com.mesmer.Utility.extractNumericData'(recommendedText)
		MesmerLogUtils.logInfo("Number of Test Cases in Crawl = " + noOfTestCases)
		MesmerLogUtils.logInfo("Number of Test Cases in Crawl = " + num2)

	}
	else{
		KeywordUtil.markFailed("FAILED: Recommended Tests text is not displayed")
	}

	//Verify Recommended Tests button
	if(WebUI.verifyElementVisible(btnRecommended)==true){
		KeywordUtil.markPassed("PASSED: Recommended Tests button is displayed")
		WebUI.click(btnRecommended)

		if(WebUI.waitForElementVisible(RecommendedTestCasesPage, 10)==true){
			if(WebUI.waitForElementVisible(noTestCaseAvailableinRecommended, 5)==false){
				int appMapRecommendedTestsCount = 0;
				String text_TotalTCsRec = WebUI.getText(totalNumberOfRecommendedTests)
				int numberOfRecommendedTestCases = CustomKeywords.'com.mesmer.Utility.extractNumericData'(text_TotalTCsRec)
				
				if(noOfTestCases==numberOfRecommendedTestCases){
					MesmerLogUtils.markPassed("AppMap TCs count is equal to the count of Test Cases available on Recommended TCs page")
					result = true
				}
				else{
					MesmerLogUtils.markFailed("AppMap TCs count are not equal to the count of Test Cases available on Recommended TCs page")
				}
				
			}
			else{
				MesmerLogUtils.logInfo("No Test case found on Recommended page")
			}

		}
		else{
			MesmerLogUtils.logInfo("User is not navigated to Recommended page")
		}



	}
	else{
		KeywordUtil.markFailed("FAILED: Recommended Tests button is not displayed")
	}
	return result;
}
