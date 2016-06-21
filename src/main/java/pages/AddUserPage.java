package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddUserPage {

	private WebDriver driver;
	
	@FindBy(xpath="//*[@id='usersList']/tbody")
	private WebElement usersTable;
	
	@FindBy(name="usersList_length")
	private WebElement listLenghtSelect;
	
	public AddUserPage(WebDriver currentDriver){
		driver = currentDriver;
		PageFactory.initElements(driver, this);
	}
	
	public int getUsersCount(){		
		return getAllUsers().size();
	}	
	
	public List<WebElement> getAllUsers(){
		Select select = new Select(listLenghtSelect);
		select.selectByValue("100");		
		List<WebElement> userRows = usersTable.findElements(By.tagName("tr"));
		return userRows;
	}
	
	public boolean isUserInSystem(String firstName, String lastName){
		List<WebElement> users = getAllUsers();
		for(WebElement user : users){
			String text = user.getText();
			if(text.toLowerCase().contains(firstName.toLowerCase()) && 
				text.toLowerCase().contains(lastName.toLowerCase())	){
				return true;
			}
		}
		return false;
	}
}
