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
import org.openqa.selenium.Keys as Keys

//MS-Recommended-04 -- Add/Edit Recom. test case names

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
 ///////////////////////////////

CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

WebUI.waitForPageLoad(10)
WebUI.delay(2)
if(WebUI.waitForElementVisible(NoTestCaseAvailable, 3)==false){
	
	if(WebUI.waitForElementVisible(EditPencilButton, 5)==true){
		WebUI.click(EditPencilButton)
		
		if(WebUI.verifyElementVisible(CloseButtonOnRenameTC)==true){
			KeywordUtil.markPassed("PASSED: Close button is available on Editing Test Case name")
			
		}
		else{
			KeywordUtil.markFailed("FAILED: Close button is not displayed")
		}
		
		if(WebUI.waitForElementVisible(EnterTextinInputField, 5)==true){
			WebUI.delay(1)
		
			WebUI.sendKeys(EnterTextinInputField, Keys.chord(Keys.CONTROL, 'a'))
			
			WebUI.delay(1)
			WebUI.sendKeys(EnterTextinInputField, Keys.chord(Keys.BACK_SPACE))
			WebUI.delay(2)
			WebUI.sendKeys(EnterTextinInputField, TCName+"1")
			WebUI.sendKeys(EnterTextinInputField, Keys.chord(Keys.ENTER))
			WebUI.delay(2)
			String xpTitle = VerifyTCName.findPropertyValue('xpath')
			println(xpTitle)
			xpTitle = xpTitle.toString().replace('<placeholder>',TCName+"1")
			
			VerifyTCName.findProperty('xpath').setValue(xpTitle)
			
			if(WebUI.verifyElementVisible(VerifyTCName)==true){
				KeywordUtil.markPassed("PASSED: Name is added successfully for Recommended Test Case")
			}
			else{
				KeywordUtil.markFailed("FAILED: Name is not added for Recommended Test Case")
			}
				
		}
		else{
			KeywordUtil.markFailed("FAILED: Input field is not displayed correctly")
		}
		
	}
	else{
		KeywordUtil.markFailed("FAILED:: Edit Pencil button is not found")
	}
	
}
else{
	KeywordUtil.markWarning("Warning:: No Test Case available")
}