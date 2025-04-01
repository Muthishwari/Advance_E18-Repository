package genericBaseClassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import GenericUtility.DatabaseUtility;
import GenericUtility.PropertiesFileUtility;
import ObjjectRepository.DashboardPage;
import ObjjectRepository.LoginPage;

	public class BaseClass {
		public WebDriver driver=null;
		public static WebDriver  sdriver=null;
		DatabaseUtility dbUtil=new DatabaseUtility();
		PropertiesFileUtility pro=new PropertiesFileUtility();

	@BeforeSuite
	public void beforesuite() throws SQLException
	{
		
	
	System.out.println("Establisted database connection");
	dbUtil.getDBConnection("jdbc:mysql://localhost:3306/Ninza_E18", "root", "Ishwari@0210");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Pre Configuration setup");
	}
	@BeforeClass
	public void beforeclass() throws IOException
	{
		System.out.println("Launch the browser");
		String BROWSER = pro.readingDataFromPropertyFile("browser");
		ChromeOptions Coption=new ChromeOptions();
		FirefoxOptions Foption=new FirefoxOptions();
		EdgeOptions Eoption=new EdgeOptions();
		Coption.addArguments("--headless");
		Foption.addArguments("--headless");
		Eoption.addArguments("--headless");
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver(Coption);
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver(Foption);
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver(Eoption);
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
	}
	@BeforeMethod
	public void beforemethod() throws IOException
	{
		System.out.println("Login");
		String URL = pro.readingDataFromPropertyFile("url");
		String UN = pro.readingDataFromPropertyFile("uname");
		String PWD = pro.readingDataFromPropertyFile("pwd");
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.login(UN, PWD);
	}
	
	@AfterMethod
	public void aftermthod()
	{
		System.out.println("Logout");
		DashboardPage dp=new DashboardPage(driver);
		dp.logout();
	}
	@AfterClass
	public void afterclass()
	{
		System.out.println("close the browser");
		driver.quit();
	}
	@AfterTest
	public void aftetest()
	{
		System.out.println("Post  configuration set up");
	}
	
	@AfterSuite
	public void aftersuite()
	{
		System.out.println("Close the db connection");
		dbUtil.closeDBConnection();
	}
	
}
