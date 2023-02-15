package com.selenium.amazon_automation.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.amazon_automation.base.TestBase;

public class CheckoutPage extends TestBase {
	public boolean buyProduct(String fullname, String phone, String pincode, String addressLine, String city, String state) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		if(isAddressSaved()) {
			WebElement useSavedAddressButton = driver.findElement(By.id("shipToThisAddressButton"));
			useSavedAddressButton.click();
		}else {
//			String fullname = validTestDataProperties.getProperty("fullname");
//			String phone = validTestDataProperties.getProperty("phone");
//			String pincode = validTestDataProperties.getProperty("pincode");
//			String addressLine = validTestDataProperties.getProperty("address_line");
//			String city = validTestDataProperties.getProperty("city");
//			String state = validTestDataProperties.getProperty("state").toUpperCase();
			
			fillValidAddressForm(fullname, phone, pincode, addressLine, city, state);
		}
		
		WebElement span = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@Id='payment']//h3[contains(text(), 'Select')]")));
		if(span.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isAddressSaved() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			WebElement shipToAddressButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("shipToThisAddressButton")));
			return shipToAddressButton.isDisplayed();
		}catch (Exception e) {
			return false;
		}
		
	}
	
	public void selectPaymentOption() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		List<WebElement> paymentOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("ppw-instrumentRowSelection")));
		WebElement lastOption = paymentOptions.stream().reduce((first, second) -> second).orElse(null);
		
		lastOption.click();
		
		WebElement continueButton = driver.findElement(By.name("ppw-widgetEvent:SetPaymentPlanSelectContinueEvent"));
		continueButton.click();
	}
	
	public void placeOrder() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@Class,'fasttrackavailable')]")));
		
		WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitOrderButtonId-announce")));
		placeOrderButton.click();
		System.out.println(placeOrderButton.getText());
	}
	
	public boolean continuePurchase() {
		try {
			selectPaymentOption();
			placeOrder();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean fillValidAddressForm(String fullname, String phone, String pincode, String addressLine, String city, String state) {
		fillAddressForm(fullname, phone, pincode, addressLine, city, state);
		
		if(driver.findElement(By.xpath("//div[@data-a-input-name='ppw-instrumentRowSelection']")).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean fillInvalidAddressForm(String fullname, String phone, String pincode, String addressLine, String city, String state) {
		fillAddressForm(fullname, phone, pincode, addressLine, city, state);
		
		if(driver.findElement(By.xpath("//input[contains(@Class,'a-form-error') or contains(@Class, 'warning-state')]")).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public void fillAddressForm(String fullname, String phone, String pincode, String addressLine, String city, String state) {
		WebElement fullNameInput = driver.findElement(By.id("address-ui-widgets-enterAddressFullName"));
		WebElement mobileInput = driver.findElement(By.id("address-ui-widgets-enterAddressPhoneNumber"));
		WebElement pinCodeInput = driver.findElement(By.id("address-ui-widgets-enterAddressPostalCode"));
		WebElement addressLineInput = driver.findElement(By.id("address-ui-widgets-enterAddressLine1"));
		WebElement cityInput = driver.findElement(By.id("address-ui-widgets-enterAddressCity"));
		Select stateSelect = new Select(driver.findElement(By.id("address-ui-widgets-enterAddressStateOrRegion-dropdown-nativeId")));
		
		WebElement submitButton = driver.findElement(By.id("address-ui-widgets-form-submit-button"));
		
		clearAndSendKeys(fullNameInput, fullname);
		clearAndSendKeys(mobileInput, phone);
		clearAndSendKeys(pinCodeInput, pincode);
		clearAndSendKeys(addressLineInput, addressLine);
		clearAndSendKeys(cityInput, city);
		
		stateSelect.selectByVisibleText(state.toUpperCase());
		
		submitButton.click();
	}

	public void clearAndSendKeys(WebElement element, String keys) {
		element.clear();
		element.sendKeys(keys);
	}
}
