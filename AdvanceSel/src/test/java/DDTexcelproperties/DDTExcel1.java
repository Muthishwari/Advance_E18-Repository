package DDTexcelproperties;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DDTExcel1 {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

    FileInputStream fis= new FileInputStream("D:\\AdvanceSel\\src\\test\\resources\\E18.xlsx");
   Workbook wb = WorkbookFactory.create(fis);
   String Campaign = wb.getSheet("DDT").getRow(1).getCell(2).getStringCellValue();
      double targetsize = wb.getSheet("DDT").getRow(1).getCell(3).getNumericCellValue();  
      System.out.println(Campaign);
      System.out.println(targetsize);
    

	}

}
