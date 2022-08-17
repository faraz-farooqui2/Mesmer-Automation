import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

//Replay-DeviceSelecton-42 | Verify user can select a single device for running multiple replays
//1. Select a project from Project list
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)
CustomKeywords.'com.mesmer.Utility.goToTestResults'()

try{


	//Additional Steps to perform Step No.2
	if(WebUI.waitForElementPresent(Tag, 10) ==true){
		WebUI.click(Tag)

		TestObject xpathOptionTag = findTestObject('Object Repository/OR_Replay/select_Tag')
		TestObject optionTag = CustomKeywords.'com.mesmer.Utility.selectTag'(xpathOptionTag, tagName)
		WebUI.delay(1)

		WebUI.click(optionTag)

		if(WebUI.waitForElementPresent(TagSelected, 10) == true){
			WebUI.click(TagSelected)
			KeywordUtil.markPassed('PASSED: Closed the tag dropdown list')

			//2. Click on 'Replay test cases' button
			if(WebUI.waitForElementPresent(btnReplay, 10)==true){
				WebUI.click(btnReplay)
				KeywordUtil.markPassed('PASSED: Clicked on Replay Test Cases')
				WebUI.delay(2)
				if(WebUI.waitForElementPresent(btnRun, 10)==true){
					WebUI.click(btnRun)
					KeywordUtil.markPassed('PASSED: Clicked on Run Test Cases')

					if(WebUI.waitForElementPresent(btnYes, 10)==true){
						WebUI.click(btnYes)
						KeywordUtil.markPassed('PASSED: Clicked on Yes')
						WebUI.delay(5)
					}else{
						KeywordUtil.markFailed('FAILED: Could not click on Yes button')
					}
				}else{
					KeywordUtil.markFailed('FAILED:Button Run Test Not Clicked')
				}
			}else{
				KeywordUtil.markFailed('FAILED:Button Replay Not Clicked')
			}
		}else{
			KeywordUtil.markFailed('FAILED: Could not close tag dropdown')
		}
	}
	else{
		KeywordUtil.markFailed('FAILED: Could not click on  label tag')
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	//	WebUI.refresh()
	if(CustomKeywords.'com.mesmer.Utility.stopExecution'()){
	}else{
		KeywordUtil.markFailed("Could not stop test case replay")
	}
}