import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.AppMapController

//AppMap-History-62 Verify recommended test cases of selected crawl history should be displayed

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{

	int crawlNumber = 1
	if(AppMapController.getInstance().openCrawlHistory())
	{
		MesmerLogUtils.logInfo("Crawl history is displayed")

		int RecommendedTestsFromCrawl = AppMapController.getInstance().getRecommendedTestsFromCrawl(crawlNumber)
		if(AppMapController.getInstance().clickViewRecommendedButtonFromCrawlHistory(crawlNumber)){
			MesmerLogUtils.markPassed("User is successfully navigated to Recommended page")
				
			String recoTests = WebUI.getText(numberOfRecommendedTests)
			int number = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(recoTests)
			MesmerLogUtils.logInfo("Number of Test Cases from Recommended Page - " + recoTests)
			
			if(RecommendedTestsFromCrawl == number){
				MesmerLogUtils.markPassed("AppMap Test Case # 62 is passed")
				
			
				
			}
			else{
				MesmerLogUtils.markFailed("AppMap Test Case # 62 is failed")
			}
			
		}else{
			MesmerLogUtils.markFailed("Recommended Tests page is not displayed")
		}
		

	}
	else{
		MesmerLogUtils.logInfo("Crawl history is not displayed")
	}

	WebUI.delay(5)

}
catch(Exception e){
	e.printStackTrace()
}
finally{

}

