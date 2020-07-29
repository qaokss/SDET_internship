package ui.tests.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.tests.appmanager.BaseHelper;

import java.io.IOException;
import java.util.List;

public class InboxPage extends BaseHelper{

    public static final String locatorForMailsTable = "table[role='grid'] tr[role='row']";
    @FindBy(css = locatorForMailsTable)
    public static  List<WebElement>                     MAILS_TABLE;

    @FindBy(xpath = "//div[text()='Написать']")
    public static  WebElement                          WRITE_NEW_EMAIL;

    @FindBy(name = "subjectbox")
    public static  WebElement                          THEME_FIELD;

    @FindBy(css = "div[role='textbox']")
    public static  WebElement                          TEXT_FIELD;

    @FindBy(xpath = "//div[text()='Отправить']")
    public static  WebElement                          SEND_MESSAGE;

    @FindBy(name = "to")
    public static  WebElement                          RECEIVER;


    public InboxPage(WebDriver wd) {
        PageFactory.initElements(wd, this);
        this.wd = wd;
    }


    public static int countMessagesWithTheme(String theme) {
        List<WebElement> messages = findAllMessagesOnPage();
        goTo().inbox();
        return (int)messages.stream().filter(webElement -> webElement.getText().contains(theme)).count();
    }

    /**
     * поиск всех писем на странице
     **/
    protected static List<WebElement> findAllMessagesOnPage() {
        List<WebElement> messages = MAILS_TABLE;
        return messages;
    }


    /**
     * пишем письмо самому себе
     **/
    public static void writingLetterToMyself(long countBeforeSendingLetter) throws IOException {
        initProperties();
        WRITE_NEW_EMAIL.click();

        type(RECEIVER, initProperties().getProperty("mailbox_address"));

        type(THEME_FIELD, "Simbirsoft theme");

        type(TEXT_FIELD, "Найдено " + countBeforeSendingLetter + " писем");

        SEND_MESSAGE.click();

    }


    /**
     * ожидание новых писем
     */
    public static void waitNewMessages(Integer number) {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(locatorForMailsTable), number));
    }
}
