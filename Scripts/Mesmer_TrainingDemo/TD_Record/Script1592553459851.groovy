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
Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforeStartingTestCase")
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterNavigatingToCreateNewTestCase")
WebUI.delay(2)
WebUI.waitForPageLoad(5)


try{
	isTestReadyToExecute = startRecording()
}
catch(Exception e){
	e.printStackTrace()
	Utility.takeScreenshot(NewRecordPath, SrNo +"-InCatchBlock")
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
	Utility.takeScreenshot(NewRecordPath, SrNo +"-InFinallyBlock")
	Utility.logCurrentUTCTime("Final Block Time" + SrNo + " ")


	//	Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforeGoingToTestResults")
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.delay(2)
	CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
	Utility.takeScreenshot(NewRecordPath, SrNo +"-AfterNavigateToTestResults")
	KTRequestHandler.getXHRCalls()
	KTRequestHandler.getBrowserConsoleLogs()
	KTRequestHandler.clearBrowserConsoleLogs()

}

private boolean startRecording(){
	boolean result = false
	if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){
		Utility.takeScreenshot(NewRecordPath, SrNo +"-DevicesListOpen")
		List<WebElement> virtualDevicesList = Utility.getAvailableDevices(Device.toString())
		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			WebElement searchedReadyDevice = Utility.selectDevice(Device.toString())
			if(searchedReadyDevice != null){
				searchedReadyDevice.click()
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
				if(CreateTestController.getInstance().checkIfChooseAnotherDeviceBtnVisible()){
					isTestReadyToExecute = startRecording()
				}
				Utility.takeScreenshot(NewRecordPath, SrNo +"-DiscardAlertAppearedAfterDeviceSelection")
				CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
				WebUI.delay(2)
				if(WebUI.waitForElementVisible(unableToRetrieveDeviceStrip, 2)==true){
					Utility.takeScreenshot(NewRecordPath, SrNo +"-UnableToRetrieveDevice")
					MesmerLogUtils.markFailed("Unable to Retrieve Device is displayed during recording")
				}
				else{
					Utility.takeScreenshot(NewRecordPath, SrNo +"-BeforewaitForDeviceAndBuildInstallation")
					waitForDeviceAndBuildInstallation()
					result = true
				}

			}
		}
		else{
			Utility.takeScreenshot(NewRecordPath, SrNo +"-NoDeviceAvailable")
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
		Utility.takeScreenshot(NewRecordPath, SrNo +"-RecordingNotStartedWitin4Minutes")
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
		Utility.takeScreenshot(NewRecordPath, SrNo +"-noDeviceAvailable")
		result = true
	}
	return result
}

private void setTestCaseName(){
	moveBrowserDown()
//	String tcName = ProjectName+" "+platformName+" "+SrNo
	String tcName = testCaseName
	if(CreateTestController.getInstance().clickDoneGreenButton()){
		if(CreateTestController.getInstance().checkIfSaveTestAlertAppeared()){
			if(CreateTestController.getInstance().setRecordedTestCaseName(tcName)){
				Utility.takeScreenshot(NewRecordPath, SrNo +"-SetTestCaseName")
				if(CreateTestController.getInstance().saveNewTestCase()){
					if(CreateTestController.getInstance().clickOnReleaseDeviceAndCreateNew()){
						MesmerLogUtils.logInfo("Test case recorded successfully")
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

private void deleteTestCase(){
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.waitForPageLoad(10)
	Utility.takeScreenshot(NewRecordPath, SrNo +"-TestResultScreen")
	WebUI.delay(2)
	CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()
	WebUI.delay(2)
	String testcaseName = ProjectName+" "+platformName+" "+SrNo
	findTestCaseAndPerformAction(testcaseName,"selectTestCase","delete")
}

private void findTestCaseAndPerformAction(String testCaseName, String actionName, String subActionName){
	if(testCasesDivXPath != null && testCasesDivXPath.findPropertyValue("xpath") != null){
		String testResultTestCasesDivXPath = testCasesDivXPath.findPropertyValue("xpath")
		WebDriver driver = DriverFactory.getWebDriver()
		if(testCasesListXPath != null && testCasesListXPath.findPropertyValue("xpath") != null){
			String testResultTestCasesXPath = testCasesListXPath.findPropertyValue("xpath")
			List<WebElement> testCasesList = driver.findElements(By.xpath(testResultTestCasesXPath))
			if(testCasesList != null && testCasesList.size() > 0){
				for(int i= 0; i < testCasesList.size(); i++){
					String titleXPath = '//app-test-results-tiles[@class="vScrollCards"]/div/a['+(i+1)+']/descendant::div[@class="mat-card-title"]/span[contains(@class,"ng-star-inserted")]'
					WebElement testCaseTitle = driver.findElement(By.xpath(titleXPath))
					if(testCaseTitle != null && testCaseTitle.getText().equals(testCaseName)){
						WebUI.delay(2)
						TestObject to = new TestObject("objectName")
						to.addProperty("xpath", ConditionType.EQUALS,'//app-test-results-tiles[@class="vScrollCards"]/div/a['+(i+1)+']/descendant::div[@class="mat-card-title"]/span[contains(@class,"ng-star-inserted") and text()="'+testCaseName+'"]')
						WebUI.scrollToElement(to, 10)
						WebUI.delay(1)
						WebUI.mouseOver(to)
						WebUI.delay(1)
						if(actionName == "selectTestCase"){
							String checkBoxXPath = '//app-test-results-tiles[@class="vScrollCards"]/div/a['+(i+1)+']/descendant::div[@class="round"]'
							WebElement checkBox = driver.findElement(By.xpath(checkBoxXPath))
							if(checkBox != null){
								//								checkBox.click()
								//								WebUI.delay(2)
								WebUI.click(to)
								WebUI.delay(5)
								if(subActionName == "delete"){
									deleteTest()
								}
								break
							}
							else{
								MesmerLogUtils.markFailed('Checkbox not found')
							}
						}
					}
				}
			}
			else{
				MesmerLogUtils.markWarning('There is no test case in the list')
			}
		}
		else{
			MesmerLogUtils.markWarning('There is no test case in the list')
		}
	}
	else{
		MesmerLogUtils.markWarning('Test cases container not found')
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
		MesmerLogUtils.markFailed("Button 3 dots not found")
	}
}