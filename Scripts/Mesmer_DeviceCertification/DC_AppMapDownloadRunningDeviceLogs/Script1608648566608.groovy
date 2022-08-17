import org.openqa.selenium.WebDriver

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.AppMapController

try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)

	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(CustomKeywords.'com.mesmer.Utility.goToAppMap'()){

			if(AppMapController.getInstance().clickRunCrawl(hours , minutes , Device , deviceUDID)){
				
				MesmerLogUtils.logInfo("Device Selected :" + " " + Device + " "	+
									   "Device UDID " + " " + deviceUDID )
				
				WebUI.refresh()
				
				
				if(AppMapController.getInstance().deviceLogs("Download Log")){
					MesmerLogUtils.markPassed("Downloading....")
				}
				else{
					MesmerLogUtils.markFailed("Could not download logs")
				}

			}else{
				MesmerLogUtils.markFailed("Could not configure and start crawl")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to navigate to app map screen" )
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	if(AppMapController.getInstance().stopACrawl()){
		MesmerLogUtils.markPassed("Stopping Crawl")
	}else{
		MesmerLogUtils.markWarning("Could not stop a crawl")
	}
}

