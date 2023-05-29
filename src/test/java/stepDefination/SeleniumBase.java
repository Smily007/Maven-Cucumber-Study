package stepDefination;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.EnvironmentHelperUtil;

public class SeleniumBase {
	private static ChromeDriverService chromeService = null;
	public static WebDriver driver;
	EnvironmentHelperUtil environmentHelper = new EnvironmentHelperUtil();
    Map<String, String> credentials = environmentHelper.getEnvironments();
    private Wait<WebDriver> wait;
    
    
    public SeleniumBase(WebDriver driver)
    {
	     this.driver=driver;
	     PageFactory.initElements(driver,this);
    }
	public WebDriver getBrowserDriver(String browser) {

		switch (browser) {
		case "chrome": 
			 WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			break;
		case "firefox": 
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "safari": 
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "desktop": 
			driver = getDesktopDriver();
			break;
		default:
			driver = getChromeDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}

	public static ChromeDriverService createAndStartChromeService(String browser) {
		String os = System.getProperty("os.name");
		//String browser = "desktop"; //System.getProperty("browser");
		File file = new File("src/test/resources");
		File path = null;
		
		if (os.contains("Windows") && browser.equals("desktop")) {
			
			path = new File(file, "windows/92/chromedriver.exe");
			
		} else if (os.contains("Windows") && !browser.equals("desktop")) {
			
			path = new File(file, "windows/101/chromedriver.exe");
		}
		
		if (os.contains("Mac") && browser.equals("desktop")) {
			
			path = new File(file, "mac/92/chromedriver");
			
		} else if (os.contains("Mac") && !browser.equals("desktop")) {
			
			path = new File(file, "mac/101/chromedriver.exe");
		}
		if (chromeService == null) {

			chromeService = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File(path.getAbsolutePath()))
					.usingAnyFreePort()
					.build();
		}
		return chromeService;
	}
	
	public WebDriver getChromeDriver() {
		
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", "src/test/resources/windows/103/chromedriver.exe");
		driver = new ChromeDriver(options);
		return driver;
	}
	
	// This one for KidCheck client
	public WebDriver getDesktopDriver() {
		String os = System.getProperty("os.name");
		ChromeOptions options = new ChromeOptions();
		if (os.contains("Windows")) {
			
			options.setBinary("C:\\Program Files (x86)\\KidCheck Client\\KidCheckClient.exe");
		}
		if (os.contains("Mac")) {

			options.setBinary("/Applications/KidCheckClient.app/Contents/MacOS/KidCheckClient");
		}
		
		options.addArguments("remote-debugging-port=13776");
		//driver = new RemoteWebDriver(chromeService.getUrl(), options);
		if (os.contains("Windows")) {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/windows/92/chromedriver.exe");
		}
		if (os.contains("Mac")) {

			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		}
		driver = new ChromeDriver(options);
		return driver;
	}
	
	public WebElement getElement(WebDriver driver, By by) {		
		return driver.findElement(by);
	}
	
	public List<WebElement> getElements(WebDriver driver, By by) {		
		return driver.findElements(by);
	}
	
	public void stopChromeService() {		
		chromeService.stop();
		chromeService = null;
	}
	
	public int getValuePositionInList(List<WebElement> element, String value) {		
		//List<WebElement> element = element; 
		int index = 0;
		for (int i = 0; i < element.size(); i++) {
			if (element.get(i).getText().trim().toLowerCase().contains(value.toLowerCase())) {				
				index = i;
				break;
			}
		}		
		return index;
	}
	
	public int getValuePositionInGetAttributes(List<WebElement> element, String value) {		
		//List<WebElement> element = element; 
		int index = 0;
		for (int i = 0; i < element.size(); i++) {			
			if (element.get(i).getAttribute("value").trim().contains(value)) {				
				index = i;
				break;
			}
		}		
		return index;
	}
	
	public boolean isValuePresentInList(List<WebElement> element, String value) {		
		boolean flag = false;
		for (int i = 0; i < element.size(); i++) {
			System.out.println("actual : " + element.get(i).getText().trim().toLowerCase());
			System.out.println(value.toLowerCase());
			if (element.get(i).getText().trim().toLowerCase().contains(value.toLowerCase())) {
				System.out.println("****** in");
				flag = true;
				break;
			}
		}		
		return flag;
	}
}
