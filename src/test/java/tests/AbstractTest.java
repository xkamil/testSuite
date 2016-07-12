package tests;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.DriverFactory;
import utils.Screenshot;

public class AbstractTest {
	protected static final Logger LOGGER = Logger.getLogger(AbstractTest.class.getName());
	protected static WebDriver driver;
	protected static int currentImplicitWait;
	protected static int lastImplicitWait;
	protected static String baseUrl;
	protected static String os;
	protected static String driverName;
	
	@BeforeSuite
	@Parameters({"driver","os","implicitWait","baseUrl"})
	public void setUpSuite(	@Optional("chrome") String driverName, 
							@Optional("mac") String os, 
							@Optional("10") int currentImplicitWait,
							@Optional("") String baseUrl) {
		
		AbstractTest.driverName = driverName;
		AbstractTest.os = os;
		AbstractTest.baseUrl = baseUrl;
		AbstractTest.currentImplicitWait = currentImplicitWait;	
		
		Screenshot.getInstance().clearScrrenshotFolder();
	}	
	
	public void initWebDriver(String name, String os){
		DriverFactory.getDriver(name, os);
		setImplicitWait(currentImplicitWait);
	}
	
	public void initWebDriver(){
		driver = DriverFactory.getDriver(driverName, os);
		setImplicitWait(currentImplicitWait);
	}
	
	public void setImplicitWait(int seconds){
		currentImplicitWait = seconds;
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		LOGGER.log(Level.INFO, "Implicit wait changed to " + seconds + " seconds.");
	}
	
	public void changeImplicitWait(int seconds){
		lastImplicitWait = currentImplicitWait;
		setImplicitWait(seconds);
		LOGGER.log(Level.INFO, "Implicit wait changed to " + seconds + " seconds.");
	}
	
	public void restoreImplicitWait(){
		setImplicitWait(lastImplicitWait);
		LOGGER.log(Level.INFO, "Implicit wait restored to " + lastImplicitWait + " seconds.");
	}
}
