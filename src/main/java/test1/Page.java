package test1;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page {
	protected WebDriver driver;
	
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
