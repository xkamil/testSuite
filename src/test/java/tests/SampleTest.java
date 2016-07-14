package tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import pages.*;
import utils.Screenshot;

public class SampleTest extends AbstractTest{
	
	@BeforeMethod
	public void setUpTestMethod(){
		initWebDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://a1.kiss1.pl/#/");
	}
	
	@Test(enabled=false)
	public void test_login_as_kda(){
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsKda();
	}
	
	@Test(enabled=false)
	public void test_open_tasks_module(){
		new LoginPage(driver)
			.loginAsKda();
		new MainPage(driver)
			.openTasksModule();
	}	
	
	@Test(enabled=false)
	public void test_open_new_event_form(){
		new LoginPage(driver)
			.loginAsKda();
		new MainPage(driver)
			.openTasksModule()
			.openNewEventForm();
	}
	
	@Test
	public void test_add_new_event(){
		new LoginPage(driver)
			.loginAsKda();  
		new MainPage(driver)
			.openTasksModule()  
			.openNewEventForm(); 
		new AddEventPage(driver)
			.selectEventCategory("Spotkanie") 
			.setEventTitle("Tytuł wydarzenia test1")
			.setEventDescription("Opis wydarzenia test1")
			.setEventLastsAllDay()
			.setDateFrom("13.10.2020")
			.setDateTo("14.10.2020")
			.submitForm();	 
		
		//TODO add assertion
	}	
		
	@AfterMethod
	public void tearDownTestMethod(){
		wait(10); //wait 5seconds before closing test method - FOR VISIAL DEBUGGING ONLY
		driver.close();
		
	}
	
	
}
