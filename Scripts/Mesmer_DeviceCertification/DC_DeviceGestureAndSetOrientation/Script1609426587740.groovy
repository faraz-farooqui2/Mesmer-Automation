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
import com.mesmer.MesmerRecordHelper

import controllers.CreateTestController
import controllers.TestResultController
import controllers.CommonMethodsController

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

		CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()

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

									if(WebUI.waitForElementVisible(verifyRecordingIcon, 180)){
										WebUI.delay(10)

										waitForDeviceAndBuildInstallation(deviceNameElement)
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
						MesmerLogUtils.markFailed("Recording icon not appears ")
					}
				}else{
					MesmerLogUtils.markFailed("Starting text not appears")
				}

			}else{
				MesmerLogUtils.markFailed("Unable to click on ready device")
			}
		}else{
			MesmerLogUtils.markFailed("Create new test screen not open")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " + ProjectName + "  not found" )
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

private void waitForDeviceAndBuildInstallation(String deviceName){
	if(WebUI.waitForElementVisible(recordingStarted, 240)){

		WebUI.delay(5)
		WebUI.setViewPortSize(1920, 925)
		WebUI.delay(2)

		if(!deviceName.isEmpty()){
			MesmerRecordHelper.getInstance().parseJsonAndPerformActions(jsonProjectName, platformName, deviceName, appUserName, appPassword)
			setTestCaseName()
		}
		else{
			MesmerLogUtils.markFailed("Json device name is different from what is selected")
		}
	}
	else{
		if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
			startRecording()
		}
		MesmerLogUtils.markFailed("Recording not started within 4 minutes")

	}
}

private boolean startRecording(){
	boolean result = false
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){

		List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())

		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			String deviceNameElement = Utility.selectDeviceAndSetDeviceParam(deviceUDID.toString())
			if(CreateTestController.getInstance().clickNextBtn()){
				if(CreateTestController.getInstance().clickApplyBtn()){
					if(CreateTestController.getInstance().clickStartBtn()){
						if(CreateTestController.getInstance().deviceChecks()){


							CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
							WebUI.delay(2)

							if(WebUI.waitForElementVisible(unableToRetrieveDeviceStrip, 2)==true){

								MesmerLogUtils.markFailed("Unable to Retrieve Device is displayed during recording")
							}
							else if(WebUI.waitForElementVisible(deviceErrorDuringRecording, 2)){

								CreateTestController.getInstance().checkDeviceErrorAndCancel()
							}
							else{

								waitForDeviceAndBuildInstallation(deviceNameElement)
								result = true
							}

							if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
								isTestReadyToExecute = startRecording()
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
		}
		else{

			MesmerLogUtils.markFailed("No "+Device.toString()+"device available in the list")
		}
	}
	else{
		MesmerLogUtils.markFailed("CreateNewTest screen did not open within 1 minute")
	}
	return result
}



private void setTestCaseName(){
	moveBrowserDown()

	String tcName = "Auto-1"
	if(CreateTestController.getInstance().clickDoneGreenButton()){
		if(CreateTestController.getInstance().checkIfSaveTestAlertAppeared()){
			if(CreateTestController.getInstance().setRecordedTestCaseName(tcName)){

				if(CreateTestController.getInstance().saveNewTestCase()){
					if(CreateTestController.getInstance().viewTestCase()){
						if(CreateTestController.getInstance().viewAndEditTestCase()){

							//	deleteTest()
						}
						else{

							MesmerLogUtils.logInfo("View and edit test option not appeared")
						}
					}
					else{

						MesmerLogUtils.logInfo("View test option not appeared")
					}
				}
				else{

					MesmerLogUtils.logInfo("Test case did not save successfully")
					discardTest()
				}
			}
			else{

				MesmerLogUtils.logInfo("Test case name did not set peoperly")
			}
		}
		else{

			MesmerLogUtils.logInfo("Save test alert did not appear")
		}
	}
	else{

		MesmerLogUtils.logInfo("Button green done not clicked")
	}

}
private void moveBrowserDown(){
	WebUI.scrollToElement(headerContentDiv, 10)
	WebUI.delay(5)
}

private void deleteTest(){
	//7. Click on Delete option from the drop down list of 3 dots menu

	if(WebUI.waitForElementPresent(option3DotButton, 20)){


		if(WebUI.waitForElementVisible(notifcationNew, 2)){
			WebUI.delay(4)
		}

		WebUI.click(option3DotButton)
		WebUI.delay(5)
		if(WebUI.waitForElementPresent(btnDelete, 20)){
			WebUI.click(btnDelete)
			WebUI.delay(5)
			MesmerLogUtils.markPassed("Clicked on Delete Button ")
			if(WebUI.waitForElementPresent(deleteConfirmation, 20)==true){
				MesmerLogUtils.markPassed("Test Case Delete Confirmation Dialogue")

				//8. Click on Yes option
				if(WebUI.waitForElementPresent(deleteYes, 20)==true){
					WebUI.click(deleteYes)
					Utility.logCurrentUTCTime("Test case deleted at time")
					if(Utility.checkIfSpinnerNotVisible()){

						MesmerLogUtils.markPassed("Clicked on Yes to Delete Test Case")
					}
					else{

						MesmerLogUtils.markFailed("Test case did not delete within 2 minutes")
					}
					TestResultController.getInstance().checkIfDeleteInQueueDialogAppears()
				}else{
					MesmerLogUtils.markFailed("Unable to Click on Yes to Delete Test Case ")
				}
			}else{
				MesmerLogUtils.markFailed("No Test Case Delete Confirmation Dialogue ")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to Click on Delete Button")
		}
	}
	else{
		MesmerLogUtils.markWarning("Button 3 dots not found")
	}
}

private void discardTest(){
	if(CreateTestController.getInstance().clickDiscardButton()){
		if(CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()){
			MesmerLogUtils.logInfo("Test case discarded successfully")
		}
		else{
			MesmerLogUtils.logInfo("Discard alert not appeared")
		}
	}
	else{
		MesmerLogUtils.logInfo("Discard button not clicked")
	}
}
