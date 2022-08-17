import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

//MR-BuildUpload-1 | Verify that user should see device type " virtual" or "physical" below the project title

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)

MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
try{
	if(WebUI.waitForElementVisible(clickProjectdropdown, 20) ==true){
		WebUI.click(clickProjectdropdown)

		if(WebUI.waitForElementPresent(deviceTypeVirtual, 5) ==true){

		}else if (WebUI.waitForElementPresent(deviceTypePhysical, 5) ==true){

		}else{
			MesmerLogUtils.markFailed("No icon for Virtual/Physical device type")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on drop down ")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}
