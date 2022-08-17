import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.RecommendedTestCaseController
import controllers.TestDetailsController
import controllers.TestResultController

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
//1. Observe test result page
navigateToTestResult()

try{
	// MR-SingleTestCaseScreen-56
	singleTestCase56("Recommended Test Case 1")
	navigateToTestResult()
	// MR-SingleTestCaseScreen-58
	singleTestCase58("Recommended Test Case 1 Updated")
	navigateToTestResult()
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	
}

private void navigateToTestResult(){
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.waitForPageLoad(5)
}

private boolean singleTestCase56(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		List<WebElement> screensList = TestResultController.getInstance().getReplayedTestScreensList()
		if(screensList != null && screensList.size() > 1){
			screensList.get(1).click()
			WebUI.delay(2)
			if(TestDetailsController.getInstance().checkIfScreenResultsWindowOpen()){
				if(TestDetailsController.getInstance().checkIfAsserrtionsBtnAvailable()){
					result = true
					MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-56 Passed")
					WebUI.delay(2)
				}
			}
		}
	}
	return result
}

private boolean singleTestCase57(String testCaseName){
	// TODO
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestResultController.getInstance().editTestCaseName("Recommended Test Case 1","clickEditIcon")){
			result = true
			MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-57 Passed")
			WebUI.delay(2)
		}
	}
	return result
}

private boolean singleTestCase58(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		List<WebElement> screensList = TestDetailsController.getInstance().checkIfAScreenWithErrorExists()
		if(screensList != null && screensList.size() > 1){
			screensList.get(0).click()
			WebUI.delay(2)
			if(TestDetailsController.getInstance().checkIfScreenResultsWindowOpen()){
				if(TestDetailsController.getInstance().clickShowAllIssuesButton()){
					WebUI.delay(2)
					if(TestDetailsController.getInstance().clickShowAllIssuesButton()){
						result = true
						MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-58 Passed")
						WebUI.delay(2)
					}
				}
			}
		}
	}
	return result
}

private boolean singleTestCase59(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		List<WebElement> screensList = TestResultController.getInstance().getReplayedTestScreensList()
		if(screensList != null && screensList.size() > 1){
			screensList.get(1).click()
			WebUI.delay(2)
			if(TestDetailsController.getInstance().checkIfScreenResultsWindowOpen()){
				if(TestDetailsController.getInstance().clickAssertionsButton()){
					WebUI.delay(2)
					if(TestDetailsController.getInstance().checkIfAssertionsWindowOpen()){
						if(TestDetailsController.getInstance().clickAssertionTypeText()){
							if(TestDetailsController.getInstance().clickTheAssertionType("Wait")){
								if(TestDetailsController.getInstance().clickAndInsertWaitAssertionInputField("1")){
									result = true
									MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-59 Passed")
									WebUI.delay(2)
								}
							}
						}
					}
				}
			}
		}
	}
	return result
}

private void reRunATestCase(){
	boolean result = false
	if(TestResultController.getInstance().clickRerunButton()){
		WebUI.delay(4)
		List<WebElement> devicesList = Utility.getAvailableDevices("Virtual")
		if(devicesList != null && devicesList.size() > 1){
			WebElement selectedDevice = Utility.selectDevice("Virtual")
			if(selectedDevice != null){
				selectedDevice.click()
				WebUI.delay(2)
				if(TestResultController.getInstance().clickRunButton()){
					if(TestResultController.getInstance().clickYesButton()){
						result = true
						WebUI.delay(5)
					}
				}
			}
		}
		else{
			MesmerLogUtils.markFailed("No virtual device found in the list")
		}
	}
	
	return result
}

