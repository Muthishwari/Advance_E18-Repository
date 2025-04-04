package DDTpractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import GenericUtility.WebDriverUtility;

public class CreateProduct{

	
	public static void main(String[] args)  throws IOException, InterruptedException {
	
		
		FileInputStream fis=new FileInputStream("D:\\AdvanceSel\\src\\test\\resources\\CommonData_E18.Properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String UN = prop.getProperty("uname");
		String PWD = prop.getProperty("pwd");
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);
		
		Random run= new Random();
		int randomnum = run.nextInt();

	    FileInputStream fis1= new FileInputStream("D:\\AdvanceSel\\src\\test\\resources\\E18.xlsx");
	   Workbook wb = WorkbookFactory.create(fis1);
	   String ProductName = wb.getSheet("Product").getRow(0).getCell(0).getStringCellValue()+randomnum;
	      String Quantity = wb.getSheet("Product").getRow(1).getCell(1).getStringCellValue();
	        String Price= wb.getSheet("Product").getRow(1).getCell(2).getStringCellValue();
		
		
		//Launching the browser
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//navigating to ninza CRM
		driver.get(URL);
		//enter the username and password
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		//click on sign in button
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productName")).sendKeys(ProductName);
		   WebDriverUtility wUtil=new WebDriverUtility();
		WebElement category = driver.findElement(By.name("productCategory"));
	    // wUtil.select(category, "Electronics");
		   
		//Select s= new Select(category);
	//	s.selectByValue("Electronics");
		driver.findElement(By.name("quantity")).sendKeys(Quantity);
		driver.findElement(By.name("price")).sendKeys(Price);
		WebElement vendor = driver.findElement(By.name("vendorId"));
		wUtil.select(vendor, "VID_008");
		//wUtil.select(vendor, "Vendor1 - (Electronics)");
		//Select s1= new Select(vendor);
		
		//s1.selectByIndex(2);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		String ConfMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		if(ConfMsg.contains(ProductName))
		{
			System.out.println("Product added successfully");
		}
		else
		{
			System.out.println("Product not added");
		}
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		
	
		WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		wUtil.actionMoveToElement(driver, logout);
		driver.close();
		
	}

}
