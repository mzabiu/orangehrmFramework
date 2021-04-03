package com.orangehrm.reporting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.orangehrm.framework.components.Constants;

/**
 * 
 * @author Zabiulla_Pro
 *
 */
public class GenerateExcelReport implements Constants {

	private static String reportLocation = "reports/";

	public static void writeFileUsingPOI(ArrayList<Object[]> executionResult, String fileName) throws IOException {
		// create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Result");

		// Iterate over data and write to sheet
		int rownum = 0;
		for (Object[] excelData : executionResult) {
			Row row = sheet.createRow(rownum++);

			int cellnum = 0;
			for (Object obj : excelData) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
				else if (obj.toString().contains(".png")) {
					cell.setCellType(CellType.FORMULA);
					cell.setCellFormula(("=" + (String) obj));
				}
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(reportLocation + fileName + ".xlsx"));
			workbook.write(out);
			out.close();
			MyLog.logInfo("Atomation has been created successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
	}

}
