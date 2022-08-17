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

	//	if(AppMapController.getInstance().startACrawl()){
	if(WebUI.waitForElementVisible(btnLogs, 5)){
		MesmerLogUtils.logInfo("Logs icon is displayed. Now clicking logs icon")
		WebUI.click(btnLogs)
		MesmerLogUtils.logInfo("Logs icon is clicked")

		if(WebUI.verifyElementPresent(logsSettings, 1)){
			MesmerLogUtils.markPassed("Logs Settings button is displayed")
			MesmerLogUtils.logInfo("Clicking Log Settings")
			WebUI.click(logsSettings)
			MesmerLogUtils.logInfo("Log Settings is clicked")

			if(WebUI.verifyElementPresent(optionFilter, 1)){
				MesmerLogUtils.markPassed("Filter option is displayed")


				if(WebUI.verifyElementPresent(optionPause, 1)){
					MesmerLogUtils.markPassed("Pause option is displayed")


					if(WebUI.verifyElementPresent(optionWrapText, 1)){
						MesmerLogUtils.markPassed("WrapText option is displayed")


						if(WebUI.verifyElementPresent(optionClearLog, 1)){
							MesmerLogUtils.markPassed("ClearLog option is displayed")


							if(WebUI.verifyElementPresent(optionDownloadLog, 1)){
								MesmerLogUtils.markPassed("Download Log option is displayed")

							}
							else{
								MesmerLogUtils.markFailed("Logs Settings button is not displayed")
							}
						}
						else{
							MesmerLogUtils.markFailed("Download Log option is not displayed")
						}
					}
					else{
						MesmerLogUtils.markFailed("ClearLog option is not displayed")
					}
				}
				else{
					MesmerLogUtils.markFailed("WrapText option is not displayed")
				}
			}
			else{
				MesmerLogUtils.markFailed("Pause option is not displayed")
			}
		}
		else{
			MesmerLogUtils.markFailed("Filter option is not displayed")
		}
	}
	else{
		MesmerLogUtils.logInfo("Could not click on log button")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}

