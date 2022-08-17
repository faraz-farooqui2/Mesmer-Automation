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


//MR-Replay-01 | APPSIGHT-20594 | Verify that all available devices are listed in test run section

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
				passedTile.get(0).click()
				MesmerLogUtils.logInfo("Passed test case opened")
				WebUI.delay(5)
				if(ReplayController.getInstance().clickOnReplayButtonTestDetail()){
					MesmerLogUtils.markPassed("Clicked on Replay button")

					if(ReplayController.getInstance().availableDeviceListTestDetails()){

						MesmerLogUtils.logInfo("List of available devices")

					}else{
						MesmerLogUtils.markFailed("No available device list found")
					}
				}else{
					MesmerLogUtils.markFailed("Could not click on Replay button")
				}

			}else if (failedTile.size() > 0) {
				MesmerLogUtils.logInfo("Number of Failed test cases in a project " + "  :  " + failedTile.size())

				failedTile.get(0).click()
				MesmerLogUtils.logInfo("Failed test case opened")

				if(ReplayController.getInstance().clickOnReplayButtonTestDetail()){
					MesmerLogUtils.markPassed("Clicked on Replay button")

					if(ReplayController.getInstance().availableDeviceListTestDetails()){
						MesmerLogUtils.markPassed("List of available devices")
					}else{
						MesmerLogUtils.markFailed("No available device list found")
					}
				}else{
					MesmerLogUtils.markFailed("Could not click on Replay button")
				}

			}else if (brokenTile.size() > 0) {
				MesmerLogUtils.logInfo("Number of Broken test cases in a project " + "  :  " + brokenTile.size())

				failedTile.get(0).click()
				MesmerLogUtils.logInfo("Broken test case opened")

				if(ReplayController.getInstance().clickOnReplayButtonTestDetail()){
					MesmerLogUtils.markPassed("Clicked on Replay button")

					if(ReplayController.getInstance().availableDeviceListTestDetails()){
						MesmerLogUtils.markPassed("List of available devices")
					}else{
						MesmerLogUtils.markFailed("No available device list found")
					}
				}else{
					MesmerLogUtils.markFailed("Could not click on Replay button")
				}

			}else{
				MesmerLogUtils.logInfo("Passed , Failed and Broken tests not found ")
			}
		}else{
			MesmerLogUtils.logInfo("No test case found in a project " + "  :  " +  testCaseTile.size() )
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}

}catch(Exception e){

	e.printStackTrace()

}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}