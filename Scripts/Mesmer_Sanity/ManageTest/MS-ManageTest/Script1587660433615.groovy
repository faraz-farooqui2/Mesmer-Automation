import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MS-Manage Tests-01 | Verify that user is shown all test cases in 'Manage Tests' page with their respective statuses.
 * MS-Manage Tests-02 | Verify that filter counts in 'Manage Test' are as per Test result page
 * MS-Manage Tests-03 | Verify that user can Start executions from Manage Test page
 * MS-Manage Tests-04 | Verify that user should be able to Copy/Move test cases to another project of same platform from 'Manage Test' page.
 * MS-Manage Tests-05 | Verify that user should be able to use all the available options for a test result like Comments, Assignee,Duplicate test, Download Assets,Delete.
 * MS-Manage Tests-06 | Verify that user should be able to Edit Test Results																	
 */

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToTestResults'()
testResultTestsCount = TestResultController.getInstance().getTestResultTestCasesCount()
CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.delay(2)

try{
	if(ManageTest01()){
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-01",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-01",Utility.getPlatformName(),2)
	}
	if(ManageTest02()){
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-02",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-02",Utility.getPlatformName(),2)
	}
	if(ManageTest03()){
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-03",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-03",Utility.getPlatformName(),2)
	}
	if(ManageTest04()){
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-04",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-04",Utility.getPlatformName(),2)
	}
	if(ManageTest05()){
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-05",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-05",Utility.getPlatformName(),2)
	}
	if(ManageTest06()){
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-06",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-ManageTest-06",Utility.getPlatformName(),2)
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}

/**
 * MS-ManageTest-01
 * @return
 */
private boolean ManageTest01(){
	boolean result = false
	int manageTestsCount = ManageTestController.getInstance().getAllTestCasesStatusCount()
	List<WebElement> testCasesList = ManageTestController.getInstance().getTestCasesList()
	if((manageTestsCount == testResultTestsCount) && (testCasesList != null && (testCasesList.size()-1) == testResultTestsCount)){
		result = true
		MesmerLogUtils.markPassed("MS-ManageTest-01 Passed")
	}
	else{
		MesmerLogUtils.logInfo("ManageTest and test result page count does not match")
	}
	
	return result
}
/**
 * MS-ManageTest-02
 * @return
 */
private boolean ManageTest02(){
	boolean result = false
	if(ManageTestController.getInstance().verifyManageTestStatuses()){
		int manageTestsCount = ManageTestController.getInstance().getAllTestCasesStatusCount()
		if(manageTestsCount == testResultTestsCount){
			result = true
			MesmerLogUtils.markPassed("MS-ManageTest-02 Passed")
		}
		else{
			MesmerLogUtils.logInfo("ManageTest statuses verification failed as total test cases does not macth on test result screen")
		}
	}
	else{
		MesmerLogUtils.logInfo("ManageTest statuses verification failed")
	}
	
	return result
}

/**
 * MS-ManageTest-03
 * @return
 */
private boolean ManageTest03(){
	boolean result = false
	if(ManageTestController.getInstance().selectRandomTestCases()){
		if(ManageTestController.getInstance().clickReRunButton()){
			if(ManageTestController.getInstance().checkIfSelectDeviceHeaderVisible()){
				List<WebElement> virtualDevicesList = Utility.getAvailableDevices("Virtual")
				if(virtualDevicesList != null && virtualDevicesList.size() >=1){
					WebElement searchedReadyDevice = Utility.selectDevice("Virtual")
					if(searchedReadyDevice != null){
						searchedReadyDevice.click()
						if(ManageTestController.getInstance().clickButtonRun()){
							if(ManageTestController.getInstance().checkRunConfirmationDialog()){
								if(ManageTestController.getInstance().clickButtonYes()){
									result = true
									MesmerLogUtils.markPassed("MS-ManageTest-03 Passed")
								}
								else{
									MesmerLogUtils.logInfo("Yes button not clicked")
								}
							}
							else{
								MesmerLogUtils.logInfo("Confirmation dialog not appeared")
							}
						}
						else{
							MesmerLogUtils.logInfo("Run botton not found or not clicked")
						}
					}
				}
				else{
					MesmerLogUtils.logInfo("No virtual device available in the list")
				}
			}
			else{
				MesmerLogUtils.logInfo("Select device header not found")
			}
		}
		else{
			MesmerLogUtils.logInfo("ManageTest statuses verification failed as total test cases does not macth on test result screen")
		}
	}
	else{
		MesmerLogUtils.logInfo("Test cases not selected or not available in the list")
	}
	
	return result
}

/**
 * MS-ManageTest-04
 * @return
 */
private boolean ManageTest04(){
	boolean result = false
	if(ManageTestController.getInstance().selectRandomTestCases()){
		if(ManageTestController.getInstance().clickButtonCopy()){
			if(ManageTestController.getInstance().checkIfCopyToOtherProjectPresent()){
				if(ManageTestController.getInstance().clickSelectProjectTick()){
					if(ManageTestController.getInstance().clickCopyAllTestCases()){
						if(ManageTestController.getInstance().clickCopyAllTestCasesConfirmationYes()){
							result = true
							MesmerLogUtils.markPassed("MS-ManageTest-04 Passed")
						}
						else{
							MesmerLogUtils.logInfo("Copy all test cases confirmation yes button not clicked")
						}
					}
					else{
						MesmerLogUtils.logInfo("Copy all test cases not clicked")
					}
				}
				else{
					MesmerLogUtils.logInfo("Project select tick not found")
				}
			}
			else{
				MesmerLogUtils.logInfo("Copy to other project text not found")
			}
		}
		else{
			MesmerLogUtils.logInfo("Copy button not clicked")
		}
	}
	else{
		MesmerLogUtils.logInfo("Test cases not selected or not available in the list")
	}
	
	return result
}
/**
 * MS-ManageTest-05
 * @return
 */
private boolean ManageTest05(){
	boolean result = false
	// Find the test case and clone it
	if(ManageTestController.getInstance().findTestCaseAndPerformAction(testCaseName1,"clone")){
		// Verify if the confirmation dialog appears
		if(ManageTestController.getInstance().verifyDuplicateTestCaseAlert()){
			WebUI.delay(4)
			// Find the test case and download assests
			if(ManageTestController.getInstance().findTestCaseAndPerformAction(testCaseName1,"download")){
				// Find the test case and try to delete it
				if(ManageTestController.getInstance().findTestCaseAndPerformAction(testCaseName1,"comments")){
					WebUI.delay(4)
					// Move to Managetests again
					CustomKeywords.'com.mesmer.Utility.goToManageTests'()
					WebUI.delay(4)
					// Verify if the delete confirmation dialog appears
//					if(ManageTestController.getInstance().clickDeleteTestCaseYes()){
						// Find and click comment icon
						if(ManageTestController.getInstance().findTestCaseAndPerformAction(testCaseName1,"assignUser")){
							// Check the assignedUser and change it
							if(ManageTestController.getInstance().findTestCaseAndPerformAction(testCaseName1,"delete")){
								result = true
								MesmerLogUtils.markPassed("MS-ManageTest-05 Passed")
							}
							else{
								MesmerLogUtils.logInfo("Unable to delete the provided test case")
							}
						}
						else{
							MesmerLogUtils.logInfo("Unable to assign the user to test case")
						}
//					}
//					else{
//						MesmerLogUtils.logInfo("Delete dialog yes button not found")
//					}
				}
				else{
					MesmerLogUtils.logInfo("Unable to comment on provided test case")
				}
			}
			else{
				MesmerLogUtils.logInfo("Unable to download the provided test case")
			}
		}
		else{
			MesmerLogUtils.logInfo("Duplicate test case alert not appeared")
		}
	}
	else{
		MesmerLogUtils.logInfo("Unable to clone the provided test case")
	}
	
	return result
}
/**
 * MS-ManageTest-06
 * @return
 */
private boolean ManageTest06(){
	boolean result = false
	if(ManageTestController.getInstance().findTestCaseAndPerformAction(testCaseName2,"edit")){
		WebUI.delay(5)
		// Select reDoFromHere Option
		if(ManageTestController.getInstance().checkAndClickTheScreenShot("reDoFromHere")){
			// Select Add assertion option
			if(ManageTestController.getInstance().checkAndClickTheScreenShot("assertions")){
				// Select data option
				if(ManageTestController.getInstance().checkAndClickTheScreenShot("data")){
					// Select delete option
					if(ManageTestController.getInstance().checkAndClickTheScreenShot("delete")){
						result = true
						MesmerLogUtils.markPassed("MS-ManageTest-06 Passed")
					}
					else{
						MesmerLogUtils.logInfo("Unable to delete the screenshot")
					}
				}
				else{
					MesmerLogUtils.logInfo("Unable to find data option")
				}
			}
			else{
				MesmerLogUtils.logInfo("Unable to find assertion option")
			}
		}
		else{
			MesmerLogUtils.logInfo("Unable to edit the provided test case")
		}
	}
	else{
		MesmerLogUtils.logInfo("Unable to edit the provided test case")
	}
	return result
}

