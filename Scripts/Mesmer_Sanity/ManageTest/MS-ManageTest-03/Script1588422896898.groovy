import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.ReplayController
import controllers.TestResultController

/*
 * MS-Manage Tests-03 | Verify that user can Start executions from Manage Test page																
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

		if(ManageTestController.getInstance().selectRandomTestCases()){
			if(ManageTestController.getInstance().clickReRunButton()){
				if(ReplayController.getInstance().selectDevice()){

					if(ManageTestController.getInstance().clickButtonRun()){
						if(ManageTestController.getInstance().checkRunConfirmationDialog()){
							if(ManageTestController.getInstance().clickButtonYes()){

								MesmerLogUtils.markPassed("MS-ManageTest-03 Passed")
							}
							else{
								MesmerLogUtils.markFailed("Yes button not clicked")
							}
						}
						else{
							MesmerLogUtils.markFailed("Confirmation dialog not appeared")
						}
					}
					else{
						MesmerLogUtils.markFailed("Run botton not found or not clicked")
					}
				}else{
					MesmerLogUtils.markFailed("Could not select device")
				}
			}
			else{
				MesmerLogUtils.markFailed("ManageTest statuses verification failed as total test cases does not macth on test result screen")
			}
		}
		else{
			MesmerLogUtils.markFailed("[DATA ISSUE] Test cases not selected or not available in the list")
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
