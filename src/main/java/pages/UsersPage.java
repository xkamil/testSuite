package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UsersPage extends AbstractPage{
	public static final int QUANTITY_10 = 10;
	public static final int QUANTITY_25 = 10;
	public static final int QUANTITY_50 = 10;
	public static final int QUANTITY_100 = 10;
	
	public static final String FILTER_ALL = "Wszyscy";
	public static final String FILTER_OPERATOR = "Operator";
	public static final String FILTER_AGENT = "Agent";
	public static final String FILTER_ADMIN = "Administrator";
	
	@FindBy(id="search-users")
	private WebElement roleFilterSelect;
	@FindBy(name="usersList_length")
	private WebElement resultQuantitySelect;
	@FindAll({@FindBy(css="div[id='usersList_paginate'] span a")})
	private List<WebElement> paginateButtons;
	
	private static By userRow = By.cssSelector("table[id='usersList'] > tbody > tr");
	private static By successAlert = By.cssSelector("div[class='callout callout-success'] > h4");
	
	public UsersPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getAllUsers(){
		List<WebElement> users = new ArrayList<>();
		int index = 0;
		do{
			List<WebElement> usersOnPage = driver.findElements(userRow);
			users.addAll(usersOnPage);
			try{
				paginateButtons.get(index+1).click();
			}catch(Exception ex){}
			index++;
		}while(index < paginateButtons.size());
		return users;
	}	
	
	public List<String> getAllUsersAsTextList(){
		List<String> users = new ArrayList<>();
		int index = 0;
		do{
			List<WebElement> usersOnPage = driver.findElements(userRow);
			for(WebElement wUsr : usersOnPage){
				users.add(wUsr.getText());
			}
			try{
				paginateButtons.get(index+1).click();
			}catch(Exception ex){}
			index++;
		}while(index < paginateButtons.size());
		return users;
	}	

	public String findUserBy(String...texts){
		List<String> users = getAllUsersAsTextList();
		WebElement userRow = null;
		for(String user : users){
			boolean found = false;
			for(String text : texts){
				if(user.contains(text)){
					found = true;
				}else{
					found = false;
					break; //TODO wywalić tego brzydkiego brejka
				}
			}
			if(found){
				return user;
			}
		}
		return null;		
	}
	
	public boolean checkIfAllUsersContainsText(String text){
		List<String> users = getAllUsersAsTextList();
		System.out.println("Checking users count: " + users.size() + " for text: " + text);
		for(String user : users){
			if(!user.contains(text)){
				return false;
			}
		}
		return true;
	}

	public boolean isSuccessAlertPresent(){
		try{
			if(driver.findElement(successAlert).isDisplayed()){
				return true;
			}
			return false;
		}catch(Exception ex){  //TODO replace with proper Exception (for element not found exception)
			return false;
		}
	}

	public void setUserFilter(String filter){
		Select roleFilter = new Select(roleFilterSelect);
		roleFilter.selectByVisibleText(filter);
	}
}
