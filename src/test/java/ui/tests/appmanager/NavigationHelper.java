package ui.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NavigationHelper {

    private WebDriver wd;

    @FindBy(linkText = "Войти")
    public static WebElement        ENTER;

    public static final String      BASE_URL    = "https://www.google.com/intl/ru/gmail/about/#";
    public static final String      INBOX       = "https://mail.google.com/mail/u/0/#inbox";


    public NavigationHelper(WebDriver wd) {
        PageFactory.initElements(wd, this);
        this.wd = wd;
    }

    public void loginPage() {
        wd.get(BASE_URL);
        ENTER.click();
    }

    public void inbox() {
        wd.get(INBOX);
    }
}
