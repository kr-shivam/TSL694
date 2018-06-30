package test4testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;

/**
 * this is the excel executor goes row by row of excel reads the keywords in
 * excel sheet executes one by one
 *
 */
public class ExcelExecutor_KDF {

	WebDriver driver;
	Sheet excelSheet;
	GetByObjectAndAct_KDF getAndAct;
	HashMap<String, String> variableData = new HashMap<String, String>();
	public HashMap<String, String> getVariableMap() {
        return variableData;
   }
	List<String[]> logData = new ArrayList<String[]>();
	public List<String[]> getLogMap() {
        return logData;
   }
	List<String[]> logStatus = new ArrayList<String[]>();
	public List<String[]> getLogStatus() {
        return logStatus;
   }
	
	List<String> lsOldData = new ArrayList<String>();
	@BeforeTest
	public void setUp() throws Exception {
		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));
		
	}

	@Test
	public void testLogin() throws Exception {

		
		getAndAct = new GetByObjectAndAct_KDF(driver);
		// Read keyword sheet
		//this.driver = getAndAct.g
		excelSheet = ReadExcelFileSheet_KDF.getExcelSheet(
				"test\\resources\\data\\", "TestCase.xlsx",
				"Frameworksheet");
		// Find number of rows in excel file
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
		System.out.println(rowCount);
		// Create a loop over all the rows of excel file to read it
		for (int i = 1; i < rowCount + 1; i++) {
			
			// Loop over all the rows
			Row row = excelSheet.getRow(i);
			//System.out.println(row.getLastCellNum());
			// Check if the first cell contain a value, if yes, That means it is
			// the new testcase name
			if (row.getCell(0).toString().length() == 0) {
				lsOldData.clear();
				// Print testcase detail on console
				System.out.println(row.getCell(1).toString() + "----"
						+ row.getCell(2).toString() + "----"
						+ row.getCell(3).toString() + "----"
						+ row.getCell(4).toString() + "----"
						+ row.getCell(5).toString());
				String[] tempS = {row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(),row.getCell(4).toString(),row.getCell(5).toString()};
				logData.add(tempS);
			
				// Call perform function to perform operation on UI
				try {
					
					getAndAct.performAction(row.getCell(1).toString(), row
							.getCell(2).toString(), row.getCell(3).toString(),
							row.getCell(4).toString(),row.getCell(5).toString());
				
					String[] sStatus = {"success"};
					logStatus.add(sStatus);
					
				} catch (Exception e) {
					System.out.println("fail =" + e.getMessage());
					
					String[] fStatus = {"failure", e.getMessage()};
					logStatus.add(fStatus);
					
					
					
				}// end catch
			}// end if
			else {
				WriteExcelFileSheet_KDF excelWriter = new WriteExcelFileSheet_KDF();
				
				excelWriter.excelWriter(logData, logStatus, row.getCell(0).toString());
				
				System.out.println("New Testcase->" + row.getCell(0).toString()
						+ " Started");
			}// end else
		}// end for
	}

	@AfterClass
	public void tearDown() {

		//driver.quit();
		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - "
				+ new Timestamp(date.getTime()));
	}
}