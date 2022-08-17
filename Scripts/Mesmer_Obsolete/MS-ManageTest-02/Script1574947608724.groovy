import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
/*
 * MS-Manage Tests-02 | Verify that filter counts in 'Manage Test' are as per Test result page
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
		String manageTestsTotalCount = WebUI.getText(counterTotalTC);
		WebUI.delay(2)
		// Check passed test cases count and label
		if(WebUI.waitForElementPresent(counterPassed,10) && WebUI.waitForElementPresent(textPassed,10)){
			WebUI.delay(1)
			// Check failed test cases count and label
			if(WebUI.waitForElementPresent(counterFailed,10) && WebUI.waitForElementPresent(textFailed,10)){
				WebUI.delay(1)
				// Check broken test cases count and label
				if(WebUI.waitForElementPresent(counterBroken,10) && WebUI.waitForElementPresent(textBroken,10)){
					WebUI.delay(1)
					// Check needs review test cases count and label
					if(WebUI.waitForElementPresent(counterNeedsReview,10) && WebUI.waitForElementPresent(textNeedsReview,10)){
						WebUI.delay(1)
						// Go to test results screen
						CustomKeywords.'com.mesmer.Utility.goToTestResults'()
						if(WebUI.waitForElementPresent(textTestResults,60)){
							if(WebUI.verifyElementPresent(countTotalTestResultsCount, 10)){
								String testResultsCount = WebUI.getText(countTotalTestResultsCount)
								if(testResultsCount.equalsIgnoreCase(manageTestsTotalCount)){
									KeywordUtil.markPassed("SUCCESS: MS-ManageTest-02 passed")
								}
								else{
									KeywordUtil.markFailed('FAILED: MS-ManageTest-02 Failed')
								}
							}
							else{
								KeywordUtil.markFailed('FAILED: Test Results count not found')
							}
						}
						else{
							KeywordUtil.markFailed('FAILED: Test Results label not found')
						}
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
