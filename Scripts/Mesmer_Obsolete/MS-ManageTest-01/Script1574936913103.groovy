import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
/*
 * MS-ManageTest-01 | Verify that user is shown all test cases in 'Manage Tests' page with their respective statuses.
 */
// Go to managetests page
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.delay(2)
try{
	// Check test cases count and label
	if(WebUI.waitForElementPresent(counterTotalTC,10) && WebUI.waitForElementPresent(textTotalTC,10)){
		WebUI.delay(2)
		// Check passed test cases count and label
		if(WebUI.waitForElementPresent(counterPassed,10) && WebUI.waitForElementPresent(textPassed,10)){
			WebUI.click(textPassed)
			WebUI.delay(2)
			// Check failed test cases count and label
			if(WebUI.waitForElementPresent(counterFailed,10) && WebUI.waitForElementPresent(textFailed,10)){
				WebUI.click(textFailed)
				WebUI.delay(2)
				// Check broken test cases count and label
				if(WebUI.waitForElementPresent(counterBroken,10) && WebUI.waitForElementPresent(textBroken,10)){
					WebUI.click(textBroken)
					WebUI.delay(2)
					// Check needs review test cases count and label
					if(WebUI.waitForElementPresent(counterNeedsReview,10) && WebUI.waitForElementPresent(textNeedsReview,10)){
						WebUI.click(textNeedsReview)
						WebUI.delay(1)
						KeywordUtil.markPassed("SUCCESS: MS-ManageTest-01 passed")
					}
					else{
						KeywordUtil.markFailed('FAILED: Needs review test cases count or title not found')
					}
				}
				else{
					KeywordUtil.markFailed('FAILED: Broken test cases count or title not found')
				}
			}
			else{
				KeywordUtil.markFailed('FAILED: Failed test cases count or title not found')
			}
		}
		else{
			KeywordUtil.markFailed('FAILED: Passed test cases count or title not found')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Test Cases count or title not found')
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}
