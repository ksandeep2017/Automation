package GenericLibrary;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import Test.BaseTest;

public class Listener implements ITestListener 
{

	static String currentPath = System.getProperty("user.dir");
	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult test) 
	{
		 Object currentClass = test.getInstance();
	     WebDriver webDriver = ((BaseTest) currentClass).getDriver();
	     if (webDriver != null)
	     {  
	        
		   System.out.println(currentPath);
		   EventFiringWebDriver edriver=new EventFiringWebDriver(webDriver);
		   File srcimg=edriver.getScreenshotAs(OutputType.FILE);
		   File destimg=new File(currentPath +"//ScreenShots//"+test.getName()+".png");
		   try 
		   {
			   FileUtils.copyFile(srcimg, destimg);
		   } 
		   catch (IOException e) 
		   {
			   e.printStackTrace();
		   }
	     }
	}

	public void onTestSkipped(ITestResult test) 
	{
		 Object currentClass = test.getInstance();
	     WebDriver webDriver = ((BaseTest) currentClass).getDriver();
	     if (webDriver != null)
	     {  	
		   EventFiringWebDriver edriver=new EventFiringWebDriver(webDriver);
		   File srcimg=edriver.getScreenshotAs(OutputType.FILE);
		   File destimg=new File(currentPath +"//ScreenShots//"+test.getName()+".png");
		   try 
		   {
			   FileUtils.copyFile(srcimg, destimg);
		   } 
		   catch (IOException e) 
		   {
			  e.printStackTrace();
		   }
	     }
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult test) 
	{
		
		 Object currentClass = test.getInstance();
	     WebDriver webDriver = ((BaseTest) currentClass).getDriver();
	     if (webDriver != null)
	     {  	
		   EventFiringWebDriver edriver=new EventFiringWebDriver(webDriver);
		   File srcimg=edriver.getScreenshotAs(OutputType.FILE);
		   File destimg=new File(currentPath +"//ScreenShots//"+test.getName()+".png");
		   try 
		   {
			   FileUtils.copyFile(srcimg, destimg);
		   } 
		   catch (IOException e) 
		   {
			  e.printStackTrace();
		   }
	     }
	}
}

