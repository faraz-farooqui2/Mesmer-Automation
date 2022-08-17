package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

public class HelpChatController {

	private static HelpChatController mInstance = null

	private HelpChatController(){
	}

	public static HelpChatController getInstance(){
		if(mInstance == null){
			mInstance = new HelpChatController()
		}

		return mInstance
	}


	public boolean startConversation(){

		boolean result = false

		TestObject helpIcon = findTestObject('Object Repository/OR_Help/icon_help')
		TestObject helpScreenIFrame = findTestObject('Object Repository/OR_Help/Page_Mesmer/iframe__intercom-messenger-frame')
		if(WebUI.waitForElementPresent(helpIcon, 30)){
			WebUI.click(helpIcon)
			WebUI.delay(2)
			//			WebUI.switchToFrame(helpScreenIFrame, 10)
			//			WebUI.comment("Switched the frame")
			TestObject btnNewConversation = findTestObject('Object Repository/OR_Help/HelpChatNewPaths/button_Send us a message')
			if(WebUI.waitForElementPresent(btnNewConversation, 30)){
				WebUI.scrollToElement(btnNewConversation, 20)
				WebUI.click(btnNewConversation)
				result = true
				WebUI.delay(2)
			}else{
				MesmerLogUtils.markFailed("Unable to click on New conversation button")
			}
		}
		else{
			MesmerLogUtils.markFailed("Help/Chat icon not found")
		}
		return result
	}

	public boolean clickHelpChatIcon(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_Help/icon_help')
		if(WebUI.waitForElementPresent(obj, 30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		return result
	}


}
