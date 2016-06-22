package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import utils.DataProviderGenerator;

public class LoginPage extends AbstractPage{
	
	@FindBy(name="_username")
	private WebElement usernameField;
	
	@FindBy(name="_password")
	private WebElement passwordField;
	
	@FindBy(css="button[type='submit']")
	private WebElement loginButton;
	
	private static By loginAlert = By.xpath("//html/body/div[1]/h4");
	
	public LoginPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
		
	public MainPage logIn(String username, String password){
		usernameField.clear();
		usernameField.sendKeys(username);
		passwordField.clear();
		passwordField.sendKeys(password);
		loginButton.click();
		return new MainPage(driver);
	}
	
	public boolean isLoginFailedAlertPresent(){
		return this.areElementsPresent(loginAlert);
	}
	
	public MainPage logInAsAdmin(){
		Object[][] admin = DataProviderGenerator.getData(new File("test-input/admin_user.csv"));
		String username = admin[0][0].toString();
		String password = admin[0][1].toString();
		return logIn(username, password);
	}
}
