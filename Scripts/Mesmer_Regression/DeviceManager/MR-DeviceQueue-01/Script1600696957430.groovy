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

// MR-DeviceQueue-01 | Verify Device queue detail page should have section: 'Device detail bar' , 'Current Task', 'Queue' , 'Last Task'

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

WebDriver driver = DriverFactory.getWebDriver()
try{

	if(DeviceManagerController.getInstance().clickOnEyeIcon()){
		if(DeviceManagerController.getInstance().verifyDeviceQueueDialogue()){
			if(DeviceManagerController.getInstance().deviceQueueCurrentTask()){
				WebUI.delay(2)
				if(DeviceManagerController.getInstance().deviceQueueTask()){
					WebUI.delay(2)
					if(DeviceManagerController.getInstance().deviceQueueLastTask()){
						WebUI.delay(2)
					}else{
						MesmerLogUtils.markFailed("deviceQueueLastTask method failed")
					}
				}else{
					MesmerLogUtils.markFailed("deviceQueueTask method failed")
				}
			}else{
				MesmerLogUtils.markFailed("deviceQueueCurrentTask method failed")
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