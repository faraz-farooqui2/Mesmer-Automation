import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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


//MR-Replay-APPSIGHT-20594-03 | Verify Clicking stop should stop running test case and clicking watch live run should display the video of current running test case

WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)){


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		String inProgressTileXpath = findTestObject('Object Repository/OR_Replay/tile_inProgress').findPropertyValue('xpath').toString()
		List<WebElement> inProgressTile = driver.findElements(By.xpath(inProgressTileXpath))


		if(inProgressTile != null && inProgressTile.size() > 0){
			MesmerLogUtils.logInfo("Number of in-progress test cases in a project" + "" + inProgressTile.size())

			Actions builder = new Actions(driver);
			builder.moveToElement(inProgressTile.get(0)).click(inProgressTile.get(0)).build().perform();
			MesmerLogUtils.logInfo("Test case selected")

			if(ReplayController.getInstance().stopTestCaseFromTestResult()){
				MesmerLogUtils.markPassed("Test case stopped successfully")

				String passedTileXpath = findTestObject('Object Repository/OR_Replay/tile_inProgress').findPropertyValue('xpath').toString()
				List<WebElement> passedTile = driver.findElements(By.xpath(passedTileXpath))


				if(passedTile != null && passedTile.size() > 0){
					MesmerLogUtils.logInfo("Number of Passed test cases in a project" + "" + passedTile.size())

					passedTile.get(0).click()
					MesmerLogUtils.logInfo("Passed test case opened")
				}else{
					MesmerLogUtils.logInfo("No test case with Passed status found in a Project")
				}

			}else{
				MesmerLogUtils.markFailed("Could not stopped test case")
			}

		}else{
			MesmerLogUtils.markFailed("[DATA ISSUE] : No in-progress test case found in a Project")
		}
	}else{
		MesmerLogUtils.markFailed("[DATA ISSUE] : Project   " +  ProjectName  + "  does not exist" )
	}
}catch(Exception e){

	e.printStackTrace()

}finally{
	if(WebUI.waitForElementPresent(btnReplay, 10)==true){
		WebUI.click(btnReplay)

	}else{
		MesmerLogUtils.markFailed("FAILED: Unable to close replay dialogue")
	}
}