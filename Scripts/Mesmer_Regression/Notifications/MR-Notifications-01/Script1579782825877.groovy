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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory



//MR-Notifications-01 | Verify user can receive\view notifications with 'Mark All as Read' option by clicking on Bell icon

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{
	//1-Click on the bell icon in the top right
	if(WebUI.waitForElementPresent(btnNotify, 10)== true){
		WebUI.click(btnNotify)
		KeywordUtil.markPassed("PASSED: Clicked on Bell icon")


		if(WebUI.waitForElementPresent(headerNotifications, 10)==true){
			KeywordUtil.markPassed("PASSED: New Notification Found")

			List<WebElement> notificationMsgs = driver.findElements(By.xpath('//div[@class="notifyBody"]/div'))
			int notificationCount = notificationMsgs.size()
			if(notificationMsgs != null && notificationMsgs.size() > 0){
				KeywordUtil.markPassed("PASSED: Notifications exists in the list ")
				//2-Click on "Mark All as Read"
				if(WebUI.waitForElementPresent(txtMarkAllAsRead, 10)==true){
					WebUI.click(txtMarkAllAsRead)
					if(WebUI.verifyElementNotPresent(txtMarkAllAsRead, 10)==true){
						KeywordUtil.markPassed("PASSED: Mark All As Read is not visible")
					}else{
						KeywordUtil.markPassed("PASSED: Mark All As Read is visible")
					}
					KeywordUtil.markPassed("PASSED: Mark All As Read Found")
				}else {
					KeywordUtil.markFailed("FAILED: Mark All As Read Not Found")
				}
			}else {
				KeywordUtil.markFailed("FAILED: No Notifications exists in the list")
			}
		}else{
			KeywordUtil.markFailed("FAILED: No New Notifications")
		}
	}else{
		KeywordUtil.markFailed("FAILED: Unable to click on bell icon")
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
	WebUI.refresh()
}