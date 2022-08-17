import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import com.mesmer.KTRequestHandler

//CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
//WebUI.delay(2)
//CustomKeywords.'com.mesmer.Utility.goToTestResults'()
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebUI.delay(5)
try{
	if(HelpChat01()){
		KTRequestHandler.updateTaskOnZephyr("MS-HelpChat-01",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-HelpChat-01",Utility.getPlatformName(),-1)
	}
	
	if(HelpChat02()){
		KTRequestHandler.updateTaskOnZephyr("MS-HelpChat-02",Utility.getPlatformName(),1)
	}
	else{
		KTRequestHandler.updateTaskOnZephyr("MS-HelpChat-02",Utility.getPlatformName(),-1)
	}
	
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
	WebUI.delay(2)
}



private boolean HelpChat01(){
	boolean result = false
	startConversation()
	WebUI.delay(5)
	if(WebUI.waitForElementPresent(textAreaSendMessage, 30)){
		WebUI.click(textAreaSendMessage)
		WebUI.setText(textAreaSendMessage, "Hello, This is a test message")
		WebUI.delay(2)
		if(WebUI.waitForElementPresent(btnSendMessage, 30)){
			WebUI.click(btnSendMessage)
			WebUI.delay(2)
			MesmerLogUtils.markPassed("MS-HelpChat-01 successful")
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Send message button not found")
		}
	}
	else{
		MesmerLogUtils.markFailed("Text area for sending the message not found")
	}
	return result
}


private boolean HelpChat02(){
	boolean result = false
	if(WebUI.waitForElementPresent(btnBack, 30)){
		WebUI.click(btnBack)
		WebUI.delay(5)
	
	if(WebUI.waitForElementPresent(inputSearchArticle, 30)){
		WebUI.click(inputSearchArticle)
		WebUI.setText(inputSearchArticle, "Mesmer")
		WebUI.sendKeys(null,Keys.chord(Keys.ENTER))
		WebUI.delay(2)
		if(WebUI.waitForElementPresent(btnCross, 30)){
			WebUI.click(btnCross)
			WebUI.delay(2)
			result = true
		}
		else{
			MesmerLogUtils.markWarning("Cross button not found")
		}
		MesmerLogUtils.markPassed("MS-HelpChat-02 successful")
	}
	else{
		MesmerLogUtils.markFailed("Input Search article not found")
	}
	}
	else{
		MesmerLogUtils.markWarning("Back button not found")
	}
	return result
}

def startConversation(){
	if(WebUI.waitForElementPresent(helpIcon, 30)){
		WebUI.click(helpIcon)
		WebUI.delay(2)
		if(WebUI.waitForElementPresent(btnNewConversation, 30)){
			WebUI.scrollToElement(btnNewConversation, 20)
			WebUI.click(btnNewConversation)
			WebUI.delay(2)
		}else{
			MesmerLogUtils.markFailed("Unable to click on New conversation button")
		}
	}
	else{
		MesmerLogUtils.markFailed("Help/Chat icon not found")
	}

}