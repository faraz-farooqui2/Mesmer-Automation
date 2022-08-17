import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

//MS-Test Details-01 | Verify that when user clicks on a tile/test case from Test Result page a Test Detail page will open (Android project).

//1. Click on project list from top left corner.
//2. Select the Android project from the list




try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		//3. Click on a Tile/Test case to open.
		WebDriver driver = DriverFactory.getWebDriver()
		String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
		List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

		MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())

		if(titleStreamList.size() > 0){
			if(WebUI.waitForElementPresent(titleStreamList, 20)== true){

				titleStreamList.get(0).click()

				MesmerLogUtils.markPassed("Test case opens")

				if(WebUI.waitForElementPresent(verifyTestCaseTitle, 20)== true){

					MesmerLogUtils.markPassed("User on test detail page")
				}else{
					MesmerLogUtils.markFailed("User is not on test detail page")
				}
			}else{
				MesmerLogUtils.markFailed("Test Case Not Open")
			}
		}else{
			MesmerLogUtils.markFailed("[DATA ISSUE] : No test case found in this project")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}