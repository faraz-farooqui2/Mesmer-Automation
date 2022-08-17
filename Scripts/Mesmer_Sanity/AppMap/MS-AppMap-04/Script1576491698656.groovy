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


//MS-AppMap-04 | Verify that user can configure the time and set the device from 'Configure Crawl' option.


WebDriver driver = DriverFactory.getWebDriver()

try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())


		CustomKeywords.'com.mesmer.Utility.goToAppMap'()

		//1. Click on 3 dot menu button the top right corner of the AppMap screen.
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
									if(AppMapController.getInstance().setHrsMintsForCrawlConfig(hours , minutes)){
										if(AppMapController.getInstance().clickOkButton()){

										}else{
											MesmerLogUtils.markFailed("Could not click on Ok button")
										}
									}else{
										MesmerLogUtils.markFailed("Could not set time")
									}
								}else{
									MesmerLogUtils.markFailed("Could not click on Next button")
								}
							}else{
								MesmerLogUtils.markFailed("Could not click on ready or provisioned device")
							}
						}else{
							MesmerLogUtils.markFailed("Could not verify select device dialogue")
						}
					}else{

						MesmerLogUtils.markFailed("Could not click on Next button")
					}
				}else{
					MesmerLogUtils.markFailed("Configure crawl dialogue not open")
				}

			}
			else{
				MesmerLogUtils.markFailed("Run Crawl button is not displayed")
			}
		}
		else{
			MesmerLogUtils.markFailed("Could not click on three dot button")
		}

	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{

	if(AppMapController.getInstance().clickCloseButton()){
		KeywordUtil.markPassed("Dialogue closed successfully")
	}else{
		WebUI.refresh()
	}
}
