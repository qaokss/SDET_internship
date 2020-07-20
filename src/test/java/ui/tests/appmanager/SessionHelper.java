package ui.tests.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SessionHelper {

    public WebDriver wd;
    private NavigationHelper navigationHelper;
//    private BaseHelper baseHelper;

    public WebDriver initChromeWebDriver() {
        this.wd = new ChromeDriver();
        this.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return wd;
    }

//        public void login() {
//            baseHelper.type(By.name("user"), "");
//            baseHelper.type(By.name("pass"), "");
//            baseHelper.click(By.xpath("//input[@value='Login']"));
//
//
//        }

    public NavigationHelper goTo() {
        navigationHelper = new NavigationHelper(this.wd);
        return navigationHelper;
    }



}
