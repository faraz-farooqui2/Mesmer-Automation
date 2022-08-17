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

//List Devices
//Test Scope
// Device Manager

WebDriver driver = DriverFactory.getWebDriver()
try{
	//Utility.setPlatformName(platformName)
	//MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
	if(CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()){

		WebUI.delay(15)

		if(WebUI.waitForElementVisible(allDevicesOptionActive, 5)==false){
			MesmerLogUtils.logInfo("All Devices option is not selected")
			MesmerLogUtils.logInfo("Clicking All devices option")
			WebUI.click(allDevicesOptionActive)
			MesmerLogUtils.logInfo("All devices option is selected")

		}
		else{
			MesmerLogUtils.logInfo("All devices option is selected")

			WebUI.waitForPageLoad(30)

			listReadyiOS = readyIOSList.findPropertyValue('xpath').toString()
			List<WebElement> listOfReadyIOSDevices = driver.findElements(By.xpath(listReadyiOS))

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


			//		Count Devices
			readyiOSCount = listOfReadyIOSDevices.size()
			readyAndroidCount = listOfReadyAndroidDevices.size()
			inUseiOSCount = listOfinUseiOSDevices.size()
			inUseAndroidCount = listOfinUseAndroidDevices.size()
			provisionediOSCount = listOfProvisionediOSDevices.size()
			provisionedAndroidCount = listOfProvisionedAndroidDevices.size()
			brokeniOSCount = listOfBrokeniOSDevices.size()
			brokenAndroidCount = listOfBrokenAndroidDevices.size()
			unavailableiOSCount = listOfUnavailableiOSDevices.size()
			unavailableAndroidCount = listOfUnavailableAndroidDevices.size()

			// Device Name

			listDeviceNameiOSReady = deviceNameListiOSReady.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesNameiOSReady = driver.findElements(By.xpath(listDeviceNameiOSReady))

			listDeviceNameAndroidReady = deviceNameListAndroidReady.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesNameAndroidReady = driver.findElements(By.xpath(listDeviceNameAndroidReady))

			listDeviceNameiOSProv = deviceNameListiOSProv.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesNameiOSProv = driver.findElements(By.xpath(listDeviceNameiOSProv))

			listDeviceNameAndroidProv = deviceNameListAndroidProv.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesNameAndroidProv = driver.findElements(By.xpath(listDeviceNameAndroidProv))


			// Device Platform

			listDevicePlatformiOSReady = devicePlatformListiOSReady.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesPlatformiOSReady = driver.findElements(By.xpath(listDevicePlatformiOSReady))

			listDevicePlatformAndroidReady = devicePlatformListAndroidReady.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesPlatformAndroidReady = driver.findElements(By.xpath(listDevicePlatformAndroidReady))

			listDevicePlatformiOSProv = devicePlatformListiOSProv.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesPlatformiOSProv = driver.findElements(By.xpath(listDevicePlatformiOSProv))

			listDeviceNameAndroidProv = devicePlatformListAndroidProv.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesPlatformAndroidProv = driver.findElements(By.xpath(listDeviceNameAndroidProv))


			// Device Type

			listDeviceTypeiOSReady = deviceTypeListiOSReady.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesTypeiOSReady = driver.findElements(By.xpath(listDeviceTypeiOSReady))

			listDeviceTypeAndroidReady = deviceTypeListAndroidReady.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesTypeAndroidReady = driver.findElements(By.xpath(listDeviceTypeAndroidReady))

			listDeviceTypeiOSProv = deviceTypeListiOSProv.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesTypeiOSProv = driver.findElements(By.xpath(listDeviceTypeiOSProv))

			listDeviceTypeAndroidProv = deviceTypeListAndroidProv.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesTypeAndroidProv = driver.findElements(By.xpath(listDeviceTypeAndroidProv))

			// Device Resolution

			listDeviceResolutioniOSReady = deviceResolutionListiOSReady.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesResolutioniOSReady = driver.findElements(By.xpath(listDeviceResolutioniOSReady))

			listDeviceResolutionAndroidReady = deviceResolutionListAndroidReady.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesResolutionAndroidReady = driver.findElements(By.xpath(listDeviceResolutionAndroidReady))

			listDeviceResolutioniOSProv = deviceResolutionListiOSProv.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesResolutioniOSProv = driver.findElements(By.xpath(listDeviceResolutioniOSProv))

			listDeviceResolutionAndroidProv = deviceResolutionListAndroidProv.findPropertyValue('xpath').toString()
			List<WebElement> listOfDevicesResolutionAndroidProv = driver.findElements(By.xpath(listDeviceResolutionAndroidProv))


			//iOS Ready Devices check
			if(readyiOSCount > 0){

				for (WebElement webElement : listOfDevicesNameiOSReady) {
					String deviceNameiOSReadyListText = webElement.getText();
					MesmerLogUtils.logInfo("Ready iOS Device Name " + " : " + "  "  +  deviceNameiOSReadyListText)
				}

				for (WebElement webElement : listOfDevicesPlatformiOSReady) {
					String devicePlatformiOSReadyListText = webElement.getText();
					MesmerLogUtils.logInfo("Ready iOS Device OS " + " : " + "  "  +  devicePlatformiOSReadyListText)
				}

				for (WebElement webElement : listOfDevicesTypeiOSReady) {
					MesmerLogUtils.logInfo("Ready iOS devices is Virtual ")
				}

				for (WebElement webElement : listOfDevicesResolutioniOSReady) {
					String deviceResolutioniOSReadyListText = webElement.getText();
					MesmerLogUtils.logInfo("Ready iOS Device Resolution " + " : " + "  "  +  deviceResolutioniOSReadyListText)
				}

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

				for (WebElement webElement : listOfDevicesPlatformAndroidReady) {
					String devicePlatformAndroidReadyListText = webElement.getText();
					MesmerLogUtils.logInfo("Ready Android Device OS " + " : " + "  "  +  devicePlatformAndroidReadyListText)
				}

				for (WebElement webElement : listOfDevicesTypeAndroidReady) {
					MesmerLogUtils.logInfo("Ready Android devices is Virtual ")
				}

				for (WebElement webElement : listOfDevicesResolutionAndroidReady) {
					String deviceResolutionAndroidReadyListText = webElement.getText();
					MesmerLogUtils.logInfo("Ready Android Device Resolution " + " : " + "  "  +  deviceResolutionAndroidReadyListText)
				}
			}
			else{
				MesmerLogUtils.markWarning("No Android device is in Ready state")
			}

			//iOS In-Use Devices check
			if(inUseiOSCount > 0){
				MesmerLogUtils.markPassed("Total -> "+ inUseiOSCount + " iOS devices available in In-Use state")

			}
			else{
				MesmerLogUtils.markWarning("No iOS device is in In-Use state")
			}

			//Android In-Use Devices check
			if(inUseAndroidCount > 0){
				MesmerLogUtils.markPassed("Total -> "+ inUseAndroidCount + " Android devices available in In-Use state")

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

				for (WebElement webElement : listOfDevicesPlatformiOSProv) {
					String devicePlatformiOSProvListText = webElement.getText();
					MesmerLogUtils.logInfo("Provisioned iOS Device OS " + " : " + "  "  +  devicePlatformiOSProvListText)
				}

				for (WebElement webElement : listOfDevicesTypeiOSProv) {
					MesmerLogUtils.logInfo("Provisioned iOS devices is Virtual ")
				}

				for (WebElement webElement : listOfDevicesResolutioniOSProv) {
					String deviceResolutioniOSProvListText = webElement.getText();
					MesmerLogUtils.logInfo("Provisioned iOS Device Resolution " + " : " + "  "  +  deviceResolutioniOSProvListText)
				}
			}
			else{
				MesmerLogUtils.markWarning("No iOS device is in Provisioned state")
			}

			//Android Provisioned Devices check
			if(provisionedAndroidCount > 0){
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
					MesmerLogUtils.logInfo("Provisioned Android devices is Virtual ")
				}

				for (WebElement webElement : listOfDevicesResolutionAndroidProv) {
					String deviceResolutionAndroidProvListText = webElement.getText();
					MesmerLogUtils.logInfo("Provisioned Android Device Resolution " + " : " + "  "  +  deviceResolutionAndroidProvListText)
				}
			}
			else{
				MesmerLogUtils.markWarning("No Android device is in Provisioned state")
			}

			//iOS Broken Devices check
			if(brokeniOSCount > 0){
				MesmerLogUtils.markPassed("Total -> "+ brokeniOSCount + " iOS devices available in Broken state")

			}
			else{
				MesmerLogUtils.logInfo("No iOS device is in Broken state")
			}

			//Android Broken Devices check
			if(brokenAndroidCount > 0){
				MesmerLogUtils.markPassed("Total -> "+ brokenAndroidCount + " Android devices available in Broken state")

			}
			else{
				MesmerLogUtils.logInfo("No Android device is in Broken state")
			}

			//iOS Unavailable Devices check
			if(unavailableiOSCount > 0){
				MesmerLogUtils.markWarning("Total -> "+ unavailableiOSCount + " iOS devices available in Unavailable state")
			}
			else{
				MesmerLogUtils.markPassed("No iOS device is in Unavailable state")
			}

			//Android Unavailable Devices check
			if(unavailableAndroidCount > 0){
				MesmerLogUtils.markWarning("Total -> "+ unavailableAndroidCount + " Android devices available in Unavailable state")
			}
			else{
				MesmerLogUtils.markPassed("No Android device is in Unavailable state")
			}
		}
	}else{
		MesmerLogUtils.markFailed("Unable to navigate to device manager page")
	}


}catch(Exception e){
	println(e.stackTrace)
}
finally{

}
