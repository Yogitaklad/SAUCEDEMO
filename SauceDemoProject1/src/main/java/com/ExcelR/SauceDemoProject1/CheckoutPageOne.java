package com.ExcelR.SauceDemoProject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageOne {

	public CheckoutPageOne(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	//Repository	
	@FindBy(id="first-name") WebElement FirstName;
	@FindBy(id="last-name") WebElement LastName;
	@FindBy(id="postal-code") WebElement ZipPostalCode;
	@FindBy(id="cancel") WebElement Cancel;
	@FindBy(id="continue") WebElement Continue;
	@FindBy(xpath = "//h3[@data-test='error']") WebElement CheckoutPageOneErrorMsg;
	
	//Methods
	public WebElement firstNamePresence() {
		return FirstName;
	}
	public WebElement lastNamePresence() {
		return LastName;
	}
	public WebElement zipPostalCodePresence() {
		return ZipPostalCode;
	}
	public WebElement cancelBtnPresence() {
		return Cancel;
	}
	public WebElement continueBtnPresence() {
		return Continue;
	}
	public void clickOnCancelBtn() {
		Cancel.click();
	}
	public void clickOnContinueBtn() {
		Continue.click();
	}
	public String getErrorMsg() {
		return CheckoutPageOneErrorMsg.getText();
	}
	public void emptyfields() {
		FirstName.sendKeys("");
		LastName.sendKeys("");
		ZipPostalCode.sendKeys("");
	}
	public void enterFieldValues(String Fn, String Ln, String Zipcode) {
		FirstName.sendKeys(Fn);
		LastName.sendKeys(Ln);
		ZipPostalCode.sendKeys(Zipcode);
	}
	public void enterLastNameNZipcodeFieldValues(String Ln, String Zipcode) {
		FirstName.sendKeys("");
		LastName.sendKeys(Ln);
		ZipPostalCode.sendKeys(Zipcode);
	}
	public void enterFirstNameNZipcodeValues(String Fn, String Zipcode) {
		FirstName.sendKeys(Fn);
		LastName.sendKeys("");
		ZipPostalCode.sendKeys(Zipcode);
	}
	public void enterFirstNLastNameValues(String Fn, String Ln) {
		FirstName.sendKeys(Fn);
		LastName.sendKeys(Ln);
		ZipPostalCode.sendKeys("");
	}
}
