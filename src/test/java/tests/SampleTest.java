package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import io.selendroid.client.SelendroidDriver;
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
	public void test_opening_google_site(){
		driver.get("http://www.google.pl");
		Screenshot.getInstance().takeScreenshot(driver, "test_opening_google_site3");
	}
	
	@Test
	public void test_opening_onet_site(){
		driver.get("http://www.onet.pl");
		Screenshot.getInstance().takeScreenshot(driver, "test_opening_onet_site3");
	}	
	
	@Test(dataProvider = "valid_users", enabled = false)
	public void test_printing_users(String username, String password){
		System.out.println(username + " | " + password);
	}
	
	@AfterMethod
	public void tearDownClass(){
		if(!(driver instanceof SelendroidDriver)){
			driver.close();
		}	
	}
	
	
}
