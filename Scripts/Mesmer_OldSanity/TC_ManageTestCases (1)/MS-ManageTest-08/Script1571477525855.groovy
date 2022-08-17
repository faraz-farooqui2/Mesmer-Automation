import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil


CustomKeywords."com.mesmer.Utility.selectProject"(projName, xpathString, platformName, ProjectName)
CustomKeywords."com.mesmer.Utility.goToManageTests"()
WebUI.delay(1)
WebUI.refresh()
WebUI.delay(1)

try{
	if(WebUI.waitForElementPresent(testCaseBar, 10) == true){
		if(WebUI.getText(testCasesCount) == "0"){
			KeywordUtil.markPassed("PASSED: There is no test case in the list")
		}
		else{
			if(WebUI.waitForElementPresent(copyTestCaseToOtherProject, 10) == true){
				WebUI.click(copyTestCaseToOtherProject)
				if(WebUI.waitForElementPresent(copyToProjectPopOver, 10) == true){
					if(WebUI.waitForElementPresent(selectProjectTick, 10)== true){
						WebUI.click(selectProjectTick)
						if(WebUI.waitForElementPresent(btnCopyAllTestCases, 10) == true){
							WebUI.click(btnCopyAllTestCases)
							if(WebUI.waitForElementPresent(btnConfirmationDialogueYes, 10) == true){
								WebUI.click(btnConfirmationDialogueYes)
								if(WebUI.waitForElementPresent(confirmationDialogueTestCaseCoypingStarted, 10) == true){
									if(WebUI.waitForElementPresent(confirmationDialogueTestCasesCopyingCompleted, 10) == true){
									}else{
										KeywordUtil.markFailed("FAILED: Test Cases Copying UnCompleted")
									}
								}else{
									KeywordUtil.markFailed("FAILED: Test Cases Copying Failed")
								}
							}else{
	
								KeywordUtil.markFailed("FAILED: Copy All Test Cases Confirmation Button Not Clicked")
							}
						}else{
	
							KeywordUtil.markFailed("FAILED: Copy All Test Cases Button Not Clicked")
	
	
						}
					}else{
						KeywordUtil.markFailed("FAILED: Project Not Selected")
	
					}
				}else{
					KeywordUtil.markFailed("FAILED: Pop Over Not Open")
				}
			}else{
				KeywordUtil.markFailed("FAILED: Unable To Click on Copy Tests To Project")
			}
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: There is no test case title")
	}
}
	catch(Exception e){
	e.printStackTrace()

}finally{
WebUI.refresh()
}