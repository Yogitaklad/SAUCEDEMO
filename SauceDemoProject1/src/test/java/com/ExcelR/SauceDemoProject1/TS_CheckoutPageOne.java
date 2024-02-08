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

public class TS_CheckoutPageOne {
	WebDriver driver; 
	InventoryPage inventoryPage; LoginPage loginPage; CheckoutPageOne checkoutPageOne;
	CartPage cartPage;
	XSSFSheet cartsheet = ExcelFileData.readExcelData("CartPageData");
	XSSFSheet pageonesheet = ExcelFileData.readExcelData("CheckoutPageOneData");
	XSSFSheet pagetwosheet = ExcelFileData.readExcelData("CheckoutPageTwoData");

	@BeforeTest
	public void setUp() {
		driver= new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		inventoryPage = new InventoryPage(driver); loginPage = new LoginPage(driver);
		loginPage.login("standard_user", "secret_sauce");checkoutPageOne = new CheckoutPageOne(driver);
		cartPage = new CartPage(driver);
		inventoryPage.clickOnAnyAddToCart();
		inventoryPage.clickCart();
		cartPage.clickOnCheckout();
	}

	@Test
	public void checkoutPageOneFieldsPresence() {
		WebElement FirstName =  checkoutPageOne.firstNamePresence();
		Assert.assertTrue(FirstName.isDisplayed());
		Assert.assertEquals(FirstName.getAttribute("placeholder"),pageonesheet.getRow(1).getCell(1).toString());
		WebElement LastName =  checkoutPageOne.lastNamePresence();
		Assert.assertTrue(LastName.isDisplayed());
		Assert.assertEquals(FirstName.getAttribute("placeholder"),pageonesheet.getRow(2).getCell(1).toString());
		WebElement ZipPostalCode =  checkoutPageOne.zipPostalCodePresence();
		Assert.assertTrue(ZipPostalCode.isDisplayed());
		Assert.assertEquals(FirstName.getAttribute("placeholder"),pageonesheet.getRow(3).getCell(1).toString());
	}
	@Test
	public void checkoutPageOneErrorMsgValidation() {
		checkoutPageOne.clickOnContinueBtn();
		checkoutPageOne.emptyfields();
		Assert.assertEquals(checkoutPageOne.getErrorMsg(),pageonesheet.getRow(4).getCell(1).toString());
		checkoutPageOne.enterLastNameNZipcodeFieldValues(pageonesheet.getRow(8).getCell(1).toString(), pageonesheet.getRow(9).getCell(1).toString());
		checkoutPageOne.clickOnContinueBtn();
		Assert.assertEquals(checkoutPageOne.getErrorMsg(), pageonesheet.getRow(4).getCell(1).toString());
		driver.navigate().refresh();
		checkoutPageOne.enterFirstNameNZipcodeValues(pageonesheet.getRow(7).getCell(1).toString(), pageonesheet.getRow(9).getCell(1).toString());
		checkoutPageOne.clickOnContinueBtn();
		Assert.assertEquals(checkoutPageOne.getErrorMsg(), pageonesheet.getRow(5).getCell(1).toString());
		driver.navigate().refresh();
		checkoutPageOne.enterFirstNLastNameValues(pageonesheet.getRow(7).getCell(1).toString(), pageonesheet.getRow(8).getCell(1).toString());
		checkoutPageOne.clickOnContinueBtn();
		Assert.assertEquals(checkoutPageOne.getErrorMsg(), pageonesheet.getRow(6).getCell(1).toString());
	}
	@Test
	public void checkoutPageOneCancelBtnValidation() {
		checkoutPageOne.enterFieldValues(pageonesheet.getRow(7).getCell(1).toString(), pageonesheet.getRow(8).getCell(1).toString(), pageonesheet.getRow(9).getCell(1).toString());
		WebElement CancelBtn = checkoutPageOne.cancelBtnPresence();
		Assert.assertTrue(CancelBtn.isDisplayed());
		Assert.assertTrue(CancelBtn.isEnabled());
		Assert.assertEquals(CancelBtn.getText(), pageonesheet.getRow(10).getCell(1).toString() );
		checkoutPageOne.clickOnCancelBtn();
		Assert.assertEquals(driver.getCurrentUrl(), cartsheet.getRow(0).getCell(1).toString());
	}
	@Test
	public void checkoutPageOneContinueBtnValidation() {
		checkoutPageOne.enterFieldValues(pageonesheet.getRow(7).getCell(1).toString(), pageonesheet.getRow(8).getCell(1).toString(), pageonesheet.getRow(9).getCell(1).toString());
		WebElement CheckoutBtn = checkoutPageOne.continueBtnPresence();
		Assert.assertTrue(CheckoutBtn.isDisplayed());
		Assert.assertTrue(CheckoutBtn.isEnabled());
		Assert.assertEquals(CheckoutBtn.getAttribute("value"),pageonesheet.getRow(11).getCell(1).toString() );
		checkoutPageOne.clickOnContinueBtn();
		Assert.assertEquals(driver.getCurrentUrl(), pagetwosheet.getRow(0).getCell(1).toString());		
	}
}