import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestResultController

/*
 * MR-Manage Test Case-01 | Manage Test case Screen should display after clicking Test Cases Tab then click Manage Tests														
 */

CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
WebUI.delay(2)



try{
	CustomKeywords.'com.mesmer.Utility.goToManageTests'()
	WebUI.waitForPageLoad(5)
	MesmerLogUtils.markPassed("MS-ManageTest-01 Successful")
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.waitForPageLoad(5)
}
