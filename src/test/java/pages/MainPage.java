package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage{
	private static By tasksModuleLink = By.partialLinkText("Zadania");	
	private static By selectCreateForm = By.cssSelector("div[id='navAdditionalChanger'] span");
	private static By addTaskButton = By.partialLinkText("Dodaj zadanie");
	private static By addEventButton = By.partialLinkText("Dodaj wydarzenie");
	
	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public MainPage openTasksModule(){
		this.getElementIfClicableBeforeTimeout(tasksModuleLink, 5).click();
		return this;
	}
	
	public MainPage openNewTaskForm(){
		driver.findElement(selectCreateForm).click();
		this.getElementIfClicableBeforeTimeout(addTaskButton, 3).click();
		return this;
	}
	
	public MainPage openNewEventForm(){
		driver.findElement(selectCreateForm).click();
		this.getElementIfClicableBeforeTimeout(addEventButton, 3).click();
		return this;
	}	
	
	
}
