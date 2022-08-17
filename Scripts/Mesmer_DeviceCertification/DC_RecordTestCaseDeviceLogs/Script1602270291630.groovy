import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController

/*
 Device Logs
 */


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
									if(CreateTestController.getInstance().waitForRecordingStarts()){
										if(startRecordingAndPerformActions()){
											MesmerLogUtils.markPassed("Start Recording and Perform Actions method Passed")
										}else{
											MesmerLogUtils.markFailed("Start Recording and Perform Actions method failed")
										}
									}else{
										MesmerLogUtils.markFailed("Recording not started yet")
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

private boolean  startRecordingAndPerformActions(){
	boolean result = false

	if(WebUI.waitForElementPresent(divDevice, 60)){
		WebUI.delay(10)
		WebUI.clickOffset(divDevice,15,65)
		if(WebUI.waitForElementVisible(btnLogs, 30)){
			WebUI.click(btnLogs)
			WebUI.delay(10)

			if( Utility.getPlatformName() == "Android"){
				WebUI.clickOffset(divDevice, -100, 0)

				WebUI.delay(20)

				if(getDeviceLogsList()){
					MesmerLogUtils.markPassed("Device logs appeared" )
					result = true
				}else{
					MesmerLogUtils.markFailed("Device logs are not appearing" )
				}

			}else if ( Utility.getPlatformName() == "iOS")  {
				WebUI.clickOffset(divDevice, -100, 0)
				WebUI.delay(10)
				if(getDeviceLogsList()){
					MesmerLogUtils.markPassed("Device logs appeared" )
					result = true
				}else{
					MesmerLogUtils.markFailed("Device logs are not appearing" )
				}
			}
		}
		else{
			MesmerLogUtils.markFailed("Device logs icon not found")
		}

	}else{
		MesmerLogUtils.markFailed("Unable to click on device div")
	}
	return result
}



private boolean getDeviceLogsList(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()

	String deviceLogsXPath = findTestObject('Object Repository/OR_CreateNewTestCase/text_deviceLog').findPropertyValue('xpath').toString()
	List<WebElement> deviceLogsList = driver.findElements(By.xpath(deviceLogsXPath))
	WebUI.delay(10)
	if(deviceLogsList != null && deviceLogsList.size() > 0){
		MesmerLogUtils.markPassed("Device Logs are in the list")
		result = true
	}
	else{
		MesmerLogUtils.markFailed("There is no log in the list")
	}
	return result
}
