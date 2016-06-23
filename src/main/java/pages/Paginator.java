package pages;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Paginator implements Iterator<WebElement>{
	
	private List<WebElement> paginationButtons;
	private WebDriver driver;
	private By locator;	
	private int pageIndex = 0;
	
	public Paginator(WebDriver driver, By locator){
		this.driver = driver;
		this.locator = locator;
		initElements();
	}
	
	public void initElements(){
		CopyOnWriteArrayList cArray;
		paginationButtons = new CopyOnWriteArrayList(driver.findElements(locator));
		
		for(WebElement paginateButton : paginationButtons){
			if(!paginateButton.isDisplayed() || !paginateButton.isEnabled()){
				paginationButtons.remove(paginateButton);
			}
		}
		
	}
	
	@Override
	public boolean hasNext() {
		initElements();
		if(paginationButtons.size() <=1 || pageIndex > paginationButtons.size()){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public WebElement next() {
		initElements();
		if(paginationButtons.size() > 1 && pageIndex > 0 && pageIndex < paginationButtons.size()){
			if(paginationButtons.get(pageIndex).isDisplayed() && paginationButtons.get(pageIndex).isEnabled()){
				paginationButtons.get(pageIndex).click();
			}		
			
		}
		pageIndex++;
		return null;	
	}
}
