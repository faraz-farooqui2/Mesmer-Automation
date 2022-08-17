import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController

/*
 * MS-Create a new Test Case-04 | Verify that user can see Device logs during recording.
 */


try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		if(CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()){
		WebUI.delay(2)

		List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())
		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			Utility.selectDeviceAndSetParams("" , "" ,Device.toString(), "" , "", "", "")
			if(CreateTestController.getInstance().deviceChecks()){
				if(CreateTestController.getInstance().waitForRecordingStarts()){
					if(getDeviceLogsList()){
						MesmerLogUtils.markPassed("Multiple line of logs appears")
						
						if(CreateTestController.getInstance().clickDoneGreenButton()){
							if(CreateTestController.getInstance().clickDiscardButton()){
								if(CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()){
								}else{
									MesmerLogUtils.markFailed("Unable to click on discard alert")
								}
							}else{
								MesmerLogUtils.markFailed("Unable to click on discard button")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to click on done button")
						}
					}
					else{
						MesmerLogUtils.markFailed("No logs in the list")
					}
				}else{
					MesmerLogUtils.markFailed("Recording not started yet")
				}
			}else{
				MesmerLogUtils.markFailed("Device checks failed")
			}
		}
		else{
			MesmerLogUtils.markFailed("No "+ Device.toString() + " device available in the list")
		}
		}else{
		MesmerLogUtils.markFailed("Unable to navigate to create new test case")
	}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	
}

private boolean getDeviceLogsList(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()


	if(WebUI.waitForElementVisible(btnLogs, 30)){
		WebUI.click(btnLogs)

		if(Utility.getPlatformName() == "Android") {
			
			WebUI.clickOffset(divDevice,-115, -15)
			WebUI.delay(10)
			WebUI.clickOffset(divDevice,0 , 50)
			WebUI.delay(10)
		}else{
			WebUI.clickOffset(divDevice,-100, 25)
			WebUI.delay(10)
			WebUI.clickOffset(divDevice,0 , 50)
			WebUI.delay(10)
		}

		TestObject deviceLogs = findTestObject('Object Repository/OR_CreateNewTestCase/text_deviceLog')
		String deviceLogsXPath = findTestObject('Object Repository/OR_CreateNewTestCase/text_deviceLog').findPropertyValue('xpath').toString()
		List<WebElement> deviceLogsList = driver.findElements(By.xpath(deviceLogsXPath))

		if(WebUI.waitForElementPresent(deviceLogs, 120)){
			MesmerLogUtils.markPassed("Found logs")
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Device logs not appears in 2 mins")
		}
	}
	else{
		MesmerLogUtils.markFailed("Device logs icon not found")
	}
	return result
}
