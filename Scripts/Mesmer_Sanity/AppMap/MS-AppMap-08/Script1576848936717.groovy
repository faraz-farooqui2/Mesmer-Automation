import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

//MS-AppMap-08 | Verify that recommended test cases are generating during crawl

try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)){

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		CustomKeywords.'com.mesmer.Utility.goToRecommendedTests'()

		String textRecoTCs = WebUI.getText(noOfRecommendedTests)
		int totalNumberOfRecommendedTCs = CustomKeywords.'com.mesmer.Utility.extractNumericData'(textRecoTCs)

		MesmerLogUtils.logInfo("Number of TCs on RECEOMMENDED screen " + totalNumberOfRecommendedTCs)

		if(totalNumberOfRecommendedTCs > 0){
			MesmerLogUtils.markPassed("Recommended Test Cases are generating during Crawl")

		}
		else{
			MesmerLogUtils.markFailed("Recommended Test Cases are not generating during Crawl")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{

}


