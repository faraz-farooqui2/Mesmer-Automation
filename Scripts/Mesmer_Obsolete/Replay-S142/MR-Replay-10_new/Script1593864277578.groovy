import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-07 | Verify that the complete device information appears in the drop-down
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
			String deviceList = findTestObject('Object Repository/OR_Replay/list_DeviceList').findPropertyValue('xpath').toString()

			List<WebElement> device = driver.findElements(By.xpath(deviceList))

			Utility.logCurrentUTCTime("Number of devices " +  device.size() +  " at this time in Test Result Page")

			if(WebUI.waitForElementPresent(deviceName, 10)==true){

				String dName = findTestObject('Object Repository/OR_Replay/device_Name').findPropertyValue('xpath').toString()
				List<WebElement> dNameList = driver.findElements(By.xpath(dName))

				for (WebElement webElement : dNameList) {
					String nameDevice = webElement.getText();
					KeywordUtil.logInfo("PASSED: Device Name is " +  nameDevice)
				}



				if(WebUI.waitForElementPresent(deviceModel, 10)==true){
					String dModel = findTestObject('Object Repository/OR_Replay/device_Model').findPropertyValue('xpath').toString()
					List<WebElement> dModelList = driver.findElements(By.xpath(dModel))


					for (WebElement webElement : dModelList) {
						String nameModel = webElement.getText();
						KeywordUtil.logInfo("PASSED: Device Model is " +  nameModel)
					}


					if(WebUI.waitForElementPresent(deviceState, 10)==true){
						String dState = findTestObject('Object Repository/OR_Replay/device_State').findPropertyValue('xpath').toString()
						List<WebElement> dStateList = driver.findElements(By.xpath(dState))

						for (WebElement webElement : dStateList) {
							String stateDevice = webElement.getText();
							KeywordUtil.logInfo("PASSED: Device State is " +  stateDevice)
						}
					}else{
						KeywordUtil.markFailed("FAILED: No Device State")
					}
				}else{
					KeywordUtil.markFailed("FAILED: No Device Model")
				}
			}else{
				KeywordUtil.markFailed("FAILED: No Device Name")
			}
		}else{
			KeywordUtil.markFailed("FAILED: Unable to Click on Replay Button")
		}
	}else{
		KeywordUtil.markFailed("FAILED: No Devices Found")
		Utility.logCurrentUTCTime("No devices at this time in Test Result Page")
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