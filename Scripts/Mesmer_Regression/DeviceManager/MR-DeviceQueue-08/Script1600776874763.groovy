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

// MR-DeviceQueue-08 | Verify user should be able to stop/delete/clear the in-progress task by hover over the task in 'Current Task' section and clicking the cross (x) icon

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

WebDriver driver = DriverFactory.getWebDriver()
try{

	if(DeviceManagerController.getInstance().clickOnEyeIcon()){
		if(DeviceManagerController.getInstance().verifyDeviceQueueDialogue()){
			if(DeviceManagerController.getInstance().deviceQueueCurrentTask()){
				
				if(DeviceManagerController.getInstance().removeRunningTask()){
					WebUI.delay(2)
					if(DeviceManagerController.getInstance().confirmationToRemoveSingleTest()){
						WebUI.delay(2)
						if(DeviceManagerController.getInstance().confirmationToRemoveSingleTestCancel()){
							WebUI.delay(2)
							//Repeat step 3 and 4
							if(DeviceManagerController.getInstance().removeRunningTask()){
								WebUI.delay(2)
								if(DeviceManagerController.getInstance().confirmationToRemoveSingleTest()){
									WebUI.delay(2)
									if(DeviceManagerController.getInstance().confirmationToRemoveSingleTestYes()){
										WebUI.delay(2)
									}else{
										MesmerLogUtils.logInfo("confirmationToRemoveSingleTestYes method failed")
									}
								}else{
									MesmerLogUtils.logInfo("confirmationToRemoveSingleTest step 3 method failed")
								}
							}else{
								MesmerLogUtils.logInfo("removeRunningTask step 3 method failed")
							}
						}else{
							MesmerLogUtils.logInfo("confirmationToRemoveSingleTestCancel method failed")
						}
					}else{
						MesmerLogUtils.logInfo("confirmationToRemoveSingleTest method failed")
					}
				}else{
					MesmerLogUtils.logInfo("removeRunningTask method failed")
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
		MesmerLogUtils.markFailed("Could not close device queue dialogue")
	}
}