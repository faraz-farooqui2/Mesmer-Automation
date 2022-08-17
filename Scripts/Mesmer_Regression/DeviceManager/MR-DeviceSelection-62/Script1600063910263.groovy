import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.ReplayController

// MR-Device Selection-62 | Rerun | Verify Replay modal closes on clicking outside the screen

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{


	//2. Click on 'Replay' button appearing in the top right corner
	if(WebUI.waitForElementPresent(btnReplay , 120)==true){
		WebUI.click(btnReplay)
		MesmerLogUtils.markPassed("Click On Replay button")
		WebUI.delay(2)
		WebUI.click(blankClick)
		if(WebUI.waitForElementNotPresent(replayDevicesListOption ,2) == true){
			MesmerLogUtils.markPassed("Replay devices list option closed")
		}else{
		MesmerLogUtils.markFailed("Replay devices list option not closed")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to Click on replay button")
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{

	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}