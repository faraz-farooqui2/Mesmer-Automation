import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MS-Manage Tests-01 | Verify that user is shown all test cases in 'Manage Tests' page with their respective statuses.														
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

		int manageTestsCount = ManageTestController.getInstance().getAllTestCasesStatusCount()
		List<WebElement> testCasesList = ManageTestController.getInstance().getTestCasesList()
		if((manageTestsCount == testResultTestsCount) && (testCasesList != null && (testCasesList.size()-1) == testResultTestsCount)){
			MesmerLogUtils.markPassed("MS-ManageTest-01 Passed")
		}
		else{
			MesmerLogUtils.logInfo("ManageTest and test result page count does not match")
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
