package ui.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

    private WebDriver wd;


    public static final By ENTER = By.linkText("Войти");
    public static final String BASE_URL = "https://www.google.com/intl/ru/gmail/about/#";
    public static final String INBOX = "https://mail.google.com/mail/u/0/#inbox";



    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }


    public void loginPage() {
        wd.get(BASE_URL);
        wd.findElement(ENTER).click();
    }

    public void inbox() {
        wd.get(INBOX);
    }
}
