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

// AppMap-37 | Verify user should not be able to select multiple devices for crawl

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)

MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{
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

						String selectedReady = findTestObject('Object Repository/OR_AppMap/device_SelectedReady').findPropertyValue('xpath').toString()
						List<WebElement> selectedReadyDevice = driver.findElements(By.xpath(selectedReady))

						String deviceReady = findTestObject('Object Repository/OR_AppMap/list_ReadyDevices').findPropertyValue('xpath').toString()
						List<WebElement> readyDeviceList = driver.findElements(By.xpath(deviceReady))

						if(devicesList != null && devicesList.size() > 0){

							readyDeviceList.get(0).click()


							readyDeviceList.get(1).click()


							if (selectedReadyDevice.size() > 2){
								KeywordUtil.markFailed("FAILED: Multiple devices selected for a crawl")
							}else{
								KeywordUtil.markPassed("PASSED: Single device is selected for a crawl")
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

}

