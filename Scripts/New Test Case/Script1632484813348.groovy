import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.sun.jna.platform.win32.WinDef.WORD as WORD
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow as XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.ss.usermodel.Row as Row
import org.apache.poi.ss.usermodel.Sheet as Sheet
import org.apache.poi.ss.usermodel.Workbook as Workbook
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.utilities.snapshot as snapshot
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Alert;
try {
    String define = GlobalVariable.expDir

    GlobalVariable.CURRENT_TESTCASE_ID = TestCaseID

    ///////////////  logging code with excel username ////////////////////////
    WebUI.openBrowser('')
    WebUI.maximizeWindow()
    WebUI.navigateToUrl(GlobalVariable.url)
    if (WebUI.waitForElementPresent(UserName, 10) == true) {
        WebUI.click(UserName)
        // Set username from Data file
        WebUI.setText(UserName, username)
        KeywordUtil.markPassed('user is not able to set username')
        if (WebUI.waitForElementPresent(PassWord, 10) == true) {
            WebUI.click(PassWord)
            //Set password from Data file
            WebUI.setText(PassWord, pass)
            KeywordUtil.markPassed('user is able to set username')
            //User clicks on login button.
            if (WebUI.waitForElementPresent(Login_btn, 10) == true) {
                WebUI.click(Login_btn)
                KeywordUtil.markPassed('user is able to login with valid credentials')
            } else {
                KeywordUtil.markFailed('LogIn Button Not Found')
                snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
            }
        } else {
            KeywordUtil.markFailed('Password field Not Found')
            snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
        }
    } else {
        KeywordUtil.markFailed('User Name field Not Found')
        snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
    }
    
    ///////////////  navigation through support  ////////////////////////
    //	WebUI.click(findTestObject('Page_FILOS (SOLYDA - nimda)/Dashboard'))
    /////// menu button
    TestObject menu_btn = findTestObject('Page_FILOS (SOLYDA - nimda)/Dashboard')
    if (WebUI.waitForElementPresent(menu_btn, 10) == true) {
        WebUI.click(menu_btn)
    } else {
        KeywordUtil.markFailed('Menu Button is not functional')
        snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
    }
    
    //// menu sub option support check
    TestObject support_btn = findTestObject('Page_FILOS (SOLYDA - nimda)/Support_menu')
    if (WebUI.waitForElementPresent(support_btn, 10) == true) {
        WebUI.click(support_btn)
    } else {
        KeywordUtil.markFailed('Support Button is not functional')
        snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
    }
    
    //// change proposal menu option check

	TestObject change_proposal_no_btn = findTestObject('Page_FILOS (SOLYDA - nimda)/div_Change_Proposal_No')
    if (WebUI.waitForElementPresent(change_proposal_no_btn, 10) == true) {
        WebUI.click(change_proposal_no_btn)
    } else {
        KeywordUtil.markFailed('Change Proposal No Menu option is not functional')
        snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
    }
    
    WebUI.verifyElementPresent(findTestObject('Page_FILOS (SOLYDA - nimda)/Change_Proposal_No_heading'), 0, FailureHandling.STOP_ON_FAILURE)
    WebUI.verifyElementPresent(findTestObject('Page_FILOS (SOLYDA - nimda)/input_proposal_number_field'), 0, FailureHandling.STOP_ON_FAILURE)
    WebUI.verifyElementPresent(findTestObject('Page_FILOS (SOLYDA - nimda)/search_proposal_number'), 0, FailureHandling.STOP_ON_FAILURE)

    ///// code for verifying the proposal no field 
 
	   //   WebUI.setText(findTestObject('Page_FILOS (SOLYDA - nimda)/input_proposal_number_field'), proposal_number)
    TestObject proposal_no_field = findTestObject('Page_FILOS (SOLYDA - nimda)/input_proposal_number_field')
    if (WebUI.waitForElementPresent(proposal_no_field, 10) == true) {
        WebUI.click(proposal_no_field)
        if (WebUI.waitForElementPresent(proposal_no_field, 10) == true) {
            WebUI.setText(findTestObject('Page_FILOS (SOLYDA - nimda)/input_proposal_number_field'), proposal_number)
        } else {
            KeywordUtil.markFailed('Change Proposal Number Field value cant be set')
            snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
        }
    } else {
        KeywordUtil.markFailed('Change Proposal Number Field Not Found')
        snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
    }
    
    /// code for searching 


    	TestObject proposal_no_search_btn = findTestObject('Page_FILOS (SOLYDA - nimda)/search_proposal_number')
    		if(WebUI.waitForElementPresent(proposal_no_search_btn, 10)==true){
    	WebUI.click(proposal_no_search_btn)
    	}else {
    	KeywordUtil.markFailed('Change Proposal Number Search Button is not functional')
    	snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
    	}
   

    
    /////////////////// table verification ///////////////////
    WebDriver driver = DriverFactory.getWebDriver()
    String ExpectedValue = proposal_number;
    'To locate table'
    WebElement Table = driver.findElement(By.xpath('//table/tbody'))
    'To locate rows of table it will Capture all the rows available in the table'
    List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
    'To calculate no of rows In table'
    int rows_count = rows_table.size()
    'Loop will execute for all the rows of the table'
    Loop: for (int row = 0; row < rows_count; row++) {
        'To locate columns(cells) of that specific row'
        List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))
        'To calculate no of columns(cells) In that specific row'
        int columns_count = Columns_row.size()
        println((('Number of cells In Row ' + row) + ' are ') + columns_count)
        'Loop will execute till the last cell of that specific row'
        for (int column = 0; column < columns_count; column++) {
            'It will retrieve text from each cell'
            String celltext = Columns_row.get(column).getText()
            println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)
            'Checking if Cell text is matching with the expected value'
            if (celltext == ExpectedValue) {
                'Getting the Country Name if cell text i.e Company name matches with Expected value'
                println('Text present in row number 3 is: ' + Columns_row.get(0).getText())
                'After getting the Expected value from Table we will Terminate the loop'
                Loop: break
            }
        }
    }
    
 
			
}
catch (Exception e) {
    e.printStackTrace()

    KeywordUtil.markFailed('Change Proposal No Test Case is not functional')
}


public boolean renameProposal() {
	boolean result = false
	/// code for rename button
	TestObject rename_btn = findTestObject('Page_FILOS (SOLYDA - nimda)/div_Rename')

	if (WebUI.waitForElementPresent(rename_btn, 10) == true) {
		WebUI.click(rename_btn)
		WebUI.verifyElementPresent(findTestObject('Page_FILOS (SOLYDA - nimda)/div_SuccessProposal'), 0)
		WebUI.verifyElementPresent(findTestObject('Page_FILOS (SOLYDA - nimda)/Success_title'), 0)
		WebUI.verifyElementPresent(findTestObject('Page_FILOS (SOLYDA - nimda)/div_Proposal_updation_msg'), 0) //WebUI.click(findTestObject('Page_FILOS (SOLYDA - nimda)/button_OK_proposal'))   
		WebUI.click(findTestObject('Page_FILOS (SOLYDA - nimda)/button_OKay'))
		result = true 
		
		 } else {
		KeywordUtil.markFailed('Rename Proposal Number Button is not functional')
		snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
	}
	return result
}

public boolean renameProposalField () {
	boolean result = false
	/////////////// rename proposal number field
	///// code for verifying the proposal no field
	TestObject rename_proposal_no_field = findTestObject('Page_FILOS (SOLYDA - nimda)/input_Rename_proposal')

	if (WebUI.waitForElementPresent(rename_proposal_no_field, 10) == true) {
		WebUI.click(rename_proposal_no_field)
		if (WebUI.waitForElementPresent(rename_proposal_no_field, 10) == true) {
			WebUI.setText(findTestObject('Page_FILOS (SOLYDA - nimda)/input_Rename_proposal'), update_proposal_number)
			result = true;
		} else {
			KeywordUtil.markFailed('Rename Proposal Number Field value cant be set')
			snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
		}
	} else {
		KeywordUtil.markFailed('Rename Proposal Number Field Not Found')
		snapshot.takeScreenshot(define, GlobalVariable.CURRENT_TESTCASE_ID)
	}
	return result
}

