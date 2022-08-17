import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-15 | Baseline - Verify general behavior of baseline
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	if(WebUI.waitForElementPresent(testLink, 20)==true){
		WebUI.click(testLink)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Test Case Opens")
		if(WebUI.waitForElementPresent(txtDeviceName, 10)==true){

			if(WebUI.waitForElementPresent(txtDeviceInfo, 10)==true){

				if(WebUI.waitForElementPresent(txtExecutionDate, 10)==true){
						
					if(WebUI.waitForElementPresent(optionMenuEllipsis, 10)==true){
	
					}else{
						KeywordUtil.markFailed("FAILED: Option Menu Ellipsis not exists")
					}

				}else{
					KeywordUtil.markFailed("FAILED: Execution date not exists")
				}

			}else{
				KeywordUtil.markFailed("FAILED: Device Info not exists")
			}

		}else{
			KeywordUtil.markFailed("FAILED: Device Name not exists")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to open test case")
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
// CustomKeywords.'com.mesmer.Utility.stopExecution'()
}