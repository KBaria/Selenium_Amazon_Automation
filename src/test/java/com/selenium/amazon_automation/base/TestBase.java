package com.selenium.amazon_automation.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	protected static WebDriver driver;
	protected static Properties driverConfigProperties;
	protected static Properties validTestDataProperties;
	
	public TestBase() {
		super();
		
		driverConfigProperties = new Properties();
		validTestDataProperties = new Properties();
		
		try(FileInputStream fis = new FileInputStream("./src/test/java/com/selenium/amazon_automation/config/driver_config.properties")) {
			driverConfigProperties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(FileInputStream fis = new FileInputStream("./src/test/java/com/selenium/amazon_automation/config/valid_test_data.properties")) {
			validTestDataProperties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void setUp() {
		String browser = driverConfigProperties.getProperty("browser");
		String url = driverConfigProperties.getProperty("url");
		int pageLoadTimeout = Integer.parseInt(driverConfigProperties.getProperty("pageLoadTimeout"));
		int implicitWaitTimeout = Integer.parseInt(driverConfigProperties.getProperty("implicitWaitTimeout"));
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTimeout));
		
		driver.manage().window().maximize();
		
		driver.get(url);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
