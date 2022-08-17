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

//AppMap-44 | Verify incompatible devices cannot be selected for crawl
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

						//3. Select the available device from the list for crawl.

						String incompatibleDevice = findTestObject('Object Repository/OR_AppMap/list_incompatible').findPropertyValue('xpath').toString()
						List<WebElement> incompatibleDeviceList = driver.findElements(By.xpath(incompatibleDevice))

						TestObject incomp = findTestObject('Object Repository/OR_AppMap/list_incompatible')


						if(WebUI.waitForElementPresent(incomDeviceList, 10)){
							WebUI.waitForElementClickable(incomp, 10)
						}
						else{
							KeywordUtil.markWarning("No incompatiable devices found")
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
	if(AppMapController.getInstance().clickCloseButton()){
		KeywordUtil.logInfo("Dialogue closed successfully")
	}else{
		WebUI.refresh()
	}
}

