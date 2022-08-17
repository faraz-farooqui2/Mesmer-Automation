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
//import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil
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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.DeviceManagerController

Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

try{

	CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(3)
	String test
	MesmerLogUtils.logInfo("Size of list = " + listOfStatus.size())
	
	for(int i=2; i<listOfStatus.size();i++){
		if(WebUI.waitForElementVisible(optionShowStatus, 2)){
			MesmerLogUtils.logInfo("Show Status option is displayed")
			WebUI.click(optionShowStatus)
			MesmerLogUtils.logInfo("Show Status is clicked")
			WebUI.delay(2)
			MesmerLogUtils.logInfo("List of status - " + listOfStatus.get(i))
//			test = listOfStatus.get(i)
			
			MesmerLogUtils.logInfo("XPATH == " + selectDeviceStatus(statusOfDevices, listOfStatus.get(i)))
			WebUI.click(selectDeviceStatus(statusOfDevices, listOfStatus.get(i)))
//			WebUI.click(DeviceManagerController.getInstance().selectDeviceStatus(statusOfDevices, test))
//			WebElement test = driver.findElement(By.xpath(DeviceManagerController.getInstance().selectDeviceStatus(statusOfDevices, test)))
//			MesmerLogUtils.logInfo("XPATH = " + test)
//			test.click()
			WebUI.delay(1)
//			if(DeviceManagerController.getInstance().selectDeviceStatus(statusOfDevices, listOfStatus.get(i))){
//				MesmerLogUtils.logInfo(listOfStatus.get(i))
//			}
//			
			
		}
		
		
	}
}

catch(Exception e){
	println(e.stackTrace)
}
finally{
	WebUI.refresh()
}

def selectDeviceStatus(TestObject deviceStatus, String statusOption){
	String xpStatusName
	
	MesmerLogUtils.logInfo("Status in function = " + statusOption)
	
	xpStatusName = deviceStatus.findPropertyValue('xpath')
	MesmerLogUtils.logInfo("" + xpStatusName)
	xpStatusName = xpStatusName.toString().replace('<pstatus>', statusOption)
	MesmerLogUtils.logInfo("" + xpStatusName)

	deviceStatus.findProperty('xpath').setValue(xpStatusName)
	MesmerLogUtils.logInfo("" + deviceStatus)

//	WebUI.click(deviceStatus)
	return deviceStatus
	WebUI.delay(1)
}

	//Click on Show Status dropdown
//	WebUI.click(optionShowStatus)
//	WebUI.delay(1)
//	//Check that ALL option is visible in Show Status dropdown
//	if(WebUI.waitForElementVisible(selectAll,2)==true){
//		//Click on ALL option from Show Status dropdown
//		WebUI.click(selectAll)
//		WebUI.delay(1)
//		//Check ALL is selected successfully
//		if(WebUI.waitForElementVisible(checkAllSelected,2)==true){
//
//			//Check if No Device is available on Devices page
//			if(WebUI.waitForElementVisible(listNoRecordAvailable, 5, FailureHandling.CONTINUE_ON_FAILURE) == true)
//			{
//				KeywordUtil.markWarning("Warning: No Device is available")
//			}
//			else{
//				List<WebElement> numberOfALLDevicess = driver.findElements(By.xpath("//div[contains(@class,'list')]/div"))
//
//				int countOfAllDevices = numberOfALLDevicess.size()
//				System.err.println("Count of ALL devices = " + countOfAllDevices)
//
//				KeywordUtil.markPassed('SUCCESS: All devices option is selected successfully and count of all devices is = ' + countOfAllDevices)
//			}
//
//
//			KeywordUtil.markPassed('SUCCESS: All option is selected successfully')
//		}
//		else{
//			KeywordUtil.markFailed('FAILED: All option is NOT selected successfully')
//		}
//
//	}
//

/*	WebUI.click(optionShowStatus)
	WebUI.delay(1)
	if(WebUI.verifyElementVisible(selectInUse)==true){
		WebUI.click(selectInUse)
		WebUI.delay(1)
		if(WebUI.verifyElementVisible(checkInUseSelected)==true)
		{
			println("\n In Use devices \n")
			if(WebUI.waitForElementVisible(listNoRecordAvailable,2, FailureHandling.CONTINUE_ON_FAILURE)==true)
			{
				KeywordUtil.markWarning('Warning: No Record available in In Use option')
			}
			else{
				//div[contains(@class,'statusText')]
				List<WebElement> numberOfInUseDevices = driver.findElements(By.xpath("//div[contains(@class,'statusText')]/div[contains(text(),'In Use')]"))

				int countOfInUseDevices = numberOfInUseDevices.size()
				System.err.println("Count of In Use devices = " + countOfInUseDevices)

				KeywordUtil.markPassed('SUCCESS: In Use devices option is selected successfully and count of all In Use devices is = ' + countOfInUseDevices)


			}
		}
		else{
			KeywordUtil.markFailed('FAILED: In Use option is NOT selected successfully')
		}


	}


	WebUI.click(optionShowStatus)
	WebUI.delay(1)
	if(WebUI.verifyElementVisible(selectReady)==true){
		WebUI.click(selectReady)
		WebUI.delay(1)

		if(WebUI.verifyElementVisible(checkReadySelected)==true)
		{
			if(WebUI.waitForElementVisible(listNoRecordAvailable,2, FailureHandling.CONTINUE_ON_FAILURE)==true)
			{
				KeywordUtil.markWarning('Warning: No Record available in Ready option')

			}
			else
			{
				List<WebElement> numberOfReadyDevices = driver.findElements(By.xpath("//div[contains(@class,'statusText')]/div[contains(text(),'Ready')]"))

				int countOfReadyDevices = numberOfReadyDevices.size()
				System.err.println("Count of Ready devices = " + countOfReadyDevices)

				KeywordUtil.markPassed('SUCCESS: Ready devices option is selected successfully and count of all Ready devices is = ' + countOfReadyDevices)

			}

		}
		else{
			KeywordUtil.markPassed('SUCCESS: Ready option is selected successfully')
		}
	}


	WebUI.click(optionShowStatus)
	WebUI.delay(1)
	if(WebUI.verifyElementVisible(selectBroken)==true){
		WebUI.click(selectBroken)
		WebUI.delay(2)
		if(WebUI.verifyElementVisible(checkBrokenSelected)==true){

			if(WebUI.waitForElementVisible(listNoRecordAvailable,2, FailureHandling.CONTINUE_ON_FAILURE)==true){

				KeywordUtil.markWarning('Warning: No device available in Broken option')
			}
			else{
				List<WebElement> numberOfBrokenDevices = driver.findElements(By.xpath("//div[contains(@class,'statusText')]/div[contains(text(),'Broken')]"))

				int countOfBrokenDevices = numberOfBrokenDevices.size()
				System.err.println("Count of Broken devices = " + countOfBrokenDevices)

				KeywordUtil.markPassed('SUCCESS: Broken devices option is selected successfully and count of all Broken devices is = ' + countOfBrokenDevices)

			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Broken option is NOT selected successfully')
		}
	}
	else{
		MesmerLogUtils.markFailed("Broken Option is not displayed")
	}


	WebUI.click(optionShowStatus)
	WebUI.delay(1)
	if(WebUI.verifyElementVisible(selectRebooting)==true){
		WebUI.click(selectRebooting)
		WebUI.delay(2)
		if(WebUI.verifyElementVisible(checkRebootingSelected)==true){

			if(WebUI.waitForElementVisible(listNoRecordAvailable,2, FailureHandling.CONTINUE_ON_FAILURE)==true){

				KeywordUtil.markWarning('Warning: No device available in Rebooting option')
			}
			else{
				List<WebElement> numberOfRebootingDevices = driver.findElements(By.xpath("//div[contains(@class,'statusText')]/div[contains(text(),'Rebooting')]"))

				int countOfRebootingDevices = numberOfRebootingDevices.size()
				System.err.println("Count of Rebooting devices = " + countOfRebootingDevices)

				KeywordUtil.markPassed('SUCCESS: Rebooting devices option is selected successfully and count of all Rebooting devices is = ' + countOfRebootingDevices)


			}

		}
		else{
			KeywordUtil.markFailed('FAILED: Rebooting option is NOT selected successfully')
		}

	}
	else{
		MesmerLogUtils.markFailed("Rebooting option is not displayed")
	}
	*/






