package com.mesmer

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Image
import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import java.awt.image.PixelGrabber
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration as RC
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import controllers.CreateTestController
import utils.ImageResizer

//import internal.GlobalVariable as GlobalVariable


public class Utility {

	@Keyword
	public boolean goToDeviceManager(){
		boolean result = false
		try{

			TestObject settingsOption = findTestObject('Object Repository/OR_Common/Option_Settings')
			TestObject verifyDeviceManagerPage = findTestObject('Object Repository/OR_Common/page_verifyDeviceManager')
			TestObject settingsDropDownVerification = findTestObject('Object Repository/OR_Common/dropdown_verifySettings')
			TestObject settingsDevicesOption = findTestObject('Object Repository/OR_Common/Option_Settings--Devices')
			TestObject settingsTestDataOption = findTestObject('Object Repository/OR_Common/Option_Settings--TestData')
			TestObject settingsJiraIntegrationOption = findTestObject('Object Repository/OR_Common/Option_Settings--JiraIntegration')

			if(WebUI.waitForElementPresent(settingsOption,5)){
				MesmerLogUtils.logInfo("Setting option is displayed. Clicking Settings")
				WebUI.click(settingsOption)
				MesmerLogUtils.logInfo("Settings option is clicked")

				//				if(WebUI.verifyElementVisible(settingsDevicesOption) && WebUI.verifyElementVisible(settingsTestDataOption) && WebUI.verifyElementVisible(settingsJiraIntegrationOption)){
				//					MesmerLogUtils.logInfo("Devices, Test Data and JiraIntegration option are displayed in dropdown")
				//					MesmerLogUtils.logInfo("Clicking Devices option")
				if(WebUI.waitForElementPresent(settingsDevicesOption,5)){
					WebUI.click(settingsDevicesOption)
					MesmerLogUtils.logInfo("Devices option is clicked")

					if(WebUI.waitForElementPresent(verifyDeviceManagerPage,10)==true){
						MesmerLogUtils.logInfo("User navigated to Device Manager page")
						WebUI.delay(5)
						result = true
					}
					else{
						MesmerLogUtils.logInfo("Clicked on device option")
					}
				}
				else{
					MesmerLogUtils.logInfo("User is not navigated to Device Manage page")
				}

				//				}else{
				//					MesmerLogUtils.logInfo("Devices, Test Data and JiraIntegration option are not displayed in dropdown")
				//				}

			}
			else{
				MesmerLogUtils.logInfo("Settings option is not displayed")
			}
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
		return result
	}

	@Keyword
	public boolean selectProject(TestObject projName, String xpProjName, String platformName_value, String projectName_value) {
		boolean result = false
		try {

			//		String xpProjName

			TestObject projectNameAtt = findTestObject('Object Repository/OR_Common/info_projectName')
			TestObject projectIconValue = findTestObject('Object Repository/OR_Common/img_projectIcon')

			String titleName = WebUI.getAttribute(projectNameAtt, 'title')
			String projectIcon = WebUI.getAttribute(projectIconValue, 'class')

			boolean isProjectSame = false;
			if(projectName_value.equals(titleName) && !projectIcon.contains(platformName_value)){
				isProjectSame = true;

			}
			else if(!projectName_value.equals(titleName) && projectIcon.contains(platformName_value)){
				isProjectSame = true;

			}
			else if(!projectName_value.equals(titleName) && !projectIcon.contains(platformName_value)){
				isProjectSame = true;

			}
			else{
				MesmerLogUtils.markWarning("Project is already selected")
				//				WebUI.click(null)
				result = true
				return result;
			}

			//			if(projectName_value != titleName){
			MesmerLogUtils.logInfo("Title of proj = " + titleName)

			if(isProjectSame){
				System.err.println("Project Icon = " + projectIcon)
				TestObject projectDropDownIcon = findTestObject('OR_CreateNewTestCase/project_dropdown')
				if(WebUI.verifyElementVisible(projectDropDownIcon)){
					WebUI.click(projectDropDownIcon)
					TestObject textProjectName = findTestObject('Object Repository/OR_Common/option_CheckProjName')
					if(WebUI.verifyElementVisible(textProjectName)){
						WebUI.click(findTestObject('OR_CreateNewTestCase/project_slideArrow'))

						WebUI.delay(2)

						MesmerLogUtils.logInfo("Calling Select Project Method")
						MesmerLogUtils.logInfo("" + projName)
						xpProjName = projName.findPropertyValue('xpath')
						MesmerLogUtils.logInfo("" + xpProjName)
						xpProjName = xpProjName.toString().replace('<pType>', platformName_value)
						MesmerLogUtils.logInfo("" + xpProjName)
						xpProjName = xpProjName.toString().replace('<pName>', projectName_value)
						MesmerLogUtils.logInfo("" + xpProjName)
						projName.findProperty('xpath').setValue(xpProjName)

						WebUI.click(projName)
						WebUI.delay(3)
						result = true
					}
					else{
						MesmerLogUtils.markWarning("Project Name text is not visibled")
					}
				}
				else{
					MesmerLogUtils.markWarning("Dropdown Icon not found")
				}

			}
			else{
				MesmerLogUtils.markWarning("Either name or icon is different")
			}
			//			CreateTestController.getInstance().checkIfDiscardAlertAppeared()

		}
		catch(Exception e)
		{
			e.printStackTrace()
		}
		return result
	}

	public static void selectProject(String projectName, String platformName, String buildNumber){
		try{
			TestObject projectNameAtt = findTestObject('Object Repository/OR_Common/info_projectName')
			TestObject projectIconValue = findTestObject('Object Repository/OR_Common/img_projectIcon')

			String titleName = WebUI.getAttribute(projectNameAtt, 'title')
			String buildNum = WebUI.getAttribute(projectNameAtt, 'id')
			String projectIcon = WebUI.getAttribute(projectIconValue, 'class')

			boolean isProjectSame = false;
			if(projectName.equals(titleName) && !projectIcon.contains(platformName)){
				isProjectSame = true;
			}
			else if(!projectName.equals(titleName) && projectIcon.contains(platformName)){
				isProjectSame = true;
			}
			else if(!projectName.equals(titleName) && !projectIcon.contains(platformName)){
				isProjectSame = true;
			}
			else{
				if(!buildNum.equalsIgnoreCase(buildNumber)){
					changeProjectBuild(buildNumber)
				}
				else{
					MesmerLogUtils.logInfo(projectName+" with provided build number "+buildNumber+" is already selected")
				}
				return;
			}

			if(isProjectSame){
				TestObject projectDropDownIcon = findTestObject('OR_CreateNewTestCase/project_dropdown')
				if(WebUI.verifyElementVisible(projectDropDownIcon)){
					WebUI.click(projectDropDownIcon)
					TestObject textProjectName = findTestObject('Object Repository/OR_Common/option_CheckProjName')
					if(WebUI.verifyElementVisible(textProjectName)){
						WebUI.click(findTestObject('OR_CreateNewTestCase/project_slideArrow'))

						WebUI.delay(2)

						MesmerLogUtils.logInfo("Calling Select Project Method")

						TestObject projName = getProjectSelectionObject()
						if(projName != null){
							MesmerLogUtils.logInfo("" + projName)
							String xpProjName = projName.findPropertyValue('xpath')
							MesmerLogUtils.logInfo("" + xpProjName)
							xpProjName = xpProjName.toString().replace('<pType>', platformName)
							MesmerLogUtils.logInfo("" + xpProjName)
							xpProjName = xpProjName.toString().replace('<pName>', projectName)
							MesmerLogUtils.logInfo("" + xpProjName)
							projName.findProperty('xpath').setValue(xpProjName)

							WebUI.click(projName)
							WebUI.delay(3)
							if(!buildNum.equalsIgnoreCase(buildNumber)){
								changeProjectBuild(buildNumber)
							}
							else{
								MesmerLogUtils.logInfo(projectName+" with provided build number "+buildNumber+" has been selected")
							}
						}
					}
					else{
						MesmerLogUtils.markWarning("Project Name text is not visibled")
					}
				}
				else{
					MesmerLogUtils.markWarning("Dropdown Icon not found")
				}
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
	}

	private static TestObject getProjectSelectionObject(){
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/project_selection')

		return obj
	}

	private static void changeProjectBuild(String buildNumber){
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			TestObject projectDropDownIcon = findTestObject('OR_CreateNewTestCase/project_dropdown')
			if(WebUI.verifyElementVisible(projectDropDownIcon)){
				WebUI.click(projectDropDownIcon)
				WebUI.delay(2)
				String buildsListXP = '//div[@class="builds-list"]/div'
				List<WebElement> elements = driver.findElements(By.xpath(buildsListXP))
				if(elements != null && elements.size() > 0){
					for(int i= 0; i < elements.size(); i++){
						String buildXPath = '//div[@class="builds-list"]/div['+(i+1)+']/div/descendant::span[1]'
						WebElement buildElement = driver.findElement(By.xpath(buildXPath))
						if(buildElement != null && buildElement.getText().equalsIgnoreCase(buildNumber)){
							TestObject to = new TestObject("buildNumberObj")
							to.addProperty("xpath", ConditionType.EQUALS,buildXPath)
							WebUI.scrollToElement(to, 10)
							WebUI.click(to)
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
	}

	@Keyword
	def selectTestCase(TestObject selectionTC, String testCaseName){
		String xpTCName
		//		TestObject selectionTag = findTestObject('Object Repository/OR_Replay/tagSelection')
		xpTCName = selectionTC.findPropertyValue('xpath')
		MesmerLogUtils.logInfo("" + xpTCName)
		xpTCName = xpTCName.toString().replace('<pTC>', testCaseName)
		MesmerLogUtils.logInfo("" + xpTCName)

		selectionTC.findProperty('xpath').setValue(xpTCName)
		MesmerLogUtils.logInfo("" + selectionTC)

		//		WebUI.click(selectionTC)

		return selectionTC
		WebUI.delay(1)
	}



	@Keyword
	def buildSelection(TestObject selectionBuild, String buildNumber){
		String xpBNumber
		//		TestObject selectionTag = findTestObject('Object Repository/OR_Replay/tagSelection')
		xpBNumber = selectionBuild.findPropertyValue('xpath')
		MesmerLogUtils.logInfo("" + xpBNumber)
		xpBNumber = xpBNumber.toString().replace('<pBN>', buildNumber)
		MesmerLogUtils.logInfo("" + xpBNumber)

		selectionBuild.findProperty('xpath').setValue(xpBNumber)
		MesmerLogUtils.logInfo("" + selectionBuild)

		//		WebUI.click(selectionTC)

		return selectionBuild
		WebUI.delay(1)
	}

	@Keyword
	def clickCheckbox(TestObject checkBox, String testCaseName){
		String xpChkBox
		//		TestObject selectionTag = findTestObject('Object Repository/OR_Replay/tagSelection')
		xpChkBox = checkBox.findPropertyValue('xpath')
		MesmerLogUtils.logInfo("" + xpChkBox)
		xpChkBox = xpChkBox.toString().replace('<pChk>', testCaseName)
		MesmerLogUtils.logInfo("" + xpChkBox)

		checkBox.findProperty('xpath').setValue(xpChkBox)
		MesmerLogUtils.logInfo("" + checkBox)

		//		WebUI.click(selectionTC)

		return checkBox
		WebUI.delay(1)
	}

	@Keyword
	def selectTag(TestObject selectionTag, String tagName){
		String xpTagName
		//		TestObject selectionTag = findTestObject('Object Repository/OR_Replay/tagSelection')
		xpTagName = selectionTag.findPropertyValue('xpath')
		MesmerLogUtils.logInfo("" + xpTagName)
		xpTagName = xpTagName.toString().replace('<pTag>', tagName)
		MesmerLogUtils.logInfo("" + xpTagName)

		selectionTag.findProperty('xpath').setValue(xpTagName)
		MesmerLogUtils.logInfo("" + selectionTag)

		WebUI.click(selectionTag)
		WebUI.delay(1)
	}

	@Keyword
	public boolean goToTestResults(){
		boolean result = false
		try{
			TestObject linkTestResult = findTestObject('Object Repository/OR_Common/Link_TestResults')
			if(WebUI.waitForElementPresent(linkTestResult, 10)){
				WebUI.click(linkTestResult)
				MesmerLogUtils.logInfo("Clicked on Test Result")
				WebUI.delay(2)
				result = true
			}else{
				MesmerLogUtils.logInfo("Could not click on Test Result")
			}

			if(CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()){

			}else{
				MesmerLogUtils.logInfo("Discard alert not appeared")
			}

		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
		return result
	}

	@Keyword
	def goToNotifications(){

		try{

			TestObject linkNotification = findTestObject('Object Repository/OR_Common/Link_Notifications')
			TestObject verifyNotificationClicked = findTestObject('Object Repository/OR_Common/link_VerifyNotificationClicked')

			WebUI.click(linkNotification)

			if(WebUI.waitForElementPresent(verifyNotificationClicked, 10)==true){

				MesmerLogUtils.logInfo("Notification Dialogue Open")
			}

			WebUI.delay(2)
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
	}



	@Keyword
	def goToManageTests(){

		try{

			TestObject testCases = findTestObject('Object Repository/OR_Common/Link_TestCases')
			WebUI.delay(1)
			if(WebUI.waitForElementPresent(testCases, 10)){
				WebUI.click(testCases)
				TestObject popoverManageTest = findTestObject('Object Repository/OR_Common/Popover_ManageTest')
				if(WebUI.waitForElementPresent(popoverManageTest, 10)){
					TestObject manageTests = findTestObject('Object Repository/OR_Common/Option_ManageTests')
					TestObject RecommendedTests = findTestObject('Object Repository/OR_Common/Option_RecommendedTests')
					TestObject createNewTest = findTestObject('Object Repository/OR_Common/Option_CreateNewTest')

					if(WebUI.waitForElementPresent(manageTests, 10) && WebUI.waitForElementPresent(RecommendedTests, 10) &&
					WebUI.waitForElementPresent(createNewTest, 10)){
						WebUI.click(manageTests)
						//						TestObject checkDropdown = findTestObject('Object Repository/OR_Common/dropdown_verifyTestCasesDropdown')
						TestObject verifyManageTestCasesPage = findTestObject('Object Repository/OR_Common/link_verifyTestCasesClicked')

						//						WebUI.verifyElementVisible(checkDropdown)
						WebUI.delay(2)
						if(WebUI.waitForElementPresent(verifyManageTestCasesPage,20)){
							MesmerLogUtils.logInfo("User navigated to Manage Tests page")
						}

						WebUI.delay(1)
					}
					else{
						MesmerLogUtils.markFailed("MS-ManageTest: Popover options does not match")
					}
				}
				else{
					MesmerLogUtils.markFailed("MS-ManageTest: ManageTest popover not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("MS-ManageTest: Test Cases label not found")
			}
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
	}

	@Keyword
	def goToRecommendedTests(){

		try{

			TestObject testCases = findTestObject('Object Repository/OR_Common/Link_TestCases')
			TestObject checkDropdown = findTestObject('Object Repository/OR_Common/dropdown_verifyTestCasesDropdown')
			TestObject recommendedTests = findTestObject('Object Repository/OR_Common/Option_RecommendedTests')
			TestObject verifyRecommendedTestCasesPage = findTestObject('OR_Recommended/page_verifyRecommendedTestCases')

			WebUI.click(testCases)
			WebUI.delay(1)
			WebUI.waitForElementPresent(checkDropdown,5)
			WebUI.waitForElementClickable(recommendedTests,	10)
			WebUI.click(recommendedTests)
			WebUI.delay(2)
			if(WebUI.waitForElementPresent(verifyRecommendedTestCasesPage, 5)==true){
				MesmerLogUtils.logInfo("User navigated to Recommended Test Cases page")
			}

			WebUI.delay(1)
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
	}

	@Keyword
	public boolean goToTestData(){
		boolean result = false
		try{
			TestObject settingsOption = findTestObject('Object Repository/OR_Common/Option_Settings')

			TestObject devicesOption = findTestObject('Object Repository/OR_Common/Option_Settings--Devices')
			TestObject testDataOption = findTestObject('Object Repository/OR_Common/Option_Settings--TestData')
			TestObject jiraOption = findTestObject('Object Repository/OR_Common/Option_Settings--TestData')


			TestObject settingsDropDownVerification = findTestObject('Object Repository/OR_Common/dropdown_verifySettings')
			TestObject testDataPageVerification = findTestObject('Object Repository/OR_Common/page_verifyProvideTestData')
			WebUI.delay(1)

			if(WebUI.waitForElementPresent(settingsOption, 5)==true){
				WebUI.click(settingsOption)
				WebUI.delay(1)

				if(WebUI.waitForElementPresent(devicesOption, 5)==true && WebUI.waitForElementPresent(testDataOption, 5)==true
				&& WebUI.waitForElementPresent(jiraOption, 5)==true){
					WebUI.click(testDataOption)
					MesmerLogUtils.logInfo("Clicked on test data option")
					WebUI.delay(2)
					if(WebUI.waitForElementPresent(testDataPageVerification,5)==true){
						MesmerLogUtils.logInfo("User navigated to Provide Test Data page")
						result = true
					}
					else{
						MesmerLogUtils.logInfo("Unable to verify test data page")
					}

				}else{
					MesmerLogUtils.logInfo("Device option / Test data option / Jira option not visible")
				}
			}else{
				MesmerLogUtils.logInfo("Could not click on settings option")
			}

			WebUI.delay(1)
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
		return result
	}

	@Keyword
	def goToJiraIntegration(){

		try{

			TestObject settingsOption = findTestObject('Object Repository/OR_Common/Option_Settings')
			TestObject settingsDropDownVerification = findTestObject('Object Repository/OR_Common/dropdown_verifySettings')
			TestObject jiraIntegrationOption = findTestObject('Object Repository/OR_Common/Option_Settings--JiraIntegration')
			TestObject jiraIntegrationPageVerification = findTestObject('Object Repository/OR_Common/page_verifyJiraIntegration')
			WebUI.delay(1)
			WebUI.click(settingsOption)
			WebUI.delay(1)
			WebUI.verifyElementVisible(settingsDropDownVerification)
			WebUI.click(jiraIntegrationOption)
			WebUI.delay(2)
			if(WebUI.verifyElementVisible(jiraIntegrationPageVerification)==true){
				MesmerLogUtils.logInfo("User navigated to Jira Integration page")
			}

			WebUI.delay(1)
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
	}

	@Keyword
	public boolean goToAppMap(){
		boolean result = false
		try{

			TestObject appMapOption = findTestObject('Object Repository/OR_Common/Link_AppMap')
			TestObject verifyAppMapPage = findTestObject('Object Repository/OR_Common/page_verifyAppMap')

			if(WebUI.waitForElementPresent(appMapOption,20)){
				WebUI.click(appMapOption)
				WebUI.delay(2)
				if(WebUI.waitForElementPresent(verifyAppMapPage, 10)==true){
					MesmerLogUtils.logInfo("User navigated to App Map page")
					WebUI.delay(1)
					result = true
				}else{
					MesmerLogUtils.logInfo("Could not verify App Map page")
				}
			}else{
				MesmerLogUtils.logInfo("Could not click on App Map link")
			}
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
		return result
	}


	@Keyword
	public boolean goToInviteUser(){
		boolean result = false
		try{

			TestObject userProfile = findTestObject('OR_LogIn/image_UserImageLogo')
			TestObject userProfileDropDownVerification = findTestObject('OR_LogIn/List_UserProfile')
			TestObject inviteUserOption = findTestObject('Object Repository/OR_Common/UserProfile_InviteUsers')
			TestObject inviteUserPageVerification = findTestObject('Object Repository/OR_Common/page_VerifyInviteUser')
			WebUI.delay(2)
			WebUI.click(userProfile)
			WebUI.delay(1)
			WebUI.waitForElementPresent(userProfileDropDownVerification,5)
			WebUI.click(inviteUserOption)
			WebUI.delay(1)
			if(WebUI.waitForElementPresent(inviteUserPageVerification,10)==true){
				MesmerLogUtils.logInfo("User navigated to Invite User page")
				result = true
			}
			else{
				MesmerLogUtils.markFailed("User unable to navigate to invite user page")
			}
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
		return result
	}

	@Keyword
	public boolean goToCreateNewTestCase(){
		boolean result = false
		try{
			TestObject testCasesOption = findTestObject('Object Repository/OR_Common/Link_TestCases')

			if(WebUI.waitForElementPresent(testCasesOption, 30)==true){
				WebUI.delay(1)
				WebUI.click(testCasesOption)
				WebUI.delay(2)
				//				TestObject dropdownTestCases = findTestObject('Object Repository/OR_Common/dropdown_verifyTestCasesDropdown')
				//				if(WebUI.waitForElementVisible(dropdownTestCases,30)){
				TestObject createNewTestOption = findTestObject('Object Repository/OR_Common/Option_CreateNewTest')
				if(WebUI.waitForElementPresent(createNewTestOption,10)==true){
					WebUI.delay(1)
					WebUI.click(createNewTestOption)
					WebUI.delay(2)
					result = true
				}
				else{
					MesmerLogUtils.markFailed("Create a New Test option is not found")
				}

			}
			else{
				MesmerLogUtils.markFailed("Test Cases link is not found")
			}
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
		return result
	}



	@Keyword
	def extractNumericData(String response){
		if(response){
			def numberList = response.findAll( /[0-9]+.[0-9]*|[0-9]*.[0-9]+|[0-9]+/ )
			if(numberList.size() == 1)
			{
				return numberList.get(0) as BigDecimal

			}
			else
			{
				return -1
			}
		}
	}

	@Keyword
	def getNumberOutOfString(String response){
		if(response){
			def numberList = response.findAll( /\d+/ )*.toInteger()
			if(numberList.size() == 1)
			{
				return numberList.get(0) as Integer

			}
			else
			{
				return -1
			}
		}
	}

	@Keyword
	def goToUserProfile(){

		try{
			WebUI.delay(1)
			TestObject profileOption = findTestObject('Object Repository/OR_Common/Option_Profile')
			TestObject selectProfile = findTestObject('Object Repository/OR_Common/UserProfile_YourProfile')
			TestObject verifyYourProfilePage = findTestObject('Object Repository/OR_Common/UserProfile_YourProfile')
			WebUI.click(profileOption)

			if(WebUI.verifyElementVisible(selectProfile)== true){
				WebUI.click(selectProfile)
				WebUI.delay(2)
			}

			if(WebUI.verifyElementVisible(verifyYourProfilePage)==true){
				MesmerLogUtils.logInfo("User navigated to Profile page")
			}

			WebUI.delay(1)
		}
		catch(NoSuchElementException e){
			e.printStackTrace()
		}
	}

	@Keyword
	def ImageComparisonByURL(URL file1, URL file2){
		//		String file1 = "/Users/untitled/Desktop/Image1.png"
		//		String file2 = "/Users/untitled/Desktop/Image3.png"

		//		Image image1 = Toolkit.getDefaultToolkit().getImage(file1)
		//		Image image2 = Toolkit.getDefaultToolkit().getImage(file2)
		//
		Image image1 = Toolkit.getDefaultToolkit().getImage(file1)
		Image image2 = Toolkit.getDefaultToolkit().getImage(file2)

		PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1, false)
		PixelGrabber grab2 = new PixelGrabber(image2, 0, 0, -1, -1, false)


		int[] data1 = null

		if(grab1.grabPixels()){
			int width = grab1.getWidth()
			int height = grab1.getHeight()
			MesmerLogUtils.logInfo("Width of Image1 = " + width)
			MesmerLogUtils.logInfo("Height of Image1 = " + height)
			data1 = new int[width * (height-60)]
			data1 = (int[]) grab1.getPixels()
			//			println("DATA of Image1 = " + data1)
		}

		int[] data2 = null

		if(grab2.grabPixels()){
			int width = grab2.getWidth()
			int height = grab2.getHeight()
			MesmerLogUtils.logInfo("Width of Image2 = " + width)
			MesmerLogUtils.logInfo("Height of Image2 = " + height)
			data2 = new int[width * (height-60)]
			data2 = (int[]) grab2.getPixels()
			//			println("DATA of Image2 = " + data2)
		}


		if(Arrays.equals(data1, data2)==true){
			MesmerLogUtils.markPassed("Both images are equal")
		}
		else{
			MesmerLogUtils.markFailed("Images are different")
		}

	}

	/**
	 * Clear Input text of element
	 */
	@Keyword
	def clearElementText(String to) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(1)
		WebElement element = driver.findElement(By.xpath(to))
		WebUI.executeJavaScript("arguments[0].value=''", Arrays.asList(element))
	}

	private static String platformName = "";
	@Keyword
	def static void setPlatformName(String name){
		if(name == null || name.equalsIgnoreCase("") || name.equalsIgnoreCase("Generic")){
			platformName = "Generic"
		}
		else if(name.equalsIgnoreCase("apple")){
			platformName = "iOS"
		}
		else if(name.equalsIgnoreCase("android")){
			platformName = "Android"
		}
		else{
			platformName = name
		}
	}
	def static String getPlatformName(){
		return platformName;
	}


	@Keyword
	def uploadFile (TestObject to, String filePath) {
		WebUI.click(to)
		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	@Keyword
	public boolean stopExecution(){

		boolean result = false
		goToTestResults()
		WebUI.delay(3)
		TestObject stopButton = findTestObject('Object Repository/OR_Replay/stop_execution')
		if(WebUI.waitForElementClickable(stopButton, 20)){
			WebUI.click(stopButton)
			WebUI.delay(2)
			TestObject yesButton = findTestObject('Object Repository/OR_Replay/btn_Yes')
			if(WebUI.waitForElementClickable(yesButton, 20)){
				WebUI.click(yesButton)
				WebUI.delay(10)
				result = true
			}else{
				MesmerLogUtils.logInfo("Unable to click on stop execution confirmation Yes button")
			}
		}else{
			MesmerLogUtils.logInfo("Unable to click on the hand icon")
		}
		return result
	}

	// Check the provided device type and select it
	public static WebElement selectDeviceAndSetParams(String deviceType, String language, String gpsLatLng, String tag){
		selectDeviceAndSetParams("","",deviceType,language,"",gpsLatLng, tag)
	}

	// Check the provided device type and select it
	public static String selectDeviceAndSetParams(String imagePath, String Srno, String deviceType, String language, String region, String gpsLatLng, String tag){
		String searchedDevice = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebUI.delay(4)
		// Get devices list
		String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
		List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
		WebUI.delay(2)
		if(devicesList != null && devicesList.size() > 0){
			for(int i=0; i < devicesList.size(); i++){
				String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div'
				WebElement deviceStatusStr = driver.findElement(By.xpath(devicesStatusXPath))
				String deviceTypeXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="mesmerSimpleTooltip ng-star-inserted"]/span[@class="deviceUDID singleEllipses"]'
				WebElement deviceTypeElement = driver.findElement(By.xpath(deviceTypeXPath))

				if(deviceStatusStr != null && (deviceStatusStr.getText().contains("Ready") ||
				deviceStatusStr.getText().contains("Provisioned")) &&
				deviceTypeElement.getText().contains(deviceType)){

					String deviceNameXP = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="infoText ng-star-inserted"]/span[@class="infoText"]'
					WebElement deviceNameElement = driver.findElement(By.xpath(deviceNameXP))
					if(deviceNameElement != null){
						searchedDevice = deviceNameElement.getText()
					}
					MesmerLogUtils.logInfo("Device Name " +  " "  + deviceNameElement.getText())

					String alreadySelectedXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"selected ng-star-inserted")]/div[2]/div[@class="mesmerSimpleTooltip ng-star-inserted"]/span[@class="deviceUDID singleEllipses"]'
					List<WebElement> alreadySelectedElement = driver.findElements(By.xpath(alreadySelectedXPath))
					if(alreadySelectedElement != null && alreadySelectedElement.size() > 0){
						MesmerLogUtils.logInfo("Device already selected " + " : " +  alreadySelectedElement.get(0).getText())
					}else{
						devicesList.get(i).click()
						WebUI.delay(3)
					}


					if(!imagePath.isEmpty()){
						Utility.takeScreenshot(imagePath, "imageSelected-"+Srno)
					}
					break
				}
			}
			if(CreateTestController.getInstance().clickNextBtn()){
				if(!language.isEmpty()){
					if(CreateTestController.getInstance().setDeviceLanguage(language, "US")){
						MesmerLogUtils.logInfo("Language selected and applied")
					}
				}
				if(!region.isEmpty()){
					if(CreateTestController.getInstance().setDeviceRegion(region)){
						MesmerLogUtils.logInfo("Region selected and applied")
					}
				}
				if(!gpsLatLng.isEmpty()){
					if(CreateTestController.getInstance().setGPSLocation(gpsLatLng)){
						MesmerLogUtils.logInfo("GPS Location selected and applied")
					}
				}
				if(!tag.isEmpty()){
					if(CreateTestController.getInstance().setDeviceRegion(tag)){
						MesmerLogUtils.logInfo("Tag selected and applied")
					}
				}
				if(CreateTestController.getInstance().startBuildLoading(tag)){
					MesmerLogUtils.logInfo("All configs are configured successfully")
				}
			}
		}
		else{
			MesmerLogUtils.markWarning("There is no device in the list")
		}

		return searchedDevice
	}

	// Check the provided device type and select it
	public static WebElement selectDevice(String deviceType){
		WebElement searchedDevice = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebUI.delay(4)
		// Get devices list
		String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
		List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
		WebUI.delay(2)
		if(devicesList != null && devicesList.size() > 0){
			for(int i=0; i < devicesList.size(); i++){
				String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div'
				WebElement deviceStatusStr = driver.findElement(By.xpath(devicesStatusXPath))
				String deviceTypeXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="mesmerSimpleTooltip ng-star-inserted"]/span[@class="deviceUDID singleEllipses"]'
				WebElement deviceTypeElement = driver.findElement(By.xpath(deviceTypeXPath))
				if(deviceStatusStr != null && (deviceStatusStr.getText().contains("Ready") ||
				deviceStatusStr.getText().contains("Provisioned")) &&
				deviceTypeElement.getText().contains(deviceType)){
					searchedDevice = devicesList.get(i)
					break
					//					String deviceUdIdXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[3]/span[@class="deviceUDID singleEllipses"]'
					//					WebElement deviceUDIDElement = driver.findElement(By.xpath(deviceUdIdXPath))
					//					if(deviceUDIDElement != null){
					//						searchedDevice = deviceUDIDElement
					//						break
					//					}
				}
			}
		}
		else{
			MesmerLogUtils.markWarning("There is no device in the list")
		}

		return searchedDevice
	}

	/**
	 * New method to fetch the available devices list based on their provided type
	 * @param deviceType (Virtual/Physical)
	 * @return
	 */
	public static List<WebElement> getAvailableDevicesList(String deviceType){
		List<WebElement> searchedDevicesList = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebUI.delay(4)
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/OR_CreateNewTestCase/div_DevicesWindow"), 60)){
			if(checkIfNoDeviceAvailable()){
				MesmerLogUtils.markFailed("No device available in the list")
				return searchedDevicesList
			}

			// Get devices list
			String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
			//div[@class="deviceList ng-star-inserted"]/div[1]/div[@class="deviceDetails"]/div[@class="deviceState"]/div/div
			List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
			if(devicesList != null && devicesList.size() > 0){
				searchedDevicesList = new ArrayList<WebElement>()
				for(int i=0; i < devicesList.size(); i++){
					String deviceTypeXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="mesmerSimpleTooltip ng-star-inserted"]/span[@class="deviceUDID singleEllipses"]'
					WebElement deviceTypeElement = driver.findElement(By.xpath(deviceTypeXPath))
					String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div'
					WebElement deviceStatusElement = driver.findElement(By.xpath(devicesStatusXPath))
					if(deviceTypeElement != null && deviceTypeElement.getText().contains(deviceType)){
						if(deviceStatusElement != null && (deviceStatusElement.getText().contains("Ready") ||
						deviceStatusElement.getText().contains("Provisioned"))){
							searchedDevicesList.add(devicesList.get(i))
						}
					}
				}
			}
			else{
				MesmerLogUtils.markWarning("There is no device in the list")
			}
		}
		else{
			MesmerLogUtils.markWarning("Devices list window not appeared")
		}
		return searchedDevicesList
	}

	public static List<WebElement> getAvailableDevices(String deviceType){
		List<WebElement> searchedDevicesList = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebUI.delay(4)
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/OR_CreateNewTestCase/div_DevicesWindow"), 60)){
			if(checkIfNoDeviceAvailable()){
				MesmerLogUtils.markFailed("No device available in the list")
				return searchedDevicesList
			}

			// Get devices list
			String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
			//div[@class="deviceList ng-star-inserted"]/div[1]/div[@class="deviceDetails"]/div[@class="deviceState"]/div/div
			List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
			if(devicesList != null && devicesList.size() > 0){
				searchedDevicesList = new ArrayList<WebElement>()
				for(int i=0; i < devicesList.size(); i++){
					String deviceTypeXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="infoText"]'
					WebElement deviceTypeElement = driver.findElement(By.xpath(deviceTypeXPath))
					String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div'
					WebElement deviceStatusElement = driver.findElement(By.xpath(devicesStatusXPath))
					if(deviceTypeElement != null && deviceTypeElement.getText().contains(deviceType)){
						if(deviceStatusElement != null && (deviceStatusElement.getText().contains("Ready") ||
						deviceStatusElement.getText().contains("Provisioned"))){
							searchedDevicesList.add(devicesList.get(i))
						}
					}
				}
			}
			else{
				MesmerLogUtils.markWarning("There is no device in the list")
			}
		}
		else{
			MesmerLogUtils.markWarning("Devices list window not appeared")
		}
		return searchedDevicesList
	}

	public static boolean checkIfNoDeviceAvailable(){
		boolean result = false;
		if(WebUI.waitForElementVisible(findTestObject("Object Repository/OR_CreateNewTestCase/div_NoDeviceAvailable"), 4)){
			result = true
		}
		return result
	}

	public static String getBaseUrl(){
		String url = WebUI.getUrl()
		String result = ""
		if(url != null && !url.isEmpty()){
			url = url.replace("https://", "")
			String[] splittedUrl = url.split("/")
			if(splittedUrl != null){
				result = "https://"+splittedUrl[0]+"/"
			}
		}
		else{
			MesmerLogUtils.markWarning("Test case url not found ")
		}
		return result
	}

	public static void logCurrentUTCTime(String logInfo){
		Instant instant = Instant.now();
		MesmerLogUtils.logInfo(logInfo+" "+instant.toString())
	}

	public static void zipFile(String outputFolder, String filePath) {
		try {
			File file = new File(filePath);
			String zipFileName = file.getName().concat(".zip");

			FileOutputStream fos = new FileOutputStream(outputFolder+"/"+zipFileName);
			ZipOutputStream zos = new ZipOutputStream(fos);

			zos.putNextEntry(new ZipEntry(file.getName()));

			byte[] bytes = Files.readAllBytes(Paths.get(filePath));
			zos.write(bytes, 0, bytes.length);
			zos.closeEntry();
			zos.close();

		} catch (FileNotFoundException ex) {
			System.err.format("The file %s does not exist", filePath);
		} catch (IOException ex) {
			System.err.println("I/O error: " + ex);
		}
	}

	public static void deleteFile(String fileName){
		File file = new File(fileName);

		if(file.delete())
		{
			System.out.println("File deleted successfully");
		}
		else
		{
			System.out.println("Failed to delete the file");
		}
	}

	public static boolean checkIfSpinnerNotVisible(){
		boolean result = false
		TestObject spinnerObj = findTestObject('Object Repository/OR_CreateNewTestCase/div_Spinner')
		if(WebUI.verifyElementHasAttribute(spinnerObj,'hidden', 120)){
			result = true
			MesmerLogUtils.logInfo("Spinner has disappeared")
		}
		else{
			MesmerLogUtils.logInfo("Spinner did not disappear within 2 minutes")
		}
		return result
	}

	public static boolean isWindows(){
		String OS = System.getProperty("os.name").toLowerCase()
		return (OS.indexOf("win") >= 0)
	}

	public static boolean isMac(){
		String OS = System.getProperty("os.name").toLowerCase()
		return (OS.indexOf("mac") >= 0)
	}

	public static boolean isDefaultProfile(){
		def executionProfile = RC.getExecutionProfile()
		return executionProfile.equalsIgnoreCase("default")
	}

	public static boolean isSanityProfile(){
		def executionProfile = RC.getExecutionProfile()
		return executionProfile.equalsIgnoreCase("sanity")
	}

	public static boolean isRegressionProfile(){
		def executionProfile = RC.getExecutionProfile()
		return executionProfile.equalsIgnoreCase("regression")
	}

	public static boolean isProductionProfile(){
		def executionProfile = RC.getExecutionProfile()
		return executionProfile.equalsIgnoreCase("production")
	}

	public static boolean isPocProfile(){
		def executionProfile = RC.getExecutionProfile()
		return executionProfile.equalsIgnoreCase("poc")
	}

	public static boolean isStressTestProfile(){
		def executionProfile = RC.getExecutionProfile()
		return executionProfile.equalsIgnoreCase("stressTest")
	}

	public static void takeScreenshot(String folderName, String fileName){
		String inputFilePath = folderName+fileName+".png"
		String filePath = WebUI.takeScreenshot(inputFilePath)

		String outFilePath = folderName+fileName+".png"

		ImageResizer.resize(filePath, outFilePath, 1.0)
		//		deleteFile(filePath)
	}

	public static String getSlash(){
		if (isMac()){
			return "/"
		}
		if (isWindows()){
			return "\\\\"
		}
	}


	public static boolean compareDateTime(String dateTime1, String dateTime2){
		boolean result = false
		//		dateTime1 = formatDateTime("Apr 29, 2020 11:45 PM")
		//		dateTime2 = formatDateTime("Apr 29, 2020 7:21 PM")
		dateTime1 = formatDateTime(dateTime1)
		dateTime2 = formatDateTime(dateTime2)
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy hh:mm aa",Locale.US);
		Date date1 = sdf.parse(dateTime1);
		Date date2 = sdf.parse(dateTime2);

		System.out.println("date1 : " + sdf.format(date1));
		System.out.println("date2 : " + sdf.format(date2));

		if (date1.compareTo(date2) > 0) {
			result = true
			System.out.println("Date1 is after Date2");
		} else if (date1.compareTo(date2) < 0) {
			System.out.println("Date1 is before Date2");
		} else if (date1.compareTo(date2) == 0) {
			System.out.println("Date1 is equal to Date2");
		}

		return result
	}

	public static boolean compareDateTimeEqual(String dateTime1, String dateTime2){
		boolean result = false
		//		dateTime1 = formatDateTime("Apr 29, 2020 11:45 PM")
		//		dateTime2 = formatDateTime("Apr 29, 2020 7:21 PM")
		dateTime1 = formatDateTime(dateTime1)
		dateTime2 = formatDateTime(dateTime2)
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy hh:mm aa",Locale.US);
		Date date1 = sdf.parse(dateTime1);
		Date date2 = sdf.parse(dateTime2);

		System.out.println("date1 : " + sdf.format(date1));
		System.out.println("date2 : " + sdf.format(date2));

		if (date1.compareTo(date2) > 0) {

			System.out.println("Date1 is after Date2");
		} else if (date1.compareTo(date2) < 0) {
			System.out.println("Date1 is before Date2");
		} else if (date1.compareTo(date2) == 0) {
			result = true
			System.out.println("Date1 is equal to Date2");
		}

		return result
	}


	private static String formatDateTime(String dateTime){
		String result = ""
		dateTime = dateTime.replace(",", "")
		String[] splitter = dateTime.split(" ")
		if(splitter != null){
			String month = splitter[0]
			String day = splitter[1]
			String year = splitter[2]
			String hourmints = splitter[3]
			String amPm = splitter[4]

			result = month+"-"+day+"-"+year+" "+hourmints+" "+amPm
		}
		return result
	}

	// Check the provided device type and select it
	public static String selectDeviceAndSetDeviceParam(String deviceType){
		String searchedDevice = null
		WebDriver driver = DriverFactory.getWebDriver()
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebUI.delay(4)
		// Get devices list
		String devicesListXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]'
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(devicesListXPath)));
		List<WebElement> devicesList = driver.findElements(By.xpath(devicesListXPath))
		WebUI.delay(2)
		if(devicesList != null && devicesList.size() > 0){
			for(int i=0; i < devicesList.size(); i++){
				String devicesStatusXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="deviceState"]/div/div'
				WebElement deviceStatusStr = driver.findElement(By.xpath(devicesStatusXPath))
				String deviceTypeXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="mesmerSimpleTooltip ng-star-inserted"]/span[@class="deviceUDID singleEllipses"]'
				WebElement deviceTypeElement = driver.findElement(By.xpath(deviceTypeXPath))

				if(deviceStatusStr != null && (deviceStatusStr.getText().contains("Ready") ||
				deviceStatusStr.getText().contains("Provisioned")) &&
				deviceTypeElement.getText().contains(deviceType)){

					String deviceNameXP = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"ng-star-inserted")]['+(i+1)+']/div[2]/div[@class="infoText ng-star-inserted"]/span[@class="infoText"]'
					WebElement deviceNameElement = driver.findElement(By.xpath(deviceNameXP))
					if(deviceNameElement != null){
						searchedDevice = deviceNameElement.getText()
					}
					MesmerLogUtils.logInfo("Device Name " +  " "  + deviceNameElement.getText())

					String alreadySelectedXPath = '//div[@class="deviceList ng-star-inserted"]/div[contains(@class,"selected ng-star-inserted")]/div[2]/div[@class="mesmerSimpleTooltip ng-star-inserted"]/span[@class="deviceUDID singleEllipses"]'
					List<WebElement> alreadySelectedElement = driver.findElements(By.xpath(alreadySelectedXPath))
					if(alreadySelectedElement != null && alreadySelectedElement.size() > 0){
						MesmerLogUtils.logInfo("Device already selected " + " : " +  alreadySelectedElement.get(0).getText())

						break;
					}else{
						devicesList.get(i).click()

						WebUI.delay(3)
						break;
					}
				}else{
					MesmerLogUtils.logInfo("No ready or provision device found in a device list")
				}
			}
		}else{
			MesmerLogUtils.markFailed("No device available")
		}
		return searchedDevice
	}
}