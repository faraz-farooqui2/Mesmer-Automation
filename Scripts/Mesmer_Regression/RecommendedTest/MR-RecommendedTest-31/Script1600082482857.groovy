import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

import controllers.RecommendedTestCaseController
import controllers.TestResultController

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

WebUI.waitForPageLoad(10)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MR-RecommendedTest-31 successful")
	}
}
catch(Exception e){
	e.printStackTrace()
}

private boolean checkTestCaseAndPerformActions(){
	boolean result = false
	if(RecommendedTestCaseController.getInstance().checkIfRecommendedTestScreenOpen()){
		List<WebElement> testCasesList = RecommendedTestCaseController.getInstance().getTestCasesList()
		if(testCasesList != null && testCasesList.size() > 0){
			if(RecommendedTestCaseController.getInstance().findElementAndPerformAction(testCaseName,"acceptTest")){
				navigateToTestResult()
				if(TestResultController.getInstance().findTestCaseAndPerformAction(testCaseName, "selectTestCase", "execute")){
					result = true
				}
			}
		}
	}
	return result
}

private void navigateToTestResult(){
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.waitForPageLoad(5)
}