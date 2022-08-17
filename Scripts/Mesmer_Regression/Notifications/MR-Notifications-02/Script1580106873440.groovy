import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility as Utility


//MR-Notifications-05 | Clicking on "crawl-finished" notification should redirect user to app map page

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName("")
WebDriver driver = DriverFactory.getWebDriver()
try {

	JavascriptExecutor js = ((driver) as JavascriptExecutor)
	String notificationsListXPath = '//div[@class="notifyBody"]/div'
	List<WebElement> notificationsList = driver.findElements(By.xpath(notificationsListXPath))
	String emailAddress = 'faraz.alam@mesmerhq.com'

	CustomKeywords.'com.mesmer.Utility.goToAppMap'()

	CustomKeywords.'com.mesmer.Utility_AppMap.configureCrawl'()

	WebUI.delay(2)

	CustomKeywords.'com.mesmer.Utility_AppMap.startCrawl'()

	WebUI.delay(420)

	if (WebUI.waitForElementPresent(btnNotify, 30) == true) {
		WebUI.click(btnNotify)
		KeywordUtil.markPassed('PASSED: Clicked on Bell icon')
		if (notifyMsgList.get(0).getText().contains('finished the crawl')) {
			KeywordUtil.markPassed('PASSED:Finished the Crawl')
		} else {
			KeywordUtil.markFailed('FAILED: No finish the crawl notification appear')
		}
	} else {
		KeywordUtil.markFailed('FAILED: Unable to click on Bell icon')
	}

	WebUI.delay(10)

	inviteUser()

	if (WebUI.waitForElementPresent(btnNotify, 30) == true) {
		WebUI.click(btnNotify)
		KeywordUtil.markPassed('PASSED: Clicked on Bell icon')
		if (notifyMsgList.get(0).getText().contains('Invitation failure, a user is already registered with')) {
			KeywordUtil.markPassed('PASSED:User is already registered')
		} else {
			KeywordUtil.markFailed('FAILED: No user already registered notification appear')
		}
	} else {
		KeywordUtil.markFailed('FAILED: Unable to click on Bell icon')
	}
}
catch (Exception e) {
	e.printStackTrace()
}

def scrollNotifications() {
	WebDriver driver = DriverFactory.getWebDriver()

	String emailAddress = 'faraz.alam@mesmerhq.com'

	JavascriptExecutor js = ((driver) as JavascriptExecutor)

	String notificationsListXPath = '//div[@class="notifyBody"]/div'

	List<WebElement> notificationsList = driver.findElements(By.xpath(notificationsListXPath))

	//	if ((notificationsList != null) && (notificationsList.size() > 0)) {
	//		String notifyMsgXPath = '//div[@class="notifyBody"]/div/div[@class="notifyText ng-star-inserted"]/span[@class="notifyMsg"]'
	//
	//		List<WebElement> notifyMsgList = driver.findElements(By.xpath(notifyMsgXPath))
	//
	//		if (notifyMsgList.get(0).getText().contains('Invitation failure, a user is already registered with')) {
	//			KeywordUtil.markPassed('PASSED:User is already registered')
	//		} else {
	//			KeywordUtil.markFailed('FAILED: No user already registered notification appear')
	//		}
	//	} else if ((notificationsList != null) && (notificationsList.size() > 0)) {
	//		String notifyMsgXPath = '//div[@class="notifyBody"]/div/div[@class="notifyText ng-star-inserted"]/span[@class="notifyMsg"]'
	//
	//		List<WebElement> notifyMsgList = driver.findElements(By.xpath(notifyMsgXPath))
	//
	//		if (notifyMsgList.get(0).getText().contains('created a new test case')) {
	//			KeywordUtil.markPassed('PASSED:Created a new test case')
	//		} else {
	//			KeywordUtil.markFailed('FAILED: No create a new test case notification appear')
	//		}
	//}
	//		else if ((notificationsList != null) && (notificationsList.size() > 0)) {
	//		String notifyMsgXPath = '//div[@class="notifyBody"]/div/div[@class="notifyText ng-star-inserted"]/span[@class="notifyMsg"]'
	//
	//		List<WebElement> notifyMsgList = driver.findElements(By.xpath(notifyMsgXPath))
	//
	//		if (notifyMsgList.get(0).getText().contains('finished the crawl')) {
	//			KeywordUtil.markPassed('PASSED:Finished the Crawl')
	//		} else {
	//			KeywordUtil.markFailed('FAILED: No finish the crawl notification appear')
	//		}
	//	} else if ((notificationsList != null) && (notificationsList.size() > 0)) {
	//		String notifyMsgXPath = '//div[@class="notifyBody"]/div/div[@class="notifyText ng-star-inserted"]/span[@class="notifyMsg"]'
	//
	//		List<WebElement> notifyMsgList = driver.findElements(By.xpath(notifyMsgXPath))
	//
	//		if (notifyMsgList.get(0).getText().contains('new recommended test cases for you.')) {
	//			KeywordUtil.markPassed('PASSED:New recommended test cases for you.')
	//		} else {
	//			KeywordUtil.markFailed('FAILED: No new recommended test cases for you notification appear')
	//		}
	//	} else if ((notificationsList != null) && (notificationsList.size() > 0)) {
	//		String notifyMsgXPath = '//div[@class="notifyBody"]/div/div[@class="notifyText ng-star-inserted"]/span[@class="notifyMsg"]'
	//
	//		List<WebElement> notifyMsgList = driver.findElements(By.xpath(notifyMsgXPath))
	//
	//		if (notifyMsgList.get(0).getText().contains('joined Mesmer.')) {
	//			KeywordUtil.markPassed('PASSED:User joined Mesmer.')
	//		} else {
	//			KeywordUtil.markFailed('FAILED: No user joined Mesmer notification appear')
	//		}
	//	}
}

def inviteUser(){
	CustomKeywords.'com.mesmer.Utility.goToInviteUser'()
	if(WebUI.verifyElementVisible(AddInviteIcon)==true){

		if(WebUI.verifyElementVisible(InviteEmailField)==true){
			WebUI.sendKeys(InviteEmailField, emailAddress)

			WebUI.delay(1)
			WebUI.click(AddInviteIcon)

			if(WebUI.verifyElementPresent(Invite, 2)==true){
				WebUI.click(Invite)

				//Verification:: Sending Invite' alert message will appear in blue from the top right corner of mesmer console.
				if(WebUI.waitForElementVisible(notificationSendingInvite, 10)==true){
					KeywordUtil.markPassed("PASSED: Invite successfully sent")
					WebUI.delay(1)

					//1-Click on the bell icon in the top right
					if(WebUI.waitForElementPresent(btnNotify, 10)== true){
						WebUI.click(btnNotify)
						scrollNotifications()
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
}

def CreateNewTestCase(){
	
	CustomKeywords.'com.mesmer.Utility.goToCreateNewTestCase'()
	
	
	
}
