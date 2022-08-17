import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-04 | Verify user should be able to see if a device is Ready to be used
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()

WebDriver driver = DriverFactory.getWebDriver()
try{

	//1. User clicks on replay icon
	if(WebUI.waitForElementPresent(btnReplay, 30)==true){
		WebUI.click(btnReplay)
		KeywordUtil.markPassed("PASSED: Clicked on Replay Button")
		if(WebUI.waitForElementPresent(deviceList, 10)==true){

			Utility.logCurrentUTCTime("Devices list fetching time in Test Result Page")

			String readyDeviceList = findTestObject('Object Repository/OR_Replay/list_readyDeviceList').findPropertyValue('xpath').toString()

			List<WebElement> readydevice = driver.findElements(By.xpath(readyDeviceList))

			if((readydevice != null) && (readydevice.size() > 0 )){
				KeywordUtil.markPassed("PASSED: Number of Ready Devices " + (readydevice.size()) +" in the list")
				Utility.logCurrentUTCTime("Number of Ready devices at this time in Test Result Page")

			}else{
				KeywordUtil.markWarning("WARNING: No Ready Devices Found")
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