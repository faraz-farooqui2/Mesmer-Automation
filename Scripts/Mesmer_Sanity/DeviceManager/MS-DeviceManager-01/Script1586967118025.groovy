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

//MS-Device Manager-01 | Verify that user is seeing all devices in Device Manager if nodes are up and devices are working fine.
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

try{
	CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
	WebDriver driver = DriverFactory.getWebDriver()
	
	
	if(WebUI.waitForElementVisible(allDevicesOptionActive, 2)==false){
		MesmerLogUtils.logInfo("All Devices option is not selected")
		MesmerLogUtils.logInfo("Clicking All devices option")
		WebUI.click(allDevicesOptionActive)
		MesmerLogUtils.logInfo("All devices option is selected")
		
	}
	else{
		MesmerLogUtils.markFailed("All devices option is selected")
		
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
		
		//iOS Ready Devices check
		if(readyiOSCount > 0){
			MesmerLogUtils.markPassed("Total -> "+ readyiOSCount + " iOS devices available in Ready state")
		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in Ready state")
		}
		
		//Android Ready Devices check
		if(readyAndroidCount > 0){
			MesmerLogUtils.markPassed("Total -> "+ readyAndroidCount + " Android devices available in Ready state")
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
			MesmerLogUtils.markPassed("Total -> "+ provisionediOSCount + " iOS devices available in Provisioned state")
		}
		else{
			MesmerLogUtils.markWarning("No iOS device is in Provisioned state")
		}
		
		//Android Provisioned Devices check
		if(provisionedAndroidCount > 0){
			MesmerLogUtils.markPassed("Total -> "+ provisionedAndroidCount + " Android devices available in Provisioned state")
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
			MesmerLogUtils.markWarning("No Android device is in Broken state")
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

	
}catch(Exception e){
	println(e.stackTrace)
}
finally{
		WebUI.refresh()
}
	
	
		

