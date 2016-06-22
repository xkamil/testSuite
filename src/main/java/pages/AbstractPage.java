package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {
	protected WebDriver driver;
	
	public AbstractPage(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean areElementsPresent(By... elements){
		for(By element : elements){
			try{
				WebElement webElement = driver.findElement(element);
				if(!webElement.isDisplayed()){
					return false;
				}
			}catch(NoSuchElementException ex){
				return false;
			}			
		}
		return true;
	}
}
