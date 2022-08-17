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

//MR-Notifications-04 | Previous notifications should be shown by scrolling
CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{
	//1-Click on the bell icon in the top right
	if(WebUI.waitForElementPresent(btnNotify, 10)== true){
		WebUI.click(btnNotify)
		KeywordUtil.markPassed("PASSED: Clicked on Bell icon")
		List<WebElement> notificationMsgs = driver.findElements(By.xpath('//div[@class="notifyBody"]/div'))
		int notificationCount = notificationMsgs.size()
		System.err.println("Number of Notifications in a list = " + notificationCount)
		if(notificationMsgs != null && notificationMsgs.size() > 0){
			scrollNotifications();
			KeywordUtil.markPassed("PASSED: Notification Menu Scrolled")
		}else{
			KeywordUtil.markFailed("FAILED: Unable to Scroll in Notification Menu")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to click on bell icon")
	}

}catch(Exception e){
	e.printStackTrace()
}

def scrollNotifications(){
	WebDriver driver = DriverFactory.getWebDriver()
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String notificationsListXPath = '//div[@class="notifyBody"]/div';
	List<WebElement> notificationsList = driver.findElements(By.xpath(notificationsListXPath))
	//This will scroll the page till the element is found
	js.executeScript("arguments[0].scrollIntoView();", notificationsList.get(notificationsList.size()-1));
}
