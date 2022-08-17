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

//AppMap-50 | Verify if user does not select a device to run a crawl

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{
	//1. Click on 3 dot menu button the top right corner of the AppMap screen.

	TestObject ThreeDotButton = findTestObject('Object Repository/OR_AppMap/button_3Dots')
	TestObject RunCrawlOption = findTestObject('Object Repository/OR_AppMap/optionDropdown_RunCrawl')
	TestObject startCrawlbtn = findTestObject('Object Repository/OR_AppMap/btn_startCrawl')

	if(WebUI.waitForElementVisible(ThreeDotButton,5)==true){
		WebUI.click(ThreeDotButton)

		if(WebUI.waitForElementVisible(RunCrawlOption,5)==true){
			WebUI.click(RunCrawlOption)
			WebUI.delay(1)
			if(AppMapController.getInstance().verifyConfigureCrawlDialogue()){
				if(AppMapController.getInstance().clickNextButton()){
					WebUI.delay(5)
					if(AppMapController.getInstance().verifyDeviceDialogue()){
						if(AppMapController.getInstance().clickNextButton()){
							if(AppMapController.getInstance().setHrsMintsForCrawlConfig(hours , minutes)){
								if(AppMapController.getInstance().clickOkButton()){

									if(WebUI.waitForElementPresent(startCrawlbtn,10)){
										WebUI.click(startCrawlbtn)
										if(AppMapController.getInstance().checkCrawlStarted()){

										}else{
											KeywordUtil.logInfo("Could not verify crawl started")
										}
									}
									else{
										KeywordUtil.logInfo("Yes Crawl button is not displayed")
									}
								}else{
									MesmerLogUtils.logInfo("Could not click on Ok button")
								}
							}else{
								MesmerLogUtils.logInfo("Could not set time")
							}
						}else{
							MesmerLogUtils.logInfo("Could not click on Next button")
						}
					}else{
						MesmerLogUtils.logInfo("Could not verify select device dialogue")
					}
				}else{

					MesmerLogUtils.logInfo("Could not click on Next button")
				}
			}else{
				MesmerLogUtils.logInfo("Configure crawl dialogue not open")
			}

		}
		else{
			KeywordUtil.logInfo("Run Crawl button is not displayed")
		}
	}
	else{
		KeywordUtil.logInfo("Could not click on three dot button")
	}
}
catch(Exception e){
	println(e.stackTrace)
}
finally{

}

