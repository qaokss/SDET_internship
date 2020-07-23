package ui.tests.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.tests.PageObjects.InboxPage;
import ui.tests.PageObjects.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SessionHelper {

    public static WebDriver wd;
    private static NavigationHelper navigationHelper;
    private static LoginPage loginPage;
    private static InboxPage inboxPage;
    private static Properties properties;


    public WebDriver initChromeWebDriver() {
        this.wd = new ChromeDriver();
        this.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return wd;
    }


    public static Properties initProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/config.properties"));
        return properties;
    }

    public static NavigationHelper goTo() {
        navigationHelper = new NavigationHelper(wd);
        return navigationHelper;
    }


    public static LoginPage lp() {
        loginPage = new LoginPage(wd);
        return loginPage;
    }

    public static InboxPage ip() {
        inboxPage = new InboxPage(wd);
        return inboxPage;
    }

}
