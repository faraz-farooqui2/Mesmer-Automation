import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

// MR-Device Manager-29 | Verify that Virtual / Physical device should not stuck in BROKEN state
CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebDriver driver = DriverFactory.getWebDriver()

try{

	String statusBrokenXpath = findTestObject('Object Repository/OR_DeviceManager/device_brokenStatus').findPropertyValue('xpath').toString()
	List <WebElement> statusBroken = driver.findElements(By.xpath(statusBrokenXpath))

	if (statusBroken != null && statusBroken.size() > 0){
		for (int i = 0 ; i < statusBroken.size(); i++){
			WebElement element = statusBroken.get(i)
			TestObject	statusBrokenObj	= WebUI.convertWebElementToTestObject(element)

			if(WebUI.waitForElementNotPresent(statusBrokenObj, 120)){
				MesmerLogUtils.markPassed("Broken device recovered")
				break;
			}else{
				MesmerLogUtils.markFailed("Broken device not reconditioned in 2 minutes")
			}
		}
	}else{
		MesmerLogUtils.markWarning("Could not find broken device on Device Manager")
	}


}catch(Exception e){
	e.printStackTrace()
}
finally{

}
