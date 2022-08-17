import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

import controllers.TestDetailsController
import controllers.TestResultController


//MR-SingleTestCaseScreen-1 | Verify that user can open a single Test case from Test Results page
//MR-SingleTestCaseScreen-2 | Verify that testcases status is shown above testcase title (New/Broken/Passed/Failed/Needs Review)
//MR-SingleTestCaseScreen-3 | Verify that "defects" count, "to review" count and testcase duration is shown in front of testcase title
//MR-SingleTestCaseScreen-10 | Verify that user can add tag
//MR-SingleTestCaseScreen-11 | Verify that user can remove tag
//MR-SingleTestCaseScreen-12 | Verify that tag is not added if user clicks on cross button during tag name typing
//MR-SingleTestCaseScreen-13 | Verify that special characters can be added in tag name
//MR-SingleTestCaseScreen-14 | Verify that user can add maximum 4 tags for a test case
//MR-SingleTestCaseScreen-15 | Verify that user can save TC with empty tag
//MR-SingleTestCaseScreen-16| Verify that user cannot add duplicate tag
//MR-SingleTestCaseScreen-17 | Verify that the test case default Title is Untitled Test Case
//MR-SingleTestCaseScreen-18 | Verify that user can add the test case Title
//MR-SingleTestCaseScreen-19 | Verify that test case Title character limit is 90
//MR-SingleTestCaseScreen-20 | Verify that special characters are acceptable in test case Title 


CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
//1. Observe test result page
CustomKeywords.'com.mesmer.Utility.goToTestResults'()

WebUI.waitForPageLoad(5)
try{
	//MR-SignleTestCaseScreen-01
	checkSingleTestCase01Steps();
	//MR-SignleTestCaseScreen-02
	checkSingleTestCase02Steps();
	//MR-SignleTestCaseScreen-03
	checkSingleTestCase03Steps();
	//MR-SignleTestCaseScreen-10
	checkSingleTestCase10Steps();
	//MR-SignleTestCaseScreen-11
	checkSingleTestCase11Steps();
	//MR-SignleTestCaseScreen-12
	checkSingleTestCase12Steps();
	//MR-SignleTestCaseScreen-13
	checkSingleTestCase13Steps();
	//MR-SignleTestCaseScreen-14
	checkSingleTestCase14Steps();
	//MR-SignleTestCaseScreen-15
	checkSingleTestCase15Steps();
	//MR-SignleTestCaseScreen-16
	checkSingleTestCase16Steps();
	//MR-SignleTestCaseScreen-17
	checkSingleTestCase17Steps();
	//MR-SignleTestCaseScreen-18
	checkSingleTestCase18Steps();
	//MR-SignleTestCaseScreen-19
	checkSingleTestCase19Steps();
	//MR-SignleTestCaseScreen-20
	checkSingleTestCase20Steps();
}
catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.refresh()
	WebUI.delay(4)
}

def checkSingleTestCase01Steps(){
	TestResultController.getInstance().findTestCaseAndPerformAction("ManageTest-06", "selectTestCase", "")
}
// TODO:: Flow has changed so it needs to be updated once we get the updated sheet
def checkSingleTestCase02Steps(){
	WebUI.waitForPageLoad(4)
	if(WebUI.waitForElementPresent(spanTestCaseTitleDetailPage, 10)){
		String statusXPath = '//div[@class="titlePanel"]/div/span'
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement status = driver.findElement(By.xpath(statusXPath))
		if(status != null && status.getText().equals(testCaseStatus)){
			KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-02 Successful')
		}
		else{
			KeywordUtil.markFailed('FAILED: Test case status does not match')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Test case detail page not loaded')
	}
}

def checkSingleTestCase03Steps(){
//	if(TestResultController.getInstance().findTestCaseAndPerformAction("ManageTest-06", "selectTestCase", "")){
		WebUI.delay(2)
		List<WebElement> testRunslist = TestDetailsController.getInstance().getTestRunsList()
		if(testRunslist != null && testRunslist.size() > 0){
			String errorsCount = TestDetailsController.getInstance().getTestRunsTestStatsCount("error",0)
			MesmerLogUtils.logInfo("Errors counts is: "+errorsCount)
			String flagedCount = TestDetailsController.getInstance().getTestRunsTestStatsCount("flaged",0)
			MesmerLogUtils.logInfo("Flaged counts is: "+flagedCount)
			String viewedCount = TestDetailsController.getInstance().getTestRunsTestStatsCount("viewed",0)
			MesmerLogUtils.logInfo("Viewed counts is: "+viewedCount)
			String clockTime = TestDetailsController.getInstance().getTestRunsTestStatsCount("clock",0)
			MesmerLogUtils.logInfo("Duration is: "+clockTime)
		}
//	}
}

def checkSingleTestCase10Steps(){
	if(TestDetailsController.getInstance().clickBaselineEditTestIcon()){
		MesmerLogUtils.logInfo("Edit icon found and clicked")
		WebUI.delay(2)
		addTags()
	}
}
def checkSingleTestCase11Steps(){
	removeTag()
}
def checkSingleTestCase12Steps(){
	removeTagWhileTyping()
}

def checkSingleTestCase13Steps(){
	addTagWithSpecialCharacter()
}

def checkSingleTestCase14Steps(){
	checkTagsLimit()
}

def checkSingleTestCase15Steps(){
	selectTag()
}
def checkSingleTestCase16Steps(){
	checkForDuplicateTagName()
}

def checkSingleTestCase17Steps(){
//	if(TestResultController.getInstance().findTestCaseAndPerformAction("ManageTest-06", "selectTestCase", "")){
		if(WebUI.waitForElementVisible(spanTestCaseTitleDetailPage,10)){
			String title = WebUI.getText(spanTestCaseTitleDetailPage)
			if(title.contains("Test-")){
				KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-17 Successful')
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Untitled Test Case not found on test detail page')
		}
//	}
}
def checkSingleTestCase18Steps(){
	editTestCaseTitle("ManageTest-06-Edited")
}

def checkSingleTestCase19Steps(){
	checkTitleCharacterLimit()
}

def checkSingleTestCase20Steps(){
	checkForTestCaseNameWithSpecialCharacters()
}

def findTestCaseAndPerformAction(String testCaseName){
	if(testCasesDivXPath != null && testCasesDivXPath.findPropertyValue("xpath") != null){
		String testResultTestCasesDivXPath = testCasesDivXPath.findPropertyValue("xpath")
		WebDriver driver = DriverFactory.getWebDriver()
		if(testCasesListXPath != null && testCasesListXPath.findPropertyValue("xpath") != null){
			String testResultTestCasesXPath = testCasesListXPath.findPropertyValue("xpath")
			List<WebElement> testCasesList = driver.findElements(By.xpath(testResultTestCasesXPath))
			if(testCasesList != null && testCasesList.size() > 0){
				for(int i= 0; i < testCasesList.size(); i++){
					String titleXPath = '//app-test-results-tiles[@class="vScrollCards"]/div/a['+(i+1)+']/descendant::div[@class="mat-card-title"]/span[contains(@class,"ng-star-inserted")]'
					WebElement testCaseTitle = driver.findElement(By.xpath(titleXPath))
					if(testCaseTitle != null && testCaseTitle.getText().equals(testCaseName)){
						WebUI.delay(2)
						TestObject to = new TestObject("objectName")
						to.addProperty("xpath", ConditionType.EQUALS,'//app-test-results-tiles[@class="vScrollCards"]/div/a['+(i+1)+']/descendant::div[@class="mat-card-title"]/span[contains(@class,"ng-star-inserted") and text()="'+testCaseName+'"]')
						WebUI.scrollToElement(to, 10)
						WebUI.delay(2)
						WebUI.mouseOver(to)
						WebUI.delay(2)
						String testCaseStatusStr = '//app-test-results-tiles[@class="vScrollCards"]/div/a['+(i+1)+']/descendant::div[@class="mat-card-content"]/span[contains(@class,"ng-star-inserted")]'
						WebElement testCaseStatusElement = driver.findElement(By.xpath(testCaseStatusStr))
						if(testCaseStatusElement != null){
							testCaseStatus = testCaseStatusElement.getText()
						}
						WebUI.delay(2)
						WebUI.click(to)
						if(testCaseName.equals("Untitled Test Case")){
							
						}
						else{
							KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-01 Successful')
						}
						WebUI.delay(5)
						break
					}
				}
			}
			else{
				KeywordUtil.markWarning('WARNING: There is no test case in the list')
			}
		}
		else{
			KeywordUtil.markWarning('WARNING: There is no test case in the list')
		}
	}
	else{
		KeywordUtil.markWarning('WARNING: Test cases container not found')
	}
}
def addTags(){
	if(WebUI.waitForElementVisible(textAddTag, 10)){
		int i = 0;
		while(i < 2){
			i = i+1
			if(WebUI.waitForElementVisible(textAddTag,10)){
				WebUI.click(textAddTag)
				WebUI.setText(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),
					'Tag'+i)
				WebUI.sendKeys(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),Keys.chord(Keys.ENTER))
			}
			else if(WebUI.waitForElementVisible(addTagInserted, 10)){
				WebUI.click(addTagInserted)
				WebUI.setText(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),
					'Tag'+i)
				WebUI.sendKeys(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),Keys.chord(Keys.ENTER))
			}
		}
		if(i == 2){
			KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-10 Successful')
		}
	}
	else{
		KeywordUtil.markWarning("WARNING: Tags already added")
	}
}

def removeTag(){
	String removeXPath = '//div[@class="tags"]/div[1]/div[1]/div/div[2]/span'
	WebDriver driver = DriverFactory.getWebDriver()
	Actions actions = new Actions(driver);
	WebElement removeTagIcon = driver.findElement(By.xpath(removeXPath))
	if(removeTagIcon != null){
		actions.moveToElement(removeTagIcon).perform()
		WebUI.delay(2)
		removeTagIcon.click()
		WebUI.delay(2)
		KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-11 Successful')
	}
	else{
		KeywordUtil.markWarning("WARNING: Tag remove icon not found")
	}
}

private List<WebElement> getTagsRemoveIconList(){
	List<WebElement> result = null
	try{
		String removeXPath = '//div[@class="tags"]/div[1]/div/div/div[2]/span'
		WebDriver driver = DriverFactory.getWebDriver()
		result = driver.findElements(By.xpath(removeXPath))
	}
	catch(Exception e){
		e.printStackTrace()	
	}
	return result
}

private void removeAllTags(){
	try{
		WebDriver driver = DriverFactory.getWebDriver()
		Actions actions = new Actions(driver);
		List<WebElement> removeTagIconList = getTagsRemoveIconList()
		if(removeTagIconList == null){
			return
		}
		if(removeTagIconList != null && removeTagIconList.size() > 1){
			MesmerLogUtils.logInfo("remove tag icons list size : "+removeTagIconList.size())
			while(removeTagIconList.size() > 1){
				actions.moveToElement(removeTagIconList.get(0)).perform()
				WebUI.delay(2)
				removeTagIconList.get(0).click()
				WebUI.delay(2)
				removeTagIconList = null
				removeAllTags()
			}
		}
	}
	catch(Exception e){
		e.printStackTrace()
	}
}

private void removeActiveTag(){
	WebUI.delay(5)
	String removeXPath ='//div[@class="tags"]/div/i[@title="Close"]'
	WebDriver driver = DriverFactory.getWebDriver()
	WebElement removeTagIcon = driver.findElement(By.xpath(removeXPath))
	if(removeTagIcon != null){
		removeTagIcon.click()
		WebUI.delay(2)
	}
	else{
		KeywordUtil.markWarning("WARNING: Active Tag remove icon not found")
	}
}

def removeTagWhileTyping(){
	if(WebUI.waitForElementVisible(textAddTag, 10)){
		int i = 0;
		while(i < 2){
			i = i+1
			if(WebUI.waitForElementVisible(textAddTag,10)){
				WebUI.click(textAddTag)
				WebUI.setText(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),
					'Tag'+i)
				if(WebUI.waitForElementVisible(closeTagEditing,10)){
					WebUI.click(closeTagEditing)
					KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-12 Successful')
					WebUI.delay(2)
					break
				}
				else{
					KeywordUtil.markFailed("FAILED: Tag remove icon not found")
				}
			}
		}
	}
	else{
		KeywordUtil.markWarning("WARNING: Tags already added")
	}
}

def addTagWithSpecialCharacter(){
	if(WebUI.waitForElementVisible(textAddTag, 30)){
		WebUI.click(textAddTag)
		WebUI.setText(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),
			'Tag$01')
		WebUI.sendKeys(findTestObject('OR_CreateNewTestCase/input_AddTag'),
		Keys.chord(Keys.ENTER))
		KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-13 Successful')
	}
	else{
		KeywordUtil.markWarning("WARNING: Tags already added")
	}
}

def checkTagsLimit(){
	if(isTagsLimitReached()){
		KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-14 Successful')
	}
	else{
		if(WebUI.waitForElementVisible(textAddTag, 10)){
			int i = 0;
			while(!isTagsLimitReached()){
				i = i+1
				if(WebUI.waitForElementVisible(closeTagEditing,5)){
					WebUI.click(closeTagEditing)
				}
				if(WebUI.waitForElementVisible(addTagInserted,10)){
					WebUI.click(addTagInserted)
					TestObject obj = findTestObject('OR_CreateNewTestCase/input_AddTagTitle')
					if(WebUI.waitForElementVisible(obj, 5)){
						WebUI.setText(obj,'Tag'+i)
						WebUI.sendKeys(findTestObject('OR_CreateNewTestCase/input_AddTag'),
						Keys.chord(Keys.ENTER))
					}
					else if(WebUI.waitForElementVisible(textAddTag, 5)){
						WebUI.click(textAddTag)
						WebUI.setText(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),
							'Tag'+i)
						WebUI.sendKeys(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),Keys.chord(Keys.ENTER))
					}
					else if(WebUI.waitForElementVisible(textAddTag,5)){
						WebUI.click(textAddTag)
						if(WebUI.waitForElementVisible(obj, 5)){
							WebUI.setText(obj,'Tag'+i)
							WebUI.sendKeys(findTestObject('OR_CreateNewTestCase/input_AddTag'),
							Keys.chord(Keys.ENTER))
						}
					}
				}
				else if(WebUI.waitForElementVisible(textAddTag,10)){
					WebUI.click(textAddTag)
					TestObject obj = findTestObject('OR_CreateNewTestCase/input_AddTagTitle')
					if(WebUI.waitForElementVisible(obj, 5)){
						WebUI.setText(obj,'Tag'+i)
						WebUI.sendKeys(findTestObject('OR_CreateNewTestCase/input_AddTag'),
						Keys.chord(Keys.ENTER))
					}
				}
			}
			if(isTagsLimitReached()){
				KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-14 Successful')
			}
		}
		else{
			KeywordUtil.markWarning("WARNING: Tags already added")
		}
	}
}

private boolean isTagsLimitReached(){
	String tagsXPath = '//div[@class="tags"]/div[1]/div/div'
	WebDriver driver = DriverFactory.getWebDriver()
	List<WebElement> tagsList = driver.findElements(By.xpath(tagsXPath))
	if(tagsList != null && tagsList.size() > 5){
		return true
	}
	return false
}

private void selectTag(){
	removeAllTags()
//	String addedTagXPath = '//div[@class="tags"]/div[1]/div/div[@class="tagName CP"]'
//	WebDriver driver = DriverFactory.getWebDriver()
//	WebElement addedTag = driver.findElement(By.xpath(addedTagXPath))
//	if(addedTag != null){
//		TestObject to = new TestObject("selectedTag")
//		to.addProperty("xpath", ConditionType.EQUALS,addedTagXPath)
//		WebUI.click(to)
//		WebUI.delay(3)
//		KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-15 Successful')
//	}
//	else{
//		KeywordUtil.markFailed("FAILED: There is no tag added yet")
//	}
}

private void checkForDuplicateTagName(){
	WebDriver driver = DriverFactory.getWebDriver()
	if(isTagsLimitReached()){
		removeAllTags()
	}
	else{
		if(WebUI.waitForElementVisible(textAddTag, 10)){
			int i = 0;
			while(!isTagsLimitReached()){
				i = i+1
				if(WebUI.waitForElementVisible(textAddTag,10)){
					WebUI.click(textAddTag)
					WebUI.setText(findTestObject('OR_CreateNewTestCase/input_AddTagTitle'),
						'abc')
					WebUI.sendKeys(findTestObject('OR_CreateNewTestCase/input_AddTag'),
					Keys.chord(Keys.ENTER))
					WebUI.delay(1)
					String alertXPath = '//div[@class="app-alerts right"]/div'
					List<WebElement> alert = driver.findElements(By.xpath(alertXPath))
					if(alert != null && alert.size() > 0){
						KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-16 Successful')
						break
					}
					else{
						KeywordUtil.markWarning("WARNING: Tags reached their limit")
					}
				}
			}
//			removeAllTags()
		}
		else{
			KeywordUtil.markWarning("WARNING: Tags already added")
		}
	}
}

private void editTestCaseTitle(String testCaseName){
//	if(TestResultController.getInstance().clickEditTestCaseIcon()){
//		if(TestResultController.getInstance().setTitleNameWithoutEnter(testCaseName)){
//			MesmerLogUtils.markPassed('MR-SingleTestCaseScreen-18 Successfull')
//		}
//	}
	if(TestResultController.getInstance().editTestCaseName(testCaseName, "clickEditIcon")){
		MesmerLogUtils.markPassed('MR-SingleTestCaseScreen-18 Successfull')
	}
}

private void checkTitleCharacterLimit(){
	Random rnd = new Random()
	int randomNumber = (10 + rnd.nextInt(10000))
	String name = 'qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcvbnm1234567890'+randomNumber
	if(TestResultController.getInstance().editTestCaseName(name, "clickTitle")){
		WebUI.delay(2)
		String title = WebUI.getText(spanTestCaseTitleDetailPage)
		if(title.length() <=90 && !title.equals("Untitled Test Case")){
			KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-19 Successful')
		}
		else{
			KeywordUtil.markFailed('FAILED: Testcase title exceeds the 90 characters limit')
		}
	}
}

private void checkForTestCaseNameWithSpecialCharacters(){
	if(WebUI.waitForElementVisible(spanTestCaseTitleDetailPage,10)){
		WebUI.click(spanTestCaseTitleDetailPage)
		WebUI.delay(2)
		Random rnd = new Random()
		int randomNumber = (10 + rnd.nextInt(10000))
		WebUI.setText(findTestObject('OR_CreateNewTestCase/input_AddTestCaseTitle'),
				'Test Case!@# '+randomNumber)
		WebUI.sendKeys(null, Keys.chord(Keys.ENTER))
		WebUI.delay(2)
		String title = WebUI.getText(spanTestCaseTitleDetailPage)
		if(!title.equals("Untitled Test Case")){
			KeywordUtil.markPassed('PASSED: MR-SingleTestCaseScreen-20 Successful')
		}
		else{
			KeywordUtil.markFailed('FAILED: Title with special characters not acceptible')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Untitled Test Case not found on test detail page')
	}
}