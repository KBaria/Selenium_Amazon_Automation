package com.selenium.amazon_automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.amazon_automation.base.TestBase;

public class HomePage extends TestBase {
	public boolean isUserSignedIn() {
		WebElement accountList = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		if(accountList.getText().equals("Hello, sign in")) {
			return false;
		}
		return true;
	}
	
	public LoginPage getLoginPage() {
		WebElement loginPageLink = driver.findElement(By.id("nav-link-accountList"));
		loginPageLink.click();
		
		if(driver.findElement(By.name("signIn")).isEnabled()) {
			return new LoginPage();
		}
		return null;
	}
	
	public ProductListPage searchProduct(String product) {
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.clear();
		searchBox.sendKeys(product);
		searchBox.submit();
		
		if(driver.findElement(By.xpath("//div[@data-component-type='s-search-result']")).isDisplayed()) {
			return new ProductListPage();
		}
		return null;
	}
}
