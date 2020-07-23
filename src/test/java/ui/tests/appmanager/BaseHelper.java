package ui.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.SECONDS;

public class BaseHelper extends SessionHelper {


    public static final By MAILBOX_ADDRESS_FIELD = By.id("identifierId");
    public static final By NEXT = By.xpath("//button/span[text()='Далее']");
    public static final By PASSWORD_FIELD = By.name("password");
    public static final By MAILS_TABLE = By.cssSelector("table[role='grid'] tr[role='row']");
    public static final By WRITE_NEW_EMAIL = By.xpath("//div[text()='Написать']");
    public static final By THEME_FIELD = By.name("subjectbox");
    public static final By TEXT_FIELD = By.cssSelector("div[role='textbox']");
    public static final By SEND_MESSAGE = By.xpath("//div[text()='Отправить']");
    public static final By RECIEVER = By.name("to");


    // обходим ошибку "Element is not clickable..." для гугл хром
    private Actions safeClickToElement(By locator) {
        Actions actions = new Actions(wd);
        actions.pause(300).moveToElement(wd.findElement(locator)).click().perform();
        return actions;
    }

    protected void type(By locator, String text) {
        this.safeClickToElement(locator);
        if (text != null) {
            this.wd.findElement(locator).clear();
            this.wd.findElement(locator).sendKeys(text);
        }
    }

    /**
     * поиск всех писем на странице
     **/
    protected List<WebElement> findAllMessagesOnPage() {
        List<WebElement> messages = wd.findElements(MAILS_TABLE);
        return messages;
    }


    protected int countMessagesWithTheme(String theme) {
        List<WebElement> messages = findAllMessagesOnPage();
        goTo().inbox();
        return (int)messages.stream().filter(webElement -> webElement.getText().contains(theme)).count();
    }

    /**
     * Метод логинится с указанным ящиком и паролем
     *
     * @param mailboxAddress
     * @param password
     */
    protected void login(String mailboxAddress, String password) {
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
    protected void loginWithCorrectLoginAndPassword() throws IOException {
        initProperties();
        login(initProperties().getProperty("mailbox_address"), initProperties().getProperty("mailbox_password"));
    }


    /**
     * пишем письмо самому себе
     **/
    protected void writingLetterToMyself(long countBeforeSendingLetter) throws IOException {
        initProperties();
        wd.findElement(WRITE_NEW_EMAIL).click();

        type(RECIEVER, initProperties().getProperty("mailbox_address"));

        type(THEME_FIELD, "Simbirsoft theme");

        type(TEXT_FIELD, "Найдено " + countBeforeSendingLetter + " писем");

        wd.findElement(SEND_MESSAGE).click();

    }


    /**
     * неявное ожидание загрузки страницы
     */
    protected void waitNewMessages(Integer number) {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan( MAILS_TABLE, number));
    }

//    protected void waitNewMessages() {
//        Wait<WebDriver> fluentWait = new FluentWait<>(wd)
//                .ignoring(StaleElementReferenceException.class)
//                .pollingEvery(Duration.of(500, MILLIS))
//                .withTimeout(Duration.of(10, SECONDS))
//                .withMessage("not found");
//
//        fluentWait.until(webDriver -> webDriver.findElement(MAILS_TABLE));
//    }
}
