package Functional_TestCase_HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import GenericLibrary.BrowserSelection;
import GenericLibrary.CommonFunctions;
import GenericLibrary.ConnectOracle10gDB;
import GenericLibrary.ExtentReportGeneration;
import GenericLibrary.KeywordDrivenCommonFunctions;
import GenericLibrary.LogReports;
import Test.BaseTest;

public class HomePage_Test 
{
	public  WebDriver driver;
	public static org.apache.log4j.Logger log;
	 
	
	@Test
	public void HomePage() throws Throwable
	{		
		ExtentReportGeneration.reportStart();
		//ConnectOracle10gDB.displayDBName();

		//log = LogReports.writeLog(BaseTest.class);
		KeywordDrivenCommonFunctions key=new KeywordDrivenCommonFunctions();
		//key.updateTestCaseStatus();
		key.readDataEngine(driver);
			
		
		//Logging into the Report
		ExtentReportGeneration.et.log(LogStatus.PASS, "Step1");
		Reporter.log("Step1");
		ExtentReportGeneration.et.log(LogStatus.PASS, "Step2", "Entered Step2");
		Reporter.log("Entered Step2");
		
		
		ExtentReportGeneration.reportend();
	}
	
	
	
	
	
	
	
}
