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
			String inUseDeviceList = findTestObject('Object Repository/OR_Replay/list_DeviceInUse').findPropertyValue('xpath').toString()

			List<WebElement> inUsedevice = driver.findElements(By.xpath(inUseDeviceList))
			int inUseListCount = inUsedevice.size()
			println(inUseListCount)
			if(inUsedevice.size() > 0 ){
				inUsedevice.get(0).click()
				KeywordUtil.markPassed("PASSED: Number of InUse Devices " + (inUseListCount) +" in the list")

			}else{
				KeywordUtil.markWarning("WARNING: No InUse Devices Found")
			}
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to Click on Replay Button")
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
CustomKeywords.'com.mesmer.Utility.stopExecution'()
}