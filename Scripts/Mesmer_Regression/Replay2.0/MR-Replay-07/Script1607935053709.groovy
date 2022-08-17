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
 * MR-Replay-07 | APPSIGHT-20594 | Verify user can remove an in-queue test case from queue
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
				if(TestDetailsController.getInstance().stopInQueueTestCase()){
					MesmerLogUtils.markPassed("MR-Replay-07 Successful")
				}
			}
		}
	}

}catch(Exception e){
	e.printStackTrace()

}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
}