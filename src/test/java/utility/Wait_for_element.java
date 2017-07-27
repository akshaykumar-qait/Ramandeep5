package utility;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Wait_for_element {

	public void waits_by(WebDriver driver, String value) throws IOException {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(new Wait_for_element().Getbys(value)));

	}

	public void waits_contains_something(WebDriver driver, String value) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.attributeToBeNotEmpty((driver.findElement(new Wait_for_element().Getbys(value))), "value"));

	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("os.name"));
	}

	By Getbys(String value) throws IOException {

		System.out.println("value is " + value);

		value.toLowerCase();
		String value2[] = value.split("_");

		if (value2[1].equals("id")) {
			return By.id(value2[0]);
		} else if (value2[1].equals("css")) {
			return By.cssSelector(value2[0]);

		} else if (value2[1].equals("name")) {
			return By.name(value2[0]);

		} else if (value2[1].equals("xpath")) {
			return By.xpath(value2[0]);

		} else if (value2[1].equals("linktext")) {
			return By.linkText(value2[0]);

		}

		return null;

	}

}
