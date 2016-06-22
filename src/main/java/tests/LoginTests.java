package tests;

import java.io.File;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import utils.DataProviderGenerator;
import static org.junit.Assert.*;

public class LoginTests extends AbstractTest{
	
	@Test(dataProvider = "invalid_users")
	public void test_login_as_invalid_user(String username, String password){
		LoginPage loginPage = new LoginPage(driver);
		loginPage.logIn(username, password);
		assertTrue(loginPage.isLoginFailedAlertPresent());
	}
	
	@Test(dataProvider = "valid_users")
	public void test_login_as_valid_user(String username, String password){
		LoginPage loginPage = new LoginPage(driver);		
		MainPage mainPage = loginPage.logIn(username, password);
		assertTrue(mainPage.pageLoaded());						
	}
	
	@Test(dataProvider = "operator_user")
	public void test_login_as_operator(String username, String password){
		LoginPage loginPage = new LoginPage(driver);		
		MainPage mainPage = loginPage.logIn(username, password);
		assertTrue(mainPage.loggedInAsOperator());		
	}
	
	@Test(dataProvider = "admin_user")
	public void test_login_as_admin(String username, String password){
		LoginPage loginPage = new LoginPage(driver);		
		MainPage mainPage = loginPage.logIn(username, password);
		assertTrue(mainPage.loggedInAsAdmin());		
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
	public static Object[][] valid_users(){
		return DataProviderGenerator.getData(new File("test-input/valid_users.csv"));
	}
	
	@DataProvider
	public static Object[][] invalid_users(){
		return DataProviderGenerator.getData(new File("test-input/invalid_users.csv"));
	}	
	
	@DataProvider
	public static Object[][] admin_user(){
		return DataProviderGenerator.getData(new File("test-input/admin_user.csv"));
	}	
	
	@DataProvider
	public static Object[][] operator_user(){
		return DataProviderGenerator.getData(new File("test-input/operator_user.csv"));
	}	
}
