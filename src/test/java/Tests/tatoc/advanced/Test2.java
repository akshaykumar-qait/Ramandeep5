package Tests.tatoc.advanced;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.Datadecider;
import utility.InitWebdriver;
import utility.PathSetter;
import utility.Wait_for_element;
import utility.WebElementUse;

public class Test2 {

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
		new PathSetter().setPath("resource/TotacTest/Advanced/");
		url = data.readit("base_url", "urls");

	}

	@Test
	public void TestA_on_hover_menu() throws IOException, InterruptedException {

	
		useElements.webElement_open_url(driver, url + data.readit("hover_menu", "urls"));
		
		
		waitElements.waits_by(driver, ".menutop.m2_css");
		
	    
		useElements.webElement_hover_move(driver, ".menutop.m2_css");
	
		waitElements.waits_by(driver, "//span[@class='menuitem'][text()='Go Next']_xpath");
		useElements.webElement_click(driver, "//span[@class='menuitem'][text()='Go Next']_xpath");

	}

	@Test(dependsOnMethods = "TestA_on_hover_menu")
	public void TestA_check_the_database() throws IOException, InterruptedException {

		useElements.webElement_open_url(driver, url + data.readit("query_gate", "urls"));
		String datais = useElements.getText(driver, "symboldisplay_id");
		datais = "'" + datais + "'";

		System.out.println(datais);
		MySqlT obj_sql = new MySqlT();
		String record[], record2[];

		record = obj_sql.getResult("symbol", datais, "identity", 2).split(",");
		System.out.println(record[0]);

		record2 = obj_sql.getResult("id", record[0], "credentials", 3).split(",");
		System.out.println(record2[1] + "  " + record2[2]);

		useElements.webElement_fill(driver, "name_id", record2[1]);
		useElements.webElement_fill(driver, "passkey_id", record2[2]);

		// Thread.sleep(3000);
		useElements.webElement_click(driver, "submit_id");
		// waitElements.waits_by_linktext(driver, "Proceed");
		assertThat(driver.getCurrentUrl()).isEqualTo(url + data.readit("vedio_player", "urls"));

	}

	@Test(dependsOnMethods = "TestA_check_the_database")
	public void TestA_check_after_vedio_playback() throws Exception {

		useElements.webElement_open_url(driver, url + data.readit("after_vedio", "urls"));
		APIuse api = new APIuse();
		String session[] = useElements.getText(driver, "session_id_id").split(":");
		String token = api.gettoken(session[1].trim());
		// System.out.println(token);

		api.register(session[1].trim(), token);
		useElements.webElement_click(driver, "Proceed_linktext");
		waitElements.waits_by(driver, "Download File_linktext");
		assertThat(driver.getCurrentUrl()).isEqualTo(url + data.readit("file_handle", "urls"));

	}

	@Test(dependsOnMethods = "TestA_check_after_vedio_playback")
	public void TestA_check_file_handle() throws Exception {

		useElements.webElement_click(driver, "Download File_linktext");

		Thread.sleep(4000);
		//waitElements.wait(4000);

		File f = new File("resource/downloads/file_handle_test.dat");

		BufferedReader b = new BufferedReader(new FileReader(f));

		String readLine = "";

		String arrsplit = null;
		while ((readLine = b.readLine()) != null) {

			arrsplit = arrsplit + readLine;

		}
		f.delete();

		String session[] = arrsplit.split(":");

		System.out.println(session[session.length - 1].trim());

		useElements.webElement_fill(driver, "signature_id", session[session.length - 1].trim());

		useElements.webElement_click(driver, ".submit_css");
		assertThat(driver.getCurrentUrl()).isEqualTo(data.readit("totac_complete", "urls"));

	}

}
