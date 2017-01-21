package GenericLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
		String filePath = currentPath +"/src/test/resources/DataEngine.xlsx";
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sht2 =  wb.getSheet("Scenario");
		int LastRowExcel = sht2.getLastRowNum();
		
		 for(int i=1;i<=LastRowExcel;i++)
		 {
			 Sheet sht =  wb.getSheet("Scenario");
			 String BrowserType=sht.getRow(i).getCell(4).getStringCellValue();

			 if(BrowserType.equals("FIREFOX"))
			 {

				  driver=new BrowserSelection().selectBrowserDriver("FIREFOX");
				  driver.manage().window().maximize();
			      driver.manage().deleteAllCookies();
			      driver.get("javascript:localStorage.clear()");
			      driver.get("http://40.117.184.175/UtopiaAdvisor/login.html");

			 }
			 if(BrowserType.equals("CHROME"))
			 {

				  driver=new BrowserSelection().selectBrowserDriver("CHROME");
				  driver.manage().window().maximize();
			      driver.manage().deleteAllCookies();				
			      driver.get("http://40.117.184.175/UtopiaAdvisor/login.html");

			 }
			 
			 //http://selenium-release.storage.googleapis.com/index.html?path=2.50/
			 if(BrowserType.equals("IE"))
			 {
				  driver=new BrowserSelection().selectBrowserDriver("IE");
				  driver.manage().window().maximize();
			      driver.manage().deleteAllCookies();
			      driver.get("http://40.117.184.175/UtopiaAdvisor/login.html");
			 }

			 sht =  wb.getSheet("Scenario");		 
			 
			 String TestCase=sht.getRow(i).getCell(0).getStringCellValue();
			 String RunMode=sht.getRow(i).getCell(2).getStringCellValue();
			 
			 if(RunMode.equals("Yes"))
			 {
				    sht.getRow(i).getCell(3).setCellValue("+Running+");
					FileOutputStream fosw=new FileOutputStream(file);
					wb.write(fosw);
				 
				    
					sht =  wb.getSheet(TestCase);
					 int LastRowExcel2 = sht.getLastRowNum();				 
										
					for(int j=1;j<=LastRowExcel2;j++)
					{
						
						String Action=sht.getRow(j).getCell(3).getStringCellValue();
						String ObjectKey=sht.getRow(j).getCell(4).getStringCellValue();
						String TextFieldValue=sht.getRow(j).getCell(6).getStringCellValue();
						
						
						CellStyle style = wb.createCellStyle();
				        Font font = wb.createFont();
				        font.setColor(HSSFColor.GREEN.index);
				        style.setFont(font);
				        
						try
						{
						   if(Action.equals("Click"))
						   {
						       clickElement(driver,ObjectKey);
						       
						        sht.getRow(j).getCell(5).setCellStyle(style);
						        sht.getRow(j).getCell(5).setCellValue("Pass");
								FileOutputStream fosA=new FileOutputStream(file);
								wb.write(fosA);
								
						   }
						
						   if(Action.equals("EnterText"))
						   {
							   element_EnterValuesToTextField(driver,ObjectKey,TextFieldValue);
							   sht.getRow(j).getCell(5).setCellStyle(style);
						        sht.getRow(j).getCell(5).setCellValue("Pass");
								FileOutputStream fosB=new FileOutputStream(file);
								wb.write(fosB);
								//System.out.println("Written");
						   }
						}
						catch(Exception e)
						{
							font.setColor(HSSFColor.RED.index);
					        style.setFont(font);														
							sht.getRow(j).getCell(5).setCellStyle(style);
					        sht.getRow(j).getCell(5).setCellValue("Error");
							FileOutputStream fosC=new FileOutputStream(file);
							wb.write(fosC);
							
							 sht =  wb.getSheet("Scenario");		 
							 sht.getRow(i).getCell(3).setCellValue("Error");
							 FileOutputStream fosp=new FileOutputStream(file);
							 wb.write(fosp);
														
						}
						finally
						{
							sht =  wb.getSheet(TestCase);
						}
						
						
					}
			}			
			 driver.quit();
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
		
	}
	
	//==============================================================================================================================================

	private static void clickElement(WebDriver driver,String ObjectKey) throws Throwable 
	{
	     
		CommonFunctions.LoadPageExpicitWait(driver); //Common functions-1
		WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(driver,ObjectKey);//Common functions-2
		CommonFunctions.scrollPageUp(driver,0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(driver,element);//Common functions-4
		
		element.click();
		
		CommonFunctions.LoadPageExpicitWait(driver); //Common functions-5
		CommonFunctions.scrollPageUp(driver,0,-1000); //Common functions-6
		
	}
	

}
