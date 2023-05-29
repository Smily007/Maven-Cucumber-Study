package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions	(
		features = {"C:\\Users\\Admin\\OneDrive\\Desktop\\Study\\Demo\\Feature\\OrangeLogin.feature"},
		glue	 = {"stepDefination"},
		plugin 	 = {"pretty", "html:target/cucumber-reports"},
		dryRun = false,
		monochrome = true
		//strict =true
		
		)
public class LoginRunner {

}
