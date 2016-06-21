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

import utils.Screenshot;

public class AbstractTest {
	protected static final Logger LOGGER = Logger.getLogger(AbstractTest.class.getName());
	protected static WebDriver driver;
	protected static int currentImplicitWait;
	protected static int lastImplicitWait;
	
	private static String driverName;
	private static String driverPath;
	private static String osName;

	@BeforeSuite
	@Parameters({"driver", "driverPath","os","implicitWait"})
	public void setUpSuite(	@Optional("firefox") String driverNameParam,
							@Optional("") String driverPathParam, 
							@Optional("windows") String osNameParam, 
							@Optional("10") int implicitWaitParam) {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Setting webdriver to " + driverNameParam);
		LOGGER.info("Setting driver path to " + driverPathParam);
		LOGGER.info("Setting os name to " + osNameParam);
		LOGGER.info("Setting implicit wait time to " + implicitWaitParam + " seconds");
		
		driverName = driverNameParam;
		driverPath = driverPathParam;
		osName = osNameParam;
		currentImplicitWait = implicitWaitParam;	
		
		Screenshot.getInstance().clearScrrenshotFolder();
	}	
	
	public void initWebDriver(){
		setWebDriver(driverName, driverPath, osName);
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

	public void setWebDriver(String driverName, String driverPath, String osName){
		switch (driverName.toLowerCase()) {
		case "firefox": {
			driver = new FirefoxDriver();
		};break;
		case "chrome": {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		};break;
		case "internetexplorer": {
			System.setProperty("webdriver.ie.driver", driverPath);
			driver = new InternetExplorerDriver();
		};break;
		case "opera": {
			System.setProperty("os.name",osName);
			System.setProperty("opera.binary", driverPath);
			driver = new OperaDriver();
		};break;
		case "safari": {
			System.setProperty("SafariDefaultPath", driverPath);
			driver = new SafariDriver();
		};break;
		case "htmlunit": {
			driver = new HtmlUnitDriver();
		};break;
		}
	}
}
