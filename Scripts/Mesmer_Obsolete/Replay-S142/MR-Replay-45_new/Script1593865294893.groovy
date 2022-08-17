import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//Replay-DeviceSelecton-43 | Verify user can select/deselect In Use device
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
				WebUI.delay(1)
				KeywordUtil.markPassed("PASSED: Number of InUse Devices " + (inUseListCount) +" in the list")
				if(WebUI.waitForElementPresent(deviceSelected, 10)==true){
					inUsedevice.get(0).click()
					WebUI.delay(1)
					if(WebUI.waitForElementPresent(deviceUnselectedInuse, 10)==true){
					}else{
						KeywordUtil.markFailed("FAILED: In Use device not unselected")
					}
				}else{
					KeywordUtil.markFailed("FAILED: In Use device not selected")
				}

			}else{
				WebUI.comment("COMMENT: InUse Devices Count " + "" + inUsedevice.size())
			}
		}else{
		KeywordUtil.markFailed("FAILED: No devices in list ")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to Click on Replay Button")
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
if(CustomKeywords.'com.mesmer.Utility.stopExecution'()){
		}else{
		KeywordUtil.markFailed("Could not stop test case replay")
	}
}