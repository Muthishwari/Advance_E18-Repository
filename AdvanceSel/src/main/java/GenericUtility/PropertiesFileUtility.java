package GenericUtility;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;

public class PropertiesFileUtility {
	
	public String readingDataFromPropertyFile(String Key) throws IOException 
	{
		
	
	FileInputStream fis= new  FileInputStream("D:\\AdvanceSel\\src\\test\\resources\\CommonData_E18.Properties");
	Properties prop=new Properties();
	prop.load(fis);
	String data = prop.getProperty(Key);
	return data;


}
}
