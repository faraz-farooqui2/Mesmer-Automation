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

// MR-DeviceQueue-03 | Verify clicking 'Stop and Clear Tasks' button should stop the inprogress/Inqueue tasks

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

WebDriver driver = DriverFactory.getWebDriver()
try{

	if(DeviceManagerController.getInstance().clickOnEyeIcon()){
		if(DeviceManagerController.getInstance().verifyDeviceQueueDialogue()){
			if(DeviceManagerController.getInstance().clickOnStopAndClearTasks()){
				WebUI.delay(2)
				if(DeviceManagerController.getInstance().confirmationDialogueClearTasks()){
					WebUI.delay(2)
					if(DeviceManagerController.getInstance().confirmationToRemoveSingleTestCancel()){
						WebUI.delay(2)
						if(DeviceManagerController.getInstance().clickOnStopAndClearTasks()){
							WebUI.delay(2)
							if(DeviceManagerController.getInstance().confirmationToRemoveSingleTestYes()){
								WebUI.delay(2)
								if(DeviceManagerController.getInstance().verifyNothingInQueue()){
									WebUI.delay(2)
									if(DeviceManagerController.getInstance().verifyNoTaskRunning()){
										WebUI.delay(2)
									}else{
										MesmerLogUtils.markFailed("verifyNoTaskRunning method failed")
									}
								}else{
									MesmerLogUtils.markFailed("verifyNothingInQueue method failed")
								}
							}else{
								MesmerLogUtils.markFailed("confirmationToRemoveSingleTestYes method failed")
							}
						}else{
							MesmerLogUtils.markFailed("clickOnStopAndClearTasks method failed")
						}
					}else{
						MesmerLogUtils.markFailed("confirmationToRemoveSingleTestCancel method failed")
					}
				}else{
					MesmerLogUtils.markFailed("confirmationDialogueClearTasks method failed")
				}
			}else{
				MesmerLogUtils.markFailed("clickOnStopAndClearTasks method failed")
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