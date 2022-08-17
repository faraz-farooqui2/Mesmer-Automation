import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

//MR-BuildUpload-01 | Verify if user can see project/build info to be static on project/build title UI


//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

try{

	CustomKeywords.'com.mesmer.Utility.goToTestResults'()

	if(WebUI.waitForElementPresent(projectBuildLable, 10)){


	}else{
		MesmerLogUtils.markFailed("No project build label found")
	}


}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}
