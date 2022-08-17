import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility


//MR-Replay-33 | Behaviour when a test case is deleted when its replay is in progress
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	if(WebUI.waitForElementPresent(testLink, 20)==true){
		WebUI.click(testLink)
		WebUI.delay(2)
		KeywordUtil.markPassed("PASSED: Test Case Opens")
		if(WebUI.waitForElementPresent(btnRerun, 10)==true){
			WebUI.click(btnRerun)
			if(WebUI.waitForElementPresent(txtSelectDevices, 10)==true){
				if(WebUI.waitForElementPresent(deviceList, 10)==true){
					String deviceList = findTestObject('Object Repository/OR_Replay/list_DeviceList').findPropertyValue('xpath').toString()

					List<WebElement> device = driver.findElements(By.xpath(deviceList))
					int deviceListCount = device.size()
					println(deviceListCount)
					if(device.size() > 0 ){

						WebUI.delay(1)
						if(WebUI.waitForElementPresent(btnRun, 10)==true){
							WebUI.click(btnRun)
							WebUI.delay(1)
							if(WebUI.waitForElementPresent(txtConfirmationDlg, 10)==true){
								if(WebUI.waitForElementPresent(btnYes, 10)==true){
									WebUI.click(btnYes)

									if(WebUI.waitForElementPresent(msgQueue, 20)==true){
										WebUI.delay(1)
									//	WebUI.refresh()
										if(WebUI.waitForElementPresent(inProgress, 180)==true){
											WebUI.delay(5)
											if(WebUI.waitForElementPresent(btnOptions, 10)==true){
												WebUI.click(btnOptions)
												if(WebUI.waitForElementPresent(btnDelete, 10)==true){
													WebUI.click(btnDelete)
													if(WebUI.waitForElementPresent(txtConfirmationDelete, 10)==true){
														if(WebUI.waitForElementPresent(btnYes, 10)==true){
															WebUI.click(btnYes)
															if(WebUI.waitForElementPresent(deleteInProgressDialogue, 10)==true){
																if(WebUI.waitForElementPresent(btnYes, 10)==true){
																		WebUI.click(btnClose)
																}else{
																	KeywordUtil.markFailed("FAILED:Unable to click on Yes confirmation delete dialogue while test case in-progress")
																}
															}else{
																KeywordUtil.markFailed("FAILED: Confirmation delete dialogue while test case in-progress")
															}
														}else{
															KeywordUtil.markFailed("FAILED: Unable to click on Yes button Delete confirmation dialogue")
														}
													}else{
														KeywordUtil.markFailed("FAILED: No delete confirmation dialogue appears")
													}
												}else{
													KeywordUtil.markFailed("FAILED: Unable to click on delete button")
												}
											}else{
												KeywordUtil.markFailed("FAILED: Unable to click on options buttons")
											}
										}else{
											KeywordUtil.markFailed("FAILED: Test case not started in 3 mins")
										}
									}else{
										KeywordUtil.markFailed("FAILED: Test Case Lined up in queue")
									}
								}else{
									KeywordUtil.markFailed("FAILED: No Yes button found")
								}
							}else{
								KeywordUtil.markFailed("FAILED: No confirmation dialogue appears")
							}
						}else{
							KeywordUtil.markFailed("FAILED: Unable to click on Run button")
						}
					}else{
						KeywordUtil.markFailed("FAILED: No device available")
					}
				}else{
					KeywordUtil.markWarning("WARNING: Unable to click on Provisioned Device")
				}

			}else{
				KeywordUtil.markFailed("FAILED: Select Devices not found")
			}

		}else{
			KeywordUtil.markFailed("FAILED: Unable to click on Rerun button")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to open test case")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
if(CustomKeywords.'com.mesmer.Utility.stopExecution'()){
	}else{
	KeywordUtil.markFailed("FAILED: Could not stop test case replay")
	}
}