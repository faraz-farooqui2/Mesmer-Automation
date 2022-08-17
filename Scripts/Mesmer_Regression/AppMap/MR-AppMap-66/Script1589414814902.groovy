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


//"AppMap-Device logs-66 - Verify user can see device logs list icon on right side menu bar on App Map page"

//Calling Select Project Method

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{

	if(WebUI.waitForElementVisible(btnLogs, 5)==true){
		MesmerLogUtils.markPassed("Logs icon is displayed")
		MesmerLogUtils.markPassed("AppMap TC#66 is passed")
	}
	else{
		MesmerLogUtils.markFailed("Logs icon is not displayed")
		MesmerLogUtils.markFailed("AppMap TC#66 is failed")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{

}

