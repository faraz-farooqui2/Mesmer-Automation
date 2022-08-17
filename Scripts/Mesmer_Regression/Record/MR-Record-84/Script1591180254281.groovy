import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import org.openqa.selenium.Keys
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.interactions.Actions
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.CreateTestController

/*
 * MS-Create a new Test Case-01 | Verify that user should be able to record an iOS test case (Physical/Simulator)
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	WebElement searchedReadyDevice = Utility.selectDevice(Device.toString())
	if(searchedReadyDevice != null){
		searchedReadyDevice.click()
		if(CreateTestController.getInstance().deviceChecks()){
			if(CreateTestController.getInstance().waitForRecordingStarts()){
				String deviceLogsIconXpath = findTestObject('Object Repository/OR_CreateNewTestCase/icon_logs').findPropertyValue('xpath').toString()
				WebElement deviceLogsIcon = driver.findElement(By.xpath(deviceLogsIconXpath))
				deviceLogsIcon.click()

				if(WebUI.waitForElementPresent(btnSetting, 20)){
					WebUI.click(btnSetting)

					if(CreateTestController.getInstance().verifyOptionsAvailableOnSetting()){

						if(WebUI.waitForElementPresent(btnFilter, 20)){
							WebUI.click(btnFilter)

							if(WebUI.waitForElementPresent(filterField, 20)){

								WebUI.setText(findTestObject('Object Repository/OR_CreateNewTestCase/input_filterBox'),
										filter)
								WebUI.sendKeys(findTestObject('Object Repository/OR_CreateNewTestCase/input_filterBox'),
										Keys.chord(Keys.ENTER))

								WebUI.delay(5)

								String deviceLogsListXpath = findTestObject('Object Repository/OR_CreateNewTestCase/list_deviceLogs').findPropertyValue('xpath').toString()
								List<WebElement> deviceLogsList = driver.findElements(By.xpath(deviceLogsListXpath))
								for (WebElement webElement : deviceLogsList) {
									MesmerLogUtils.logInfo("List" + " " + webElement)
								}

								if(WebUI.waitForElementPresent(btnCloseFilter, 20)){
									WebUI.click(btnCloseFilter)

									if(WebUI.waitForElementNotPresent(verifyFilterPopupClosed, 5)){

									}else{
										MesmerLogUtils.markFailed("Filter pop over is not closed")
									}
								}else{
									MesmerLogUtils.markFailed("Unable to click on close filter")
								}
							}else{
								MesmerLogUtils.markFailed("Unable to apply filter")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to click on filter icon")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to verify available option for settings button")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to click on settings icon")
				}
			}else{
				MesmerLogUtils.markFailed("Recording not started yet")
			}
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
