import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//Replay-DeviceSelecton-44 | Verify user can select dedicated single device for running test case
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
			String readydeviceList = findTestObject('Object Repository/OR_Replay/list_readyDeviceList').findPropertyValue('xpath').toString()

			List<WebElement> readydevice = driver.findElements(By.xpath(readydeviceList))
			int readyListCount = readydevice.size()
			println(readyListCount)
			if(readydevice.size() > 0 ){
				readydevice.get(0).click()
				KeywordUtil.markPassed("PASSED: Number of ready Devices " + readyListCount +" in the list")
				

			}else{
				KeywordUtil.markWarning("WARNING: No ready Devices Found")
			}
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