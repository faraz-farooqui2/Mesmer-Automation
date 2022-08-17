import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.sun.media.ui.CacheControlComponent.CancelButton
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

//AppMap-40 | Behavior when user changes the device when the crawl is already in progress

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()

try{
	//1. Click on 3 dot menu button the top right corner of the AppMap screen.
	if(WebUI.waitForElementVisible(ThreeDotButton,10)==true){
		WebUI.click(ThreeDotButton)
		WebUI.delay(1)
		if(WebUI.waitForElementVisible(ConfigureCrawlOption,5)==true){
			//2. Click on 'Configure Crawl' option
			WebUI.click(ConfigureCrawlOption)
			WebUI.delay(1)

			//3. Select the available device from the list for crawl.
			//			String devicesList = findTestObject('Object Repository/OR_AppMap/list_DeviceList').findPropertyValue('xpath').toString()
			//			List<WebElement> numberOfDevices = driver.findElements(By.xpath(devicesList))

			String selectedReady = findTestObject('Object Repository/OR_AppMap/device_SelectedReady').findPropertyValue('xpath').toString()
			List<WebElement> selectedReadyDevice = driver.findElements(By.xpath(selectedReady))

			String deviceReady = findTestObject('Object Repository/OR_AppMap/list_ReadyDevices').findPropertyValue('xpath').toString()
			List<WebElement> readyDeviceList = driver.findElements(By.xpath(deviceReady))

			if(WebUI.waitForElementPresent(deviceList, 10)){

				readyDeviceList.get(0).click()

				if(WebUI.waitForElementPresent(SaveConfigButton,10)==true){
					WebUI.click(SaveConfigButton)


					if(WebUI.waitForElementVisible(ThreeDotButton,10)==true){
						WebUI.click(ThreeDotButton)
						WebUI.delay(1)
						if(WebUI.waitForElementVisible(runCrawl,10)==true){
							WebUI.click(runCrawl)

							if(WebUI.waitForElementVisible(btnYes,10)==true){
								WebUI.click(btnYes)

								if(WebUI.waitForElementVisible(preparingDevice,120)==true){
									if(WebUI.waitForElementVisible(crawling,120)==true){

//										if(WebUI.waitForElementVisible(crawlComplete,1200)==true){

											if(WebUI.waitForElementVisible(ThreeDotButton,10)==true){
												WebUI.click(ThreeDotButton)
												WebUI.delay(1)
												if(WebUI.waitForElementVisible(ConfigureCrawlOption,5)==true){
													//2. Click on 'Configure Crawl' option
													WebUI.click(ConfigureCrawlOption)
													WebUI.delay(1)

													if(WebUI.waitForElementVisible(deviceList, 10)==true){
														readyDeviceList.get(1).click()
														//5. Check Save button and Click on it
														if(WebUI.waitForElementPresent(SaveConfigButton, 2)==true){
															WebUI.click(SaveConfigButton)
															WebUI.delay(1)
															if(WebUI.waitForElementVisible(ConfigSavedNotification,15)==true){
																KeywordUtil.markPassed("PASSED: Configuaration is saved successfully")
															}else{
																KeywordUtil.markFailed("FAILED: Unable to save configuration")
															}
														}	else{
															KeywordUtil.markFailed("FAILED: Unable to click on save button")
														}
													} else{
														KeywordUtil.markFailed("FAILED: Unable to Select device")
													}
												}else{
													KeywordUtil.markFailed("FAILED: Unable to click on configure crawl option")
												}
											}else{
												KeywordUtil.markFailed("FAILED: Unable to click on three dot menu")
											}

//										}else{
//											KeywordUtil.markFailed("FAILED: Still Crawling")
//										}
									}else{
										KeywordUtil.markFailed("FAILED: Unable to find text crawling")
									}
								}else{
									KeywordUtil.markFailed("FAILED: Unable to find preparing device")
								}
							}else{
								KeywordUtil.markFailed("FAILED: Unable to click on Yes button")
							}
						}else{
							KeywordUtil.markFailed("FAILED: Unable to click on run crawl")
						}
					}else{
						KeywordUtil.markFailed("FAILED: Unable to click on 3 dot menu")
					}
				}else{
					KeywordUtil.markFailed("FAILED: Unable to save config")
				}
			}
			else{
				KeywordUtil.markWarning("No devices available")
			}
		}
		else{
			KeywordUtil.markFailed("FAILED: CONFIG CRAWL Option is not found")
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

