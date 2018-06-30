/*package test4testng;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelFileSheet_KDF {

	*//**
	 * A very simple program that writes some data to an Excel file using the
	 * Apache POI library.
	 * 
	 * @author Kumar Shivam
	 *
	 **//*

	public void excelWriter(List<String[]> logData, List<String[]> logStatus, String description)
			throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("KDF_log");

		
		Row row2 = sheet.createRow(0);
		//row2.setHeight((short) 20);
		Cell cell2 = row2.createCell(0);
		cell2.setCellValue("Test Description");
		cell2 = row2.createCell(1);
		cell2.setCellValue("KEYWORD");
		cell2 = row2.createCell(2);
		cell2.setCellValue("OBJECT LOCATOR");
		cell2 = row2.createCell(3);
		cell2.setCellValue("OBJECT TYPE");
		cell2 = row2.createCell(4);
		cell2.setCellValue("VALUE");
		cell2 = row2.createCell(5);
		cell2.setCellValue("VARIABLE");
		cell2 = row2.createCell(6);
		cell2.setCellValue("RESULT");
		cell2 = row2.createCell(7);
		cell2.setCellValue("REASON");
		cell2 = row2.createCell(8);
		cell2.setCellValue("FINAL RESULT");
		
		
		
		row2 = sheet.createRow(1);
		Cell cell3 = row2.createCell(0);
		cell3.setCellValue(description);
		int rownum = 2;
		boolean flag = true;
		for (String[] str : logData) {
			Row row = sheet.getRow(rownum);
			//row.setHeight((short) 100);
			
			System.out.println("creating row : " + rownum);
			int cellnum = 1;
			for (String str1 : str) {
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue(str1);
			}
			
			for (String str2 : logStatus.get(rownum-2)) {
				if(str2.equals("failure")){
					flag=false;
				}
				Cell cell = row.createCell(cellnum++);

				cell.setCellValue(str2);
			}
			rownum++;
			Cell cell = row.createCell(cellnum++);

			cell.setCellValue((String) obj);
		}
		Row rowX = sheet.getRow(--rownum);
		Cell cellX = rowX.createCell(rowX.getPhysicalNumberOfCells()+2);
		if(flag){
			cellX.setCellValue("SUCCESS");
		}else cellX.setCellValue("FAILURE");
		
		// unable to write data into merged cell , so not merging the cells
	
		//sheet.addMergedRegion(new CellRangeAddress(1,logData.size(),8, 8));
	
		
		
		
		
		// Iterate over data and write to sheet
		
		 * Set<String> keyset = logData.keySet(); //
		 * System.out.println(keyset.size()+"jasdfkjlaefjeswiho");
		 * 
		 * for (String key : keyset) { // this creates a new row in the sheet
		 * 
		 * 
		 * 
		 * List<String> objArr = logData.get(key); int cellnum = 0; for (String
		 * obj : objArr) { // this line creates a cell in the next column of
		 * that row Cell cell = row.createCell(cellnum++);
		 * 
		 * cell.setCellValue(obj);
		 * 
		 * } sheet.getRow(row.getRowNum() + 1); }
		 

		try (FileOutputStream outputStream = new FileOutputStream(
				"test//resources//data//TestCaseReport2.xlsx")) {
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
*/

package test4testng;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelFileSheet_KDF {

	/**
	 * A very simple program that writes some data to an Excel file using the
	 * Apache POI library.
	 * 
	 * @author Kumar Shivam
	 *
	 **/

	public void excelWriter(List<String[]> logData, List<String[]> logStatus, String description)
			throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("KDF_log");

		/*
		 * for (String op : logData) { System.out.println(op.toString()); }
		 */
		// System.out.println(logData.size()+"whjefj");
		Row row2 = sheet.createRow(0);
		//row2.setHeight((short) 20);
		Cell cell2 = row2.createCell(0);
		cell2.setCellValue("TEST DESCRIPTION");
		cell2 = row2.createCell(1);
		cell2.setCellValue("KEYWORD");
		cell2 = row2.createCell(2);
		cell2.setCellValue("OBJECT LOCATOR");
		cell2 = row2.createCell(3);
		cell2.setCellValue("OBJECT TYPE");
		cell2 = row2.createCell(4);
		cell2.setCellValue("VALUE");
		cell2 = row2.createCell(5);
		cell2.setCellValue("VARIABLE");
		cell2 = row2.createCell(6);
		cell2.setCellValue("RESULT");
		cell2 = row2.createCell(7);
		cell2.setCellValue("REASON");
		cell2 = row2.createCell(8);
		cell2.setCellValue("FINAL RESULT");
		
		boolean flag = true;
		int rownum = 1;
		for (String[] str : logData) {
			Row row = sheet.createRow(rownum);
			//row.setHeight((short) 100);
			
			System.out.println("creating row : " + rownum);
			int cellnum = 0;
			for (String str1 : str) {
				if (rownum==1 && cellnum==0) {
					Cell cell = row.createCell(cellnum++);

					cell.setCellValue(description);
				}else if(cellnum==0){
					Cell cell = row.createCell(cellnum++);

					cell.setCellValue(" ");
				}
				Cell cell = row.createCell(cellnum++);

				cell.setCellValue(str1);
			}
			//int idx = 0;
			for (String str2 : logStatus.get(rownum-1)) {
				
				if(str2.equals("failure")){
					flag=false;
				}
				Cell cell = row.createCell(cellnum++);

				cell.setCellValue(str2);
			}
			rownum++;
		}
		
		Row rowX = sheet.getRow(--rownum);
		Cell cellX = rowX.createCell(rowX.getPhysicalNumberOfCells()+1);
		if(flag){
			cellX.setCellValue("SUCCESS");
		}else cellX.setCellValue("FAILURE");

		try (FileOutputStream outputStream = new FileOutputStream(
				"test//resources//data//TestCaseReport.xlsx")) {
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
