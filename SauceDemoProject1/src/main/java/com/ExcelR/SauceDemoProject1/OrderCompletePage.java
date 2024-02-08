package com.ExcelR.SauceDemoProject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletePage {
	
	public OrderCompletePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Repository
	@FindBy(xpath = "//span[text()='Checkout: Complete!']") private WebElement TitleText;
	@FindBy(xpath = "//h2[text()='Thank you for your order!']") private WebElement ThankYouText;
	@FindBy(xpath="//div[@class='complete-text']") WebElement OrderCompletionText ;
	@FindBy(id="back-to-products") WebElement BackHomeBt;
	
	public String titleText() {
		return TitleText.getText();
	}
	public String thankyouText() {
		return ThankYouText.getText();
	}
	public String orderCompletionText() {
		return OrderCompletionText.getText();
	}
	public WebElement backHomeBtn() {
		return BackHomeBt;
	}
	public void clickOnBackHomeBtn() {
		BackHomeBt.click();
	}
}
