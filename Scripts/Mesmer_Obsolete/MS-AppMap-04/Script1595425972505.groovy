import org.openqa.selenium.By

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.Utility_AppMap

import com.mesmer.MesmerLogUtils
import com.mesmer.KTRequestHandler
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;


//MS-AppMap-04 | Verify that user can configure the time and set the device from 'Configure Crawl' option.

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
////////////////////////////////////
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()

try{
	//1. Click on 3 dot menu button the top right corner of the AppMap screen.
	if(WebUI.waitForElementVisible(ThreeDotButton,10)==true){
		WebUI.click(ThreeDotButton)
		WebUI.delay(1)
		if(WebUI.waitForElementVisible(ConfigureCrawlOption,5)==true){
			//2. Click on 'Configure Crawl' option
			WebUI.click(ConfigureCrawlOption)
			WebUI.delay(3)

			//3. Select the available device from the list for crawl.


			if(WebUI.waitForElementVisible(deviceList, 10)==true){
				MesmerLogUtils.logInfo("[MESMER]: Device list is displayed")
				
				List<WebElement> virtualDevicesList = Utility.getAvailableDevices(Device)
				if(virtualDevicesList != null && virtualDevicesList.size() >=1){
					System.out.println("Virtual Devices Count: "+virtualDevicesList.size())
					WebElement searchedReadyDevice = Utility.selectDevice(Device)
					if(searchedReadyDevice != null){
						searchedReadyDevice.click()
						Utility.logCurrentUTCTime("Selecting Device time in Crawl")
					}
				}
				else{
					MesmerLogUtils.markFailed("[MESMER]: Required device not available")
				}

			}
			else{
				MesmerLogUtils.markWarning("[MESMER]: Device list is not shown")
			}
			
			
			//4. Set the required time of crawl in Crawl duration field.
			if(WebUI.waitForElementVisible(InputTimeInMinutes, 10)==true){

				MesmerLogUtils.logInfo("[MESMER]: Hrs and Minute fields are displayed")
				WebUI.clearText(InputTimeInHrs)
				WebUI.clearText(InputTimeInMinutes)
				MesmerLogUtils.logInfo("[MESMER]: Hrs and Minute fields are cleared")
				WebUI.delay(1)
				MesmerLogUtils.logInfo("[MESMER]: Providing hrs and minutes in respective fields")
				WebUI.sendKeys(InputTimeInHrs, timeInHrs)
				WebUI.sendKeys(InputTimeInHrs, Keys.chord(Keys.ENTER))
				WebUI.sendKeys(InputTimeInMinutes, timeInMinutes)
				WebUI.sendKeys(InputTimeInMinutes, Keys.chord(Keys.ENTER))
				
				WebUI.delay(1)
				
				//5. Check Save button and Click on it
				if(WebUI.waitForElementPresent(SaveConfigButton, 2)==true){
					WebUI.click(SaveConfigButton)
					WebUI.delay(1)
					if(WebUI.waitForElementVisible(ConfigSavedNotification,15)==true){
						MesmerLogUtils.markPassed("Configuaration is saved successfully")

						WebUI.refresh()
						
						goToConfigCrawl()
						
						WebUI.delay(1)
						String mins = WebUI.getAttribute(InputTimeInMinutes, 'value')
						String hrs = WebUI.getAttribute(InputTimeInHrs, 'value')
						MesmerLogUtils.logInfo("hrs = " + hrs + "mins = " + mins)
						
						int mins1 = Integer.parseInt(mins)
						int hrs1 = Integer.parseInt(hrs)
						int totaltimeSetInFields = mins1 + hrs1*60
						
						int setTimeMins =  Integer.parseInt(timeInMinutes)
						int setTimeHrs = Integer.parseInt(timeInHrs) 
						int totalTimeFromDataFile = setTimeMins + setTimeHrs*60
						
						System.err.println('total Time From Fields: ' + totaltimeSetInFields)
						System.err.println('total Time From Data Files ' + totalTimeFromDataFile)

						println("NUMBER--1 IS --------------- " + totaltimeSetInFields)
						println("NUMBER--2 IS --------------- " + totalTimeFromDataFile)
						
						
						if(totaltimeSetInFields == totalTimeFromDataFile){
							MesmerLogUtils.markPassed("Configurations are saved and shown in Time field")
							MesmerLogUtils.logInfo("Clicking Cancel button")
							WebUI.click(CancelButton)
							MesmerLogUtils.logInfo("Cancel button is clicked")
						}
						else{
							MesmerLogUtils.markFailed("Configurations are not saved in Time field")
							WebUI.click(CancelButton)
						}
					}
					else{
						MesmerLogUtils.markFailed("Configuration is not saved")
					}

				}
				else{
					MesmerLogUtils.markFailed("SAVE button is not available on Config Crawl screen")
				}
			}
			else{
				MesmerLogUtils.markFailed("Input Time field is not available on Config Crawl screen")
			}
		}
		else{
			MesmerLogUtils.markFailed("CONFIG CRAWL Option is not found")
		}
	}
	else{
		MesmerLogUtils.markFailed("3 Dot button is not visible")
	}


}
catch(Exception e){
	e.printStackTrace()
}
finally{

	WebUI.refresh()
}

boolean goToConfigCrawl(){
boolean	result = false
	
	if(WebUI.waitForElementClickable(ThreeDotButton, 10))
	{
		WebUI.delay(2)
		WebUI.click(ThreeDotButton)
		MesmerLogUtils.logInfo("Clicking Config Crawl option")
		WebUI.delay(1)
		if(WebUI.waitForElementVisible(ConfigureCrawlOption, 15)){
			MesmerLogUtils.logInfo("Config Crawl option is displayed. Now clicking...")
			WebUI.click(ConfigureCrawlOption)
			MesmerLogUtils.logInfo("Config Crawl option is clicked")
			result = true
		}
		else{
			MesmerLogUtils.logInfo("Config crawl option is not displayed")
		}
	
	}
	else{
		MesmerLogUtils.logInfo("ThreeDot Button is not displayed")
	}
	return result
	
}

