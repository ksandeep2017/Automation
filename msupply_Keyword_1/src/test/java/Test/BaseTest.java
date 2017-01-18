package Test;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import GenericLibrary.BrowserSelection;
import GenericLibrary.LogReports;

public class BaseTest 
{
	    public  WebDriver driver;
	    public static org.apache.log4j.Logger log;
	    public  SoftAssert softAssert;
	    
	    public WebDriver getDriver() 
	    {
            return driver;
	    }
	    
	    @BeforeTest
	    public void beforeTestCofig(ITestContext context)
	    {
	    	/*Object initialization*/
			log = LogReports.writeLog(BaseTest.class);
			//String seleniumBrowser = context.getCurrentXmlTest().getParameter("BrowserType");
			driver=new BrowserSelection().selectBrowserDriver("firefox");
			driver.manage().window().maximize();
			softAssert = new SoftAssert();
			
			//Set all Scenarios to Pending
			
			
			
			
	    }
	   	    
	    @AfterTest
		public void tearDown() throws Throwable
		{
	    	softAssert.assertAll(); 
	    	
	    	
		}
	    
	    
}
