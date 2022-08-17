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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
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


try{
	CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(3)
	//boolean checkNoRecordAvailable =
	if(WebUI.waitForElementVisible(showStatus, 10)==true){
		WebUI.click(showStatus)
		WebUI.delay(1)
		
		if(WebUI.waitForElementVisible(inUseOption, 10)==true){
			WebUI.click(inUseOption)
			WebUI.delay(1)
			if (WebUI.waitForElementVisible(noRecordAvailable,3) == true) {
				//call create new test case
				KeywordUtil.markWarning("MESSAGE: No In Use device available in Record")
				
			}
			
			else if(WebUI.waitForElementVisible(firstInUseDevice, 2)==true){
				
				List<WebElement> numberOfInUseDevices = driver.findElements(By.xpath("//div[contains(@class,'statusText')]"))
				
				int countOfInUseDevices = numberOfInUseDevices.size()
				System.err.println("Count of In Use devices = " + countOfInUseDevices)
				//println(WebUI.getText(infoFirstInUseDevice))
				for(int i=0; i<countOfInUseDevices;i++){
					System.err.println("Device text is " + numberOfInUseDevices.get(i).getText())
					if(numberOfInUseDevices.get(i).getText().contains("In Use")){
						KeywordUtil.markPassed("PASSED: Device number "+ i + " is IN USE device")
					}
				}
				
			}
			
			else{
				KeywordUtil.markWarning("WARNING: In Use devices are not found")
			}
			
			
		}
		else{
			System.err.println("In Use Option is not displayed")
		}
		
		
	}
	else{
		System.err.println("Show Status option is not displayed")
	}
	
	
	
}catch(Exception e){
	println(e.printStackTrace())
}
finally{
//	WebUI.click(showStatus)
//	WebUI.delay(1)
//	WebUI.click(allOption)
	WebUI.refresh()
}






