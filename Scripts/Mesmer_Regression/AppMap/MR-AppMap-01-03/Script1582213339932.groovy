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

import java.util.List
import java.util.ArrayList
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys

import controllers.CommonMethodsController
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

WebDriver driver = DriverFactory.getWebDriver()
try{

	//AppMap-01 - Verify that user can Add/Delete login credentials from Build Upload modal window
	checkAppMapTestCase01Steps();

	CommonMethodsController.getInstance().closeButton()

	//AppMap-02 - Verify that user can Add/Delete login credentials from Settings
	checkAppMapTestCase02Steps();

	//AppMap-03 - Verify that error message should appear in case user leaves username or password field empty
	checkAppMapTestCase03Steps();

}

catch(Exception e){
	e.printStackTrace()
}
finally{
	//	WebUI.refresh()
}


private boolean  checkAppMapTestCase01Steps(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	//Check project/build option
	if(WebUI.waitForElementVisible(buildProjectPopOver, 10) ==true){

		//Click on Project option down arrow button
		//2. user click on projects menu
		WebUI.click(buildProjectPopOver)

		//If Build upload button is displayed then click on Back arrow button so that 'Create New Project' button is displayed
		//3. user is on project menu drop down
		if(WebUI.waitForElementVisible(uploadNewBuildButton, 2)==true){
			//4. user click on back arrow from drop down
			WebUI.click(projectSlideArrow)

			WebUI.delay(2)


			if(WebUI.verifyElementVisible(projectList) == true){
				//5. user click on Create New Project button
				WebUI.click(btnCreateNewProject)

				addUser(userEmail, userPassword)

				deleteUser()

				result = true

			}

			else{
				MesmerLogUtils.markFailed("FAILED: Project List and Create New Project button not found")
			}

		}else{
			MesmerLogUtils.markFailed("FAILED: Upload new build button is not visible")
		}


	}
	else{
		MesmerLogUtils.markFailed("FAILED: Project drop down not found")
	}
	return result
}

def checkAppMapTestCase02Steps(){

	CustomKeywords.'com.mesmer.Utility.goToTestData'()


	addUserTestData()
	deleteUserTestData()
	//	deleteUser()
}

private boolean checkAppMapTestCase03Steps(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	//Check project/build option
	if(WebUI.waitForElementVisible(buildProjectPopOver, 10) ==true){

		//Click on Project option down arrow button
		//2. user click on projects menu
		WebUI.click(buildProjectPopOver)

		//If Build upload button is displayed then click on Back arrow button so that 'Create New Project' button is displayed
		//3. user is on project menu drop down
		if(WebUI.waitForElementVisible(uploadNewBuildButton, 10)==true){
			//4. user click on back arrow from drop down
			WebUI.click(projectSlideArrow)


			WebUI.delay(2)


			if(WebUI.verifyElementVisible(projectList) == true){
				//5. user click on Create New Project button
				WebUI.click(btnCreateNewProject)

				addUser(userEmail, "")

				if(WebUI.waitForElementPresent(requiredFieldValidator, 20)){
					WebUI.refresh()
					result = true
				}else{
					MesmerLogUtils.markFailed("FAILED: No required field pop up appears")
				}

			}else{
				MesmerLogUtils.markFailed("FAILED: Project List and Create New Project button not found")
			}
		}else{
			MesmerLogUtils.markFailed("FAILED: Upload new build button is not visible")
		}
	}
	else{
		MesmerLogUtils.markFailed("FAILED: Project drop down not found")
	}
	return result
}



private boolean addUser(String username, String password){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	if(WebUI.waitForElementVisible(addUserBtn, 5)==true){

		WebUI.click(addUserBtn)

		if(WebUI.waitForElementVisible(usernameField, 5)==true && WebUI.waitForElementVisible(passwordField, 5)==true){

			String user = listNumberOfUsersAdded.findPropertyValue("xpath")
			List<WebElement> listBeforeAddUser = driver.findElements(By.xpath(user))
			int sizeBeforeAddingUser = listBeforeAddUser.size()
			//
			WebUI.sendKeys(usernameField, username)
			WebUI.sendKeys(passwordField, password)

			if(username.length()>0 && password.length()>0)
			{
				if(WebUI.waitForElementVisible(titleCreateNewProject, 5)==true){
					WebUI.click(titleCreateNewProject)
					WebUI.delay(2)

					String user1 = listNumberOfUsersAdded.findPropertyValue("xpath")
					List<WebElement> listAfterAddUser = driver.findElements(By.xpath(user1))
					int sizeAfterAddingUser = listAfterAddUser.size()

					println("Number of added users" + sizeAfterAddingUser)

					if(sizeAfterAddingUser > sizeBeforeAddingUser){
						MesmerLogUtils.markPassed("PASSED: User is added successfully")
						result = true
					}
					else{
						MesmerLogUtils.markFailed("FAILED: User is not added successfully")
					}

				}
				else{
					MesmerLogUtils.markFailed("FAILED: Title - Create New Project is not found")
				}
			}else{
				if(WebUI.waitForElementVisible(titleCreateNewProject, 5)==true){
					WebUI.click(titleCreateNewProject)
					WebUI.delay(1)

					if(WebUI.waitForElementVisible(notificationPleaseFillReqfields, 5)==true){
						MesmerLogUtils.markPassed("PASSED: Required field notification is displayed")
						result = true
					}
					else{
						MesmerLogUtils.markFailed("FAILED: Required field notification is not displayed")
					}

				}
				else{
					MesmerLogUtils.markFailed("FAILED: Title - Create New Project is not found")
				}
			}
		}
		else{
			MesmerLogUtils.markFailed("FAILED: Username and Password field is not visible")

		}
	}
	else{
		MesmerLogUtils.markFailed("FAILED: Add user button is not found")
	}
	return result
}

private boolean deleteUser(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()

	String user1 = listNumberOfUsersAdded.findPropertyValue("xpath")
	List<WebElement> listAfterAddUser = driver.findElements(By.xpath(user1))
	int sizeBeforeDeletingUser = listAfterAddUser.size()
	println("Number of added users - " + sizeBeforeDeletingUser)


	String closeBtn = listDeleteBtn.findPropertyValue("xpath")
	List<WebElement> listCloseBtn = driver.findElements(By.xpath(closeBtn))
	listCloseBtn.get(0).click()

	String user2 = listNumberOfUsersAdded.findPropertyValue("xpath")
	List<WebElement> listAfterDeleteUser = driver.findElements(By.xpath(user2))
	int sizeAfterDeletingUser = listAfterDeleteUser.size()
	println("Size after deleting user - " + sizeAfterDeletingUser)

	if(sizeAfterDeletingUser < sizeBeforeDeletingUser){
		MesmerLogUtils.markPassed("PASSED: User is deleted successfully")
		result = true
	}else{
		MesmerLogUtils.markFailed("FAILED: User is not deleted")
	}
	return result
}

private boolean  addUserTestData(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()


	if(WebUI.waitForElementVisible(addUserBtnTestData, 5)==true){

		WebUI.click(addUserBtnTestData)
		WebUI.delay(2)

		if(WebUI.waitForElementVisible(usernameField, 5)==true && WebUI.waitForElementVisible(passwordField, 5)==true){

			String user = listOfAddedUsersTestData.findPropertyValue("xpath")
			List<WebElement> listBeforeAddUser = driver.findElements(By.xpath(user))
			int sizeBeforeAddingUser = listBeforeAddUser.size()
			println("Number of users before adding any user = " + sizeBeforeAddingUser)

			//
			WebUI.sendKeys(usernameField, userEmail)
			WebUI.sendKeys(passwordField, userPassword)

			WebUI.click(titleProvideTestData)
			WebUI.delay(1)

			String user1 = listOfAddedUsersTestData.findPropertyValue("xpath")
			List<WebElement> listAfterAddUser = driver.findElements(By.xpath(user1))
			int sizeAfterAddingUser = listAfterAddUser.size()

			println("Number of added users = " + sizeAfterAddingUser)

			if(sizeAfterAddingUser > sizeBeforeAddingUser){
				MesmerLogUtils.markPassed("PASSED: User is added successfully")
				result = true
			}
			else{
				MesmerLogUtils.markFailed("FAILED: User is not added successfully")
			}


		}
		else{
			MesmerLogUtils.markFailed("FAILED: User and Password field is not visible")
		}
	}
	else{
		MesmerLogUtils.markFailed("FAILED: Add user button is not found")
	}
	return result
}

private boolean deleteUserTestData(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()

	String user1 = listOfAddedUsersTestData.findPropertyValue("xpath")
	List<WebElement> listAfterAddUser = driver.findElements(By.xpath(user1))
	int sizeBeforeDeletingUser = listAfterAddUser.size()
	println("Number of added users - " + sizeBeforeDeletingUser)


	String closeBtn = deleteUserBtnTestData.findPropertyValue("xpath")
	List<WebElement> listCloseBtn = driver.findElements(By.xpath(closeBtn))
	listCloseBtn.get(0).click()

	WebUI.delay(1)

	String user2 = listOfAddedUsersTestData.findPropertyValue("xpath")
	List<WebElement> listAfterDeleteUser = driver.findElements(By.xpath(user2))
	int sizeAfterDeletingUser = listAfterDeleteUser.size()
	println("Size after deleting user - " + sizeAfterDeletingUser)

	WebUI.delay(1)
	if(sizeAfterDeletingUser < sizeBeforeDeletingUser){
		MesmerLogUtils.markPassed("PASSED: User is deleted successfully")
		result = true
	}else{
		MesmerLogUtils.markFailed("FAILED: User is not deleted")
	}
	return result
}