package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{

	@FindBy(id="username")
	private WebElement usernameField;
	
	@FindBy(id="password")
	private WebElement passwordField;	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage loginAsKda(){
		usernameField.clear();
		usernameField.sendKeys("kda");
		passwordField.clear();
		passwordField.sendKeys("kda");
		passwordField.submit();
		return this;
	}	
}
