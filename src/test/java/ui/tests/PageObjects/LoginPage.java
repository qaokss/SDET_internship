package ui.tests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.tests.appmanager.BaseHelper;
import ui.tests.appmanager.SessionHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginPage extends BaseHelper {
    public static final By MAILBOX_ADDRESS_FIELD = By.id("identifierId");
    public static final By NEXT = By.xpath("//button/span[text()='Далее']");
    public static final By PASSWORD_FIELD = By.name("password");



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
        wd.findElement(MAILBOX_ADDRESS_FIELD).sendKeys(mailboxAddress);

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
        initProperties();
        login(initProperties().getProperty("mailbox_address"), initProperties().getProperty("mailbox_password"));
    }

}
