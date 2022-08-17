import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-18 | Rerun Dropdown - Verify behavior of Run Button
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
			if(WebUI.waitForElementPresent(txtSelectDevices, 10)==true){
				if(WebUI.waitForElementPresent(deviceList, 10)==true){
					String deviceList = findTestObject('Object Repository/OR_Replay/list_DeviceList').findPropertyValue('xpath').toString()

					List<WebElement> device = driver.findElements(By.xpath(deviceList))
					int deviceListCount = device.size()
					println(deviceListCount)
					if(device.size() > 0 ){
						device.get(0).click()
						WebUI.delay(1)
						if(WebUI.waitForElementPresent(btnRun, 10)==true){
							WebUI.click(btnRun)
							WebUI.delay(2)
							if(WebUI.waitForElementPresent(txtConfirmationDlg, 20)==true){
								if(WebUI.waitForElementPresent(btnYes,20)==true){
									if(WebUI.waitForElementPresent(btnNo, 20)==true){
										if(WebUI.waitForElementPresent(btnClose, 20)==true){
											WebUI.click(btnClose)

										}else{
											KeywordUtil.markFailed("FAILED: No Close button found")
										}
									}else{
										KeywordUtil.markFailed("FAILED: No NO button found")
									}
								}else{
									KeywordUtil.markFailed("FAILED: No Yes button found")
								}
							}else{
								KeywordUtil.markFailed("FAILED: No confirmation dialogue appears")
							}
						}else{
							KeywordUtil.markFailed("FAILED: Unable to click on Run button")
						}
					}else{
						KeywordUtil.markFailed("FAILED: No device available")
					}
				}else{
					KeywordUtil.markWarning("WARNING: Unable to click on Provisioned Device")
				}

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
CustomKeywords.'com.mesmer.Utility.stopExecution'()
}