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


try{
	CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
	WebDriver driver = DriverFactory.getWebDriver()
	
	
	WebUI.click(optionShowStatus)
	WebUI.delay(1)
	if(WebUI.verifyElementVisible(selectAll)==true){
		WebUI.click(selectAll)
		WebUI.delay(2)
		if(WebUI.verifyElementVisible(checkAllSelected)==true){
			if(WebUI.waitForElementVisible(listNoRecordAvailable, 5, FailureHandling.CONTINUE_ON_FAILURE) == true)
			{
				KeywordUtil.markWarning("Warning: No Device is available")
			}
			else{
				List<WebElement> numberOfALLDevicess = driver.findElements(By.xpath("//div[contains(@class,'list')]/div"))
				
				int countOfAllDevices = numberOfALLDevicess.size()
				System.err.println("Count of ALL devices = " + countOfAllDevices)
				
				KeywordUtil.markPassed('SUCCESS: All devices option is selected successfully and count of all devices is = ' + countOfAllDevices)
			}
			
			
			KeywordUtil.markPassed('SUCCESS: All option is selected successfully')
		}
		else{
			KeywordUtil.markFailed('FAILED: All option is NOT selected successfully')
		}
		
	}
		
		
		WebUI.click(optionShowStatus)
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
	
	
}catch(Exception e){
	println(e.stackTrace)
}
finally{
		WebUI.refresh()
}
	
	
		

