import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-13 | Verify the devices available for sequential replay
//1. Select a project from Project list
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)
CustomKeywords.'com.mesmer.Utility.goToTestResults'()
try{
//Additional Steps to perform Step No.2
	if(WebUI.waitForElementPresent(Tag, 10) ==true){
		WebUI.click(Tag)
		if(WebUI.waitForElementPresent(optionTag, 10) == true){
			WebUI.click(optionTag)
			WebUI.click(Tag)
			KeywordUtil.markPassed('PASSED: Clicked on Tags and Selected a Tag')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Tag option not found')
	}
//2. Click on 'Replay test cases' button
	if(WebUI.waitForElementPresent(btnReplay, 10)==true){
		WebUI.click(btnReplay)
		KeywordUtil.markPassed('PASSED: Clicked on Replay Test Cases')
		WebUI.delay(2)
		if(WebUI.waitForElementPresent(btnRun, 10)==true){
			WebUI.click(btnRun)
			KeywordUtil.markPassed('PASSED: Clicked on Run Test Cases')
		}else{
			KeywordUtil.markFailed('FAILED:Button Run Test Not Clicked')
		}
		if(WebUI.waitForElementPresent(btnYes, 10)==true){
			WebUI.click(btnYes)
			KeywordUtil.markPassed('PASSED: Clicked on Yes')
			WebUI.delay(5)
		}
	}else{
		KeywordUtil.markFailed('FAILED:Button Replay Not Clicked')
	}

//	if(WebUI.waitForElementPresent(btnStop, 20)==true){
//		WebUI.delay(2)
//		WebUI.click(btnStop)
//		KeywordUtil.markPassed('PASSED: Clicked on Stop Button')
//		
//		if(WebUI.waitForElementPresent(btnYes, 20)==true){
//			WebUI.delay(2)
//			WebUI.click(btnYes)
//			KeywordUtil.markPassed('PASSED: Clicked on Yes')
//		}else{
//			KeywordUtil.markFailed('FAILED: Unable to Click on Yes')
//		}
//	}else{
//		KeywordUtil.markFailed('FAILED: Unable to Click on Stop Button')
//	}
}catch(Exception e){
	e.printStackTrace()
}finally{
WebUI.refresh()
}