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


//AppMap-23 - Verify that user should be able to select device, add crawl time and auto crawl should start after clicking Crawl and Finish Button
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

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
			WebUI.click(uploadNewBuildButton)

			WebUI.delay(2)


			if(WebUI.verifyElementText(windowBuildUpload, textVerifyForUploadDialogue) ==true){
				MesmerLogUtils.logInfo("Upload Build windows is displayed correctly")

				if(WebUI.verifyElementVisible(lblBrowseToSelect) == true){

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

							//Check Integrity after build just uploaded
							if(WebUI.waitForElementVisible(buildIntegrityCheck, 180)==true){
								//Check Similarity on build upload process
								if(WebUI.waitForElementVisible(buildSimiliarityCheck, 180)==true){
									//Check Compatibility on build upload process
									if(WebUI.waitForElementVisible(compatibilityCheck, 180)==true){
										//Check Certificate on build upload process
										if(WebUI.waitForElementVisible(certificateCheck, 180)==true){
											MesmerLogUtils.markPassed("All checks are marked successfully")
											String checksOfSuccessXpath = findTestObject('Object Repository/OR_AppMap/xpath_checksOfSuccess').findPropertyValue('xpath').toString()
											List<WebElement> checksOfSuccess = driver.findElements(By.xpath(checksOfSuccessXpath))
											WebUI.delay(3)

											System.err.println(checksOfSuccess.size())

											//Next button on successful checks screen
											if(WebUI.waitForElementVisible(nextButton, 5)==true){
												WebUI.click(nextButton)
												if(AppMapController.getInstance().verifyConfigureCrawlDialogue()){
													if(AppMapController.getInstance().clickNextButton()){
														WebUI.delay(5)
														if(AppMapController.getInstance().verifyDeviceDialogue()){
															if(AppMapController.getInstance().selectDevice()){
																if(AppMapController.getInstance().clickNextButton()){
																	if(AppMapController.getInstance().setHrsMintsForCrawlConfig(hours , minutes)){
																		if(AppMapController.getInstance().clickOkButton()){
																			TestObject startCrawlbtn = findTestObject('Object Repository/OR_AppMap/btn_startCrawl')
																			if(WebUI.waitForElementPresent(startCrawlbtn,10)){
																				WebUI.click(startCrawlbtn)
																				
																				CustomKeywords.'com.mesmer.Utility.goToAppMap'()
								
																				if (WebUI.waitForElementVisible(crawlingText, 150) == true) {
																					KeywordUtil.markPassed('SUCCESS: Crawl is in progress...')
								
																					WebUI.delay(30)
																					CustomKeywords.'com.mesmer.Utility_AppMap.stopCrawlFromButton'()
																				}
																				else{
																					KeywordUtil.markFailed("FAILED: Crawl is not in progress...")
																				}
																			}
																			else{
																				KeywordUtil.logInfo("Yes Crawl button is not displayed")
																			}
																		}else{
																			MesmerLogUtils.logInfo("Could not click on Ok button")
																		}
																	}else{
																		MesmerLogUtils.logInfo("Could not set time")
																	}
																}else{
																	MesmerLogUtils.logInfo("Could not click on Next button")
																}
															}else{
																MesmerLogUtils.logInfo("Could not click on ready or provisioned device")
															}
														}else{
															MesmerLogUtils.logInfo("Could not verify select device dialogue")
														}
													}else{

														MesmerLogUtils.logInfo("Could not click on Next button")
													}
												}else{
													MesmerLogUtils.logInfo("Configure crawl dialogue not open")
												}
												
												
											}else{
													KeywordUtil.markFailed("Could not click on Next button")
												}
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
								KeywordUtil.markFailed("FAILED: Build Integrity Check is failed in 2 minutes")
							}

						}
						else{
							MesmerLogUtils.markFailed("Build is still not uploaded in 20 mins")
						}
					}
					else{
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

}

catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}
