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


// MR-Replay-03 | APPSIGHT-20594 | Verify clicking select all "Checkbox" should select all listed devices and multiple replays can be executed at same time

WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)){


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		String testCaseTileXpath = findTestObject('Object Repository/OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
		List<WebElement> testCaseTile = driver.findElements(By.xpath(testCaseTileXpath))

		String passedTileXpath = findTestObject('Object Repository/OR_Replay/tile_Passed').findPropertyValue('xpath').toString()
		List<WebElement> passedTile = driver.findElements(By.xpath(passedTileXpath))

		String failedTileXpath = findTestObject('Object Repository/OR_Replay/tile_failed').findPropertyValue('xpath').toString()
		List<WebElement> failedTile = driver.findElements(By.xpath(failedTileXpath))

		String brokenTileXpath = findTestObject('Object Repository/OR_Replay/tile_broken').findPropertyValue('xpath').toString()
		List<WebElement> brokenTile = driver.findElements(By.xpath(brokenTileXpath))


		if(testCaseTile != null && testCaseTile.size() > 0 ){

			if(passedTile.size() > 0 ){
				WebUI.delay(2)
				MesmerLogUtils.logInfo("Number of Passed test cases in a project " + "  :  " + passedTile.size())

				WebUI.delay(5)
				if(ReplayController.getInstance().clickOnReplayTestResults()){
					MesmerLogUtils.markPassed("Clicked on Replay button")

					if(ReplayController.getInstance().selectAllDevices()){

						MesmerLogUtils.logInfo("All ready devices selected")

						if(ReplayController.getInstance().clickOnRunButton()){
							MesmerLogUtils.markPassed("Clicked on run button")

							if(ReplayController.getInstance().confirmationRunningTestCase()){
								MesmerLogUtils.markPassed("Clicked on yes button")
								if(ReplayController.getInstance().testResultInQueue()){
									MesmerLogUtils.markPassed("Test Case in queue")
									if(ReplayController.getInstance().testResultInProgress()){
										MesmerLogUtils.markPassed("Test Case in progress")

									}
								}
							}
						}

					}
				}else{
				MesmerLogUtils.markFailed("")
				}

			}else if (failedTile.size() > 0) {
				MesmerLogUtils.logInfo("Number of Failed test cases in a project " + "  :  " + failedTile.size())

				failedTile.get(0).click()
				MesmerLogUtils.logInfo("Failed test case opened")

				WebUI.delay(5)
				if(ReplayController.getInstance().clickOnReplayTestResults()){
					MesmerLogUtils.markPassed("Clicked on Replay button")

					if(ReplayController.getInstance().selectAllDevices()){

						MesmerLogUtils.logInfo("All ready devices selected")

						if(ReplayController.getInstance().clickOnRunButton()){
							MesmerLogUtils.markPassed("Clicked on run button")

							if(ReplayController.getInstance().confirmationRunningTestCase()){
								MesmerLogUtils.markPassed("Clicked on yes button")
								if(ReplayController.getInstance().testResultInQueue()){
									MesmerLogUtils.markPassed("Test Case in queue")
									if(ReplayController.getInstance().testResultInProgress()){
										MesmerLogUtils.markPassed("Test Case in progress")

									}
								}
							}
						}

					}
				}

			}else if (brokenTile.size() > 0) {
				MesmerLogUtils.logInfo("Number of Broken test cases in a project " + "  :  " + brokenTile.size())

				brokenTile.get(0).click()
				MesmerLogUtils.logInfo("Broken test case opened")

				WebUI.delay(5)
				if(ReplayController.getInstance().clickOnReplayTestResults()){
					MesmerLogUtils.markPassed("Clicked on Replay button")

					if(ReplayController.getInstance().selectAllDevices()){

						MesmerLogUtils.logInfo("All ready devices selected")

						if(ReplayController.getInstance().clickOnRunButton()){
							MesmerLogUtils.markPassed("Clicked on run button")

							if(ReplayController.getInstance().confirmationRunningTestCase()){
								MesmerLogUtils.markPassed("Clicked on yes button")
								if(ReplayController.getInstance().testResultInQueue()){
									MesmerLogUtils.markPassed("Test Case in queue")
									if(ReplayController.getInstance().testResultInProgress()){
										MesmerLogUtils.markPassed("Test Case in progress")

									}
								}
							}
						}

					}
				}

			}else{
				MesmerLogUtils.markFailed("Passed , Failed and Broken tests not found ")
			}
		}else{
			MesmerLogUtils.markFailed("No test case found in a project " + "  :  " +  testCaseTile.size() )
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}

}catch(Exception e){

	e.printStackTrace()

}finally{
	if(ReplayController.getInstance().stopATestCase()){
		MesmerLogUtils.markPassed("Clicked on stopped test case")
		WebUI.delay(5)
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	}else{
		WebUI.refresh()
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()
		MesmerLogUtils.markFailed("Could not click on stop test case button")
	}

}