package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;

public class WebElementUse {
	
	
	
	
	public void webElement_open_url(WebDriver driver,String url)
	{
		driver.get(url);
	
	}
	
	public void webElement_click_by_id(WebDriver driver,String id)
	{
		driver.findElement(By.id(id)).click();
	}
	
	public void webElement_click_by_css(WebDriver driver,String css)
	{
	   driver.findElement(By.cssSelector(css)).click();;	
	}

	public void webElement_click_by_xpath(WebDriver driver,String xpath)
	{
	   driver.findElement(By.xpath(xpath)).click();
	}
	
	
	public void webElement_fill_by_id(WebDriver driver,String id,String keys)
	{
		
		
		driver.findElement(By.id(id)).sendKeys(keys);
	}
	
	
	public void webElement_fill_by_css(WebDriver driver,String id,String keys)
	{
		driver.findElement(By.cssSelector(id)).sendKeys(keys);
	}
	

	public void webElement_click_by_linkname(WebDriver driver, String linkText) {
		// TODO Auto-generated method stub
		driver.findElement(By.linkText(linkText)).click();

	}
	public String getText_by_id(WebDriver driver, String id)
	{
		return driver.findElement(By.id(id)).getText();
	}

	public String getText_by_css(WebDriver driver, String css) {
		// TODO Auto-generated method stub
		return driver.findElement(By.cssSelector(css)).getText();

	
	}
	
	
	

}
