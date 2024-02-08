package readExcelFile;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileData {
	static FileInputStream fis;
	static XSSFSheet sheet;
	public static XSSFSheet readExcelData(String Sheet) {
		try {
			fis = new FileInputStream(".\\ExcelData\\SauceDemo.xlsx");
		}catch(Exception e) {
			System.out.println("Sorry! the specified file do not exist");
			//e.printStackTrace();
		}
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(Sheet);
		}catch (Exception e) {
			System.out.println("The workbook does not exist");
		}
		return sheet;
	}	
}