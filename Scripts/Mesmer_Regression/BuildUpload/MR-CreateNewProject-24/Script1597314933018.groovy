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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.AppMapController
import controllers.BuildUploadController



// MR-CreateNewProject-24 |Verify that user Should see all devices and Crawl duration (hours : minuts) and "run Crawl" button on Build Uploaded Popup
//ASSUMPTION:: In order to run this test case, project should not be available on the testing server
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebDriver driver = DriverFactory.getWebDriver()
try{

	if(BuildUploadController.getInstance().clickCreateNewProjectButton()){
		if(BuildUploadController.getInstance().uploadBuildFile(buildName)){

			//check Sit Tight text when build upload in progress
//			if(WebUI.waitForElementVisible(sitTightText, 180)==true){
//				MesmerLogUtils.markPassed("Build upload is in progress")
//				if(WebUI.waitForElementNotPresent(sitTightText, 1200)==true){
					//8. build upload processing sequence and check mark
					//Check Integrity after build just uploaded

					if(WebUI.waitForElementPresent(progressBar, 10)){
						if(WebUI.waitForElementNotPresent(progressBar, 120)){



							buildIntegrity()
							//Check Similarity on build upload process
							buildSimiliarity()
							//Check Compatibility on build upload process
							buildCompatibility()
							//Check Certificate on build upload process
							buildCertificateCheck()

							//	checkSuccesful()

							//Next button on successful checks screen
							if(WebUI.waitForElementVisible(nextButton, 20)==true){
								WebUI.click(nextButton)
								WebUI.delay(5)

								if(BuildUploadController.getInstance().CreateNewProjectNameDialogue()){
									if(WebUI.waitForElementPresent(btnCancel, 5)){
										WebUI.click(btnCancel)
										if(BuildUploadController.getInstance().discardBuildPopUp()){

										}else{
											MesmerLogUtils.markFailed("Could not verify dicard build dialogue on cancel button")
										}
									}else{
										MesmerLogUtils.markFailed("Could not click on Cancel button")
									}
								}else{
									MesmerLogUtils.markFailed("Could not verify create new project name dialogue")
								}
							}
							else{
								MesmerLogUtils.markFailed("NEXT button is not visible on build success checks window")
							}

						}else{
							MesmerLogUtils.markFailed("Build upload progress bar still showing")
						}

					}else{
						MesmerLogUtils.markFailed("No progress bar shown")
					}
//
//				}
//				else{
//					MesmerLogUtils.markFailed("Build is still not uploaded in 20 mins")
//				}
//			}else{
//				MesmerLogUtils.markFailed("Sit tight msg not appears")
//			}

		}else{
			MesmerLogUtils.markFailed("uploadBuildFile method failed ")
		}
	}
	else{
		MesmerLogUtils.markFailed("clickCreateNewProjectButton method failed ")
	}

}

catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}


private boolean buildIntegrity(){
	boolean result = false

	if(WebUI.waitForElementVisible(buildIntegrityCheck, 20)==true){
		result = true
	}
	else{
		MesmerLogUtils.markFailed("Build Integrity Check is failed in 2 minutes")
	}
	return result
}

private boolean buildSimiliarity(){
	boolean result = false

	if(WebUI.waitForElementVisible(buildSimiliarityCheck, 20)==true){
		result = true
	}
	else{
		MesmerLogUtils.markFailed("Build Similarity Check is failed in 2 minutes")
	}
	return result
}

private boolean buildCompatibility(){
	boolean result = false

	if(WebUI.waitForElementVisible(compatibilityCheck, 20)==true){
		result = true
	}
	else{
		MesmerLogUtils.markFailed("Build Compatibility Check is failed in 2 minutes")
	}
	return result
}

private boolean buildCertificateCheck(){
	boolean result = false

	if(WebUI.waitForElementVisible(certificateCheck, 20)==true){

		result = true
	}
	else{
		MesmerLogUtils.markFailed("Build certificate Check is failed in 2 minutes")
	}
	return result
}

//private boolean checkSuccesful(){
//	boolean result = false
//	WebDriver driver = DriverFactory.getWebDriver()
//
//	String checksOfSuccessXpath = findTestObject('Object Repository/OR_AppMap/xpath_checksOfSuccess').findPropertyValue('xpath').toString()
//	List<WebElement> checksOfSuccess = driver.findElements(By.xpath(checksOfSuccessXpath))
//	WebUI.delay(10)
//	if(checksOfSuccess.size > 0){
//	System.err.println(checksOfSuccess.size())
//	result = true
//	}else{
//	MesmerLogUtils.markFailed("Check of success size is null")
//	}
//	return result
//}