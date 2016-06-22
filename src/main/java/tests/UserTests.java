package tests;

import java.io.File;
import java.util.logging.Level;

import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import pages.AddUserPage;
import pages.LoginPage;
import pages.MainPage;
import pages.UsersPage;
import utils.DataGenerator;
import utils.DataProviderGenerator;
import utils.Screenshot;

import static org.junit.Assert.*;

public class UserTests extends AbstractTest{
		
	@Test
	public void test_username_auto_fill_for_agent(){
		AddUserPage addUserPage = new LoginPage(driver).logInAsAdmin().openAddUserPage();
		String firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String phoneNumber = String.valueOf(DataGenerator.getRandomNumber(9));
		String email = DataGenerator.getRandomEmail("example.com");	
		String role = AddUserPage.ROLE_AGENT;
		addUserPage.fillForm(firstName, lastName, role, phoneNumber, email);
	
		if(!addUserPage.getUsername().equals(phoneNumber)){
			fail("Test fail: username should be same as phone number for agent: phone/username: " + phoneNumber + "/" + "username");
		}
	}
	
	@Test
	public void test_username_auto_fill_for_operator(){
		AddUserPage addUserPage = new LoginPage(driver).logInAsAdmin().openAddUserPage();
		String firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String phoneNumber = String.valueOf(DataGenerator.getRandomNumber(9));
		String email = DataGenerator.getRandomEmail("example.com");	
		String role = AddUserPage.ROLE_AGENT;
		addUserPage.fillForm(firstName, lastName, role, phoneNumber, email);
	
		if(!addUserPage.getUsername().equals(phoneNumber)){
			fail("Test fail: username should be same as email number for operator: email/username: " + email + "/" + "username");
		}
	}	

	@Test
	public void test_username_auto_fill_for_admin(){
		AddUserPage addUserPage = new LoginPage(driver).logInAsAdmin().openAddUserPage();
		String firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String phoneNumber = String.valueOf(DataGenerator.getRandomNumber(9));
		String email = DataGenerator.getRandomEmail("example.com");	
		String role = AddUserPage.ROLE_AGENT;
		addUserPage.fillForm(firstName, lastName, role, phoneNumber, email);
	
		if(!addUserPage.getUsername().equals(phoneNumber)){
			fail("Test fail: username should be same as email number for admin: email/username: " + email + "/" + "username");
		}
	}
	
	@Test
	public void test_add_agent(){		
		AddUserPage addUserPage = new LoginPage(driver).logInAsAdmin().openAddUserPage();
		String firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String phoneNumber = String.valueOf(DataGenerator.getRandomNumber(9));
		String email = DataGenerator.getRandomEmail("example.com");	
		String role = AddUserPage.ROLE_AGENT;
		LOGGER.log(Level.INFO, "Adding user: " + firstName + " | " + lastName + " | " + phoneNumber + " | " + email);
		addUserPage.addUser(firstName, lastName, role, phoneNumber, email);
		UsersPage usersPage = new UsersPage(driver);
		if(!usersPage.isSuccessAlertPresent()){
			fail("Success alert not present after saved new user. Check screenshots");
			Screenshot.getInstance().takeScreenshot(driver, "test_add_agent");
		}
		String user = usersPage.findUserBy(firstName, lastName, phoneNumber, role);
		if(user == null || user == ""){
			fail("Added user not found!");
		}				
	}
	
	@Test
	public void test_add_operator(){		
		AddUserPage addUserPage = new LoginPage(driver).logInAsAdmin().openAddUserPage();
		String firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String phoneNumber = String.valueOf(DataGenerator.getRandomNumber(9));
		String email = DataGenerator.getRandomEmail("example.com");	
		String role = AddUserPage.ROLE_OPERATOR;
		LOGGER.log(Level.INFO, "Adding user: " + firstName + " | " + lastName + " | " + phoneNumber + " | " + email);
		addUserPage.addUser(firstName, lastName, role, phoneNumber, email);
		UsersPage usersPage = new UsersPage(driver);
		if(!usersPage.isSuccessAlertPresent()){
			fail("Success alert not present after saved new user. Check screenshots");
			Screenshot.getInstance().takeScreenshot(driver, "test_add_agent");
		}
		String user = usersPage.findUserBy(firstName, lastName, email, role);
		if(user == null || user == ""){
			fail("Added user not found!");
		}				
	}	

	@Test
	public void test_add_admin(){		
		AddUserPage addUserPage = new LoginPage(driver).logInAsAdmin().openAddUserPage();
		String firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String phoneNumber = String.valueOf(DataGenerator.getRandomNumber(9));
		String email = DataGenerator.getRandomEmail("example.com");	
		String role = AddUserPage.ROLE_ADMIN;
		LOGGER.log(Level.INFO, "Adding user: " + firstName + " | " + lastName + " | " + phoneNumber + " | " + email);
		addUserPage.addUser(firstName, lastName, role, phoneNumber, email);
		UsersPage usersPage = new UsersPage(driver);
		if(!usersPage.isSuccessAlertPresent()){
			fail("Success alert not present after saved new user. Check screenshots");
			Screenshot.getInstance().takeScreenshot(driver, "test_add_agent");
		}
		String user = usersPage.findUserBy(firstName, lastName, email, role);
		if(user == null || user == ""){
			fail("Added user not found!");
		}				
	}	

	@BeforeMethod
	public  void setUpTestMethod(){
		initWebDriver();
		driver.get(baseUrl);;
	}
			
	@AfterMethod
	public void tearDownTestMethod(){
		driver.close();
	}
	
	@DataProvider
	public static Object[][] admin_user(){
		return DataProviderGenerator.getData(new File("test-input/admin_user.csv"));
	}
	
}
