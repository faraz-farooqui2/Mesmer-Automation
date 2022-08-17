import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils


//MS-Jira Integration-02 | Verify that Jira account added should be on Project level. (Jira account should be attached with one project only where it was added)
//1. Click on the Settings icon on the top right corner of the mesmer console in the middle of Bell icon and Avatar
//2. Click on the 'Jira Integration' option
// Set the platformName for the testcase like, Generic/iOS/Android

//Additional Comments -- We have already saved configurations in MS-Jira Integration-01.Here we are just validating that in any other project same configuration not set

try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName("Generic")
	
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		
		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		CustomKeywords.'com.mesmer.Utility.goToJiraIntegration'()

		//5. Select another project from the Project list on top left corner of mesmer console
		//6. Go to Jira Integration page again.
		if(WebUI.getText(jiraURL).toString().isEmpty()){
			MesmerLogUtils.markPassed('Jira URL Not Set In Other Project')

			if(WebUI.getText(jiraProjectName).toString().isEmpty()){
				MesmerLogUtils.markPassed('Jira Project Not Set In Other Project')


				if(WebUI.getText(jiraAdminLogin).toString().isEmpty()){
					MesmerLogUtils.markPassed('Jira Admin Login Not Set In Other Project')



					if(WebUI.getText(jiraAPIToken).toString().isEmpty()){

						MesmerLogUtils.markPassed('Jira API Token Not Set In Other Project')
					}else{
						MesmerLogUtils.markFailed('Jira API Token Set In Other Project')
					}
				}else{
					MesmerLogUtils.markFailed('Jira Admin Login Set In Other Project')
				}
			}else{
				MesmerLogUtils.markFailed('Jira Project Name Set In Other Project')
			}
		}else{
			MesmerLogUtils.markFailed('Jira URL Set In Other Project')
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}

}catch(Exception e){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	WebUI.refresh()
}




