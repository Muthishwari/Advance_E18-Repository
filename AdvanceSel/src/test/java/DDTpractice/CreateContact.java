package DDTpractice;



	import java.io.FileInputStream;
	import java.io.IOException;
	import java.time.Duration;


	import java.util.Properties;

	import java.util.Random;
	import java.util.Set;

	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.jspecify.annotations.Nullable;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;


	import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;

	public class CreateContact {

		public static void main(String[] args) throws IOException, InterruptedException {
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
			
			String organization = excelutil.readingDataFromExcelFileUtility("Contact", 1,2)+randomNum;
			String title = excelutil.readingDataFromExcelFileUtility("Contact", 1,3);
			String contactName = excelutil.readingDataFromExcelFileUtility("Contact", 1,4)+randomNum;
			String mobile = excelutil.readingDataFromExcelFileUtility("Contact", 1,5);
			WebDriver driver=null;
			if(BROWSER.equalsIgnoreCase("chrome")
					)
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
			driver.get(URL);
			driver.findElement(By.id("username")).sendKeys(UN);
			driver.findElement(By.id("inputPassword")).sendKeys(PWD);
			driver.findElement(By.xpath("//button[text()='Sign In']")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Campaigns")).click();
			driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
			driver.findElement(By.name("campaignName")).sendKeys(Campaign);
			driver.findElement(By.name("targetSize")).sendKeys(targetSize);
			driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
			Thread.sleep(3000);
			WebElement contactLink = driver.findElement(By.linkText("Contacts"));
		   WebDriverUtility wUtil=new WebDriverUtility();
		   wUtil.waitForElementClickable(driver, contactLink, 20);
		   
			contactLink.click();
		
		Thread.sleep(5000);
			WebElement createContactBtn = driver.findElement(By.xpath("//span[text()='Create Contact']"));
			wUtil.waitForElementClickable(driver, createContactBtn, 20);
			createContactBtn.click();
		
			driver.findElement(By.name("organizationName")).sendKeys(organization);
			driver.findElement(By.name("title")).sendKeys(title);
			driver.findElement(By.name("contactName")).sendKeys(contactName);
			driver.findElement(By.name("mobile")).sendKeys(mobile);
			driver.findElement(By.xpath("//button[@type='button' and contains(@style,'white-space')]")).click();
			
			wUtil.switchToWindow(driver, "selectCampaign");
			WebElement selectTypeDD = driver.findElement(By.id("search-criteria"));
		    wUtil.select(selectTypeDD, "campaignName");
	         driver.findElement(By.id("search-input")).sendKeys(Campaign);
	         driver.findElement(By.xpath("//button[@class='select-btn']")).click();

	        wUtil.switchToWindow(driver, "create-contact");
	        driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
	          Thread.sleep(3000);
	       String ConfirmationMsg = driver.findElement(By.xpath("//div[text()='Contact "+contactName+" Successfully Added']")).getText();
	        if(ConfirmationMsg.contains(contactName))
	        {
	        	System.out.println("Contact added Successfully");
	        }
	        else
	        {
	        	System.out.println("Contact not added");
	        }
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("//*[name()='svg' and @role=\"img\"]")).click();
	       WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
	   	   wUtil.actionMoveToElement(driver, logout);
	       driver.quit();
		}

	}