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

// MR-Device Manager-07 | Verify user name and activity performed on device is showing next to the in use device icon
CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(2)

try{

	String inUseDevicesXpath = findTestObject('Object Repository/OR_DeviceManager/device_refreshingStatus').findPropertyValue('xpath').toString()
	List<WebElement> inUseDevices = driver.findElements(By.xpath(inUseDevicesXpath))

	String inUseDevicesCount = inUseDevices.size()

	if(inUseDevicesCount > 0){
		String activityStatusXpath = findTestObject('Object Repository/OR_DeviceManager/list_activityStatus').findPropertyValue('xpath').toString()
		List<WebElement> activityStatus = driver.findElements(By.xpath(activityStatusXpath))

		String activityPerformedByUserXpath = findTestObject('Object Repository/OR_DeviceManager/list_activityPerformedByUser').findPropertyValue('xpath').toString()
		List<WebElement> activityPerformedByUser = driver.findElements(By.xpath(activityPerformedByUserXpath))

		for (WebElement webElement : activityStatus) {
			String activityStatusText = webElement.getText();
			MesmerLogUtils.logInfo("Activity Status is" + " : " + activityStatusText )
		}

		for (WebElement webElement : activityPerformedByUser) {
			String activityPerformedByUserText = webElement.getText();
			MesmerLogUtils.logInfo("Activity performed by " + " : " + activityPerformedByUserText)
		}


	}else{
		MesmerLogUtils.logInfo("No device is in use  : " +  " " + inUseDevicesCount.toString())
	}



}catch(Exception e){
	e.printStackTrace()
}
finally{

}
