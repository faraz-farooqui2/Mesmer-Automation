import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.mesmer.MesmerLogUtils
import controllers.AppMapController


//AppMap-24 - Verify user can set maximum crawl duration 720 minutes (12 hrs) and minimum 5 minutes

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{
	

	TestObject ThreeDotButton = findTestObject('Object Repository/OR_AppMap/button_3Dots')
	TestObject RunCrawlOption = findTestObject('Object Repository/OR_AppMap/optionDropdown_RunCrawl')
	TestObject startCrawlbtn = findTestObject('Object Repository/OR_AppMap/btn_startCrawl')

	if(WebUI.waitForElementVisible(ThreeDotButton,5)==true){
		WebUI.click(ThreeDotButton)

		if(WebUI.waitForElementVisible(RunCrawlOption,5)==true){
			WebUI.click(RunCrawlOption)
			WebUI.delay(1)
			if(AppMapController.getInstance().verifyConfigureCrawlDialogue()){
				if(AppMapController.getInstance().clickNextButton()){
					WebUI.delay(5)
					if(AppMapController.getInstance().verifyDeviceDialogue()){
						if(AppMapController.getInstance().selectDevice()){
							if(AppMapController.getInstance().clickNextButton()){


								int timeHrs = Integer.parseInt(timeInHrs)
								int timeMins = Integer.parseInt(timeInMins)

//								int totalTime = timeHrs*60+timeMins
//								MesmerLogUtils.logInfo("Total Time is = " + totalTime)

								WebUI.clearText(inputTimeInMins)

								WebUI.clearText(inputTimeInHrs)

//								MesmerLogUtils.logInfo("Time in Hrs from Datafile = " + timeInHrs)
//								MesmerLogUtils.logInfo("Time in Mins from Datafile = " + timeInMins)

								WebUI.delay(2)
								WebUI.sendKeys(inputTimeInHrs, timeInHrs)
								WebUI.sendKeys(inputTimeInHrs, Keys.chord(Keys.ENTER))

								WebUI.sendKeys(inputTimeInMins, timeInMins)
								WebUI.sendKeys(inputTimeInMins, Keys.chord(Keys.ENTER))

								if(timeMins < 5){

									MesmerLogUtils.logInfo("Checking less than 5 minutes...")

									if(WebUI.waitForElementVisible(notificationLessThan5Mins, 5)==true ||WebUI.waitForElementVisible(invalidMins, 5) || WebUI.waitForElementVisible(disabledSaveConfigBtn, 5)==true){
										MesmerLogUtils.markPassed("AppMap - Test Case 24 is Passed")

									}
									else
									{
										MesmerLogUtils.markFailed("AppMap - Test Case 24 is Failed")
									}

								}

								else if(timeHrs > 12){
									MesmerLogUtils.logInfo("Checking greater than 12 hrs...")

									if(WebUI.waitForElementVisible(invalidHrs, 5) || WebUI.waitForElementVisible(invalidMins, 5)==true || WebUI.waitForElementVisible(disabledSaveConfigBtn, 5)==true){
										MesmerLogUtils.markPassed("AppMap - Test Case 24 is Passed")

									}
									else
									{
										MesmerLogUtils.markFailed("AppMap - Test Case 24 is Failed")
									}
								}

								else{
									MesmerLogUtils.logInfo("Correct time in Config Crawl...")

//									if(WebUI.waitForElementVisible(saveConfigBtn, 5)==true){
//										MesmerLogUtils.markPassed("AppMap - Test Case 24 is Passed")
//									}
//									else{
//										MesmerLogUtils.markFailed("AppMap - Test Case 24 is Failed")
//									}
								}

							}else{
								MesmerLogUtils.logInfo("Could not click on Next button")
							}
						}else{
							MesmerLogUtils.logInfo("Could not click on ready or provisioned device")
						}
					}else{
						MesmerLogUtils.logInfo("Could not verify select device dialogue")
					}
				}else{

					MesmerLogUtils.logInfo("Could not click on Next button")
				}
			}else{
				MesmerLogUtils.logInfo("Configure crawl dialogue not open")
			}

		}
		else{
			KeywordUtil.logInfo("Run Crawl button is not displayed")
		}
	}
	else{
		KeywordUtil.logInfo("Could not click on three dot button")
	}
}catch(Exception e){
	e.printStackTrace()
}
finally{

//	WebUI.delay(2)
//	if(WebUI.waitForElementVisible(btnCancelConfigCrawl, 5)==true){
//		MesmerLogUtils.logInfo("Cancel Config Crawl button is displayed")
//		WebUI.click(btnCancelConfigCrawl)
//		MesmerLogUtils.logInfo("Cancel Config Crawl button is clicked")
//		WebUI.delay(2)
//	}
//	else{
//		MesmerLogUtils.logInfo("Cancel Config Crawl button is not displayed")
//	}
	WebUI.refresh()
}
