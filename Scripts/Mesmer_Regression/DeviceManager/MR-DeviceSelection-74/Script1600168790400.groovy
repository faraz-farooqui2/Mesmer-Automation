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
 * MR-Device Selection-74 | Record | Verify selected device should have a tick mark against it
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

		String readyDeviceXpath = findTestObject('Object Repository/OR_DeviceCertification/record_listDevicesReady').findPropertyValue('xpath').toString()
		List <WebElement> readyDevice = driver.findElements(By.xpath(readyDeviceXpath))



		if (readyDevice != null && readyDevice.size() > 0){
			readyDevice.get(0).click()
			if(WebUI.waitForElementPresent(readyDeviceSelected , 10)){
				MesmerLogUtils.markPassed("Device selected")
			}else{
			MesmerLogUtils.markFailed("Device not selected")
			}
		}else{
			MesmerLogUtils.markWarning("No Physical ready device available in record")
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
