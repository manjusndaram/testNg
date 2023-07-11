package com.ms.testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	  WebDriver driver;
		By userNameField = By.xpath("//input[@id='username']");
		By passwordField = By.xpath("//input[@id='password']");
		By signinField = By.xpath("//button[text()='Sign in']");
		By customersField = By.xpath("//span[text()='Customers']");
		By addcustomerField = By.xpath("//*[@id='side-menu']/li[3]/ul/li[1]/a");
		By addCustomerContactsField = By.xpath("//*[@id='page-wrapper']/div[2]/div/h2");
		By addCustomerAddContactsField = By.xpath("//*[@id='page-wrapper']/div[3]/div[1]/div/div/div/div[1]/h5");
		By fullNameFeild = By.xpath("//input[@id='account']");
		By companyDropDownField = By.xpath("//*[@id=\"cid\"]");
		By emailField = By.xpath("//*[@id=\'email\']");
		By phoneField = By.xpath("//*[@id=\"phone\"]");
		By addressField = By.xpath("//*[@id='address']");
		By cityField = By.xpath("//*[@id=\'city\']");
		By stateRegionField = By.xpath("//*[@id=\"state\"]");
		By zipPostalField = By.xpath("//*[@id=\"zip\"]");
		By countryField = By.xpath("//*[@id=\"country\"]");
		By saveButtonField = By.xpath("//*[@id=\"submit\"]");
		By listCustomers = By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[2]/a");
		
	  @BeforeMethod
	  public void beforeMethod() {
		  System.out.println("Starting Browser Session");
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = (WebDriver) new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("https://www.techfios.com/billing/?ng=admin/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }
		@Test
		public void login() throws InterruptedException {
			/*
			 * Enter username:  demo@techfios.com Enter password: abc123 Click login button
			 */
			driver.findElement(userNameField).sendKeys("Demo@techfios.com");
			driver.findElement(passwordField).sendKeys("abc123");
			driver.findElement(signinField).click();
			// Click on Customers button in the left Side Navigation
			driver.findElement(customersField).click();
			// Click on Add Customer
			driver.findElement(addcustomerField).click();

			// Add Customer Page Title "Contacts - iBilling" validations."
			String actualPageTitle = driver.getTitle();
			Assert.assertEquals( "Contacts - iBilling", actualPageTitle, "Add customer page not found");
			// Add Customer Page header "Contacts" validation.
			Assert.assertEquals( "Contacts",
					driver.findElement(addCustomerContactsField).getText(), "Add customer page contacts field not found");
			Thread.sleep(5000);
			// Add Customer Page header "Add Contact" validations.
			Assert.assertEquals( "Add Contact",
					driver.findElement(addCustomerAddContactsField).getText(), "Add customer page add contacts field not found");

			// Fill in the Add Customer Form
			driver.findElement(fullNameFeild).sendKeys("MS");
			// For selecting a company element from the dropdown.
			Select selectCompany = new Select(driver.findElement(companyDropDownField));
			selectCompany.selectByVisibleText("Bank Of America");
			driver.findElement(emailField).sendKeys("ms@abc.com");
			driver.findElement(phoneField).sendKeys("777-7777-777");
			driver.findElement(addressField).sendKeys("Wesford St");
			driver.findElement(cityField).sendKeys("Lowell");
			driver.findElement(stateRegionField).sendKeys("MA");
			driver.findElement(zipPostalField).sendKeys("85201");
			// For selecting a country element from the dropdown.
			Select selectCountry = new Select(driver.findElement(countryField));
			selectCountry.selectByValue("United Kingdom");
			// Click submit
			driver.findElement(saveButtonField).click();
			Thread.sleep(15000);
			// Go to CRM -> List Customer Page
			driver.findElement(listCustomers).click();

			// Search for the new Customer in the search field
			// Validate contact Customer
		}

	  @AfterMethod
	  public void afterMethod() {
		  System.out.println("Closing Browser Session");
		  driver.quit();
		  driver.close();
	  }

}
