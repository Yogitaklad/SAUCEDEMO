package com.ExcelR.SauceDemoProject1;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterPage {
	
	public FooterPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Repository 
	
	@FindBy(xpath="//ul[@class='social']/li") List<WebElement> socialNetworks;
	@FindBy(xpath="//div[@class='footer_copy']") WebElement FooterCopy;
	
	//Methods
	public String footerCopy() {
		return FooterCopy.getText();
	}
	public List<WebElement> socialNetworks(){
		return socialNetworks;
	}
}