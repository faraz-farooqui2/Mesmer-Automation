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
	// MR-SingleTestCaseScreen-21
//	singleTestCase21("ManageTest-06")
//	//	navigateToTestResult()
//	// MR-SingleTestCaseScreen-22
//	singleTestCase22("ManageTest-0")
//	//	navigateToTestResult()
//	// MR-SingleTestCaseScreen-23
//	singleTestCase23("ManageTest-06")
//	//	navigateToTestResult()
//	// MR-SingleTestCaseScreen-24
//	singleTestCase24("ManageTest-06")
//		navigateToTestResult()
	 // MR-SingleTestCaseScreen-25
	singleTestCase25("ManageTest-06")
	//	navigateToTestResult()
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	navigateToTestResult()
}

private void navigateToTestResult(){
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.waitForPageLoad(5)
}

private boolean singleTestCase21(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestDetailsController.getInstance().clickBaselineEditTestIcon()){
			if(TestResultController.getInstance().editTestCaseName(testCaseName+" Updated","clickTitle")){
				result = true
				MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-21 Passed")
				WebUI.delay(2)
			}
		}
	}
	return result
}

private boolean singleTestCase22(String testCaseName){
	boolean result = false
	//	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
	WebUI.delay(5)
	if(TestResultController.getInstance().editTestCaseName("ManageTest-06","clickEditIcon")){
		result = true
		MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-22 Passed")
		WebUI.delay(2)
	}
	//	}
	return result
}

private boolean singleTestCase23(String testCaseName){
	boolean result = false
	//	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
	WebUI.delay(5)
	if(TestResultController.getInstance().clickTestCaseTitleName()){
		if(TestResultController.getInstance().setTitleNameWithoutEnter(testCaseName+" Updating")){
			if(TestResultController.getInstance().clickEditCloseIcon()){
				if(TestResultController.getInstance().getTestCaseTitleName().equalsIgnoreCase(testCaseName)){
					result = true
					MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-23 Passed")
					WebUI.delay(2)
				}
			}
		}
	}
	//	}
	return result
}

private boolean singleTestCase24(String testCaseName){
	boolean result = false
//	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestResultController.getInstance().clickTestCaseTitleName()){
			if(TestResultController.getInstance().clearTitleInputField()){
				if(TestResultController.getInstance().sendEnterKey()){
					WebUI.delay(2)
					if(TestResultController.getInstance().getTestCaseTitleName().equalsIgnoreCase(testCaseName)){
						result = true
						MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-24 Passed")
						WebUI.delay(2)
					}
				}
			}
		}
//	}
	return result
}

private boolean singleTestCase25(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestDetailsController.getInstance().clickBaselineTitleSection()){
			if(TestDetailsController.getInstance().checkIfBaselineExpandDeviceViewVisible()){
				if(TestDetailsController.getInstance().clickBaselineTitleSection()){
					result = true
					MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-25 Passed")
					WebUI.delay(2)
				}
			}
			else{
				if(TestDetailsController.getInstance().clickBaselineTitleSection()){
					if(TestDetailsController.getInstance().checkIfBaselineExpandDeviceViewVisible()){
						result = true
						MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-25 Passed")
						WebUI.delay(2)
					}
				}
			}
		}
	}
	return result
}

private boolean singleTestCase26(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		List<WebElement> testRunsList = TestDetailsController.getInstance().getTestRunsList()
		if(testRunsList.size() > 0){
			if(TestDetailsController.getInstance().expandTestRunsTest(0)){
				if(TestDetailsController.getInstance().checkIfTestRunsTestExpanded(0)){
					result = true
					MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-26 Passed")
					WebUI.delay(2)
				}
				else{
					if(TestDetailsController.getInstance().expandTestRunsTest(0)){
						if(TestDetailsController.getInstance().checkIfTestRunsTestExpanded(0)){
							result = true
							MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-26 Passed")
							WebUI.delay(2)
						}
					}
				}
			}
		}
	}
	return result
}

private boolean singleTestCase27(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestDetailsController.getInstance().checkIfBaselineDeviceElementsInfoExists()){
			result = true
			MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-27 Passed")
			WebUI.delay(2)
		}
	}
	return result
}

private boolean singleTestCase28(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		List<WebElement> testRunsList = TestDetailsController.getInstance().getTestRunsList()
		if(testRunsList.size() > 0){
			if(TestDetailsController.getInstance().checkIfTestRunsDeviceInfoElementsExists(0)){
				result = true
				MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-28 Passed")
				WebUI.delay(2)
			}
		}
	}
	return result
}

private boolean singleTestCase29(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		List<WebElement> testRunsList = TestDetailsController.getInstance().getTestRunsList()
		if(testRunsList.size() > 0){
			if(TestDetailsController.getInstance().clickRunTestIconInTestRuns(0)){
				result = true
				MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-29 Passed")
				WebUI.delay(2)
			}
		}
	}
	return result
}

private boolean singleTestCase30(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestResultController.getInstance().clickReplayButton()){
			if(TestResultController.getInstance().checkDevicesElements()){
				result = true
				MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-30 Passed")
				WebUI.delay(2)
			}
		}
	}
	return result
}

private boolean singleTestCase31(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestDetailsController.getInstance().expandBaseLineScreensList()){
			List<WebElement> baselineTestRunsList = TestDetailsController.getInstance().getBaselineTestCaseScreensList()
			if(baselineTestRunsList != null && baselineTestRunsList.size() > 0){
				if(TestDetailsController.getInstance().clickBaselineRightArrow()){
					result = true
					MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-31 Passed")
					WebUI.delay(5)
				}
			}
		}
	}
	return result
}
private boolean singleTestCase33(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestResultController.getInstance().clickDotsOptionButton()){
			if(TestResultController.getInstance().clickDropDownOption("Download Results")){
				result = true
				MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-33 Passed")
				WebUI.delay(5)
			}
		}
	}
	return result
}

private boolean singleTestCase37(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestResultController.getInstance().checkDotsDropDownOptions()){
			if(TestResultController.getInstance().clickDropDownOption("Show Comments")){
				if(TestResultController.getInstance().addCommentToTextArea("Test Comment")){
					result = true
					MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-37 Passed")
					WebUI.delay(5)
				}
			}
			else{
				MesmerLogUtils.logInfo("Download results option not clicked")
			}
		}
		else{
			MesmerLogUtils.markFailed("3 dots option items not found")
		}

	}
	return result
}

private boolean singleTestCase39(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "delete")){
		WebUI.delay(5)
		result = true
		MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-39 Passed")
		WebUI.delay(5)

	}
	return result
}

private boolean singleTestCase45(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "delete")){
		WebUI.delay(5)
		List<WebElement> testRunsList = TestDetailsController.getInstance().getTestRunsList()
		if(testRunsList.size() > 0){
			if(TestDetailsController.getInstance().click3DotsOfTestRunsTest(0)){
				if(TestDetailsController.getInstance().checkAndClickPopOverOption("download results")){
					result = true
					MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-45 Passed")
					WebUI.delay(5)
				}
			}
		}

	}
	return result
}

private boolean singleTestCase41(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		List<WebElement> testRunsList = TestDetailsController.getInstance().getTestRunsList()
		if(testRunsList.size() > 0){
			if(TestDetailsController.getInstance().expandTestRunsTest(0)){
				if(TestDetailsController.getInstance().checkIfTestRunsTestExpanded(0)){
					result = true
					WebUI.delay(2)
				}
				else{
					if(TestDetailsController.getInstance().expandTestRunsTest(0)){
						if(TestDetailsController.getInstance().checkIfTestRunsTestExpanded(0)){
							result = true
							WebUI.delay(2)
						}
					}
				}
			}
			if(result){
				result = false
				if(TestDetailsController.getInstance().click3DotsOfTestRunsTest(0)){
					if(TestDetailsController.getInstance().checkAndClickPopOverOption("watch video")){
						result = true
						MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-41 Passed")
						WebUI.delay(5)
					}
				}
			}
		}
	}
	return result
}

private boolean singleTestCase44(String testCaseName){
	boolean result = false
	CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()
	WebUI.delay(5)
	if(RecommendedTestCaseController.getInstance().findElementAndPerformAction(testCaseName, "acceptTest")){
		WebUI.delay(5)
		navigateToTestResult()
		WebUI.delay(5)
		if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
			WebUI.delay(5)
			if(TestResultController.getInstance().clickBaselineReplayedTestDotsIcon(1)){
				if(!TestResultController.getInstance().clickThePopOverActions("Watch Video")){
					if(TestResultController.getInstance().clickBaselineReplayedTestDotsIcon(2)){
						if(TestResultController.getInstance().clickThePopOverActions("Watch Video")){
							WebUI.delay(10)
							if(TestResultController.getInstance().clickVideoDialogCloseButton()){
								result = true
								MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-43 Passed")
								WebUI.delay(5)
							}
						}
					}
				}
				else{
					MesmerLogUtils.markFailed("Download results icon not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("Baseline 3 dots icon not clicked")
			}

		}
	}
	return result
}


private boolean singleTestCase43(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		List<WebElement> testRunsList = TestDetailsController.getInstance().getTestRunsList()
		if(testRunsList.size() > 0){
			if(TestDetailsController.getInstance().expandTestRunsTest(0)){
				if(TestDetailsController.getInstance().checkIfTestRunsTestExpanded(0)){
					result = true
					WebUI.delay(2)
				}
				else{
					if(TestDetailsController.getInstance().expandTestRunsTest(0)){
						if(TestDetailsController.getInstance().checkIfTestRunsTestExpanded(0)){
							result = true
							WebUI.delay(2)
						}
					}
				}
			}
			if(result){
				result = false
				if(TestDetailsController.getInstance().click3DotsOfTestRunsTest(0)){
					if(TestDetailsController.getInstance().checkAndClickPopOverOption("watch video")){
						if(TestResultController.getInstance().checkIfVideoViewLoaded()){
							if(TestResultController.getInstance().clickVideoPlayButton()){
								WebUI.delay(10)
								if(TestResultController.getInstance().clickVideoDialogCloseButton()){
									result = true
									MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-44 Passed")
									WebUI.delay(5)
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

private boolean singleTestCase48(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(reRunATestCase()){
			WebUI.delay(10)
			TestResultController.getInstance().clickRerunButton()
			if(TestResultController.getInstance().checkIfReRunInProgressAlertAppears()){
				result = true
				MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-48 Passed")
				WebUI.delay(2)
			}
			else if(TestResultController.getInstance().checkIfReRunInInQueueDialogAppears()){
				if(TestResultController.getInstance().clickGotItButton()){
					WebUI.delay(10)
					TestResultController.getInstance().clickRerunButton()
					if(TestResultController.getInstance().checkIfReRunInProgressAlertAppears()){
						result = true
						MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-48 Passed")
						WebUI.delay(2)
					}
				}
			}
		}
	}
	return result
}

private boolean singleTestCase50(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(reRunATestCase()){
			WebUI.delay(10)
			TestResultController.getInstance().clickRerunButton()
			if(TestResultController.getInstance().checkIfReRunInInQueueDialogAppears()){
				if(TestResultController.getInstance().clickCancelRunButton()){
					result = true
					MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-50 Passed")
					WebUI.delay(5)
				}
			}
		}
	}
	return result
}

private boolean singleTestCase52(String testCaseName){
	boolean result = false
	if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "")){
		WebUI.delay(5)
		if(TestResultController.getInstance().clickRerunButton()){
			WebUI.delay(4)
			if(ManageTestController.getInstance().checkIfNoDeviceTextVisible()){
				result = true
				MesmerLogUtils.markPassed("MR-SingleTestCaseScreen-52 Passed")
				WebUI.delay(5)
			}
		}
	}
	return result
}

private boolean reRunATestCase(){
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

