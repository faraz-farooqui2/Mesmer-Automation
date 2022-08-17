import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MR-Manage Test Case-08 | Verify that by clicking on Comments Icon from single test case user should be redirected to test results page for adding comments on that Test Case														
 */

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)

CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.waitForPageLoad(5)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MS-ManageTest-08 Successful")
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
		if(ManageTestController.getInstance().findTestCaseAndPerformAction("ManageTest-Automation", "comments")){
			result = true
		}
	}
	return result
}

