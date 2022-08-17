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


CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)

MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{
	
	TestObject downloadFullLog = findTestObject('Object Repository/OR_AppMap/option_downloadFullLog')
	TestObject btnDeviceLogs = findTestObject('Object Repository/OR_AppMap/btn_deviceLogs')

	if(WebUI.waitForElementVisible(btnDeviceLogs, 60)){
		WebUI.click(btnDeviceLogs)
		if(WebUI.waitForElementVisible(downloadFullLog, 20)){
			WebUI.click(downloadFullLog)
			result = true
			MesmerLogUtils.markPassed("Logs are downloaded successfully")
		}
		else{
			MesmerLogUtils.markFailed("Download Logs option is not displayed")
		}
	}
	else{
		MesmerLogUtils.markFailed("Download Logs option is not displayed")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	
}

