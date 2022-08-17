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



WebDriver driver = DriverFactory.getWebDriver()
try {
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)

	//Calling Select Project Method

	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())


		CustomKeywords.'com.mesmer.Utility.goToAppMap'()

		if(AppMapController.getInstance().clickRunCrawl(hours , minutes , Device)){

			WebUI.delay(30)

			if(checkDeviceLogs()){

				if(AppMapController.getInstance().stopACrawl()){

				}else{
					KeywordUtil.markFailed("Could not stop a crawl")
				}
			}else{
				KeywordUtil.markFailed("Device logs failed")
			}
		}else{
			MesmerLogUtils.markFailed("Could not configure and start crawl")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally {

}

private boolean  checkDeviceLogs(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.refresh()
	WebUI.delay(3)
	if(WebUI.waitForElementClickable(buttonDeviceLogs, 10)==true){
		MesmerLogUtils.markPassed("Device logs button is visible")
		WebUI.click(buttonDeviceLogs)


		if(WebUI.waitForElementPresent(multipleLinesOfLogs, 30, FailureHandling.CONTINUE_ON_FAILURE)==true){
			MesmerLogUtils.markPassed("Tail Logs are displayed successfully")


			//2. Click on 'Verbose' beside Show filter.
			//3. Click on different option from the list.
			if(WebUI.waitForElementVisible(showOption, 2)==true){
				WebUI.click(showOption)

				if(WebUI.waitForElementVisible(verboseLogs, 2)==true){
					WebUI.click(verboseLogs)
					MesmerLogUtils.markPassed("Verbose logs are displayed and clicked successfully")


					WebUI.click(showOption)
					if(WebUI.waitForElementVisible(infoLogs, 2)==true){
						WebUI.click(infoLogs)
						MesmerLogUtils.markPassed("Info logs are displayed and clicked successfully")


						WebUI.click(showOption)
						if(WebUI.waitForElementVisible(debugLogs, 2)==true){
							WebUI.click(debugLogs)
							MesmerLogUtils.markPassed("Debug logs are displayed and clicked successfully")



							if(platformName=='apple'){

								WebUI.click(showOption)
								WebUI.delay(1)
								if(WebUI.waitForElementVisible(errorLogs, 2)==true){
									WebUI.click(errorLogs)
									MesmerLogUtils.markPassed("Error logs are displayed and clicked successfully")


									WebUI.click(showOption)
									WebUI.delay(1)
									if(WebUI.waitForElementVisible(faultLogs, 2)==true){
										WebUI.click(faultLogs)
										MesmerLogUtils.markPassed("Fault logs are displayed and clicked successfully")
										if(performActions()){

										}else{
											MesmerLogUtils.markFailed("Actions not performed")
										}
									}
									else{
										MesmerLogUtils.markFailed("Fault logs are not displayed")
									}
								}
								else{
									MesmerLogUtils.markFailed("Error logs are not displayed")
								}

							}else{
								WebUI.delay(1)
								WebUI.click(showOption)
								WebUI.delay(1)
								if(WebUI.waitForElementVisible(warnLogs, 2)==true){
									WebUI.click(warnLogs)
									MesmerLogUtils.markPassed("Warning logs are displayed and clicked successfully")


									WebUI.delay(1)
									WebUI.click(showOption)
									WebUI.delay(1)
									if(WebUI.waitForElementVisible(errorLogs, 2)==true){
										WebUI.click(errorLogs)
										MesmerLogUtils.markPassed("Error logs are displayed and clicked successfully")
										if(performActions()){

										}else{
											MesmerLogUtils.markFailed("Actions not performed")
										}
									}
									else{
										MesmerLogUtils.markFailed("Error logs are not displayed")
									}
								}
								else{
									MesmerLogUtils.markFailed("Warning logs are not displayed")
								}

							}
						}else{
							MesmerLogUtils.markFailed("Debug logs are not displayed")
						}
					}else{
						MesmerLogUtils.markFailed("Info logs are not displayed")
					}
				}else{
					MesmerLogUtils.markFailed("Verbose logs are not displayed")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to click on show options")
			}
		}else{
			MesmerLogUtils.markFailed("Tail logs are not displayed")
		}
	}else{
		MesmerLogUtils.markFailed("Device logs button is not displayed")
	}
	return result
}

private boolean performActions(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.click(showOption)
	WebUI.click(verboseLogs)

	if(WebUI.waitForElementVisible(optionLogsDropdown, 5)==true){
		MesmerLogUtils.markPassed("Device Logs dropdown is displayed")


		if(WebUI.waitForElementVisible(optionLogsSettings, 5)==true){
			MesmerLogUtils.markPassed("Device Logs settings option is displayed")

			WebUI.click(optionLogsSettings)
			WebUI.delay(1)
			//Pause-Resume logs verification
			if(WebUI.waitForElementVisible(pauseOptioninDropdown, 10)==true){
				WebUI.click(pauseOptioninDropdown)
				WebUI.delay(1)
				if(WebUI.waitForElementVisible(checkLogsPaused, 10)==true){
					MesmerLogUtils.markPassed("Logs are paused successfully")

					if(WebUI.waitForElementVisible(resumeOption, 5)==true){
						WebUI.click(optionLogsSettings)
						WebUI.delay(1)
						if(WebUI.waitForElementVisible(resumeOptioninDropdown, 5)==true){
							WebUI.click(resumeOptioninDropdown)
							WebUI.delay(1)
							if(WebUI.waitForElementVisible(checkLogsPaused, 5)==false){
								MesmerLogUtils.markPassed("Logs are resumed successfully")



								//Verification of 7. Click on 'Wrap Text'/'UnWrap text' options
								WebUI.click(optionLogsSettings)
								if(WebUI.waitForElementVisible(wrapTextOption, 2)==true){
									MesmerLogUtils.markPassed("Wrap option is displayed correctly")
									WebUI.click(wrapTextOption)

									WebUI.click(optionLogsSettings)
									if(WebUI.waitForElementVisible(unwrapTextOption, 2)==true){
										MesmerLogUtils.markPassed("UnWrap option is displayed correctly")
										WebUI.click(unwrapTextOption)



										//9. Click on 'Filter' button
										//Search Logs
										//10. Enter text that need to be seached in logs
										CustomKeywords.'com.mesmer.Utility_AppMap.checkSearchinLogs'()


										//5. Click on 'Download Log'
										WebUI.click(optionLogsSettings)
										if(WebUI.waitForElementVisible(downloadLogs, 2)==true){
											WebUI.click(downloadLogs)
											MesmerLogUtils.markPassed("Logs are downloaded successfully")



											//6. Click on 'Clear Logs'
											//Verification -- All the logs appearing on modal will disappear.
											WebUI.click(optionLogsSettings)
											if(WebUI.waitForElementVisible(clearLogs, 2)==true){
												WebUI.click(clearLogs)

												String logsRow = findTestObject('Object Repository/OR_AppMap/xpath_logsRow').findPropertyValue('xpath').toString()

												List<WebElement> logRowsinSearch = driver.findElements(By.xpath(logsRow))
												int countLogs = logRowsinSearch.size()
												if(countLogs==0){
													MesmerLogUtils.markPassed("Logs are cleared successfully")


													if(WebUI.waitForElementVisible(optionResizeLogsWindow, 5)==true){
														MesmerLogUtils.markPassed("Device Logs resize Window option is displayed")


														if(WebUI.waitForElementVisible(optionCollapseDrawer, 5)==true){
															MesmerLogUtils.markPassed("Device Logs collapse Window option is displayed")
															result = true
														}
														else{
															MesmerLogUtils.markFailed("Device logs collapse Window option is not visible")
														}
													}
													else{
														MesmerLogUtils.markFailed("Device logs resize Window option is not visible")
													}
												}
												else{
													MesmerLogUtils.markFailed("Logs are not cleared")
												}

											}else{
												MesmerLogUtils.markFailed("Unable to click om Clear Logs button")
											}
										}
										else{
											MesmerLogUtils.markFailed("Device logs settings option is not visible")
										}
									}
									else{
										MesmerLogUtils.markFailed("UnWrap option is not displayed correctly")
									}
								}else{
									MesmerLogUtils.markFailed("Wrap option is not displayed correctly")
								}
							}
							else{
								MesmerLogUtils.markFailed("Unable to resume logs")
							}

						}
						else{
							MesmerLogUtils.markFailed("Resume option is not displayed in dropdown after pausing logs")
						}

					}else{
						MesmerLogUtils.markFailed("Unable to click on Option Log Settings")
					}

				}
				else{
					MesmerLogUtils.markFailed("Logs are not paused successfully")
				}
			}
			else{
				MesmerLogUtils.markFailed("Pause option is not displayed")
			}
		}
		else{
			MesmerLogUtils.markFailed("Device logs button is not visible")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on Show Option button")
	}
	return result
}