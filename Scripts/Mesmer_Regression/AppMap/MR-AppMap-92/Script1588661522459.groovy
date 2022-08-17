import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.AppMapController

//AppMap-App Logs-92 | Verify if user deletes all the crawl histories from the crawl history section
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)


// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebDriver driver = DriverFactory.getWebDriver()

CustomKeywords.'com.mesmer.Utility.goToAppMap'()
try{
	if(WebUI.waitForElementPresent(historyBtn, 20)){
		WebUI.click(historyBtn)
		MesmerLogUtils.markPassed("Successfully clicked on History button")
		
		String crwalHistoryListXpath = findTestObject('Object Repository/OR_AppMap/CrawlHistory/xpath_crawlHistoryList').findPropertyValue('xpath').toString()
		List<WebElement> crwalHistoryList = driver.findElements(By.xpath(crwalHistoryListXpath))
		
		String deleteCrawlListXpath = findTestObject('Object Repository/OR_AppMap/CrawlHistory/xpath_deleteCrawlHistoryList').findPropertyValue('xpath').toString()
		List<WebElement> deleteCrawlList = driver.findElements(By.xpath(deleteCrawlListXpath))
		
		
		if (crwalHistoryList.size() > 0 && deleteCrawlList.size() > 0 ){
			
			
			deleteCrawlList.get(0).click()
			

			}else{
			MesmerLogUtils.markFailed("Crawl History  " + " : " + crwalHistoryList.size() +  "No delete button " + " : " +  deleteCrawlList.size())
			}
		
		}else{
		MesmerLogUtils.markFailed("Unable to click on history button")
	}
	

}catch(Exception e){
	e.printStackTrace()
}