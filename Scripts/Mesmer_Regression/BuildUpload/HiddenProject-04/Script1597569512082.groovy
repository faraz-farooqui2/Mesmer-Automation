import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.AppMapController


// HiddenProject-03 | Verify that non-mesmer user cannot create hidden project
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebDriver driver = DriverFactory.getWebDriver()
try{

	//Check project/build option
	if(WebUI.waitForElementPresent(buildProjectPopOver, 60) ==true){

		//Click on Project option down arrow button
		//2. user click on projects menu
		WebUI.click(buildProjectPopOver)

		//If Build upload button is displayed then click on Back arrow button so that 'Create New Project' button is displayed
		//3. user is on project menu drop down
		if(WebUI.waitForElementPresent(uploadNewBuildButton, 20)==true){
			//4. user click on back arrow from drop down
			WebUI.click(projectSlideArrow)


			WebUI.delay(2)

			String listProjectsXpath = findTestObject('Object Repository/OR_BuildUpload/projectList').findPropertyValue('xpath').toString()

			if (WebUI.verifyElementPresent(findTestObject('Object Repository/OR_BuildUpload/projectList'), 10)){

				List<WebElement> projList = driver.findElements(By.xpath(listProjectsXpath))

				for (WebElement webElement : projList) {
					String projListText = webElement.getText();
					if(projListText.contains(hiddenProjects)){
						MesmerLogUtils.markFailed("Hidden project is visible to Non Mesmer user" + " : " + hiddenProjects )
					} else {
						MesmerLogUtils.markPassed("Hidden project is not visible to Non Mesmer user")
						
					}
				}

			}else{
				MesmerLogUtils.markFailed("Project List and Create New Project button not found")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to click on project slide arrow ")
		}
	}
	else{
		MesmerLogUtils.markFailed("Project drop down not found")
	}

}

catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}
