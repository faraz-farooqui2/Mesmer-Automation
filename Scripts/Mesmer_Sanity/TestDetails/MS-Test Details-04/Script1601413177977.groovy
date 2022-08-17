import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import org.openqa.selenium.Keys
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.interactions.Actions
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.TestResultController


//MS-Test Details-04 | Verify that user can Assign 'Assertions' on a screenshot for verification (UDAs)


WebDriver driver = DriverFactory.getWebDriver()
Actions builder = new Actions(driver);
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		String titleStream = findTestObject('Object Repository/OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
		List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

		MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
		if(titleStreamList.size() > 0){

			TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
			TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)

			//1. Click on any test case from test result page.

			if(WebUI.waitForElementPresent(testCaseTitle,30) ==true){
				//WebUI.scrollToElement(openTestCase, 10)
				WebUI.click(testCaseTitle)
				WebUI.delay(5)
				MesmerLogUtils.markPassed("Testcase open successfully ")
				//2. Click on Baseline tab

				String editIconXpath = findTestObject('Object Repository/OR_TestDetails/testCase_editIcon').findPropertyValue('xpath').toString()
				WebElement editIcon = driver.findElement(By.xpath(editIconXpath))


				builder.moveToElement(editIcon).click(editIcon).build().perform();


				//4. Click on Action Panel

				if(WebUI.waitForElementPresent(btnActionPanel, 30) ==true){
					WebUI.delay(5)
					WebUI.click(btnActionPanel)
					WebUI.delay(2)
					MesmerLogUtils.markPassed("Clicked on action panel button")

					//3. Click on a screenshot where assertion is required for verification
					if(WebUI.waitForElementVisible(openTestCaseScreen, 30) ==true){
						WebUI.delay(5)
						WebUI.click(openTestCaseScreen)
						MesmerLogUtils.markPassed("Testcase screen open successfully ")
						WebUI.delay(5)

						if(WebUI.waitForElementPresent(btnPlusSign, 30) ==true){
							WebUI.click(btnPlusSign)

							if(WebUI.waitForElementPresent(btnAssertion, 30) ==true){
								WebUI.delay(5)
								WebUI.click(btnAssertion)
								WebUI.delay(2)
								MesmerLogUtils.markPassed("Clicked on assertion button")

								//						if(WebUI.waitForElementPresent(loadingDataFromMDOM, 10) ==true){
								//							if(WebUI.waitForElementPresent(screenLoaded, 10) ==true){
								if(WebUI.waitForElementNotPresent(elementsFetchFailed, 20) ==true){
									MesmerLogUtils.markPassed("Fetched elements")

									if(Utility.getPlatformName() == "Android") {
										String matched = false
										if(WebUI.waitForElementPresent(screenElement, 20)){
											WebUI.click(screenElement)
											MesmerLogUtils.markPassed("Screen Element Selected ")
											matched = true
											if(applyUDAObjectLevel()){
												MesmerLogUtils.markPassed("Object level UDA applied successfully")
											}else{
												MesmerLogUtils.markFailed("Object level UDA not applied successfully")
											}
										}else{
											MesmerLogUtils.markFailed("[DATA ISSUE] Screen elements not matched")
										}
									}else if (Utility.getPlatformName() == "iOS") {
										if(WebUI.waitForElementPresent(screenElement2, 20)){
											WebUI.click(screenElement2)
											MesmerLogUtils.markPassed("Screen Element Selected ")
											matched = true
											if(applyUDAObjectLevel()){
												MesmerLogUtils.markPassed("Object level UDA applied successfully")
											}else{
												MesmerLogUtils.markFailed("Object level UDA not applied successfully")
											}
										}else{
											MesmerLogUtils.markFailed("[DATA ISSUE] Screen elements not matched")
										}
									}
								}else{
									MesmerLogUtils.markFailed("Failed to fetch elements")
								}
								//							}else{
								//								MesmerLogUtils.markFailed("Screen data not loaded")
								//							}
								//						}else{
								//							MesmerLogUtils.markFailed("Loading screen data failed")
								//						}
							}else{
								MesmerLogUtils.markFailed("Could not click on assertion button")
							}
						}else{
							MesmerLogUtils.markFailed("Could not click on plus button")
						}
					}else{
						MesmerLogUtils.markFailed("Testcase screen not open")
					}
				}else{
					MesmerLogUtils.markFailed("Could not click on action panel")
				}

			}else{
				MesmerLogUtils.markFailed("Testcase not open")
			}
		}else{
			MesmerLogUtils.markFailed("No test case found")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	//	TestResultController.getInstance().checkIfAssertionDialogAppears()
	//	WebUI.refresh()
	WebUI.delay(2)
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()

}

private boolean applyUDAObjectLevel(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	Actions builder = new Actions(driver);
	if(WebUI.waitForElementPresent(assertionType, 20)==true){
		WebUI.click(assertionType)
		if(WebUI.waitForElementPresent(udaTextFromObject, 20)==true){
			WebUI.click(udaTextFromObject)
			if(WebUI.waitForElementPresent(selectType, 20)==true){
				WebUI.click(selectType)
				if(WebUI.waitForElementPresent(textEqual, 20)==true){
					WebUI.click(textEqual)
					if(WebUI.waitForElementPresent(inputFieldAssertion, 20)==true){
						String inputFieldValueXpath = findTestObject('Object Repository/OR_UDA/input_valueAssertion').findPropertyValue('xpath').toString()
						WebElement inputFieldValue = driver.findElement(By.xpath(inputFieldValueXpath))
						inputFieldValue.click()
						WebUI.delay(2)
						inputFieldValue.sendKeys(inputpageUdaValue)
						inputFieldValue.sendKeys(Keys.ENTER)
						MesmerLogUtils.markPassed("Text Added in Value field ")
						if(WebUI.waitForElementPresent(btnOkay, 20)==true){
							WebUI.click(btnOkay)
							if(WebUI.waitForElementPresent(actionType, 20)==true){
								WebUI.click(actionType)
								if(WebUI.waitForElementPresent(actionTypeContinueTest, 20)==true){
									WebUI.click(actionTypeContinueTest)
									if(WebUI.waitForElementPresent(inputPassMessage, 20)==true){
										String inputFieldActionXpath = findTestObject('Object Repository/OR_UDA/input_passMessage').findPropertyValue('xpath').toString()
										WebElement inputFieldAction = driver.findElement(By.xpath(inputFieldActionXpath))
										inputFieldAction.click()
										WebUI.delay(2)
										inputFieldAction.sendKeys(inputpageUdaValue)
										inputFieldAction.sendKeys(Keys.ENTER)
										MesmerLogUtils.markPassed("Text Added in Message field ")
										if(WebUI.waitForElementPresent(actionType, 20)==true){
											WebUI.click(actionType)
											WebUI.delay(1)
											if(WebUI.waitForElementPresent(actionTypeDefectStopTest, 20)==true){
												WebUI.click(actionTypeDefectStopTest)
												WebUI.delay(1)
												if(WebUI.waitForElementPresent(inputDefectMessage, 20)==true){
													String inputFieldDefectXpath = findTestObject('Object Repository/OR_UDA/input_defectmessage').findPropertyValue('xpath').toString()
													WebElement inputFieldDefect = driver.findElement(By.xpath(inputFieldDefectXpath))
													inputFieldDefect.click()
													WebUI.delay(1)
													inputFieldDefect.sendKeys(inputpageUdaValue)
													inputFieldDefect.sendKeys(Keys.ENTER)
													WebUI.delay(1)
													MesmerLogUtils.markPassed("Text Added in Message field ")
													if(WebUI.waitForElementPresent(applyAssertion, 20)==true){
														WebUI.click(applyAssertion)
														MesmerLogUtils.markPassed("Clicked on apply assertion")
														WebUI.delay(1)
														if(WebUI.waitForElementPresent(doneAssertion, 20)==true){
															WebUI.click(doneAssertion)
															MesmerLogUtils.markPassed("Clicked on done assertion")
															if(WebUI.waitForElementPresent(viewTest, 20)==true){
																WebUI.click(viewTest)
																MesmerLogUtils.markPassed("Clicked on view test")
																result = true
															}else{
																MesmerLogUtils.markFailed("Could not click on view test")
															}
														}else{
															MesmerLogUtils.markFailed("Could not click on done assertion")
														}
													}else{
														MesmerLogUtils.markFailed("Could not click on apply assertion")
													}
												}else{
													MesmerLogUtils.markFailed("Could not click on inputDefectMessage")
												}
											}else{
												MesmerLogUtils.markFailed("Could not click on actionTypeDefectStopTest")
											}
										}else{
											MesmerLogUtils.markFailed("Could not click on input action type message")
										}
									}else{
										MesmerLogUtils.markFailed("Could not click on input action type message")
									}
								}else{
									MesmerLogUtils.markFailed("Could not click on action type continue test")
								}
							}else{
								MesmerLogUtils.markFailed("Could not click on action type")
							}
						}else{
							MesmerLogUtils.markFailed("Could not click on okay button")
						}
					}else{
						MesmerLogUtils.markFailed("Could not click on text equal")
					}
				}else{
					MesmerLogUtils.markFailed("Could not click on text equal")
				}
			}else{
				MesmerLogUtils.markFailed("Could not click on select screen type")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on Search for Text")
		}
	}else{
		MesmerLogUtils.markFailed("Could not click on Assertion Type")
	}
	return result
}
