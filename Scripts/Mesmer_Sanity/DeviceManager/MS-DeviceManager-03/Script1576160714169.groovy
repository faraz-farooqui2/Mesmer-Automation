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

//MS-Device Manager-03 | Verify that user can reboot the devices only those which are connected with server. (i.e State= Ready, InUse)
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebDriver driver = DriverFactory.getWebDriver()

CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

WebUI.waitForPageLoad(10)
WebUI.delay(2)


try{
	
	//Ready Devices List
	String ReadyDevices = listReadyDevices.findPropertyValue('xpath').toString()
	List<WebElement> listOfReadyDevices = driver.findElements(By.xpath(ReadyDevices))
	
	String ProvisionedDevices = listProvisionedDevices.findPropertyValue('xpath').toString()
	List<WebElement> listOfProvisionedDevices = driver.findElements(By.xpath(ProvisionedDevices))
	
	String BrokenDevices = listBrokenDevices.findPropertyValue('xpath').toString()
	List<WebElement> listOfBrokenDevices = driver.findElements(By.xpath(BrokenDevices))
	
	String InUseDevices = listInUseDevices.findPropertyValue('xpath').toString()
	List<WebElement> listOfInUseDevices = driver.findElements(By.xpath(InUseDevices))
	
	String btnEnabledforReady = btnRebootReadyEnabled.findPropertyValue('xpath').toString()
	List<WebElement> listOfRebootReadyEnabledBtn = driver.findElements(By.xpath(btnEnabledforReady))

	String btnDisabledforReady = btnRebootReadyDisabled.findPropertyValue('xpath').toString()
	List<WebElement> listOfRebootReadyDisabledBtn = driver.findElements(By.xpath(btnDisabledforReady))
	
	String btnEnabledforProvisioned = btnRebootProvisionedEnabled.findPropertyValue('xpath').toString()
	List<WebElement> listOfRebootProvisionedEnabledBtn = driver.findElements(By.xpath(btnEnabledforProvisioned))

	String btnDisabledforProvisioned = btnRebootProvisionedDisabled.findPropertyValue('xpath').toString()
	List<WebElement> listOfRebootProvisionedDisabledBtn = driver.findElements(By.xpath(btnDisabledforProvisioned))

	String btnEnabledforBroken = btnRebootBrokenEnabled.findPropertyValue('xpath').toString()
	List<WebElement> listOfRebootBrokenEnabledBtn = driver.findElements(By.xpath(btnEnabledforBroken))

	String btnDisabledforBroken = btnRebootBrokenDisabled.findPropertyValue('xpath').toString()
	List<WebElement> listOfRebootBrokenDisabledBtn = driver.findElements(By.xpath(btnDisabledforBroken))
	
	String btnEnabledforInUse = btnRebootInUseEnabled.findPropertyValue('xpath').toString()
	List<WebElement> listOfRebootInUseEnabledBtn = driver.findElements(By.xpath(btnEnabledforInUse))

	String btnDisabledforInUse = btnRebootInUseDisabled.findPropertyValue('xpath').toString()
	List<WebElement> listOfRebootInUseDisabledBtn = driver.findElements(By.xpath(btnDisabledforInUse))
		
	//Check for Ready devices
	if(listOfReadyDevices.size()>0){
		MesmerLogUtils.logInfo("Total " + listOfReadyDevices.size() + " Ready devices are available")
		
		if(listOfRebootReadyEnabledBtn.size() >0 && listOfRebootReadyDisabledBtn.size() == 0){
			MesmerLogUtils.markPassed("Reboot buttons are Enabled for Ready devices")
			
			if(isDeviceRebooted(listOfRebootReadyEnabledBtn)){
				MesmerLogUtils.markPassed("Ready Device Rebooted Successfully")
			}
			else{
				MesmerLogUtils.markFailed("Ready device is failed to Reboot")
			}
			
		}
		else{
			MesmerLogUtils.markFailed("Reboot buttons are Disabled for Ready devices")
		}
		
			
	}else{
		MesmerLogUtils.markWarning("Ready devices are not available")
	}
	
	
	//Check for InUse devices
	if(listOfInUseDevices.size() > 0){
		MesmerLogUtils.logInfo("Total " + listOfInUseDevices.size() + " In Use devices are available")
		
		if(listOfRebootInUseEnabledBtn.size() >0 && listOfRebootInUseDisabledBtn.size() == 0){
			MesmerLogUtils.markPassed("Reboot buttons are Enabled for In Use devices")
			
		}
		else{
			MesmerLogUtils.markFailed("Reboot buttons are Disabled for In Use devices")
		}
		
			
	}else{
		MesmerLogUtils.logInfo("In Use devices are not available")
	}
	
	
	//Check for Broken devices
	if(listOfBrokenDevices.size()>0){
		MesmerLogUtils.logInfo("Total " + listOfBrokenDevices.size() + " Broken devices are available")
		
		if(listOfRebootBrokenDisabledBtn.size() >0 && listOfRebootBrokenEnabledBtn.size() == 0){
			MesmerLogUtils.markPassed("Reboot buttons are Disabled for Broken devices")
			
		}
		else{
			MesmerLogUtils.markFailed("Reboot buttons are Enabled for Broken devices")
		}
		
			
	}else{
		MesmerLogUtils.markWarning("Broken devices are not available")
	}
	
	//Check for Provisioned devices
	if(listOfProvisionedDevices.size()>0){
		MesmerLogUtils.logInfo("Total " + listOfProvisionedDevices.size() + " Provisioned devices are available")
		
		if(listOfRebootProvisionedDisabledBtn.size() >0 && listOfRebootProvisionedEnabledBtn.size() == 0){
			MesmerLogUtils.markPassed("Reboot buttons are Disabled for Provisioned devices")
			
		}
		else{
			MesmerLogUtils.markFailed("Reboot buttons are Enabled for Provisioned devices")
		}
		
			
	}else{
		MesmerLogUtils.markWarning("Provisioned devices are not available")
	}
	
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	
}

boolean isDeviceRebooted(List<WebElement> deviceRebootBtn){
	boolean result = false
	
	deviceRebootBtn.get(0).click()
	
	if(WebUI.waitForElementVisible(rebootConfirmationWindows, 5)==true){
		MesmerLogUtils.logInfo("Reboot Confirmation Window is displayed")
			if(WebUI.waitForElementVisible(continueButtonOnConfirmationWindow, 3)==true){
				MesmerLogUtils.logInfo("Continue button is displayed on Reboot Confirmation Window")
				MesmerLogUtils.logInfo("Clicking Continue button on Reboot Confirmation Window")
				WebUI.click(continueButtonOnConfirmationWindow)
				MesmerLogUtils.logInfo("Continue button is clicked on Reboot Confirmation Window")
	
				if(WebUI.waitForElementVisible(notificationRebootingDevice, 10)==true){
					MesmerLogUtils.logInfo("Rebooting Device notification is displayed")
					MesmerLogUtils.markPassed("Device Rebooted Successfully")
					result = true
	
				}
				else{
					MesmerLogUtils.markFailed("Device Rebooting notification is not displayed")
				}
			}
			else{
				MesmerLogUtils.markFailed("Continue button is not displayed on confirmation window")
			}
		}
		else
		{
			MesmerLogUtils.markFailed("Reboot confirmation windows is not displayed")
		}
	
		return result
}



