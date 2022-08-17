import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.DeviceManagerController

// MR-DeviceManager-01 | Verify a new column 'Queue' is added in the device manager page

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	if(DeviceManagerController.getInstance().selectTestCaseFromNew()){
		if(DeviceManagerController.getInstance().runTestCase()){

			if(CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()){

				if(DeviceManagerController.getInstance().verifyTestsQueueCount()){
				}else{
					MesmerLogUtils.markFailed("Verify Test Queue Count method failed")
				}
			}else{
				MesmerLogUtils.markFailed("Go to Device Manager method failed")
			}
		}else{
			MesmerLogUtils.markFailed("Run Test Case method failed")
		}
	}else{
		MesmerLogUtils.markFailed("Select Test Case From New method failed")
	}
}
catch(Exception e){
	e.printStackTrace()
}