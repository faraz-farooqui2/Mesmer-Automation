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


//AppMap-Device logs-70 - Verify user can search logs by entering any keyword in search field
//AppMap-Device logs-71 - Verify user can remove search keywords from field by clicking on cross icon shown in field when some text is entered

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()

try{

	CustomKeywords.'com.mesmer.Utility_AppMap.startCrawl'()
	
	
//	if(AppMapController.getInstance().startACrawl()){
		
		WebUI.delay(5)
		if(AppMapController.getInstance().deviceLogs("Pause")){
			MesmerLogUtils.markPassed("Logs are Paused")
			
			if(AppMapController.getInstance().deviceLogs("Resume")){
				MesmerLogUtils.markPassed("Logs are Resume")
			}
			else{
				MesmerLogUtils.markFailed("Logs are Not Resumed")
			}
		}
		else{
			MesmerLogUtils.markFailed("Logs are Not Paused")
		}
		

}
catch(Exception e){
	e.printStackTrace()
}
finally{
	
}

