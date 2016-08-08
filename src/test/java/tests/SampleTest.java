package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import pages.SamplePage;
import utils.DataProviderGenerator;
import utils.Screenshot;

public class SampleTest extends AbstractTest{
	@DataProvider
	public static Object[][] valid_users(){
		return DataProviderGenerator.getData(new File("test-input/valid_users.csv"));
	}
	
	@BeforeMethod
	public void setUpClass(){
		initWebDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void test_implicit_wait(){
		driver.get(baseUrl);
		new SamplePage(driver)
			.login("admin", "admin")
			.openAddEventForm()
			.selectEventCategory("Spotkanie")
			.selectEventProject("Daty raty");
		
	}
	
	@AfterMethod
	public void tearDownClass(){
		driver.close();
	}
	
	
}
