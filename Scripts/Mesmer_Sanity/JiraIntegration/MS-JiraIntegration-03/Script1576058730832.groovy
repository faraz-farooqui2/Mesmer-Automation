import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.mesmer.MesmerLogUtils


//MS-Jira Integration-03 | Verify that user should be able to see 'Jira Issues' option in the test result page and can 'Link' and 'Create new Issue'.
//1. Click on the Settings icon on the top right corner of the mesmer console in the middle of Bell icon and Avatar
//2. Click on the 'Jira Integration' option
WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName("Generic")

	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){


		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(CustomKeywords.'com.mesmer.Utility.goToTestResults'()){

			//		String lblPassed = WebUI.getText(passedTestCaseLabel)
			String lblFailed = WebUI.getText(failedTestCaseLabel)

			String lblBroken = WebUI.getText(brokenTestCaseLabel)



			//		int passedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblPassed)
			int failedTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblFailed)

			int brokenTestCaseCount = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(lblBroken)

			//		if(passedTestCaseCount > 0){
			//			WebUI.click(passedTestCaseLabel)
			//			WebUI.delay(2)
			//			if(openTestCase()){
			//				MesmerLogUtils.logInfo("Test case opened. Now performing actions")
			//				if(performActions()){
			//					MesmerLogUtils.markPassed("Actions performed Successfully")
			//				}else{
			//					MesmerLogUtils.markFailed("Actions not performed")
			//				}
			//			}else{
			//				MesmerLogUtils.markFailed("Test case not opened")
			//			}
			//		}
			//		else
			if(failedTestCaseCount > 0){
				WebUI.click(failedTestCaseLabel)
				WebUI.delay(2)
				if(openTestCase()){
					MesmerLogUtils.logInfo("Failed test case opened. Now performing actions")
					WebUI.delay(5)
					if(performActions()){
						MesmerLogUtils.markPassed("Actions performed Successfully")
					}else{
						MesmerLogUtils.markFailed("Actions not performed")
					}
				}else{
					MesmerLogUtils.markFailed("Failed test case not opened")
				}

			}
			else if(brokenTestCaseCount > 0){
				WebUI.click(brokenTestCaseLabel)
				WebUI.delay(2)
				if(openTestCase()){
					MesmerLogUtils.logInfo("Broken test case opened. Now performing actions")
					WebUI.delay(5)
					if(performActions()){
						MesmerLogUtils.markPassed("Actions performed Successfully")
					}else{
						MesmerLogUtils.markFailed("Actions not performed")
					}
				}else{
					MesmerLogUtils.markFailed("Broken test case not opened")
				}
			}
			else{
				MesmerLogUtils.markFailed("[DATA ISSUE] No test cases in Passed , Failed , Broken")
			}
		}
		else{
			MesmerLogUtils.markFailed("Could not navigate to test result page")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}

}catch(Exception e ){
	e.printStackTrace()
}finally{
	CustomKeywords.'com.mesmer.Utility.goToTestResults'()
	//	WebUI.closeWindowIndex(0)
}

private boolean openTestCase(){
	WebDriver driver = DriverFactory.getWebDriver()
	boolean result = false
	String titleStream = findTestObject('OR_TestDetails/list_titleStream').findPropertyValue('xpath').toString()
	List<WebElement> titleStreamList = driver.findElements(By.xpath(titleStream))

	MesmerLogUtils.logInfo("Number of test cases in a project" + "" + titleStreamList.size())
	if(titleStreamList.size() > 0){
		titleStreamList.get(0).click()
		WebUI.delay(3)
		result = true
		MesmerLogUtils.markPassed("Clicked on Test Case")
	}else{
		MesmerLogUtils.markFailed("Test case not opens")
	}
	return result
}

private boolean performActions(){
	boolean result = false
	if(WebUI.waitForElementPresent(clickToExpandReplayResult, 10) == true){
	WebUI.click(clickToExpandReplayResult)
	
	if(WebUI.waitForElementPresent(expandReplay, 10) == true){

		WebUI.click(expandReplay)
		MesmerLogUtils.markPassed('Clicked on expand replay result')


		if(WebUI.waitForElementPresent(openTestCaseScreen, 10) == true){

			WebUI.click(openTestCaseScreen)
			MesmerLogUtils.markPassed('Clicked on open test case screen')


			if(WebUI.waitForElementPresent(clickOnJiraIssues, 10) == true){

				WebUI.click(clickOnJiraIssues)
				MesmerLogUtils.markPassed('Clicked on Jira Issues Successfully')


				//3. Click on 'Create new issue'
				if(WebUI.waitForElementPresent(createNewIssue, 10) == true){

					WebUI.click(createNewIssue)
					MesmerLogUtils.markPassed('Clicked on Create New Issue Successfully')

					String mesmerURL = WebUI.getUrl()
					String mesmerTilte = WebUI.getWindowTitle()
					WebUI.delay(5)

					WebUI.switchToWindowTitle('Log in to continue - Log in with Atlassian account')

					if(WebUI.waitForElementPresent(jiraUserField, 10) == true){

						WebUI.setText(jiraUserField, JiraUsername)
						WebUI.delay(1)

						MesmerLogUtils.markPassed('User Name Set Successfully')


						if(WebUI.waitForElementPresent(jiraContinueButton, 10) == true){

							WebUI.click(jiraContinueButton)
							WebUI.delay(1)

							MesmerLogUtils.markPassed('Click On The Continue Button')


							if(WebUI.waitForElementPresent(jiraPasswordField, 10) == true){

								WebUI.setEncryptedText(jiraPasswordField, jiraPassword)
								WebUI.delay(1)

								MesmerLogUtils.markPassed('Jira Password Set')



								if(WebUI.waitForElementPresent(jiraLogin, 10) == true){

									WebUI.click(jiraLogin)

									WebUI.delay(1)

									MesmerLogUtils.markPassed('Click On The Jira Login Button')

									WebUI.delay(20)

									String jiraTitle = WebUI.getWindowTitle()

									if(WebUI.verifyNotEqual(mesmerTilte, jiraTitle) == true){
										MesmerLogUtils.markPassed('Jira Issue Created')



										//WebUI.navigateToUrl(mesmerURL)
										WebUI.switchToWindowUrl(mesmerURL)
										WebUI.delay(5)
										if(WebUI.waitForElementPresent(clickOnJiraIssues, 10) == true){

											WebUI.click(clickOnJiraIssues)
											MesmerLogUtils.markPassed('Clicked on Jira Issues Successfully')
											WebUI.delay(2)

											//4. Click on 'Link to issue'
											if(WebUI.waitForElementPresent(LinkToIssue, 10) ==true){
												WebUI.click(LinkToIssue)
												MesmerLogUtils.markPassed('Clicked on Link To Issue Successfully')


												//5. Enter the Jira issue ID and hit enter

												if(WebUI.waitForElementPresent(JiraIssueNumber, 10)==true){
													//		WebUI.setText(JiraIssueNumber, IssueNumber)
													//		JiraIssueNumber.sendKeys(Keys.ENTER)
													WebUI.setText(findTestObject('Object Repository/OR_JiraIntegration/input_JiraIssueNumber'),
															IssueNumber)
													WebUI.sendKeys(findTestObject('Object Repository/OR_JiraIntegration/input_JiraIssueNumber'),
															Keys.chord(Keys.ENTER))
													//WebUI.sendKeys(null,Keys.chord(Keys.ENTER))
													MesmerLogUtils.markPassed('Issue Linked Successfully')
													result = true
												}else{
													MesmerLogUtils.markFailed('Issue Not Linked')
												}
											}else{
												MesmerLogUtils.markFailed('Unable to Click on Link To Issue')
											}
										}else{
											MesmerLogUtils.markFailed('Unable to Click on Jira Issues')
										}
									}else{

										MesmerLogUtils.markFailed('Jira Issue Not Created')
									}
								}else{
									MesmerLogUtils.markFailed('Unable To Click On The Jira Login Button')
								}
							}else{
								MesmerLogUtils.markFailed('Unable To Set Jira Password')
							}
						}else{
							MesmerLogUtils.markFailed('Unable To Click On The Continue Button')
						}
					}else{
						MesmerLogUtils.markFailed('Unable To Set User Name')
					}
				}else{
					MesmerLogUtils.markFailed('Unable to Click on Create New Issue')

				}
			}else{
				MesmerLogUtils.markFailed('Unable to Click on Jira Issues')
			}
		}else{
			MesmerLogUtils.markFailed('Unable to Clicked on open test case screen')
		}
	}else{
	MesmerLogUtils.markFailed('Unable to Click on expand replay result')
}
	}else{
		MesmerLogUtils.markFailed('Unable to Click to open replay result')
	}
	return result
}