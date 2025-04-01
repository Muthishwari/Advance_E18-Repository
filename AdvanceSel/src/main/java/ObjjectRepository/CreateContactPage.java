package ObjjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.WebDriverUtility;

public class CreateContactPage {
	WebDriver driver;
	
	public CreateContactPage(WebDriver driver)
{
	
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}
	
	@FindBy (name="organizationName")
    private WebElement orgName;
	
	
	@FindBy (name="title")
	private WebElement title;
	
	
	@FindBy (name="contactName")
	private	WebElement contactName;
	
	
	@FindBy (name="mobile")
	private WebElement mobile;
	 
	
	@FindBy(xpath="(//*[name()='svg' and @role='img'])[2]")
	private WebElement selectCampaign;
	
	@FindBy (id="search-criteria")
	private	WebElement searchDD;
	
	@FindBy (id="search-input")
	private	WebElement searchInp;
	
	
	@FindBy(xpath="//button[@class='select-btn']")
	private		WebElement selectbtn;
	
	
	@FindBy (xpath="//button[text()='Create Contact']")
	private		WebElement createContbutton;
	
	public WebElement getOrgName() {
		return orgName;
	}


	public WebElement getTitle() {
		return title;
	}


	public WebElement getContactName() {
		return contactName;
	}


	public WebElement getMobile() {
		return mobile;
	}


	public WebElement getSelectCampaign() {
		return selectCampaign;
		
	}


	public WebElement getSearchDD() {
		return searchDD;
	}


	public WebElement getSearchInp() {
		return searchInp;
	}


	public WebElement getSelectbtn() {
		return selectbtn;
	}


	public WebElement getCreateContbutton() {
		return createContbutton;
	}
	

	public void createContactWithCampaign(String org,String tit, String cont,String mob,String childUrl,String parentUrl,String campName)
	{
		orgName.sendKeys(org);
		title.sendKeys(tit);
		contactName.sendKeys(cont);
		mobile.sendKeys(mob);
		selectCampaign.click();
		WebDriverUtility wUtil= new WebDriverUtility();
		wUtil.switchToWindow(driver, childUrl);
		wUtil.select(searchDD, 1);
		searchInp.sendKeys(campName);
		selectbtn.click();
		wUtil.switchToWindow(driver, parentUrl);
		createContbutton.click();
	}
}
