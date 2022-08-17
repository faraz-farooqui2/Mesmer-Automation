package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

public class CommonMethodsController {

	private static CommonMethodsController mInstance = null

	private CommonMethodsController(){
	}

	public static CommonMethodsController getInstance(){
		if(mInstance == null){
			mInstance = new CommonMethodsController()
		}

		return mInstance
	}
	/**
	 * Check and click the replay crawl button
	 * @return
	 */
	public boolean closeButton(){
		boolean result = false
		TestObject btnClose = findTestObject('Object Repository/OR_AppMap/btn_close')
		if(WebUI.waitForElementVisible(btnClose, 30)){
			WebUI.click(btnClose)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Unable to close dialogue")
		}
		return result
	}
}