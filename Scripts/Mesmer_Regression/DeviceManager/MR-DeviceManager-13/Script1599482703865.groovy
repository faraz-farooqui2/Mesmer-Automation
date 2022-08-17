import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

// MR-Device Manager-13 | Verify there is a Reboot column having reboot button in front of every device
CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(2)

try{
	
		String statusReadyXpath = findTestObject('Object Repository/OR_DeviceManager/device_readyStatus').findPropertyValue('xpath').toString()
		List<WebElement> statusReady = driver.findElements(By.xpath(statusReadyXpath))
		
		String statusInUseXpath = findTestObject('Object Repository/OR_DeviceManager/device_inUseStatus').findPropertyValue('xpath').toString()
		List<WebElement> statusInUse = driver.findElements(By.xpath(statusInUseXpath))
		
		String statusProvisionedXpath = findTestObject('Object Repository/OR_DeviceManager/device_provisionedStatus').findPropertyValue('xpath').toString()
		List<WebElement> statusProvisioned = driver.findElements(By.xpath(statusProvisionedXpath))
		
		String statusUnavailableXpath = findTestObject('Object Repository/OR_DeviceManager/device_unavailableStatus').findPropertyValue('xpath').toString()
		List<WebElement> statusUnavailable = driver.findElements(By.xpath(statusUnavailableXpath))
		
		String statusBrokenleXpath = findTestObject('Object Repository/OR_DeviceManager/device_brokenStatus').findPropertyValue('xpath').toString()
		List<WebElement> statusBroken = driver.findElements(By.xpath(statusBrokenleXpath))
		
		String statusConfiguringXpath = findTestObject('Object Repository/OR_DeviceManager/device_configuringStatus').findPropertyValue('xpath').toString()
		List<WebElement> statusConfiguring = driver.findElements(By.xpath(statusConfiguringXpath))

		String statusRebootingXpath = findTestObject('Object Repository/OR_DeviceManager/device_rebootingStatus').findPropertyValue('xpath').toString()
		List<WebElement> statusRebooting = driver.findElements(By.xpath(statusRebootingXpath))
		
		String statusRefreshingXpath = findTestObject('Object Repository/OR_DeviceManager/device_refreshingStatus').findPropertyValue('xpath').toString()
		List<WebElement> statusRefreshing = driver.findElements(By.xpath(statusRefreshingXpath))
			
		MesmerLogUtils.markPassed("Number of Devices with Ready Status  : " +  " " + statusReady.size().toString())
		MesmerLogUtils.markPassed("Number of Devices with InUse Status  : " +  " " + statusInUse.size().toString())
		MesmerLogUtils.markPassed("Number of Devices with Provisioned Status  : " +  " " + statusProvisioned.size().toString())
		MesmerLogUtils.markPassed("Number of Devices with Unavailable Status  : " +  " " + statusUnavailable.size().toString())
		MesmerLogUtils.markPassed("Number of Devices with Broken Status  : " +  " " + statusBroken.size().toString())
		MesmerLogUtils.markPassed("Number of Devices with Configuring Status  : " +  " " + statusConfiguring.size().toString())
		MesmerLogUtils.markPassed("Number of Devices with Rebooting Status  : " +  " " + statusRebooting.size().toString())
		MesmerLogUtils.markPassed("Number of Devices with Refreshing Status  : " +  " " + statusRefreshing.size().toString())

		String totalDevices = statusReady.size() + statusInUse.size() + statusProvisioned.size() + statusUnavailable.size() + statusBroken.size() + statusConfiguring.size() + statusRebooting.size() + statusRefreshing.size()
		
		String rebootEnableXpath = findTestObject('Object Repository/OR_DeviceCertification/deviceManager_rebootEnable').findPropertyValue('xpath').toString()
		List<WebElement> rebootEnable = driver.findElements(By.xpath(rebootEnableXpath))
		
		MesmerLogUtils.markPassed("Reboot Enabled for   : " +  " " + rebootEnable.size().toString() +  "  " + " Out of " + totalDevices + " "  +  "Devices" )
	

}catch(Exception e){
	e.printStackTrace()
}
finally{

}
