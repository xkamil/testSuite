package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
	protected WebDriver driver;
	
	public AbstractPage(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean areElementsPresent(By... elements){
		for(By element : elements){
			try{
				driver.findElement(element);
			}catch(NoSuchElementException ex){
				return false;
			}			
		}
		return true;
	}
}
