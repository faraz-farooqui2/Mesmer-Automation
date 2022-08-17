import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MR-Manage Test Case-05 | Verify filter Test Case by "Broken", Only Broken Test Case(s) should be displayed														
 */

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)

CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.waitForPageLoad(5)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MS-ManageTest-05 Successful")
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
	int count = ManageTestController.getInstance().getBrokenTestCasesStatusCount()
	if(count > 0){
		if(ManageTestController.getInstance().clickBrokenTestCasesLabel()){
			List<WebElement> testCasesList = ManageTestController.getInstance().getTestCasesList()
			if(testCasesList != null && testCasesList.size() == count){
				result = true
			}
		}
	}
	else{
		result = true
		MesmerLogUtils.logInfo("Manage test broken test cases count is 0")
	}
	return result
}

