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
import com.mesmer.MesmerRecordHelper
import com.mesmer.Utility
import com.mesmer.KTRequestHandler

import controllers.CreateTestController
import controllers.TestResultController

//String NewRecordPath = ""
//Sanity Record

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

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(5)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
WebUI.delay(2)
WebUI.waitForPageLoad(5)


try{
	isTestReadyToExecute = startRecording()
}
catch(Exception e){
	e.printStackTrace()
	Utility.logCurrentUTCTime("Catch Block Time" + SrNo + " ")
	MesmerLogUtils.logInfo("Record" + SrNo + " URL IN Catch Block - " + WebUI.getUrl())
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
	MesmerLogUtils.logInfo("Record" + SrNo + " URL IN Final Block - " + WebUI.getUrl())
	Utility.logCurrentUTCTime("Final Block Time" + SrNo + " ")
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.delay(2)
	CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()

}

private boolean startRecording(){
	boolean result = false
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){
		List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())
		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			Utility.selectDeviceAndSetParams(Device.toString(),"","","")
			Utility.logCurrentUTCTime("Device selection time")
			WebUI.delay(1)
			if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
				isTestReadyToExecute = startRecording()
			}
			CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
			WebUI.delay(2)
			if(WebUI.waitForElementVisible(unableToRetrieveDeviceStrip, 2)==true){
				MesmerLogUtils.markFailed("Unable to Retrieve Device is displayed during recording")
			}
			else{
				waitForDeviceAndBuildInstallation()
				result = true
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

private void waitForDeviceAndBuildInstallation(){
	if(WebUI.waitForElementVisible(divDevice, 240)){
		Utility.logCurrentUTCTime("Build installed at this time")
		checkDevicesLogsOptions()
		WebUI.delay(5)
		if(ProjectName.equals("Wingstop Android") && platformName.equals("android")){
			startWingstopAndroidRecording()
		}
		else if(ProjectName.equals("Wingstop iOS") && platformName.equals("apple")){
			startWingstopiOSRecording()
		}
		else if(ProjectName.equals("Starbucks Android") && platformName.equals("android")){
			startStarbucksAndroidRecording()
		}
		else if(ProjectName.equals("Starbucks iOS") && platformName.equals("apple")){
			startStarbucksiOSRecording()
		}
		else if(ProjectName.equals("SyncUP DRIVE Android") && platformName.equals("android")){
			startMojioAndroid()
		}
		else if(ProjectName.equals("SyncUP DRIVE iOS") && platformName.equals("apple")){
			startMojioiOS()
		}
	}
	else{
		if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
			startRecording()
		}
		MesmerLogUtils.markFailed("Recording not started within 4 minutes")
	}
}

private void checkDevicesLogsOptions(){
	if(CreateTestController.getInstance().clickLogsIcon()){
		WebUI.delay(2)
		if(CreateTestController.getInstance().checkIfGearIconAndShowFilterAvailable()){
			if(CreateTestController.getInstance().getDeviceLogsList().size() > 0){
				MesmerLogUtils.logInfo("MR-E2E-6 is successful")
			}
		}
	}
}

private void moveBrowserDown(){
	WebUI.scrollToElement(headerContentDiv, 10)
	WebUI.delay(5)
}

private void startWingstopAndroidRecording(){
	MesmerRecordHelper.getInstance().startWingstopAndroidRecording(ProjectName,platformName, NewRecordPath, SrNo, appUserName, appPassword)
	setTestCaseName()
}
private void startWingstopiOSRecording(){
	MesmerRecordHelper.getInstance().startWingstopiOSRecording(NewRecordPath, SrNo, appUserName, appPassword)
	setTestCaseName()
}

private void startStarbucksAndroidRecording(){
	MesmerRecordHelper.getInstance().startStarbucksAndroidRecording(NewRecordPath, SrNo, appUserName, appPassword)
	setTestCaseName()
}

private void startStarbucksiOSRecording(){
	MesmerRecordHelper.getInstance().startStarbucksiOSRecording(NewRecordPath, SrNo, appUserName, appPassword)
	setTestCaseName()
}

private void startMojioAndroid(){
	MesmerRecordHelper.getInstance().startMojioAndroid(NewRecordPath, SrNo, appUserName, appPassword)
	setTestCaseName()
}

private void startMojioiOS(){
	MesmerRecordHelper.getInstance().startMojioiOS(NewRecordPath, SrNo, appUserName, appPassword)
	setTestCaseName()
}

private boolean checkIfNoDeviceAvailable(){
	boolean result = false;
	if(WebUI.waitForElementVisible(noDeviceAvailable, 10)){
		result = true
	}
	return result
}

private void setTestCaseName(){
	moveBrowserDown()
	String tcName = testCaseName
	if(CreateTestController.getInstance().clickDoneGreenButton()){
		if(CreateTestController.getInstance().checkIfSaveTestAlertAppeared()){
			if(CreateTestController.getInstance().setRecordedTestCaseName(tcName)){
				if(CreateTestController.getInstance().saveNewTestCase()){
					if(CreateTestController.getInstance().clickOnReleaseDeviceAndCreateNew()){
						MesmerLogUtils.logInfo("Test case recorded successfully")
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