import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.DeviceManagerController

/*
 * MR-Device Selection-60 | Rerun | Verify the physical devices in the record list of devices
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{
	
	TestObject btnReplay = findTestObject('Object Repository/OR_Replay/btn_Replay')
	TestObject btnRun = findTestObject('Object Repository/OR_Replay/btn_Run')
	TestObject btnYes = findTestObject('Object Repository/OR_Replay/btn_Yes')
	TestObject queueMsg = findTestObject('Object Repository/OR_Replay/msg_queue')

	if(DeviceManagerController.getInstance().selectTestCaseFromNew()){
		String physicalDeviceXpath = findTestObject('OR_DeviceManager/deviceQueue_task').findPropertyValue('xpath').toString()
		List<WebElement> physicalDevice = driver.findElements(By.xpath(physicalDeviceXpath))

		if(WebUI.waitForElementPresent(btnReplay , 120)==true){
			WebUI.click(btnReplay)
			MesmerLogUtils.markPassed("Click On Re-run")

			if(physicalDevice.size() > 0){
				MesmerLogUtils.markPassed("Physical Device found")

				if(WebUI.waitForElementClickable(btnRun , 20) == true){
					WebUI.click(btnRun)
					MesmerLogUtils.markPassed("Click on Run Button")

					if(WebUI.waitForElementPresent(btnYes , 20) == true){
						WebUI.click(btnYes)
						MesmerLogUtils.markPassed("Click on Yes Button")

						if(WebUI.waitForElementPresent(queueMsg , 30) == true){
							MesmerLogUtils.markPassed("Test cases lined up in queue")

							if(WebUI.waitForElementNotPresent(queueMsg , 30) == true){
								MesmerLogUtils.markPassed("Queue msg pop up disappeared")

							}else{
								MesmerLogUtils.logInfo("Queue msg pop up not disappeared")
							}
						}else{
							MesmerLogUtils.logInfo("Test cases not lined up in a queue")
						}
					}else{
						MesmerLogUtils.logInfo("Unable to click on Yes button")
					}
				}else{
					MesmerLogUtils.logInfo("Unable to click on Run button")
				}
			}else{
				MesmerLogUtils.markPassed("No Physical Device found")
			}
		}else{

			MesmerLogUtils.logInfo("Unable to click on replay button")
		}
	}else{
		MesmerLogUtils.logInfo("selectTestCaseFromNew method failed")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{

}
