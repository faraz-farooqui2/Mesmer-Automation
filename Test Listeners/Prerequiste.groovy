import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.concurrent.atomic.AtomicInteger

import org.apache.poi.ss.usermodel.Row
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogEntry
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.KTRequestHandler
import com.mesmer.Utility
import com.mesmer.WriteExcelSheet

import controllers.EmailController
import controllers.UploadFileController
import datamodels.EmailModel
import internal.GlobalVariable

class Prerequiste {
	AtomicInteger passedTCAndroid = new AtomicInteger(0)
	AtomicInteger passedTCiOS = new AtomicInteger(0)
	AtomicInteger passedTCGeneric = new AtomicInteger(0)
	AtomicInteger passedTCLogin = new AtomicInteger(0)
	AtomicInteger passedTCLogout = new AtomicInteger(0)
	AtomicInteger failedTCAndroid = new AtomicInteger(0)
	AtomicInteger failedTCiOS = new AtomicInteger(0)
	AtomicInteger failedTCGeneric = new AtomicInteger(0)
	AtomicInteger failedTCLogin = new AtomicInteger(0)
	AtomicInteger failedTCLogout = new AtomicInteger(0)
	HashMap<String, EmailModel> testCasesDataMap = new HashMap();
	String platFormName = ""

	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def executeBeforeTestCase(TestCaseContext testCaseContext) {
		String textCaseId = testCaseContext.getTestCaseId();
		System.out.println("TestCaseId : "+textCaseId.substring((textCaseId.lastIndexOf("/").toInteger()) + 1))
//		System.out.println("TestCaseStatus : "+testCaseContext.getTestCaseStatus())
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseVariables()

		//		if(!Utility.isDefaultProfile()){
		//			try{
		//				TestObject login_LoginBtn = findTestObject('Object Repository/OR_LogIn/button_Login')
		//
		//				if(WebUI.waitForElementPresent(login_LoginBtn, 1)==true){
		//					KeywordUtil.logInfo("User is going to login again...")
		//					WebUI.callTestCase(findTestCase("Test Cases/Mesmer_Sanity/Login/MS-LogIn-01"), [:], FailureHandling.STOP_ON_FAILURE)
		//				}
		//				else{
		//					println("Already logged In")
		//				}
		//			}
		//			catch(Exception e){
		//				println(e.message)
		//			}
		//		}
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def executeAfterTestCase(TestCaseContext testCaseContext) {
		String textCaseId = testCaseContext.getTestCaseId();
		textCaseId = textCaseId.substring((textCaseId.lastIndexOf("/").toInteger()) + 1);
		System.out.println("TestCaseId : "+textCaseId)
		System.out.println("TestCaseStatus : "+testCaseContext.getTestCaseStatus())
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseStatus()
		platFormName = Utility.getPlatformName()
		int statusId = -1;
		if(testCaseContext.getTestCaseStatus().equals("PASSED")){
			statusId = 1;
			if(platFormName.equalsIgnoreCase("iOS")){
				passedTCiOS.getAndIncrement()
			}
			else if(platFormName.equalsIgnoreCase("Android")){
				passedTCAndroid.getAndIncrement()
			}
			else if(platFormName.equalsIgnoreCase("Generic") || platFormName.equalsIgnoreCase("")){
				passedTCGeneric.getAndIncrement()
			}
			else{
				if(platFormName.equalsIgnoreCase("Login")){
					passedTCLogin.getAndIncrement()
				}
				else if(platFormName.equalsIgnoreCase("Logout")){
					passedTCLogout.getAndIncrement()
				}
			}
		}
		else if(testCaseContext.getTestCaseStatus().equals("FAILED")){
			statusId = 2;
			if(platFormName.equalsIgnoreCase("iOS")){
				failedTCiOS.getAndIncrement()
			}
			else if(platFormName.equalsIgnoreCase("Android")){
				failedTCAndroid.getAndIncrement()
			}
			else if(platFormName.equalsIgnoreCase("Generic") || platFormName.equalsIgnoreCase("")){
				failedTCGeneric.getAndIncrement()
			}
			else{
				if(platFormName.equalsIgnoreCase("Login")){
					failedTCLogin.getAndIncrement()
				}
				else if(platFormName.equalsIgnoreCase("Logout")){
					failedTCLogout.getAndIncrement()
				}
			}
		}
		setMapData(textCaseId)

		if(Utility.isSanityProfile()){
			// Update the test case status on JIRA
			Row resultRow = WriteExcelSheet.readDataFromExcel(textCaseId, platFormName)
			if(resultRow != null){
				KTRequestHandler.updateTaskStatus(resultRow, statusId);
			}
		}


	}

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def executeBeforeTestSuite(TestSuiteContext testSuiteContext) {
		String testSuitId = testSuiteContext.getTestSuiteId()
		System.out.println("testSuitId : "+testSuitId.substring((testSuitId.lastIndexOf("/").toInteger()) + 1))
		println testSuiteContext.getTestSuiteId()

		WebUI.openBrowser('')
		WebUI.maximizeWindow()

		WebUI.navigateToUrl(GlobalVariable.hostName)
		
//		WebUI.navigateToUrl("https://qa.stress-test.nonprod.appsight.us/")
		
		//		WriteExcelSheet.createSikuliDataFile()
		if(Utility.isSanityProfile()){
			//Fetch issues list from JIRA and save them to excel sheet
			fetchIssuesListFromJIRA()
		}
		//		if(!Utility.isDefaultProfile()){
		//			WebUI.callTestCase(findTestCase("Test Cases/Mesmer_Sanity/Login/MS-LogIn-01"), [:], FailureHandling.STOP_ON_FAILURE)
		//		}
	}

	def void setMapData(String testCaseId){
		EmailModel em = new EmailModel()
		if(platFormName.equalsIgnoreCase("iOS")){
			em.setPassedTestCount(passedTCiOS.get())
			em.setFailedTestCount(failedTCiOS.get())
		}
		else if(platFormName.equalsIgnoreCase("Android")){
			em.setPassedTestCount(passedTCAndroid.get())
			em.setFailedTestCount(failedTCAndroid.get())
		}
		else if(platFormName.equalsIgnoreCase("Generic") || platFormName.equalsIgnoreCase("")){
			em.setPassedTestCount(passedTCGeneric.get())
			em.setFailedTestCount(failedTCGeneric.get())
		}
		else{
			if(platFormName.equalsIgnoreCase("Login")){
				em.setPassedTestCount(passedTCLogin.get())
				em.setFailedTestCount(failedTCLogin.get())
			}
			else if(platFormName.equalsIgnoreCase("Logout")){
				em.setPassedTestCount(passedTCLogout.get())
				em.setFailedTestCount(failedTCLogout.get())
			}
		}
		testCasesDataMap.put(platFormName, em)
	}

	private void fetchIssuesListFromJIRA(){
		if(!GlobalVariable.versionId.toString().isEmpty() && !GlobalVariable.projectId.toString().isEmpty()){
			List<Map<Object,Object>> issuesListMap = KTRequestHandler.getIssuesListDataFromJIRA(GlobalVariable.versionId,GlobalVariable.projectId);
			if(issuesListMap != null && issuesListMap.size() > 0){
				System.out.println("IssuesList Size : "+issuesListMap.size())
				WriteExcelSheet.saveDataToExcelFile(issuesListMap)
			}
		}
	}

	def void runBatchFile(String batchFile) {
		String bf = RunConfiguration.getProjectDir() + '/' + batchFile
		WebUI.comment("Running batch file: " + bf)
		Runtime.runtime.exec(bf)
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def excuteAfterTestSuite(TestSuiteContext testSuiteContext) {
		String testSuitId = testSuiteContext.getTestSuiteId()
		String suiteName = testSuitId.substring((testSuitId.lastIndexOf("/").toInteger()) + 1)
		System.out.println("testSuitId : "+testSuitId.substring((testSuitId.lastIndexOf("/").toInteger()) + 1))
		println testSuiteContext.getTestSuiteId()
		// Generate html report for email sending
		new KatalonReportListener().exportKatalonReports()
		WebUI.delay(4)


		String fileUrl = UploadFileController.getInstance().uploadFile()
		if(fileUrl != null && !fileUrl.equalsIgnoreCase("")){
			EmailController.sendReportInEmail(GlobalVariable.hostName, suiteName,fileUrl, testCasesDataMap)
		}
		//		if(!Utility.isDefaultProfile()){
		//			WebUI.callTestCase(findTestCase("Test Cases/Mesmer_Sanity/Logout/MS-Logout-01"), [:], FailureHandling.STOP_ON_FAILURE)
		//		}

		WebUI.closeBrowser()


		//Sikuli script for Default Profile
		if(Utility.isDefaultProfile()){
			// This part is for sikuli script. DONT DELETE IT
			WebUI.delay(5)
			int executedSuitesCount = WriteExcelSheet.getSikuliDataFromExcel()
			WebUI.delay(2)
			System.out.println("executedSuitesCount: "+executedSuitesCount)
			WebUI.delay(2)
			WriteExcelSheet.updateSikuliDataInExcel(executedSuitesCount+1)
			WebUI.delay(2)
			executedSuitesCount = WriteExcelSheet.getSikuliDataFromExcel()
			WebUI.delay(2)
			System.out.println("executedSuitesCount after update: "+executedSuitesCount)
			WebUI.delay(2)
			if(executedSuitesCount == 11){
				System.out.println("Final executedSuitesCount is : "+executedSuitesCount)
				WriteExcelSheet.updateSikuliDataInExcel(0)
				WebUI.delay(2)
				runBatchFile("sikuliscript.bat")
			}
		}
		
		
		//Sikuli for STress Test Profile
//		if(Utility.isStressTestProfile()){
//			// This part is for sikuli script. DONT DELETE IT
//			WebUI.delay(5)
//			int executedSuitesCount = WriteExcelSheet.getSikuliDataFromExcel()
//			WebUI.delay(2)
//			System.out.println("executedSuitesCount: "+executedSuitesCount)
//			WebUI.delay(2)
//			WriteExcelSheet.updateSikuliDataInExcel(executedSuitesCount+1)
//			WebUI.delay(2)
//			executedSuitesCount = WriteExcelSheet.getSikuliDataFromExcel()
//			WebUI.delay(2)
//			System.out.println("executedSuitesCount after update: "+executedSuitesCount)
//			WebUI.delay(2)
//			if(executedSuitesCount == 10){
//				System.out.println("Final executedSuitesCount is : "+executedSuitesCount)
//				WriteExcelSheet.updateSikuliDataInExcel(0)
//				WebUI.delay(2)
//				runBatchFile("sikuliscript.bat")
//			}
//		}
	}
}