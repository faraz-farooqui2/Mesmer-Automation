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

// AppMap-35 | Verify user can see only Android devices in Android project on App Map

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
						//3. Select the available device from the list for crawl.
						String devicesList = findTestObject('Object Repository/OR_AppMap/list_DeviceList').findPropertyValue('xpath').toString()
						List<WebElement> numberOfDevices = driver.findElements(By.xpath(devicesList))

						if(devicesList != null && devicesList.size() > 0){

							if(WebUI.waitForElementPresent(androidDeviceList, 5) == true && WebUI.waitForElementPresent(iOSDeviceList, 5) == false ){
								MesmerLogUtils.markPassed("No iOS device in Android Project")
							}else{
							KeywordUtil.markFailed("iOS device found in Android Project")
							}
						}
						else{
							KeywordUtil.markWarning("No devices available")
						}
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
		KeywordUtil.markFailed("FAILED: 3 Dot button is not visible")
	}


}
catch(Exception e){
	println(e.stackTrace)
}
finally{
WebUI.refresh()
}

