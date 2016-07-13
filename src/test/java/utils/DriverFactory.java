package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import static utils.DriverConstants.*;


public final class DriverFactory {
	public static WebDriver getDriver(String name){
		String driverPath = DRIVERS_PATH + getDriverFolderSpecificToOs() + "/" + name + "driver" + getOsSpecificFileExtension();
		
		switch(name){
			case DRIVER_FIREFOX : {
				return new FirefoxDriver();
			}			
			case DRIVER_CHROME : {
				System.setProperty("webdriver.chrome.driver", driverPath);
				return new ChromeDriver();
			}			
			case DRIVER_IE : {
				System.setProperty("webdriver.ie.driver", driverPath);
				return new InternetExplorerDriver();
			}			
			case DRIVER_SAFARI : {
				System.setProperty("SafariDefaultPath", driverPath);
				return new SafariDriver();
			}			
			case DRIVER_HTMLUNIT : {
				return new HtmlUnitDriver();
			}			
			default : {
				return new FirefoxDriver();
			}
		}
	}
	
	private static String getOsSpecificFileExtension(){
		if(isWindows){
			return ".exe";
		}
		return "";
	}
	
	private static String getDriverFolderSpecificToOs(){
		if(isWindows){
			return "windows";
		}else if(isMac){
			return "mac";
		}else if(isUnix){
			return "linux";
		}
		return "";
	}
}
