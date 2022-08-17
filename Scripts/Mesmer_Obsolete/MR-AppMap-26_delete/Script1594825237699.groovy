import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
//AppMap-04 | Verify that user should be able to select and upload file through Browes Files link button.

WebUI.delay(2)
WebDriver driver = DriverFactory.getWebDriver()
//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("")
try{
	if(WebUI.waitForElementVisible(clickProjectdropdown, 10) ==true){
		WebUI.click(clickProjectdropdown)
		//		WebUI.click(projectSlideArrow)
		WebUI.delay(2)
		if(WebUI.waitForElementVisible(buttonBuildUpload,10) == true){
			//1. Click on Upload new build from the Project list on the top left corner of the mesmer console
			WebUI.click(buttonBuildUpload)
			println("Build upload button is clicked")
			WebUI.delay(2)

			//Verification - Upload your latest build window will appear
			if(WebUI.waitForElementVisible(uploadAppBuild, 20, FailureHandling.CONTINUE_ON_FAILURE)==true){

				//2. Drag/Browse the required app build with valid app login credentials (Optional)
				if(WebUI.verifyElementVisible(lblBrowseToSelect) == true){
					//	WebUI.click(btnBrowseToSelect)

					// Change the file path with absolute path.
					//					//Enable this for Windows
					//WebUI.uploadFile(btnBrowseToSelect, 'C:\\Users\\Mesmer\\Desktop\\Ring.apk', FailureHandling.STOP_ON_FAILURE)

					//Enable this for MAC
					WebUI.uploadFile(btnBrowseToSelect, '/Users/mesmer/KatalonStudio/MesmerAutomationProject/Resources/AndroidBuilds/DQ_Android-2.2.5(1974)-preProduction-release.apk', FailureHandling.STOP_ON_FAILURE)

					WebUI.delay(4)

					//check Sit Tight text when build upload in progress
					if(WebUI.waitForElementVisible(sitTightText, 5)==true){
						KeywordUtil.markPassed("PASSED: Build upload is in progress")
					}

					//Check Integrity after build just uploaded
					if(WebUI.waitForElementVisible(buildIntegrityCheck, 180)==true){
						//Check Similarity on build upload process
						if(WebUI.waitForElementVisible(buildSimiliarityCheck, 20)==true){
							//Check Compatibility on build upload process
							if(WebUI.waitForElementVisible(compatibilityCheck, 20)==true){
								//Check Certificate on build upload process
								if(WebUI.waitForElementVisible(certificateCheck, 20)==true){
									KeywordUtil.markPassed("PASSED: All checks are marked successfully")

									String checksOfSuccessXpath = findTestObject('Object Repository/OR_AppMap/xpath_checksOfSuccess').findPropertyValue('xpath').toString()
									List<WebElement> checksOfSuccess = driver.findElements(By.xpath(checksOfSuccessXpath))
									WebUI.delay(3)

									System.err.println(checksOfSuccess.size())

									if(WebUI.waitForElementVisible(nextButton, 5)==true){
										WebUI.click(nextButton)


										if(WebUI.waitForElementVisible(successfulBuildUpload, 5)==true){
											KeywordUtil.markPassed("PASSED: Build uploaded successfully")

											configureCrawl()

											reConfigureCrawl()
										}
										else{
											KeywordUtil.markFailed("FAILED: Build is not uploaded")
										}

									}
									else{
										KeywordUtil.markFailed("FAILED: NEXT button is not visible on build success checks window")
									}


								}
								else{
									KeywordUtil.markFailed("FAILED: Certificate check is failed")
								}
							}else{
								KeywordUtil.markFailed("FAILED: Compatibility check is failed")
							}

						}
						else{
							KeywordUtil.markFailed("FAILED: Similarity check is failed")
						}

					}
					else{
						KeywordUtil.markFailed("FAILED: Build Integrity Check is failed in 3 minutes")
					}
				}


				else{
					KeywordUtil.markFailed("FAILED: User is not on Build Upload Dialogue")
				}
			}

			else{
				KeywordUtil.markFailed("FAILED: Project List and Create New Project button not found")
			}

		}
		else{
			KeywordUtil.markFailed("FAILED: Project drop down not found")
		}

	}
}
catch(Exception e){
	e.printStackTrace()
}finally{
WebUI.refresh()
}

def configureCrawl() {
	//"AppMap-05 | Verify user runs crawl for 12 hours and it stops automatically after completing its duration"
	WebDriver driver = DriverFactory.getWebDriver()
	
	String deviceList = findTestObject('Object Repository/OR_AppMap/list_devicesBuildUpload').findPropertyValue('xpath').toString()
	List<WebElement> devices = driver.findElements(By.xpath(deviceList))

	System.err.println(devices.size())

	if(devices.size() > 0 ){
		devices.get(0).click()
		WebUI.delay(2)
		if(WebUI.waitForElementPresent(inputTimeInHrs, 10)){
			WebUI.clearText(inputTimeInHrs)
			WebUI.setText(inputTimeInHrs, "00")
			WebUI.delay(1)
			WebUI.sendKeys(inputTimeInHrs, Keys.chord(Keys.ENTER))

			if(WebUI.waitForElementPresent(inputTimeInMinutes, 10)){
				WebUI.clearText(inputTimeInMinutes)
				WebUI.setText(inputTimeInMinutes, "06")
				WebUI.delay(1)
				WebUI.sendKeys(inputTimeInMinutes, Keys.chord(Keys.ENTER))

				if(WebUI.waitForElementPresent(btnFinishAndCrawl, 10)){
					WebUI.click(btnFinishAndCrawl)
				}else{
					KeywordUtil.markFailed("FAILED: Unable to click on Finish and Crawl button")
				}
			}else{
				KeywordUtil.markFailed("FAILED: Time in minutes not set")
			}
		}else{
			KeywordUtil.markFailed("FAILED: Time in hours not set")
		}
	}else {
		KeywordUtil.markFailed("FAILED: No device found")
	}
}

def reConfigureCrawl(){
	//AppMap-26 | Verify user can run crawl on default device for the same build
	CustomKeywords.'com.mesmer.Utility.goToAppMap'()

	if(WebUI.waitForElementPresent(crawlComplete, 720)){
		if(WebUI.waitForElementVisible(ThreeDotButton,60)==true){
			WebUI.click(ThreeDotButton)
			if(WebUI.waitForElementPresent(ConfigureCrawlOption,60)==true){
				//2. Click on 'Configure Crawl' option
				WebUI.click(ConfigureCrawlOption)
				WebUI.delay(1)

				if(WebUI.waitForElementPresent(deviceSelectedAlready,60)==true){
					
				}else{
					KeywordUtil.markFailed("FAILED: No device Already selected")
				}
			}else{
				KeywordUtil.markFailed("FAILED: Unable to click on three dots button")
			}
		}else{
			KeywordUtil.markFailed("FAILED: Unable to click on configure crawl option")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Crawl is not yet completed")
	}
}