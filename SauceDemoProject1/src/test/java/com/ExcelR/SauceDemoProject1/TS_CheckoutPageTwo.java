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

public class TS_CheckoutPageTwo {
	WebDriver driver; 
	InventoryPage inventoryPage; LoginPage loginPage; CheckoutPageOne checkoutPageOne;
	CartPage cartPage;CheckoutPageTwo checkoutPageTwo;

	XSSFSheet inventorysheet = ExcelFileData.readExcelData("InventoryPageData");
	XSSFSheet pageonesheet = ExcelFileData.readExcelData("CheckoutPageOneData");
	XSSFSheet pagetwosheet = ExcelFileData.readExcelData("CheckoutPageTwoData");
	XSSFSheet completecheckout = ExcelFileData.readExcelData("CompleteCheckoutPageData");
	
	@BeforeTest
	public void setUp() {
		driver= new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		inventoryPage = new InventoryPage(driver); loginPage = new LoginPage(driver);
		loginPage.login("standard_user", "secret_sauce");checkoutPageOne = new CheckoutPageOne(driver);
		cartPage = new CartPage(driver);checkoutPageTwo = new CheckoutPageTwo(driver);
		inventoryPage.clickOnAnyAddToCart();
		inventoryPage.clickCart(); cartPage.clickOnCheckout();
		checkoutPageOne.enterFieldValues(pageonesheet.getRow(7).getCell(1).toString(), pageonesheet.getRow(8).getCell(1).toString(), pageonesheet.getRow(9).getCell(1).toString()); 
		checkoutPageOne.clickOnContinueBtn();
	}

	@Test
	public void priceValidation() {
		Assert.assertNotEquals(checkoutPageTwo.sumTotalCartPrice(), checkoutPageTwo.itemTotalPrice());		
	}
	
	@Test
	public void cancelBtnValidation() {
		WebElement CancelBtn = checkoutPageTwo.cancelBtnPresence();
		Assert.assertTrue(CancelBtn.isDisplayed());
		Assert.assertTrue(CancelBtn.isEnabled());
		Assert.assertEquals(CancelBtn.getText(), pagetwosheet.getRow(1).getCell(1).toString());
		CancelBtn.click();
		Assert.assertEquals(driver.getCurrentUrl(), inventorysheet.getRow(4).getCell(1).toString());
		
	}
	@Test
	public void finishBtnValidation() {
		WebElement FinishBtn = checkoutPageTwo.finishBtnPresence();
		Assert.assertTrue(FinishBtn.isDisplayed());
		Assert.assertTrue(FinishBtn.isEnabled());
		Assert.assertEquals(FinishBtn.getText(), pagetwosheet.getRow(2).getCell(1).toString());
		checkoutPageTwo.clickOnFinishBtn();
		Assert.assertEquals(driver.getCurrentUrl(), completecheckout.getRow(0).getCell(1).toString());		
	}
}