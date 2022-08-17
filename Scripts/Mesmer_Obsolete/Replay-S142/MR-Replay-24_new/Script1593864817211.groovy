import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-22 | Status on title bar of the Replayed test case - UI
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	if(WebUI.waitForElementPresent(testLink, 20)==true){
		WebUI.click(testLink)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Test Case Opens")
		if(WebUI.waitForElementPresent(testCaseStatus, 10)==true){
			if(WebUI.waitForElementPresent(testCaseName, 10)==true){
				if(WebUI.waitForElementPresent(counterFailed, 10)==true){
					if(WebUI.waitForElementPresent(counterReview, 10)==true){
						if(WebUI.waitForElementPresent(counterDuration, 10)==true){
							if(WebUI.waitForElementPresent(jiraIssues, 10)==true){
								if(WebUI.waitForElementPresent(lblTag, 10)==true){
									if(WebUI.waitForElementPresent(btnRerun, 10)==true){
										if(WebUI.waitForElementPresent(btnOptions, 10)==true){
										}else{
											KeywordUtil.markFailed("FAILED: No options button found")
										}
									}else{
										KeywordUtil.markFailed("FAILED: No Rerun button found")
									}
								}else{
									KeywordUtil.markFailed("FAILED: No Add tag label found")
								}
							}else{
								KeywordUtil.markFailed("FAILED: No Jira Configuration in the Project")
							}
						}else{
							KeywordUtil.markFailed("FAILED: No duration counter found")
						}
					}else{
						KeywordUtil.markFailed("FAILED: No review counter found")
					}
				}else{
					KeywordUtil.markFailed("FAILED: No defects counter found")
				}
			}else{
				KeywordUtil.markFailed("FAILED: No test case name found")
			}
		}else{
			KeywordUtil.markFailed("FAILED: No test case status found")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to open test case")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
CustomKeywords.'com.mesmer.Utility.stopExecution'()
}