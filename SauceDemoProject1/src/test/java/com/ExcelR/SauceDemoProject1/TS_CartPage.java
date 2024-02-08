package com.ExcelR.SauceDemoProject1;

import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import readExcelFile.ExcelFileData;

public class TS_CartPage {
	WebDriver driver; LoginPage loginPage;InventoryPage inventoryPage;
	CartPage cartPage;
	XSSFSheet cartsheet = ExcelFileData.readExcelData("CartPageData");
	XSSFSheet inventorysheet = ExcelFileData.readExcelData("InventoryPageData");
	XSSFSheet pageonesheet = ExcelFileData.readExcelData("ChecoutPageOneData");
	
	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.saucedemo.com/");
		loginPage = new LoginPage(driver);
		loginPage.login("standard_user", "secret_sauce"); inventoryPage = new InventoryPage(driver);
		cartPage = new CartPage(driver);
	}
	
	@Test
	public void cartPageValidation() {
		inventoryPage.clickOnAnyAddToCart();
		inventoryPage.clickCart();
		Assert.assertEquals(driver.getCurrentUrl(), cartsheet.getRow(0).getCell(1).toString());
	}
	@Test
	public void removeCartItemValidation() {
		inventoryPage.clickOnAnyAddToCart();
		inventoryPage.clickCart();
		String BeforeRemoving = inventoryPage.getCartValue();
		cartPage.clickOnCartRemove();
		String AfterRemoving = inventoryPage.getCartValue();
		Assert.assertNotEquals(AfterRemoving, BeforeRemoving);
	}
	@Test
	public void cartContinueShoppingBtnValidation() {
		inventoryPage.clickOnAnyAddToCart();
		inventoryPage.clickCart();
		WebElement ContinueShopping = cartPage.continueShoppingPresence();
		Assert.assertTrue(ContinueShopping.isDisplayed());
		Assert.assertTrue(ContinueShopping.isEnabled());
		Assert.assertEquals(ContinueShopping.getText(),cartsheet.getRow(1).getCell(1).toString());
		cartPage.clickOnContinueShopping();
		Assert.assertEquals(driver.getCurrentUrl(),inventorysheet.getRow(4).getCell(1).toString());
	}
	@Test
	public void cartCheckoutBtnValidation() {
		inventoryPage.clickOnAnyAddToCart();
		inventoryPage.clickCart();
		WebElement Checkout = cartPage.checkoutPresence();
		Assert.assertTrue(Checkout.isDisplayed());
		Assert.assertTrue(Checkout.isEnabled());
		Assert.assertEquals(Checkout.getText(),cartsheet.getRow(2).getCell(1).toString());
		cartPage.clickOnCheckout();
		Assert.assertEquals(driver.getCurrentUrl(),pageonesheet.getRow(0).getCell(1).toString());
	}
	
}
