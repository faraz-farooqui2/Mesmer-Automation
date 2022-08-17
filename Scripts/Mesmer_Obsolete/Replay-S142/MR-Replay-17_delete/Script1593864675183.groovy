import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import org.openqa.selenium.interactions.Actions

//MR-Replay-17 | Rerun Button - Verify Device Selection behavior
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	if(WebUI.waitForElementPresent(testLink, 20)==true){
		WebUI.click(testLink)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Test Case Opens")
		if(WebUI.waitForElementPresent(btnRerun, 10)==true){
			WebUI.click(btnRerun)
			if(WebUI.waitForElementPresent(txtSelectDevices, 20)==true){
//				if(WebUI.waitForElementPresent(deviceList, 10)==true){
					String deviceList = findTestObject('Object Repository/OR_Replay/list_DeviceList').findPropertyValue('xpath').toString()

					List<WebElement> device = driver.findElements(By.xpath(deviceList))


					int deviceListCount = device.size()
					println(deviceListCount)

					if(device != null && device.size() > 1 ){
						Actions builder = new Actions(driver);
						builder.moveToElement(device.get(0)).perform();
						WebUI.delay(5)
//						device.get(0).click()
//						WebUI.delay(10)

//						if(WebUI.waitForElementPresent(deviceSelected, 10)==true){
//							if(device != null && device.size() > 0){
//								device.get(0).click()
//								WebUI.delay(1)
//							}
//							else{
//								KeywordUtil.markFailed("FAILED: Device not de-selected")
//							}
//
//						}else{
//							KeywordUtil.markFailed("FAILED: Could not find selected device")
//						}
					}else{
						KeywordUtil.markFailed("FAILED: No device available")
					}
//				}else{
//					KeywordUtil.markFailed("FAILED: Unable to click on Provisioned Device")
//				}

			}else{
				KeywordUtil.markFailed("FAILED: Select Devices not found")
			}

		}else{
			KeywordUtil.markFailed("FAILED: Unable to click on Rerun button")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to open test case")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	//CustomKeywords.'com.mesmer.Utility.stopExecution'()
}