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
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

CustomKeywords."com.mesmer.Utility.selectProject"(projName, xpathString, platformName, ProjectName)

CustomKeywords."com.mesmer.Utility.goToManageTests"()
WebUI.refresh()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(1)


try{
	if(WebUI.waitForElementPresent(btnNew, 10) == true){
		WebUI.click(btnNew)
		List<WebElement> newTestCasesList = driver.findElements(By.xpath('//div[@class="actionPanel new"]'))
		if(newTestCasesList != null && newTestCasesList.size() > 0) {
			newTestCasesList.get(0).click()
			if	(WebUI.verifyElementNotPresent(noAnomaliesHeader, 10) ==true){
				KeywordUtil.markPassed("PASSED: ManageTest-09 Test Case In New State Header Not Open")

			}else{
				KeywordUtil.markFailed("FAILED: Test Case In New State And Its Header Opens")
			}
		}
	}else{
		KeywordUtil.markWarning("WARNING: There is no new test case in the list")
	}
}catch(Exception e){
	e.printStackTrace()

}finally{
WebUI.refresh()
}
