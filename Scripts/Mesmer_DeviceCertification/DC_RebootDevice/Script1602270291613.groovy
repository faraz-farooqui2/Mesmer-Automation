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




WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()){
			if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){

				List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())

				if(virtualDevicesList != null && virtualDevicesList.size() >=1){
					String deviceNameElement = Utility.selectDeviceAndSetDeviceParam(deviceUDID.toString())
					if(CreateTestController.getInstance().clickNextBtn()){
						if(CreateTestController.getInstance().clickApplyBtn()){
							if(CreateTestController.getInstance().clickStartBtn()){
								if(CreateTestController.getInstance().deviceChecks()){

									TestObject verifyStartingText = findTestObject('Object Repository/OR_CreateNewTestCase/text_Starting')

									if(WebUI.waitForElementVisible(verifyStartingText, 120)){

										TestObject verifyRecordingIcon = findTestObject('Object Repository/OR_CreateNewTestCase/titletext_recordingDot')

										if(WebUI.waitForElementVisible(verifyRecordingIcon, 120)){
											WebUI.delay(10)
											if(CreateTestController.getInstance().rebootDevice()){

											}else{
												MesmerLogUtils.markFailed("Unable to reboot device")
											}
										}else{
											MesmerLogUtils.markFailed("Recording icon not appears ")
										}
									}else{
										MesmerLogUtils.markFailed("Starting text not appears")
									}
								}else{
									MesmerLogUtils.markFailed("Device checks failed")
								}
							}else{
								MesmerLogUtils.markFailed("Unable to click on start button")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to click on apply button")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to click on next button")
					}

				}else{
					MesmerLogUtils.markFailed("Unable to click on ready device")
				}
			}else{
				MesmerLogUtils.markFailed("Create new test screen not open")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to navigate to create new test case screen" )
		}
	}else{
		MesmerLogUtils.markFailed("Project   " + ProjectName + "  not found" )
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	if(CustomKeywords.'com.mesmer.Utility.goToTestResults'()){

	}else{
		MesmerLogUtils.markFailed("Unable to navigate to test result screen" )
	}
}
