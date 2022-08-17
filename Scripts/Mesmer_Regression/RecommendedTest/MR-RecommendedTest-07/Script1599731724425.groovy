import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.mesmer.MesmerLogUtils

import controllers.RecommendedTestCaseController
import controllers.TestDataController
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

WebUI.waitForPageLoad(10)

try{
	if(checkTestCaseAndPerformActions()){
		MesmerLogUtils.markPassed("MR-RecommendedTest-07 successful")
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
			if(testCasesList.size() > 2){
				int count = 0
				for(int i = 0; i < 2; i++){
					String text = RecommendedTestCaseController.getInstance().getTestCaseTitle(i)
					if(i == 0 && text.equalsIgnoreCase("Recommended Test Case 1")){
						count++
					}
					else if(i == 2 && text.equalsIgnoreCase("Recommended Test Case 2")){
						count++
					}
					if(count == 2){
						count = 0
						result = true
						break
					}
				}
			}
			else{
				String text = RecommendedTestCaseController.getInstance().getTestCaseTitle(0)
				if(text.equalsIgnoreCase("Recommended Test Case 1")){
					result = true
					break
				}
			}
		}
	}
	return result
}
