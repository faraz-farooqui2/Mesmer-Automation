import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.MesmerLogUtils as MesmerLogUtils
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.ReplayController


// MR-Replay2.0-11 | Verify that menu options like download results, delete test, duplicate test etc working fine

WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)){


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		if(ReplayController.getInstance().checkNumberOfTestCasesInTestResult()){
			MesmerLogUtils.markPassed("Test Case found in Test Result")

			if (ReplayController.getInstance().openPassedTestCase()){
				MesmerLogUtils.markPassed("Passed test case opened")
				if (ReplayController.getInstance().performOperations(action)){
					MesmerLogUtils.markPassed("Test Case Replay-05 Passed")
				}

			}else if (ReplayController.getInstance().openFailedTestCase()){
				MesmerLogUtils.markPassed("Failed test case opened")
				if(performActions()){
					MesmerLogUtils.markPassed("Test Case Replay-05 Passed")
				}

			}else if (ReplayController.getInstance().openBrokenTestCase()){
				MesmerLogUtils.markPassed("Broken test case opened")
				if(performActions()){
					MesmerLogUtils.markPassed("Test Case Replay-05 Passed")
				}

			}

		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}

}catch(Exception e){

	e.printStackTrace()

}finally{

	CustomKeywords.'com.mesmer.Utility.goToTestResults'()

}

public boolean performActions(){
	boolean result = false
	if(ReplayController.getInstance().clickOnReplayButtonTestDetail()){
		MesmerLogUtils.markPassed("Clicked on Re-run button")
		if(ReplayController.getInstance().selectInUseDevice()){
			MesmerLogUtils.markPassed("Device selected")
			if(ReplayController.getInstance().clickOnRunButton()){
				MesmerLogUtils.markPassed("Clicked on Run button")
				if(ReplayController.getInstance().confirmationRunningTestCase()){
					MesmerLogUtils.markPassed("Clicked on Yes button")

					if(ReplayController.getInstance().msgQueue()){
						MesmerLogUtils.markPassed("Queue msg appears")

						TestObject replayStarting = findTestObject('Object Repository/OR_TestDetails/msg_startingTestCase')
						if(WebUI.waitForElementPresent(replayStarting, 240)){

						}else{
							MesmerLogUtils.markFailed("Replay not started in 2 mins")
						}
					}
				}
			}
		}
	}
	return result
}