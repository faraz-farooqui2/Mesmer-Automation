import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.MesmerLogUtils as MesmerLogUtils
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.ReplayController


// MR-Replay-04 | APPSIGHT-20594 | Verify clicking stop should display confirmation alert, clicking stop on confirmation pop up should stop the replay and clicking cancle on confirmation pop up should continue replay

WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)){


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		if(ReplayController.getInstance().checkNumberOfTestCasesInTestResult()){
			MesmerLogUtils.markPassed("Test Case found in Test Result")

			if(ReplayController.getInstance().openInProgressTestCase()){
				MesmerLogUtils.markPassed("In-Progress test case opened")
				if(ReplayController.getInstance().clickOnStopTestCaseIcon()){
					MesmerLogUtils.markPassed("Clicked on Stop icon")

					if(ReplayController.getInstance().confirmationDialogueStopTestCase()){
						MesmerLogUtils.markPassed("Confirmation stop test case msg appears")
						if(ReplayController.getInstance().clickOnCancelStopTestCase()){
							MesmerLogUtils.markPassed("Clicked on cancel stop test case")
							if(ReplayController.getInstance().clickOnStopTestCaseIcon()){
								MesmerLogUtils.markPassed("Clicked on stop test case icon")

								if(ReplayController.getInstance().clickOnYesStopTestCase()){
									MesmerLogUtils.markPassed("Clicked on stop test case yes button")
									result = true
								}else{
									MesmerLogUtils.markFailed("Could not click on stop test case yes button")
								}
							}else{
								MesmerLogUtils.markFailed("Could not click on stop test case icon")
							}
						}else{
							MesmerLogUtils.markFailed("Could not click on cancel stop test case")
						}
					}else{
						MesmerLogUtils.markFailed("No confirmation stop test case msg appears")
					}
				}else{
					MesmerLogUtils.markFailed("Could not click on stop icon")
				}
			}else if (ReplayController.getInstance().openPassedTestCase()){
				MesmerLogUtils.markPassed("Passed test case opened")
				if(performActions()){
					MesmerLogUtils.markPassed("Test Case Replay-04 Passed")
				}else{
					MesmerLogUtils.markFailed("Test Case Replay-04 Failed")
				}

			}else if (ReplayController.getInstance().openFailedTestCase()){
				MesmerLogUtils.markPassed("Failed test case opened")
				if(performActions()){
					MesmerLogUtils.markPassed("Test Case Replay-04 Passed")
				}else{
					MesmerLogUtils.markFailed("Test Case Replay-04 Failed")
				}

			}else if (ReplayController.getInstance().openBrokenTestCase()){
				MesmerLogUtils.markPassed("Broken test case opened")
				if(performActions()){
					MesmerLogUtils.markPassed("Test Case Replay-04 Passed")
				}else{
					MesmerLogUtils.markFailed("Test Case Replay-04 Failed")
				}

			}else{
				MesmerLogUtils.markFailed("No Test Case found with In-Progress , Passed , Failed and Broken Status in Test Result")
			}

		}else{
			MesmerLogUtils.markFailed("No Test Case found in Test Result")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}

}catch(Exception e){

	e.printStackTrace()

}finally{

	CustomKeywords.'com.mesmer.Utility.goToTestResults'()

}

public boolean performActions(){
	boolean result = false
	if(ReplayController.getInstance().clickOnReplayButtonTestDetail()){
		MesmerLogUtils.markPassed("Clicked on Re-run button")
		if(ReplayController.getInstance().selectDevice()){
			MesmerLogUtils.markPassed("Device selected")
			if(ReplayController.getInstance().clickOnRunButton()){
				MesmerLogUtils.markPassed("Clicked on Run button")
				if(ReplayController.getInstance().confirmationRunningTestCase()){
					MesmerLogUtils.markPassed("Clicked on Yes button")

					TestObject replayStarting = findTestObject('Object Repository/OR_TestDetails/msg_startingTestCase')
					if(WebUI.waitForElementPresent(replayStarting, 120)){

						if(ReplayController.getInstance().clickOnStopTestCaseIcon()){
							MesmerLogUtils.markPassed("Clicked on Stop icon")

							if(ReplayController.getInstance().confirmationDialogueStopTestCase()){
								MesmerLogUtils.markPassed("Confirmation stop test case msg appears")
								if(ReplayController.getInstance().clickOnCancelStopTestCase()){
									MesmerLogUtils.markPassed("Clicked on cancel stop test case")
									if(ReplayController.getInstance().clickOnStopTestCaseIcon()){
										MesmerLogUtils.markPassed("Clicked on stop test case icon")

										if(ReplayController.getInstance().clickOnYesStopTestCase()){
											MesmerLogUtils.markPassed("Clicked on stop test case yes button")
											result = true
										}else{
											MesmerLogUtils.markFailed("Could not click on stop test case yes button")
										}
									}else{
										MesmerLogUtils.markFailed("Could not click on stop test case icon")
									}
								}else{
									MesmerLogUtils.markFailed("Could not click on cancel stop test case")
								}
							}else{
								MesmerLogUtils.markFailed("No confirmation stop test case msg appears")
							}
						}else{
							MesmerLogUtils.markFailed("Could not click on stop icon")
						}
					}else{
						MesmerLogUtils.markFailed("Replay not started in 2 mins")
					}
				}else{
					MesmerLogUtils.markFailed("Could not click on Yes button")
				}
			}else{
				MesmerLogUtils.markFailed("Could not click on run button")
			}
		}else{
			MesmerLogUtils.markFailed("Could not select a device")
		}
	}else{
		MesmerLogUtils.markFailed("Could not click on Re-run button")
	}
	return result
}