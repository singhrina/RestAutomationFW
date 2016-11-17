package com.wbl.rest.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	
	static String LOCATION = System.getProperty("user.dir")+"\\resources";
	
	private static Logger logger = Logger.getLogger(ExcelUtils.class);
	

	public  static Object[][] getExcelData(String sheetName,String fileName){
		  Object[][] data=null;
		  if(logger.isDebugEnabled()){
			  logger.debug("in excel dat reading...");
		  }
		  
		  try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(LOCATION+fileName));
			XSSFSheet sheet= wb.getSheet(sheetName);
			
			 int noOfrows=  sheet.getLastRowNum();//give no of rows in the sheet
			 data = new Object[noOfrows-1][];
			 Row row = null;
			 for(int i = 1;i<noOfrows;i++){
				  row = sheet.getRow(i);
				  int noOfcolumns = row.getLastCellNum();
				  String [] strData = new String [noOfcolumns];
				  
				  for(int j = 0; j<noOfcolumns;j++){
				  strData [j] = row.getCell(j).getStringCellValue();
				  }
				  data[1-i]=strData;
				 
			 }
			
		} catch (FileNotFoundException e) {
			logger.error("file not found - Please give correct file details");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("issue regarding the given configurtion properties file");
			e.printStackTrace();
		}
		  return data;
	}
	

}
