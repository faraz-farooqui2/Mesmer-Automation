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

// MR-Device Manager-04 | Verify user can select Show Status drop list along with all devices statuse



CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.waitForPageLoad(10)
WebUI.delay(2)


try{


	MesmerLogUtils.logInfo("Checking Broken Devices in Show Filter")

	if(WebUI.waitForElementPresent(btnShowStatus, 10)){
		WebUI.click(btnShowStatus)

		if(WebUI.waitForElementPresent(btnBroken, 10)){
			WebUI.click(btnBroken)

			if(WebUI.waitForElementPresent(listDevicesShowFilter, 5)){

			}else if (WebUI.waitForElementPresent(listDevices, 5)){
				//Broken Devices List
				String BrokenDevices = listBrokenDevices.findPropertyValue('xpath').toString()
				List<WebElement> listOfBrokenDevices = driver.findElements(By.xpath(BrokenDevices))

				MesmerLogUtils.logInfo("Number of Broken Devices"  + " : " + listOfBrokenDevices.size())
			}else{
				MesmerLogUtils.logInfo("No device Available")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on show status")
		}
	}

	MesmerLogUtils.logInfo("Checking Configuring Devices in Show Filter")

	if(WebUI.waitForElementPresent(btnShowStatus, 10)){
		WebUI.click(btnShowStatus)

		if(WebUI.waitForElementPresent(btnConfiguring, 10)){
			WebUI.click(btnConfiguring)

			if(WebUI.waitForElementPresent(listDevicesShowFilter, 5)){

			}else if (WebUI.waitForElementPresent(listDevices, 5)){
				//Broken Devices List
				String ConfiguringnDevices = listConfiguringDevices.findPropertyValue('xpath').toString()
				List<WebElement> listOfConfiguringDevices = driver.findElements(By.xpath(ConfiguringnDevices))

				MesmerLogUtils.logInfo("Number of Configuring Devices"  + " : " + listOfConfiguringDevices.size())
			}else{
				MesmerLogUtils.logInfo("No device Available")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on show status")
		}
	}

	MesmerLogUtils.logInfo("Checking InUse Devices in Show Filter")
	if(WebUI.waitForElementPresent(btnShowStatus, 10)){
		WebUI.click(btnShowStatus)

		if(WebUI.waitForElementPresent(btnInUse, 10)){
			WebUI.click(btnInUse)

			if(WebUI.waitForElementPresent(listDevicesShowFilter, 5)){

			}else if (WebUI.waitForElementPresent(listDevices, 5)){

				//In-Use Devices List
				String InUseDevices = listInUseDevices.findPropertyValue('xpath').toString()
				List<WebElement> listOfInUseDevices = driver.findElements(By.xpath(InUseDevices))

				MesmerLogUtils.logInfo("Number of InUse Devices"  + " : " + listOfInUseDevices.size())
			}else{
				MesmerLogUtils.logInfo("No device Available")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on show status")
		}
	}


	MesmerLogUtils.logInfo("Checking Provisioned Devices in Show Filter")
	if(WebUI.waitForElementPresent(btnShowStatus, 10)){
		WebUI.click(btnShowStatus)

		if(WebUI.waitForElementPresent(btnProvisioned, 10)){
			WebUI.click(btnProvisioned)

			if(WebUI.waitForElementPresent(listDevicesShowFilter, 5)){

			}else if (WebUI.waitForElementPresent(listDevices, 5)){

				//Provisioned Devices List
				String ProvisionedDevices = listProvisionedDevices.findPropertyValue('xpath').toString()
				List<WebElement> listOfProvisionedDevices = driver.findElements(By.xpath(ProvisionedDevices))

				MesmerLogUtils.logInfo("Number of Provisioned Devices"  + " : " + listOfProvisionedDevices.size())
			}else{
				MesmerLogUtils.logInfo("No device Available")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on show status")
		}
	}

	MesmerLogUtils.logInfo("Checking Ready Devices in Show Filter")
	if(WebUI.waitForElementPresent(btnShowStatus, 10)){
		WebUI.click(btnShowStatus)

		if(WebUI.waitForElementPresent(bnReady, 10)){
			WebUI.click(bnReady)

			if(WebUI.waitForElementPresent(listDevicesShowFilter, 5)){

			}else if (WebUI.waitForElementPresent(listDevices, 5)){
				//Ready Devices List
				String ReadyDevices = listReadyDevices.findPropertyValue('xpath').toString()
				List<WebElement> listOfReadyDevices = driver.findElements(By.xpath(ReadyDevices))

				MesmerLogUtils.logInfo("Number of Ready Devices"  + " : " + listOfReadyDevices.size())
			}else{
				MesmerLogUtils.logInfo("No device Available")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on show status")
		}
	}

	MesmerLogUtils.logInfo("Checking Rebooting Devices in Show Filter")
	if(WebUI.waitForElementPresent(btnShowStatus, 10)){
		WebUI.click(btnShowStatus)

		if(WebUI.waitForElementPresent(btnRebooting, 10)){
			WebUI.click(btnRebooting)

			if(WebUI.waitForElementPresent(listDevicesShowFilter, 5)){

			}else if (WebUI.waitForElementPresent(listDevices, 5)){
				
				String RebootingDevices = listRebootingDevices.findPropertyValue('xpath').toString()
				List<WebElement> listOfRebootingDevices = driver.findElements(By.xpath(RebootingDevices))

				MesmerLogUtils.logInfo("Number of Rebooting Devices"  + " : " + listOfRebootingDevices.size())
			}else{
				MesmerLogUtils.logInfo("No device Available")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on show status")
		}
	}
	
	MesmerLogUtils.logInfo("Checking Refreshing Devices in Show Filter")
	if(WebUI.waitForElementPresent(btnShowStatus, 10)){
		WebUI.click(btnShowStatus)

		if(WebUI.waitForElementPresent(btnRefreshing, 10)){
			WebUI.click(btnRefreshing)

			if(WebUI.waitForElementPresent(listDevicesShowFilter, 5)){

			}else if (WebUI.waitForElementPresent(listDevices, 5)){
				//Ready Devices List
				String RefreshingDevices = listRefreshingDevices.findPropertyValue('xpath').toString()
				List<WebElement> listOfRefreshingDevices = driver.findElements(By.xpath(RefreshingDevices))

				MesmerLogUtils.logInfo("Number of Refreshing Devices"  + " : " + listOfRefreshingDevices.size())
			}else{
				MesmerLogUtils.logInfo("No device Available")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on show status")
		}
	}
	
	MesmerLogUtils.logInfo("Checking Unavailable Devices in Show Filter")
	if(WebUI.waitForElementPresent(btnShowStatus, 10)){
		WebUI.click(btnShowStatus)

		if(WebUI.waitForElementPresent(btnUnavailable, 10)){
			WebUI.click(btnUnavailable)

			if(WebUI.waitForElementPresent(listDevicesShowFilter, 5)){

			}else if (WebUI.waitForElementPresent(listDevices, 5)){
				
				String UnavailableDevices = listUnavailableDevices.findPropertyValue('xpath').toString()
				List<WebElement> listOfUnavailableDevices = driver.findElements(By.xpath(UnavailableDevices))

				MesmerLogUtils.logInfo("Number of Unavailable Devices"  + " : " + listOfUnavailableDevices.size())
			}else{
				MesmerLogUtils.logInfo("No device Available")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on show status")
		}
	}

	MesmerLogUtils.logInfo("Checking All Devices in Show Filter")
	if(WebUI.waitForElementPresent(btnShowStatus, 10)){
		WebUI.click(btnShowStatus)

		if(WebUI.waitForElementPresent(btnAll, 10)){
			WebUI.click(btnAll)

			if(WebUI.waitForElementPresent(listDevicesShowFilter, 5)){

			}else if (WebUI.waitForElementPresent(listDevices, 5)){
				
				String AllDevices = listAllDevices.findPropertyValue('xpath').toString()
				List<WebElement> listOfAllDevices = driver.findElements(By.xpath(AllDevices))

				MesmerLogUtils.logInfo("Number of All Devices"  + " : " + listOfAllDevices.size())
			}else{
				MesmerLogUtils.logInfo("No device Available")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on show status")
		}
	}


}
catch(Exception e){
	e.printStackTrace()
}
finally{

}
