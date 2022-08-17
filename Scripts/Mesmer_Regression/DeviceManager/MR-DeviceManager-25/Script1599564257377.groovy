import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import org.openqa.selenium.Keys
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.interactions.Actions
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.AppMapController

// MR-Device Manager-25 | Verify that Clicking view crawl link redirect user to the in-progress crawl if the selected project is a different one
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//Calling go to App Map page method
//CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	//	if(AppMapController.getInstance().clickRunCrawl(hours , minutes)){

	// Switching build
	//		CustomKeywords.'com.mesmer.Utility.selectProject'(projName2, xpString, platformName, secondBuild)

	CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()

	WebUI.delay(5)
	String statusInUseXpath = findTestObject('Object Repository/OR_DeviceManager/device_inUseStatus').findPropertyValue('xpath').toString()
	List<WebElement> statusInUse = driver.findElements(By.xpath(statusInUseXpath))

	MesmerLogUtils.logInfo("Number of Devices with InUse Status  : " +  " " + statusInUse.size().toString())

	if (statusInUse.size() > 0) {

		String mouseOverInUseXpath = findTestObject('Object Repository/OR_DeviceManager/inUse_deviceLiveTaskView').findPropertyValue('xpath').toString()
		List<WebElement> moverOverInUse = driver.findElements(By.xpath(mouseOverInUseXpath))

		//		if(moverOverInUse != null && moverOverInUse.size() > 0){

		Actions builder = new Actions(driver);
		builder.moveToElement(moverOverInUse.get(0)).perform();

		MesmerLogUtils.markPassed("Mouse Hover on a Screen ")

		if(WebUI.waitForElementPresent(btnLiveViewTask, 5)){
			WebUI.click(btnLiveViewTask)

			if(WebUI.waitForElementPresent(verifyappMapLiveViewTask, 5)){
				MesmerLogUtils.markPassed("User navigates to App Map")
			}else if(WebUI.waitForElementPresent(verifyreplayLiveViewTask, 5)){
				MesmerLogUtils.markPassed("User navigates to Replay")
			}else if (WebUI.waitForElementPresent(verifyrecordLiveViewTask, 5)){
				MesmerLogUtils.markPassed("User navigates to Record")
			}else{
				MesmerLogUtils.markFailed("User could not navigate to any screen")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on Live Task View")
		}
		//		}else{
		//			MesmerLogUtils.markFailed("Could not mouse hover on a screen")
		//		}
	}else{
		MesmerLogUtils.markFailed("No InUSe device")
	}

	//	}else{
	//		MesmerLogUtils.markFailed("Crawl is not started successfully")
	//	}


}catch(Exception e){
	e.printStackTrace()
}