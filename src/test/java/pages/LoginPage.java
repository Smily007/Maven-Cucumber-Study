package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepDefination.SeleniumBase;

public class LoginPage extends SeleniumBase {

	// private WebDriver driver;
	
	@FindBy(xpath = "//h5")
	WebElement logintextElement;

	@FindBy(name = "username")
	WebElement usernameElement;

	@FindBy(name = "password")
	WebElement passwordElement;

	@FindBy(xpath = "//button[@type=\"submit\"]")
	WebElement loginButtonElement;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}

	public void getLoginLogoElement() {
		if (logintextElement.isDisplayed()) {
			System.out.println(logintextElement.getText());
		}
	}

	public String getUsernameElement(String email) {
		usernameElement.sendKeys(email);
		return email;
	}

	public String getPasswordElement(String password) {
		passwordElement.sendKeys(password);
		return password;
	}

	public void getLoginButtonElement() {
		loginButtonElement.click();
		
	}
}
