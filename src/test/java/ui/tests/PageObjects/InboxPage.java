package ui.tests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.tests.appmanager.BaseHelper;

import java.io.IOException;
import java.util.List;

public class InboxPage extends BaseHelper{

    public static final By MAILS_TABLE = By.cssSelector("table[role='grid'] tr[role='row']");
    public static final By WRITE_NEW_EMAIL = By.xpath("//div[text()='Написать']");
    public static final By THEME_FIELD = By.name("subjectbox");
    public static final By TEXT_FIELD = By.cssSelector("div[role='textbox']");
    public static final By SEND_MESSAGE = By.xpath("//div[text()='Отправить']");
    public static final By RECIEVER = By.name("to");

    public static int countMessagesWithTheme(String theme) {
        List<WebElement> messages = findAllMessagesOnPage();
        goTo().inbox();
        return (int)messages.stream().filter(webElement -> webElement.getText().contains(theme)).count();
    }

    /**
     * поиск всех писем на странице
     **/
    protected static List<WebElement> findAllMessagesOnPage() {
        List<WebElement> messages = wd.findElements(MAILS_TABLE);
        return messages;
    }


    /**
     * пишем письмо самому себе
     **/
    public static void writingLetterToMyself(long countBeforeSendingLetter) throws IOException {
        initProperties();
        wd.findElement(WRITE_NEW_EMAIL).click();

        type(RECIEVER, initProperties().getProperty("mailbox_address"));

        type(THEME_FIELD, "Simbirsoft theme");

        type(TEXT_FIELD, "Найдено " + countBeforeSendingLetter + " писем");

        wd.findElement(SEND_MESSAGE).click();

    }


    /**
     * ожидание новых писем
     */
    public static void waitNewMessages(Integer number) {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(MAILS_TABLE, number));
    }
}
