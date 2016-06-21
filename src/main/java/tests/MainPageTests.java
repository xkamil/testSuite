package tests;

import static org.testng.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.UsersPage;

public class MainPageTests {
	private static String baseUrl = "http://optidata.kiss1.pl/app_dev.php/login";
	private WebDriver driver;

	@BeforeTest
	public void setUpTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}
	
	@BeforeMethod
	public void setUpTestMethod(){
		driver.manage().deleteAllCookies();
		driver.navigate().to(baseUrl);		
	}

	@Test()
	public void test_login_as_admin() {
		String username = "admin@example.com";
		String password = "test";
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.logIn(username, password);
		assertTrue(mainPage.logggedInAsAdmin());
	}

	@Test()
	public void test_user_present_in_system() {
		String firstName = "Agent";
		String lastName = "Cieszynka";
		UsersPage usersPage = new LoginPage(driver).logInAsAdmin().goToUsersPage();
		boolean userPresent = usersPage.isUserInSystem(firstName, lastName);
		assertTrue(userPresent);
	}

	@AfterTest
	public void tearDownTest() {
		driver.close();
	}
}
