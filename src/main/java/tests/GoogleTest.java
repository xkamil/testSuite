package tests;

import java.io.File;

import org.testng.annotations.*;

import utils.DataProviderGenerator;
import utils.Screenshot;

public class GoogleTest extends AbstractTest{
	@DataProvider
	public static Object[][] valid_users(){
		return DataProviderGenerator.getData(new File("test-input/valid_users.csv"));
	}
	
	@BeforeMethod
	public void setUpClass(){
		initWebDriver();
	}
	@Test
	public void test_opening_google_site(){
		driver.get("http://www.google.pl");
		Screenshot.getInstance().takeScreenshot(driver, "test_opening_google_site3");
	}
	
	@Test
	public void test_opening_onet_site(){
		this.changeImplicitWait(20);
		driver.get("http://www.onet.pl");
		Screenshot.getInstance().takeScreenshot(driver, "test_opening_onet_site3");
		this.restoreImplicitWait();
	}	
	
	@Test(dataProvider = "valid_users")
	public void test_printing_users(String username, String password){
		System.out.println(username + " | " + password);
	}
	
	@AfterMethod
	public void tearDownClass(){
		driver.close();
	}
	
	
}
