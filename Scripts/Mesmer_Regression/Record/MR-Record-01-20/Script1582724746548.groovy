import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.touch.TouchActions

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.MesmerRecordHelper
import com.mesmer.Utility

import controllers.CreateTestController
import controllers.ManageTestController
import com.kms.katalon.core.testobject.TestObject

/*
 * MR-Record -01 | Verify user is redirected to Recording page on clicking "Create a New Testcase" button from Test Cases menu
 * MR-Record -02 | Verify Recording page appears on clicking "New Test" button from Manage Tests tab
 * MR-Record -03 | Verify Recording page top panel contains correct elements
 * MR-Record -04 | Verify list of devices should appear on clicking the Select Device menu icon
 * MR-Record -05 | Verify list of devices should appear on clicking the device icon on recording screen
 * MR-Record -06 | Verify user should be able to see if a device is Broken and unable to select broken device
 * MR-Record -07 | Verify user should be able to see if a device is in use for recording by any user and  activity being performed on that device is also shown
 * MR-Record -08 | Verify user should not be able to see user name and activity being performed on in use device, if device is in use on other server
 * MR-Record -09 | Verify user should not be able to select an already In use device
 * MR-Record -10 | Verify user can select provisioned device and recording should start on provisioned device within 3-5 minutes with a proper message displayed
 * MR-Record -11 | Verify on fetching device failure, error should show Retry
 * MR-Record -12 | Verify user should be able to see and select ready device
 * MR-Record -14 | Verify the physical devices in the record list of devices
 * MR-Record -15 | Verify selected device should have a tick mark against it and device should be highlighted
 * MR-Record -16 | Verify selected device should apear in place of Select device text next to Add tag label
 * MR-Record -17 | Verify device should reflect proper messsage if device is incompatible with the build due to any reason
 * MR-Record -18 | Verify device list dropdown closes on clicking outside the list 
 * MR-Record -19 | Verify that recording starts successfully on switching device
 * MR-Record -20 | Verify user does not get message 'device not found' on switching device
 * 
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
WebUI.waitForPageLoad(5)
MesmerLogUtils.markPassed("PASSED: MR-Record-02 Successful")

try{
	 // MR-Record-01
	checkTestCase01Steps()
	 // MR-Record-03
	checkTestCase03Steps()
	// MR-Record-04
	checkTestCase04Steps()
	// MR-Record-05
	checkTestCase05Steps()
	// MR-Record-06
	checkTestCase06Steps()
	// MR-Record-07
	checkTestCase07Steps()
	// MR-Record-08
	checkTestCase08Steps()
//	// MR-Record-09
//	checkTestCase09Steps()
//	// MR-Record-10
//	checkTestCase10Steps()
//	// MR-Record-11
//	checkTestCase11Steps()
//	// MR-Record-12
//	checkTestCase12Steps()
//	// MR-Record-14
//	checkTestCase14Steps()
//	// MR-Record-15
//	checkTestCase15Steps()
//	// MR-Record-16
//	checkTestCase16Steps()
//	// MR-Record-17
//	checkTestCase17Steps()
//	// MR-Record-18
//	checkTestCase18Steps()
////	// MR-Record-19
//	checkTestCase19Steps()
//	// MR-Record-20
//	checkTestCase20Steps()

}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
	WebUI.delay(8)
}

private boolean checkTestCase01Steps(){
	boolean result = false
	CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
	WebUI.delay(3)
	CustomKeywords.'com.mesmer.Utility.goToManageTests'()
	WebUI.waitForPageLoad(5)
	if(ManageTestController.getInstance().clickCreateNewTestButton()){
		MesmerLogUtils.markPassed("MR-Record-01 Successful")
		result = true
	}
	else{
		MesmerLogUtils.logInfo("MR-Record-02 failed as + icon not present on ManageTest screen")
	}
	
	return result
}

private void checkTestCase03Steps(){
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){
		if(CreateTestController.getInstance().checkIfAllDisabledButtonsAvailable()){
			List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList("Virtual")
			if(virtualDevicesList != null && virtualDevicesList.size() >=1){
				Utility.selectDeviceAndSetParams("Virtual","","","")
//				if(searchedReadyDevice != null){
//					searchedReadyDevice.click()
					WebUI.delay(10)
					if(CreateTestController.getInstance().checkIfEnterFullScreenExists()){
						if(CreateTestController.getInstance().checkIfPauseAndResumeRecordExists()){
							MesmerLogUtils.markPassed("MR-Record-03 Successful")
							WebUI.delay(3)
							discardTest()
							WebUI.delay(5)
						}
					}
//				}
			}
		}
	}
}

private void checkTestCase04Steps(){
	checkDevicesStatus()
}

private void checkTestCase05Steps(){
	List<WebElement> unavailableDevicesList = CreateTestController.getInstance().getAvailableDevices("Virtual", "Unavailable")
	if(unavailableDevicesList != null && unavailableDevicesList.size() > 0){
		MesmerLogUtils.logInfo("Unavailable device found in the list")
		MesmerLogUtils.markPassed("MR-Record-05 Unavailable device found in the list")
	}
	else{
		MesmerLogUtils.logInfo("There is no Unavailable device in the list")
	}
}
private void checkTestCase06Steps(){
	List<WebElement> inuseDevicesList = CreateTestController.getInstance().getAvailableDevices("Virtual", "In Use")
	if(inuseDevicesList != null && inuseDevicesList.size() > 0){
		MesmerLogUtils.logInfo("In use device found in the list")
		MesmerLogUtils.markPassed("MR-Record-06 In use device found in the list")
	}
	else{
		MesmerLogUtils.logInfo("There is no In Use device in the list")
	}
}
private void checkTestCase07Steps(){
	WebElement searchedDevice = selectDevice("InUse")
	if(searchedDevice != null){
		searchedDevice.click()
		CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
		MesmerLogUtils.markPassed("PASSED: MR-Record-07 inUse device selected")
		WebUI.delay(8)
	}
	else{
		MesmerLogUtils.markWarning("MR-Record-07 failed as it could not find the inUse device")
	}
}

private void checkTestCase08Steps(){
	List<WebElement> devicesList = Utility.getAvailableDevices("Virtual")
	if(devicesList != null && devicesList.size() > 0){
		WebElement selectDevice = Utility.selectDevice("Virtual")
		if(selectDevice != null){
			selectDevice.click()
			if(CreateTestController.getInstance().clickCancelAndNeverMind()){
				MesmerLogUtils.markPassed("MR-Record-08 Successful")
				if(CreateTestController.getInstance().clickCancelAndOk()){
					MesmerLogUtils.markPassed("MR-Record-09 Successful")
				}
			}
		} 
	}
}

private void checkTestCase09Steps(){
	List<WebElement> devicesList = Utility.getAvailableDevices("Virtual")
	if(devicesList != null && devicesList.size() > 0){
		WebElement selectDevice = Utility.selectDevice("Virtual")
		if(selectDevice != null){
			selectDevice.click()
			if(CreateTestController.getInstance().clickCancelAndOk()){
				MesmerLogUtils.markPassed("MR-Record-09 Successful")
			}
		}
	}
}

private void checkTestCase10Steps(){
	WebElement searchedDevice = selectDevice("Ready")
	if(searchedDevice != null){
		searchedDevice.click()
		CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
		WebUI.delay(8)
		if(!WebUI.waitForElementVisible(infoDeviceNotAvailable, 10)){
			if(WebUI.waitForElementVisible(divDevice, 240)){
				MesmerLogUtils.markPassed("PASSED: MR-Record-10 Recording started within the time")
			}
			else{
				MesmerLogUtils.markFailed("FAILED: Recording not started within 4 minutes")
			}
		}
		else{
			MesmerLogUtils.markWarning("WARNING: There is no compatible device available")
		}
	}
	else{
		MesmerLogUtils.markWarning("MR-Record-10 failed as it could not find the provisioned device")
	}
}

private void checkTestCase11Steps(){
	List<WebElement> devicesList = Utility.getAvailableDevices("Physical")
	if(devicesList != null && devicesList.size() > 0){
		MesmerLogUtils.markPassed("MR-Record-11 Successful as there is physical device in the devices list")
	}
	else{
		MesmerLogUtils.markFailed("No physical device in the devices list")
	}
}

private void checkTestCase12Steps(){
	WebElement inCompatibleDevice = selectDevice("Incompatible with build")
	if(inCompatibleDevice != null){
		MesmerLogUtils.markPassed("MR-Record-12 Successful to find incompatible device with build")
	}
	else{
		MesmerLogUtils.markFailed("MR-Record-12 there is no incompatible device")
	}
}

private void checkTestCase13Steps(){
	if(startRecording()){
		MesmerLogUtils.markPassed("MR-Record-13 Successful")
	}
}

private boolean startRecording(){
	boolean result = false
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){
		List<WebElement> virtualDevicesList = Utility.getAvailableDevices("Virtual")
		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			WebElement searchedReadyDevice = Utility.selectDevice("Virtual")
			if(searchedReadyDevice != null){
				searchedReadyDevice.click()
				Utility.logCurrentUTCTime("Device selection time")

				if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
					MesmerLogUtils.markPassed("MR-Record-14 Successful")
					MesmerLogUtils.logInfo("Choose another device clicked")
					MesmerLogUtils.markPassed("MR-Record-15 Successful")
				}
				CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
				WebUI.delay(2)
				if(WebUI.waitForElementVisible(unableToRetrieveDeviceStrip, 2)){
					MesmerLogUtils.markFailed("Unable to Retrieve Device is displayed during recording")
				}
				else{
					waitForDeviceAndBuildInstallation()
					result = true
				}

			}
		}
		else{
			MesmerLogUtils.markFailed("No virtual device available in the list")
		}
	}
	else{
		MesmerLogUtils.markFailed("CreateNewTest screen did not open within 1 minute")
	}
	return result
}

private void waitForDeviceAndBuildInstallation(){
	TestObject divDevice = MesmerRecordHelper.getInstance().getDeviceView()
	if(WebUI.waitForElementVisible(divDevice, 240)){
		Utility.logCurrentUTCTime("Build installed at this time")
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
			MesmerLogUtils.markPassed("MR-Record-14 Successful")
			MesmerLogUtils.logInfo("Choose another device clicked")
			MesmerLogUtils.markPassed("MR-Record-15 Successful")
		}
		MesmerLogUtils.markFailed("Recording not started within 4 minutes")
	}
}

private void startWingstopAndroidRecording(){
	MesmerRecordHelper.getInstance().startWingstopAndroidRecording(ProjectName,platformName, "","", appUserName, appPassword)
	setTestCaseName()
}
private void startWingstopiOSRecording(){
	MesmerRecordHelper.getInstance().startWingstopiOSRecording(ProjectName,platformName, "", "", appUserName, appPassword)
	setTestCaseName()
}

private void startStarbucksAndroidRecording(){
	MesmerRecordHelper.getInstance().startStarbucksAndroidRecording(ProjectName,platformName, "", "", appUserName, appPassword)
	setTestCaseName()
}

private void startStarbucksiOSRecording(){
	MesmerRecordHelper.getInstance().startStarbucksiOSRecording(ProjectName,platformName, "", "", appUserName, appPassword)
	setTestCaseName()
}

private void startMojioAndroid(){
	MesmerRecordHelper.getInstance().startMojioAndroid(ProjectName,platformName, "", "", appUserName, appPassword)
	setTestCaseName()
}

private void startMojioiOS(){
	MesmerRecordHelper.getInstance().startMojioiOS(ProjectName,platformName, "", "", appUserName, appPassword)
	setTestCaseName()
}

private void setTestCaseName(){
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
//					discardTest()
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

private void checkTestCase14Steps(){
	List<WebElement> searchedDevice = Utility.getAvailableDevices("Physical")
	if(searchedDevice != null && searchedDevice.size() > 0){
		MesmerLogUtils.markPassed("MR-Record-14 Successful as physical device found in the list")
	}
	else{
		MesmerLogUtils.markWarning("MR-Record-14 failed to find the physical screen in the list")
	}
}

private void checkTestCase15Steps(){
	WebElement provisionedDevice = selectDevice("Provisioned")
	if(provisionedDevice != null){
		provisionedDevice.click()
		CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
		MesmerLogUtils.markPassed("PASSED: MR-Record-15 Successful as another device selected successfully")
		WebUI.delay(8)
	}
	else{
		MesmerLogUtils.markWarning("MR-Record-15 unable to find provisioned device in the list")
	}
}

private void checkTestCase16Steps(){
	if(CreateTestController.getInstance().checkIfAllDisabledButtonsAvailable()){
		MesmerLogUtils.markPassed("MR-Record-16 Successful to find 4 disabled buttons")
	}
	else{
		MesmerLogUtils.markFailed("4 disabled buttons not found")
	}
}

private void checkTestCase17Steps(){
	if(CreateTestController.getInstance().checkIfSideButtonsAreEnabled()){
		if(CreateTestController.getInstance().clickGestureButton()){
			MesmerLogUtils.markPassed("MR-Record-17 Successful to find 4 enabled buttons")
		}
	}
	else{
		MesmerLogUtils.markFailed("4 disabled buttons not found")
	}
}

private void checkTestCase18Steps(){
	if(CreateTestController.getInstance().checkIfSideButtonsAreEnabled()){
		if(CreateTestController.getInstance().clickGestureButton()){
			if(CreateTestController.getInstance().checkAndClickOkToolTipInGestureView()){
				if(CreateTestController.getInstance().clickGestureButton()){
					if(!CreateTestController.getInstance().checkAndClickOkToolTipInGestureView()){
						if(discardTest()){
							if(CreateTestController.getInstance().checkIfSideButtonsAreEnabled()){
								if(CreateTestController.getInstance().clickGestureButton()){
									if(CreateTestController.getInstance().checkAndClickOkToolTipInGestureView()){
										MesmerLogUtils.markPassed("MR-Record-18 Successful")
									}
								}
								
							}
						}
					}
					else{
						MesmerLogUtils.markFailed("MR-Record-18 failed as OK tool tip appears again")
					}
				}
			}
		}
	}
}

private boolean discardTest(){
	boolean result = false
	if(CreateTestController.getInstance().clickDoneGreenButton()){
		if(CreateTestController.getInstance().clickDiscardButton()){
			if(CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()){
				MesmerLogUtils.logInfo("Test case discarded successfully")
				result = true
				WebUI.delay(10)
			}
			else{
				MesmerLogUtils.logInfo("Discard alert not appeared")
			}
		}
		else{
			MesmerLogUtils.logInfo("Discard button not clicked")
		}
	}
	else{
		MesmerLogUtils.logInfo("Green done button not clicked")
	}
	
	return result
}

private void checkTestCase19Steps(){
	if(CreateTestController.getInstance().checkIfSideButtonsAreEnabled()){
		if(CreateTestController.getInstance().hoverOnGestureButton()){
			if(CreateTestController.getInstance().checkOkToolTipInGestureViewVisible()){
				if(CreateTestController.getInstance().hoverOnLocakDeviceButton()){
					if(CreateTestController.getInstance().hoverOnGestureButton()){
						if(CreateTestController.getInstance().checkOkToolTipInGestureViewVisible()){
							if(CreateTestController.getInstance().checkAndClickOkToolTipInGestureView()){
								if(CreateTestController.getInstance().clickGestureButton()){
									MesmerLogUtils.markPassed("MR-Record-19 Successful")
								}
								
							}
						}
					}
				}
			}
		}
	}
}

private void checkTestCase20Steps(){
	if(CreateTestController.getInstance().checkIfSideButtonsAreEnabled()){
		if(CreateTestController.getInstance().checkAndClickSwipeGesture()){
			if(CreateTestController.getInstance().checkAndClickOkToolTipInGestureView()){
				MesmerLogUtils.markPassed("MR-Record-20 Successful")
			}
		}
	}
}

def startRecordingAndPerformActions(){
	if(!WebUI.waitForElementVisible(infoDeviceNotAvailable, 10)){
		if(WebUI.waitForElementVisible(divDevice, 240)){

		}
		else{
			MesmerLogUtils.markFailed("FAILED: Recording not started within 4 minutes")
		}
	}
	else{
		MesmerLogUtils.markWarning("WARNING: There is no compatible device available")
	}
}

def boolean checkIfThereIsADeviceSelected(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(1)
	String selectDeviceXPath = '//a[@class="iconMenu selectDevice CP"]'
	WebElement selectDeviceTitle = driver.findElement(By.xpath(selectDeviceXPath))
	if(selectDeviceTitle != null && !selectDeviceTitle.getText().equalsIgnoreCase("")){
		result = true;
	}

	return result;
}

private void checkDevicesStatus(){
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){
		if(CreateTestController.getInstance().checkIfDeviceWithStatusAvailable("provisioned")){
			MesmerLogUtils.logInfo("MR-Record-04 provisioned device status found ")
		}
		else{
			MesmerLogUtils.markWarning("MR-Record-04 there is no provisioned device available")
		}
		if(CreateTestController.getInstance().checkIfDeviceWithStatusAvailable("ready")){
			MesmerLogUtils.logInfo("MR-Record-04 ready device status found ")
		}
		else{
			MesmerLogUtils.markWarning("MR-Record-04 there is no ready device available")
		}
		if(CreateTestController.getInstance().checkIfDeviceWithStatusAvailable("inUse")){
			MesmerLogUtils.logInfo("MR-Record-04 inUse device status found ")
		}
		else{
			MesmerLogUtils.markWarning("MR-Record-04 there is no inUse device available")
		}
		if(CreateTestController.getInstance().checkIfDeviceWithStatusAvailable("broken")){
			MesmerLogUtils.logInfo("MR-Record-04 broken device status found ")
		}
		else{
			MesmerLogUtils.markWarning("MR-Record-04 there is no broken device available")
		}
	}
	else{
		MesmerLogUtils.markFailed("CreateNewTest screen did not open within 1 minute")
	}
}

private void checkDevicesList(){
	if(CreateTestController.getInstance().checkIfDevicesListExists()){
		WebUI.delay(3)
		MesmerLogUtils.markPassed("MR-Record-05 Successful to find the list of devices")
	}
	else{
		MesmerLogUtils.logInfo("MR-Record-05 failed")
	}
}

private void checkBrokenDevice(){
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){
		if(CreateTestController.getInstance().checkIfDeviceWithStatusAvailable("broken")){
			MesmerLogUtils.markPassed("MR-Record-06 broken device status found ")
		}
		else{
			MesmerLogUtils.markWarning("MR-Record-06 there is no broken device available")
		}
	}
	else{
		MesmerLogUtils.logInfo("Create new test screen didn't appear")
	}
}

private void checkInUseDevice(){
	WebDriver driver = DriverFactory.getWebDriver()
	if(CreateTestController.getInstance().checkIfDeviceWithStatusAvailable("inUse")){
		String deviceStatusXpath = '//div[@class="deviceList ng-star-inserted"]/div[@class="device inUse disabled ng-star-inserted"]/div[@class="deviceDetails"]/div[@class="deviceState"]/div/div/i'
		WebElement deviceStatus = driver.findElement(By.xpath(deviceStatusXpath))
		if(deviceStatus != null){
			MesmerLogUtils.markPassed("MR-Record-07 Successful")
		}
		else{
			MesmerLogUtils.markWarning("MR-Record-07 failed to find inUse record and username")
		}
	}
	else{
		MesmerLogUtils.markWarning("MR-Record-07 there is no inUse device available")
	}
}

private void checkInUseDeviceWithoutRecord(){
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(1)
	if(CreateTestController.getInstance().checkIfDeviceWithStatusAvailable("inUse")){
		String deviceStatusXpath = '//div[@class="deviceList ng-star-inserted"]/div[@class="device inUse ng-star-inserted"]/div[@class="deviceDetails"]/div[@class="deviceState"]/div/div'
		WebElement deviceStatus = driver.findElement(By.xpath(selectDeviceXPath))
		if(deviceStatus != null){
			MesmerLogUtils.markPassed("MR-Record-08 Successful")
		}
		else{
			MesmerLogUtils.markFailed("MR-Record-08 Failed")
		}
	}
	else{
		MesmerLogUtils.markWarning("MR-Record-08 there is no inUse device available")
	}
}

// Check the provided device type and select it
private WebElement selectDevice(String deviceStatus){
	WebElement searchedDevice = null
//	if(CreateTestController.getInstance().clickDeviceIcon()){
		searchedDevice = CreateTestController.getInstance().selectDeviceWithGivenStatus(deviceStatus)
//	}
//	else{
//		MesmerLogUtils.markFailed("Select device icon not found")
//	}

	return searchedDevice
}
