package ContactTest;

import java.io.IOException;
import java.time.Duration;

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
import ObjjectRepository.ContactPage;
import ObjjectRepository.CreateCampaignPage;
import ObjjectRepository.CreateContactPage;
import ObjjectRepository.DashboardPage;
import ObjjectRepository.LoginPage;
import genericBaseClassUtility.BaseClass;
import genericListenserUtility.ListenserImp;
//@Listeners(ListenserImp.class)
public class CreateContact extends BaseClass{
	//@Parameters ("browser")
	@Test(groups= {"RegressionTest"})
	public void CreateContactWithCampaignTest() throws IOException, InterruptedException {
	
		
		JavaUtility Jutil=new JavaUtility();
		int randomNum=Jutil.getRandomNum(2000);
		
		ExcelFileUtility excelutil=new ExcelFileUtility();
		String Campaign = excelutil.readingDataFromExcelFileUtility("DDT", 1, 2)+randomNum;
		String targetSize = excelutil.readingDataFromExcelFileUtility("DDT", 1,3);
		
		String organization = excelutil.readingDataFromExcelFileUtility("Contact", 1,2)+randomNum;
		String title = excelutil.readingDataFromExcelFileUtility("Contact", 1,3);
		String contactName = excelutil.readingDataFromExcelFileUtility("Contact", 1,4)+randomNum;
		String mobile = excelutil.readingDataFromExcelFileUtility("Contact", 1,5);
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
		
		DashboardPage dp= new DashboardPage(driver);
		Thread.sleep(2000);
		dp.getCampaignsLink().click();
	
		
		CampaignPage cp= new CampaignPage(driver);
		cp.getCreateCampaign().click();
		
		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.createCampaignWithMandatoryFields(Campaign, targetSize);
		Thread.sleep(3000);
		
		
		WebElement contactLink = dp.getContactsLink();
	    WebDriverUtility wUtil=new WebDriverUtility();
	    wUtil.waitForElementClickable(driver, contactLink, 20);
		contactLink.click();
	
	    Thread.sleep(5000);
	    ContactPage cp1=new ContactPage(driver);
	    
	    
		WebElement createContactBtn = cp1.getCreateContact();
		wUtil.waitForElementClickable(driver, createContactBtn, 20);
		createContactBtn.click();
	
		
	
		
		CreateContactPage cct=new CreateContactPage(driver);
		cct.createContactWithCampaign(organization, title, contactName, mobile, "selectCampaign", "create-contact", Campaign);
		
		
          Thread.sleep(5000);
        String ConfirmationMsg = cp.getConfMsg().getText();
        boolean status = ConfirmationMsg.contains(contactName);
        Assert.assertEquals(status, true,"Contact not added");
		//	Assert.assertTrue(status, "Contact not added");
        
       Reporter.log("Contact " +contactName+  " added Successfully",true);
       
      

        Thread.sleep(4000);
		
	}

}


