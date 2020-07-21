package ui.tests.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SessionHelper {

    public WebDriver wd;
    private NavigationHelper navigationHelper;
//
    private String fileWithProperties;

    public WebDriver initChromeWebDriver() {
      //  properties.load(new FileReader(new File(fileWithProperties)));

        this.wd = new ChromeDriver();
        this.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return wd;
    }


    public NavigationHelper goTo() {
        navigationHelper = new NavigationHelper(this.wd);
        return navigationHelper;
    }



}
