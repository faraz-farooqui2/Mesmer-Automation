import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

/*
 * MS-Recommended Test Cases-03 | Verify that user should be able to use filters on recommended test case page.
 */


try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

		WebUI.waitForPageLoad(4)

		if(WebUI.waitForElementVisible(noTestCaseAvailableinRecommended, 5)==false){
			//Check Screens option
			if(WebUI.waitForElementVisible(screenOption, 10)){
				WebUI.click(screenOption)
				WebUI.delay(5)
				//Check Sort By option
				if(WebUI.waitForElementVisible(sortByOption, 10)){
					WebUI.click(sortByOption)
					WebUI.delay(5)
					if(WebUI.waitForElementVisible(dropDownStatus, 10)){
						WebUI.click(dropDownStatus)
						WebUI.delay(5)
						WebUI.click(sortByOption)
						if(WebUI.waitForElementVisible(dropDownScreenName, 10)){
							WebUI.click(dropDownScreenName)
							WebUI.delay(5)
							MesmerLogUtils.markPassed("MS-RecommendedTest-03 Successful")
						}
						else{
							MesmerLogUtils.markFailed("Screen name option is not displayed")
						}
					}
					else{
						MesmerLogUtils.markFailed("Status option is not displayed")
					}

				}else{
					MesmerLogUtils.markFailed("Sort By option is not displayed")
				}

			}else{
				MesmerLogUtils.markFailed("Screens option is not displayed")
			}

		}
		else{
			MesmerLogUtils.markFailed("There is no Test Case available in Recommended Test Cases")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}
