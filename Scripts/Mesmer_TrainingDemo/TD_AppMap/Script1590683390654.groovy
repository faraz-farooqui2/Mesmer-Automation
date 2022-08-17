import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.AppMapController

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()

try{
	playAppMap()
	configureCrawl()
}
catch(Exception e){
	e.printStackTrace()
}

private boolean playAppMap(){
	boolean result = false
	if(AppMapController.getInstance().clickReplayCrawlButton()){
		WebUI.delay(4)
		if(AppMapController.getInstance().clickPauseCrawlButton()){
			WebUI.delay(4)
			if(AppMapController.getInstance().checkIfDisabledButtonsOnRightSidePresent()){
				WebUI.delay(4)
				if(AppMapController.getInstance().clickMinimizePreviewButton()){
					WebUI.delay(4)
					if(AppMapController.getInstance().clickDevicePreviewButton()){
						WebUI.delay(4)
						if(AppMapController.getInstance().clickDoneCrawlButton()){
							result = true
							MesmerLogUtils.logInfo("Crawl video played successfully")
							WebUI.delay(2)
						}
					}
				}
			}
		}
	}
	
	return result
}
/**
 * Configure the crawl by selecting the device and setting the crawl time
 * @return
 */
private boolean configureCrawl(){
	boolean result = false
	if(AppMapController.getInstance().clickThreeDotsButton()){
		WebUI.delay(2)
		if(AppMapController.getInstance().clickConfigureCrawlButton()){
			WebUI.delay(4)
			if(selectDevice("Virtual")){
				WebUI.delay(2)
				if(AppMapController.getInstance().setHrsMintsForCrawlConfig("6","0")){
					WebUI.delay(2)
					if(AppMapController.getInstance().clickSaveConfigButton()){
						if(AppMapController.getInstance().checkIfSaveConfigAlertAppears()){
							result = true
							MesmerLogUtils.logInfo("Crawl configured and saved successfully")
						}
					}
				}
			}
		}
	}
	return result
}
/**
 * Show the object library and check if elements exists in it
 * @return
 */
private boolean showObjectLibrary(){
	boolean result = false
	if(AppMapController.getInstance().clickObjLibraryActiveButton()){
		if(AppMapController.getInstance().checkIfAppObjectsTextVisible()){
			if(AppMapController.getInstance().checkIfItemsExistsInObjectLibrary()){
				result = true
				MesmerLogUtils.logInfo("Object library shown successfully")
			}
		}
	}
	return result
}

/**
 * Select the device from devices list based on the provided deviceType. 
 * Device type could be Virtual or Physical
 * @param deviceType
 * @return
 */
private boolean selectDevice(String deviceType){
	boolean result = false
	if(AppMapController.getInstance().checkIfDeviceListPresent()){
		WebUI.delay(2)
		List<WebElement> virtualDevicesList = Utility.getAvailableDevices(deviceType)
		if(virtualDevicesList != null && virtualDevicesList.size() >=1){
			System.out.println(deviceType+" Devices Count: "+virtualDevicesList.size())
			WebElement searchedReadyDevice = Utility.selectDevice(deviceType)
			if(searchedReadyDevice != null){
				searchedReadyDevice.click()
				result = true
				Utility.logCurrentUTCTime("Selecting Device time in Crawl")
			}
		}
		else{
			MesmerLogUtils.markFailed("Required device not available")
		}
	}
	else{
		MesmerLogUtils.markWarning("Device list is not shown")
	}
	
	return result
}