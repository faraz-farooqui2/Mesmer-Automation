import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.EditTestController
import controllers.ManageTestController
import controllers.TestResultController

/*
 * MR-Manage Test Case-12 | Verify delete Test case icon button behavior from single test case bar														
 */

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)

CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.waitForPageLoad(5)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MS-ManageTest-12 Successful")
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
	List<WebElement> testCasesList = ManageTestController.getInstance().getTestCasesList()
	if(testCasesList != null && testCasesList.size() > 0){
		if(ManageTestController.getInstance().findTestCaseAndPerformAction("ManageTest-Automation", "delete-no")){
			result = true
		}
	}
	return result
}

