package com.orangehrm.framework.components;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * Data reading utility
 * 
 * @author Zabiulla_Pro
 *
 */

public class ReadExcelTestData implements Constants {

	private static final String excelDir = CURRENT_DIR + ReadPropertiesFile.getProperties().get(TEST_DATA_PATH);

	private static Map<String, HashMap<String, String>> suiteData;
	private static Workbook workbook;
	private static FileInputStream fis;
	private static Sheet excelSheet;

	static Cell cell;

	/**
	 * 
	 * This will return the Suite Data
	 * 
	 * @param workBookName
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */

	public static Map<String, HashMap<String, String>> getSuiteData(String workBookName, String sheetName)
			throws Exception {
		String excelFilePath = excelDir + workBookName + ".xlsx";
		fis = new FileInputStream(new File(excelFilePath));

		LinkedList<String> row1 = new LinkedList<String>();
		suiteData = new HashMap<String, HashMap<String, String>>();

		workbook = new XSSFWorkbook(fis);
		excelSheet = workbook.getSheet(sheetName); // this is sheet name and not index
		Iterator<Row> iterator = excelSheet.iterator();

		// loading the Row 0 which is a header
		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();

		Cell cell = cellIterator.next();
		while (cellIterator.hasNext()) {
			cell = cellIterator.next();
			switch (cell.getCellType()) {
			case STRING:
				row1.add(cell.getStringCellValue());
				break;
			case BOOLEAN:
				row1.add(cell.getStringCellValue());
				break;
			case NUMERIC:
				row1.add("" + cell.getNumericCellValue());
				break;
			default:
				break;
			}
		}
		MyLog.logInfo("Number of Rows in " + sheetName + " is :" + excelSheet.getPhysicalNumberOfRows());

		while (iterator.hasNext()) {
			HashMap<String, String> data1 = new HashMap<String, String>();
			nextRow = iterator.next();
			cellIterator = nextRow.cellIterator();
			String cellTestCase = "";

			for (int i = 0; i < nextRow.getLastCellNum(); i++) {
				cell = cellIterator.next();
				String cellData = "";
				switch (cell.getCellType()) {
				case STRING:
					cellData = cell.getStringCellValue();
					break;
				case BOOLEAN:
					cellData = cell.getStringCellValue();
					break;
				case NUMERIC:
					cellData = "" + cell.getNumericCellValue();
					break;

				default:
					cellData = "" + cell.getStringCellValue();
					break;
				}
				if (i == 0) {
					cellTestCase = cellData;
				} else {
					String temp = row1.get(i - 1);
					data1.put(row1.get(i - 1), cellData);
					MyLog.logInfo("" + data1);
				}
			}
			suiteData.put(cellTestCase, data1);
		}
		workbook.close();
		fis.close();

		return suiteData;
	}

	public static void main(String[] args) throws Exception {
		ReadExcelTestData.getSuiteData("TestData", "Data");
	}
}
