package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEventPage extends AbstractPage{

	@FindBy(css="input[ng-model='ctrl.data.event.title']")
	private WebElement eventTitleField;

	@FindBy(css="textarea[ng-model='ctrl.data.event.description']")
	private WebElement eventDescriptionField;
	
	@FindBy(css="kiss-date[date='ctrl.data.event.start'] input[id='label-input']")
	private WebElement dateFromInput;
	
	@FindBy(css="kiss-date[date='ctrl.data.event.end'] input[id='label-input']")
	private WebElement dateToInput;
	
	@FindBy(css="button[ng-click='ctrl.save()']")
	private WebElement submitButton;
	
	
	private static By allDayCheckbox = By.cssSelector("label[for='allDay']");
	private static By eventCategorySelect = By.cssSelector("kiss-select[model='ctrl.data.event.category'] span");
	private static By eventCategory = null;
	//private static By dateFrom = By.cssSelector("kiss-date[date='ctrl.data.event.start'] input[id='label-input']");
	//private static By dateTo = By.cssSelector("kiss-date[date='ctrl.data.event.end'] input[id='label-input']");
	
	public AddEventPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public AddEventPage selectEventCategory(String category){
		eventCategory = By.partialLinkText(category);
		this.getElementIfClicableBeforeTimeout(eventCategorySelect, 10)
			.click();
		this.getElementBeforeTimeout(eventCategory, 10).click();
		return this;
	}

	public AddEventPage setEventTitle(String title){
		eventTitleField.clear();
		eventTitleField.sendKeys(title);
		return this;
	}
	
	public AddEventPage setEventDescription(String description){
		eventDescriptionField.clear();
		eventDescriptionField.sendKeys(description);
		return this;
	}	
	
	public AddEventPage setEventLastsAllDay(){
		this.getElementIfClicableBeforeTimeout(allDayCheckbox, 3).click();
		return this;
	}
	
	public AddEventPage setDateFrom(String date){
		dateFromInput.clear();
		dateFromInput.click();
		dateFromInput.sendKeys(date);
		return this;
	}
	
	public AddEventPage setDateTo(String date){
		dateToInput.clear();
		dateToInput.click();
		dateToInput.sendKeys(date);
		return this;
	}
	
	public AddEventPage submitForm(){
		submitButton.click();
		return this;
	}
}
