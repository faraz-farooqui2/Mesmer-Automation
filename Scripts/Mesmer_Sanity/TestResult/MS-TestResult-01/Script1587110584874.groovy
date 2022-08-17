import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

//MS-Test Result-01 | Verify that user is shown all types (Recorded, Recommended,Duplicate,Redo from here) of test cases under a specific project for a specific platform (iOS or Android).
//1. Select a project from Project list

//2. See if all types of test cases are appearing in Test result page.

try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		if(WebUI.verifyElementPresent(test_recorded, 10)==true){
			MesmerLogUtils.markPassed('Found Recorded Test Case')

			if(WebUI.verifyElementPresent(test_clone, 10)==true){
				MesmerLogUtils.markPassed('Found Clone Test Case')

				if(WebUI.verifyElementPresent(test_redo, 10)==true){
					MesmerLogUtils.markPassed('Found Redo From Here Test Case')

					if(WebUI.verifyElementPresent(test_moveFromOtherProject, 10)==true){
						MesmerLogUtils.markPassed('Found MoveFromOtherProject Test Case')

						if(WebUI.verifyElementPresent(test_recommended, 10)==true){
							MesmerLogUtils.markPassed('Found Recommended Test Case')
						}else{
							MesmerLogUtils.markWarning('Recommended Test Case Not Found')
						}
					}else{
						MesmerLogUtils.markWarning('MoveFromOtherProject Test Case Not Found')
					}

				}else{
					MesmerLogUtils.markWarning('Redo From Here Test Case Not Found')
				}
			}else{
				MesmerLogUtils.markWarning('Clone Test Case Not Found')
			}
		}else{
			MesmerLogUtils.markWarning('Recorded Test Case Not Found')
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}catch(Exception e){
	e.printStackTrace()
}finally{

}
