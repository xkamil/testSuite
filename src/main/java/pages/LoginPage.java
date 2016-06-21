package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;
	
	@FindBy(name="_username")
	private WebElement usernameField;
	
	@FindBy(name="_password")
	private WebElement passwordField;
		
	public LoginPage(WebDriver currentDriver){
		driver = currentDriver;
		PageFactory.initElements(driver, this);
	}
	
	public MainPage logIn(String username, String password){
		usernameField.clear();
		usernameField.sendKeys(username);
		passwordField.clear();
		passwordField.sendKeys(password);
		passwordField.submit();
		return new MainPage(driver);
	}
	
	public MainPage logInAsAdmin(){
		logIn("admin@example.com", "test");
		return new MainPage(driver);
	}
		
}
