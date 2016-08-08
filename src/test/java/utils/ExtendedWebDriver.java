package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ExtendedWebDriver extends WebDriver{

	void wait(int seconds);
	WebElement findClickableElementBeforeTimeout(By by, int timeout);
	WebElement findElementBeforeTimeout(By by, int timeout);
	boolean areElementsPresent(By... locators);
	boolean areElementsPresentAndClickable(By... locators);
}
