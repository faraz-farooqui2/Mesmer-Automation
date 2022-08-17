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

WebUI.delay(2)

try{
	if(WebUI.waitForElementVisible(clickProjectdropdown, 10) ==true){
		WebUI.click(clickProjectdropdown)
//		WebUI.click(projectSlideArrow)
		WebUI.delay(2)
		if(WebUI.waitForElementVisible(buttonBuildUpload,10) == true){
			WebUI.click(buttonBuildUpload)
			println("Build upload button is clicked")
			WebUI.delay(2)

			if(WebUI.waitForElementVisible(verifyBuildUploadDialogue, 20, FailureHandling.CONTINUE_ON_FAILURE)==true){


				if(WebUI.verifyElementVisible(lblBrowseToSelect) == true){
				//	WebUI.click(btnBrowseToSelect)

					// Change the file path with absolute path.
					WebUI.uploadFile(btnBrowseToSelect, '/Users/untitled/Downloads/TMobile-us', FailureHandling.STOP_ON_FAILURE)
					WebUI.delay(2)

					if(WebUI.waitForElementVisible(textInstallingYourApp, 180, FailureHandling.CONTINUE_ON_FAILURE)==true){

						println("Build uploaded Successfully.")
						KeywordUtil.markPassed("SUCCESS: Build uploaded successfully")
						
						if (WebUI.waitForElementVisible(notificationCrawlStarted, 90) == true) {
							KeywordUtil.markPassed('SUCCESS: Crawl started after uploading build')
					
							CustomKeywords.'com.mesmer.Utility.goToAppMap'()
					
							if (WebUI.waitForElementVisible(textStillCrawling, 10) == true) {
								KeywordUtil.markPassed('SUCCESS: Crawl is in progress...')
							}
						}
					
						else {
							KeywordUtil.markFailed('FAILED: Crawl is not started')
						}
						
						
					}
					else{
						KeywordUtil.markFailed("FAILED: Build upload failed")
					}
					
					
				}

				else{
					KeywordUtil.markFailed("FAILED: Unable to Upload a File")
				}

			}


			else{
				KeywordUtil.markFailed("FAILED: User is not on Build Upload Dialogue")
			}
		}

		else{
			KeywordUtil.markFailed("FAILED: Project List and Create New Project button not found")
		}

	}
	else{
		KeywordUtil.markFailed("FAILED: Project drop down not found")
	}

}

catch(Exception e){
	e.printStackTrace()
}
finally{

}
