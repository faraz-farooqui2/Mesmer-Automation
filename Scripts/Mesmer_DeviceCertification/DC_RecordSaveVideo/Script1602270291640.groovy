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
			if(WebUI.waitForElementPresent(btnNew, 20)== true){
				WebUI.click(btnNew)
				WebUI.delay(2)
				MesmerLogUtils.markPassed("Clicked on New filter")

				String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
				List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

				MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
				if(titleStreamList.size() > 0){
					titleStreamList.get(0).click()
					WebUI.delay(3)
					MesmerLogUtils.markPassed("Clicked on Test Case")

					String baseLineTitleXpath = findTestObject('Object Repository/OR_DeviceCertification/record_BaselineTitle').findPropertyValue('xpath').toString()
					WebElement baseLineTitle = driver.findElement(By.xpath(baseLineTitleXpath))
					if(baseLineTitle != null){

						Actions builder = new Actions(driver);
						builder.moveToElement(baseLineTitle).perform();
						WebUI.delay(5)
						MesmerLogUtils.markPassed("Mouse Hovered on a Baseline Screen ")
						if(WebUI.waitForElementPresent(btnPlayVideo, 20)== true){
							WebUI.click(btnPlayVideo)
							WebUI.delay(5)
							if(WebUI.waitForElementPresent(videoPlayIcon, 20)== true){
								WebUI.click(videoPlayIcon)
								if(WebUI.waitForElementPresent(videoStart, 60)== true){
									if(WebUI.waitForElementPresent(videoEnd, 240)== true){
										if(WebUI.waitForElementPresent(btnClose, 60)== true){
											WebUI.click(btnClose)
										}else{
											MesmerLogUtils.markFailed("Unable to close video dialogue")
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
							MesmerLogUtils.markFailed("Could not click on Play Video button")
						}
					}else{
						MesmerLogUtils.markFailed("There is no baseline title")
					}

				}else{
					MesmerLogUtils.markFailed(" Test Case in New Filter " + " : " + titleStreamList.size())
				}
			}
			else{
				MesmerLogUtils.markFailed("Could not clicked on New filter")
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
	if(CustomKeywords.'com.mesmer.Utility.goToTestResults'()){

	}else{
		MesmerLogUtils.markFailed("Unable to navigate to test result screen" )
	}
}