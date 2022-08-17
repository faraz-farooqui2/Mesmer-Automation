import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils


//MS-Jira Integration-01 | Verify that user should be able to 'Save' a jira account on mesmer console after adding the valid credentials.
//1. Click on the Settings icon on the top right corner of the mesmer console in the middle of Bell icon and Avatar
//2. Click on the 'Jira Integration' option


try{

	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName("Generic")
	
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){
		
		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		CustomKeywords.'com.mesmer.Utility.goToJiraIntegration'()

		//3. Click on the input fields and enter valid credentials.
		if(WebUI.waitForElementPresent(jiraURL, 10) == true){

			WebUI.setText(jiraURL, myJiraURL)

			MesmerLogUtils.markPassed('Jira URL Set Successfully')


			if(WebUI.waitForElementPresent(jiraProjectName, 10) == true){

				WebUI.setText(jiraProjectName, myjiraProjectName)

				MesmerLogUtils.markPassed('Jira Project Name Set Successfully')


				if(WebUI.waitForElementPresent(jiraAdminLogin, 10) == true){

					WebUI.setText(jiraAdminLogin, myjiraEmail)

					MesmerLogUtils.markPassed('Jira Admin Login Set Successfully')



					if(WebUI.waitForElementPresent(jiraAPIToken, 10) == true){

						WebUI.setText(jiraAPIToken, myjiraAPIToken)

						MesmerLogUtils.markPassed('Jira API Token Set Successfully')


						//4. Click on save button
						if(WebUI.waitForElementPresent(saveConfiguration, 10) == true){

							WebUI.click(saveConfiguration)

							MesmerLogUtils.markPassed('Jira Configuration Saved Successfully')



							if(WebUI.waitForElementPresent(checkSaveConfiguration, 10) == true){

								MesmerLogUtils.markPassed('Jira Configuration Saved Confirmation')
							}else{
								MesmerLogUtils.markFailed('Jira Configuration Not Saved')
							}

						}else{
							MesmerLogUtils.markFailed('Jira Configuration Not Saved')
						}

					}else{
						MesmerLogUtils.markFailed('Jira API Token Not Set')
					}
				}else{
					MesmerLogUtils.markFailed('Jira Admin Login Not Set')
				}
			}else{
				MesmerLogUtils.markFailed('Jira Project Name Not Set')
			}
		}else{
			MesmerLogUtils.markFailed('Jira URL Not Set')
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




