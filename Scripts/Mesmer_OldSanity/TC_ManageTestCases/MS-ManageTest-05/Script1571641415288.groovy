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
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

CustomKeywords."com.mesmer.Utility.selectProject"(projName, xpathString, platformName, ProjectName)
WebUI.delay(1)
CustomKeywords."com.mesmer.Utility.goToManageTests"()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.refresh()
WebUI.delay(1)

try{
	if(WebUI.verifyElementVisible(testCaseBar) == true){
		if(WebUI.getText(testCasesCount) == "0"){
			KeywordUtil.markFailed("FAILED: There is no test case in the list")
		}
		else{

			// Duplicate the TC
			WebUI.delay(1)
			WebUI.click(editTCTitle)
			WebUI.delay(1)
			WebUI.click(duplicateTC)
			WebUI.delay(1)
			WebUI.click(butonConfirmationYes)
			KeywordUtil.markPassed("Success: Test Case duplicated on page")
			WebUI.delay(4)
			// Duplicate the TC
			
			
			
//			WebUI.mouseOverOffset(commentsOption, 0, 20)
//			WebUI.click(duplicateTC)
//			WebUI.click(butonConfirmationYes)
//			WebUI.delay(4)
			
			
			
			// Edit the duplicate test case
			WebUI.click(clickFilterNew)
			WebUI.mouseOverOffset(commentsOption, 0, 20)
			WebUI.delay(1)
			if(WebUI.waitForElementVisible(dotsIcon, 60)){
				WebUI.click(dotsIcon)
				WebUI.delay(4)
				if(WebUI.waitForElementVisible(editTestCaseTitleIcon, 60)){
					String titleText = WebUI.getText(testCaseTitleSet)
					int titleLength = titleText.length()
					WebUI.click(editTestCaseTitleIcon)
					WebUI.delay(2)
					String input = WebUI.getText(EnterTextinInputField)
					System.err.println(input)
					for (int i = 0 ; i < titleLength ; i++   )
					{
						System.err.println(i)
						WebUI.delay(1)
						WebUI.sendKeys(EnterTextinInputField, Keys.chord(Keys.BACK_SPACE))
					}
					// Name a Test Case with Random generated number
					Random rnd = new Random()
					int randomNumber = (10 + rnd.nextInt(1000))
					WebUI.setText(EnterTextinInputField,
							"Test Case Android"+randomNumber)
					WebUI.sendKeys(null, Keys.chord(Keys.ENTER))
					WebUI.delay(2)
					if(WebUI.waitForElementVisible(textAddTag,2)){
						int i = 0;
						while(i < 4){
							i = i+1
							if(WebUI.waitForElementVisible(textAddTag,2)){
								WebUI.click(textAddTag)
								WebUI.setText(findTestObject("OR_CreateNewTestCase/input_AddTagTitle"),
										"Tag"+i)
								WebUI.sendKeys(findTestObject("OR_CreateNewTestCase/input_AddTag"),
										Keys.chord(Keys.ENTER))
							}
							else{
								KeywordUtil.markPassed("Tags already added")
							}
						}
						// Save test case
						CustomKeywords."com.mesmer.Utility.goToManageTests"()
						WebUI.delay(1)
						WebUI.click(buttonSaveTCOnNavigate)
						KeywordUtil.markPassed("Test Case saved successfully")
						WebUI.delay(1)
						// Delete test case
						WebUI.mouseOverOffset(commentsOption, 0, 20)
						WebUI.delay(1)
						WebUI.click(optionDeleteTestCase)
						WebUI.delay(1)
						WebUI.click(buttonDeleteTCConfirmationYes)
						WebUI.delay(2)
						KeywordUtil.markPassed("Test Case deleted successfully")
					}
					else{
						KeywordUtil.markPassed("Tags already added")
					}

				}
				else{
					KeywordUtil.markFailed("FAILED: Edit icon for title not displayed")
				}
			}
			else{
				KeywordUtil.markFailed("FAILED: Edit icon not displayed")
			}
		}
	}
	else{
		KeywordUtil.markFailed("FAILED: There is no test case title")
	}
	
}
catch(Exception e){
	e.printStackTrace()
}finally{
//CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.refresh()
}