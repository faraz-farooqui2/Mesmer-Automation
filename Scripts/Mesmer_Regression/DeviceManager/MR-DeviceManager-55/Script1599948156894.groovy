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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil
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
import controllers.AppMapController

// MR-Device Selection-55 | Rerun | Verify that the complete device information appears in the drop-down


CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebDriver driver = DriverFactory.getWebDriver()

try{

	replayDeviceList()

}catch(Exception e){
	println(e.stackTrace)
}
finally{

}

def replayDeviceList(){
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()

	WebDriver driver = DriverFactory.getWebDriver()


	if(WebUI.waitForElementVisible(btnReplay,5)==true){
		WebUI.click(btnReplay)
		WebUI.delay(5)
		DeviceList()

	}
	else{
		MesmerLogUtils.logInfo("Could not click on replay button")
	}
}


def DeviceList(){
	WebDriver driver = DriverFactory.getWebDriver()

	if( Utility.getPlatformName() == "Android"){

		listReadyAndroid = readyAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfReadyAndroidDevices = driver.findElements(By.xpath(listReadyAndroid))
		listInUseAndroid = inUseAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfinUseAndroidDevices = driver.findElements(By.xpath(listInUseAndroid))
		listProvisionedAndroid = provisionedAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfProvisionedAndroidDevices = driver.findElements(By.xpath(listProvisionedAndroid))
		listBrokenAndroid = brokenAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfBrokenAndroidDevices = driver.findElements(By.xpath(listBrokenAndroid))
		listUnavailableAndroid = unavailableAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfUnavailableAndroidDevices = driver.findElements(By.xpath(listUnavailableAndroid))

		// Count Devices
		readyAndroidCount = listOfReadyAndroidDevices.size()
		inUseAndroidCount = listOfinUseAndroidDevices.size()
		provisionedAndroidCount = listOfProvisionedAndroidDevices.size()
		brokenAndroidCount = listOfBrokenAndroidDevices.size()
		unavailableAndroidCount = listOfUnavailableAndroidDevices.size()

		// Device Name
		listDeviceNameAndroidReady = deviceNameListAndroidReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidReady = driver.findElements(By.xpath(listDeviceNameAndroidReady))

		listDeviceNameAndroidProv = deviceNameListAndroidProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidProv = driver.findElements(By.xpath(listDeviceNameAndroidProv))

		listDeviceNameAndroidInUse = deviceNameListAndroidInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidInUse = driver.findElements(By.xpath(listDeviceNameAndroidInUse))

		// Device OS
		listDevicePlatformAndroidReady = devicePlatformListAndroidReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidReady = driver.findElements(By.xpath(listDevicePlatformAndroidReady))

		listDeviceNameAndroidProv = devicePlatformListAndroidProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidProv = driver.findElements(By.xpath(listDeviceNameAndroidProv))

		listDeviceNameAndroidInUse = devicePlatformListAndroidInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidInUse = driver.findElements(By.xpath(listDeviceNameAndroidInUse))

		// Device UDID
		listDeviceTypeAndroidReady = deviceTypeListAndroidReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidReady = driver.findElements(By.xpath(listDeviceTypeAndroidReady))

		listDeviceTypeAndroidProv = deviceTypeListAndroidProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidProv = driver.findElements(By.xpath(listDeviceTypeAndroidProv))

		listDeviceTypeAndroidInUse = deviceTypeListAndroidInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidInUse = driver.findElements(By.xpath(listDeviceTypeAndroidInUse))

		//Android Ready Devices check
		if(readyAndroidCount > 0){
			MesmerLogUtils.markPassed("Total -> "+ readyAndroidCount + " Android devices available in Ready state")
			for (WebElement webElement : listOfDevicesNameAndroidReady) {
				String deviceNameAndroidReadyListText = webElement.getText();
				MesmerLogUtils.logInfo("Ready Android Device Name " + " : " + "  "  +  deviceNameAndroidReadyListText)
			}

			for (WebElement webElement : listOfDevicesPlatformAndroidReady) {
				String devicePlatformAndroidReadyListText = webElement.getText();
				MesmerLogUtils.logInfo("Ready Android Device OS " + " : " + "  "  +  devicePlatformAndroidReadyListText)
			}

			for (WebElement webElement : listOfDevicesTypeAndroidReady) {
				String listOfDevicesTypeAndroidReadyListText = webElement.getText();
				MesmerLogUtils.logInfo("Ready Android Device UDID " + " : " + "  "  +  listOfDevicesTypeAndroidReadyListText)
			}
		}
		if (inUseAndroidCount > 0){
			//Android In-Use Devices check
			MesmerLogUtils.markPassed("Total -> "+ inUseAndroidCount + " Android devices available in In-Use state")

			for (WebElement webElement : listOfDevicesNameAndroidInUse) {
				String deviceNameAndroidInUseListText = webElement.getText();
				MesmerLogUtils.logInfo("InUse Android Device Name " + " : " + "  "  +  deviceNameAndroidInUseListText)
			}

			for (WebElement webElement : listOfDevicesPlatformAndroidInUse) {
				String devicePlatformAndroidInUseListText = webElement.getText();
				MesmerLogUtils.logInfo("InUse Android Device OS " + " : " + "  "  +  devicePlatformAndroidInUseListText)
			}

			for (WebElement webElement : listOfDevicesTypeAndroidInUse) {
				String listOfDevicesTypeAndroidInUseListText = webElement.getText();
				MesmerLogUtils.logInfo("InUse Android Device UDID " + " : " + "  "  +  listOfDevicesTypeAndroidInUseListText)
			}
		}
		if (provisionedAndroidCount > 0){
			//Android Provisioned Devices check
			MesmerLogUtils.markPassed("Total -> "+ provisionedAndroidCount + " Android devices available in Provisioned state")

			for (WebElement webElement : listOfDevicesNameAndroidProv) {
				String deviceNameAndroidProvListText = webElement.getText();
				MesmerLogUtils.logInfo("Provisioned Android Device Name " + " : " + "  "  +  deviceNameAndroidProvListText)
			}

			for (WebElement webElement : listOfDevicesPlatformAndroidProv) {
				String devicePlatformAndroidProvListText = webElement.getText();
				MesmerLogUtils.logInfo("Provisioned Android Device OS " + " : " + "  "  +  devicePlatformAndroidProvListText)
			}

			for (WebElement webElement : listOfDevicesTypeAndroidProv) {
				String deviceUDIDAndroidProvListText = webElement.getText();
				MesmerLogUtils.logInfo("Provisioned Android Device UDID " + " : " + "  "  +  deviceUDIDAndroidProvListText)
			}
		}
		if(brokenAndroidCount > 0){
			//Android Broken Devices check
			MesmerLogUtils.markPassed("Total -> "+ brokenAndroidCount + " Android devices available in Broken state")


		}
		if(unavailableAndroidCount > 0){
			//Android Unavailable Devices check

			MesmerLogUtils.markWarning("Total -> "+ unavailableAndroidCount + " Android devices available in Unavailable state")
		}
		else{
			MesmerLogUtils.markPassed("No Android device is in Unavailable state")
		}

	} else if (Utility.getPlatformName() == "iOS"){

		listReadyiOS = readyIOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfReadyIOSDevices = driver.findElements(By.xpath(listReadyiOS))

		listInUseiOS = inUseiOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfinUseiOSDevices = driver.findElements(By.xpath(listInUseiOS))

		listProvisionediOS = provisionediOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfProvisionediOSDevices = driver.findElements(By.xpath(listProvisionediOS))

		listBrokeniOS = brokeniOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfBrokeniOSDevices = driver.findElements(By.xpath(listBrokeniOS))

		listUnavailableiOS = unavailableiOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfUnavailableiOSDevices = driver.findElements(By.xpath(listUnavailableiOS))


		// Count Devices
		readyiOSCount = listOfReadyIOSDevices.size()

		inUseiOSCount = listOfinUseiOSDevices.size()

		provisionediOSCount = listOfProvisionediOSDevices.size()

		brokeniOSCount = listOfBrokeniOSDevices.size()

		unavailableiOSCount = listOfUnavailableiOSDevices.size()

		// Device Name
		listDeviceNameiOSReady = deviceNameListiOSReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSReady = driver.findElements(By.xpath(listDeviceNameiOSReady))

		listDeviceNameiOSProv = deviceNameListiOSProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSProv = driver.findElements(By.xpath(listDeviceNameiOSProv))

		listDeviceNameiOSInUse = deviceNameListiOSInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSInUse = driver.findElements(By.xpath(listDeviceNameiOSInUse))

		// Device OS
		listDevicePlatformiOSReady = devicePlatformListiOSReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSReady = driver.findElements(By.xpath(listDevicePlatformiOSReady))

		listDevicePlatformiOSProv = devicePlatformListiOSProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSProv = driver.findElements(By.xpath(listDevicePlatformiOSProv))

		listDevicePlatformiOSInUse = devicePlatformListiOSInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSInUse = driver.findElements(By.xpath(listDevicePlatformiOSInUse))

		// Device UDID
		listDeviceTypeiOSReady = deviceTypeListiOSReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSReady = driver.findElements(By.xpath(listDeviceTypeiOSReady))

		listDeviceTypeiOSProv = deviceTypeListiOSProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSProv = driver.findElements(By.xpath(listDeviceTypeiOSProv))

		listDeviceTypeiOSInUse = deviceTypeListiOSInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSInUse = driver.findElements(By.xpath(listDeviceTypeiOSInUse))


		//iOS Ready Devices check
		if(readyiOSCount > 0){
			MesmerLogUtils.markPassed("Total -> "+ readyiOSCount + " iOS devices available in Ready state")
			for (WebElement webElement : listOfDevicesNameiOSReady) {
				String deviceNameiOSReadyListText = webElement.getText();
				MesmerLogUtils.logInfo("Ready iOS Device Name " + " : " + "  "  +  deviceNameiOSReadyListText)
			}

			for (WebElement webElement : listOfDevicesPlatformiOSReady) {
				String devicePlatformiOSReadyListText = webElement.getText();
				MesmerLogUtils.logInfo("Ready iOS Device OS " + " : " + "  "  +  devicePlatformiOSReadyListText)
			}

			for (WebElement webElement : listOfDevicesTypeiOSReady) {
				String deviceUDIDiOSReadyListText = webElement.getText();
				MesmerLogUtils.logInfo("Ready iOS Device UDID " + " : " + "  "  +  deviceUDIDiOSReadyListText)
			}
		}
		//iOS In-Use Devices check
		if(inUseiOSCount > 0){
			MesmerLogUtils.markPassed("Total -> "+ inUseiOSCount + " iOS devices available in In-Use state")
			for (WebElement webElement : listOfDevicesNameiOSInUse) {
				String deviceNameiOSInUseListText = webElement.getText();
				MesmerLogUtils.logInfo("InUse iOS Device Name " + " : " + "  "  +  deviceNameiOSInUseListText)
			}

			for (WebElement webElement : listOfDevicesPlatformiOSInUse) {
				String devicePlatformiOSInUseListText = webElement.getText();
				MesmerLogUtils.logInfo("InUse iOS Device OS " + " : " + "  "  +  devicePlatformiOSInUseListText)
			}

			for (WebElement webElement : listOfDevicesTypeiOSInUse) {
				String deviceUDIDiOSInUseListText = webElement.getText();
				MesmerLogUtils.logInfo("InUse iOS Device UDID " + " : " + "  "  +  deviceUDIDiOSInUseListText)
			}

		}
		//iOS Provisioned Devices check
		if(provisionediOSCount > 0){


			for (WebElement webElement : listOfDevicesNameiOSProv) {
				String deviceNameiOSProvListText = webElement.getText();
				MesmerLogUtils.logInfo("Provisioned iOS Device Name " + " : " + "  "  +  deviceNameiOSProvListText)
			}

			for (WebElement webElement : listOfDevicesPlatformiOSProv) {
				String devicePlatformiOSProvListText = webElement.getText();
				MesmerLogUtils.logInfo("Provisioned iOS Device OS " + " : " + "  "  +  devicePlatformiOSProvListText)
			}

			for (WebElement webElement : listOfDevicesTypeiOSProv) {
				String deviceUDIDiOSProvListText = webElement.getText();
				MesmerLogUtils.logInfo("Provisioned iOS Device UDID " + " : " + "  "  +  deviceUDIDiOSProvListText)
			}
		}

		//iOS Broken Devices check
		if(brokeniOSCount > 0){
			MesmerLogUtils.markPassed("Total -> "+ brokeniOSCount + " iOS devices available in Broken state")

		}

		//iOS Unavailable Devices check
		if(unavailableiOSCount > 0){
			MesmerLogUtils.markWarning("Total -> "+ unavailableiOSCount + " iOS devices available in Unavailable state")
		}
		else{
			MesmerLogUtils.markPassed("No iOS device is in Unavailable state")
		}
	}
}