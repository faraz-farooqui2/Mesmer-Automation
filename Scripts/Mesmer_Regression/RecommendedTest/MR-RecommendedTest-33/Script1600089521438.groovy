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
		MesmerLogUtils.markPassed("MR-RecommendedTest-33 successful")
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
			if(RecommendedTestCaseController.getInstance().clickTestCaseTitle("Recommended Test 1")){
				String testCasename = 'Duplicate title test'
				if(RecommendedTestCaseController.getInstance().editTitleInInputField(testCasename)){
					if(RecommendedTestCaseController.getInstance().clickTestCaseTitle("Recommended Test 2")){
						if(RecommendedTestCaseController.getInstance().editTitleInInputField(testCasename)){
							result = true
						}
					}
				}
			}
		}
	}
	return result
}