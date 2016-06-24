package tests;

import java.io.File;
import java.util.logging.Level;
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
	public void test_user_list_filter_by_quantity(){
		UsersPage usersPage = new LoginPage(driver).logInAsAdmin().openUsersPage();
		int usersCount = usersPage.getAllUsersAsTextList().size();
		new MainPage(driver).openUsersPage();
		
		usersPage.setListLenghtFilter("10");
		int usersOnPage = usersPage.getVisibleUsers().size();
		if(usersCount >= 10 && usersOnPage != 10){
			fail("Selected list size: 10 , Users on list: " + usersOnPage);
		}
		usersPage.setListLenghtFilter("25");
		usersOnPage = usersPage.getVisibleUsers().size();
		if(usersCount >= 25 && usersOnPage != 25){
			fail("Selected list size: 25 , Users on list: " + usersOnPage);
		}
		usersPage.setListLenghtFilter("50");
		usersOnPage = usersPage.getVisibleUsers().size();
		if(usersCount >= 50 && usersOnPage != 50){
			fail("Selected list size: 50 , Users on list: " + usersOnPage);
		}
		usersPage.setListLenghtFilter("100");
		usersOnPage = usersPage.getVisibleUsers().size();
		if(usersCount >= 100 && usersOnPage != 100){
			fail("Selected list size: 100 , Users on list: " + usersOnPage);
		}
	}
	
	@Test
	public void test_user_filter_by_role_admin(){
		UsersPage usersPage = new LoginPage(driver).logInAsAdmin().openUsersPage();
		usersPage.setUserFilter(UsersPage.FILTER_ADMIN);
		assertTrue(usersPage.checkIfAllUsersHaveSameRole(UsersPage.FILTER_ADMIN));
	}

	@Test
	public void test_user_filter_by_role_operator(){
		UsersPage usersPage = new LoginPage(driver).logInAsAdmin().openUsersPage();
		usersPage.setUserFilter(UsersPage.FILTER_OPERATOR);
		assertTrue(usersPage.checkIfAllUsersHaveSameRole(UsersPage.FILTER_OPERATOR));
	}	
	
	@Test
	public void test_user_filter_by_role_agent(){
		UsersPage usersPage = new LoginPage(driver).logInAsAdmin().openUsersPage();
		usersPage.setUserFilter(UsersPage.FILTER_AGENT);
		assertTrue(usersPage.checkIfAllUsersHaveSameRole(UsersPage.FILTER_AGENT));
	}
	
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
	public void test_add_user_with_phone_assigned_to_deleted_user() {
		// Add valid user
		AddUserPage addUserPage = new LoginPage(driver).logInAsAdmin().openAddUserPage();
		String firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		String phoneNumber = String.valueOf(DataGenerator.getRandomNumber(9));
		String email = DataGenerator.getRandomEmail("example.com");
		String role = AddUserPage.ROLE_AGENT;
		LOGGER.log(Level.INFO, "Adding user: " + firstName + " | " + lastName + " | " + phoneNumber + " | " + email);
		addUserPage.addUser(firstName, lastName, role, phoneNumber, email);
		UsersPage usersPage = new UsersPage(driver);
		// Checks if user is saved
		if (!usersPage.isSuccessAlertPresent()) {
			fail("Success alert not present after saved new user. Check screenshots");
			Screenshot.getInstance().takeScreenshot(driver, "test_add_user_with_phone_assigned_to_deleted_user_1");
		}
		String user = new MainPage(driver).openUsersPage().findUserBy(firstName, lastName, role);
		if (user == null || user == "") {
			fail("Added user not found!");
		}

		// Delete added user
		usersPage = new MainPage(driver).openUsersPage();
		usersPage.deleteUser(firstName, lastName, phoneNumber);
		LOGGER.log(Level.INFO, "Deleted user: " + firstName + " | " + lastName + " | " + phoneNumber + " | " + email);
		// Checks if user is deleted
		user = new MainPage(driver).openUsersPage().findUserBy(firstName, lastName, role);
		if (user != null) {
			fail("User is not deleted!");
		}	
		
		// Add user with phone number assigned to deleted user
		addUserPage = new MainPage(driver).openAddUserPage();
		firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		email = DataGenerator.getRandomEmail("example.com");
		LOGGER.log(Level.INFO, "Adding user: " + firstName + " | " + lastName + " | " + phoneNumber + " | " + email);
		addUserPage.addUser(firstName, lastName, role, phoneNumber, email);
		usersPage = new UsersPage(driver);
		// Checks if user is saved
		if (!usersPage.isSuccessAlertPresent()) {
			fail("Success alert not present after saved new user. Check screenshots");
			Screenshot.getInstance().takeScreenshot(driver, "test_add_user_with_phone_assigned_to_deleted_user_2");
		}
		user = new MainPage(driver).openUsersPage().findUserBy(firstName, lastName, role);
		if (user == null || user == "") {
			fail("Added user not found!");
		}
	}
	
	@Test
	public void test_add_user_with_duplicated_email(){
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
		
		new MainPage(driver).openAddUserPage();
		firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		phoneNumber = String.valueOf(DataGenerator.getRandomNumber(9));
		LOGGER.log(Level.INFO, "Adding user with duplicated email: " + firstName + " | " + lastName + " | " + phoneNumber + " | " + email);
		addUserPage.addUser(firstName, lastName, role, phoneNumber, email);
		if(!addUserPage.isDangerAlertPresent()){
			fail("User with duplicated email added to system!");
		}
	}
	
	@Test
	public void test_add_user_with_duplicated_phone_number(){
		// Add valid user
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
		
		String user = new MainPage(driver).openUsersPage().findUserBy(firstName, lastName, phoneNumber, role);
		if(user == null || user == ""){
			fail("Added user not found!");
		}
		
		// Add user with phone same as previous user
		new MainPage(driver).openAddUserPage();
		firstName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		lastName = DataGenerator.getRandomString(DataGenerator.POLISH_LETTERS, 10);
		email = DataGenerator.getRandomEmail("example.com");
		LOGGER.log(Level.INFO, "Adding user with duplicated email: " + firstName + " | " + lastName + " | " + phoneNumber + " | " + email);
		addUserPage.addUser(firstName, lastName, role, phoneNumber, email);
		user = new MainPage(driver).openUsersPage().findUserBy(firstName, lastName, phoneNumber, role);
		if(user != null ){
			fail("Added user with duplicated phone number!");
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
