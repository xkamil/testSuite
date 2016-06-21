package tests;

import java.util.Random;
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

public class AbstractTest {
	protected static final Logger LOGGER = Logger.getLogger("");
	protected WebDriver driver;

	@BeforeSuite
	@Parameters({"driver", "driverPath","os"})
	public void setUpSuite(@Optional("firefox") String driverName, @Optional("") String driverPath, @Optional("windows") String osName) {
		LOGGER.setLevel(Level.ALL);
		LOGGER.info("Setting up webdriver as" + driverName);
		
		switch (driverName.toLowerCase()) {
		case "firefox": {
			driver = new FirefoxDriver();
		};break;
		case "chrome": {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		};break;
		case "ie": {
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
