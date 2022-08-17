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

//MS-Upload new Build/Project-03 | Verify that user should be able to upload a large size build of upto 500MB (iOS/Andoird)
//ASSUMPTION:: In order to run this test case, project should not be available on the testing server

Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebDriver driver = DriverFactory.getWebDriver()
try{

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
		}

		WebUI.delay(2)


		if(WebUI.verifyElementVisible(projectList) == true){
			//5. user click on Create New Project button
			WebUI.click(btnCreateNewProject)
			WebUI.delay(2)

			if(WebUI.verifyElementText(windowCreateNewProject, textVerifyForUploadDialogue)){

				if(WebUI.verifyElementVisible(lblBrowseToSelect) == true){

					// Change the file path with absolute path.
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
					if(WebUI.waitForElementVisible(sitTightText, 180)==true){
						MesmerLogUtils.markPassed("Build upload is in progress")
						if(WebUI.waitForElementNotPresent(sitTightText, 1200)==true){

							//8. build upload processing sequence and check mark
							//Check Integrity after build just uploaded
							if(WebUI.waitForElementVisible(buildIntegrityCheck, 120)==true){
								//Check Similarity on build upload process
								if(WebUI.waitForElementVisible(buildSimiliarityCheck, 120)==true){
									//Check Compatibility on build upload process
									if(WebUI.waitForElementVisible(compatibilityCheck, 120)==true){
										//Check Certificate on build upload process
										if(WebUI.waitForElementVisible(certificateCheck, 120)==true){
											MesmerLogUtils.markPassed("All checks are marked successfully")
											String checksOfSuccessXpath = findTestObject('Object Repository/OR_AppMap/xpath_checksOfSuccess').findPropertyValue('xpath').toString()
											List<WebElement> checksOfSuccess = driver.findElements(By.xpath(checksOfSuccessXpath))
											WebUI.delay(10)

											System.err.println(checksOfSuccess.size())

											//Next button on successful checks screen
											if(WebUI.waitForElementVisible(nextButton, 20)==true){
												WebUI.click(nextButton)

												//check Project Name field screen
												if(WebUI.waitForElementVisible(projectNameField, 20)==true){

													WebUI.setText(findTestObject('Object Repository/OR_BuildUpload/field_newProjectNameField'),
															setProjectName)

													WebUI.sendKeys(findTestObject('Object Repository/OR_BuildUpload/field_newProjectNameField'),
															Keys.chord(Keys.ENTER))
													MesmerLogUtils.logInfo(WebUI.getText(projectNameField))

													WebUI.delay(5)

													if(Utility.isProductionProfile() || Utility.isPocProfile()){
														if(WebUI.waitForElementPresent(toogleSwitch, 20)){
															WebUI.click(toogleSwitch)
															WebUI.delay(5)
														}else{
															MesmerLogUtils.markFailed("Unable to click on hide project button")
														}
													}else{
														MesmerLogUtils.logInfo("Not a production or poc profile")
													}

													//Check Next button on Project Name field screen
													if(WebUI.waitForElementVisible(nextButton, 20)==true){
														WebUI.click(nextButton)
														if(WebUI.waitForElementPresent(txtProjectAlreadyExists, 10)){
															MesmerLogUtils.logInfo("Project already exists with the same name")
															if(WebUI.waitForElementPresent(closeWindowBtn, 5)){
																WebUI.click(closeWindowBtn)
																if(WebUI.waitForElementPresent(btnDiscardBuild, 5)){
																	WebUI.click(btnDiscardBuild)
																	MesmerLogUtils.logInfo("Clicked on discard build")
																	WebUI.delay(2)
																	if(CustomKeywords.'com.mesmer.Utility.goToAppMap'()){
																		WebUI.delay(5)
																		if(AppMapController.getInstance().clickRunCrawl(hours , minutes , Device)){
																			MesmerLogUtils.logInfo("Crawl configure and start successfully")
																		}else{
																			MesmerLogUtils.markFailed("Could not click and configure crawl")
																		}
																	}
																	else{
																		MesmerLogUtils.markFailed("Could not navigate to app map or could not verify app map page")
																	}
																}else{
																	MesmerLogUtils.markFailed("Unable to click on discard button")
																}
															}
															else{
																MesmerLogUtils.markFailed("Unable to click on close button")
															}
														}else{
															if(buildSuccessfulUploaded(hours , minutes)){
															}else{
																MesmerLogUtils.markFailed("Build not uploaded successfully")
															}
														}
													}
													else{
														MesmerLogUtils.markFailed("NEXT button is not visible on Project Name field window")
													}

												}
												else{
													MesmerLogUtils.markFailed("Project Name field is not visible")
												}
											}
											else{
												MesmerLogUtils.markFailed("NEXT button is not visible on build success checks window")
											}


										}
										else{
											MesmerLogUtils.markFailed("Certificate check is failed")
										}
									}else{
										MesmerLogUtils.markFailed("Compatibility check is failed")
									}

								}
								else{
									MesmerLogUtils.markFailed("Similarity check is failed")
								}

							}
							else{
								MesmerLogUtils.markFailed("Build Integrity Check is failed in 2 minutes")
							}
						}
						else{
							MesmerLogUtils.markFailed("Build is still not uploaded in 20 mins")
						}
					}else{
						MesmerLogUtils.markFailed("Sit tight msg not appears")
					}

				}else{
					MesmerLogUtils.markFailed("Label browse to select not appears")
				}
			}
			else{
				MesmerLogUtils.markFailed("User is not on Build Upload Dialogue")
			}
		}

		else{
			MesmerLogUtils.markFailed("Project List and Create New Project button not found")
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


private boolean buildSuccessfulUploaded(String hours , String minutes){
	boolean result = false

	if(AppMapController.getInstance().verifyConfigureCrawlDialogue()){
		if(AppMapController.getInstance().clickNextButton()){
			if(AppMapController.getInstance().verifyDeviceDialogue()){
				if(AppMapController.getInstance().selectDevice()){
					if(AppMapController.getInstance().clickNextButton()){
						if(AppMapController.getInstance().setHrsMintsForCrawlConfig(hours , minutes)){
							if(AppMapController.getInstance().clickOkButton()){
								TestObject startCrawlbtn = findTestObject('Object Repository/OR_AppMap/btn_startCrawl')
								if(WebUI.waitForElementPresent(startCrawlbtn,10)){
									WebUI.click(startCrawlbtn)

									if(CustomKeywords.'com.mesmer.Utility.goToAppMap'()){

										if (WebUI.waitForElementVisible(crawlingText, 150) == true) {
											MesmerLogUtils.markPassed('SUCCESS: Crawl is in progress...')

											WebUI.delay(30)
											CustomKeywords.'com.mesmer.Utility_AppMap.stopCrawlFromButton'()
											result = true
										}
										else{
											MesmerLogUtils.markFailed("FAILED: Crawl is not in progress...")
										}
									}
									else{
										MesmerLogUtils.markFailed("Could not navigate to app map or could not verify app map page")
									}
								}
								else{
									MesmerLogUtils.markFailed("Yes Crawl button is not displayed")
								}
							}else{
								MesmerLogUtils.markFailed("Could not click on Ok button")
							}
						}else{
							MesmerLogUtils.markFailed("Could not set time")
						}
					}else{
						MesmerLogUtils.markFailed("Could not click on Next button")
					}
				}else{
					MesmerLogUtils.markFailed("Could not click on ready or provisioned device")
				}
			}else{
				MesmerLogUtils.markFailed("Could not verify select device dialogue")
			}

		}else{

			MesmerLogUtils.markFailed("Could not click on Next button")
		}
	}else{
		MesmerLogUtils.markFailed("Configure crawl dialogue not open")
	}
	return result
}