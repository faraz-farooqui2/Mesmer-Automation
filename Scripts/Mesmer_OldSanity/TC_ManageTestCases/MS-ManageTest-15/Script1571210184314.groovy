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
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(1)
try{
	if(WebUI.verifyElementVisible(testCaseBar) == true){
		if(WebUI.getText(testCasesCount) == "0"){
			KeywordUtil.markFailed("FAILED: There is no test case in the list")
		}
		else{
			if(WebUI.waitForElementVisible(reRunIcon, 60)){
				WebUI.click(reRunIcon)
				WebUI.delay(1)
				if(WebUI.waitForElementVisible(selectDevicesPopup, 10)){
					List<WebElement> devicesList = driver.findElements(By.xpath('//div[@class="deviceList ng-star-inserted"]/div'))
					if(devicesList != null && devicesList.size() > 0){
						devicesList.get(0).click()
						WebUI.delay(1)
						if(WebUI.waitForElementVisible(btnRun, 10)){
							WebUI.click(btnRun)
							if(WebUI.waitForElementVisible(runConfimationDialog, 10)){
								if(WebUI.waitForElementVisible(btnYes, 10)){
									WebUI.click(btnYes)
									WebUI.delay(3)
									KeywordUtil.markPassed("PASSED: MS-ManageTest-15 successfull")
								}
								else{
									KeywordUtil.markFailed("FAILED: Yes button not available")
								}
							}
							else{
								KeywordUtil.markFailed("FAILED: Run confirmation dialog not visible")
							}
						}
						else{
							KeywordUtil.markFailed("FAILED: Run botton not visible")
						}
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: Test Case Failed")
				}
			}
			else{
				KeywordUtil.markFailed("FAILED: reRun icon not found")
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

}