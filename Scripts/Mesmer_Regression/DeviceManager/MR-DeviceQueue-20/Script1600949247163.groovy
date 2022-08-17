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

// MR-DeviceQueue | Verify in queue tets cases should appear in Device Queue modal,  when device gets unavailable during crawl

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

WebDriver driver = DriverFactory.getWebDriver()
try{

	if(DeviceManagerController.getInstance().clickOnEyeIcon()){
		if(DeviceManagerController.getInstance().verifyDeviceQueueDialogue()){

			if(DeviceManagerController.getInstance().deviceQueueTask()){
				WebUI.delay(2)

			}else{
				MesmerLogUtils.markFailed("deviceQueueTask method failed")
			}
		}else{
			MesmerLogUtils.markFailed("verifyDeviceQueueDialogue method failed")
		}
	}else{
		MesmerLogUtils.markFailed("clickOnEyeIcon method failed")
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
	if(DeviceManagerController.getInstance().clickOnCloseBtn()){
	}else{
		MesmerLogUtils.markFailed("Could not close TASKS AWAITING A DEVICE DIALOGUE")
	}
}