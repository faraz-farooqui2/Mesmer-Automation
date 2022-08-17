import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.MesmerLogUtils as MesmerLogUtils
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils
import controllers.ReplayController
import controllers.TestDetailsController
import controllers.TestResultController


/**
 * MR-Replay-08 | APPSIGHT-20594 | Verify clicking cross icon should display confirmation pop up, clicking remove on confirmation pop up should remove the device from queue list and clicking cancle should disappear the pop up and device should be in queue list
 */
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)
Utility.setPlatformName(platformName)

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	if(ReplayController.getInstance().checkNumberOfTestCasesInTestResult()){
		MesmerLogUtils.logInfo("Test Case found in Test Result")
		if(TestResultController.getInstance().replayTestCases(Device)){
			if(ReplayController.getInstance().selectATestCaseWithStatus("inQueue")){
				if(TestDetailsController.getInstance().clickStopInQueueTestIcon()){
					if(TestDetailsController.getInstance().checkIfDeleteInQueueTestCasePopUpAppears()){
						if(TestDetailsController.getInstance().clickCancelButton()){
							if(TestDetailsController.getInstance().clickRemovePinkButton()){
								MesmerLogUtils.markPassed("MR-Replay-08 Successful")
							}
						}
					}
				}
			}
		}
	}

}catch(Exception e){
	e.printStackTrace()

}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}