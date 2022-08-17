import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
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
 * MR-Device Selection-68 | Record | Verify user should not be able to select an already in use device
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

		WebUI.delay(4)



		if(WebUI.waitForElementPresent(inUseDisable, 10)){

			String inUseDeviceDisabledXpath = findTestObject('Object Repository/OR_DeviceCertification/record_InUseDeviceDisabled').findPropertyValue('xpath').toString()
			WebElement inUseDevice = driver.findElement(By.xpath(inUseDeviceDisabledXpath))
			if(inUseDevice.getAttribute("class").contains("disabled")){
				MesmerLogUtils.markPassed("In Use device is not selectable")
			}else{
				MesmerLogUtils.markFailed("In Use device is selectable")
			}
		}else{
			MesmerLogUtils.logInfo("No in use device found")
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
