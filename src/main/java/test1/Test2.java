package test1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test2 extends AbstractTest{
	@BeforeClass
	public void setUpClass(){
		driver.get("http://www.google.pl");
	}
	
	@Test
	public void test_print_message(){
		System.out.println("2 Message in teset_print_message");
	}
	
	@Test
	public void test_print_message2(){
		System.out.println("2 Message in teset_print_message2");
	}	
	
	@AfterClass
	public void tearDownClass(){
		driver.close();
	}
}
