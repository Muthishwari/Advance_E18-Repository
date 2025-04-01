package DDTexcelproperties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WritingDataExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		  FileInputStream fis= new FileInputStream("D:\\AdvanceSel\\src\\test\\resources\\E18.xlsx");
		   Workbook wb = WorkbookFactory.create(fis);
		   
		   wb.createSheet("Writedata").createRow(1).createCell(2).setCellValue("Selenium");
		   FileOutputStream fos= new FileOutputStream("D:\\AdvanceSel\\src\\test\\resources\\E18.xlsx");
		   wb.write(fos);
		   wb.close();
		   System.out.println("Data written Successfully");

	}

}
