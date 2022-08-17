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
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler

//import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor;
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
/*
 *MS-Notifications-02 | Verify that user is getting all the required notifications in Notifications tab.
 */
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
WebUI.delay(1)

CustomKeywords.'com.mesmer.Utility.goToNotifications'()
WebUI.delay(1)
try{
	if(WebUI.waitForElementPresent(textNoNotifications, 10)){
		scrollNotifications();
		MesmerLogUtils.markPassed("MS-Notification-02 Successful")
	}
	else
	{
		if(WebUI.waitForElementPresent(markAllAsRead, 10)){
			scrollNotifications();

		}
		else{
			MesmerLogUtils.markFailed('There is no new notification')
		}
	}
}catch(Exception e){
	e.printStackTrace()
}

def scrollNotifications(){
	WebDriver driver = DriverFactory.getWebDriver()

	JavascriptExecutor js = (JavascriptExecutor) driver;

	String notificationsListXPath = findTestObject('Object Repository/OR_Notifications/xpath_notificationList').findPropertyValue('xpath').toString()

	List<WebElement> notificationsList = driver.findElements(By.xpath(notificationsListXPath))
	if(notificationsList != null && notificationsList.size() > 0){
		String notifyMsgXPath = findTestObject('Object Repository/OR_Notifications/xpath_notifyMsg').findPropertyValue('xpath').toString()

		List<WebElement> notifyMsgList = driver.findElements(By.xpath(notifyMsgXPath))
		for(int i=0; i< notifyMsgList.size(); i++){
			if(notifyMsgList.get(i).getText().contains(" joined Mesmer.") || notifyMsgList.get(i).getText().contains(" finished the crawl")
			|| notifyMsgList.get(i).getText().contains(" created a new test case")){
				MesmerLogUtils.markPassed("MS-Notification-02 Successful")
				break;
			}
		}
	}
	else{
		MesmerLogUtils.markFailed("There is no notification in the list")
	}
}



