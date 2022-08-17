package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

public class TestDetailsController {
	private static TestDetailsController mInstance = null

	private TestDetailsController(){
	}

	public static TestDetailsController getInstance(){
		if(mInstance == null){
			mInstance = new TestDetailsController()
		}
		return mInstance
	}
	/**
	 * Check if screen results window is open
	 * @return
	 */
	public boolean checkIfScreenResultsWindowOpen(){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/title_WindowScreenResults')
		if(WebUI.waitForElementPresent(obj, 60)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results window title not found")
		}

		return result
	}

	/**
	 * Check and click the screen results cross button
	 * @return
	 */
	public boolean clickScreenResultsCrossButton(){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/btn_CrossScreenResults')
		if(WebUI.waitForElementPresent(obj, 60)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results window cross button not found")
		}

		return result
	}
	/**
	 * Check if baseline and tested text available on screen results button
	 * @param text
	 * @return
	 */
	public boolean checkIfBaselineAndTestedTextAvailable(String text){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String itemXPath = '//div[@class="screenLabel" and contains(text(),"'+text+'")]'
		WebElement itemElement = driver.findElement(By.xpath(itemXPath))
		if(itemElement != null && itemElement.isDisplayed()){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to find the text "+text)
		}
		return result
	}

	/**
	 * Check if the baseline screen visible
	 * @return
	 */
	public boolean checkIfBaselineScreenAvailable(){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/screen_baseline')
		if(WebUI.waitForElementPresent(obj, 60)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results window basline screen not found")
		}

		return result
	}

	/**
	 * Check if the tested screen visible
	 * @return
	 */
	public boolean checkIfTestedScreenAvailable(){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/screen_tested')
		if(WebUI.waitForElementPresent(obj, 60)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results window tested screen not found")
		}

		return result
	}

	/**
	 * Check if the assertions button visible
	 * @return
	 */
	public boolean checkIfAsserrtionsBtnAvailable(){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/btn_Assertions')
		if(WebUI.waitForElementPresent(obj, 60)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results window assertions button not found")
		}

		return result
	}

	/**
	 * Check and click the assertions button
	 * @return
	 */
	public boolean clickAssertionsButton(){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/btn_Assertions')
		if(WebUI.waitForElementPresent(obj, 60)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results window assertions button not found")
		}

		return result
	}

	/**
	 * Returns the list of screens of a replayed test based on the provided type
	 * 1 for baseline and 2 for next replay tab
	 * @param type
	 * @return
	 */
	public List<WebElement> checkIfAScreenWithErrorExists(){
		List<WebElement> result = null
		String xpath = '//div[@class="testCasesScroll"]/mat-card[contains(@class,"mat-card error ng-star-inserted")]'

		try{
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
			if(itemElements != null && itemElements.size() > 0){
				result = itemElements
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	/**
	 * Check and click the show all issues button
	 * @return
	 */
	public boolean clickShowAllIssuesButton(){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/checkBox_ShowAllIssuesOnScreen')
		if(WebUI.waitForElementPresent(obj, 60)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results window show all issues button not found")
		}

		return result
	}

	/**
	 * Check if assertions window is open
	 * @return
	 */
	public boolean checkIfAssertionsWindowOpen(){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/title_WindowAssertions')
		if(WebUI.waitForElementPresent(obj, 60)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Assertions window title not found")
		}

		return result
	}

	/**
	 * Check and click the assertion type text
	 * @return
	 */
	public boolean clickAssertionTypeText(){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/title_AssertionType')
		if(WebUI.waitForElementPresent(obj, 60)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Assertion type text not found")
		}

		return result
	}

	/**
	 * Check and click the provided assertion type
	 * @param name
	 * @return
	 */
	public boolean clickTheAssertionType(String name){
		boolean result = false
		String xpath = '//select[@class="dSelect ng-valid ng-dirty ng-touched"]/option[contains(text(),"'+name+'")]'

		try{
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement itemElements = driver.findElement(By.xpath(xpath))
			if(itemElements != null){
				itemElements.click()
				result = true
				WebUI.delay(2)
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	/**
	 * Click and set the value in input field
	 * @return
	 */
	public boolean clickAndInsertWaitAssertionInputField(String value){
		int result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/input_WaitAssertionValue')
		if(WebUI.waitForElementPresent(obj, 60)){
			WebUI.click(obj)
			WebUI.delay(2)
			WebUI.setText(obj, value)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Input field to set the value of wait assertion type not found")
		}

		return result
	}

	public boolean checkIfTestResultScreenOpen(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestResult/title_TestResultScreen')
		if(WebUI.waitForElementVisible(obj, 60)){
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Test result screen opened")
		}
		return result
	}

	public String getTestResultScreenTestCaseTitle(){
		String result = ""
		TestObject obj = findTestObject('Object Repository/OR_TestResult/title_TestResultScreen')
		if(WebUI.waitForElementVisible(obj, 60)){
			result = WebUI.getText(obj)
		}
		else{
			MesmerLogUtils.markFailed("Test result screen test case title not found")
		}
		return result
	}

	public boolean clickBaselineTitleSection(){
		boolean result = false
		WebDriver driver = DriverFactory.getWebDriver()
		String xpath = '//div[@class="deviceStream"]/div[@class="card"]/div[@class="titleSection iconVH"]'
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Baseline title section not found")
		}

		return result
	}

	public boolean clickBaselineEditTestIcon(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/deviceType_Xpath')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.mouseOver(obj)
			WebUI.delay(2)
			String xpath = '//div[@class="deviceStream"]/div[@class="card"]/div[starts-with(@class,"titleSection iconVH")]/div[@class="statusSection"]/div'
			WebDriver driver = DriverFactory.getWebDriver()
			WebElement itemElement = driver.findElement(By.xpath(xpath))
			if(itemElement != null){
				itemElement.click()
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Baseline edit test icon not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Baseline title device type info not found")
		}
		return result
	}

	public boolean checkIfBaselineDeviceElementsInfoExists(){
		boolean result = false
		TestObject objDeviceOS = findTestObject('Object Repository/OR_TestResult/device_OSVersion')
		if(WebUI.waitForElementVisible(objDeviceOS,30)){
			TestObject objDeviceName = findTestObject('Object Repository/OR_TestResult/device_Name')
			if(WebUI.waitForElementVisible(objDeviceName,30)){
				TestObject objDeviceResolution = findTestObject('Object Repository/OR_TestResult/device_Name')
				if(WebUI.waitForElementVisible(objDeviceResolution,30)){
					result = true
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markFailed("Device resolution not found on baseline test")
				}
			}
			else{
				MesmerLogUtils.markFailed("Device name not found on baseline test")
			}
		}
		else{
			MesmerLogUtils.markFailed("Device OS not found on baseline test")
		}
		return result
	}

	public boolean checkIfBaselineExpandDeviceViewVisible(){
		boolean result = false
		try{
			String xpath = '//div[@class="deviceStream"]/div[@class="card"]/div[starts-with(@class,"expandDevice ng")]'
			WebDriver driver = DriverFactory.getWebDriver()
			Boolean isPresent = driver.findElements(By.xpath(xpath)).size() > 0
			if(isPresent){
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Baseline expanded device section not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public List<WebElement> getBaselineTestCaseScreensList(){
		List<WebElement> result = null
		String xpath = '//div[@class="deviceStream"]/div[@class="card"]/div[starts-with(@class,"expandDevice ng")]/descendant::mat-card'
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
		if(itemElements != null && itemElements.size() > 0){
			result = itemElements
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Baseline test case screens list not found")
		}
		return result
	}

	public boolean clickBaselineRightArrow(){
		boolean result = false
		String xpath = '//div[@class="deviceStream"]/div[@class="card"]/div[starts-with(@class,"expandDevice ng")]/descendant::a[starts-with(@class,"rightArrow ng")]'
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
		if(itemElements != null && itemElements.size() > 0){
			itemElements.get(0).click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Baseline right arrow not found")
		}
		return result
	}

	public boolean expandBaseLineScreensList(){
		boolean result = false
		if(checkIfBaselineExpandDeviceViewVisible()){
			result = true
		}
		else{
			if(clickBaselineTitleSection()){
				if(checkIfBaselineExpandDeviceViewVisible()){
					result = true
				}
			}
		}
		return result
	}

	public boolean checkIfTestRunTitleExists(){
		boolean result = false
		String xpath = '//div[@class="testTitle"]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null && itemElement.isDisplayed()){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test run title not found")
		}

		return result
	}

	public boolean clickSelectAllCheckBox(){
		boolean result = false
		String xpath = '//div[@class="roundCheckBox"]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Select all checkbox not found")
		}
		return result
	}

	public List<WebElement> getTestRunsList(){
		List<WebElement> result = null
		//descendant::div[@class="titleSection iconVH"]
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div'
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
		if(itemElements != null && itemElements.size() > 0){
			result = itemElements
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test runs test cases list not found")
		}
		return result
	}
	/**
	 * Click the run test icon in test runs list based on the provided test position in the list
	 * @param position
	 * @return
	 */
	public boolean clickRunTestIconInTestRuns(int position){
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[@class="titleSection iconVH"]//div/div[starts-with(@class,"icon runtest ng")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("run test icon not found")
		}

		return result
	}

	/**
	 * Click the stop test icon in test runs list based on the provided test position in the list
	 * @param position
	 * @return
	 */
	public boolean clickStopTestIconInTestRuns(int position){
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[@class="titleSection iconVH"]//div/div[starts-with(@class,"icon stoptest ng")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("stop test icon not found")
		}

		return result
	}
	/**
	 * Stop executed test run based on the provided position
	 * @param position
	 * @return
	 */
	public boolean stopExecutedTestRun(int position){
		boolean result = false
		if(clickStopTestIconInTestRuns(position)){
			TestObject obj = findTestObject('Object Repository/OR_TestResult/btn_Cancel')
			if(WebUI.waitForElementVisible(obj,30)){
				WebUI.click(obj)
				result = true
				WebUI.delay(2)
			}
		}
		return result
	}

	public boolean checkIfTestRunsDeviceInfoElementsExists(int position){
		boolean result = false
		String deviceOSXpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[@class="titleSection iconVH"]//div/descendant::div[starts-with(@class,"deviceName")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement OsElement = driver.findElement(By.xpath(deviceOSXpath))
		if(OsElement != null && OsElement.isDisplayed()){
			String deviceNameXPath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[@class="titleSection iconVH"]//div/descendant::span[starts-with(@class,"deviceName")]'
			WebElement nameElement = driver.findElement(By.xpath(deviceNameXPath))
			if(nameElement != null && nameElement.isDisplayed()){
				String deviceResXPath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[@class="titleSection iconVH"]//div/descendant::span[starts-with(@class,"deviceResolution")]'
				WebElement ResElement = driver.findElement(By.xpath(deviceResXPath))
				if(ResElement != null && ResElement.isDisplayed()){
					result = true
					WebUI.delay(2)
				}
			}
		}
		return result
	}

	public boolean expandTestRunsTest(int position){
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[starts-with(@class,"WP100 ng")]/descendant::div[contains(@class,"expandDevice")]/div/div[@class="leftSideActions"]/div[starts-with(@class,"datetimeStamp")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test runs test timestamp not found")
		}
		return result
	}
	/**
	 * Check if the test runs test is expanded at the given position
	 * @param position
	 * @return
	 */
	public boolean checkIfTestRunsTestExpanded(int position){
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/descendant::app-test-cases'
		WebDriver driver = DriverFactory.getWebDriver()
		Boolean isAvailable = driver.findElements(By.xpath(xpath)).size() > 0
		if(isAvailable){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test runs test expanded view not found")
		}
		return result
	}

	public boolean clickPlayIconInTestRunsTest(int position){
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[starts-with(@class,"WP100 ng")]/descendant::div[contains(@class,"expandDevice")]/div/div[@class="leftSideActions"]/div[starts-with(@class,"icon playIcon")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test runs test cases play icon not found")
		}
		return result
	}

	public boolean clickCheckBoxInTestRunsTest(int position){
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[starts-with(@class,"WP100 ng")]/descendant::div[contains(@class,"expandDevice")]/div/div[@class="leftSideActions"]/div[starts-with(@class,"roundCheckBox chkBtnCtrl ng")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test runs test cases check box not found")
		}
		return result
	}

	public String getTestRunsTestStatsCount(String type, int position){
		String result = "0"
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[starts-with(@class,"WP100 ng")]/descendant::div[contains(@class,"expandDevice")]/div/div[@class="rightSideActions"]/div[starts-with(@class,"displayFlex")]/div'
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
		if(itemElements != null && itemElements.size() == 4){
			switch(type){
				case "error":
					String countXPath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[starts-with(@class,"WP100 ng")]/descendant::div[contains(@class,"expandDevice")]/div/div[@class="rightSideActions"]/div[starts-with(@class,"displayFlex")]/div[1]/span[2]'
					WebElement itemElement = driver.findElement(By.xpath(countXPath))
					if(itemElement != null){
						result = itemElement.getText()
					}
					break
				case "flaged":
					String countXPath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[starts-with(@class,"WP100 ng")]/descendant::div[contains(@class,"expandDevice")]/div/div[@class="rightSideActions"]/div[starts-with(@class,"displayFlex")]/div[2]/span[2]'
					WebElement itemElement = driver.findElement(By.xpath(countXPath))
					if(itemElement != null){
						result = itemElement.getText()
					}
					break
				case "viewed":
					String countXPath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[starts-with(@class,"WP100 ng")]/descendant::div[contains(@class,"expandDevice")]/div/div[@class="rightSideActions"]/div[starts-with(@class,"displayFlex")]/div[3]/span[2]'
					WebElement itemElement = driver.findElement(By.xpath(countXPath))
					if(itemElement != null){
						result = itemElement.getText()
					}
					break
				case "clock":
					String countXPath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[starts-with(@class,"WP100 ng")]/descendant::div[contains(@class,"expandDevice")]/div/div[@class="rightSideActions"]/div[starts-with(@class,"displayFlex")]/div[4]/span[2]'
					WebElement itemElement = driver.findElement(By.xpath(countXPath))
					if(itemElement != null){
						result = itemElement.getText()
					}
					break

				default:
					break
			}
		}
		else{
			MesmerLogUtils.markFailed("Test runs test cases stats not found")
		}
		return result
	}

	public boolean click3DotsOfTestRunsTest(int position){
		boolean result = false
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/div[starts-with(@class,"WP100 ng")]/descendant::div[contains(@class,"expandDevice")]/div/div[@class="rightSideActions"]/div[starts-with(@class,"icon more")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test runs test 3 dots icon not found")
		}
		return result
	}

	public boolean checkAndClickPopOverOption(String option){
		boolean result = false
		String xpath = '//div[starts-with(@class,"menuWrap ng")]/a'
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
		if(itemElements != null && itemElements.size() == 4){
			switch(option){
				case "download results":
					String downloadResultXPath = '//div[starts-with(@class,"menuWrap ng")]/a[1]/span[2]'
					WebElement itemElement = driver.findElement(By.xpath(downloadResultXPath))
					if(itemElement != null){
						itemElement.click()
						result = true
						WebUI.delay(2)
					}
					break
				case "watch video":
					String watchVideoXPath = '//div[starts-with(@class,"menuWrap ng")]/a[2]/span[2]'
					WebElement itemElement = driver.findElement(By.xpath(watchVideoXPath))
					if(itemElement != null){
						itemElement.click()
						result = true
						WebUI.delay(2)
					}
					break
				case "set as baseline":
					String baselineXPath = '//div[starts-with(@class,"menuWrap ng")]/a[3]/span[2]'
					WebElement itemElement = driver.findElement(By.xpath(baselineXPath))
					if(itemElement != null){
						itemElement.click()
						result = true
						WebUI.delay(2)
					}
					break
				case "remove test run":
					String removeTestRunXPath = '//div[starts-with(@class,"menuWrap ng")]/a[4]/span[2]'
					WebElement itemElement = driver.findElement(By.xpath(removeTestRunXPath))
					if(itemElement != null){
						itemElement.click()
						result = true
						WebUI.delay(2)
					}
					break

				default:
					break
			}
		}
		return result
	}

	public List<WebElement> getTestRunsTestExpandedList(int position){
		List<WebElement> result = null
		String xpath = '//div[starts-with(@class,"deviceStream ng")]/div['+(position+1)+']/descendant::app-test-cases/descendant::mat-card'
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> itemElements = driver.findElements(By.xpath(xpath))
		if(itemElements != null && itemElements.size() > 0){
			result = itemElements
		}
		return result
	}

	public boolean clickSetAsBaselineInStepDetailScreen(){
		boolean result = false
		String xpath = '//a[@class="blueLink" and contains(text(),"Set as Baseline")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement itemElement = driver.findElement(By.xpath(xpath))
		if(itemElement != null){
			itemElement.click()
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Set as baseline text not found")
		}
		return result
	}

	public boolean clickStopInQueueTestIcon(){
		boolean result = false
		String xpath = '//div[starts-with(@class,"icon inqueue ng")]'
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> itemElements= driver.findElements(By.xpath(xpath))
		if(itemElements != null && itemElements.size() > 0){
			itemElements.get(0).click()
			result = true
			WebUI.delay(1)
		}
		return result
	}

	public boolean stopInQueueTestCase(){
		boolean result = false
		if(clickStopInQueueTestIcon()){
			if(stopExecutedTestRun()){
				result = true
			}
		}
		return result
	}

	public boolean checkIfDeleteInQueueTestCasePopUpAppears(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestResult/btn_Cancel')
		if(WebUI.waitForElementVisible(obj,30)){
			result = true
			WebUI.delay(1)
		}
		else{
			MesmerLogUtils.markFailed("Remove in queue test case popup not visible")
		}
		return result
	}

	public boolean clickRemovePinkButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/btn_RemovePink')
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(1)
		}
		else{
			MesmerLogUtils.markFailed("Remove pink button not visible")
		}
		return result
	}
	public boolean clickCancelButton(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestDetails/btnCancelInQueuePopup')
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(1)
		}
		else{
			MesmerLogUtils.markFailed("Cancel button not visible")
		}
		return result
	}
}
