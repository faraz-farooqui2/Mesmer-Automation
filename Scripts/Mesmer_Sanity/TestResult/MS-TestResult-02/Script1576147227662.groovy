import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

//MS-Test Result-02 | Verify that user can Start/Stop executions from test result page successfully (iOS/ Android).


WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()


		//1. Click on a Tile/Test case to open.

		TestObject spanTestCase = findTestObject('Object Repository/OR_Replay/span_TestCase')
		TestObject testCaseTitle = CustomKeywords.'com.mesmer.Utility.selectTestCase'(spanTestCase, tcName)

		TestObject checkBoxTestCase = findTestObject('Object Repository/OR_Replay/chkbox_replayProv')
		TestObject clickCheckBoxTestCaseTitle = CustomKeywords.'com.mesmer.Utility.clickCheckbox'(checkBoxTestCase, cName)
		WebUI.delay(2)
		if (WebUI.waitForElementPresent(testCaseTitle, 30)== true){

			Actions builder = new Actions(driver);
			String selectTestCase = testCaseTitle.findPropertyValue('xpath').toString()
			WebElement selectTestCaseTitle = driver.findElement(By.xpath(selectTestCase))
			builder.moveToElement(selectTestCaseTitle).perform();
			MesmerLogUtils.logInfo("Test case focused successfully by mouse hovering")

			WebUI.click(clickCheckBoxTestCaseTitle)
			WebUI.delay(2)
			MesmerLogUtils.logInfo("Test case selected successfully")

			//2. Click on 'Replay test cases' button
			if(WebUI.waitForElementPresent(btnReplay, 10)==true){
				WebUI.click(btnReplay)
				MesmerLogUtils.markPassed("Clicked on Replay Test Cases")

				if(WebUI.waitForElementPresent(btnRun, 10)==true){
					WebUI.click(btnRun)
					MesmerLogUtils.markPassed("Clicked on Run Test Cases")

					if(WebUI.waitForElementPresent(btnYes, 10)==true){
						WebUI.click(btnYes)
						MesmerLogUtils.markPassed("Clicked on Yes button")


						TestObject testCaseXpath = findTestObject('Object Repository/OR_Replay/span_InProgress')
						TestObject selectInprogress = CustomKeywords.'com.mesmer.Utility.selectTestCase'(testCaseXpath, tcName)

						if(WebUI.waitForElementPresent(testCaseXpath, 60)){
							String selectInprgressTestCaseXpath = selectInprogress.findPropertyValue('xpath').toString()
							WebElement selectInProgressTestCaseTitle = driver.findElement(By.xpath(selectInprgressTestCaseXpath))
							builder.moveToElement(selectInProgressTestCaseTitle).click().build().perform();
							MesmerLogUtils.logInfo("Test case focused successfully by mouse hovering")


							//3. Click on 'Stop selected test cases execution' button (hand stop sign)
							if(stopTestCase()){
								MesmerLogUtils.markPassed("Test case stopped successfully")
							}else{
								MesmerLogUtils.markFailed("Could not stop test case")
							}
						}else{
							MesmerLogUtils.markFailed("Test case is not in-progress")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to click on Yes button")
					}
				}else{
					MesmerLogUtils.markFailed("Button Run Test Not Clicked")
				}
			}else{
				MesmerLogUtils.markFailed("Button Replay Not Clicked")
			}
		}else{
			MesmerLogUtils.markFailed("Test Case Not Open")
		}

	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}

}catch(Exception e){
	e.printStackTrace()
}finally{
	//	WebUI.refresh()
}

private boolean stopTestCase(){
	boolean result = false
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.delay(2)

	if(WebUI.waitForElementPresent(btnStop, 20)==true){
		WebUI.click(btnStop)
		MesmerLogUtils.markPassed('Clicked on Stop Button')

		if(WebUI.waitForElementPresent(btnYes, 20)==true){
			WebUI.delay(2)
			WebUI.click(btnYes)
			MesmerLogUtils.markPassed('Clicked on Yes')
			result = true
			WebUI.delay(10)
		}else{
			MesmerLogUtils.markFailed('Unable to Click on Yes')
		}
	}else{
		MesmerLogUtils.markFailed('Unable to Click on Stop Button')
	}
	return result
}