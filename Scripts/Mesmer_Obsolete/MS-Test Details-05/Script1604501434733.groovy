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


//MS-Test Details-05 | Verify that user can Assign conditions/page assertions on a test case. (Conditionals)

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
Actions builder = new Actions(driver);
try{

	String titleStream = findTestObject('Object Repository/OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
	List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

	MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
	if(titleStreamList.size() > 1){

		TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
		TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)

		//1. Click on any test case from test result page.

		if(WebUI.waitForElementPresent(testCaseTitle,30) ==true){
			//WebUI.scrollToElement(openTestCase, 10)
			WebUI.click(testCaseTitle)
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

					if(WebUI.waitForElementPresent(btnAssertion, 30) ==true){
						WebUI.delay(5)
						WebUI.click(btnAssertion)
						WebUI.delay(2)
						MesmerLogUtils.markPassed("Clicked on assertion button")

						//					if(WebUI.waitForElementPresent(loadingDataFromMDOM, 10) ==true){
						if(WebUI.waitForElementNotPresent(elementsFetchFailed, 20) ==true){
							MesmerLogUtils.markPassed("Fetched elements")
							if(WebUI.waitForElementPresent(assertionType, 20)==true){
								WebUI.click(assertionType)
								if(WebUI.waitForElementPresent(searchForText, 20)==true){
									WebUI.click(searchForText)
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
																	if(WebUI.waitForElementPresent(actionTypeDefectStopTest, 20)==true){
																		WebUI.click(actionTypeDefectStopTest)

																		if(WebUI.waitForElementPresent(inputDefectMessage, 20)==true){
																			String inputFieldDefectXpath = findTestObject('Object Repository/OR_UDA/input_defectmessage').findPropertyValue('xpath').toString()
																			WebElement inputFieldDefect = driver.findElement(By.xpath(inputFieldDefectXpath))
																			inputFieldDefect.click()
																			WebUI.delay(2)
																			inputFieldDefect.sendKeys(inputpageUdaValue)
																			inputFieldDefect.sendKeys(Keys.ENTER)
																			MesmerLogUtils.markPassed("Text Added in Message field ")

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
						}else{
							MesmerLogUtils.markFailed("Failed to fetch elements")
						}
						//						}else{
						//							MesmerLogUtils.markFailed("Failed to load data from MDOM")
						//						}
					}else{
						MesmerLogUtils.markFailed("Could not click on assertion button")
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
}catch(Exception e){
	e.printStackTrace()
}finally{
	//	TestResultController.getInstance().checkIfAssertionDialogAppears()
	//	WebUI.refresh()
	//	CustomKeywords.'com.mesmer.Utility.goToTestResults'()

}


def UDA(){
	WebDriver driver = DriverFactory.getWebDriver()
	//7. Click on Assertion type field
	if(WebUI.waitForElementPresent(assertionType, 20)==true){
		WebUI.click(assertionType)
		MesmerLogUtils.markPassed("Assertion Page List opens ")
		if(WebUI.waitForElementPresent(textEqual, 20)==true){
			MesmerLogUtils.markPassed("Text Equal is there ")
			if(WebUI.waitForElementPresent(textDoesNotEqual, 20)==true){
				MesmerLogUtils.markPassed("Text Does Not Equal is there ")
				if(WebUI.waitForElementPresent(textContains, 20)==true){
					MesmerLogUtils.markPassed("Text Contains is there ")
					if(WebUI.waitForElementPresent(textNotContains, 20)==true){
						MesmerLogUtils.markPassed("Text Contains is there ")
						if(WebUI.waitForElementPresent(textStartsWith, 20)==true){
							MesmerLogUtils.markPassed("Text Starts With is there ")
							if(WebUI.waitForElementPresent(textNotStartsWith, 20)==true){
								MesmerLogUtils.markPassed("Text Not Starts With is there ")
								if(WebUI.waitForElementPresent(textGT, 20)==true){
									MesmerLogUtils.markPassed("Text Greater Than is there ")
									if(WebUI.waitForElementPresent(textLT, 20)==true){
										MesmerLogUtils.markPassed("Text Lesser Than is there ")
										if(WebUI.waitForElementPresent(textGTE, 20)==true){
											MesmerLogUtils.markPassed("Text Greater Than Or Equals is there ")
											if(WebUI.waitForElementPresent(textLTE, 20)==true){
												MesmerLogUtils.markPassed("Text Lesser Than Or Equals is there ")
												if(WebUI.waitForElementPresent(textREGEX, 20)==true){
													MesmerLogUtils.markPassed("REGEX is there ")
													if(WebUI.waitForElementPresent(text_CREATE_VAR, 20)==true){
														MesmerLogUtils.markPassed("Create Variable is there ")
														if(WebUI.waitForElementPresent(text_CONTAIN_VAR, 20)==true){
															MesmerLogUtils.markPassed("Contain Variable is there ")
															//8. Select required assertions and enter values and hit enter
															if(WebUI.waitForElementPresent(textEqual, 20)==true){
																WebUI.click(textEqual)
																MesmerLogUtils.markPassed("Select UDA type Text Equal ")
																String udaValueXpath = findTestObject('Object Repository/OR_TestDetails/xpath_udaValue').findPropertyValue('xpath').toString()
																WebElement udaValue = driver.findElement(By.xpath(udaValueXpath))
																//																String inputUda = "Sign Up"
																udaValue.sendKeys(inputUda)
																udaValue.sendKeys(Keys.ENTER)
																MesmerLogUtils.markPassed("Text Added in Value field ")
																if(WebUI.waitForElementPresent(udaSaved, 20)==true){
																	MesmerLogUtils.markPassed("UDA Saved Successfully ")
																	//9. Click on 'x' button
																	if(WebUI.waitForElementPresent(closeButton, 10)==true){
																		WebUI.click(closeButton)
																		WebUI.delay(10)
																		MesmerLogUtils.markPassed("UDA Dialogue Closed ")
																		//10. Hover the mouse on screenshot of baseline, where assertions was defined
																		String udaElementsXpath = findTestObject('Object Repository/OR_TestDetails/xpath_udaElements').findPropertyValue('xpath').toString()
																		List<WebElement> udaElements = driver.findElements(By.xpath(udaElementsXpath))
																		if(udaElements != null && udaElements.size() > 1){

																			Actions builder = new Actions(driver);
																			builder.moveToElement(udaElements.get(1)).perform();

																			MesmerLogUtils.markPassed("Mouse Hover on a Screen ")

																			if(WebUI.waitForElementPresent(mouseHoveronAssertion, 10)){
																				MesmerLogUtils.markPassed("Green tick on the visible ")
																				//11. Replay the same test case with added assertions
																				if(WebUI.waitForElementPresent(btnRerun, 10) ==true){
																					WebUI.click(btnRerun)
																					MesmerLogUtils.markPassed("Testcase Run successfully ")

																					if(WebUI.waitForElementPresent(btnRun, 10) ==true){
																						WebUI.click(btnRun)
																						MesmerLogUtils.markPassed("Testcase Replayed successfully ")

																						if(WebUI.waitForElementPresent(btnYes, 10) ==true){
																							WebUI.click(btnYes)
																							MesmerLogUtils.markPassed("Button Yes Clicked successfully ")
																						}else{
																							MesmerLogUtils.markFailed("Testcase Not Replayed successfully")
																						}
																					}else{
																						MesmerLogUtils.markFailed("Button Yes Not Clicked successfully")
																					}
																				}else{
																					MesmerLogUtils.markFailed("Testcase Not Run successfully")
																				}
																			}else{
																				MesmerLogUtils.markFailed("Green tick not visible")
																			}
																		}else{
																			MesmerLogUtils.markFailed("Mouse Hover Not Worked")
																		}
																	}else{
																		MesmerLogUtils.markFailed("UDA Dialogue Not Closed")
																	}
																}else{
																	MesmerLogUtils.markFailed("UDA Not Saved")
																}
															}else{
																MesmerLogUtils.markFailed("Could Not Select UDA type Text Equal")
															}
														}else{
															MesmerLogUtils.markFailed("Contain Variable is not there")
														}
													}else{
														MesmerLogUtils.markFailed("Create Variable is not there")
													}
												}else{
													MesmerLogUtils.markFailed("REGEX is not there")
												}
											}else{
												MesmerLogUtils.markFailed("Text Lesser Than Or Equals is not there")
											}
										}else{
											MesmerLogUtils.markFailed("Text Greater Than Or Equals is not there")
										}
									}else{
										MesmerLogUtils.markFailed("Text Lesser Than is not there")
									}
								}else{
									MesmerLogUtils.markFailed("Text Greater Than is not there")
								}
							}else{
								MesmerLogUtils.markFailed("Text Not Starts With is not there")
							}
						}else{
							MesmerLogUtils.markFailed("Text Starts With is not there")
						}
					}else{
						MesmerLogUtils.markFailed("Text Contains is not there")
					}
				}else{
					MesmerLogUtils.markFailed("Text Contains is not there")
				}
			}else{
				MesmerLogUtils.markFailed("Text Does Not Equal is not there")
			}
		}else{
			MesmerLogUtils.markFailed("Text Equal is not there")
		}
	}else{
		MesmerLogUtils.markFailed("Assertion Page List not open")
	}

}