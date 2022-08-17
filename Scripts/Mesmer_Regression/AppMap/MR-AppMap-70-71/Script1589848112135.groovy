import org.openqa.selenium.WebDriver

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.mesmer.KTRequestHandler
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.AppMapController


//AppMap-Device logs-70 - Verify user can search logs by entering any keyword in search field
//AppMap-Device logs-71 - Verify user can remove search keywords from field by clicking on cross icon shown in field when some text is entered

//Calling Select Project Method
CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

//Calling go to App Map page method
CustomKeywords.'com.mesmer.Utility.goToAppMap'()

WebDriver driver = DriverFactory.getWebDriver()

try{

	//	if(AppMapController.getInstance().startACrawl()){
	if(WebUI.waitForElementVisible(btnLogs, 10)){
		MesmerLogUtils.logInfo("Logs icon is displayed. Now clicking logs icon")
		WebUI.click(btnLogs)
		MesmerLogUtils.logInfo("Logs icon is clicked")

		if(WebUI.verifyElementPresent(logsSettings, 10)){
			MesmerLogUtils.markPassed("Logs Settings button is displayed")
			MesmerLogUtils.logInfo("Clicking Log Settings")
			WebUI.click(logsSettings)
			MesmerLogUtils.logInfo("Log Settings is clicked")

			if(WebUI.verifyElementPresent(optionFilter, 10)){
				MesmerLogUtils.markPassed("Filter option is displayed")

				MesmerLogUtils.logInfo("Clicking Filter option")
				WebUI.click(optionFilter)
				MesmerLogUtils.logInfo("Filter option is clicked")

				if(WebUI.waitForElementVisible(btnCross, 10)){
					MesmerLogUtils.markPassed("Ignore case close button is displayed")

					if(WebUI.waitForElementVisible(checkboxIgnoreCase, 10)){
						MesmerLogUtils.markPassed("Ignore case checkbox is displayed")


						if(WebUI.waitForElementVisible(searchField, 10)){
							MesmerLogUtils.markPassed("Search field is displayed")
							WebUI.sendKeys(searchField, "com")

							String logRowsString = logRows.findPropertyValue('xpath').toString()
							List<WebElement> logRowsList = driver.findElements(By.xpath(logRowsString))

							if(logRowsList!=null && logRowsList.size()>0){

								for(int i = 0; i<logRowsList.size(); i++){
									if(logRowsList.get(i).getText().contains("com")){
										MesmerLogUtils.markFailed("AppMap TC#70 is passed")
									}
									else{
										MesmerLogUtils.markFailed("AppMap TC#70 is failed")
									}

								}
								MesmerLogUtils.markFailed("Matching logs are found in the search list")

								WebUI.delay(2)

								if(WebUI.waitForElementVisible(btnCross, 2)){
									MesmerLogUtils.markPassed("Cross button is displayed")
									MesmerLogUtils.logInfo("Clicking cross button")

									WebUI.click(btnCross)
									MesmerLogUtils.logInfo("Cross button button is clicked to delete the text in Search box")

									String text = WebUI.getText(searchField)

									if(text.contains("")){
										MesmerLogUtils.markPassed("AppMap TC#71 is passed")
									}
									else{
										MesmerLogUtils.markFailed("AppMap TC#71 is failed")
									}

								}
								else{
									MesmerLogUtils.markFailed("Cross button is not displayed")
								}
							}
							else{
								MesmerLogUtils.markFailed("Search field is not displayed")
							}

						}
						else{
							MesmerLogUtils.markFailed("Filter option is not displayed")
						}

					}
					else{
						MesmerLogUtils.markFailed("Logs Settings button is not displayed")
					}
				}
				else{
					MesmerLogUtils.markFailed("No matching logs found in the search list")
				}


			}else{
				MesmerLogUtils.markFailed("Ignore case checkbox is not displayed")

			}
		}else{
			MesmerLogUtils.markFailed("Ignore case close button is not displayed")
		}

	}
	else{
		MesmerLogUtils.logInfo("Could not click on log button")
	}
	//	}
	//	else{
	//		MesmerLogUtils.logInfo("Crawl is not started")
	//	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
	//	if(AppMapController.getInstance().stopACrawl()){
	//		MesmerLogUtils.logInfo("Crawl is stopped successfully")
	//	}
	WebUI.refresh()
}

