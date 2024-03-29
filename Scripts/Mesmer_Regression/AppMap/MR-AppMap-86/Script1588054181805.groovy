import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.AppMapController

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()
try{
	

	if(WebUI.waitForElementPresent(btnDeviceLogs, 30)){
		WebUI.click(btnDeviceLogs)
	
	CustomKeywords.'com.mesmer.Utility_AppMap.checkSearchinLogs'()
	}else{
	MesmerLogUtils.markFailed("Unable to click on Device Logs button")
	}
	

}catch(Exception e){
	e.printStackTrace()
}