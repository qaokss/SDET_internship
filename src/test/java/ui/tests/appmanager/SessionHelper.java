package ui.tests.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SessionHelper {

    public WebDriver wd;
    private NavigationHelper navigationHelper;
    private BaseHelper baseHelper;

    public void init() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("https://www.google.com/intl/ru/gmail/about/#");

    }
        public void login() {
            baseHelper.type(By.name("user"), "");
            baseHelper.type(By.name("pass"), "");
            baseHelper.click(By.xpath("//input[@value='Login']"));
            navigationHelper = new NavigationHelper(wd);

        }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public void stop() {
        wd.quit();
    }
}
