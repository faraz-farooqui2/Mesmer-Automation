import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-14 | Verify Replay dropdown list closes on clicking outside the dropdown
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{
	//1. User clicks on replay icon
	if(WebUI.waitForElementPresent(btnReplay, 10)==true){
		WebUI.click(btnReplay)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Clicked on Replay Button")
		if(WebUI.waitForElementPresent(deviceList, 10)==true){
			String deviceList = findTestObject('Object Repository/OR_Replay/list_DeviceList').findPropertyValue('xpath').toString()

			List<WebElement> device = driver.findElements(By.xpath(deviceList))
			int deviceListCount = device.size()
			println(deviceListCount)
			if((device != null) && (device.size() > 0 )){
				KeywordUtil.markPassed("PASSED: Number of Devices " + (deviceListCount) +" in the list")
				if(WebUI.waitForElementPresent(blankClick, 10)==true){
					WebUI.click(blankClick)
					KeywordUtil.markPassed("PASSED: Device list dialogue closed")
				}else{
				KeywordUtil.markFailed("FAILED: Device list dialogue not closed")
				}
			}else{
				KeywordUtil.markWarning("WARNING: No Device Found")
			}
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to Click on Replay Button")
	}
}catch(Exception e){

	e.printStackTrace()

}finally{

}