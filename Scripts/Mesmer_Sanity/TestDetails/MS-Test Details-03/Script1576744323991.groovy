import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.ReplayController

//MS-Test Details-03 | Verify that if a test case is replayed there should be a 'Baseline' and Replayed tab mentioning the specs of device on it.


try{

	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		WebDriver driver = DriverFactory.getWebDriver()
		//	String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
		//	List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))
		//
		//	MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
		//	if(titleStreamList.size() > 1){
		//
		//		TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
		//		TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)
		//		//1. Click on any test case from test result page.
		//		if(WebUI.waitForElementPresent(testCaseTitle, 20)== true){
		//			//WebUI.scrollToElement(openTestCase, 10)
		//			WebUI.click(testCaseTitle)
		//			MesmerLogUtils.markPassed("Test case opens")


		TestObject passedTestCaseLabel = findTestObject('Object Repository/OR_TestResult/testCase_OptionPassed')
		TestObject failedTestCaseLabel = findTestObject('Object Repository/OR_TestResult/testCase_OptionFailed')
		TestObject brokenTestCaseLabel = findTestObject('Object Repository/OR_TestResult/testCase_OptionBroken')

		String lblPassed = WebUI.getText(passedTestCaseLabel)
		String lblFailed = WebUI.getText(failedTestCaseLabel)
		String lblBroken = WebUI.getText(brokenTestCaseLabel)

		int passedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblPassed)
		int failedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblFailed)
		int brokenTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblBroken)

		if(passedTestCaseCount > 0){
			WebUI.click(passedTestCaseLabel)
			WebUI.delay(2)
			if(openTestCase()){
				MesmerLogUtils.logInfo("Test case opened. Now performing actions")
				if(performActions()){
					MesmerLogUtils.markPassed("Actions performed Successfully")
					if(checkExecution()){
						MesmerLogUtils.markPassed("Test execution completed")
					}else{
						MesmerLogUtils.markFailed("Test execution not completed")
					}
				}else{
					MesmerLogUtils.markFailed("Actions not performed")
				}
			}else{
				MesmerLogUtils.markFailed("Test case not opened")
			}
		}
		else if(failedTestCaseCount > 0){
			WebUI.click(failedTestCaseLabel)
			WebUI.delay(2)
			if(openTestCase()){
				MesmerLogUtils.logInfo("Test case opened. Now performing actions")
				if(performActions()){
					MesmerLogUtils.markPassed("Actions performed Successfully")
					if(checkExecution()){
						MesmerLogUtils.markPassed("Test execution completed")
					}else{
						MesmerLogUtils.markFailed("Test execution not completed")
					}
				}else{
					MesmerLogUtils.markFailed("Actions not performed")
				}
			}else{
				MesmerLogUtils.markFailed("Test case not opened")
			}

		}
		else if(brokenTestCaseCount > 0){
			WebUI.click(brokenTestCaseLabel)
			WebUI.delay(2)
			if(openTestCase()){
				MesmerLogUtils.logInfo("Test case opened. Now performing actions")
				if(performActions()){
					MesmerLogUtils.markPassed("Actions performed Successfully")
					if(checkExecution()){
						MesmerLogUtils.markPassed("Test execution completed")
					}else{
						MesmerLogUtils.markFailed("Test execution not completed")
					}
				}else{
					MesmerLogUtils.markFailed("Actions not performed")
				}
			}else{
				MesmerLogUtils.markFailed("Test case not opened")
			}
		}
		else{
			MesmerLogUtils.markFailed("[DATA ISSUE] No test cases in Passed , Failed , Broken")
		}


		//		}else{
		//			MesmerLogUtils.markFailed("Test Case Not Open")
		//		}
		//	}else{
		//		MesmerLogUtils.markFailed("No test case found in this project")
		//	}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.refresh()
}

private boolean openTestCase(){
	WebDriver driver = DriverFactory.getWebDriver()
	boolean result = false
	String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
	List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

	MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
	if(titleStreamList.size() > 0){
		titleStreamList.get(0).click()
		WebUI.delay(3)
		result = true
		MesmerLogUtils.markPassed("Clicked on Test Case")
	}else{
		MesmerLogUtils.markFailed("Test case not opens")
	}
	return result
}

private boolean performActions(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	//2. Click on 'Rerun' button appearing in the top right corner
	if(WebUI.waitForElementPresent(reRun , 40)==true){
		WebUI.click(reRun)
		MesmerLogUtils.markPassed("Click On Re-run")

		//3. Click/select a device that is in Provisioned/ Ready state
		if(ReplayController.getInstance().selectDevice()){
			MesmerLogUtils.markPassed("Clicked on a device")

			//		if(WebUI.waitForElementPresent(tickIcon , 40) == true){
			//			WebUI.click(tickIcon)
			//			MesmerLogUtils.markPassed("Device Selected")

			//4. Click on Run button
			if(WebUI.waitForElementPresent(btnRun , 40) == true){
				WebUI.click(btnRun)
				MesmerLogUtils.markPassed("Click on Run Button")

				//5. Click on Yes option
				if(WebUI.waitForElementPresent(btnYes , 40) == true){
					WebUI.delay(2)
					WebUI.click(btnYes)
					//6. See the Device details on baseline and replayed test case tabs.
					if(WebUI.waitForElementPresent(queueMsg , 40) == true){
						MesmerLogUtils.markPassed("Test Case Queued")
						//								WebUI.refresh()
						//								if(WebUI.waitForElementPresent(queueNumber , 40) == true){
						//									MesmerLogUtils.markPassed("This test is about to run! It's number xx in the queue.")
						//									if(WebUI.waitForElementPresent(cancelRun , 40) == true){
						//										MesmerLogUtils.markPassed("Cancel Run Button in Footer.")
						if(WebUI.waitForElementPresent(inProgressBar , 120) == true){
							MesmerLogUtils.markPassed("In-Progress Bar")

							if(WebUI.waitForElementPresent(inprogressLoader, 120)==true){
								MesmerLogUtils.markPassed("In-progress Loader")



								String titleOverlayXpath = findTestObject('Object Repository/OR_TestDetails/title_overlay').findPropertyValue('xpath').toString()
								List<WebElement> titleOverlay = driver.findElements(By.xpath(titleOverlayXpath))

								TestObject titleOverlayObj = findTestObject('Object Repository/OR_TestDetails/title_overlay')

								if(WebUI.waitForElementPresent(titleOverlayObj, 120)==true){

									TestObject titleOverlayMoveObj = findTestObject('Object Repository/OR_TestDetails/title_overlay2')

									String titleOverlayMoveXpath = findTestObject('Object Repository/OR_TestDetails/title_overlay2').findPropertyValue('xpath').toString()
									List<WebElement> titleOverlayMove = driver.findElements(By.xpath(titleOverlayMoveXpath))

									if(WebUI.waitForElementPresent(titleOverlayMoveObj, 120)==true){

										for (WebElement webElement : titleOverlayMove) {

											MesmerLogUtils.markPassed("Title overlay move to next screen")
										}

										//												if(WebUI.waitForElementPresent(titleOverlay, 120)==true){
										//													MesmerLogUtils.markPassed("Overlay On the Tile 1 ")


										if(WebUI.waitForElementPresent(deviceName, 40)==true){
											MesmerLogUtils.markPassed("Device Name found in Baseline")

											if(WebUI.waitForElementPresent(deviceInfo, 40)==true){
												MesmerLogUtils.markPassed("Device resolution found in Baseline")
												if(WebUI.waitForElementPresent(btnInfo, 40)==true){
													WebUI.click(btnInfo)
													MesmerLogUtils.markPassed("Clicked on Info button")
													if(WebUI.waitForElementPresent(date, 40)==true){
														MesmerLogUtils.markPassed("Date Text Found in Info section")
														if(WebUI.waitForElementPresent(btnInfo, 40)==true){
															MesmerLogUtils.markPassed("Clicked on Info button")
															WebUI.click(btnInfo)
															if(WebUI.waitForElementPresent(deviceNameReplay, 40)==true){
																MesmerLogUtils.markPassed("Replay Device Name")

																if(WebUI.waitForElementPresent(deviceInfoReplay, 40)==true){
																	MesmerLogUtils.markPassed("Replay Device Name")

																}else{
																	MesmerLogUtils.markFailed("No Replay Device Name")
																}
																if(WebUI.waitForElementPresent(dateReplay, 40)==true){
																	MesmerLogUtils.markPassed("Date Text Found in Replay")
																	result = true
																}else{
																	MesmerLogUtils.markFailed("No Date Text Found in Replay")
																}

															}else{
																MesmerLogUtils.markFailed("No Replay Device Name")
															}
														}else{
															MesmerLogUtils.markFailed("Could not close info bitton")
														}

													}else{
														MesmerLogUtils.markFailed("No Date Text Found in Baseline")
													}
												}else{
													MesmerLogUtils.markFailed("Could not open info bitton")
												}
											}else{
												MesmerLogUtils.markFailed("No Device Info Text Found in Baseline")
											}

										}else{
											MesmerLogUtils.markFailed("No Baseline Title  ")
										}
									}else{
										MesmerLogUtils.markFailed("No Overlay on the Tile Screen 3")
									}
								}else{
									MesmerLogUtils.markFailed("No Overlay on the Tile Screen")
								}

							}else{
								MesmerLogUtils.markFailed("No In-progress Loader")
							}
						}else{
							MesmerLogUtils.markFailed("No In-progress Bar")
						}

						//									}else{
						//										MesmerLogUtils.markFailed("No Cancel Run Button in Footer")
						//									}
						//								}else{
						//									MesmerLogUtils.markFailed("No Footer For Queue")
						//								}
					}else{
						MesmerLogUtils.markFailed("No Test Case in Queue")
					}

				}else{
					MesmerLogUtils.markFailed("Unable to Click on Yes Button")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to Click on Run Button")
			}
		}
		else{
			MesmerLogUtils.markFailed("Could not select a device")
		}

	}
	else{
		MesmerLogUtils.markFailed("Unable to Click on Re-run")
	}
	return result
}

private boolean checkExecution(){
	boolean result = false
	TestObject executing = findTestObject('Object Repository/OR_Replay/replay_executing')
	if(WebUI.waitForElementPresent(executing, 30)){
		MesmerLogUtils.markPassed("Test Case Executing")
		if(WebUI.waitForElementNotPresent(executing, 1200)){
			MesmerLogUtils.markPassed("Test Case Execution Completed")
			result = true
		}else{
			MesmerLogUtils.markFailed("Test case Execution still in-progress")
		}
	}else{
		MesmerLogUtils.markFailed("Test case not executed")
	}
	return result
}