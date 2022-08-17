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


CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
WebUI.takeScreenshot("/Users/untitled/test11.png")

try{

	if (WebUI.waitForElementPresent(startRecordingButton, 120) == true){
		WebUI.click(startRecordingButton)
		WebUI.delay(2)
		WebUI.takeScreenshot("/Users/untitled/test12.png")
		if (WebUI.waitForElementPresent(rebootButtonRecording, 20) == true){
			WebUI.click(rebootButtonRecording)
			
			WebUI.delay(2)
			WebUI.takeScreenshot("/Users/untitled/test13.png")
			if(WebUI.waitForElementPresent(popUpRebootDevice, 10) == true){
				KeywordUtil.markPassed("PASSED: Device Reboot Button Is Enabled")
				WebUI.delay(1)
				WebUI.click(btnClose)
				WebUI.delay(1)
				WebUI.takeScreenshot("/Users/untitled/test14.png")

			}else{
				KeywordUtil.markFailed("FAILED: Device Reboot Dialogue Appears")
			}
		}else{
			KeywordUtil.markFailed("FAILED: Unable To Click On Reboot Button")
		}
	}
	else if(WebUI.verifyElementPresent(preparingLiveView, 10) == true){
		KeywordUtil.markWarning("Preparing Live View")
	}

	else if (WebUI.verifyElementPresent(clickToRetry, 10) == true){
		KeywordUtil.markFailed("Click To Retry")
	}

	else{
		KeywordUtil.markFailed("FAILED: Unable To Click On Start Recording")
	}



}

catch(Exception e){
	e.printStackTrace()
}finally{

	WebUI.takeScreenshot("/Users/untitled/test15.png")
//	if(WebUI.waitForElementPresent(actionItem, 10)==true){
//		WebUI.click(actionItem)
//	}else{
//	KeywordUtil.markWarning("WARNING: Click Failed On Action Item")
//	}
//	
//	if(WebUI.waitForElementPresent(discard, 10)==true){
//		WebUI.click(discard)
//	}else{
//	KeywordUtil.markWarning("WARNING: Click Failed On Discard Button")
//	}
//	
//	if(WebUI.waitForElementPresent(yesButton, 10)==true){
//		WebUI.click(yesButton)
//	}else{
//	KeywordUtil.markFailed("FAILED: Click Failed On Yes Button")
//	}
//	
//	CustomKeywords.'com.mesmer.Utility.goToTestResults'()

	WebUI.click(testResult)
	WebUI.delay(2)
	CustomKeywords.'com.mesmer.CreateNewTestCase.discardTestCase'()
	WebUI.delay(1)
	WebUI.takeScreenshot("/Users/untitled/test16.png")
}

