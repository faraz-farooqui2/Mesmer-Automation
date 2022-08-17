import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.RecommendedTestCaseController

/*
 * MS-Recommended Test Cases-01 | Verify that user can move all or selected recommended test case to Test Result page successfully after adding a name.
 */


try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

		WebUI.waitForPageLoad(10)
		WebUI.delay(60)

		if(WebUI.waitForElementVisible(noTestCaseAvailableinRecommended, 20)==false){
			//Check Recommended Test cases title
			if(WebUI.waitForElementVisible(verifyRecommendedTCPage, 10)){
				//Check Test Cases tabs
				if(WebUI.waitForElementVisible(verifyTestCasesCounter, 10)){
					if(WebUI.waitForElementVisible(verifyTCFailedCounter, 10)){
						//					if(WebUI.waitForElementVisible(verifyTCInReviewCounter, 10)){
						//Check Accept All Test cases option
						if(WebUI.waitForElementVisible(acceptAllicon, 10)){
							WebUI.delay(5)
							// Find the test case and perform Action
							RecommendedTestCaseController.getInstance().findElementAndPerformAction(testCaseName1,actionName1)

							RecommendedTestCaseController.getInstance().findElementAndPerformAction(testCaseName2,actionName2)

						}else{
							MesmerLogUtils.markFailed("Accept All Test cases option is not displayed")
						}
						//					}
						//					else{
						//						MesmerLogUtils.markFailed("In Review option is not displayed")
						//					}
					}
					else{
						MesmerLogUtils.markFailed("Failed option is not displayed")
					}
				}
				else{
					MesmerLogUtils.markFailed("[DATA ISSUE] There is no test case in the recommended list")
				}
			}else
			{
				MesmerLogUtils.markFailed("Recommended page title is not displayed correctly")

			}
		}
		else{
			MesmerLogUtils.markFailed("[DATA ISSUE] There is no Test Case available in Recommended Test Cases")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}




