import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.delay(2)
WebDriver driver = DriverFactory.getWebDriver()

try{
	if(WebUI.verifyElementVisible(testCaseBar) == true){
		if(WebUI.getText(testCasesCount) == "0"){
			KeywordUtil.markPassed("PASSED: There is no test case in the list")
		}
		else{
			if(WebUI.verifyElementVisible(counterNeedsReview)){
				if(WebUI.getText(counterNeedsReview) == "0"){
					KeywordUtil.markPassed("PASSED: There is no test case in review list")
				}
				else{
					WebUI.click(counterNeedsReview)
					List<WebElement> InReviewList = driver.findElements(By.xpath('//div[@class="actionPanel inReview"]'))
					if(InReviewList != null && InReviewList.size() > 0){
						InReviewList.get(0).click()
						WebUI.delay(2)
						WebUI.delay(2)
						if(WebUI.waitForElementPresent(infoOS, 10)==true){
							if(WebUI.waitForElementPresent(infoDeviceOS, 10)==true){
								if(WebUI.waitForElementPresent(infoDevice, 10)==true){
									if(WebUI.waitForElementPresent(infoDeviceModel, 10)==true){
										if(WebUI.waitForElementPresent(infoDeviceID, 10)==true){
											if(WebUI.waitForElementPresent(infoDeviceType, 10)==true){
												if(WebUI.waitForElementPresent(infoResolution, 10)==true){
													if(WebUI.waitForElementPresent(infoResolutionPixels, 10)==true){
														KeywordUtil.markPassed("PASSED: Test case passed")
													}
												}
											}
										}
									}
								}
							}
						}
					}
					else{
						KeywordUtil.markWarning('WARNING: There is no test in review list')
					}
				}
			}
			else{
				KeywordUtil.markFailed('FAILED: Needs review option not found')
			}
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: There is no test case title")
	}
}catch(Exception e){
	e.printStackTrace()

}finally{
WebUI.refresh()
}