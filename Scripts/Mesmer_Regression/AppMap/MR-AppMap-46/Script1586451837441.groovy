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

// AppMap-46 | Verify physical devices for crawl [Android]

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

						String deviceList = findTestObject('Object Repository/OR_AppMap/list_DeviceList').findPropertyValue('xpath').toString()
						List<WebElement> listOfDevices = driver.findElements(By.xpath(deviceList))

						String physicalDevice = findTestObject('Object Repository/OR_AppMap/list_devicePhysicalText').findPropertyValue('xpath').toString()
						List<WebElement> physicalDeviceList = driver.findElements(By.xpath(physicalDevice))


						String virtualDevice = findTestObject('Object Repository/OR_AppMap/list_deviceVirtualText').findPropertyValue('xpath').toString()
						List<WebElement> virtualDeviceList = driver.findElements(By.xpath(virtualDevice))

						if(physicalDeviceList.size() > 0 ){

							KeywordUtil.logInfo("Physical Devices available :" + " " +	physicalDeviceList.size())

						}
						else{
							KeywordUtil.markWarning("No Physical devices found")
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

