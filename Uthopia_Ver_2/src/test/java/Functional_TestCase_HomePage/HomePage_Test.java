package Functional_TestCase_HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericLibrary.BrowserSelection;
import GenericLibrary.CommonFunctions;
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
		
		//log = LogReports.writeLog(BaseTest.class);
		KeywordDrivenCommonFunctions key=new KeywordDrivenCommonFunctions();
		key.updateTestCaseStatus();
		key.readDataEngine(driver);
		
	}
	
}
