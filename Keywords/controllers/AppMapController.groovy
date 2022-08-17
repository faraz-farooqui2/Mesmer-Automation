package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

public class AppMapController {

	private static AppMapController mInstance = null

	private AppMapController(){
	}

	public static AppMapController getInstance(){
		if(mInstance == null){
			mInstance = new AppMapController()
		}

		return mInstance
	}
	/**
	 * Check and click the replay crawl button
	 * @return
	 */
	public boolean clickReplayCrawlButton(){
		boolean result = false
		TestObject btnReplayCrawl = findTestObject('Object Repository/OR_AppMap/btn_PLAYonTopRight')
		if(WebUI.waitForElementVisible(btnReplayCrawl, 30)){
			WebUI.click(btnReplayCrawl)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button replay crawl not found")
		}
		return result
	}

	/**
	 * Check and click the 3 dots button
	 * @return
	 */
	public boolean clickThreeDotsButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/button_3Dots')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Button 3 dots not found")
		}
		return result
	}

	/**
	 * Check and click the configure crawl button
	 * @return
	 */
	public boolean clickConfigureCrawlButton(){
		boolean result = false
		//		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_configureCrawl')
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_runCrawl')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)

			TestObject verifyConfigureCrawlDialogue = findTestObject('Object Repository/OR_AppMap/verify_configureCrawlDialogue')
			if(WebUI.verifyElementPresent(verifyConfigureCrawlDialogue, 30)){

				TestObject verifyTitle = findTestObject('Object Repository/OR_AppMap/verify_configureCrawlDialogue')
				if(WebUI.verifyElementPresent(verifyTitle, 30)){
					result = true
					WebUI.delay(2)
				}else{
					MesmerLogUtils.logInfo("Could not verify title")
				}
			}else{
				MesmerLogUtils.logInfo("Configure crawl dialogue not open")
			}
		}
		else{
			MesmerLogUtils.logInfo("Button configure crawl not found")
		}
		return result
	}

	/**
	 * Verify configure crawl dialogue
	 * @return
	 */
	public boolean verifyConfigureCrawlDialogue(){
		boolean result = false

		TestObject verifyConfigureCrawl = findTestObject('Object Repository/OR_AppMap/verify_configureCrawlDialogue')
		TestObject verifyBuildUpload = findTestObject('Object Repository/OR_AppMap/verify_buildUploadDialogue')

		if(WebUI.waitForElementPresent(verifyConfigureCrawl, 5)){
			verifyCrawlType()
			result = true

		}else if (WebUI.waitForElementPresent(verifyBuildUpload, 5)){
			verifyCrawlType()
			result = true
		}else{
			MesmerLogUtils.logInfo("Dialogue not verified")
		}

		return result
	}

	public boolean verifyCrawlType(){
		boolean result = false
		TestObject verifyTitle = findTestObject('Object Repository/OR_AppMap/verify_crawlType')
		if(WebUI.verifyElementPresent(verifyTitle, 10)){
			TestObject verifyNextBtn = findTestObject('Object Repository/OR_AppMap/btn_next')
			if(WebUI.verifyElementPresent(verifyNextBtn, 10)){

				TestObject verifyCancelBtn = findTestObject('Object Repository/OR_AppMap/btn_cancel')
				if(WebUI.verifyElementPresent(verifyCancelBtn, 10)){

					result = true

				}else{
					MesmerLogUtils.logInfo("Could not verify next button")
				}
			}else{
				MesmerLogUtils.logInfo("Could not verify next button")
			}
		}else{
			MesmerLogUtils.logInfo("Could not verify title")
		}
		return result
	}

	/**
	 * Start Crawl
	 * @return
	 */
	public boolean clickRunCrawl(String hours , String minutes , String Device , String deviceUDID ){
		//		AppMapController.getInstance().startACrawl()

		boolean result = false
		TestObject ThreeDotButton = findTestObject('Object Repository/OR_AppMap/button_3Dots')
		TestObject RunCrawlOption = findTestObject('Object Repository/OR_AppMap/optionDropdown_RunCrawl')


		if(WebUI.waitForElementVisible(ThreeDotButton,5)==true){
			WebUI.click(ThreeDotButton)

			if(WebUI.waitForElementVisible(RunCrawlOption,5)==true){
				WebUI.click(RunCrawlOption)
				WebUI.delay(1)
				if(AppMapController.getInstance().verifyConfigureCrawlDialogue()){
					if(AppMapController.getInstance().clickNextButton()){
						WebUI.delay(5)
						if(AppMapController.getInstance().verifyDeviceDialogue()){
							List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())
							if(virtualDevicesList != null && virtualDevicesList.size() >=1){
								Utility.selectDeviceAndSetDeviceParam(deviceUDID.toString())
								//if(AppMapController.getInstance().selectDevice()){
								if(AppMapController.getInstance().clickNextButton()){
									if(AppMapController.getInstance().setHrsMintsForCrawlConfig(hours , minutes)){
										if(AppMapController.getInstance().clickOkButton()){

											TestObject startCrawlbtn = findTestObject('Object Repository/OR_AppMap/btn_startCrawl')
											if(WebUI.waitForElementPresent(startCrawlbtn,10)){
												WebUI.click(startCrawlbtn)

												if(AppMapController.getInstance().checkCrawlStarted()){
													WebUI.delay(5)
													result = true
													//WebUI.refresh()
												}else{
													KeywordUtil.logInfo("Could not verify crawl started")
												}
											}
											else{
												KeywordUtil.logInfo("Yes Crawl button is not displayed")
											}
										}else{
											MesmerLogUtils.logInfo("Could not click on Ok button")
										}
									}else{
										MesmerLogUtils.logInfo("Could not set time")
									}
								}else{
									MesmerLogUtils.logInfo("Could not click on Next button")
								}
							}else{
								MesmerLogUtils.logInfo("Could not select device")
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

		return result
	}


	/**
	 * Check and click the next button
	 * @return
	 */
	public boolean clickNextButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_next')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Could not click on Next button")
		}
		return result
	}

	/**
	 * Check and click the cancel button
	 * @return
	 */
	public boolean clickCancelButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_cancel')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Could not click on Cancel button")
		}
		return result
	}

	/**
	 * Verify devices
	 * @return
	 */
	public boolean verifyDeviceDialogue(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/verify_selectDeviceDialogue')

		WebDriver driver = DriverFactory.getWebDriver()
		String devicesListXpath = findTestObject('Object Repository/OR_AppMap/list_DeviceList').findPropertyValue('xpath').toString()
		List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXpath))
		if(WebUI.waitForElementVisible(obj, 30)){

			if(devicesList != null && devicesList.size() >0){

				result = true
				WebUI.delay(2)
			}else{
				MesmerLogUtils.logInfo("No device found in a list")
			}
		}
		else{
			MesmerLogUtils.logInfo("Could not verify select device dialogue")
		}
		return result
	}


	/**
	 * Select device
	 * @return
	 */
	public boolean selectDevice(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String readDevices = findTestObject('Object Repository/OR_AppMap/list_ReadyDevices').findPropertyValue('xpath').toString()
		List<WebElement> numberOfReadyDevices = driver.findElements(By.xpath(readDevices))
		String provisionedDevices = findTestObject('Object Repository/OR_AppMap/list_provisionedDevices').findPropertyValue('xpath').toString()
		List<WebElement> numberOfProvisionedDevices = driver.findElements(By.xpath(provisionedDevices))

		String deviceAlreadySelectedXpath = findTestObject('Object Repository/OR_AppMap/list_deviceSelected').findPropertyValue('xpath').toString()
		List<WebElement> deviceAlreadySelected = driver.findElements(By.xpath(deviceAlreadySelectedXpath))
		// Check if device is already selected
		if (deviceAlreadySelected.size > 0){
			MesmerLogUtils.logInfo("Device is already selected")
			result = true

		}else{
			if (numberOfReadyDevices != null && numberOfReadyDevices.size> 0){
				numberOfReadyDevices.get(0).click()

				result = true

			}else if (numberOfProvisionedDevices != null && numberOfProvisionedDevices.size > 0){
				numberOfProvisionedDevices.get(0).click()

				result = true

			}else{
				MesmerLogUtils.logInfo("Could not click on ready or provisioned device")
			}
		}
		return result
	}

	/**
	 * Check and click the close button
	 * @return
	 */
	public boolean deviceSelected(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/list_deviceSelected')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Device unselected")
		}
		return result
	}

	/**
	 * Check and click the close button
	 * @return
	 */
	public boolean clickCloseButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_close')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Could not click on Close button")
		}
		return result
	}

	/**
	 * Check and click the ok button
	 * @return
	 */
	public boolean clickOkButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_ok')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Could not click on Ok button")
		}
		return result
	}

	/**
	 * Check and click the play crawl button
	 * @return
	 */
	public boolean clickPlayCrawlButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_PlayOnWatchVideo')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button play crawl not found")
		}
		return result
	}

	/**
	 * Check and click the pause crawl button
	 * @return
	 */
	public boolean clickPauseCrawlButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_PauseOnWatchVideo')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button pause crawl not found")
		}
		return result
	}

	/**
	 * Check and click the done crawl button
	 * @return
	 */
	public boolean clickDoneCrawlButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_DoneOnWatchVideo')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button done crawl not found")
		}
		return result
	}

	/**
	 * Check and click the minimize preview button on play video
	 * @return
	 */
	public boolean clickMinimizePreviewButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_minimizePreviewOnWatchVideo')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button minimize preview not found")
		}
		return result
	}

	/**
	 * Check and click the save crawl config button
	 * @return
	 */
	public boolean clickSaveConfigButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/button_SaveConfigCrawl')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button save config not found")
		}
		return result
	}

	/**
	 * Check and click the device preview button on play video
	 * @return
	 */
	public boolean clickDevicePreviewButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_devicePreviewOnWatchVideo')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button device preview not found")
		}
		return result
	}

	/**
	 * Check and click the object library active button
	 * @return
	 */
	public boolean clickObjLibraryActiveButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_ObjLibraryActive')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button object library active not found")
		}
		return result
	}

	/**
	 * Check if app objects text is visible
	 * @return
	 */
	public boolean checkIfAppObjectsTextVisible(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/text_AppObjects')
		if(WebUI.waitForElementVisible(obj, 30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("App objects text not visible")
		}
		return result
	}
	/**
	 * Check if items exists in the object library other than load more button
	 * @return
	 */
	public boolean checkIfItemsExistsInObjectLibrary(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject obj = findTestObject('Object Repository/OR_AppMap/list_objLibrayItems')
		if(WebUI.waitForElementVisible(obj, 30)){
			String objectLibraryItemsXPath = '//div[@class="objLibrary__list ng-star-inserted"]/div'
			List<WebElement> listOfItems = driver.findElements(By.xpath(objectLibraryItemsXPath))
			if(listOfItems != null && listOfItems.size() > 1){
				result = true
				WebUI.delay(2)
			}
		}
		else{
			MesmerLogUtils.markFailed("Object library items container not visible")
		}
		return result
	}

	/**
	 * Check if app map screen is visible
	 * @return
	 */
	public boolean checkIfAppMapScreenVisible(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/link_ScreenManager')
		if(WebUI.waitForElementVisible(obj, 30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("App map screen not visible")
		}
		return result
	}

	/**
	 * Check if disabled info icon present on play video
	 * @return
	 */
	public boolean checkIfDisabledInfoBtnPresent(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_infoInactiveOnWatchVideo')
		if(WebUI.waitForElementPresent(obj, 30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Disabled info icon not found")
		}
		return result
	}

	/**
	 * Check if disabled logs icon present on play video
	 * @return
	 */
	public boolean checkIfDisabledLogsBtnPresent(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_logsInactiveOnWatchVideo')
		if(WebUI.waitForElementPresent(obj, 30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Disabled logs icon not found")
		}
		return result
	}

	/**
	 * Check if disabled object repo icon present on play video
	 * @return
	 */
	public boolean checkIfDisabledObjRepoBtnPresent(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_objRepInactiveOnWatchVideo')
		if(WebUI.waitForElementPresent(obj, 30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Disabled object library icon not found")
		}
		return result
	}

	/**
	 * Check if disabled object repo icon present on play video
	 * @return
	 */
	public boolean checkIfDisabledHistoryBtnPresent(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_historyInactiveOnWatchVideo')
		if(WebUI.waitForElementPresent(obj, 30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Disabled history icon not found")
		}
		return result
	}
	/**
	 * Check if the disabled buttons are present on right side when crawl is replayed
	 * @return
	 */
	public boolean checkIfDisabledButtonsOnRightSidePresent(){
		boolean result = false
		if(checkIfDisabledInfoBtnPresent()){
			if(checkIfDisabledObjRepoBtnPresent()){
				if(checkIfDisabledHistoryBtnPresent()){
					if(checkIfDisabledLogsBtnPresent()){
						result = true
					}
				}
			}
		}
		return result
	}

	/**
	 * Set the hours and minutes on crawl config window
	 * @param hrs
	 * @param mints
	 * @return
	 */
	public boolean setHrsMintsForCrawlConfig(String hrs, String mints){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/verify_crawlDialogue')
		TestObject hrsObj = findTestObject('Object Repository/OR_AppMap/input_timeInHrs')
		TestObject mintsObj = findTestObject('Object Repository/OR_AppMap/input_TimeInMinutes')
		if(WebUI.waitForElementPresent(obj, 30)){
			if(WebUI.waitForElementVisible(hrsObj, 30) && WebUI.waitForElementVisible(mintsObj, 30)){
				WebUI.clearText(hrsObj)
				WebUI.clearText(mintsObj)
				WebUI.delay(1)
				WebUI.sendKeys(hrsObj, hrs)
				WebUI.delay(1)
				WebUI.sendKeys(mintsObj, mints)
				WebUI.delay(1)
				WebUI.sendKeys(mintsObj, Keys.chord(Keys.ENTER))
				result = true
				MesmerLogUtils.logInfo("Time entered in fields "+hrs + " Hours and " +mints+ " Minutes")
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Hours or minutes input field not found")
			}
		}else{
			MesmerLogUtils.logInfo("Could not verify Crawl duration dialogue")
		}
		return result
	}

	/**
	 * Check if device list container is visible
	 * @return
	 */
	public boolean checkIfDeviceListPresent(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/list_DeviceList')
		if(WebUI.waitForElementPresent(obj, 30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Device list container not visible")
		}
		return result
	}

	/**
	 * Check if save config alert appears successfully
	 * @return
	 */
	public boolean checkIfSaveConfigAlertAppears(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/notification_ConfigSavedSuccessfully')
		if(WebUI.waitForElementPresent(obj, 60)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Config saved alert does not appeared in 60 seconds")
		}
		return result
	}


	public boolean openCrawlHistory(){
		boolean result = false

		TestObject btnCrawlHistory = findTestObject('Object Repository/OR_AppMap/icon_historyIcon')
		TestObject crawlHistoryHeading = findTestObject('Object Repository/OR_AppMap/text_CrawlHistory')

		if(WebUI.verifyElementNotPresent(crawlHistoryHeading, 1)){
			MesmerLogUtils.logInfo("Crawl History is not displayed")

			if(WebUI.waitForElementVisible(btnCrawlHistory, 5)==true){
				MesmerLogUtils.logInfo("Crawl History button is dispalyed. Now clicking Crawl History button")
				WebUI.click(btnCrawlHistory)
				WebUI.delay(2)
				//				TestObject crawlHistoryHeading = findTestObject('Object Repository/OR_AppMap/text_CrawlHistory')
				if(WebUI.verifyElementPresent(crawlHistoryHeading, 2)==true){
					MesmerLogUtils.logInfo("Crawl history is displayed")
					result = true
				}
				else{
					MesmerLogUtils.logInfo("Crawl History heading is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl History button is not displayed")
			}
			result = true

		}
		else
		{
			MesmerLogUtils.logInfo("Crawl history is already displayed")

		}

		return result
	}

	public boolean checkCrawlObjects(int size){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String crawlStatus = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlStatus').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlStatus = driver.findElements(By.xpath(crawlStatus))

		//Crawl Initiated Text and Value
		String crawlInitiated = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_Initiated').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlInitiated = driver.findElements(By.xpath(crawlInitiated))
		String crawlInitiatedValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_Initiated').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlInitiatedValue = driver.findElements(By.xpath(crawlInitiatedValue))

		//Crawl Device Text and Value
		String crawlDevice = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_Device').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlDevices = driver.findElements(By.xpath(crawlDevice))
		String crawlDeviceValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_Device').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlDevicesValue = driver.findElements(By.xpath(crawlDeviceValue))


		String crawlDuration = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_CrawlDuration').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlDuration = driver.findElements(By.xpath(crawlDuration))
		String crawlDurationValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_crawlDuration').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlDurationValue = driver.findElements(By.xpath(crawlDurationValue))


		String crawlScreens = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_ScreensCrawled').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlScreens = driver.findElements(By.xpath(crawlScreens))
		String crawlScreensValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_screenCrawled').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlScreensValue = driver.findElements(By.xpath(crawlScreensValue))


		String crawlRecommendedTests = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_RecommendedTests').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlRecommendedTests = driver.findElements(By.xpath(crawlRecommendedTests))
		String crawlRecommendedTestsValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_recommendedTests').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlRecommendedTestsValue = driver.findElements(By.xpath(crawlRecommendedTestsValue))

		String crawlAppCrashes = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_appCrashes').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlAppCrashes = driver.findElements(By.xpath(crawlAppCrashes))
		String crawlAppCrashesValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_appCrashes').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlAppCrashesValue = driver.findElements(By.xpath(crawlAppCrashesValue))


		String crawlMaxHeapUser = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_MaxHeapUser').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlMaxHeapUser = driver.findElements(By.xpath(crawlMaxHeapUser))
		String crawlMaxHeapUserValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_maxHeapUse').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlMaxHeapUserValue = driver.findElements(By.xpath(crawlMaxHeapUserValue))



		for(int i=0;i<size;i++){

			//check status of crawl
			if(listOfCrawlStatus.get(i) != null){
				MesmerLogUtils.logInfo("Crawl Status is displayed")
				if(listOfCrawlStatus.get(i).getText() != null){
					MesmerLogUtils.markPassed("Crawl Status of Crawl " + (i+1) + " is -- " + listOfCrawlStatus.get(i).getText())
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Status text is not displayed")
				}
			}
			else{
				MesmerLogUtils.logInfo("Crawl Status info of Crawl#" + (i+1) + " is not displayed")
			}

			//Check Crawl Initiated
			if(listOfCrawlInitiated.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Initiated info is displayed")
				String initiatedinfo = listOfCrawlInitiatedValue.get(i).getText()
				if(initiatedinfo != null){
					MesmerLogUtils.markPassed("Crawl Initiated of Crawl " + (i+1) + " is -- " + initiatedinfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Initiated text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl Initiated info of Crawl#" + (i+1) + "is not displayed")

			}


			//Check Crawl Devices
			if(listOfCrawlDevices.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Devices info is displayed")
				String devicesInfo = listOfCrawlDevicesValue.get(i).getText()
				if(devicesInfo != null){
					MesmerLogUtils.markPassed("Crawl Devices of Crawl " + (i+1) + " is -- " + devicesInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Device text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl Devices info of Crawl#" + (i+1) + "is not displayed")

			}


			//Check Crawl Duration
			if(listOfCrawlDuration.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Duration info is displayed")
				String crawldurationInfo = listOfCrawlDurationValue.get(i).getText()
				if(crawldurationInfo != null){
					MesmerLogUtils.markPassed("Crawl Duration of Crawl " + (i+1) + " is -- " + crawldurationInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Duration text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl Duration info of Crawl#" + (i+1) + "is not displayed")

			}

			//Check Crawl Screens
			if(listOfCrawlScreens.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Screens info is displayed")
				String crawlScreenInfo = listOfCrawlScreensValue.get(i).getText()
				if(crawlScreenInfo != null){
					MesmerLogUtils.markPassed("Crawl Screen info of Crawl " + (i+1) + " is -- " + crawlScreenInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl screen info text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl screen info of Crawl#" + (i+1) + "is not displayed")

			}

			//Check Crawl Recommended Tests
			if(listOfCrawlRecommendedTests.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl Recommended Tests info is displayed")
				String crawlRecommendedTestInfo = listOfCrawlRecommendedTestsValue.get(i).getText()
				if(crawlRecommendedTestInfo != null){
					MesmerLogUtils.markPassed("Crawl Recommended Tests info of Crawl " + (i+1) + " is -- " + crawlRecommendedTestInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl Recommended Tests info text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl Recommended Tests info of Crawl#" + (i+1) + "is not displayed")

			}

			//Check Crawl App Crashes
			if(listOfCrawlAppCrashes.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl App Crashes info is displayed")
				String crawlAppCrashesInfo = listOfCrawlAppCrashesValue.get(i).getText()
				if(crawlAppCrashesInfo != null){
					MesmerLogUtils.markPassed("Crawl App Crashes info of Crawl " + (i+1) + " is -- " + crawlAppCrashesInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl App Crashes info text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl App Crashes info of Crawl#" + (i+1) + "is not displayed")

			}


			//Check Crawl UserMaxHeap Info
			if(listOfCrawlMaxHeapUser.get(i) !=null){
				MesmerLogUtils.logInfo("Crawl UserMaxHeap info is displayed")
				String crawlUserMaxHeapInfo = listOfCrawlMaxHeapUserValue.get(i).getText()
				if(crawlUserMaxHeapInfo != null){
					MesmerLogUtils.markPassed("Crawl UserMaxHeap info of Crawl " + (i+1) + " is -- " + crawlUserMaxHeapInfo)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Crawl UserMaxHeap info text is not displayed")
				}

			}
			else{
				MesmerLogUtils.logInfo("Crawl UserMaxHeap info of Crawl#" + (i+1) + "is not displayed")

			}

		}

		return result
	}

	/**
	 * Get Number of Recommended Test Cases from a specific Crawl. Crawl number 0 means recent crawl
	 **/
	public int getRecommendedTestsFromCrawl(int crawlNumber){
		WebDriver driver = DriverFactory.getWebDriver()
		String crawlRecommendedTests = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_RecommendedTests').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlRecommendedTests = driver.findElements(By.xpath(crawlRecommendedTests))
		String crawlRecommendedTestsValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_recommendedTests').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlRecommendedTestsValue = driver.findElements(By.xpath(crawlRecommendedTestsValue))

		if(listOfCrawlRecommendedTests.get(crawlNumber) !=null){
			MesmerLogUtils.logInfo("Crawl Recommended Tests info is displayed")
			String crawlRecommendedTestInfo = listOfCrawlRecommendedTestsValue.get(crawlNumber).getText()
			int numberOfRecommendedTCs = Integer.parseInt(crawlRecommendedTestInfo)

			if(numberOfRecommendedTCs != null){
				MesmerLogUtils.markPassed("Crawl Recommended Tests info of Crawl " + (crawlNumber) + " is -- " + numberOfRecommendedTCs)
				return numberOfRecommendedTCs
			}
			else{
				MesmerLogUtils.markFailed("Crawl Recommended Tests info text is not displayed")
			}

		}
		else{
			MesmerLogUtils.logInfo("Crawl Recommended Tests info of Crawl#" + (crawlNumber) + "is not displayed")

		}
	}

	/**
	 * Get Crawl Initiated Date and Time from a specific Crawl. Crawl number 0 means recent crawl
	 **/
	public String getCrawlIniatedValue(int crawlNumber, WebDriver driver){


		String crawlInitiated = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_crawlHistory_Initiated').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlInitiated = driver.findElements(By.xpath(crawlInitiated))
		String crawlInitiatedValue = findTestObject('Object Repository/OR_AppMap/CrawlHistory/value_crawlHistory_Initiated').findPropertyValue('xpath').toString()
		List<WebElement> listOfCrawlInitiatedValue = driver.findElements(By.xpath(crawlInitiatedValue))

		if(listOfCrawlInitiated.get(crawlNumber) !=null){
			MesmerLogUtils.logInfo("Crawl Initiated info is displayed")
			String initiatedinfo = listOfCrawlInitiatedValue.get(crawlNumber).getText()
			if(initiatedinfo != null){
				MesmerLogUtils.markPassed("Crawl Initiated of Crawl " + (crawlNumber) + " is -- " + initiatedinfo)
				return initiatedinfo
			}
			else{
				MesmerLogUtils.markFailed("Crawl Initiated text is not displayed")
			}

		}
		else{
			MesmerLogUtils.logInfo("Crawl Initiated info of Crawl#" + (crawlNumber) + "is not displayed")

		}

	}

	/**
	 * This method checks if user is successfully navigated to Recommended Page after clicking View Recommended Tests button
	 * from Crawl History of CrawlNumber provided in parmeter
	 * 
	 * @param CrawlNumber
	 * @return
	 */
	public boolean clickViewRecommendedButtonFromCrawlHistory(int CrawlNumber){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()

		String crawlViewRecommendedBtn = findTestObject('Object Repository/OR_AppMap/btn_viewRecommendedTCs').findPropertyValue('xpath').toString()
		List<WebElement> listOfViewRecommendedBtn = driver.findElements(By.xpath(crawlViewRecommendedBtn))

		if(listOfViewRecommendedBtn.size() >= 1 && listOfViewRecommendedBtn != null){
			MesmerLogUtils.logInfo("Recommended Test Button is displayed for Crawl number " + CrawlNumber)
			MesmerLogUtils.logInfo("Clicking Recommended TC button for Crawl# " + CrawlNumber)
			listOfViewRecommendedBtn.get(CrawlNumber).click()

			TestObject recommendedPage = findTestObject('Object Repository/OR_Recommended/page_verifyRecommendedTestCases')
			if(WebUI.waitForElementVisible(recommendedPage, 10)){
				MesmerLogUtils.logInfo("User is successfully navigated to Recommended Test Cases page")
				result = true
			}
			else{
				MesmerLogUtils.logInfo("User is not navigated to Recommended Test Cases page")
			}
		}
		else{
			MesmerLogUtils.logInfo("There is not View Recommended Test Case button available in Crawl History")
		}
		return result
	}

	public boolean checkCrawlInQueueFromCrawlHistory(){

		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		//		WebDriverWait wait = new WebDriverWait(driver, 60);
		//		String selectDeviceXPath = '//a[@class="iconMenu selectDevice CP"]'
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectDeviceXPath)));
		TestObject crawlInQueue = findTestObject('Object Repository/OR_AppMap/info_CrawlInQueue')
		TestObject btnCrawlHistory = findTestObject('Object Repository/OR_AppMap/icon_historyIcon')
		TestObject crawlHistoryHeading = findTestObject('Object Repository/OR_AppMap/text_CrawlHistory')

		if(WebUI.waitForElementVisible(crawlHistoryHeading, 2)==false){
			MesmerLogUtils.logInfo("Crawl History Heading is not displayed")

			if(WebUI.waitForElementVisible(btnCrawlHistory, 5)==true){
				MesmerLogUtils.logInfo("Crawl History button is displayed")

			}
			else{
				MesmerLogUtils.logInfo("Crawl History button is not displayed")
			}

		}

		if(WebUI.waitForElementVisible(crawlInQueue, 5)==true){
			MesmerLogUtils.logInfo("Crawl is in queue...")
			result = true
		}
		else{
			MesmerLogUtils.logInfo("Crawl is not in Queue")
		}

		return result;

	}


	public boolean checkCrawlingFromCrawlHistory(){

		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject crawlingFromHistory = findTestObject('Object Repository/OR_AppMap/text_crawlingFromCrawlHistory')
		TestObject btnCrawlHistory = findTestObject('Object Repository/OR_AppMap/icon_historyIcon')
		TestObject crawlHistoryHeading = findTestObject('Object Repository/OR_AppMap/text_CrawlHistory')

		if(WebUI.waitForElementVisible(crawlHistoryHeading, 2)==false){
			MesmerLogUtils.logInfo("Crawl History Heading is not displayed")

			if(WebUI.waitForElementVisible(btnCrawlHistory, 5)==true){
				MesmerLogUtils.logInfo("Crawl History button is displayed")

			}
			else{
				MesmerLogUtils.logInfo("Crawl History button is not displayed")
			}

		}

		if(WebUI.waitForElementVisible(crawlingFromHistory, 5)==true){
			MesmerLogUtils.logInfo("Crawling is in progress..")
			result = true
		}
		else{
			MesmerLogUtils.logInfo("Crawling in progress")
		}

		return result;

	}


	public boolean checkGoToConfigCrawl(){

		boolean configureCrawlResult = false

		TestObject ThreeDotButton = findTestObject('Object Repository/OR_AppMap/button_3Dots')
		TestObject btnConfigureCrawl = findTestObject('Object Repository/OR_AppMap/btn_configureCrawl')
		TestObject InputTimeInMinutes = findTestObject('Object Repository/OR_AppMap/input_TimeInMinutes')
		TestObject InputTimeInHours = findTestObject('Object Repository/OR_AppMap/input_timeInHrs')
		TestObject SaveConfigButton =  findTestObject('Object Repository/OR_AppMap/button_SaveConfigCrawl')
		TestObject ConfigSavedNotification =findTestObject('Object Repository/OR_AppMap/notification_ConfigSavedSuccessfully')
		TestObject WindowConfigureCrawl = findTestObject('Object Repository/OR_AppMap/window_ConfigureCrawlHeading')


		if(WebUI.waitForElementPresent(ThreeDotButton, 20)==true){
			WebUI.click(ThreeDotButton)
			if(WebUI.waitForElementPresent(btnConfigureCrawl, 20)==true){
				WebUI.click(btnConfigureCrawl)

				if(WebUI.waitForElementVisible(WindowConfigureCrawl, 5)==true){
					MesmerLogUtils.markPassed("Configure Crawl windows is displayed correctly")
					WebUI.delay(2)
					configureCrawlResult = true
				}
				else{
					MesmerLogUtils.markFailed("Configure Crawl window is not displayed")
				}


			}else{
				KeywordUtil.markFailed("PASSED: Unable to click on Configure Crawl Button")
			}
		}else{
			KeywordUtil.markFailed("PASSED: Unable to click on 3 dots button")
		}

		return configureCrawlResult;
	}


	public boolean checkCrawlStarted(){
		boolean result = false

		//TestObject text_CrawlInProgress = findTestObject('Object Repository/OR_AppMap/text_CrawlingInProgress')

		TestObject preparingDevice = findTestObject('Object Repository/OR_AppMap/text_preparingDevice')
		if(WebUI.waitForElementPresent(preparingDevice, 120)){

			TestObject deviceRetrieved = findTestObject('Object Repository/OR_AppMap/text_DeviceRetrieved')
			if(WebUI.waitForElementPresent(deviceRetrieved, 120)){

				TestObject connectToDevice = findTestObject('Object Repository/OR_AppMap/text_DeviceConnected')
				if(WebUI.waitForElementPresent(connectToDevice, 120)){

					TestObject deviceConfigured = findTestObject('Object Repository/OR_AppMap/text_Configured')
					if(WebUI.waitForElementPresent(deviceConfigured, 120)){

						TestObject buildDownload = findTestObject('Object Repository/OR_AppMap/text_BuildDownloaded')
						if(WebUI.waitForElementPresent(buildDownload, 120)){

							TestObject buildInstalled = findTestObject('Object Repository/OR_AppMap/text_buildInstalled')
							if(WebUI.waitForElementPresent(buildInstalled, 120)){

								TestObject startingCrawl = findTestObject('Object Repository/OR_AppMap/text_startingCrawl')
								if(WebUI.waitForElementPresent(startingCrawl, 120)){
									MesmerLogUtils.logInfo("Starting crawl")

									TestObject crawling = findTestObject('Object Repository/OR_AppMap/text_Crawling')
									if(WebUI.waitForElementPresent(crawling, 120)){
										MesmerLogUtils.logInfo("Crawl started")
										WebUI.delay(5)
										result = true

									}else{
										MesmerLogUtils.markFailed("Unable to start crawl on device")
									}

								}else{
									MesmerLogUtils.markFailed("Unable to start crawl on device")
								}
							}else{
								MesmerLogUtils.markFailed("Unable to install build on device")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to download build on device")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to configure device")
					}

				}else{
					MesmerLogUtils.markFailed("Unable to connect to device")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to retrieve device")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to prepare device")
		}
		return result
	}


	public boolean startACrawl(){

		boolean crawlResult = false
		TestObject ThreeDotButton = findTestObject('Object Repository/OR_AppMap/button_3Dots')
		TestObject CrawlStartedNotification = findTestObject('Object Repository/OR_AppMap/notification_CrawlStarted')
		TestObject StillCrawlingText = findTestObject('Object Repository/OR_AppMap/text_StillCrawling')
		TestObject CrawlText = findTestObject('Object Repository/OR_AppMap/text_Crawling-SP124')
		TestObject CanvasImageAfterStartingCrawl = findTestObject('Object Repository/OR_AppMap/image_canvasAfterStartingCrawl')

		if(WebUI.waitForElementVisible(ThreeDotButton,30)){
			WebUI.click(ThreeDotButton)
			TestObject RunCrawlOption = findTestObject('Object Repository/OR_AppMap/optionDropdown_RunCrawl')
			if(WebUI.waitForElementVisible(RunCrawlOption,30)){
				WebUI.click(RunCrawlOption)
				WebUI.delay(1)
				TestObject YesReCrawlButton = findTestObject('Object Repository/OR_AppMap/button_YesOnRe-Crawl')
				if(WebUI.verifyElementVisible(YesReCrawlButton)){
					WebUI.click(YesReCrawlButton)
					WebUI.delay(2)
					//WebUI.refresh()
					if(AppMapController.getInstance().checkCrawlStarted())
					{
						crawlResult = true
						MesmerLogUtils.logInfo("Crawl is started successfully")
					}
					else{
						MesmerLogUtils.logInfo("Crawl is not started")
					}
					WebUI.delay(2)
					WebUI.refresh()
					//checking Crawl Image
					//					if(WebUI.waitForElementVisible(CanvasImageAfterStartingCrawl, 30, FailureHandling.CONTINUE_ON_FAILURE)==true || WebUI.waitForElementVisible(CrawlText, 30)==true ){
					//						MesmerLogUtils.markPassed("PASSED: Crawl started successfully")
					//						crawlResult = true
					//					}
					//					else{
					//						MesmerLogUtils.markFailed("FAILED: Canvas image is not displayed and Crawl is not started")
					//					}

				}
				else{
					MesmerLogUtils.logInfo("Yes Crawl button is not displayed")
				}
			}
			else{
				MesmerLogUtils.logInfo("Run Crawl button is not displayed")
			}
		}
		else{
			MesmerLogUtils.logInfo("Run Crawl option is not displayed")
		}

		return crawlResult;

	}

	public boolean stopACrawl(){
		boolean stopCrawlResult = false
		TestObject stopCrawlButton = findTestObject('Object Repository/OR_AppMap/button_StopCrawl')

		if(WebUI.waitForElementVisible(stopCrawlButton, 30)){
			WebUI.click(stopCrawlButton)
			TestObject confirmationWindow_StopCrawl = findTestObject('Object Repository/OR_AppMap/window_stopCrawlConfirmationWindow')
			if(WebUI.waitForElementVisible(confirmationWindow_StopCrawl, 30)){
				TestObject yesButton_StopCrawl = findTestObject('Object Repository/OR_AppMap/button_YesOnRe-Crawl')
				if(WebUI.waitForElementVisible(yesButton_StopCrawl, 30)){
					WebUI.click(yesButton_StopCrawl)

					TestObject verifyStoppingCrawl = findTestObject('Object Repository/OR_AppMap/text_stoppingCrawl')
					if(WebUI.waitForElementPresent(verifyStoppingCrawl, 20)){
						MesmerLogUtils.logInfo("Stopping Crawl...")

						//WebUI.refresh()

						TestObject textCrawlCompleted = findTestObject('Object Repository/OR_AppMap/txt_CrawlComplete')
						if(WebUI.waitForElementVisible(textCrawlCompleted, 120)){
							MesmerLogUtils.logInfo("Crawl stopped successfully")
							stopCrawlResult = true

						}else{
							MesmerLogUtils.logInfo("Crawl Complete not appears")
						}

					}else{
						MesmerLogUtils.logInfo("Unable to stop Crawl")
					}
				}
				else{
					MesmerLogUtils.markFailed("Unable to click on Yes button on Stop Crawl window")
				}

			}
			else{
				MesmerLogUtils.markFailed("Confirmation window of Stop Crawl is not displayed")
			}
		}
		else{
			MesmerLogUtils.markWarning("Stop Crawl button is not displayed")
		}
		return stopCrawlResult;


	}

	public boolean clickDeviceLogButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_AppMap/btn_deviceLogs')
		if(WebUI.waitForElementVisible(obj, 60)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button device logs not found")
		}
		return result
	}

	public List<WebElement> getDeviceLogsList(){
		List<WebElement> result = null
		WebDriver driver = DriverFactory.getWebDriver()
		String deviceLogsXPath = '//div[@class="deviceLog hScroll ng-star-inserted"]/div'
		List<WebElement> deviceLogsList = driver.findElements(By.xpath(deviceLogsXPath))
		if(deviceLogsList != null && deviceLogsList.size() > 0){
			MesmerLogUtils.logInfo("Device logs list is populating")
			String screenMarkerXPath = '//div[@class="deviceLog hScroll ng-star-inserted"]/div/div[@class="logTitle ng-star-inserted"]'
			List<WebElement> logsMarkerList = driver.findElements(By.xpath(screenMarkerXPath))
			if(logsMarkerList != null && logsMarkerList.size() > 0){
				result = logsMarkerList
				MesmerLogUtils.logInfo("Device markers list is populating")
			}
		}
		return result
	}

	public boolean downloadDeviceLogs(){
		boolean result = false
		TestObject btnDeviceLogs = findTestObject('Object Repository/OR_AppMap/btn_deviceLogs')

		if (WebUI.waitForElementPresent(btnDeviceLogs, 20)){
			WebUI.click(btnDeviceLogs)
			WebUI.refresh()
			WebUI.delay(3)
			WebUI.click(btnDeviceLogs)

			TestObject btnDownloadDeviceLogs = findTestObject('Object Repository/OR_AppMap/btn_downloadFullLog')
			if (WebUI.waitForElementPresent(btnDownloadDeviceLogs, 20)){
				WebUI.click(btnDownloadDeviceLogs)
				result = true
			}else{
				MesmerLogUtils.logInfo("Could not click on Download Device Logs")
			}
		}else{
			MesmerLogUtils.logInfo("Could not click on device log button")
		}
		return result
	}

	public boolean deviceLogs(String action){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		TestObject btnDeviceLogs = findTestObject('Object Repository/OR_AppMap/btn_deviceLogs')

		if(WebUI.waitForElementVisible(btnDeviceLogs, 60)){
			WebUI.click(btnDeviceLogs)
			TestObject multipleLinesOfLogs = findTestObject('Object Repository/OR_AppMap/logs_multipleLinesOfLogs')
			if(WebUI.waitForElementPresent(multipleLinesOfLogs, 120)){
				TestObject optionLogsSettings = findTestObject('Object Repository/OR_AppMap/option_logsSettings')
				if(WebUI.waitForElementPresent(optionLogsSettings, 20)){
					WebUI.click(optionLogsSettings)

					if(action == "Wrap Text"){
						TestObject wrapTextOption = findTestObject('Object Repository/OR_AppMap/option_WrapText')
						if(WebUI.waitForElementVisible(wrapTextOption, 20)){
							WebUI.click(wrapTextOption)
							if(WebUI.waitForElementPresent(optionLogsSettings, 20)){
								WebUI.click(optionLogsSettings)
								TestObject unwrapTextOption = findTestObject('Object Repository/OR_AppMap/option_UnwrapText')
								if(WebUI.waitForElementVisible(unwrapTextOption, 20)){
									WebUI.click(unwrapTextOption)
									result = true
								}
								else{
									MesmerLogUtils.markFailed("UnWrap option is not displayed correctly")
								}
							}else{
								MesmerLogUtils.markFailed("Unable to click on setting options")
							}
						}else{
							MesmerLogUtils.markFailed("Wrap option is not displayed correctly")
						}

					}else if (action == "Clear Log"){

						TestObject clearLogs = findTestObject('Object Repository/OR_AppMap/option_ClearLog')
						if(WebUI.waitForElementVisible(clearLogs, 20)){
							WebUI.click(clearLogs)

							String logsRow = findTestObject('Object Repository/OR_AppMap/xpath_logsRow').findPropertyValue('xpath').toString()

							if(WebUI.waitForElementVisible(clearLogs, 20)){
								WebUI.click(clearLogs)

								//					String logsRow = findTestObject('Object Repository/OR_AppMap/xpath_logsRow').findPropertyValue('xpath').toString()


								List<WebElement> logRowsinSearch = driver.findElements(By.xpath(logsRow))
								int countLogs = logRowsinSearch.size()
								if(countLogs==0){
									result = true
									MesmerLogUtils.markPassed("Logs are cleared successfully")
								}
								else{
									MesmerLogUtils.markFailed("Logs are not cleared")
								}

							}else{
								MesmerLogUtils.markFailed("Unable to click om Clear Logs button")
							}
						}
					}
					else if (action == "Download Log"){

						TestObject logRowsinSearchObj = findTestObject('Object Repository/OR_AppMap/xpath_logsRow')
						String logsRow = findTestObject('Object Repository/OR_AppMap/xpath_logsRow').findPropertyValue('xpath').toString()
						List<WebElement> logRowsinSearch = driver.findElements(By.xpath(logsRow))
						if(WebUI.waitForElementVisible(logRowsinSearchObj, 120)){
							if(logRowsinSearch.size() > 1){

								TestObject downloadLogs = findTestObject('Object Repository/OR_AppMap/option_downloadLogs')
								if(WebUI.waitForElementVisible(downloadLogs, 20)){
									WebUI.click(downloadLogs)
									MesmerLogUtils.markPassed("Logs are downloaded successfully")
									result = true
								}
								else{
									MesmerLogUtils.markFailed("Download Logs option is not displayed")
								}
							}
							else{
								MesmerLogUtils.markFailed("Logs are not appearing")
							}
						}
						else{
							MesmerLogUtils.markFailed("Logs are not appearing in 2 minutes")
						}

					}
					else if (action == "Pause"){
						TestObject btnPause = findTestObject('Object Repository/OR_AppMap/btn_Pause')
						if(WebUI.waitForElementVisible(btnPause, 20)){
							WebUI.click(btnPause)
							TestObject verifyLogPaused = findTestObject('Object Repository/OR_AppMap/text_verifyLogPaused')
							if(WebUI.verifyElementPresent(verifyLogPaused, 20)){
								result = true
								MesmerLogUtils.markPassed("Logs Paused successfully")
							}else{
								MesmerLogUtils.markFailed("Log Paused msg not appears")
							}
						}
						else{
							MesmerLogUtils.markFailed("Unable to click on pause button")
						}

					}else if (action == "Resume"){
						TestObject btnPause = findTestObject('Object Repository/OR_AppMap/btn_Pause')
						if(WebUI.waitForElementVisible(btnPause, 20)){
							WebUI.click(btnPause)
							TestObject verifyLogPaused = findTestObject('Object Repository/OR_AppMap/text_verifyLogPaused')
							if(WebUI.verifyElementPresent(verifyLogPaused, 20)){
								result = true
								MesmerLogUtils.markPassed("Logs Paused successfully")
							}else{
								MesmerLogUtils.markFailed("Log Paused msg not appears")
							}
						}
						else{
							MesmerLogUtils.markFailed("Unable to click on pause button")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to resume logs")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to click on option settings")
				}

			}else{
				MesmerLogUtils.markFailed("Device logs are not appeared")
			}
		}
		else{
			MesmerLogUtils.markFailed("Unable to click on device log button")
		}
		return result
	}

}