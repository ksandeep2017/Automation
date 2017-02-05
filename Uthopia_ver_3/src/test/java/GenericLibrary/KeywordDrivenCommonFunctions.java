package GenericLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;


public class KeywordDrivenCommonFunctions 
{
	
	
	public void updateTestCaseStatus() throws Throwable
	{
		
		String currentPath = System.getProperty("user.dir");		
		String filePath = currentPath +"/src/test/resources/DataEngine.xlsx";
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sht =  wb.getSheet("Scenario");
		int LastRowExcel = sht.getLastRowNum();
		
		 for(int i=1;i<=LastRowExcel;i++)
		 {
			       sht =  wb.getSheet("Scenario");
			        String TestCase=sht.getRow(i).getCell(0).getStringCellValue();		   
			        sht.getRow(i).getCell(3).setCellValue("Pending");
					FileOutputStream fos=new FileOutputStream(file);
					wb.write(fos);
					
					
				        
					sht =  wb.getSheet(TestCase);
					int LastRowExcel2 = sht.getLastRowNum();				 
										
					for(int j=1;j<=LastRowExcel2;j++)
					{
						
						    sht.getRow(j).getCell(5).setCellValue("Pending");
						    FileOutputStream fos1=new FileOutputStream(file);
							wb.write(fos1);
												
					}
			 			 
					
				
		 }		
	}
	public void readDataEngine(WebDriver driver) throws Throwable
	{
        String currentPath = System.getProperty("user.dir");		
		String TestControllerfilePath = currentPath +"/src/test/resources/TestController.xlsx";
		File TestControllerfile = new File(TestControllerfilePath);
		FileInputStream TestControllerfis = new FileInputStream(TestControllerfile);
		Workbook TestControllerwb = WorkbookFactory.create(TestControllerfis);
		Sheet TestControllersht = TestControllerwb.getSheet("Modules");
		int TestControllerLastRowExcel = TestControllersht.getLastRowNum();
		
		 for(int i=1;i<=TestControllerLastRowExcel;i++)
		 {
			 String TestControllerTestCaseID=TestControllersht.getRow(i).getCell(1).getStringCellValue();
			 String TestControllerRunMode=TestControllersht.getRow(i).getCell(4).getStringCellValue();
			 String TestControllerBrowserType=TestControllersht.getRow(i).getCell(6).getStringCellValue();
			 String TestControllerModuleName=TestControllersht.getRow(i).getCell(2).getStringCellValue();
			 
			 //When no Scripts there to execute
			 if(TestControllerRunMode.equals("NO"))
			 {
				 break;
			 }
			 if(TestControllerBrowserType.equals("FIREFOX"))
			 {

				  driver=new BrowserSelection().selectBrowserDriver("FIREFOX");
				  driver.manage().window().maximize();
			      driver.manage().deleteAllCookies();
			      driver.get("javascript:localStorage.clear()");
			      driver.get("http://40.117.184.175/UtopiaAdvisor/login.html");

			 }
			 if(TestControllerBrowserType.equals("CHROME"))
			 {

				  driver=new BrowserSelection().selectBrowserDriver("CHROME");
				  driver.manage().window().maximize();
			      driver.manage().deleteAllCookies();				
			      driver.get("http://40.117.184.175/UtopiaAdvisor/login.html");

			 }
			 
			 //http://selenium-release.storage.googleapis.com/index.html?path=2.50/
			 if(TestControllerBrowserType.equals("IE"))
			 {
				  driver=new BrowserSelection().selectBrowserDriver("IE");
				  driver.manage().window().maximize();
			      driver.manage().deleteAllCookies();
			      driver.get("http://40.117.184.175/UtopiaAdvisor/login.html");
			 }
			 
			 if(TestControllerRunMode.equals("YES"))
			 {				   
				    		
					String TestScriptsfilePath = currentPath +"/src/test/resources/TestScripts.xlsx";
					File TestScriptsfile = new File(TestScriptsfilePath);
					FileInputStream TestScriptsfis = new FileInputStream(TestScriptsfile);
					Workbook TestScriptswb = WorkbookFactory.create(TestScriptsfis);
					Sheet TestScriptssht = TestScriptswb.getSheet(TestControllerModuleName);
					int TestScriptsLastRowExcel = TestScriptssht.getLastRowNum();
					
					for(int j=1;j<=TestScriptsLastRowExcel;j++)
					{		
					     String TestScriptsFunctionName=TestScriptssht.getRow(j).getCell(3).getStringCellValue();
					     String TestScriptsExecute=TestScriptssht.getRow(j).getCell(4).getStringCellValue();
					     String TestScriptsParameter1=TestScriptssht.getRow(j).getCell(5).getStringCellValue();
					     String TestScriptsParameter2=TestScriptssht.getRow(j).getCell(6).getStringCellValue();
					     String TestScriptsParameter3=TestScriptssht.getRow(j).getCell(7).getStringCellValue();
					     String TestScriptsParameter4=TestScriptssht.getRow(j).getCell(8).getStringCellValue();
					     String TestScriptsParameter5=TestScriptssht.getRow(j).getCell(9).getStringCellValue();
					     
					     
					     if(TestScriptsExecute.equals("YES"))
						 {				   
					              	 
					    	    String KeywordLibraryfilePath = currentPath +"/src/test/resources/KeywordLibrary.xlsx";
								File KeywordLibraryfile = new File(KeywordLibraryfilePath);
								FileInputStream KeywordLibraryfis = new FileInputStream(KeywordLibraryfile);
								Workbook KeywordLibrarywb = WorkbookFactory.create(KeywordLibraryfis);
								Sheet KeywordLibrarysht = KeywordLibrarywb.getSheet(TestScriptsFunctionName);
								int KeywordLibraryLastRowExcel = KeywordLibrarysht.getLastRowNum();
					
													
								for(int k=1;k<=KeywordLibraryLastRowExcel;k++)
								{
									String KeywordLibraryFunctionName=KeywordLibrarysht.getRow(k).getCell(0).getStringCellValue();
									String KeywordLibraryActionofKeyword=KeywordLibrarysht.getRow(k).getCell(2).getStringCellValue();
									String KeywordLibraryObjectLogicalName=KeywordLibrarysht.getRow(k).getCell(3).getStringCellValue();
									String KeywordLibraryExecute=KeywordLibrarysht.getRow(k).getCell(4).getStringCellValue();
									String KeywordLibraryParameter1=KeywordLibrarysht.getRow(k).getCell(5).getStringCellValue();
									String TestDataParameter = null;
									
								    if(KeywordLibraryExecute.equals("YES"))
								    {
								    	
								    	//Get the Parameters from TestScripts to Keyword file
								    	
								    	if(!KeywordLibraryParameter1.equals("NA")&&k==1)
								    		KeywordLibraryParameter1=TestScriptsParameter1;
								    	if(!KeywordLibraryParameter1.equals("NA")&&k==2)
								    		KeywordLibraryParameter1=TestScriptsParameter2;
								    	if(!KeywordLibraryParameter1.equals("NA")&&k==3)
								    		KeywordLibraryParameter1=TestScriptsParameter3;
								    	if(!KeywordLibraryParameter1.equals("NA")&&k==4)
								    		KeywordLibraryParameter1=TestScriptsParameter4;
								    	if(!KeywordLibraryParameter1.equals("NA")&&k==5)
								    		KeywordLibraryParameter1=TestScriptsParameter5;
					
								if (!KeywordLibraryParameter1.equals("NA")) 
								{
									String TestDatafilePath = currentPath + "/src/test/resources/TestData.xlsx";
									File TestDatafile = new File(TestDatafilePath);
									FileInputStream TestDatafis = new FileInputStream(TestDatafile);
									Workbook TestDatawb = WorkbookFactory.create(TestDatafis);
									Sheet TestDatasht = TestDatawb.getSheet(KeywordLibraryFunctionName);
									int TestDataRowLastExcel = TestDatasht.getLastRowNum();

									

									for (int p = 1; p <= TestDataRowLastExcel; p++) 
									{
										for (int r = 0; r < TestDatasht.getRow(p).getLastCellNum(); r++) 
										{
											String TestCase = TestDatasht.getRow(0).getCell(r).getStringCellValue();

											if (TestCase.equals(KeywordLibraryParameter1)) 
											{
												TestDataParameter = TestDatasht.getRow(p).getCell(r).getStringCellValue();

												break;
											}
										}
									}
									
								 }								    		
							 }
								
							//Perform Actions
								    
							if(KeywordLibraryActionofKeyword.equals("CLICK"))
							  	   clickElement(driver,KeywordLibraryObjectLogicalName);
							
							if(KeywordLibraryActionofKeyword.equals("ENTERTEXT"))
								element_EnterValuesToTextField(driver,KeywordLibraryObjectLogicalName,TestDataParameter);	    
								    
								    
						}
				    }
				}
			 }
			 
			 //driver.quit();
		 }
	}
	

	//==============================================================================================================================================
	
	public static void element_EnterValuesToTextField(WebDriver driver,String ObjectKey,String TextFieldValue) throws Throwable
	{   
		CommonFunctions.LoadPageExpicitWait(driver); //Common functions-1
		WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(driver,ObjectKey);//Common functions-2
		CommonFunctions.scrollPageUp(driver,0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(driver,element);//Common functions-4
		
		element.clear();
		element.sendKeys(TextFieldValue);
		
		
		ExtentReportGeneration.getScreenShot(driver);
		
		CommonFunctions.LoadPageExpicitWait(driver); //Common functions-5
		CommonFunctions.scrollPageUp(driver,0,-1000); //Common functions-6
	}
	
	//==============================================================================================================================================

	private static void clickElement(WebDriver driver,String ObjectKey) throws Throwable 
	{
	     
		CommonFunctions.LoadPageExpicitWait(driver); //Common functions-1
		WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(driver,ObjectKey);//Common functions-2
		CommonFunctions.scrollPageUp(driver,0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(driver,element);//Common functions-4
		
		element.click();
		
		ExtentReportGeneration.getScreenShot(driver);
		
		CommonFunctions.LoadPageExpicitWait(driver); //Common functions-5
		CommonFunctions.scrollPageUp(driver,0,-1000); //Common functions-6
		
	}
	

}
