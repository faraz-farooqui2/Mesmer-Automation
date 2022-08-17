import java.io.IOException as IOException
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
//import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent as HttpTextBodyContent
import static org.assertj.core.api.Assertions.*
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.verification.WSResponseManager as WSResponseManager
import groovy.json.JsonSlurper as JsonSlurper
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import java.io.FileInputStream as FileInputStream
import java.io.FileNotFoundException as FileNotFoundException
import java.io.FileOutputStream as FileOutputStream
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.ss.usermodel.Row as Row
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFCellStyle as XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFRow as XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import java.util.ArrayList
import java.util.List
import org.apache.poi.xssf.usermodel.XSSFWorkbook

def resp = WS.sendRequest(findTestObject('ZAPI-GETISSUEID'))

def jsonSlurper = new JsonSlurper()

def jsonResponse = jsonSlurper.parseText(resp.getResponseBodyContent())
//String test = jsonResponse.searchResult.searchObjectList[0].issueKey
//println(jsonResponse.searchResult.searchObjectList[0].issueKey)
List<String> issueKey = new ArrayList<String>()
List<String> issueId = new ArrayList<String>()
List<String> issueSummary = new ArrayList<String>()
//reslt=ls.add(test)

//println(reslt)
for (int i = 0; i < 50; i++) {
    issueKey.add(jsonResponse.searchResult.searchObjectList[i].issueKey)
	issueId.add(jsonResponse.searchResult.searchObjectList[i].execution.issueId)
	issueSummary.add(jsonResponse.searchResult.searchObjectList[i].issueSummary)
	println(issueKey.get(i) + " ----> "+ issueId.get(i) + "---------->" + issueSummary.get(i) )
	
}

   
FileInputStream fis = new FileInputStream("/Users/mesmer/Documents/Katalon_DDF.xlsx");
XSSFWorkbook workbook = new XSSFWorkbook(fis);
XSSFSheet sheet = workbook.getSheet("Sheet5");
for (int j = 0; j < 50; j++){
sheet.createRow(j+2).createCell(1).setCellValue(issueKey.get(j));
sheet.getRow(j+2).createCell(2).setCellValue(issueId.get(j));
sheet.getRow(j+2).createCell(3).setCellValue(issueSummary.get(j));
}
//int rowCount = j;
//if (Column_Name=='IssueId'){
//	Row row = sheet.getRow(rowCount+1);
//	Cell cell = row.createCell(2)
//	cell.setCellType(cell.CELL_TYPE_STRING);
//	cell.setCellValue(issueKey.get(0));
//}

//		if (Column_Name=='Credential2'){
//			Row row2 = sheet.getRow(rowCount+1);
//			Cell cell2 = row2.createCell(3,0);
//			cell2.setCellType(cell2.CELL_TYPE_STRING);
//			cell2.setCellValue(name);
//			i = rowCount+1;
//		}

FileOutputStream fos = new FileOutputStream("/Users/mesmer/Documents/Katalon_DDF.xlsx");
workbook.write(fos);
fos.close();

