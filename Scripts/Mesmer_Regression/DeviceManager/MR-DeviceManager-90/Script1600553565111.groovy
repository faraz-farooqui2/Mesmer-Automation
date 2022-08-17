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


// MR-DeviceManager-04 | Verify clicking 'Task Awaiting Device' link should show the list of Queued tasks

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

WebUI.delay(10)
try{
	if(DeviceManagerController.getInstance().clickOnTasksAwaitingDeviceBtn()){
		if(DeviceManagerController.getInstance().verifyTasksAwaitingADeviceDialogue()){
			WebUI.delay(5)
			if(DeviceManagerController.getInstance().clickOnTasksAwaitingDeviceReplayFilter()){
				WebUI.delay(5)
				if(DeviceManagerController.getInstance().verifyQueueInTaskAwaitingADevice()){
					WebUI.delay(5)
					if(DeviceManagerController.getInstance().clickOnTasksAwaitingDeviceCrawlFilter()){
						WebUI.delay(5)
						if(DeviceManagerController.getInstance().verifyQueueInTaskAwaitingADevice()){
						}else{
							MesmerLogUtils.markFailed("verifyQueueInTaskAwaitingADevice-Crawl method failed")
						}
					}else{
						MesmerLogUtils.markFailed("clickOnTasksAwaitingDeviceCrawlFilter method failed")
					}
				}else{
					MesmerLogUtils.markFailed("verifyQueueInTaskAwaitingADevice method failed")
				}
			}else{
				MesmerLogUtils.markFailed("clickOnTasksAwaitingDeviceReplayFilter method failed")
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