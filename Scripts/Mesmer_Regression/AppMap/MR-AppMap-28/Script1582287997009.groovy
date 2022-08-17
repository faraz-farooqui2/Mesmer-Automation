import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.sun.media.ui.CacheControlComponent.CancelButton
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.mesmer.MesmerLogUtils
import controllers.AppMapController

// AppMap-28 | Verify the list of devices should show updated status

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

//Calling go to App Map page method
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

						// 3. User scrolls through the devices list
						String devicesList = findTestObject('Object Repository/OR_AppMap/list_DeviceList').findPropertyValue('xpath').toString()
						List<WebElement> numberOfDevices = driver.findElements(By.xpath(devicesList))
						String readDevices = findTestObject('Object Repository/OR_AppMap/list_ReadyDevices').findPropertyValue('xpath').toString()
						List<WebElement> numberOfReadyDevices = driver.findElements(By.xpath(readDevices))
						String provisionedDevices = findTestObject('Object Repository/OR_AppMap/list_provisionedDevices').findPropertyValue('xpath').toString()
						List<WebElement> numberOfProvisionedDevices = driver.findElements(By.xpath(provisionedDevices))
						String InUseDevices = findTestObject('Object Repository/OR_AppMap/list_InUseDevices').findPropertyValue('xpath').toString()
						List<WebElement> numberOfInUseDevices = driver.findElements(By.xpath(InUseDevices))

						String unavailableDevices = findTestObject('Object Repository/OR_AppMap/list_UnavailableDevices').findPropertyValue('xpath').toString()
						List<WebElement> numberOfUnavailableDevices = driver.findElements(By.xpath(unavailableDevices))
						if(devicesList != null && devicesList.size() >0){

							MesmerLogUtils.logInfo("Provisioned Devices" + " : " + numberOfProvisionedDevices.size())

							MesmerLogUtils.logInfo("Ready Devices" + " : " + numberOfReadyDevices.size())

							MesmerLogUtils.logInfo("In Use Devices" + " : " + numberOfInUseDevices.size())

							MesmerLogUtils.logInfo("Unavailable Devices" + " : " + numberOfUnavailableDevices.size())

						}
						else{
							KeywordUtil.markWarning("There is no Provision , Ready and In Use and Unavailable devices in a list")
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
			KeywordUtil.logInfo("[MESMER]: Run Crawl button is not displayed")
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

