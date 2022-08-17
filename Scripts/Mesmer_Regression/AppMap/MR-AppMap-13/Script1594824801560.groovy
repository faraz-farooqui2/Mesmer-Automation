import com.kms.katalon.core.util.MesmerLogUtils as MesmerLogUtils
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.AppMapController

//AppMap-13 | Verify there is a device preview expand/collapse option during running crawl and this option is working fine

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()


try{

	if(AppMapController.getInstance().clickRunCrawl(hours , minutes)){
		MesmerLogUtils.markPassed("[MEMSER]: Crawl started successfully")

		WebUI.delay(60)
		if(WebUI.waitForElementVisible(imgCrawlPreview, 5)==true && WebUI.waitForElementVisible(btnMinPreviewDuringCrawl, 5)==true)
		{
			WebUI.delay(1)
			MesmerLogUtils.markPassed("PASSED: Crawl Preview and Minimize Preview buttons are displayed")
			WebUI.click(btnMinPreviewDuringCrawl)
			MesmerLogUtils.logInfo("[MESMER]: Minimize Preview button is clicked")
			WebUI.delay(5)
			if(WebUI.waitForElementVisible(btnDevicePreviewWatchVideo, 5)==true){
				MesmerLogUtils.markPassed("PASSED: Device Preview is displayed")
				WebUI.click(btnDevicePreviewWatchVideo)
				MesmerLogUtils.logInfo("[MESMER]: Device Preview button is clicked")
				WebUI.delay(2)

				//WebUI.waitForElementVisible(imgCrawlPreviewWatchVideo, 5)==true &&
				if(WebUI.verifyElementNotPresent(btnDevicePreviewWatchVideo, 5)==true){
					MesmerLogUtils.markPassed("PASSED: Crawl Preview is displayed")
					result = true
				}
				else{
					MesmerLogUtils.markFailed("FAILED: Crawl Preview is not displayed after clicking Device Preview")
				}
			}
			else{
				MesmerLogUtils.markFailed("FAILED: Device Preview button is not displayed")
			}
		}
		else{
			MesmerLogUtils.markFailed("FAILED: Crawl Preview and Minimize Preview buttons are not displayed")
		}
	}else{
		MesmerLogUtils.markFailed("[MESMER]: Crawl is not started successfully")
	}

}
catch(Exception e){
	println(e.printStackTrace())
}
finally{

	WebUI.delay(5)


	CustomKeywords.'com.mesmer.Utility_AppMap.stopCrawlFromButton'()

	WebUI.delay(5)
}

