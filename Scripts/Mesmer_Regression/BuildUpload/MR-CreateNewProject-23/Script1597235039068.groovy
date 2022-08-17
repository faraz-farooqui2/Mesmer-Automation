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
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.AppMapController
import controllers.BuildUploadController



//MS-Upload new Build/Project-01 | Verify that user should be able to 'Create a new' Project by uploading a build (iOS/Android).
//ASSUMPTION:: In order to run this test case, project should not be available on the testing server
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebDriver driver = DriverFactory.getWebDriver()
try{

	//Check project/build option
	if(WebUI.waitForElementPresent(buildProjectPopOver, 60) ==true){

		//Click on Project option down arrow button
		//2. user click on projects menu
		WebUI.click(buildProjectPopOver)

		//If Build upload button is displayed then click on Back arrow button so that 'Create New Project' button is displayed
		//3. user is on project menu drop down
		if(WebUI.waitForElementPresent(uploadNewBuildButton, 20)==true){
			//4. user click on back arrow from drop down
			WebUI.click(projectSlideArrow)


			WebUI.delay(2)


			if(WebUI.verifyElementVisible(projectList) == true){
				//5. user click on Create New Project button
				WebUI.delay(2)
				WebUI.click(btnCreateNewProject)

				WebUI.delay(2)
				if(WebUI.verifyElementText(windowCreateNewProject, textVerifyForUploadDialogue)){

					if(WebUI.verifyElementVisible(lblBrowseToSelect) == true){


						//6. user drag a .app, apk,.ipa and .zip file on Doted box
						//7. user Click on Browse File button

						if(Utility.isWindows()){
							String Slash = "\\\\"
							WebUI.uploadFile(btnBrowseToSelect, GlobalVariable.buildPath +Slash+ buildName, FailureHandling.STOP_ON_FAILURE)
						}else if(Utility.isMac()){
							String Slash = "/"
							WebUI.uploadFile(btnBrowseToSelect, GlobalVariable.buildPathMac +Slash+ buildName, FailureHandling.STOP_ON_FAILURE)
						}


						//check Sit Tight text when build upload in progress
//						if(WebUI.waitForElementVisible(sitTightText, 180)==true){
//							MesmerLogUtils.markPassed("Build upload is in progress")
//							if(WebUI.waitForElementNotPresent(sitTightText, 1200)==true){
								//8. build upload processing sequence and check mark
								//Check Integrity after build just uploaded

								if(WebUI.waitForElementPresent(progressBar, 10)){
									if(WebUI.waitForElementNotPresent(progressBar, 120)){



										buildIntegrity()
										//Check Similarity on build upload process
										buildSimiliarity()
										//Check Compatibility on build upload process
										buildCompatibility()
										//Check Certificate on build upload process
										buildCertificateCheck()

										//	checkSuccesful()

										//Next button on successful checks screen
										if(WebUI.waitForElementVisible(nextButton, 20)==true){
											WebUI.click(nextButton)
											WebUI.delay(5)
											
											if(BuildUploadController.getInstance().CreateNewProjectNameDialogue()){
												if(WebUI.waitForElementPresent(btnCancel, 5)){
													WebUI.click(btnCancel)
													if(BuildUploadController.getInstance().discardBuildPopUp()){
														
													}else{
													MesmerLogUtils.markFailed("Could not verify dicard build dialogue on cancel button")
													}
												}else{
												MesmerLogUtils.markFailed("Could not click on Cancel button")
												}
											}else{
												MesmerLogUtils.markFailed("Could not verify create new project name dialogue")
												}
										
//											//check Project Name field screen
//											if(WebUI.waitForElementVisible(projectNameField, 20)==true){
//
//
//												WebUI.setText(findTestObject('Object Repository/OR_BuildUpload/field_newProjectNameField'),
//														setProjectName)
//
//												WebUI.sendKeys(findTestObject('Object Repository/OR_BuildUpload/field_newProjectNameField'),
//														Keys.chord(Keys.ENTER))
//
//												MesmerLogUtils.logInfo(WebUI.getText(projectNameField))
//
//												WebUI.delay(5)
//
//												if(Utility.isProductionProfile()){
//													if(WebUI.waitForElementPresent(toogleSwitch, 20)){
//														WebUI.click(toogleSwitch)
//														WebUI.delay(5)
//													}else{
//														MesmerLogUtils.logInfo("Unable to click on hide project button")
//													}
//												}else{
//													MesmerLogUtils.logInfo("Not a production profile")
//												}
//												//Check Next button on Project Name field screen
//												if(WebUI.waitForElementVisible(nextButton, 20)==true){
//													WebUI.click(nextButton)
//													if(WebUI.waitForElementPresent(txtProjectAlreadyExists, 10)){
//														MesmerLogUtils.logInfo("Project already exists with the same name")
//														if(WebUI.waitForElementPresent(closeWindowBtn, 5)){
//															WebUI.click(closeWindowBtn)
//															if(WebUI.waitForElementPresent(btnDiscardBuild, 5)){
//																WebUI.click(btnDiscardBuild)
//															}else{
//																MesmerLogUtils.logInfo("Unable to click on discard button")
//															}
//														}
//														else{
//															MesmerLogUtils.logInfo("Unable to click on close button")
//														}
//
//														//Check Success message on uploading project
//														TestObject successfulBuildUpload = findTestObject('Object Repository/OR_AppMap/verify_buildUploadDialogue')
//													}else if (WebUI.waitForElementVisible(successfulBuildUpload, 120)==true){
//														MesmerLogUtils.markPassed("Build uploaded successfully")
//
//														if(WebUI.waitForElementVisible(closeWindowBtn, 10)==true){
//															WebUI.click(closeWindowBtn)
//															WebUI.delay(2)
//															MesmerLogUtils.markPassed("Build uploaded windows closed")
//														}
//														else{
//															MesmerLogUtils.markFailed("Build uploaded windows is not closed")
//														}
//													}
//													else{
//														MesmerLogUtils.markFailed("Build is not uploaded")
//													}
//												}
//												else{
//													MesmerLogUtils.markFailed("NEXT button is not visible on Project Name field window")
//												}
//
//											}
//											else{
//												MesmerLogUtils.markFailed("Project Name field is not visible")
//											}
										}
										else{
											MesmerLogUtils.markFailed("NEXT button is not visible on build success checks window")
										}

									}else{
										MesmerLogUtils.markFailed("Build upload progress bar still showing")
									}

								}else{
									MesmerLogUtils.markFailed("No progress bar shown")
								}

//							}
//							else{
//								MesmerLogUtils.markFailed("Build is still not uploaded in 20 mins")
//							}
//						}else{
//							MesmerLogUtils.markFailed("Sit tight msg not appears")
//						}

					}else{
						MesmerLogUtils.markFailed("Label browse to select not found ")
					}
				}
				else{
					MesmerLogUtils.markFailed("User is not on Build Upload Dialogue")
				}
			}

			else{
				MesmerLogUtils.markFailed("Project List and Create New Project button not found")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to click on project slide arrow ")
		}
	}
	else{
		MesmerLogUtils.markFailed("Project drop down not found")
	}

}

catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}


private boolean buildIntegrity(){
	boolean result = false

	if(WebUI.waitForElementVisible(buildIntegrityCheck, 20)==true){
		result = true
	}
	else{
		MesmerLogUtils.markFailed("Build Integrity Check is failed in 2 minutes")
	}
	return result
}

private boolean buildSimiliarity(){
	boolean result = false

	if(WebUI.waitForElementVisible(buildSimiliarityCheck, 20)==true){
		result = true
	}
	else{
		MesmerLogUtils.markFailed("Build Similarity Check is failed in 2 minutes")
	}
	return result
}

private boolean buildCompatibility(){
	boolean result = false

	if(WebUI.waitForElementVisible(compatibilityCheck, 20)==true){
		result = true
	}
	else{
		MesmerLogUtils.markFailed("Build Compatibility Check is failed in 2 minutes")
	}
	return result
}

private boolean buildCertificateCheck(){
	boolean result = false

	if(WebUI.waitForElementVisible(certificateCheck, 20)==true){

		result = true
	}
	else{
		MesmerLogUtils.markFailed("Build certificate Check is failed in 2 minutes")
	}
	return result
}

//private boolean checkSuccesful(){
//	boolean result = false
//	WebDriver driver = DriverFactory.getWebDriver()
//
//	String checksOfSuccessXpath = findTestObject('Object Repository/OR_AppMap/xpath_checksOfSuccess').findPropertyValue('xpath').toString()
//	List<WebElement> checksOfSuccess = driver.findElements(By.xpath(checksOfSuccessXpath))
//	WebUI.delay(10)
//	if(checksOfSuccess.size > 0){
//	System.err.println(checksOfSuccess.size())
//	result = true
//	}else{
//	MesmerLogUtils.markFailed("Check of success size is null")
//	}
//	return result
//}