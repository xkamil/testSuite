package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ClientsPage extends AbstractPage{
		
	public ClientsPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
			
}
