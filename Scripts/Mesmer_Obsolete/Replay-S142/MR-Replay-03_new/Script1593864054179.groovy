import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-02 | Verify user should be able to see if a device is Broken
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
				Utility.logCurrentUTCTime("Broken devices at this time in Test Result Page")

			}else{
				KeywordUtil.markPassed("PASSED: No Broken Devices Found")
				Utility.logCurrentUTCTime("No Broken Device this time in Test Result Page")
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