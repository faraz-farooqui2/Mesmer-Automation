import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

import controllers.ManageTestController
import controllers.RecommendedTestCaseController
import controllers.TestResultController

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

WebUI.waitForPageLoad(10)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MR-RecommendedTest-36 successful")
		RecommendedTestCaseController.getInstance().clickScreenResultsCrossBtn()
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
			List<WebElement> screensList = RecommendedTestCaseController.getInstance().getTestCaseScreensList("Recommended Test 1")
			if(screensList != null && screensList.size() > 1){
				screensList.get(1).click()
				WebUI.delay(2)
				if(RecommendedTestCaseController.getInstance().checkIfScreenResultsPopupExists()){
					if(RecommendedTestCaseController.getInstance().getScreenResultDefectsCount() >= 0){
						if(RecommendedTestCaseController.getInstance().getScreenResultAlertCount() >= 0){
							result = true
						}
					}
				}
			}
		}
	}
	return result
}