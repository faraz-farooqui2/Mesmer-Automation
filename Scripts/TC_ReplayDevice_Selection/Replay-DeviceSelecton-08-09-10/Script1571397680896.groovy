import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory



WebUI.callTestCase(findTestCase('TC_Replay/MS-Replay-02'), [('ProjectName') : ProjectName, ('BuildNumber') : BuildNumber , ('platformName') : platformName
	, ('Device') : Device, ('xpathString') : '', ('projName') : findTestObject('OR_CreateNewTestCase/project_selection')
	, ('replayIcon') : findTestObject('OR_Replay/btn_Replay'), ('selectDevicesPopup') : findTestObject('OR_Replay/popup_SelectDevices')
	, ('textNoDeviceAvailable') : findTestObject('OR_Replay/text_NoDeviceAvailable'), ('tickIcon') : findTestObject('OR_Replay/ClickON_DeviceTickIcon')
	, ('btnRun') : findTestObject('OR_Replay/btn_Run'), ('runConfirmationDialog') : findTestObject('OR_Replay/runConfirmation_Dialog')
	, ('btnYes') : findTestObject('OR_Replay/btn_Yes'), ('textStartExecution') : findTestObject('OR_Replay/div_Starting execution')
	, ('textInProgress') : findTestObject('OR_Replay/span_InProgress')], FailureHandling.OPTIONAL)

try{
	if (WebUI.waitForElementPresent(runAllTestCase, 10)== true){
		WebUI.click(runAllTestCase)

		WebDriver driver = DriverFactory.getWebDriver()

		List<WebElement> checkIconInUseDevices = driver.findElements(By.xpath("//div[@class='deviceList ng-star-inserted']/div[@class = 'device inUse selectable ng-star-inserted']/div[@class='checkIcon']"))

		//Number of InUse Devices
		int lengthCheckIcon = checkIconInUseDevices.size()
		println("Number of In Use Devices = " + lengthCheckIcon)

		//Clicking InUse Device

		if (lengthCheckIcon >= 1 ){
			checkIconInUseDevices.get(0).click()

			if (WebUI.waitForElementPresent(checkIconSelected, 10)==true){
				KeywordUtil.markPassed("PASSED: In Use device is selected")

			}else{
				KeywordUtil.markFailed("FAILED: In Use Device Is Not Clicked")

			}
		}
		else{
			KeywordUtil.markFailed("FAILED: No InUse Device Found")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Run All Test Case Pop Up Not Appear")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{

}

