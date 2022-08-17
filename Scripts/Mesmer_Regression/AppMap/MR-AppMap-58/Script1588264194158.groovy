import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.AppMapController


//AppMap-History-58 Verify that latest crawl data should always appear on the top of the list 


//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{

	if(AppMapController.getInstance().openCrawlHistory())
	{
		MesmerLogUtils.logInfo("Crawl history is displayed")
		
		String date1 = AppMapController.getInstance().getCrawlIniatedValue(0, driver)
		String date2 = AppMapController.getInstance().getCrawlIniatedValue(1, driver)
		
		MesmerLogUtils.logInfo("Initiated Date and Time of Crawl 1 is " + date1)
		MesmerLogUtils.logInfo("Initiated Date and Time of Crawl 2 is " + date2)
		
		if(Utility.compareDateTime(date1, date2)){
			MesmerLogUtils.markPassed("Recent Crawl is showing at top")
		}
		else{
			MesmerLogUtils.markFailed("Recent Crawl is not showing at top")
		
		}

	}
	else{
		MesmerLogUtils.logInfo("Crawl history is not displayed")
	}

	WebUI.delay(2)

}
catch(Exception e){
	println(e.stackTrace)
}
finally{
//	KTRequestHandler.getXHRCalls()
//	KTRequestHandler.getBrowserConsoleLogs()
//	KTRequestHandler.clearBrowserConsoleLogs()
}

