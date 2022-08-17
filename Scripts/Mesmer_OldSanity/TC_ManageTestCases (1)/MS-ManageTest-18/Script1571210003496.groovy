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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.interactions.Action as Action
import org.openqa.selenium.interactions.Actions as Actions

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.delay(1)

WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(1)

try {
    if (WebUI.verifyElementVisible(testCaseBar)) {
        if (WebUI.getText(testCasesCount) == "0") {
            KeywordUtil.markPassed("PASSED: There is no test case in the list")
        } else {
			List<WebElement> testCasesList = driver.findElements(By.xpath('//app-manage-test-case-list/div'))
			if(testCasesList != null && testCasesList.size() > 1){
				WebElement iconDots = driver.findElement(By.xpath('//app-manage-test-case-list/div[1]/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/div/a/span'))
				if(iconDots != null){
					iconDots.click()
					WebUI.delay(2)
					List<WebElement> popOverEditIcon = driver.findElements(By.xpath('//popover-container/div[@class="popover-content popover-body"]/div/a/span[@class="icon editTestCase"]'))
					if(popOverEditIcon != null && popOverEditIcon.size() > 0){
						popOverEditIcon.get(0).click()
					}
					WebUI.delay(2)
					//					WebUI.takeScreenshot("/Users/untitled/test2.png")
					if(WebUI.waitForElementVisible(editTestOptionsIcon, 60)){
						WebUI.delay(1)
						WebUI.click(editTestOptionsIcon)
						WebUI.delay(1)
						if(WebUI.waitForElementVisible(cloneOption, 60)){
							WebUI.click(cloneOption)
							if(WebUI.waitForElementVisible(btnYes, 60)){
								WebUI.click(btnYes)
								WebUI.delay(4)
								KeywordUtil.markPassed("PASSED: MS-ManageTest-18 Successfull")
							}
							else{
								KeywordUtil.markFailed("FAILED: Yes button not available")
							}
						}
						else{
							KeywordUtil.markFailed("FAILED: Clone option not available")
						}
					}
					else{
						KeywordUtil.markFailed("FAILED: Edit test case option icon not available")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: Dots icon not found")
				}
			}
			else{
				KeywordUtil.markPassed("PASSED: There is no test case in the list")
			}
        }
    } else {
        KeywordUtil.markFailed("FAILED: There is no test case bar")
    }
}
catch (Exception e) {
    e.printStackTrace()
}finally{
WebUI.refresh()
}

