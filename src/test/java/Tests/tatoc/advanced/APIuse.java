package Tests.tatoc.advanced;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import utility.Datadecider;
import utility.InitWebdriver;
import utility.PathSetter;
import utility.Wait_for_element;
import utility.WebElementUse;

public class APIuse {

	WebDriver driver;
	WebElementUse useElements;
	Wait_for_element waitElements;
	Datadecider data;
	String url;

	public APIuse() throws IOException {

		
		useElements = new WebElementUse();
		waitElements = new Wait_for_element();
		data = new Datadecider();
		new PathSetter().setPath("resource/TotacTest/Advanced/");
		url = data.readit("base_url", "urls");

	}

	public String gettoken(String session) throws Exception {

		URL url = new URL(this.url + data.readit("generate_token", "urls") + session);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

		String line = br.readLine();

		JSONParser parser = new JSONParser();
		String token = ((JSONObject) parser.parse(line.toString())).get("token").toString();
		System.err.println(line);
		return token;
	}

	public void register(String session, String token) throws Exception {

		URL url = new URL(this.url + data.readit("register_service", "urls")+"?signature="+token+"&id="+session+"&allow_access=1");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");

		connection.setRequestProperty("Content-Type", "application/json");
		connection.setFixedLengthStreamingMode(0);
		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

//		String line = br.readLine();
//		while (line != null) {
//
//			line = br.readLine();
//			System.out.println(line);
//		}

		
	}

}
