package ui.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.tests.appmanager.BaseHelper;

import java.time.Duration;
import java.util.List;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.SECONDS;
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
    public void countMassagesTest() throws IllegalMonitorStateException, InterruptedException {


        goTo().loginPage();

        loginWithCorrectLoginAndPassword();

        // подсчёт писем с темой
        long countBeforeSendingLetter = countMessagesWithTheme("Simbirsoft theme");

        //  пишем письмо самому себе
        writingLetterToMyself(countBeforeSendingLetter);

        // снова считаем письма с темой
        waitNewMessages();

        long countAfterSendingLetter = countMessagesWithTheme("Simbirsoft theme");

        assertEquals(countBeforeSendingLetter + 1, countAfterSendingLetter);
    }


}
