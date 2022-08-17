import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.MesmerLogUtils as MesmerLogUtils
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils


//MR-Replay-01 | Verify clicking replay icon should show the list of all devices
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{
	
	String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
	List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

	MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
	if(titleStreamList.size() > 0){
		titleStreamList.get(0).click()
		WebUI.delay(3)
		MesmerLogUtils.markPassed("Clicked on Test Case")
		
	//1. User clicks on replay icon
	if(WebUI.waitForElementPresent(btnReplay, 10)==true){
		WebUI.click(btnReplay)
		MesmerLogUtils.markPassed("PASSED: Clicked on Replay Button")

		if(WebUI.waitForElementPresent(deviceList, 10)==true){
			
			Utility.logCurrentUTCTime("Devices list fetching time in Test Result Page")
			
			String deviceList = findTestObject('Object Repository/OR_Replay/list_DeviceList').findPropertyValue('xpath').toString()
			
			List<WebElement> device = driver.findElements(By.xpath(deviceList))
			
			if(device != null && device.size() > 0 ){
				MesmerLogUtils.markPassed("PASSED: Number of Devices " + device.size() +" in the list")
				Utility.logCurrentUTCTime("Number of devices at this time in Test Result Page")

			}else{
				MesmerLogUtils.markWarning("WARNING: No Device Found")
				Utility.logCurrentUTCTime("No devices at this time in Test Result Page")
			}
		}
	}else{
		MesmerLogUtils.markFailed("FAILED: Unable to Click on Replay Button")
	}
	}else{
	MesmerLogUtils.markFailed("FAILED: Unable to Click on Replay Button")
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
if(WebUI.waitForElementPresent(btnReplay, 10)==true){
	WebUI.click(btnReplay)
	
}else{
MesmerLogUtils.markFailed("FAILED: Unable to close replay dialogue")
}
}