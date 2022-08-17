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


// MR-DeviceManager-06 | Verify user should be able to delete a single Queue Task

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

WebUI.delay(10)
try{
	if(DeviceManagerController.getInstance().clickOnTasksAwaitingDeviceBtn()){
		if(DeviceManagerController.getInstance().verifyTasksAwaitingADeviceDialogue()){
			WebUI.delay(5)
			if(DeviceManagerController.getInstance().contentOfQueueTable()){
				WebUI.delay(5)
				if(DeviceManagerController.getInstance().removeSingleTestFromQueue()){
					WebUI.delay(2)
					if(DeviceManagerController.getInstance().confirmationToRemoveSingleTest()){
						WebUI.delay(2)
						if(DeviceManagerController.getInstance().confirmationToRemoveSingleTestYes()){
							WebUI.delay(2)
							//Repeat step 3 and 4
							if(DeviceManagerController.getInstance().removeSingleTestFromQueue()){
								WebUI.delay(2)
								if(DeviceManagerController.getInstance().confirmationToRemoveSingleTest()){
									WebUI.delay(2)
									if(DeviceManagerController.getInstance().confirmationToRemoveSingleTestCancel()){
										WebUI.delay(2)
									}else{
										MesmerLogUtils.logInfo("confirmationToRemoveSingleTestCancel method failed")
									}
								}else{
									MesmerLogUtils.logInfo("confirmationToRemoveSingleTest step 3 method failed")
								}
							}else{
								MesmerLogUtils.logInfo("removeSingleTestFromQueue step 3 method failed")
							}
						}else{
							MesmerLogUtils.logInfo("confirmationToRemoveSingleTestYes method failed")
						}
					}else{
						MesmerLogUtils.logInfo("confirmationToRemoveSingleTest method failed")
					}
				}else{
					MesmerLogUtils.logInfo("removeSingleTestFromQueue method failed")
				}
			}else{
				MesmerLogUtils.logInfo("contentOfQueueTable method failed")
			}
		}else{
			MesmerLogUtils.markFailed("Tasks awaiting dialogue not opened")
		}
	}else{
		MesmerLogUtils.markFailed("Could not click on task awaiting button")
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