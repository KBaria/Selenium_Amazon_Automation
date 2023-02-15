package com.selenium.amazon_automation.test;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.amazon_automation.base.TestBase;
import com.selenium.amazon_automation.page.CheckoutPage;
import com.selenium.amazon_automation.page.HomePage;
import com.selenium.amazon_automation.page.LoginPage;
import com.selenium.amazon_automation.page.ProductListPage;
import com.selenium.amazon_automation.page.ProductPage;
import com.selenium.amazon_automation.util.ExcelReader;

@Listeners(com.selenium.amazon_automation.util.ProductTestListener.class)
public class ProductTest extends TestBase {
	private HomePage homePage;
	private LoginPage loginPage;
	private ProductListPage productListPage;
	private ProductPage productPage;
	private CheckoutPage checkoutPage;
	
	public ProductTest() {
		super();
		homePage = new HomePage();
	}
	
	@BeforeMethod
	public void setUp() {
		super.setUp();
		loginPage = homePage.getLoginPage();
		loginPage.validLogin(validTestDataProperties.getProperty("username"), validTestDataProperties.getProperty("password"));
	}
	
	@Test(dataProvider = "validAddressData")
	public void validBuyProductTest(String fullname, String phone, String pincode, String addressLine, String city, String state) {
		String product = validTestDataProperties.getProperty("product");
		productListPage = homePage.searchProduct(product);
		productPage = productListPage.selectProduct();
		checkoutPage = productPage.buyProduct();
		
		AssertJUnit.assertEquals(checkoutPage.buyProduct(fullname, phone, pincode, addressLine, city, state), true);
	}
	
	@Test(dataProvider = "invalidAddressData")
	public void invalidBuyProductTest(String fullname, String phone, String pincode, String addressLine, String city, String state) {
		String product = validTestDataProperties.getProperty("product");
		productListPage = homePage.searchProduct(product);
		productPage = productListPage.selectProduct();
		checkoutPage = productPage.buyProduct();
		AssertJUnit.assertEquals(checkoutPage.fillInvalidAddressForm(fullname, phone, pincode, addressLine, city, state), true);
	}
	
	@DataProvider(name = "invalidAddressData")
	public String[][] invalidAdderssData() {
		ExcelReader reader = new ExcelReader("./test-data/addressData.xlsx");
		String[][] data = reader.getData("invalid");
		reader.close();
		return data;
	}
	
	@DataProvider(name = "validAddressData")
	public String[][] validAddressData() {
		String fullname = validTestDataProperties.getProperty("fullname");
		String phone = validTestDataProperties.getProperty("phone");
		String pincode = validTestDataProperties.getProperty("pincode");
		String addressLine = validTestDataProperties.getProperty("address_line");
		String city = validTestDataProperties.getProperty("city");
		String state = validTestDataProperties.getProperty("state").toUpperCase();
		
		String[][] data = {{fullname, phone, pincode, addressLine, city, state}};
		return data;
	}
	
	@AfterMethod
	public void tearDown() {
		super.tearDown();
	}
}
