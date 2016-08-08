package utils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class SuperWebDriver implements ExtendedWebDriver{
	private WebDriver driver;
	
	public SuperWebDriver(WebDriver driver){
		this.driver = driver;
	}
	
	@Override
	public void close() {
		driver.close();
		
	}

	@Override
	public WebElement findElement(By arg0) {
		return driver.findElement(arg0);
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		return driver.findElements(arg0);
	}

	@Override
	public void get(String arg0) {
		driver.get(arg0);
		
	}

	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override
	public Options manage() {
		return driver.manage();
	}

	@Override
	public Navigation navigate() {
		return driver.navigate();
	}

	@Override
	public void quit() {
		driver.quit();
		
	}

	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	@Override
	public void wait(int seconds) {
		try{
			Thread.sleep(seconds * 1000);
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	}

	@Override
	public WebElement findClickableElementBeforeTimeout(By by, int timeout) {
		WebElement element = (new FluentWait<WebDriver>(driver)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(250, TimeUnit.MILLISECONDS)
				.until(ExpectedConditions.elementToBeClickable(by)));
		return element;
	}

	@Override
	public WebElement findElementBeforeTimeout(final By by, int timeout) {
		WebElement element = (new FluentWait<WebDriver>(driver)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.until(new ExpectedCondition<WebElement>(){
					@Override
					public WebElement apply(WebDriver driver) {
						return driver.findElement(by);
					}}));
		return element;
		
	}

	
	@Override
	public boolean areElementsPresent(By... locators) {
		for (By locator : locators) {
			try {
				driver.findElement(locator);
			} catch (NoSuchElementException ex) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean areElementsPresentAndClickable(By... locators){
		for (By locator : locators) {
			try {
				WebElement element = driver.findElement(locator);
				if(!element.isDisplayed()){
					throw new ElementNotVisibleException("Element " + element.toString() + " is not visible.");
				}
				
				if(!element.isEnabled()){
					throw new NoSuchElementException("Element " + element.toString() + " is not enabled.");
				}
			} catch (NoSuchElementException | ElementNotVisibleException ex) {
				return false;
			}
		}
		return true;		
	}

}
