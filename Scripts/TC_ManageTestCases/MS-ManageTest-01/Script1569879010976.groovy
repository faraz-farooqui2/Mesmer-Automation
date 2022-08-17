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


CustomKeywords."com.mesmer.Utility.goToManageTests"()


WebUI.delay(1)
try{
	if(WebUI.verifyElementVisible(counterTotalTC) == true){
		WebUI.click(counterTotalTC)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Counter Total Test cases found")
	}
	else{
		KeywordUtil.markFailed("FAILED: Counter Total Test cases not found")
	}
	
	if(WebUI.verifyElementVisible(textTotalTC) == true){
		WebUI.click(textTotalTC)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Text Total Test cases found")
		
	}
	else{
		KeywordUtil.markFailed("FAILED: Text test cases not found")
	}
	if(WebUI.verifyElementVisible(counterPassed) == true){
		WebUI.click(counterPassed)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Counter Passed Test cases found")
		
	}
	else{
		KeywordUtil.markFailed("FAILED: Counter Passed test cases not found")
	}
	if(WebUI.verifyElementVisible(textPassed) == true){
		WebUI.click(textPassed)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Text Passed Test cases found")

	}
	else{
		KeywordUtil.markFailed("FAILED: Text Passed not found")
	}
	if(WebUI.verifyElementVisible(counterFailed) == true){
		WebUI.click(counterFailed)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Counter Failed Test cases found")
	}
	else{
		KeywordUtil.markFailed("FAILED: Counter Failed test cases not found")
	}
	if(WebUI.verifyElementVisible(textFailed) == true){
		WebUI.click(textFailed)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Text Failed Test cases found")
	}
	else{
		KeywordUtil.markFailed("FAILED: Text Failed not found")
	}
	if(WebUI.verifyElementVisible(counterBroken) == true){
		WebUI.click(counterBroken)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Counter Broken Test cases found")
		}else{
		KeywordUtil.markFailed("FAILED: Counter Broken test cases not found")
	}
	
	if(WebUI.verifyElementVisible(textBroken) == true){
		WebUI.click(textBroken)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Text Broken Test cases found")
	}
	else{
		KeywordUtil.markFailed("FAILED: Text Broken not found")
	}

	if(WebUI.verifyElementVisible(counterNeedsReview) == true){
		WebUI.click(counterNeedsReview)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Counter Needs Review Test cases found")
	}
	else{
		KeywordUtil.markFailed("FAILED: Counter Needs Review test cases not found")
	}
	
	if(WebUI.verifyElementVisible(textNeedsReview) == true){
		WebUI.click(textNeedsReview)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Text Broken Test cases found")
	}
	else{
		KeywordUtil.markFailed("FAILED: Text Needs Review not found")
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
WebUI.refresh()
}
