import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController

/*
 * MR-Device Selection-69 | Record | Verify user should be able to see if a device is Ready to be use
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
WebDriver driver = DriverFactory.getWebDriver()
try{
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){

		String readyDeviceXpath = findTestObject('Object Repository/OR_DeviceCertification/record_deviceStatusReady').findPropertyValue('xpath').toString()
		List <WebElement> readyDevice = driver.findElements(By.xpath(readyDeviceXpath))



		if (readyDevice != null && readyDevice.size() > 0){
			for (WebElement webElement : readyDevice) {
				String readyDeviceListText = webElement.getText();

				MesmerLogUtils.markPassed("Device Status is " + " : " + "  "  +  readyDeviceListText )
			}
		}else{
			MesmerLogUtils.markWarning("No in ready device found")
		}


	}
	else{
		MesmerLogUtils.markFailed("CreateNewTest screen did not open within 1 minute")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}
