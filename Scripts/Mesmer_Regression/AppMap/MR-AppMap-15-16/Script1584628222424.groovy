import org.openqa.selenium.WebDriver

import com.kms.katalon.core.util.MesmerLogUtils as MesmerLogUtils
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.AppMapController




//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	if(checkAppMapTestCase15Steps()){

		if(checkAppMapTestCase16Steps()){

		}else{
			MesmerLogUtils.markFailed("TC-16 has failed")
		}
	}else{
		MesmerLogUtils.markFailed("TC-15 has failed")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{

}

private boolean checkAppMapTestCase15Steps(){
	boolean result = false
	//ALL BELOW VERIFICATIONs ARE DONE WHEN CRAWL IS FINISHED
	//Verify Ignore button
	if(WebUI.verifyElementVisible(ignoreAllButton)==true){
		MesmerLogUtils.markPassed("Ignore button is displayed")

		//Verify Watch Replay text
		if(WebUI.verifyElementVisible(textWatchReplay)==true){
			MesmerLogUtils.markPassed("Watch Replay text is displayed")

			//Verify Watch Replay button
			if(WebUI.verifyElementVisible(btnWatchReplay)==true){
				MesmerLogUtils.markPassed("Watch Replay button is displayed")

				//Verify Recommended Tests text
				if(WebUI.verifyElementVisible(textRecommended)==true){
					MesmerLogUtils.markPassed("Recommended Tests text is displayed")

					//Verify Recommended Tests button
					if(WebUI.verifyElementVisible(btnRecommended)==true){
						MesmerLogUtils.markPassed(" Recommended Tests button is displayed")

						//Verify Add More Data text
						if(WebUI.verifyElementVisible(textAddMoreData)==true){
							MesmerLogUtils.markPassed("Add More Data text is displayed")

							//Verify Add Data button
							if(WebUI.verifyElementVisible(btnAddData)==true){
								MesmerLogUtils.markPassed("Add Data button is displayed")

								//Verify Close button on Watch Replay text area
								if(WebUI.verifyElementVisible(btnCloseWatchReplay)==true){
									MesmerLogUtils.markPassed(" Close button of Watch Replay is displayed successfully")

									//Verify Close button on Recommended Text text area
									if(WebUI.verifyElementVisible(btnCloseRecommended)==true){
										MesmerLogUtils.markPassed("Close button of Recommended Tests is displayed successfully")

										//Verify Close button on Add Data text area
										if(WebUI.verifyElementVisible(btnCloseAddData)==true){
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

private boolean checkAppMapTestCase16Steps(){
	boolean result = false
	//Verify Recommended Tests text
	if(WebUI.waitForElementVisible(textRecommended,10)==true){
		MesmerLogUtils.markPassed("Recommended Tests text is displayed")

		//Verify Recommended Tests button
		if(WebUI.waitForElementVisible(btnRecommended,10)==true){
			MesmerLogUtils.markPassed("Recommended Tests button is displayed")
			WebUI.click(btnRecommended)
			WebUI.delay(2)
			if(WebUI.waitForElementVisible(recommendedPage, 10)==true){
				MesmerLogUtils.markPassed("Successfully navigated to Recommended page")
				result = true
			}
			else{
				MesmerLogUtils.markFailed("Recommended page is not displayed")
			}
		}
		else{
			MesmerLogUtils.markFailed("Recommended Tests button is not displayed")
		}
	}
	else{
		MesmerLogUtils.markFailed("Recommended Tests text is not displayed")
	}

	return result
}