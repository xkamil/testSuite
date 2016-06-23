package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UsersPage extends AbstractPage {

	public static final String FILTER_ADMIN = "Administrator";
	public static final String FILTER_AGENT = "Agent";
	public static final String FILTER_OPERATOR = "Operator";
	public static final String FILTER_ALL = "Wszyscy";

	@FindBy(id = "search-users")
	private WebElement roleFiltert;
	@FindBy(name = "usersList_length")
	private WebElement listLenghtFilter;

	private static By paginateButton = By.cssSelector("div[id='usersList_paginate'] span a");
	private static By userRow = By.cssSelector("table[id='usersList'] > tbody > tr");
	private static By successAlert = By.cssSelector("div[class='callout callout-success'] > h4");

	public UsersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public List<String> getAllUsersAsTextList() {
		List<String> allUsers = new ArrayList<>();
		Paginator paginator = new Paginator(driver, paginateButton);
		
		do{
			List<WebElement> usersOnPage = driver.findElements(userRow);
			for (WebElement wUsr : usersOnPage) {
				allUsers.add(wUsr.getText());
			}
			paginator.next();
		}while(paginator.hasNext());
		return allUsers;
	}

	public List<String> getAllVisibleUsersAsTextList() {
		List<String> allVisibleUsers = new ArrayList<>();
		List<WebElement> usersOnPage = driver.findElements(userRow);
		for (WebElement wUsr : usersOnPage) {
			allVisibleUsers.add(wUsr.getText());
		}
		return allVisibleUsers;
	}

	public String findUserBy(String... textParams) {
		List<String> users = getAllUsersAsTextList();
		for (String user : users) {
			if (isStringContainsText(user, textParams)) {
				return user;
			}
		}
		return null;
	}

	public void openUserPage(String... textParams) {
		WebElement user = null;
		Paginator paginator = new Paginator(driver, paginateButton);
		do{
			List<WebElement> visibleUsers = driver.findElements(userRow);
			for(WebElement currentUser : visibleUsers){
				if(this.isStringContainsText(currentUser.getText(), textParams)){
					user = currentUser;
					break;
				}
			}
			if(user != null){
				user.findElement(By.cssSelector("td[class='sorting_1'] > a")).click();
				break;
			}			
			paginator.next();
		}while(paginator.hasNext() && user == null);
	}

	public void deleteUser(String... textParams) {
		WebElement user = null;
		Paginator paginator = new Paginator(driver, paginateButton);
		do{
			List<WebElement> visibleUsers = driver.findElements(userRow);
			for(WebElement currentUser : visibleUsers){
				if(this.isStringContainsText(currentUser.getText(), textParams)){
					user = currentUser;
					break;
				}
			}
			if(user != null){
				user.findElement(By.cssSelector("td[class='td-delete-width'] > a")).click();
				break;
			}			
			paginator.next();
		}while(paginator.hasNext() && user == null);
	}

	public boolean checkIfAllUsersContainsText(String text) {
		List<String> users = getAllUsersAsTextList();
		System.out.println("Checking users count: " + users.size() + " for text: " + text);
		for (String user : users) {
			if (!user.contains(text)) {
				return false;
			}
		}
		return true;
	}

	public boolean isSuccessAlertPresent() {
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

	public void setUserFilter(String filter) {
		Select roleFilter = new Select(roleFiltert);
		roleFilter.selectByVisibleText(filter);
	}
}
