import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController



WebDriver driver = DriverFactory.getWebDriver()
try{

	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)

	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		WebUI.delay(2)

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(WebUI.waitForElementPresent(projectDropDown, 10)){
			WebUI.click(projectDropDown)
			WebUI.delay(5)

			String buildListXpath = findTestObject('Object Repository/OR_BuildUpload/list_buildList').findPropertyValue('xpath').toString()
			List<WebElement> buildList = driver.findElements(By.xpath(buildListXpath))
			//	TestObject buildXpath = findTestObject('Object Repository/OR_Common/xpath_buildSelection')
			//	TestObject buildNumberSelection = CustomKeywords.'com.mesmer.Utility.buildSelection'(buildXpath, buildNumber1)
			//	WebUI.click(buildNumberSelection)
			if(buildList.size() > 1 ){
				CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
				if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){

					List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())

					if(virtualDevicesList != null && virtualDevicesList.size() >=1){
						String deviceNameElement = Utility.selectDeviceAndSetDeviceParam(deviceUDID.toString())
						if(CreateTestController.getInstance().clickNextBtn()){
							if(CreateTestController.getInstance().clickApplyBtn()){
								if(CreateTestController.getInstance().clickStartBtn()){
									TestObject deviceRetrieved = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_deviceRetrieve')
									if(WebUI.waitForElementPresent(deviceRetrieved, 90)){

										TestObject connectToDevice = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_connectedToDevice')
										if(WebUI.waitForElementPresent(connectToDevice, 90)){

											TestObject deviceConfigured = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_deviceConfigured')
											if(WebUI.waitForElementPresent(deviceConfigured, 90)){

												TestObject buildDownload = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_buildDownloaded')
												if(WebUI.waitForElementPresent(buildDownload, 90)){

													TestObject buildInstalled = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_buildInstalled')
													if(WebUI.waitForElementPresent(buildInstalled, 90)){

														TestObject startingStream = findTestObject('Object Repository/OR_CreateNewTestCase/infoMsg_startingStream')
														if(WebUI.waitForElementPresent(startingStream, 90)){
															MesmerLogUtils.logInfo("Starting Stream")
															if(checkInstallationTime ()){
															}else{
																MesmerLogUtils.markFailed("checkInstallationTime failed")
															}
														}else{
															MesmerLogUtils.markFailed("Unable to start stream on device")
														}
													}else{
														MesmerLogUtils.markFailed("Unable to install build on device")
													}
												}else{
													MesmerLogUtils.markFailed("Unable to download build on device")
												}
											}else{
												MesmerLogUtils.markFailed("Unable to configure device")
											}

										}else{
											MesmerLogUtils.markFailed("Unable to connect to device")
										}
									}else{
										MesmerLogUtils.markFailed("Unable to retrieve device")
									}

									//	}else{
									//	MesmerLogUtils.markFailed("Device checks failed")
									//	}

								}else{
									MesmerLogUtils.markFailed("Unable to click on start button")
								}
							}else{
								MesmerLogUtils.markFailed("Unable to click on apply button")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to click on next button")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to click on ready device")
					}
				}
			}else{
				MesmerLogUtils.markFailed("Only a single build exists in a project " +  "  : "  + buildList.size())
			}
		}else{
			MesmerLogUtils.markFailed("Unable to click on project drop down 1")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}catch(Exception e){
	e.printStackTrace()
}

private boolean checkInstallationTime () {
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
	if(CustomKeywords.'com.mesmer.Utility.goToTestResults'()){
	if(WebUI.waitForElementPresent(projectDropDown, 10)){
		WebUI.click(projectDropDown)
		WebUI.delay(5)
		String buildListXpath2 = findTestObject('Object Repository/OR_BuildUpload/list_buildList').findPropertyValue('xpath').toString()
		List<WebElement> buildList2 = driver.findElements(By.xpath(buildListXpath2))

		String buildAppNameXpath = findTestObject('Object Repository/OR_BuildUpload/build_appName').findPropertyValue('xpath').toString()
		List<WebElement> buildAppName = driver.findElements(By.xpath(buildAppNameXpath))

		String buildAppDTXpath = findTestObject('Object Repository/OR_BuildUpload/build_appDT').findPropertyValue('xpath').toString()
		List<WebElement> buildAppDT = driver.findElements(By.xpath(buildAppDTXpath))

		for (WebElement webElement : buildAppName.get(1)) {
			String buildAppNameText = webElement.getText();
			MesmerLogUtils.logInfo("App Name at Index 1 " + " : " + "  "  +  buildAppNameText)
		}

		for (WebElement webElement : buildAppDT.get(1)) {
			String buildAppDTText = webElement.getText();
			MesmerLogUtils.logInfo("App Date Time at Index 1 " + " : " + "  "  +  buildAppDTText)
		}

		buildList2.get(1).click()
		CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
		if(CreateTestController.getInstance().checkIfCreateNewTestScreenOpen()){

			List<WebElement> virtualDevicesList = Utility.getAvailableDevicesList(Device.toString())
			if(virtualDevicesList != null && virtualDevicesList.size() >=1){
				String deviceNameElement = Utility.selectDeviceAndSetDeviceParam(deviceUDID.toString())
				if(CreateTestController.getInstance().clickNextBtn()){
					if(CreateTestController.getInstance().clickApplyBtn()){
						if(CreateTestController.getInstance().clickStartBtn()){

							if(WebUI.waitForElementPresent(deviceRetrieved, 60)){

								if(WebUI.waitForElementPresent(connectToDevice, 60)){

									if(WebUI.waitForElementPresent(deviceConfigured, 60)){

										if(WebUI.waitForElementPresent(buildDownload, 5)){

											if(WebUI.waitForElementPresent(buildInstalled, 5)){
												MesmerLogUtils.markFailed("Build Already Installed on the device")
												if(WebUI.waitForElementPresent(startingStream, 5)){
													MesmerLogUtils.logInfo("Starting Stream")
													result = true
												}else{
													MesmerLogUtils.markFailed("Unable to start stream on device")
												}
											}else{
												MesmerLogUtils.logInfo("Installing new build on device")
											}
										}else{
											MesmerLogUtils.markFailed("Unable to download build on device")
										}
									}else{
										MesmerLogUtils.markFailed("Unable to configure device")
									}

								}else{
									MesmerLogUtils.markFailed("Unable to connect to device")
								}
							}else{
								MesmerLogUtils.markFailed("Unable to retrieve device")
							}
						}else{
							MesmerLogUtils.markFailed("Unable to click on start button")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to click on apply button")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to click on next button")
				}

			}else{
				MesmerLogUtils.markFailed("Unable to click on ready device")
			}
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on project drop down 2 attepmt")
	}
	}else{
	MesmerLogUtils.markFailed("Unable to navigate to test result page")
	}

	return result
}