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
        List<WebElement> messages = wd.findElements(By.cssSelector("table[role='grid'] tr[role='row']"));
        return messages;
    }



    protected long countMessagesWithTheme(String theme) {
        List<WebElement> messages = findAllMessagesOnPage();
        goTo().inbox();
        return messages.stream().filter(webElement -> webElement.getText().contains(theme)).count();
    }


    protected void login(String mailboxAdress, String password) {
        // переключение на активную вкладку
        ArrayList<String> tabs2 = new ArrayList<>(wd.getWindowHandles());
        wd.switchTo().window(tabs2.get(1));

        // ввод логина
        wd.findElement(By.id("identifierId")).sendKeys(mailboxAdress);

        // далее
        safeClickToElement(By.xpath("//button/span[text()='Далее']"));

        // ввод пароля
        type(By.name("password"), password);

        safeClickToElement(By.xpath("//button/span[text()='Далее']"));
    }

    /**
     * логинимся с корректным логином и паролем
     **/
    protected void loginWithCorrectLoginAndPassword() throws IOException {
        initProperties();
        login(initProperties().getProperty("mailbox_address"), initProperties().getProperty("mailbox_password"));
    }



    /** пишем письмо самому себе **/
    protected void writingLetterToMyself(long countBeforeSendingLetter) throws IOException {
        initProperties();
        wd.findElement(By.xpath("//div[text()='Написать']")).click();

        type(By.name("to"), initProperties().getProperty("mailbox_address"));

        type(By.name("subjectbox"), "Simbirsoft theme");

        type(By.cssSelector("div[role='textbox']"), "Найдено " + countBeforeSendingLetter + " писем");

        wd.findElement(By.xpath("//div[text()='Отправить']")).click();

    }


    protected void waitNewMessages() {
        Wait<WebDriver> fluentWait = new FluentWait<>(wd)
                .ignoring(StaleElementReferenceException.class)
                .pollingEvery(Duration.of(500, MILLIS))
                .withTimeout(Duration.of(10, SECONDS))
                .withMessage("not found");

        fluentWait.until(webDriver -> webDriver.findElement(By.cssSelector("div[role='tabpanel']")));
    }
}
