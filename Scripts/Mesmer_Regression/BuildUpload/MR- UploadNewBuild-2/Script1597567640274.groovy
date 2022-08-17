import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

//MR- UploadNewBuild-2 |Same Build, Same Project | Verify that user can upload same build on a same project for multiple times through Upload New Build button

WebUI.delay(2)
WebDriver driver = DriverFactory.getWebDriver()
//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
try{
	if(WebUI.waitForElementVisible(clickProjectdropdown, 20) ==true){
		WebUI.click(clickProjectdropdown)
		//		WebUI.click(projectSlideArrow)
		WebUI.delay(2)
		if(WebUI.waitForElementVisible(buttonBuildUpload,60) == true){
			//1. Click on Upload new build from the Project list on the top left corner of the mesmer console
			WebUI.click(buttonBuildUpload)
			println("Build upload button is clicked")
			WebUI.delay(2)

			//Verification - Upload your latest build window will appear
			if(WebUI.waitForElementVisible(uploadAppBuild, 20, FailureHandling.CONTINUE_ON_FAILURE)==true){

				//2. Drag/Browse the required app build with valid app login credentials (Optional)
				if(WebUI.verifyElementVisible(lblBrowseToSelect) == true){
					//	WebUI.click(btnBrowseToSelect)

					if(Utility.isWindows()){
						String Slash = "\\\\"
						WebUI.uploadFile(btnBrowseToSelect, GlobalVariable.buildPath +Slash+ buildName, FailureHandling.STOP_ON_FAILURE)
					}else if(Utility.isMac()){
						String Slash = "/"
						WebUI.uploadFile(btnBrowseToSelect, GlobalVariable.buildPathMac +Slash+ buildName, FailureHandling.STOP_ON_FAILURE)
					}

					

					//check Sit Tight text when build upload in progress
					if(WebUI.waitForElementVisible(sitTightText, 180)==true){
						MesmerLogUtils.markPassed("Build upload is in progress")
						if(WebUI.waitForElementNotPresent(sitTightText, 1200)==true){

							//Check Integrity after build just uploaded
							if(WebUI.waitForElementVisible(buildIntegrityCheck, 180)==true){
								//Check Similarity on build upload process
								if(WebUI.waitForElementVisible(buildSimiliarityCheck, 180)==true){
									//Check Compatibility on build upload process
									if(WebUI.waitForElementVisible(compatibilityCheck, 180)==true){
										//Check Certificate on build upload process
										if(WebUI.waitForElementVisible(certificateCheck, 180)==true){
											MesmerLogUtils.markPassed("All checks are marked successfully")

											String checksOfSuccessXpath = findTestObject('Object Repository/OR_AppMap/xpath_checksOfSuccess').findPropertyValue('xpath').toString()
										List<WebElement> checksOfSuccess = driver.findElements(By.xpath(checksOfSuccessXpath))
											WebUI.delay(10)

											System.err.println(checksOfSuccess.size())

											if(WebUI.waitForElementVisible(nextButton, 20)==true){
												WebUI.click(nextButton)
												WebUI.delay(5)

												if(WebUI.waitForElementVisible(successfulBuildUpload, 20)==true){
													MesmerLogUtils.markPassed("Build uploaded successfully")

													if(WebUI.waitForElementVisible(closeWindowBtn, 20)==true){
														WebUI.click(closeWindowBtn)
														WebUI.delay(2)
														MesmerLogUtils.markPassed("Build uploaded windows closed")
													}
													else{
														MesmerLogUtils.markFailed("Build uploaded windows is not closed")
													}
												}
												else{
													MesmerLogUtils.markFailed("Build is not uploaded")
												}

											}
											else{
												MesmerLogUtils.markFailed("NEXT button is not visible on build success checks window")
											}


										}
										else{
											MesmerLogUtils.markFailed("Certificate check is failed")
										}
									}else{
										MesmerLogUtils.markFailed("Compatibility check is failed")
									}

								}
								else{
									MesmerLogUtils.markFailed("Similarity check is failed")
								}

							}
							else{
								MesmerLogUtils.markFailed("Build Integrity Check is failed in 3 minutes")
							}
						}
						else{
							MesmerLogUtils.markFailed("Build is still not uploaded in 20 mins")
						}
					}else{
						MesmerLogUtils.markFailed("Sit tight msg not appears")
					}
				}
				else{
					MesmerLogUtils.markFailed("User is not on Build Upload Dialogue")
				}
			}

			else{
				MesmerLogUtils.markFailed("Project List and Create New Project button not found")
			}

		}
		else{
			MesmerLogUtils.markFailed("Unable to click on button build upload")
		}

	}else{
		MesmerLogUtils.markFailed("Unable to click on drop down ")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	WebUI.refresh()
}
