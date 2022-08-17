import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MS-Manage Tests-02 | Verify that filter counts in 'Manage Test' are as per Test result page														
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


		if(ManageTestController.getInstance().verifyManageTestStatuses()){
			int manageTestsCount = ManageTestController.getInstance().getAllTestCasesStatusCount()
			if(manageTestsCount == testResultTestsCount){
				MesmerLogUtils.markPassed("MS-ManageTest-02 Passed")
			}
			else{
				MesmerLogUtils.logInfo("ManageTest statuses verification failed as total test cases does not macth on test result screen")
			}
		}
		else{
			MesmerLogUtils.logInfo("ManageTest statuses verification failed")
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
