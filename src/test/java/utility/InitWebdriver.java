package utility;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import Readers.OptionReader;

public class InitWebdriver {

	protected WebDriver driver;

	public WebDriver Browserdecider() throws IOException {
		String machine_type = System.getProperty("os.name");
		String getbrowservalue = new OptionReader().optionFileReader("browser");
		if (getbrowservalue.equals("chrome")) {
			
			

			System.out.println("value   " + getbrowservalue + machine_type);

			if (machine_type.startsWith("Linux")) {
				System.setProperty("webdriver.chrome.driver", "resource/Linux_drivers/chromedriver");
			} else if (machine_type.startsWith("Windows")) {
				System.setProperty("webdriver.chrome.driver", "resource/Windows_drivers/chromedriver.exe");
			}
          	String downloadFilepath = "resource/downloads";
			HashMap chromePrefs = new HashMap();
			chromePrefs.put("profile.default_content_settings.popups", 0);	
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();		
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();	
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);	
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(cap);
			//driver = new ChromeDriver();
			

			return driver;
			
			
		} else if (getbrowservalue.equals("firefox")) {

			if (machine_type.startsWith("Linux")) {
				System.setProperty("webdriver.gecko.driver", "resource/Linux_drivers/geckodriver");
			} else if (machine_type.startsWith("Windows")) {
				System.setProperty("webdriver.gecko.driver", "resource/Windows_drivers/geckodriver.exe");
			}

			//driver = new FirefoxDriver();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderlist", 2);
			profile.setPreference("browser.helperapps.neverAsk.saveToDisk", ".dat");
			profile.setPreference("browser.download.dir", "resource/downloads");
			driver = new FirefoxDriver(profile);

			return driver;
		}

		return null;

	}

}
