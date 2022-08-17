import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-03 | Verify user should not be able to select a broken device
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{
	//1. User clicks on replay icon
	if(WebUI.waitForElementPresent(btnReplay, 10)==true){
		WebUI.click(btnReplay)
		KeywordUtil.markPassed("PASSED: Clicked on Replay Button")
		if(WebUI.waitForElementPresent(deviceList, 10)==true){
			
			Utility.logCurrentUTCTime("Devices list fetching time in Test Result Page")
			
			String brokenDeviceList = findTestObject('Object Repository/OR_Replay/list_BrokenDevice').findPropertyValue('xpath').toString()

			List<WebElement> brokenDevice = driver.findElements(By.xpath(brokenDeviceList))
			
			if(brokenDevice != null && brokenDevice.size() > 0 ){
				KeywordUtil.markFailed("FAILED: Number of Broken Devices " + brokenDevice.size() +" in the list")
				
				if(WebUI.waitForElementNotPresent(brokenDeviceselected, 5)){
					KeywordUtil.markPassed("PASSED: Broken device is not selectable ")
				}else{
					KeywordUtil.markFailed("FAILED: Broken device is selectable")
				}
			}else{
				KeywordUtil.markPassed("PASSED: No Broken Devices Found")
			}
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to Click on Replay Button")
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
if(WebUI.waitForElementPresent(btnReplay, 10)==true){
	WebUI.click(btnReplay)
	
}else{
KeywordUtil.markFailed("FAILED: Unable to close replay dialogue")
}
}