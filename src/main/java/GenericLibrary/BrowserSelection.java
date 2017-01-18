package GenericLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserSelection 
{
	
	public   WebDriver driver;
	static String currentPath = System.getProperty("user.dir");
	
	public WebDriver selectBrowserDriver(String browser)
	{
		if (browser.equalsIgnoreCase("chrome")) 
		{
			String filePath = currentPath +"/src/test/resources/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", filePath);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches","--disable-extensions");
			System.setProperty("webdriver.chrome.driver",filePath);
			
			driver = new ChromeDriver(options);
			
		} 
		else if (browser.equalsIgnoreCase("ie")) 
		{
			String filePath = currentPath +"/src/test/resources/IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", filePath);
			driver = new InternetExplorerDriver();
			
		} 
		if(browser.equalsIgnoreCase("firefox"))
		{			
			driver = new FirefoxDriver();
		}
		return driver;
	}
}

