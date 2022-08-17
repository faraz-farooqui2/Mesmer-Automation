import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-27 | Watch video for the replayed test case
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()

try{
	if (WebUI.waitForElementPresent(linkPassed , 20)){
		WebUI.click(linkPassed)
		WebUI.delay(2)
		if (WebUI.waitForElementPresent(testLink , 20)){
			WebUI.click(testLink)
			WebUI.delay(2)

			if (WebUI.waitForElementPresent(titleBar_elipsis, 10)){
				WebUI.click(titleBar_elipsis)
				WebUI.delay(1)
				KeywordUtil.markPassed("Options in title bar opened successfully")
				if (WebUI.waitForElementPresent(downloadResults, 10)){
					KeywordUtil.markPassed("Download results option is present")

					if(WebUI.verifyElementPresent(watchVideo, 10)){
						WebUI.click(watchVideo)
						WebUI.delay(1)
						KeywordUtil.markPassed("Clicked on watch video successfully")

						if(WebUI.verifyElementPresent(titleWatchVideo, 10)){
							KeywordUtil.markPassed("Watch video title is present")

							if(WebUI.verifyElementPresent(videoPlayButton, 10)){
								WebUI.click(videoPlayButton)
								KeywordUtil.markPassed("Video play button is clicked")


								if(WebUI.verifyElementPresent(playbackSpeedIcon, 10)){
									WebUI.click(playbackSpeedIcon)
									KeywordUtil.markPassed("Clicked on play back speed icon")


									if(WebUI.verifyElementPresent(videoScrubBar, 10)){
										KeywordUtil.markPassed("Video scrub bar is present")


										if(WebUI.verifyElementPresent(videoTime, 10)){
											KeywordUtil.markPassed("Video time is present")

											if(WebUI.verifyElementPresent(btnClose, 10)){
												WebUI.click(btnClose)
												KeywordUtil.markPassed("Video dialogue is closed")
											}else{
												KeywordUtil.markFailed("No close button is present in video dialogue")
											}

										}else{
											KeywordUtil.markFailed("No video time is present in video dialogue")
										}

									}else{
										KeywordUtil.markFailed("No video scrub bar is present in video dialogue")
									}

								}else{
									KeywordUtil.markFailed("No video playback speed icon is present in video dialogue")
								}
							}else{
								KeywordUtil.markFailed("No video play button is present in video dialogue")
							}

						}else{
							KeywordUtil.markFailed("Watch video not present")
						}

					}else{
						KeywordUtil.markFailed("Watch video option not available")
					}

				}else{
					KeywordUtil.markFailed("Download results button is not available")
				}

			}else{
				KeywordUtil.markFailed("Options in title bar not opened")
			}
		}else{
			KeywordUtil.markFailed("Could not click on test case")
		}

	}else{
		KeywordUtil.markFailed("Could not click on Passed filter")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{

}