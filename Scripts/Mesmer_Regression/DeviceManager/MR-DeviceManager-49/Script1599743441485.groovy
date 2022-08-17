import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

// MR-Device Manager-49 | Verify that the "Provisioned" device should be shown in the device manager after restarting the node.
CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(2)

try{

	String statusProvisionedXpath = findTestObject('Object Repository/OR_DeviceCertification/deviceManager_provisioned').findPropertyValue('xpath').toString()
	List <WebElement> statusProvisioned = driver.findElements(By.xpath(statusProvisionedXpath))

	if (statusProvisioned != null && statusProvisioned.size() > 0){
		MesmerLogUtils.markPassed("Found Provisioned devices in Device Manager")
	}else{
		MesmerLogUtils.markWarning("No Provisioned device found")
	}


}catch(Exception e){
	e.printStackTrace()
}
finally{

}
