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
import com.mesmer.WriteExcelSheet

//import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor;
import org.apache.poi.ss.usermodel.Row;
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
/*
 * MS-Notifications-01 | Verify that user can see all the notifications under 'Notifications' tab in mesmer console
 */
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

WebUI.delay(1)

//List<Map<Object,Object>> issuesListMap = KTRequestHandler.getIssuesListDataFromJIRA("10030","10400");
//if(issuesListMap != null && issuesListMap.size() > 0){
//	System.out.println("IssuesList Size : "+issuesListMap.size())
//	WriteExcelSheet.saveDataToExcelFile(issuesListMap)
//	Row resultRow = WriteExcelSheet.readDataFromExcel("MS-ManageTest-04", "Generic")
//	System.out.println("Excel Data Row  : "+resultRow.getCell(0)+" ")
//	if(resultRow != null){
//		KTRequestHandler.updateTaskStatus(resultRow);
//	}
//}

CustomKeywords.'com.mesmer.Utility.goToNotifications'()
WebUI.delay(1)
try{
	if(WebUI.waitForElementPresent(textNoNotifications, 10)){
		scrollNotifications();
		MesmerLogUtils.markPassed("MS-Notification-01 Successful")
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
		//This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", notificationsList.get(notificationsList.size()-1));
		MesmerLogUtils.markPassed("MS-Notification-01 Successful")
	}
	else{
		MesmerLogUtils.markFailed("There is no notification in the list")
	}
}



