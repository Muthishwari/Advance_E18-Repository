package DDTpractice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjjectRepository.DashboardPage;
import ObjjectRepository.LoginPage;

public class LoginTest {

	public static void main(String[] args) throws IOException, InterruptedException {
	PropertiesFileUtility propUtil= new PropertiesFileUtility();
	String BROWSER = propUtil.readingDataFromPropertyFile("browser");
	String URL = propUtil.readingDataFromPropertyFile("url");
	String UN = propUtil.readingDataFromPropertyFile("uname");
	String PWD = propUtil.readingDataFromPropertyFile("pwd");
		
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("CHROME"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("FIREFOX"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("EDGE"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		LoginPage lp= new LoginPage(driver);
         lp.login(UN, PWD);
		Thread.sleep(2000);
		String url=driver.getCurrentUrl();
		System.out.println(url);
		if(url.contains("dashboard"))
		{
			System.out.println("Expected Page displayed");
		}
		else
		{
			System.out.println(" Expected page not displayed");
		
		}
		DashboardPage dp= new DashboardPage(driver);
		dp.logout();
	//	driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		//WebElement logbutton = driver.findElement(By.xpath("//div[text()='Logout ']"));
		// WebDriverUtility wUtil=new WebDriverUtility();
	    //	wUtil.actionMoveToElement(driver, logbutton);
		driver.close();
	
		
		}}
