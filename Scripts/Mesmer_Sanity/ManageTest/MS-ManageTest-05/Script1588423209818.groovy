import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MS-Manage Tests-05 | Verify that user should be able to use all the available options for a test result like Comments, Assignee,Duplicate test, Download Assets,Delete.																	
 */



try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()
		testResultTestsCount = TestResultController.getInstance().getTestResultTestCasesCount()
		CustomKeywords.'com.mesmer.Utility.goToManageTests'()
		WebUI.delay(2)

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
								MesmerLogUtils.markFailed("Unable to delete the provided test case")
							}
						}
						else{
							MesmerLogUtils.markFailed("Unable to assign the user to test case")
						}
						//					}
						//					else{
						//						MesmerLogUtils.logInfo("Delete dialog yes button not found")
						//					}
					}
					else{
						MesmerLogUtils.markFailed("Unable to comment on provided test case")
					}
				}
				else{
					MesmerLogUtils.markFailed("Unable to download the provided test case")
				}
			}
			else{
				MesmerLogUtils.markFailed("Duplicate test case alert not appeared")
			}
		}
		else{
			MesmerLogUtils.markFailed("Unable to clone the provided test case")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()

}
