package DDTpractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjjectRepository.LoginPage;

public class CampaignTest2 {

	public static void main(String[] args) throws InterruptedException, IOException {
		PropertiesFileUtility propUtil= new PropertiesFileUtility();
		String BROWSER = propUtil.readingDataFromPropertyFile("browser");
		String URL = propUtil.readingDataFromPropertyFile("url");
		String UN = propUtil.readingDataFromPropertyFile("uname");
		String PWD = propUtil.readingDataFromPropertyFile("pwd");
		
		
		JavaUtility Jutil=new JavaUtility();
		int randomNum=Jutil.getRandomNum(2000);

		ExcelFileUtility excelutil=new ExcelFileUtility();
		String Campaign = excelutil.readingDataFromExcelFileUtility("DDT", 1, 2)+randomNum;
		String targetSize = excelutil.readingDataFromExcelFileUtility("DDT", 1,3);
		
		
 
	String closeDate = Jutil.generateReqDate(30);
		
	//  Date dateobj= new Date();
	//	SimpleDateFormat sim= new SimpleDateFormat("dd-MM-YYYY"); // MM should be Capital letter ,the only works
	//	String todaysDate = sim.format(dateobj);
	//	System.out.println(todaysDate);
		
	//	Calendar cal = sim.getCalendar();
	//	cal.add(Calendar.DAY_OF_MONTH, 30);
	//	String closeDate = sim.format(cal.getTime());
	//	System.out.println(closeDate);
		
		
		
		
		String expectedURL="http://49.249.28.218:8098/dashboard";
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
		LoginPage lp= new LoginPage(driver);
        lp.login(UN, PWD);
        
		Thread.sleep(2000);
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		driver.findElement(By.name("targetSize")).clear();
		driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		Thread.sleep(2000);
		driver.findElement(By.name("expectedCloseDate")).sendKeys(closeDate);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(2000);
		String ConfMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		if(ConfMsg.contains(Campaign))
		{
			System.out.println("campaign added successfully");
		}
		else
		{
			System.out.println("campaign not added");
		}
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
        WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		   WebDriverUtility wUtil=new WebDriverUtility();
    	wUtil.actionMoveToElement(driver, logout);
        //close the browser
        driver.quit();

	}

}
