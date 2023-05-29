package stepDefination;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import pages.DashboardPage;
import pages.LoginPage;
import utility.ExcelUtil;

public class LoginSteps {

	WebDriver driver;
	LoginPage lp;
	DashboardPage dp;
	SeleniumBase se = new SeleniumBase(driver);
	String sheetName = "LoginFunctionality";
	ExcelUtil excel = new ExcelUtil();

	@Given("User Launch {string} browser")
	public void user_launch_browser(String string) {
		se.getBrowserDriver(string);
	}

	@And("Open Url {string}")
	public void open_url(String string) {
		this.driver = SeleniumBase.driver;
		driver.get(string);
	}

	@When("User Enter Email {string} and Password {string}")
	public void user_enter_email_and_password(String string, String string2) throws InterruptedException {
		lp = new LoginPage(driver);
		Thread.sleep(10);
		lp.getUsernameElement(string);
		Thread.sleep(10);
		lp.getPasswordElement(string2);
	}

	@And("user clicks on login button.")
	public void user_clicks_on_login_button() {
		lp.getLoginButtonElement();
	}

	@Then("User lands on Dashboard page")
	public void user_lands_on_dashboard_page() {
		dp = new DashboardPage(driver);
		dp.getdashboardElement();
		driver.quit();
	}
}
