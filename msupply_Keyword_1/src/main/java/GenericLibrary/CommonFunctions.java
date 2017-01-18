package GenericLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonFunctions 
{
   static String currentPath = System.getProperty("user.dir");
   public static int yPosition;

   public static int get_SelectedDiscount_CheckBoxNumber(int Dicountnumber) throws Throwable
   {
	   if(Dicountnumber<=10)
		   return 1;
	   if(Dicountnumber>=10&&Dicountnumber<=20)
		   return 2;
	   if(Dicountnumber>=20&&Dicountnumber<=30)
			return 3;
	   if(Dicountnumber>=30&&Dicountnumber<=40)
			return 4;
	   if(Dicountnumber>=40&&Dicountnumber<=50)
			return 5;
	   if(Dicountnumber>=50)
			return 6;
	   
	   return 0; 	   
   }
   
   public static String extractStringBetweenString(String value,String Start,String End)
   {
	   Pattern pattern = Pattern.compile(Start+"(.*?)"+End);
	   Matcher matcher = pattern.matcher(value);
	   while (matcher.find()) 
	   {
	        return matcher.group(1);
	   }
	   return null;
   }
   
   public static int getNumber(String str)
	{
		StringBuilder myNumbers = new StringBuilder();
		for (int i = 0; i < str.length(); i++)
		{
		    if (Character.isDigit(str.charAt(i)))
		       {
		           myNumbers.append(str.charAt(i));
		       }
		}
		String Numbers=myNumbers.toString();
		int no=Integer.parseInt(Numbers);
		return no;
	}
   
   
   public static String readPropertiesFile(String Key)
   {
	   String filePath = currentPath +"/src/test/resources/ExecutionEnviromentParameterizing.properties";
	   String Value=null;
	   
	   try
	   {	     
	     Properties prop=new Properties();
	     File file=new File(filePath);
	     FileInputStream fis=new FileInputStream(file);
	     prop.load(fis);
	     Value=prop.getProperty(Key);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	     
	   return Value;	   
   }
 
   
  //Scroll WebPage Up	
    public static void scrollPageUp(WebDriver driver,int x, int y) 
    {
   	 JavascriptExecutor jse2 = (JavascriptExecutor)(driver);
     jse2.executeScript("window.scrollBy("+x+","+y+")","");
	}
    
   //Scroll Down WebPage
	public static void scrollDownPage(WebDriver driver,int x,int y)
	{
		 JavascriptExecutor jse2 = (JavascriptExecutor)(driver);
	     jse2.executeScript("window.scrollBy("+x+","+y+")","");
	}
	public static void SwitchtoWebPage(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	//Read Values from Excel File	
			public static String getElementFromExcel(String Key) throws Throwable
			{
				String filePath = currentPath +"/src/test/resources/Locators.xlsx";				
				File file = new File(filePath);
				FileInputStream fis = new FileInputStream(file);
				Workbook wb = WorkbookFactory.create(fis);
				Sheet sht =  wb.getSheet("Data");
				int LastRowExcel = sht.getLastRowNum();
				String LocatorValue = null;
				    for(int i=1;i<=LastRowExcel;i++)
					{				
						if(sht.getRow(i).getCell(0).getStringCellValue().equals(Key))
						{								
							LocatorValue=sht.getRow(i).getCell(2).getStringCellValue();
							break;
						}
						//System.out.println(LocatorValue=sht.getRow(i).getCell(2).getStringCellValue());
					}
				return LocatorValue;
			}
			
			
	 //Read Locators from Excel File	
		public static Object ConstructElementFromExcel(WebDriver driver,String Key) throws Throwable
		{
			String filePath = currentPath +"/src/test/resources/Locators.xlsx";
			WebElement element=null;
			List<WebElement> elements=null;
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sht =  wb.getSheet("Data");
			int LastRowExcel = sht.getLastRowNum();
				
			    for(int i=1;i<=LastRowExcel;i++)
				{				
					if(sht.getRow(i).getCell(0).getStringCellValue().equals(Key))
					{								
						String LocatorType=sht.getRow(i).getCell(1).getStringCellValue();					
						String LocatorValue=sht.getRow(i).getCell(2).getStringCellValue();
						if(LocatorType.equals("ID"))
						{
							element=LoadLocators.loadElementByID(driver,LocatorValue);
							return element;
						}
						if(LocatorType.equals("NAME"))
						{
							element=LoadLocators.loadElementByName(driver,LocatorValue);
							return element;
						}
						if(LocatorType.equals("CLASSNAME"))
						{
							element=LoadLocators.loadElementByClassName(driver,LocatorValue);
							return element;
						}
						if(LocatorType.equals("TAGNAME"))
						{
							element=LoadLocators.loadElementByTagName(driver,LocatorValue);
							return element;
						}
						if(LocatorType.equals("LINKTEXT"))
						{
							element=LoadLocators.loadElementByLinkText(driver,LocatorValue);
							return element;
						}
						if(LocatorType.equals("PARTIALLINKTEXT"))
						{
							element=LoadLocators.loadElementByPartialLinkText(driver,LocatorValue);
							return element;
						}
						if(LocatorType.equals("XPATH"))
						{
							element=LoadLocators.loadElementByXpath(driver,LocatorValue);
							return element;
						}
						if(LocatorType.equals("XPATH_S"))
						{
							elements=LoadLocators.loadElementByXpath_findElements(driver,LocatorValue);
							return elements;
						}
						if(LocatorType.equals("CSSSELECTOR"))
						{
							 element=LoadLocators.loadElementByCssSelector(driver,LocatorValue);
							 return element;
						}
						else
						{
							//WebDriverCommonFunctions.PrintinLogAndHTMLReports("=============Not A Valid Locator Given in the Excel==========");
					       throw new Exception();
						}						
					}
				}
				return element;
			
		}

	//Wait for Page to Load using Explicit Wait
	public static void LoadPageExpicitWait(WebDriver driver) throws Throwable 
	{
		JavascriptExecutor js = (JavascriptExecutor)(driver);
		
		for (int i=0; i<25; i++)
		{ 
			
				Thread.sleep(1000); 
		    	if (js.executeScript("return document.readyState").toString().equals("complete"))
					break;
	   }
		
	}


	public static void SearchForElement(WebDriver driver,WebElement element) throws Throwable 
	{		  
		  yPosition=0;
		  
		  for (int i=0; i<10; i++)
		  { 
			  try 
			    {
				    Thread.sleep(5000);
				    (driver).switchTo().defaultContent();
				    element.getLocation();
				    //System.out.println(element.getText());
				    //WebDriverCommonFunctions.PrintinLogAndHTMLReports("Element Found");
				    break;
			    } 
			    catch (Exception e)
			    {			       
			       JavascriptExecutor jse2 = (JavascriptExecutor)(driver);
			       jse2.executeScript("window.scrollBy(0,"+yPosition+")","");
			       e.toString();
			       //WebDriverCommonFunctions.PrintinLogAndHTMLReports("Finding Element"+e.getMessage());
			       yPosition=yPosition+100;
			     }  
		  }          
	}
	
	
	public static void SearchForElement_Method_2(WebDriver driver,WebElement element,String Action) throws Throwable 
	{		  
		 boolean selected=true;		
		  int y=50;
		  while(selected)
		  {
		      try
		      {
		    	  CommonFunctions.scrollDownPage(driver,0,y);
		    	  if(Action.equals("GET_LOCATION"))
		              element.getLocation();
		    	  if(Action.equals("CLICK"))
		              element.click();
		    	  if(Action.equals("isDisplayed"))
		              System.out.println(element.isDisplayed());
		          selected=false;
		      }
		      catch(Exception e)
		      {
		    	  CommonFunctions.scrollPageUp(driver,0,-1000);
		    	  y=y+50;
		    	  //WebDriverCommonFunctions.PrintinLogAndHTMLReports("Search for Element");
		      }
		  }
		         
	}
	


	public static void SearchForElements(WebDriver driver,List<WebElement> element) 
	{
	
		int yPosition=100;
		for (int i=0; i<10; i++)
		{ 
			  try 
			    {
				    Thread.sleep(5000);
				    (driver).switchTo().defaultContent();				    
				    element.get(0).getLocation();
				    //System.out.println(element.getText());
				    //WebDriverCommonFunctions.PrintinLogAndHTMLReports("Element Found");
				    break;
			    } 
			    catch (Exception e)
			    {			       
			       JavascriptExecutor jse2 = (JavascriptExecutor)(driver);
			       jse2.executeScript("window.scrollBy(0,"+yPosition+")","");
			       e.toString();
			       //WebDriverCommonFunctions.PrintinLogAndHTMLReports("Finding Element"+e.getMessage());
			       yPosition=yPosition+100;
			     }  
		  } 
		
		
	}
	
	

	
	public static void CheckifStringContainsNumbers(WebDriver driver,ArrayList<String> StringToTest,String Message) throws Throwable 
	{		
		for(int i=0;i<=StringToTest.size()-1;i++)
		{	
			int j;
			for (j = 0; j < StringToTest.get(i).length()-1; j++)
			{
			    if (Character.isDigit(StringToTest.get(i).charAt(j)))
			    {			    	
			    	break;
			    }
			}
			if(j==StringToTest.get(i).length())
			{
				//WebDriverCommonFunctions.Print_WithException_inLogAndHTMLReports("Number Not Present in"+""+j);
			} 
			else
			{
				//WebDriverCommonFunctions.PrintinLogAndHTMLReports("Number Present in"+Message+""+j);
			}
		}
		
		
	}
	
	

	
	
	
}
