import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.CreateTestController
import controllers.ManageTestController


CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebUI.delay(2)
CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.delay(2)


try{
	ManageTestAndRecordFromHere("ManageTest-06")
}
catch(Exception e){
	e.printStackTrace()
}

finally{
	
}

private boolean ManageTestAndRecordFromHere(String name){
	boolean result = false
	if(ManageTestController.getInstance().findTestCaseAndPerformAction(name,"edit")){
		WebUI.delay(5)
		if(ManageTestController.getInstance().checkAndClickTheScreenShot("recordFromHere")){
			MesmerLogUtils.logInfo("MR-E2E-13 Successful")
		}
		else{
			MesmerLogUtils.logInfo("Unable to edit the provided test case")
		}
	}
	else{
		MesmerLogUtils.logInfo("Unable to edit the provided test case")
	}
	return result
}