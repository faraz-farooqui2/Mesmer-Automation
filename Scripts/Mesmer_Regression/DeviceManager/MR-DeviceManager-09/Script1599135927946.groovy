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
//import internal.GlobalVariable as GlobalVariable
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

// MR-Device Manager-09 | Verify there is a Device column showing device names with real device and virtual device icons

try{

	deviceManagerList()


}catch(Exception e){
	println(e.stackTrace)
}
finally{
	//	WebUI.refresh()
}

def deviceManagerList(){

	CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
	WebDriver driver = DriverFactory.getWebDriver()


	if(WebUI.waitForElementVisible(allDevicesOptionActive, 2)==false){
		MesmerLogUtils.logInfo("All Devices option is not selected")
		MesmerLogUtils.logInfo("Clicking All devices option")
		WebUI.click(allDevicesOptionActive)
		MesmerLogUtils.logInfo("All devices option is selected")

	}
	else{
		MesmerLogUtils.logInfo("All devices option is selected")

		WebUI.waitForPageLoad(30)

		listReadyiOS = readyiOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfReadyiOSDevices = driver.findElements(By.xpath(listReadyiOS))

		listReadyAndroid = readyAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfReadyAndroidDevices = driver.findElements(By.xpath(listReadyAndroid))

		listInUseiOS = inUseiOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfinUseiOSDevices = driver.findElements(By.xpath(listInUseiOS))

		listInUseAndroid = inUseAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfinUseAndroidDevices = driver.findElements(By.xpath(listInUseAndroid))


		listProvisionediOS = provisionediOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfProvisionediOSDevices = driver.findElements(By.xpath(listProvisionediOS))

		listProvisionedAndroid = provisionedAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfProvisionedAndroidDevices = driver.findElements(By.xpath(listProvisionedAndroid))

		listBrokeniOS = brokeniOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfBrokeniOSDevices = driver.findElements(By.xpath(listBrokeniOS))

		listBrokenAndroid = brokenAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfBrokenAndroidDevices = driver.findElements(By.xpath(listBrokenAndroid))


		listUnavailableiOS = unavailableiOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfUnavailableiOSDevices = driver.findElements(By.xpath(listUnavailableiOS))

		listUnavailableAndroid = unavailableAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfUnavailableAndroidDevices = driver.findElements(By.xpath(listUnavailableAndroid))

		listConfiguringiOS = configuringiOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfConfiguringiOSDevices = driver.findElements(By.xpath(listConfiguringiOS))

		listConfiguringAndroid = configuringAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfConfiguringAndroidDevices = driver.findElements(By.xpath(listConfiguringAndroid))

		listRefreshingiOS = refreshingiOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfRefreshingiOSDevices = driver.findElements(By.xpath(listRefreshingiOS))

		listRefreshingAndroid = refreshingAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfRefreshingAndroidDevices = driver.findElements(By.xpath(listRefreshingAndroid))


		listRebootingiOS = rebootingiOSList.findPropertyValue('xpath').toString()
		List<WebElement> listOfRebootingiOSDevices = driver.findElements(By.xpath(listRebootingiOS))

		listRebootingAndroid = rebootingAndroidList.findPropertyValue('xpath').toString()
		List<WebElement> listOfRebootingAndroidDevices = driver.findElements(By.xpath(listRebootingAndroid))

		//		Count Devices
		readyiOSCount = listOfReadyiOSDevices.size()
		readyAndroidCount = listOfReadyAndroidDevices.size()
		inUseiOSCount = listOfinUseiOSDevices.size()
		inUseAndroidCount = listOfinUseAndroidDevices.size()
		provisionediOSCount = listOfProvisionediOSDevices.size()
		provisionedAndroidCount = listOfProvisionedAndroidDevices.size()
		brokeniOSCount = listOfBrokeniOSDevices.size()
		brokenAndroidCount = listOfBrokenAndroidDevices.size()
		unavailableiOSCount = listOfUnavailableiOSDevices.size()
		unavailableAndroidCount = listOfUnavailableAndroidDevices.size()
		configuringiOSCount = listOfConfiguringiOSDevices.size()
		configuringAndroidCount = listOfConfiguringAndroidDevices.size()
		refreshingiOSCount = listOfRefreshingiOSDevices.size()
		refreshingAndroidCount = listOfRefreshingAndroidDevices.size()
		rebootingiOSCount = listOfRebootingiOSDevices.size()
		rebootingAndroidCount = listOfRebootingAndroidDevices.size()


		// Device Name

		listDeviceNameiOSReady = deviceNameListiOSReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSReady = driver.findElements(By.xpath(listDeviceNameiOSReady))

		listDeviceNameAndroidReady = deviceNameListAndroidReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidReady = driver.findElements(By.xpath(listDeviceNameAndroidReady))

		listDeviceNameiOSInUse = deviceNameListiOSInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSInUse = driver.findElements(By.xpath(listDeviceNameiOSInUse))

		listDeviceNameAndroidInUse = deviceNameListAndroidInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidInUse = driver.findElements(By.xpath(listDeviceNameAndroidInUse))

		listDeviceNameiOSProv = deviceNameListiOSProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSProv = driver.findElements(By.xpath(listDeviceNameiOSProv))

		listDeviceNameAndroidProv = deviceNameListAndroidProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidProv = driver.findElements(By.xpath(listDeviceNameAndroidProv))

		listDeviceNameiOSBroken = deviceNameListiOSBroken.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSBroken = driver.findElements(By.xpath(listDeviceNameiOSBroken))

		listDeviceNameAndroidBroken = deviceNameListAndroidBroken.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidBroken = driver.findElements(By.xpath(listDeviceNameAndroidBroken))

		listDeviceNameiOSUnavailable = deviceNameListiOSUnavailable.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSUnavailable = driver.findElements(By.xpath(listDeviceNameiOSUnavailable))

		listDeviceNameAndroidUnavailable = deviceNameListAndroidUnavailable.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidUnavailable = driver.findElements(By.xpath(listDeviceNameAndroidUnavailable))

		listDeviceNameiOSConfiguring = deviceNameListiOSConfiguring.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSConfiguring = driver.findElements(By.xpath(listDeviceNameiOSConfiguring))

		listDeviceNameAndroidConfiguring = deviceNameListAndroidConfiguring.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidConfiguring = driver.findElements(By.xpath(listDeviceNameAndroidConfiguring))

		listDeviceNameiOSRefreshing = deviceNameListiOSRefreshing.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSRefreshing = driver.findElements(By.xpath(listDeviceNameiOSRefreshing))

		listDeviceNameAndroidRefreshing = deviceNameListAndroidRefreshing.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidRefreshing = driver.findElements(By.xpath(listDeviceNameAndroidRefreshing))

		listDeviceNameiOSRebooting = deviceNameListiOSRebooting.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameiOSRebooting = driver.findElements(By.xpath(listDeviceNameiOSRebooting))

		listDeviceNameAndroidRebooting = deviceNameListAndroidRebooting.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesNameAndroidRebooting = driver.findElements(By.xpath(listDeviceNameAndroidRebooting))

		// Device Platform

		listDevicePlatformiOSReady = devicePlatformListiOSReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSReady = driver.findElements(By.xpath(listDevicePlatformiOSReady))

		listDevicePlatformAndroidReady = devicePlatformListAndroidReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidReady = driver.findElements(By.xpath(listDevicePlatformAndroidReady))

		listDevicePlatformiOSInUse = devicePlatformListiOSInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSInUse = driver.findElements(By.xpath(listDevicePlatformiOSInUse))

		listDevicePlatformAndroidInUse = devicePlatformListAndroidInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidInUse = driver.findElements(By.xpath(listDevicePlatformAndroidInUse))

		listDevicePlatformiOSProv = devicePlatformListiOSProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSProv = driver.findElements(By.xpath(listDevicePlatformiOSProv))

		listDevicePlatformAndroidProv = devicePlatformListAndroidProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidProv = driver.findElements(By.xpath(listDevicePlatformAndroidProv))

		listDevicePlatformiOSBroken = devicePlatformListiOSBroken.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSBroken = driver.findElements(By.xpath(listDevicePlatformiOSBroken))

		listDevicePlatformAndroidBroken = devicePlatformListAndroidBroken.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidBroken = driver.findElements(By.xpath(listDevicePlatformAndroidBroken))

		listDevicePlatformiOSUnavailable = devicePlatformListiOSUnavailable.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSUnavailable = driver.findElements(By.xpath(listDevicePlatformiOSUnavailable))

		listDevicePlatformAndroidUnavailable = devicePlatformListAndroidUnavailable.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidUnavailable = driver.findElements(By.xpath(listDevicePlatformAndroidUnavailable))

		listDevicePlatformiOSConfiguring = devicePlatformListiOSConfiguring.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSConfiguring = driver.findElements(By.xpath(listDevicePlatformiOSConfiguring))

		listDevicePlatformAndroidConfiguring = devicePlatformListAndroidConfiguring.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidConfiguring = driver.findElements(By.xpath(listDevicePlatformAndroidConfiguring))

		listDevicePlatformiOSRefreshing = devicePlatformListiOSRefreshing.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSRefreshing = driver.findElements(By.xpath(listDevicePlatformiOSRefreshing))

		listDevicePlatformAndroidRefreshing = devicePlatformListAndroidRefreshing.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidRefreshing = driver.findElements(By.xpath(listDevicePlatformAndroidRefreshing))

		listDevicePlatformiOSRebooting = devicePlatformListiOSRebooting.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformiOSRebooting = driver.findElements(By.xpath(listDevicePlatformiOSRebooting))

		listDevicePlatformAndroidRebooting = devicePlatformListAndroidRebooting.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesPlatformAndroidRebooting = driver.findElements(By.xpath(listDevicePlatformAndroidRebooting))


		// Device Type

		listDeviceTypeiOSReady = deviceTypeListiOSReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSReady = driver.findElements(By.xpath(listDeviceTypeiOSReady))

		listDeviceTypeAndroidReady = deviceTypeListAndroidReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidReady = driver.findElements(By.xpath(listDeviceTypeAndroidReady))
		
		listDeviceTypeiOSInUse = deviceTypeListiOSInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSInUse = driver.findElements(By.xpath(listDeviceTypeiOSInUse))

		listDeviceTypeAndroidInUse = deviceTypeListAndroidInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidInUse = driver.findElements(By.xpath(listDeviceTypeAndroidInUse))

		listDeviceTypeiOSProv = deviceTypeListiOSProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSProv = driver.findElements(By.xpath(listDeviceTypeiOSProv))

		listDeviceTypeAndroidProv = deviceTypeListAndroidProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidProv = driver.findElements(By.xpath(listDeviceTypeAndroidProv))
		
		listDeviceTypeiOSBroken = deviceTypeListiOSBroken.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSBroken = driver.findElements(By.xpath(listDeviceTypeiOSBroken))

		listDeviceTypeAndroidBroken = deviceTypeListAndroidBroken.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidBroken = driver.findElements(By.xpath(listDeviceTypeAndroidBroken))
		
		listDeviceTypeiOSUnavailable = deviceTypeListiOSUnavailable.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSUnavailable = driver.findElements(By.xpath(listDeviceTypeiOSUnavailable))

		listDeviceTypeAndroidUnavailable = deviceTypeListAndroidUnavailable.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidUnavailable = driver.findElements(By.xpath(listDeviceTypeAndroidUnavailable))
		
		listDeviceTypeiOSConfiguring = deviceTypeListiOSConfiguring.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSConfiguring = driver.findElements(By.xpath(listDeviceTypeiOSConfiguring))

		listDeviceTypeAndroidConfiguring = deviceTypeListAndroidConfiguring.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidConfiguring = driver.findElements(By.xpath(listDeviceTypeAndroidConfiguring))
		
		listDeviceTypeiOSRefreshing = deviceTypeListiOSRefreshing.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSRefreshing = driver.findElements(By.xpath(listDeviceTypeiOSRefreshing))

		listDeviceTypeAndroidRefreshing = deviceTypeListAndroidRefreshing.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidRefreshing = driver.findElements(By.xpath(listDeviceTypeAndroidRefreshing))
		
		listDeviceTypeiOSRebooting = deviceTypeListiOSRebooting.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeiOSRebooting = driver.findElements(By.xpath(listDeviceTypeiOSRebooting))

		listDeviceTypeAndroidRebooting = deviceTypeListAndroidRebooting.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesTypeAndroidRebooting = driver.findElements(By.xpath(listDeviceTypeAndroidRebooting))

		// Device Resolution

		listDeviceResolutioniOSReady = deviceResolutionListiOSReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutioniOSReady = driver.findElements(By.xpath(listDeviceResolutioniOSReady))

		listDeviceResolutionAndroidReady = deviceResolutionListAndroidReady.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutionAndroidReady = driver.findElements(By.xpath(listDeviceResolutionAndroidReady))
		
		listDeviceResolutioniOSInUse = deviceResolutionListiOSInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutioniOSInUse = driver.findElements(By.xpath(listDeviceResolutioniOSInUse))

		listDeviceResolutionAndroidInUse = deviceResolutionListAndroidInUse.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutionAndroidInUse = driver.findElements(By.xpath(listDeviceResolutionAndroidInUse))

		listDeviceResolutioniOSProv = deviceResolutionListiOSProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutioniOSProv = driver.findElements(By.xpath(listDeviceResolutioniOSProv))

		listDeviceResolutionAndroidProv = deviceResolutionListAndroidProv.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutionAndroidProv = driver.findElements(By.xpath(listDeviceResolutionAndroidProv))
		
		listDeviceResolutioniOSBroken = deviceResolutionListiOSBroken.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutioniOSBroken = driver.findElements(By.xpath(listDeviceResolutioniOSBroken))

		listDeviceResolutionAndroidBroken = deviceResolutionListAndroidBroken.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutionAndroidBroken = driver.findElements(By.xpath(listDeviceResolutionAndroidBroken))
		
		listDeviceResolutioniOSUnavailable = deviceResolutionListiOSUnavailable.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutioniOSUnavailable = driver.findElements(By.xpath(listDeviceResolutioniOSUnavailable))

		listDeviceResolutionAndroidUnavailable = deviceResolutionListAndroidUnavailable.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutionAndroidUnavailable = driver.findElements(By.xpath(listDeviceResolutionAndroidUnavailable))
		
		listDeviceResolutioniOSConfiguring = deviceResolutionListiOSConfiguring.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutioniOSConfiguring = driver.findElements(By.xpath(listDeviceResolutioniOSConfiguring))

		listDeviceResolutionAndroidConfiguring = deviceResolutionListAndroidConfiguring.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutionAndroidConfiguring = driver.findElements(By.xpath(listDeviceResolutionAndroidConfiguring))
		
		listDeviceResolutioniOSRefreshing = deviceResolutionListiOSRefreshing.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutioniOSRefreshing = driver.findElements(By.xpath(listDeviceResolutioniOSRefreshing))

		listDeviceResolutionAndroidRefreshing = deviceResolutionListAndroidRefreshing.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutionAndroidRefreshing = driver.findElements(By.xpath(listDeviceResolutionAndroidRefreshing))
		
		listDeviceResolutioniOSRebooting = deviceResolutionListiOSRebooting.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutioniOSRebooting = driver.findElements(By.xpath(listDeviceResolutioniOSRebooting))

		listDeviceResolutionAndroidRebooting = deviceResolutionListAndroidRebooting.findPropertyValue('xpath').toString()
		List<WebElement> listOfDevicesResolutionAndroidRebooting = driver.findElements(By.xpath(listDeviceResolutionAndroidRebooting))


		//iOS Ready Devices check
		if(readyiOSCount > 0){

						for (WebElement webElement : listOfDevicesNameiOSReady) {
							String deviceNameiOSReadyListText = webElement.getText();
							MesmerLogUtils.logInfo("Ready iOS Device Name " + " : " + "  "  +  deviceNameiOSReadyListText)
						}

//			for (WebElement webElement : listOfDevicesPlatformiOSReady) {
//				String devicePlatformiOSReadyListText = webElement.getText();
//				MesmerLogUtils.logInfo("Ready iOS Device OS " + " : " + "  "  +  devicePlatformiOSReadyListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeiOSReady) {
//				MesmerLogUtils.logInfo("Ready iOS devices is Virtual ")
//			}

			//			for (WebElement webElement : listOfDevicesResolutioniOSReady) {
			//				String deviceResolutioniOSReadyListText = webElement.getText();
			//				MesmerLogUtils.logInfo("Ready iOS Device Resolution " + " : " + "  "  +  deviceResolutioniOSReadyListText)
			//			}

		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in Ready state")
		}

		//Android Ready Devices check
		if(readyAndroidCount > 0){

						for (WebElement webElement : listOfDevicesNameAndroidReady) {
							String deviceNameAndroidReadyListText = webElement.getText();
							MesmerLogUtils.logInfo("Ready Android Device Name " + " : " + "  "  +  deviceNameAndroidReadyListText)
						}

//			for (WebElement webElement : listOfDevicesPlatformAndroidReady) {
//				String devicePlatformAndroidReadyListText = webElement.getText();
//				MesmerLogUtils.logInfo("Ready Android Device OS " + " : " + "  "  +  devicePlatformAndroidReadyListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeAndroidReady) {
//				MesmerLogUtils.logInfo("Ready Android devices is Virtual ")
//			}

			//			for (WebElement webElement : listOfDevicesResolutionAndroidReady) {
			//				String deviceResolutionAndroidReadyListText = webElement.getText();
			//				MesmerLogUtils.logInfo("Ready Android Device Resolution " + " : " + "  "  +  deviceResolutionAndroidReadyListText)
			//			}
		}
		else{
			MesmerLogUtils.markWarning("No Android device is in Ready state")
		}

		//iOS In-Use Devices check
		if(inUseiOSCount > 0){
			
			for (WebElement webElement : listOfDevicesNameiOSInUse) {
				String deviceNameiOSInUseListText = webElement.getText();
				MesmerLogUtils.logInfo("InUse iOS Device Name " + " : " + "  "  +  deviceNameiOSInUseListText)
			}

//			for (WebElement webElement : listOfDevicesPlatformiOSInUse) {
//				String devicePlatformiOSInUseListText = webElement.getText();
//				MesmerLogUtils.logInfo("Ready iOS Device OS " + " : " + "  "  +  devicePlatformiOSInUseListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeiOSInUse) {
//				MesmerLogUtils.logInfo("Ready iOS devices is Virtual ")
//			}

		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in In-Use state")
		}

		//Android In-Use Devices check
		if(inUseAndroidCount > 0){

			for (WebElement webElement : listOfDevicesNameAndroidInUse) {
				String deviceNameAndroidInUseListText = webElement.getText();
				MesmerLogUtils.logInfo("InUse Android Device Name " + " : " + "  "  +  deviceNameAndroidInUseListText)
			}

//			for (WebElement webElement : listOfDevicesPlatformAndroidInUse) {
//				String devicePlatformAndroidInUseListText = webElement.getText();
//				MesmerLogUtils.logInfo("InUse Android Device OS " + " : " + "  "  +  devicePlatformAndroidInUseListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeAndroidReady) {
//				MesmerLogUtils.logInfo("InUse Android devices is Virtual ")
//			}

		}
		else{
			MesmerLogUtils.markWarning("No Android device is in In-Use state")
		}

		//iOS Provisioned Devices check
		if(provisionediOSCount > 0){


						for (WebElement webElement : listOfDevicesNameiOSProv) {
							String deviceNameiOSProvListText = webElement.getText();
							MesmerLogUtils.logInfo("Provisioned iOS Device Name " + " : " + "  "  +  deviceNameiOSProvListText)
						}

//			for (WebElement webElement : listOfDevicesPlatformiOSProv) {
//				String devicePlatformiOSProvListText = webElement.getText();
//				MesmerLogUtils.logInfo("Provisioned iOS Device OS " + " : " + "  "  +  devicePlatformiOSProvListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeiOSProv) {
//				MesmerLogUtils.logInfo("Provisioned iOS devices is Virtual ")
//			}

			//			for (WebElement webElement : listOfDevicesResolutioniOSProv) {
			//				String deviceResolutioniOSProvListText = webElement.getText();
			//				MesmerLogUtils.logInfo("Provisioned iOS Device Resolution " + " : " + "  "  +  deviceResolutioniOSProvListText)
			//			}
		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in Provisioned state")
		}

		//Android Provisioned Devices check
		if(provisionedAndroidCount > 0){

						for (WebElement webElement : listOfDevicesNameAndroidProv) {
							String deviceNameAndroidProvListText = webElement.getText();
							MesmerLogUtils.logInfo("Provisioned Android Device Name " + " : " + "  "  +  deviceNameAndroidProvListText)
						}

//			for (WebElement webElement : listOfDevicesPlatformAndroidProv) {
//				String devicePlatformAndroidProvListText = webElement.getText();
//				MesmerLogUtils.logInfo("Provisioned Android Device OS " + " : " + "  "  +  devicePlatformAndroidProvListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeAndroidProv) {
//				MesmerLogUtils.logInfo("Provisioned Android devices is Virtual ")
//			}

			//			for (WebElement webElement : listOfDevicesResolutionAndroidProv) {
			//				String deviceResolutionAndroidProvListText = webElement.getText();
			//				MesmerLogUtils.logInfo("Provisioned Android Device Resolution " + " : " + "  "  +  deviceResolutionAndroidProvListText)
			//			}
		}
		else{
			MesmerLogUtils.markWarning("No Android device is in Provisioned state")
		}

		//iOS Broken Devices check
		if(brokeniOSCount > 0){
			
			for (WebElement webElement : listOfDevicesNameiOSBroken) {
				String deviceNameiOSBrokenListText = webElement.getText();
				MesmerLogUtils.logInfo("Broken iOS Device Name " + " : " + "  "  +  deviceNameiOSBrokenListText)
			}
//			for (WebElement webElement : listOfDevicesPlatformiOSBroken) {
//				String devicePlatformiOSBrokenListText = webElement.getText();
//				MesmerLogUtils.logInfo("Broken iOS Device OS " + " : " + "  "  +  devicePlatformiOSBrokenListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeiOSBroken) {
//				MesmerLogUtils.logInfo("Broken iOS devices is Virtual ")
//			}

		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in Broken state")
		}

		//Android Broken Devices check
		if(brokenAndroidCount > 0){
			
			
			for (WebElement webElement : listOfDevicesNameAndroidBroken) {
				String deviceNameAndroidBrokenListText = webElement.getText();
				MesmerLogUtils.logInfo("Broken Android Device Name " + " : " + "  "  +  deviceNameAndroidBrokenListText)
			}
//			for (WebElement webElement : listOfDevicesPlatformAndroidBroken) {
//				String devicePlatformAndroidBrokenListText = webElement.getText();
//				MesmerLogUtils.logInfo("Broken Android Device OS " + " : " + "  "  +  devicePlatformAndroidBrokenListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeAndroidBroken) {
//				MesmerLogUtils.logInfo("Broken Android devices is Virtual ")
//			}
		}
		else{
			MesmerLogUtils.markWarning("No Android device is in Broken state")
		}

		//iOS Unavailable Devices check
		if(unavailableiOSCount > 0){
			
			
			for (WebElement webElement : listOfDevicesNameiOSUnavailable) {
				String deviceNameiOSUnavailableListText = webElement.getText();
				MesmerLogUtils.logInfo("Unavailable iOS Device Name " + " : " + "  "  +  deviceNameiOSUnavailableListText)
			}
//			for (WebElement webElement : listOfDevicesPlatformiOSUnavailable) {
//				String devicePlatformiOSUnavailableListText = webElement.getText();
//				MesmerLogUtils.logInfo("Unavailable iOS Device OS " + " : " + "  "  +  devicePlatformiOSUnavailableListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeiOSUnavailable) {
//				MesmerLogUtils.logInfo("Unavailable iOS devices is Virtual ")
//			}
		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in Unavailable state")
		}

		//Android Unavailable Devices check
		if(unavailableAndroidCount > 0){
			
			for (WebElement webElement : listOfDevicesNameAndroidUnavailable) {
				String deviceNameAndroidUnavailableListText = webElement.getText();
				MesmerLogUtils.logInfo("Unavailable Android Device Name " + " : " + "  "  +  deviceNameAndroidUnavailableListText)
			}
//			for (WebElement webElement : listOfDevicesPlatformAndroidUnavailable) {
//				String devicePlatformAndroidUnavailableListText = webElement.getText();
//				MesmerLogUtils.logInfo("Unavailable Android Device OS " + " : " + "  "  +  devicePlatformAndroidUnavailableListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeAndroidUnavailable) {
//				MesmerLogUtils.logInfo("Unavailable Android devices is Virtual ")
//			}
		}
		else{
			MesmerLogUtils.markWarning("No Android device is in Unavailable state")
		}

		//iOS Configuring Devices check
		if(configuringiOSCount > 0){
			for (WebElement webElement : listOfDevicesNameiOSConfiguring) {
				String deviceNameiOConfiguringListText = webElement.getText();
				MesmerLogUtils.logInfo("Configuring iOS Device Name " + " : " + "  "  +  deviceNameiOConfiguringListText)
			}
		
//			for (WebElement webElement : listOfDevicesPlatformiOSConfiguring) {
//				String devicePlatformiOSConfiguringListText = webElement.getText();
//				MesmerLogUtils.logInfo("Configuring iOS Device OS " + " : " + "  "  +  devicePlatformiOSConfiguringListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeiOSConfiguring) {
//				MesmerLogUtils.logInfo("Configuring iOS devices is Virtual ")
//			}
		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in Configuring state")
		}

		//Android Configuring Devices check
		if(configuringAndroidCount > 0){
			
			
			for (WebElement webElement : listOfDevicesNameAndroidConfiguring) {
				String deviceNameAndroidConfiguringListText = webElement.getText();
				MesmerLogUtils.logInfo("Configuring Android Device Name " + " : " + "  "  +  deviceNameAndroidConfiguringListText)
			}
//			for (WebElement webElement : listOfDevicesPlatformAndroidConfiguring) {
//				String devicePlatformAndroidConfiguringListText = webElement.getText();
//				MesmerLogUtils.logInfo("Configuring Android Device OS " + " : " + "  "  +  devicePlatformAndroidConfiguringListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeAndroidConfiguring) {
//				MesmerLogUtils.logInfo("Configuring Android devices is Virtual ")
//			}
		}
		else{
			MesmerLogUtils.markWarning("No Android device is in Configuring state")
		}

		//iOS Refreshing Devices check
		if(refreshingiOSCount > 0){
			
			for (WebElement webElement : listOfDevicesNameiOSRefreshing) {
				String deviceNameiORefreshingListText = webElement.getText();
				MesmerLogUtils.logInfo("Refreshing iOS Device Name " + " : " + "  "  +  deviceNameiORefreshingListText)
			}
//			for (WebElement webElement : listOfDevicesPlatformiOSRefreshing) {
//				String devicePlatformiOSRefreshingListText = webElement.getText();
//				MesmerLogUtils.logInfo("Refreshing iOS Device OS " + " : " + "  "  +  devicePlatformiOSRefreshingListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeiOSRefreshing) {
//				MesmerLogUtils.logInfo("Refreshing iOS devices is Virtual ")
//			}
		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in Refreshing state")
		}

		//Android Refreshing Devices check
		if(refreshingAndroidCount > 0){
			
			for (WebElement webElement : listOfDevicesNameAndroidRefreshing) {
				String deviceNameAndroidRefreshingListText = webElement.getText();
				MesmerLogUtils.logInfo("Refreshing Android Device Name " + " : " + "  "  +  deviceNameAndroidRefreshingListText)
			}
//			for (WebElement webElement : listOfDevicesPlatformAndroidRefreshing) {
//				String devicePlatformAndroidRefreshingListText = webElement.getText();
//				MesmerLogUtils.logInfo("Refreshing Android Device OS " + " : " + "  "  +  devicePlatformAndroidRefreshingListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeAndroidRefreshing) {
//				MesmerLogUtils.logInfo("Refreshing Android devices is Virtual ")
//			}
		}
		else{
			MesmerLogUtils.markWarning("No Android device is in Refreshing state")
		}

		//iOS Rebooting Devices check
		if(rebootingiOSCount > 0){
			
			for (WebElement webElement : listOfDevicesNameiOSRebooting) {
				String deviceNameiORebootingListText = webElement.getText();
				MesmerLogUtils.logInfo("Rebooting iOS Device Name " + " : " + "  "  +  deviceNameiORebootingListText)
			}
//			for (WebElement webElement : listOfDevicesPlatformiOSRebooting) {
//				String devicePlatformiOSRebootingListText = webElement.getText();
//				MesmerLogUtils.logInfo("Rebooting iOS Device OS " + " : " + "  "  +  devicePlatformiOSRebootingListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeiOSRebooting) {
//				MesmerLogUtils.logInfo("Rebooting iOS devices is Virtual ")
//			}
		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in Rebooting state")
		}

		//Android Rebooting Devices check
		if(rebootingAndroidCount > 0){
			
			for (WebElement webElement : listOfDevicesNameAndroidRebooting) {
				String deviceNameAndroidRebootingListText = webElement.getText();
				MesmerLogUtils.logInfo("Rebooting Android Device Name " + " : " + "  "  +  deviceNameAndroidRebootingListText)
			}
//			for (WebElement webElement : listOfDevicesPlatformAndroidRebooting) {
//				String devicePlatformAndroidRebootingListText = webElement.getText();
//				MesmerLogUtils.logInfo("Rebooting Android Device OS " + " : " + "  "  +  devicePlatformAndroidRebootingListText)
//			}
//
//			for (WebElement webElement : listOfDevicesTypeAndroidRebooting) {
//				MesmerLogUtils.logInfo("Rebooting Android devices is Virtual ")
//			}
		}
		else{
			MesmerLogUtils.markWarning("No Android device is in Rebooting state")
		}


	}

}

