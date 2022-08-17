import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.MesmerLogUtils as MesmerLogUtils
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

/*
 * AppMap-History-65 Verify the count of app generated test cases on crawl history section should be the same as on the Recommended Test Cases page
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

WebUI.waitForPageLoad(4)
try{
	if(WebUI.waitForElementVisible(noTestCaseAvailableinRecommended, 5)==false){
		int appMapRecommendedTestsCount = 0;
		String text_TotalTCsRec = WebUI.getText(totalNumberOfRecommendedTests)
		int numberOfRecommendedTestCases = CustomKeywords.'com.mesmer.Utility.extractNumericData'(text_TotalTCsRec)
		// Go to AppMap to check the recommendedTests count
		CustomKeywords.'com.mesmer.Utility.goToAppMap'()
		WebUI.waitForPageLoad(4)
		
		if(WebUI.waitForElementVisible(btnHistory, 10)){
			MesmerLogUtils.logInfo("History button is displayed. Now clicking History button")
			WebUI.click(btnHistory)
			MesmerLogUtils.logInfo("History button is clicked")
			WebUI.delay(3)
			WebDriver driver = DriverFactory.getWebDriver()
			///div[@class="label viewRTC"]
			String historyResultsXPath = findTestObject('Object Repository/OR_Recommended/xpath_HistoryResult').findPropertyValue('xpath').toString()
			List<WebElement> historyResultsList = driver.findElements(By.xpath(historyResultsXPath))
			if(historyResultsList != null && historyResultsList.size() > 0){
				String historyElementXPath = findTestObject('Object Repository/OR_Recommended/xpath_historyElement').findPropertyValue('xpath').toString()
				List<WebElement> historyElementsList = driver.findElements(By.xpath(historyElementXPath))
				if(historyElementsList != null && historyElementsList.size() > 0){
					for(int i= 0; i < historyElementsList.size(); i++){
						if(historyElementsList.get(i).getText().equalsIgnoreCase("Recommended Tests Created")){
							System.out.println("Recommended Count === "+historyElementsList.get(i+1).getText())
							String testText = historyElementsList.get(i+1).getText();
							appMapRecommendedTestsCount = CustomKeywords.'com.mesmer.Utility.extractNumericData'(testText)
							break;
						}
					}
				}
			}
			if(numberOfRecommendedTestCases == appMapRecommendedTestsCount){
				MesmerLogUtils.markPassed("MR-AppMap-65 Successful")
			}
			else{
				MesmerLogUtils.markFailed("Recommened Test counts does not match")
			}
		}
		else{
			MesmerLogUtils.markFailed("History icon not found")
		}
		
	}
	else{
		MesmerLogUtils.markWarning("There is no Test Case available in Recommended Test Cases")
	}
	
}
catch(Exception e){
	e.printStackTrace()
}
