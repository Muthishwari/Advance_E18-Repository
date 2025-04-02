package CampaignTest;


	
	import static org.testng.Assert.assertEquals;

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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
	import GenericUtility.JavaUtility;
	import GenericUtility.PropertiesFileUtility;
	import GenericUtility.WebDriverUtility;
	import ObjjectRepository.CampaignPage;
	import ObjjectRepository.CreateCampaignPage;
	import ObjjectRepository.DashboardPage;
	import ObjjectRepository.LoginPage;
import genericBaseClassUtility.BaseClass;
import genericListenserUtility.ListenserImp;
//@Listeners(ListenserImp.class)
	public class Createcampaign extends BaseClass {
		//@Parameters("browser")
		//@Test(groups= {"Integration"})
		//@Test(invocationCount = 3, threadPoolSize= 4)
		@Test()
		public void createCampaignTest() throws InterruptedException, IOException {
		
			
			JavaUtility jUtil=new JavaUtility();
			int randomNum = jUtil.getRandomNum(5000);
			
			ExcelFileUtility exUtil=new ExcelFileUtility();
			String Campaign = exUtil.readingDataFromExcelFileUtility("DDT", 1, 2)+randomNum;
			String targetSize = exUtil.readingDataFromExcelFileUtility("DDT", 1, 3);
			
			String expectedURL="http://49.249.28.218:8098/dashboard";
			//Launching the browser
		
		
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			//navigating to ninza CRM
			
			//enter the username and password
		
			DashboardPage dp=new DashboardPage(driver);
			Thread.sleep(2000);
			dp.getCampaignsLink().click();
			Thread.sleep(2000);
			CampaignPage cp=new CampaignPage(driver);
			cp.getCreateCampaign().click();
			Thread.sleep(2000);
			CreateCampaignPage ccp=new CreateCampaignPage(driver);
			ccp.createCampaignWithMandatoryFields(Campaign, targetSize);
			Thread.sleep(2000);
			String ConfMsg = cp.getConfMsg().getText();
		     boolean status = ConfMsg.contains(Campaign);
			Assert.assertEquals(status, true,"Campaign not added");
		//	Assert.assertTrue(status, "campaign not added");
	
			Reporter.log("campaign " +Campaign+" added successfully",true);
			Thread.sleep(4000);
			

		}
		//@Parameters("browser")
	//@Test (groups= {"SmokeTest"})
	@Test(dependsOnMethods="createCampaignTest")
		public void createCampaignWithCloseDateTest() throws IOException, InterruptedException
		{
		
		
		
		JavaUtility jUtil=new JavaUtility();
		int randomNum = jUtil.getRandomNum(5000);
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String Campaign = exUtil.readingDataFromExcelFileUtility("DDT", 1, 2)+randomNum;
		String targetSize = exUtil.readingDataFromExcelFileUtility("DDT", 1, 3);
		
		String closeDate = jUtil.generateReqDate(30);
		
		String expectedURL="http://49.249.28.218:8098/dashboard";
		//Launching the browser
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//navigating to ninza CRM
	
		//enter the username and password
		
		DashboardPage dp=new DashboardPage(driver);
		Thread.sleep(2000);
		dp.getCampaignsLink().click();
		CampaignPage cp=new CampaignPage(driver);
		cp.getCreateCampaign().click();
		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.createCampaignWithClosteDate(Campaign, targetSize,closeDate);
		Thread.sleep(2000);
		
		String ConfMsg = cp.getConfMsg().getText();
	     boolean status = ConfMsg.contains(Campaign);
		Assert.assertEquals(status, true,"Campaign not added");
	//	Assert.assertTrue(status, "campaign not added");
		Reporter.log("campaign " +Campaign+" added successfully",true);
		Thread.sleep(6000);
		
	}

}
 

	
	 


