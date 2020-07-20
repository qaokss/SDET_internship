package ui.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

   private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    private String baseUrl = "https://www.google.com/intl/ru/gmail/about/#";


    public void loginPage() {
        wd.get(baseUrl);
        wd.findElement(By.linkText("Войти")).click();
    }
}
