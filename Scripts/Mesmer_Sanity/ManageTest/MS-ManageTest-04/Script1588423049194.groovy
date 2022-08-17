import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MS-Manage Tests-04 | Verify that user should be able to Copy/Move test cases to another project of same platform from 'Manage Test' page.														
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
			if(ManageTestController.getInstance().clickButtonCopy()){
				if(ManageTestController.getInstance().checkIfCopyToOtherProjectPresent()){
					if(ManageTestController.getInstance().clickSelectProjectTick()){
						if(ManageTestController.getInstance().clickCopyAllTestCases()){
							if(ManageTestController.getInstance().clickCopyAllTestCasesConfirmationYes()){

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

