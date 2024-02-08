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

public class TS_orderCompletePage {
	WebDriver driver; 
	InventoryPage inventoryPage; LoginPage loginPage; CheckoutPageOne checkoutPageOne;
	CartPage cartPage;CheckoutPageTwo checkoutPageTwo;OrderCompletePage orderCompletePage; 
	
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
		orderCompletePage = new OrderCompletePage(driver); 
		inventoryPage.clickOnAnyAddToCart();
		inventoryPage.clickCart(); cartPage.clickOnCheckout();
		checkoutPageOne.enterFieldValues(pageonesheet.getRow(7).getCell(1).toString(), pageonesheet.getRow(8).getCell(1).toString(), pageonesheet.getRow(9).getCell(1).toString()); 
		checkoutPageOne.clickOnContinueBtn();
		checkoutPageTwo.clickOnFinishBtn();
	}
	
	@Test
	public void checkoutCompletionValidation() {		
		Assert.assertEquals(orderCompletePage.titleText(), completecheckout.getRow(1).getCell(1).toString());
		Assert.assertEquals(orderCompletePage.thankyouText(), completecheckout.getRow(2).getCell(1).toString());
		Assert.assertEquals(orderCompletePage.orderCompletionText(), completecheckout.getRow(3).getCell(1).toString());
	}
	
	@Test
	public void backHomebtnValidation() {
		WebElement backhome=  orderCompletePage.backHomeBtn();
		Assert.assertTrue(backhome.isDisplayed());
		Assert.assertTrue(backhome.isEnabled());
		Assert.assertEquals(backhome.getText(), completecheckout.getRow(4).getCell(1).toString());
		orderCompletePage.clickOnBackHomeBtn();
		Assert.assertEquals(driver.getCurrentUrl(), inventorysheet.getRow(4).getCell(1).toString());
	}

}