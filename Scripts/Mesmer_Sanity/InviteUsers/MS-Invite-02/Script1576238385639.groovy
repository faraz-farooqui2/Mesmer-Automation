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

//MS-Invite Users-02 | Verify that user can't invite exisiting users on mesmer console.
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

WebDriver driver = DriverFactory.getWebDriver()
//1. User clicks on Avatar icon/User Account option on the top right corner of mesmer console.
//2. User clicks on Invite Users options
CustomKeywords.'com.mesmer.Utility.goToInviteUser'()
WebUI.delay(1)
try{
	//3. User add valid email address in input field that is not register earlier on this server.
	if(WebUI.verifyElementVisible(AddInviteIcon)==true){

		if(WebUI.verifyElementVisible(InviteEmailField)==true){
			WebUI.sendKeys(InviteEmailField, existingEmailAddress)

			WebUI.delay(1)
			WebUI.click(AddInviteIcon)

			if(WebUI.verifyElementPresent(Invite, 2)==true){
				//4. User clicks on 'Invite' button
				WebUI.click(Invite)
				
				//Verification:: Sending Invite' alert message will appear in blue from the top right corner of mesmer console.
				if(WebUI.waitForElementPresent(notificationSendingInvite,10)==true && WebUI.waitForElementPresent(notificationNew,10)==true){
					WebUI.delay(6)
					//Verification of already registered user
					if(WebUI.waitForElementVisible(notificationButton, 20)==true){
						WebUI.click(notificationButton)
						
//						List<WebElement> notification = driver.findElements(By.xpath("//span[@class='notifyMsg' and contains(text(),'Invitation failure, a user is already registered’)]"))
						String notificationXpath = findTestObject('Object Repository/OR_InviteUser/xpath_notification').findPropertyValue('xpath').toString()
						List<WebElement> notification = driver.findElements(By.xpath(notificationXpath))
						
						String notifyText = notification.get(0).getText()
						
						//Verify in the notification option if user already exists notification added or not
						if(notifyText.contains("Invitation failure, a user is already registered with")){
							MesmerLogUtils.markPassed("Invitation failure, a user is already registered with "+ existingEmailAddress)
						}
						else{
							MesmerLogUtils.markFailed("Invitation is sent to already registered user  "  + existingEmailAddress )
						}
						
					}
					else{
						MesmerLogUtils.markFailed("Unable to click on notification icon")
					}
				}
				else{
					MesmerLogUtils.markFailed("Either send notification invite or notification new not appears")
				}

			}
			else{
				MesmerLogUtils.markFailed("Unable to click on invite button")
			}
		}
	}
}
catch(Exception e){
	println(e.printStackTrace())
}

finally{


}


