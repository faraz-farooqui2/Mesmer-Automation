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


try{

	CustomKeywords.'com.mesmer.Utility.goToTestResults'()

	if(WebUI.waitForElementPresent(projectBuildLable, 10)){

		buildUploadList()

	}else{
		MesmerLogUtils.markFailed("No project build label found")
	}

}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}


private boolean buildUploadList(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	if(WebUI.waitForElementVisible(clickProjectdropdown, 20) ==true){
		WebUI.click(clickProjectdropdown)
		WebUI.delay(2)

		if(WebUI.waitForElementPresent(crawled, 10)){
			
			CustomKeywords.'com.mesmer.Utility.goToAppMap'()
			
			if(WebUI.waitForElementPresent(screenManager, 10)){
				
			}else{
			MesmerLogUtils.markFailed("No crawl ran on the build")
			}

		}else{
			MesmerLogUtils.markFailed("Crawled status not shown")
		}
	}else{
		MesmerLogUtils.markFailed("Could not click on Project dropdown")
	}
	return result
}