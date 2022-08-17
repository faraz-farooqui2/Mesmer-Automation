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

try{
	if(WebUI.waitForElementVisible(buildProjectPopOver, 20) ==true){
		WebUI.click(buildProjectPopOver)
		WebUI.delay(1)
		WebUI.click(projectSlideArrow)
		WebUI.delay(2)
		if(WebUI.verifyElementVisible(projectList) == true){
			WebUI.click(btnCreateNewProject)

			if(WebUI.verifyElementText(verifyBuildUploadDialogue, textVerifyForUploadDialogue)){

				if(WebUI.verifyElementVisible(lblBrowseToSelect) == true){
				//	WebUI.click(btnBrowseToSelect)

					// Change the file path with absolute path.
					WebUI.uploadFile(btnBrowseToSelect, '/Users/untitled/mesmer-automation/Resources/AndroidBuilds/Ring.apk', FailureHandling.STOP_ON_FAILURE)
					if(WebUI.waitForElementVisible(textInstallingYourApp, 300, FailureHandling.CONTINUE_ON_FAILURE)==true){

						println("Build uploaded Successfully.")
						KeywordUtil.markPassed("SUCCESS: Build uploaded successfully")
					}
					else{
						KeywordUtil.markFailed("FAILED: Build upload failed")
					}
					
				}
				if (WebUI.waitForElementVisible(radibtnCreateNewProject, 10) == true){
					WebUI.click(radibtnCreateNewProject)
				}

				if (WebUI.verifyElementVisible(btnContinue) == true){
					WebUI.click(btnContinue)

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
WebUI.refresh()
}
