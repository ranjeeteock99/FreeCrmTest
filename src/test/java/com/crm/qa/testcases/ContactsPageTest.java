package com.crm.qa.testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	   
	public ContactsPageTest(){
			super();
			
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		TestUtil.runTimeInfo("error", "login successful");
		testUtil.switchToFrame();
		//contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1,enabled=true)
	public void verifyContactsPageLabel(){
		contactsPage = homePage.clickOnContactsLink();
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}
	
	@Test(priority=2,enabled=true)
	public void selectSingleContactsTest() throws IOException{
		contactsPage = homePage.clickOnContactsLink();
		contactsPage.selectContactsByName("gS Rawat");
		TestUtil.takeScreenshotAtEndOfTest();
		System.out.println("Screeen shot taken successfully");
	}
	
	@Test(priority=3 , enabled=false)
	public void selectMultipleContactsTest() throws IOException{
		contactsPage = homePage.clickOnContactsLink();
		contactsPage.selectContactsByName("gS Rawat");
		contactsPage.selectContactsByName("Mukta Sharma");
		TestUtil.takeScreenshotAtEndOfTest();
		System.out.println("Screeen shot taken successfully");

	}
	
	@DataProvider
	public Object[][] getCRMTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData", enabled=false)
	public void validateCreateNewContact(String title, String firstName, String lastName, String company){
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title, firstName, lastName, company);
		
	}
	//for hard coded data
	@Test(priority=5, enabled=false)
	public void validateCreateNewContact2() {
		homePage.clickOnNewContactLink();
	
		contactsPage.createNewContact("Mr.", "madan", "sharma", "mdr");
		
		
	}
	
	

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
}
