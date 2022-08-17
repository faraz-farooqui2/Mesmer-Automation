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


// MR-DeviceManager-05 | Verify the content of the Queue table on Task Awaiting Device page

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

WebUI.delay(10)
try{
	if(DeviceManagerController.getInstance().clickOnTasksAwaitingDeviceBtn()){
		if(DeviceManagerController.getInstance().verifyTasksAwaitingADeviceDialogue()){
			WebUI.delay(5)
			if(DeviceManagerController.getInstance().contentOfQueueTable()){
				WebUI.delay(5)
				
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