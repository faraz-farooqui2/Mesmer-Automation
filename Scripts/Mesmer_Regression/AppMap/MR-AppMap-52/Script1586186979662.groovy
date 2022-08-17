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
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

//AppMap-History-52 - Verify Crawl History icon should be available on AppMapp page

//Calling Select Project Method

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()

try{
	
	if(WebUI.waitForElementVisible(btnHistory, 5)==true){
		MesmerLogUtils.markPassed("AppMap TC#52 is Passed \n History Icon is displayed")
		
	}
	else{
		MesmerLogUtils.markFailed("AppMap TC#52 is Failed \n History icon is not displayed")
	}

}
catch(Exception e){
	println(e.stackTrace)
}
finally{

}

