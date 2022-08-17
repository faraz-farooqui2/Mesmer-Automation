import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import com.kms.katalon.core.testobject.TestObject
import controllers.ManageTestController
import controllers.TestResultController
import org.openqa.selenium.JavascriptExecutor;
/*
 * MS-Manage Tests-06 | Verify that user should be able to Edit Test Results																	
 */


try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()
		testResultTestsCount = TestResultController.getInstance().getTestResultTestCasesCount()
		CustomKeywords.'com.mesmer.Utility.goToManageTests'()
		WebUI.delay(2)

		if(ManageTestController.getInstance().findTestCaseAndPerformAction(testCaseName2,"edit")){
			WebUI.delay(5)
			// Select reDoFromHere Option

			if(ManageTestController.getInstance().checkAndClickTheScreenShot("recordFromHere")){
				// Select Add assertion option
				//			if(ManageTestController.getInstance().checkAndClickTheScreenShot("addAssertions")){
				//				// Select data option
				//				if(ManageTestController.getInstance().checkAndClickTheScreenShot("data")){

				// Select delete option
				if(ManageTestController.getInstance().checkAndClickTheScreenShot("clearStep")){
					MesmerLogUtils.markPassed("MS-ManageTest-06 Passed")
				}
				else{
					MesmerLogUtils.markFailed("Unable to delete the screenshot")
				}
				//				}
				//				else{
				//					MesmerLogUtils.markFailed("Unable to find data option")
				//				}
				//			}
				//			else{
				//				MesmerLogUtils.markFailed("Unable to find assertion option")
				//			}
			}
			else{
				MesmerLogUtils.markFailed("Unable to perform record from here")
			}
		}
		else{
			MesmerLogUtils.markFailed("Unable to edit the provided test case")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()

}

