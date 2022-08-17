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
import org.openqa.selenium.JavascriptExecutor;

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
JavascriptExecutor js = (JavascriptExecutor) driver;
try{

	WebElement searchedReadyDevice = Utility.selectDevice(Device.toString())
	if(searchedReadyDevice != null){
		searchedReadyDevice.click()
		if(CreateTestController.getInstance().deviceChecks()){
			if(CreateTestController.getInstance().waitForRecordingStarts()){
				String deviceLogsIconXpath = findTestObject('Object Repository/OR_CreateNewTestCase/icon_logs').findPropertyValue('xpath').toString()
				WebElement deviceLogsIcon = driver.findElement(By.xpath(deviceLogsIconXpath))
				deviceLogsIcon.click()
				WebUI.delay(60)
				String deviceLogListXPath = findTestObject('Object Repository/OR_CreateNewTestCase/logs_deviceLogsList').findPropertyValue('xpath').toString()
				List<WebElement> deviceLogList = driver.findElements(By.xpath(deviceLogListXPath))
				if(deviceLogList != null && deviceLogList.size() > 50){
					
				js.executeScript("arguments[0].scrollIntoView();", deviceLogList.get(deviceLogList.size()-1));

				}else{
					MesmerLogUtils.markFailed("Log size is not greater than 50 lines")
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


