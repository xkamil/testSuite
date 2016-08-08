package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ExtendedWebDriver;

public class SamplePage extends AbstractPage{
	
	@FindBy(id="username")
	private WebElement usernameField;
	
	@FindBy(id="password")
	private WebElement passwordField;
	
	public SamplePage(ExtendedWebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public SamplePage login(String login, String password){
		usernameField.clear();
		usernameField.sendKeys(login);
		passwordField.clear();
		passwordField.sendKeys(password);
		passwordField.sendKeys(Keys.ENTER);
		return this;
	}
	
	public SamplePage openAddEventForm(){
		driver.findElement(By.cssSelector("div[id='navAdditionalChanger'] span")).click();
		driver.findElement(By.partialLinkText("Dodaj wydarzenie")).click();
		
		return this;
	}
	
	public SamplePage selectEventCategory(String category){
		WebElement el = driver.findElementBeforeTimeout(By.cssSelector("kiss-select[source='ctrl.resources.categories'] span"), 1);
		
		try{
			el.click();
		}catch(Exception ex){
			el = driver.findElementBeforeTimeout(By.cssSelector("kiss-select[source='ctrl.resources.categories'] span"), 15);
		}
		el.click();
		driver.findElement(By.partialLinkText(category)).click();

		return this;
	}
	
	public SamplePage selectEventProject(String project){
		WebElement el = driver.findElementBeforeTimeout(By.cssSelector("kiss-select[source='ctrl.resources.projects'] span"), 1);
		
		try{
			el.click();
		}catch(Exception ex){
			el = driver.findElementBeforeTimeout(By.cssSelector("kiss-select[source='ctrl.resources.projects'] span"), 15);	
		}
		el.click();
		driver.findElement(By.partialLinkText(project)).click();
		return this;		
	}
	
}
