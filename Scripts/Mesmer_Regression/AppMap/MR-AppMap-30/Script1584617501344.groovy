
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

// AppMap-30 | Verify user can select virtual (simluator) or physical device for crawl from devices list

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


						String devicesList = findTestObject('Object Repository/OR_AppMap/list_DeviceList').findPropertyValue('xpath').toString()
						List<WebElement> numberOfDevices = driver.findElements(By.xpath(devicesList))


						String virtualDevices = findTestObject('Object Repository/OR_AppMap/list_VirtualDevice').findPropertyValue('xpath').toString()
						List<WebElement> numberOfVirtualDevices = driver.findElements(By.xpath(virtualDevices))
						String physicalDevices = findTestObject('Object Repository/OR_AppMap/list_PhysicalDevices').findPropertyValue('xpath').toString()
						List<WebElement> numberOfPhysicalDevices = driver.findElements(By.xpath(physicalDevices))

						if(devicesList != null && devicesList.size() > 0){
							if(numberOfVirtualDevices.size() > 0){

								MesmerLogUtils.logInfo("Virtual Devices" + " : " + numberOfVirtualDevices.size())

								numberOfVirtualDevices.get(0).click()
								if (AppMapController.getInstance().deviceSelected()){

								}else{
									MesmerLogUtils.logInfo("Virtual Device selected")
								}

							}else if (numberOfPhysicalDevices.size() > 0){

								MesmerLogUtils.logInfo("Physical Devices" + " : " + numberOfPhysicalDevices.size())

								numberOfPhysicalDevices.get(0).click()

								if (AppMapController.getInstance().deviceSelected()){

								}else{
									MesmerLogUtils.logInfo("Physical Device selected")
								}

							}else{
								MesmerLogUtils.logInfo("Virtual Devices" + " : " + numberOfVirtualDevices.size())

								MesmerLogUtils.logInfo("Physical Devices" + " : " + numberOfPhysicalDevices.size())
							}
						}
						else{
							KeywordUtil.markWarning("There is no Virtual , Physical devices in a list")
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


