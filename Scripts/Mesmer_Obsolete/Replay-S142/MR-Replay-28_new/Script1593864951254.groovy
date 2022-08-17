import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-26 | Download Results for replayed test case
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()

try{

	if (WebUI.waitForElementPresent(linkPassed , 20)){
		WebUI.click(linkPassed)
		WebUI.delay(2)
		if (WebUI.waitForElementPresent(testLink , 20)){
			WebUI.click(testLink)
			WebUI.delay(2)

			if (WebUI.waitForElementPresent(titleBar_elipsis, 10)){
				WebUI.click(titleBar_elipsis)
				WebUI.delay(1)
				KeywordUtil.markPassed("Options in title bar opened successfully")
				if (WebUI.waitForElementPresent(downloadResults, 10)){
					WebUI.click(downloadResults)
					WebUI.delay(1)
					KeywordUtil.markPassed("Clicked on Download Result successfully")

				}else{
					KeywordUtil.markFailed("Download results button is not available")
				}

			}else{
				KeywordUtil.markFailed("Options in title bar not opened")
			}
		}else{
			KeywordUtil.markFailed("Could not click on test case")
		}

	}else{
		KeywordUtil.markFailed("Could not click on Passed filter")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{

}