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

// MR-DeviceQueue-02 | Verify Device name and its OS should appear in the top bar with 'Stop and Clear Tasks' button against it

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

WebDriver driver = DriverFactory.getWebDriver()
try{

	if(DeviceManagerController.getInstance().clickOnEyeIcon()){
		if(DeviceManagerController.getInstance().verifyDeviceQueueDialogue()){
			if(DeviceManagerController.getInstance().deviceQueueDeviceOS()){
				WebUI.delay(2)
				if(DeviceManagerController.getInstance().deviceQueueDeviceName()){
					WebUI.delay(2)
					if(DeviceManagerController.getInstance().deviceQueueDeviceType()){
						WebUI.delay(2)
						if(DeviceManagerController.getInstance().deviceQueueStopAndClearTasks()){
							WebUI.delay(2)
							if(DeviceManagerController.getInstance().deviceQueueRebootBtn()){
								WebUI.delay(2)
							}else{
								MesmerLogUtils.markFailed("deviceQueueRebootBtn method failed")
							}
						}else{
							MesmerLogUtils.markFailed("deviceQueueStopAndClearTasks method failed")
						}
					}else{
						MesmerLogUtils.markFailed("deviceQueueDeviceType method failed")
					}
				}else{
					MesmerLogUtils.markFailed("deviceQueueDeviceName method failed")
				}
			}else{
				MesmerLogUtils.markFailed("deviceQueueDeviceOS method failed")
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
		MesmerLogUtils.markFailed("Could not close device queue dialogue")
	}
}