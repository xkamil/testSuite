package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
	private WebDriver driver;
	
	@FindBy(partialLinkText="Użytkownicy")
	private WebElement usersButton;
	
	@FindBy(partialLinkText="Klienci")
	private WebElement clientsButton;
	
	@FindBy(partialLinkText="Wyceny")
	private WebElement pricingsButton;	
	
	@FindBy(partialLinkText="Dodaj użytkownika")
	private WebElement addUserButton;
		
	public MainPage(WebDriver currentDriver){
		driver = currentDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean pageLoaded(){
		if(pricingsButton.isDisplayed() && clientsButton.isDisplayed()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean logggedInAsAdmin(){
		if(addUserButton.isDisplayed()){
			return true;
		}
		return false;
	}
	
	public boolean logggedInAsOperator(){
		if(addUserButton.isDisplayed()){
			return false;
		}
		return true;
	}	
	
	public UsersPage goToUsersPage(){
		usersButton.click();
		return new UsersPage(driver);
	}
	
	public AddUserPage goToAddUserPage(){
		addUserButton.click();
		return new AddUserPage(driver);
	}
}
