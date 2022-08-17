package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

public class EditTestController {

	private static EditTestController mInstance= null

	private EditTestController(){
	}

	public static EditTestController getInstance(){
		if(mInstance == null){
			mInstance = new EditTestController()
		}

		return mInstance
	}

	public boolean checkIfTestCaseTitleExists(){
		boolean result = false
		try{
			TestObject obj = findTestObject('Object Repository/OR_ManageTestCases/title_EditTest')
			if(WebUI.waitForElementVisible(obj, 30)){
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Edit test title not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean checkIf3DotsOptionExists(){
		boolean result = false
		try{
			TestObject obj = findTestObject('Object Repository/OR_ManageTestCases/btn_3DotOptions')
			if(WebUI.waitForElementVisible(obj, 30)){
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Button 3 dots option not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean checkIfCloneDeleteOptionExists(){
		boolean result = false
		try{
			TestObject cloneObj = findTestObject('Object Repository/OR_ManageTestCases/xpath_Clone')
			if(WebUI.waitForElementVisible(cloneObj, 30)){
				TestObject deleteObj = findTestObject('Object Repository/OR_ManageTestCases/xpath_delete')
				if(WebUI.waitForElementVisible(deleteObj, 30)){
					result = true
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markFailed("Button delete option not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("Button clone option not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean checkIfEditTestScreenOptionsExists(){
		boolean result = false
		if(checkIfTestCaseTitleExists()){
			if(checkIf3DotsOptionExists()){
				if(click3DotsOptionBtn()){
					if(checkIfCloneDeleteOptionExists()){
						result = true
					}
				}
			}
		}
		return result
	}
	/**
	 * Check and click the 3 dots option icon
	 * @return
	 */
	public boolean click3DotsOptionBtn(){
		boolean result = false
		try{
			TestObject obj = findTestObject('Object Repository/OR_ManageTestCases/btn_3DotOptions')
			if(WebUI.waitForElementVisible(obj, 30)){
				WebUI.click(obj)
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Button 3 dots option not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean editTestCaseTitle(String title){
		boolean result = false
		try{
			TestObject inputField = findTestObject('Object Repository/OR_ManageTestCases/input_EditTestCaseTitle')
			if(Utility.isMac()){
				WebUI.sendKeys(inputField, Keys.chord(Keys.COMMAND, 'a'))
			}
			else{
				WebUI.sendKeys(inputField, Keys.chord(Keys.CONTROL, "a"))
			}
			WebUI.sendKeys(inputField, Keys.chord(Keys.chord(Keys.BACK_SPACE)))
			WebUI.clearText(inputField)
			WebUI.delay(2)
			WebUI.setText(inputField,title)
			WebUI.delay(2)
			//			sendEnterKey()
			clickTickIconInEditTest()
			WebUI.delay(2)
			result = true
			WebUI.delay(2)
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean clickTickIconInEditTest(){
		boolean result = false
		try{
			TestObject obj = findTestObject('Object Repository/OR_ManageTestCases/icon_Edit_Tick')
			if(WebUI.waitForElementVisible(obj, 30)){
				WebUI.click(obj)
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Tick icon during title editing not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}

	public boolean clickEditTestIcon(){
		boolean result = false
		try{
			TestObject obj = findTestObject('Object Repository/OR_ManageTestCases/icon_Edit_Tick')
			if(WebUI.waitForElementVisible(obj, 30)){
				WebUI.click(obj)
				result = true
				WebUI.delay(2)
			}
			else{
				MesmerLogUtils.markFailed("Pencil icon during title editing not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
		return result
	}
}
