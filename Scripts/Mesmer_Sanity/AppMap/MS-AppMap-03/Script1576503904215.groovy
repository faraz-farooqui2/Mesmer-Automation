import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.mesmer.MesmerLogUtils
import controllers.AppMapController


//MS-AppMap-03 | Verify that user can 'Re-Run' or 'Stop' the triggered walk.

//Calling Select Project Method

WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)

	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)){
		WebUI.delay(2)

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(CustomKeywords.'com.mesmer.Utility.goToAppMap'()){

			TestObject stopCrawlButton = findTestObject('Object Repository/OR_AppMap/button_StopCrawl')
			if(WebUI.waitForElementPresent(stopCrawlButton, 20)){
				if(AppMapController.getInstance().stopACrawl()){
					if(verifyBehaviourCrawlEnd()){
						MesmerLogUtils.markPassed("All verification passed")
					}else{
						MesmerLogUtils.markFailed("Could not verify behaviour after crawl end")
					}
				}else{
					MesmerLogUtils.markFailed("Could not stop a crawl")
				}

			}else{
				MesmerLogUtils.logInfo("Configuring Crawl....")
				if(AppMapController.getInstance().clickRunCrawl(hours , minutes , Device)){

					if (WebUI.waitForElementVisible(crawlingText, 240) == true) {
						KeywordUtil.markPassed('SUCCESS: Crawl started')

						if (WebUI.waitForElementNotPresent(crawlingText, 120) == true) {
							KeywordUtil.markPassed('Crawl stopped')

							if(verifyBehaviourCrawlEnd()){
								MesmerLogUtils.markPassed("All verification passed")
							}else{
								MesmerLogUtils.markFailed("Could not verify behaviour after crawl end")
							}
						}
						else{
							KeywordUtil.logInfo("WARNING: Crawl runs more than its specified time  " + " Hours :  " + hours  + " Minutes : " +  minutes + "")
							if(CustomKeywords.'com.mesmer.Utility_AppMap.stopCrawlFromButton'()){
								if(verifyBehaviourCrawlEnd()){
									MesmerLogUtils.markPassed("All verification passed")
								}else{
									MesmerLogUtils.markFailed("Could not verify behaviour after crawl end")
								}
							}else{
							MesmerLogUtils.markFailed("Could not stop crawl from button")
							}
						}
					}
					else{
						KeywordUtil.markFailed("FAILED: Crawl not started in 4 mins")
					}
				}else{
					MesmerLogUtils.markFailed("Could not configure and start crawl")
				}
			}
		}else{
			MesmerLogUtils.markFailed("Unable to navigate to app map screen" )
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	println(e.printStackTrace())
}
finally{

}

private boolean verifyBehaviourCrawlEnd(){
	boolean result = false
	//ALL BELOW VERIFICATIONs ARE DONE WHEN CRAWL IS FINISHED
	//Verify Ignore button
	if(WebUI.waitForElementPresent(ignoreAllButton , 10)==true){
		MesmerLogUtils.markPassed("Ignore button is displayed")

		//Verify Watch Replay text
		if(WebUI.waitForElementPresent(textWatchReplay , 10)==true){
			MesmerLogUtils.markPassed("Watch Replay text is displayed")

			//Verify Watch Replay button
			if(WebUI.waitForElementPresent(btnWatchReplay , 10)==true){
				MesmerLogUtils.markPassed("Watch Replay button is displayed")

				//Verify Recommended Tests text
				if(WebUI.waitForElementPresent(textRecommended , 10)==true){
					MesmerLogUtils.markPassed("Recommended Tests text is displayed")

					//Verify Recommended Tests button
					if(WebUI.waitForElementPresent(btnRecommended , 10)==true){
						MesmerLogUtils.markPassed("Recommended Tests button is displayed")

						//Verify Add More Data text
						if(WebUI.waitForElementPresent(textAddMoreData , 10)==true){
							MesmerLogUtils.markPassed("Add More Data text is displayed")

							//Verify Add Data button
							if(WebUI.waitForElementPresent(btnAddData , 10)==true){
								MesmerLogUtils.markPassed("Add Data button is displayed")

								//Verify Close button on Watch Replay text area
								if(WebUI.waitForElementPresent(btnCloseWatchReplay , 10)==true){
									MesmerLogUtils.markPassed("Close button of Watch Replay is displayed successfully")

									//Verify Close button on Recommended Text text area
									if(WebUI.waitForElementPresent(btnCloseRecommended , 10)==true){
										MesmerLogUtils.markPassed("Close button of Recommended Tests is displayed successfully")

										//Verify Close button on Add Data text area
										if(WebUI.waitForElementPresent(btnCloseAddData , 10)==true){
											MesmerLogUtils.markPassed("Close button of Add More Data is displayed successfully")
											result = true
										}
										else{
											MesmerLogUtils.markFailed("Close button of Add More Data is not displayed successfully")
										}
									}
									else{
										MesmerLogUtils.markFailed("Close button of Recommended Tests is not displayed successfully")
									}

								}
								else{
									MesmerLogUtils.markFailed("Close button of Watch Replay is not displayed successfully")
								}
							}
							else{
								MesmerLogUtils.markFailed("Add Data button is not displayed")
							}
						}
						else{
							MesmerLogUtils.markFailed("Add More Data text is not displayed")
						}
					}
					else{
						MesmerLogUtils.markFailed("Recommended Tests button is not displayed")
					}

				}
				else{
					MesmerLogUtils.markFailed("Recommended Tests text is not displayed")
				}

			}
			else{
				MesmerLogUtils.markFailed("Watch Replay button is not displayed")
			}
		}
		else{
			MesmerLogUtils.markFailed("Watch Replay text is not displayed")
		}

	}
	else{
		MesmerLogUtils.markFailed("Ignore button is not displayed")
	}
	return result
}