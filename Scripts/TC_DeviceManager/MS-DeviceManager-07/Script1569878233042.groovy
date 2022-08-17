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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

//Assumption
//Call Crawl/Replay/Record Test Case before executing this test cases

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.waitForPageLoad(20)
WebUI.delay(3)

	List<WebElement> enabledRebootButton = driver.findElements(By.xpath("//div[@class='reboot enable']")) 
	int numberOfRebootEnabledDevices = enabledRebootButton.size()
	
	if(numberOfRebootEnabledDevices > 0){
		enabledRebootButton.get(0).click()
		
		WebUI.delay(1)
		if(WebUI.waitForElementVisible(rebootConfirmationWindows, 5)==true){
			if(WebUI.waitForElementVisible(continueButtonOnConfirmationWindow, 3)==true){
				WebUI.click(continueButtonOnConfirmationWindow)
				
				if(WebUI.waitForElementVisible(notificationRebooting1stDevice, 10)==true){
					KeywordUtil.markPassed("PASSED: Device Rebooted Successfully")
					
					WebUI.waitForElementNotVisible(notificationDeviceRebooted, 10)
					WebUI.delay(3)
				}
				else{
					KeywordUtil.markFailed("FAILED: Device Rebooting notification is not displayed")
				}
			}
			else{
				KeywordUtil.markFailed("FAILED: Continue button is not displayed on confirmation window")
			}
		}
		else
		{
			KeywordUtil.markFailed("FAILED: Reboot confirmation windows is not displayed")
		}
		
	}
//	WebUI.click(button1stDeviceReboot)



else if(WebUI.waitForElementVisible(noDeviceAvailable, 15)==true){
	KeywordUtil.logInfo("No Device available")
	
}

else{
	
	KeywordUtil.markFailed("FAILED: Reboot button of device is not found")
}



