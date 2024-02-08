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

public class TS_Footer {
	WebDriver driver; 
	InventoryPage inventoryPage; LoginPage loginPage; CheckoutPageOne checkoutPageOne;
	CartPage cartPage;CheckoutPageTwo checkoutPageTwo;OrderCompletePage orderCompletePage; 
	FooterPage footer;
	XSSFSheet pageonesheet = ExcelFileData.readExcelData("CheckoutPageOneData");
	XSSFSheet footersheet = ExcelFileData.readExcelData("FooterPageData");
	@BeforeTest
	public void setUp() {
		driver= new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		inventoryPage = new InventoryPage(driver); loginPage = new LoginPage(driver);
		loginPage.login("standard_user", "secret_sauce");checkoutPageOne = new CheckoutPageOne(driver);
		cartPage = new CartPage(driver);checkoutPageTwo = new CheckoutPageTwo(driver);
		orderCompletePage = new OrderCompletePage(driver); footer = new FooterPage(driver);
		inventoryPage.clickOnAnyAddToCart();
		inventoryPage.clickCart(); cartPage.clickOnCheckout();
		checkoutPageOne.enterFieldValues(pageonesheet.getRow(7).getCell(1).toString(), pageonesheet.getRow(8).getCell(1).toString(), pageonesheet.getRow(9).getCell(1).toString()); checkoutPageOne.clickOnContinueBtn();
		checkoutPageTwo.clickOnFinishBtn(); orderCompletePage.clickOnBackHomeBtn();
	}
	
	@Test
	public void footerSocialNetworksValidation() {
		List<WebElement> SocialNetworks = footer.socialNetworks();
		for(int i =0;i<SocialNetworks.size();i++) {
			Assert.assertEquals(SocialNetworks.get(i).getText(), footersheet.getRow(i).getCell(1).toString());
		}
	}
	
	@Test
	public void footerCopyValidation() {
		Assert.assertEquals(footer.footerCopy(), footersheet.getRow(3).getCell(1).toString());
	}
}