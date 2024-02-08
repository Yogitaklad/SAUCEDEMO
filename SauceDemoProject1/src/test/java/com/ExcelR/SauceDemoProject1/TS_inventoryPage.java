package com.ExcelR.SauceDemoProject1;



import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import readExcelFile.ExcelFileData;

public class TS_inventoryPage {

	WebDriver driver;
	InventoryPage inventoryPage; LoginPage loginPage;
	XSSFSheet inventorysheet = ExcelFileData.readExcelData("InventoryPageData");
	
	@BeforeTest
	public void launchBrowserAndInititialisation() {
		driver= new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		inventoryPage = new InventoryPage(driver); loginPage = new LoginPage(driver);
		loginPage.login("standard_user", "secret_sauce");
	}
	
	@Test
	public void testpresenceOfHamburgerMenuButton()
	{
		Assert.assertTrue(inventoryPage.presenceOfHamburgerMenuButton(), "Hamburger Menu is not present");
	}
	@Test
	public void testHamburgerMenuOpens()
	{
		inventoryPage.openHambergerMenu();
		Assert.assertTrue(inventoryPage.isHambergerMenuOpen(), "Hamburger Menu is not open");
	}
	@Test
	public void testAllItemPageredirect()
	{
		inventoryPage.openHambergerMenu();
		inventoryPage.clickOnAllItemsLink();		
		Assert.assertEquals(driver.getCurrentUrl(), inventorysheet.getRow(4).getCell(1).toString(), "All item page did not redirect to the expected URL");
	}
	@Test
	public void testAboutPageredirect()
	{
		inventoryPage.openHambergerMenu();
		inventoryPage.clickonAboutLink();
		Assert.assertEquals(driver.getCurrentUrl(), inventorysheet.getRow(5).getCell(1).toString(), "About page did not redirect to the expected URL");
	}
	@Test
	public void testLogoutLink()
	{
		inventoryPage.openHambergerMenu();
		inventoryPage.clickOnLogoutLink();
		Assert.assertTrue(driver.getCurrentUrl().contains(inventorysheet.getRow(6).getCell(1).toString()), "Logout did not navigate to the login page");
	}
	@Test
	public void testcloseHamburgerMenu() throws InterruptedException
	{
		inventoryPage.openHambergerMenu();
		inventoryPage.closeHamburgerMenu();
		Assert.assertTrue(inventoryPage.isHambergerMenuOpen(), "Hamburger Menu is not closed.");
	}	
	@Test
    public void testDefaultDropdown() {
        // By default, the sorting should be "Name A-Z"
		Assert.assertEquals(inventoryPage.defaultDropdownValue(), inventorysheet.getRow(7).getCell(1).toString());
	}
	@Test
	public void testDropdownContents() {
		XSSFSheet sheet = ExcelFileData.readExcelData("InventoryPageData");
		List<WebElement> dropdownValues = inventoryPage.dropdownContents();
		for(int i = 0,r = 5; i <dropdownValues.size();i++,r++) {
			Assert.assertEquals(dropdownValues.get(i).getText(), sheet.getRow(r).getCell(1).toString());			
		}
	}	
	@Test
	public void selectByNameAtoZValidation() {
		inventoryPage.selectSortingOption(inventorysheet.getRow(7).getCell(2).toString());
		List<WebElement> ProductsName = inventoryPage.getProductName();
		for(int i = 0,r = 11; i <ProductsName.size();i++,r++) {
			Assert.assertEquals(ProductsName.get(i).getText(), inventorysheet.getRow(r).getCell(1).toString());			
		}
	}	
	@Test
	public void selectByNameZtoAValidation() {
		inventoryPage.selectSortingOption(inventorysheet.getRow(8).getCell(2).toString());
		List<WebElement> ProductsName = inventoryPage.getProductName();
		for(int i = 0,r = 17; i <ProductsName.size();i++,r++) {
			Assert.assertEquals(ProductsName.get(i).getText(), inventorysheet.getRow(r).getCell(1).toString());			
		}
	}
	@Test
	public void selectByPriceHighToLowValidation() {
		inventoryPage.selectSortingOption(inventorysheet.getRow(10).getCell(2).toString());
		List<WebElement> ProductsPrice = inventoryPage.getProductPrice();
		for(int i = 0,r = 23; i <ProductsPrice.size();i++,r++) {
			System.out.println(ProductsPrice.get(i).getText());
			Assert.assertEquals(ProductsPrice.get(i).getText(), inventorysheet.getRow(r).getCell(1).toString());			
		}
	}
	@Test
	public void selectByPriceLowToHighValidation() {
		inventoryPage.selectSortingOption(inventorysheet.getRow(9).getCell(2).toString());
		List<WebElement> ProductsPrice = inventoryPage.getProductPrice();
		for(int i = 0,r = 29; i <ProductsPrice.size();i++,r++) {
			System.out.println(ProductsPrice.get(i).getText());
			Assert.assertEquals(ProductsPrice.get(i).getText(), inventorysheet.getRow(r).getCell(1).toString());			
		}
	}
	@Test
	public void inventoryAddToCartBtnValidation() {		
		inventoryPage.clickOnAnyAddToCart();
		inventoryPage.getCartValue();
		System.out.println(inventoryPage.getCartValue());
	}
	@Test
	public void clickOnProductImageValidation() {
		inventoryPage.clickOnAnyImage();
		Assert.assertTrue(driver.getCurrentUrl().contains(inventorysheet.getRow(35).getCell(1).toString()));
	}
	
	@Test
	public void productsAddToCartBtnValidation() {
		inventoryPage.clickOnAnyImage();
		System.out.println(inventoryPage.productsAddToCart());
		inventoryPage.clickOnProductsAddToCart();
		System.out.println(inventoryPage.productsAddToCart());
		inventoryPage.getCartValue();
	}
	@Test
	public void backToProductsValidation() {
		inventoryPage.clickOnAnyImage();
		inventoryPage.clickOnProductsAddToCart();
		inventoryPage.clickonBackToProduct();
		Assert.assertEquals(driver.getCurrentUrl(),inventorysheet.getRow(4).getCell(1).toString());
	}
}