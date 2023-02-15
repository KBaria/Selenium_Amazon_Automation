package com.selenium.amazon_automation.test;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.amazon_automation.base.TestBase;
import com.selenium.amazon_automation.page.HomePage;
import com.selenium.amazon_automation.page.LoginPage;
import com.selenium.amazon_automation.util.ExcelReader;

@Listeners(com.selenium.amazon_automation.util.LoginTestListener.class)
public class LoginTest extends TestBase {
	private HomePage homePage;
	private LoginPage loginPage;
	
	public LoginTest() {
		super();
		homePage = new HomePage();
	}
	
	@BeforeMethod
	public void setUp() {
		super.setUp();
		loginPage = homePage.getLoginPage();
	}
	
	@Test
	public void validLoginTest() {
		String username = validTestDataProperties.getProperty("username");
		String password = validTestDataProperties.getProperty("password");
		loginPage.validLogin(username, password);
		AssertJUnit.assertEquals(homePage.isUserSignedIn(), true);
	}
	
	@Test(dataProvider = "invalidLoginData")
	public void invalidLoginTest(String username, String password) {
		AssertJUnit.assertEquals(loginPage.invalidLogin(username, password), true);
	}
	
	@DataProvider(name = "invalidLoginData")
	public String[][] invalidLoginData() {
		ExcelReader reader = new ExcelReader("./test-data/loginData.xlsx");
		String[][] data = reader.getData("invalid");
		reader.close();
		return data;
	}
	
	
	@AfterMethod
	public void tearDown() {
		super.tearDown();
	}
}
