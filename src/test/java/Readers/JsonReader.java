package Readers;

import java.io.FileReader;
import java.util.ArrayList;

import org.apache.bcel.generic.NEW;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import utility.PathSetter;

public class JsonReader {

	public String readit(String key, String type) {

		JSONParser parser = new JSONParser();
		String resFile = "";
		JSONObject mainJsonObj;

		try {

			// System.err.println("type is" + type);

			OptionReader readopt = new OptionReader();
			if (type.equals("ids")) {

				resFile = readopt.optionFileReader("currentpath") + "Json_Files/ids.json";
			} else if (type.equals("locators")) {
				resFile = readopt.optionFileReader("currentpath") + "Json_Files/locators.json";
			} else if (type.equals("password")) {
				resFile = readopt.optionFileReader("currentpath") + "Json_Files/passwords.json";
			} else if (type.equals("urls")) {
				resFile = readopt.optionFileReader("currentpath") + "Json_Files/urls.json";
			} else {
				resFile = readopt.optionFileReader("currentpath") + "Json_Files/" + type + ".json";

			}

			mainJsonObj = (JSONObject) parser.parse(new FileReader(resFile));

			JSONArray locator = (JSONArray) mainJsonObj.get(type.toLowerCase());
			JSONObject locators = (JSONObject) locator.get(0);
				return locators.get(key).toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	

}
