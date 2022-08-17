import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility



WebDriver driver = DriverFactory.getWebDriver()
try{

	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(CustomKeywords.'com.mesmer.Utility.goToTestResults'()){

			//1. Click on any test case/tile with status New/Recommended test case
			if(WebUI.waitForElementPresent(btnPassed, 20)== true){
				WebUI.click(btnPassed)
				WebUI.delay(2)
				MesmerLogUtils.markPassed("Clicked on Passed filter")

				String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
				List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

				MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
				if(titleStreamList.size() > 0){
					titleStreamList.get(0).click()
					WebUI.delay(3)
					MesmerLogUtils.markPassed("Clicked on Test Case")

					String watchVideoXpath = findTestObject('Object Repository/OR_Replay/NewUIXpaths/button_watchVideoNew').findPropertyValue('xpath').toString()
					List<WebElement> watchVideo = driver.findElements(By.xpath(watchVideoXpath))
					if(watchVideo != null){

						watchVideo.get(0).click()


						WebUI.delay(5)
						if(WebUI.waitForElementPresent(videoPlayIcon, 20)== true){
							WebUI.click(videoPlayIcon)
							if(WebUI.waitForElementPresent(videoStart, 60)== true){
								if(WebUI.waitForElementPresent(videoEnd, 180)== true){
									if(WebUI.waitForElementPresent(btnClose, 60)== true){
										WebUI.click(btnClose)
									}else{
										MesmerLogUtils.markFailed("Video not Completed")
									}
								}else{
									MesmerLogUtils.markFailed("Video not Completed 100%")
								}
							}else{
								MesmerLogUtils.markFailed("Video not started")
							}
						}else{
							MesmerLogUtils.markFailed("Could not click on video play icon")
						}
					}else{
						MesmerLogUtils.markFailed("There is no watch video icon")
					}

				}else{
					MesmerLogUtils.markFailed(" Test Case in Passed Filter " + " : " + titleStreamList.size())
				}
			}
			else{
				MesmerLogUtils.markFailed("Could not clicked on Passed filter")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to navigate to test result screen" )
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.delay(5)
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}