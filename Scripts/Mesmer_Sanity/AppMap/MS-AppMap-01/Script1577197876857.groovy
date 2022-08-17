import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import internal.GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.mesmer.MesmerLogUtils
import controllers.AppMapController



//MS-AppMap-01 | Verify that when a build is uploaded a walk will trigger on a compatible device (.apk)(emulator/Physical).

WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		//Check project/build option
		if(WebUI.waitForElementVisible(buildProjectPopOver, 20) ==true){

			//Click on Project option down arrow button
			//2. user click on projects menu
			WebUI.click(buildProjectPopOver)

			//If Build upload button is displayed then click on Back arrow button so that 'Create New Project' button is displayed
			//3. user is on project menu drop down
			if(WebUI.waitForElementVisible(uploadNewBuildButton, 20)==true){
				//4. user click on back arrow from drop down
				WebUI.click(uploadNewBuildButton)

				WebUI.delay(2)

				if(WebUI.verifyElementText(windowBuildUpload, textVerifyForUploadDialogue) ==true){
					MesmerLogUtils.logInfo("Upload Build windows is displayed correctly")

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
							KeywordUtil.markPassed("PASSED: Build upload is in progress")
							if(WebUI.waitForElementNotPresent(sitTightText, 1200)==true){

								//8. build upload processing sequence and check mark
								//Check Integrity after build just uploaded
								if(WebUI.waitForElementVisible(buildIntegrityCheck, 240)==true){
									//Check Similarity on build upload process
									if(WebUI.waitForElementVisible(buildSimiliarityCheck, 240)==true){
										//Check Compatibility on build upload process
										if(WebUI.waitForElementVisible(compatibilityCheck, 240)==true){
											//Check Certificate on build upload process
											if(WebUI.waitForElementVisible(certificateCheck, 240)==true){
												KeywordUtil.markPassed("PASSED: All checks are marked successfully")
												//											String checksOfSuccessXpath = findTestObject('Object Repository/OR_AppMap/xpath_checkSuccess').findPropertyValue('xpath').toString()
												//											List<WebElement> checksOfSuccess = driver.findElements(By.xpath(checksOfSuccessXpath))
												//											WebUI.delay(3)
												//
												//											MesmerLogUtils.logInfo(checksOfSuccess.size())

												WebUI.delay(2)
//												if(addUser(userEmail, userPassword)){
//													KeywordUtil.markPassed("User email and password entered")
//													WebUI.delay(2)
													//Next button on successful checks screen
													if(WebUI.waitForElementVisible(nextButton, 5)==true){
														WebUI.click(nextButton)

														if(AppMapController.getInstance().verifyConfigureCrawlDialogue()){
															if(AppMapController.getInstance().clickNextButton()){
																WebUI.delay(5)
																if(AppMapController.getInstance().verifyDeviceDialogue()){
																	//		if(AppMapController.getInstance().selectDevice()){
																	List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())
																	if(virtualDevicesList != null && virtualDevicesList.size() >=1){
																		Utility.selectDeviceAndSetDeviceParam(Device.toString())
																		if(AppMapController.getInstance().clickNextButton()){
																			if(AppMapController.getInstance().setHrsMintsForCrawlConfig(hours , minutes)){
																				if(AppMapController.getInstance().clickOkButton()){
																					TestObject startCrawlbtn = findTestObject('Object Repository/OR_AppMap/btn_startCrawl')
																					if(WebUI.waitForElementPresent(startCrawlbtn,10)){
																						WebUI.click(startCrawlbtn)

																						if(CustomKeywords.'com.mesmer.Utility.goToAppMap'()){

																							if(AppMapController.getInstance().checkCrawlStarted()){

																								if (WebUI.waitForElementPresent(crawlingText, 240) == true) {
																									KeywordUtil.markPassed('SUCCESS: Crawl started')

																									if (WebUI.waitForElementNotPresent(crawlingText, 660) == true) {
																										KeywordUtil.markPassed('Crawl stopped')
																									}
																									else{
																										KeywordUtil.logInfo("WARNING: Crawl runs more than its specified time  " + " Hours :  " + hours  + " Minutes : " +  minutes + "")
																										if(CustomKeywords.'com.mesmer.Utility_AppMap.stopCrawlFromButton'()){
																											KeywordUtil.markPassed('SUCCESS: Stopping Crawl')
																										}else{
																											KeywordUtil.logInfo('WARNING: Could not stop the crawl')
																										}
																									}
																								}
																								else{
																									KeywordUtil.markFailed("FAILED: Crawl not started in 4 mins")
																								}
																							}else{
																								MesmerLogUtils.markFailed("Device checks failed")
																							}
																						}else{
																							MesmerLogUtils.markFailed("Device checks failed")
																						}
																					}
																					else{
																						KeywordUtil.markFailed("Yes Crawl button is not displayed")
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
																		MesmerLogUtils.markFailed("No devices available")
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

													}
													else{
														KeywordUtil.markFailed("FAILED: NEXT button is not visible on build success checks window")
													}
//												}
//												else{
//													KeywordUtil.markFailed("FAILED: Username and password not entered")
//												}
											}
											else{
												KeywordUtil.markFailed("FAILED: Certificate check is failed")
											}
										}else{
											KeywordUtil.markFailed("FAILED: Compatibility check is failed")
										}

									}
									else{
										KeywordUtil.markFailed("FAILED: Similarity check is failed")
									}

								}
								else{
									KeywordUtil.markFailed("FAILED: Build Integrity Check is failed")
								}
							}
							else{
								KeywordUtil.markFailed("FAILED: Build is still not uploaded in 20 mins")
							}
						}else{
							KeywordUtil.markFailed("FAILED: Sit tight msg not appears")
						}
					}
					else{

						KeywordUtil.markFailed("Browser button is not displayed")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: User is not on Build Upload Dialogue")
				}
			}else{
				KeywordUtil.markFailed("FAILED: Click on build upload dialogue")
			}
		}
		else{
			KeywordUtil.markFailed("FAILED: Project drop down not found")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}

catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}

private boolean addUser(String username, String password){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	TestObject alreadyUserExists = findTestObject('Object Repository/OR_BuildUpload/userAlreadyExists')
	TestObject alreadyPasswordExists = findTestObject('Object Repository/OR_BuildUpload/passwordAlreadyExists')
	if(WebUI.waitForElementVisible(addUserBtn, 5)==true){
		WebUI.click(addUserBtn)
		WebUI.delay(2)
		if(WebUI.waitForElementVisible(usernameField, 5)==true && WebUI.waitForElementVisible(passwordField, 5)==true){

			WebUI.sendKeys(usernameField, username)
			WebUI.delay(2)
			WebUI.sendKeys(passwordField, password)
			WebUI.delay(2)
			result =true
		}
		else{
			MesmerLogUtils.markFailed("FAILED: Username and Password field is not visible")

		}
	}
	else if(WebUI.waitForElementPresent(alreadyUserExists, 5) == true &&  WebUI.waitForElementPresent(alreadyPasswordExists, 5)== true ){
		String user = WebUI.getText(alreadyUserExists)
		String pass = WebUI.getText(alreadyPasswordExists)

		MesmerLogUtils.logInfo("Info: User "  +  user  + "and "  + "Password  " + pass + "  already exists" )
		result = true
	}
	return result
}