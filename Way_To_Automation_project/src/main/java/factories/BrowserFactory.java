package factories;

import driver.DriverManager;
import enums.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.remote.Browser.CHROME;

public class BrowserFactory {
    protected static void initBrowser(BrowserType browserType) {
        switch (browserType) {

            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en");
                DriverManager.setDriver(new ChromeDriver(options));
                break;

        }
    }
}