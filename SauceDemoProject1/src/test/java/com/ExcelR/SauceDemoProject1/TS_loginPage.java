package com.ExcelR.SauceDemoProject1;

import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import readExcelFile.ExcelFileData;

public class TS_loginPage {
	WebDriver driver;
	LoginPage loginPage; InventoryPage inventoryPage;
	XSSFSheet loginsheet = ExcelFileData.readExcelData("LoginPageData");
	
	@BeforeTest
	public void launchBrowserAndInititialisation() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.saucedemo.com/");
		loginPage = new LoginPage(driver); 
		inventoryPage  = new InventoryPage(driver); 
	}

	@Test
	public void validateLoginPage() {
		// Validate that you are on the correct login page	
		Assert.assertTrue(loginPage.isHomePageTextDisplayed(), "Home Page text is not displayed.");
		Assert.assertEquals(loginPage.homePageText(), loginsheet.getRow(0).getCell(1).toString());
	}
	
	@DataProvider (name="LoginTestData")
	public Object[][]LoginCredentials()
	{
		Object[][] details = {{"standard_user","secret_sauce"},{"problem_user","secret_sauce"},
				{"performance_glitch_user","secret_sauce"},{"error_user","secret_sauce"},
				{"visual_user","secret_sauce"}};
		return details;
	}
	
	@Test (priority = 2, dataProvider ="LoginTestData")
	public void loginWithValidateCredentials(String Username, String Password)
	{
		loginPage.login(Username, Password);
		Assert.assertEquals(driver.getCurrentUrl(),loginsheet.getRow(1).getCell(1).toString() , "URL does not match after successful login");
		inventoryPage.openHambergerMenu();
		inventoryPage.clickOnLogoutLink();		
	}
	
	@Test(priority = 3)
	public void loginWithInvalidCredentials()
	{
		loginPage.login(loginsheet.getRow(1).getCell(1).toString(), loginsheet.getRow(2).getCell(2).toString());
		Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Invalid credentials");	
		Assert.assertEquals(loginPage.errorMessage(), loginsheet.getRow(3).getCell(1).toString());
	}
	
	@Test(priority = 4)
	public void loginWithLockedUser()
	{
		loginPage.login("locked_out_user", "secret_sauce");
		Assert.assertTrue(loginPage.isLockedOutMessageDisplayed(), "Locked out user");
		Assert.assertEquals(loginPage.lockedOutErrorMessage(), loginsheet.getRow(4).getCell(1).toString());
	}
	
}