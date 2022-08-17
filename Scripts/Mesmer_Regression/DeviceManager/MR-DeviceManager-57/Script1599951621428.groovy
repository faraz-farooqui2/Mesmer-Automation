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

// MR-Device Selection-57 | Rerun | Verify the device with the status "In Use" could be accessed by any other user

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	//1. Click on any test case/tile with status New/Recommended test case
//	if(WebUI.waitForElementPresent(btnNew, 20)== true){
//		WebUI.click(btnNew)
//		WebUI.delay(2)
//		MesmerLogUtils.markPassed("Clicked on New filter")
//
//		String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
//		List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))
//
//		MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
//		if(titleStreamList.size() > 0){


			//2. Click on 'Replay' button appearing in the top right corner
			if(WebUI.waitForElementPresent(btnReplay , 120)==true){
				WebUI.click(btnReplay)
				MesmerLogUtils.markPassed("Click On Replay button")
				WebUI.delay(2)
				String listInUseDeviceXpath = findTestObject('Object Repository/OR_Replay/list_DeviceInUse').findPropertyValue('xpath').toString()
				List<WebElement> listInUseDevice = driver.findElements(By.xpath(listInUseDeviceXpath))

				if(listInUseDevice != null && listInUseDevice.size() >0){
					listInUseDevice.get(0).click()
					WebUI.delay(1)
					MesmerLogUtils.markPassed("Clicked on InUse device")

					if(WebUI.waitForElementPresent(btnRun , 20) == true){

						WebUI.click(btnRun)
						MesmerLogUtils.markPassed("Click on Run Button")

						if(WebUI.waitForElementPresent(btnYes , 20) == true){
							WebUI.click(btnYes)

							MesmerLogUtils.markPassed("Click on Yes Button")

							if(WebUI.waitForElementPresent(msg_queue , 20) == true){

								MesmerLogUtils.markPassed("Job lined up in queue msg appears")
							
								}else{
								MesmerLogUtils.markFailed("Job lined up in queue msg not appears")
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
//		}else{
//			MesmerLogUtils.markFailed("No test case found in new filter ")
//		}
//	}else{
//		MesmerLogUtils.markFailed("Unable to click on New filter")
//	}
}
catch(Exception e){
	e.printStackTrace()
}finally{

	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}