import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
/*
 * MS-Manage Tests-03 | Verify that user can Start executions from Manage Test page
 */
// Go to managetests page
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.delay(2)
try{
	// Check test cases count and label
	if(WebUI.waitForElementPresent(counterTotalTC,10) && WebUI.waitForElementPresent(textTotalTC,10)){
		String manageTestsTotalCount = WebUI.getText(counterTotalTC);
		WebUI.delay(2)
		// Check the test cases and select few of them
		selectTestCases();
		// Find and click re-run button
		if(WebUI.waitForElementVisible(reRunIcon, 30)){
			WebUI.click(reRunIcon)
			WebUI.delay(1)
			// Wait for select device popup to appear
			if(WebUI.waitForElementVisible(selectDevicesPopup, 10)){
				// Find and select a device from list
				findAndSelectDevice();
				// Find and click the run button
				if(WebUI.waitForElementVisible(btnRun, 10)){
					WebUI.click(btnRun)
					// Wait for confirmation dialog to appear
					if(WebUI.waitForElementVisible(runConfimationDialog, 30)){
						// Find and click yes button on confirmation dialog
						if(WebUI.waitForElementVisible(btnYes, 30)){
							WebUI.click(btnYes)
							WebUI.delay(2)
							KeywordUtil.markPassed("PASSED: MS-ManageTest-03 passed")
						}
						else{
							KeywordUtil.markFailed("FAILED: Yes button not found")
						}
					}
					else{
						KeywordUtil.markWarning("WARNING: Confirmation dialog does not appeared")
					}
				}
				else{
					KeywordUtil.markWarning("WARNING: Run button not found")
				}
			}
			else{
				KeywordUtil.markFailed("FAILED: Select device popup not found")
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Re-Run icon not found')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Test Cases count or title not found')
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}

def void selectTestCases(){
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(1)
	try{
		List<WebElement> testCasesList = driver.findElements(By.xpath('//app-manage-test-case-list/div'))
		if(testCasesList != null && testCasesList.size() > 0){
			List<WebElement> numberOFcheckboxes = driver.findElements(By.xpath('//app-manage-test-case-list/div/div[@class="round"]'))
			int checkBoxesSize = numberOFcheckboxes.size();
			if(numberOFcheckboxes.size() > 3){
				checkBoxesSize = 3;
			}
			for(int i=0; i<=checkBoxesSize; i++){
				numberOFcheckboxes.get(i).click()
				WebUI.delay(1)
			}
			
		}
		else{
			KeywordUtil.markWarning("WARNING: There is no test case in the list")
		}
	}
	catch(Exception e){
		e.printStackTrace()
	}
}
def findAndSelectDevice(){
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(1)
	try{
		List<WebElement> devicesList = driver.findElements(By.xpath('//div[@class="deviceList ng-star-inserted"]/div'))
		if(devicesList != null && devicesList.size() > 0){
			devicesList.get(0).click()
			WebUI.delay(1)
		}
		else{
			KeywordUtil.markWarning("WARNING: There is no device in the list")
		}
	}
	catch(Exception e){
		e.printStackTrace()
	}
}
