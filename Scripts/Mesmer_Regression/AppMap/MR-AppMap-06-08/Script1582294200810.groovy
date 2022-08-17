import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import controllers.AppMapController
//MS-AppMap-08 | Verify that recommended test cases are generating during crawl


//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName) 
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()


try{

		
//AppMap-06 | Verify crawl video can be replayed by Watch Replay button on Crawl Complete screen
		checkAppMapTestCase06Steps()
		
		checkAppMapTestCase07Steps()
		
		checkAppMapTestCase08Steps()
		
}
catch(Exception e){
	println(e.printStackTrace())
}
finally{
	
}

private boolean checkAppMapTestCase06Steps(){
	boolean result = false
	if(WebUI.waitForElementVisible(btnWatchReplay, 5)==true){
		WebUI.click(btnWatchReplay)
		
		WebUI.delay(1)
		if(WebUI.waitForElementVisible(btnPauseWatchVideo, 5)==true){
			WebUI.delay(1)
			if(WebUI.waitForElementVisible(btnPlayWatchVideo, 20)==true){
				KeywordUtil.logInfo("Info: Video is stopped properly")
				WebUI.delay(1)
				if(WebUI.waitForElementVisible(btnDisabledInfo, 2)==true && WebUI.waitForElementVisible(btnDisabledObjRepo, 2)==true
					&& WebUI.waitForElementVisible(btnDisabledHistory, 2)==true && WebUI.waitForElementVisible(btnDisabledLogs, 2)==true)
				{
					WebUI.delay(1)
					KeywordUtil.markPassed("PASSED: All the buttons in the right drawer are disabled")
					
					if(WebUI.waitForElementVisible(imgCrawlPreviewWatchVideo, 5)==true && WebUI.waitForElementVisible(btnMinPreviewWatchVideo, 5)==true)
					{
						WebUI.delay(1)
						KeywordUtil.markPassed("PASSED: Crawl Preview and Minimize Preview buttons are displayed")
						WebUI.click(btnMinPreviewWatchVideo)
						KeywordUtil.logInfo("[MESMER]: Minimize Preview button is clicked")
						WebUI.delay(5)
						if(WebUI.waitForElementVisible(btnDevicePreviewWatchVideo, 5)==true){
							KeywordUtil.markPassed("PASSED: Device Preview is displayed")
							WebUI.click(btnDevicePreviewWatchVideo)
							KeywordUtil.logInfo("[MESMER]: Device Preview button is clicked")
							WebUI.delay(2)
							
							//WebUI.waitForElementVisible(imgCrawlPreviewWatchVideo, 5)==true && 
							if(WebUI.waitForElementNotPresent(btnDevicePreviewWatchVideo, 5)==true){
								KeywordUtil.markPassed("PASSED: Crawl Preview is displayed")
								result = true
							}
							else{
								KeywordUtil.markFailed("FAILED: Crawl Preview is not displayed after clicking Device Preview")
							}
							
					
						}
						else{
							KeywordUtil.markFailed("FAILED: Device Preview button is not displayed")
						}
						
						
					}
					else{
						KeywordUtil.markFailed("FAILED: Crawl Preview and Minimize Preview buttons are not displayed")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: All the buttons in the right drawer are not disabled")
				}
			}
			else{
				KeywordUtil.logInfo("Info: Video is not stopped properly")
			}
			
		}
		else{
			KeywordUtil.markFailed("FAILED: Video is not playing after clicking Watch Replay button")
		}
		
	}
	else{
		KeywordUtil.markFailed("FAILED: Watch Replay button is not displayed")
	}
	return result
}


private boolean checkAppMapTestCase08Steps(){
	boolean result = false
	if(WebUI.waitForElementVisible(btnWatchReplay, 5)==true){
		WebUI.click(btnWatchReplay)
		
		if(WebUI.waitForElementVisible(btnPauseWatchVideo, 5)==true){
			KeywordUtil.markPassed("PASSED: Video is playing")
			
			if(WebUI.waitForElementVisible(btnPlayWatchVideo, 20)==true){
				KeywordUtil.logInfo("Info: Video is stopped properly")
				
				WebUI.click(btnPlayWatchVideo)
				if(WebUI.waitForElementVisible(btnPauseWatchVideo, 5)==true){
					KeywordUtil.markPassed("PASSED: Video is played")
					
					WebUI.click(btnPauseWatchVideo)
					if(WebUI.waitForElementVisible(btnPlayWatchVideo, 5)==true){
						KeywordUtil.markPassed("PASSED: Video is paused")
						result = true
					}
					else{
						KeywordUtil.markFailed("FAILED: Video is not paused")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: Video is not played")
				}
				
			}
			else{
				KeywordUtil.logInfo("Info: Video is not stopped properly")
			}
			
		}
		else{
			KeywordUtil.markFailed("FAILED: Video is not playing after clicking Watch Replay button")
		}
		
	}
	else{
		KeywordUtil.markFailed("FAILED: Watch Replay button is not displayed")
	}
}

private boolean checkAppMapTestCase07Steps(){
	boolean result = false
	if(WebUI.waitForElementVisible(btnWatchReplay, 5)==true){
		KeywordUtil.logInfo("[MESMER]: Watch Replay button is displayed")
		WebUI.click(btnWatchReplay)
		KeywordUtil.logInfo("[MESMER]: Watch Replay button is clicked")
		
		if(WebUI.waitForElementVisible(btnPauseWatchVideo, 5)==true){
			KeywordUtil.markPassed("PASSED: Video is playing")
			
			if(WebUI.waitForElementVisible(btnDoneWatchVideo, 5)==true){
				KeywordUtil.markPassed("PASSED: Done button is displayed")
				
				WebUI.click(btnDoneWatchVideo)
				WebUI.delay(2)
				
				if(WebUI.waitForElementVisible(linkScreenManager, 5)==true){
					KeywordUtil.markPassed("PASSED: Done button is clicked and AppMap page is displayed successfully")
					result = true
				}
				else{
					KeywordUtil.markFailed("FAILED: AppMap page is not displayed")
				}
					
			}else{
				KeywordUtil.markFailed("FAILED:  Done button is not displayed")
			}
			
		}
		else{
			KeywordUtil.markFailed("FAILED: Video is not playing after clicking Watch Replay button")
		}
		
	}
	else{
		KeywordUtil.markFailed("FAILED: Watch Replay button is not displayed")
	}
}

