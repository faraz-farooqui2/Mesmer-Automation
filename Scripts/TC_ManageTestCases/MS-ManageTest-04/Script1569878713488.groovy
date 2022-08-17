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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

//Calling Select Project Method
CustomKeywords."com.mesmer.Utility.selectProject"(projName, xpathString, platformName, ProjectName)

WebUI.delay(2)

CustomKeywords."com.mesmer.Utility.goToManageTests"()

WebUI.delay(1)

WebDriver driver = DriverFactory.getWebDriver()

WebUI.refresh()

WebUI.delay(1)

try {
    if (WebUI.verifyElementVisible(testCaseBar) == true) {
        List<WebElement> unAssignedList = driver.findElements(By.xpath('//span[@class="userName" and @id="null"]'))

        if ((unAssignedList != null) && (unAssignedList.size() > 0)) {
            WebElement unAssignedItem = unAssignedList.get(0)

            if (unAssignedItem != null) {
                WebUI.delay(1)

                Actions actions = new Actions(driver)

                actions.moveToElement(unAssignedItem).click().build().perform()

                WebUI.delay(1)

                if (WebUI.verifyElementVisible(unAssignedText)) {
                    WebUI.click(unAssignedText)
                } else {
                }
                
                if (WebUI.waitForElementVisible(usersListObject, 60)) {
                    List<WebElement> usersList = driver.findElements(By.xpath('//div[@class="usersList ng-star-inserted"]/a'))

                    if ((usersList != null) && (usersList.size() > 0)) {
                        usersList.get(0).click()

                        KeywordUtil.markPassed("PASSED: MS-ManageTest-04 PASSEDfull")
                    } else {
                        KeywordUtil.markFailed("FAILED: There is no user in the list")
                    }
                } else {
                    KeywordUtil.markFailed("FAILED: Users list not visible")

                    if (WebUI.verifyElementVisible(unAssignedText)) {
                        WebUI.click(unAssignedText)
                    } else {
                    }
                }
            } else {
            }
        } else {
            KeywordUtil.markWarning("WARNING: There is no unassigned element")
        }
    } else {
        KeywordUtil.markFailed("FAILED: Test case not found")
    }
}
catch (Exception e) {
} 
finally { 
    CustomKeywords.'com.mesmer.Utility.goToManageTests'()
}

