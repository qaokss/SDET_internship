package ui.tests;

import api.tests.helpers.TestInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ui.tests.appmanager.BaseHelper;

import java.io.IOException;

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
    @DisplayName("Проверка отправки письма самому себе и сравнение кол-ва писем до и после с определённой темой")
    @TestInfo(preconditions = "С помощью Selenium открыть браузер, открыть gmail.com, авторизоваться, зайти на почту \n" +
            "перейти во «Входящие» и определить, сколько писем с темой из шага 2 этапа подготовки нашлось \n",
            summary = "С помощью Selenium и интерфейса почты автоматически написать и отправить письмо самому себе \n" +
                    "в тексте которого указать найденное количество писем"
    )
    public void countMassagesTest() throws IllegalMonitorStateException, IOException {


        goTo().loginPage();

        loginWithCorrectLoginAndPassword();

        long countBeforeSendingLetter = countMessagesWithTheme("Simbirsoft theme");

        writingLetterToMyself(countBeforeSendingLetter);

        waitNewMessages();

        long countAfterSendingLetter = countMessagesWithTheme("Simbirsoft theme");

        assertEquals(countBeforeSendingLetter + 1, countAfterSendingLetter);
    }


}
