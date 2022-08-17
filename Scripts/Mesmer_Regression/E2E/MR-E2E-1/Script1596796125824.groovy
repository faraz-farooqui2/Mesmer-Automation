import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.AppMapController
import controllers.E2EController
import controllers.TestResultController

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebUI.delay(2)

try{
	// Get the replay test devices list
	List<WebElement> replayTestDevicesList = getReplayDevicesList()
	// Get the create new test devices list
	List<WebElement> createNewTestDevicesList = getCreateNewTestDevicesList()
	// Get the crawl test devices list
//	List<WebElement> crawlDevicesList = getCrawlDevicesList()
	CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
	WebUI.delay(5)
	String pName = ""
	if(platformName.equals("apple")){
		pName = "iOS"
	}
	else{
		pName = "Android"
	}
	if(E2EController.getInstance().clickDeviceListOptions(pName)){
		List<WebElement> androidDevicesList = E2EController.getInstance().getDevicesList(pName)
		if(androidDevicesList != null && androidDevicesList.size() > 0){
			MesmerLogUtils.logInfo(pName+" devices list size is : "+androidDevicesList.size())
		}
	}
}
catch(Exception e){
	e.printStackTrace()
}

private List<WebElement> getCreateNewTestDevicesList(){
	List<WebElement> result = null
	CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
	WebUI.waitForPageLoad(5)
	List<WebElement> createNewTestDevicesList = E2EController.getInstance().getAvailableDevicesList()
	if(createNewTestDevicesList != null && createNewTestDevicesList.size() > 0){
		result = createNewTestDevicesList
		MesmerLogUtils.logInfo("Devices list size in create new test is : "+createNewTestDevicesList.size())
	}
	return result
}

private List<WebElement> getReplayDevicesList(){
	List<WebElement> result = null
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.waitForPageLoad(5)
	if(TestResultController.getInstance().clickReplayButton()){
		WebUI.delay(2)
		List<WebElement> replayDevicesList = E2EController.getInstance().getAvailableDevicesList()
		if(replayDevicesList != null && replayDevicesList.size() > 0){
			result = replayDevicesList
			MesmerLogUtils.logInfo("Devices list size in replay test is : "+replayDevicesList.size())
		}
	}
	return result
}

private List<WebElement> getCrawlDevicesList(){
	List<WebElement> result = null
	CustomKeywords.'com.mesmer.Utility.goToAppMap'()
	WebUI.waitForPageLoad(5)
	if(AppMapController.getInstance().clickThreeDotsButton()){
		WebUI.delay(2)
		if(AppMapController.getInstance().clickConfigureCrawlButton()){
			WebUI.delay(2)
			List<WebElement> crawlDevicesList = E2EController.getInstance().getAvailableDevicesList()
			if(crawlDevicesList != null && crawlDevicesList.size() > 0){
				result = crawlDevicesList
				MesmerLogUtils.logInfo("Devices list size in crawl test is : "+crawlDevicesList.size())
			}
		}
	}
	return result
}