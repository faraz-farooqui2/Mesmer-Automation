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

// MR-Device Manager-05 | Verify user can see multiple columns on Device Manager table
CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(2)

try{
	if(WebUI.waitForElementPresent(headerStatus, 5)){
	}else{
		MesmerLogUtils.markFailed("Header Status not found")
	}
	if(WebUI.waitForElementPresent(headerPlatform, 5)){
	}else{
		MesmerLogUtils.markFailed("Header Platform not found")
	}
	if(WebUI.waitForElementPresent(headerDevice, 5)){
	}else{
		MesmerLogUtils.markFailed("Header Device not found")
	}
	if(WebUI.waitForElementPresent(headerType, 5)){
	}else{
		MesmerLogUtils.markFailed("Header Type not found")
	}
	if(WebUI.waitForElementPresent(headerResolution, 5)){
	}else{
		MesmerLogUtils.markFailed("Header Resolution not found")
	}
	if(WebUI.waitForElementPresent(headerQueue, 5)){
	}else{
		MesmerLogUtils.markFailed("Header Queue not found")
	}
	if(WebUI.waitForElementPresent(headerReboot, 5)){
	}else{
		MesmerLogUtils.markFailed("Header Reboot not found")
	}
}catch(Exception e){
	e.printStackTrace()
}
finally{

}
