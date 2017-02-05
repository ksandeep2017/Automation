package GenericLibrary;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportGeneration 
{
	public static ExtentReports er;
	public static  ExtentTest et;
	
	public static void reportStart() 
	{		
					
		//Location where report has to be generated
		er= new ExtentReports(System.getProperty("user.dir")+"/extent.html",true);
		er.addSystemInfo("First", "Sandeep");
		er.addSystemInfo("LastName", "Krishnappa");
		//Start the testCase
		et=er.startTest("HomePage");
		
	}
	public static void reportend() 
	{	
		er.endTest(et);
		er.flush();
		er.close();
		
	}
	
	public static void getScreenShot(WebDriver driver)
	{
		       //ScreenShot : http://automationtesting.in/capture-screenshot-in-extent-reports-java/
				TakesScreenshot screen=(TakesScreenshot)driver;
				File srcimg=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File destimg=new File(System.getProperty("user.dir")+"/ABC"+".png");
				try 
				   {
					   FileUtils.copyFile(srcimg, destimg);
				   } 
				   catch (IOException e) 
				   {
					  e.printStackTrace();
				   }
					
				ExtentReportGeneration.et.log(LogStatus.PASS, ExtentReportGeneration.et.addScreenCapture(System.getProperty("user.dir")+"/ABC"+".png"));

	}

}
