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

//AppMap-31 | Verify user cannot select broken, incomaptible or in use devices from the list

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
			String devicesList = findTestObject('Object Repository/OR_AppMap/list_DeviceList').findPropertyValue('xpath').toString()
			List<WebElement> numberOfDevices = driver.findElements(By.xpath(devicesList))

			String brokenDevices = findTestObject('Object Repository/OR_AppMap/list_BrokenDevice').findPropertyValue('xpath').toString()
			List<WebElement> numberOfBrokenDevices = driver.findElements(By.xpath(brokenDevices))

			String inUseDevices = findTestObject('Object Repository/OR_AppMap/list_inUse').findPropertyValue('xpath').toString()
			List<WebElement> numberOfInUseDevices = driver.findElements(By.xpath(inUseDevices))

			String incompatibleDevices = findTestObject('Object Repository/OR_AppMap/list_incompatible').findPropertyValue('xpath').toString()
			List<WebElement> numberOfIncompatibleDevices = driver.findElements(By.xpath(incompatibleDevices))

			System.err.println(numberOfBrokenDevices.size())
			System.err.println(numberOfInUseDevices.size())
			System.err.println (numberOfIncompatibleDevices)

			if(WebUI.waitForElementPresent(numberOfDevices, 10)){

				if(numberOfBrokenDevices.size() > 0){

					if(WebUI.verifyElementPresent(numberOfBrokenDevices,10)){

						WebUI.verifyElementClickable(numberOfBrokenDevices.get(0))

					}else{
						KeywordUtil.markFailed("FAILED: Broken device is clickable")
					}

				}else{
					KeywordUtil.markWarning("No Broken device")
				}

				if (numberOfInUseDevices.size() > 0){
					if(WebUI.verifyElementPresent(numberOfInUseDevices , 10)){
						WebUI.verifyElementClickable(numberOfInUseDevices.get(0))
					}else{
						KeywordUtil.markFailed("FAILED: InUse device is clickable")
					}
				}else{
					KeywordUtil.markWarning("No InUse device")
				}


				if (numberOfIncompatibleDevices.size() > 0){
					if(WebUI.verifyElementPresent(numberOfIncompatibleDevices , 10)){
						WebUI.verifyElementClickable(numberOfIncompatibleDevices.get(0))
					}else{
						KeywordUtil.markFailed("FAILED: In-compatible device is clickable")
					}

				}else{
					KeywordUtil.markWarning("No In-compatible device")
				}


				if(WebUI.waitForElementPresent(btnCancelConfigCrawl,10)==true){
					WebUI.click(btnCancelConfigCrawl)
				}else{
					KeywordUtil.markFailed("FAILED: Unable to click on cancel button")
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
	WebUI.delay(20)
	WebUI.refresh()
}

