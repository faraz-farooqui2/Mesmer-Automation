import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import javax.media.bean.playerbean.MediaPlayer.popupActionListener
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

//MS-Test Details-02 | Verify that there should only be 'Baseline' tab for a New / Recommended test case


WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()

		//1. Click on any test case/tile with status New/Recommended test case
		if(WebUI.waitForElementPresent(btnNew, 20)== true){
			WebUI.click(btnNew)
			MesmerLogUtils.markPassed("Clicked on New filter")
			WebUI.delay(2)


			String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
			List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

			MesmerLogUtils.logInfo("Number of test cases in a project" + " : " + titleStreamList.size())
			if(titleStreamList.size() > 0){

				titleStreamList.get(0).click()
				WebUI.delay(3)
				MesmerLogUtils.markPassed("Test case opens")
				if(WebUI.waitForElementPresent(noReplay, 10)){
					MesmerLogUtils.markPassed("No replay result found for New Test Case")
				}else{
					MesmerLogUtils.markFailed("Replay result found for New Test Case")
				}
			}else{
				MesmerLogUtils.markFailed("[DATA ISSUE] : No test case found in New filter")
			}
		}else{
			MesmerLogUtils.markFailed("Not Clicked on New Filter")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.refresh()
}