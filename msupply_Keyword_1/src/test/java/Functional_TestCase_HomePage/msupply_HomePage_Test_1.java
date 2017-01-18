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
import GenericLibrary.CommonFunctions;
import GenericLibrary.KeywordDrivenCommonFunctions;
import Test.BaseTest;

public class msupply_HomePage_Test_1 extends BaseTest 
{
	@Test
	public void msupply_HomePage() throws Throwable
	{		
		WebDriver driver=getDriver();
		driver.get("http://stg.msupply.com");
		KeywordDrivenCommonFunctions.updateTestCaseStatus();
		KeywordDrivenCommonFunctions.readDataEngine(driver);
		
	}

	
	
}
