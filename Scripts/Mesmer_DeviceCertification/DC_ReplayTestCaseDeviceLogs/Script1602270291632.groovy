import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import org.openqa.selenium.Keys
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.interactions.Actions
import com.mesmer.MesmerLogUtils



WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(CustomKeywords.'com.mesmer.Utility.goToTestResults'()){
			//1. Click on any test case from test result page.
			String recommendedTest = findTestObject('Object Repository/OR_TestDetails/list_recommendedTestCase').findPropertyValue('xpath').toString()
			List<WebElement> recoTests = driver.findElements(By.xpath(recommendedTest))

			MesmerLogUtils.logInfo("Number of Recommended Test Cases on Test Results page Before Deleting = " + recoTests.size())

			if(recoTests != null && recoTests.size() > 0){

				recoTests.get(0).click()

				MesmerLogUtils.markPassed("Recommended test case opens")
				WebUI.delay(2)
				//2. Click on 3 dot menu button appearing besides Select device button on top right corner.
				if(WebUI.waitForElementPresent(option3DotButton, 20)==true){
					WebUI.click(option3DotButton)
					MesmerLogUtils.markPassed("Clicked on three dot button")
					//3. Click on downlaod result option from the list
					if(WebUI.waitForElementPresent(downloadResult, 20)==true){
						WebUI.click(downloadResult)
						MesmerLogUtils.markPassed("Downloading Results.....")
						WebUI.delay(5)
						WebUI.click(option3DotButton)
						WebUI.delay(2)
						
					}else{
						MesmerLogUtils.markFailed("Unable to download results")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to Click on three dot button")
				}
			}else{
				MesmerLogUtils.markFailed("No Recommended Test Case Found")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to navigate to test result screen" )
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
	if(CustomKeywords.'com.mesmer.Utility.goToTestResults'()){
	}else{
		MesmerLogUtils.markFailed("Unable to navigate to test result screen" )
	}
}