import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

//MS-AppMap-07 | Verify that user is able to see Info and Crawl History from the 'Info' and 'Crawl History' options and they are working fine.

WebDriver driver = DriverFactory.getWebDriver()


try {
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)){

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		if(CustomKeywords.'com.mesmer.Utility.goToAppMap'()){


			//1. Click on 'Info' icon on the vertical tools bar on the right side of AppMap
			if(WebUI.waitForElementNotPresent(noAppMapAvailableText, 10)==true){
				if(WebUI.waitForElementPresent(infoBtn, 2)==true){
					WebUI.click(infoBtn)

					if(WebUI.waitForElementPresent(infoHeadingText, 20)==true){


						//VERIFICATION -- An info modal will open with info like Last Crawl, Device, Crawl Duration of the last crawl triggered.
						if(WebUI.verifyElementPresent(lastCrawlInfo, 10)==true){
							MesmerLogUtils.markPassed('Last Crawl Info is displayed correctly')


							if(WebUI.verifyElementPresent(deviceInfo, 10)==true ){
								MesmerLogUtils.markPassed('Device Info is displayed correctly')

								if(WebUI.verifyElementPresent(crawlDurationInfo, 10)==true){
									MesmerLogUtils.markPassed('Crawl Duration is displayed correctly')

									//2. Click on 'History' icon under 'Info' icon on vertical tools bar.
									if (WebUI.waitForElementPresent(historyIcon, 20) == true) {
										MesmerLogUtils.markPassed('History Icon is displayed correctly')

										WebUI.click(historyIcon)

										//		VERIFICATION
										//"A modal will open with all the crawls history ran on the build with info like
										//		Initiated, Device, Crawl duration, Screens Crawled, New/Changed screens, Recommended tests created,
										//		App crashes, Max. heap use with Delete Crawl button in the botton of each crawl history.
										//Details like App crashes, Recommended tests created for the crawls should match with their respective recommended test pages.
										if (WebUI.waitForElementPresent(textCrawlHistory, 10) == true) {

											//Count Number of Crawls in History
											String countCrawls = findTestObject('Object Repository/OR_AppMap/xpath_crawlCounts').findPropertyValue('xpath').toString()
											int noOfCrawlsinHistory = CustomKeywords.'com.mesmer.Utility_AppMap.CountCrawlsInHistory'(countCrawls)

											//////////////////////////////////
											MesmerLogUtils.logInfo('Number of Crawls in HIstory => ' + noOfCrawlsinHistory)

											WebUI.delay(2)

											if(CustomKeywords.'com.mesmer.Utility_AppMap.checkListElementsOfCrawl'()){

												//DELETE CRAWL VERIFICATION::
												String numOfCrawlsInHistory =findTestObject('Object Repository/OR_AppMap/xpath_noOfCrawlInHistory').findPropertyValue('xpath').toString()

												String crawlInitiatedTimeXpath =findTestObject('Object Repository/OR_AppMap/xpath_crawlInitiatedTime').findPropertyValue('xpath').toString()

												List<WebElement> crawlInitiatedTime = driver.findElements(By.xpath(crawlInitiatedTimeXpath))
												//	String crawl1Time = ""
												if(crawlInitiatedTime != null && crawlInitiatedTime.size()> 0){
													String crawl1Time = crawlInitiatedTime.get(0).getText()
													MesmerLogUtils.logInfo(crawl1Time)
													WebUI.delay(1)

													int crawlbeforedeletion = CustomKeywords.'com.mesmer.Utility_AppMap.CountCrawlsInHistory'(numOfCrawlsInHistory)
													MesmerLogUtils.logInfo("CRAWL COUNT BEFORE DELETION = " + crawlbeforedeletion)
													if(CustomKeywords.'com.mesmer.Utility_AppMap.deleteCrawl'()){
														int crawlAfterDeletion = CustomKeywords.'com.mesmer.Utility_AppMap.CountCrawlsInHistory'(numOfCrawlsInHistory)
														MesmerLogUtils.logInfo("CRAWL COUNT AFTER DELETION = " + crawlAfterDeletion)

														List<WebElement> crawlInitiatedTime2 = driver.findElements(By.xpath(crawlInitiatedTimeXpath))
														if(crawlInitiatedTime2 != null && crawlInitiatedTime2.size()> 0){
															String crawl2Time = crawlInitiatedTime2.get(0).getText()
															MesmerLogUtils.logInfo(crawl2Time)
															if(!crawl1Time.equalsIgnoreCase(crawl2Time)){
																MesmerLogUtils.markPassed("Crawl is deleted successfully")
																WebUI.delay(1)
																if (noOfCrawlsinHistory >= 5) {
																	MesmerLogUtils.logInfo('More than 5 crawls are available in History')

																	if (WebUI.waitForElementPresent(loadMoreButton, 5) == true) {
																		WebUI.scrollToElement(loadMoreButton, 2)

																		WebUI.click(loadMoreButton)

																		WebUI.delay(2)

																		String noOfCrawls1Xpath =findTestObject('Object Repository/OR_AppMap/xpath_noOfCrawls1').findPropertyValue('xpath').toString()
																		List<WebElement> noOfCrawls1 = driver.findElements(By.xpath(noOfCrawls1Xpath))

																		MesmerLogUtils.logInfo(noOfCrawls1.size())

																		if(noOfCrawls1.size() > 5) {
																			MesmerLogUtils.markPassed('Load more button is clicked and more than 5 Crawls are displayed correctly')
																			//						this.deleteCrawl()

																		} else {
																			MesmerLogUtils.markFailed('Load more button is not clicked and more than 5 Crawls are not displayed correctly')
																		}
																	} else {
																		MesmerLogUtils.markFailed('Load More button is not available')
																	}
																}else {
																	MesmerLogUtils.markFailed('[DATA ISSUE ]: Crawl count is lesser than 5')
																}
															}else{

																MesmerLogUtils.markFailed("Crawl is not deleted")
															}
														}else{
															MesmerLogUtils.markFailed('No crawl initiated time 2')
														}
													}else{
														MesmerLogUtils.markFailed('Could not delete a crawl')
													}
												}else{
													MesmerLogUtils.markFailed('No crawl initiated time 1')
												}
											} else {
												MesmerLogUtils.markFailed('[DATA ISSUE] No crawl is associated with this build')
											}
										} else {
											MesmerLogUtils.markFailed('Crawl History text is not displayed')
										}
									} else {
										MesmerLogUtils.markFailed('Crawl History text is not displayed')
									}
								}
								else{
									MesmerLogUtils.markFailed('Could not click on Info button')
								}

							}else{
								MesmerLogUtils.markFailed('Crawl Duration is not displayed correctly')
							}

						}else{
							MesmerLogUtils.markFailed('Device Info is not displayed correctly')
						}
					}
					else{
						MesmerLogUtils.markFailed('Last Crawl Info is not displayed correctly')
					}
				}
				else{
					MesmerLogUtils.markFailed('INFO heading is not displayed correctly')
				}
			}else{
				MesmerLogUtils.markFailed("[DATA ISSUE] NO APP MAP AVAILABLE")

			}
		}else{
			MesmerLogUtils.markFailed("Could not click on App Map or could not verify App Map page" )
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch (Exception e) {
	e.printStackTrace()
}
finally {
}


