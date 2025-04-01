package ObjjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.WebDriverUtility;

public class ProductPage {
	
	WebDriver driver;
	
	
	
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy (xpath="//select[@class='form-control']")
	private WebElement prodId;
	
	
	@FindBy (xpath="//input [@type='text']")
	private WebElement  searchId;
	
	
	@FindBy (xpath="//span[text()='Add Product']")
	private WebElement createProduct;

	@FindBy (xpath="//div[@role='alert']")
	private WebElement confMsg;


	public WebElement getProdId() {
		return prodId;
	}


	public WebElement getSearchId() {
		return searchId;
	}


	public WebElement getCreateProduct() {
		return createProduct;
	}


	public WebElement getConfMsg() {
		return confMsg;
	}
	
	

}
