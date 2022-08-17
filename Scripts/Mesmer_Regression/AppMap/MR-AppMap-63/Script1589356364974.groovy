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
import internal.GlobalVariable

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{

	String getUsername = GlobalVariable.username

	if(getUsername.contains("mesmerhq.com")){
		MesmerLogUtils.logInfo("User is from mesmerhq.com domain")
		

		if(AppMapController.getInstance().openCrawlHistory())
		{
			MesmerLogUtils.logInfo("Crawl history is displayed")

			if(AppMapController.getInstance().checkCrawlObjects(1))
			{
				MesmerLogUtils.markPassed("Test case 63 is passed")
			}
			else{
				MesmerLogUtils.markFailed("Test case 63 is failed")
			}

		}
		else{
			MesmerLogUtils.logInfo("Crawl history is not displayed")
		}
	}
	else{
		MesmerLogUtils.logInfo("User is not from mesmerhq.com domain")
	}

}
catch(Exception e){
	println(e.stackTrace)
}
finally{

}

