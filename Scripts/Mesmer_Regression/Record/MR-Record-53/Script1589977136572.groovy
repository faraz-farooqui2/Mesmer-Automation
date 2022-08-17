import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.CreateTestController

/*
 * MS-Create a new Test Case-01 | Verify that user should be able to record an iOS test case (Physical/Simulator)
 */

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)
// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
WebDriver driver = DriverFactory.getWebDriver()
try{
	Random rnd = new Random()
	int randomNumber = (10 + rnd.nextInt(1000))

	String tcName = "record" + randomNumber
	String tagName = "tag" + randomNumber

	WebElement searchedReadyDevice = Utility.selectDevice(Device.toString())
	if(searchedReadyDevice != null){
		searchedReadyDevice.click()
		if(CreateTestController.getInstance().deviceChecks()){
			WebUI.delay(10)
			if(CreateTestController.getInstance().clickDoneGreenButton()){

				if(CreateTestController.getInstance().setRecordedTestCaseName(tcName)){

					if(CreateTestController.getInstance().setRecordedTestCaseTag(tagName)){

						if(CreateTestController.getInstance().saveNewTestCase()){

						}else{
							MesmerLogUtils.markFailed("Unable to save test case")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to add tag")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to set test case name")
				}

			}else{
				MesmerLogUtils.markFailed("Unable to click on done button")
			}
		}else{
			MesmerLogUtils.markFailed("Device checks failed")
		}
	}else{
		MesmerLogUtils.markFailed("Unable to click on ready device")
	}

}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}

