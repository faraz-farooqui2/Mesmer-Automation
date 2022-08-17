import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.html5.WebStorage
import org.openqa.selenium.html5.LocalStorage

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerRecordHelper

import controllers.CreateTestController
import controllers.TestResultController

//
//MesmerLogUtils.logInfo("RecordPath = " + RecordPath)
//int SN = Integer.parseInt(SrNo)
//
//if (SN <= 100){
//	NewRecordPath = RecordPath + "user01" + Utility.getSlash()
//	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)
//
//} else if (SN > 100 && SN <= 200){
//	NewRecordPath = RecordPath + "user02" + Utility.getSlash()
//	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)
//} else if (SN > 200 && SN <= 300){
//	NewRecordPath = RecordPath + "user03" + Utility.getSlash()
//	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)
//} else if (SN > 300 && SN <= 400){
//	NewRecordPath = RecordPath + "user04" + Utility.getSlash()
//	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)
//}
//else if (SN > 400 && SN <= 500){
//	NewRecordPath = RecordPath + "user05" + Utility.getSlash()
//	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)
//}

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(5)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
//Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforeStartingTestCase")
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
//Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterNavigatingToCreateNewTestCase")
WebUI.delay(2)
WebUI.waitForPageLoad(5)


try{
	isTestReadyToExecute = startRecording()
}
catch(Exception e){
	e.printStackTrace()
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-InCatchBlock")
//	Utility.logCurrentUTCTime("Catch Block Time" + SrNo + " ")
//	MesmerLogUtils.logInfo("Record" + SrNo + " URL IN Catch Block - " + WebUI.getUrl())
	if(WebUI.waitForElementVisible(exitFullScreen, 2)){
		CreateTestController.getInstance().exitFullScreenMode()
	}

}

finally{

	if(isTestReadyToExecute){
		isTestReadyToExecute = false
		WebUI.delay(10)
	}

	if(WebUI.waitForElementVisible(exitFullScreen, 2)){
		CreateTestController.getInstance().exitFullScreenMode()
	}
//	MesmerLogUtils.logInfo("Record" + SrNo + " URL IN Final Block - " + WebUI.getUrl())
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-InFinallyBlock")
//	Utility.logCurrentUTCTime("Final Block Time" + SrNo + " ")


	//	Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforeGoingToTestResults")
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.delay(2)
	CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterNavigateToTestResults")
//	KTRequestHandler.getXHRCalls()
//	KTRequestHandler.getBrowserConsoleLogs()
//	KTRequestHandler.clearBrowserConsoleLogs()

}

private boolean startRecording(){
	boolean result = false
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){
	//	Utility.takeScreenshot(NewRecordPath, SrNo +"-DevicesListOpen")
		List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())

		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			Utility.selectDeviceAndSetParams("", "", Device.toString(), lang , region, coordinates, tag)

//			Utility.logCurrentUTCTime("Device selection time")
//			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection1")
//			WebUI.delay(1)
//			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection2")
//			WebUI.delay(1)
//			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection3")
//			String SessionID1 = WebUI.executeJavaScript("return localStorage.getItem('SessionID')", null)
//			MesmerLogUtils.logInfo("Session ID of iteration " + SrNo + " ---- > " + SessionID1)
//
//			WebUI.delay(1)
//			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection4")
//			WebUI.delay(1)
//			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection5")
//			
//			Utility.takeScreenshot(NewRecordPath, SrNo +"-DiscardAlertAppearedAfterDeviceSelection")
			CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
			WebUI.delay(2)

			if(WebUI.waitForElementVisible(unableToRetrieveDeviceStrip, 2)==true){
			//	Utility.takeScreenshot(NewRecordPath, SrNo +"-UnableToRetrieveDevice")
				MesmerLogUtils.markFailed("Unable to Retrieve Device is displayed during recording")
			}
			else if(WebUI.waitForElementVisible(deviceErrorDuringRecording, 2)){
			//	Utility.takeScreenshot(NewRecordPath, SrNo +"-ConnectionLost")
				CreateTestController.getInstance().checkDeviceErrorAndCancel()
			}
			else{
			//	Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforewaitForDeviceAndBuildInstallation")
				waitForDeviceAndBuildInstallation()
				result = true
			}
			
			if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
				isTestReadyToExecute = startRecording()
			}

		}
		else{
		//	Utility.takeScreenshot(NewRecordPath, SrNo +"-NoDeviceAvailable")

			MesmerLogUtils.markFailed("No "+Device.toString()+"device available in the list")
		}
	}
	else{
		MesmerLogUtils.markFailed("CreateNewTest screen did not open within 1 minute")
	}
	return result
}

private void waitForDeviceAndBuildInstallation(){
	if(WebUI.waitForElementVisible(recordingStarted, 240)){
		Utility.logCurrentUTCTime("Build installed at this time")
		WebUI.delay(5)

		if(ProjectName.equals("Dairy Queen Preprod") && platformName.equals("android")){
			startDQRecordingAndPerformActions()
		}
		else if(ProjectName.equals("Nationwide") && platformName.equals("android")){
			startNWAndroidRecording()
		}
		else if(ProjectName.equals("Starbucks-Android") && platformName.equals("android")){
			startStarbucksAndroidRecording()
		}
		else if(ProjectName.equals("Starbucks-iOS") && platformName.equals("apple")){
			startStarbucksiOSRecording()
		}
		else if(ProjectName.equals("Nationwide") && platformName.equals("apple")){
			startNWiOSRecording()
		}
		else if(ProjectName.equals("Starbucks") && platformName.equals("android")){
			startStarbucksAndroidRecording()
			//startStarbucksAndroidNexus5Recording()
		}
		
		else if(ProjectName.equals("Starbucks") && platformName.equals("apple")){
			startStarbucksiOSRecording()
		}

		else if(ProjectName.equals("Starbucks - Virtual") && platformName.equals("apple")){
			startStarbucksiOSRecording()
		}
		else if(ProjectName.equals("Mesmer") && platformName.equals("apple")){
			startMesmeriOSRecording()
		}
		else if(ProjectName.equals("sandbox-Mesmer") && platformName.equals("apple")){
			startSandboxMesmeriOSRecording()
		}
	}
	else{
		if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
			startRecording()
		}
		MesmerLogUtils.markFailed("Recording not started within 4 minutes")
		//Utility.takeScreenshot(NewRecordPath, SrNo +"-RecordingNotStartedWitin4Minutes")
	}
}

private void moveBrowserDown(){
	WebUI.scrollToElement(headerContentDiv, 10)
	WebUI.delay(5)
}

private void startMesmeriOSRecording(){
	MesmerRecordHelper.getInstance().startMesmeriOSRecording(ProjectName,platformName, NewRecordPath, SrNo, appUserName, appPassword)
	setTestCaseName()
}


//private void startStarbucksAndroidNexus5Recording(){
//	Utility.logCurrentUTCTime("Recording start time")
//	CreateTestController.getInstance().enterFullScreenMode()
//	WebUI.delay(10)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step1")
//	WebUI.clickOffset(divDevice,130,-100)
//	WebUI.delay(10)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step2")
//	WebUI.clickOffset(divDevice,-40,-100)
//	WebUI.delay(10)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step3")
//	WebUI.clickOffset(divDevice,-120,-135)
//	WebUI.delay(5)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step4")
//	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
//	WebUI.delay(10)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step5")
//	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
//	WebUI.delay(10)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step6")
//	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appUserName))
//	WebUI.delay(10)
//	WebUI.click(btnReply)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-clickBTNReplayBetweenSBAndroidREcording-Recording-Step7")
//	WebUI.delay(10)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterdelayingFor30secsBetweenSBAndroidREcording-Recording-Step8")
//	WebUI.clickOffset(divDevice,-120,-100)
//	WebUI.delay(10)
//	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
//	WebUI.delay(10)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-BetweenSBAndroidREcording-Recording-Step9")
//	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
//	WebUI.delay(10)
//	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appPassword))
//	WebUI.delay(10)
//	WebUI.click(btnReply)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-ClickingApplyBtnAfterEnteringPassword-Recording-Step10")
//	WebUI.delay(30)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-ClickingApplyBtnAfterEnteringPasswordAfter30sWait")
//	WebUI.clickOffset(divDevice,120,200)
//	WebUI.delay(60)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-ClickingSignINBtnAfterWaiting60Sec-Recording-Step11")
//	WebUI.clickOffset(divDevice,-40,-135)
//	WebUI.delay(15)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step12")
//	CreateTestController.getInstance().exitFullScreenMode()
//	Utility.logCurrentUTCTime("Recording end time")
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step13")
//	setTestCaseName()
//
//}

private void startStarbucksAndroidRecording(){
	MesmerRecordHelper.getInstance().startStarbucksAndroidRecording(ProjectName,platformName, NewRecordPath, SrNo, appUserName, appPassword)
	setTestCaseName()
}

private void startStarbucksiOSRecording(){
	MesmerRecordHelper.getInstance().startStarbucksiOSRecording(ProjectName,platformName, NewRecordPath, SrNo, appUserName, appPassword)
	setTestCaseName()
}


private boolean checkIfNoDeviceAvailable(){
	boolean result = false;
	if(WebUI.waitForElementVisible(noDeviceAvailable, 10)){
		Utility.takeScreenshot(NewRecordPath, SrNo +"-noDeviceAvailable")
		result = true
	}
	return result
}

private void setTestCaseName(){
	moveBrowserDown()
	String tcName = ProjectName+" "+platformName+" "+SrNo
	if(CreateTestController.getInstance().clickDoneGreenButton()){
		if(CreateTestController.getInstance().checkIfSaveTestAlertAppeared()){
			if(CreateTestController.getInstance().setRecordedTestCaseName(tcName)){
				Utility.takeScreenshot(NewRecordPath, SrNo +"-SetTestCaseName")
				if(CreateTestController.getInstance().saveNewTestCase()){
					if(CreateTestController.getInstance().viewTestCase()){
						if(CreateTestController.getInstance().viewAndEditTestCase()){
							Utility.takeScreenshot(NewRecordPath, SrNo +"-ViewAndEditTestVisible")
							deleteTest()
						}
						else{
							Utility.takeScreenshot(NewRecordPath, SrNo +"-ViewAndEditTestNotVisible")
							MesmerLogUtils.logInfo("View and edit test option not appeared")
						}
					}
					else{
						Utility.takeScreenshot(NewRecordPath, SrNo +"-ViewTestNotVisible")
						MesmerLogUtils.logInfo("View test option not appeared")
					}
				}
				else{
					Utility.takeScreenshot(NewRecordPath, SrNo +"-TestCaseNotSaved")
					MesmerLogUtils.logInfo("Test case did not save successfully")
					discardTest()
				}
			}
			else{
				Utility.takeScreenshot(NewRecordPath, SrNo +"-TestCaseNameNotSet")
				MesmerLogUtils.logInfo("Test case name did not set peoperly")
			}
		}
		else{
			Utility.takeScreenshot(NewRecordPath, SrNo +"-SaveTestAlertNotAppeared")
			MesmerLogUtils.logInfo("Save test alert did not appear")
		}
	}
	else{
		Utility.takeScreenshot(NewRecordPath, SrNo +"-ButtonGreenDoneNotAppeared")
		MesmerLogUtils.logInfo("Button green done not clicked")
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

private void saveTestCase(){
	if(WebUI.waitForElementVisible(btnSaveActive, 30)){
		WebUI.click(btnSaveActive)
		Utility.logCurrentUTCTime("Test case saved at time")
		WebUI.delay(2)
		Utility.takeScreenshot(NewRecordPath, SrNo +"-SaveTestCase1")
		if(Utility.checkIfSpinnerNotVisible()){
			Utility.takeScreenshot(NewRecordPath, SrNo +"-SaveTestCase")
			deleteTestCase()
		}
		else{
			Utility.takeScreenshot(NewRecordPath, SrNo +"-TestCaseNotSaved")
			MesmerLogUtils.markFailed("Test case not saved within 2 minutes")
		}
	}
	else{
		MesmerLogUtils.markWarning("Button save active not found")
	}
}

private void deleteTest(){
	//7. Click on Delete option from the drop down list of 3 dots menu
	Utility.takeScreenshot(NewRecordPath, SrNo +"-WaitingFore3DotButton")
	if(WebUI.waitForElementPresent(option3DotButton, 20)){
		Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforeClicking3DotbuttonOption")

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
				Utility.takeScreenshot(NewRecordPath, SrNo +"-DeleteTestCaseAlert")
				//8. Click on Yes option
				if(WebUI.waitForElementPresent(deleteYes, 20)==true){
					WebUI.click(deleteYes)
					Utility.logCurrentUTCTime("Test case deleted at time")
					if(Utility.checkIfSpinnerNotVisible()){
						Utility.takeScreenshot(NewRecordPath, SrNo +"-DeleteInProgress")
						MesmerLogUtils.markPassed("Clicked on Yes to Delete Test Case")
					}
					else{
						Utility.takeScreenshot(NewRecordPath, SrNo +"-TestCaseNotDeleted")
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

