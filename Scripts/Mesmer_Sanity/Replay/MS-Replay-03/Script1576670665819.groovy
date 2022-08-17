import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.ReplayController

//MS-Replay-03 | Verify that during replay a green highlighter will move according to the execution of screen.


WebDriver driver = DriverFactory.getWebDriver()

try{
	
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		//	//1. Click on a Tile/Test case to open.
		//	TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
		//	TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)
		//
		//	if(WebUI.waitForElementPresent(testCaseTitle, 20)== true){
		//		WebUI.scrollToElement(testCaseTitle, 10)
		//		WebUI.click(testCaseTitle)
		//		MesmerLogUtils.markPassed("Test case opens")

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
				if(WebUI.waitForElementPresent(reRun , 20)==true){
					WebUI.click(reRun)
					MesmerLogUtils.markPassed("Click On Re-run")

					//3. Click/select a device that is in Provisioned/ Ready state
					if(ReplayController.getInstance().selectDevice()){
						MesmerLogUtils.markPassed("Clicked on a device")

						//4. Click on Run button
						if(WebUI.waitForElementPresent(btnRun , 30) == true){
							WebUI.click(btnRun)
							MesmerLogUtils.markPassed("Click on Run Button")

							if(WebUI.waitForElementPresent(btnYes , 20) == true){
								WebUI.click(btnYes)

								if(WebUI.waitForElementPresent(inprogress_Status, 120)==true){
									MesmerLogUtils.markPassed("Test case in-progress")

									if(WebUI.waitForElementPresent(inProgressLoader, 120)==true){
										MesmerLogUtils.markPassed("In-progress Loader")

										if(WebUI.waitForElementPresent(titleOverlay, 120)==true){
											MesmerLogUtils.markPassed("Overlay present on step")

											if(WebUI.waitForElementNotPresent(titleOverlay, 1200)==true){
												MesmerLogUtils.markPassed("Test case end")

											}else{
												MesmerLogUtils.markFailed("Replay not end")
											}

										}else{
											MesmerLogUtils.markFailed("No Overlay on the Tile Screen 1")
										}

									}else{
										MesmerLogUtils.markFailed("No In-progress Loader")
									}

								}else{
									MesmerLogUtils.markFailed("Test case in-progress not started")
								}

							}else{
								MesmerLogUtils.markFailed("Unable to Click on Yes Button")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to Click on Run Button")
						}
					}
					else{
						MesmerLogUtils.markFailed("Device not selected")
					}
				}else {
					MesmerLogUtils.markFailed("Unable to click on reRUn button")
				}
			}else{
				MesmerLogUtils.markFailed("No test case in New filter")
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

}