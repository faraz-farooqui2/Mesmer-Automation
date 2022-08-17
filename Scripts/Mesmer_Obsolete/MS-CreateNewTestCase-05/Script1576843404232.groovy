import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController

/*
 * MS-Create a new Test Case-05 | Verify that user can change the element selected during recording using Object library feature i.e CITL
 */
//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
WebUI.delay(2)

try{
	WebElement searchedReadyDevice = Utility.selectDevice(Device.toString())
	if(searchedReadyDevice != null){
		searchedReadyDevice.click()
		startRecordingAndPerformActions()
	}else{
		MesmerLogUtils.markFailed("Unable to click on ready device")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	//	WebUI.refresh()
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

def startRecordingAndPerformActions(){
	
	WebDriver driver = DriverFactory.getWebDriver()
	if(WebUI.waitForElementPresent(deviceRetrieved, 60)){

		if(WebUI.waitForElementPresent(connectToDevice, 60)){

			if(WebUI.waitForElementPresent(deviceConfigured, 60)){

				if(WebUI.waitForElementPresent(buildDownload, 60)){

					if(WebUI.waitForElementPresent(buildInstalled, 60)){

						if(WebUI.waitForElementPresent(startingStream, 60)){
							WebUI.delay(10)
							
							if(WebUI.waitForElementPresent(divDevice, 60)){
								WebUI.delay(10)
								WebUI.clickOffset(divDevice,15,65)

							if(WebUI.waitForElementVisible(btnObjectLibrary, 30)){
								WebUI.click(btnObjectLibrary)
								WebUI.delay(3)
								// Set Object Name
								if(WebUI.waitForElementVisible(inputObjectName, 30)){
									WebUI.click(inputObjectName)
									Random rnd = new Random()
									int randomNumber = (10 + rnd.nextInt(1000))
									WebUI.setText(inputObjectName,
											'Object Name '+randomNumber)
									WebUI.sendKeys(null, Keys.chord(Keys.ENTER))
									WebUI.delay(2)
									// Set Object Purpose
									if(WebUI.waitForElementVisible(inputObjectPurpose, 30)){
										WebUI.click(inputObjectPurpose)
										Random rnd1 = new Random()
										int randomNumber1 = (10 + rnd1.nextInt(1000))
										WebUI.setText(inputObjectPurpose,
												'Object Purpose '+randomNumber1)
										WebUI.sendKeys(null, Keys.chord(Keys.ENTER))
										WebUI.delay(2)
										if(WebUI.waitForElementVisible(btnUpdateObjectLibrary, 30)){
											WebUI.click(btnUpdateObjectLibrary)
											WebUI.delay(3)
											if(WebUI.waitForElementVisible(objLibDoneButton, 30)){
												WebUI.click(objLibDoneButton)
												WebUI.delay(6)
												MesmerLogUtils.markPassed("MS-CreateNewTest-05 Successful")
											}
											else{
												MesmerLogUtils.markWarning("WARNING: Unable to click on done button")
											}
										}
										else{
											MesmerLogUtils.markFailed("Button update object library not found")
										}

									}
									else{
										MesmerLogUtils.markFailed("Input object purpose field not found")
									}
								}
								else{
									MesmerLogUtils.markFailed("Input object name field not found")
								}
							}
							else{
								MesmerLogUtils.markFailed("Button Object Library not found")
							}
							}else{
							MesmerLogUtils.markFailed("Unable to click on offset div device")
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
}











