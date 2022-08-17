import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

/**
 * 
 * MS-Manage Tests-08 | Verify that user should be able to see and use the 'Anomalies' and 'Device Specs' (OS, Resolution,Device ID,Device Model) and other available options successfully for a test result.
 *
 */

WebDriver driver = DriverFactory.getWebDriver()



try {
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToManageTests'()
		WebUI.delay(2)

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
						KeywordUtil.markPassed("PASSED: MS-ManageTest-8 Successfull")
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
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
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

