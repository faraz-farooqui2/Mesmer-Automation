package controllers
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

public class E2EController {

	private static E2EController mInstance = null

	private E2EController(){
	}

	public static E2EController getInstance(){
		if(mInstance == null){
			mInstance = new E2EController()
		}

		return mInstance
	}
	/**
	 * Check and click the settings icon
	 * @return
	 */
	public boolean clickSettingsIcon(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_Common/Option_Settings')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markWarning("Settings icon not found")
		}
		return result
	}

	/**
	 * Check and click the devices option in drop down
	 * @return
	 */
	public boolean clickDevicesOption(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_Common/Option_Settings--Devices')
		if(WebUI.waitForElementVisible(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markWarning("Devices option not found in drop down")
		}
		return result
	}
	/**
	 * Click the options shown above the devices list like: all, android, iOS etc...
	 * @param option
	 * @return
	 */
	public boolean clickDeviceListOptions(String option){
		boolean result = false
		String optionXPath = '//div[@class="textLinks"]/a[contains(text(),"'+option+'")]'
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
		WebElement searchedItem = driver.findElement(By.xpath(optionXPath))
		if(searchedItem != null){
			searchedItem.click()
			result = true
			WebUI.delay(2)
		}

		return result
	}
	/**
	 * Get the devices list based on the provided name like Android or iOS
	 * @param deviceName
	 * @return
	 */
	public List<WebElement> getDevicesList(String deviceName){
		List<WebElement> result = null
		String devicesXPath = '//span[contains(text(),"'+deviceName+'")]'
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> searchedDevicesList = driver.findElements(By.xpath(devicesXPath))
		result = searchedDevicesList
		return result
	}
	/**
	 * Get the list of devices in create new, replay and crawl test screen
	 * @return
	 */
	public List<WebElement> getAvailableDevicesList(){
		List<WebElement> result = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebUI.delay(4)
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/OR_CreateNewTestCase/div_DevicesWindow"), 60)){
			if(CreateTestController.getInstance().checkIfNoDeviceAvailable()){
				MesmerLogUtils.markFailed("No device available in the list")
				return result
			}

			// Get devices list
			String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
			List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
			if(devicesList != null && devicesList.size() > 0){
				result = devicesList
			}
			else{
				MesmerLogUtils.markWarning("There is no device in the list")
			}
		}
		else{
			MesmerLogUtils.markWarning("Devices list window not appeared")
		}
		return result
	}
}
