package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddUserPage extends AbstractPage{
	public static final String ROLE_AGENT = "Agent";
	public static final String ROLE_OPERATOR = "Operator";
	public static final String ROLE_ADMIN = "Administrator";
	
	@FindBy(id="firstName")
	private WebElement firstNameField;
	@FindBy(id="lastName")
	private WebElement lastNameField;
	@FindBy(id="select-role")
	private WebElement roleSelect;	
	@FindBy(id="phoneNumber")
	private WebElement phoneNumberField;	
	@FindBy(id="email")
	private WebElement emailField;	
	@FindBy(id="userName")
	private WebElement userNameField;		
	@FindBy(name="registration")
	private WebElement registrationButton;		
	
	private static By successAlert = By.cssSelector("div[class='callout callout-danger'] > h4");
	private static By validationError = By.cssSelector("div > label[class='error']");
	
	public AddUserPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void addUser(String firstName, String lastName, String role, String phoneNumber, String email){
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
		phoneNumberField.clear();
		phoneNumberField.sendKeys(phoneNumber);
		emailField.clear();
		emailField.sendKeys(email);		
		Select selectRole = new Select(roleSelect);
		selectRole.selectByVisibleText(role);
		registrationButton.click();
	}
	
	public void fillForm(String firstName, String lastName, String role, String phoneNumber, String email){
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
		phoneNumberField.clear();
		phoneNumberField.sendKeys(phoneNumber);
		emailField.clear();
		emailField.sendKeys(email);		
		Select selectRole = new Select(roleSelect);
		selectRole.selectByVisibleText(role);
	}
	
	public void sendForm(){
		registrationButton.click();
	}
	
	public String getUsername(){
		return userNameField.getAttribute("value");
	}
	
	public boolean isDangerAlertPresent(){
		try {
			if (driver.findElement(successAlert).isDisplayed()) {
				return true;
			}
			return false;
		} catch (Exception ex) { // TODO replace with proper Exception (for
									// element not found exception)
			return false;
		}		
	}
	
	public List<String> getValidationErrors(){
		List<String> errors = new ArrayList<>();
		List<WebElement> validationErrorsElements = driver.findElements(validationError);
		for(WebElement errorElement : validationErrorsElements){
			if(errorElement.getText().trim() != ""){
				errors.add(errorElement.getText().trim());
			}
		}
		return errors;
	}
}
