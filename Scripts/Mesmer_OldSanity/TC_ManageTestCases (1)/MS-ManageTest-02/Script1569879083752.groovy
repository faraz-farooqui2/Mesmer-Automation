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
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By

//Calling Select Project Method
CustomKeywords."com.mesmer.Utility.selectProject"(projName, xpathString, platformName, ProjectName)
CustomKeywords."com.mesmer.Utility.goToManageTests"()
WebUI.refresh()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(1)


try{
	if(WebUI.waitForElementPresent(testCaseBar, 10)){
		if (WebUI.getText(testCasesCount) == "0") {
			KeywordUtil.markWarning("WARNING: There is no test case in the list")
		}
		else{
			WebUI.mouseOverOffset(commentsOption, 0, 20)
			WebUI.delay(1)
			// Find the test cases list and select the title
			findTestCasesAndSelectTitle();
			// Verify comments tab
			verifyCommentsSection();
		}
	}else{
		KeywordUtil.markFailed("FAILED: Test case not found")
	}

}
catch(Exception e){
	e.printStackTrace()
}finally {
	CustomKeywords.'com.mesmer.Utility.goToManageTests'()
}

def void findTestCasesAndSelectTitle(){
	WebDriver driver = DriverFactory.getWebDriver()
	try{
		List<WebElement> testCasesList = driver.findElements(By.xpath('//app-manage-test-case-list/div'))
		if(testCasesList != null && testCasesList.size() > 1){
			WebElement testCaseTitle = driver.findElement(By.xpath('//app-manage-test-case-list/div[1]/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneLeft"]/div[@class="title-container"]'))
			if(testCaseTitle != null){
				testCaseTitle.click();
				WebUI.delay(1)
				String input = WebUI.getText(EnterTextinInputField)
				for (int i = 0 ; i < input.length() ; i++   )
				{
					System.out.println(i)
					WebUI.delay(1)
					WebUI.sendKeys(EnterTextinInputField, Keys.chord(Keys.BACK_SPACE))
				}
				// Name a Test Case with Random generated number
				Random rnd = new Random()
				int randomNumber = (10 + rnd.nextInt(1000))
				WebUI.setText(EnterTextinInputField,
						"Test Case "+randomNumber)
				WebUI.sendKeys(null, Keys.chord(Keys.ENTER))
				WebUI.delay(1)
				KeywordUtil.markPassed("Title editable and updated")
			}
			else{
				KeywordUtil.markFailed("FAILED: Title of test case not found")
			}
		}
		else{
			KeywordUtil.markWarning("WARNING: There is no test case in the list")
		}
	}
	catch(Exception e){
		e.printStackTrace()
	}
}
def void verifyCommentsSection(){
	WebDriver driver = DriverFactory.getWebDriver()
	try{
		WebElement testCaseCommentsTab = driver.findElement(By.xpath('//app-manage-test-case-list/div[1]/div[@class="testcase-detail"]/app-test-case-tile-header/div/div[@class="actionPaneRight"]/a/span[@class="commentsTxt"]'))
		if(testCaseCommentsTab != null){
			KeywordUtil.markPassed("PASSED: MS-ManageTest-02 Comments tab exists")
		}
		else{
			KeywordUtil.markFailed("FAILED: Comments tab not found")
		}
	}
	catch(Exception e){
		e.printStackTrace()
	}
}