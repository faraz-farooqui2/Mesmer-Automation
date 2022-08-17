import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.mesmer.MesmerLogUtils
import controllers.AppMapController

//AppMap-36 | Verify user can close Configure Crawl popup from cross icon or Cancel button

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{
	//1. Click on 3 dot menu button the top right corner of the AppMap screen.
	if(WebUI.waitForElementVisible(ThreeDotButton,10)==true){
		WebUI.click(ThreeDotButton)
		WebUI.delay(1)
		TestObject RunCrawlOption = findTestObject('Object Repository/OR_AppMap/optionDropdown_RunCrawl')
		if(WebUI.waitForElementVisible(RunCrawlOption,5)==true){
			WebUI.click(RunCrawlOption)
			WebUI.delay(1)
			if(AppMapController.getInstance().verifyConfigureCrawlDialogue()){
				if(AppMapController.getInstance().clickNextButton()){
					WebUI.delay(5)
					if(AppMapController.getInstance().verifyDeviceDialogue()){
						if(AppMapController.getInstance().clickCloseButton()){
							
						}else{
						MesmerLogUtils.markFailed("Could not close select device dialogue")
						}
					}else{
					MesmerLogUtils.markFailed("Could not verify select device dialogue")
				}
			}else{

				MesmerLogUtils.markFailed("Could not click on Next button")
			}
		}else{
			MesmerLogUtils.markFailed("Configure crawl dialogue not open")
		}

	}
	else{
		KeywordUtil.markFailed("[MESMER]: Run Crawl button is not displayed")
	}
}
else{
	KeywordUtil.markFailed("FAILED: 3 Dot button is not visible")
}
	

}
catch(Exception e){
	println(e.stackTrace)
}
finally{
WebUI.refresh()
}

