package Readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SpecReader {

	public static void main(String agrs[]) throws IOException {

		File f = new File("resource/data.spec");

		BufferedReader b = new BufferedReader(new FileReader(f));

		String readLine = "";
		boolean flag = false;

		String arrsplit[], temp[] = null;
		while ((readLine = b.readLine()) != null) {

			arrsplit = readLine.split(" ");
			// if(arrsplit.length==3)
			{
				System.out.println(arrsplit[0] + arrsplit[1] + arrsplit[2]);
			}

		}

	}

	public String readit(String Key, String type) throws IOException {
		File file;
		OptionReader objread = new OptionReader();

		String resFile = null;
		resFile = objread.optionFileReader("currentpath") + "/Spec_Files/data.spec";

		// if (type.equals("ids")) {
		// resFile =
		// objread.optionFileReader("currentpath")+"/Excel_Files/ids.ods";
		// } else if (type.equals("locators")) {
		// resFile =
		// objread.optionFileReader("currentpath")+"/Excel_Files/locators.ods";
		// } else if (type.equals("password")) {
		// resFile =
		// objread.optionFileReader("currentpath")+"/Excel_Files/passwords.ods";
		// } else if (type.equals("urls")) {
		// resFile =
		// objread.optionFileReader("currentpath")+"/Excel_Files/urls.ods";
		// }

		file = new File(resFile);

		BufferedReader read = new BufferedReader(new FileReader(file));

		String readLine = "";

		String arrsplit[], temp[] = null;
		while ((readLine = read.readLine()) != null) {

			arrsplit = readLine.split(" +");
			if (arrsplit.length >= 3 && readLine.contains(Key) && readLine.contains(type)) {

				return arrsplit[2];

			}

		}

		return null;
	}

}
