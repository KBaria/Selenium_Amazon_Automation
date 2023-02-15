package com.selenium.amazon_automation.page;

import java.time.Duration;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.amazon_automation.base.TestBase;

public class ProductPage extends TestBase {
	public CheckoutPage buyProduct() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		if(driver.findElement(By.id("buy-now-button")).isDisplayed()) {
			WebElement buyNowButton = driver.findElement(By.id("buy-now-button"));
			buyNowButton.click();
		}else {
			WebElement addToCartButton = driver.findElement(By.xpath("//*[@Id='add-to-cart-button' or @Id='dealsx_atc_btn-announce']"));
			wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
			addToCartButton.click();
			
			wait.until(ExpectedConditions.textMatches(By.id("nav-cart-count"), Pattern.compile("[^0]")));
			
			WebElement cartButton = driver.findElement(By.id("nav-cart"));
			cartButton.click();
			
			WebElement checkoutButton = driver.findElement(By.name("proceedToRetailCheckout"));
			checkoutButton.click();
		}
		
		if(driver.findElement(By.xpath("//h1[contains(text(),'Checkout')]")).isDisplayed()) {
			return new CheckoutPage();
		}
		return null;
	}
}
