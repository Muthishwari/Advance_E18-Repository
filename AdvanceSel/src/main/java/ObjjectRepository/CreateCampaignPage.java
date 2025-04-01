package ObjjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage {
	
	
	WebDriver driver;


	public CreateCampaignPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}


	@FindBy (name="campaignName")
	private WebElement campaignname;
	
	@FindBy (name="targetSize")
	private WebElement targetsize;
	  
	
	@FindBy (name="expectedCloseDate")
	 private WebElement closedate;
	
	
	@FindBy (xpath="//button[text()='Create Campaign']")
	private WebElement createcampaign;

	

	

	public WebElement getCampaignname() {
		return campaignname;
	}


	public WebElement getTarget() {
		return targetsize;
	}


	public WebElement getClosedate() {
		return closedate;
	}


	public WebElement getCreatecampaign() {
		return createcampaign;
	} 
	
	public void createCampaignWithMandatoryFields(String campName,String target)
	{
		campaignname.sendKeys(campName);
		targetsize.clear();
		targetsize.sendKeys(target);
		createcampaign.click();
		
	}
	public void createCampaignWithClosteDate(String campName,String target,String date)
	{
		campaignname.sendKeys(campName);
		targetsize.clear();
		targetsize.sendKeys(target);
		closedate.sendKeys(date);
		createcampaign.click();
		
	}
	}

