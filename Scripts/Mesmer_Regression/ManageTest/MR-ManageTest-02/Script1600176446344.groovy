import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MR-Manage Test Case-02 | Verify Test count along with different Filters														
 */

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)

CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.waitForPageLoad(5)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MS-ManageTest-02 Successful")
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
	if(ManageTestController.getInstance().verifyManageTestStatuses()){
		result = true
	}
	return result
}

