import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * Pre-Req | Clone a test case for sanity testing														
 */

try{
	//	if(recordIterationCount.equals("1")){
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)
		// Set the platformName for the testcase like, Generic/iOS/Android
		Utility.setPlatformName(platformName)
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		CustomKeywords.'com.mesmer.Utility.goToManageTests'()

		if(ManageTestController.getInstance().findTestCaseAndPerformAction(testCaseName, action)){
			if(ManageTestController.getInstance().clickCopyAllTestCasesConfirmationYes()){
				MesmerLogUtils.logInfo("Test case cloned successfully")
			}else{
				MesmerLogUtils.markFailed("Test case cloning failed")
			}
		}else{
			MesmerLogUtils.markFailed("Could not find test case and perform actions")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
	//	}
}
catch(Exception e){
	e.printStackTrace()
}
