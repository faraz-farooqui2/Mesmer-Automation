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

//AppMap-29 | Verify user can select only provisioned or ready status devices from the list

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

						//3. Select the available device from the list for crawl.
						String devicesList = findTestObject('Object Repository/OR_AppMap/list_DeviceList').findPropertyValue('xpath').toString()
						List<WebElement> numberOfDevices = driver.findElements(By.xpath(devicesList))
						String readDevices = findTestObject('Object Repository/OR_AppMap/list_ReadyDevices').findPropertyValue('xpath').toString()
						List<WebElement> numberOfReadyDevices = driver.findElements(By.xpath(readDevices))
						String provisionedDevices = findTestObject('Object Repository/OR_AppMap/list_provisionedDevices').findPropertyValue('xpath').toString()
						List<WebElement> numberOfProvisionedDevices = driver.findElements(By.xpath(provisionedDevices))

						if(devicesList != null && devicesList.size() > 0){



							if(numberOfProvisionedDevices.size() > 0){
								MesmerLogUtils.logInfo("Provisioned Devices" + " : " + numberOfProvisionedDevices.size())
								numberOfProvisionedDevices.get(0).click()

							}else if (numberOfReadyDevices.size() > 0){
								MesmerLogUtils.logInfo("Ready Devices" + " : " + numberOfReadyDevices.size())
								numberOfReadyDevices.get(0).click()

							}
							else{
								MesmerLogUtils.logInfo("Provisioned Devices" + " : " + numberOfProvisionedDevices.size())

								MesmerLogUtils.logInfo("Ready Devices" + " : " + numberOfReadyDevices.size())
							}
							//
							//							if(WebUI.waitForElementPresent(btnCancelConfigCrawl,10)==true){
							//								WebUI.click(btnCancelConfigCrawl)
							//							}else{
							//								KeywordUtil.markFailed("FAILED: Unable to click on cancel button")
							//							}
						}
						else{
							KeywordUtil.markWarning("There is no Provision , Ready devices in a list")
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

