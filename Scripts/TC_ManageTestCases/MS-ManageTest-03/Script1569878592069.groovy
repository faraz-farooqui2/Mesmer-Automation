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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil

WebUI.delay(1)
CustomKeywords."com.mesmer.Utility.goToManageTests"()
WebUI.refresh()

WebUI.delay(1)
try{
if(WebUI.verifyElementVisible(testCaseBar) == true){
	KeywordUtil.markPassed("PASSED: Test Case exists on page")
		if(WebUI.verifyElementVisible(optionsComments) == true){
			KeywordUtil.markPassed("PASSED: Option Test Case Comment found")
			WebUI.click(optionsComments)
			if(WebUI.verifyElementVisible(buttonAddComment) == true){	
				KeywordUtil.markPassed("PASSED: User is on Add Comment Page")
			}
			else{
				KeywordUtil.markFailed("FAILED: Add Comment Page not found")
				}
			}
		else{
				KeywordUtil.markFailed("FAILED: Option Test Case Comment not found")
	}

}		
CustomKeywords."com.mesmer.Utility.goToManageTests"()
}
catch(Exception e){
	e.printStackTrace()
}finally{
WebUI.refresh()
}