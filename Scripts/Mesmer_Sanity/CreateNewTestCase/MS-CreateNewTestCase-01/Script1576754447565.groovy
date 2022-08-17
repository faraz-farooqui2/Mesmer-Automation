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
 * MS-Create a new Test Case-01 | Verify that user should be able to record an iOS test case (Physical/Simulator)
 */

try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)

	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()){

			List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())
			if(virtualDevicesList != null && virtualDevicesList.size() >=1){
				Utility.selectDeviceAndSetParams("" , "" ,Device.toString(), "" , "", "", "")
				if(CreateTestController.getInstance().deviceChecks()){
					if(CreateTestController.getInstance().waitForRecordingStarts()){
						if(startRecordingAndPerformActions()){

						}else{
							MesmerLogUtils.markFailed("Could not perform actions")
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
			MesmerLogUtils.markFailed("Unable to navigate to create new test case screen" )
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

private boolean startRecordingAndPerformActions(){
	WebDriver driver = DriverFactory.getWebDriver()
	boolean result = false

	if(CreateTestController.getInstance().checkAndClickTapGesture()){

		if(CreateTestController.getInstance().checkAndClickLongPressGesture()){

			if(CreateTestController.getInstance().checkAndClickSwipeGesture()){
				if(CreateTestController.getInstance().checkAndClickTapGesture()){
					if(WebUI.waitForElementVisible(captureSection, 120)){
						WebUI.click(captureSection)
						WebUI.delay(2)
						WebUI.click(captureSection)
						WebUI.delay(5)
						if(Utility.getPlatformName() == "Android") {
							if(WebUI.waitForElementClickable(deviceLock, 30)){
								WebUI.click(deviceLock)
								WebUI.delay(10)

								if(WebUI.waitForElementClickable(deviceUnlock, 30)){
									WebUI.click(deviceUnlock)
									WebUI.delay(10)

									if(CreateTestController.getInstance().closeApplicationButton()){

										if(CreateTestController.getInstance().rebootDevice()){
											result = true

										}else{
											MesmerLogUtils.markFailed("Could not reboot device")
										}
									}else{
										MesmerLogUtils.markFailed("Could not close application")
									}
								}else{
									MesmerLogUtils.markFailed("Unable to unlock device")
								}
							}else{
								MesmerLogUtils.markFailed("Unable to lock device")
							}
						}else if (Utility.getPlatformName() == "iOS") {
							if(WebUI.waitForElementClickable(deviceHome, 30)){
								WebUI.click(deviceHome)
								WebUI.delay(10)
								if(CreateTestController.getInstance().closeApplicationButton()){

									if(CreateTestController.getInstance().rebootDevice()){
										result = true

									}else{
										MesmerLogUtils.markFailed("Could not reboot device")
									}
								}else{
									MesmerLogUtils.markFailed("Could not close application")
								}


							}else{
								MesmerLogUtils.markFailed("Unable to click on device home button")
							}
						}
					}
					else{
						MesmerLogUtils.markFailed("Unable to click on capture section")
					}
				}else{
					MesmerLogUtils.markFailed("Could not click on tap gesture")
				}

			}else{
				MesmerLogUtils.markFailed("Could not click and perform swipe gesture")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click and perform long press gesture")
		}
	}else{
		MesmerLogUtils.markFailed("Could not click and perform tap gesture")
	}
	return result
}

