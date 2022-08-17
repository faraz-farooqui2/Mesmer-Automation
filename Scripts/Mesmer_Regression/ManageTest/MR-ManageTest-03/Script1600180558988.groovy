import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MR - Manage Test Case-03 | Verify filter Test Case by "Passed", only Passed Test Case(s) should be displayed														
 */

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)

CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.waitForPageLoad(5)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MS-ManageTest-03 Successful")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.waitForPageLoad(5)
}

private boolean checkTestCaseAndPerformActions(){
	boolean result = false
	int count = ManageTestController.getInstance().getPassedTestCasesStatusCount()
	if(count > 0){
		if(ManageTestController.getInstance().clickPassedTestCasesLabel()){
			List<WebElement> testCasesList = ManageTestController.getInstance().getTestCasesList()
			if(testCasesList != null && testCasesList.size() == count){
				result = true
			}
		}
	}
	else{
		result = true
		MesmerLogUtils.logInfo("Manage test passed test cases count is 0")
	}
	return result
}

