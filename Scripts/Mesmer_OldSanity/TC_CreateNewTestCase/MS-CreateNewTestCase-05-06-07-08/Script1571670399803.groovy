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
import com.kms.katalon.core.testdata.InternalData as InternalData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testdata.reader.ExcelFactory as ExcelFactory
import org.junit.After as After
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

if(WebUI.waitForElementVisible(beforeYouGoWindow,1)==true){
	CustomKeywords.'com.mesmer.CreateNewTestCase.discardTestCase'()
}
else{
	KeywordUtil.logInfo("Before you go window is not displayed")
}

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()

try{
	
	if(WebUI.waitForElementPresent(startRecordingIcon, 100)==true){
		WebUI.click(startRecordingIcon)
		if(WebUI.waitForElementPresent(enabledOptionsIcon, 60)==true){

			WebUI.delay(3)
			if(WebUI.waitForElementClickable(deviceLogsOption, 10)==true){
				KeywordUtil.markPassed("SUCCESS: Device logs button is visible")
				WebUI.click(deviceLogsOption)


				//MS-CreateNewTestCase-06 validation starts
				if(WebUI.waitForElementPresent(multipleLinesOfLogs, 50)==true){
					KeywordUtil.markPassed("SUCCESS: Tail Logs are displayed successfully")
				}else{
					KeywordUtil.markFailed("FAILED: Tail logs are not displayed")
				}
				//MS-CreateNewTestCase-06 ends


				if(WebUI.waitForElementPresent(optionLogsDropdown, 5)==true){
					KeywordUtil.markPassed("SUCCESS: Device Logs dropdown is displayed")
				}
				else{
					KeywordUtil.markFailed("FAILED: Device logs button is not visible")
				}

				if(WebUI.waitForElementPresent(optionLogsSettings, 5)==true){
					KeywordUtil.markPassed("SUCCESS: Device Logs settings option is displayed")

					//MS-CreateNewTestCase-07 validation starts
					WebUI.click(optionLogsSettings)
					WebUI.delay(1)
					if(WebUI.waitForElementPresent(pauseOptioninDropdown, 10)==true){
						WebUI.click(pauseOptioninDropdown)
						WebUI.delay(1)
						if(WebUI.waitForElementPresent(checkLogsPaused, 10)==true){
							KeywordUtil.markPassed("SUCCESS: Logs are paused successfully")

							if(WebUI.waitForElementPresent(resumeOption, 5)==true){
								WebUI.click(optionLogsSettings)
								WebUI.delay(1)
								if(WebUI.waitForElementPresent(resumeOptioninDropdown, 5)==true){
									WebUI.click(resumeOptioninDropdown)
									WebUI.delay(1)
									if(WebUI.waitForElementVisible(checkLogsPaused, 5)==false){
										KeywordUtil.markPassed("SUCCESS: Logs are resumed successfully")
									}
									else{
										KeywordUtil.markFailed("FAILED: Unable to resume logs")
									}

								}
								else{
									KeywordUtil.markFailed("FAILED: Resume option is not displayed in dropdown after pausing logs")
								}

							}

						}
						else{
							KeywordUtil.markFailed("FAILED: Logs are not paused successfully")
						}

					} //MS-CreateNewTestCase-07 validation Ends

					else{
						KeywordUtil.markFailed("FAILED: Pause option is not displayed")
					}

				}
				else{
					KeywordUtil.markFailed("FAILED: Device logs settings option is not visible")
				}

				if(WebUI.waitForElementPresent(optionResizeLogsWindow, 5)==true){
					KeywordUtil.markPassed("SUCCESS: Device Logs resize Window option is displayed")
				}
				else{
					KeywordUtil.markFailed("FAILED: Device logs resize Window option is not visible")
				}

				if(WebUI.waitForElementPresent(optionCollapseDrawer, 5)==true){
					KeywordUtil.markPassed("SUCCESS: Device Logs collapse Window option is displayed")
				}
				else{
					KeywordUtil.markFailed("FAILED: Device logs collapse Window option is not visible")
				}

//				if(WebUI.waitForElementPresent(panelDeviceLogs, 5)==true){
//					KeywordUtil.markPassed("SUCCESS: Running device logs are displayed")
//				}
//				else{
//					KeywordUtil.markFailed("FAILED: Running device logs are not displayed")
//				}

				if(WebUI.waitForElementPresent(optionLogsSettings, 5)==true){
					WebUI.click(optionLogsSettings)
					if(WebUI.waitForElementPresent(downloadLogs, 5)==true){
						WebUI.delay(1)
						WebUI.click(downloadLogs)
						WebUI.delay(1)
					}
					else{
						KeywordUtil.markFailed("FAILED: Download logs option in Settings is not displayed")
					}

				}
				else{
					KeywordUtil.markFailed("FAILED: Device logs Settings button is not displayed")
				}

				if(WebUI.waitForElementPresent(enabledSavedText, 60)==true){
					WebUI.click(enabledSavedText)
					WebUI.delay(12)
					KeywordUtil.markPassed("PASSED: CreateNewTestCase-04 successfull")
				}
				else{
					KeywordUtil.markFailed("FAILED: Discard text not found")
				}

			}
			else{
				KeywordUtil.markFailed("FAILED: Device logs button is not displayed")
			}


		}
		else{
			KeywordUtil.markFailed("FAILED: Enabled options icon not found")
		}
	}else if(WebUI.waitForElementPresent(preparingLiveView, 2) == true){
		KeywordUtil.markWarning("Preparing Live View")
	}else if (WebUI.waitForElementPresent(clickToRetry, 2) == true){
		KeywordUtil.markFailed("FAILED: Stream Failed")
	}
	else{
		KeywordUtil.markFailed("FAILED: Start recording icon not found")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.refresh()
}
