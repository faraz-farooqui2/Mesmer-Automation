import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import com.sun.media.ui.CacheControlComponent.CancelButton

import controllers.AppMapController

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

//AppMap-History-59 Verify the previous crawl data also appear in crawl history section

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

		//Passing 5 to get data of recent 5 crawls
		if(AppMapController.getInstance().checkCrawlObjects(5))
		{
			MesmerLogUtils.markPassed("Test case AppMap#59 is passed")
		}
		else{
			MesmerLogUtils.markFailed("Test case AppMap#59 is failed")
		}

	}
	else{
		MesmerLogUtils.logInfo("Crawl history is not displayed")
	}

	WebUI.delay(5)

}
catch(Exception e){
	println(e.stackTrace)
}
finally{

}

