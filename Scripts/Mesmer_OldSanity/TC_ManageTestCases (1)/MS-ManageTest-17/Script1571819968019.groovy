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
CustomKeywords."com.mesmer.Utility.selectProject"(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
CustomKeywords."com.mesmer.Utility.goToManageTests"()
WebUI.delay(1)

WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(1)

try {
	if (WebUI.verifyElementVisible(testCaseBar) == true) {
		if (WebUI.getText(testCasesCount) == "0") {
			KeywordUtil.markFailed("FAILED: There is no test case in the list")
		} else {
			if (WebUI.waitForElementVisible(failedLabel, 10)) {
				driver.findElement(By.xpath('//div[@class="counter failed"]')).click()
				WebUI.delay(2)
				WebElement failedTestCasesCount = driver.findElement(By.xpath('//div[@class="counter failed current"]/span'))

				if ((failedTestCasesCount != null) && !(failedTestCasesCount.getText().equalsIgnoreCase("0"))) {
					checkTestCaseOptions()
				}
			} else {
				KeywordUtil.markWarning("WARNING: Failed test case not available")
			}
			WebUI.delay(2)
			if (WebUI.waitForElementVisible(brokenLabel, 10)) {
				driver.findElement(By.xpath('//div[@class="counter broken"]')).click()

				WebElement brokenTestCasesCount = driver.findElement(By.xpath('//div[@class="counter broken current"]/span'))

				if ((brokenTestCasesCount != null) && !(brokenTestCasesCount.getText().equalsIgnoreCase("0"))) {
					checkTestCaseOptions()
				}
			} else {
				KeywordUtil.markWarning("WARNING: Broken case not available")
			}
			WebUI.delay(2)
			if (WebUI.waitForElementVisible(needsReviewLabel, 10)) {
				driver.findElement(By.xpath('//div[@class="counter review"]')).click()
				WebUI.delay(2)
				WebElement reviewTestCasesCount = driver.findElement(By.xpath('//div[@class="counter review current"]/span'))

				if ((reviewTestCasesCount != null) && !(reviewTestCasesCount.getText().equalsIgnoreCase("0"))) {
					checkTestCaseOptions()
					KeywordUtil.markPassed("PASSED: MS-ManageTest-17 Successfull")
				}
				//					else {
				//                    KeywordUtil.markWarning("WARNING: No Test Cases ")
				//                }
			} else {
				KeywordUtil.markWarning("WARNING: Needs review test case not available")
			}
		}
	} else {
		KeywordUtil.markFailed("FAILED: There is no test case title")
	}
}
catch (Exception e) {
	e.printStackTrace()
}finally{
	// CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebUI.refresh()
}

def checkTestCaseOptions() {
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(1)
	if (WebUI.waitForElementVisible(textFunctional, 10)) {
		String functionalErrorsStr = WebUI.getText(textFunctional)

		int functionalErrorsCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(functionalErrorsStr)

		if (functionalErrorsCount > 0) {
			WebUI.click(textFunctional)

			WebUI.delay(2)

			List<WebElement> errorsList = driver.findElements(By.xpath('//app-manage-test-case-list/div'))

			if ((errorsList != null) && (errorsList.size() > 0)) {
				errorsList.get(0).click()

				WebUI.delay(1)

				if (WebUI.waitForElementVisible(viewAllResults, 10)) {
					KeywordUtil.markPassed("PASSED: View all results option present")
				}

				if (WebUI.waitForElementVisible(markAsDefect, 10)) {
					KeywordUtil.markPassed("PASSED: Mark as defect option present")
				}

				if (WebUI.waitForElementVisible(ignoreText, 10)) {
					KeywordUtil.markPassed("PASSED: Ignore option present")
				}

				if (WebUI.waitForElementVisible(watchVideo, 10)) {
					KeywordUtil.markPassed("PASSED: Watch video option present")
				}

				if (WebUI.waitForElementVisible(correctExpected, 10)) {
					KeywordUtil.markPassed("PASSED: Correct expected option present")
				}
			}
		} else {
			KeywordUtil.markWarning("WARNING: Functional errors count is zero")
		}
	} else {
		KeywordUtil.markWarning("WARNING: There is no functional error text")
	}
}

