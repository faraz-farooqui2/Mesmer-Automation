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


////"AppMap-Device logs-67 - Verify user can select device logs list icon and log list (logs panel) opens along with different options"

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{

	if(WebUI.waitForElementVisible(btnLogs, 10)){
		MesmerLogUtils.logInfo("Logs icon is displayed. Now clicking logs icon")
		WebUI.click(btnLogs)
		MesmerLogUtils.logInfo("Logs icon is clicked")

		if(WebUI.verifyElementPresent(headingDeviceLogs, 10)){
			MesmerLogUtils.markPassed("Device Logs title is displayed")


			if(WebUI.verifyElementPresent(showFilter, 10)){
				MesmerLogUtils.markPassed("Show Filter option is displayed")

				if(WebUI.verifyElementPresent(logsSettings, 10)){
					MesmerLogUtils.markPassed("Logs Settings button is displayed")
				}
				else{
					MesmerLogUtils.markFailed("Logs Settings button is not displayed")
				}
			}
			else{
				MesmerLogUtils.markFailed("Show Filter option is not displayed")
			}

		}
		else{
			MesmerLogUtils.markFailed("Device Logs title is not displayed")
		}
	}else{
		MesmerLogUtils.markFailed("Could not clicked on log setting button")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}

