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
WebUI.delay(2)
CustomKeywords."com.mesmer.Utility.goToManageTests"()
WebUI.delay(1)
WebUI.refresh()
WebUI.delay(1)
try{
	if(WebUI.verifyElementVisible(testCaseBar) == true){
		if(WebUI.getText(testCasesCount) == "0"){
			KeywordUtil.markFailed("FAILED: There is no test case in the list")
		}
		else{
			if(WebUI.waitForElementVisible(sortByLabel, 60)){
				WebUI.click(sortByLabel)
				WebUI.delay(1)
				if(WebUI.waitForElementVisible(latestRunOption, 10) && WebUI.waitForElementVisible(statusOption, 10)
				&& WebUI.waitForElementVisible(tagOption, 10)){
					KeywordUtil.markPassed("PASSED: MS-ManageTest-07 successfull")
				}
				else{
					KeywordUtil.markFailed("FAILED: Test Case Failed")
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
} finally{

	WebUI.refresh()
}