package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

public class RecommendedTestCaseController {
	private static RecommendedTestCaseController mInstance = null

	private RecommendedTestCaseController(){
	}

	public static RecommendedTestCaseController getInstance(){
		if(mInstance == null){
			mInstance = new RecommendedTestCaseController()
		}

		return mInstance
	}

	public boolean findElementAndPerformAction(String testCaseName, String action){
		boolean result = false;
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(1)
		String testCasesListXpath = findTestObject('Object Repository/OR_Recommended/xpath_TestCasesList').findPropertyValue('xpath').toString()
		List<WebElement> testCasesList = driver.findElements(By.xpath(testCasesListXpath))
		if(testCasesList != null && testCasesList.size() > 0){
			for(int i= 0; i < testCasesList.size(); i++){
				String titleXPath = findTestObject('Object Repository/OR_Recommended/xpath_title').findPropertyValue('xpath').toString()
				titleXPath = titleXPath.toString().replace('<pCounter>', String.valueOf(i+1))
				WebElement testCaseTitle = driver.findElement(By.xpath(titleXPath))
				//				WebElement testCaseTitle = driver.findElement(By.xpath('//div[@class="vScrollCards contentPanelFooter"]/div['+(i+1)+']/div[@class="actionPanel CP"]/div/div[@class="title noTitle"]/span'))
				if(testCaseTitle != null && testCaseTitle.getText().equals(testCaseName)){
					WebUI.delay(2)
					TestObject to = new TestObject("objectName")
					String spanXpath = findTestObject('Object Repository/OR_Recommended/xpath_spanText').findPropertyValue('xpath').toString()
					to.addProperty("xpath", ConditionType.EQUALS,spanXpath +testCaseName+'"]')
					WebUI.scrollToElement(to, 10)
					WebUI.delay(2)
					if(action == "acceptTest"){
						String acceptTestXPath = findTestObject('Object Repository/OR_Recommended/xpath_acceptTest').findPropertyValue('xpath').toString()
						acceptTestXPath = acceptTestXPath.toString().replace('<pCounter>', String.valueOf(i+1))
						WebElement acceptTestIcon = driver.findElement(By.xpath(acceptTestXPath))
						//						WebElement acceptTestIcon = driver.findElement(By.xpath('//div[@class="vScrollCards contentPanelFooter"]/div['+(i+1)+']/div[@class="actionPanel CP"]/descendant::a[@class="CP acceptTest"]'))
						if(acceptTestIcon != null){
							acceptTestIcon.click();
							WebUI.delay(2)
							// Verify Confirmation dialog
							if(verifyConfirmationDialog()){
								result = true
								break
							}
						}
						else{
							MesmerLogUtils.markFailed("Accept test icon not found")
						}
					}
					else if(action == "acceptTestWithNo"){
						String acceptTestXPath = findTestObject('Object Repository/OR_Recommended/xpath_acceptTest').findPropertyValue('xpath').toString()
						acceptTestXPath = acceptTestXPath.toString().replace('<pCounter>', String.valueOf(i+1))
						WebElement acceptTestIcon = driver.findElement(By.xpath(acceptTestXPath))
						//						WebElement acceptTestIcon = driver.findElement(By.xpath('//div[@class="vScrollCards contentPanelFooter"]/div['+(i+1)+']/div[@class="actionPanel CP"]/descendant::a[@class="CP acceptTest"]'))
						if(acceptTestIcon != null){
							acceptTestIcon.click();
							WebUI.delay(2)
							// Verify Confirmation dialog
							if(verifyConfirmationDialogAndClickNo()){
								result = true
								break
							}
						}
						else{
							MesmerLogUtils.markFailed("Accept test icon not found")
						}
					}
					else if(action == "acceptAllTests"){
						String roundButtonsXPath = findTestObject('Object Repository/OR_Recommended/xpath_roundButtons').findPropertyValue('xpath').toString()
						roundButtonsXPath = roundButtonsXPath.toString().replace('<pCounter>', String.valueOf(i+1))
						WebElement checkBoxIcon = driver.findElement(By.xpath(roundButtonsXPath))
						if(checkBoxIcon != null){
							checkBoxIcon.click()
							WebUI.delay(2)
							TestObject acceptAllicon = findTestObject('Object Repository/OR_Recommended/button_AcceptAll')
							if(WebUI.waitForElementVisible(acceptAllicon, 10)){
								WebUI.click(acceptAllicon)
								WebUI.delay(2)
							}
							else{
								MesmerLogUtils.markFailed("Accept all icon not found")
							}
							// Verify Confirmation dialog
							if(verifyAcceptAllConfirmationDialog()){
								result = true
								break
							}
						}
						else{
							MesmerLogUtils.markFailed("Checkbox icon not found")
						}
					}

					WebUI.delay(2)
					break;
				}
				if(i == (testCasesList.size()-1)){
					MesmerLogUtils.markWarning("Test case not found")
				}
			}
		}
		else{
			MesmerLogUtils.markWarning("There is no test case in the list")
		}
		return result;
	}

	public boolean verifyConfirmationDialog(){
		boolean result = false;
		TestObject confirmationDialog = findTestObject('Object Repository/OR_Recommended/text_AcceptSingleTCConfirmationWindow')
		TestObject btnYes = findTestObject('Object Repository/OR_Recommended/button_YesOnConfirmationWindow')
		if(WebUI.waitForElementVisible(confirmationDialog, 10)){
			if(WebUI.waitForElementVisible(btnYes, 10)){
				WebUI.click(btnYes)
				result = true;
			}
			else{
				MesmerLogUtils.markFailed("Yes button not visible")
			}
		}
		else{
			MesmerLogUtils.markFailed("Confirmation dialg didn't appear")
		}
		return result;
	}
	public boolean verifyConfirmationDialogAndClickNo(){
		boolean result = false;
		TestObject confirmationDialog = findTestObject('Object Repository/OR_Recommended/text_AcceptSingleTCConfirmationWindow')
		TestObject btnNo = findTestObject('Object Repository/OR_Recommended/button_NoOnConfirmationWindow')
		if(WebUI.waitForElementVisible(confirmationDialog, 10)){
			if(WebUI.waitForElementVisible(btnNo, 10)){
				WebUI.click(btnNo)
				result = true;
			}
			else{
				MesmerLogUtils.markFailed("No button not visible")
			}
		}
		else{
			MesmerLogUtils.markFailed("Confirmation dialg didn't appear")
		}
		return result;
	}
	public boolean verifyAcceptAllConfirmationDialog(){
		boolean result = false;
		TestObject confirmationWindowonAcceptAllTCs = findTestObject('Object Repository/OR_Recommended/screen_ConfirmationWindowOnAcceptingAllTCs')
		TestObject btnYes = findTestObject('Object Repository/OR_Recommended/button_YesOnConfirmationWindow')
		if(WebUI.waitForElementVisible(confirmationWindowonAcceptAllTCs, 10)){
			if(WebUI.waitForElementVisible(btnYes, 10)){
				WebUI.click(btnYes)
				result = true;
			}
			else{
				MesmerLogUtils.markFailed("Yes button not visible")
			}
		}
		else{
			MesmerLogUtils.markFailed("Confirmation dialg didn't appear")
		}
		return result;
	}

	public boolean checkIfRecommendedTestExists(){
		boolean result = false;
		TestObject noTestCaseAvailableinRecommended = findTestObject('Object Repository/OR_Recommended/text_NoTestCaseAvailable')
		if(WebUI.waitForElementVisible(noTestCaseAvailableinRecommended, 5)==false){
			result = true;
		}
		return result;
	}
	/**
	 * Check if recommended test cases screen opened
	 * @return
	 */
	public boolean checkIfRecommendedTestScreenOpen(){
		boolean result = false;
		TestObject obj = findTestObject('Object Repository/OR_Recommended/text_RecommendedTest')
		if(WebUI.waitForElementVisible(obj, 30)){
			String titleText = WebUI.getText(obj)
			if(titleText.equalsIgnoreCase("Recommended Test Cases")){
				result = true;
				WebUI.delay(2)
			}
		}
		else{
			MesmerLogUtils.markFailed("Recommended test cases text not found")
		}
		return result;
	}

	public boolean checkIfTestCasesAndDefectsCountExists(){
		boolean result = false;
		TestObject countObj = findTestObject('Object Repository/OR_Recommended/info_testcasesCounter')
		if(WebUI.waitForElementVisible(countObj,30)){
			TestObject defectsObj = findTestObject('Object Repository/OR_Recommended/info_testcasesFailedCounter')
			if(WebUI.waitForElementVisible(defectsObj,30)){
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Defects count view not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Test cases count view not found")
		}
		return result;
	}

	public String getTestCasesCount(){
		String result = "0"
		TestObject countObj = findTestObject('Object Repository/OR_Recommended/info_testcasesCounter')
		if(WebUI.waitForElementVisible(countObj,30)){
			result = WebUI.getText(countObj)
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Test cases count not found")
		}
		return result
	}

	public String getDefectsCount(){
		String result = "0"
		TestObject countObj = findTestObject('Object Repository/OR_Recommended/info_testcasesFailedCounter')
		if(WebUI.waitForElementVisible(countObj,30)){
			result = WebUI.getText(countObj)
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Defects count not found")
		}
		return result
	}

	public boolean checkIfFiltersExist(){
		boolean result = false
		TestObject showCrawlObj = findTestObject('Object Repository/OR_Recommended/option_showCrawl')
		if(WebUI.waitForElementVisible(showCrawlObj,30)){
			TestObject optionsObj = findTestObject('Object Repository/OR_Recommended/option_Screens')
			if(WebUI.waitForElementVisible(optionsObj,30)){
				TestObject sortByObj = findTestObject('Object Repository/OR_Recommended/option_SortBy')
				if(WebUI.waitForElementVisible(sortByObj,30)){
					TestObject allTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionAll")
					if(WebUI.waitForElementPresent(allTestCaseLabel, 30)){
						result = true
						WebUI.delay(2)
					}
					else{
						MesmerLogUtils.markFailed("All test cases label not found")
					}
				}
				else{
					MesmerLogUtils.markFailed("Sort by text not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("Screens text not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Show crawl text not found")
		}
		return result
	}

	/**
	 * Check and click the all test cases label
	 * @return
	 */
	public boolean clickAllTestCasesLabel(){
		boolean result = false
		TestObject allTestCaseLabel = findTestObject("Object Repository/OR_TestResult/testCase_OptionAll")
		if(WebUI.waitForElementPresent(allTestCaseLabel, 30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("All test cases label not found")
		}

		return result
	}

	public boolean clickSortbyFilter(){
		boolean result = false
		TestObject sortByObj = findTestObject('Object Repository/OR_Recommended/option_SortBy')
		if(WebUI.waitForElementVisible(sortByObj,30)){
			WebUI.click(sortByObj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Sort by text not found")
		}
		return result
	}

	public boolean clickShowCrawlFilter(){
		boolean result = false
		TestObject showCrawlObj = findTestObject('Object Repository/OR_Recommended/option_showCrawl')
		if(WebUI.waitForElementVisible(showCrawlObj,30)){
			WebUI.click(showCrawlObj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Show crawl text not found")
		}
		return result
	}

	public boolean clickScreensFilter(){
		boolean result = false
		TestObject optionsObj = findTestObject('Object Repository/OR_Recommended/option_Screens')
		if(WebUI.waitForElementVisible(optionsObj,30)){
			WebUI.click(optionsObj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screens text not found")
		}
		return result
	}

	public boolean clickAppMapHistoryBtn(){
		boolean result = false
		TestObject optionsObj = findTestObject('Object Repository/OR_Recommended/btnHistory_AppMap')
		if(WebUI.waitForElementVisible(optionsObj,30)){
			WebUI.click(optionsObj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("History button in app map not found")
		}
		return result
	}

	public boolean clickScreensFilterClearAllBtn(){
		boolean result = false
		TestObject optionsObj = findTestObject('Object Repository/OR_Recommended/option_Screens')
		if(WebUI.waitForElementVisible(optionsObj,30)){
			WebUI.click(optionsObj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screens text not found")
		}
		return result
	}

	public boolean checkIfNoCrawlsTextExists(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_Recommended/text_NoCrawls')
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("No crawls text not found")
		}
		return result
	}

	public String getShowCrawlFilterOptionText(String optionName){
		String result = ""
		if(clickShowCrawlFilter()){
			String listXP = '//div[@class="popover-content popover-body"]/div[starts-with(@class,"dropScroll")]/div'
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> optionsList = driver.findElements(By.xpath(listXP))
			if(optionsList != null && optionsList.size() > 0){
				for(int i = 1; i < optionsList.size(); i++){
					String elementXP = '//div[@class="popover-content popover-body"]/div[starts-with(@class,"dropScroll")]/div['+(i+1)+']/descendant::div[starts-with(@class,"dropdown-popover-item CP ng")]'
					WebElement optionElement = driver.findElement(By.xpath(elementXP))
					if(optionElement != null && optionElement.isDisplayed() && optionElement.getText().equalsIgnoreCase(optionName)){
						result = optionElement.getText()
						result = true
						WebUI.delay(2)
						break
					}
				}
			}
		}
		return result
	}

	public boolean selectShowCrawlFilterOption(String optionName){
		boolean result = false
		if(clickShowCrawlFilter()){
			String listXP = '//div[@class="popover-content popover-body"]/div[starts-with(@class,"dropScroll")]/div'
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> optionsList = driver.findElements(By.xpath(listXP))
			if(optionsList != null && optionsList.size() > 0){
				for(int i = 1; i < optionsList.size(); i++){
					String elementXP = '//div[@class="popover-content popover-body"]/div[starts-with(@class,"dropScroll")]/div['+(i+1)+']/descendant::div[starts-with(@class,"dropdown-popover-item CP ng")]'
					WebElement optionElement = driver.findElement(By.xpath(elementXP))
					if(optionElement != null && optionElement.isDisplayed() && optionElement.getText().equalsIgnoreCase(optionName)){
						optionElement.click()
						result = true
						WebUI.delay(2)
						break
					}
				}
				if(!result){
					result = clickShowCrawlFilter()
				}
			}
		}
		return result
	}

	public boolean checkIfCrawlFilterContainsOptions(){
		boolean result = false
		if(clickShowCrawlFilter()){
			String listXP = '//div[@class="popover-content popover-body"]/div[starts-with(@class,"dropScroll")]/div'
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> optionsList = driver.findElements(By.xpath(listXP))
			if(optionsList != null && optionsList.size() > 0){
				result = true
				WebUI.delay(2)
				clickShowCrawlFilter()
			}
		}
		return result
	}

	public boolean selectShowScreensFilterOption(String optionName){
		boolean result = false
		if(clickScreensFilter()){
			String listXP = '//div[@class="popover-content popover-body"]/div'
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> optionsList = driver.findElements(By.xpath(listXP))
			if(optionsList != null && optionsList.size() > 0){
				for(int i = 0; i < optionsList.size(); i++){
					String elementXP = '//div[@class="popover-content popover-body"]/div['+(i+1)+']/ul/li'
					WebElement optionElement = driver.findElement(By.xpath(elementXP))
					if(optionElement != null && optionElement.isDisplayed() && optionElement.getText().contains(optionName)){
						optionElement.click()
						result = true
						WebUI.delay(2)
						break
					}
				}
			}
			clickScreensFilter()
		}
		return result
	}

	public boolean selectShowSortByFilterOption(String optionName){
		boolean result = false
		if(clickSortbyFilter()){
			String listXP = '//div[@class="popover-content popover-body"]/div[starts-with(@class,"dropScroll")]/div'
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> optionsList = driver.findElements(By.xpath(listXP))
			if(optionsList != null && optionsList.size() > 0){
				for(int i = 0; i < optionsList.size(); i++){
					String elementXP = '//div[@class="popover-content popover-body"]/div['+(i+1)+']/ul/li'
					WebElement optionElement = optionsList.get(i)
					if(optionElement != null && optionElement.isDisplayed() && optionElement.getText().contains(optionName)){
						optionElement.click()
						result = true
						WebUI.delay(2)
						break
					}
				}
			}
		}
		return result
	}

	public int getAllTestCasesCount(){
		int result = 0
		result = TestResultController.getInstance().getAllTestCasesCount()
		return result
	}

	public List<WebElement> getTestCasesList(){
		List<WebElement> result = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(1)
		String testCasesListXpath = findTestObject('Object Repository/OR_Recommended/xpath_TestCasesList').findPropertyValue('xpath').toString()
		result = driver.findElements(By.xpath(testCasesListXpath))
		return result
	}

	public List<WebElement> getTestCaseScreensList(String testCaseName){
		List<WebElement> result = null
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> testCasesList = getTestCasesList()
			if(testCasesList != null && testCasesList.size() > 0){
				for(int i = 0; i < testCasesList.size(); i++){
					String xpath = '//div[@class="vScrollCards"]/div['+(i+1)+']/div/div[starts-with(@class,"titleWrap")]/div/span'
					WebElement element = driver.findElement(By.xpath(xpath))
					if(element != null && element.isDisplayed()){
						if(element.getText().equalsIgnoreCase(testCaseName)){
							String xp = '//div[@class="vScrollCards"]/div['+(i+1)+']/div[starts-with(@class,"expandDevice ng")]/descendant::mat-card'
							List<WebElement> screenElements = driver.findElements(By.xpath(xp))
							if(screenElements != null && screenElements.size() > 1){
								result = screenElements
								WebUI.delay(2)
								break
							}
						}
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}

		return result
	}

	public boolean checkIfTestCasesExpanded(int position){
		boolean result = false
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			String xpath = '//div[@class="vScrollCards"]/div['+(position+1)+']/div[starts-with(@class,"expandDevice ng")]'
			List<WebElement> list = driver.findElements(By.xpath(xpath))
			if(list !=null && list.size() > 0){
				result = true
				WebUI.delay(2)
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean expandCollapseTestCases(int position, String action){
		boolean result = false
		if(action.equalsIgnoreCase("expand")){
			if(checkIfTestCasesExpanded(position)){
				result = true
				WebUI.delay(2)
			}
			else{
				if(collapseTestCasesTab(position)){
					result = true
					WebUI.delay(2)
				}
			}
		}
		else if(action.equalsIgnoreCase("collapse")){
			if(checkIfTestCasesExpanded(position)){
				if(collapseTestCasesTab(position)){
					result = true
					WebUI.delay(2)
				}
			}
		}
		return result
	}

	public boolean collapseTestCasesTab(int position){
		boolean result = false
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			String xpath = '//div[@class="vScrollCards"]/div['+(position+1)+']/div/div[starts-with(@class,"statusWrap")]'
			WebElement element = driver.findElement(By.xpath(xpath))
			if(element != null && element.isDisplayed()){
				element.click()
				result = true
				WebUI.delay(2)
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public String getTestCaseTitle(int position){
		String result = ""
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			String xpath = '//div[@class="vScrollCards"]/div['+(position+1)+']/div/div[starts-with(@class,"titleWrap")]/div/span'
			WebElement element = driver.findElement(By.xpath(xpath))
			if(element != null && element.isDisplayed()){
				result = element.getText()
				WebUI.delay(2)
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean checkIfTestCountAndListCountMatches(){
		boolean result = false
		int testsCount = getAllTestCasesCount()
		List<WebElement> testList = getTestCasesList()
		if(testList != null && testList.size() == testsCount){
			result = true
		}
		return result
	}

	public boolean checkIfDismissAndProvideTestDataExists(){
		boolean result = false
		TestObject btnDismiss = findTestObject('Object Repository/OR_Recommended/btn_Dismiss')
		if(WebUI.waitForElementVisible(btnDismiss,30)){
			TestObject btnProvideTestData = findTestObject('Object Repository/OR_Recommended/btn_ProvideTestData')
			if(WebUI.waitForElementVisible(btnProvideTestData,30)){
				result = true
			}
			else{
				MesmerLogUtils.markFailed("Button provide test data not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("Button dismiss not found")
		}
		return result
	}

	public boolean clickDismissBtn(){
		boolean result = false
		TestObject btnDismiss = findTestObject('Object Repository/OR_Recommended/btn_Dismiss')
		if(WebUI.waitForElementVisible(btnDismiss,30)){
			WebUI.click(btnDismiss)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button dismiss not found")
		}
		return result
	}

	public boolean clickProvideTestDataBtn(){
		boolean result = false
		TestObject btnProvideTestData = findTestObject('Object Repository/OR_Recommended/btn_ProvideTestData')
		if(WebUI.waitForElementVisible(btnProvideTestData,30)){
			WebUI.click(btnProvideTestData)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button provide test data not found")
		}
		return result
	}

	public boolean clickEditCloseBtn(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_Recommended/icon_EditCross')
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Button cross for edit test title not found")
		}
		return result
	}

	public boolean clickTestCaseTitle(String testName){
		boolean result = false
		//		String xpath = '//div[starts-with(@class,"input-field titleNOF")]'
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> testCasesList = getTestCasesList()
			if(testCasesList != null && testCasesList.size() > 0){
				for(int i = 0; i < testCasesList.size(); i++){
					String xpath = '//div[@class="vScrollCards"]/div['+(i+1)+']/div/div[starts-with(@class,"titleWrap")]/div/span'
					WebElement element = driver.findElement(By.xpath(xpath))
					if(element != null && element.isDisplayed()){
						if(element.getText().equalsIgnoreCase(testName)){
							element.click()
							result = true
							WebUI.delay(2)
							break
						}
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean clickTestCaseEditIcon(String testName){
		boolean result = false
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> testCasesList = getTestCasesList()
			if(testCasesList != null && testCasesList.size() > 0){
				for(int i = 0; i < testCasesList.size(); i++){
					String xpath = '//div[@class="vScrollCards"]/div['+(i+1)+']/div/div[starts-with(@class,"titleWrap")]/div/span'
					WebElement element = driver.findElement(By.xpath(xpath))
					if(element != null && element.isDisplayed()){
						if(element.getText().equalsIgnoreCase(testName)){
							String editIconXP = '//div[@class="vScrollCards"]/div['+(i+1)+']/div/div[starts-with(@class,"titleWrap")]/div/i'
							WebElement elementEditIcon = driver.findElement(By.xpath(editIconXP))
							if(elementEditIcon != null && elementEditIcon.isDisplayed()){
								elementEditIcon.click()
								result = true
								WebUI.delay(2)
								break
							}
						}
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	/**
	 * Set the name in title input field
	 * @param name
	 * @return
	 */
	public boolean editTitleInInputField(String testName){
		boolean result = false
		try{
			TestObject inputField = findTestObject('Object Repository/OR_Recommended/input_EditTestCaseTitle')
			if(Utility.isMac()){
				WebUI.sendKeys(inputField, Keys.chord(Keys.COMMAND, 'a'))
			}
			else{
				WebUI.sendKeys(inputField, Keys.chord(Keys.CONTROL, "a"))
			}
			WebUI.sendKeys(inputField, Keys.chord(Keys.chord(Keys.BACK_SPACE)))
			WebUI.clearText(inputField)
			WebUI.delay(2)
			WebUI.setText(inputField,testName)
			WebUI.delay(2)
			sendEnterKey()
			WebUI.delay(2)
			result = true
			WebUI.delay(2)
		}
		catch(Exception e){
			e.printStackTrace()
		}

		return result
	}

	/**
	 * Set the name in title input field
	 * @param name
	 * @return
	 */
	public boolean editTitleInInputFieldWithoutEnter(String testName){
		boolean result = false
		try{
			TestObject inputField = findTestObject('Object Repository/OR_Recommended/input_EditTestCaseTitle')
			if(Utility.isMac()){
				WebUI.sendKeys(inputField, Keys.chord(Keys.COMMAND, 'a'))
			}
			else{
				WebUI.sendKeys(inputField, Keys.chord(Keys.CONTROL, "a"))
			}
			WebUI.sendKeys(inputField, Keys.chord(Keys.chord(Keys.BACK_SPACE)))
			WebUI.clearText(inputField)
			WebUI.delay(2)
			WebUI.setText(inputField,testName)
			WebUI.delay(2)
			//			sendEnterKey()
			WebUI.delay(2)
			result = true
			WebUI.delay(2)
		}
		catch(Exception e){
			e.printStackTrace()
		}

		return result
	}

	public void sendEnterKey(){
		WebUI.sendKeys(null, Keys.chord(Keys.ENTER))
		WebUI.delay(2)
	}

	public boolean checkIfScreenResultsPopupExists(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_Recommended/dialog_screenResults')
		if(WebUI.waitForElementVisible(obj,30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results popup not found")
		}
		return result
	}

	public boolean clickScreenResultsCrossBtn(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_Recommended/btn_CrossScreenResults')
		if(WebUI.waitForElementVisible(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results cross button not found")
		}
		return result
	}

	public int getScreenResultDefectsCount(){
		int count = 0
		TestObject obj = findTestObject('Object Repository/OR_Recommended/defects_CountScreenResults')
		if(WebUI.waitForElementVisible(obj,30)){
			count = WebUI.getText(obj)
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results defects count not found")
		}
		return count
	}

	public int getScreenResultAlertCount(){
		int count = 0
		TestObject obj = findTestObject('Object Repository/OR_Recommended/alerts_CountScreenResults')
		if(WebUI.waitForElementVisible(obj,30)){
			count = WebUI.getText(obj)
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Screen results alerts count not found")
		}
		return count
	}

	public boolean checkIfBaselineImgOnScreenResultsExists(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_Recommended/img_baselineScreenResults')
		if(WebUI.waitForElementVisible(obj,30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Baseline image on screen results popup not found")
		}
		return result
	}
}
