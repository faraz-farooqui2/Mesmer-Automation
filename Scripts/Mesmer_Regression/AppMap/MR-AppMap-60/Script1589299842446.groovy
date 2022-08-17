import org.openqa.selenium.WebDriver

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.AppMapController


// AppMap-History-60 | Verify that latest crawl should always be dispalyed by default on visiting App Map page


//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

CustomKeywords.'com.mesmer.Utility.goToAppMap'()
WebDriver driver = DriverFactory.getWebDriver()

try{

	if(AppMapController.getInstance().openCrawlHistory())
	{
		MesmerLogUtils.logInfo("Crawl history is displayed")
		
		
		String SelectedCrawlTime = WebUI.getText(selectedCrawlTime)
		MesmerLogUtils.logInfo("Selected Crawl Time ==== > " + SelectedCrawlTime)
		
//		0 means top crawl from history and 1 means 2nd crawl from history
		String date1 = AppMapController.getInstance().getCrawlIniatedValue(0, driver)
		String date2 = AppMapController.getInstance().getCrawlIniatedValue(1, driver)
//		
		MesmerLogUtils.logInfo("Initiated Date and Time of Crawl 1 is " + date1)
		MesmerLogUtils.logInfo("Initiated Date and Time of Crawl 2 is " + date2)
		
		if(Utility.compareDateTimeEqual(date1, SelectedCrawlTime)){
			MesmerLogUtils.markPassed("Recent Crawl is showing at top")
		}
		else{
			MesmerLogUtils.markFailed("Recent Crawl is not showing at top")
		
		}

	}
	else{
		MesmerLogUtils.logInfo("Crawl history is not displayed")
	}

	WebUI.delay(2)

}
catch(Exception e){
	e.printStackTrace()
}
finally{

}

