package ObjjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {

WebDriver driver;
	
	public CampaignPage(WebDriver driver)
	{
		
		this.driver=driver;
	PageFactory.initElements(driver, this);
	
}
	@FindBy (xpath="//span[text()='Create Campaign']")
	WebElement createCampaign;
	
	
	@FindBy (xpath="//select [@class='form-control']")
	WebElement searchByDD;
	
	
	@FindBy(xpath="//input[@type='text']")
	WebElement searchfield;

	
	@FindBy (xpath="//div[@role='alert']")
	private WebElement confMsg;
	
	public WebElement getCreateCampaign() {
		return createCampaign;
	}

	public WebElement getSearchByDD() {
		return searchByDD;
	}

	public WebElement getSearchfield() {
		return searchfield;
	}

	public WebElement getConfMsg() {
		return confMsg;
	}
	
}
