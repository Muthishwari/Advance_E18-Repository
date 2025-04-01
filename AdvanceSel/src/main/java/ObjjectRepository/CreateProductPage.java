package ObjjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.WebDriverUtility;

public class CreateProductPage
{

WebDriver driver;
public CreateProductPage(WebDriver driver)
{
	
	this.driver=driver;
PageFactory.initElements(driver, this);

}

	
	@FindBy (name="productName")
	private WebElement productName;
	
	@FindBy (name="productCategory")
	private WebElement productCategory;
	
	@FindBy (name="quantity")
	private WebElement quantity;
	
	
	@FindBy (name="price")
	private WebElement price;
	
	@FindBy (name="vendorId")
	private WebElement vendor;
	
	@FindBy (xpath="//button[text()='Add']")
	private WebElement addprod;

	public WebElement getProductName() {
		return productName;
	}

	public WebElement getProductCategory() {
		return productCategory;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getVendor() {
		return vendor;
		
	}

	public WebElement getAddprod() {
		return addprod;
	}

	public void createProductWithMandatoryFields(String prodName,String qty,String Price)
	
	{
		productName.sendKeys(prodName);
		WebDriverUtility wUtil= new WebDriverUtility();
		wUtil.select(productCategory, 1);
		quantity.sendKeys(qty);
		price.sendKeys(Price);
		
		//wUtil.select(vendor, 2);
		wUtil.select(vendor, "VID_001");
	
		}
	


}
