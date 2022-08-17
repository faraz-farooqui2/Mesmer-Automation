import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.BuildUploadController

// MR- UploadNewBuild-28 | Verify that user should see an error text "You must upload a .ZIP, .apk, .ipa" when user drag & drop or upload a file other than .zip, .ipa, .apk

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)

MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

try{

	if(BuildUploadController.getInstance().clickUploadBuildExisiting()){

		if(BuildUploadController.getInstance().verifyUploadBuildDialogue()){


			if(BuildUploadController.getInstance().uploadBuildFile(buildName)){

				if(WebUI.waitForElementPresent(errorMsg, 10)){

				}else{
					MesmerLogUtils.markFailed("Error msg not appears on uploading wrong format file")
				}

			}else{
				MesmerLogUtils.markFailed("uploadBuildFile method failed")
			}

		}else{
			MesmerLogUtils.markFailed("verifyUploadBuildDialogue method failed")
		}

	}else{
		MesmerLogUtils.markFailed("clickUploadBuildExisiting method failed")
	}

}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}