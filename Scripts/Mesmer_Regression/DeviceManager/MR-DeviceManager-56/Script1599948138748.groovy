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

// MR-Device Selection-56 | Rerun | Verify the device with the state "ready" should be accessed within no time

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
				WebUI.delay(2)
				String listReadyDeviceXpath = findTestObject('Object Repository/OR_Replay/list_readyDeviceList').findPropertyValue('xpath').toString()
				List<WebElement> listReadyDevice = driver.findElements(By.xpath(listReadyDeviceXpath))

				if(listReadyDevice != null && listReadyDevice.size() >0){
					listReadyDevice.get(0).click()
					WebUI.delay(1)
					MesmerLogUtils.markPassed("Clicked on ready device")

					if(WebUI.waitForElementPresent(btnRun , 20) == true){

						WebUI.click(btnRun)
						MesmerLogUtils.markPassed("Click on Run Button")

						if(WebUI.waitForElementPresent(btnYes , 20) == true){
							WebUI.click(btnYes)
							WebUI.delay(2)
							MesmerLogUtils.markPassed("Click on Yes Button")

							if(WebUI.waitForElementPresent(btnAll , 20) == true){
								WebUI.click(btnAll)
								MesmerLogUtils.markPassed("Click on All Button")
								WebUI.delay(6)
//								if(ReplayController.getInstance().stopTestCaseFromTestResult()){
//									MesmerLogUtils.logInfo("Stopping replay")
//								}else {
//									MesmerLogUtils.logInfo("Could not stop replays")
//								}
							}else{
								MesmerLogUtils.markFailed("Unable to Click on All Button")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to Click on Yes Button")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to Click on Run Button")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to Click on ready device")
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
	
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}