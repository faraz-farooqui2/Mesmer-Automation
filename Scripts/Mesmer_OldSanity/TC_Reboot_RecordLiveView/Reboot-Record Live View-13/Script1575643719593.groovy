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
WebUI.takeScreenshot("/Users/untitled/test1.png")

try{

	if (WebUI.waitForElementPresent(startRecordingButton, 120) == true){
		WebUI.click(startRecordingButton)
		WebUI.delay(2)
		WebUI.takeScreenshot("/Users/untitled/test2.png")
		if (WebUI.waitForElementPresent(rebootButtonRecording, 30) == true){
			WebUI.click(rebootButtonRecording)
			WebUI.takeScreenshot("/Users/untitled/test3.png")
			if(WebUI.waitForElementPresent(popUpRebootDevice, 30) == true){
				KeywordUtil.markPassed('PASSED: Device Reboot Button Is Enabled')
				WebUI.delay(1)
				if(WebUI.waitForElementPresent(btnContinueReboot, 30)== true){
					WebUI.click(btnContinueReboot)
					WebUI.delay(1)
					KeywordUtil.markPassed('PASSED: Continue To Reboot Device')

					if (WebUI.waitForElementPresent(confirmationRebootingDialogue, 20)== true){
						KeywordUtil.markPassed('PASSED: Confirmation Dialogue For Device Reboot')

						if(WebUI.waitForElementPresent(rebootDevice,10) == true){
							KeywordUtil.markPassed('PASSED: Device Rebooted')

						}else{
							KeywordUtil.markFailed('FAILED: Device Not Reboot' )
						}
					}else{
						KeywordUtil.markFailed('FAILED: No Device Reboot Confirmation Dialogue Appears' )

					}
				}else{

					KeywordUtil.markFailed('FAILED: Unable To Click On Continue To Reboot Device')
				}
			}else{
				KeywordUtil.markFailed('FAILED: Device Reboot Dialogue Appears')
			}
		}else{
			KeywordUtil.markFailed('FAILED: Unable To Click On Reboot Button')
		}
	}else if(WebUI.verifyElementPresent(preparingLiveView, 2) == true) {
		KeywordUtil.markWarning("Preparing Live View")
	}else if (WebUI.verifyElementPresent(clickToRetry, 10) == true) {
		KeywordUtil.markFailed("Click To Retry")
	}else{
		KeywordUtil.markFailed('FAILED: Unable To Click On Start Recording')

	}

}catch(NoSuchElementException e){
	e.printStackTrace()
}
finally{
	WebUI.click(testResultsLink)
	WebUI.delay(2)
	CustomKeywords.'com.mesmer.CreateNewTestCase.discardTestCase'()
	
}

