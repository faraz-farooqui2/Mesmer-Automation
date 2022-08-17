import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

import controllers.RecommendedTestCaseController

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

WebUI.waitForPageLoad(10)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MR-RecommendedTest-20 successful")
	}
}
catch(Exception e){
	e.printStackTrace()
}

private boolean checkTestCaseAndPerformActions(){
	boolean result = false
	if(RecommendedTestCaseController.getInstance().checkIfRecommendedTestScreenOpen()){
		List<WebElement> testCasesList = RecommendedTestCaseController.getInstance().getTestCasesList()
		if(testCasesList != null && testCasesList.size() > 0){
			String dateText = RecommendedTestCaseController.getInstance().getShowCrawlFilterOptionText("Aug 18, 2020 11:32 PM")
			// Go to AppMap to check the date if its the same
			CustomKeywords.'com.mesmer.Utility.goToAppMap'()
			WebUI.waitForPageLoad(5)
			if(RecommendedTestCaseController.getInstance().clickAppMapHistoryBtn()){
				WebDriver driver = DriverFactory.getWebDriver()
				String historyDateXP = '//div[@class="crawlHistory"]/div[1]/div[3]'
				String historyResultsXPath = findTestObject('Object Repository/OR_Recommended/xpath_HistoryResult').findPropertyValue('xpath').toString()
				List<WebElement> historyResultsList = driver.findElements(By.xpath(historyResultsXPath))
				if(historyResultsList != null && historyResultsList.size() > 0){
					WebElement historyDateElement = driver.findElement(By.xpath(historyDateXP))
					if(historyDateElement != null && historyDateElement.isDisplayed()){
						String historyDate = historyDateElement.getText()
						if(dateText.equalsIgnoreCase(historyDate)){
							result = true
						}
					}
					else{
						MesmerLogUtils.markFailed("History date element not found")
					}
				}
			}
		}
	}
	return result
}
