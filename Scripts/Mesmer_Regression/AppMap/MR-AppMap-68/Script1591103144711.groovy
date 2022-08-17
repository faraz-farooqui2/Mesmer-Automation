import org.openqa.selenium.WebDriver

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.AppMapController


//AppMap-Device logs-68 - Verify user can select Show filters to filter logs as required
//(Verbose, Info, Debug, Warn, Error)


//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{

	//	if(AppMapController.getInstance().startACrawl()){
	if(WebUI.waitForElementVisible(btnLogs, 5)){
		MesmerLogUtils.logInfo("Logs icon is displayed. Now clicking logs icon")
		WebUI.click(btnLogs)
		MesmerLogUtils.logInfo("Logs icon is clicked")

		//Checking Verbose Logs
		if(WebUI.waitForElementVisible(showOption, 5)){
			MesmerLogUtils.logInfo("Show option is displayed. Now clicking...")
			WebUI.click(showOption)
			MesmerLogUtils.logInfo("Show dropdown is clicked")

			if(WebUI.waitForElementVisible(verboseOption, 5)){
				MesmerLogUtils.logInfo("Verbose option is displayed")
				MesmerLogUtils.logInfo("Clicking Verbose option")
				WebUI.click(verboseOption)
				MesmerLogUtils.logInfo("Verbose option is clicked")

				if(WebUI.waitForElementVisible(selectedVerboseLogs, 5)){
					MesmerLogUtils.markPassed("Verbose option is selected successfully")



					//Checking Logs Info Option
					if(WebUI.waitForElementVisible(showOption, 5)){
						MesmerLogUtils.logInfo("Show option is displayed. Now clicking...")
						WebUI.click(showOption)
						MesmerLogUtils.logInfo("Show dropdown is clicked")

						if(WebUI.waitForElementVisible(infoOption, 5)){
							MesmerLogUtils.logInfo("Info option is displayed")
							MesmerLogUtils.logInfo("Clicking Info option")
							WebUI.click(infoOption)
							MesmerLogUtils.logInfo("Info option is clicked")

							if(WebUI.waitForElementVisible(selectedInfoLogs, 5)){
								MesmerLogUtils.markPassed("Info option is selected successfully")


								//Checking Debug Logs Option
								if(WebUI.waitForElementVisible(showOption, 5)){
									MesmerLogUtils.logInfo("Show option is displayed. Now clicking...")
									WebUI.click(showOption)
									MesmerLogUtils.logInfo("Show dropdown is clicked")

									if(WebUI.waitForElementVisible(debugOption, 5)){
										MesmerLogUtils.logInfo("Debug option is displayed")
										MesmerLogUtils.logInfo("Clicking Debug option")
										WebUI.click(debugOption)
										MesmerLogUtils.logInfo("Debug option is clicked")

										if(WebUI.waitForElementVisible(selectedDebugLogs, 5)){
											MesmerLogUtils.markPassed("Debug option is selected successfully")

											//Checking Warn Logs option
											if(WebUI.waitForElementVisible(showOption, 5)){
												MesmerLogUtils.logInfo("Show option is displayed. Now clicking...")
												WebUI.click(showOption)
												MesmerLogUtils.logInfo("Show dropdown is clicked")

												if(WebUI.waitForElementVisible(warnOption, 5)){
													MesmerLogUtils.logInfo("Warn option is displayed")
													MesmerLogUtils.logInfo("Clicking Warn option")
													WebUI.click(warnOption)
													MesmerLogUtils.logInfo("Warn option is clicked")

													if(WebUI.waitForElementVisible(selectedWarnLogs, 5)){
														MesmerLogUtils.markPassed("Warn option is selected successfully")



														//Checking Error Logs option
														if(WebUI.waitForElementVisible(showOption, 5)){
															MesmerLogUtils.logInfo("Show option is displayed. Now clicking...")
															WebUI.click(showOption)
															MesmerLogUtils.logInfo("Show dropdown is clicked")

															if(WebUI.waitForElementVisible(errorOption, 5)){
																MesmerLogUtils.logInfo("Error option is displayed")
																MesmerLogUtils.logInfo("Clicking Error option")
																WebUI.click(errorOption)
																MesmerLogUtils.logInfo("Error option is clicked")

																if(WebUI.waitForElementVisible(selectedErrorLogs, 5)){
																	MesmerLogUtils.markPassed("Error option is selected successfully")
																}
																else{
																	MesmerLogUtils.markFailed("Error option is not selected")
																}

															}
															else{
																MesmerLogUtils.logInfo("Error Option is not displayed")
															}
														}
														else{
															MesmerLogUtils.logInfo("Show option is not displayed")
														}
													}
													else{
														MesmerLogUtils.markFailed("Warn option is not selected")
													}

												}
												else{
													MesmerLogUtils.logInfo("Warn Option is not displayed")
												}
											}
											else{
												MesmerLogUtils.logInfo("Show option is not displayed")
											}
										}
										else{
											MesmerLogUtils.markFailed("Debug option is not selected")
										}

									}
									else{
										MesmerLogUtils.logInfo("Debug Option is not displayed")
									}
								}
								else{
									MesmerLogUtils.logInfo("Show option is not displayed")
								}



							}
							else{
								MesmerLogUtils.markFailed("Info option is not selected")
							}

						}
						else{
							MesmerLogUtils.logInfo("Info Option is not displayed")
						}
					}
					else{
						MesmerLogUtils.logInfo("Show option is not displayed")
					}

				}
				else{
					MesmerLogUtils.markFailed("Verbose option is not selected")
				}

			}
			else{
				MesmerLogUtils.logInfo("Verbose Option is not displayed")
			}
		}
		else{
			MesmerLogUtils.logInfo("Show option is not displayed")
		}

	}
	else{
		MesmerLogUtils.logInfo("Could not click on log button")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}

