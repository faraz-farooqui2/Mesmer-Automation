
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


MesmerLogUtils.logInfo("RecordPath = " + RecordPath)
int SN = Integer.parseInt(SrNo)

if (SN <= 100){
	NewRecordPath = RecordPath + "user01" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)

} else if (SN > 100 && SN <= 200){
	NewRecordPath = RecordPath + "user02" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)
} else if (SN > 200 && SN <= 300){
	NewRecordPath = RecordPath + "user03" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)
} else if (SN > 300 && SN <= 400){
	NewRecordPath = RecordPath + "user04" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)
}
else if (SN > 400 && SN <= 500){
	NewRecordPath = RecordPath + "user05" + Utility.getSlash()
	MesmerLogUtils.logInfo("NewRecordPath = " + NewRecordPath)
}

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(5)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforeStartingTestCase")
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
if(WebUI.waitForElementVisible(recordingText, 2)){
	if (WebUI.waitForElementVisible(exitFullScreen, 2)){
		WebUI.click(exitFullScreen)
		WebUI.delay(2)
	}
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterNavigatingToTestResults")
	CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
	Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterNavigatingToCreateNewTestCase-2")
}
	
Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterNavigatingToCreateNewTestCase")
WebUI.delay(2)
WebUI.waitForPageLoad(5)


try{
	isTestReadyToExecute = startRecording()
}
catch(Exception e){
	MesmerLogUtils.logInfo("##########CATCH###########")
	e.printStackTrace()
	CreateTestController.getInstance().exitFullScreenMode()
	Utility.takeScreenshot(NewRecordPath, SrNo +"-InCatchBlock")
	Utility.logCurrentUTCTime("Catch Block Time" + SrNo + " ")
//	WebUI.refresh()
	WebUI.delay(2)
	MesmerLogUtils.logInfo("Record" + SrNo + " URL IN Catch Block - " + WebUI.getUrl())
	
	if(WebUI.waitForElementVisible(deviceErrorDuringRecording, 2)){
		Utility.takeScreenshot(NewRecordPath, SrNo +"-ConnectionLost")
		CreateTestController.getInstance().checkDeviceErrorAndCancel()
	}
	
	if(WebUI.waitForElementVisible(discardbtncheck, 5)){
		WebUI.click(discardbtncheck)
		MesmerLogUtils.logInfo("Dicard button is displayed in catch block")
		CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
	}
	
//	if(WebUI.waitForElementVisible(exitFullScreen, 2)){
//		CreateTestController.getInstance().exitFullScreenMode()
//	}

}

finally{
	MesmerLogUtils.logInfo("##########CATCH###########")
	if(isTestReadyToExecute){
		isTestReadyToExecute = false
		WebUI.delay(5)
	}
	
//	if(WebUI.waitForElementVisible(deviceErrorDuringRecording, 2)){
//		Utility.takeScreenshot(NewRecordPath, SrNo +"-ConnectionLost")
//		CreateTestController.getInstance().checkDeviceErrorAndCancel()
//	}

	if(WebUI.waitForElementVisible(exitFullScreen, 2)){
		CreateTestController.getInstance().exitFullScreenMode()
	}
	MesmerLogUtils.logInfo("Record" + SrNo + " URL IN Final Block - " + WebUI.getUrl())
	Utility.takeScreenshot(NewRecordPath, SrNo +"-InFinallyBlock")
	Utility.logCurrentUTCTime("Final Block Time" + SrNo + " ")


	//	Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforeGoingToTestResults")
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.delay(2)
	
	if(WebUI.waitForElementVisible(discardbtncheck, 5)){
		WebUI.click(discardbtncheck)
		MesmerLogUtils.logInfo("Dicard button is displayed in finally block")
		CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
	}

	Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterNavigateToTestResults")
	KTRequestHandler.getXHRCalls()
	KTRequestHandler.getBrowserConsoleLogs()
	KTRequestHandler.clearBrowserConsoleLogs()

}

private boolean startRecording(){
	boolean result = false
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){
		Utility.takeScreenshot(NewRecordPath, SrNo +"-DevicesListOpen")
		List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())

		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			Utility.selectDeviceAndSetParams(NewRecordPath, SrNo, Device.toString(), lang , region, coordinates, tag)

			Utility.logCurrentUTCTime("Device selection time")
			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection1")
			WebUI.delay(1)
			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection2")
			WebUI.delay(1)
			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection3")
			String SessionID1 = WebUI.executeJavaScript("return localStorage.getItem('SessionID')", null)
			MesmerLogUtils.logInfo("Session ID of iteration " + SrNo + " ---- > " + SessionID1)

			WebUI.delay(1)
			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection4")
			WebUI.delay(1)
			Utility.takeScreenshot(NewRecordPath, SrNo +"-DeviceSelection5")
			
			Utility.takeScreenshot(NewRecordPath, SrNo +"-DiscardAlertAppearedAfterDeviceSelection")
			
			if(WebUI.waitForElementVisible(discardbtncheck, 5)){
				WebUI.click(discardbtncheck)
				MesmerLogUtils.logInfo("Dicard button is displayed in catch block")
				CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
			}
			
			WebUI.delay(2)

			if(WebUI.waitForElementVisible(unableToRetrieveDeviceStrip, 2)==true){
				Utility.takeScreenshot(NewRecordPath, SrNo +"-UnableToRetrieveDevice")
				MesmerLogUtils.markFailed("Unable to Retrieve Device is displayed during recording")
			}
			else if(WebUI.waitForElementVisible(connectionLostMsg, 2)==true){
				Utility.takeScreenshot(NewRecordPath, SrNo +"-SorryConnectionLost")
				MesmerLogUtils.markFailed("Sorry Connection was lost between server and device")
			}
			else if(WebUI.waitForElementVisible(deviceErrorDuringRecording, 2)){
				Utility.takeScreenshot(NewRecordPath, SrNo +"-ConnectionLost")
				CreateTestController.getInstance().checkDeviceErrorAndCancel()
			}
			else{
				Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforewaitForDeviceAndBuildInstallation")
				waitForDeviceAndBuildInstallation()
				result = true
			}
			
			if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
				Utility.takeScreenshot(NewRecordPath, SrNo +"-chooseAnotherDeviceAvailableOption")
			}

		}
		else{
			Utility.takeScreenshot(NewRecordPath, SrNo +"-NoDeviceAvailable")

			MesmerLogUtils.markFailed("No "+Device.toString()+ " device available in the list")
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

		if(ProjectName.equals("Dairy Queen Preprod")){
			startDQRecordingAndPerformActions()
		}
		else if(ProjectName.equals("Nationwide") && platformName.equals("android")){
			startNWAndroidRecording()
		}
		else if(ProjectName.equals("Nationwide") && platformName.equals("apple")){
			startNWiOSRecording()
		}
		else if(ProjectName.equals("Starbucks") && platformName.equals("android")){
			startStarbucksAndroidRecording()
			//			startStarbucksAndroidNexus5Recording()
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
		else if(ProjectName.equals("Mojio-Automation-Android") && platformName.equals("android")){
			// startMojioAndroid()
			startMojioAndroidSamsung()
		}
		else if(ProjectName.equals("Mojio-Automation-iOS") && platformName.equals("apple")){
			startMojioiOS()
		}
	}
	else{
//		if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
//			startRecording()
//		}
		MesmerLogUtils.markFailed("Recording not started within 4 minutes")
		Utility.takeScreenshot(NewRecordPath, SrNo +"-RecordingNotStartedWitin4Minutes")
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

private void startMojioiOS(){
	int secDelay = 10;
	Utility.logCurrentUTCTime("Recording start time")
	CreateTestController.getInstance().enterFullScreenMode()
	WebUI.delay(5)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step1")
	WebUI.clickOffset(divDevice,0,295)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step2")
	WebUI.clickOffset(divDevice,0,35)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step3")
	WebUI.clickOffset(divDevice,-40,-140)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step4")
	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step5")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step6")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appUserName))
	WebUI.delay(5)
	WebUI.click(btnApply)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step7")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,-40,-110)
	WebUI.delay(secDelay)
	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step9")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(2)
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appPassword))
	WebUI.delay(5)
	WebUI.click(btnApply)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step10")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,0,-30)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step11")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,50,50)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step12")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,0,10)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step13")
	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-50,300)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step14")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,50,300)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step15")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,140,300)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step16")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-140,300)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step17")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,140,-290)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step18")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,140,-290)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step19")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-140,-290)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step20")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,150,290)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step21")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,50,300)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step22")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,80,-130)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step23")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-140,-290)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step24")
//	WebUI.delay(secDelay)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step25")
//	WebUI.delay(secDelay)
	CreateTestController.getInstance().exitFullScreenMode()
	Utility.logCurrentUTCTime("Recording end time")
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step26")
	setTestCaseName()
}

private void startMojioAndroidSamsung(){
	int secDelay = 10;
	Utility.logCurrentUTCTime("Recording start time")
	CreateTestController.getInstance().enterFullScreenMode()
	WebUI.delay(5)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step1")
	WebUI.clickOffset(divDevice,0,280)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step2")
	WebUI.clickOffset(divDevice,0,-10)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step3")
	WebUI.clickOffset(divDevice,-40,-240)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step4")
	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step5")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step6")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appUserName))
	WebUI.delay(5)
	WebUI.click(btnApply)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step7")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,-40,-180)
	WebUI.delay(secDelay)
	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step9")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(2)
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appPassword))
	WebUI.delay(5)
	WebUI.click(btnApply)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step10")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,0,-80)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step11")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,80,30)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step12")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,-45,275)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step13")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,45,275)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step14")
	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,125,265)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step15")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-125,265)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step16")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step17")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step18")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step19")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,130,250)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step20")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,45,265)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step21")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,70,-180)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step22")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step23")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-125,265)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step24")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,0,0)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step25")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step26")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step27")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-210)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step28")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-120,200)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step29")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,70,40)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step30")
//	WebUI.delay(secDelay)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step31")
	CreateTestController.getInstance().exitFullScreenMode()
	Utility.logCurrentUTCTime("Recording end time")
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step123")
	setTestCaseName()
}

private void startMojioAndroid(){
	int secDelay = 10;
	Utility.logCurrentUTCTime("Recording start time")
	CreateTestController.getInstance().enterFullScreenMode()
	WebUI.delay(5)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step1")
	WebUI.clickOffset(divDevice,0,240)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step2")
	WebUI.clickOffset(divDevice,0,0)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step3")
	WebUI.clickOffset(divDevice,-40,-200)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step4")
	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step5")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step6")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appUserName))
	WebUI.delay(5)
	WebUI.click(btnApply)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step7")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,-40,-160)
	WebUI.delay(secDelay)
	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	WebUI.delay(2)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step9")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(2)
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appPassword))
	WebUI.delay(5)
	WebUI.click(btnApply)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step10")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,0,-70)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step11")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,70,30)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step12")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,-45,265)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step13")
	WebUI.delay(secDelay)
	WebUI.clickOffset(divDevice,45,265)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step14")
	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,125,265)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step15")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-125,265)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step16")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step17")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step18")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step19")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,130,250)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step20")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,45,265)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step21")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,70,-180)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step22")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step23")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-125,265)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step24")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,0,0)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step25")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step26")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-270)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step27")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-135,-210)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step28")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,-120,200)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step29")
//	WebUI.delay(secDelay)
//	WebUI.clickOffset(divDevice,70,40)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step30")
//	WebUI.delay(secDelay)
//	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step31")
	CreateTestController.getInstance().exitFullScreenMode()
	Utility.logCurrentUTCTime("Recording end time")
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step123")
	setTestCaseName()
}


private void startStarbucksAndroidNexus5Recording(){
	int secDelay = 10
	Utility.logCurrentUTCTime("Recording start time")
	CreateTestController.getInstance().enterFullScreenMode()
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step1")
	WebUI.clickOffset(divDevice,130,-100)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step2")
	WebUI.clickOffset(divDevice,-40,-100)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step3")
	WebUI.clickOffset(divDevice,-120,-135)
	WebUI.delay(5)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step4")
	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step5")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step6")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appUserName))
	WebUI.delay(secDelay)
	WebUI.click(btnApply)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-clickBTNReplayBetweenSBAndroidREcording-Recording-Step7")
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterdelayingFor30secsBetweenSBAndroidREcording-Recording-Step8")
	WebUI.clickOffset(divDevice,-120,-100)
	WebUI.delay(secDelay)
	WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-BetweenSBAndroidREcording-Recording-Step9")
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	WebUI.delay(secDelay)
	WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appPassword))
	WebUI.delay(secDelay)
	WebUI.click(btnApply)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-ClickingApplyBtnAfterEnteringPassword-Recording-Step10")
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-ClickingApplyBtnAfterEnteringPasswordAfter30sWait")
	WebUI.clickOffset(divDevice,120,200)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-ClickingSignINBtnAfterWaiting60Sec-Recording-Step11")
	WebUI.clickOffset(divDevice,-40,-135)
	WebUI.delay(secDelay)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step12")
	CreateTestController.getInstance().exitFullScreenMode()
	Utility.logCurrentUTCTime("Recording end time")
	Utility.takeScreenshot(NewRecordPath, SrNo +"-Recording-Step13")
	setTestCaseName()

}

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

