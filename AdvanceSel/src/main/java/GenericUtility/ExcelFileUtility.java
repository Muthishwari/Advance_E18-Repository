package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility 
{
	
	public String readingDataFromExcelFileUtility(String sheet,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis= new FileInputStream("D:\\AdvanceSel\\src\\test\\resources\\E18.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
	String data = wb.getSheet(sheet).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
		
	}

}
