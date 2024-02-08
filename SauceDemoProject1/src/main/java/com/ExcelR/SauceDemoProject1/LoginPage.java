package com.ExcelR.SauceDemoProject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver ;	
	public LoginPage (WebDriver driver)
	{
		this.driver= driver; 
		PageFactory.initElements(driver, this);
	}	
	// Repository
	@FindBy(xpath= "//div[text()='Swag Labs']") private WebElement HomePageText; 
	@FindBy (id="user-name") private WebElement UserName; 
	@FindBy (id ="password") private WebElement Password;
	@FindBy (id="login-button") private WebElement LoginButton ;
	@FindBy (xpath="//div[@class='error-message-container error']/h3") private WebElement ErrorMsg;
	@FindBy (xpath="//button[@class='error-button']")private WebElement LockedoutMsg;
	

	// Method to enter user name and password and click on the login button
	
	public void login(String username, String password) 
	{
		UserName.sendKeys(username);
		Password.sendKeys(password);
		LoginButton.click();
	}
	
	// Method to check if the home page text is displayed
	public boolean isHomePageTextDisplayed()
	{
		return HomePageText.isDisplayed();
		
	}
	public String homePageText()
	{
		return HomePageText.getText();
		
	}
	
	// Method to check if the error message is displayed	
	public boolean isErrorMessageDisplayed()
	{
		return ErrorMsg.isDisplayed();
	}
	public String errorMessage()
	{
		return ErrorMsg.getText();
	}
	public boolean isLockedOutMessageDisplayed()
	{
		return LockedoutMsg.isDisplayed();
	}
	public String lockedOutErrorMessage()
	{
		return LockedoutMsg.getText();
	}
	
}
