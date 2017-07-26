package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait_for_element {

	public void waits_by_css(WebDriver driver, String css) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));

	}

	public void waits_by_id(WebDriver driver, String id) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));

	}
	
	
	public void waits_by_id_contains_something(WebDriver driver, String id) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.attributeToBeNotEmpty((driver.findElement(By.id(id))),"value"));

	}
	
	public void waits_by_xpath(WebDriver driver, String id) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));

	}
	
	public void waits_by_name(WebDriver driver, String name) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));

	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("os.name"));
	}

	public void waits_by_linktext(WebDriver driver, String string) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(string)));
		
	}

}
