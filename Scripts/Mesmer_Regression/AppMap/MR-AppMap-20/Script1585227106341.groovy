import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.AppMapController

//AppMap-20 | Verify User should be able to download screens after crawl is finished


//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName) 
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()


try{
	
if(WebUI.waitForElementVisible(ThreeDotButton, 10)==true){
		MesmerLogUtils.logInfo("Three dot button is displayed")
		WebUI.click(ThreeDotButton)
		MesmerLogUtils.logInfo("Three dot button is clicked")
		if(WebUI.waitForElementPresent(DownloadScreenOption, 10)==true){
			MesmerLogUtils.logInfo("Download Screen option is displayed correctly")
			WebUI.click(DownloadScreenOption)
			MesmerLogUtils.markPassed("Download screen option is clicked succecssfully")
			
		}
		else{
			MesmerLogUtils.markFailed("Download Screen option is not displayed")
		}
		
	}
	else{
		MesmerLogUtils.logInfo("Three dot button is not displayed")
	}
	
}
catch(Exception e){
	println(e.printStackTrace())
}




