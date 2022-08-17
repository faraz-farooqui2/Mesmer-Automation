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
import controllers.CreateTestController

/*
 * MR-Record -24 | Verify gestures button contains all 3 screen gestures and working fine
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()

try{
	WebElement searchedReadyDevice = Utility.selectDevice(Device.toString())
	if(searchedReadyDevice != null){
		searchedReadyDevice.click()
		if(CreateTestController.getInstance().deviceChecks()){
			WebUI.delay(15)
			startRecordingAndPerformActions()

		}else{
			MesmerLogUtils.markFailed("Device checks failed")
		}

	}else{
		MesmerLogUtils.markFailed("Unable to click on ready device")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

def startRecordingAndPerformActions(){
	WebDriver driver = DriverFactory.getWebDriver()
	String iconGestureXpath = findTestObject('Object Repository/OR_CreateNewTestCase/icon_Gestures').findPropertyValue('xpath').toString()
	WebElement iconGestureHover = driver.findElement(By.xpath(iconGestureXpath))
	if(iconGestureHover != null){
		Actions builder = new Actions(driver);
		builder.moveToElement(iconGestureHover).perform()

		if(WebUI.waitForElementVisible(toolTip, 30)){
			WebUI.delay(2)
			if(WebUI.waitForElementVisible(btnLinkOk, 30)){
				WebUI.click(btnLinkOk)
				WebUI.delay(2)
				WebUI.click(iconGestures)
				WebUI.delay(2)

				if(WebUI.waitForElementPresent(defaultTapType, 10)){
					WebUI.click(iconTap)
					
					if(WebUI.waitForElementPresent(divDevice, 20)){
						WebUI.clickOffset(divDevice, 10, 10)
						WebUI.delay(10)
					}else{

						MesmerLogUtils.markFailed("Unable to single tap of device div")
					}
				}else{
					MesmerLogUtils.markFailed("Default Tap is not single click")
				}
			}
			else{
				MesmerLogUtils.markWarning("Button link ok not found")
			}
		}
		else{
			MesmerLogUtils.markWarning("Tooltip Test with gestures not appeared")
		}
	}
	else{
		MesmerLogUtils.markFailed("Unable to mouse hover on Tap icon")
	}
}

