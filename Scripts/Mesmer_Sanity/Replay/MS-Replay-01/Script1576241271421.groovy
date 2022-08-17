import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ReplayController

//MS-Replay-01 | Verify that user should be able to replay a test case successfully by selecting or not selecting the device. (Physical/emulator).


WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		//	//1. Click on a Tile/Test case to open.
		//
		//	TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
		//	TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)
		//
		//	if(WebUI.waitForElementPresent(testCaseTitle, 30)== true){
		//		WebUI.scrollToElement(testCaseTitle, 10)
		//		WebUI.click(testCaseTitle)

		//1. Click on any test case/tile with status New/Recommended test case
		if(WebUI.waitForElementPresent(btnNew, 20)== true){
			WebUI.click(btnNew)
			WebUI.delay(2)
			MesmerLogUtils.markPassed("Clicked on New filter")

			String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
			List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

			MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())

			if(titleStreamList.size() > 0){
				titleStreamList.get(0).click()
				WebUI.delay(3)
				MesmerLogUtils.markPassed("Clicked on Test Case")

				//2. Click on 'Rerun' button appearing in the top right corner
				if(WebUI.waitForElementPresent(reRun , 120)==true){
					WebUI.click(reRun)
					MesmerLogUtils.markPassed("Click On Re-run")

					//3. Click/select a device that is in Provisioned/ Ready state
					if(ReplayController.getInstance().selectDevice()){
						MesmerLogUtils.markPassed("Clicked on a device")

						//4. Click on Run button
						if(WebUI.waitForElementClickable(btnRun , 20) == true){
							WebUI.click(btnRun)
							MesmerLogUtils.markPassed("Click on Run Button")

							if(WebUI.waitForElementPresent(btnYes , 20) == true){
								WebUI.click(btnYes)
								MesmerLogUtils.markPassed("Click on Yes Button")
								if(WebUI.waitForElementPresent(inProgress, 180)==true){
									MesmerLogUtils.markPassed("Test case in-progress")
									WebUI.delay(5)
									if(ReplayController.getInstance().stopATestCase()){
										MesmerLogUtils.markPassed("Test case stopped replaying")

										//5.Redo steps 1-2 (rerun test case without selecting a device)
										if(WebUI.waitForElementPresent(reRun, 180)==true){
											WebUI.click(reRun)
											MesmerLogUtils.markPassed("Click On Re-run Step 5")

											//6. Click on Run button
											if(WebUI.waitForElementPresent(btnRun , 20) == true){
												WebUI.click(btnRun)
												MesmerLogUtils.markPassed("Click on Run Button Step 6")

												//7. Click on Yes option
												if(WebUI.waitForElementPresent(btnYes , 20) == true){
													WebUI.click(btnYes)
													MesmerLogUtils.markPassed("Click on Yes Button Step 7")

													if(WebUI.waitForElementPresent(inProgress, 180)==true){
														MesmerLogUtils.markPassed("Test case in-progress")
														WebUI.delay(5)
														if(ReplayController.getInstance().stopATestCase()){
															MesmerLogUtils.markPassed("Test case stopped replaying")

														}else{
															MesmerLogUtils.markFailed("Test case not stopped")
														}
													}
													else{
														MesmerLogUtils.markFailed("Test case not replayed in 3 mins")
													}
												}else{
													MesmerLogUtils.markFailed("Unable to Click on Yes Button Step 7")
												}
											}else{
												MesmerLogUtils.markFailed("Unable to Click on Run Button Step 6")
											}
										}else{
											MesmerLogUtils.markFailed("Unable to Click on re Run Button")
										}
									}else{
										MesmerLogUtils.markFailed("Test case not stopped")
									}
								}
								else{
									MesmerLogUtils.markFailed("Test case not replayed in 3 mins")
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
			}else{
				MesmerLogUtils.markFailed("[DATA ISSUE] : No test case in New filter")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to click on New filter")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}

}
catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.delay(5)
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}