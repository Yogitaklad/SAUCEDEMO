package com.ExcelR.SauceDemoProject1;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	public CartPage (WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	//Repository
	@FindBy(xpath = "//div[@class='cart_list']//div[@class='item_pricebar']/button") private List<WebElement> RemoveCartItem;
	@FindBy(id="continue-shopping")WebElement ContinueShopping; 
	@FindBy(id="checkout") WebElement checkout;
	@FindBy(xpath="//button[text()='Remove']") WebElement remove;
	
	//Methods
	public void clickOnCartRemove() {
		Random rand = new Random();
		RemoveCartItem.get(rand.nextInt(RemoveCartItem.size())).click();
	}
	public WebElement continueShoppingPresence() {
		return ContinueShopping;
	}
	public void clickOnContinueShopping()
	{
		ContinueShopping.click();
	}
	public WebElement checkoutPresence() {
		return checkout;
	}
	public void clickOnCheckout()
	{
		checkout.click();
	}
	
	public boolean isContnueShoppingbtndisplayed()
	{
		return  ContinueShopping.isDisplayed();
	}
}