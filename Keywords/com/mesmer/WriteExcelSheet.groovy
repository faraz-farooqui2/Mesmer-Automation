package com.mesmer

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import java.io.FileOutputStream

import com.kms.katalon.core.annotation.Keyword


public class WriteExcelSheet {

	@Keyword
	public static void saveDataToExcelFile(List<Map<Object,Object>> dataList) throws IOException{
		if(dataList != null && dataList.size() > 0){
			// Blank workbook
			XSSFWorkbook workbook = new XSSFWorkbook();
			// Create a sheet
			XSSFSheet sheet = workbook.createSheet("JiraIssuesList");

			// This data needs to be written (Object[])
			Map<String, Object[]> data = new TreeMap<String, Object[]>();
			// Columns name
			Object[] items = new Object[14]//{"id","issueId", "versionId", "projectId","cycleId","cycleName","folderId","folderName","issueKey","issueSummary","statusName","statusId"};
			items[0] = "id"
			items[1] = "issueId"
			items[2] = "versionId"
			items[3] = "projectId"
			items[4] = "cycleId"
			items[5] = "cycleName"
			items[6] = "folderId"
			items[7] = "folderName"
			items[8] = "issueKey"
			items[9] = "issueSummary"
			items[10] = "statusName"
			items[11] = "statusId"
			items[12] = "customFieldId"
			items[13] = "customFieldValue"
			data.put("1", items);
			for(int j=0; j < dataList.size(); j++){
				String[] itemsArr = new String[14]
				itemsArr[0] = dataList.get(j).get("id")
				itemsArr[1] = dataList.get(j).get("issueId")
				itemsArr[2] = dataList.get(j).get("versionId")
				itemsArr[3] = dataList.get(j).get("projectId")
				itemsArr[4] = dataList.get(j).get("cycleId")
				itemsArr[5] = dataList.get(j).get("cycleName")
				itemsArr[6] = dataList.get(j).get("folderId")
				itemsArr[7] = dataList.get(j).get("folderName")
				itemsArr[8] = dataList.get(j).get("issueKey")
				itemsArr[9] = dataList.get(j).get("issueSummary")
				itemsArr[10] = dataList.get(j).get("statusName")
				itemsArr[11] = dataList.get(j).get("statusId")
				itemsArr[12] = dataList.get(j).get("customFieldId")
				itemsArr[13] = dataList.get(j).get("customFieldValue").toString().trim()
				data.put((j+2)+"", itemsArr);
			}
			// Iterate over data and write to sheet
			Set<String> keyset = data.keySet();
			int rownum = 0;
			for (String key : keyset) {
				// this creates a new row in the sheet
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					// this line creates a cell in the next column of that row
					Cell cell = row.createCell(cellnum++);
					cell.setCellType(Cell.CELL_TYPE_STRING)
					//					cell.setCellType(CellType.STRING)
					cell.setCellValue(obj)
					//					if (obj instanceof String)
					//						cell.setCellValue((String)obj);
					//					else if (obj instanceof Integer)
					//						cell.setCellValue((Integer)obj);
				}
			}
			try {
				// this Writes the workbook issuesList
				FileOutputStream out = new FileOutputStream(new File("Resources/Data Files/issuesList.xlsx"));
				workbook.write(out);
				out.close();
				System.out.println("issuesList.xlsx written successfully on disk.");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Keyword
	public static Row readDataFromExcel(String testCaseName, String platformName) throws IOException{
		try{
			FileInputStream file = new FileInputStream(new File("Resources/Data Files/issuesList.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			boolean isNameMatched = false;
			boolean isPlatformMatched = false;
			Row resultRow = null
			for (Row row : sheet) {
				isNameMatched = false
				isPlatformMatched = false
				for (Cell cell : row) {
					String cellValue = cell.getCellType() == Cell.CELL_TYPE_NUMERIC? cell.getNumericCellValue()+ "":cell.getStringCellValue()+ ""
					if(cell != null && cellValue.equalsIgnoreCase(testCaseName)){
						isNameMatched = true;
					}
					if(cell != null && cellValue.equalsIgnoreCase(platformName)){
						isPlatformMatched = true;
					}
					if(isNameMatched && isPlatformMatched){
						resultRow = row;
						//						System.out.println("CellABC: "+resultRow)
						break;
					}
				}
				if(isNameMatched && isPlatformMatched){
					break;
				}
			}
			return resultRow;

		}
		catch(Exception e){
			e.printStackTrace()
		}
	}

	public static void createSikuliDataFile(){
		//		if(dataList != null && dataList.size() > 0){
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a sheet
		XSSFSheet sheet = workbook.createSheet("sikuliDataList");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		Object[] items = new Object[1]
		items[0] = "executedSuitesCount"
		data.put("1", items);
		//			for(int j=0; j < dataList.size(); j++){
		String[] itemsArr = new String[1]
		itemsArr[0] = "0"//dataList.get(j).get("executedSuitesCount")
		data.put((2)+"", itemsArr);
		//			}
		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			// this creates a new row in the sheet
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				// this line creates a cell in the next column of that row
				Cell cell = row.createCell(cellnum++);
				cell.setCellType(Cell.CELL_TYPE_STRING)
				cell.setCellValue(obj)
			}
		}
		try {
			// this Writes the workbook issuesList
			FileOutputStream out = new FileOutputStream(new File("Resources/Data Files/sikuliDataListSheet.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("sikuliDataListSheet.xlsx written successfully on disk.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//		}
	}

	public static int getSikuliDataFromExcel() throws IOException{
		int result = 0
		try{
			FileInputStream file = new FileInputStream(new File("Resources/Data Files/sikuliDataListSheet.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet("sikuliDataList");
			//			System.out.println("Sikuli Sheet name:: "+sheet)
			Cell cell2Update = sheet.getRow(1).getCell(0);
			result = Integer.parseInt(cell2Update.getStringCellValue())
			//			System.out.println("Sikuli Value from excel sheet:: "+result)
		}
		catch(Exception e){
			e.printStackTrace()
		}

		return result
	}

	public static void updateSikuliDataInExcel(int value){
		try{
			FileInputStream file = new FileInputStream(new File("Resources/Data Files/sikuliDataListSheet.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet("sikuliDataList");
			Cell cell2Update = sheet.getRow(1).getCell(0);
			//			System.out.println("Sikuli Cell Value:: "+cell2Update)
			cell2Update.setCellValue(String.valueOf(value));
			//			System.out.println("Sikuli Updated Cell Value:: "+sheet.getRow(1).getCell(0))
			try {
				// this Writes the workbook issuesList
				FileOutputStream out = new FileOutputStream(new File("Resources/Data Files/sikuliDataListSheet.xlsx"));
				workbook.write(out);
				out.close();
				System.out.println("sikuliDataListSheet.xlsx updated successfully.");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
	}
}
