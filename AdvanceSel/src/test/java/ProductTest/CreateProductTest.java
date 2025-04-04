package ProductTest;

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
import ObjjectRepository.CreateProductPage;
import ObjjectRepository.DashboardPage;
import ObjjectRepository.LoginPage;
import ObjjectRepository.ProductPage;
import genericBaseClassUtility.BaseClass;
import genericListenserUtility.ListenserImp;
//@Listeners(ListenserImp.class)
public class CreateProductTest extends BaseClass{
	//@Parameters("browser")
	@Test(groups= {"RegressionTest"})

public void addProductTest()throws IOException, InterruptedException {
	
	
		
		
		JavaUtility jutil=new JavaUtility();
		int randNum = jutil.getRandomNum(2000);
		ExcelFileUtility excelUtil=new ExcelFileUtility();
		String prodName = excelUtil.readingDataFromExcelFileUtility("Product", 0, 1)+randNum;
		String quantity = excelUtil.readingDataFromExcelFileUtility("Product", 1, 1);
		String price = excelUtil.readingDataFromExcelFileUtility("Product", 1, 2);
		
		Random run= new Random();
		int randomnum = run.nextInt();

		
		//Launching the browser
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
		
		DashboardPage dp= new DashboardPage(driver);
		Thread.sleep(2000);
		dp.getProductsLink().click();

		
		ProductPage pp= new ProductPage(driver);
		pp.getCreateProduct().click();
		
         CreateProductPage cpp=new CreateProductPage(driver);
         cpp.createProductWithMandatoryFields(prodName, quantity, price);
        
         Thread.sleep(3000);
         cpp.getAddprod().click();

		
		Thread.sleep(3000);
		String ConfMsg = pp.getConfMsg().getText();

	     boolean status = ConfMsg.contains(prodName);
		Assert.assertEquals(status, true,"Product not added");
	//	Assert.assertTrue(status, "Product not added");

		Reporter.log("campaign " +prodName+" added successfully",true);
		Thread.sleep(5000);
	
		
	}

}
