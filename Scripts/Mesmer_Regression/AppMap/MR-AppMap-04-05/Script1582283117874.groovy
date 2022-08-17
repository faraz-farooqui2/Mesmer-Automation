import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.AppMapController
//AppMap-04 | Verify that user should be able to select and upload file through Browes Files link button.

WebUI.delay(2)
WebDriver driver = DriverFactory.getWebDriver()
//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
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

											testCaseFive()


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

//private boolean configureCrawl() {
//	//"AppMap-05 | Verify user runs crawl for 12 hours and it stops automatically after completing its duration"
//	boolean result = false
//	WebDriver driver = DriverFactory.getWebDriver()
//
//	String deviceList = findTestObject('Object Repository/OR_AppMap/list_devicesBuildUpload').findPropertyValue('xpath').toString()
//	List<WebElement> devices = driver.findElements(By.xpath(deviceList))
//
//	System.err.println(devices.size())
//
//	if(devices.size() > 0 ){
//		devices.get(0).click()
//		WebUI.delay(2)
//
//		WebUI.clearText(inputTimeInHrs)
//		WebUI.clearText(inputTimeInMinutes)
//
//		if(WebUI.waitForElementPresent(inputTimeInHrs, 10)){
//			WebUI.setText(inputTimeInHrs, hours)
//			WebUI.delay(1)
//			WebUI.sendKeys(inputTimeInHrs, Keys.chord(Keys.ENTER))
//
//			if(WebUI.waitForElementPresent(inputTimeInMinutes, 10)){
//
//				WebUI.setText(inputTimeInMinutes, minutes)
//				WebUI.delay(1)
//				WebUI.sendKeys(inputTimeInMinutes, Keys.chord(Keys.ENTER))
//
//				if(WebUI.waitForElementPresent(btnFinishAndCrawl, 10)){
//					WebUI.click(btnFinishAndCrawl)
//					result = true
//				}else{
//					KeywordUtil.markFailed("FAILED: Unable to click on Finish and Crawl button")
//				}
//			}else{
//				KeywordUtil.markFailed("FAILED: Time in minutes not set")
//			}
//		}else{
//			KeywordUtil.markFailed("FAILED: Time in hours not set")
//		}
//	}else {
//		KeywordUtil.markFailed("FAILED: No device found")
//	}
//	return result
//}
//
//private boolean reConfigureCrawl(){
//	boolean result = false
//	//AppMap-26 | Verify user can run crawl on default device for the same build
//	CustomKeywords.'com.mesmer.Utility.goToAppMap'()
//
//	if(WebUI.waitForElementPresent(crawlComplete, 600)){
//		if(WebUI.waitForElementVisible(ThreeDotButton,10)==true){
//			WebUI.click(ThreeDotButton)
//			WebUI.delay(1)
//			if(WebUI.waitForElementPresent(ConfigureCrawlOption,5)==true){
//				//2. Click on 'Configure Crawl' option
//				WebUI.click(ConfigureCrawlOption)
//				WebUI.delay(1)
//
//				if(WebUI.waitForElementPresent(deviceSelectedAlready,10)==true){
//					result = true
//				}else{
//					KeywordUtil.markFailed("FAILED: No device Already selected")
//				}
//			}else{
//				KeywordUtil.markFailed("FAILED: Unable to click on three dots button")
//			}
//		}else{
//			KeywordUtil.markFailed("FAILED: Unable to click on configure crawl option")
//		}
//	}else{
//		KeywordUtil.markFailed("FAILED: Crawl is not yet completed")
//	}
//	return result
//}


private boolean testCaseFive(){
	boolean result = false
	if(WebUI.waitForElementVisible(nextButton, 20)==true){
		WebUI.click(nextButton)
		WebUI.delay(5)

		if(AppMapController.getInstance().verifyConfigureCrawlDialogue()){
			if(AppMapController.getInstance().clickNextButton()){
				WebUI.delay(5)
				if(AppMapController.getInstance().verifyDeviceDialogue()){
					if(AppMapController.getInstance().selectDevice()){
						if(AppMapController.getInstance().clickNextButton()){
							if(AppMapController.getInstance().setHrsMintsForCrawlConfig(hours , minutes)){
								result = true
							}else{
							MesmerLogUtils.markFailed("Could not set time")
							}
						}else{
							MesmerLogUtils.markFailed("Could not click on Next button")
						}
					}else{
						MesmerLogUtils.markFailed("Could not click on ready or provisioned device")
					}
				}else{
					MesmerLogUtils.markFailed("Could not verify select device dialogue")
				}
			}else{

				MesmerLogUtils.markFailed("Could not click on Next button")
			}
		}else{
			MesmerLogUtils.markFailed("Configure crawl dialogue not open")
		}
	}
	else{
		MesmerLogUtils.markFailed("NEXT button is not visible on build success checks window")
	}
	return result
}