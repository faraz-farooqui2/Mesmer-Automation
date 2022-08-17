import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

import controllers.RecommendedTestCaseController

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

WebUI.waitForPageLoad(10)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MR-RecommendedTest-23 successful")
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
				String testCasename = 'ahaksjhdkjashdjkashdjkashdkjashdjkahskdhakjsdhjkashdjkashdjkashdjkashdjkashdjkashdjkahsdkjashdjkashd'
				if(RecommendedTestCaseController.getInstance().editTitleInInputField(testCasename)){
					String name = RecommendedTestCaseController.getInstance().getTestCaseTitle(0)
					if(name.length() == 100){
						result = true
					}
				}
			}
		}
	}
	return result
}
