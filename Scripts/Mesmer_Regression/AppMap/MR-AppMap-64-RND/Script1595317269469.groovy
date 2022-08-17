import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import com.sun.media.ui.CacheControlComponent.CancelButton

import controllers.AppMapController
import internal.GlobalVariable

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{

	String getUsername = GlobalVariable.username

	if(getUsername.contains("mesmerhq.com")){
		MesmerLogUtils.logInfo("User is from mesmerhq.com domain")


		if(AppMapController.getInstance().openCrawlHistory())
		{
			MesmerLogUtils.logInfo("Crawl history is displayed")

			String crawlStatus = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlStatus').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlStatus = driver.findElements(By.xpath(crawlStatus))

			//Crawl Initiated Text and Value
			String crawlInitiated = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_Initiated').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlInitiated = driver.findElements(By.xpath(crawlInitiated))
			String crawlInitiatedValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_Initiated').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlInitiatedValue = driver.findElements(By.xpath(crawlInitiatedValue))

			//Crawl Device Text and Value
			String crawlDevice = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_Device').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlDevices = driver.findElements(By.xpath(crawlDevice))
			String crawlDeviceValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_Device').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlDevicesValue = driver.findElements(By.xpath(crawlDeviceValue))


			String crawlDuration = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_CrawlDuration').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlDuration = driver.findElements(By.xpath(crawlDuration))
			String crawlDurationValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_crawlDuration').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlDurationValue = driver.findElements(By.xpath(crawlDurationValue))


			String crawlScreens = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_ScreensCrawled').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlScreens = driver.findElements(By.xpath(crawlScreens))
			String crawlScreensValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_screenCrawled').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlScreensValue = driver.findElements(By.xpath(crawlScreensValue))


			String crawlRecommendedTests = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_RecommendedTests').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlRecommendedTests = driver.findElements(By.xpath(crawlRecommendedTests))
			String crawlRecommendedTestsValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_recommendedTests').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlRecommendedTestsValue = driver.findElements(By.xpath(crawlRecommendedTestsValue))

			String crawlAppCrashes = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_appCrashes').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlAppCrashes = driver.findElements(By.xpath(crawlAppCrashes))
			String crawlAppCrashesValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_appCrashes').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlAppCrashesValue = driver.findElements(By.xpath(crawlAppCrashesValue))


			String crawlMaxHeapUser = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_MaxHeapUser').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlMaxHeapUser = driver.findElements(By.xpath(crawlMaxHeapUser))
			String crawlMaxHeapUserValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_maxHeapUse').findPropertyValue('xpath').toString()
			List<WebElement> listOfCrawlMaxHeapUserValue = driver.findElements(By.xpath(crawlMaxHeapUserValue))


			//check status of crawl
			if(listOfCrawlStatus.get(i) != null){
				MesmerLogUtils.logInfo("Crawl Status is displayed")
				if(listOfCrawlStatus.get(0).getText() != null){
					MesmerLogUtils.markPassed("Crawl Status of Crawl " + (1) + " is -- " + listOfCrawlStatus.get(i).getText())
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Status text is not displayed")
				}
			}
			else{
				MesmerLogUtils.logInfo("Crawl Status info of Crawl#" + (1) + " is not displayed")
			}

			//Check Crawl Initiated
			if(listOfCrawlInitiated.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Initiated info is displayed")
				String initiatedinfo = listOfCrawlInitiatedValue.get(0).getText()
				if(initiatedinfo != null){
					MesmerLogUtils.markPassed("Crawl Initiated of Crawl " + (1) + " is -- " + initiatedinfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Initiated text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl Initiated info of Crawl#" + (1) + "is not displayed")

			}


			//Check Crawl Devices
			if(listOfCrawlDevices.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Devices info is displayed")
				String devicesInfo = listOfCrawlDevicesValue.get(0).getText()
				if(devicesInfo != null){
					MesmerLogUtils.markPassed("Crawl Devices of Crawl " + (1) + " is -- " + devicesInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Device text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl Devices info of Crawl#" + (1) + "is not displayed")

			}


			//Check Crawl Duration
			if(listOfCrawlDuration.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Duration info is displayed")
				String crawldurationInfo = listOfCrawlDurationValue.get(0).getText()
				if(crawldurationInfo != null){
					MesmerLogUtils.markPassed("Crawl Duration of Crawl " + (1) + " is -- " + crawldurationInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Duration text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl Duration info of Crawl#" + (1) + "is not displayed")

			}

			//Check Crawl Screens
			if(listOfCrawlScreens.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Screens info is displayed")
				String crawlScreenInfo = listOfCrawlScreensValue.get(0).getText()
				if(crawlScreenInfo != null){
					MesmerLogUtils.markPassed("Crawl Screen info of Crawl " + (1) + " is -- " + crawlScreenInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl screen info text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl screen info of Crawl#" + (1) + "is not displayed")

			}

			//Check Crawl Recommended Tests
			if(listOfCrawlRecommendedTests.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Recommended Tests info is displayed")
				String crawlRecommendedTestInfo = listOfCrawlRecommendedTestsValue.get(0).getText()
				if(crawlRecommendedTestInfo != null){
					MesmerLogUtils.markPassed("Crawl Recommended Tests info of Crawl " + (1) + " is -- " + crawlRecommendedTestInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Recommended Tests info text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl Recommended Tests info of Crawl#" + (1) + "is not displayed")

			}

			//Check Crawl App Crashes
			if(listOfCrawlAppCrashes.get(i) == null){
				MesmerLogUtils.markPassed("Crawl App Crashes info is not shown for Non-Mesmer user")

			}
			else{
				MesmerLogUtils.markFailed("Crawl App Crashes info is shown for Non-Mesmer user")

			}


			//Check Crawl UserMaxHeap Info
			if(listOfCrawlMaxHeapUser.get(i) ==null){
				MesmerLogUtils.markPassed("Crawl UserMaxHeap info is not shown for Non-Mesmer user")

			}
			else{
				MesmerLogUtils.markFailed("Crawl UserMaxHeap info is shown for Non-Mesmer user")

			}

		}
		else{
			MesmerLogUtils.logInfo("Crawl history is not displayed")
		}
	}
	else{
		MesmerLogUtils.logInfo("User is not from mesmerhq.com domain")
	}

}
catch(Exception e){
	println(e.stackTrace)
}
finally{

}

