package tests;

import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverFactory;
import utils.Screenshot;

public class AbstractTest {
	protected static final Logger LOGGER = Logger.getLogger(AbstractTest.class.getName());
	protected static WebDriver driver;
	protected static String baseUrl;
	protected static String driverName;
		
	@BeforeSuite
	@Parameters({"driver","baseUrl"})
	public void setUpSuite(	@Optional("chrome") String driverName,  
							@Optional("") String baseUrl) {
		
		AbstractTest.driverName = driverName;
		AbstractTest.baseUrl = baseUrl;			
		Screenshot.getInstance().clearScrrenshotFolder();
		
		String system = System.getProperty("os.name");
		System.out.println("System: " + system);
		
	}	
	
	public void initWebDriver(String name, String os){
		DriverFactory.getDriver(name);
	}
	
	public void initWebDriver(){
		driver = DriverFactory.getDriver(driverName);
	}
	
	public WebElement findElementBy(final By webElement, int explicitTimeInSeconds){
		return (new WebDriverWait(driver, explicitTimeInSeconds)).until(new ExpectedCondition<WebElement>(){
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(webElement);
			}			
		});
	}

}
