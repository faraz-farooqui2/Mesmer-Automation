import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

//CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
//WebUI.delay(2)
//CustomKeywords.'com.mesmer.Utility.goToTestResults'()
Utility.setPlatformName("")
WebUI.delay(4)
try{
	if(WebUI.waitForElementPresent(helpIcon, 30)){
		WebUI.click(helpIcon)
		WebUI.delay(2)
		startSearch()
	}
	else{
		KeywordUtil.markFailed("FAILED: Help/Chat icon not found")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
	WebUI.delay(2)
}
def startSearch(){
	if(WebUI.waitForElementPresent(inputSearchArticle, 30)){
		WebUI.click(inputSearchArticle)
		WebUI.setText(inputSearchArticle, "Test")
		WebUI.sendKeys(null,Keys.chord(Keys.ENTER))
		WebUI.delay(2)
		if(WebUI.waitForElementPresent(btnCross, 30)){
			WebUI.click(btnCross)
			WebUI.delay(2)
		}
		else{
			KeywordUtil.markWarning("WARNING: Cross button not found")
		}
		KeywordUtil.markPassed("PASSED: MS-HelpChat-02 successful")
	}
	else{
		KeywordUtil.markFailed("FAILED: Input Search article not found")
	}

}