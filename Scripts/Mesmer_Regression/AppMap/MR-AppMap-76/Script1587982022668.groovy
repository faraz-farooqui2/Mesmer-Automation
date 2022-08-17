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

	if(AppMapController.getInstance().deviceLogs("Wrap Text")){
		
	}
	else{
		MesmerLogUtils.markFailed("Logs are Not Wrap")
	}

}catch(Exception e){
	e.printStackTrace()
}