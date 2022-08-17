package com.mesmer

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.util.List as List
import java.util.ArrayList as ArrayList
import org.openqa.selenium.Keys as Keys
import com.mesmer.MesmerLogUtils
import java.awt.Image
import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import java.awt.image.PixelGrabber
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Instant
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import controllers.AppMapController
import controllers.CreateTestController

//import internal.GlobalVariable as GlobalVariable

public class Utility_AppMap{

	WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	int CountCrawlsInHistory(String CrawlXpaths) {
		List<WebElement> numberOfCrawls = driver.findElements(By.xpath(CrawlXpaths))

		return numberOfCrawls.size()
	}

	@Keyword
	public boolean deleteCrawl(){
		boolean result = false
		List<WebElement> deleteBtn = driver.findElements(By.xpath("//a[@title='Delete Crawl']"))
		TestObject DeleteCrawlConfirmationWindow = findTestObject('Object Repository/OR_AppMap/window_deleteCrawl')
		TestObject YesDeleteCrawl = findTestObject('Object Repository/OR_AppMap/button_YesDeleteCrawl')

		deleteBtn.get(0).click()
		WebUI.delay(2)
		if (WebUI.waitForElementVisible(DeleteCrawlConfirmationWindow, 2) == true) {
			WebUI.click(YesDeleteCrawl)
			KeywordUtil.markPassed("SUCCESS: Delete Crawl Confirmation windows is displayed correctly")
			WebUI.delay(2)
			result = true
		} else {
			KeywordUtil.markFailed("FAILED: Delete Crawl windows is not appeared")
		}
		return result
	}

	@Keyword
	def startCrawl() {

		//		AppMapController.getInstance().startACrawl()

		boolean crawlResult = false
		TestObject ThreeDotButton = findTestObject('Object Repository/OR_AppMap/button_3Dots')
		TestObject RunCrawlOption = findTestObject('Object Repository/OR_AppMap/optionDropdown_RunCrawl')
		TestObject YesReCrawlButton = findTestObject('Object Repository/OR_AppMap/button_YesOnRe-Crawl')
		TestObject CrawlStartedNotification = findTestObject('Object Repository/OR_AppMap/notification_CrawlStarted')
		TestObject StillCrawlingText = findTestObject('Object Repository/OR_AppMap/text_StillCrawling')
		TestObject CrawlText = findTestObject('Object Repository/OR_AppMap/text_Crawling-SP124')
		TestObject CanvasImageAfterStartingCrawl = findTestObject('Object Repository/OR_AppMap/image_canvasAfterStartingCrawl')

		if(WebUI.waitForElementVisible(ThreeDotButton,5)==true){
			WebUI.click(ThreeDotButton)

			if(WebUI.waitForElementVisible(RunCrawlOption,5)==true){
				WebUI.click(RunCrawlOption)
				WebUI.delay(1)

				if(WebUI.verifyElementVisible(YesReCrawlButton)==true){
					WebUI.click(YesReCrawlButton)
					WebUI.delay(4)
					//WebUI.refresh()
					checkCrawlStarted()
					WebUI.delay(2)
					WebUI.refresh()
					//checking Crawl Image
					if(WebUI.waitForElementVisible(CanvasImageAfterStartingCrawl, 30, FailureHandling.CONTINUE_ON_FAILURE)==true || WebUI.waitForElementVisible(CrawlText, 30)==true ){
						KeywordUtil.markPassed("PASSED: Crawl started successfully")
						crawlResult = true
					}
					else{
						KeywordUtil.markFailed("FAILED: Canvas image is not displayed and Crawl is not started")
					}

				}
				else{
					KeywordUtil.logInfo("[MESMER]: Yes Crawl button is not displayed")
				}
			}
			else{
				KeywordUtil.logInfo("[MESMER]: Run Crawl button is not displayed")
			}
		}
		else{
			KeywordUtil.logInfo("[MESMER]: Run Crawl option is not displayed")
		}

		return crawlResult;
	}

	@Keyword
	def configureCrawl(){
		TestObject ThreeDotButton = findTestObject('Object Repository/OR_AppMap/button_3Dots')
		TestObject btnConfigureCrawl = findTestObject('Object Repository/OR_AppMap/btn_configureCrawl')
		TestObject InputTimeInMinutes = findTestObject('Object Repository/OR_AppMap/input_TimeInMinutes')
		TestObject InputTimeInHours = findTestObject('Object Repository/OR_AppMap/input_timeInHrs')
		TestObject SaveConfigButton =  findTestObject('Object Repository/OR_AppMap/button_SaveConfigCrawl')
		TestObject ConfigSavedNotification =findTestObject('Object Repository/OR_AppMap/notification_ConfigSavedSuccessfully')
		String timeInMinutes = 06
		String timeInHrs = 00
		if(WebUI.waitForElementPresent(ThreeDotButton, 20)==true){
			WebUI.click(ThreeDotButton)
			if(WebUI.waitForElementPresent(btnConfigureCrawl, 20)==true){
				WebUI.click(btnConfigureCrawl)
				WebUI.delay(2)
				//WebUI.click(InputTimeInHours)
				WebUI.clearText(InputTimeInHours)
				WebUI.delay(1)
				WebUI.sendKeys(InputTimeInHours, timeInHrs)
				WebUI.sendKeys(InputTimeInHours, Keys.chord(Keys.ENTER))
				WebUI.delay(2)
				//WebUI.click(InputTimeInMinutes)
				WebUI.clearText(InputTimeInMinutes)
				WebUI.delay(1)
				WebUI.sendKeys(InputTimeInMinutes, timeInMinutes)
				WebUI.sendKeys(InputTimeInMinutes, Keys.chord(Keys.ENTER))
				WebUI.delay(1)
				if(WebUI.waitForElementPresent(SaveConfigButton, 2)==true){
					WebUI.click(SaveConfigButton)
					WebUI.delay(1)
					if(WebUI.waitForElementVisible(ConfigSavedNotification,15)==true){
						KeywordUtil.markPassed("PASSED: Configuaration is saved successfully")
						WebUI.delay(2)
					}else{
						KeywordUtil.markFailed("PASSED: Unable to save configuaration")
					}
				}else{
					KeywordUtil.markFailed("PASSED: Unable to click on save button")
				}
			}else{
				KeywordUtil.markFailed("PASSED: Unable to click on Configure Crawl Button")
			}
		}else{
			KeywordUtil.markFailed("PASSED: Unable to click on 3 dots button")
		}
	}



	@Keyword
	def checkCrawlStarted(){

		//		AppMapController.getInstance().checkCrawlStarted()

		TestObject text_preparingDevice = findTestObject('Object Repository/OR_AppMap/text_preparingDevice')
		TestObject text_deviceRetrieved = findTestObject('Object Repository/OR_AppMap/text_DeviceRetrieved')
		TestObject text_deviceConnected = findTestObject('Object Repository/OR_AppMap/text_DeviceConnected')
		TestObject text_deviceConfigured = findTestObject('Object Repository/OR_AppMap/text_Configured')
		TestObject text_buildDownloaded = findTestObject('Object Repository/OR_AppMap/text_BuildDownloaded')
		TestObject text_buildIntsalled = findTestObject('Object Repository/OR_AppMap/text_buildInstalled')
		//		TestObject text_startingCrawl = findTestObject('Object Repository/OR_AppMap/text_startingCrawl')
		//		TestObject text_stillCrawling = findTestObject('Object Repository/OR_AppMap/text_StillCrawling')
		TestObject text_CrawlInProgress = findTestObject('Object Repository/OR_AppMap/text_CrawlingInProgress')




		if(WebUI.waitForElementVisible(text_preparingDevice, 60)==true){
			KeywordUtil.logInfo("[MESMER]: Preparing Device text is displayed")
			if(WebUI.waitForElementVisible(text_deviceRetrieved, 60)==true){
				KeywordUtil.logInfo("[MESMER]: Device is Retrieved")
				if(WebUI.waitForElementVisible(text_deviceConnected, 60)==true){
					KeywordUtil.logInfo("[MESMER]: Device is Connected successfully")
					if(WebUI.waitForElementVisible(text_deviceConfigured, 60)==true){
						KeywordUtil.logInfo("[MESMER]: Device is Configured successfully")
						if(WebUI.waitForElementVisible(text_buildDownloaded, 60)==true){
							KeywordUtil.logInfo("[MESMER]: Build downloaded successfully on device")
							if(WebUI.waitForElementVisible(text_buildIntsalled, 60)==true){
								KeywordUtil.logInfo("[MESMER]: Build is installed successfully on device")
								if(WebUI.waitForElementVisible(text_CrawlInProgress, 60)==true){
									KeywordUtil.markPassed("PASSED: Crawl started successfully")
								}
								else{
									KeywordUtil.markWarning("FAILED: Crawling is not appeared")
								}
							}else{
								KeywordUtil.markFailed("FAILED: Build installed is not completed")
							}

						}
						else{
							KeywordUtil.markFailed("FAILED: Build downloaded is not completed")
						}

					}
					else{
						KeywordUtil.markFailed("FAILED: Device Configured is not completed")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: Device Connected is not completed")
				}

			}else{
				KeywordUtil.markFailed("FAILED: Device Retrieved is not Completed")
			}

		}else{

		}
	}

	@Keyword
	public boolean stopCrawlFromButton(){

		//		AppMapController.getInstance().stopACrawl()

		boolean result = false
		TestObject textCrawlCompleted = findTestObject('Object Repository/OR_AppMap/txt_CrawlComplete')
		TestObject stopCrawlButton = findTestObject('Object Repository/OR_AppMap/button_StopCrawl')
		TestObject confirmationWindow_StopCrawl = findTestObject('Object Repository/OR_AppMap/window_stopCrawlConfirmationWindow')
		TestObject yesButton_StopCrawl = findTestObject('Object Repository/OR_AppMap/button_YesOnRe-Crawl')

		if(WebUI.waitForElementPresent(stopCrawlButton, 10)==true){
			WebUI.click(stopCrawlButton)

			if(WebUI.waitForElementPresent(confirmationWindow_StopCrawl, 10)==true){
				if(WebUI.waitForElementPresent(yesButton_StopCrawl, 10)==true){
					WebUI.click(yesButton_StopCrawl)

					if(WebUI.waitForElementPresent(textCrawlCompleted, 60)==true){
						MesmerLogUtils.logInfo("Crawl stopped successfully")
						result = true;

					}else{
						MesmerLogUtils.logInfo("Unable to stop Crawl")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: Unable to click on Yes button on Stop Crawl window")
				}

			}
			else{
				KeywordUtil.markFailed("FAILED: Confirmation window of Stop Crawl is not displayed")
			}
		}
		else{
			KeywordUtil.markWarning("Warning: Stop Crawl button is not displayed")
		}
		return result;
	}

	@Keyword
	def checkSearchinLogs(){
		TestObject optionLogsSettings = findTestObject('Object Repository/OR_AppMap/option_logsSettings')
		TestObject pauseOption = findTestObject('Object Repository/OR_AppMap/dropdown_SettingsPauseOption')
		TestObject filterOption = findTestObject('Object Repository/OR_AppMap/option_FilterLogs')
		TestObject searchField = findTestObject('Object Repository/OR_AppMap/input_filterSearch')
		TestObject pauseText = findTestObject('Object Repository/OR_AppMap/text_deviceISpaused')


		String logsRow = "//div[contains(@class,'logRow')]"

		if(WebUI.waitForElementVisible(optionLogsSettings, 2)==true){
			WebUI.click(optionLogsSettings)
			WebUI.delay(1)

			if(WebUI.waitForElementVisible(pauseOption, 2)==true){
				WebUI.click(pauseOption)
				WebUI.delay(1)
				if(WebUI.waitForElementVisible(pauseText, 2, FailureHandling.CONTINUE_ON_FAILURE)==true){
					WebUI.click(optionLogsSettings)

					if(WebUI.waitForElementVisible(filterOption, 2)==true){
						WebUI.click(filterOption)

						if(WebUI.waitForElementVisible(searchField, 2)==true){
							WebUI.setText(searchField, "se")

							List<WebElement> logRowsinSearch = driver.findElements(By.xpath(logsRow))
							int countLogs = logRowsinSearch.size()
							println("Number of Log rows are = " + countLogs)
							if(countLogs>0){
								for(int i=0; i<logRowsinSearch.size(); i++){

									if(logRowsinSearch.get(i).getText().contains("se")){
										KeywordUtil.markPassed("PASSED: All row contains filter text")
									}
									else{
										KeywordUtil.markWarning("WARNING: No logs available")
									}

								}

							}
							else{
								KeywordUtil.markWarning("Warning: No log rows available")
							}
						}
						else{
							KeywordUtil.markFailed("FAILED: Search field is not displayed")
						}
					}
					else{
						KeywordUtil.markFailed("FAILED: Filter option is not displayed")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: Pause text is not displayed")
				}

			}
			else{
				KeywordUtil.markFailed("FAILED: Pause option is not displayed")
			}


		}
		else{
			KeywordUtil.markFailed("FAILED: Logs option in Settings is not displayed")
		}
	}

	@Keyword
	public boolean checkListElementsOfCrawl() {
		boolean result = false

		List<WebElement> initiated_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'Initiated')]"))

		List<WebElement> device_CrawlHistory = driver.findElements(By.xpath("//div[@class='label' and contains(text(),'Device')]"))

		List<WebElement> crawlDuration_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'Crawl Duration')]"))

		List<WebElement> screensCrawled_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'Screens Crawled')]"))

		List<WebElement> newChangedScreens_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'New/Changed Screens')]"))

		List<WebElement> recommendedTestCreated_CrawlHistory = driver.findElements(By.xpath("//span[contains(text(),'Recommended Tests Created')]"))

		List<WebElement> appCrashes_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'App Crashes')]"))

		TestObject noBuildAssociated = findTestObject('Object Repository/OR_AppMap/CrawlHistory/info_noCrawlHistory')

		//		List<WebElement> maxHeapUse_CrawlHistory = driver.findElements(By.xpath("//div[contains(text(),'Max Heap Use')]"))

		//		MesmerLogUtils.logInfo("initiated_CrawlHistory = " + initiated_CrawlHistory.size())
		//		MesmerLogUtils.logInfo("device_CrawlHistory = " + device_CrawlHistory.size())
		//		MesmerLogUtils.logInfo("crawlDuration_CrawlHistory = " + crawlDuration_CrawlHistory.size())
		//		MesmerLogUtils.logInfo("screensCrawled_CrawlHistory = " + screensCrawled_CrawlHistory.size())
		//		MesmerLogUtils.logInfo("newChangedScreens_CrawlHistory = " + newChangedScreens_CrawlHistory.size())
		//		MesmerLogUtils.logInfo("recommendedTestCreated_CrawlHistory = " + recommendedTestCreated_CrawlHistory.size())
		//		MesmerLogUtils.logInfo("appCrashes_CrawlHistory = " + appCrashes_CrawlHistory.size())

		if(WebUI.waitForElementPresent(noBuildAssociated, 10)){
			MesmerLogUtils.logInfo('No crawl is associated with this build')
			result = false
		}else{

			if(initiated_CrawlHistory != null && initiated_CrawlHistory.size()> 0 ){

				if(initiated_CrawlHistory.get(0).displayed == true){
					MesmerLogUtils.markPassed("PASSED: Initiated label for Crawl History is displayed")


					if(device_CrawlHistory != null && device_CrawlHistory.size()> 0 ){
						if (device_CrawlHistory.get(0).displayed == true) {
							MesmerLogUtils.markPassed('Device label for Crawl History is displayed')


							if(crawlDuration_CrawlHistory != null && crawlDuration_CrawlHistory.size()> 0 ){
								if (crawlDuration_CrawlHistory.get(0).displayed == true) {
									MesmerLogUtils.markPassed('Crawl Duration label for Crawl History is displayed')


									if(screensCrawled_CrawlHistory != null && screensCrawled_CrawlHistory.size()> 0 ){
										if (screensCrawled_CrawlHistory.get(0).displayed == true) {
											MesmerLogUtils.markPassed('Screens Crawled label for Crawl History is displayed')


											if(newChangedScreens_CrawlHistory != null && newChangedScreens_CrawlHistory.size()> 0 ){
												if (newChangedScreens_CrawlHistory.get(0).displayed == true) {
													MesmerLogUtils.markPassed('New/Changed Screens label for Crawl History is displayed')


													if(recommendedTestCreated_CrawlHistory != null && recommendedTestCreated_CrawlHistory.size()> 0 ){
														if (recommendedTestCreated_CrawlHistory.get(0).displayed == true) {
															MesmerLogUtils.markPassed('Recommended Tests Created label for Crawl History is displayed')


															if(appCrashes_CrawlHistory != null && appCrashes_CrawlHistory.size()> 0 ){
																if (appCrashes_CrawlHistory.get(0).displayed == true) {
																	MesmerLogUtils.markPassed('App Crashes label for Crawl History is displayed')
																	result = true
																} else {
																	MesmerLogUtils.logInfo('App Crashes label for Crawl History is not displayed')
																}
															}else{
																MesmerLogUtils.logInfo("WARNING: App Crashes Crawled History is Null")
															}

															//		if(maxHeapUse_CrawlHistory.get(0).displayed == true) {
															//			KeywordUtil.markPassed('Max Heap Use label for Crawl History is displayed')
															//		} else {
															//			KeywordUtil.markWarning('Max Heap Use label for Crawl History is not displayed')
															//		}
														} else {
															MesmerLogUtils.logInfo('Recommended Tests Created label for Crawl History is not displayed')
														}
													}else{
														MesmerLogUtils.logInfo("WARNING: Recommended Test Created Crawled History is Null")
													}
												} else {
													MesmerLogUtils.logInfo('New/Changed Screens label for Crawl History is not displayed')
												}
											}else{
												MesmerLogUtils.logInfo("WARNING: New Changed Screen Crawled History is Null")
											}
										} else {
											MesmerLogUtils.logInfo('Screens Crawled label for Crawl History is not displayed')
										}
									}else{
										MesmerLogUtils.logInfo("WARNING: Screen Crawled History is Null")
									}
								} else {
									MesmerLogUtils.logInfo('Crawl Duration label for Crawl History is not displayed')
								}
							}else{
								MesmerLogUtils.logInfo("WARNING: Crawl Duration History is Null")
							}
						} else {
							MesmerLogUtils.logInfo('Device label for Crawl History is not displayed')
						}
					}else{
						MesmerLogUtils.logInfo("WARNING: Device Crawl History is Null")
					}
				}
				else{
					MesmerLogUtils.logInfo("WARNING: Initiated label for Crawl History is not displayed")
				}
			}else{
				MesmerLogUtils.logInfo("WARNING: Initiated Crawl History is Null")
			}
		}
		return result
	}

	@Keyword
	def goToConfigureCrawl(){

		AppMapController.getInstance().checkGoToConfigCrawl()
	}



}



