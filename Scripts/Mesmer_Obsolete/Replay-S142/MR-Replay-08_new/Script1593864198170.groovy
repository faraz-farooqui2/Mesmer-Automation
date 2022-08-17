import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//Replay-DeviceSelecton-41 | Verify user can select In Use device from test results screen
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
			Utility.logCurrentUTCTime("Number of devices at this time in Test Result Page")

			String inUseDeviceList = findTestObject('Object Repository/OR_Replay/list_DeviceInUse').findPropertyValue('xpath').toString()

			List<WebElement> inUsedevice = driver.findElements(By.xpath(inUseDeviceList))

			if((inUsedevice != null) && (inUsedevice.size() > 0 )){
				KeywordUtil.markPassed("PASSED: Number of InUse Devices " + (inUsedevice.size()) +" in the list")
				Utility.logCurrentUTCTime("Number of In Use devices at this time in Test Result Page")

			}else{
				KeywordUtil.markWarning("WARNING: No InUse Devices Found")
				Utility.logCurrentUTCTime("No In Use devices at this time in Test Result Page")
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