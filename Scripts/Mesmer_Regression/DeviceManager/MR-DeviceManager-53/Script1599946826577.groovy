import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

// MR-Device Selection-53 | Rerun | Verify user should be able to see if a device is Ready to be use

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	//1. Click on any test case/tile with status New/Recommended test case
	if(WebUI.waitForElementPresent(btnNew, 20)== true){
		WebUI.click(btnNew)
		WebUI.delay(2)
		MesmerLogUtils.markPassed("Clicked on New filter")

		String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
		List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

		MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
		if(titleStreamList.size() > 0){


			//2. Click on 'Replay' button appearing in the top right corner
			if(WebUI.waitForElementPresent(btnReplay , 120)==true){
				WebUI.click(btnReplay)
				MesmerLogUtils.markPassed("Click On Replay button")
				if(WebUI.waitForElementPresent(listReadyDevices, 10)==true){
					MesmerLogUtils.markPassed("Ready device found")

				}else{
					MesmerLogUtils.logInfo("No Ready device found")
				}
			}else{
				MesmerLogUtils.markFailed("Unable to Click on replay button")
			}
		}else{
			MesmerLogUtils.markFailed("No test case found in new filter ")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on New filter")
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{

	WebUI.click(btnReplay)
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}