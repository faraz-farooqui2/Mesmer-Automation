import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.util.List
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController
import controllers.ManageTestController
import org.openqa.selenium.JavascriptExecutor;


/*
 * MS-Create a new Test Case-02 | Verify that user should be able to 'Save' or 'Discard' the test case.
 */

try{

	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)

	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()

		// Fetch Device and Perform Discard Test Case Options (Step 1)
		if(fetchDeviceAndStartStream()){
			TestObject recordingText = findTestObject('Object Repository/OR_CreateNewTestCase/titletext_recordingDot')
			if(WebUI.waitForElementPresent(recordingText, 60)){
				if(discardRecording()){

					// Fetch Device and Perform Continue Recording (Step 2)
					if(fetchDeviceAndStartStream()){
						if(WebUI.waitForElementPresent(recordingText, 60)){
							if(recordSteps()){

								if(CreateTestController.getInstance().clickDoneGreenButton()){

									if(CreateTestController.getInstance().saveNewTestCase()){

										if(CreateTestController.getInstance().clickOnReturnToDevice()){

											if(CreateTestController.getInstance().continueRecording()){

												if(performStartFromHere()){

													if(CreateTestController.getInstance().clickDoneGreenButton()){

														if(CreateTestController.getInstance().saveNewTestCase()){


															// Fetch Device and Perform Restart App And Rec New (Step 3)
															if(CreateTestController.getInstance().clickOnReturnToDevice()){

																if(CreateTestController.getInstance().restartAppAndRecordNewTest()){

																	if(CreateTestController.getInstance().clickDoneGreenButton()){

																		if(CreateTestController.getInstance().saveNewTestCase()){


																			// Click on Return to Device (Step 4)
																			if(CreateTestController.getInstance().clickOnReturnToDevice()){

																				WebUI.delay(20)

																				if(CreateTestController.getInstance().resetDeviceAndRecordNewTest()){

																					WebUI.delay(10)

																					if(CreateTestController.getInstance().clickDoneGreenButton()){

																						if(CreateTestController.getInstance().saveNewTestCase()){

																							if(CreateTestController.getInstance().clickOnViewTest()){

																								WebUI.delay(10)

																								if(CreateTestController.getInstance().viewAndEditTestCase()){

																									if(performRedoFromHere()){

																										WebUI.delay(5)

																										// Fetch Device and navigating to screen (Step 5)

																										if(fetchDeviceAndStartStream()){

																											if(WebUI.waitForElementPresent(navigatingToScreen, 120)){

																												if(WebUI.waitForElementNotPresent(navigatingToScreen, 240)){

																													if(CreateTestController.getInstance().clickDoneGreenButton()){

																														if(CreateTestController.getInstance().saveNewTestCase()){

																															// Click on View Test (Step 6)
																															if(CreateTestController.getInstance().clickOnViewTest()){

																																if(CreateTestController.getInstance().clickOnViewTestAndReplay()){

																																	if(CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()){

																																		// Fetch device and release device and create new test case (Step 7)
																																		if(fetchDeviceAndStartStream()){

																																			if(CreateTestController.getInstance().clickDoneGreenButton()){

																																				if(CreateTestController.getInstance().saveNewTestCase()){

																																					if(CreateTestController.getInstance().clickOnReleaseDeviceAndCreateNew()){
																																					}else{
																																						MesmerLogUtils.markFailed("Step-7 : Unable to click on release device and create new test")
																																					}
																																				}else{
																																					MesmerLogUtils.markFailed("Step-7 : Unable to save test case")
																																				}
																																			}else{
																																				MesmerLogUtils.markFailed("Step-7 : Unable to click on done button")
																																			}
																																		}else{
																																			MesmerLogUtils.markFailed("Step-7 : Unable to fetch device and start recording")
																																		}
																																	}else{
																																		MesmerLogUtils.markFailed("Step-6 : Unable to go to create new test case screen")
																																	}
																																}else{
																																	MesmerLogUtils.markFailed("Step-6 : Unable to click on view test and replay")
																																}
																															}else{
																																MesmerLogUtils.markFailed("Step-6 : Unable to click on view test")
																															}
																														}else{
																															MesmerLogUtils.markFailed("Step-5 : Unable to save new test case")
																														}
																													}else{
																														MesmerLogUtils.markFailed("Step-5 : Unable to click on done button")
																													}
																												}else{
																													MesmerLogUtils.markFailed("Step-5 : Still navigating to screen")
																												}
																											}else{
																												MesmerLogUtils.markFailed("Step-5 : Unable to navigating to screen")
																											}

																										}else{
																											MesmerLogUtils.markFailed("Step-5 : Unable to fetch device and start recording")
																										}
																									}else{
																										MesmerLogUtils.markFailed("Step-4 : Unable to perform redo from here")
																									}
																								}else{
																									MesmerLogUtils.markFailed("Step-4 : Unable to click on view and edit test case")
																								}
																							}else{
																								MesmerLogUtils.markFailed("Step-4 : Unable to click on view test")
																							}
																						}else{
																							MesmerLogUtils.markFailed("Step-4 : Unable to click on save test case")
																						}
																					}else{
																						MesmerLogUtils.markFailed("Step-4 : Unable to click on done button")
																					}
																				}else{
																					MesmerLogUtils.markFailed("Step-4 : Unable to click on reset device and record new test")
																				}
																			}else{
																				MesmerLogUtils.markFailed("Step-4 : Unable to click on return to device")
																			}
																		}else{
																			MesmerLogUtils.markFailed("Step-3 : Unable to save new test case")
																		}
																	}else{
																		MesmerLogUtils.markFailed("Step-3 : Unable to click on done green button")
																	}
																}else{
																	MesmerLogUtils.markFailed("Step-3 : Unable to restart app and record new test")
																}
															}else{
																MesmerLogUtils.markFailed("Step-3 : Unable to click on return to device")
															}
														}else{
															MesmerLogUtils.markFailed("Step-2.1 : Unable to save test case")
														}
													}else{
														MesmerLogUtils.markFailed("Step-2.1 : Unable to click on Done Green button")
													}
												}else{
													MesmerLogUtils.markFailed("Step-2 : Unable to perform Start From Here ")
												}
											}else{
												MesmerLogUtils.markFailed("Step-2 : Unable to click on continue recording")
											}
										}else{
											MesmerLogUtils.markFailed("Step-2 : Unable to click on Return to Device")
										}
									}else{
										MesmerLogUtils.markFailed("Step-2 : Unable to save test case")
									}
								}else{
									MesmerLogUtils.markFailed("Step-2 : Unable to click on Done Green Button")
								}
							}else{
								MesmerLogUtils.markFailed("Step-2 : Unable to perform recording steps")
							}
						}else{
							MesmerLogUtils.markFailed("Step-2 : Recording has not been started yet")
						}
					}else{
						MesmerLogUtils.markFailed("Step-2 : Fetch Device and Perform Continue Recording")
					}
				}else{
					MesmerLogUtils.markFailed("Step-1 : Unable to perform discard recording")
				}
			}else{
				MesmerLogUtils.markFailed("Step-1 : Recording has not been started yet")
			}
		}else{
			MesmerLogUtils.markFailed("Step-1 : Unable to fetch device and start recording")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}catch(Exception e){
	e.printStackTrace()
}finally{

	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

public boolean discardRecording(){
	WebUI.delay(10)
	boolean result = false
	if(CreateTestController.getInstance().clickDoneGreenButton()){
		if(CreateTestController.getInstance().clickDiscardButton()){
			if(CreateTestController.getInstance().clickOnNeverMindOnDiscardAlert()){
				if(CreateTestController.getInstance().clickDoneGreenButton()){
					if(CreateTestController.getInstance().clickDiscardButton()){
						if(CreateTestController.getInstance().checkIfNewDiscardAlertAppeared()){
							result = true
						}else{
							MesmerLogUtils.markFailed("Unable to click on discard alert")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to click on discard button")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to click on done button")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to click on never mind button")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to click on discard button")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on done button")
	}
	return result
}


public boolean performRedoFromHere(){

	WebDriver driver = DriverFactory.getWebDriver()
	boolean result = false
	TestObject openTestCaseScreen = findTestObject('Object Repository/OR_UDA/click_ToOpenTestCaseScreen')
	if(WebUI.waitForElementVisible(openTestCaseScreen, 30) ==true){
		WebUI.delay(5)
		WebUI.click(openTestCaseScreen)
		MesmerLogUtils.markPassed("Testcase screen open successfully ")
		WebUI.delay(2)

		TestObject reDoFromHere = findTestObject('Object Repository/OR_ManageTestCases/btn_recordFromHere')

		if(WebUI.waitForElementPresent(reDoFromHere, 60)){
			//WebUI.click(reDoFromHere)

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String redoFromHereXpath = findTestObject('Object Repository/OR_ManageTestCases/btn_recordFromHere').findPropertyValue('xpath').toString()
			WebElement redoFromHereHover = driver.findElement(By.xpath(redoFromHereXpath))


			((JavascriptExecutor) driver).executeScript("arguments[0].click();", redoFromHereHover);
			WebUI.delay(2)
			result = true

		}else{
			MesmerLogUtils.markFailed("Could perform record from here")
		}
	}else{
		MesmerLogUtils.markFailed("Could not edit test case")
	}
	return result
	//	WebDriver driver = DriverFactory.getWebDriver()
	//	Actions builder = new Actions(driver);
	//	String selectScreenViewEditXpath = findTestObject('Object Repository/OR_CreateNewTestCase/xpath_selectScreenViewEdit').findPropertyValue('xpath').toString()
	//	WebElement selectScreenViewEdit = driver.findElement(By.xpath(selectScreenViewEditXpath))
	//	if(selectScreenViewEdit != null){
	//		builder.moveToElement(selectScreenViewEdit).perform();
	//		WebUI.delay(5)
	//
	//		String screenElipsisViewEditXpath = findTestObject('Object Repository/OR_CreateNewTestCase/click_ViewAndEditElipsis').findPropertyValue('xpath').toString()
	//		WebElement screenElipsisViewEdit = driver.findElement(By.xpath(screenElipsisViewEditXpath))
	//		if(screenElipsisViewEdit != null){
	//			screenElipsisViewEdit.click()
	//			if(WebUI.waitForElementPresent(redoFromHere, 20)){
	//				WebUI.click(redoFromHere)


	//
	//			}


}


//public boolean performStartFromHere(){
//	boolean result = false
//	WebUI.delay(10)
//	WebDriver driver = DriverFactory.getWebDriver()
//	Actions builder = new Actions(driver);
//	if(WebUI.waitForElementPresent(showSteps, 20)){
//		WebUI.click(showSteps)
//		String selectScreenXpath = findTestObject('Object Repository/OR_CreateNewTestCase/select_Screen').findPropertyValue('xpath').toString()
//		WebElement selectScreen = driver.findElement(By.xpath(selectScreenXpath))
//		if(selectScreen != null){
//			builder.moveToElement(selectScreen).perform();
//			WebUI.delay(5)
//			String screenElipsisXpath = findTestObject('Object Repository/OR_CreateNewTestCase/click_screenElipsis').findPropertyValue('xpath').toString()
//			WebElement screenElipsis = driver.findElement(By.xpath(screenElipsisXpath))
//			if(screenElipsis != null){
//				screenElipsis.click()
//				if(WebUI.waitForElementPresent(startFromHere, 20)){
//					WebUI.click(startFromHere)
//					result = true
//					WebUI.delay(5)
//
//				}else{
//					MesmerLogUtils.markFailed("Unable to click on start from here button")
//				}
//			}else{
//				MesmerLogUtils.markFailed("Unable to click on elipsis on screen")
//			}
//		}else{
//			MesmerLogUtils.markFailed("Screen Element not found")
//		}
//	}else{
//		MesmerLogUtils.markFailed("Unable to click on show steps")
//	}
//	return result
//}

public boolean performStartFromHere(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	if(WebUI.waitForElementPresent(showSteps, 20)){
		WebUI.click(showSteps)

		TestObject openTestCaseScreen = findTestObject('Object Repository/OR_UDA/click_ToOpenTestCaseScreen')
		if(WebUI.waitForElementVisible(openTestCaseScreen, 30) ==true){
			WebUI.delay(5)
			WebUI.click(openTestCaseScreen)
			MesmerLogUtils.markPassed("Testcase screen open successfully ")
			WebUI.delay(2)


			TestObject startFromHere = findTestObject('Object Repository/OR_CreateNewTestCase/button_startFromHere')

			if(WebUI.waitForElementPresent(startFromHere, 60)){
				//WebUI.click(reDoFromHere)

				JavascriptExecutor js = (JavascriptExecutor) driver;
				String startFromHereXpath = findTestObject('Object Repository/OR_CreateNewTestCase/button_startFromHere').findPropertyValue('xpath').toString()
				WebElement startFromHereHover = driver.findElement(By.xpath(startFromHereXpath))


				((JavascriptExecutor) driver).executeScript("arguments[0].click();", startFromHereHover);
				WebUI.delay(2)
				result = true

			}else{
				MesmerLogUtils.markFailed("Unable to click on start from here button")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to click on elipsis on screen")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on show steps")
	}
	return result
}



public boolean fetchDeviceAndStartStream(){
	boolean result = false
	List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())
	if(virtualDevicesList != null && virtualDevicesList.size() >=1){
		Utility.selectDeviceAndSetParams("" , "" ,Device.toString(), "" , "", "", "")
		if(CreateTestController.getInstance().deviceChecks()){
			result = true
		}else{
			MesmerLogUtils.markFailed("Device checks failed")
		}
	}else{
		MesmerLogUtils.markFailed("No "+ Device.toString() + " device available in the list")
	}
	return result
}

private boolean recordSteps(){
	boolean result = false
	WebUI.setViewPortSize(1920, 925)
	WebUI.delay(2)
	if(Utility.getPlatformName() == "Android") {
		CreateTestController.getInstance().enterFullScreenMode()
		WebUI.delay(10)
		WebUI.clickOffset(divDevice,-115, -15)
		WebUI.delay(10)

		WebUI.clickOffset(divDevice,0 , 50)
		WebUI.delay(10)

		WebUI.clickOffset(divDevice,-100, -10)
		WebUI.delay(10)

		WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
		WebUI.delay(10)

		WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(10)

		WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appUserName))
		WebUI.delay(10)
		WebUI.click(btnReply)
		WebUI.delay(10)
		CreateTestController.getInstance().exitFullScreenMode()
		result = true
	}
	else
	{
		CreateTestController.getInstance().enterFullScreenMode()
		WebUI.delay(10)
		WebUI.clickOffset(divDevice,-100, 25)
		WebUI.delay(10)

		WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
		WebUI.delay(10)

		WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
		WebUI.delay(10)

		WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, appUserName))
		WebUI.delay(10)
		WebUI.click(btnReply)
		WebUI.delay(10)
		CreateTestController.getInstance().exitFullScreenMode()
		result = true
	}
	return result
}