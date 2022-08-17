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
import controllers.BuildUploadController

// MR-NegativeScenario-46 | Verify that convert text file into .zip file then upload through Browse file Button integrity check mark as failed


WebDriver driver = DriverFactory.getWebDriver()
try{

	if(BuildUploadController.getInstance().clickUploadBuildExisiting()){

		if(BuildUploadController.getInstance().verifyUploadBuildDialogue()){


			if(BuildUploadController.getInstance().uploadBuildFile(buildName)){

				if(WebUI.waitForElementVisible(sitTightText, 180)==true){
					MesmerLogUtils.markPassed("Build upload is in progress")
					if(WebUI.waitForElementNotPresent(sitTightText, 1200)==true){
						if(BuildUploadController.getInstance().buildIntegrity()){
							if(BuildUploadController.getInstance().buildSimiliarity()){
								if(BuildUploadController.getInstance().buildCompatibility()){
									if(BuildUploadController.getInstance().buildCertificate()){

										String checksOfSuccessXpath = findTestObject('Object Repository/OR_AppMap/xpath_checksOfSuccess').findPropertyValue('xpath').toString()
										List<WebElement> checksOfSuccess = driver.findElements(By.xpath(checksOfSuccessXpath))
									
										MesmerLogUtils.markPassed("All " + " " + checksOfSuccess.size() + " " + " " + "  checks are marked successfully")
										
									}
									else{
										MesmerLogUtils.markFailed("buildCertificate method failed")
									}
								}
								else{
									MesmerLogUtils.markFailed("buildCompatibility method failed")
								}
							}
							else{
								MesmerLogUtils.markFailed("buildSimiliarity method failed")
							}
						}
						else{
							MesmerLogUtils.markFailed("buildIntegrity method failed")
						}
					}
					else{
						MesmerLogUtils.markFailed("Build is still not uploaded in 20 mins")
					}
				}else{
					MesmerLogUtils.markFailed("Sit tight msg not appears")
				}
			}else{
				MesmerLogUtils.markFailed("uploadBuildFile method failed")
			}

		}else{
			MesmerLogUtils.markFailed("verifyUploadBuildDialogue method failed")
		}

	}else{
		MesmerLogUtils.markFailed("clickUploadBuildExisiting method failed")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}




