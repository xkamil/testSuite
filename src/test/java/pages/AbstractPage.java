package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
	
	public void loadAllResultsOnPage(){
		By flashListContainer = By.cssSelector("kiss-flash-list");
		By flashListElement = By.cssSelector("*[element^='collection[']");
		WebElement flashContainer = driver.findElement(flashListContainer);
		List<WebElement> elements = new ArrayList<>();
		int count = 0;
		boolean allDataLoaded = false;
		do{
			elements = flashContainer.findElements(flashListElement);
			count = elements.size();
			Actions actions = new Actions(driver);
			actions.moveToElement(elements.get(count - 1)).build().perform();
			try{
				driver.findElement(By.cssSelector("*[element='collection[" + (count + 1) + "]']"));
			}catch(Exception ex){
				allDataLoaded = true;
			}
		}while(allDataLoaded == false);
	}
	
	/**
	 * For debugging only!
	 * @param seconds - time in seconds to 
	 */
	public void wait(int seconds){
		int time = seconds * 1000;
		try{
			Thread.sleep(time);
		}catch(InterruptedException ex){}
	}
}
