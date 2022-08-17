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
import com.kms.katalon.core.testdata.InternalData as InternalData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testdata.reader.ExcelFactory as ExcelFactory
import org.junit.After as After
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
//Calling Select Project Method

//MS-CreateNewTestCase-04	Doing save and continue and discarding it through create new test case

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
WebUI.delay(2)
try{
	if(WebUI.waitForElementPresent(startRecordingIcon, 90)==true){
		WebUI.click(startRecordingIcon)
		if(WebUI.waitForElementPresent(enabledOptionsIcon, 60)==true){
			if(WebUI.waitForElementPresent(enabledSavedText, 60)==true){
				WebUI.click(enabledSavedText)
				WebUI.delay(12)
				KeywordUtil.markPassed("PASSED: CreateNewTestCase-04 successfull")
			}
			else{
				KeywordUtil.markFailed("FAILED: Discard text not found")
			}
		}
		else{
			KeywordUtil.markFailed("FAILED: Enabled options icon not found")
		}
	}else if(WebUI.waitForElementPresent(preparingLiveView, 2) == true){
		KeywordUtil.markWarning("Preparing Live View")
	}

	else if (WebUI.waitForElementPresent(clickToRetry, 2) == true){
		KeywordUtil.markFailed("FAILED: Stream Failed")
	}
	else{
		KeywordUtil.markFailed("FAILED: Start recording icon not found")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	if(WebUI.waitForElementVisible(beforeYouGoWindow,1)==true){
		CustomKeywords.'com.mesmer.CreateNewTestCase.discardTestCase'()
	}else{
		println("Before you go window is not displayed")
	}
}