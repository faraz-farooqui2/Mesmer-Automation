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
 * MR-Device Selection-78 | Record | Verify device should reflect a message "Incompatible with build" if device is incompatible with the build due to any reason
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

		String incompatibleDeviceXpath = findTestObject('Object Repository/OR_DeviceCertification/record_incompatibleDevice').findPropertyValue('xpath').toString()
		List <WebElement> incompatibleDevice = driver.findElements(By.xpath(incompatibleDeviceXpath))



		if (incompatibleDevice != null && incompatibleDevice.size() > 0){
			for (WebElement webElement : incompatibleDevice) {
				String incompatibleDeviceListText = webElement.getText();

				MesmerLogUtils.markPassed("Device " + " : " + "  "  +  incompatibleDeviceListText )
				break
			}
		}else{
			MesmerLogUtils.markWarning("No incompatible device found")
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
