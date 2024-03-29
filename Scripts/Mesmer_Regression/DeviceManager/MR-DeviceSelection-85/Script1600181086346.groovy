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

//MR-Device Selection-81 | Record | Verify clicking confiure crawl button should open a modal with devices list

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

						//3. Select the available device from the list for crawl.
						String devicesList = findTestObject('Object Repository/OR_AppMap/list_DeviceList').findPropertyValue('xpath').toString()
						List<WebElement> numberOfDevices = driver.findElements(By.xpath(devicesList))

						String deviceModelXpath = findTestObject('Object Repository/OR_AppMap/appMap_deviceModel').findPropertyValue('xpath').toString()
						List<WebElement> deviceModel = driver.findElements(By.xpath(deviceModelXpath))

						String deviceOSXpath = findTestObject('Object Repository/OR_AppMap/appMap_deviceOS').findPropertyValue('xpath').toString()
						List<WebElement> deviceOS = driver.findElements(By.xpath(deviceOSXpath))

						String deviceUDIDXpath = findTestObject('Object Repository/OR_AppMap/appMap_deviceUDID').findPropertyValue('xpath').toString()
						List<WebElement> deviceUDID = driver.findElements(By.xpath(deviceUDIDXpath))

						String deviceStateXpath = findTestObject('Object Repository/OR_AppMap/appMap_deviceState').findPropertyValue('xpath').toString()
						List<WebElement> deviceState = driver.findElements(By.xpath(deviceStateXpath))


						if(numberOfDevices != null && numberOfDevices.size() > 0){

							for (WebElement webElement : deviceModel) {
								String deviceModelListText = webElement.getText();
								MesmerLogUtils.logInfo("Device Model " + " : " + "  "  +  deviceModelListText)
							}

							for (WebElement webElement : deviceOS) {
								String deviceOSListText = webElement.getText();
								MesmerLogUtils.logInfo(" Device OS " + " : " + "  "  +  deviceOSListText)
							}

							for (WebElement webElement : deviceUDID) {
								String deviceUDIDListText = webElement.getText();
								MesmerLogUtils.logInfo(" Device UDID " + " : " + "  "  +  deviceUDIDListText)
							}

							for (WebElement webElement : deviceState) {
								String deviceStateListText = webElement.getText();
								MesmerLogUtils.logInfo("Device State " + " : " + "  "  +  deviceStateListText)
							}

						}

						else{
							MesmerLogUtils.markWarning("There is no Provision , Ready and In Use devices is available")
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
	if(WebUI.waitForElementPresent(btnClose, 10)){
		WebUI.click(btnClose)
		KeywordUtil.logInfo("Device dialogue closed")
	}else{
		KeywordUtil.logInfo("Unable to close device dialogue")
	}
}

