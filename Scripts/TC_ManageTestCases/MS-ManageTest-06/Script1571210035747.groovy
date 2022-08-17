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
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

//Calling Select Project Method
CustomKeywords."com.mesmer.Utility.selectProject"(projName, xpathString, platformName, ProjectName)
WebUI.delay(5)
CustomKeywords."com.mesmer.Utility.goToManageTests"()
WebUI.delay(3)
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(1)
try{
	if(WebUI.verifyElementVisible(testCaseBar) == true){
		if(WebUI.getText(testCasesCount) == "0"){
			KeywordUtil.markPassed("PASSED: There is no test case in the list")
		}
		else{
			if(WebUI.getText(failedCount) == "0"){
				if(WebUI.getText(brokenCount) == "0"){
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
							if(WebUI.waitForElementVisible(bookIcon, 60)){
								WebUI.click(bookIcon)
								KeywordUtil.markPassed("PASSED: Test case passed")	
							}
							else{
								KeywordUtil.markFailed("FAILED: Book icon not visible")
							}
						}
						else{
							KeywordUtil.markWarning("WARNING: There is no test case in InReview list")
						}
					}
				}
				else{
					WebUI.click(brokenCount);
					WebUI.delay(2)
					//div[@class="actionPanel broken"]
					List<WebElement> brokenList = driver.findElements(By.xpath('//div[@class="devicesWrap"]/descendant::div[1]/descendant::div[@class="timeStamp ng-star-inserted"]'))
					if(brokenList != null && brokenList.size() > 0){
						brokenList.get(0).click()
						WebUI.delay(2)
						if(WebUI.waitForElementVisible(bookIcon, 60)){
							WebUI.click(bookIcon)
							KeywordUtil.markPassed("PASSED: Test case passed")
						}
						else{
							KeywordUtil.markFailed("FAILED: Book icon not visible")
						}
					}
					else{
						KeywordUtil.markWarning("WARNING: There is no test case in broken list")
					}
				}
			}
			else{
				WebUI.click(failedCount);
				WebUI.delay(2)
				//div[@class="actionPanel broken"]
				List<WebElement> failedList = driver.findElements(By.xpath('//div[@class="devicesWrap"]/descendant::div[1]/descendant::div[@class="timeStamp ng-star-inserted"]'))
				if(failedList != null && failedList.size() > 0){
					failedList.get(0).click()
					WebUI.delay(2)
					if(WebUI.waitForElementVisible(bookIcon, 60)){
						WebUI.click(bookIcon)
						KeywordUtil.markPassed("PASSED: Test case passed")
					}
					else{
						KeywordUtil.markFailed("FAILED: Book icon not visible")
					}
				}
				else{
					KeywordUtil.markWarning("WARNING: There is no test case in failed list")
				}
			}

		}
	}
	else{
		KeywordUtil.markFailed("FAILED: There is no test case title")
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
	//CustomKeywords."com.mesmer.Utility.goToManageTests"()
	WebUI.refresh()
}