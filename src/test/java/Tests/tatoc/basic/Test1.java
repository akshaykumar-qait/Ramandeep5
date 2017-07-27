package Tests.tatoc.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.Datadecider;
import utility.InitWebdriver;
import utility.PathSetter;
import utility.Wait_for_element;
import utility.WebElementUse;

public class Test1 {

	WebDriver driver;

	WebElementUse useElements;
	Wait_for_element waitElements;
	Datadecider data;
	String url;

	@BeforeTest
	public void Initializer() throws IOException {

		driver = new InitWebdriver().Browserdecider();
		useElements = new WebElementUse();
		waitElements = new Wait_for_element();
		data = new Datadecider();
		new PathSetter().setPath("resource/TotacTest/Basic/");
		url = data.readit("base_url", "urls");

	}

	@Test
	public void TestA_test_the_green_box_function() throws IOException, InterruptedException {

		// 10 time tested the same code because the position of the red box
		// changes randomly

		for (int times = 0; times < 10; times++) {
			useElements.webElement_open_url(driver, url + data.readit("grid_gate", "urls"));
			waitElements.waits_by(driver, data.readit("greenbox_css", "locators"));
			useElements.webElement_click(driver, data.readit("greenbox_css", "locators"));
			assertThat(driver.getCurrentUrl()).isEqualTo(url + data.readit("frame_dungeon", "urls"));
		}

	}

	@Test(dependsOnMethods = "TestA_test_the_green_box_function")
	public void TestB_test_the_red_box_function() throws IOException, InterruptedException {
		for (int row = 1; row <= 4; row++) {
			for (int col = 1; col <= 4; col++) {
				useElements.webElement_open_url(driver, url + data.readit("grid_gate", "urls"));
				String cell_xpath[] = data.readit("eachbox_xpath", "locators").toString().replaceAll("row", (row + ""))
						.replaceAll("col", col + "").split("_");

				System.out.println(cell_xpath);

				WebElement cell = driver.findElement(By.xpath(cell_xpath[0]));
				waitElements.waits_by(driver, cell_xpath[0] + "_xpath");

				System.out.println("hereeeeeee" + cell.getAttribute("class"));

				if (cell.getAttribute("class").equals("redbox")) {
					useElements.webElement_click(driver, cell_xpath[0] + "_xpath");
					assertThat(driver.getCurrentUrl()).isEqualTo(data.readit("error_url", "urls"));

				}
			}
		}
	}

	@Test(dependsOnMethods = "TestB_test_the_red_box_function")
	public void TestC_test_matching_for_the_two_colour_boxes() throws IOException, InterruptedException {

		Boolean flag = true;
		useElements.webElement_open_url(driver, url + data.readit("frame_dungeon", "urls"));

		driver.switchTo().frame("main");
		String Box1_col = driver.findElement(By.id("answer")).getAttribute("class");

		while (flag) {

			driver.switchTo().frame("child");
			String Box2_col = driver.findElement(By.id("answer")).getAttribute("class");

			if (Box1_col.equals(Box2_col)) {
				flag = false;
			} else {

				driver.switchTo().parentFrame();
				useElements.webElement_click(driver, "/html/body/center/a[1]_xpath");
			}
		}

		driver.switchTo().parentFrame();

		useElements.webElement_click(driver, "/html/body/center/a[2]_xpath");
		// waitElements.waits_by_id(driver, "dropbox");
		assertThat(driver.getCurrentUrl()).isEqualTo(url + data.readit("drag_url", "urls"));

	}

	@Test(dependsOnMethods = "TestC_test_matching_for_the_two_colour_boxes")
	public void TestD_test_the_wrong_drop_of_element() throws IOException, InterruptedException {

		useElements.webElement_open_url(driver, url + data.readit("drag_url", "urls"));

		WebElement From = driver.findElement(By.id("dragbox"));

		WebElement To = driver.findElement(By.cssSelector(".footer"));
		Actions builder = new Actions(driver);

		Action dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To).build();

		dragAndDrop.perform();
		// Thread.sleep(5000);

		useElements.webElement_click(driver, "Proceed_linktext");
		waitElements.waits_by(driver, ".error_css");
		assertThat(driver.getCurrentUrl()).isEqualTo(data.readit("error_url", "urls"));

	}

	@Test(dependsOnMethods = "TestD_test_the_wrong_drop_of_element")
	public void TestE_check_the_drag_and_drop_function() throws IOException, InterruptedException {

		useElements.webElement_open_url(driver, url + data.readit("drag_url", "urls"));

		WebElement From = driver.findElement(By.id("dragbox"));

		WebElement To = driver.findElement(By.id("dropbox"));
		Actions builder = new Actions(driver);

		Action dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To).build();

		dragAndDrop.perform();

		useElements.webElement_click(driver, "Proceed_linktext");
		waitElements.waits_by(driver, "Launch Popup Window_linktext");

		assertThat(driver.getCurrentUrl()).isEqualTo(url + data.readit("windows_url", "urls"));

	}

	@Test(dependsOnMethods = "TestE_check_the_drag_and_drop_function")
	public void TestF_test_the_form_filling() throws IOException, InterruptedException {

		useElements.webElement_open_url(driver, url + data.readit("windows_url", "urls"));

		waitElements.waits_by(driver, "Launch Popup Window_linktext");
		useElements.webElement_click(driver, "Launch Popup Window_linktext");

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// driver.switchTo().window(driver.getWindowHandle());

		driver.switchTo().window(tabs2.get(tabs2.size() - 1));

		System.out.println(tabs2.get(1));
		System.out.println("switched" + driver.getWindowHandles());

		waitElements.waits_by(driver, "name_id");
		useElements.webElement_fill(driver, "name_id", "nishant");
		driver.switchTo().window(tabs2.get(tabs2.size() - 1));
		useElements.webElement_click(driver, "submit_id");

		System.out.println("submitted");

		driver.switchTo().window(tabs2.get(0));
		System.out.println("final");
		waitElements.waits_by(driver, "Proceed_linktext");

		System.out.println("final");
		useElements.webElement_click(driver, "Proceed_linktext");

		waitElements.waits_by(driver, "Generate Token_linktext");

		assertThat(driver.getCurrentUrl()).isEqualTo(url + data.readit("cookie_url", "urls"));

	}

	@Test
	public void TestG_test_the_token_getting_cookie_setting() throws IOException, InterruptedException {

		useElements.webElement_open_url(driver, url + data.readit("cookie_url", "urls"));
		waitElements.waits_by(driver, "Generate Token_linktext");
		useElements.webElement_click(driver, "Generate Token_linktext");
		waitElements.waits_by(driver, "token_id");
		System.out.println(useElements.getText(driver, "token_id").replaceAll("Token:", "").trim());
		assertThat(useElements.getText(driver, "token_id").replaceAll("Token:", "").trim()).isNotEqualTo("");
		Cookie ck = new Cookie("Token", useElements.getText(driver, "token_id").replaceAll("Token:", "").trim());
		driver.manage().addCookie(ck);
		useElements.webElement_click(driver, "Proceed_linktext");
		waitElements.waits_by(driver, ".finish_css");

		assertThat(driver.getCurrentUrl()).isEqualTo(data.readit("finish_url", "urls"));
		System.out.println(useElements.getText(driver, "span.finish_css"));

		assertThat(useElements.getText(driver, "span.finish_css")).isEqualTo("Obstacle Course is Complete!");

	}

	@AfterClass
	public void Closer() throws IOException {

		driver.close();

	}

}
