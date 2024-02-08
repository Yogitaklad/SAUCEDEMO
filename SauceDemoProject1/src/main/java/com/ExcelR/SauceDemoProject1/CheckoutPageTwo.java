package com.ExcelR.SauceDemoProject1;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageTwo {	
	public CheckoutPageTwo(WebDriver driver){
		PageFactory.initElements(driver, this);	
	}	
	
	//Repository	
	@FindBy(id="cancel") WebElement Cancel;
	@FindBy(id="finish") WebElement Finish;
	@FindBy(xpath = "//div[@class='cart_list']//div[@class='inventory_item_price']") private List<WebElement> CartPrice;
	@FindBy(xpath = "//div[@class='summary_subtotal_label']") private WebElement ItemTotal;
	@FindBy(xpath = "//div[@class='summary_tax_label']") private WebElement Tax;
	@FindBy(xpath = "//div[@class='summary_info_label summary_total_label']") private WebElement TotalPriceWithTax;
	@FindBy(id = "cancel") private WebElement CancelBtn;
	@FindBy(name = "finish") private WebElement FinishBtn;
	@FindBy(xpath="//div[@class='cart_item']/div") WebElement CartQty;
	@FindBy(xpath="//div[@class='cart_item']/div[2]") WebElement Itemdetails;
	
	public WebElement cancelBtnPresence() {
		return Cancel;
	}
	public WebElement finishBtnPresence() {
		return Finish;
	}
	public void clickOnFinishBtn() {
		FinishBtn.click();
	}
	public float sumTotalCartPrice() {
		float SubTotal = 0;
		for(int i = 0; i< CartPrice.size();i++) {
			Pattern p = Pattern.compile("[^0-9]([0-9]+(\\.[0-9]*)?)");
		    Matcher m = p.matcher(CartPrice.get(i).getText());
		    m.matches();
		    String Price = m.group(1);
		    SubTotal = SubTotal + Float.valueOf(Price);
		}
		return SubTotal;
	}	
	public String itemTotalPrice() {
		return 	ItemTotal.getText();
	}
	public String tax() {
		return Tax.getText();
	}
	public String totalPrice() {
		return TotalPriceWithTax.getText();
	}
}