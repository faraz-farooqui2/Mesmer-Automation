import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import javax.media.bean.playerbean.MediaPlayer.popupActionListener
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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.util.List
import java.util.ArrayList
import org.junit.After
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.configuration.RunConfiguration
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper

//MR-Notifications-03 | Verify the time shown under every notification is correct

CustomKeywords.'com.mesmer.Utility.goToInviteUser'()
WebDriver driver = DriverFactory.getWebDriver()
String emailAddress = 'faraz.alam@mesmerhq.com'
try{
	if(WebUI.verifyElementVisible(AddInviteIcon)==true){

		if(WebUI.verifyElementVisible(InviteEmailField)==true){
			WebUI.sendKeys(InviteEmailField, emailAddress)

			WebUI.delay(1)
			WebUI.click(AddInviteIcon)

			if(WebUI.verifyElementPresent(Invite, 2)==true){
				WebUI.click(Invite)

				//Verification:: Sending Invite' alert message will appear in blue from the top right corner of mesmer console.
				if(WebUI.waitForElementVisible(notificationSendingInvite, 10)==true)
				{
					KeywordUtil.markPassed("PASSED: Invite successfully sent")
					WebUI.delay(1)

					//1-Click on the bell icon in the top right
					if(WebUI.waitForElementPresent(btnNotify, 10)== true){
						WebUI.click(btnNotify)
						scrollNotifications()
						String timeXpath = '//div[@class="time"]'
						List<WebElement> timeList = driver.findElements(By.xpath(timeXpath))
						if(timeList.get(0).getText().contains("minutes ago")){
							KeywordUtil.markPassed("PASSED:"+ timeList)
							KeywordUtil.markPassed("PASSED: Time is correctly showing")
						}else{
						KeywordUtil.markFailed("FAILED: Time is not correct")
						}
						KeywordUtil.markPassed("PASSED: Clicked on Bell icon")
					}else{
						KeywordUtil.markFailed("FAILED: Unable to click on Bell icon")
					}
				}
				else{
					KeywordUtil.markFailed("FAILED: Invite is already sent to the user")
				}
			}
			else{
				KeywordUtil.markFailed("FAILED: Invite not sent")
			}
		}else{
			KeywordUtil.markFailed("FAILED: Unable to write email address in text field")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to click on Add invite icon")
	}
}catch(Exception e){
	e.printStackTrace()
}

def scrollNotifications(){
	WebDriver driver = DriverFactory.getWebDriver()
	String emailAddress = 'faraz.alam+1@mesmerhq.com'
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String notificationsListXPath = '//div[@class="notifyBody"]/div';
	List<WebElement> notificationsList = driver.findElements(By.xpath(notificationsListXPath))
	
	if(notificationsList != null && notificationsList.size() > 0){
		String notifyMsgXPath = '//div[@class="notifyBody"]/div/div[@class="notifyText ng-star-inserted"]/span[@class="notifyMsg"]'
		
		List<WebElement> notifyMsgList = driver.findElements(By.xpath(notifyMsgXPath))
		
			if(notifyMsgList.get(0).getText().contains("Invitation failure, a user is already registered with")){
				KeywordUtil.markPassed("PASSED:User is already registered")
			}
		
		//This will scroll the page till the element is found
//		js.executeScript("arguments[0].scrollIntoView();", notificationsList.get(notificationsList.size()-1));
	}
	else{
		KeywordUtil.markWarning("WARNING: There is no notification in the list")
	}
}
