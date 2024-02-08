package com.ExcelR.SauceDemoProject1;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class InventoryPage {
	WebDriverWait wait;
	public InventoryPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	WebElement button;
	//Repository 
	@FindBy (xpath="//button[@id='react-burger-menu-btn']") WebElement HamburgerMenuButton;
	@FindBy (xpath="//div[@class='bm-menu']/nav/a") List <WebElement> HamburgerMenuList; 
	@FindBy (id="inventory_sidebar_link") WebElement AllItemsLink;
	@FindBy (id="about_sidebar_link")WebElement AboutLink;
	@FindBy(id="logout_sidebar_link")WebElement LogoutLink;
	@FindBy (xpath="//div[@class='bm-cross-button']")WebElement HamburgerIconCloseBtn; 
	@FindBy(xpath = "//select[@class='product_sort_container']")private WebElement SelectDropdown;
	@FindBy(xpath="//select[@class='product_sort_container']/option") List<WebElement> DropDownOptions;

	@FindBy(xpath ="//a[@class='shopping_cart_link']")WebElement CartLink;
	@FindBy(xpath = "//span[@class='shopping_cart_badge']") private WebElement shoppingCartContent;
	@FindBy(xpath="//*[@id=\"shopping_cart_container\"]/a/span") private WebElement cartContent;

	@FindBy(xpath = "//div[@class='inventory_list']/div/div[2]/div[1]") private List<WebElement> ProductsDescription;
	@FindBy(xpath = "//div[@class='inventory_item_name ']") private List<WebElement> ProductsName;
	@FindBy(xpath = "//div[@class='inventory_item_price']") private List<WebElement> ProductsPrice;
	@FindBy(xpath = "//div[@class='pricebar']/button") private List<WebElement> InventoryAddToCartBtn;

	@FindBy(xpath = "//div[@class='inventory_list']/div/div[1]")private List<WebElement> ProductImages;
	@FindBy(xpath ="//div[@class='inventory_details_desc_container']/button")WebElement ProductAddToCartBtn;
	@FindBy(xpath = "//div[@class='left_component']/button") private WebElement BackToProductsLink;


	@FindBy (xpath ="button[@id='back-to-products']")private WebElement BackToProductbtn; 
	//Methods
	public boolean presenceOfHamburgerMenuButton()
	{
		return HamburgerMenuButton.isDisplayed();

	}
	public void openHambergerMenu()
	{
		HamburgerMenuButton.click();
		wait.until(ExpectedConditions.visibilityOf(HamburgerIconCloseBtn));
	}
	public boolean isHambergerMenuOpen(){
		return HamburgerMenuList.size()>0;
	}
	public void clickOnAllItemsLink()
	{
		AllItemsLink.click();
	}
	public void clickonAboutLink()
	{
		AboutLink.click();
	}
	public void clickOnLogoutLink()
	{
		LogoutLink.click();
	}
	public void closeHamburgerMenu() throws InterruptedException{		
		HamburgerIconCloseBtn.click();
		Thread.sleep(2000);
	}
	//default drop down value
	public String defaultDropdownValue() {
		Select defaultValue = new Select(SelectDropdown);
		return defaultValue.getFirstSelectedOption().getText();
	}
	public List<WebElement> dropdownContents() {
		return  DropDownOptions;
	}
	//for Sorting Drop Down 
	public String selectSortingOption(String option) {
		Select dropdown = new Select(SelectDropdown);
		dropdown.selectByValue(option);
		return dropdown.getFirstSelectedOption().getText();
	}
	public List<WebElement>	getProductDiscription()
	{
		return ProductsDescription;
	}
	public List<WebElement>	getProductName()
	{
		return ProductsName;
	}
	public List <WebElement> getProductPrice()
	{
		return ProductsPrice;

	}
	public void clickCart() 
	{
		CartLink.click();
	}
	public void clickOnAnyAddToCart() {
		Random rand = new Random();
		for(int i = 0; i< 3; i++) {
			InventoryAddToCartBtn.get(rand.nextInt(InventoryAddToCartBtn.size())).click();
		}		
	}
	//click on any image
	public void clickOnAnyImage() {
		Random random = new Random(); 
		ProductImages.get(random.nextInt(ProductImages.size())).click();		
	}
	//add to cart button text retrieval
	public String productsAddToCart() {
		return ProductAddToCartBtn.getText();
	}
	public void clickOnProductsAddToCart() {
		ProductAddToCartBtn.click();
	}
	//cart content
	public String getCartValue() {
		return cartContent.getText();
	}
	//clickonBack to products
	public void clickonBackToProduct() {
		BackToProductbtn.click();
	}

}