package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage{
	@FindBy(partialLinkText="Klienci")
	private WebElement clientsLink;
	
	@FindBy(partialLinkText="Wyceny")
	private WebElement pricingsLink;
	
	@FindBy(partialLinkText="Dodaj klienta")
	private WebElement addClientLink;

	@FindBy(partialLinkText="Wyloguj si�")
	private WebElement logoutLink;
	
	private static By addUserLinkLocator = By.partialLinkText("Dodaj u�ytkownika");
	
	private static By usersLinkLocator = By.partialLinkText("U�ytkownicy");
	
	public MainPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean pageLoaded(){
		if(addClientLink.isDisplayed() && logoutLink.isDisplayed()){
			return true;
		}
		return false;
	}
	
	public boolean loggedInAsAdmin(){
		return this.hasElementsPresent(addUserLinkLocator, usersLinkLocator);
	}
	
	public boolean loggedInAsOperator(){
		return !this.hasElementsPresent(addUserLinkLocator, usersLinkLocator);
	}	
	
	public AddUserPage openAddUserPage(){
		driver.findElement(addUserLinkLocator).click();
		return new AddUserPage(driver);
	}
	
	public UsersPage openUsersPage(){
		driver.findElement(usersLinkLocator).click();
		return new UsersPage(driver);
	}	
}
