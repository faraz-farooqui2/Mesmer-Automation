import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

// MR-BuildUpload-02 | Verify if user can see project/build info to be static even after changing a build under the same project
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)

MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebDriver driver = DriverFactory.getWebDriver()
try{

	CustomKeywords.'com.mesmer.Utility.goToTestResults'()

	if(WebUI.waitForElementPresent(projectBuildLable, 10)){

		
		if(WebUI.waitForElementVisible(clickProjectdropdown, 20) ==true){
			WebUI.click(clickProjectdropdown)

			String buildListXpath = findTestObject('Object Repository/OR_BuildUpload/list_buildList').findPropertyValue('xpath').toString()
			List<WebElement> buildList = driver.findElements(By.xpath(buildListXpath))
			if(buildList.size() > 2){
				buildList.get(1).click()
			}else{
				MesmerLogUtils.markFailed("Build list size not greater than 2")
			}
		}else{
			MesmerLogUtils.markFailed("Could not click on Project dropdown")
		}
	}else{
		MesmerLogUtils.markFailed("No project build label found")
	}

}
catch(Exception e){
	e.printStackTrace()
}
finally{
//	WebUI.refresh()
}