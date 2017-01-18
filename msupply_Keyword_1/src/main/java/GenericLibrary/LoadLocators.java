package GenericLibrary;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoadLocators 
{
	public static WebElement loadElementByID(WebDriver driver,String Element) throws Throwable
	{
		WebElement element=(driver).findElement(By.id(Element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByName(WebDriver driver,String Element) throws Throwable
	{
		WebElement element=(driver).findElement(By.name(Element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByClassName(WebDriver driver,String Element) throws Throwable
	{
		WebElement element=(driver).findElement(By.className(Element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByTagName(WebDriver driver,String Element) throws Throwable
	{
		WebElement element=(driver).findElement(By.tagName(Element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByLinkText(WebDriver driver,String Element) throws Throwable
	{
		WebElement element=(driver).findElement(By.linkText(Element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByPartialLinkText(WebDriver driver,String Element) throws Throwable
	{
		WebElement element=(driver).findElement(By.partialLinkText(Element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByXpath(WebDriver driver,String Element) throws Throwable
	{
		WebElement element=(driver).findElement(By.xpath(Element));
		((JavascriptExecutor)(driver)).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}

	public static List<WebElement> loadElementByXpath_findElements(WebDriver driver,String Element) throws Throwable
	{
		List<WebElement> element=(driver).findElements(By.xpath(Element));
		return element;
	}
	
	public static WebElement loadElementByCssSelector(WebDriver driver,String Element) throws Throwable
	{
		WebElement element=(driver).findElement(By.cssSelector(Element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}	
	
}
