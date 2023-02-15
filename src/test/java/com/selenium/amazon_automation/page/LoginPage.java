package com.selenium.amazon_automation.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.amazon_automation.base.TestBase;

public class LoginPage extends TestBase {
	public void validLogin(String username, String password) {
		submitUsername(username);
		submitPassword(password);
	}
	
	public boolean invalidLogin(String username, String password) {
		submitUsername(username);
		if(!isValidUsername()) {
			return true;
		}
		
		submitPassword(password);
		if(!isValidPassword()) {
			return true;
		}
		
		return false;
	}

	public void submitUsername(String username) {
		WebElement usernameInput = driver.findElement(By.id("ap_email"));
		clearAndSendKeys(usernameInput, username);
		
		WebElement continueButton = driver.findElement(By.id("continue"));
		continueButton.click();
	}
	
	public void submitPassword(String password) {
		WebElement passwordInput = driver.findElement(By.id("ap_password"));
		clearAndSendKeys(passwordInput, password);
		
		WebElement signInButton = driver.findElement(By.id("signInSubmit"));
		signInButton.click();
	}
	
	public void clearAndSendKeys(WebElement webElement, String value) {
		webElement.clear();
		webElement.sendKeys(value);
	}
	
	public boolean isValidUsername() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@Id='auth-error-message-box' or @Id='auth-email-missing-alert']"))));
			System.out.println("username failed");
			return false;
		} catch (Exception e) {
//			e.printStackTrace();
			return true;
		}
	}
	
	public boolean isValidPassword() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@Id='auth-error-message-box' or @Id='auth-password-missing-alert']")));
			System.out.println("password failed");
			return false;
		} catch (Exception e) {
//			e.printStackTrace();
		}		
		return true;
	}
}
