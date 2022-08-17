import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import org.openqa.selenium.interactions.Actions
import controllers.ReplayController

//MS-Replay-04 | Verify that if a replay goes in queue a proper message will appear both on top right corner and in the bottom mentioning the queue number of the testcase.


WebDriver driver = DriverFactory.getWebDriver()
try{
	
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		//1. Click on any test case/tile with status New/Recommended test case
		if(WebUI.waitForElementPresent(btnNew, 20)== true){
			WebUI.click(btnNew)
			WebUI.delay(2)
			MesmerLogUtils.markPassed("Clicked on New filter")

			String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
			List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

			MesmerLogUtils.logInfo("Number of test cases in a project" + " : " + titleStreamList.size())
			if(titleStreamList.size() > 0){
				titleStreamList.get(0).click()
				WebUI.delay(3)
				MesmerLogUtils.markPassed("Clicked on Test Case")

				//2. Click on 'Rerun' button appearing in the top right corner
				if(WebUI.waitForElementPresent(reRun , 30) ==true){
					WebUI.click(reRun)
					MesmerLogUtils.markPassed("Click On Re-run")
					//3. Click/select a device that is in Provisioned/ Ready state

					WebUI.delay(5)

					if(WebUI.waitForElementPresent(listDevices , 30) == true){

						if(ReplayController.getInstance().selectDevice()){

							//4. Click on Run button
							if(WebUI.waitForElementPresent(btnRun , 120) == true){
								WebUI.click(btnRun)
								MesmerLogUtils.markPassed("Click on Run Button")
							}else{
								MesmerLogUtils.markFailed("Unable to Click on Run Button")
							}
							if(WebUI.waitForElementPresent(btnYes , 20) == true){
								WebUI.click(btnYes)

								if(WebUI.waitForElementPresent(queueMsg , 20) == true){
									MesmerLogUtils.markPassed("Test Case Queued")

									if(WebUI.waitForElementPresent(queueNumber , 30) == true){
										MesmerLogUtils.markPassed(WebUI.getText(queueNumber))


										String mouseHoverQueueXpath = findTestObject('Object Repository/OR_Replay/btn_Cancel').findPropertyValue('xpath').toString()
										WebElement mouseHoverQueue = driver.findElement(By.xpath(mouseHoverQueueXpath))
										if(mouseHoverQueue != null){

											Actions builder = new Actions(driver);
											builder.moveToElement(mouseHoverQueue).click(mouseHoverQueue).build().perform();

											MesmerLogUtils.markPassed("Mouse Hover on a cancel button")

										}else{
											MesmerLogUtils.markFailed("No Cancel Run Button in Footer")
										}
										//}else if(WebUI.waitForElementPresent(noTestRun , 10)){
										//MesmerLogUtils.markPassed("Test has not been run yet")

									}else if(WebUI.waitForElementPresent(onSameDevice, 30)){
										MesmerLogUtils.markPassed("Replay starting on a same device")
										if(WebUI.waitForElementNotPresent(executing, 1200)){
											MesmerLogUtils.markPassed("Test Case Execution Completed")
										}else{
											MesmerLogUtils.markFailed("Test case Execution still in-progress")
										}

									}else if(WebUI.waitForElementPresent(executing, 30)){
										MesmerLogUtils.markPassed("Test Case Executing")
										if(WebUI.waitForElementNotPresent(executing, 1200)){
											MesmerLogUtils.markPassed("Test Case Execution Completed")
										}else{
											MesmerLogUtils.markFailed("Test case Execution still in-progress")
										}
									}else{
										MesmerLogUtils.markFailed("Test case not executed")
									}

								}else{
									MesmerLogUtils.markFailed("No Test Case in Queue pop up appears")
								}

							}else{
								MesmerLogUtils.markFailed("Unable to Click on Yes Button")
							}
						}
						else{
							MesmerLogUtils.markFailed("Device not selected")
						}
					}
					else{
						MesmerLogUtils.markFailed("Device list not fetched")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to click on reRun button")
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
}catch(Exception e){
	e.printStackTrace()
}finally{

}