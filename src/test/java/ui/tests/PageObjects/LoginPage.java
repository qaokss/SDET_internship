package ui.tests.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.tests.appmanager.BaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


public class LoginPage extends BaseHelper {
    @FindBy(id = "identifierId")
    public static WebElement           MAILBOX_ADDRESS_FIELD;

    @FindBy(xpath = "//button/span[text()='Далее']")
    public static WebElement           NEXT;

    @FindBy(name = "password")
    public static WebElement           PASSWORD_FIELD;


    public LoginPage(WebDriver wd) {
        PageFactory.initElements(wd, this);

    }

    /**
     * Метод логинится с указанным ящиком и паролем
     *
     * @param mailboxAddress
     * @param password
     */
    public static void login(String mailboxAddress, String password) {
        // переключение на активную вкладку
        ArrayList<String> tabs2 = new ArrayList<>(wd.getWindowHandles());
        wd.switchTo().window(tabs2.get(1));

        // ввод логина
        MAILBOX_ADDRESS_FIELD.sendKeys(mailboxAddress);

        // далее
        safeClickToElement(NEXT);

        // ввод пароля
        type(PASSWORD_FIELD, password);

        safeClickToElement(NEXT);
    }

    /**
     * Логин с корректным ящиком и паролем, которые берутся из файла .properties
     */
    public static void loginWithCorrectLoginAndPassword() throws IOException {
        Properties properties = initProperties();
        login(properties.getProperty("mailbox_address"), properties.getProperty("mailbox_password"));
    }

}
