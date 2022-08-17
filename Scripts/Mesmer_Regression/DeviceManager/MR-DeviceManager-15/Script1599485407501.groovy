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



	String statusInUseXpath = findTestObject('Object Repository/OR_DeviceManager/device_inUseStatus').findPropertyValue('xpath').toString()
	List<WebElement> statusInUse = driver.findElements(By.xpath(statusInUseXpath))

	MesmerLogUtils.logInfo("Number of Devices with InUse Status  : " +  " " + statusInUse.size().toString())

	if (statusInUse.size() > 0) {

		String statusInUseRebootBtnXpath = findTestObject('Object Repository/OR_DeviceManager/btn_RebootInUseEnabled').findPropertyValue('xpath').toString()
		List<WebElement> btnInUseReboot = driver.findElements(By.xpath(statusInUseRebootBtnXpath))

		btnInUseReboot.get(0).click()

		if(WebUI.waitForElementPresent(confirmationDialogueReboot , 10)){

			MesmerLogUtils.markPassed("Confirmation dialogue appears for InUse device reboot")
		}else{
			MesmerLogUtils.markFailed("No confirmation dialogue appears for InUse device reboot")
		}

	}else {
	MesmerLogUtils.markWarning("No InUse device available")
	}

}catch(Exception e){
	e.printStackTrace()
}
finally{
if(WebUI.waitForElementPresent(btnClose, 10)){
	WebUI.click(btnClose)
	}else{
	WebUI.refresh()
	}
}
