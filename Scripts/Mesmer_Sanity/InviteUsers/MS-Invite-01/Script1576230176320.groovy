import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import internal.GlobalVariable as GlobalVariable
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

//MR-Invite-01 | Valid email in invite users section

//1. User clicks on Avatar icon/User Account option on the top right corner of mesmer console.
//2. User clicks on Invite Users options
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToInviteUser'()
WebUI.delay(1)
WebDriver driver = DriverFactory.getWebDriver()
try{
	//3. User add valid email address in input field that is not register earlier on this server.
	if(WebUI.verifyElementVisible(AddInviteIcon)==true){

		if(WebUI.verifyElementVisible(InviteEmailField)==true){
			WebUI.sendKeys(InviteEmailField, emailAddress)

			WebUI.delay(1)
			WebUI.click(AddInviteIcon)

			if(WebUI.verifyElementPresent(Invite, 2)==true){
				//4. User clicks on 'Invite' button
				WebUI.click(Invite)

				//Verification:: Sending Invite' alert message will appear in blue from the top right corner of mesmer console.
				if(WebUI.waitForElementPresent(notificationSendingInvite, 10)==true && WebUI.waitForElementPresent(notificationNew, 5)==false)
				{
					WebUI.delay(6)
					MesmerLogUtils.markPassed("Invitation sent to" + " " + " " + emailAddress )
				}
				else {
					MesmerLogUtils.markFailed("User is already registered with" + " " + " " + emailAddress )
				}
			}else{
				MesmerLogUtils.markFailed("Unable to click on invite button")
			}
		}else{
			MesmerLogUtils.markFailed("Unable to add email address in invite field ")
		}
	}else{
		MesmerLogUtils.markFailed("Add invite icon is visible")
	}
}
catch(Exception e){
	println(e.printStackTrace())
}

finally{


}


