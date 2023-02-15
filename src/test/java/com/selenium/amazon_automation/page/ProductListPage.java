package com.selenium.amazon_automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.amazon_automation.base.TestBase;

public class ProductListPage extends TestBase{
	public ProductPage selectProduct() {
		WebElement product = driver.findElement(By.xpath("//div[@data-component-type='s-search-result'][1]"));
		WebElement productLink = product.findElement(By.xpath("//h2//a"));
		
		productLink.click();
		
		String nextTabHandle = driver.getWindowHandles().stream().reduce((current, next) -> next).orElse(null);
		driver.switchTo().window(nextTabHandle);
		
		if(driver.findElement(By.id("productTitle")).isDisplayed()) {
			return new ProductPage();
		}
		return null;
	}
}
