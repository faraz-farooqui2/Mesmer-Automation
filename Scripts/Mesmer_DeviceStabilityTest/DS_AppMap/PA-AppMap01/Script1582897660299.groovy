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
import javassist.compiler.ast.Keyword

import com.kms.katalon.core.testobject.TestObject
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils;



MesmerLogUtils.logInfo(SrNumber + " AppMap URL Before Selecting project" + WebUI.getUrl())
//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)

System.out.println("PATH --- " + AppMapPath)
MesmerLogUtils.logInfo(SrNumber + " AppMap URL After Selecting project" + WebUI.getUrl())

//Utility.takeScreenshot(AppMapPath, "BeforeGoingToAppMap" + SrNumber + "")
Utility.takeScreenshot(AppMapPath, SrNumber+"-BeforeGoingToAppMap")
CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()
Utility.takeScreenshot(AppMapPath, SrNumber+"-AfterGoingToAppMap")

try{

	WebUI.delay(2)

	if(AppMapController.getInstance().clickRunCrawl(timeInHrs, timeInMinutes)){
		KeywordUtil.logInfo("[MESMER]: Crawl is started successfully")
		
		if(checkCrawlStopped()){
			
					KeywordUtil.logInfo("[MESMER]: Crawl is completed successfully")
					//LastCrawlID
					String LastCrawlID1 = WebUI.executeJavaScript("return localStorage.getItem('LastCrawlID')", null)
					MesmerLogUtils.logInfo("Last Crawl ID of iteration " + SrNumber + " ---- > " + LastCrawlID1)
			
					if(LastCrawlID1 != null && !LastCrawlID1.isEmpty()){
						KTRequestHandler.getCrawlLogsErrors(LastCrawlID1)
						//					getCrawlLogsErrors(LastCrawlID1)
					}
					else{
						MesmerLogUtils.logInfo("Last crawl id not found")
					}
			
			
				}
				else{
			
					KeywordUtil.markFailed("[MESMER]: Crawl is not completed successfully")
					String LastCrawlID1 = WebUI.executeJavaScript("return localStorage.getItem('LastCrawlID')", null)
					MesmerLogUtils.logInfo("Last Crawl ID of iteration " + SrNumber + " ---- > " + LastCrawlID1)
			
					if(LastCrawlID1 != null && !LastCrawlID1.isEmpty()){
						KTRequestHandler.getCrawlLogsErrors(LastCrawlID1)
						//					getCrawlLogsErrors(LastCrawlID1)
					}
					else{
						MesmerLogUtils.logInfo("Last crawl id not found")
					}
				}
		
	}
	else{
		KeywordUtil.markFailed("[MESMER]: Crawl is failed to start")
	}

	

}
catch(Exception e){
	e.printStackTrace()
	Utility.takeScreenshot(AppMapPath, SrNumber+"-InCatchBlock")
	Utility.logCurrentUTCTime("Catch Block Time" + SrNumber + " ")
	MesmerLogUtils.logInfo("AppMap" + SrNumber + " URL IN Catch Block - " + WebUI.getUrl())
}
finally{
	Utility.takeScreenshot(AppMapPath, SrNumber + "-InFinalBlock")
	MesmerLogUtils.logInfo("AppMap" + SrNumber + " URL IN Final Block - " + WebUI.getUrl())
	Utility.logCurrentUTCTime("AppMap" + "Final Block Time" + SrNumber + " ")
	WebUI.refresh()
	KTRequestHandler.getXHRCalls()
	KTRequestHandler.getBrowserConsoleLogs()
	KTRequestHandler.clearBrowserConsoleLogs()
}


private boolean startCrawl() {
	boolean crawlResult = false

	Utility.takeScreenshot(AppMapPath, SrNumber+"-BeforeRunningCrawl")
	if(WebUI.waitForElementVisible(ThreeDotButton,5)==true){
		KeywordUtil.logInfo("[MESMER]: Three dot button is displayed")
		WebUI.click(ThreeDotButton)
		KeywordUtil.logInfo("[MESMER]: Three dot button is clicked")
		if(WebUI.waitForElementVisible(RunCrawlOption,5)==true){
			KeywordUtil.logInfo("[MESMER]: Run Crawl option is displayed")
			WebUI.click(RunCrawlOption)
			KeywordUtil.logInfo("[MESMER]: Run Crawl option is clicked")
			WebUI.delay(1)


			if(WebUI.waitForElementVisible(YesReCrawlButton,5)==true){
				KeywordUtil.logInfo("[MESMER]: Yes button is displayed on Run Crawl window")
				WebUI.click(YesReCrawlButton)
				KeywordUtil.logInfo("[MESMER]: Yes button is clicked on Run Crawl window")
				WebUI.delay(1)
				//WebUI.refresh()
				Utility.takeScreenshot(AppMapPath, SrNumber+"-AfterClickingCrawlYesButton")
				KeywordUtil.logInfo("[MESMER]: Calling Check Crawl Started method")
				if(checkCrawlStarted()){
					MesmerLogUtils.logInfo(SrNumber + " AppMap URL on starting crawl -- " + WebUI.getUrl())
					Utility.takeScreenshot(AppMapPath, SrNumber+"-OnStartingCrawl")
					WebUI.delay(2)
					WebUI.refresh()
					WebUI.delay(5)
					//checking Crawl Image
					if(WebUI.waitForElementPresent(stopCrawlButton, 5)==true || WebUI.waitForElementVisible(WebUI.waitForElementVisible(stillCrawlingText, 5)==true)){
						KeywordUtil.logInfo("[MESMER]:Crawl is in progress")
						crawlResult = true

					}
					else{
						KeywordUtil.markWarning("[MESMER]: Canvas image is not displayed and Crawl is not started")
					}
				}
				else{
					KeywordUtil.markWarning("[MESMER]: Crawl is not started")
				}
			}
			else{
				KeywordUtil.markWarning("[MESMER]: Yes button is not displayed on Run Crawl window")
			}
		}else{
			KeywordUtil.markWarning("[MESMER]: Run Crawl option is not displayed in list")
		}
	}
	else{
		KeywordUtil.markWarning("[MESMER]: Three dot button is not displayed")
	}
	return crawlResult;
}


private boolean checkCrawlStarted(){
	boolean checkCrawlStartedResult = false
	Utility.logCurrentUTCTime("Starting Crawl time")
	Utility.takeScreenshot(AppMapPath, SrNumber+"-CheckCrawlStarted")
	if(WebUI.waitForElementVisible(text_preparingDevice, 360)==true){
		KeywordUtil.logInfo("[MESMER]: Preparing device text is completed")

		if(WebUI.waitForElementVisible(text_deviceRetrieved, 180)==true){
			KeywordUtil.logInfo("[MESMER]: Device is Retrieved")

			if(WebUI.waitForElementVisible(text_deviceConnected, 180)==true){
				KeywordUtil.logInfo("[MESMER]: Device is Connected successfully")

				if(WebUI.waitForElementVisible(text_deviceConfigured, 180)==true){
					KeywordUtil.logInfo("[MESMER]: Device is Configured successfully")

					if(WebUI.waitForElementVisible(text_buildDownloaded, 180)==true){
						KeywordUtil.logInfo("[MESMER]: Build is downloaded on device")

						if(WebUI.waitForElementVisible(text_buildIntsalled, 180)==true){
							KeywordUtil.logInfo("[MESMER]: Build is installed on device")

							if(WebUI.waitForElementVisible(stillCrawlingText, 300)==true){
								KeywordUtil.logInfo("[MESMER]: Crawl started successfully")
								checkCrawlStartedResult = true
								Utility.takeScreenshot(AppMapPath, SrNumber+"-OnStartingCrawl")
							}
							else if(WebUI.waitForElementVisible(unableToStartCrawl, 5)==true){
								KeywordUtil.markFailed("[MESMER]: Unable to start crawl")

								//Cancelling crawl in queue
								cancelCrawlinQueue()

							}
							else if(WebUI.verifyElementPresent(historyIcon, 5)){

								WebUI.refresh()
								WebUI.delay(5)
								WebUI.click(historyIcon)
								WebUI.delay(2)
								Utility.takeScreenshot(AppMapPath, SrNumber+"-AfterClickingHistoryBtn")
								if(WebUI.waitForElementVisible(crawlinqueue, 5)==true){
									MesmerLogUtils.logInfo("Crawl is still in queue after waiting for 6-10 minutes")
								}
								else{
									MesmerLogUtils.logInfo("Crawl started after waiting for 6-10 minutes")
									checkCrawlStartedResult = true
								}

							}

							else{
								KeywordUtil.markWarning("[MESMER]: Crawl is not started...")
							}
						}else{
							KeywordUtil.markFailed("[MESMER]: Build installed is not completed")
						}

					}
					else{
						KeywordUtil.markFailed("[MESMER]: Build downloaded is not completed")
					}

				}
				else{
					KeywordUtil.markFailed("[MESMER]: Device Configured is not completed")
				}
			}
			else{
				KeywordUtil.markFailed("[MESMER]: Device Connected is not completed")
			}

		}else{
			KeywordUtil.markFailed("[MESMER]: Device Retrieved is not Completed")
		}

	}else{
		KeywordUtil.markFailed("[MESMER]: Device preparation is not Completed")
	}
	return checkCrawlStartedResult;
}

private boolean checkCrawlStopped(){
	boolean stopCrawlResult = false

	int dataTimeHrs = Integer.parseInt(timeInHrs)
	int dataTimemins = Integer.parseInt(timeInMinutes)
	int totalDataTime = dataTimeHrs*60+dataTimemins
	int totalTimeInSeconds = (totalDataTime*60)+600
	if(WebUI.waitForElementVisible(textCrawlCompleted, totalTimeInSeconds)==true){
		Utility.takeScreenshot(AppMapPath, SrNumber+"-WaitingforCrawltoStop")
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


			if(crawlDuration_CrawlHistory != null && crawlDuration_CrawlHistory.size()>0){
				String crawlTime = crawlDuration_CrawlHistory.get(0).getText()
				
				int totalTime = getTimeFromHistory(crawlTime)
				KeywordUtil.logInfo("[MESMER]: Total Crawl time - " + totalTime)
				int dataTimeHrs = Integer.parseInt(timeInHrs)
				int dataTimemins = Integer.parseInt(timeInMinutes)
				int totalDataTime = dataTimeHrs*60+dataTimemins
				KeywordUtil.logInfo("[MESMER]: Total time from Data file - " + totalDataTime)
				Utility.takeScreenshot(AppMapPath, SrNumber+"-CrawlHistory")
				if((totalTime+5) < totalDataTime){
					KeywordUtil.markFailed("[MESMER]: Crawl terminated without completing configured time. Time was set to "
							+ totalDataTime + " mins. However it completed " + totalTime + " mins" )
				}
				else{
					KeywordUtil.markPassed('[MESMER]: Crawl successfully ran for the configured time')
				}
				
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


