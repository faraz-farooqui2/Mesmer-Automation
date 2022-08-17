import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController

/*
 * MR-Record -30 | Verify Lock button on Recording (Android only) locks device successfully
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()

try{
	WebElement searchedReadyDevice = Utility.selectDevice(Device.toString())
	if(searchedReadyDevice != null){
		searchedReadyDevice.click()
		startRecordingAndPerformActions()
	}else{
		MesmerLogUtils.markFailed("Unable to click on ready device")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

def startRecordingAndPerformActions(){
	WebDriver driver = DriverFactory.getWebDriver()
	if(WebUI.waitForElementPresent(deviceRetrieved, 60)){

		if(WebUI.waitForElementPresent(connectToDevice, 60)){

			if(WebUI.waitForElementPresent(deviceConfigured, 60)){

				if(WebUI.waitForElementPresent(buildDownload, 60)){

					if(WebUI.waitForElementPresent(buildInstalled, 60)){

						if(WebUI.waitForElementPresent(startingStream, 60)){
							WebUI.delay(10)

							if(Utility.getPlatformName() == "Android") {
								if(WebUI.waitForElementClickable(deviceLock, 30)){
									WebUI.click(deviceLock)
									WebUI.delay(10)

									if(WebUI.waitForElementClickable(deviceUnlock, 30)){
										WebUI.click(deviceUnlock)
										WebUI.delay(10)
									}else{
										MesmerLogUtils.markFailed("Unable to unlock device")
									}
								}else{
									MesmerLogUtils.markFailed("Unable to lock device")
								}
							}else{
								if(WebUI.waitForElementClickable(deviceHome, 30)){
									WebUI.click(deviceHome)
									WebUI.delay(10)

								}else{
									MesmerLogUtils.markFailed("Unable to click on device home button")
								}
							}

						}else{
							MesmerLogUtils.markFailed("Unable to start stream on device")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to install build on device")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to download build on device")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to configure device")
			}

		}else{
			MesmerLogUtils.markFailed("Unable to connect to device")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to retrieve device")
	}
}

