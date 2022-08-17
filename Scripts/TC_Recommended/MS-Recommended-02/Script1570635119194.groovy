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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


//MS-Recommended-02 -- Recommended test cases page design

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
 ///////////////////////////////


CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

WebUI.waitForPageLoad(10)

try{
	
	if(WebUI.waitForElementVisible(noTestCaseAvailable, 3)==false){
		//Check Recommended Test cases title
		if(WebUI.waitForElementVisible(verifyRecommendedTCPage, 5)==true){
		
			KeywordUtil.markPassed("SUCCESS: User is on Recommended Test Cases page and title is displayed correctly")
		}else
		{
			KeywordUtil.markFailed("FAILED: Recommended page title is not displayed correctly")
		
		}
		
		//Check Test Cases text
		if(WebUI.verifyElementVisible(verifyTestCasesCounter, FailureHandling.CONTINUE_ON_FAILURE)==true){
			KeywordUtil.markPassed("SUCCESS: Test Cases counter is displayed")
			
		}else{
			KeywordUtil.markFailed("FAILED: Test Cases counter is not displayed")
		}
		
		//Check In Review counter text
		if(WebUI.verifyElementVisible(verifyTCInReviewCounter, FailureHandling.CONTINUE_ON_FAILURE)==true){
			KeywordUtil.markPassed("SUCCESS: In Review counter is displayed")
			
		}else{
			KeywordUtil.markFailed("FAILED: In Review counter is not displayed")
		}
		
		//Check Failed Counter text
		if(WebUI.verifyElementVisible(verifyTCFailedCounter, FailureHandling.CONTINUE_ON_FAILURE)==true){
			KeywordUtil.markPassed("SUCCESS: Test Cases failed counter is displayed")
			
		}else{
			KeywordUtil.markFailed("FAILED: Test Cases failed counter is not displayed")
		}
		
		//Check Accept All Test cases option
		if(WebUI.verifyElementVisible(acceptAllicon, FailureHandling.CONTINUE_ON_FAILURE)==true){
			KeywordUtil.markPassed("SUCCESS: Accept All Test cases option is displayed")
			
		}else{
			KeywordUtil.markFailed("FAILED: Accept All Test cases option is not displayed")
		}
		
		//Check number of ALL test cases option
		if(WebUI.verifyElementVisible(numberOfAllTCs, FailureHandling.CONTINUE_ON_FAILURE)==true){
			KeywordUtil.markPassed("SUCCESS: Number of All Test cases option is displayed")
			
		}else{
			KeywordUtil.markFailed("FAILED: Number of All Test cases option is not displayed")
		}
		
		//Check Screens option
		if(WebUI.verifyElementVisible(screenOption, FailureHandling.CONTINUE_ON_FAILURE)==true){
			KeywordUtil.markPassed("SUCCESS: Screens option is displayed")
			
		}else{
			KeywordUtil.markFailed("FAILED: Screens option is not displayed")
		}
		
		//Check Sort By option
		if(WebUI.verifyElementVisible(sortByOption, FailureHandling.CONTINUE_ON_FAILURE)==true){
			KeywordUtil.markPassed("SUCCESS: Sort By option is displayed")
			
		}else{
			KeywordUtil.markFailed("FAILED: Sort By option is not displayed")
		}
		
		//Edit Pencil button
		if(WebUI.verifyElementVisible(editButtonPencilIcon, FailureHandling.CONTINUE_ON_FAILURE)==true){
			KeywordUtil.markPassed("SUCCESS: Edit Pencil button is displayed")
			WebUI.click(editButtonPencilIcon)
			WebUI.delay(1)
			
			//Verify Close button after clicking Edit Pencil button
			if(WebUI.waitForElementVisible(closeAfterPencilEditButton, 3)==true){
				KeywordUtil.markPassed("SUCCESS: Close button is displayed after clicking Edit Pencil button")
				WebUI.click(closeAfterPencilEditButton)
				
			}else{
					KeywordUtil.markFailed("FAILED: Close button is not displayed after clicking Edit Pencil button")
			}
			
			
		}else{
			KeywordUtil.markFailed("FAILED: Edit Pencil button is not displayed")
		}
		
		if(WebUI.verifyElementVisible(acceptSingleTC)==true){
			KeywordUtil.markPassed("SUCCESS: Single Accept button is displayed ")
		}
		else{
			KeywordUtil.markFailed("FAILED: Single Accept button is not displayed")
		}
		
		if(WebUI.waitForElementVisible(secondImageOfRecommendedTC,10)==true){
			WebUI.delay(5)
			
			/*WebUI.clickImage(secondImageOfRecommendedTC)
			KeywordUtil.markPassed("SUCCESS: Image is clicked successfully")
			
			if(WebUI.waitForElementVisible(screenResultWindow, 5)==true){
				KeywordUtil.markPassed("SUCCESS: Screen Result is displayed successfully")
				
				if(WebUI.waitForElementVisible(leftArrowbutton, 3)==true && WebUI.waitForElementVisible(rightArrowbutton, 3)==true){
					KeywordUtil.markPassed("SUCCESS: Left and Right Arrows are displayed correctly ")
					
					WebUI.click(closeScreenResultWindow)
				}
				else{
					KeywordUtil.markFailed("FAILED: Left and Right Arrows are not displayed correctly ")
				}
					
			}
			else{
				KeywordUtil.markFailed("FAILED: Screen Result is not displayed successfully ")
			}*/
			
		}
		else{
			KeywordUtil.markFailed("FAILED: Single Accept button is not displayed")
		}
	
	}else{
		KeywordUtil.markWarning("WARNING::: NO TEST CASE AVAILABLE")
	}
	
		
	
	
	
}
finally{

	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

