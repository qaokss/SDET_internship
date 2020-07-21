package ui.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.tests.appmanager.BaseHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountMailsTests extends BaseHelper {


    @Before
    public void setUp() {
        initChromeWebDriver();
    }

    @After
    public void tearDown() {
        wd.quit();

    }

    @Test
    public void countMassagesTest() {


        goTo().loginPage();

        loginWithCorrectLoginAndPassword();

        // получаем все письма
        List<WebElement> allMessages = findAllMessagesOnPage();

        // подсчёт писем с темой
        long countBeforeSendingLetter = countMessagesWithTheme("Simbirsoft theme", allMessages);

        //  пишем письмо самому себе
        writingLetterToMyself(countBeforeSendingLetter);

        // снова считаем письма с темой
        long countAfterSendingLetter = countMessagesWithTheme("Simbirsoft theme", allMessages);

        assertEquals(countBeforeSendingLetter + 1, countAfterSendingLetter);
    }


}
