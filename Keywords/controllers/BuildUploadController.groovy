package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility



public class BuildUploadController {

	private static BuildUploadController mInstance = null

	private BuildUploadController(){
	}

	public static BuildUploadController getInstance(){
		if(mInstance == null){
			mInstance = new BuildUploadController()
		}
		return mInstance
	}


	public boolean verifyCreateNewProjectDialogue(){
		boolean result = false
		TestObject createNewProjectDialog = findTestObject('Object Repository/OR_BuildUpload/dialogue_createNewProject')
		TestObject uploadApp = findTestObject('Object Repository/OR_BuildUpload/verify-UploadApp')
		TestObject dragAndDrop = findTestObject('Object Repository/OR_BuildUpload/verify-UploadApp')
		TestObject userLoginOption = findTestObject('Object Repository/OR_BuildUpload/verify_userLoginOptional')
		TestObject userLoginForCrawl = findTestObject('Object Repository/OR_BuildUpload/verify_subTitleUserLoginForCrawl')
		TestObject btnNext = findTestObject('Object Repository/OR_BuildUpload/btnNext')
		TestObject btnCancel = findTestObject('Object Repository/OR_BuildUpload/btn_cancel')
		TestObject btnCross = findTestObject('Object Repository/OR_BuildUpload/btn_closeBuild')

		if(WebUI.waitForElementPresent(createNewProjectDialog, 5)){
			if(WebUI.waitForElementPresent(uploadApp, 5)){
				if(WebUI.waitForElementPresent(dragAndDrop, 5)){
					if(WebUI.waitForElementPresent(userLoginOption, 5)){
						if(WebUI.waitForElementPresent(userLoginForCrawl, 5)){
							if(WebUI.waitForElementPresent(btnNext, 5)){
								if(WebUI.waitForElementPresent(btnCancel, 5)){
									if(WebUI.waitForElementPresent(btnCross, 5)){
										result = true
									}else{
										MesmerLogUtils.logInfo("Could not verify Cross button")
									}
								}else{
									MesmerLogUtils.logInfo("Could not verify Cancel button")
								}
							}else{
								MesmerLogUtils.logInfo("Could not verify Next button")
							}
						}else{
							MesmerLogUtils.logInfo("Could not verify Provide user login for crawl.")
						}
					}else{
						MesmerLogUtils.logInfo("Could not verify User Login (Optional)")
					}
				}else{
					MesmerLogUtils.logInfo("Could not verif Drag and drop your .ipa, .apk, .app, .zip file")
				}
			}else{
				MesmerLogUtils.logInfo("Could not verify Upload App text")
			}
		}else{
			MesmerLogUtils.logInfo("Could not verify create new project dialogue")
		}
		return result
	}

	public boolean verifyUploadBuildDialogue(){
		boolean result = false
		TestObject uploadBuildDialog = findTestObject('Object Repository/OR_BuildUpload/dialogue_UploadAppBuild')
		TestObject projName = findTestObject('Object Repository/OR_BuildUpload/verify_projName')
		TestObject projType = findTestObject('Object Repository/OR_BuildUpload/verify_projType')
		TestObject dragDropiOS = findTestObject('Object Repository/OR_Replay/dragdrop_iOS')
		TestObject dragDropAndroid = findTestObject('Object Repository/OR_Replay/dragDrop_Android')

		if(WebUI.waitForElementPresent(uploadBuildDialog, 20)){
			if(WebUI.waitForElementPresent(projName, 5)){
				if(WebUI.waitForElementPresent(projType, 5)){

					if(Utility.getPlatformName() == "Android") {
						if(WebUI.waitForElementPresent(dragDropAndroid , 5)){

							verifyUploadBuildDialogueElements()
							result = true
						}else{
							MesmerLogUtils.logInfo("Could not verify Drag and drop your .APK with x86 flag enabled here")
						}
					}else if (Utility.getPlatformName() == "iOS") {
						if(WebUI.waitForElementPresent(dragDropiOS , 5)){

							verifyUploadBuildDialogueElements()
							result = true
						}else{
							MesmerLogUtils.logInfo("Could not verify Zip your .APP and drag then drop it here")
						}
					}
				}else{
					MesmerLogUtils.logInfo("Could not verify proj type")
				}
			}else{
				MesmerLogUtils.logInfo("Could not verify proj name")
			}
		}else{
			MesmerLogUtils.logInfo("Could not verify upload new build dialogue")
		}
		return result
	}

	public boolean verifyUploadBuildDialogueElements(){
		boolean result = false

		TestObject userLoginOption = findTestObject('Object Repository/OR_BuildUpload/verify_userLoginOptional')
		TestObject userLoginForCrawl = findTestObject('Object Repository/OR_BuildUpload/verify_subTitleUserLoginForCrawl')
		TestObject btnNext = findTestObject('Object Repository/OR_BuildUpload/btnNext')
		TestObject btnCancel = findTestObject('Object Repository/OR_BuildUpload/btn_cancel')
		TestObject btnCross = findTestObject('Object Repository/OR_BuildUpload/btn_closeBuild')

		if(WebUI.waitForElementPresent(userLoginOption, 5)){
			if(WebUI.waitForElementPresent(userLoginForCrawl, 5)){
				if(WebUI.waitForElementPresent(btnNext, 5)){
					if(WebUI.waitForElementPresent(btnCancel, 5)){
						if(WebUI.waitForElementPresent(btnCross, 5)){
							result = true
						}else{
							MesmerLogUtils.logInfo("Could not verify Cross button")
						}
					}else{
						MesmerLogUtils.logInfo("Could not verify Cancel button")
					}
				}else{
					MesmerLogUtils.logInfo("Could not verify Next button")
				}
			}else{
				MesmerLogUtils.logInfo("Could not verify Provide user login for crawl.")
			}
		}else{
			MesmerLogUtils.logInfo("Could not verify User Login (Optional)")
		}

		return result
	}

	public boolean clickCreateNewProjectButton(){
		boolean result = false

		TestObject buildProjectPopOver = findTestObject('Object Repository/OR_BuildUpload/dropdown_buildProjectPopover')
		TestObject uploadNewBuildButton = findTestObject('Object Repository/OR_BuildUpload/btn_uploadbuild')
		TestObject projectList = findTestObject('Object Repository/OR_BuildUpload/projectList')
		TestObject projectSlideArrow = findTestObject('Object Repository/OR_BuildUpload/project_slideArrow')
		TestObject btnCreateNewProject = findTestObject('Object Repository/OR_BuildUpload/btnCreateNewProject')
		TestObject windowCreateNewProject = findTestObject('Object Repository/OR_BuildUpload/window_createNewProject1')
		String textVerifyForUploadDialogue = "Create New Project"
		TestObject lblBrowseToSelect = findTestObject('Object Repository/OR_BuildUpload/label_BrowseToSelectFile')


		//Check project/build option
		if(WebUI.waitForElementPresent(buildProjectPopOver, 60) ==true){

			//Click on Project option down arrow button
			//2. user click on projects menu
			WebUI.click(buildProjectPopOver)

			//If Build upload button is displayed then click on Back arrow button so that 'Create New Project' button is displayed
			// user is on project menu drop down
			if(WebUI.waitForElementPresent(uploadNewBuildButton, 20)==true){

				if(WebUI.waitForElementPresent(projectSlideArrow, 20)==true){
					// user click on back arrow from drop down
					WebUI.click(projectSlideArrow)

					WebUI.delay(2)

					if(WebUI.verifyElementVisible(projectList) == true){

						WebUI.delay(2)
						// user click on Create New Project button
						if(WebUI.verifyElementVisible(btnCreateNewProject) == true){
							WebUI.click(btnCreateNewProject)

							WebUI.delay(2)
							if(WebUI.verifyElementText(windowCreateNewProject, textVerifyForUploadDialogue)){

								if(WebUI.verifyElementVisible(lblBrowseToSelect) == true){
									result = true

								}else{
									MesmerLogUtils.logInfo("Label browse to select not found ")
								}
							}
							else{
								MesmerLogUtils.logInfo("User is not on Build Upload Dialogue")
							}
						}else{
							MesmerLogUtils.logInfo("Create New Project button not found")
						}
					}else{
						MesmerLogUtils.logInfo("Project list is not displayed")
					}
				}else{
					MesmerLogUtils.logInfo("Could no click on project side arrow")
				}
			}else{
				MesmerLogUtils.logInfo("Build upload button is displayed")
			}
		}
		else{
			MesmerLogUtils.logInfo("Build project drop down not found")
		}
		return result
	}

	public boolean clickUploadBuildExisiting(){
		boolean result = false
		TestObject clickProjectdropdown = findTestObject('Object Repository/OR_CreateNewTestCase/project_dropdown')
		TestObject buttonBuildUpload = findTestObject('Object Repository/OR_BuildUpload/btn_uploadbuild')
		TestObject uploadAppBuild = findTestObject('Object Repository/OR_BuildUpload/window_uploadAppBuild')
		TestObject lblBrowseToSelect = findTestObject('Object Repository/OR_BuildUpload/label_BrowseToSelectFile')

		if(WebUI.waitForElementVisible(clickProjectdropdown, 20) ==true){
			WebUI.click(clickProjectdropdown)
			//		WebUI.click(projectSlideArrow)
			WebUI.delay(2)
			if(WebUI.waitForElementVisible(buttonBuildUpload,60) == true){
				//1. Click on Upload new build from the Project list on the top left corner of the mesmer console
				WebUI.click(buttonBuildUpload)
				println("Build upload button is clicked")
				WebUI.delay(2)

				//Verification - Upload your latest build window will appear
				if(WebUI.waitForElementVisible(uploadAppBuild, 20, FailureHandling.CONTINUE_ON_FAILURE)==true){

					//2. Drag/Browse the required app build with valid app login credentials (Optional)
					if(WebUI.verifyElementVisible(lblBrowseToSelect) == true){
						//	WebUI.click(btnBrowseToSelect)
						result = true
					}
					else{
						MesmerLogUtils.logInfo("User is not on Build Upload Dialogue")
					}
				}

				else{
					MesmerLogUtils.logInfo("Project List and Create New Project button not found")
				}

			}
			else{
				MesmerLogUtils.logInfo("Unable to click on button build upload")
			}

		}else{
			MesmerLogUtils.logInfo("Unable to click on drop down ")
		}
		return result
	}

	public boolean CreateNewProjectNameDialogue(){
		boolean result = false

		TestObject verifyCreateNewProjectNameDialogue = findTestObject('Object Repository/OR_BuildUpload/dialogue_createNewProjectName')
		TestObject btnNext = findTestObject('Object Repository/OR_BuildUpload/btnNext')
		TestObject btnCancel = findTestObject('Object Repository/OR_BuildUpload/btn_cancel')
		TestObject btnCross = findTestObject('Object Repository/OR_BuildUpload/btn_closeBuild')
		TestObject lblProjectName = findTestObject('Object Repository/OR_BuildUpload/label_ProjectName')
		TestObject projectNameField = findTestObject('Object Repository/OR_BuildUpload/field_newProjectNameField')

		if(WebUI.waitForElementPresent(verifyCreateNewProjectNameDialogue, 10)){
			if(WebUI.waitForElementPresent(btnNext, 10)){
				if(WebUI.waitForElementPresent(btnCancel, 10)){
					if(WebUI.waitForElementPresent(btnCross, 10)){

						if(WebUI.waitForElementPresent(lblProjectName, 10)){

							if(WebUI.waitForElementPresent(projectNameField, 10)){

								result = true

							}else{
								MesmerLogUtils.logInfo("Unable to verify Project Name field on Create New Project Name Dialogue")
							}

						}else{
							MesmerLogUtils.logInfo("Unable to verify Label on Create New Project Name Dialogue")
						}

					}else{
						MesmerLogUtils.logInfo("Unable to verify Cross button on Create New Project Name Dialogue")
					}
				}else{
					MesmerLogUtils.logInfo("Unable to verify Cancel button on Create New Project Name Dialogue")
				}
			}else{
				MesmerLogUtils.logInfo("Unable to verify Next button on Create New Project Name Dialogue")
			}
		}else{
			MesmerLogUtils.logInfo("Unable to verify Create New Project Name Dialogue")
		}
		return result
	}

	public boolean discardBuildPopUp(){

		boolean result = false
		TestObject verifyDiscardBuildPopUp = findTestObject('Object Repository/OR_BuildUpload/dialogue_buildDiscardConfirmation')
		TestObject btnDiscardBuild = findTestObject('Object Repository/OR_BuildUpload/btn_discardBuild')
		TestObject btnNo = findTestObject('Object Repository/OR_BuildUpload/btn_No')

		if(WebUI.waitForElementPresent(verifyDiscardBuildPopUp, 10)){
			if(WebUI.waitForElementPresent(btnDiscardBuild, 10)){
				if(WebUI.waitForElementPresent(btnNo, 10)){
					result = true
				}else{
					MesmerLogUtils.logInfo("Unable to verify No button build discard Dialogue")
				}
			}else{
				MesmerLogUtils.logInfo("Unable to verify discard button on build discard Dialogue")
			}

		}else{
			MesmerLogUtils.logInfo("Unable to verify build discard Dialogue")
		}
		return result
	}

	public boolean setProjectName(String setProjectName){
		boolean result = false

		TestObject projectNameField = findTestObject('Object Repository/OR_BuildUpload/field_newProjectNameField')

		if(WebUI.waitForElementVisible(projectNameField, 20)==true){


			WebUI.setText(findTestObject('Object Repository/OR_BuildUpload/field_newProjectNameField'),
					setProjectName)

			WebUI.sendKeys(findTestObject('Object Repository/OR_BuildUpload/field_newProjectNameField'),
					Keys.chord(Keys.ENTER))

			MesmerLogUtils.logInfo(WebUI.getText(projectNameField))
			result = true

		}else{
			MesmerLogUtils.logInfo("Could not find project name field")
		}
		return result
	}
	public boolean uploadBuildFile(String buildName){

		boolean result = false
		TestObject btnBrowseToSelect = findTestObject('Object Repository/OR_BuildUpload/btnUploadFile')

		if(Utility.isWindows()){
			String Slash = "\\\\"
			WebUI.uploadFile(btnBrowseToSelect, GlobalVariable.buildPath +Slash+ buildName, FailureHandling.STOP_ON_FAILURE)
			result = true
		}else if(Utility.isMac()){
			String Slash = "/"
			WebUI.uploadFile(btnBrowseToSelect, GlobalVariable.buildPathMac +Slash+ buildName, FailureHandling.STOP_ON_FAILURE)
			result = true
		}
		return result
	}

	public boolean checkBuildUploadStatusAndPerformActions(){
		boolean result = false

		TestObject txtProjectAlreadyExists = findTestObject('Object Repository/OR_BuildUpload/txt_projectAlreadyExists')
		//Check Success message on uploading project
		TestObject successfulBuildUpload = findTestObject('Object Repository/OR_AppMap/verify_buildUploadDialogue')

		if(WebUI.waitForElementPresent(txtProjectAlreadyExists, 10)){
			MesmerLogUtils.logInfo("Project already exists with the same name")

			performActionsIfProjectAlreadyExists()
			result = true

		}else(WebUI.waitForElementPresent(successfulBuildUpload, 10)==true){
				MesmerLogUtils.markPassed("Build uploaded successfully")

				performActionsProjectSuccesfullyUploaded()
				result = true
			}
		return result
	}

	public boolean performActionsIfProjectAlreadyExists(){
		boolean result = false

		TestObject closeWindowBtn = findTestObject('Object Repository/OR_BuildUpload/btn_closeBuild')
		TestObject btnDiscardBuild = findTestObject('Object Repository/OR_BuildUpload/btn_discardBuild')

		if(WebUI.waitForElementPresent(closeWindowBtn, 5)){
			WebUI.click(closeWindowBtn)

			if(WebUI.waitForElementPresent(btnDiscardBuild, 5)){
				WebUI.click(btnDiscardBuild)

				result = true

			}else{
				MesmerLogUtils.logInfo("Unable to click on discard button")
			}
		}
		else{
			MesmerLogUtils.logInfo("Unable to click on close button")
		}
		return result

	}


	public boolean performActionsProjectSuccesfullyUploaded(){
		boolean result = false

		TestObject closeWindowBtn = findTestObject('Object Repository/OR_BuildUpload/btn_closeBuild')

		if(WebUI.waitForElementVisible(closeWindowBtn, 10)==true){
			WebUI.click(closeWindowBtn)
			WebUI.delay(2)
			MesmerLogUtils.markPassed("Build uploaded windows closed")
		}
		else{
			MesmerLogUtils.markFailed("Build uploaded windows is not closed")
		}

	}

	public boolean buildIntegrity(){
		boolean result = false

		TestObject buildIntegrityCheck = findTestObject('Object Repository/OR_BuildUpload/check_buildintegrity')
		TestObject errorIntegrityCheck = findTestObject('Object Repository/OR_BuildUpload/error_integrityCheck')
		TestObject integrityCheckFailedReason = findTestObject('Object Repository/OR_BuildUpload/integrity_CheckFailed')

		if(WebUI.waitForElementVisible(buildIntegrityCheck, 120)==true){
			MesmerLogUtils.markFailed("Build Integrity Check Passed")
			result = true
		}else if (WebUI.waitForElementVisible(errorIntegrityCheck, 30)==true) {

			MesmerLogUtils.logInfo("Build Integrity Check Failed ")


			if (WebUI.waitForElementVisible(integrityCheckFailedReason, 30)==true) {
				MesmerLogUtils.logInfo("Integrity check failed " + ": " + "We analyzed this upload and it doesn’t appear to be the same app as the last build uploaded. Double check the project and try again.")
				result = true
			}else{
				MesmerLogUtils.logInfo("Unable to find the reason for integrity check fail ")
			}
		}
		return result
	}

	public boolean buildSimiliarity(){
		boolean result = false

		TestObject similiarityCheck = findTestObject('Object Repository/OR_BuildUpload/check_buildSimilarity')

		TestObject errorSimiliarityCheck = findTestObject('Object Repository/OR_BuildUpload/error_similarlityCheck')

		TestObject similiarityCheckFailedReason = findTestObject('Object Repository/OR_BuildUpload/similarity_checkFailed')

		if(WebUI.waitForElementVisible(similiarityCheck, 120)==true){
			MesmerLogUtils.logInfo("Build Similarity Check Passed")
			result = true
		}else if (WebUI.waitForElementVisible(errorSimiliarityCheck, 30)==true) {

			MesmerLogUtils.logInfo("Build Similarity Check Failed ")

			if (WebUI.waitForElementVisible(similiarityCheckFailedReason, 30)==true) {
				MesmerLogUtils.logInfo("Similarity check failed " + ": " + "We analyzed this upload and it doesn’t appear to be the same app as the last build uploaded. Double check the project and try again.")
				result = true
			}else{
				MesmerLogUtils.logInfo("Unable to find the reason for certificate check fail ")
			}
		}
		return result
	}

	public boolean buildCompatibility(){
		boolean result = false

		TestObject compatibilityCheck = findTestObject('Object Repository/OR_BuildUpload/check_compatibility')
		TestObject errorCompatibilityCheck = findTestObject('Object Repository/OR_BuildUpload/error_compatibilityCheck')
		TestObject compatibilityCheckFailedReason = findTestObject('Object Repository/OR_BuildUpload/compatibility_CheckFailed')

		if(WebUI.waitForElementVisible(compatibilityCheck, 120)==true){
			MesmerLogUtils.logInfo("Build Compatibility Check Passed")
			result = true
		}else if (WebUI.waitForElementVisible(errorCompatibilityCheck, 30)==true){
			MesmerLogUtils.logInfo("Build Compatibility Check Failed")


			if (WebUI.waitForElementVisible(compatibilityCheckFailedReason, 30)==true) {
				MesmerLogUtils.logInfo("Compatibility check failed " + ": " + "We checked this build out and it wasn’t properly built for the devices provisioned to the account.")
				result = true
			}else{
				MesmerLogUtils.logInfo("Unable to find the reason for compatibility check fail ")
			}
		}
		return result
	}

	public boolean buildCertificate(){
		boolean result = false

		TestObject certificateCheck = findTestObject('Object Repository/OR_BuildUpload/check_certificate')
		TestObject errorCertificateCheck = findTestObject('Object Repository/OR_BuildUpload/error_certificateCheck')
		TestObject certificateCheckFailedReason = findTestObject('Object Repository/OR_BuildUpload/certificate_checkFailed')

		if(WebUI.waitForElementVisible(certificateCheck, 120)==true){
			MesmerLogUtils.logInfo("Build certificate Check Passed")
			result = true
		}else if (WebUI.waitForElementVisible(errorCertificateCheck, 30)==true) {
			MesmerLogUtils.logInfo("Build Certificate Check Failed")

			if (WebUI.waitForElementVisible(certificateCheckFailedReason, 30)==true) {
				MesmerLogUtils.logInfo("Certificate check failed " + ": " + "We check the build and can’t quite get it signed correctly to run on Mesmer. We’re looking into it. Hang tight.")
				result = true
			}else{
				MesmerLogUtils.logInfo("Unable to find the reason for certificate check fail ")
			}
		}
		return result
	}

	public boolean addUser(String username, String password){
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

}