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
		
}
catch(Exception e){
	e.printStackTrace()
}
finally{

	WebUI.refresh()
}

