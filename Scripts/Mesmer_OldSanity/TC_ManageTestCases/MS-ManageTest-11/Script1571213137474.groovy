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

CustomKeywords."com.mesmer.Utility.selectProject"(projName, xpathString, platformName, ProjectName)

CustomKeywords."com.mesmer.Utility.goToManageTests"()
WebUI.delay(2)
WebDriver driver = DriverFactory.getWebDriver()

try{
	if(WebUI.getText(reviewCount) == "0"){
		KeywordUtil.markWarning("WARNING: There is no test case in review")
	}
	else{
		WebUI.click(reviewCount);
		WebUI.delay(2)
		List<WebElement> InReviewList = driver.findElements(By.xpath('//div[@class="devicesWrap"]/descendant::div[1]/descendant::div[@class="timeStamp ng-star-inserted"]'))
		if(InReviewList != null && InReviewList.size() > 0){
			InReviewList.get(0).click()
			WebUI.delay(1)
			if(WebUI.waitForElementPresent(bookIcon, 30)){
				WebUI.click(bookIcon)
				WebUI.delay(2)
				KeywordUtil.markPassed("PASSED: Clicked On Book Icon")
	
				if(WebUI.verifyElementText(testCaseDetailText, verifyTestCaseDetailText) == true){
	
					KeywordUtil.markPassed("PASSED: ANOMALIES Text Matched")
					if(WebUI.waitForElementPresent(btnNextAnomaly, 10) == true){
						
							WebUI.verifyElementText(textAnomalyClickOne, verifyTextAnomalyClickOne)
							KeywordUtil.markPassed("PASSED: Text Matched Visual")
							WebUI.click(btnNextAnomaly)
							WebUI.click(btnNextAnomaly)
							WebUI.click(btnNextAnomaly)
							
							WebUI.verifyElementText(textAnomalyClickFour, verifyTextAnomalyClickFour)
							KeywordUtil.markPassed("PASSED: Text Matched App Error")
							
						
					}else{
						KeywordUtil.markFailed("FAILED: Next Button Found")
	
					}
				}else{
	
					KeywordUtil.markFailed("FAILED: ANOMALIES Text Not Matched")
				}
	
			}else{
				KeywordUtil.markFailed("FAILED: Book Icon Not Found")
			}
		}
		else{
			KeywordUtil.markWarning("WARNING: There is no test case in In-Review list")
		}
	}
}catch(Exception e){
	e.printStackTrace()

}finally{
WebUI.refresh()
}
