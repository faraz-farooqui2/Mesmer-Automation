package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

public class CreateTestController {

	private static CreateTestController mInstance = null

	private CreateTestController(){
	}

	public static CreateTestController getInstance(){
		if(mInstance == null){
			mInstance = new CreateTestController()
		}

		return mInstance
	}

	public void clickDevicesIcon(){
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		String selectDeviceXPath = '//a[@class="iconMenu selectDevice CP"]'
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectDeviceXPath)));
		WebElement selectDeviceTitle = driver.findElement(By.xpath(selectDeviceXPath))
		if(selectDeviceTitle != null){
			selectDeviceTitle.click()
			WebUI.delay(3)
		}
		else{
			MesmerLogUtils.markError("Device selection failed")
		}
	}

	public boolean clickDeviceIcon(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		String selectDeviceXPath = '//a[@class="iconMenu selectDevice CP"]'
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectDeviceXPath)));
		WebElement selectDeviceTitle = driver.findElement(By.xpath(selectDeviceXPath))
		if(selectDeviceTitle != null){
			selectDeviceTitle.click()
			result = true
			WebUI.delay(3)
		}
		else{
			MesmerLogUtils.markError("Device selection failed")
		}

		return result
	}

	public void toggleDeviceIcon(){
		WebDriver driver = DriverFactory.getWebDriver()
		String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
		List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
		WebUI.delay(2)
		if(devicesList != null && devicesList.size() > 0){
			clickDevicesIcon()
		}
	}

	public void checkIfDiscardAlertAppeared(){
		TestObject discardTest = findTestObject("Object Repository/OR_CreateNewTestCase/alert_discardRecodingTestCase")
		if(WebUI.waitForElementVisible(discardTest,10)){
			TestObject buttonDiscard = findTestObject("Object Repository/OR_CreateNewTestCase/option_Discard")
			if(WebUI.waitForElementPresent(buttonDiscard, 10)){
				WebUI.click(buttonDiscard)
				if(WebUI.waitForElementNotPresent(discardTest, 60)){
				}
			}
		}
	}

	public boolean checkIfNewDiscardAlertAppeared(){
		boolean result = false
		TestObject alertDiscardTest = findTestObject("Object Repository/OR_CreateNewTestCase/alert_DiscardTest")
		if(WebUI.waitForElementVisible(alertDiscardTest,10)){
			TestObject optionDiscard = findTestObject("Object Repository/OR_CreateNewTestCase/btn_YesDiscard")
			if(WebUI.waitForElementPresent(optionDiscard, 10)){
				WebUI.click(optionDiscard)
				if(WebUI.waitForElementNotPresent(alertDiscardTest, 120)){
					result = true
					MesmerLogUtils.logInfo("Test case discarded successfully")
				}
			}
		}
		return result
	}

	public boolean checkIfNoDeviceAvailable(){
		boolean result = false;
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/OR_CreateNewTestCase/div_NoDeviceAvailable"), 10)){
			result = true
		}
		return result
	}

	public boolean checkIfDevicesListExists(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);

		// Get devices list
		TestObject devicesListObject = findTestObject('Object Repository/OR_Common/xpath_DevicesList')
		String devicesListXPath = devicesListObject.findPropertyValue("xpath").toString()
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
		List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))

		if(devicesList != null && devicesList.size() > 0){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Devices list not exists or there is no device in the list")
		}

		return result
	}

	public boolean checkIfDeviceWithStatusAvailable(String deviceStatus){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		// Get devices list
		TestObject devicesListObject = findTestObject('Object Repository/OR_Common/xpath_DevicesList')
		String devicesListXPath = devicesListObject.findPropertyValue("xpath").toString()
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
		List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
		if(devicesList != null && devicesList.size() > 0){
			// Check if there is any device available with provided status
			if(deviceStatus.equalsIgnoreCase("inUse")){
				deviceStatus = "inUse disabled"
			}
			String devicesXPath = '//div[@class="deviceList ng-star-inserted"]/div[@class="device '+deviceStatus+' ng-star-inserted"]'
			List<WebElement> searchedDevicesList = driver.findElements(By.xpath(devicesXPath))
			if(searchedDevicesList != null && searchedDevicesList.size() > 0){
				result = true
			}
			else{
				//Check if there is any selected device available with provided status
				String selectedDevicesXPath = '//div[@class="deviceList ng-star-inserted"]/div[@class="device '+deviceStatus+' selected ng-star-inserted"]'
				List<WebElement> selectedDevicesList = driver.findElements(By.xpath(selectedDevicesXPath))
				if(selectedDevicesList != null && selectedDevicesList.size() > 0){
					result = true
				}
			}
		}
		else{
			MesmerLogUtils.markWarning("There is no device in the list")
		}

		return result
	}

	// Check the provided device type and select it
	public WebElement selectDeviceWithGivenStatus(String deviceStatus){
		WebElement searchedDevice = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		// Get devices list
		TestObject devicesListObject = findTestObject('Object Repository/OR_Common/xpath_DevicesList')
		String devicesListXPath = devicesListObject.findPropertyValue("xpath").toString()
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
		//div[@class="deviceList ng-star-inserted"]/div[1]/div[@class="deviceDetails"]/div[@class="deviceState"]/div/div
		List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
		WebUI.delay(2)
		if(devicesList != null && devicesList.size() > 0){
			for(int i=0; i < devicesList.size(); i++){
				String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div'
				WebElement deviceStatusStr = driver.findElement(By.xpath(devicesStatusXPath))
				if(deviceStatusStr != null && deviceStatusStr.getText().contains(deviceStatus)){
					searchedDevice = devicesList.get(i)
					break
				}
			}
		}
		else{
			MesmerLogUtils.markWarning("There is no device in the list")
		}

		return searchedDevice
	}

	public WebElement checkIfPhysicalDeviceExists(String virtualOrPhysical){
		WebElement searchedDevice = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(1)
		String selectDeviceXPath = '//a[@class="iconMenu selectDevice CP"]'
		WebElement selectDeviceTitle = driver.findElement(By.xpath(selectDeviceXPath))
		if(selectDeviceTitle != null){
			selectDeviceTitle.click()
			WebUI.delay(2)
			// Get devices list
			String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
			//div[@class="deviceList ng-star-inserted"]/div[1]/div[@class="deviceDetails"]/div[@class="deviceState"]/div/div
			List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
			if(devicesList != null && devicesList.size() > 0){
				for(int i=0; i < devicesList.size(); i++){
					String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="infoText"][2]'
					WebElement deviceStatusStr = driver.findElement(By.xpath(devicesStatusXPath))
					if(deviceStatusStr != null && deviceStatusStr.getText().contains(virtualOrPhysical)){
						searchedDevice = devicesList.get(i)
						break
					}
				}
			}
			else{
				KeywordUtil.markWarning("WARNING: There is no device in the list")
			}
		}

		return searchedDevice
	}

	public boolean setTestCaseName(String name){
		boolean result = false
		TestObject untitledField = findTestObject('Object Repository/OR_CreateNewTestCase/text_Untitled')
		if(untitledField != null && WebUI.waitForElementVisible(untitledField,30)){
			WebUI.click(untitledField)
			WebUI.delay(1)
			TestObject testCaseTitleInput = findTestObject('OR_CreateNewTestCase/input_AddTestCaseTitle')
			WebUI.setText(testCaseTitleInput,name)
			WebUI.sendKeys(null, Keys.chord(Keys.ENTER))
			result = true
			WebUI.delay(4)
		}
		else{
			MesmerLogUtils.markWarning("Test case name already added")
		}

		return result
	}

	public boolean saveTestCase(){
		boolean result = false
		TestObject btnSaveActive = findTestObject('Object Repository/OR_CreateNewTestCase/text_EnabledSaveTest')
		if(WebUI.waitForElementVisible(btnSaveActive, 30)){
			WebUI.click(btnSaveActive)
			result = true
			WebUI.delay(20)
		}
		else{
			MesmerLogUtils.markWarning("Button save active not found")
		}

		return result
	}

	public void addTags(){
		TestObject textAddTag = findTestObject('Object Repository/OR_CreateNewTestCase/text_AddTag')
		if(WebUI.waitForElementVisible(textAddTag, 10)){
			int i = 0;
			while(i < 4){
				i = i+1
				if(WebUI.waitForElementVisible(textAddTag,10)){
					WebUI.click(textAddTag)
					WebUI.setText(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),'Tag'+i)
					WebUI.sendKeys(findTestObject('OR_CreateNewTestCase/input_AddTag'),Keys.chord(Keys.ENTER))
				}
				else{
					MesmerLogUtils.markFailed("Add Tag not found")
				}
			}
		}
		else{
			MesmerLogUtils.markWarning("Tags already added")
		}
	}
	/**
	 * Check and click the device logs icon
	 * @return
	 */
	public boolean clickLogsIcon(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/icon_logs')
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Logs icon during recording not appeared")
		}
		return result
	}

	/**
	 * Check and click the device logs icon
	 * @return
	 */
	public boolean clickHideLogsIcon(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/btn_CloseDeviceLogs')
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Hide logs icon during recording not appeared")
		}
		return result
	}

	/**
	 * Check and click the device logs icon
	 * @return
	 */
	public boolean clickSettingsIcon(){
		boolean result = false
		TestObject settingsobj = findTestObject('Object Repository/OR_CreateNewTestCase/icon_settings')
		if(WebUI.waitForElementVisible(settingsobj,30)){
			WebUI.click(settingsobj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Settings icon in device logs not visible")
		}
		return result
	}

	public boolean checkIfAllSettingsOptionsAvailable(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String optionsXPath = '//div[@class="popover-content popover-body"]/div/a/span[2]'
		List<WebElement> optionsList = driver.findElements(By.xpath(optionsXPath))
		if(optionsList != null && optionsList.size() > 0){
			int count = 0
			for(int i= 0; i < optionsList.size(); i++){
				WebElement item = optionsList.get(i)
				if(item != null && item.getText().contains("Filter") || item.getText().contains("Pause") || item.getText().contains("Wrap Text")
				|| item.getText().contains("Clear Log") || item.getText().contains("Download Log")){
					count++
				}
			}
			if(count == 5){
				result = true
			}
		}
		else{
			MesmerLogUtils.markFailed("There is no settings option in the list")
		}
		return result
	}

	public boolean clickSettingsOption(String optionName){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String optionsXPath = '//div[@class="popover-content popover-body"]/div/a/span[2]'
		List<WebElement> optionsList = driver.findElements(By.xpath(optionsXPath))
		if(optionsList != null && optionsList.size() > 0){
			int count = 0
			for(int i= 0; i < optionsList.size(); i++){
				WebElement item = optionsList.get(i)
				if(item != null && item.getText().contains(optionName)){
					item.click()
					result = true
					break
				}
			}
		}
		else{
			MesmerLogUtils.markFailed("There is no show filter option in the list")
		}
		return result
	}

	/**
	 * Check if show filter visible in device logs window
	 * @return
	 */
	public boolean checkIfShowFilterAvailable(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/filter_Show')
		if(WebUI.waitForElementVisible(obj,30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Show filter in device logs not visible")
		}
		return result
	}

	/**
	 * Check and click the show filter
	 * @return
	 */
	public boolean clickShowFilter(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/filter_Show')
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Show filter in device logs not visible")
		}
		return result
	}

	public boolean checkIfShowFilterContainsAllOptions(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String optionsXPath = '//div[@class="popover-content popover-body"]/div/div'
		List<WebElement> optionsList = driver.findElements(By.xpath(optionsXPath))
		if(optionsList != null && optionsList.size() > 0){
			int count = 0
			for(int i= 0; i < optionsList.size(); i++){
				WebElement item = optionsList.get(i)
				if(item != null && item.getText().contains("Verbose") || item.getText().contains("Info") || item.getText().contains("Debug")
				|| item.getText().contains("Warn") || item.getText().contains("Error")){
					count++
				}
			}
			if(count == 5){
				result = true
			}
		}
		else{
			MesmerLogUtils.markFailed("There is no show filter option in the list")
		}
		return result
	}

	public boolean clickShowFilterOption(String optionName){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String optionsXPath = '//div[@class="popover-content popover-body"]/div/div'
		List<WebElement> optionsList = driver.findElements(By.xpath(optionsXPath))
		if(optionsList != null && optionsList.size() > 0){
			int count = 0
			for(int i= 0; i < optionsList.size(); i++){
				WebElement item = optionsList.get(i)
				if(item != null && item.getText().contains(optionName)){
					item.click()
					result = true
					break
				}
			}
		}
		else{
			MesmerLogUtils.markFailed("There is no show filter option in the list")
		}
		return result
	}

	/**
	 * Check if both gear icon and show filter is available in device logs window
	 * @return
	 */
	public boolean checkIfGearIconAndShowFilterAvailable(){
		boolean result = false
		if(checkIfShowFilterAvailable()){
			TestObject settingsobj = findTestObject('Object Repository/OR_CreateNewTestCase/icon_settings')
			if(WebUI.waitForElementVisible(settingsobj,30)){
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Settings icon in device logs not visible")
			}
		}
		return result
	}

	public List<WebElement> getDeviceLogsList(){
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(1)

		List<WebElement> resultList = null

		String deviceLogsXPath = '//div[@class="deviceLog ng-star-inserted hScroll"]/div'
		List<WebElement> deviceLogsList = driver.findElements(By.xpath(deviceLogsXPath))
		if(deviceLogsList != null && deviceLogsList.size() > 0){
			resultList = deviceLogsList
		}
		else{
			MesmerLogUtils.markFailed("There is no log in the list")
		}

		return resultList
	}

	public List<WebElement> getLogsByFontColor(String colorName){
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(1)

		List<WebElement> resultList = null
		String deviceLogsXPath = ""
		if(colorName.equalsIgnoreCase("yellow")){
			deviceLogsXPath = '//div[@class="deviceLog ng-star-inserted hScroll"]/div[@class="logRow force-select fontYellow greyFlag ng-star-inserted"]'
		}
		else if(colorName.equalsIgnoreCase("pink")){
			deviceLogsXPath = '//div[@class="deviceLog ng-star-inserted hScroll"]/div[@class="logRow force-select fontPink greyFlag ng-star-inserted"]'
		}
		else if(colorName.equalsIgnoreCase("blue")){
			deviceLogsXPath = '//div[@class="deviceLog ng-star-inserted hScroll"]/div[@class="logRow force-select fontBlue greyFlag ng-star-inserted"]'
		}
		else{
			deviceLogsXPath = '//div[@class="deviceLog ng-star-inserted hScroll"]/div[@class="logRow force-select ng-star-inserted"]'
		}
		List<WebElement> deviceLogsList = driver.findElements(By.xpath(deviceLogsXPath))
		if(deviceLogsList != null && deviceLogsList.size() > 0){
			resultList = deviceLogsList
		}
		else{
			MesmerLogUtils.markFailed("There is no log in the list")
		}

		return resultList
	}

	public boolean discardTestCase(){
		boolean result = false
		// Check if 3 dots button enabled
		TestObject btnOptionsEnabled = findTestObject('Object Repository/OR_CreateNewTestCase/iconEnabledOptions')
		if(WebUI.waitForElementVisible(btnOptionsEnabled, 30)){
			WebUI.click(btnOptionsEnabled)
			WebUI.delay(2)
			TestObject btnDiscard = findTestObject('Object Repository/OR_CreateNewTestCase/option_Discard')
			if(WebUI.waitForElementVisible(btnDiscard, 30)){
				WebUI.click(btnDiscard)
				WebUI.delay(2)
				TestObject alertDiscard = findTestObject('Object Repository/OR_CreateNewTestCase/alert_Discard')
				if(WebUI.waitForElementVisible(alertDiscard, 30)){
					TestObject btnYes = findTestObject('Object Repository/OR_CreateNewTestCase/text_yes')
					WebUI.click(btnYes)
					result = true
					WebUI.delay(5)
				}
				else{
					MesmerLogUtils.markFailed("Discard alert not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("Button discard not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Options enabled button not found")
		}

		return result
	}

	public boolean saveAndContinueTestCase(){
		boolean result = false
		// Check if 3 dots button enabled
		TestObject btnOptionsEnabled = findTestObject('Object Repository/OR_CreateNewTestCase/iconEnabledOptions')
		if(WebUI.waitForElementVisible(btnOptionsEnabled, 30)){
			WebUI.click(btnOptionsEnabled)
			WebUI.delay(2)
			TestObject btnSaveAndContinue = findTestObject('Object Repository/OR_CreateNewTestCase/text_SaveAndContinue')
			if(WebUI.waitForElementVisible(btnSaveAndContinue, 30)){
				WebUI.click(btnSaveAndContinue)
				result = true
				WebUI.delay(5)
			}
			else{
				MesmerLogUtils.markFailed("Button save and continue not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Options enabled button not found")
		}

		return result
	}
	/**
	 * This method will check if the create new test screen successfully
	 * opened or not
	 * @return
	 */
	public boolean checkIfCreateNewTestScreenOpen(){
		boolean result = false
		TestObject titleNewTest = findTestObject('Object Repository/OR_CreateNewTestCase/title_NewTestCase')
		if(WebUI.waitForElementVisible(titleNewTest, 60)){
			TestObject titleSelectDevice = findTestObject('Object Repository/OR_CreateNewTestCase/title_SelectDevice')
			if(WebUI.waitForElementVisible(titleSelectDevice, 60)){
				result = true
			}
			else{
				MesmerLogUtils.markFailed("Select device title not visible on screen")
			}
		}
		else{
			MesmerLogUtils.markFailed("New test case title not visible on screen")
		}
		return result
	}
	/**
	 * This method is used to find and click the done button for saving the test case
	 * @return
	 */
	public boolean clickDoneGreenButton(){
		boolean result = false
		TestObject btnDoneGreen = findTestObject('Object Repository/OR_CreateNewTestCase/btn_DoneGreen')
		if(WebUI.waitForElementVisible(btnDoneGreen, 60)){
			WebUI.click(btnDoneGreen)
			result = true

		}
		else{
			MesmerLogUtils.markFailed("Green done button for saving the test case not visible")
		}
		return result
	}

	/**
	 * This method will check if the save test alert appeared or not
	 * @return
	 */
	public boolean checkIfSaveTestAlertAppeared(){
		boolean result = false
		TestObject alertSaveTest = findTestObject('Object Repository/OR_CreateNewTestCase/title_DialogSaveTestNew')
		if(WebUI.waitForElementVisible(alertSaveTest, 60)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Save test dialog not appeared within 1 minute")
		}
		return result
	}
	/**
	 * This method will be used to set the test case name
	 * @param name
	 * @return
	 */
	public boolean setRecordedTestCaseName(String name){
		boolean result = false
		TestObject inputTestCaseTitle = findTestObject('Object Repository/OR_CreateNewTestCase/input_TestCaseTitle')
		if(WebUI.waitForElementVisible(inputTestCaseTitle, 60)){
			WebUI.click(inputTestCaseTitle)
			WebUI.clearText(inputTestCaseTitle)
			WebUI.delay(2)
			WebUI.setText(inputTestCaseTitle, name)
			WebUI.delay(2)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Test case title input field not appeared within 1 minute")
		}

		return result
	}

	/**
	 * This method will be used to set the tag
	 * @param name
	 * @return
	 */
	public boolean setRecordedTestCaseTag(String tagname){
		boolean result = false
		TestObject inputTagTitle = findTestObject('Object Repository/OR_CreateNewTestCase/input_AddTagField')
		if(WebUI.waitForElementVisible(inputTagTitle, 60)){
			WebUI.click(inputTagTitle)
			//			WebUI.clearText(inputTagTitle)
			WebUI.delay(2)
			WebUI.setText(inputTagTitle, tagname)
			WebUI.sendKeys(inputTagTitle, Keys.chord(Keys.ENTER))
			WebUI.delay(5)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Add tag input field not appeared within 1 minute")
		}

		return result
	}

	public boolean clickOnNeverMindOnDiscardAlert(){
		boolean result = false
		TestObject neverMindDiscardTest = findTestObject("Object Repository/OR_CreateNewTestCase/alert_discardRecodingTestCase")
		if(WebUI.waitForElementVisible(neverMindDiscardTest,60)){
			TestObject buttonNeverMind = findTestObject("Object Repository/OR_CreateNewTestCase/button_neverMind")
			if(WebUI.waitForElementPresent(buttonNeverMind, 10)){
				WebUI.click(buttonNeverMind)
				if(WebUI.waitForElementNotPresent(neverMindDiscardTest, 60)){
					result = true
				}
			}
		}else{
			MesmerLogUtils.markFailed("Discard alert not appeared within 1 minute")
		}
		return result
	}



	/**
	 * This is the method to click the discard button on save test dialog screen
	 * @return
	 */
	public boolean clickDiscardButton(){
		boolean result = false
		TestObject btnDiscard = findTestObject('Object Repository/OR_CreateNewTestCase/btn_DiscardTest')
		if(WebUI.waitForElementVisible(btnDiscard, 60)){
			WebUI.click(btnDiscard)
			result = true
			WebUI.delay(4)
		}
		else{
			MesmerLogUtils.markFailed("Discard button not available")
		}

		return result
	}
	/**
	 * This method will be used to click the choose another device when one device would be unable to retrieve the device
	 * @return
	 */
	public boolean checkIfChooseAnotherDeviceBtnVisible(){
		boolean result = false
		TestObject btnChooseAnotherDevice = findTestObject('Object Repository/OR_CreateNewTestCase/btn_ChooseAnotherDevice')
		if(WebUI.waitForElementVisible(btnChooseAnotherDevice, 5)){
			WebUI.click(btnChooseAnotherDevice)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Choose another device button not available")
		}

		return result
	}
	/**
	 * This method will be used to discard the test case by clicking on green done button
	 */
	public boolean discardTestCaseNew(){
		boolean result = false

		if(clickDoneGreenButton()){
			if(checkIfNewDiscardAlertAppeared()){
				result = true
			}
			else{
				MesmerLogUtils.logInfo("Test case not discarded")
			}
		}
		else{
			MesmerLogUtils.logInfo("Green done button not clicked")
		}

		return result
	}

	/**
	 * Check if enter the full screen mode view exists
	 * @return
	 */
	public boolean checkIfEnterFullScreenExists(){
		boolean result = false
		TestObject modeObj = findTestObject('Object Repository/OR_CreateNewTestCase/enter_FullScreen')
		if(WebUI.waitForElementVisible(modeObj, 30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Full screen mode text not visible")
		}

		return result
	}

	/**
	 * Enter the full screen mode view
	 * @return
	 */
	public boolean enterFullScreenMode(){
		boolean result = false
		TestObject modeObj = findTestObject('Object Repository/OR_CreateNewTestCase/enter_FullScreen')
		if(WebUI.waitForElementVisible(modeObj, 30)){
			WebUI.click(modeObj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markWarning("Full screen mode text not visible")
		}

		return result
	}

	/**
	 * Exit the full screen mode view
	 * @return
	 */
	public boolean exitFullScreenMode(){
		boolean result = false
		TestObject modeObj = findTestObject('Object Repository/OR_CreateNewTestCase/exit_FullScreen')
		if(WebUI.waitForElementVisible(modeObj, 30)){
			WebUI.click(modeObj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markWarning("Exit full screen mode text not visible")
		}

		return result
	}
	/**
	 * Check and click the never mind button while device preparation
	 * @return
	 */
	public boolean clickCancelAndNeverMind(){
		boolean result = false
		TestObject cancelButton = findTestObject('Object Repository/OR_CreateNewTestCase/btn_CancelOnUpperBar')
		if(WebUI.waitForElementVisible(cancelButton, 30)){
			WebUI.click(cancelButton)
			WebUI.delay(1)
			TestObject btnNeverMind = findTestObject('Object Repository/OR_CreateNewTestCase/btn_NeverMindOnUpperBar')
			if(WebUI.waitForElementVisible(btnNeverMind, 5)){
				WebUI.click(btnNeverMind)
				result = true
				MesmerLogUtils.logInfo("NeverMind button found and clicked")
			}
			else{
				MesmerLogUtils.markFailed("Button never mind not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Cancel button during device preparation not found")
		}
		return result
	}

	/**
	 * Check and click the ok, cancel button while device preparation
	 * @return
	 */
	public boolean clickCancelAndOk(){
		boolean result = false
		TestObject cancelButton = findTestObject('Object Repository/OR_CreateNewTestCase/btn_CancelOnUpperBar')
		if(WebUI.waitForElementVisible(cancelButton, 30)){
			WebUI.click(cancelButton)
			WebUI.delay(1)
			TestObject btnCancelOk = findTestObject('Object Repository/OR_CreateNewTestCase/btn_YesCancelOnUpperBar')
			if(WebUI.waitForElementVisible(btnCancelOk, 5)){
				WebUI.click(btnCancelOk)
				result = true
				MesmerLogUtils.logInfo("Yes cancel button found and clicked")
			}
			else{
				MesmerLogUtils.markFailed("Button yes cancel not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Cancel button during device preparation not found")
		}
		return result
	}


	public boolean clickCancelButton(){
		boolean result = false
		TestObject cancelButton = findTestObject('Object Repository/OR_CreateNewTestCase/btn_CancelOnUpperBar')
		if(WebUI.waitForElementVisible(cancelButton, 2)){
			MesmerLogUtils.logInfo("Clicking Cancel button")
			WebUI.click(cancelButton)
			WebUI.delay(1)

			TestObject yesCancelButton = findTestObject('Object Repository/OR_CreateNewTestCase/btn_YesCancelOnUpperBar')
			if(WebUI.waitForElementVisible(yesCancelButton, 5)){
				MesmerLogUtils.logInfo("Clicking Yes Cancel button")
				WebUI.click(yesCancelButton)
				TestObject selectDevice = findTestObject('Object Repository/OR_CreateNewTestCase/title_SelectDevice')
				if(WebUI.waitForElementVisible(selectDevice, 10)==true){
					MesmerLogUtils.logInfo("Select device is displayed")
					result = true
				}
				else{
					MesmerLogUtils.logInfo("Select Device info is not displayed")
				}

			}else{
				MesmerLogUtils.logInfo("Yes Cancel button is not displayed")
			}


		}else{
			MesmerLogUtils.logInfo("Cancel button is not displayed")
		}
		return result
	}


	/**
	 * This function is used to find out the Device Error observed during Recoding session
	 */
	public void checkDeviceErrorAndCancel(){

		TestObject deviceErrorText = findTestObject('Object Repository/OR_CreateNewTestCase/text_DeviceErrorInUpperBar')
		TestObject checkError = findTestObject('Object Repository/OR_CreateNewTestCase/text_errorDuringRecording')

		if(WebUI.waitForElementVisible(deviceErrorText, 2)==true){
			MesmerLogUtils.logInfo("Device Error is displayed")

			if(WebUI.waitForElementVisible(checkError, 2)){
				String getErrorText = WebUI.getText(checkError)

				if(getErrorText.contains("You already have an active")){
					MesmerLogUtils.markFailed("Test is failed due to " + getErrorText)
					clickCancelButton()
				}

				else if(getErrorText.contains("Connection was lost")){
					MesmerLogUtils.markFailed("Test is failed due to " + getErrorText)
					clickCancelButton()
				}

				else{
					MesmerLogUtils.markFailed("Failure reason is ---> " + getErrorText)
					clickCancelButton()
				}

			}
			else{
				MesmerLogUtils.logInfo("No Device error found")

			}

		}
		else{
			MesmerLogUtils.logInfo("Device Error is not displayed")

		}

	}


	public boolean checkStartingStuckError(){
		boolean result = false
		TestObject deviceErrorText = findTestObject('Object Repository/OR_CreateNewTestCase/text_DeviceErrorInUpperBar')
		TestObject selectDevice = findTestObject('Object Repository/OR_CreateNewTestCase/title_SelectDevice')
		TestObject cancelButton = findTestObject('Object Repository/OR_CreateNewTestCase/btn_CancelOnUpperBar')
		TestObject startingText = findTestObject('Object Repository/OR_CreateNewTestCase/text_Starting')

		if(WebUI.waitForElementVisible(cancelButton, 2)==true && WebUI.waitForElementVisible(startingText, 2)==true){
			MesmerLogUtils.logInfo("Starting text and Cancel button are displayed")
			MesmerLogUtils.logInfo("Clicking Cancel button")
			WebUI.click(cancelButton)
			WebUI.delay(1)

			TestObject yesCancelButton = findTestObject('Object Repository/OR_CreateNewTestCase/btn_YesCancelOnUpperBar')
			if(WebUI.waitForElementVisible(yesCancelButton, 5)){
				MesmerLogUtils.logInfo("Clicking Yes Cancel button")
				WebUI.click(yesCancelButton)

				if(WebUI.waitForElementVisible(selectDevice, 20)==true){
					MesmerLogUtils.logInfo("Select device is displayed")
					result = true
				}
				else{
					MesmerLogUtils.logInfo("Select Device info is not displayed")
				}

			}else{
				MesmerLogUtils.logInfo("Yes Cancel button is not displayed")
			}


		}else{
			MesmerLogUtils.logInfo("Starting text and Cancel button are not displayed")
		}

		//		}
		//		else{
		//			MesmerLogUtils.logInfo("Device Error is not displayed")
		//
		//		}
		return result
	}

	public boolean deleteTestCase(){
		boolean result = false

		return result
	}

	/**
	 * This method will be used to check input fields and buttons on save test case dialogue
	 * @return
	 */
	public boolean optionSaveTestCaseDialogue(){
		boolean result = false
		TestObject inputTestCaseTitle = findTestObject('Object Repository/OR_CreateNewTestCase/input_TestCaseTitle')
		if(WebUI.waitForElementVisible(inputTestCaseTitle, 20)){

			TestObject inputTagTitle = findTestObject('Object Repository/OR_CreateNewTestCase/input_AddTagField')
			if(WebUI.waitForElementVisible(inputTagTitle, 20)){

				TestObject btnSaveTest = findTestObject('Object Repository/OR_CreateNewTestCase/btn_SaveTest')
				if(WebUI.waitForElementVisible(btnSaveTest, 20)){

					TestObject optionDiscard = findTestObject("Object Repository/OR_CreateNewTestCase/btn_YesDiscard")
					if(WebUI.waitForElementPresent(optionDiscard, 20)){
						result = true

					}else{
						MesmerLogUtils.markFailed("Unable to find discard button")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to find Save button")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to find input tag title")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to find input test case title")
		}
		return result
	}

	public void checkErrorsAfterSavingATestCase(){

		TestObject errorOnSavingTC = findTestObject('Object Repository/OR_CreateNewTestCase/errorMsg_OnSavingATestCase')

		String msg = WebUI.getText(errorOnSavingTC)
		if(msg.contains("Test case with this name already exists")){
			MesmerLogUtils.markWarning("Test case with same name already exists")
		}
		else if(msg.contains("Stopping Test Case")){
			MesmerLogUtils.markFailed("Stopping Test Case Recording Failed msg is displayed")
		}
		else if(msg.contains("Test case data not found")){
			MesmerLogUtils.markFailed("Test case data not found")
		}
		else if(msg.contains("504 Gateway")){
			MesmerLogUtils.markFailed("504 Gateway timeout")
		}
		else{
			MesmerLogUtils.markFailed("Failure reason not found")
		}

	}


	/**
	 * This method will be used to save the test case
	 * @return
	 */
	public boolean saveNewTestCase(){
		boolean result = false
		TestObject btnSaveTest = findTestObject('Object Repository/OR_CreateNewTestCase/btn_SaveTest')
		if(WebUI.waitForElementVisible(btnSaveTest, 60)){
			WebUI.click(btnSaveTest)
			WebUI.delay(10)

			TestObject iconSuccess = findTestObject('Object Repository/OR_CreateNewTestCase/icon_SuccessMsg')
			if(WebUI.waitForElementVisible(iconSuccess, 60)){
				MesmerLogUtils.logInfo("Test case saved successfully")

				result = availableOptionsAfterSavingTestCase()
				Utility.logCurrentUTCTime("SaveTestCase-timestamp-")
			}
			else{
				checkErrorsAfterSavingATestCase()
				//				MesmerLogUtils.markFailed("Test case not saved successfully")
			}
		}
		else{
			MesmerLogUtils.markFailed("Button save test case not available")
		}

		return result
	}

	/**
	 * This method will be used to verify options available after saving a test case
	 * @return
	 */
	public boolean availableOptionsAfterSavingTestCase(){
		WebUI.delay(2)

		boolean result = false

		TestObject btnReturnToDevice = findTestObject('Object Repository/OR_CreateNewTestCase/button_returnToDevice')
		TestObject optionViewTestCase = findTestObject('Object Repository/OR_CreateNewTestCase/option_ViewTest')
		TestObject btnReleaseDeviceAndCreate = findTestObject('Object Repository/OR_CreateNewTestCase/button_releaseDeviceAndCreateNewTest')

		if(WebUI.waitForElementVisible(btnReturnToDevice, 30)){

			if(WebUI.waitForElementVisible(optionViewTestCase, 30)){

				if(WebUI.waitForElementVisible(btnReleaseDeviceAndCreate, 30)){
					result = true
				}else{
					MesmerLogUtils.markFailed("Release Device And Create New Test option not found")
				}
			}else{
				MesmerLogUtils.markFailed("View Tes Case option not found")
			}
		}else{
			MesmerLogUtils.markFailed("Return to Device option not found")
		}
		return result
	}


	/**
	 * This method will be used for clicking on Return to device
	 * @return
	 */
	public boolean clickOnReturnToDevice(){
		boolean result = false
		TestObject btnReturnToDevice = findTestObject('Object Repository/OR_CreateNewTestCase/button_returnToDevice')

		if(WebUI.waitForElementVisible(btnReturnToDevice, 60)){
			WebUI.click(btnReturnToDevice)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Unable to click on Return to Device button")
		}
		return result
	}

	/**
	 * This method will be used to verify options available when clicking on Return to device
	 * @return
	 */
	public boolean verifyAvailableOptionsReturnToDevice(){
		boolean result = false

		//check options on Return to Device
		TestObject btnContinueRecordingTest = findTestObject('Object Repository/OR_CreateNewTestCase/button_continueRecording')

		if(WebUI.waitForElementVisible(btnContinueRecordingTest, 60)){

			TestObject btnRestartAppAndRecordNewTest = findTestObject('Object Repository/OR_CreateNewTestCase/button_restartAppAndRecordNew')
			if(WebUI.waitForElementVisible(btnRestartAppAndRecordNewTest, 60)){

				TestObject btnResetDeviceAndRecordNewTest = findTestObject('Object Repository/OR_CreateNewTestCase/button_resetDeviceAndRecordNewTest')
				if(WebUI.waitForElementVisible(btnResetDeviceAndRecordNewTest, 60)){
					result = true

				}else{
					MesmerLogUtils.markFailed("Reset Device And Record New Test button not found")
				}
			}else{
				MesmerLogUtils.markFailed("Restart App And Record New Test button not found")
			}
		}else{
			MesmerLogUtils.markFailed("Continue Recording Test button not found")
		}

		return result
	}

	/**
	 * This method will be used for clicking on Continue Recording and Verify the Continue Recording Screen
	 * @return
	 */

	public boolean continueRecording(){
		boolean result = false
		TestObject btnContinueRecordingTest = findTestObject('Object Repository/OR_CreateNewTestCase/button_continueRecording')

		if(WebUI.waitForElementVisible(btnContinueRecordingTest, 60)){
			WebUI.click(btnContinueRecordingTest)

			TestObject verifyContinueRecordingText = findTestObject('Object Repository/OR_CreateNewTestCase/text_continueRecording')
			if(WebUI.waitForElementVisible(verifyContinueRecordingText, 60)){

				TestObject verifyDivDevice = findTestObject('Object Repository/OR_CreateNewTestCase/div_device')
				if(WebUI.waitForElementVisible(verifyDivDevice, 60)){

					TestObject verifyRecordingIcon = findTestObject('Object Repository/OR_CreateNewTestCase/titletext_recordingDot')
					if(WebUI.waitForElementVisible(verifyRecordingIcon, 60)){
						result = true

					}else{
						MesmerLogUtils.markFailed("Recording icon not appears ")
					}
				}else{
					MesmerLogUtils.markFailed("User is not on device Recording Screen")
				}
			}else{
				MesmerLogUtils.markFailed("Continue Recording Text not appears")
			}

		}else{
			MesmerLogUtils.markFailed("Unable to click on Continue Recording Button")
		}
		return result
	}

	/**
	 * This method will be used for clicking on Restart app and Record New Test and Verify the Options
	 * @return
	 */

	public boolean restartAppAndRecordNewTest(){
		boolean result = false
		TestObject btnRestartAppAndRecordNewTest = findTestObject('Object Repository/OR_CreateNewTestCase/button_restartAppAndRecordNew')

		if(WebUI.waitForElementVisible(btnRestartAppAndRecordNewTest, 60)){
			WebUI.click(btnRestartAppAndRecordNewTest)

			TestObject verifyRestartingApplicationText = findTestObject('Object Repository/OR_CreateNewTestCase/text_verifyRestartingApplication')
			if(WebUI.waitForElementVisible(verifyRestartingApplicationText, 60)){

				TestObject verifyStartingText = findTestObject('Object Repository/OR_CreateNewTestCase/text_Starting')
				if(WebUI.waitForElementVisible(verifyStartingText, 60)){

					TestObject verifyRecordingIcon = findTestObject('Object Repository/OR_CreateNewTestCase/titletext_recordingDot')
					if(WebUI.waitForElementVisible(verifyRecordingIcon, 60)){
						result = true

					}else{
						MesmerLogUtils.markFailed("Recording icon not appears ")
					}
				}else{
					MesmerLogUtils.markFailed("Starting text not appears")
				}
			}else{
				MesmerLogUtils.markFailed("Restarting Application Text not appears")
			}

		}else{
			MesmerLogUtils.markFailed("Unable to click on Restart App And Record New Test Button")
		}
		return result
	}

	/**
	 * This method will be used for clicking on reset device and record new test and verify
	 * @return
	 */
	public boolean resetDeviceAndRecordNewTest(){
		boolean result = false
		TestObject btnResetDeviceAndRecordNewTest = findTestObject('Object Repository/OR_CreateNewTestCase/button_resetDeviceAndRecordNewTest')

		if(WebUI.waitForElementVisible(btnResetDeviceAndRecordNewTest, 60)){
			WebUI.click(btnResetDeviceAndRecordNewTest)

			TestObject verifyResettingDevice = findTestObject('Object Repository/OR_CreateNewTestCase/text_verifyResettingDevice')
			if(WebUI.waitForElementVisible(verifyResettingDevice, 60)){


				TestObject clearingPriorSession = findTestObject('Object Repository/OR_CreateNewTestCase/infoMessage_clearingPriorSession')
				if(WebUI.waitForElementVisible(clearingPriorSession, 240)){

					deviceChecks()

					TestObject verifyStartingText = findTestObject('Object Repository/OR_CreateNewTestCase/text_Starting')
					if(WebUI.waitForElementVisible(verifyStartingText, 120)){

						TestObject verifyRecordingIcon = findTestObject('Object Repository/OR_CreateNewTestCase/titletext_recordingDot')
						if(WebUI.waitForElementVisible(verifyRecordingIcon, 120)){
							result = true

						}else{
							MesmerLogUtils.markFailed("Recording icon not appears ")
						}
					}else{
						MesmerLogUtils.markFailed("Starting text not appears")
					}
				}else{
					MesmerLogUtils.markFailed("Prior Session not cleared")
				}
			}else{
				MesmerLogUtils.markFailed("Restarting Application Text not appears")
			}

		}else{
			MesmerLogUtils.markFailed("Unable to click on Restart App And Record New Test Button")
		}
		return result
	}

	/**
	 * This method will be used for clicking on a back arrow to return to device options and verify list of options available
	 * @return
	 */

	public boolean backReturnToDevice(){
		boolean result = false
		TestObject backArrowReturnToDevice = findTestObject('Object Repository/OR_CreateNewTestCase/backArrow_returnToDevice')


		if(WebUI.waitForElementVisible(backArrowReturnToDevice, 60)){
			WebUI.click(backArrowReturnToDevice)

			availableOptionsAfterSavingTestCase()

		}
		else{
			MesmerLogUtils.markFailed("Unable to click on Back button Return To Device")
		}
		return result
	}

	/**
	 * This method will be used for clicking View Test
	 * @return
	 */
	public boolean clickOnViewTest(){
		boolean result = false
		TestObject btnViewTest = findTestObject('Object Repository/OR_CreateNewTestCase/button_viewTest')

		if(WebUI.waitForElementVisible(btnViewTest, 60)){
			WebUI.click(btnViewTest)
			result = true
			WebUI.delay(3)
		}
		else{
			MesmerLogUtils.markFailed("Unable to click on View Test button")
		}
		return result
	}

	/**
	 * This method will be used to verify available options when clicking on View Test
	 * @return
	 */
	public boolean verifyAvailableOptionsViewTest(){
		boolean result = false
		TestObject btnViewAndEditTest = findTestObject('Object Repository/OR_CreateNewTestCase/button_viewAndEditTest')

		if(WebUI.waitForElementVisible(btnViewAndEditTest, 60)){

			TestObject btnViewTestAndReplay = findTestObject('Object Repository/OR_CreateNewTestCase/button_viewTestAndReplay')
			if(WebUI.waitForElementVisible(btnViewTestAndReplay, 60)){
				result = true

			}else{
				MesmerLogUtils.markFailed("View And Edit Test button not found")
			}
		}else{
			MesmerLogUtils.markFailed("View Test And Replay button not found")
		}

		return result
	}


	/**
	 * This method will be used for clicking view and edit test case
	 * @return
	 */
	public boolean viewAndEditTestCase(){
		boolean result = false
		TestObject optionViewAndEditTestCase = findTestObject('Object Repository/OR_CreateNewTestCase/option_ViewAndEdit')
		if(WebUI.waitForElementVisible(optionViewAndEditTestCase, 60)){
			WebUI.click(optionViewAndEditTestCase)
			result = true
			WebUI.delay(5)
		}
		else{
			MesmerLogUtils.markFailed("View and edit test case option not available")
		}
		return result
	}

	/**
	 * This method will be used for clicking View Test and Replay
	 * @return
	 */
	public boolean clickOnViewTestAndReplay(){
		boolean result = false
		TestObject btnViewTestAndReplay = findTestObject('Object Repository/OR_CreateNewTestCase/button_viewTestAndReplay')
		if(WebUI.waitForElementVisible(btnViewTestAndReplay, 60)){
			WebUI.click(btnViewTestAndReplay)
			result = true
			WebUI.delay(3)
		}
		else{
			MesmerLogUtils.markFailed("Unable to click on View Test And Replay")
		}
		return result
	}

	/**
	 * This method will be used for clicking on back arrow view test
	 * @return
	 */
	public boolean backViewTest(){
		boolean result = false
		TestObject backArrowviewTest = findTestObject('Object Repository/OR_CreateNewTestCase/backArrow_returnToDevice')


		if(WebUI.waitForElementVisible(backArrowviewTest, 60)){
			WebUI.click(backArrowviewTest)
			availableOptionsAfterSavingTestCase()
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Unable to click on Back button View Test")
		}
		return result
	}


	/**
	 * This method will be used for clicking Release Device And Create New Test
	 * @return
	 */
	public boolean clickOnReleaseDeviceAndCreateNew(){
		boolean result = false
		TestObject btnReleaseDeviceAndCreateNew = findTestObject('Object Repository/OR_CreateNewTestCase/button_releaseDeviceAndCreateNewTest')


		if(WebUI.waitForElementVisible(btnReleaseDeviceAndCreateNew, 60)){
			WebUI.click(btnReleaseDeviceAndCreateNew)
			WebUI.delay(3)
			TestObject verifyReleasingDeviceText = findTestObject('Object Repository/OR_CreateNewTestCase/text_verifyReleasingDevice')
			if(WebUI.waitForElementVisible(verifyReleasingDeviceText, 60)){
				result = true
			}else{
				MesmerLogUtils.markFailed("Unable to click on Return to Device button")
			}
		}
		else{
			MesmerLogUtils.markFailed("Unable to click on Return to Device button")
		}
		return result
	}

	public boolean rebootDevice(){
		boolean result = false
		TestObject deviceReboot = findTestObject('Object Repository/OR_CreateNewTestCase/iconWrap_deviceReboot')

		if(WebUI.waitForElementPresent(deviceReboot, 60)){
			WebUI.click(deviceReboot)
			WebUI.delay(5)
			TestObject confirmationRebootDialogue = findTestObject('Object Repository/OR_CreateNewTestCase/confirmation_rebootDevice')
			if(WebUI.waitForElementPresent(confirmationRebootDialogue, 20)){
				TestObject btnContinueReboot = findTestObject('Object Repository/OR_CreateNewTestCase/btn_continueRebootDevice')
				if(WebUI.waitForElementPresent(btnContinueReboot, 20)){
					WebUI.click(btnContinueReboot)
					MesmerLogUtils.logInfo("Clicked on continue reboot button")
					result = true
				}else{
					MesmerLogUtils.markFailed("Unable to click on continue reboot device button")
				}
			}else{
				MesmerLogUtils.markFailed("Reboot device confirmation dialogue not appears")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to click on device reboot button")
		}
		return result
	}

	public boolean closeApplicationButton(){
		boolean result = false
		TestObject closeApplication = findTestObject('Object Repository/OR_CreateNewTestCase/iconWrap_closeApplication')

		if(WebUI.waitForElementPresent(closeApplication, 30)){
			WebUI.click(closeApplication)
			WebUI.delay(10)
			TestObject confirmationCloseDialogue = findTestObject('Object Repository/OR_CreateNewTestCase/confirmation_closeApplication')
			if(WebUI.waitForElementPresent(confirmationCloseDialogue, 10)){
				WebUI.delay(2)
				TestObject btnYesCloseIt = findTestObject('Object Repository/OR_CreateNewTestCase/btn_yesCloseIt')
				if(WebUI.waitForElementPresent(btnYesCloseIt, 10)){
					WebUI.click(btnYesCloseIt)
					result = true
				}else{
					MesmerLogUtils.markFailed("Unable to click on Yes close it")
				}
			}else{
				MesmerLogUtils.markFailed("Confirmation dialogue close application not appears")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to click on close application button")
		}
		return result
	}
	/**
	 * Check if the side icons enabled
	 * Gestures, Lock device, Reboot device and close application
	 * @return
	 */
	public boolean checkIfSideButtonsAreEnabled(){
		boolean result = false
		TestObject iconGestures = findTestObject('Object Repository/OR_CreateNewTestCase/icon_Gestures')
		if(WebUI.waitForElementPresent(iconGestures, 30)){
			TestObject iconLock = findTestObject('Object Repository/OR_CreateNewTestCase/icon_lock')
			if(WebUI.waitForElementPresent(iconLock, 30)){
				TestObject iconReboot = findTestObject('Object Repository/OR_CreateNewTestCase/icon_Reboot')
				if(WebUI.waitForElementPresent(iconReboot, 30)){
					TestObject closeApplication = findTestObject('Object Repository/OR_CreateNewTestCase/iconWrap_closeApplication')
					if(WebUI.waitForElementPresent(closeApplication, 30)){
						result = true
						MesmerLogUtils.logInfo("All the 4 enabled icons found")
						WebUI.delay(2)
					}
					else{
						MesmerLogUtils.markFailed("Enabled close application icon not found")
					}
				}
				else{
					MesmerLogUtils.markFailed("Enabled reboot device icon not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("Enabled lock device icon not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Enabled gestures icon not found")
		}
		return result
	}

	public boolean deviceChecks(){
		boolean result = false
		TestObject deviceRetrieved = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_deviceRetrieve')
		if(WebUI.waitForElementPresent(deviceRetrieved, 90)){

			TestObject connectToDevice = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_connectedToDevice')
			if(WebUI.waitForElementPresent(connectToDevice, 90)){

				TestObject deviceConfigured = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_deviceConfigured')
				if(WebUI.waitForElementPresent(deviceConfigured, 90)){

					TestObject buildDownload = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_buildDownloaded')
					if(WebUI.waitForElementPresent(buildDownload, 90)){

						TestObject buildInstalled = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_buildInstalled')
						if(WebUI.waitForElementPresent(buildInstalled, 90)){

							TestObject startingStream = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_startingStream')
							if(WebUI.waitForElementPresent(startingStream, 90)){
								MesmerLogUtils.logInfo("Starting Stream")
								result = true

							}else{
								MesmerLogUtils.markFailed("Unable to start stream on device")
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
		return result
	}

	/**
	 * This method will be used to wait until recording starts
	 * @return
	 */
	public boolean waitForRecordingStarts(){
		boolean result = false
		TestObject recordingIcon = findTestObject('Object Repository/OR_CreateNewTestCase/text_Recording')

		if(WebUI.waitForElementVisible(recordingIcon, 180)){
			WebUI.delay(10)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Recording is not started yet")
		}
		return result
	}

	/**
	 * This method will be used to verify options available when clicking on setting button
	 * @return
	 */
	public boolean verifyOptionsAvailableOnSetting(){
		boolean result = false
		TestObject iconFilter = findTestObject('Object Repository/OR_CreateNewTestCase/option_FilterLogs')

		if (WebUI.waitForElementPresent(iconFilter, 10)){

			TestObject btnPause = findTestObject('Object Repository/OR_CreateNewTestCase/btn_Pause')
			if (WebUI.waitForElementPresent(btnPause, 10)){

				TestObject wrapTextOption = findTestObject('Object Repository/OR_CreateNewTestCase/option_WrapText')
				if (WebUI.waitForElementPresent(wrapTextOption, 10)){

					TestObject clearLogs = findTestObject('Object Repository/OR_CreateNewTestCase/option_ClearLog')
					if (WebUI.waitForElementPresent(clearLogs, 10)){

						TestObject downloadLogs = findTestObject('Object Repository/OR_CreateNewTestCase/option_downloadLogs')
						if (WebUI.waitForElementPresent(downloadLogs, 10)){
							result = true

						}else{
							MesmerLogUtils.markFailed("Download log icon not appears ")
						}
					}else{
						MesmerLogUtils.markFailed("Clear log icon not appears")
					}
				}else{
					MesmerLogUtils.markFailed("Wrap text icon not appears ")
				}
			}else{
				MesmerLogUtils.markFailed("Pause icon not appears")
			}
		}else{
			MesmerLogUtils.markFailed("Filter icon not appears")
		}

		return result
	}


	/**
	 * This method will be used to navigate to view and edit test case option
	 * @return
	 */
	public boolean viewTestCase(){
		boolean result = false
		TestObject optionViewTestCase = findTestObject('Object Repository/OR_CreateNewTestCase/option_ViewTest')
		if(WebUI.waitForElementVisible(optionViewTestCase, 60)){
			WebUI.click(optionViewTestCase)
			result = true
			WebUI.delay(5)
		}
		else{
			MesmerLogUtils.markFailed("View test case option not available")
		}
		return result
	}
	/**
	 * Check and click the gestures icon in record screen
	 * @return
	 */
	public boolean clickGestureButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/icon_Gestures')
		if(WebUI.waitForElementVisible(obj, 60)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Gestures icon not found")
		}
		return result
	}

	/**
	 * Check and hover on the gestures icon in record screen
	 * @return
	 */
	public boolean hoverOnGestureButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/icon_Gestures')
		if(WebUI.waitForElementVisible(obj, 60)){
			WebUI.mouseOver(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Gestures icon not found")
		}
		return result
	}

	/**
	 * Check and hover on the lock device icon in record screen
	 * @return
	 */
	public boolean hoverOnLocakDeviceButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/icon_lock')
		if(WebUI.waitForElementVisible(obj, 60)){
			WebUI.mouseOver(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Lock device icon not found")
		}
		return result
	}

	/**
	 * Check if the tooltip ok icon when gestures button is visible
	 * @return
	 */
	public boolean checkOkToolTipInGestureViewVisible(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/btnLinkOk')
		if(WebUI.waitForElementVisible(obj, 5)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Tooltip OK button in gestures pane not found")
		}
		return result
	}
	/**
	 * Check and click the tooltip ok icon when gestures button clicked
	 * @return
	 */
	public boolean checkAndClickOkToolTipInGestureView(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/btnLinkOk')
		if(WebUI.waitForElementVisible(obj, 5)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.logInfo("Tooltip OK button in gestures pane not found")
		}
		return result
	}

	/**
	 * Check and click swipe icon
	 * @return
	 */
	public boolean checkAndClickSwipeGesture(){
		boolean result = false
		//		if(clickGestureButton()){
		//			if(checkAndClickOkToolTipInGestureView()){
		//				WebUI.delay(2)
		clickGestureButton()
		//			}
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/icon_Swipe')
		if(WebUI.waitForElementVisible(obj, 10)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Swipe icon not found")
		}
		//		}

		return result
	}

	/**
	 * Check and click the tap icon
	 * @return
	 */
	public boolean checkAndClickTapGesture(){
		boolean result = false
		//		if(clickGestureButton()){
		//			if(checkAndClickOkToolTipInGestureView()){
		//				WebUI.delay(2)
		clickGestureButton()
		//			}
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/icon_Tap')
		if(WebUI.waitForElementVisible(obj, 10)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Tap icon not found")
		}
		//		}

		return result
	}

	/**
	 * Check and click the long press icon
	 * @return
	 */
	public boolean checkAndClickLongPressGesture(){
		boolean result = false
		//		if(clickGestureButton()){
		//			if(checkAndClickOkToolTipInGestureView()){
		//				WebUI.delay(2)
		clickGestureButton()
		//			}
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/icon_LongPress')
		if(WebUI.waitForElementVisible(obj, 10)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Long press icon not found")
		}
		//		}

		return result
	}
	/**
	 * Check if the device loading view is loaded successfully
	 * @return
	 */
	public boolean checkIfDeviceViewLoaded(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/div_device')
		if(WebUI.waitForElementVisible(obj, 240)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Device loading view not visible within 4 minutes")
		}
		return result
	}
	/**
	 * This method checks if the 4 disabled buttons appear on record screen before starting of recording
	 * @return
	 */
	public boolean checkIfAllDisabledButtonsAvailable(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		String disabledIconsXPath = findTestObject('Object Repository/OR_CreateNewTestCase/div_DisabledButtons').findPropertyValue("xpath").toString()
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(disabledIconsXPath)));
		List<WebElement> disabledIconsList = driver.findElements(By.xpath(disabledIconsXPath))
		if(disabledIconsList != null && disabledIconsList.size() == 4){
			result = true
			MesmerLogUtils.logInfo("4 disabled buttons found")
			//			disabledIconsList.get(0).click()
			MesmerLogUtils.logInfo("Disabled buttons not clickable")
			WebUI.delay(3)
		}
		else{
			MesmerLogUtils.markFailed("Disabled buttons not found on record screen")
		}
		return result
	}
	/**
	 * Check if pause and record buttons appear while recording a test case
	 * @return
	 */
	public boolean checkIfPauseAndResumeRecordExists(){
		boolean result = false
		TestObject btnPause = findTestObject('Object Repository/OR_CreateNewTestCase/btn_Pause')
		if(WebUI.waitForElementPresent(btnPause, 30)){
			WebUI.click(btnPause)
			WebUI.delay(4)
			TestObject btnRecord = findTestObject('Object Repository/OR_CreateNewTestCase/btn_Record')
			if(WebUI.waitForElementPresent(btnRecord, 30)){
				WebUI.click(btnRecord)
				result = true
				WebUI.delay(5)
			}
			else{
				MesmerLogUtils.markFailed("Record button not found while recording is paused")
			}
		}
		else{
			MesmerLogUtils.markFailed("Pause button not found during recording")
		}

		return result
	}
	/**
	 * This is the method which will return the list of devices based on the provided deviceType and device status
	 * @param deviceType (Virtual/Physical)
	 * @param deviceStatus (Ready/Provisioned/inUse/Unavailable)
	 * @return
	 */
	public List<WebElement> getAvailableDevices(String deviceType, String deviceStatus){
		List<WebElement> searchedDevicesList = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebUI.delay(4)
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/OR_CreateNewTestCase/div_DevicesWindow"), 60)){
			if(checkIfNoDeviceAvailable()){
				MesmerLogUtils.markFailed("No device available in the list")
				return searchedDevicesList
			}

			// Get devices list
			String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
			//div[@class="deviceList ng-star-inserted"]/div[1]/div[@class="deviceDetails"]/div[@class="deviceState"]/div/div
			List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
			if(devicesList != null && devicesList.size() > 0){
				searchedDevicesList = new ArrayList<WebElement>()
				for(int i=0; i < devicesList.size(); i++){
					String deviceTypeXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="infoText"]'
					WebElement deviceTypeElement = driver.findElement(By.xpath(deviceTypeXPath))
					String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div[starts-with(@class,"label")]'
					WebElement deviceStatusElement = driver.findElement(By.xpath(devicesStatusXPath))
					if(deviceTypeElement != null && deviceTypeElement.getText().contains(deviceType)){
						if(deviceStatusElement != null && (deviceStatusElement.getText().contains(deviceStatus))){
							searchedDevicesList.add(devicesList.get(i))
						}
					}
				}
			}
			else{
				MesmerLogUtils.markWarning("There is no device in the list")
			}
		}
		else{
			MesmerLogUtils.markWarning("Devices list window not appeared")
		}
		return searchedDevicesList
	}
	/**
	 * Check and click the next button in device selection
	 * @return
	 */
	public boolean clickNextBtn(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/btn_NextInDeviceSelection')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button next not found in device selection")
		}

		return result
	}

	/**
	 * Check and click the start button in device selection
	 * @return
	 */
	public boolean clickStartBtn(){
		boolean result = false

		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/btn_startInDeviceSelection')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button start not found in device selection")
		}

		return result
	}


	/**
	 * Check and click the apply button in device selection
	 * @return
	 */
	public boolean clickApplyBtn(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/btn_applyInDeviceSelection')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button apply not found in device selection")
		}

		return result
	}

	/**
	 * Check and click the cancel button in device selection
	 * @return
	 */
	public boolean clickCancelBtn(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/btn_CancelInDeviceSelection')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button cancel not found in device selection")
		}

		return result
	}
	/**
	 * Check and select the language in device config
	 * @return
	 */
	public boolean selectLanguageLabel(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/label_ConfigLang')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Label select language not found in device config")
		}
		return result
	}
	/**
	 * Check and select the region in device config
	 * @return
	 */
	public boolean selectRegionLabel(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/label_ConfigRegion')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Label select region not found in device config")
		}
		return result
	}

	/**
	 * Check and select the gps location in device config
	 * @return
	 */
	public boolean selectGPSLabel(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/label_ConfigGPS')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Label gps location not found in device config")
		}
		return result
	}
	/**
	 * Click the search input field and search for the provided region
	 * @param region
	 * @return
	 */
	public boolean searchRegionInSearchField(String region){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/input_SearchRegion')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			WebUI.delay(2)
			WebUI.setText(obj, region)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Search field to enter region not found")
		}
		return result
	}
	/**
	 * Set the device language with provided language and region combination
	 * @param language
	 * @param region
	 * @return
	 */
	public boolean setDeviceLanguage(String language, String region){
		boolean result = false
		if(selectLanguageLabel()){
			String languagesListXPath = '//app-device-language/div[@class="contents"]/div'
			WebDriver driver = DriverFactory.getWebDriver()
			WebDriverWait wait = new WebDriverWait(driver, 60);
			List<WebElement> languagesList = driver.findElements(By.xpath(languagesListXPath))
			if(languagesList != null && languagesList.size() > 0){
				for(int i = 0; i < languagesList.size(); i++){
					String languageXPath = '//app-device-language/div[@class="contents"]/div['+(i+1)+']/div[@class="languageRegion"]/div[@class="language"]'
					WebElement languageElement = driver.findElement(By.xpath(languageXPath))
					String regionXPath = '//app-device-language/div[@class="contents"]/div['+(i+1)+']/div[@class="languageRegion"]/div[@class="region"]'
					WebElement regionElement = driver.findElement(By.xpath(regionXPath))
					if(languageElement.getText().equalsIgnoreCase(language) && regionElement.getText().equalsIgnoreCase(region)){
						languagesList.get(i).click()
						WebUI.delay(2)
						if(clickApplyBtn()){
							result = true
							break
						}
					}
				}
			}
			else{
				MesmerLogUtils.logInfo("There is no language in the list")
			}
		}

		return result
	}
	/**
	 * Set the device region by searching it and selecting the searched region
	 * @param region
	 * @return
	 */
	public boolean setDeviceRegion(String region){
		boolean result = false
		if(selectRegionLabel()){
			if(searchRegionInSearchField(region)){
				String regionsXPath = '//div[@class="regionsWrap CP ng-star-inserted"]/div[@class="region ng-star-inserted"]'
				WebDriver driver = DriverFactory.getWebDriver()
				WebDriverWait wait = new WebDriverWait(driver, 60);
				List<WebElement> regionsList = driver.findElements(By.xpath(regionsXPath))
				if(regionsList != null && regionsList.size() > 0){
					for(int i = 0; i < regionsList.size(); i++){
						String regionXPath = '//div[@class="regionsWrap CP ng-star-inserted"]/div[@class="region ng-star-inserted"]['+(i+1)+']/div[@class="regionName"]'
						WebElement regionElement = driver.findElement(By.xpath(regionXPath))
						if(regionElement != null && regionElement.getText().equalsIgnoreCase(region)){
							regionsList.get(i).click()
							WebUI.delay(2)
							if(clickApplyBtn()){
								result = true
								break
							}
						}
					}
				}
				else{
					MesmerLogUtils.logInfo("Searched regions list is empty")
				}
			}
		}

		return result
	}
	/**
	 * Set the provided gps location in the input field
	 * @param gpsLocation
	 * @return
	 */
	public boolean setGPSLocation(String gpsLocation){
		boolean result = false
		if(selectGPSLabel()){
			TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/input_EnterLatLng')
			if(WebUI.waitForElementPresent(obj, 30)){
				WebUI.click(obj)
				WebUI.delay(2)
				WebUI.setText(obj, gpsLocation)
				WebUI.delay(2)
				if(clickApplyBtn()){
					result = true
				}
			}
			else{
				MesmerLogUtils.markFailed("Search region to set gps location not found")
			}
		}
		return result
	}

	/**
	 * Set the provided tag in the input field
	 * @param tag
	 * @return
	 */
	public boolean setTagsInDeviceSelection(String tag){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/input_EnterTags')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			WebUI.delay(2)
			WebUI.setText(obj, tag)
			WebUI.delay(2)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Input field to add tags not found")
		}
		return result
	}

	public boolean startBuildLoading(String tag){
		boolean result = false
		if(clickApplyBtn()){
			MesmerLogUtils.logInfo("Next button clicked and moved to add tags screens")
			if(!tag.isEmpty()){
				setTagsInDeviceSelection(tag)
			}
			if(clickStartBtn()){
				MesmerLogUtils.logInfo("Start button clicked and build loading starts")
				result = true
			}
		}
		return result
	}

	public WebElement selectDeviceAndSetParams(String deviceType, String UDID , String language, String region, String gpsLatLng, String tag){
		WebElement searchedDevice = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebUI.delay(4)
		// Get devices list
		String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
		List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
		WebUI.delay(2)
		if(devicesList != null && devicesList.size() > 0){
			for(int i=0; i < devicesList.size(); i++){
				String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div'
				WebElement deviceStatusStr = driver.findElement(By.xpath(devicesStatusXPath))
				String deviceTypeXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="mesmerSimpleTooltip ng-star-inserted"]/span[@class="deviceUDID singleEllipses"]'
				WebElement deviceTypeElement = driver.findElement(By.xpath(deviceTypeXPath))
				//	String deviceUDIDXpath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div'
				//	WebElement deviceUDID = driver.findElement(By.xpath(deviceUDIDXpath))
				if(deviceStatusStr != null && (deviceStatusStr.getText().contains("Ready") ||
				deviceStatusStr.getText().contains("Provisioned")) &&
				deviceTypeElement.getText().contains(deviceType) && deviceTypeElement.getText().contains(UDID)){
					devicesList.get(i).click()
					WebUI.delay(3)

					break
				}
			}
			if(CreateTestController.getInstance().clickNextBtn()){
				if(!language.isEmpty()){
					if(CreateTestController.getInstance().setDeviceLanguage(language, "US")){
						MesmerLogUtils.logInfo("Language selected and applied")
					}
				}
				if(!region.isEmpty()){
					if(CreateTestController.getInstance().setDeviceRegion(region)){
						MesmerLogUtils.logInfo("Region selected and applied")
					}
				}
				if(!gpsLatLng.isEmpty()){
					if(CreateTestController.getInstance().setGPSLocation(gpsLatLng)){
						MesmerLogUtils.logInfo("GPS Location selected and applied")
					}
				}
				if(!tag.isEmpty()){
					if(CreateTestController.getInstance().setDeviceRegion(tag)){
						MesmerLogUtils.logInfo("Tag selected and applied")
					}
				}
				if(CreateTestController.getInstance().startBuildLoading(tag)){
					MesmerLogUtils.logInfo("All configs are configured successfully")
				}
			}
		}
		else{
			MesmerLogUtils.markWarning("There is no device in the list")
		}

		return searchedDevice
	}
}
