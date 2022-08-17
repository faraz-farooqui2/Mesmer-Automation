import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.mobile.keyword.builtin.WaitForElementPresentKeyword
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import org.openqa.selenium.Keys
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.interactions.Actions
import com.mesmer.MesmerLogUtils


//MS-Test Details-06 | Verify that user should be able to use all options from 3 dot menus.


WebDriver driver = DriverFactory.getWebDriver()
try{
	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName(platformName)
	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		CustomKeywords.'com.mesmer.Utility.goToTestResults'()
		
	//1. Click on any test case from test result page.
	String recommendedTest = findTestObject('Object Repository/OR_TestDetails/list_recommendedTestCase').findPropertyValue('xpath').toString()
	List<WebElement> recoTests = driver.findElements(By.xpath(recommendedTest))
	
	MesmerLogUtils.logInfo("Number of Recommended Test Cases on Test Results page Before Deleting = " + recoTests.size())
	
	if(recoTests != null && recoTests.size() > 0){
		
		recoTests.get(0).click()

		MesmerLogUtils.markPassed("Recommended test case opens")
		WebUI.delay(2)
		//2. Click on 3 dot menu button appearing besides Select device button on top right corner.
		if(WebUI.waitForElementPresent(option3DotButton, 20)==true){
			WebUI.click(option3DotButton)
			MesmerLogUtils.markPassed("Clicked on three dot button")
			//3. Click on downlaod result option from the list
			if(WebUI.waitForElementPresent(downloadResult, 20)==true){
				WebUI.click(downloadResult)
				MesmerLogUtils.markPassed("Downloading Results.....")
				WebUI.delay(5)
//				WebUI.click(option3DotButton)
//				WebUI.delay(2)
				
				if(WebUI.waitForElementPresent(infoBtn, 20)==true){
					WebUI.click(infoBtn)
					MesmerLogUtils.markPassed("Clicked on info button")
				//4. Click on Show comments in the drop down list
				if(WebUI.waitForElementPresent(showComments, 20)==true){
					WebUI.click(showComments)
					MesmerLogUtils.markPassed("Clicked on Show Comments")
					//5. Enter comments or tag any existing user in comments.
					if(WebUI.waitForElementPresent(textAreaComments, 20)==true){
						WebUI.click(textAreaComments)
						MesmerLogUtils.markPassed("Clicked on Add Comment... ")
						WebUI.sendKeys(textAreaComments, "Hello")
						MesmerLogUtils.markPassed("Setting Comments ")
						WebUI.delay(2)
						//6.Click on 'x' button in top right coner of comments modal
						if(WebUI.waitForElementPresent(btnAddComment, 20)==true){
							WebUI.click(btnAddComment)
							MesmerLogUtils.markPassed("Comments Added ")
							WebUI.click(infoBtn)
							MesmerLogUtils.markPassed("Closed info dialogue")
							WebUI.click(option3DotButton)
							//7. Click on Delete option from the drop down list of 3 dots menu
							if(WebUI.waitForElementPresent(btnDelete, 20)==true){
								WebUI.click(btnDelete)
								MesmerLogUtils.markPassed("Clicked on Delete Button ")
								if(WebUI.waitForElementPresent(deleteConfirmation, 20)==true){
									MesmerLogUtils.markPassed("Test Case Delete Confirmation Dialogue")
									//8. Click on Yes option
									if(WebUI.waitForElementPresent(deleteYes, 20)==true){
										WebUI.delay(2)
										WebUI.click(deleteYes)
										MesmerLogUtils.markPassed("Clicked on Yes to Delete Test Case")
									}else{
										MesmerLogUtils.markFailed("Unable to Click on Yes to Delete Test Case ")
									}
								}else{
									MesmerLogUtils.markFailed("No Test Case Delete Confirmation Dialogue ")
								}
							}else{
								MesmerLogUtils.markFailed("Unable to Click on Delete Button")
							}

						}else{
							MesmerLogUtils.markFailed("Unable to Click on Button Add Comments")
						}
					}else{
						MesmerLogUtils.markFailed("Unable to click on Add Comment...")
					}

				}else{
					MesmerLogUtils.markFailed("Unable to click on show comments")
				}
				}else{
				MesmerLogUtils.markFailed("Unable to click on info button")
			}
			}else{
				MesmerLogUtils.markFailed("Unable to download results")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to Click on three dot button")
		}
	}else{
		MesmerLogUtils.markFailed("[DATA ISSUE] No Recommended Test Case Found")
	}
}else{
MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
}
}
catch(Exception e){
	e.printStackTrace()
}finally{
// Additional steps if Inqueue/Inprogress test case deletion
if(WebUI.waitForElementPresent(deleteInqueueInprogressTest, 20)==true){
	MesmerLogUtils.logInfo("Inqueue/Inprogress delete dialogue appears")
	if(WebUI.waitForElementPresent(btnYes, 20)==true){
		WebUI.click(btnYes)
		MesmerLogUtils.logInfo("Clicked on Yes button")
	}else{
	MesmerLogUtils.markFailed("Could not click on Yes button")
	}
}else{
MesmerLogUtils.logInfo("No Inqueue/Inprogress delete dialogue appears")
}

}