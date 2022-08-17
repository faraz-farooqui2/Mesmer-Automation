import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

// MR-Device Manager-01 | Verify user can select Devices option from settings

try{

	TestObject settingsOption = findTestObject('Object Repository/OR_Common/Option_Settings')
	TestObject verifyDeviceManagerPage = findTestObject('Object Repository/OR_Common/page_verifyDeviceManager')
	TestObject settingsDropDownVerification = findTestObject('Object Repository/OR_Common/dropdown_verifySettings')
	TestObject settingsDevicesOption = findTestObject('Object Repository/OR_Common/Option_Settings--Devices')
	TestObject settingsTestDataOption = findTestObject('Object Repository/OR_Common/Option_Settings--TestData')
	TestObject settingsJiraIntegrationOption = findTestObject('Object Repository/OR_Common/Option_Settings--JiraIntegration')

	if(WebUI.waitForElementVisible(settingsOption,5)){
		MesmerLogUtils.logInfo("Setting option is displayed. Clicking Settings")
		WebUI.click(settingsOption)
		MesmerLogUtils.logInfo("Settings option is clicked")

		if(WebUI.verifyElementVisible(settingsDevicesOption) && WebUI.verifyElementVisible(settingsTestDataOption) && WebUI.verifyElementVisible(settingsJiraIntegrationOption)){
			MesmerLogUtils.logInfo("Devices, Test Data and JiraIntegration option are displayed in dropdown")
			MesmerLogUtils.logInfo("Clicking Devices option")
			WebUI.click(settingsDevicesOption)
			MesmerLogUtils.logInfo("Devices option is clicked")

			if(WebUI.verifyElementVisible(verifyDeviceManagerPage)==true){
				MesmerLogUtils.logInfo("User navigated to Device Manager page")
			}
			else{
				MesmerLogUtils.logInfo("User is not navigated to Device Manage page")
			}

		}else{
			MesmerLogUtils.logInfo("Devices, Test Data and JiraIntegration option are not displayed in dropdown")
		}

	}
	else{
		MesmerLogUtils.logInfo("Settings option is not displayed")
	}

}catch(Exception e){
	e.printStackTrace()
}